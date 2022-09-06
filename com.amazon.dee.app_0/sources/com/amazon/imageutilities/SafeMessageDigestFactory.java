package com.amazon.imageutilities;
/* loaded from: classes12.dex */
class SafeMessageDigestFactory {
    private final SafeMessageDigest cloneMe;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SafeMessageDigestFactory(String str) throws UnsupportedAlgorithmException {
        this.cloneMe = new SafeMessageDigest(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getAlgorithm() {
        return this.cloneMe.getAlgorithm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SafeMessageDigest newInstance() {
        return this.cloneMe.safeClone();
    }
}
