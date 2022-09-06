package com.amazon.identity.auth.device;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class jt {
    private static ju rR;

    public static synchronized jt gZ() {
        synchronized (jt.class) {
            if (rR != null) {
                return rR.gZ();
            }
            return null;
        }
    }

    public abstract boolean ct();

    public abstract String f();

    public abstract String gX();

    public abstract String gY();
}
