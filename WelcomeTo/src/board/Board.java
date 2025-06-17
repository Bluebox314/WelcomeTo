package board;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import cardTable.Card;
import game.Game;

public class Board extends JFrame {
	
	//elements
	private JLabel label = new JLabel(new ImageIcon("C:\\Users\\nathanroy\\Downloads\\welcomeToBoard.png"));
	private JLabel infoBox = new JLabel("SELECT A CARD AND HOUSE", SwingConstants.CENTER);
	private JButton skipButton = new JButton("SKIP");
	private BoardCheckBox[] boxes = new BoardCheckBox[71];
	private BoardNumBox[] nums = new BoardNumBox[53];
	private Fence[] vertFences = new Fence[30];
	private Fence[] horizFences = new Fence[33];
	
	//class connection
	private Game game;
	
	//action performing fields
	private boolean waiting = false;
	private Card selectedCard = null;
	private int selectedHouse = -1;
	
	//constructor/class utility & constructor helpers
	public Board(Game game) {
		//set fields
		this.game = game;
		
		//set some standard JFrame attributes
		setTitle("Welcome To");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add the (invisible) skip button
		skipButton.setLocation(10, 10);
		skipButton.setSize(100, 50);
		skipButton.setBackground(Color.black);
		skipButton.setForeground(Color.white);
		skipButton.setFont(new Font(skipButton.getFont().getName(), skipButton.getFont().getStyle(), 16));
		skipButton.setVisible(false);
		skipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.disableEffectSelectionMode();
				setVisible(false);
			}
		});
		label.add(skipButton);
		
		//add the infoBox
		infoBox.setLocation(195, 7);
		infoBox.setSize(400, 20);
		infoBox.setForeground(Color.red);
		infoBox.setFont(new Font(infoBox.getFont().getName(), infoBox.getFont().getStyle(), 16));
		label.add(infoBox);
		
		//add all the buttons to the label and add it to the frame
		makeVertFences();
		makeHorizFences();
		makeCheckButtons();
		makeTextBoxes();
		add(label);
		
		//size fit and render
		pack();
		setVisible(true);
	}
	private void makeCheckButtons() {
		Map<Integer, ArrayList<BoardCheckBox>> linkedPrep = new HashMap<Integer, ArrayList<BoardCheckBox>>();
		int[] typeList = {1, 1, 1,
						  2, 2, 2, 2,
						  3, 3, 3, 3, 3,
						  4, 4, 4, 4, 4, 4, 4, 4, 4,
						  5, 5, 5, 5, 5, 5, 5, 5, 5,
						  6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
						  7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
						  8, 8, 8, 8, 8, 8, 8, 8, 8,
						  9, 9, 9};
		int[] upperPoolHouses = {2, 6, 7, 10, 13, 17, 22, 27, 31};
		int[] xCoordList = {607, 633, 659, 579, 607, 634, 660, 551, 579, 607, 634, 660,
							278, 490, 552, 118, 278, 498, 123, 387, 600,
							145, 170, 145, 170, 145, 170, 145, 170, 145,
							227, 259, 243, 227, 259, 243, 227, 259, 243, 227, 259,
							327, 364, 364, 398, 398, 398, 434, 434, 434, 434, 471, 471, 471, 471, 505, 505, 505, 505,
							571, 599, 571, 599, 571, 599, 571, 599, 571,
							654, 654, 654};
		int[] yCoordList = { 40,  40,  40, 184, 184, 184, 184, 327, 327, 327, 327, 327,
							 78,  80,  80, 222, 222, 224, 366, 366, 366,
							572, 572, 598, 598, 626, 626, 653, 653, 680,
							560, 560, 575, 592, 591, 606, 622, 621, 637, 652, 652,
							609, 595, 619, 582, 605, 628, 569, 592, 615, 637, 557, 580, 604, 626, 545, 568, 592, 614,
							548, 548, 580, 579, 613, 614, 646, 647, 679,
							592, 625, 656};
		
		for(int i=0; i<boxes.length; i++) {
			boxes[i] = new BoardCheckBox(typeList[i]);
			boxes[i].setLocation(xCoordList[i], yCoordList[i]);
			label.add(boxes[i]);
			
			//linked list assignment prep
			if(!linkedPrep.containsKey(boxes[i].getType()))
				linkedPrep.put(boxes[i].getType(), new ArrayList<BoardCheckBox>());
			
			linkedPrep.get(boxes[i].getType()).add(boxes[i]);
		}
		
		//create upper pool links
		for(int i=0; i<linkedPrep.get(BoardCheckBox.UPPER_POOL_TYPE).size(); i++) {
			linkedPrep.get(BoardCheckBox.UPPER_POOL_TYPE).get(i).setAssociatedHouse(upperPoolHouses[i]);
		}
		
		//instantiate linked lists
		for(int i=1; i<=9; i++) game.getLinkedLists().put(i, new CheckBoxLinkedList(linkedPrep.get(i)));
	}
	private void makeTextBoxes() {
		int[] typeList = {
				0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0,
				0,
				2, 2, 2,  
				3, 3, 3,
				4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
		int[] xCoordList = {167, 221, 275, 329, 382, 437, 489, 543, 597, 649,
							114, 167, 221, 275, 329, 382, 437, 489, 543, 597, 649, 
							 59, 114, 167, 221, 275, 329, 382, 437, 489, 543, 597, 649,
							 22,
							 13, 13,  13,  
							 78, 78,  78,
							 14, 79, 151, 236, 318, 354, 390, 426, 466, 506, 581, 651, 707};
		int[] yCoordList = {100,  99, 108, 100,  99, 100, 108, 108, 100,  99, 
							252, 244, 244, 252, 244, 244, 244, 252, 244, 244, 244, 
							388, 394, 388, 388, 388, 388, 394, 388, 388, 388, 394, 388, 
							134,
							542, 605, 669,
							587, 627, 666,
							725, 725, 725, 725, 725, 725, 725, 725, 725, 725, 725, 725, 725};
			
		
		for(int i=0; i<nums.length; i++) {
			nums[i] = new BoardNumBox(typeList[i], this);
			nums[i].setLocation(xCoordList[i], yCoordList[i]);
			label.add(nums[i]);
		}
		
		//create row fences
		nums[0].setFenced(true);
		nums[10].setFenced(true);
		nums[21].setFenced(true);
		
		//explicitly state the size of the neighborhood name box
		nums[33].setSize(100, 30);
	}
	private void makeVertFences() {
		int[] xCoordList = {205, 259, 314, 367, 421, 475, 528, 581, 634,
							153, 205, 259, 314, 367, 421, 475, 528, 581, 634,
							 99, 153, 205, 259, 315, 367, 421, 475, 528, 581, 634};
		int[] yCoordList = { 76,  76,  76,  76,  76,  76,  76,  76,  76,
							220, 220, 220, 220, 220, 220, 220, 220, 220, 220,
							364, 364, 364, 364, 364, 364, 364, 364, 364, 364, 364};
		
		for(int i=0; i<vertFences.length; i++) {
			vertFences[i] = new Fence(true);
			vertFences[i].setLocation(xCoordList[i], yCoordList[i]);
			label.add(vertFences[i]);
		}
		
	}
	private void makeHorizFences() {
		int[] xCoordList = {158, 211, 265, 319, 373, 427, 480, 533, 586, 639,
							104, 158, 211, 265, 319, 373, 427, 480, 533, 586, 639,
							 50, 104, 158, 211, 265, 319, 373, 427, 480, 533, 586, 639};
		int[] yCoordList = { 68,  68,  68,  68,  68,  68,  68,  68,  68,  68,
							212, 212, 212, 212, 212, 212, 212, 212, 212, 212, 212,
							355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355, 355};

		for(int i=0; i<horizFences.length; i++) {
			horizFences[i] = new Fence(false);
			horizFences[i].setLocation(xCoordList[i], yCoordList[i]);
			label.add(horizFences[i]);
		}
	}
	
	//getters
	public Game getGame() {
		return game;
	}
	public boolean isWaiting() {
		return waiting;
	}
	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}
	public Card getSelectedCard() {
		return selectedCard;
	}
	public void setSelectedCard(Card selectedCard) {
		this.selectedCard = selectedCard;
	}
	public int getSelectedHouse() {
		return selectedHouse;
	}
	public void setSelectedHouse(int selectedHouse) {
		this.selectedHouse = selectedHouse;
	}
	public BoardCheckBox getCheckBoxAt(int index) {
		return boxes[index];
	}
	public BoardNumBox getHouseAt(int index) {
		return nums[index];
	}
	public Fence getVerticalFenceAt(int index) {
		return vertFences[index];
	}
	public Fence getHorizontalFenceAt(int index) {
		return horizFences[index];
	}
	
	//public methods
	public void setInfoBoxText(String text) {
		infoBox.setText(text);
	}
	public void clearSelectedCard() {
		selectedCard = null;
	}
	public void clearSelectedHouses() {
		selectedHouse = -1;
		for(int i=0; i<33; i++) {
			nums[i].setBackground(Color.white);
			nums[i].setSelected(false);
		}
	}
	public void tryAction(Object obj) {
		if(obj instanceof Card) selectedCard = (Card) obj;
		else if(obj instanceof BoardNumBox) selectedHouse = ((BoardNumBox) obj).getID();
		else System.out.println("ERROR IN CALL SOURCE");
		
		if(selectedCard != null && selectedHouse != -1) {
			doCardAction();
		}
	}
	public void doCardAction() {
		//create local versions of variables (abbreviation)
		BoardNumBox house = nums[selectedHouse];
		Card card = selectedCard;
		
		//run proper card type if house is valid for num
		if(game.isValidHousePlacement(card.getNum(), house.getID()) || card.getType() == Card.CONSTRUCTION_TYPE) {
			
			//switch over card type (note that non-automatic effects exit method early)
			switch(card.getType()) {
			
			case Card.POOL_TYPE:
				doPoolAction(house, card);
				break;
			case Card.CONSTRUCTION_TYPE: //learn how to do popups
				doConstructionAction(house, card);
				return;
			case Card.BIS_TYPE: //select another house or skip
				doBisAction(house, card);
				return;
			case Card.PARK_TYPE:
				doParkAction(house, card);
				break;
			case Card.MARKET_TYPE: //select a market val
				doMarketAction(house, card);
				return;
			case Card.FENCE_TYPE: //select a fence
				doFenceAction(house, card);
				return;
			default: //print an error and do nothing
				System.out.println("CARD TYPE ERROR");
				break;
			} //end switch
			
			//set house val
			house.setText(card.getNum()+"");
			game.setHouseAt(house.getID(), card.getNum());
			game.getTable().draw();
			
			//clear selections
			game.getTable().clearSelectedCards();
			clearSelectedHouses();
		
		} else setInfoBoxText("INVALID HOUSE PLACEMENT");
	}
	public void completeEffectSelection() {
		switch(selectedCard.getType()) {
		
		case Card.CONSTRUCTION_TYPE:
			doConstructionAction(nums[selectedHouse], selectedCard);
			break;
		case Card.BIS_TYPE:
			doBisAction(nums[selectedHouse], selectedCard);
			break;
		case Card.MARKET_TYPE:
			doMarketAction(nums[selectedHouse], selectedCard);
			break;
		case Card.FENCE_TYPE:
			doFenceAction(nums[selectedHouse], selectedCard);
			break;
		
		default:
			break;
		}
	}
	
	
	//card actions
	private void doPoolAction(BoardNumBox house, Card card) {
		if(house.getType()==BoardNumBox.POOL_TYPE) {
			for(BoardCheckBox b: boxes) {
				if(b.getAssociatedHouse() == house.getID()) b.setVisible(true);
			}
			game.checkNextBox(BoardCheckBox.LOWER_POOL_TYPE);
		}
	}
	private void doConstructionAction(BoardNumBox house, Card card) { //WIP
		game.checkNextBox(BoardCheckBox.CONSTRUCTION_TYPE);
		
	}
	private void doBisAction(BoardNumBox house, Card card) { //WIP
		if(!game.inEffectSelectionMode()) {
			//set the house val and unhighlight houses
			house.setText(card.getNum()+"");
			game.setHouseAt(house.getID(), card.getNum());
			clearSelectedHouses();
			
			//remove card selection visually but retain variable assignment
			game.getTable().clearSelectedCards();
			setSelectedCard(card);
			
			//handle picking dupe house (or skip)
			setInfoBoxText("SELECT A NUM TO DUPLICATE (OR SKIP)");
			game.enableEffectSelectionMode();
			skipButton.setVisible(true);
			
		} else {
			
			
		}
	}
	private void doParkAction(BoardNumBox house, Card card) {
		if(house.getID()<10) {
			game.checkNextBox(BoardCheckBox.PARK_LVL_1_TYPE);
			
		} else if(house.getID()<21) {
			game.checkNextBox(BoardCheckBox.PARK_LVL_2_TYPE);
			
		} else if(house.getID()<32) {
			game.checkNextBox(BoardCheckBox.PARK_LVL_3_TYPE);
			
		} else System.out.println("HOUSE OUT OF BOUNDS");
	}
	private void doMarketAction(BoardNumBox house, Card card) { //WIP
		setInfoBoxText("SELECT A MARKET UPGRADE");
	}
	private void doFenceAction(BoardNumBox house, Card card) { //WIP
		setInfoBoxText("SELECT A MARKET UPGRADE");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
