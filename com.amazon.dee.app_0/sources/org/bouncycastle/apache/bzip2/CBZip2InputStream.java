package org.bouncycastle.apache.bzip2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
/* loaded from: classes4.dex */
public class CBZip2InputStream extends InputStream implements BZip2Constants {
    private static final int NO_RAND_PART_A_STATE = 5;
    private static final int NO_RAND_PART_B_STATE = 6;
    private static final int NO_RAND_PART_C_STATE = 7;
    private static final int RAND_PART_A_STATE = 2;
    private static final int RAND_PART_B_STATE = 3;
    private static final int RAND_PART_C_STATE = 4;
    private static final int START_BLOCK_STATE = 1;
    private boolean blockRandomised;
    private int blockSize100k;
    private int bsBuff;
    private int bsLive;
    private InputStream bsStream;
    int ch2;
    int chPrev;
    private int computedBlockCRC;
    private int computedCombinedCRC;
    int count;
    int i;
    int i2;
    int j2;
    private int last;
    private int nInUse;
    private int origPtr;
    private int storedBlockCRC;
    private int storedCombinedCRC;
    int tPos;
    char z;
    private CRC mCrc = new CRC();
    private boolean[] inUse = new boolean[256];
    private char[] seqToUnseq = new char[256];
    private char[] unseqToSeq = new char[256];
    private char[] selector = new char[BZip2Constants.MAX_SELECTORS];
    private char[] selectorMtf = new char[BZip2Constants.MAX_SELECTORS];
    private int[] unzftab = new int[256];
    private int[][] limit = (int[][]) Array.newInstance(int.class, 6, 258);
    private int[][] base = (int[][]) Array.newInstance(int.class, 6, 258);
    private int[][] perm = (int[][]) Array.newInstance(int.class, 6, 258);
    private int[] minLens = new int[6];
    private boolean streamEnd = false;
    private int currentChar = -1;
    private int currentState = 1;
    int rNToGo = 0;
    int rTPos = 0;
    private char[] ll8 = null;
    private int[] tt = null;

    public CBZip2InputStream(InputStream inputStream) throws IOException {
        bsSetStream(inputStream);
        initialize();
        initBlock();
        setupBlock();
    }

    private static void badBlockHeader() {
        cadvise();
    }

    private static void blockOverrun() {
        cadvise();
    }

    private void bsFinishedWithStream() {
        try {
            if (this.bsStream == null || this.bsStream == System.in) {
                return;
            }
            this.bsStream.close();
            this.bsStream = null;
        } catch (IOException unused) {
        }
    }

    private int bsGetInt32() {
        return bsGetint();
    }

    private int bsGetIntVS(int i) {
        return bsR(i);
    }

    private char bsGetUChar() {
        return (char) bsR(8);
    }

    private int bsGetint() {
        return bsR(8) | ((((((bsR(8) | 0) << 8) | bsR(8)) << 8) | bsR(8)) << 8);
    }

    private int bsR(int i) {
        while (true) {
            int i2 = this.bsLive;
            if (i2 >= i) {
                int i3 = (this.bsBuff >> (i2 - i)) & ((1 << i) - 1);
                this.bsLive = i2 - i;
                return i3;
            }
            char c = 0;
            try {
                c = (char) this.bsStream.read();
            } catch (IOException unused) {
                compressedStreamEOF();
            }
            if (c == 65535) {
                compressedStreamEOF();
            }
            this.bsBuff = (c & 255) | (this.bsBuff << 8);
            this.bsLive += 8;
        }
    }

    private void bsSetStream(InputStream inputStream) {
        this.bsStream = inputStream;
        this.bsLive = 0;
        this.bsBuff = 0;
    }

    private static void cadvise() {
        System.out.println("CRC Error");
    }

    private void complete() {
        this.storedCombinedCRC = bsGetInt32();
        if (this.storedCombinedCRC != this.computedCombinedCRC) {
            crcError();
        }
        bsFinishedWithStream();
        this.streamEnd = true;
    }

    private static void compressedStreamEOF() {
        cadvise();
    }

    private static void crcError() {
        cadvise();
    }

