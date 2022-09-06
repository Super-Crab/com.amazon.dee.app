package com.amazon.alexa;
/* compiled from: AttentionSystemLatencyProcessor.java */
/* loaded from: classes.dex */
/* synthetic */ class peA {
    public static final /* synthetic */ int[] zZm = new int[wSq.values().length];

    static {
        try {
            zZm[wSq.LISTENING.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zZm[wSq.PREPARING_TO_LISTEN.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zZm[wSq.THINKING.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zZm[wSq.IDLE.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}
