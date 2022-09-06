package com.amazon.alexa.api;
/* loaded from: classes6.dex */
class ApiType_DialogTurnStopCallbackWrapper extends ao implements AlexaDialogTurnStopCallback {
    private final AlexaDialogTurnStopCallback alexaDialogTurnStopCallback;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_DialogTurnStopCallbackWrapper(AlexaDialogTurnStopCallback alexaDialogTurnStopCallback) {
        this.alexaDialogTurnStopCallback = alexaDialogTurnStopCallback;
    }

    @Override // com.amazon.alexa.api.ao, com.amazon.alexa.api.AlexaDialogTurnStopCallback
    public void stopRecording() {
        this.alexaDialogTurnStopCallback.stopRecording();
    }
}
