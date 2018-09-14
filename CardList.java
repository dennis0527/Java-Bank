//Dennis Heerlein
//111689989
import java.util.ArrayList;

public class CardList {
	private ArrayList<BankCard> arrB;
	
	public CardList() {
		arrB = new ArrayList<>();
	}
	
	public void add(BankCard b) {
		arrB.add(b);
	}
	
	public void add(int index, BankCard b) {
		arrB.add(index, b);
	}
	
	public int size() {
		return arrB.size();
	}
	
	public BankCard get(int index) {
		if (index < 0 || index >= arrB.size()) {
			return null;
		}
		
		else {
			return arrB.get(index);
		}
	}
	
	public int indexOf (long cardNumber) {
		for(int i = 0; i < arrB.size(); i++) {
			if (cardNumber == arrB.get(i).number()) {
				return i;
			}
		}
			return -1;
	}
}
