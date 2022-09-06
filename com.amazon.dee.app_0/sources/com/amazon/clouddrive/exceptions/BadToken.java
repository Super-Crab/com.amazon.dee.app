package com.amazon.clouddrive.exceptions;

import javax.servlet.http.HttpServletResponse;
/* loaded from: classes11.dex */
public class BadToken extends ActionRequiredException {
    public BadToken() {
    }

    @Override // com.amazon.clouddrive.exceptions.NoRetryException, com.amazon.clouddrive.exceptions.CloudDriveException
    public int getHttpCode() {
        return HttpServletResponse.SC_UNAUTHORIZED;
    }

    public BadToken(Throwable th) {
        super(th);
    }

    public BadToken(String str) {
        super(str);
    }

    public BadToken(String str, Throwable th) {
        super(str, th);
    }

    public BadToken(String str, String str2, String str3) {
        super(str, str2, str3);
    }
}
