package com.amazon.alexa.devices.speechrecognizer;

import com.amazon.alexa.devices.AlexaException;
import java.util.List;
/* loaded from: classes6.dex */
public interface UtteranceProvider {
    boolean listen(String str) throws AlexaException;

    void startUtteranceRecognition(List<String> list, int i, AudioEventListener audioEventListener) throws AlexaException;

    boolean stopAudioCapture(String str) throws AlexaException;

    void stopUtteranceRecognition() throws AlexaException;
}
