package org.bouncycastle.crypto.modes;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.SkippingStreamCipher;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class SICBlockCipher extends StreamBlockCipher implements SkippingStreamCipher {
    private byte[] IV;
    private final int blockSize;
    private int byteCount;
    private final BlockCipher cipher;
    private byte[] counter;
    private byte[] counterOut;

    public SICBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.cipher = blockCipher;
        this.blockSize = this.cipher.getBlockSize();
        int i = this.blockSize;
        this.IV = new byte[i];
        this.counter = new byte[i];
        this.counterOut = new byte[i];
        this.byteCount = 0;
    }

    private void adjustCounter(long j) {
        int i = 5;
        if (j >= 0) {
            long j2 = (this.byteCount + j) / this.blockSize;
            long j3 = j2;
            if (j2 > 255) {
                while (i >= 1) {
                    long j4 = 1 << (i * 8);
                    while (j3 >= j4) {
                        incrementCounterAt(i);
                        j3 -= j4;
                    }
                    i--;
                }
            }
            incrementCounter((int) j3);
            this.byteCount = (int) ((j + this.byteCount) - (this.blockSize * j2));
            return;
        }
        long j5 = ((-j) - this.byteCount) / this.blockSize;
        long j6 = j5;
        if (j5 > 255) {
            while (i >= 1) {
                long j7 = 1 << (i * 8);
                while (j6 > j7) {
                    decrementCounterAt(i);
                    j6 -= j7;
                }
                i--;
            }
        }
        for (long j8 = 0; j8 != j6; j8++) {
            decrementCounterAt(0);
        }
        int i2 = (int) ((this.blockSize * j5) + this.byteCount + j);
        if (i2 >= 0) {
            this.byteCount = 0;
            return;
        }
        decrementCounterAt(0);
        this.byteCount = this.blockSize + i2;
    }

    private void checkCounter() {
        if (this.IV.length < this.blockSize) {
            int i = 0;
            while (true) {
                byte[] bArr = this.IV;
                if (i == bArr.length) {
                    return;
                }
                if (this.counter[i] != bArr[i]) {
                    throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
                }
                i++;
            }
        }
    }

    private void decrementCounterAt(int i) {
        byte[] bArr;
        byte b;
        int length = this.counter.length - i;
        do {
            length--;
            if (length < 0) {
                return;
            }
            b = (byte) (bArr[length] - 1);
            this.counter[length] = b;
        } while (b == -1);
    }

    private void incrementCounter(int i) {
        byte[] bArr = this.counter;
        byte b = bArr[bArr.length - 1];
        int length = bArr.length - 1;
        bArr[length] = (byte) (bArr[length] + i);
        if (b == 0 || bArr[bArr.length - 1] >= b) {
            return;
        }
        incrementCounterAt(1);
    }

    private void incrementCounterAt(int i) {
        byte b;
        int length = this.counter.length - i;
        do {
            length--;
            if (length < 0) {
                return;
            }
            byte[] bArr = this.counter;
            b = (byte) (bArr[length] + 1);
            bArr[length] = b;
        } while (b == 0);
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    protected byte calculateByte(byte b) throws DataLengthException, IllegalStateException {
        int i = this.byteCount;
        if (i == 0) {
            this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
            byte[] bArr = this.counterOut;
            int i2 = this.byteCount;
            this.byteCount = i2 + 1;
            return (byte) (b ^ bArr[i2]);
        }
        byte[] bArr2 = this.counterOut;
        this.byteCount = i + 1;
        byte b2 = (byte) (b ^ bArr2[i]);
        if (this.byteCount == this.counter.length) {
            this.byteCount = 0;
            incrementCounterAt(0);
            checkCounter();
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/SIC";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long getPosition() {
        byte[] bArr = this.counter;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        int length = bArr2.length - 1;
        while (length >= 1) {
            byte[] bArr3 = this.IV;
            int i = length < bArr3.length ? (bArr2[length] & 255) - (bArr3[length] & 255) : bArr2[length] & 255;
            if (i < 0) {
                int i2 = length - 1;
                bArr2[i2] = (byte) (bArr2[i2] - 1);
                i += 256;
            }
            bArr2[length] = (byte) i;
            length--;
        }
        return (Pack.bigEndianToLong(bArr2, bArr2.length - 8) * this.blockSize) + this.byteCount;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.IV = Arrays.clone(parametersWithIV.getIV());
            int i = this.blockSize;
            if (i < this.IV.length) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("CTR/SIC mode requires IV no greater than: "), this.blockSize, " bytes."));
            }
            int i2 = 8;
            if (8 > i / 2) {
                i2 = i / 2;
            }
            if (this.blockSize - this.IV.length <= i2) {
                if (parametersWithIV.getParameters() != null) {
                    this.cipher.init(true, parametersWithIV.getParameters());
                }
                reset();
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CTR/SIC mode requires IV of at least: ");
            outline107.append(this.blockSize - i2);
            outline107.append(" bytes.");
            throw new IllegalArgumentException(outline107.toString());
        }
        throw new IllegalArgumentException("CTR/SIC mode requires ParametersWithIV");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.blockSize, bArr2, i2);
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.counter, (byte) 0);
        byte[] bArr = this.IV;
        System.arraycopy(bArr, 0, this.counter, 0, bArr.length);
        this.cipher.reset();
        this.byteCount = 0;
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long seekTo(long j) {
        reset();
        return skip(j);
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long skip(long j) {
        adjustCounter(j);
        checkCounter();
        this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
        return j;
    }
}
