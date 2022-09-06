package com.amazon.communication.gw;

import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.EndpointIdentityFactory;
/* loaded from: classes12.dex */
public class GatewayControlMessage {
    public String endpointIdentity;
    public boolean isAvailable;
    public MessageType messageType;

    /* loaded from: classes12.dex */
    public enum MessageType {
        MESSAGE_TYPE_DEVICE_AVAILABLE_REQUEST,
        MESSAGE_TYPE_DEVICE_AVAILABLE_RESPONSE,
        MESSAGE_TYPE_DEVICE_AVAILABLE_NOTIFICATION
    }

    public GatewayControlMessage() {
    }

    private static String formatString(MessageType messageType, String str, boolean z) {
        if (messageType == MessageType.MESSAGE_TYPE_DEVICE_AVAILABLE_REQUEST) {
            return messageType.name() + ";" + str;
        }
        return messageType.name() + ";" + str + ";isAvailable:" + z;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GatewayControlMessage) || obj == null) {
            return false;
        }
        GatewayControlMessage gatewayControlMessage = (GatewayControlMessage) obj;
        return gatewayControlMessage.messageType == this.messageType && gatewayControlMessage.endpointIdentity.equals(this.endpointIdentity) && gatewayControlMessage.isAvailable == this.isAvailable;
    }

    public int hashCode() {
        MessageType messageType = this.messageType;
        int i = 0;
        int hashCode = (527 + (messageType != null ? messageType.hashCode() : 0)) * 31;
        String str = this.endpointIdentity;
        if (str != null) {
            i = str.hashCode();
        }
        return ((hashCode + i) * 31) + (this.isAvailable ? 1 : 0);
    }

    public String toLogSafeString() {
        return formatString(this.messageType, EndpointIdentityFactory.createFromUrn(this.endpointIdentity).toLogSafeString(), this.isAvailable);
    }

    public String toString() {
        return formatString(this.messageType, this.endpointIdentity, this.isAvailable);
    }

    public GatewayControlMessage(MessageType messageType, EndpointIdentity endpointIdentity, boolean z) {
        this.messageType = messageType;
        this.endpointIdentity = endpointIdentity.toString();
        this.isAvailable = z;
    }

    public GatewayControlMessage(MessageType messageType, EndpointIdentity endpointIdentity) {
        if (messageType == MessageType.MESSAGE_TYPE_DEVICE_AVAILABLE_REQUEST) {
            this.messageType = messageType;
            this.endpointIdentity = endpointIdentity.toString();
            this.isAvailable = false;
            return;
        }
        throw new IllegalArgumentException("This constructor can only be used with REQUEST type");
    }
}
