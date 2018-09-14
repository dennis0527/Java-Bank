//Dennis Heerlein
//111689989
public class RewardsCard extends CreditCard {
	protected int points;
	
	public RewardsCard(String holder, long number, int expiration, double limit) {
		super(holder, number, expiration, limit);
		points = 0;
	}
	
	public RewardsCard(String holder, long number, int expiration) {
		super(holder, number, expiration);
		points = 0;
	}
	
	public int rewardPoints() {
		return points;
	}
	
	public boolean redeemPoints(int points) {
		if (points < rewardPoints()) {
			if (balance - points/100.00 < 0) {
				int actualPoints = (int)(balance * 100.00);
				this.points -= actualPoints;
				balance = 0;
				Transaction r1 = new Transaction("redemption","CSE Bank", actualPoints / 100.00);
				arr.add(r1);
				return true;
			}
			else { 
			balance -= points/100.00;
			this.points -= points;
			Transaction r1 = new Transaction("redemption","CSE Bank", points/100.00);
			arr.add(r1);
			return true;
			}
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		return super.toString() + "  Reward Points: " + rewardPoints();
	}
	
	public boolean addTransaction(Transaction t) {
		if (t.type().equals("debit")) {
			if (t.amount() <= availableCredit()) {
				balance += t.amount();
				arr.add(t);
				points += (int)(t.amount() * 100);
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
