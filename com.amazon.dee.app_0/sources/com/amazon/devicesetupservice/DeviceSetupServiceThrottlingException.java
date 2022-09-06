package com.amazon.devicesetupservice;
/* loaded from: classes12.dex */
public class DeviceSetupServiceThrottlingException extends DeviceSetupServiceUnavailableException {
    private static final long serialVersionUID = -1;
    private int retryAfterInSeconds;

    public DeviceSetupServiceThrottlingException() {
    }

    public int getRetryAfterInSeconds() {
        return this.retryAfterInSeconds;
    }

    public void setRetryAfterInSeconds(int i) {
        this.retryAfterInSeconds = i;
    }

    public DeviceSetupServiceThrottlingException(Throwable th) {
        initCause(th);
    }

    public DeviceSetupServiceThrottlingException(String str) {
        super(str);
    }

    public DeviceSetupServiceThrottlingException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
