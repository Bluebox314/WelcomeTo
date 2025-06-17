package board;

import java.util.ArrayList;

public class CheckBoxLinkedList {
	
	//fields
	private BoardCheckBox current;
	private CheckBoxLinkedList next;
	
	//constructors
	public CheckBoxLinkedList(BoardCheckBox current, CheckBoxLinkedList next) {
		this.current = current;
		this.next = next;
	}
	public CheckBoxLinkedList(ArrayList<BoardCheckBox> list) {
		current = list.get(0);
		next = constructorHelper(list, 1);
	}
	public CheckBoxLinkedList(BoardCheckBox[] array) {
		current = array[0];
		next = constructorHelper(array, 1);
	}
	
	//constructor helpers
	private CheckBoxLinkedList constructorHelper(ArrayList<BoardCheckBox> list, int index) {
		if(index == list.size()) 
			return null;
		else 
			return new CheckBoxLinkedList(list.get(index), constructorHelper(list, index+1));
	}
	private CheckBoxLinkedList constructorHelper(BoardCheckBox[] array, int index) {
		if(index == array.length) 
			return null;
		else 
			return new CheckBoxLinkedList(array[index], constructorHelper(array, index+1));
	}
	
	//getters
	public BoardCheckBox getElement() {
		return current;
	}
	public CheckBoxLinkedList getNext() {
		return next;
	}

	//toString
	public String toString() {
		return "Element="+getElement()+" Next="+getNext();
	}
}
