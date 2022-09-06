package com.amazon.communication.gw;

import com.amazon.communication.gw.GatewayHandshakeMessage;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes12.dex */
public class GatewayHandshakeInitiate implements GatewayHandshakeMessage {
    private final List<AccountInformation> mAccountInformation;
    private final String mActiveCustomerId;
    private final String mCorpusAlgorithm;
    private final long mInitiateTimestamp;
    private final String mMessageId;
    private final String mSignatureAlgorithm;

    /* loaded from: classes12.dex */
    public static class AccountInformation {
        public final String directedCustomerId;
        public final byte[] signature;
        public final String token;

        public AccountInformation(String str, String str2, byte[] bArr) {
            this.directedCustomerId = str;
            this.token = str2;
            this.signature = bArr;
        }

        public boolean equals(Object obj) {
            if (obj != null && (obj instanceof AccountInformation)) {
                AccountInformation accountInformation = (AccountInformation) obj;
                String str = this.directedCustomerId;
                if (str != null) {
                    if (!str.equals(accountInformation.directedCustomerId)) {
                        return false;
                    }
                } else if (accountInformation.directedCustomerId != null) {
                    return false;
                }
                String str2 = this.token;
                if (str2 != null) {
                    if (!str2.equals(accountInformation.token)) {
                        return false;
                    }
                } else if (accountInformation.token != null) {
                    return false;
                }
                return Arrays.equals(this.signature, accountInformation.signature);
            }
            return false;
        }

        public int hashCode() {
            String str = this.directedCustomerId;
            int i = 0;
            int hashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
            String str2 = this.token;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return Arrays.hashCode(this.signature) + ((hashCode + i) * 31);
        }

        public String toString() {
            return String.format("AccountInformation directedCustomerId: %s, token: %s", this.directedCustomerId, this.token);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GatewayHandshakeInitiate(String str, long j, String str2, String str3, String str4, List<AccountInformation> list) {
        this.mMessageId = str;
        this.mInitiateTimestamp = j;
        this.mSignatureAlgorithm = str3;
        this.mCorpusAlgorithm = str2;
        this.mActiveCustomerId = str4;
        this.mAccountInformation = list;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof GatewayHandshakeInitiate)) {
            GatewayHandshakeInitiate gatewayHandshakeInitiate = (GatewayHandshakeInitiate) obj;
            String str = this.mMessageId;
            if (str != null) {
                if (!str.equals(gatewayHandshakeInitiate.mMessageId)) {
                    return false;
                }
            } else if (gatewayHandshakeInitiate.mMessageId != null) {
                return false;
            }
            if (this.mInitiateTimestamp != gatewayHandshakeInitiate.mInitiateTimestamp) {
                return false;
            }
            String str2 = this.mCorpusAlgorithm;
            if (str2 != null) {
                if (!str2.equals(gatewayHandshakeInitiate.mCorpusAlgorithm)) {
                    return false;
                }
            } else if (gatewayHandshakeInitiate.mCorpusAlgorithm != null) {
                return false;
            }
            String str3 = this.mSignatureAlgorithm;
            if (str3 != null) {
                if (!str3.equals(gatewayHandshakeInitiate.mSignatureAlgorithm)) {
                    return false;
                }
            } else if (gatewayHandshakeInitiate.mSignatureAlgorithm != null) {
                return false;
            }
            String str4 = this.mActiveCustomerId;
            if (str4 != null) {
                if (!str4.equals(gatewayHandshakeInitiate.mActiveCustomerId)) {
                    return false;
                }
            } else if (gatewayHandshakeInitiate.mActiveCustomerId != null) {
                return false;
            }
            List<AccountInformation> list = this.mAccountInformation;
            return list != null ? list.equals(gatewayHandshakeInitiate.mAccountInformation) : gatewayHandshakeInitiate.mAccountInformation == null;
        }
        return false;
    }

    public List<AccountInformation> getAccountInformation() {
        return this.mAccountInformation;
    }

    public String getActiveCustomerId() {
        return this.mActiveCustomerId;
    }

    public String getCorpusAlgorithm() {
        return this.mCorpusAlgorithm;
    }

    public long getInitiateTimestamp() {
        return this.mInitiateTimestamp;
    }

    @Override // com.amazon.communication.gw.GatewayHandshakeMessage
    public String getMessageId() {
        return this.mMessageId;
    }

    public String getSignatureAlgorithm() {
        return this.mSignatureAlgorithm;
    }

    @Override // com.amazon.communication.gw.GatewayHandshakeMessage
    public GatewayHandshakeMessage.Type getType() {
        return GatewayHandshakeMessage.Type.Initiate;
    }

    public int hashCode() {
        String str = this.mMessageId;
        int i = 0;
        int hashCode = str == null ? 0 : str.hashCode();
        long j = this.mInitiateTimestamp;
        int i2 = (((hashCode + 19) * 19) + ((int) (j ^ (j >>> 32)))) * 19;
        String str2 = this.mCorpusAlgorithm;
        int hashCode2 = (i2 + (str2 == null ? 0 : str2.hashCode())) * 19;
        String str3 = this.mSignatureAlgorithm;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 19;
        String str4 = this.mActiveCustomerId;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 19;
        List<AccountInformation> list = this.mAccountInformation;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return String.format("HandshakeInitiate messageId: %s, initiateTimestamp: %d, corpusAlgorithm: %s, signatureAlgorithm: %s, activeAccount: %s, accountInformation: %s", this.mMessageId, Long.valueOf(this.mInitiateTimestamp), this.mCorpusAlgorithm, this.mSignatureAlgorithm, this.mActiveCustomerId, this.mAccountInformation);
    }
}
