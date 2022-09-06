package com.amazon.alexa.biloba.service;
/* loaded from: classes6.dex */
public class AlertConfigurationException extends Exception {
    @ErrorCode
    private int errorCode;

    /* loaded from: classes6.dex */
    public @interface ErrorCode {
        public static final int DOWNLOAD_FAILED = -2;
        public static final int DOWNLOAD_TIMED_OUT = -1;
        public static final int UPDATE_FAILED = -3;
    }

    public AlertConfigurationException(@ErrorCode int i) {
        this.errorCode = i;
    }

    @ErrorCode
    public int getErrorCode() {
        return this.errorCode;
    }

    public AlertConfigurationException(Throwable th, @ErrorCode int i) {
        super(th);
        this.errorCode = i;
    }

    public AlertConfigurationException(String str, @ErrorCode int i) {
        super(str);
        this.errorCode = i;
    }

    public AlertConfigurationException(String str, Throwable th, @ErrorCode int i) {
        super(str, th);
        this.errorCode = i;
    }
}
