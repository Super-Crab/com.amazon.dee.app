package com.amazon.alexa.api;
/* loaded from: classes6.dex */
public interface AlexaDialogController {
    void onDialogFinished();

    void onDialogStarted();

    void onDialogTurnFinished();

    void onDialogTurnStarted();

    void startRecordingNextDialogTurn();

    void stopRecording();
}
