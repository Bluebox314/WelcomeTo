package cardTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import board.Board;

public class CardTable extends JFrame {
	//table info
	private Board boardParent;
	private ArrayList<Card> deck;
	private Card[][] cardList = new Card[3][2];
	
	//format tools
	private JPanel[] padding = new JPanel[2];
	private JPanel space = new JPanel();
	
	//constructor and class/constructor resources
	public CardTable(Board parent) {
		//set instance variables
		this.boardParent = parent;
		deck = Card.makeDeck();
		
		//set up window
		setTitle("Welcome To Cards");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.black);
		setLayout(new GridBagLayout());
		setLocation(735, 0);
		setSize(400, 800);
		
		//make/add components
		createSpacing();
		addCardPanes();
		
		setVisible(true);
	}
	private void createSpacing() {
		GridBagConstraints g = new GridBagConstraints();
		
		space.setBackground(Color.black);
		space.setLayout(new BorderLayout());
		space.setBorder(BorderFactory.createEmptyBorder(700, 114, 0, 0));
		
		for(int i=0; i<padding.length; i++) {
			padding[i] = new JPanel();
			padding[i].setBackground(Color.black);
			padding[i].setLayout(new BorderLayout());
			padding[i].setBorder(BorderFactory.createEmptyBorder(100, 350, 0, 0));
		}
		
		//add white space
		g.gridx = 1;
		g.gridy = 0;
		g.gridheight = 5;
		add(space, g);
		g.gridheight = 1;

		g.gridwidth = 3;
		g.gridx = 0;
		g.gridy = 1;
		add(padding[0], g);
		
		g.gridx = 0;
		g.gridy = 3;
		add(padding[1], g);
	}
	private void doFirstDraw() {
		for(int i=0; i<3; i++) {
			for(int k=0; k<2; k++) {
				cardList[i][k] = drawSingle();
			}
		}
	}
	private Card drawSingle() {
		return deck.remove(0).makeFaces(this);
	}
	private void addCardPanes() {
		GridBagConstraints g = new GridBagConstraints();
		doFirstDraw();
		g.gridx = 0;
		for(int y=0; y<5; y+=2) {
			g.gridy = y;
			add(cardList[y/2][0].getFrontFace(), g);
		}
		g.gridx = 2;
		for(int y=0; y<5; y+=2) {
			g.gridy = y;
			add(cardList[y/2][1].getBackFace(), g);
		}
	}
	
	//getters/setters
	public Board getBoardParent() {
		return boardParent;
	}
	
	//public methods
	public void reRender() {
		GridBagConstraints g = new GridBagConstraints();
		g.gridx = 0;
		
		setVisible(false);
		for(int y=0; y<5; y+=2) {
			g.gridy = y;
			add(cardList[y/2][0].getFrontFace(), g);
		}
		g.gridx = 2;
		for(int y=0; y<5; y+=2) {
			g.gridy = y;
			add(cardList[y/2][1].getBackFace(), g);
		}
		setVisible(true);
	}
	public void clearSelectedCards() {
		boardParent.clearSelectedCard();
		for(int i=0; i<cardList.length; i++) {
			cardList[i][0].getFrontFace().getNumDisplay().setBackground(Color.white);
			cardList[i][0].getFrontFace().setSelected(false);
		}
	}
	public void draw() {
		for(int i=0; i<3; i++) {
			//remove old panes from window
			remove(cardList[i][0].getFrontFace());
			remove(cardList[i][1].getBackFace());
			
			//update deck with removed cards
			deck.add(cardList[i][0]);
			
			//update cardList
			cardList[i][0] = cardList[i][1];
			cardList[i][1] = drawSingle();
		}
		//rerender the new components
		reRender();
	}
	public void disable() {
		for(int i=0; i<cardList.length; i++) {
			cardList[i][0].getFrontFace().getNumDisplay().setEnabled(false);
		}
	}
	public void enable() {
		for(int i=0; i<cardList.length; i++) {
			cardList[i][0].getFrontFace().getNumDisplay().setEnabled(true);
		}
	}























}
