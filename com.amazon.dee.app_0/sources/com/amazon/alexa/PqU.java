package com.amazon.alexa;

import com.amazon.alexa.api.LaunchType;
/* compiled from: LaunchSource.java */
/* loaded from: classes.dex */
/* synthetic */ class PqU {
    public static final /* synthetic */ int[] zZm = new int[LaunchType.values().length];

    static {
        try {
            zZm[LaunchType.WAKE_WORD.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zZm[LaunchType.TAP_TO_TALK.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zZm[LaunchType.TEXT.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
