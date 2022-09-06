package com.amazon.deecomms.core;

import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.conversation.CommsDynamicFeatureService;
import com.amazon.deecomms.conversation.ConversationService;
/* loaded from: classes12.dex */
public interface ICommsService {
    CommsDeviceSupport getCommsDeviceSupport();

    CommsDynamicFeatureService getCommsDynamicFeatureService();

    FeatureFilter getCommsFeatureFilter();

    CommsManager getCommsManager();

    ConversationService getConversationService();
}
