package com.google.zxing.aztec.encoder;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.zxing.common.BitArray;
import kotlin.text.Typography;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class BinaryShiftToken extends Token {
    private final short binaryShiftByteCount;
    private final short binaryShiftStart;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinaryShiftToken(Token token, int i, int i2) {
        super(token);
        this.binaryShiftStart = (short) i;
        this.binaryShiftByteCount = (short) i2;
    }

    @Override // com.google.zxing.aztec.encoder.Token
    public void appendTo(BitArray bitArray, byte[] bArr) {
        int i = 0;
        while (true) {
            short s = this.binaryShiftByteCount;
            if (i < s) {
                if (i == 0 || (i == 31 && s <= 62)) {
                    bitArray.appendBits(31, 5);
                    short s2 = this.binaryShiftByteCount;
                    if (s2 > 62) {
                        bitArray.appendBits(s2 - 31, 16);
                    } else if (i == 0) {
                        bitArray.appendBits(Math.min((int) s2, 31), 5);
                    } else {
                        bitArray.appendBits(s2 - 31, 5);
                    }
                }
                bitArray.appendBits(bArr[this.binaryShiftStart + i], 8);
                i++;
            } else {
                return;
            }
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Config.Compare.LESS_THAN);
        outline107.append((int) this.binaryShiftStart);
        outline107.append("::");
        outline107.append((this.binaryShiftStart + this.binaryShiftByteCount) - 1);
        outline107.append(Typography.greater);
        return outline107.toString();
    }
}
