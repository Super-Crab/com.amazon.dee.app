package com.amazon.alexa.devices.speechrecognizer;

import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.notification.INotificationComponent;
import java.io.OutputStream;
/* loaded from: classes6.dex */
public interface SpeechRecognizerComponent extends INotificationComponent {
    OutputStream createAudioOutputStream(AudioFormat audioFormat) throws AlexaException;

    boolean deregisterUtteranceProvider(UtteranceProvider utteranceProvider) throws AlexaException;

    void getAlexaListeningState(SpeechRecognizerCallback<AudioProcessingNotification> speechRecognizerCallback) throws AlexaException;

    boolean registerUtteranceProvider(UtteranceProvider utteranceProvider) throws AlexaException;

    OutputStream replaceAudioOutputStream(AudioFormat audioFormat) throws AlexaException;
}
