package materials;

import java.math.BigDecimal;

public class Drink extends Vendable {

	public Drink (String productCode, String productName, BigDecimal price) {
		super(productCode, productName, price);
	}
	@Override
	public String makeNoise() {
		return "Glug Glug, Yum!";
		
	}
}
