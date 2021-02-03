package materials;

import java.math.BigDecimal;

public class Candy extends Vendable {

	public Candy (String productCode, String productName, BigDecimal price) {
		super(productCode, productName, price);
	}

	@Override
	public String makeNoise() {
		return "Munch Munch, Yum!";
		
	}

}
