package com.workfusion.project.helper;

import java.math.BigDecimal;
import java.util.Random;

public final class HelperValue {

	public static long getValue() {
		Random random = new Random();
		long randomNumber = (random.nextLong());
		if (randomNumber < 0) {
			randomNumber = randomNumber * -1;
		}
		return randomNumber;
	}

	public static long createMeanValue(long maxValue, long minValue) {
		BigDecimal maxValueBD = new BigDecimal(maxValue);
		BigDecimal minValueBD = new BigDecimal(minValue);
		BigDecimal sum = maxValueBD.add(minValueBD);
		BigDecimal result = sum.divide(new BigDecimal(2));
		return result.longValue();
	}
}
