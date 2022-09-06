package com.amazonaws.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
class Base32Codec extends AbstractBase32Codec {
    private static final int OFFSET = 26;
    private static final int OFFSET_OF_2 = 24;

    /* loaded from: classes.dex */
    private static class LazyHolder {
        private static final byte[] DECODED = decodeTable();

        private LazyHolder() {
        }

        private static byte[] decodeTable() {
            byte[] bArr = new byte[123];
            for (int i = 0; i <= 122; i++) {
                if (i >= 65 && i <= 90) {
                    bArr[i] = (byte) (i - 65);
                } else if (i >= 50 && i <= 55) {
                    bArr[i] = (byte) (i - 24);
                } else if (i >= 97 && i <= 122) {
                    bArr[i] = (byte) (i - 97);
                } else {
                    bArr[i] = -1;
                }
            }
            return bArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Base32Codec() {
        super(alphabets());
    }

    private static byte[] alphabets() {
        return CodecUtils.toBytesDirect("ABCDEFGHIJKLMNOPQRSTUVWXYZ234567");
    }

    @Override // com.amazonaws.util.AbstractBase32Codec
    protected int pos(byte b) {
        byte b2 = LazyHolder.DECODED[b];
        if (b2 > -1) {
            return b2;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid base 32 character: '");
        outline107.append((char) b);
        outline107.append("'");
        throw new IllegalArgumentException(outline107.toString());
    }
}
