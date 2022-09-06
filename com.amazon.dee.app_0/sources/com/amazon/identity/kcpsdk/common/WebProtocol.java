package com.amazon.identity.kcpsdk.common;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public enum WebProtocol {
    WebProtocolHttp("http"),
    WebProtocolHttps("https");
    
    private final String mValue;

    WebProtocol(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }
}
