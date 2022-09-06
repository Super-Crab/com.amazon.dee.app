package com.amazon.alexa.sharing.sharingsdk.templates;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.sharingsdk.payload.ReactMessageMetadata;
import com.amazon.alexa.sharing.sharingsdk.payload.response.ShareRenderingTemplateResponsePayload;
import com.amazon.alexa.sharing.sharingsdk.payload.response.SharingSDKResponsePayload;
import com.amazon.comms.log.CommsLogger;
import java.util.HashMap;
/* loaded from: classes10.dex */
public class ErrorTemplate implements SharedMessageTemplate {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ErrorTemplate.class);
    public static final String TYPE = "Error";
    @Nullable
    String errorCode;
    @Nullable
    String errorSource;
    @NonNull
    ReactMessageMetadata messageMetadata;

    public ErrorTemplate(@NonNull ReactMessageMetadata reactMessageMetadata, @Nullable String str, @Nullable String str2) {
        this.messageMetadata = reactMessageMetadata;
        this.errorCode = str;
        this.errorSource = str2;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    @NonNull
    public ReactMessageMetadata getMessageMetadata() {
        return this.messageMetadata;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public HashMap<String, String> getMetadata() {
        HashMap<String, String> map = this.messageMetadata.toMap();
        map.put("status", "ERROR");
        map.put("errorCode", this.errorCode);
        map.put("errorSource", this.errorSource);
        LOG.w("[Content-Sharing-Service] Created a generic error payload for globalMessageId:", this.messageMetadata.getGlobalMessageId());
        return map;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public String getTemplateName() {
        return "Error";
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public boolean isValidTemplate() {
        return true;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public SharingSDKResponsePayload toResponsePayload() {
        ShareRenderingTemplateResponsePayload shareRenderingTemplateResponsePayload = new ShareRenderingTemplateResponsePayload();
        shareRenderingTemplateResponsePayload.name = "Error";
        shareRenderingTemplateResponsePayload.metadata = getMetadata();
        shareRenderingTemplateResponsePayload.content = new HashMap<>();
        return shareRenderingTemplateResponsePayload;
    }
}
