package org.bouncycastle.gpg.keybox;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes4.dex */
public enum BlobType {
    EMPTY_BLOB(0),
    FIRST_BLOB(1),
    OPEN_PGP_BLOB(2),
    X509_BLOB(3);
    
    private final int byteValue;

    BlobType(int i) {
        this.byteValue = i;
    }

    public static BlobType fromByte(int i) {
        BlobType[] values;
        for (BlobType blobType : values()) {
            if (blobType.byteValue == i) {
                return blobType;
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline32(i, GeneratedOutlineSupport1.outline107("Unknown blob type ")));
    }

    public int getByteValue() {
        return this.byteValue;
    }
}
