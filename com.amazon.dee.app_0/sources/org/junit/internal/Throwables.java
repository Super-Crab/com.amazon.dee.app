package org.junit.internal;
/* loaded from: classes5.dex */
public final class Throwables {
    private Throwables() {
    }

    private static <T extends Throwable> void rethrow(Throwable th) throws Throwable {
        throw th;
    }

    public static Exception rethrowAsException(Throwable th) throws Exception {
        rethrow(th);
        return null;
    }
}
