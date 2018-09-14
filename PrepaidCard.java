//Dennis Heerlein
//111689989
public class PrepaidCard extends BankCard {

	public PrepaidCard(String cardHolder, long cardNumber, double balance) {
		super(cardHolder, cardNumber);
		this.balance = balance;
	}
	
	public PrepaidCard(String cardHolder, long cardNumber) {
		super(cardHolder, cardNumber);
		this.balance = 0;
	}
	
	public boolean addTransaction(Transaction t) {
		if (t.type().equals("debit")) {
			if (t.amount() <= balance()) {
				balance -= t.amount();
				arr.add(t);
				return true;
			}
			else {
				return false;
			}
		}
		else if (t.type().equals("credit")) {
			balance -= t.amount();
			arr.add(t);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addFunds(double amount) {
		if (amount > 0) {
			balance += (amount);
			Transaction t = new Transaction("top-up", "User payment", -1 *amount);
			arr.add(t);
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		return super.toString();
	}
	
	public void printStatement() {
		System.out.println("Name: " + cardHolder() + "  " + toString());
		System.out.print("Transactions:\n\n");
		for (int i = 0; i < arr.size(); i++) {
			if (i == arr.size()-1) {
			System.out.print(arr.get(i).type() + " " + arr.get(i).merchant() + " " + arr.get(i).amount());
			}
			else {
			System.out.print(arr.get(i).type() + " " + arr.get(i).merchant() + " " + arr.get(i).amount() + "\n");
			}
			}
		System.out.println();
		System.out.println("-------------------");
	}
	
}
