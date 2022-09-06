package com.amazon.alexa;

import com.amazon.alexa.PMW;
import com.amazon.alexa.Ppr;
import com.amazon.alexa.TTH;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.uxs;
/* compiled from: MetricsAuthority.java */
/* loaded from: classes.dex */
/* synthetic */ class Ebf {
    public static final /* synthetic */ int[] BIo;
    public static final /* synthetic */ int[] jiA = new int[Ppr.zZm.values().length];
    public static final /* synthetic */ int[] zQM;
    public static final /* synthetic */ int[] zZm;
    public static final /* synthetic */ int[] zyO;

    static {
        try {
            jiA[Ppr.zZm.TIME_ZONE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            jiA[Ppr.zZm.LOCALE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            jiA[Ppr.zZm.SUPPORTS_MOBILE_DOWNCHANNEL.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        zyO = new int[UiEventName.values().length];
        try {
            zyO[UiEventName.ALEXA_UI_SHOWN.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zyO[UiEventName.CARD_VIEWS_CREATED_LATENCY.ordinal()] = 2;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zyO[UiEventName.CARD_SHOWN.ordinal()] = 3;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            zyO[UiEventName.JSON_PARSING_LATENCY.ordinal()] = 4;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            zyO[UiEventName.CARD_CREATION_LATENCY.ordinal()] = 5;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            zyO[UiEventName.CARD_RENDER_LATENCY.ordinal()] = 6;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            zyO[UiEventName.CARD_INTERACTED.ordinal()] = 7;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            zyO[UiEventName.CARD_INGRESS_TAPPED.ordinal()] = 8;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            zyO[UiEventName.NAVIGATION_TO_EXTERNAL_LINK.ordinal()] = 9;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            zyO[UiEventName.NAVIGATION_TO_INTERNAL_CARD.ordinal()] = 10;
        } catch (NoSuchFieldError unused13) {
        }
        try {
            zyO[UiEventName.MISSING_CARD_FACTORY.ordinal()] = 11;
        } catch (NoSuchFieldError unused14) {
        }
        try {
            zyO[UiEventName.FOUND_CARD_FACTORY.ordinal()] = 12;
        } catch (NoSuchFieldError unused15) {
        }
        try {
            zyO[UiEventName.CARD_CREATION_ERROR.ordinal()] = 13;
        } catch (NoSuchFieldError unused16) {
        }
        try {
            zyO[UiEventName.CARD_CONTROLLER_CREATION_ERROR.ordinal()] = 14;
        } catch (NoSuchFieldError unused17) {
        }
        try {
            zyO[UiEventName.JSON_PARSING_ERROR.ordinal()] = 15;
        } catch (NoSuchFieldError unused18) {
        }
        zQM = new int[PMW.zZm.values().length];
        try {
            zQM[PMW.zZm.IO_EXCEPTION.ordinal()] = 1;
        } catch (NoSuchFieldError unused19) {
        }
        try {
            zQM[PMW.zZm.AVS_FAILURE.ordinal()] = 2;
        } catch (NoSuchFieldError unused20) {
        }
        try {
            zQM[PMW.zZm.NO_NETWORK.ordinal()] = 3;
        } catch (NoSuchFieldError unused21) {
        }
        try {
            zQM[PMW.zZm.AUTHORIZATION.ordinal()] = 4;
        } catch (NoSuchFieldError unused22) {
        }
        BIo = new int[TTH.zZm.values().length];
        try {
            BIo[TTH.zZm.UNSUPPORTED_OPERATION.ordinal()] = 1;
        } catch (NoSuchFieldError unused23) {
        }
        try {
            BIo[TTH.zZm.UNEXPECTED_INFORMATION_RECEIVED.ordinal()] = 2;
        } catch (NoSuchFieldError unused24) {
        }
        try {
            BIo[TTH.zZm.INTERNAL_ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError unused25) {
        }
        zZm = new int[uxs.zZm.values().length];
        try {
            zZm[uxs.zZm.ALEXA_DOWN.ordinal()] = 1;
        } catch (NoSuchFieldError unused26) {
        }
        try {
            zZm[uxs.zZm.NOT_CONNECTED.ordinal()] = 2;
        } catch (NoSuchFieldError unused27) {
        }
        try {
            zZm[uxs.zZm.LOST_CONNECTION.ordinal()] = 3;
        } catch (NoSuchFieldError unused28) {
        }
        try {
            zZm[uxs.zZm.NETWORK_LOW_BANDWIDTH.ordinal()] = 4;
        } catch (NoSuchFieldError unused29) {
        }
        try {
            zZm[uxs.zZm.NETWORK_TRANSITION_AUTO.ordinal()] = 5;
        } catch (NoSuchFieldError unused30) {
        }
        try {
            zZm[uxs.zZm.NETWORK_TRANSITION_NON_AUTO.ordinal()] = 6;
        } catch (NoSuchFieldError unused31) {
        }
        try {
            zZm[uxs.zZm.CONNECTIVITY_ISSUE_AUTO.ordinal()] = 7;
        } catch (NoSuchFieldError unused32) {
        }
        try {
            zZm[uxs.zZm.CONNECTIVITY_ISSUE_NON_AUTO.ordinal()] = 8;
        } catch (NoSuchFieldError unused33) {
        }
    }
}
