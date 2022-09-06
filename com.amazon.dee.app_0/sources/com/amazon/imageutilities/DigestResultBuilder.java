package com.amazon.imageutilities;
/* loaded from: classes12.dex */
class DigestResultBuilder {
    private final String algorithm;
    private final SafeMessageDigest fileDigest;
    private final SafeMessageDigest visualDigest;
    private final StringDigestUtility stringDigestUtility = new StringDigestUtility();
    private SafeMessageDigest eoiDigest = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DigestResultBuilder(SafeMessageDigestFactory safeMessageDigestFactory) {
        this.algorithm = safeMessageDigestFactory.getAlgorithm();
        this.fileDigest = safeMessageDigestFactory.newInstance();
        this.visualDigest = safeMessageDigestFactory.newInstance();
    }

    private String getEncodedFileDigest() {
        return this.stringDigestUtility.getHexEncodedDigest(this.fileDigest.digest());
    }

    private String getEncodedVisualDigest() {
        StringDigestUtility stringDigestUtility = this.stringDigestUtility;
        SafeMessageDigest safeMessageDigest = this.eoiDigest;
        if (safeMessageDigest == null) {
            safeMessageDigest = this.visualDigest;
        }
        return stringDigestUtility.getHexEncodedDigest(safeMessageDigest.digest());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DigestResult build() {
        return new DigestResult(this.algorithm, getEncodedFileDigest(), getEncodedVisualDigest());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DigestResultBuilder update(byte[] bArr, int i) {
        this.fileDigest.update(bArr, 0, i);
        this.visualDigest.update(bArr, 0, i);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DigestResultBuilder updateFileOnly(byte[] bArr) {
        this.fileDigest.update(bArr);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DigestResultBuilder updateWithEOI(byte[] bArr, int i, int i2) {
        this.eoiDigest = this.visualDigest.safeClone();
        this.eoiDigest.update(bArr, 0, i2);
        update(bArr, i);
        return this;
    }
}
