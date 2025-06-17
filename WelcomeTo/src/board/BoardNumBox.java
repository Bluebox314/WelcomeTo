package board;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

public class BoardNumBox extends JTextField implements MouseListener {
	//typing standards
	public static final int POOL_TYPE = 1;
	public static final int TASK_TYPE = 2;
	public static final int PARK_TYPE = 3;
	public static final int CALC_ENTER_TYPE = 4;
	private int type;
	
	//fields
	private static int numBoxCount = 0;
	private Board parent;
	private boolean selected = false;
	private boolean fenced = false;
	private int id;
	
	//constructor
	public BoardNumBox(int type, Board parent) {
		this.setType(type);
		this.parent = parent;
		id = numBoxCount;
		numBoxCount++;
		
		setOpaque(true);
		setSize(30, 30);
		setEnabled(false);
		addMouseListener(this);
		setFont(new Font(getFont().getName(), getFont().getStyle(), 16));
	}
	
	//getters/setters
	public int getID() {
		return id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isFenced() {
		return fenced;
	}
	public void setFenced(boolean fenced) {
		this.fenced = fenced;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	//used implemented methods
	@Override
	public void mouseClicked(MouseEvent e) {
		if(parent.getGame().inEffectSelectionMode()) {
			
			
			
			
			
		} else if(id<33 && getText().isEmpty()) {
			//clear all houses if the current selected house is clicked again
			if(selected) {
				parent.clearSelectedHouses();
				return;
			}
			
			//unselect and unhighlight all houses
			parent.clearSelectedHouses();
			
			//select and highlight this house
			setSelected(true);
			setBackground(Color.red);
			
			//tell board a house is selected
			parent.tryAction(this);
		}
	}

	//unused implemented methods
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
