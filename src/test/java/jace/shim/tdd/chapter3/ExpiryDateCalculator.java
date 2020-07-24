package jace.shim.tdd.chapter3;

import java.time.LocalDate;

public class ExpiryDateCalculator {
    static final int amountOfMonthly = 10_000;

    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = payData.getPayAmount() / amountOfMonthly;
        if (payData.getFirstBillingDate() != null) {
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
            int firstBillingDateDayOfMonthOf = payData.getFirstBillingDate().getDayOfMonth();
            if (firstBillingDateDayOfMonthOf != candidateExp.getDayOfMonth()) {
                return candidateExp.withDayOfMonth(firstBillingDateDayOfMonthOf);
            }
        }

        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
