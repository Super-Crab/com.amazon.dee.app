package com.amazon.communication.gw;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class GatewayAccountChangeMessage {
    public String mActiveCustomerId;
    public MessageType messageType = MessageType.MESSAGE_TYPE_ACTIVE_ACCOUNT_CHANGE;

    /* loaded from: classes12.dex */
    public enum MessageType {
        MESSAGE_TYPE_ACTIVE_ACCOUNT_CHANGE
    }

    public GatewayAccountChangeMessage(String str) {
        this.mActiveCustomerId = str;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GatewayAccountChangeMessage) || obj == null) {
            return false;
        }
        GatewayAccountChangeMessage gatewayAccountChangeMessage = (GatewayAccountChangeMessage) obj;
        return gatewayAccountChangeMessage.messageType == this.messageType && gatewayAccountChangeMessage.mActiveCustomerId.equals(this.mActiveCustomerId);
    }

    public int hashCode() {
        MessageType messageType = this.messageType;
        int i = 0;
        int hashCode = (527 + (messageType != null ? messageType.hashCode() : 0)) * 31;
        String str = this.mActiveCustomerId;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.messageType.name());
        sb.append(";");
        return GeneratedOutlineSupport1.outline91(sb, this.mActiveCustomerId, "");
    }
}
