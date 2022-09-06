package com.amazon.alexa.biloba.service;
@Deprecated
/* loaded from: classes6.dex */
public class RecentActivityException extends Exception {
    @ErrorCode
    private int errorCode;

    /* loaded from: classes6.dex */
    public @interface ErrorCode {
        public static final int DOWNLOAD_FAILED = -2;
        public static final int DOWNLOAD_TIMED_OUT = -1;
    }

    public RecentActivityException(@ErrorCode int i) {
        this.errorCode = i;
    }

    @ErrorCode
    public int getErrorCode() {
        return this.errorCode;
    }

    public RecentActivityException(Throwable th, @ErrorCode int i) {
        super(th);
        this.errorCode = i;
    }

    public RecentActivityException(String str, @ErrorCode int i) {
        super(str);
        this.errorCode = i;
    }

    public RecentActivityException(String str, Throwable th, @ErrorCode int i) {
        super(str, th);
        this.errorCode = i;
    }
}
