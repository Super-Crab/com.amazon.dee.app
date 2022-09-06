package com.amazon.devicesetupservice;

import com.amazon.CoralAndroidClient.Exception.CoralException;
/* loaded from: classes12.dex */
public class ProvisionerForbiddenException extends CoralException {
    private static final long serialVersionUID = -1;
    private String forbiddenCause;

    public ProvisionerForbiddenException() {
    }

    public String getForbiddenCause() {
        return this.forbiddenCause;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public void setForbiddenCause(String str) {
        this.forbiddenCause = str;
    }

    public ProvisionerForbiddenException(Throwable th) {
        initCause(th);
    }

    public ProvisionerForbiddenException(String str) {
        super(str);
    }

    public ProvisionerForbiddenException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
