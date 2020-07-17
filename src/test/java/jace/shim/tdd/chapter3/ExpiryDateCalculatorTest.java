package jace.shim.tdd.chapter3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {
	@Test
	void 만원_납부하면_한달_뒤가_만료일이_된다() {
		assertExpiryDate(LocalDate.of(2019, 3, 1), 10_000, LocalDate.of(2019,4 ,1));
		assertExpiryDate(LocalDate.of(2019, 5, 5), 10_000, LocalDate.of(2019,6 ,5));
	}

	private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectDate) {
		ExpiryDateCalculator expiryDateCalculator = new ExpiryDateCalculator();
		LocalDate expiryDate = expiryDateCalculator.calculateExpiryDate(billingDate, payAmount);

		assertEquals(expectDate, expiryDate);
	}
}
