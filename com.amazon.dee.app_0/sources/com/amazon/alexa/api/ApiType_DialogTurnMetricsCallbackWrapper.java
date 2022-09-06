package com.amazon.alexa.api;
/* loaded from: classes6.dex */
class ApiType_DialogTurnMetricsCallbackWrapper extends an implements AlexaDialogTurnMetricsCallback {
    private final AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_DialogTurnMetricsCallbackWrapper(AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        this.alexaDialogTurnMetricsCallback = alexaDialogTurnMetricsCallback;
    }

    @Override // com.amazon.alexa.api.an, com.amazon.alexa.api.AlexaDialogTurnMetricsCallback
    public void onUserPerceivedLatencyData(UserPerceivedLatencyData userPerceivedLatencyData) {
        this.alexaDialogTurnMetricsCallback.onUserPerceivedLatencyData(userPerceivedLatencyData);
    }
}
