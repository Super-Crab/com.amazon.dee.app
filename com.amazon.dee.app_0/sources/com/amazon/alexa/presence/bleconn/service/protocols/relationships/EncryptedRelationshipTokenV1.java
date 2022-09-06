package com.amazon.alexa.presence.bleconn.service.protocols.relationships;

import com.amazon.alexa.presence.bleconn.helpers.ByteHelper;
import java.nio.ByteBuffer;
import java.util.Objects;
/* loaded from: classes9.dex */
public class EncryptedRelationshipTokenV1 {
    private static final int ENCRYPTED_DATA_LENGTH_BYTES = 16;
    private static final int ENCRYPTED_DATA_OFFSET_BYTES = 1;
    public static final byte RELATIONSHIP_TYPE_HOUSEHOLD = 1;
    private static final int RELATIONSHIP_TYPE_LENGTH_BYTES = 1;
    private static final int RELATIONSHIP_TYPE_OFFSET_BYTES = 0;
    public static final int SIZE_BYTES = 17;
    private final byte[] encryptedRelationshipData;
    private final byte relationshipType;

    public EncryptedRelationshipTokenV1(byte b, byte[] bArr) {
        this.relationshipType = ((Byte) Objects.requireNonNull(Byte.valueOf(b))).byteValue();
        this.encryptedRelationshipData = (byte[]) Objects.requireNonNull(bArr);
        validate();
    }

    public static EncryptedRelationshipTokenV1 tryParse(byte[] bArr) {
        if (bArr.length != 17) {
            return null;
        }
        return new EncryptedRelationshipTokenV1(bArr[0], ByteHelper.slice(bArr, 1, 16));
    }

    public byte[] getBytes() {
        return ByteBuffer.allocate(17).put(this.relationshipType).put(this.encryptedRelationshipData).array();
    }

    public byte[] getEncryptedRelationshipData() {
        return this.encryptedRelationshipData;
    }

    public int getRelationshipType() {
        return this.relationshipType;
    }

    public void validate() {
        if (this.encryptedRelationshipData.length == 16) {
            return;
        }
        throw new IllegalStateException("encryptedData wrong size");
    }

    public static EncryptedRelationshipTokenV1 tryParse(byte b, byte[] bArr) {
        ByteBuffer allocate = ByteBuffer.allocate(17);
        allocate.put(b);
        allocate.put(bArr);
        return tryParse(allocate.array());
    }
}
