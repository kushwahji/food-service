package com.santosh.ms.cart.helper;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Random;

/**
 * @author santosh.kushwah
 * @since: 12-12-2021
 */

@Component
public class Helper {

	static Random r = new Random();

	private Helper() {

	}

	public static BigDecimal getSubTotalForItem(BigDecimal price, int quantity) {
		return price.multiply(BigDecimal.valueOf(quantity));
	}

}
