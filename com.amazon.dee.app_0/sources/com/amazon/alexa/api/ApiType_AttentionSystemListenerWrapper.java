package com.amazon.alexa.api;
/* loaded from: classes6.dex */
class ApiType_AttentionSystemListenerWrapper extends k implements AlexaAttentionSystemListener {
    private final AlexaAttentionSystemListener alexaAttentionSystemListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_AttentionSystemListenerWrapper(AlexaAttentionSystemListener alexaAttentionSystemListener) {
        this.alexaAttentionSystemListener = alexaAttentionSystemListener;
    }

    @Override // com.amazon.alexa.api.k, com.amazon.alexa.api.AlexaAttentionSystemListener
    public void onAlexaStateChanged(AlexaState alexaState, AlexaStateExtras alexaStateExtras) {
        this.alexaAttentionSystemListener.onAlexaStateChanged(alexaState, alexaStateExtras);
    }
}
