package com.amazon.bluefront.api.common;
/* loaded from: classes11.dex */
public enum TranslateToObfuscatedCustomerIdFrom {
    DIRECTED_ID("DIRECTED_ID"),
    NO_TRANSLATION("NO_TRANSLATION");
    
    private final String mType;

    TranslateToObfuscatedCustomerIdFrom(String str) {
        this.mType = str;
    }

    public static TranslateToObfuscatedCustomerIdFrom fromSerializedForm(String str) {
        TranslateToObfuscatedCustomerIdFrom[] values;
        for (TranslateToObfuscatedCustomerIdFrom translateToObfuscatedCustomerIdFrom : values()) {
            if (translateToObfuscatedCustomerIdFrom.toSerializedForm().equals(str)) {
                return translateToObfuscatedCustomerIdFrom;
            }
        }
        return null;
    }

    public String toSerializedForm() {
        return this.mType;
    }
}
