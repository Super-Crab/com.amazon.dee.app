package com.amazon.deecomms.nativemodules;

import android.net.Uri;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes12.dex */
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

    public static AmazonPhotosUploadRequest extractFromReadableMap(ReadableMap readableMap) {
        String str = null;
        String string = !readableMap.hasKey("mediaPath") ? null : readableMap.getString("mediaPath");
        String string2 = !readableMap.hasKey("conversationId") ? null : readableMap.getString("conversationId");
        String string3 = !readableMap.hasKey(AuthorizationResponseParser.CLIENT_ID_STATE) ? null : readableMap.getString(AuthorizationResponseParser.CLIENT_ID_STATE);
        String string4 = !readableMap.hasKey(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID) ? null : readableMap.getString(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID);
        if (readableMap.hasKey("photoMessageMediaDataId")) {
            str = readableMap.getString("photoMessageMediaDataId");
        }
        return new AmazonPhotosUploadRequest(string, string2, string3, string4, str);
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
