package materials;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Vendable {
	
	private String productCode;
	private String productName;
	private BigDecimal price;
	private Integer quantity = 5;
	
	
	public Vendable(String productCode, String productName, BigDecimal price) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.price = price.setScale(2, RoundingMode.HALF_UP);;
	}


	public String getProductCode() {
		return productCode;
	}


	public String getProductName() {
		return productName;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public int setQuantity(int quantity) {
		return this.quantity = quantity;
		
	}
	
	public abstract String makeNoise();

	}
	
	


