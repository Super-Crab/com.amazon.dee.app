package com.amazon.communication.gw;

import amazon.communication.authentication.SigningException;
import com.amazon.communication.gw.GatewayHandshakeAcknowledge;
import com.amazon.communication.gw.GatewayHandshakeInitiate;
import com.amazon.communication.gw.SignatureProvider;
import com.amazon.communication.rlm.ReliableMessageProtocol;
import com.amazon.communication.time.GlobalTimeSource;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
/* loaded from: classes12.dex */
public interface GatewayHandshakeMessage {

    /* loaded from: classes12.dex */
    public static class AcknowledgeMessageBuilder {
        private List<GatewayHandshakeAcknowledge.AccountResult> mAccountResults;
        private long mInitiateTimestamp;
        private String mMessageId;
        private int mStatus;
        private boolean mBuilt = false;
        private long mAcknowledgeTimestamp = GlobalTimeSource.INSTANCE.currentTimeMillis();

        public AcknowledgeMessageBuilder addAccountResult(String str, int i) {
            if (!this.mBuilt) {
                if (this.mAccountResults == null) {
                    this.mAccountResults = new LinkedList();
                }
                this.mAccountResults.add(new GatewayHandshakeAcknowledge.AccountResult(str, i));
                return this;
            }
            throw new IllegalStateException("Message already built");
        }

        public GatewayHandshakeAcknowledge build() {
            String str = this.mMessageId;
            if (str != null) {
                this.mBuilt = true;
                int i = this.mStatus;
                long j = this.mInitiateTimestamp;
                long j2 = this.mAcknowledgeTimestamp;
                List<GatewayHandshakeAcknowledge.AccountResult> list = this.mAccountResults;
                if (list == null) {
                    list = Collections.emptyList();
                }
                return new GatewayHandshakeAcknowledge(i, str, j, j2, list);
            }
            throw new IllegalArgumentException("messageId must not be null");
        }

        public AcknowledgeMessageBuilder setAcknowledgeTimestamp(long j) {
            if (!this.mBuilt) {
                this.mAcknowledgeTimestamp = j;
                return this;
            }
            throw new IllegalStateException("Message already built");
        }

        public AcknowledgeMessageBuilder setInitiateTimestamp(long j) {
            if (!this.mBuilt) {
                this.mInitiateTimestamp = j;
                return this;
            }
            throw new IllegalStateException("Message already built");
        }

        public AcknowledgeMessageBuilder setMessageId(String str) {
            if (!this.mBuilt) {
                this.mMessageId = str;
                return this;
            }
            throw new IllegalStateException("Message already built");
        }

        public AcknowledgeMessageBuilder setStatus(int i) {
            if (!this.mBuilt) {
                this.mStatus = i;
                return this;
            }
            throw new IllegalStateException("Message already built");
        }
    }

    /* loaded from: classes12.dex */
    public static class InitiateMessageBuilder {
        private static final String ALREADY_BUILT = "Message already built";
        private List<GatewayHandshakeInitiate.AccountInformation> mAccountInfo;
        private String mActiveCustomerId;
        private boolean mBuilt;
        private byte[] mBytesToSign;
        private long mInitiateTimestamp;
        private String mMessageId;
        private final SignatureProvider mSignatureProvider;

