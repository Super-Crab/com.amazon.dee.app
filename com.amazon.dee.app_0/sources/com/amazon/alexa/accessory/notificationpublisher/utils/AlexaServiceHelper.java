package com.amazon.alexa.accessory.notificationpublisher.utils;

import com.amazon.alexa.accessory.notificationpublisher.audiofocus.AudioInteractionScheduler;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.api.compat.AlexaServicesApis;
/* loaded from: classes.dex */
public final class AlexaServiceHelper {
    private static final String TAG = "AlexaServiceHelper";

    private AlexaServiceHelper() {
    }

    public static void stopAlexa() {
        Log.i(TAG, "stopAlexa");
        AlexaServicesConnection alexaServicesConnection = AudioInteractionScheduler.getInstance().getAlexaServicesConnection();
        AudioInteractionScheduler.NotificationPlaybackState notificationPlaybackState = AudioInteractionScheduler.getInstance().getNotificationPlaybackState();
        String str = TAG;
        Log.i(str, "stopAlexa - notificationPlaybackState: " + notificationPlaybackState);
        if (alexaServicesConnection == null || !alexaServicesConnection.isConnected() || !notificationPlaybackState.equals(AudioInteractionScheduler.NotificationPlaybackState.NONE)) {
            return;
        }
        Log.i(TAG, "stopAlexa - Have a connected Alexa services connection and no notification is playing");
        AlexaServicesApis.AttentionSystem.stopForegroundAudioTask(alexaServicesConnection);
        AlexaServices.Recognizer.stop(alexaServicesConnection);
        AlexaServices.AudioPlaybackControl.stop(alexaServicesConnection);
    }
}
