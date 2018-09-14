//Dennis Heerlein
//111689989
public class CreditCard extends BankCard {
	private int expiration;
	protected double limit;
	
	public CreditCard(String cardHolder, long cardNumber, int expiration, double limit) {
		super(cardHolder, cardNumber);
		this.expiration = expiration;
		this.limit = limit;
	}
	
	public CreditCard(String cardHolder, long cardNumber, int expiration) {
		super(cardHolder, cardNumber);
		this.expiration = expiration;
		limit = 500;
	}
	
	public double limit() {
		return limit;
	}
	
	public double availableCredit() {
		return limit - balance();
	}
	
	public int expiration() {
		return expiration;
	}
	
	public String toString() {
		return super.toString() + "  Expiration date: " + expiration;
	}
	

	public boolean addTransaction(Transaction t) {
		if (t.type().equals("debit")) {
			if (t.amount() <= availableCredit()) {
				balance += t.amount();
				arr.add(t);
				return true;
			}
			else {
				return false;
			}
		}
		else if (t.type().equals("credit")) {
			balance += t.amount();
			arr.add(t);
			return true;
		}
		else {
			return false;
		}
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
