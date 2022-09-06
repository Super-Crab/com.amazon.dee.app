package com.amazon.alexa.api;
/* loaded from: classes6.dex */
class ApiType_CaptionListenerWrapper extends ag implements AlexaCaptionListener {
    private final AlexaCaptionListener alexaCaptionListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiType_CaptionListenerWrapper(AlexaCaptionListener alexaCaptionListener) {
        this.alexaCaptionListener = alexaCaptionListener;
    }

    @Override // com.amazon.alexa.api.ag, com.amazon.alexa.api.AlexaCaptionListener
    public void onReceivedCaption(CaptionResponse captionResponse) {
        this.alexaCaptionListener.onReceivedCaption(captionResponse);
    }
}
