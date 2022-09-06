package amazon.speech.tts;
/* loaded from: classes.dex */
final class ParsableBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    public byte[] data;

    public ParsableBitArray() {
    }

    private void assertValidOffset() {
        int i;
        int i2 = this.byteOffset;
        if (i2 >= 0 && (i = this.bitOffset) >= 0 && i < 8) {
            int i3 = this.byteLimit;
            if (i2 < i3) {
                return;
            }
            if (i2 == i3 && i == 0) {
                return;
            }
        }
        throw new IllegalStateException();
    }

    private int readExpGolombCodeNum() {
        int i = 0;
        int i2 = 0;
        while (!readBit()) {
            i2++;
        }
        int i3 = (1 << i2) - 1;
        if (i2 > 0) {
            i = readBits(i2);
        }
        return i3 + i;
    }

    public int bitsLeft() {
        return ((this.byteLimit - this.byteOffset) * 8) - this.bitOffset;
    }

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public int peekExpGolombCodedNumLength() {
        int i = this.byteOffset;
        int i2 = this.bitOffset;
        boolean z = false;
        int i3 = 0;
        while (this.byteOffset < this.byteLimit && !readBit()) {
            i3++;
        }
        if (this.byteOffset == this.byteLimit) {
            z = true;
        }
        this.byteOffset = i;
        this.bitOffset = i2;
        if (z) {
            return -1;
        }
        return (i3 * 2) + 1;
    }

    public boolean readBit() {
        return readBits(1) == 1;
    }

    public int readBits(int i) {
        int i2;
        int i3;
        if (i == 0) {
            return 0;
        }
        int i4 = i / 8;
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            int i7 = this.bitOffset;
            if (i7 != 0) {
                byte[] bArr = this.data;
                int i8 = this.byteOffset;
                i3 = ((bArr[i8 + 1] & 255) >>> (8 - i7)) | ((bArr[i8] & 255) << i7);
            } else {
                i3 = this.data[this.byteOffset];
            }
            i -= 8;
            i5 |= (255 & i3) << i;
            this.byteOffset++;
        }
        if (i > 0) {
            int i9 = this.bitOffset + i;
            byte b = (byte) (255 >> (8 - i));
            if (i9 > 8) {
                byte[] bArr2 = this.data;
                int i10 = this.byteOffset;
                i2 = (b & (((bArr2[i10 + 1] & 255) >> (16 - i9)) | ((bArr2[i10] & 255) << (i9 - 8)))) | i5;
                this.byteOffset = i10 + 1;
            } else {
                byte[] bArr3 = this.data;
                int i11 = this.byteOffset;
                i2 = (b & ((bArr3[i11] & 255) >> (8 - i9))) | i5;
                if (i9 == 8) {
                    this.byteOffset = i11 + 1;
                }
            }
            i5 = i2;
            this.bitOffset = i9 % 8;
        }
        assertValidOffset();
        return i5;
    }

    public int readSignedExpGolombCodedInt() {
        int readExpGolombCodeNum = readExpGolombCodeNum();
        return ((readExpGolombCodeNum + 1) / 2) * (readExpGolombCodeNum % 2 == 0 ? -1 : 1);
    }

    public int readUnsignedExpGolombCodedInt() {
        return readExpGolombCodeNum();
    }

    public void reset(byte[] bArr) {
        reset(bArr, bArr.length);
    }

    public void setPosition(int i) {
        this.byteOffset = i / 8;
        this.bitOffset = i - (this.byteOffset * 8);
        assertValidOffset();
    }

    public void skipBits(int i) {
        this.byteOffset = (i / 8) + this.byteOffset;
        this.bitOffset = (i % 8) + this.bitOffset;
        int i2 = this.bitOffset;
        if (i2 > 7) {
            this.byteOffset++;
            this.bitOffset = i2 - 8;
        }
        assertValidOffset();
    }

    public ParsableBitArray(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public void reset(byte[] bArr, int i) {
        this.data = bArr;
        this.byteOffset = 0;
        this.bitOffset = 0;
        this.byteLimit = i;
    }

    public ParsableBitArray(byte[] bArr, int i) {
        this.data = bArr;
        this.byteLimit = i;
    }
}
