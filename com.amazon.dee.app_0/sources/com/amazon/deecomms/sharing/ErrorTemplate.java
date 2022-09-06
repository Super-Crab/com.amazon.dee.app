package com.amazon.deecomms.sharing;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.sharing.payload.eventBus.ShareRenderingTemplateEventBusPayload;
import com.amazon.deecomms.sharing.payload.eventBus.SharingEventBusPayload;
import com.amazon.deecomms.sharing.payload.parse.ReactMessageMetadata;
import com.amazon.deecomms.sharing.templates.HeroImageWithCaptionTemplate;
import com.amazon.deecomms.sharing.templates.SharedMessageTemplate;
import java.util.HashMap;
/* loaded from: classes12.dex */
public class ErrorTemplate implements SharedMessageTemplate {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, HeroImageWithCaptionTemplate.class);
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

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    @NonNull
    public ReactMessageMetadata getMessageMetadata() {
        return this.messageMetadata;
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    public HashMap<String, String> getMetadata() {
        HashMap<String, String> map = this.messageMetadata.toMap();
        map.put("status", "ERROR");
        map.put("errorCode", this.errorCode);
        map.put("errorSource", this.errorSource);
        LOG.w("[Content-Sharing-Service] Created a generic error payload for globalMessageId:", this.messageMetadata.getGlobalMessageId());
        return map;
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    public String getTemplateName() {
        return "Error";
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    public boolean isValidTemplate() {
        return true;
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    public SharingEventBusPayload toEventBusPayload() {
        ShareRenderingTemplateEventBusPayload shareRenderingTemplateEventBusPayload = new ShareRenderingTemplateEventBusPayload();
        shareRenderingTemplateEventBusPayload.name = "Error";
        shareRenderingTemplateEventBusPayload.metadata = getMetadata();
        shareRenderingTemplateEventBusPayload.content = new HashMap<>();
        return shareRenderingTemplateEventBusPayload;
    }
}
