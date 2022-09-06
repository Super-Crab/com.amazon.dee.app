package com.amazon.communication.gw;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
/* loaded from: classes12.dex */
public class GatewayMessage {
    public int channel;
    public EndpointIdentity destination;
    public Message message;
    public String messageType;
    public EndpointIdentity origin;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof GatewayMessage)) {
            return false;
        }
        GatewayMessage gatewayMessage = (GatewayMessage) obj;
        Message message = this.message;
        if (message == null) {
            if (gatewayMessage.message != null) {
                return false;
            }
        } else if (!message.equals(gatewayMessage.message)) {
            return false;
        }
        String str = this.messageType;
        if (str == null) {
            if (gatewayMessage.messageType != null) {
                return false;
            }
        } else if (!str.equals(gatewayMessage.messageType)) {
            return false;
        }
        if (this.channel != gatewayMessage.channel) {
            return false;
        }
        EndpointIdentity endpointIdentity = this.origin;
        if (endpointIdentity == null) {
            if (gatewayMessage.origin != null) {
                return false;
            }
        } else if (!endpointIdentity.equals(gatewayMessage.origin)) {
            return false;
        }
        EndpointIdentity endpointIdentity2 = this.destination;
        if (endpointIdentity2 == null) {
            if (gatewayMessage.destination != null) {
                return false;
            }
        } else if (!endpointIdentity2.equals(gatewayMessage.destination)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Message message = this.message;
        int i = 0;
        int hashCode = ((message == null ? 0 : message.hashCode()) + 31) * 31;
        String str = this.messageType;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.channel) * 31;
        EndpointIdentity endpointIdentity = this.origin;
        int hashCode3 = (hashCode2 + (endpointIdentity == null ? 0 : endpointIdentity.hashCode())) * 31;
        EndpointIdentity endpointIdentity2 = this.destination;
        if (endpointIdentity2 != null) {
            i = endpointIdentity2.hashCode();
        }
        return hashCode3 + i;
    }
}
