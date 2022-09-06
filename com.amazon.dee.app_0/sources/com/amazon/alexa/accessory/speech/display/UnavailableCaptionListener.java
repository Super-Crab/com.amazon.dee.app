package com.amazon.alexa.accessory.speech.display;

import com.amazon.alexa.accessory.speechapi.listeners.Caption;
import com.amazon.alexa.accessory.speechapi.listeners.CaptionListener;
import com.amazon.alexa.api.AlexaCaptionListener;
import com.amazon.alexa.api.CaptionResponse;
/* loaded from: classes6.dex */
public class UnavailableCaptionListener implements AlexaCaptionListener, CaptionListener {
    @Override // com.amazon.alexa.accessory.speechapi.listeners.CaptionListener
    public void onReceivedCaption(Caption caption) {
    }

    @Override // com.amazon.alexa.api.AlexaCaptionListener
    public void onReceivedCaption(CaptionResponse captionResponse) {
    }
}
