package game;

import java.util.ArrayList;
import java.util.HashMap;

import board.*;
import cardTable.*;

@SuppressWarnings("unused")
public class Game {
	//main method
	public static void main(String[] args) {
		Game game = new Game();
	}
	
	//================CLASS================
	
	//windows
	private Board board;
	private CardTable table;
	
	//fields
	private int[] houses = new int[33];
	private HashMap<Integer, CheckBoxLinkedList> linkedLists;
	private boolean effectSelectionMode = false;
	private int score = 0;
	
	//constructor/class resources
	public Game() {
		linkedLists = new HashMap<Integer, CheckBoxLinkedList>();
		board = new Board(this);
		table = new CardTable(board);
		
		setDefaultHouseValue();
	}
	private void setDefaultHouseValue() {
		for(int i=0; i<houses.length; i++) {
			houses[i] = -5;
		}
	}
	
	//getters/setters
	public Board getBoard() {
		return board;
	}
	public CardTable getTable() {
		return table;
	}
	public int[] getHouses() {
		return houses;
	}
	public void setHouses(int[] houses) {
		this.houses = houses;
	}
	public void setHouseAt(int index, int num) {
		houses[index] = num;
	}
	public boolean inEffectSelectionMode() {
		return effectSelectionMode;
	}
	public void enableEffectSelectionMode() {
		effectSelectionMode = true;
		table.disable();
		
	}
	public void disableEffectSelectionMode() {
		effectSelectionMode = false;
		table.enable();
	}
	public HashMap<Integer, CheckBoxLinkedList> getLinkedLists() {
		return linkedLists;
	}
	public void setLinkedLists(HashMap<Integer, CheckBoxLinkedList> linkedLists) {
		this.linkedLists = linkedLists;
	}
	
	//public methods
	public boolean isValidHousePlacement(int num, int index) {
		if(index<=9) {
			for(int i=0; i<index; i++) 
				if(houses[i]>=num) 
					return false;
			for(int i=index+1; i<10; i++) 
				if(houses[i]<=num && houses[i]!=-5) 
					return false;	
			
		} else if(index<=20) {
			for(int i=10; i<index; i++) 
				if(houses[i]>=num) 
					return false;
			for(int i=index+1; i<20; i++) 
				if(houses[i]<=num && houses[i]!=-5) 
					return false;
			
		} else {
			for(int i=21; i<index; i++) 
				if(houses[i]>=num) 
					return false;
			for(int i=index+1; i<33; i++) 
				if(houses[i]<=num && houses[i]!=-5) 
					return false;
		}
		
		return true;
	}
	public int findHouseRow(int index) {
		if(0<=index && index<=9) return 1;
		else if(10<=index && index<=20) return 2;
		else if(21<=index && index<=32) return 3;
		else return -1;
	}
	public void checkNextBox(int index) {
		CheckBoxLinkedList link = linkedLists.get(index);
		
		while(link.getNext() != null && link.getElement().isVisible()) {
			link = link.getNext();
		}
		
		link.getElement().setVisible(true);
		link.getElement().nonVisualDisable();
	}
	public void showNextBox(int index) {
		CheckBoxLinkedList link = linkedLists.get(index);
		
		while(link.getNext() != null && link.getElement().isVisible()) {
			link = link.getNext();
		}
		
		link.getElement().setVisible(true);
	}
	public boolean hasAdjacentClearHouse(int index) {
		if(index==0 && !board.getHouseAt(index+1).isFenced() && board.getHouseAt(index+1).getText().isEmpty()) {
			return true;
		} else if(index==32 && !board.getHouseAt(index).isFenced() && board.getHouseAt(index-1).getText().isEmpty()) {
			return true;
		} else if(!board.getHouseAt(index).isFenced() && board.getHouseAt(index-1).getText().isEmpty()) {
			return true;
		} else if(!board.getHouseAt(index+1).isFenced() && board.getHouseAt(index+1).getText().isEmpty()) {
			return true;
		}
		
		return false;
	}
	
	
	public void updateScore() { //WIP
		
	}
}















