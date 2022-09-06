package com.amazon.alexa.voice.downchannel;

import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.messaging.Message;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
/* loaded from: classes11.dex */
public class NoOpMessagingReceiver implements MessagingReceiver {
    @Override // com.amazon.alexa.protocols.messaging.MessagingReceiver
    public void onReceive(@NonNull Message message) {
    }
}
