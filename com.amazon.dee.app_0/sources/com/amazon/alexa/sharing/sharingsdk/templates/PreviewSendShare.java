package com.amazon.alexa.sharing.sharingsdk.templates;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingSDKException;
import com.amazon.alexa.sharing.repo.models.acms.payload.sharing.GenericSharingMessageEvent;
import com.amazon.alexa.sharing.repo.models.acms.payload.sharing.GenericSharingMessageEventPayload;
import com.amazon.alexa.sharing.sharingsdk.payload.ReactMessageMetadata;
import com.amazon.alexa.sharing.sharingsdk.payload.response.PreviewSendShareResponse;
import com.amazon.alexa.sharing.sharingsdk.payload.response.SharingSDKResponsePayload;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.HashMap;
/* loaded from: classes10.dex */
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

    public static PreviewSendShare createFromEventPayload(@NonNull ReactMessageMetadata reactMessageMetadata) throws AlexaSharingSDKException, JsonSyntaxException {
        GenericSharingMessageEvent genericSharingMessageEvent = (GenericSharingMessageEvent) new Gson().fromJson(reactMessageMetadata.getRawData(), (Class<Object>) GenericSharingMessageEvent.class);
        if (genericSharingMessageEvent != null && !genericSharingMessageEvent.getTemplateKey().equals("TemplateKeyNotFound")) {
            PreviewSendShare previewSendShare = new PreviewSendShare(reactMessageMetadata);
            previewSendShare.setSharingMessage(genericSharingMessageEvent.getPayload());
            return previewSendShare;
        }
        CommsLogger commsLogger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[Content-Sharing-Service] GSON Serialized Class does not match the GenericSharingMessageEvent structure. Payload cannot be rendered. ID: ");
        outline107.append(reactMessageMetadata.getGlobalMessageId());
        commsLogger.e(outline107.toString());
        throw new JsonSyntaxException("GSON Serialization Failure: Not a SharingMessageEvent");
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    @NonNull
    public ReactMessageMetadata getMessageMetadata() {
        return this.messageMetadata;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public HashMap<String, String> getMetadata() {
        return this.messageMetadata.toMap();
    }

    @Nullable
    public GenericSharingMessageEventPayload getSharingMessage() {
        return this.sharingMessage;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public String getTemplateName() {
        return "PreviewSendShare";
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public boolean isValidTemplate() {
        return this.sharingMessage != null;
    }

    public void setMessageMetadata(@NonNull ReactMessageMetadata reactMessageMetadata) {
        this.messageMetadata = reactMessageMetadata;
    }

    public void setSharingMessage(@Nullable GenericSharingMessageEventPayload genericSharingMessageEventPayload) {
        this.sharingMessage = genericSharingMessageEventPayload;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public SharingSDKResponsePayload toResponsePayload() throws AlexaSharingSDKException {
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("");
        outline107.append(getTemplateName());
        outline107.append(" {templateKey='");
        outline107.append(getMessageMetadata().getTemplateKey());
        outline107.append("', sharingMessage='");
        outline107.append(this.sharingMessage);
        outline107.append("'}");
        return outline107.toString();
    }

    public PreviewSendShare(@Nullable GenericSharingMessageEventPayload genericSharingMessageEventPayload, @NonNull ReactMessageMetadata reactMessageMetadata) {
        this.sharingMessage = genericSharingMessageEventPayload;
        this.messageMetadata = reactMessageMetadata;
    }
}
