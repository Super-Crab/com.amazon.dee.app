package com.amazonaws.services.s3.internal.crypto;

import com.amazonaws.AmazonClientException;
@Deprecated
/* loaded from: classes13.dex */
class MultipartUploadCryptoContext extends MultipartUploadContext {
    private final ContentCryptoMaterial cekMaterial;
    private int partNumber;
    private volatile boolean partUploadInProgress;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MultipartUploadCryptoContext(String str, String str2, ContentCryptoMaterial contentCryptoMaterial) {
        super(str, str2);
        this.cekMaterial = contentCryptoMaterial;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void beginPartUpload(int i) {
        if (i >= 1) {
            if (!this.partUploadInProgress) {
                synchronized (this) {
                    if (i - this.partNumber <= 1) {
                        this.partNumber = i;
                        this.partUploadInProgress = true;
                    } else {
                        throw new AmazonClientException("Parts are required to be uploaded in series (partNumber=" + this.partNumber + ", nextPartNumber=" + i + ")");
                    }
                }
                return;
            }
            throw new AmazonClientException("Parts are required to be uploaded in series");
        }
        throw new IllegalArgumentException("part number must be at least 1");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void endPartUpload() {
        this.partUploadInProgress = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CipherLite getCipherLite() {
        return this.cekMaterial.getCipherLite();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ContentCryptoMaterial getContentCryptoMaterial() {
        return this.cekMaterial;
    }
}
