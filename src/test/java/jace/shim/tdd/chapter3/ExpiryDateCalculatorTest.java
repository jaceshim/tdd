package jace.shim.tdd.chapter3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {
	@Test
	void 만원_납부하면_한달_뒤가_만료일이_된다() {
		LocalDate billingDate = LocalDate.of(2019, 3, 1);
		int payAmount = 10_000;

		ExpiryDateCalculator expiryDateCalculator = new ExpiryDateCalculator();
		LocalDate expiryDate = expiryDateCalculator.calculateExpiryDate(billingDate, payAmount);

		assertEquals(LocalDate.of(2019, 4, 1), expiryDate);

		LocalDate billingDate2 = LocalDate.of(2019, 5, 5);
		int payAmount2 = 10_000;

		ExpiryDateCalculator expiryDateCalculator2 = new ExpiryDateCalculator();
		LocalDate expiryDate2 = expiryDateCalculator.calculateExpiryDate(billingDate2, payAmount2);

		assertEquals(LocalDate.of(2019, 6, 5), expiryDate2);
	}
}
