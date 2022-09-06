package com.amazon.alexa.sharing.sharingsdk.util;

import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.repo.models.acms.payload.sharing.GenericSharingMessageEvent;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.UUID;
/* loaded from: classes10.dex */
public class SharingSDKUtil {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SharingSDKUtil.class);

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
}
