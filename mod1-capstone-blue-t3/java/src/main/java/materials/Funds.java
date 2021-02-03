package materials;

import java.math.BigDecimal;

public class Funds {
	
	private BigDecimal balance = new BigDecimal(0);

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal amountAdded) {
		this.balance = this.balance.add(amountAdded);
	}
	public void removeMoney(BigDecimal amountSubtracted) {
		this.balance = this.balance.subtract(amountSubtracted);
	}
	
	public String getChange() {
		String changeString = "Your change is: ";
		BigDecimal hundred = new BigDecimal(100);
		BigDecimal changeBigDec = this.balance.multiply(hundred);
		int numOfQuarters = 0, numOfDimes = 0, numOfNickels = 0;
		int changeInt = changeBigDec.intValue();
		while (changeInt / 25 >= 1) {
			numOfQuarters = changeInt / 25;
			changeInt = changeInt % 25;
			
			changeString = changeString + numOfQuarters + " Quarters ";
		} while (changeInt / 10 >= 1) {
			numOfDimes = changeInt / 10;
			changeInt = changeInt % 10;
			
			changeString = changeString  + numOfDimes + " Dimes ";
		} while (changeInt / 5 >= 1) {
			numOfNickels = changeInt / 5;
			changeInt = changeInt % 5;
			
			changeString = changeString + numOfNickels + " Nickels ";
		}
		
		return changeString.trim();
		
	}

}
