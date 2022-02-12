package com.santosh.ms.food.helper;

import org.springframework.stereotype.Component;
import com.santosh.ms.food.entity.OrderItem;
import com.santosh.ms.food.utils.DateConverter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author santosh.kushwah
 * @since: 12-12-2021
 */

@Component
public class Helper {

	static Random r = new Random();

	private Helper() {

	}

	private static int generateRandom(int minimum, int maximum) {
		int rand = r.nextInt(50);
		return (rand * maximum) + minimum;
	}

	public static String generateOrderNumber() {
		return "OD" + DateConverter.convertDateToString(new Date(), "ddMMyyyyhhmmss") + generateRandom(1, 5);
	}

	public static BigDecimal getSubTotalForItem(BigDecimal price, int quantity) {
		return price.multiply(BigDecimal.valueOf(quantity));
	}
}
