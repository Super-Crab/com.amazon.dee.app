package com.amazon.alexa.protocols.messaging;

import androidx.annotation.NonNull;
/* loaded from: classes9.dex */
public interface MessagingReceiver {
    public static final String NOTIFICATION = "NOTIFICATION";
    public static final String NOTIFICATION_TITLE = "NOTIFICATION_TITLE";

    void onReceive(@NonNull Message message);
}
