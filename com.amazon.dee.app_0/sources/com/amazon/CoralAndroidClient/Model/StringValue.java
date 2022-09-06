package com.amazon.CoralAndroidClient.Model;
/* loaded from: classes.dex */
public class StringValue implements Value {
    private String mValue;

    public StringValue(String str) {
        this.mValue = str == null ? "" : str;
    }

    public String getValue() {
        return this.mValue;
    }

    public void setValue(String str) {
        if (str == null) {
            str = "";
        }
        this.mValue = str;
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return getValue();
    }

    public StringValue() {
        this(null);
    }
}
