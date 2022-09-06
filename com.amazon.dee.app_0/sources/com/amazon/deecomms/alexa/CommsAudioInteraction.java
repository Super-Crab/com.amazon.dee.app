package com.amazon.deecomms.alexa;

import com.amazon.alexa.api.AlexaAudioChannel;
import com.amazon.alexa.api.AlexaAudioInteraction;
/* loaded from: classes12.dex */
public class CommsAudioInteraction implements AlexaAudioInteraction {
    @Override // com.amazon.alexa.api.AlexaAudioInteraction
    public AlexaAudioChannel getAlexaAudioChannel() {
        return AlexaAudioChannel.COMMUNICATIONS;
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteraction
    public String getInteractionComponentName() {
        return "SipClient";
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteraction
    public void onBackground() {
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteraction
    public void onForeground() {
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteraction
    public void onPause() {
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteraction
    public void onResume() {
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteraction
    public void onStop() {
    }
}
