package com.amazon.communication.devicetodevice;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
/* loaded from: classes12.dex */
public class D2DMessage {
    public int channel;
    public EndpointIdentity destination;
    public String destinationApp;
    public Message message;
    public String messageType;
    public EndpointIdentity origin;
    public String originApp;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof D2DMessage)) {
            return false;
        }
        D2DMessage d2DMessage = (D2DMessage) obj;
        Message message = this.message;
        if (message == null) {
            if (d2DMessage.message != null) {
                return false;
            }
        } else if (!message.equals(d2DMessage.message)) {
            return false;
        }
        String str = this.messageType;
        if (str == null) {
            if (d2DMessage.messageType != null) {
                return false;
            }
        } else if (!str.equals(d2DMessage.messageType)) {
            return false;
        }
        if (this.channel != d2DMessage.channel) {
            return false;
        }
        EndpointIdentity endpointIdentity = this.origin;
        if (endpointIdentity == null) {
            if (d2DMessage.origin != null) {
                return false;
            }
        } else if (!endpointIdentity.equals(d2DMessage.origin)) {
            return false;
        }
        EndpointIdentity endpointIdentity2 = this.destination;
        if (endpointIdentity2 == null) {
            if (d2DMessage.destination != null) {
                return false;
            }
        } else if (!endpointIdentity2.equals(d2DMessage.destination)) {
            return false;
        }
        String str2 = this.originApp;
        if (str2 == null) {
            if (d2DMessage.originApp != null) {
                return false;
            }
        } else if (!str2.equals(d2DMessage.originApp)) {
            return false;
        }
        String str3 = this.destinationApp;
        if (str3 == null) {
            if (d2DMessage.destinationApp != null) {
                return false;
            }
        } else if (!str3.equals(d2DMessage.destinationApp)) {
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
        int hashCode4 = (hashCode3 + (endpointIdentity2 == null ? 0 : endpointIdentity2.hashCode())) * 31;
        String str2 = this.destinationApp;
        int hashCode5 = (hashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.originApp;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode5 + i;
    }
}
