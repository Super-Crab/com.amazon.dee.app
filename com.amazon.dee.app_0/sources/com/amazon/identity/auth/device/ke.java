package com.amazon.identity.auth.device;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ke {
    private String mValue;
    private String sk;

    public boolean dM(String str) {
        if (!(!ma.isNullOrEmpty(str))) {
            return false;
        }
        this.sk = str;
        return true;
    }

    public String getUrl() {
        return this.sk;
    }

    public String getValue() {
        return this.mValue;
    }

    public boolean setValue(String str) {
        if (!(!ma.isNullOrEmpty(str))) {
            return false;
        }
        this.mValue = str;
        return true;
    }
}
