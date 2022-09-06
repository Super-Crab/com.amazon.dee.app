package com.amazon.alexa.sharing.sharingsdk.templates;

import com.amazon.alexa.sharing.api.exceptions.AlexaSharingSDKException;
import com.amazon.alexa.sharing.sharingsdk.payload.ReactMessageMetadata;
import com.amazon.alexa.sharing.sharingsdk.payload.response.SharingSDKResponsePayload;
import java.util.HashMap;
/* loaded from: classes10.dex */
public interface SharedMessageTemplate {
    ReactMessageMetadata getMessageMetadata();

    HashMap<String, String> getMetadata();

    String getTemplateName();

    boolean isValidTemplate();

    SharingSDKResponsePayload toResponsePayload() throws AlexaSharingSDKException;
}
