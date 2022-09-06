package com.amazon.alexa;

import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.client.alexaservice.notifications.AlexaNotificationManager;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AlexaNotificationManager.java */
/* loaded from: classes.dex */
public /* synthetic */ class shm {
    public static final /* synthetic */ int[] BIo;
    public static final /* synthetic */ int[] zQM = new int[wSq.values().length];
    public static final /* synthetic */ int[] zZm;

    static {
        try {
            zQM[wSq.LISTENING.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            zQM[wSq.IDLE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            zQM[wSq.SPEAKING.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            zQM[wSq.ERROR.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            zQM[wSq.THINKING.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            zQM[wSq.REQUEST_PROCESSING.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            zQM[wSq.PREPARING_TO_SPEAK.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        BIo = new int[AlexaPlayerInfoState.values().length];
        try {
            BIo[AlexaPlayerInfoState.CANCELLED.ordinal()] = 1;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            BIo[AlexaPlayerInfoState.ERROR.ordinal()] = 2;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            BIo[AlexaPlayerInfoState.PLAYING.ordinal()] = 3;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            BIo[AlexaPlayerInfoState.PAUSED.ordinal()] = 4;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            BIo[AlexaPlayerInfoState.BUFFERING.ordinal()] = 5;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            BIo[AlexaPlayerInfoState.DONE.ordinal()] = 6;
        } catch (NoSuchFieldError unused13) {
        }
        zZm = new int[AlexaNotificationManager.zZm.values().length];
        try {
            zZm[AlexaNotificationManager.zZm.TAP_TO_TALK.ordinal()] = 1;
        } catch (NoSuchFieldError unused14) {
        }
        try {
            zZm[AlexaNotificationManager.zZm.MEDIA_PAUSE.ordinal()] = 2;
        } catch (NoSuchFieldError unused15) {
        }
        try {
            zZm[AlexaNotificationManager.zZm.MEDIA_PLAY.ordinal()] = 3;
        } catch (NoSuchFieldError unused16) {
        }
        try {
            zZm[AlexaNotificationManager.zZm.MEDIA_NEXT.ordinal()] = 4;
        } catch (NoSuchFieldError unused17) {
        }
        try {
            zZm[AlexaNotificationManager.zZm.MEDIA_PREVIOUS.ordinal()] = 5;
        } catch (NoSuchFieldError unused18) {
        }
        try {
            zZm[AlexaNotificationManager.zZm.DISMISSED.ordinal()] = 6;
        } catch (NoSuchFieldError unused19) {
        }
    }
}
