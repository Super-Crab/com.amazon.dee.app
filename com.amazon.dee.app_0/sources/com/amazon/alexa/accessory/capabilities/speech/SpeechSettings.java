package com.amazon.alexa.accessory.capabilities.speech;

import com.amazon.alexa.accessory.protocol.Speech;
import com.amazon.alexa.accessory.repositories.device.DeviceFeature;
import java.util.Set;
/* loaded from: classes.dex */
public interface SpeechSettings {

    /* loaded from: classes.dex */
    public interface Callback {
        void onSpeechCompleted();

        void onSpeechProcessingStarted();

        void onSpeechRecognitionCancelled();

        void onSpeechRecognitionDenied();

        void onSpeechRecognitionFailed(Throwable th);

        void onSpeechRecognitionFinished();

        void onSpeechRecognitionStarted();

        void onSpeechRequest(SpeechRequest speechRequest);

        void onSpeechStarted();
    }

    /* loaded from: classes.dex */
    public interface SpeechRequest {
        void cancel();

        SpeechSession proceed(SpeechSettings speechSettings);
    }

    AccessoryIdentifierProvider getAccessoryIdentifierProvider();

    Speech.AudioFormat getAudioFormat();

    Speech.AudioProfile getAudioProfile();

    Callback getCallback();

    String getDeviceFirmwareVersion();

    String getDeviceName();

    String getDeviceSerialNumber();

    String getDeviceType();

    int getDialogId();

    Speech.SpeechInitiator getInitiator();

    String getLocale();

    SessionIdentifierProvider getSessionIdentifierProvider();

    Set<DeviceFeature> getSpeechRelatedFeatures();

    boolean getSuppressEndpointEarcon();

    boolean getSuppressStartEarcon();
}
