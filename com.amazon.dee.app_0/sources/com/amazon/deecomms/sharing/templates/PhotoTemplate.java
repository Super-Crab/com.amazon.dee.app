package com.amazon.deecomms.sharing.templates;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.nativemodules.imagepicker.util.Compression;
import com.amazon.deecomms.sharing.exceptions.SharingSDKException;
import com.amazon.deecomms.sharing.payload.eventBus.ShareRenderingTemplateEventBusPayload;
import com.amazon.deecomms.sharing.payload.eventBus.SharingEventBusPayload;
import com.amazon.deecomms.sharing.payload.parse.PhotoSharingMessageEvent;
import com.amazon.deecomms.sharing.payload.parse.PhotoSharingMessageEventPayload;
import com.amazon.deecomms.sharing.payload.parse.ReactMessageMetadata;
import com.amazon.deecomms.sharing.photos.CommonContentProperties;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.HashMap;
/* loaded from: classes12.dex */
public class PhotoTemplate implements SharedMessageTemplate {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PhotoTemplate.class);
    public static final String TYPE = "PhotoTemplate";
    @Nullable
    private CommonContentProperties contentProperties;
    @Nullable
    String contentUri;
    @NonNull
    ReactMessageMetadata messageMetadata;

    public PhotoTemplate(@NonNull ReactMessageMetadata reactMessageMetadata, @Nullable String str, @Nullable CommonContentProperties commonContentProperties) {
        this.messageMetadata = reactMessageMetadata;
        this.contentUri = str;
        this.contentProperties = commonContentProperties;
    }

    private static boolean isAndroidContentUri(String str) {
        return str.startsWith("content:") || str.startsWith("raw:") || str.startsWith("file:");
    }

    public static PhotoTemplate parseMessageMetadata(@NonNull ReactMessageMetadata reactMessageMetadata, @NonNull Context context, @NonNull Compression compression) throws SharingSDKException, JsonSyntaxException {
        PhotoSharingMessageEvent parseJson;
        String rawData = reactMessageMetadata.getRawData();
        try {
            if (isAndroidContentUri(rawData)) {
                parseJson = PhotoSharingMessageEvent.parseContentUri(reactMessageMetadata, context, compression);
            } else {
                parseJson = PhotoSharingMessageEvent.parseJson(reactMessageMetadata, context, compression);
            }
            return saveEventToTemplate(reactMessageMetadata, parseJson);
        } catch (IOException e) {
            CommsLogger commsLogger = LOG;
            commsLogger.e("Failed to parse MessageMetadata for rawData: <" + rawData + Config.Compare.GREATER_THAN, e);
            throw new SharingSDKException("Failed to parse MessageMetadata", "uriFail");
        }
    }

    @VisibleForTesting
    public static PhotoTemplate saveEventToTemplate(ReactMessageMetadata reactMessageMetadata, PhotoSharingMessageEvent photoSharingMessageEvent) throws SharingSDKException {
        PhotoSharingMessageEventPayload payload = photoSharingMessageEvent.getPayload();
        if (payload != null) {
            PhotoTemplate photoTemplate = new PhotoTemplate(reactMessageMetadata);
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("[Content-Sharing-Service] Parsed a PhotoTemplate with Key: ");
            outline1.append(photoSharingMessageEvent.getTemplateKey());
            commsLogger.d(outline1.toString());
            String contentUri = payload.getContentUri();
            CommsLogger commsLogger2 = LOG;
            commsLogger2.d("[Content-Sharing-Service] Returning the Template with Image URI set to: " + contentUri);
            photoTemplate.setContentUri(contentUri);
            photoTemplate.contentProperties = payload.getContentProperties();
            return photoTemplate;
        }
        throw new SharingSDKException("Invalid Template: Template expected an internal 'payload' attribute.", "payload");
    }

    private void setContentProperties(@Nullable CommonContentProperties commonContentProperties) {
        this.contentProperties = commonContentProperties;
    }

    public HashMap<String, Object> getContent() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("contentUri", getContentUri());
        hashMap.put("contentProperties", this.contentProperties);
        return hashMap;
    }

    @Nullable
    public String getContentUri() {
        return this.contentUri;
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    @NonNull
    public ReactMessageMetadata getMessageMetadata() {
        return this.messageMetadata;
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    public HashMap<String, String> getMetadata() {
        HashMap<String, String> map = this.messageMetadata.toMap();
        map.put("messageType", "message/photo-message");
        return map;
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    public String getTemplateName() {
        return "PhotoTemplate";
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    public boolean isValidTemplate() {
        String str = this.contentUri;
        return str != null && !str.isEmpty();
    }

    public void setContentUri(@Nullable String str) {
        this.contentUri = str;
    }

    public void setMessageMetadata(@NonNull ReactMessageMetadata reactMessageMetadata) {
        this.messageMetadata = reactMessageMetadata;
    }

    @Override // com.amazon.deecomms.sharing.templates.SharedMessageTemplate
    public SharingEventBusPayload toEventBusPayload() {
        LOG.d("[Content-Sharing-Service] Created a success payload for templateKey:", this.messageMetadata.getTemplateKey());
        ShareRenderingTemplateEventBusPayload shareRenderingTemplateEventBusPayload = new ShareRenderingTemplateEventBusPayload();
        shareRenderingTemplateEventBusPayload.name = "PhotoTemplate";
        shareRenderingTemplateEventBusPayload.content = getContent();
        shareRenderingTemplateEventBusPayload.metadata = getMetadata();
        shareRenderingTemplateEventBusPayload.metadata.put("status", "SUCCESS");
        return shareRenderingTemplateEventBusPayload;
    }

    public String toString() {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("");
        outline1.append(getTemplateName());
        outline1.append(" {templateKey='");
        outline1.append(this.messageMetadata.getTemplateName());
        outline1.append("', globalMessageId='");
        outline1.append(this.messageMetadata.getGlobalMessageId());
        outline1.append("', imageURL='");
        return GeneratedOutlineSupport1.outline91(outline1, this.contentUri, "'}");
    }

    public PhotoTemplate(@NonNull ReactMessageMetadata reactMessageMetadata) {
        this.messageMetadata = reactMessageMetadata;
        this.contentUri = null;
        this.contentProperties = null;
    }
}
