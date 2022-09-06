package com.amazon.bluefront.api.common;
/* loaded from: classes11.dex */
public class Customer implements Comparable<Customer> {
    private String mIdentifier;
    private String mTranslateToObfuscatedCustomerIdFrom;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Customer) && compareTo((Customer) obj) == 0;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public String getTranslateToObfuscatedCustomerIdFrom() {
        return this.mTranslateToObfuscatedCustomerIdFrom;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getTranslateToObfuscatedCustomerIdFrom() == null ? 0 : getTranslateToObfuscatedCustomerIdFrom().hashCode()) + 1;
        if (getIdentifier() != null) {
            i = getIdentifier().hashCode();
        }
        return hashCode + i;
    }

    public void setIdentifier(String str) {
        this.mIdentifier = str;
    }

    public void setTranslateToObfuscatedCustomerIdFrom(String str) {
        this.mTranslateToObfuscatedCustomerIdFrom = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(Customer customer) {
        if (customer == null) {
            return -1;
        }
        if (customer == this) {
            return 0;
        }
        String translateToObfuscatedCustomerIdFrom = getTranslateToObfuscatedCustomerIdFrom();
        String translateToObfuscatedCustomerIdFrom2 = customer.getTranslateToObfuscatedCustomerIdFrom();
        if (translateToObfuscatedCustomerIdFrom != translateToObfuscatedCustomerIdFrom2) {
            if (translateToObfuscatedCustomerIdFrom == null) {
                return -1;
            }
            if (translateToObfuscatedCustomerIdFrom2 == null) {
                return 1;
            }
            int compareTo = translateToObfuscatedCustomerIdFrom.compareTo(translateToObfuscatedCustomerIdFrom2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String identifier = getIdentifier();
        String identifier2 = customer.getIdentifier();
        if (identifier != identifier2) {
            if (identifier == null) {
                return -1;
            }
            if (identifier2 == null) {
                return 1;
            }
            int compareTo2 = identifier.compareTo(identifier2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
