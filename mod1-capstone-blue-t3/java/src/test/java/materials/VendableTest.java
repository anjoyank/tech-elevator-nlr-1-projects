package materials;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class VendableTest {

	@Test
	public void potato_crisps_cost_three_and_five() {

		BigDecimal newPrice = new BigDecimal(3.05);
		Vendable testObject = new Chip("A1", "BBQ", newPrice);
		BigDecimal expectedResult = new BigDecimal(3.05).setScale(2, RoundingMode.HALF_UP);
		BigDecimal actualResult = testObject.getPrice();

		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void chip_goes_crunch() {
		BigDecimal newPrice = new BigDecimal(3.05);

		Vendable testObject = new Chip("A1", "BBQ", newPrice);
		String expectedResult = "Crunch Crunch, Yum!";
		String actualResult = testObject.makeNoise();

		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void drink_goes_glug() {
		BigDecimal newPrice = new BigDecimal(3.05);

		Vendable testObject = new Drink("A1", "coke", newPrice);
		String expectedResult = "Glug Glug, Yum!";
		String actualResult = testObject.makeNoise();

		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void gum_goes_chew() {
		BigDecimal newPrice = new BigDecimal(3.05);

		Vendable testObject = new Gum("A1", "BBQ", newPrice);
		String expectedResult = "Chew Chew, Yum!";
		String actualResult = testObject.makeNoise();

		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void candy_goes_munch() {
		BigDecimal newPrice = new BigDecimal(3.05);

		Vendable testObject = new Candy("A1", "BBQ", newPrice);
		String expectedResult = "Munch Munch, Yum!";
		String actualResult = testObject.makeNoise();

		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void code_a1_get_code_gets_a1() {
		BigDecimal newPrice = new BigDecimal(3.05);

		Vendable testObject = new Chip("A1", "BBQ", newPrice);
		String expectedResult = "A1";
		String actualResult = testObject.getProductCode();

		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void bbq_returns_bbq() {
		BigDecimal newPrice = new BigDecimal(3.05);

		Vendable testObject = new Chip("A1", "BBQ", newPrice);
		String expectedResult = "BBQ";
		String actualResult = testObject.getProductName();

		assertEquals(expectedResult, actualResult);

	}
	
	@Test
	public void quantity_returns_five() {
		BigDecimal newPrice = new BigDecimal(3.05);

		Vendable testObject = new Chip("A1", "BBQ", newPrice);
		int expectedResult = 5;
		int actualResult = testObject.getQuantity();

		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void quantity_returns_four() {
		BigDecimal newPrice = new BigDecimal(3.05);

		Vendable testObject = new Chip("A1", "BBQ", newPrice);
		int expectedResult = 4;
		int actualResult = testObject.setQuantity(4);

		assertEquals(expectedResult, actualResult);
	}

}
