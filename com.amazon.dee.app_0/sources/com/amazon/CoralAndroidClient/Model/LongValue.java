package com.amazon.CoralAndroidClient.Model;
/* loaded from: classes.dex */
public class LongValue implements Value {
    private long mValue;

    public LongValue(long j) {
        this.mValue = j;
    }

    public long getValue() {
        return this.mValue;
    }

    public void setValue(long j) {
        this.mValue = j;
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return Long.valueOf(getValue());
    }

    public LongValue() {
        this(0L);
    }
}
