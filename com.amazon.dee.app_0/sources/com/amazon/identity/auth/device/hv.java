package com.amazon.identity.auth.device;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class hv {
    private static final String TAG = "com.amazon.identity.auth.device.hv";
    private static hv qS;
    public final String kJ;
    public final int qT = 13;
    public final int qU = 50002;
    public final int qV = (this.qT * 10000000) + this.qU;

    public hv(String str) {
        this.kJ = str;
    }

    public static synchronized hv gs() {
        synchronized (hv.class) {
            if (qS != null) {
                return qS;
            }
            hv hvVar = new hv("MAPAndroidLib-1.3.2010.0");
            qS = hvVar;
            return hvVar;
        }
    }

    public static String gt() {
        return String.valueOf(gs().qV);
    }

    public String toString() {
        return this.qV + " / " + this.kJ;
    }
}
