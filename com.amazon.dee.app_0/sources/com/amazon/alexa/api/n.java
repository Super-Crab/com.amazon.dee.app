package com.amazon.alexa.api;

import android.os.RemoteException;
import com.amazon.alexa.api.AlexaAudioInteractionProxy;
import java.util.UUID;
/* loaded from: classes6.dex */
class n extends AlexaAudioInteractionProxy.Stub {
    private AlexaAudioInteraction a;
    private final String b = UUID.randomUUID().toString();

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(AlexaAudioInteraction alexaAudioInteraction) {
        this.a = alexaAudioInteraction;
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
    public AlexaAudioChannel getAlexaAudioChannel() throws RemoteException {
        return this.a.getAlexaAudioChannel();
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
    public String getIdentifier() throws RemoteException {
        return this.b;
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
    public String getInteractionComponentName() {
        return this.a.getInteractionComponentName();
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
    public void onBackground() throws RemoteException {
        this.a.onBackground();
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
    public void onForeground() throws RemoteException {
        this.a.onForeground();
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
    public void onPause() throws RemoteException {
        this.a.onPause();
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
    public void onResume() throws RemoteException {
        this.a.onResume();
    }

    @Override // com.amazon.alexa.api.AlexaAudioInteractionProxy
    public void onStop() throws RemoteException {
        this.a.onStop();
    }
}
