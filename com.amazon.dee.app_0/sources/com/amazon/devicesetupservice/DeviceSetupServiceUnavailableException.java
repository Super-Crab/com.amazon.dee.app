package com.amazon.devicesetupservice;

import com.amazon.CoralAndroidClient.Exception.CoralException;
/* loaded from: classes12.dex */
public class DeviceSetupServiceUnavailableException extends CoralException {
    private static final long serialVersionUID = -1;

    public DeviceSetupServiceUnavailableException() {
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public DeviceSetupServiceUnavailableException(Throwable th) {
        initCause(th);
    }

    public DeviceSetupServiceUnavailableException(String str) {
        super(str);
    }

    public DeviceSetupServiceUnavailableException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
