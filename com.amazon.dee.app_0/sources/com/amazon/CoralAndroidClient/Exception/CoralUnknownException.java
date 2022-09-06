package com.amazon.CoralAndroidClient.Exception;
/* loaded from: classes.dex */
public class CoralUnknownException extends CoralException {
    private final String mData;
    private final String mType;

    public CoralUnknownException(String str, String str2) {
        super(str);
        this.mType = str;
        this.mData = str2;
    }

    public String getData() {
        return this.mData;
    }

    public String getType() {
        return this.mType;
    }
}
