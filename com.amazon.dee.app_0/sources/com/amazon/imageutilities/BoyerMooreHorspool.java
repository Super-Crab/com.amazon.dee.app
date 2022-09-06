package com.amazon.imageutilities;
/* loaded from: classes12.dex */
public class BoyerMooreHorspool {
    private int index(byte b) {
        return b & 255;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object processBytes(byte[] bArr) {
        if (bArr.length <= 2) {
            return null;
        }
        int[] iArr = new int[256];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = bArr.length;
        }
        for (int i2 = 0; i2 < bArr.length - 1; i2++) {
            iArr[index(bArr[i2])] = (bArr.length - i2) - 1;
        }
        return iArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0058, code lost:
        return r4 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int searchBytes(com.amazon.imageutilities.ByteProvider r8, int r9, int r10, int r11, byte[] r12, java.lang.Object r13) {
        /*
            r7 = this;
            int r0 = r12.length
            r1 = 0
            r2 = -1
            r3 = 1
            if (r0 != r3) goto L19
            int r9 = java.lang.Math.min(r9, r11)
        La:
            if (r10 >= r9) goto L18
            byte r11 = r8.get(r10)
            r13 = r12[r1]
            if (r11 != r13) goto L15
            return r10
        L15:
            int r10 = r10 + 1
            goto La
        L18:
            return r2
        L19:
            int r0 = r12.length
            r4 = 2
            if (r0 != r4) goto L3b
            int r9 = java.lang.Math.min(r9, r11)
            int r9 = r9 - r3
        L22:
            if (r10 >= r9) goto L3a
            byte r11 = r8.get(r10)
            r13 = r12[r1]
            if (r11 != r13) goto L37
            int r11 = r10 + 1
            byte r11 = r8.get(r11)
            r13 = r12[r3]
            if (r11 != r13) goto L37
            return r10
        L37:
            int r10 = r10 + 1
            goto L22
        L3a:
            return r2
        L3b:
            int[] r13 = (int[]) r13
            int r9 = r12.length
            int r9 = r9 - r3
            r0 = r9
        L40:
            if (r0 >= r11) goto L65
            r1 = r9
            r4 = r0
        L44:
            if (r1 < 0) goto L55
            byte r5 = r8.get(r4)
            r6 = r12[r1]
            if (r5 != r6) goto L55
            if (r4 < r10) goto L55
            int r4 = r4 + (-1)
            int r1 = r1 + (-1)
            goto L44
        L55:
            if (r1 != r2) goto L59
            int r4 = r4 + r3
            return r4
        L59:
            byte r1 = r8.get(r0)
            int r1 = r7.index(r1)
            r1 = r13[r1]
            int r0 = r0 + r1
            goto L40
        L65:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.imageutilities.BoyerMooreHorspool.searchBytes(com.amazon.imageutilities.ByteProvider, int, int, int, byte[], java.lang.Object):int");
    }
}
