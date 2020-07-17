package jace.shim.tdd.chapter3;

import java.time.LocalDate;

public class ExpiryDateCalculator {
	public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
		return billingDate.plusMonths(1);
	}
}
