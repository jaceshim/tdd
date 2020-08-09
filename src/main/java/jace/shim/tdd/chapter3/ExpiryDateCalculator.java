package jace.shim.tdd.chapter3;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    static final int amountOfMonthly = 10_000;

    public LocalDate calculateExpiryDate(PayData payData) {
        final int payAmount = payData.getPayAmount();
        int addedMonths = payAmount == 100_000 ? 12 : payAmount / amountOfMonthly;
        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        int firstBillingDateDayOfMonthOf = payData.getFirstBillingDate().getDayOfMonth();
        if (firstBillingDateDayOfMonthOf != candidateExp.getDayOfMonth()) {
            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();

            if (dayLenOfCandiMon < payData.getFirstBillingDate().getDayOfMonth()) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(firstBillingDateDayOfMonthOf);
        } else {
            return candidateExp;
        }
    }
}
