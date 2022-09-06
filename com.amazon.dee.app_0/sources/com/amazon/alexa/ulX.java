package com.amazon.alexa;

import com.amazon.alexa.api.AlexaPlaybackState;
/* compiled from: BecomingNoisyManager.java */
/* loaded from: classes.dex */
/* synthetic */ class ulX {
    public static final /* synthetic */ int[] zZm = new int[AlexaPlaybackState.values().length];

    static {
        try {
            zZm[AlexaPlaybackState.STOPPABLE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zZm[AlexaPlaybackState.STOPPABLE_AND_NAVIGABLE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zZm[AlexaPlaybackState.NONE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
