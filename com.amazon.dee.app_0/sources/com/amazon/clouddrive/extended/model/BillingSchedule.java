package com.amazon.clouddrive.extended.model;
/* loaded from: classes11.dex */
public class BillingSchedule implements Comparable<BillingSchedule> {
    private Period billingPeriod;
    private String currencyCode;
    private String price;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BillingSchedule) && compareTo((BillingSchedule) obj) == 0;
    }

    public Period getBillingPeriod() {
        return this.billingPeriod;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public String getPrice() {
        return this.price;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getBillingPeriod() == null ? 0 : getBillingPeriod().hashCode()) + 1 + (getCurrencyCode() == null ? 0 : getCurrencyCode().hashCode());
        if (getPrice() != null) {
            i = getPrice().hashCode();
        }
        return hashCode + i;
    }

    public void setBillingPeriod(Period period) {
        this.billingPeriod = period;
    }

    public void setCurrencyCode(String str) {
        this.currencyCode = str;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(BillingSchedule billingSchedule) {
        if (billingSchedule == null) {
            return -1;
        }
        if (billingSchedule == this) {
            return 0;
        }
        Period billingPeriod = getBillingPeriod();
        Period billingPeriod2 = billingSchedule.getBillingPeriod();
        if (billingPeriod != billingPeriod2) {
            if (billingPeriod == null) {
                return -1;
            }
            if (billingPeriod2 == null) {
                return 1;
            }
            int compareTo = billingPeriod.compareTo(billingPeriod2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String currencyCode = getCurrencyCode();
        String currencyCode2 = billingSchedule.getCurrencyCode();
        if (currencyCode != currencyCode2) {
            if (currencyCode == null) {
                return -1;
            }
            if (currencyCode2 == null) {
                return 1;
            }
            int compareTo2 = currencyCode.compareTo(currencyCode2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String price = getPrice();
        String price2 = billingSchedule.getPrice();
        if (price != price2) {
            if (price == null) {
                return -1;
            }
            if (price2 == null) {
                return 1;
            }
            int compareTo3 = price.compareTo(price2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        return 0;
    }
}
