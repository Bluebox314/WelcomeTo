package board;

import javax.swing.*;

public class BoardCheckBox extends JCheckBox {
	//typing standards
	public static final int PARK_LVL_1_TYPE = 1;
	public static final int PARK_LVL_2_TYPE = 2;
	public static final int PARK_LVL_3_TYPE = 3;
	public static final int UPPER_POOL_TYPE = 4;
	public static final int LOWER_POOL_TYPE = 5;
	public static final int CONSTRUCTION_TYPE = 6;
	public static final int MARKET_TYPE = 7;
	public static final int BIS_TYPE = 8;
	public static final int TURN_FAIL_TYPE = 9;
	
	
	//fields
	private static int buttonCount = 0;
	private int associatedHouse = -1;
	private int id;
	private int type;
	
	//constructor
	public BoardCheckBox(int type) {
		this.type = type;
		id = buttonCount;
		buttonCount++;
		
		setOpaque(false);
		setSize(20, 20);
		setVisible(false);
	}
	
	//getters
	public int getID() {
		return id;
	}
	public int getType() {
		return type;
	}
	public int getAssociatedHouse() {
		return associatedHouse;
	}
	public void setAssociatedHouse(int associatedHouse) {
		this.associatedHouse = associatedHouse;
	}
	
	//toString
	public String toString() {
		return "BOX#"+id;
	}
	
	//public methods
	public void nonVisualDisable() {
		setModel(new DefaultButtonModel() {
			@Override
			public boolean isSelected() {
				return true;
			}
			@Override
			public void setSelected(boolean b) {
				//button cannot have state changed
			}
		});
		setFocusable(false);
	}
	
	
	
	
	
	
	
}
