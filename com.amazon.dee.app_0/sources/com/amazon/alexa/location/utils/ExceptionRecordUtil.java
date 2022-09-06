package com.amazon.alexa.location.utils;

import android.util.Log;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.protocols.service.api.LazyComponent;
/* loaded from: classes9.dex */
public final class ExceptionRecordUtil {
    private ExceptionRecordUtil() {
    }

    public static void recordExceptions(String str, String str2, Throwable th, LazyComponent<CrashReporter> lazyComponent) {
        Log.e(str, str2 + " fails", th);
        lazyComponent.mo10268get().reportNonFatal(new LocationHandledException(th));
    }
}
