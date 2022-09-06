package org.bouncycastle.crypto.macs;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class SipHash128 extends SipHash {
    public SipHash128() {
    }

    public SipHash128(int i, int i2) {
        super(i, i2);
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        int i2;
        this.m = this.m >>> ((7 - this.wordPos) << 3);
        this.m >>>= 8;
        this.m |= (((this.wordCount << 3) + i2) & 255) << 56;
        processMessageWord();
        this.v2 ^= 238;
        applySipRounds(this.d);
        long j = this.v0;
        long j2 = this.v1;
        long j3 = ((j ^ j2) ^ this.v2) ^ this.v3;
        this.v1 = j2 ^ 221;
        applySipRounds(this.d);
        reset();
        Pack.longToLittleEndian(j3, bArr, i);
        Pack.longToLittleEndian(((this.v0 ^ this.v1) ^ this.v2) ^ this.v3, bArr, i + 8);
        return 16;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash
    public long doFinal() throws DataLengthException, IllegalStateException {
        throw new UnsupportedOperationException("doFinal() is not supported");
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SipHash128-");
        outline107.append(this.c);
        outline107.append(ProcessIdUtil.DEFAULT_PROCESSID);
        outline107.append(this.d);
        return outline107.toString();
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.macs.SipHash, org.bouncycastle.crypto.Mac
    public void reset() {
        super.reset();
        this.v1 ^= 238;
    }
}
