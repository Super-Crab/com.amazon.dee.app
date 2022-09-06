package com.amazon.deecomms.alexa.voice.usecase;

import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class EventSenderUseCase {
    private AlexaServicesConnection mAlexaServicesConnection;

    @Inject
    public EventSenderUseCase(@Named("commsAlexaServiceConnection") AlexaServicesConnection alexaServicesConnection) {
        this.mAlexaServicesConnection = alexaServicesConnection;
    }

    public void send(AlexaEvent alexaEvent, boolean z, AlexaApiCallbacks alexaApiCallbacks) {
        AlexaServices.EventSender.send(this.mAlexaServicesConnection, alexaEvent, z, alexaApiCallbacks);
    }
}
