package com.amazon.alexa.protocols.messaging;

import androidx.annotation.NonNull;
/* loaded from: classes9.dex */
public interface MessagingService {
    void registerReceiver(@NonNull MessagingReceiver messagingReceiver);

    void registerReceiverWithHigherPriority(@NonNull MessagingReceiver messagingReceiver);

    void unregisterReceiver(@NonNull MessagingReceiver messagingReceiver);
}
