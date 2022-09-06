package com.amazon.alexa.accessory.notificationpublisher.transcriber.bluefront;

import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class SpeechClientConfiguration {
    public static final float DNN_NOISE_TO_SPEECH_RATIO = 1.6f;
    public static final int DNN_SPEECH_END_POINTING_THRESHOLD = 220;
    public static final int DNN_SPEECH_START_POINTING_THRESHOLD = 1;
    public static final int NO_SPEECH_TIMEOUT_IN_MILLISECONDS = (int) TimeUnit.SECONDS.toMillis(10);
    public static final int MAXIMUM_SPEECH_TIMEOUT_IN_MILLISECONDS = (int) TimeUnit.SECONDS.toMillis(30);

    private SpeechClientConfiguration() {
    }
}
