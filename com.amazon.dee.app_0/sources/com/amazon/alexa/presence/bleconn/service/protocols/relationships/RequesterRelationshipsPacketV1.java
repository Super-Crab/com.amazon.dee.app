package com.amazon.alexa.presence.bleconn.service.protocols.relationships;

import com.amazon.alexa.presence.bleconn.helpers.ByteHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes9.dex */
public class RequesterRelationshipsPacketV1 {
    private static final int HEADER_LENGTH_BYTES = 2;
    public static final byte HEADER_MASK_RELATIONSHIP_COUNT = 15;
    public static final byte HEADER_MASK_REQUESTS_VALIDATION = 1;
    public static final byte HEADER_MASK_VERSION = -16;
    private static final int HEADER_OFFSET_BYTES = 0;
    public static final byte HEADER_VALUE_VERSION_1 = 0;
    private static Logger LOG = LoggerFactory.getLogger(RequesterRelationshipsPacketV1.class);
    private static final int RELATIONSHIPS_OFFSET_BYTES = 2;
    private List<EncryptedRelationshipTokenV1> relationshipTokens;
    private boolean requiresRelationshipValidation;

    public RequesterRelationshipsPacketV1(boolean z, List<EncryptedRelationshipTokenV1> list) {
        this.requiresRelationshipValidation = ((Boolean) Objects.requireNonNull(Boolean.valueOf(z))).booleanValue();
        this.relationshipTokens = (List) Objects.requireNonNull(list);
        validate();
    }

    private static int probeProtocolVersion(byte[] bArr) {
        return (bArr[0] & HEADER_MASK_VERSION) >>> 4;
    }

    public static RequesterRelationshipsPacketV1 tryParse(byte[] bArr) {
        if (bArr.length < 2) {
            Logger logger = LOG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to parse data, header incomplete.  Data size: ");
            outline107.append(bArr.length);
            logger.warn(outline107.toString());
            return null;
        }
        int probeProtocolVersion = probeProtocolVersion(bArr);
        if (probeProtocolVersion != 0) {
            Logger logger2 = LOG;
            logger2.warn("Attempted to parse data as V1 relationship packet, but unexpected header version detected : " + probeProtocolVersion);
            return null;
        }
        boolean z = (bArr[0] & 1) == 1;
        int i = bArr[1] & 15;
        int i2 = i * 17;
        if (bArr.length != i2 + 2) {
            LOG.warn("Payload incomplete.  Expected %d bytes, but found %s.", Integer.valueOf(i2), Integer.valueOf(bArr.length));
            return null;
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i3 = 0; i3 < i; i3++) {
            arrayList.add(EncryptedRelationshipTokenV1.tryParse(ByteHelper.slice(bArr, (i3 * 17) + 2, 17)));
        }
        return new RequesterRelationshipsPacketV1(z, arrayList);
    }

    private void validate() {
        if (this.relationshipTokens.size() <= 15) {
            return;
        }
        throw new IllegalStateException("Unsupported number of relationships given (to many)");
    }

    public byte[] getBytes() {
        ByteBuffer put = ByteBuffer.allocate((this.relationshipTokens.size() * 17) + 2).put((byte) ((this.requiresRelationshipValidation ? 1 : 0) | 0)).put((byte) (this.relationshipTokens.size() & 15));
        for (EncryptedRelationshipTokenV1 encryptedRelationshipTokenV1 : this.relationshipTokens) {
            put.put(encryptedRelationshipTokenV1.getBytes());
        }
        return put.array();
    }

    public List<EncryptedRelationshipTokenV1> relationships() {
        return this.relationshipTokens;
    }

    public boolean requiresValidation() {
        return this.requiresRelationshipValidation;
    }
}
