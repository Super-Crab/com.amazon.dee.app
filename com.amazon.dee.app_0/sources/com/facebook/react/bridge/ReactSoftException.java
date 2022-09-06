package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@Deprecated
/* loaded from: classes2.dex */
public class ReactSoftException {
    private static final List<ReactSoftExceptionListener> sListeners = new CopyOnWriteArrayList();

    /* loaded from: classes2.dex */
    public interface ReactSoftExceptionListener {
        void logSoftException(String str, Throwable th);
    }

    public static void addListener(ReactSoftExceptionListener reactSoftExceptionListener) {
        if (!sListeners.contains(reactSoftExceptionListener)) {
            sListeners.add(reactSoftExceptionListener);
        }
    }

    public static void clearListeners() {
        sListeners.clear();
    }

    public static void logSoftException(String str, Throwable th) {
        if (sListeners.size() > 0) {
            for (ReactSoftExceptionListener reactSoftExceptionListener : sListeners) {
                reactSoftExceptionListener.logSoftException(str, th);
            }
            return;
        }
        FLog.e(str, "Unhandled SoftException", th);
    }

    public static void removeListener(ReactSoftExceptionListener reactSoftExceptionListener) {
        sListeners.remove(reactSoftExceptionListener);
    }
}
