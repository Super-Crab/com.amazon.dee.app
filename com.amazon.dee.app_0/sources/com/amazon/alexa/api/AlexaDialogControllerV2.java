package com.amazon.alexa.api;
/* loaded from: classes6.dex */
public interface AlexaDialogControllerV2 {
    void onDialogFinished();

    void onDialogStarted();

    void onDialogTurnFinished();

    void onDialogTurnStarted(String str);

    void startRecordingNextDialogTurn();

    void stopRecording();
}
