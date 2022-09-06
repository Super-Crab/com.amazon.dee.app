package com.amazon.dee.app.services.alexadevicebackground;
/* loaded from: classes12.dex */
public class DeviceDownloadFailedException extends Exception {
    @ErrorCode
    private int errorCode;

    /* loaded from: classes12.dex */
    public @interface ErrorCode {
        public static final int DOWNLOAD_FAILED = -2;
        public static final int DOWNLOAD_TIMED_OUT = -1;
    }

    public DeviceDownloadFailedException(@ErrorCode int i) {
        this.errorCode = i;
    }

    @ErrorCode
    public int getErrorCode() {
        return this.errorCode;
    }

    public DeviceDownloadFailedException(Throwable th, @ErrorCode int i) {
        super(th);
        this.errorCode = i;
    }

    public DeviceDownloadFailedException(String str, @ErrorCode int i) {
        super(str);
        this.errorCode = i;
    }

    public DeviceDownloadFailedException(String str, Throwable th, @ErrorCode int i) {
        super(str, th);
        this.errorCode = i;
    }
}
