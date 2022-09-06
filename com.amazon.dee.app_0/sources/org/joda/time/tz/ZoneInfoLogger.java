package org.joda.time.tz;
/* loaded from: classes5.dex */
public class ZoneInfoLogger {
    static ThreadLocal<Boolean> cVerbose = new ThreadLocal<Boolean>() { // from class: org.joda.time.tz.ZoneInfoLogger.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        /* renamed from: initialValue */
        public Boolean mo12879initialValue() {
            return Boolean.FALSE;
        }
    };

    public static void set(boolean z) {
        cVerbose.set(Boolean.valueOf(z));
    }

    public static boolean verbose() {
        return cVerbose.get().booleanValue();
    }
}