        public InitiateMessageBuilder(SignatureProvider signatureProvider) {
            this.mBuilt = false;
            if (signatureProvider != null) {
                this.mBuilt = false;
                this.mSignatureProvider = signatureProvider;
                this.mInitiateTimestamp = GlobalTimeSource.INSTANCE.currentTimeMillis();
                this.mMessageId = UUID.randomUUID().toString();
                return;
            }
            throw new IllegalArgumentException("signatureProvider must not be null");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void assertState(boolean z, String str) throws IllegalStateException {
            if (z) {
                return;
            }
            throw new IllegalStateException(str);
        }

        public InitiateMessageBuilder addAccount(String str) throws SigningException {
            assertState(!this.mBuilt, ALREADY_BUILT);
            if (str != null) {
                if (this.mBytesToSign == null) {
                    this.mBytesToSign = CorpusBuilder.build(this.mMessageId, this.mInitiateTimestamp);
                }
                SignatureProvider.SigningResult sign = this.mSignatureProvider.sign(this.mBytesToSign, str);
                if (this.mAccountInfo == null) {
                    this.mAccountInfo = new LinkedList();
                }
                this.mAccountInfo.add(new GatewayHandshakeInitiate.AccountInformation(str, sign.token, sign.signature));
                return this;
            }
            throw new IllegalArgumentException("directedCustomerId must not be null");
        }

        public InitiateMessageBuilder addActiveAccount(String str) throws SigningException {
            assertState(!this.mBuilt, ALREADY_BUILT);
            if (str != null) {
                this.mActiveCustomerId = str;
            }
            return this;
        }

        public GatewayHandshakeInitiate build() {
            this.mBuilt = true;
            String str = this.mMessageId;
            long j = this.mInitiateTimestamp;
            String algorithm = this.mSignatureProvider.getAlgorithm();
            String str2 = this.mActiveCustomerId;
            List<GatewayHandshakeInitiate.AccountInformation> list = this.mAccountInfo;
            if (list == null) {
                list = Collections.emptyList();
            }
            return new GatewayHandshakeInitiate(str, j, "1.0", algorithm, str2, list);
        }

        public InitiateMessageBuilder setInitiateTimestamp(long j) {
            boolean z = true;
            assertState(!this.mBuilt, ALREADY_BUILT);
            if (this.mBytesToSign != null) {
                z = false;
            }
            assertState(z, "Cannot set timestamp after accounts have been added");
            this.mInitiateTimestamp = j;
            return this;
        }

        public InitiateMessageBuilder setMessageId(String str) {
            boolean z = true;
            assertState(!this.mBuilt, ALREADY_BUILT);
            if (this.mBytesToSign != null) {
                z = false;
            }
            assertState(z, "Cannot set messageId after accounts have been added");
            this.mMessageId = str;
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public static class ProxiedInitiateMessageBuilder {
        private List<GatewayHandshakeInitiate.AccountInformation> mAccountInfo;
        private String mActiveCustomerId;
        private boolean mBuilt = false;
        private String mCorpusAlgorithm = "1.0";
        private long mInitiateTimestamp;
        private String mMessageId;
        private String mSignatureAlgorithm;

        public ProxiedInitiateMessageBuilder addAccountInformation(GatewayHandshakeInitiate.AccountInformation accountInformation) {
            InitiateMessageBuilder.assertState(!this.mBuilt, "Message already built");
            if (this.mAccountInfo == null) {
                this.mAccountInfo = new LinkedList();
            }
            this.mAccountInfo.add(accountInformation);
            return this;
        }

        public GatewayHandshakeInitiate build() {
            this.mBuilt = true;
            String str = this.mMessageId;
            long j = this.mInitiateTimestamp;
            String str2 = this.mCorpusAlgorithm;
            String str3 = this.mSignatureAlgorithm;
            String str4 = this.mActiveCustomerId;
            List<GatewayHandshakeInitiate.AccountInformation> list = this.mAccountInfo;
            if (list == null) {
                list = Collections.emptyList();
            }
            return new GatewayHandshakeInitiate(str, j, str2, str3, str4, list);
        }

        public ProxiedInitiateMessageBuilder setActiveAccount(String str) {
            InitiateMessageBuilder.assertState(!this.mBuilt, "Message already built");
            this.mActiveCustomerId = str;
            return this;
        }

        public ProxiedInitiateMessageBuilder setCorpusAlgorithm(String str) {
            InitiateMessageBuilder.assertState(!this.mBuilt, "Message already built");
            this.mCorpusAlgorithm = str;
            return this;
        }

        public ProxiedInitiateMessageBuilder setInitiateTimestamp(long j) {
            InitiateMessageBuilder.assertState(!this.mBuilt, "Message already built");
            this.mInitiateTimestamp = j;
            return this;
        }

        public ProxiedInitiateMessageBuilder setMessageId(String str) {
            InitiateMessageBuilder.assertState(!this.mBuilt, "Message already built");
            this.mMessageId = str;
            return this;
        }

        public ProxiedInitiateMessageBuilder setSignatureAlgorithm(String str) {
            InitiateMessageBuilder.assertState(!this.mBuilt, "Message already built");
            this.mSignatureAlgorithm = str;
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public enum Type {
        Initiate("INI"),
        Acknowledge(ReliableMessageProtocol.ACK_MESSAGE_TYPE);
        
        public static final int SIZE = 3;
        private final String mToken;

        Type(String str) {
            this.mToken = str;
        }

        public static Type fromToken(String str) {
            Type[] values;
            for (Type type : values()) {
                if (type.mToken.equals(str)) {
                    return type;
                }
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("No enumeration found for token ", str));
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mToken;
        }
    }

    String getMessageId();

    Type getType();
}
