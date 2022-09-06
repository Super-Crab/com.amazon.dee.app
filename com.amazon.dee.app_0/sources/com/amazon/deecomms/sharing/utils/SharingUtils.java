package com.amazon.deecomms.sharing.utils;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.messaging.model.payload.SharingMessagePayload;
import com.amazon.deecomms.sharing.payload.parse.GenericSharingMessageEvent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.UUID;
/* loaded from: classes12.dex */
public class SharingUtils {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SharingUtils.class);
    private final Gson gson;

    public SharingUtils() {
        this.gson = new Gson();
    }

    private String convertMessageTypeToDomainType(String str) {
        if (str != null) {
            if ("message/shared-content".equals(str)) {
                return "link";
            }
            String[] split = str.split("/");
            String str2 = split[0];
            return "message".equals(str2) ? split[1] : str2;
        }
        return "unknown";
    }

    public String extractDomainTypeFromPayload(JsonObject jsonObject) {
        return convertMessageTypeToDomainType(extractMessageTypeFromPayload(jsonObject));
    }

    public String extractMessageTypeFromPayload(JsonObject jsonObject) {
        return !jsonObject.has("payload") ? "" : getOptionalStringFromJSON(jsonObject.getAsJsonObject("payload"), "messageType");
    }

    public String getDomainTypeFromEvent(GenericSharingMessageEvent genericSharingMessageEvent) {
        return convertMessageTypeToDomainType(getMessageTypeFromEvent(genericSharingMessageEvent));
    }

    public String getDomainTypeFromMessageType(String str) {
        return convertMessageTypeToDomainType(str);
    }

    public String getMessageTypeFromEvent(GenericSharingMessageEvent genericSharingMessageEvent) {
        if (genericSharingMessageEvent == null || genericSharingMessageEvent.getPayload() == null) {
            return "invalid";
        }
        String messageType = genericSharingMessageEvent.getPayload().getMessageType();
        return (messageType == null || messageType.length() == 0) ? "unknown" : messageType;
    }

    public String getMessageTypeFromSharedMediaMessage(JsonObject jsonObject) {
        try {
            return ((SharingMessagePayload) this.gson.fromJson(jsonObject.get("payload"), (Class<Object>) SharingMessagePayload.class)).getSharingMessage().getMessageType();
        } catch (Exception e) {
            LOG.w("Exception was thrown attempting to extract message type from a SharedMediaMessage.payload", e);
            return "invalid";
        }
    }

    public String getOptionalStringFromJSON(JsonObject jsonObject, String str) {
        if (!objectHasStringWithKeyName(jsonObject, str)) {
            GeneratedOutlineSupport1.outline159("[gson-helper] Optional String Missing: ", str, LOG);
            return null;
        }
        return jsonObject.get(str).getAsString();
    }

    public String getRandomUuid() {
        return UUID.randomUUID().toString();
    }

    public boolean jsonElementIsString(JsonElement jsonElement) {
        return !jsonElement.isJsonNull() && jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString();
    }

    public boolean objectHasStringWithKeyName(JsonObject jsonObject, String str) {
        if (jsonObject == null) {
            return false;
        }
        if (!jsonObject.has(str)) {
            GeneratedOutlineSupport1.outline159("Key Missing: ", str, LOG);
            return false;
        }
        return jsonElementIsString(jsonObject.get(str));
    }

    public SharingUtils(Gson gson) {
        this.gson = gson;
    }
}
