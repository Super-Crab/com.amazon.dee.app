package com.amazon.alexa.sharing.media.model;

import android.net.Uri;
import com.amazon.alexa.sharing.util.GsonUtil;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.google.gson.JsonObject;
/* loaded from: classes10.dex */
public class AmazonPhotosUploadRequest {
    private final String clientId;
    private final Uri contentUri;
    private final String conversationId;
    private final String mediaPath;
    private final String nodeId;
    private final String photoMessageMediaDataId;

    public AmazonPhotosUploadRequest(String str, String str2, String str3, String str4, String str5) {
        this.mediaPath = str;
        this.contentUri = Uri.parse(str);
        this.conversationId = str2;
        this.clientId = str3;
        this.nodeId = str4;
        this.photoMessageMediaDataId = str5;
    }

    public static AmazonPhotosUploadRequest fromJsonObject(JsonObject jsonObject) {
        return new AmazonPhotosUploadRequest(GsonUtil.getStringFromJson(jsonObject, "mediaPath", null), GsonUtil.getStringFromJson(jsonObject, "conversationId", null), GsonUtil.getStringFromJson(jsonObject, AuthorizationResponseParser.CLIENT_ID_STATE, null), GsonUtil.getStringFromJson(jsonObject, AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, null), GsonUtil.getStringFromJson(jsonObject, "photoMessageMediaDataId", null));
    }

    public String getClientId() {
        return this.clientId;
    }

    public Uri getContentUri() {
        return this.contentUri;
    }

    public String getConversationId() {
        return this.conversationId;
    }

    public String getMediaPath() {
        return this.mediaPath;
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public String getPhotoMessageMediaDataId() {
        return this.photoMessageMediaDataId;
    }
}
