package com.amazon.alexa.api;
/* loaded from: classes6.dex */
public interface AlexaUserSpeechProvider {
    void onDialogFinished(DialogData dialogData);

    void onDialogRequestDenied(DialogRequestDeniedReason dialogRequestDeniedReason);

    void onDialogRequested(AlexaDialogTurn alexaDialogTurn);

    void onDialogStarted(DialogData dialogData);

    void onDialogTurnFinished(DialogTurnData dialogTurnData);

    void onDialogTurnRequested(AlexaNextDialogTurn alexaNextDialogTurn);

    void onDialogTurnStarted(DialogTurnData dialogTurnData);

    void pauseWakeWordDetection(String str);

    void resumeWakeWordDetection(String str);

    void setWakeWordDetectionEnabled(boolean z);
}
