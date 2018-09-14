//Dennis Heerlein
//111689989
import java.util.ArrayList;

public abstract class BankCard {
	private String cardholderName;
	protected long cardNumber;
	protected double balance;
	protected ArrayList<Transaction> arr = new ArrayList<>();
	
	public BankCard(String cardholderName, long cardNumber) {
		this.cardholderName = cardholderName;
		this.cardNumber = cardNumber;
	}
	
	public double balance() {
		return balance;
	}
	
	public long number() {
		return cardNumber;
	}
	
	public String cardHolder() {
		return cardholderName;
	}
	
	public String toString() {
		return "Card # " + cardNumber + "  Balance: $" + ((int)(balance*100))/100.0;
	}
	
	public abstract boolean addTransaction(Transaction t);
	
	public abstract void printStatement();
}
