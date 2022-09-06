package com.dee.app.http;
/* loaded from: classes2.dex */
public class CoralServiceException extends Exception {
    private int statusCode;

    public CoralServiceException() {
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public CoralServiceException(String str) {
        super(str);
    }

    public CoralServiceException(String str, int i) {
        super(str);
        this.statusCode = i;
    }

    public CoralServiceException(String str, Throwable th) {
        super(str, th);
    }

    public CoralServiceException(Throwable th) {
        super(th);
    }
}
