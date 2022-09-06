package com.amazonaws.services.s3.internal.crypto;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.s3.internal.S3Direct;
import com.amazonaws.services.s3.model.CryptoConfiguration;
import com.amazonaws.services.s3.model.CryptoMode;
import com.amazonaws.services.s3.model.EncryptionMaterialsProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
@Deprecated
/* loaded from: classes13.dex */
class S3CryptoModuleAEStrict extends S3CryptoModuleAE {
    /* JADX INFO: Access modifiers changed from: package-private */
    public S3CryptoModuleAEStrict(AWSKMSClient aWSKMSClient, S3Direct s3Direct, AWSCredentialsProvider aWSCredentialsProvider, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfiguration) {
        super(aWSKMSClient, s3Direct, aWSCredentialsProvider, encryptionMaterialsProvider, cryptoConfiguration);
        if (cryptoConfiguration.getCryptoMode() == CryptoMode.StrictAuthenticatedEncryption) {
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModuleAE
    protected final boolean isStrict() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModuleBase
    public void securityCheck(ContentCryptoMaterial contentCryptoMaterial, S3ObjectWrapper s3ObjectWrapper) {
        if (ContentCryptoScheme.AES_GCM.equals(contentCryptoMaterial.getContentCryptoScheme())) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("S3 object [bucket: ");
        outline107.append(s3ObjectWrapper.getBucketName());
        outline107.append(", key: ");
        throw new SecurityException(GeneratedOutlineSupport1.outline91(outline107, s3ObjectWrapper.getKey(), "] not encrypted using authenticated encryption"));
    }
}
