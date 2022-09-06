package com.amazon.alexa.sharing.media.model;

import com.amazon.alexa.sharing.util.GsonUtil;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.google.gson.JsonObject;
/* loaded from: classes10.dex */
public class AmazonPhotosDownloadRequest {
    final String clientId;
    final String conversationId;
    final String dimension;
    final String globalMessageId;
    final boolean isThumbnail;
    final String nodeId;
    final String ownerId;
    final String source;
    final int viewBox;

    public AmazonPhotosDownloadRequest(String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z, int i) {
        this.source = str;
        this.globalMessageId = str2;
        this.conversationId = str3;
        this.clientId = str4;
        this.dimension = str5;
        this.nodeId = str6;
        this.ownerId = str7;
        this.isThumbnail = z;
        this.viewBox = i;
    }

    public static AmazonPhotosDownloadRequest fromJsonObject(JsonObject jsonObject) {
        String stringFromJson = GsonUtil.getStringFromJson(jsonObject, "source", null);
        String stringFromJson2 = GsonUtil.getStringFromJson(jsonObject, "globalMessageId", null);
        String stringFromJson3 = GsonUtil.getStringFromJson(jsonObject, "conversationId", null);
        String stringFromJson4 = GsonUtil.getStringFromJson(jsonObject, AuthorizationResponseParser.CLIENT_ID_STATE, null);
        String stringFromJson5 = GsonUtil.getStringFromJson(jsonObject, "dimension", null);
        String stringFromJson6 = GsonUtil.getStringFromJson(jsonObject, AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, null);
        String stringFromJson7 = GsonUtil.getStringFromJson(jsonObject, MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY, null);
        boolean booleanFromJson = GsonUtil.getBooleanFromJson(jsonObject, "isThumbnail", false);
        double d = -999.0d;
        if (booleanFromJson) {
            d = GsonUtil.getDoubleFromJson(jsonObject, "viewbox", -999.0d);
        }
        return new AmazonPhotosDownloadRequest(stringFromJson, stringFromJson2, stringFromJson3, stringFromJson4, stringFromJson5, stringFromJson6, stringFromJson7, booleanFromJson, (int) d);
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getConversationId() {
        return this.conversationId;
    }

    public String getDimension() {
        return this.dimension;
    }

    public String getGlobalMessageId() {
        return this.globalMessageId;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public String getSource() {
        return this.source;
    }

    public int getViewBox() {
        return this.viewBox;
    }

    public boolean isThumbnail() {
        return this.isThumbnail;
    }
}
