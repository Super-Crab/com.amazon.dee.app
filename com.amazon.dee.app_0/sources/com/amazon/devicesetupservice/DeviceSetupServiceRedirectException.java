package com.amazon.devicesetupservice;

import com.amazon.CoralAndroidClient.Exception.CoralException;
/* loaded from: classes12.dex */
public class DeviceSetupServiceRedirectException extends CoralException {
    private static final long serialVersionUID = -1;

    public DeviceSetupServiceRedirectException() {
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public DeviceSetupServiceRedirectException(Throwable th) {
        initCause(th);
    }

    public DeviceSetupServiceRedirectException(String str) {
        super(str);
    }

    public DeviceSetupServiceRedirectException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
