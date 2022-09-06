package com.amazonaws.util;

import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public enum Throwables {
    ;
    
    private static final int MAX_RETRY = 1000;

    public static Throwable getRootCause(Throwable th) {
        if (th == null) {
            return th;
        }
        int i = 0;
        Throwable th2 = th;
        while (i < 1000) {
            Throwable cause = th2.getCause();
            if (cause == null) {
                return th2;
            }
            i++;
            th2 = cause;
        }
        Log log = LogFactory.getLog(Throwables.class);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Possible circular reference detected on ");
        outline107.append(th.getClass());
        outline107.append(": [");
        outline107.append(th);
        outline107.append("]");
        log.debug(outline107.toString());
        return th;
    }
}
