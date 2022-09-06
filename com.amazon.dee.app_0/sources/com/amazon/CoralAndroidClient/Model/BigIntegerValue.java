package com.amazon.CoralAndroidClient.Model;

import java.math.BigInteger;
/* loaded from: classes.dex */
public class BigIntegerValue implements Value {
    private BigInteger mValue;

    public BigIntegerValue(BigInteger bigInteger) {
        this.mValue = bigInteger == null ? new BigInteger("0") : bigInteger;
    }

    public BigInteger getValue() {
        return this.mValue;
    }

    public void setValue(BigInteger bigInteger) {
        if (bigInteger == null) {
            bigInteger = new BigInteger("0");
        }
        this.mValue = bigInteger;
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return getValue();
    }

    public BigIntegerValue() {
        this(null);
    }
}
