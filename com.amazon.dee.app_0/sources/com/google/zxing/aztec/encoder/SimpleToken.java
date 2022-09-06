package com.google.zxing.aztec.encoder;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.zxing.common.BitArray;
import kotlin.text.Typography;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SimpleToken extends Token {
    private final short bitCount;
    private final short value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleToken(Token token, int i, int i2) {
        super(token);
        this.value = (short) i;
        this.bitCount = (short) i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.zxing.aztec.encoder.Token
    public void appendTo(BitArray bitArray, byte[] bArr) {
        bitArray.appendBits(this.value, this.bitCount);
    }

    public String toString() {
        short s = this.value;
        short s2 = this.bitCount;
        int i = (s & ((1 << s2) - 1)) | (1 << s2);
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104(Typography.less);
        outline104.append(Integer.toBinaryString(i | (1 << this.bitCount)).substring(1));
        outline104.append(Typography.greater);
        return outline104.toString();
    }
}
