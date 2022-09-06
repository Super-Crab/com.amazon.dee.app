package com.amazon.dee.app.services.messaging;

import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.protocols.messaging.MessagingService;
/* loaded from: classes12.dex */
public class UnsupportedMessagingService implements MessagingService {
    @Override // com.amazon.alexa.protocols.messaging.MessagingService
    public void registerReceiver(@NonNull MessagingReceiver messagingReceiver) {
    }

    @Override // com.amazon.alexa.protocols.messaging.MessagingService
    public void registerReceiverWithHigherPriority(@NonNull MessagingReceiver messagingReceiver) {
    }

    @Override // com.amazon.alexa.protocols.messaging.MessagingService
    public void unregisterReceiver(@NonNull MessagingReceiver messagingReceiver) {
    }
}
