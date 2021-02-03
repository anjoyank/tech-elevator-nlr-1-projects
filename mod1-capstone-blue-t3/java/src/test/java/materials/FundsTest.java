package materials;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class FundsTest {
	//testing a setter is probably not needed, but.....
	
	@Test
	public void zero_plus_one_equals_1() {
	//make Funds object
	Funds testObject = new Funds();
	//create bigDecimal 1
	BigDecimal testInput = new BigDecimal(1.0);
	//add 1 to object
	testObject.setBalance(testInput);
	
	BigDecimal expectedResult = new BigDecimal(1.0);
	BigDecimal actualResult = testObject.getBalance();
	
	//check if 1 == 1;
	assertEquals(expectedResult, actualResult);
	
	}
	
	@Test
	public void five_minus_one_and_fifty_is_three_and_fifty() {
		//make Funds
		Funds testObject = new Funds();
		
		BigDecimal testInput = new BigDecimal(5);
		//add five to wallet to initialize
		testObject.setBalance(testInput);
		BigDecimal testRemove = new BigDecimal(1.5);
		//testing removeMoney method
		//subtract 1.50
		testObject.removeMoney(testRemove);
		
		
		BigDecimal expectedResult = new BigDecimal(3.5);
		BigDecimal actualResult = testObject.getBalance();
		
		assertEquals(expectedResult, actualResult);
		
	}

}
