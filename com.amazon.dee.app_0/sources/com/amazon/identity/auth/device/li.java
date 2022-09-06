package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.api.MAPError;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class li {
    private final MAPError mError;
    private final String mErrorMessage;
    private final int mLegacyErrorCode;
    private final String mLegacyErrorMessage;

    public li(MAPError mAPError, String str, int i, String str2) {
        this.mError = mAPError;
        this.mErrorMessage = str;
        this.mLegacyErrorCode = i;
        this.mLegacyErrorMessage = str2;
    }

    public String bF() {
        return this.mLegacyErrorMessage;
    }

    public MAPError getError() {
        return this.mError;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public int ic() {
        return this.mLegacyErrorCode;
    }
}
