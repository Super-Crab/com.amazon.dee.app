package com.amazonaws.services.s3.internal.crypto;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.internal.SdkFilterInputStream;
import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.s3.internal.S3Direct;
import com.amazonaws.services.s3.model.CryptoConfiguration;
import com.amazonaws.services.s3.model.CryptoMode;
import com.amazonaws.services.s3.model.EncryptionMaterialsProvider;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.UploadPartRequest;
import java.io.File;
@Deprecated
/* loaded from: classes13.dex */
class S3CryptoModuleEO extends S3CryptoModuleBase<MultipartUploadCbcContext> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public S3CryptoModuleEO(AWSKMSClient aWSKMSClient, S3Direct s3Direct, AWSCredentialsProvider aWSCredentialsProvider, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfiguration) {
        super(aWSKMSClient, s3Direct, aWSCredentialsProvider, encryptionMaterialsProvider, cryptoConfiguration);
        if (cryptoConfiguration.getCryptoMode() == CryptoMode.EncryptionOnly) {
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModuleBase
    protected final long ciphertextLength(long j) {
        long blockSizeInBytes = this.contentCryptoScheme.getBlockSizeInBytes();
        return (blockSizeInBytes - (j % blockSizeInBytes)) + j;
    }

    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModuleBase
    final long computeLastPartSize(UploadPartRequest uploadPartRequest) {
        long partSize;
        if (uploadPartRequest.getFile() != null) {
            if (uploadPartRequest.getPartSize() > 0) {
                partSize = uploadPartRequest.getPartSize();
            } else {
                partSize = uploadPartRequest.getFile().length();
            }
        } else if (uploadPartRequest.getInputStream() == null) {
            return -1L;
        } else {
            partSize = uploadPartRequest.getPartSize();
        }
        long blockSizeInBytes = this.contentCryptoScheme.getBlockSizeInBytes();
        return (blockSizeInBytes - (partSize % blockSizeInBytes)) + partSize;
    }

    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public S3Object getObjectSecurely(GetObjectRequest getObjectRequest) {
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModuleBase
    public final CipherLite cipherLiteForNextPart(MultipartUploadCbcContext multipartUploadCbcContext) {
        return multipartUploadCbcContext.getCipherLite().createUsingIV(multipartUploadCbcContext.getNextInitializationVector());
    }

    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModule
    public ObjectMetadata getObjectSecurely(GetObjectRequest getObjectRequest, File file) {
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModuleBase
    /* renamed from: newUploadContext */
    public final MultipartUploadCbcContext mo6713newUploadContext(InitiateMultipartUploadRequest initiateMultipartUploadRequest, ContentCryptoMaterial contentCryptoMaterial) {
        MultipartUploadCbcContext multipartUploadCbcContext = new MultipartUploadCbcContext(initiateMultipartUploadRequest.getBucketName(), initiateMultipartUploadRequest.getKey(), contentCryptoMaterial);
        multipartUploadCbcContext.setNextInitializationVector(contentCryptoMaterial.getCipherLite().getIV());
        return multipartUploadCbcContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModuleBase
    public final void updateUploadContext(MultipartUploadCbcContext multipartUploadCbcContext, SdkFilterInputStream sdkFilterInputStream) {
        multipartUploadCbcContext.setNextInitializationVector(((ByteRangeCapturingInputStream) sdkFilterInputStream).getBlock());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazonaws.services.s3.internal.crypto.S3CryptoModuleBase
    /* renamed from: wrapForMultipart  reason: collision with other method in class */
    public final ByteRangeCapturingInputStream mo6714wrapForMultipart(CipherLiteInputStream cipherLiteInputStream, long j) {
        return new ByteRangeCapturingInputStream(cipherLiteInputStream, j - this.contentCryptoScheme.getBlockSizeInBytes(), j);
    }

    S3CryptoModuleEO(S3Direct s3Direct, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfiguration) {
        this(null, s3Direct, new DefaultAWSCredentialsProviderChain(), encryptionMaterialsProvider, cryptoConfiguration);
    }

    S3CryptoModuleEO(AWSKMSClient aWSKMSClient, S3Direct s3Direct, EncryptionMaterialsProvider encryptionMaterialsProvider, CryptoConfiguration cryptoConfiguration) {
        this(aWSKMSClient, s3Direct, new DefaultAWSCredentialsProviderChain(), encryptionMaterialsProvider, cryptoConfiguration);
    }
}
