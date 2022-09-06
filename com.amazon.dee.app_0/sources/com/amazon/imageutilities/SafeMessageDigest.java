package com.amazon.imageutilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes12.dex */
class SafeMessageDigest implements Cloneable {
    private final MessageDigest messageDigest;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SafeMessageDigest(String str) throws UnsupportedAlgorithmException {
        try {
            this.messageDigest = MessageDigest.getInstance(str);
            this.messageDigest.clone();
        } catch (CloneNotSupportedException | NoSuchAlgorithmException e) {
            throw new UnsupportedAlgorithmException(e);
        }
    }

    public Object clone() {
        return new SafeMessageDigest(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] digest() {
        return this.messageDigest.digest();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getAlgorithm() {
        return this.messageDigest.getAlgorithm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SafeMessageDigest safeClone() {
        return new SafeMessageDigest(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void update(byte[] bArr) {
        this.messageDigest.update(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void update(byte[] bArr, int i, int i2) {
        this.messageDigest.update(bArr, i, i2);
    }

    private SafeMessageDigest(SafeMessageDigest safeMessageDigest) {
        try {
            this.messageDigest = (MessageDigest) safeMessageDigest.messageDigest.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
