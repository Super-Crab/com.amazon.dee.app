package com.amazon.alexa.accessory.notificationpublisher.transcriber.bluefront;

import com.amazon.blueshift.bluefront.android.SpeechClient;
import com.google.common.base.Preconditions;
/* loaded from: classes.dex */
public final class SpeechClientManager {
    private static SpeechClient<?> speechClient;

    private SpeechClientManager() {
    }

    public static void destroyClient() {
        SpeechClient<?> speechClient2 = speechClient;
        if (speechClient2 != null) {
            speechClient2.cancel();
            speechClient = null;
        }
    }

    public static boolean isListening() {
        SpeechClient<?> speechClient2 = speechClient;
        if (speechClient2 != null) {
            return speechClient2.isListening();
        }
        return false;
    }

    public static void startRequest(SpeechClient<?> speechClient2) {
        Preconditions.checkNotNull(speechClient2, "Speech client cannot be null.");
        destroyClient();
        speechClient = speechClient2;
        speechClient.startListening();
    }

    public static void stopRequest() {
        SpeechClient<?> speechClient2 = speechClient;
        if (speechClient2 != null) {
            speechClient2.stopListening();
        }
    }
}
