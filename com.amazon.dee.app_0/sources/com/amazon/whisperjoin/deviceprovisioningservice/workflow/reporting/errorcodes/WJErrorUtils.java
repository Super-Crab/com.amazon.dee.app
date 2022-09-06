package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import org.apache.commons.lang.exception.ExceptionUtils;
/* loaded from: classes13.dex */
public class WJErrorUtils {
    public static Throwable getRootCause(Throwable th) {
        if (th == null) {
            return null;
        }
        Throwable rootCause = ExceptionUtils.getRootCause(th);
        return rootCause != null ? rootCause : th;
    }
}
