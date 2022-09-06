package com.bugsnag.android;

import android.os.StrictMode;
import androidx.annotation.NonNull;
import java.lang.Thread;
import java.util.Map;
import java.util.WeakHashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final String STRICT_MODE_KEY = "Violation";
    private static final String STRICT_MODE_TAB = "StrictMode";
    private final Thread.UncaughtExceptionHandler originalHandler;
    private final StrictModeHandler strictModeHandler = new StrictModeHandler();
    final Map<Client, Boolean> clientMap = new WeakHashMap();

    ExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.originalHandler = uncaughtExceptionHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void disable(@NonNull Client client) {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler instanceof ExceptionHandler) {
            ExceptionHandler exceptionHandler = (ExceptionHandler) defaultUncaughtExceptionHandler;
            exceptionHandler.clientMap.remove(client);
            if (!exceptionHandler.clientMap.isEmpty()) {
                return;
            }
            Thread.setDefaultUncaughtExceptionHandler(exceptionHandler.originalHandler);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void enable(@NonNull Client client) {
        ExceptionHandler exceptionHandler;
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler instanceof ExceptionHandler) {
            exceptionHandler = (ExceptionHandler) defaultUncaughtExceptionHandler;
        } else {
            ExceptionHandler exceptionHandler2 = new ExceptionHandler(defaultUncaughtExceptionHandler);
            Thread.setDefaultUncaughtExceptionHandler(exceptionHandler2);
            exceptionHandler = exceptionHandler2;
        }
        exceptionHandler.clientMap.put(client, true);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable th) {
        MetaData metaData;
        String str;
        boolean isStrictModeThrowable = this.strictModeHandler.isStrictModeThrowable(th);
        for (Client client : this.clientMap.keySet()) {
            MetaData metaData2 = new MetaData();
            if (isStrictModeThrowable) {
                String violationDescription = this.strictModeHandler.getViolationDescription(th.getMessage());
                MetaData metaData3 = new MetaData();
                metaData3.addToTab(STRICT_MODE_TAB, STRICT_MODE_KEY, violationDescription);
                str = violationDescription;
                metaData = metaData3;
            } else {
                metaData = metaData2;
                str = null;
            }
            String str2 = isStrictModeThrowable ? "strictMode" : "unhandledException";
            if (isStrictModeThrowable) {
                StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
                StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
                client.cacheAndNotify(th, Severity.ERROR, metaData, str2, str, thread);
                StrictMode.setThreadPolicy(threadPolicy);
            } else {
                client.cacheAndNotify(th, Severity.ERROR, metaData, str2, str, thread);
            }
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.originalHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
            return;
        }
        System.err.printf("Exception in thread \"%s\" ", thread.getName());
        Logger.warn("Exception", th);
    }
}
