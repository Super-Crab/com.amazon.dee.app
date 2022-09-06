package com.amazon.deecomms.alexa.voice;

import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaEvent;
/* loaded from: classes12.dex */
public interface IEventSender {
    void sendEvent(AlexaEvent alexaEvent);

    void sendEvent(AlexaEvent alexaEvent, boolean z);

    void sendEvent(AlexaEvent alexaEvent, boolean z, AlexaApiCallbacks alexaApiCallbacks);
}
