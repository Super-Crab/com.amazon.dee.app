package com.amazon.clouddrive.exceptions;

import javax.servlet.http.HttpServletResponse;
/* loaded from: classes11.dex */
public class ConflictError extends ActionRequiredException {
    private final String mNodeId;

    public ConflictError() {
        this.mNodeId = null;
    }

    @Override // com.amazon.clouddrive.exceptions.NoRetryException, com.amazon.clouddrive.exceptions.CloudDriveException
    public int getHttpCode() {
        return HttpServletResponse.SC_CONFLICT;
    }

    public String getNodeId() {
        return this.mNodeId;
    }

    public ConflictError(Throwable th) {
        super(th);
        this.mNodeId = null;
    }

    public ConflictError(String str) {
        super(str);
        this.mNodeId = null;
    }

    public ConflictError(String str, Throwable th) {
        super(str, th);
        this.mNodeId = null;
    }

    public ConflictError(String str, String str2, String str3) {
        super(str, str2, str3);
        this.mNodeId = null;
    }

    public ConflictError(String str, String str2, String str3, String str4) {
        super(str, str2, str3);
        this.mNodeId = str4;
    }
}
