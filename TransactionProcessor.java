// Driver class for the final project

//Dennis Heerlein
//111689989
import java.util.*;
import java.io.*;

public class TransactionProcessor
{
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the card data file: ");
		String fName = input.next();
		CardList list = loadCardData(fName);
		System.out.print("\nEnter a transaction data file: ");
		String tfName = input.next();
		System.out.println();
		try {
			processTransactions(tfName, list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < list.size(); i++) {
			list.get(i).printStatement();
			
		}
		
	}
	
	
	
	private static String getCardType (long number)
	{
		// Return a String indicating whether 'number' belongs to a 
		// CreditCard, RewardsCard, or a PrepaidCard (or null if it's none
		// of the three)
		
		String result;
		
		int firstTwo = Integer.parseInt(("" + number).substring(0,2));
		
		switch(firstTwo)
		{
			case 84:
			case 85: result = "CreditCard"; break;
			case 86:
			case 87: result = "RewardsCard"; break;
			case 88:
			case 89: result = "PrepaidCard"; break;
			default: result = null; // invalid card number
		}
		
		return result;
	}
	
	public static BankCard convertToCard(String data) {
		Scanner input = new Scanner(data);
		long cardNumber = input.nextLong();
		String type = getCardType(cardNumber);
		String name = (input.next().replace('_', ' '));
		if (type.equals("CreditCard")) {
			int expiration = input.nextInt();
			if (input.hasNextDouble()) {
				double limit = input.nextDouble();
				CreditCard n = new CreditCard(name, cardNumber, expiration, limit);
				return n;
			}
			else {
			CreditCard n = new CreditCard(name, cardNumber, expiration);
			return n;
			}
		}
		else if(type.equals("RewardsCard")) {
			int expiration = input.nextInt();
			if (input.hasNextDouble()) {
				double limit = input.nextDouble();
				RewardsCard n = new RewardsCard(name, cardNumber, expiration, limit);
				return n;
			}
			else {
				RewardsCard n = new RewardsCard(name, cardNumber, expiration);
				return n;
			}
		}
		else {
			if (input.hasNextDouble()) {
				double balance = input.nextDouble();
				PrepaidCard n = new PrepaidCard(name, cardNumber, balance);
				return n;
			}
			else {
				PrepaidCard n = new PrepaidCard(name, cardNumber);
				return n;
			}
		}
	}
	
	public static CardList loadCardData(String fName) {
		CardList n = new CardList();
		File f = new File(fName);
		try {
			Scanner input = new Scanner(f);
			while (input.hasNext()) {
				n.add(convertToCard(input.nextLine()));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return n;
	}
	
	public static void processTransactions(String filename, CardList c) throws IOException {
		Scanner input = new Scanner(new File(filename));
		while(input.hasNextLine()) {
			String[] arr = (input.nextLine()).split("\\s+");
			Long cardNumber = Long.parseLong(arr[0]);
			int index = c.indexOf(cardNumber);
			if(arr[1].equals("redeem")) {
				int points = Integer.parseInt(arr[2]);
				RewardsCard n = (RewardsCard)c.get(index);
				n.redeemPoints(points);
			}
			else if (arr[1].equals("top-up")) {
				double funds = Double.parseDouble(arr[2]);
				PrepaidCard n = (PrepaidCard)c.get(index);
				n.addFunds(funds);
			}
			else {
				Transaction n = new Transaction(arr[1],arr[3].replace('_', ' '),Double.parseDouble(arr[2]));
				BankCard r = c.get(index);
				r.addTransaction(n);
			}
		}
	}
}