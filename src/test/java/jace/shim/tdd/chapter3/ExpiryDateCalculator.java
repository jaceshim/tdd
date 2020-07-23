package jace.shim.tdd.chapter3;

import java.time.LocalDate;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = 1;
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
