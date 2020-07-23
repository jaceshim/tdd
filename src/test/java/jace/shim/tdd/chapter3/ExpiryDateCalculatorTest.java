package jace.shim.tdd.chapter3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {
	@Test
	void 만원_납부하면_한달_뒤가_만료일이_된다() {
		assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 3, 1)).payAmount(10_000).build(), LocalDate.of(2019,4 ,1));

		assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 5, 5)).payAmount(10_000).build(), LocalDate.of(2019,6 ,5));
	}

	@Test
	void 납부일과_한달_뒤_일자가_같지_않음() {
		assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 1, 31)).payAmount(10_000).build(), LocalDate.of(2019, 2, 28));
	}

	@Test
	void 첫_납부일과_만료일의_일자가_같지_않을때_1만원을_납부하면_첫_납부일_기준으로_다음_만요일_정함() {
		PayData payData = PayData.builder().firstBillingDate(LocalDate.of(2019, 1, 31))
				.billingDate(LocalDate.of(2019, 2, 28))
				.payAmount(10_000)
				.build();

		assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

		PayData payData2 = PayData.builder().firstBillingDate(LocalDate.of(2019, 1, 30))
				.billingDate(LocalDate.of(2019, 2, 28))
				.payAmount(10_000)
				.build();

		assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));
	}

	private void assertExpiryDate(PayData payData, LocalDate expectDate) {
		ExpiryDateCalculator expiryDateCalculator = new ExpiryDateCalculator();
		LocalDate expiryDate = expiryDateCalculator.calculateExpiryDate(payData);

		assertEquals(expectDate, expiryDate);
	}
}
