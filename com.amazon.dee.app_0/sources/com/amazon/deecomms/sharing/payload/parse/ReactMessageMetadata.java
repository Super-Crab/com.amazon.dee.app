package com.amazon.deecomms.sharing.payload.parse;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.sharing.ContentSharingService;
import com.amazon.deecomms.sharing.utils.SharingUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.HashMap;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes12.dex */
public class ReactMessageMetadata {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContentSharingService.class);
    @NonNull
    private String conversationId;
    @Nullable
    private String domainType;
    @Nullable
    private String globalMessageId;
    @Nullable
    private String messageType;
    @NonNull
    private String rawData;
    @NonNull
    private String templateKey;
    @Nullable
    private String templateName;

    public ReactMessageMetadata(@NonNull String str) {
        this.conversationId = "";
        this.rawData = str;
        this.globalMessageId = "";
        this.templateKey = "TemplateKeyNotFound";
        this.templateName = "";
        this.messageType = "";
        this.domainType = "";
    }

    @NonNull
    private static String extractConversationIdFromPayload(@NonNull JsonObject jsonObject) {
        return jsonObject.has("conversationId") ? jsonObject.get("conversationId").getAsString() : "";
    }

    @NonNull
    private static String extractGlobalMessageIdFromPayload(@NonNull JsonObject jsonObject) {
        if (jsonObject.has("globalMessageId")) {
            return jsonObject.get("globalMessageId").getAsString();
        }
        return jsonObject.has("globalMessageId") ? jsonObject.get("globalMessageId").getAsString() : "";
    }

    @NonNull
    private static String extractTemplateKeyFromPayload(@NonNull JsonObject jsonObject) {
        if (jsonObject.has("templateKey")) {
            return jsonObject.get("templateKey").getAsString();
        }
        return jsonObject.has("reactionId") ? jsonObject.get("reactionId").getAsString() : "TemplateKeyNotFound";
    }

    @NonNull
    private static String extractTemplateNameFromPayload(@NonNull JsonObject jsonObject) {
        return jsonObject.has("name") ? jsonObject.get("name").getAsString() : "";
    }

    @NonNull
    public static ReactMessageMetadata fromContentURI(Uri uri) {
        String uri2 = uri.toString();
        ReactMessageMetadata reactMessageMetadata = new ReactMessageMetadata(uri2);
        try {
            reactMessageMetadata.setTemplateName("PhotoTemplate");
            reactMessageMetadata.setDomainType("photo");
            StringBuilder sb = new StringBuilder();
            sb.append("share-sheet-content::");
            sb.append(uri.getLastPathSegment());
            reactMessageMetadata.setTemplateKey(sb.toString());
        } catch (Exception e) {
            LOG.e("[Content-Sharing-Service] This URI cannot be parsed.", e);
            CommsLogger commsLogger = LOG;
            commsLogger.d("[Content-Sharing-Service] This URI cannot be parsed.", commsLogger.sensitive(uri2));
        }
        return reactMessageMetadata;
    }

    @NonNull
    public static ReactMessageMetadata fromJson(@NonNull String str) {
        JsonObject jsonObject;
        String extractTemplateNameFromPayload;
        String messageTypeFromSharedMediaMessage;
        String domainTypeFromMessageType;
        ReactMessageMetadata reactMessageMetadata = new ReactMessageMetadata(str);
        try {
            jsonObject = (JsonObject) new Gson().fromJson(str, (Class<Object>) JsonObject.class);
            String extractTemplateKeyFromPayload = extractTemplateKeyFromPayload(jsonObject);
            if (!extractTemplateKeyFromPayload.equals("")) {
                reactMessageMetadata.setTemplateKey(extractTemplateKeyFromPayload);
            }
            String extractGlobalMessageIdFromPayload = extractGlobalMessageIdFromPayload(jsonObject);
            if (!extractGlobalMessageIdFromPayload.equals("")) {
                reactMessageMetadata.setGlobalMessageId(extractGlobalMessageIdFromPayload);
            }
            String extractConversationIdFromPayload = extractConversationIdFromPayload(jsonObject);
            if (!extractConversationIdFromPayload.equals("")) {
                reactMessageMetadata.setConversationId(extractConversationIdFromPayload);
            }
            extractTemplateNameFromPayload = extractTemplateNameFromPayload(jsonObject);
            if (!extractTemplateNameFromPayload.equals("")) {
                reactMessageMetadata.setTemplateName(extractTemplateNameFromPayload);
            }
        } catch (Exception e) {
            LOG.e("[Content-Sharing-Service] Raw data is not a JSON object.", e);
            CommsLogger commsLogger = LOG;
            commsLogger.d("[Content-Sharing-Service] Raw data is not a JSON object.", commsLogger.sensitive(str));
        }
        if (!"AddReactionEvent".equals(extractTemplateNameFromPayload) && !"RemoveReactionEvent".equals(extractTemplateNameFromPayload)) {
            domainTypeFromMessageType = new SharingUtils().extractDomainTypeFromPayload(jsonObject);
            messageTypeFromSharedMediaMessage = new SharingUtils().extractMessageTypeFromPayload(jsonObject);
            reactMessageMetadata.setDomainType(domainTypeFromMessageType);
            reactMessageMetadata.setMessageType(messageTypeFromSharedMediaMessage);
            return reactMessageMetadata;
        }
        messageTypeFromSharedMediaMessage = new SharingUtils().getMessageTypeFromSharedMediaMessage(jsonObject.get("sharedMediaMessage").getAsJsonObject());
        domainTypeFromMessageType = new SharingUtils().getDomainTypeFromMessageType(messageTypeFromSharedMediaMessage);
        reactMessageMetadata.setDomainType(domainTypeFromMessageType);
        reactMessageMetadata.setMessageType(messageTypeFromSharedMediaMessage);
        return reactMessageMetadata;
    }

    @NonNull
    public String getConversationId() {
        return this.conversationId;
    }

    @NonNull
    public String getDomainType() {
        return this.domainType;
    }

    @Nullable
    public String getGlobalMessageId() {
        return this.globalMessageId;
    }

    @NonNull
    public String getMessageType() {
        return this.messageType;
    }

    @NonNull
    public String getRawData() {
        return this.rawData;
    }

    @NonNull
    public String getTemplateKey() {
        return this.templateKey;
    }

    @Nullable
    public String getTemplateName() {
        return this.templateName;
    }

    public void setConversationId(@NonNull String str) {
        this.conversationId = str;
    }

    public void setDomainType(@NonNull String str) {
        this.domainType = str;
    }

    public void setGlobalMessageId(@Nullable String str) {
        this.globalMessageId = str;
    }

    public void setMessageType(@NonNull String str) {
        this.messageType = str;
    }

    public void setRawData(@NonNull String str) {
        this.rawData = str;
    }

    public void setTemplateKey(@NonNull String str) {
        this.templateKey = str;
    }

    public void setTemplateName(@Nullable String str) {
        this.templateName = str;
    }

    public HashMap<String, String> toMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("conversationId", this.conversationId);
        hashMap.put("globalMessageId", this.globalMessageId);
        hashMap.put("templateKey", this.templateKey);
        hashMap.put("domainType", this.domainType);
        hashMap.put("messageType", this.messageType);
        return hashMap;
    }

    public String toString() {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("ReactMessageMetadata {\n\"conversationId\": \"");
        outline1.append(this.conversationId);
        outline1.append("\",\n\t\"globalMessageId\": \"");
        outline1.append(this.globalMessageId);
        outline1.append("\",\n\t\"templateKey\": \"");
        outline1.append(this.templateKey);
        outline1.append("\",\n\t\"domainType\": \"");
        outline1.append(this.domainType);
        outline1.append("\",\n\t\"messageType\": \"");
        outline1.append(this.messageType);
        outline1.append("\",\n\t\"templateName\": \"");
        outline1.append(this.templateName);
        outline1.append("\"\n");
        outline1.append(JsonReaderKt.END_OBJ);
        return outline1.toString();
    }

    public ReactMessageMetadata(GenericSharingMessageEvent genericSharingMessageEvent, Gson gson) {
        String json = gson.toJson(genericSharingMessageEvent.getPayload());
        this.conversationId = "";
        this.globalMessageId = genericSharingMessageEvent.getGlobalMessageId();
        this.templateName = genericSharingMessageEvent.getName();
        this.templateKey = genericSharingMessageEvent.getTemplateKey();
        this.rawData = json;
        SharingUtils sharingUtils = new SharingUtils();
        this.messageType = sharingUtils.getMessageTypeFromEvent(genericSharingMessageEvent);
        this.domainType = sharingUtils.getDomainTypeFromEvent(genericSharingMessageEvent);
    }
}
