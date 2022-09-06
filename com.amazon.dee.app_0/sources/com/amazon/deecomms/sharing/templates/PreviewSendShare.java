package com.amazon.deecomms.sharing.templates;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.sharing.exceptions.SharingSDKException;
import com.amazon.deecomms.sharing.payload.eventBus.PreviewSendShareResponse;
import com.amazon.deecomms.sharing.payload.eventBus.SharingEventBusPayload;
import com.amazon.deecomms.sharing.payload.parse.GenericSharingMessageEvent;
import com.amazon.deecomms.sharing.payload.parse.GenericSharingMessageEventPayload;
import com.amazon.deecomms.sharing.payload.parse.ReactMessageMetadata;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.HashMap;
/* loaded from: classes12.dex */
public class PreviewSendShare implements SharedMessageTemplate {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PreviewSendShare.class);
    public static final String TYPE = "PreviewSendShare";
    @NonNull
    ReactMessageMetadata messageMetadata;
    @Nullable
    GenericSharingMessageEventPayload sharingMessage;

    public PreviewSendShare(@NonNull ReactMessageMetadata reactMessageMetadata) {
        this(null, reactMessageMetadata);
    }

    public static PreviewSendShare createFromEventPayload(@NonNull ReactMessageMetadata reactMessageMetadata) throws SharingSDKException, JsonSyntaxException {
        GenericSharingMessageEvent genericSharingMessageEvent = (GenericSharingMessageEvent) new Gson().fromJson(reactMessageMetadata.getRawData(), (Class<Object>) GenericSharingMessageEvent.class);
        if (genericSharingMessageEvent != null && genericSharingMessageEvent.getTemplateKey() != "TemplateKeyNotFound") {
            PreviewSendShare previewSendShare = new PreviewSendShare(null, reactMessageMetadata);
            previewSendShare.setSharingMessage(genericSharingMessageEvent.getPayload());
            return previewSendShare;
        }
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("[Content-Sharing-Service] GSON Serialized Class does not match the GenericSharingMessageEvent structure. Payload cannot be rendered. ID: ");
        outline1.append(reactMessageMetadata.getGlobalMessageId());
        commsLogger.e(outline1.toString());
        throw new JsonSyntaxException("GSON Serialization Failure: Not a SharingMessageEvent");
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    @NonNull
    public ReactMessageMetadata getMessageMetadata() {
        return this.messageMetadata;
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    public HashMap<String, String> getMetadata() {
        return this.messageMetadata.toMap();
    }

    @Nullable
    public GenericSharingMessageEventPayload getSharingMessage() {
        return this.sharingMessage;
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    public String getTemplateName() {
        return "PreviewSendShare";
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    public boolean isValidTemplate() {
        return this.sharingMessage != null;
    }

    public void setMessageMetadata(@NonNull ReactMessageMetadata reactMessageMetadata) {
        this.messageMetadata = reactMessageMetadata;
    }

    public void setSharingMessage(@Nullable GenericSharingMessageEventPayload genericSharingMessageEventPayload) {
        this.sharingMessage = genericSharingMessageEventPayload;
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    public SharingEventBusPayload toEventBusPayload() throws SharingSDKException {
        PreviewSendShareResponse previewSendShareResponse = new PreviewSendShareResponse(this.sharingMessage);
        HeroImageWithCaptionTemplate heroImageWithCaptionTemplate = new HeroImageWithCaptionTemplate(this.messageMetadata);
        heroImageWithCaptionTemplate.updateTemplateForPayload(this.sharingMessage);
        HashMap<String, String> metadata = getMetadata();
        metadata.put("status", "SUCCESS");
        metadata.put("templateName", "HeroImageWithCaptionTemplate");
        previewSendShareResponse.setMetadata(metadata);
        previewSendShareResponse.setContent(heroImageWithCaptionTemplate.getContent());
        previewSendShareResponse.setName("PreviewSendShare");
        return previewSendShareResponse;
    }

    public String toString() {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("");
        outline1.append(getTemplateName());
        outline1.append(" {templateKey='");
        outline1.append(getMessageMetadata().getTemplateKey());
        outline1.append("', sharingMessage='");
        outline1.append(this.sharingMessage);
        outline1.append("'}");
        return outline1.toString();
    }

    public PreviewSendShare(@Nullable GenericSharingMessageEventPayload genericSharingMessageEventPayload, @NonNull ReactMessageMetadata reactMessageMetadata) {
        this.sharingMessage = genericSharingMessageEventPayload;
        this.messageMetadata = reactMessageMetadata;
    }
}
