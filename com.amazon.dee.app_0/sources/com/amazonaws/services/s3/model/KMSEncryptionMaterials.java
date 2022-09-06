package com.amazonaws.services.s3.model;

import java.io.Serializable;
import java.security.KeyPair;
import javax.crypto.SecretKey;
@Deprecated
/* loaded from: classes13.dex */
public class KMSEncryptionMaterials extends EncryptionMaterials implements Serializable {
    public static final String CUSTOMER_MASTER_KEY_ID = "kms_cmk_id";

    public KMSEncryptionMaterials(String str) {
        super(null, null);
        if (str != null && str.length() != 0) {
            addDescription(CUSTOMER_MASTER_KEY_ID, str);
            return;
        }
        throw new IllegalArgumentException("The default customer master key id must be specified");
    }

    @Override // com.amazonaws.services.s3.model.EncryptionMaterials
    public String getCustomerMasterKeyId() {
        return getDescription(CUSTOMER_MASTER_KEY_ID);
    }

    @Override // com.amazonaws.services.s3.model.EncryptionMaterials
    public final KeyPair getKeyPair() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazonaws.services.s3.model.EncryptionMaterials
    public final SecretKey getSymmetricKey() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazonaws.services.s3.model.EncryptionMaterials
    public final boolean isKMSEnabled() {
        return true;
    }

    public String toString() {
        return String.valueOf(getMaterialsDescription());
    }
}
