package com.amazon.alexa;

import com.amazon.alexa.OPl;
import com.amazon.alexa.api.AlexaPlayerInfoState;
/* compiled from: AudioFocusManager.java */
/* loaded from: classes.dex */
/* synthetic */ class nhT {
    public static final /* synthetic */ int[] BIo = new int[AlexaPlayerInfoState.values().length];
    public static final /* synthetic */ int[] zZm;

    static {
        try {
            BIo[AlexaPlayerInfoState.PLAYING.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            BIo[AlexaPlayerInfoState.BUFFERING.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            BIo[AlexaPlayerInfoState.PAUSED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            BIo[AlexaPlayerInfoState.DONE.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            BIo[AlexaPlayerInfoState.ERROR.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        zZm = new int[OPl.zyO.values().length];
        try {
            zZm[OPl.zyO.UNKNOWN.ordinal()] = 1;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            zZm[OPl.zyO.HAS_NEXT_ITEM.ordinal()] = 2;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            zZm[OPl.zyO.DONE.ordinal()] = 3;
        } catch (NoSuchFieldError unused8) {
        }
    }
}
