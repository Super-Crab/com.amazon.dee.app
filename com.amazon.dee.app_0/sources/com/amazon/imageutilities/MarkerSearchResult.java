package com.amazon.imageutilities;
/* loaded from: classes12.dex */
public class MarkerSearchResult {
    private final int paddedLength;
    private final boolean skipForVisualDigest;

    public MarkerSearchResult(int i, boolean z) {
        this.paddedLength = i;
        this.skipForVisualDigest = z;
    }

    public int getPaddedLength() {
        return this.paddedLength;
    }

    public boolean isSkipForVisualDigest() {
        return this.skipForVisualDigest;
    }
}
