package board;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Fence extends JButton implements ActionListener {
	//IDing resources
	private static int count = 0;
	private int id;
	
	//stores color status
	private boolean on;
	
	//position storage in vertical mode
	private int column;
	private int row;
	
	//position storage in horizontal mode
	private int house;
	
	//constructor
	public Fence(boolean vert) {
		id = count;
		count++;
		
		addActionListener(this);
		setBackground(Color.white);
		setEnabled(false);
		
		if(vert) setSize(7, 83);
		else setSize(50, 7);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("ID: "+id+"\n"+getX()+", "+getY());
		if(on) setBackground(Color.white);
		else {
			setBackground(Color.black);
			on = true;
		}
	}

}
