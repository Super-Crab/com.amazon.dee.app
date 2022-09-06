package com.amazon.alexa;

import com.amazon.alexa.api.DriveModeState;
/* compiled from: DriveModeAuthority.java */
/* loaded from: classes.dex */
/* synthetic */ class jSz {
    public static final /* synthetic */ int[] zZm = new int[DriveModeState.values().length];

    static {
        try {
            zZm[DriveModeState.ACCESSORY.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zZm[DriveModeState.AUTOBLUETOOTH.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zZm[DriveModeState.MANUAL.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
