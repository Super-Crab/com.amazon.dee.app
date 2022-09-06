package com.amazon.comms.debug;

import com.amazon.comms.log.CommsLogger;
/* loaded from: classes11.dex */
public final class DebugAssert {
    public static final CommsLogger sLog = CommsLogger.getLogger(DebugAssert.class);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class DieHardException extends RuntimeException {
        public DieHardException(String str, Throwable th) {
            super(str, th);
        }
    }

    private DebugAssert() {
    }

    public static void expect(boolean z, String str) {
        if (!z) {
            fail(str);
        }
    }

    public static void fail(String str) {
        fail(new RuntimeException(), str);
    }

    public static void fail(Throwable th, String str) {
        sLog.e(str, th);
        throw new DieHardException(str, th);
    }
}
