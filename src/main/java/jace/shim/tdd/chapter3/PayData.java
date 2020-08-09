package jace.shim.tdd.chapter3;

import java.time.LocalDate;

public class PayData {
    private LocalDate billingDate;
    private int payAmount;
    private LocalDate firstBillingDate;

    public PayData() {
    }

    public PayData(LocalDate billingDate, int payAmount, LocalDate firstBillingDate) {
        this.billingDate = billingDate;
        this.payAmount = payAmount;
        this.firstBillingDate = firstBillingDate;
    }

    public LocalDate getFirstBillingDate() {
        return firstBillingDate;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PayData payData = new PayData();

        public Builder firstBillingDate(LocalDate firstBillingDate) {
            payData.firstBillingDate = firstBillingDate;
            return this;
        }

        public Builder billingDate(LocalDate billingDate) {
            payData.billingDate = billingDate;
            return this;
        }

        public Builder payAmount(int payAmount) {
            payData.payAmount = payAmount;
            return this;
        }

        public PayData build() {
            return payData;
        }
    }
}
