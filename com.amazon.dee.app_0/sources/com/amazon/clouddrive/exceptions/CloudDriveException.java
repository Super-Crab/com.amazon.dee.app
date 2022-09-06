package com.amazon.clouddrive.exceptions;
/* loaded from: classes11.dex */
public class CloudDriveException extends Exception {
    public static final int NO_HTTP_CODE = -1;
    private static final long serialVersionUID = -1;
    private String mCode;
    private String mLogref;

    public CloudDriveException() {
    }

    public String getCode() {
        return this.mCode;
    }

    public int getHttpCode() {
        return -1;
    }

    public String getLogref() {
        return this.mLogref;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public CloudDriveException(Throwable th) {
        super(th);
    }

    public CloudDriveException(String str) {
        super(str);
    }

    public CloudDriveException(String str, Throwable th) {
        super(str, th);
    }

    public CloudDriveException(String str, String str2, String str3) {
        super(str);
        this.mCode = str2;
        this.mLogref = str3;
    }
}
