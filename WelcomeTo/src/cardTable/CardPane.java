package cardTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardPane extends JPanel implements ActionListener{
	
	public static final boolean FRONT_OF_CARD = true;
	public static final boolean BACK_OF_CARD = false;
	
	private Card card;
	private CardTable table;
	private boolean selected = false;
	private JButton numDisplay;
	private JLabel typeIconLabel;
	
	//constructor and constructor resources
	public CardPane(Card card, CardTable table, boolean facing) {
		this.card = card;
		this.table = table;
		typeIconLabel = new JLabel(fetchIcon());
		numDisplay = null;
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(51, 33, 51, 33));
		setBackground(Color.white);
		
		if(facing == FRONT_OF_CARD) {
			setBorder(BorderFactory.createEmptyBorder(38, 33, 38, 33));
			numDisplay = new JButton(card.getNum()+"");
			numDisplay.setBackground(Color.white);
			numDisplay.addActionListener(this);
			add(numDisplay, BorderLayout.CENTER);
		}
		
		add(typeIconLabel, BorderLayout.NORTH);
	}
	private ImageIcon fetchIcon() {
		switch(card.getType()) {
			case 1:
				return new ImageIcon("C:\\Users\\nathanroy\\Pictures\\Screenshots\\pool.png");
			case 2:
				return new ImageIcon("C:\\Users\\nathanroy\\Pictures\\Screenshots\\construction.png");
			case 3:
				return new ImageIcon("C:\\Users\\nathanroy\\Pictures\\Screenshots\\bis.png");
			case 4:
				return new ImageIcon("C:\\Users\\nathanroy\\Pictures\\Screenshots\\park.png");
			case 5:
				return new ImageIcon("C:\\Users\\nathanroy\\Pictures\\Screenshots\\market.png");
			case 6:
				return new ImageIcon("C:\\Users\\nathanroy\\Pictures\\Screenshots\\fence.png");
			default:
				return new ImageIcon("C:\\Users\\nathanroy\\Pictures\\Screenshots\\backgroung.png");
		}
	}
	
	//getters/setters
	public JButton getNumDisplay() {
		return numDisplay;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
	//implemented methods
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!selected) {
			//unselect and unhighlight all buttons
			table.clearSelectedCards();
			
			//select and highlight this button
			setSelected(true);
			numDisplay.setBackground(Color.red);
			
			//tell board a card is selected
			table.getBoardParent().tryAction(card);
			
		//clear selected button if it is clicked again
		} else table.clearSelectedCards();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
