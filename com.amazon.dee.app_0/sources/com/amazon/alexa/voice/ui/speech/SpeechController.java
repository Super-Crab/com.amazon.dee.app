package com.amazon.alexa.voice.ui.speech;

import com.amazon.alexa.voice.ui.util.BooleanProperty;
import com.amazon.alexa.voice.ui.util.FloatProperty;
/* loaded from: classes11.dex */
public interface SpeechController {
    void cancel();

    FloatProperty getSoundLevel();

    boolean hasMinimumRequiredPermission();

    BooleanProperty isListening();

    boolean isMultiturn();

    BooleanProperty isProcessing();

    BooleanProperty isSpeaking();

    void recognizeSpeech();
}
