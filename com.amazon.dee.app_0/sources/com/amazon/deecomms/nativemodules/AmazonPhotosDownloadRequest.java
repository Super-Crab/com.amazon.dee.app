package com.amazon.deecomms.nativemodules;

import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes12.dex */
public class AmazonPhotosDownloadRequest {
    final String clientId;
    final String conversationId;
    final String dimension;
    final boolean isThumbnail;
    final String nodeId;
    final String ownerId;
    final int viewBox;

    public AmazonPhotosDownloadRequest(String str, String str2, String str3, String str4, String str5, boolean z, int i) {
        this.conversationId = str;
        this.clientId = str2;
        this.dimension = str3;
        this.nodeId = str4;
        this.ownerId = str5;
        this.isThumbnail = z;
        this.viewBox = i;
    }

    public static AmazonPhotosDownloadRequest extractFromReadableMap(ReadableMap readableMap) {
        String str = null;
        String string = !readableMap.hasKey("conversationId") ? null : readableMap.getString("conversationId");
        String string2 = !readableMap.hasKey(AuthorizationResponseParser.CLIENT_ID_STATE) ? null : readableMap.getString(AuthorizationResponseParser.CLIENT_ID_STATE);
        String string3 = !readableMap.hasKey("dimension") ? null : readableMap.getString("dimension");
        String string4 = !readableMap.hasKey(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID) ? null : readableMap.getString(AlexaDeviceBackgroundImageBridge.KEY_NODE_ID);
        if (readableMap.hasKey(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)) {
            str = readableMap.getString(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY);
        }
        String str2 = str;
        boolean z = readableMap.hasKey("isThumbnail") && readableMap.getBoolean("isThumbnail");
        return new AmazonPhotosDownloadRequest(string, string2, string3, string4, str2, z, (int) ((!z || !readableMap.hasKey("viewbox")) ? -999.0d : readableMap.getDouble("viewbox")));
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

    public String getNodeId() {
        return this.nodeId;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public int getViewBox() {
        return this.viewBox;
    }

    public boolean isThumbnail() {
        return this.isThumbnail;
    }
}
