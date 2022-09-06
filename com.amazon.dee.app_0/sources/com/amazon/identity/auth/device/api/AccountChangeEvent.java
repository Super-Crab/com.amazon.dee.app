package com.amazon.identity.auth.device.api;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class AccountChangeEvent {
    private final String fv;
    private final String fw;

    public AccountChangeEvent(String str, String str2) {
        this.fv = str;
        this.fw = str2;
    }

    @FireOsSdk
    public String getCurrentAccount() {
        return this.fw;
    }

    @FireOsSdk
    public String getPreviousAccount() {
        return this.fv;
    }
}
