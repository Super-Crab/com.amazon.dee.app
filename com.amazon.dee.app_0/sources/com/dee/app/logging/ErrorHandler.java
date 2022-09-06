package com.dee.app.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
/* loaded from: classes2.dex */
public class ErrorHandler {
    public static String getStackTrace(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
