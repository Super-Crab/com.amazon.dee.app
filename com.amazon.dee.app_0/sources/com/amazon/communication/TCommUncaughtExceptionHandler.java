package com.amazon.communication;

import com.amazon.dp.logger.DPLogger;
import java.lang.Thread;
/* loaded from: classes12.dex */
public final class TCommUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final DPLogger log = new DPLogger("TComm.TCommUncaughtExceptionHandler");

    private void dieIfFatalError(Throwable th) {
        if (th instanceof OutOfMemoryError) {
            System.exit(0);
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        try {
            log.error("uncaughtException", "encountered an uncaught exception", "thread.getName", thread.getName(), th);
        } catch (Throwable unused) {
        }
        dieIfFatalError(th);
    }
}
