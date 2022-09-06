package com.amazon.alexa;

import com.amazon.alexa.api.AlexaPlayerInfoState;
/* compiled from: ServiceActivityAuthority.java */
/* loaded from: classes.dex */
/* synthetic */ class zIj {
    public static final /* synthetic */ int[] zZm = new int[AlexaPlayerInfoState.values().length];

    static {
        try {
            zZm[AlexaPlayerInfoState.CANCELLED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zZm[AlexaPlayerInfoState.ERROR.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zZm[AlexaPlayerInfoState.PLAYING.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zZm[AlexaPlayerInfoState.PAUSED.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zZm[AlexaPlayerInfoState.BUFFERING.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zZm[AlexaPlayerInfoState.DONE.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
    }
}
