package com.amazon.communication.utils;
/* loaded from: classes12.dex */
public final class StackTraceUtils {
    public static String buildSingleLineFormattedStackTrace() {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 3; i < stackTrace.length; i++) {
            sb.append(stackTrace[i].getClassName());
            sb.append(".");
            sb.append(stackTrace[i].getMethodName());
            sb.append("(");
            sb.append(stackTrace[i].getFileName());
            sb.append(":");
            sb.append(stackTrace[i].getLineNumber());
            sb.append(")");
            if (i < stackTrace.length - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }
}
