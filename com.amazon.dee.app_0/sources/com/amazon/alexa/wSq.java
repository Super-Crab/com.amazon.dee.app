package com.amazon.alexa;

import com.amazon.alexa.api.AlexaState;
/* compiled from: InternalAlexaState.java */
/* loaded from: classes.dex */
public enum wSq {
    PREPARING_TO_LISTEN(AlexaState.LISTENING),
    LISTENING(AlexaState.LISTENING),
    THINKING(AlexaState.THINKING),
    REQUEST_PROCESSING(AlexaState.THINKING),
    PREPARING_TO_SPEAK(AlexaState.THINKING),
    SPEAKING(AlexaState.SPEAKING),
    ERROR(AlexaState.ERROR),
    IDLE(AlexaState.IDLE),
    UNKNOWN(AlexaState.UNKNOWN);
    
    public final AlexaState associatedExternalAlexaState;

    wSq(AlexaState alexaState) {
        this.associatedExternalAlexaState = alexaState;
    }

    public AlexaState zZm() {
        return this.associatedExternalAlexaState;
    }
}
