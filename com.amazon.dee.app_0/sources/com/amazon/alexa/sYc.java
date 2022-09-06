package com.amazon.alexa;

import com.amazon.alexa.api.UiEventName;
/* compiled from: StandardVoiceCardMetricsAuthority.java */
/* loaded from: classes.dex */
/* synthetic */ class sYc {
    public static final /* synthetic */ int[] zZm = new int[UiEventName.values().length];

    static {
        try {
            zZm[UiEventName.JSON_PARSING_LATENCY.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zZm[UiEventName.CARD_VIEWS_CREATED_LATENCY.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zZm[UiEventName.CARD_RENDER_LATENCY.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zZm[UiEventName.CARD_SHOWN.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zZm[UiEventName.CARD_CREATION_LATENCY.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zZm[UiEventName.CARD_INTERACTED.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            zZm[UiEventName.CARD_INGRESS_TAPPED.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            zZm[UiEventName.NAVIGATION_TO_EXTERNAL_LINK.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            zZm[UiEventName.NAVIGATION_TO_INTERNAL_CARD.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            zZm[UiEventName.FOUND_CARD_FACTORY.ordinal()] = 10;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            zZm[UiEventName.MISSING_CARD_FACTORY.ordinal()] = 11;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            zZm[UiEventName.CARD_CONTROLLER_CREATION_ERROR.ordinal()] = 12;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            zZm[UiEventName.CARD_CREATION_ERROR.ordinal()] = 13;
        } catch (NoSuchFieldError unused13) {
        }
        try {
            zZm[UiEventName.JSON_PARSING_ERROR.ordinal()] = 14;
        } catch (NoSuchFieldError unused14) {
        }
    }
}
