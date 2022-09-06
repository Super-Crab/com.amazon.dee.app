package com.amazon.clouddrive.exceptions;
/* loaded from: classes11.dex */
public class ActionRequiredException extends NoRetryException {
    public ActionRequiredException() {
    }

    public ActionRequiredException(Throwable th) {
        super(th);
    }

    public ActionRequiredException(String str) {
        super(str);
    }

    public ActionRequiredException(String str, Throwable th) {
        super(str, th);
    }

    public ActionRequiredException(String str, String str2, String str3) {
        super(str, str2, str3);
    }
}
