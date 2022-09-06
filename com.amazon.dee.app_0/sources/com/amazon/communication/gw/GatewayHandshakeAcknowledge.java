package com.amazon.communication.gw;

import com.amazon.communication.gw.GatewayHandshakeMessage;
import java.util.List;
/* loaded from: classes12.dex */
public class GatewayHandshakeAcknowledge implements GatewayHandshakeMessage {
    private final List<AccountResult> mAccountResults;
    private final long mAcknowledgeTimestamp;
    private final long mInitiateTimestamp;
    private final String mMessageId;
    private final int mStatus;

    /* loaded from: classes12.dex */
    public static class AccountResult {
        public String directedCustomerId;
        public int status;

        public AccountResult(String str, int i) {
            this.directedCustomerId = str;
            this.status = i;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof AccountResult)) {
                AccountResult accountResult = (AccountResult) obj;
                String str = this.directedCustomerId;
                if (str != null) {
                    if (!str.equals(accountResult.directedCustomerId)) {
                        return false;
                    }
                } else if (accountResult.directedCustomerId != null) {
                    return false;
                }
                return this.status == accountResult.status;
            }
            return false;
        }

        public int hashCode() {
            String str = this.directedCustomerId;
            return (((str == null ? 0 : str.hashCode()) + 19) * 19) + this.status;
        }

        public String toString() {
            return String.format("Accountresult directedCustomerId: %s, status: %d", this.directedCustomerId, Integer.valueOf(this.status));
        }
    }

    /* loaded from: classes12.dex */
    public enum AccountStatus {
        OK(100),
        TRANSIENT_FAILURE(200),
        NON_TRANSIENT_FAILURE(300),
        AUTHENTICATION_FAILURE(303);
        
        private final int mStatus;

        AccountStatus(int i) {
            this.mStatus = i;
        }

        public int getStatus() {
            return this.mStatus;
        }

        @Override // java.lang.Enum
        public String toString() {
            return Integer.toString(this.mStatus);
        }
    }

    /* loaded from: classes12.dex */
    public enum OverallStatus {
        OK(100),
        OK_TRANSIENT_FAILURE(200),
        OK_NON_TRANSIENT_FAILURE(300),
        TRANSIENT_FAILURE(400),
        NON_TRANSIENT_FAILURE(500);
        
        private final int mStatus;

        OverallStatus(int i) {
            this.mStatus = i;
        }

        public int getStatus() {
            return this.mStatus;
        }

        @Override // java.lang.Enum
        public String toString() {
            return Integer.toString(this.mStatus);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GatewayHandshakeAcknowledge(int i, String str, long j, long j2, List<AccountResult> list) {
        this.mStatus = i;
        this.mMessageId = str;
        this.mInitiateTimestamp = j;
        this.mAcknowledgeTimestamp = j2;
        this.mAccountResults = list;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof GatewayHandshakeAcknowledge)) {
            GatewayHandshakeAcknowledge gatewayHandshakeAcknowledge = (GatewayHandshakeAcknowledge) obj;
            if (this.mStatus != gatewayHandshakeAcknowledge.mStatus) {
                return false;
            }
            String str = this.mMessageId;
            if (str != null) {
                if (!str.equals(gatewayHandshakeAcknowledge.mMessageId)) {
                    return false;
                }
            } else if (gatewayHandshakeAcknowledge.mMessageId != null) {
                return false;
            }
            if (this.mInitiateTimestamp != gatewayHandshakeAcknowledge.mInitiateTimestamp || this.mAcknowledgeTimestamp != gatewayHandshakeAcknowledge.mAcknowledgeTimestamp) {
                return false;
            }
            List<AccountResult> list = this.mAccountResults;
            return list != null ? list.equals(gatewayHandshakeAcknowledge.mAccountResults) : gatewayHandshakeAcknowledge.mAccountResults == null;
        }
        return false;
    }

    public List<AccountResult> getAccountResults() {
        return this.mAccountResults;
    }

    public long getAcknowledgeTimestamp() {
        return this.mAcknowledgeTimestamp;
    }

    public long getInitiateTimestamp() {
        return this.mInitiateTimestamp;
    }

    @Override // com.amazon.communication.gw.GatewayHandshakeMessage
    public String getMessageId() {
        return this.mMessageId;
    }

    public int getStatus() {
        return this.mStatus;
    }

    @Override // com.amazon.communication.gw.GatewayHandshakeMessage
    public GatewayHandshakeMessage.Type getType() {
        return GatewayHandshakeMessage.Type.Acknowledge;
    }

    public int hashCode() {
        int i = (this.mStatus + 37) * 37;
        String str = this.mMessageId;
        int i2 = 0;
        int hashCode = str == null ? 0 : str.hashCode();
        long j = this.mInitiateTimestamp;
        long j2 = this.mAcknowledgeTimestamp;
        int i3 = (((((i + hashCode) * 37) + ((int) (j ^ (j >>> 32)))) * 37) + ((int) (j2 ^ (j2 >>> 32)))) * 37;
        List<AccountResult> list = this.mAccountResults;
        if (list != null) {
            i2 = list.hashCode();
        }
        return i3 + i2;
    }

    public String toString() {
        return String.format("HandshakeAcknowledge messageId: %s, status: %s, initiateTimestamp: %d acknowledgeTimestamp: %d, accountResults: %s", this.mMessageId, Integer.valueOf(this.mStatus), Long.valueOf(this.mInitiateTimestamp), Long.valueOf(this.mAcknowledgeTimestamp), this.mAccountResults);
    }
}
