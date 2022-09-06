package com.amazon.deecomms.api;

import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.deecomms.sharing.ContentSharingService;
/* loaded from: classes12.dex */
public interface CommsServiceV2 {
    ContentSharingService contentSharingService();

    MessagingReceiver conversationMessagingReceiver();

    OobeService oobeService();
}
