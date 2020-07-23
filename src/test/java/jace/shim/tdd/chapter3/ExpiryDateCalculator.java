package jace.shim.tdd.chapter3;

import java.time.LocalDate;

public class ExpiryDateCalculator {
	public LocalDate calculateExpiryDate(PayData payData) {
		return payData.getBillingDate().plusMonths(1);
	}
}
