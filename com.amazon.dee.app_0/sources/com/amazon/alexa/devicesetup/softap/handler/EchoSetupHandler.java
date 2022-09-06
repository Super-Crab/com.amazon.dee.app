package com.amazon.alexa.devicesetup.softap.handler;

import com.amazon.alexa.eventbus.api.Message;
/* loaded from: classes7.dex */
public interface EchoSetupHandler {
    void handleEvent(Message message);

    void startListening();
}
