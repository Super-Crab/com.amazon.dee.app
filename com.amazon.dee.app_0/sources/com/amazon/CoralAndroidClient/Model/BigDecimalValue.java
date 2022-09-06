package com.amazon.CoralAndroidClient.Model;

import java.math.BigDecimal;
/* loaded from: classes.dex */
public class BigDecimalValue implements Value {
    private BigDecimal mValue;

    public BigDecimalValue(BigDecimal bigDecimal) {
        this.mValue = bigDecimal == null ? new BigDecimal(0) : bigDecimal;
    }

    public BigDecimal getValue() {
        return this.mValue;
    }

    public void setValue(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            bigDecimal = new BigDecimal(0);
        }
        this.mValue = bigDecimal;
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return getValue();
    }

    public BigDecimalValue() {
        this(null);
    }
}
