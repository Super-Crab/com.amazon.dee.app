package com.amazon.deecomms.smsmessaging.messagingcontroller;

import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
/* loaded from: classes12.dex */
public class MessagingControllerEventModel extends AlexaEvent {
    public MessagingControllerEventModel(@NonNull AlexaHeader alexaHeader, @NonNull AlexaPayload alexaPayload) {
        super(alexaHeader, alexaPayload);
    }

    @NonNull
    public static AlexaHeader createHeader(@NonNull String str) {
        return AlexaHeader.builder().setNamespace(MessagingControllerConstant.MESSAGING_CONTROLLER_NAMESPACE).setName(str).build();
    }

    @NonNull
    public static AlexaPayload createPayload(@NonNull String str) {
        return new AlexaPayload(str);
    }
}
