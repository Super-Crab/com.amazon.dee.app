package com.amazon.devicesetupservice;

import com.amazon.CoralAndroidClient.Exception.CoralException;
/* loaded from: classes12.dex */
public class SaveWifiNetworkFailureException extends CoralException {
    private static final long serialVersionUID = -1;

    public SaveWifiNetworkFailureException() {
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public SaveWifiNetworkFailureException(Throwable th) {
        initCause(th);
    }

    public SaveWifiNetworkFailureException(String str) {
        super(str);
    }

    public SaveWifiNetworkFailureException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
