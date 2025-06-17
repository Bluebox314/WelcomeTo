package cardTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Card {
	//typing standards
	public static final int POOL_TYPE = 1;
	public static final int CONSTRUCTION_TYPE = 2;
	public static final int BIS_TYPE = 3;
	public static final int PARK_TYPE = 4;
	public static final int MARKET_TYPE = 5;
	public static final int FENCE_TYPE = 6;
	
	//card fields
	private int num;
	private int type;
	private CardPane[] faces = null;
	
	//constructors
	public Card(int num, int type) {
		this.num = num;
		this.type = type;
	}
	public Card(int num) {
		this.num = num;
	}
	
	//getters
	public int getNum() {
		return num;
	}
	public int getType() {
		return type;
	}
	public CardPane[] getFaces() {
		return faces;
	}
	public CardPane getFrontFace() {
		return faces[0];
	}
	public CardPane getBackFace() {
		return faces[1];
	}
	public String toString() {
		return "num = "+num+"\n"
			 + "type = "+type;
	}
	
	public Card makeFaces(CardTable owner) {
		CardPane front = new CardPane(this, owner, CardPane.FRONT_OF_CARD);
		CardPane back = new CardPane(this, owner, CardPane.BACK_OF_CARD);
		faces = new CardPane[] {front, back};
		return this;
	}
	public boolean hasFaces() {
		return faces != null;
	}
	
	/* NUMERICALS
	 * 3x - 1, 2, 14, 15
	 * 4x - 3, 13
	 * 5x - 4, 12
	 * 6x - 5, 11
	 * 7x - 6, 10
	 * 8x - 7, 9
	 * 9x - 8
	 * TYPES
	 * 9x - pools, constructions, bis's
	 * 18x - fences, markets, parks
	 * pool=1, construction=2, bis=3, park=4, market=5, fence=6
	 */
 	public static ArrayList<Card> makeDeck() {
		ArrayList<Card> deck = new ArrayList<Card>();
		
		//not eloquent, but readable
		for(int i=0; i<3; i++) deck.add(new Card(1));
		for(int i=0; i<3; i++) deck.add(new Card(2));
		for(int i=0; i<3; i++) deck.add(new Card(14));
		for(int i=0; i<3; i++) deck.add(new Card(15));
		for(int i=0; i<4; i++) deck.add(new Card(3));
		for(int i=0; i<4; i++) deck.add(new Card(13));
		for(int i=0; i<5; i++) deck.add(new Card(4));
		for(int i=0; i<5; i++) deck.add(new Card(12));
		for(int i=0; i<6; i++) deck.add(new Card(5));
		for(int i=0; i<6; i++) deck.add(new Card(11));
		for(int i=0; i<7; i++) deck.add(new Card(6));
		for(int i=0; i<7; i++) deck.add(new Card(10));
		for(int i=0; i<8; i++) deck.add(new Card(7));
		for(int i=0; i<8; i++) deck.add(new Card(9));
		for(int i=0; i<9; i++) deck.add(new Card(8));
		
		ArrayList<Integer> typeList = new ArrayList<Integer>();
		for(int i=1; i<=6; i++) {
			if(i<=3)
				typeList.addAll(Arrays.asList(new Integer[] {i,i,i,i,i,i,i,i,i}));
			else
				typeList.addAll(Arrays.asList(new Integer[] {i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i,i}));
		}
		
		Collections.shuffle(deck);

		for(int i=0; i<deck.size(); i++) 
			deck.get(i).type = typeList.remove((int)(Math.random()*typeList.size()));
		
		return deck;
	}
	
	
	
	
	
	
	
}
