package com.amazon.deecomms.sharing.templates;

import com.amazon.deecomms.sharing.exceptions.SharingSDKException;
import com.amazon.deecomms.sharing.payload.eventBus.SharingEventBusPayload;
import com.amazon.deecomms.sharing.payload.parse.ReactMessageMetadata;
import java.util.HashMap;
/* loaded from: classes12.dex */
public interface SharedMessageTemplate {
    ReactMessageMetadata getMessageMetadata();

    HashMap<String, String> getMetadata();

    String getTemplateName();

    boolean isValidTemplate();

    SharingEventBusPayload toEventBusPayload() throws SharingSDKException;
}
