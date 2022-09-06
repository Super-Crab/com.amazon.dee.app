package com.amazon.imageutilities;

import java.util.Arrays;
/* loaded from: classes12.dex */
public class BacktrackingByteSearch {
    private byte[] backTrackingBytes;
    private final Object oSearchTerm;
    private final byte[] pattern;
    private final BoyerMooreHorspool searchEngine = new BoyerMooreHorspool();
    private int backTrackingAvailable = 0;

    public BacktrackingByteSearch(byte[] bArr) {
        this.pattern = Arrays.copyOf(bArr, bArr.length);
        this.oSearchTerm = this.searchEngine.processBytes(bArr);
        this.backTrackingBytes = new byte[bArr.length];
    }

    private void getBackTrackingBytes(ByteProvider byteProvider, int i) {
        byte[] bArr = this.backTrackingBytes;
        int length = i < bArr.length ? i : bArr.length;
        int retainBytes = retainBytes(length);
        for (int i2 = 0; i2 < retainBytes; i2++) {
            byte[] bArr2 = this.backTrackingBytes;
            bArr2[i2] = bArr2[(this.backTrackingAvailable - retainBytes) + i2];
        }
        for (int i3 = 0; i3 < length; i3++) {
            this.backTrackingBytes[i3 + retainBytes] = byteProvider.get((i - length) + i3);
        }
        this.backTrackingAvailable = retainBytes + length;
    }

    private int retainBytes(int i) {
        byte[] bArr = this.backTrackingBytes;
        if (i >= bArr.length) {
            return 0;
        }
        int i2 = this.backTrackingAvailable;
        return i + i2 > bArr.length ? bArr.length - i : i2;
    }

    private Byte searchByte(int i, ByteProvider byteProvider) {
        int i2 = this.backTrackingAvailable;
        if (i < i2) {
            return Byte.valueOf(this.backTrackingBytes[i]);
        }
        return Byte.valueOf(byteProvider.get(i - i2));
    }

    public int indexOfLastByteOfFirstOccurence(final ByteProvider byteProvider, int i) {
        BoyerMooreHorspool boyerMooreHorspool = this.searchEngine;
        ByteProvider byteProvider2 = new ByteProvider() { // from class: com.amazon.imageutilities.-$$Lambda$BacktrackingByteSearch$M9ucya4I3E_Nwf5_ZsaKg9W5JKo
            @Override // com.amazon.imageutilities.ByteProvider
            public final byte get(int i2) {
                return BacktrackingByteSearch.this.lambda$indexOfLastByteOfFirstOccurence$0$BacktrackingByteSearch(byteProvider, i2);
            }
        };
        int i2 = this.backTrackingAvailable;
        int searchBytes = boyerMooreHorspool.searchBytes(byteProvider2, i + i2, 0, i + i2, this.pattern, this.oSearchTerm);
        if (searchBytes >= 0) {
            int length = ((searchBytes - this.backTrackingAvailable) + this.pattern.length) - 1;
            this.backTrackingAvailable = 0;
            return length;
        }
        getBackTrackingBytes(byteProvider, i);
        return -1;
    }

    public int indexOfLastByteOfLastOccurence(final ByteProvider byteProvider, final int i) {
        ByteProvider byteProvider2 = byteProvider;
        int i2 = -1;
        int i3 = 0;
        while (i3 >= 0) {
            i3 = indexOfLastByteOfFirstOccurence(byteProvider2, i - Math.max(0, i2));
            i2 = (i2 == -1 ? 0 : 1) + Math.max(0, i2) + i3;
            final int i4 = i2 + 1;
            ByteProvider byteProvider3 = new ByteProvider() { // from class: com.amazon.imageutilities.-$$Lambda$BacktrackingByteSearch$7Ipte5u1xxID7yUSMJ1q2Du3g-w
                @Override // com.amazon.imageutilities.ByteProvider
                public final byte get(int i5) {
                    byte b;
                    b = ByteProvider.this.get(Math.min(i4 + i5, i - 1));
                    return b;
                }
            };
            if (i2 >= i - 1) {
                break;
            }
            byteProvider2 = byteProvider3;
        }
        return i2;
    }

    public /* synthetic */ byte lambda$indexOfLastByteOfFirstOccurence$0$BacktrackingByteSearch(ByteProvider byteProvider, int i) {
        return searchByte(i, byteProvider).byteValue();
    }
}