    private void endBlock() {
        this.computedBlockCRC = this.mCrc.getFinalCRC();
        if (this.storedBlockCRC != this.computedBlockCRC) {
            crcError();
        }
        int i = this.computedCombinedCRC;
        this.computedCombinedCRC = (i >>> 31) | (i << 1);
        this.computedCombinedCRC ^= this.computedBlockCRC;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x016b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void getAndMoveToFrontDecode() {
        /*
            Method dump skipped, instructions count: 487
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.apache.bzip2.CBZip2InputStream.getAndMoveToFrontDecode():void");
    }

    private void hbCreateDecodeTables(int[] iArr, int[] iArr2, int[] iArr3, char[] cArr, int i, int i2, int i3) {
        int i4 = 0;
        int i5 = i;
        int i6 = 0;
        while (i5 <= i2) {
            int i7 = i6;
            for (int i8 = 0; i8 < i3; i8++) {
                if (cArr[i8] == i5) {
                    iArr3[i7] = i8;
                    i7++;
                }
            }
            i5++;
            i6 = i7;
        }
        for (int i9 = 0; i9 < 23; i9++) {
            iArr2[i9] = 0;
        }
        for (int i10 = 0; i10 < i3; i10++) {
            int i11 = cArr[i10] + 1;
            iArr2[i11] = iArr2[i11] + 1;
        }
        for (int i12 = 1; i12 < 23; i12++) {
            iArr2[i12] = iArr2[i12] + iArr2[i12 - 1];
        }
        for (int i13 = 0; i13 < 23; i13++) {
            iArr[i13] = 0;
        }
        int i14 = i;
        while (i14 <= i2) {
            int i15 = i14 + 1;
            int i16 = (iArr2[i15] - iArr2[i14]) + i4;
            iArr[i14] = i16 - 1;
            i4 = i16 << 1;
            i14 = i15;
        }
        for (int i17 = i + 1; i17 <= i2; i17++) {
            iArr2[i17] = ((iArr[i17 - 1] + 1) << 1) - iArr2[i17];
        }
    }

    private void initBlock() {
        char bsGetUChar = bsGetUChar();
        char bsGetUChar2 = bsGetUChar();
        char bsGetUChar3 = bsGetUChar();
        char bsGetUChar4 = bsGetUChar();
        char bsGetUChar5 = bsGetUChar();
        char bsGetUChar6 = bsGetUChar();
        if (bsGetUChar == 23 && bsGetUChar2 == 'r' && bsGetUChar3 == 'E' && bsGetUChar4 == '8' && bsGetUChar5 == 'P' && bsGetUChar6 == 144) {
            complete();
        } else if (bsGetUChar != '1' || bsGetUChar2 != 'A' || bsGetUChar3 != 'Y' || bsGetUChar4 != '&' || bsGetUChar5 != 'S' || bsGetUChar6 != 'Y') {
            badBlockHeader();
            this.streamEnd = true;
        } else {
            this.storedBlockCRC = bsGetInt32();
            if (bsR(1) == 1) {
                this.blockRandomised = true;
            } else {
                this.blockRandomised = false;
            }
            getAndMoveToFrontDecode();
            this.mCrc.initialiseCRC();
            this.currentState = 1;
        }
    }

    private void initialize() throws IOException {
        char bsGetUChar = bsGetUChar();
        char bsGetUChar2 = bsGetUChar();
        if (bsGetUChar == 'B' || bsGetUChar2 == 'Z') {
            char bsGetUChar3 = bsGetUChar();
            char bsGetUChar4 = bsGetUChar();
            if (bsGetUChar3 != 'h' || bsGetUChar4 < '1' || bsGetUChar4 > '9') {
                bsFinishedWithStream();
                this.streamEnd = true;
                return;
            }
            setDecompressStructureSizes(bsGetUChar4 - '0');
            this.computedCombinedCRC = 0;
            return;
        }
        throw new IOException("Not a BZIP2 marked stream");
    }

    private void makeMaps() {
        this.nInUse = 0;
        for (int i = 0; i < 256; i++) {
            if (this.inUse[i]) {
                char[] cArr = this.seqToUnseq;
                int i2 = this.nInUse;
                cArr[i2] = (char) i;
                this.unseqToSeq[i] = (char) i2;
                this.nInUse = i2 + 1;
            }
        }
    }

    private void recvDecodingTables() {
        char[][] cArr = (char[][]) Array.newInstance(char.class, 6, 258);
        boolean[] zArr = new boolean[16];
        for (int i = 0; i < 16; i++) {
            if (bsR(1) == 1) {
                zArr[i] = true;
            } else {
                zArr[i] = false;
            }
        }
        for (int i2 = 0; i2 < 256; i2++) {
            this.inUse[i2] = false;
        }
        for (int i3 = 0; i3 < 16; i3++) {
            if (zArr[i3]) {
                for (int i4 = 0; i4 < 16; i4++) {
                    if (bsR(1) == 1) {
                        this.inUse[(i3 * 16) + i4] = true;
                    }
                }
            }
        }
        makeMaps();
        int i5 = this.nInUse + 2;
        int bsR = bsR(3);
        int bsR2 = bsR(15);
        for (int i6 = 0; i6 < bsR2; i6++) {
            int i7 = 0;
            while (bsR(1) == 1) {
                i7++;
            }
            this.selectorMtf[i6] = (char) i7;
        }
        char[] cArr2 = new char[6];
        for (char c = 0; c < bsR; c = (char) (c + 1)) {
            cArr2[c] = c;
        }
        for (int i8 = 0; i8 < bsR2; i8++) {
            char c2 = this.selectorMtf[i8];
            char c3 = cArr2[c2];
            while (c2 > 0) {
                int i9 = c2 - 1;
                cArr2[c2] = cArr2[i9];
                c2 = (char) i9;
            }
            cArr2[0] = c3;
            this.selector[i8] = c3;
        }
        for (int i10 = 0; i10 < bsR; i10++) {
            int bsR3 = bsR(5);
            for (int i11 = 0; i11 < i5; i11++) {
                while (bsR(1) == 1) {
                    bsR3 = bsR(1) == 0 ? bsR3 + 1 : bsR3 - 1;
                }
                cArr[i10][i11] = (char) bsR3;
            }
        }
        for (int i12 = 0; i12 < bsR; i12++) {
            char c4 = 0;
            char c5 = ' ';
            for (int i13 = 0; i13 < i5; i13++) {
                if (cArr[i12][i13] > c4) {
                    c4 = cArr[i12][i13];
                }
                if (cArr[i12][i13] < c5) {
                    c5 = cArr[i12][i13];
                }
            }
            hbCreateDecodeTables(this.limit[i12], this.base[i12], this.perm[i12], cArr[i12], c5, c4, i5);
            this.minLens[i12] = c5;
        }
    }

    private void setDecompressStructureSizes(int i) {
        if (i >= 0 && i <= 9) {
            int i2 = this.blockSize100k;
        }
        this.blockSize100k = i;
        if (i == 0) {
            return;
        }
        int i3 = i * 100000;
        this.ll8 = new char[i3];
        this.tt = new int[i3];
    }

    private void setupBlock() {
        int[] iArr = new int[257];
        iArr[0] = 0;
        this.i = 1;
        while (true) {
            int i = this.i;
            if (i > 256) {
                break;
            }
            iArr[i] = this.unzftab[i - 1];
            this.i = i + 1;
        }
        this.i = 1;
        while (true) {
            int i2 = this.i;
            if (i2 > 256) {
                break;
            }
            iArr[i2] = iArr[i2] + iArr[i2 - 1];
            this.i = i2 + 1;
        }
        this.i = 0;
        while (true) {
            int i3 = this.i;
            if (i3 > this.last) {
                break;
            }
            char c = this.ll8[i3];
            this.tt[iArr[c]] = i3;
            iArr[c] = iArr[c] + 1;
            this.i = i3 + 1;
        }
        this.tPos = this.tt[this.origPtr];
        this.count = 0;
        this.i2 = 0;
        this.ch2 = 256;
        if (!this.blockRandomised) {
            setupNoRandPartA();
            return;
        }
        this.rNToGo = 0;
        this.rTPos = 0;
        setupRandPartA();
    }

    private void setupNoRandPartA() {
        int i = this.i2;
        if (i > this.last) {
            endBlock();
            initBlock();
            setupBlock();
            return;
        }
        this.chPrev = this.ch2;
        char[] cArr = this.ll8;
        int i2 = this.tPos;
        this.ch2 = cArr[i2];
        this.tPos = this.tt[i2];
        this.i2 = i + 1;
        int i3 = this.ch2;
        this.currentChar = i3;
        this.currentState = 6;
        this.mCrc.updateCRC(i3);
    }

    private void setupNoRandPartB() {
        if (this.ch2 != this.chPrev) {
            this.currentState = 5;
            this.count = 1;
        } else {
            this.count++;
            if (this.count >= 4) {
                char[] cArr = this.ll8;
                int i = this.tPos;
                this.z = cArr[i];
                this.tPos = this.tt[i];
                this.currentState = 7;
                this.j2 = 0;
                setupNoRandPartC();
                return;
            }
            this.currentState = 5;
        }
        setupNoRandPartA();
    }

    private void setupNoRandPartC() {
        if (this.j2 < this.z) {
            int i = this.ch2;
            this.currentChar = i;
            this.mCrc.updateCRC(i);
            this.j2++;
            return;
        }
        this.currentState = 5;
        this.i2++;
        this.count = 0;
        setupNoRandPartA();
    }

    private void setupRandPartA() {
        if (this.i2 > this.last) {
            endBlock();
            initBlock();
            setupBlock();
            return;
        }
        this.chPrev = this.ch2;
        char[] cArr = this.ll8;
        int i = this.tPos;
        this.ch2 = cArr[i];
        this.tPos = this.tt[i];
        int i2 = 0;
        if (this.rNToGo == 0) {
            int[] iArr = BZip2Constants.rNums;
            int i3 = this.rTPos;
            this.rNToGo = iArr[i3];
            this.rTPos = i3 + 1;
            if (this.rTPos == 512) {
                this.rTPos = 0;
            }
        }
        this.rNToGo--;
        int i4 = this.ch2;
        if (this.rNToGo == 1) {
            i2 = 1;
        }
        this.ch2 = i4 ^ i2;
        this.i2++;
        int i5 = this.ch2;
        this.currentChar = i5;
        this.currentState = 3;
        this.mCrc.updateCRC(i5);
    }

    private void setupRandPartB() {
        char c = 1;
        if (this.ch2 != this.chPrev) {
            this.currentState = 2;
            this.count = 1;
        } else {
            this.count++;
            if (this.count >= 4) {
                char[] cArr = this.ll8;
                int i = this.tPos;
                this.z = cArr[i];
                this.tPos = this.tt[i];
                if (this.rNToGo == 0) {
                    int[] iArr = BZip2Constants.rNums;
                    int i2 = this.rTPos;
                    this.rNToGo = iArr[i2];
                    this.rTPos = i2 + 1;
                    if (this.rTPos == 512) {
                        this.rTPos = 0;
                    }
                }
                this.rNToGo--;
                char c2 = this.z;
                if (this.rNToGo != 1) {
                    c = 0;
                }
                this.z = (char) (c2 ^ c);
                this.j2 = 0;
                this.currentState = 4;
                setupRandPartC();
                return;
            }
            this.currentState = 2;
        }
        setupRandPartA();
    }

    private void setupRandPartC() {
        if (this.j2 < this.z) {
            int i = this.ch2;
            this.currentChar = i;
            this.mCrc.updateCRC(i);
            this.j2++;
            return;
        }
        this.currentState = 2;
        this.i2++;
        this.count = 0;
        setupRandPartA();
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.streamEnd) {
            return -1;
        }
        int i = this.currentChar;
        switch (this.currentState) {
            case 3:
                setupRandPartB();
                break;
            case 4:
                setupRandPartC();
                break;
            case 6:
                setupNoRandPartB();
                break;
            case 7:
                setupNoRandPartC();
                break;
        }
        return i;
    }
}
