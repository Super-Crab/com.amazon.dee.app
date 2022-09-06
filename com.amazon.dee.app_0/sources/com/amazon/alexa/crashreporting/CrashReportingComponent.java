package com.amazon.alexa.crashreporting;

import android.os.SystemClock;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public abstract class CrashReportingComponent {
    private static final String TAG = "CrashReportingComponent";
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler = null;
    private final AtomicBoolean initialized = new AtomicBoolean(false);

    /* loaded from: classes6.dex */
    static final class InitializationException extends Exception {
        InitializationException(String str, Throwable th) {
            super(str, th);
        }
    }

    static /* synthetic */ void lambda$initialize$0(Thread thread, Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getFriendlyName();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void handleCrash(Thread thread, Throwable th) {
        if (!isInitialized()) {
            String str = TAG;
            Log.w(str, getFriendlyName() + " won't handle crash, not initialized yet.", th);
            return;
        }
        try {
            this.uncaughtExceptionHandler.uncaughtException(thread, th);
        } catch (Throwable th2) {
            String str2 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to handle exception in ");
            outline107.append(getFriendlyName());
            outline107.append(". Gobbling it.");
            Log.w(str2, outline107.toString(), th2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void initialize() throws InitializationException {
        if (isInitialized()) {
            String str = TAG;
            Log.w(str, getFriendlyName() + " is already initialized. Won't initialize again.");
            return;
        }
        String str2 = TAG;
        Log.i(str2, "Starting initialization of " + getFriendlyName() + " now.");
        long uptimeMillis = SystemClock.uptimeMillis();
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler($$Lambda$CrashReportingComponent$icmhAZpZgdZIiNgxak80thpqZ8.INSTANCE);
        registerDefaultUncaughtExceptionHandler();
        this.uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.initialized.set(true);
        Thread.setDefaultUncaughtExceptionHandler(defaultUncaughtExceptionHandler);
        long uptimeMillis2 = SystemClock.uptimeMillis() - uptimeMillis;
        String str3 = TAG;
        Log.i(str3, getFriendlyName() + " initialization succeeded. It took " + String.valueOf(uptimeMillis2) + " ms.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isInitialized() {
        return this.initialized.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void putMetadata(String str, String str2);

    abstract void registerDefaultUncaughtExceptionHandler();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void reportNonFatal(Throwable th);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setAccount(String str, String str2, String str3);
}
