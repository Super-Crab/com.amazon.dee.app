package com.amazon.alexa;

import com.amazon.alexa.api.AlexaPlayerInfoState;
/* compiled from: AVRCPMediaPlaybackAuthority.java */
/* loaded from: classes.dex */
/* synthetic */ class LCy {
    public static final /* synthetic */ int[] BIo = new int[wSq.values().length];
    public static final /* synthetic */ int[] zZm;

    static {
        try {
            BIo[wSq.LISTENING.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        zZm = new int[AlexaPlayerInfoState.values().length];
        try {
            zZm[AlexaPlayerInfoState.PLAYING.ordinal()] = 1;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zZm[AlexaPlayerInfoState.PAUSED.ordinal()] = 2;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zZm[AlexaPlayerInfoState.BUFFERING.ordinal()] = 3;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zZm[AlexaPlayerInfoState.CANCELLED.ordinal()] = 4;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zZm[AlexaPlayerInfoState.DONE.ordinal()] = 5;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            zZm[AlexaPlayerInfoState.ERROR.ordinal()] = 6;
        } catch (NoSuchFieldError unused7) {
        }
    }
}
