package com.amazon.alexa.voice.elements;

import com.amazon.alexa.api.AlexaCardRendererListener;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
/* loaded from: classes11.dex */
public class AlexaCardAPI {
    public void deRegisterForAlexaCardRenderer(AlexaServicesConnection alexaServicesConnection, AlexaCardRendererListener alexaCardRendererListener) {
        AlexaServices.Cards.deregisterCardRendererListener(alexaServicesConnection, alexaCardRendererListener);
    }

    public void registerForAlexaCardRenderer(AlexaServicesConnection alexaServicesConnection, AlexaCardRendererListener alexaCardRendererListener) {
        AlexaServices.Cards.registerAlexaCardRendererListener(alexaServicesConnection, alexaCardRendererListener);
    }
}
