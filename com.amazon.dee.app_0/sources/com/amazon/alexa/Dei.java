package com.amazon.alexa;

import com.amazon.alexa.WnL;
import com.amazon.alexa.api.ApiCallFailure;
/* compiled from: RequestComposer.java */
/* loaded from: classes.dex */
/* synthetic */ class Dei {
    public static final /* synthetic */ int[] BIo;
    public static final /* synthetic */ int[] zQM = new int[bij.values().length];
    public static final /* synthetic */ int[] zZm;

    static {
        try {
            zQM[bij.NETWORK_UNAVAILABLE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zQM[bij.NETWORK_LOST.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zQM[bij.NETWORK_SWITCHED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zQM[bij.AVS_UNAVAILABLE_MISSING.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zQM[bij.AVS_UNAVAILABLE_DOWNCHANNEL.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zQM[bij.AVS_CONNECTION_TIMEOUT.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            zQM[bij.NETWORK_REQUEST_ERROR.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            zQM[bij.REQUEST_PARSING_ERROR.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            zQM[bij.AUTHORIZATION_ERROR.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            zQM[bij.INTERNAL_CLIENT_ERROR_CLIENT_TIMEOUT_EXCEEDED.ordinal()] = 10;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            zQM[bij.INTERNAL_CLIENT_ERROR_MESSAGE_TIMEOUT_EXCEEDED.ordinal()] = 11;
        } catch (NoSuchFieldError unused11) {
        }
        BIo = new int[ApiCallFailure.FailureType.values().length];
        try {
            BIo[ApiCallFailure.FailureType.NETWORK.ordinal()] = 1;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            BIo[ApiCallFailure.FailureType.AUTHORIZATION.ordinal()] = 2;
        } catch (NoSuchFieldError unused13) {
        }
        try {
            BIo[ApiCallFailure.FailureType.TIMEOUT.ordinal()] = 3;
        } catch (NoSuchFieldError unused14) {
        }
        try {
            BIo[ApiCallFailure.FailureType.INTERNAL.ordinal()] = 4;
        } catch (NoSuchFieldError unused15) {
        }
        zZm = new int[WnL.zyO.values().length];
        try {
            zZm[WnL.zyO.UNINITIALIZED.ordinal()] = 1;
        } catch (NoSuchFieldError unused16) {
        }
        try {
            zZm[WnL.zyO.UNAUTHORIZED.ordinal()] = 2;
        } catch (NoSuchFieldError unused17) {
        }
        try {
            zZm[WnL.zyO.AVAILABLE.ordinal()] = 3;
        } catch (NoSuchFieldError unused18) {
        }
        try {
            zZm[WnL.zyO.NETWORK.ordinal()] = 4;
        } catch (NoSuchFieldError unused19) {
        }
        try {
            zZm[WnL.zyO.CONNECTION_NOT_ESTABLISHED.ordinal()] = 5;
        } catch (NoSuchFieldError unused20) {
        }
        try {
            zZm[WnL.zyO.CONNECTION_NOT_CONNECTED.ordinal()] = 6;
        } catch (NoSuchFieldError unused21) {
        }
        try {
            zZm[WnL.zyO.CAPABILITY_PUBLISH.ordinal()] = 7;
        } catch (NoSuchFieldError unused22) {
        }
        try {
            zZm[WnL.zyO.SYNCHRONIZE_STATE.ordinal()] = 8;
        } catch (NoSuchFieldError unused23) {
        }
    }
}
