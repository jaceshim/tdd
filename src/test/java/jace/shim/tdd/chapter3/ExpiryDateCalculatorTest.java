package jace.shim.tdd.chapter3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ExpiryDateCalculatorTest {
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_된다() {
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 3, 1)).payAmount(10_000).build(), LocalDate.of(2019, 4, 1));

        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 5, 5)).payAmount(10_000).build(), LocalDate.of(2019, 6, 5));
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


    @ParameterizedTest
    @MethodSource("provideOverAmount")
    void 이만원_이상_납부하면_납부금액에_비례해서_만료일_계산(LocalDate billingDate, int payAmount, LocalDate expectDate) {
        PayData payData = PayData.builder()
                .billingDate(billingDate)
                .payAmount(payAmount)
                .build();

        assertExpiryDate(payData, expectDate);
    }

    @Test
    void 첫_납부일과_만료일자가_다를때_이만원_이상_납부() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(20_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2019, 4, 30));
    }

    private static Stream<Arguments> provideOverAmount() {
        return Stream.of(
                Arguments.arguments(LocalDate.of(2019, 3, 1), 20_000, LocalDate.of(2019, 5, 1)),
                Arguments.arguments(LocalDate.of(2019, 3, 1), 30_000, LocalDate.of(2019, 6, 1)),
                Arguments.arguments(LocalDate.of(2019, 3, 1), 40_000, LocalDate.of(2019, 7, 1)),
                Arguments.arguments(LocalDate.of(2019, 5, 1), 50_000, LocalDate.of(2019, 10, 1)),
                Arguments.arguments(LocalDate.of(2019, 7, 1), 20_000, LocalDate.of(2019, 9, 1))
        );
    }


    private void assertExpiryDate(PayData payData, LocalDate expectDate) {
        ExpiryDateCalculator expiryDateCalculator = new ExpiryDateCalculator();
        LocalDate expiryDate = expiryDateCalculator.calculateExpiryDate(payData);

        assertEquals(expectDate, expiryDate);
    }
}
