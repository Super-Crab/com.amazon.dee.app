package com.amazon.imageutilities;

import java.util.Objects;
/* loaded from: classes12.dex */
public class DigestResult {
    private final String algorithm;
    private final String fileDigest;
    private final String visualDigest;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DigestResult(String str, String str2, String str3) {
        if (str != null) {
            if (str2 == null) {
                throw new NullPointerException("File Digest cannot be null.");
            }
            if (str3 != null) {
                this.algorithm = str;
                this.fileDigest = str2;
                this.visualDigest = str3;
                return;
            }
            throw new NullPointerException("Visual Digest cannot be null.");
        }
        throw new NullPointerException("Algorithm cannot be null.");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DigestResult)) {
            return false;
        }
        DigestResult digestResult = (DigestResult) obj;
        return digestResult.algorithm.equals(this.algorithm) && digestResult.fileDigest.equals(this.fileDigest) && digestResult.visualDigest.equals(this.visualDigest);
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public String getFileDigest() {
        return this.fileDigest;
    }

    public String getVisualDigest() {
        return this.visualDigest;
    }

    public int hashCode() {
        return Objects.hash(this.algorithm, this.fileDigest, this.visualDigest);
    }
}
