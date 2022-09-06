package com.amazon.alexa.sharing.sharingsdk.templates;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingSDKException;
import com.amazon.alexa.sharing.media.picker.util.ImageUtil;
import com.amazon.alexa.sharing.sharingsdk.payload.PhotoSharingMessageEvent;
import com.amazon.alexa.sharing.sharingsdk.payload.PhotoSharingMessageEventPayload;
import com.amazon.alexa.sharing.sharingsdk.payload.ReactMessageMetadata;
import com.amazon.alexa.sharing.sharingsdk.payload.response.ShareRenderingTemplateResponsePayload;
import com.amazon.alexa.sharing.sharingsdk.payload.response.SharingSDKResponsePayload;
import com.amazon.alexa.sharing.sharingsdk.photos.CommonContentProperties;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.HashMap;
/* loaded from: classes10.dex */
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

    public static PhotoTemplate parseMessageMetadata(@NonNull ReactMessageMetadata reactMessageMetadata, @NonNull Context context, @NonNull ImageUtil imageUtil) throws AlexaSharingSDKException, JsonSyntaxException {
        PhotoSharingMessageEvent parseJson;
        String rawData = reactMessageMetadata.getRawData();
        try {
            if (isAndroidContentUri(rawData)) {
                parseJson = PhotoSharingMessageEvent.parseContentUri(reactMessageMetadata, context, imageUtil);
            } else {
                parseJson = PhotoSharingMessageEvent.parseJson(reactMessageMetadata, imageUtil);
            }
            return saveEventToTemplate(reactMessageMetadata, parseJson);
        } catch (IOException e) {
            CommsLogger commsLogger = LOG;
            commsLogger.e("Failed to parse MessageMetadata for rawData: <" + rawData + Config.Compare.GREATER_THAN, e);
            throw new AlexaSharingSDKException("Failed to parse MessageMetadata", "uriFail");
        }
    }

    @VisibleForTesting
    public static PhotoTemplate saveEventToTemplate(ReactMessageMetadata reactMessageMetadata, PhotoSharingMessageEvent photoSharingMessageEvent) throws AlexaSharingSDKException {
        PhotoSharingMessageEventPayload payload = photoSharingMessageEvent.getPayload();
        if (payload != null) {
            PhotoTemplate photoTemplate = new PhotoTemplate(reactMessageMetadata);
            CommsLogger commsLogger = LOG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[Content-Sharing-Service] Parsed a PhotoTemplate with Key: ");
            outline107.append(photoSharingMessageEvent.getTemplateKey());
            commsLogger.d(outline107.toString());
            String contentUri = payload.getContentUri();
            CommsLogger commsLogger2 = LOG;
            commsLogger2.d("[Content-Sharing-Service] Returning the Template with Image URI set to: " + contentUri);
            photoTemplate.setContentUri(contentUri);
            photoTemplate.setContentProperties(payload.getContentProperties());
            return photoTemplate;
        }
        throw new AlexaSharingSDKException("Invalid Template: Template expected an internal 'payload' attribute.", "payload");
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

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    @NonNull
    public ReactMessageMetadata getMessageMetadata() {
        return this.messageMetadata;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public HashMap<String, String> getMetadata() {
        HashMap<String, String> map = this.messageMetadata.toMap();
        map.put("messageType", "message/photo-message");
        return map;
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public String getTemplateName() {
        return "PhotoTemplate";
    }

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
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

    @Override // com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate
    public SharingSDKResponsePayload toResponsePayload() {
        LOG.d("[Content-Sharing-Service] Created a success payload for templateKey:", this.messageMetadata.getTemplateKey());
        ShareRenderingTemplateResponsePayload shareRenderingTemplateResponsePayload = new ShareRenderingTemplateResponsePayload();
        shareRenderingTemplateResponsePayload.name = "PhotoTemplate";
        shareRenderingTemplateResponsePayload.content = getContent();
        shareRenderingTemplateResponsePayload.metadata = getMetadata();
        shareRenderingTemplateResponsePayload.metadata.put("status", "SUCCESS");
        return shareRenderingTemplateResponsePayload;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("");
        outline107.append(getTemplateName());
        outline107.append(" {templateKey='");
        outline107.append(this.messageMetadata.getTemplateName());
        outline107.append("', globalMessageId='");
        outline107.append(this.messageMetadata.getGlobalMessageId());
        outline107.append("', imageURL='");
        return GeneratedOutlineSupport1.outline91(outline107, this.contentUri, "'}");
    }

    public PhotoTemplate(@NonNull ReactMessageMetadata reactMessageMetadata) {
        this.messageMetadata = reactMessageMetadata;
        this.contentUri = null;
        this.contentProperties = null;
    }
}
