package com.amazon.devicesetupservice;

import com.amazon.CoralAndroidClient.Exception.CoralException;
/* loaded from: classes12.dex */
public class DeviceSetupServiceException extends CoralException {
    private static final long serialVersionUID = -1;

    public DeviceSetupServiceException() {
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public DeviceSetupServiceException(Throwable th) {
        initCause(th);
    }

    public DeviceSetupServiceException(String str) {
        super(str);
    }

    public DeviceSetupServiceException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
