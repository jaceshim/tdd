package jace.shim.tdd.chapter3;

import java.time.LocalDate;

public class ExpiryDateCalculator {
	public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
		final PayData payData = PayData.builder().billingDate(billingDate).payAmount(payAmount).build();

		return calculateExpiryDate(payData);
	}

	public LocalDate calculateExpiryDate(PayData payData) {
		return payData.getBillingDate().plusMonths(1);
	}
}
