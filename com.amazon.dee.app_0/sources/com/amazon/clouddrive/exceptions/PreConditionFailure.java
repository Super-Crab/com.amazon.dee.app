package com.amazon.clouddrive.exceptions;
/* loaded from: classes11.dex */
public class PreConditionFailure extends ActionRequiredException {
    private static final long serialVersionUID = -1;

    public PreConditionFailure() {
    }

    @Override // com.amazon.clouddrive.exceptions.NoRetryException, com.amazon.clouddrive.exceptions.CloudDriveException
    public int getHttpCode() {
        return 412;
    }

    public PreConditionFailure(Throwable th) {
        super(th);
    }

    public PreConditionFailure(String str) {
        super(str);
    }

    public PreConditionFailure(String str, Throwable th) {
        super(str, th);
    }

    public PreConditionFailure(String str, String str2, String str3) {
        super(str, str2, str3);
    }
}
