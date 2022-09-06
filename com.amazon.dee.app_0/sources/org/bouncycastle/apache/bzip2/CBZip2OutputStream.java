package org.bouncycastle.apache.bzip2;

import androidx.core.view.InputDeviceCompat;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
/* loaded from: classes4.dex */
public class CBZip2OutputStream extends OutputStream implements BZip2Constants {
    protected static final int CLEARMASK = -2097153;
    protected static final int DEPTH_THRESH = 10;
    protected static final int GREATER_ICOST = 15;
    protected static final int LESSER_ICOST = 0;
    protected static final int QSORT_STACK_SIZE = 1000;
    protected static final int SETMASK = 2097152;
    protected static final int SMALL_THRESH = 20;
    private int allowableBlockSize;
    private char[] block;
    private int blockCRC;
    boolean blockRandomised;
    int blockSize100k;
    int bsBuff;
    int bsLive;
    private OutputStream bsStream;
    int bytesOut;
    boolean closed;
    private int combinedCRC;
    private int currentChar;
    private boolean finished;
    private boolean firstAttempt;
    private int[] ftab;
    private boolean[] inUse;
    private int[] incs;
    int last;
    CRC mCrc;
    private int[] mtfFreq;
    private int nBlocksRandomised;
    private int nInUse;
    private int nMTF;
    int origPtr;
    private int[] quadrant;
    private int runLength;
    private char[] selector;
    private char[] selectorMtf;
    private char[] seqToUnseq;
    private short[] szptr;
    private char[] unseqToSeq;
    private int workDone;
    private int workFactor;
    private int workLimit;
    private int[] zptr;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class StackElem {
        int dd;
        int hh;
        int ll;

        private StackElem() {
        }
    }

    public CBZip2OutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, 9);
    }

    public CBZip2OutputStream(OutputStream outputStream, int i) throws IOException {
        this.mCrc = new CRC();
        this.inUse = new boolean[256];
        this.seqToUnseq = new char[256];
        this.unseqToSeq = new char[256];
        this.selector = new char[BZip2Constants.MAX_SELECTORS];
        this.selectorMtf = new char[BZip2Constants.MAX_SELECTORS];
        this.mtfFreq = new int[258];
        this.currentChar = -1;
        this.runLength = 0;
        this.closed = false;
        this.incs = new int[]{1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484};
        this.block = null;
        this.quadrant = null;
        this.zptr = null;
        this.ftab = null;
        outputStream.write(66);
        outputStream.write(90);
        bsSetStream(outputStream);
        this.workFactor = 50;
        int i2 = i <= 9 ? i : 9;
        this.blockSize100k = i2 < 1 ? 1 : i2;
        allocateCompressStructures();
        initialize();
        initBlock();
    }

    private void allocateCompressStructures() {
        int i = this.blockSize100k * 100000;
        this.block = new char[i + 1 + 20];
        this.quadrant = new int[i + 20];
        this.zptr = new int[i];
        this.ftab = new int[65537];
        this.szptr = new short[i * 2];
    }

    private void bsFinishedWithStream() throws IOException {
        while (this.bsLive > 0) {
            try {
                this.bsStream.write(this.bsBuff >> 24);
                this.bsBuff <<= 8;
                this.bsLive -= 8;
                this.bytesOut++;
            } catch (IOException e) {
                throw e;
            }
        }
    }

    private void bsPutIntVS(int i, int i2) throws IOException {
        bsW(i, i2);
    }

    private void bsPutUChar(int i) throws IOException {
        bsW(8, i);
    }

    private void bsPutint(int i) throws IOException {
        bsW(8, (i >> 24) & 255);
        bsW(8, (i >> 16) & 255);
        bsW(8, (i >> 8) & 255);
        bsW(8, i & 255);
    }

    private void bsSetStream(OutputStream outputStream) {
        this.bsStream = outputStream;
        this.bsLive = 0;
        this.bsBuff = 0;
        this.bytesOut = 0;
    }

    private void bsW(int i, int i2) throws IOException {
        while (true) {
            int i3 = this.bsLive;
            if (i3 < 8) {
                this.bsBuff = (i2 << ((32 - i3) - i)) | this.bsBuff;
                this.bsLive = i3 + i;
                return;
            }
            try {
                this.bsStream.write(this.bsBuff >> 24);
                this.bsBuff <<= 8;
                this.bsLive -= 8;
                this.bytesOut++;
            } catch (IOException e) {
                throw e;
            }
        }
    }

    private void doReversibleTransformation() {
        this.workLimit = this.workFactor * this.last;
        int i = 0;
        this.workDone = 0;
        this.blockRandomised = false;
        this.firstAttempt = true;
        mainSort();
        if (this.workDone > this.workLimit && this.firstAttempt) {
            randomiseBlock();
            this.workDone = 0;
            this.workLimit = 0;
            this.blockRandomised = true;
            this.firstAttempt = false;
            mainSort();
        }
        this.origPtr = -1;
        while (true) {
            if (i > this.last) {
                break;
            } else if (this.zptr[i] == 0) {
                this.origPtr = i;
                break;
            } else {
                i++;
            }
        }
        if (this.origPtr == -1) {
            panic();
        }
    }

    private void endBlock() throws IOException {
        this.blockCRC = this.mCrc.getFinalCRC();
        int i = this.combinedCRC;
        this.combinedCRC = (i >>> 31) | (i << 1);
        this.combinedCRC ^= this.blockCRC;
        doReversibleTransformation();
        bsPutUChar(49);
        bsPutUChar(65);
        bsPutUChar(89);
        bsPutUChar(38);
        bsPutUChar(83);
        bsPutUChar(89);
        bsPutint(this.blockCRC);
        if (this.blockRandomised) {
            bsW(1, 1);
            this.nBlocksRandomised++;
        } else {
            bsW(1, 0);
        }
        moveToFrontCodeAndSend();
    }

    private void endCompression() throws IOException {
        bsPutUChar(23);
        bsPutUChar(114);
        bsPutUChar(69);
        bsPutUChar(56);
        bsPutUChar(80);
        bsPutUChar(144);
        bsPutint(this.combinedCRC);
        bsFinishedWithStream();
    }

    private boolean fullGtU(int i, int i2) {
        char[] cArr = this.block;
        int i3 = i + 1;
        char c = cArr[i3];
        int i4 = i2 + 1;
        char c2 = cArr[i4];
        if (c != c2) {
            return c > c2;
        }
        int i5 = i3 + 1;
        char c3 = cArr[i5];
        int i6 = i4 + 1;
        char c4 = cArr[i6];
        if (c3 != c4) {
            return c3 > c4;
        }
        int i7 = i5 + 1;
        char c5 = cArr[i7];
        int i8 = i6 + 1;
        char c6 = cArr[i8];
        if (c5 != c6) {
            return c5 > c6;
        }
        int i9 = i7 + 1;
        char c7 = cArr[i9];
        int i10 = i8 + 1;
        char c8 = cArr[i10];
        if (c7 != c8) {
            return c7 > c8;
        }
        int i11 = i9 + 1;
        char c9 = cArr[i11];
        int i12 = i10 + 1;
        char c10 = cArr[i12];
        if (c9 != c10) {
            return c9 > c10;
        }
        int i13 = i11 + 1;
        char c11 = cArr[i13];
        int i14 = i12 + 1;
        char c12 = cArr[i14];
        if (c11 != c12) {
            return c11 > c12;
        }
        int i15 = this.last + 1;
        do {
            char[] cArr2 = this.block;
            int i16 = i13 + 1;
            char c13 = cArr2[i16];
            int i17 = i14 + 1;
            char c14 = cArr2[i17];
            if (c13 != c14) {
                return c13 > c14;
            }
            int[] iArr = this.quadrant;
            int i18 = iArr[i13];
            int i19 = iArr[i14];
            if (i18 != i19) {
                return i18 > i19;
            }
            int i20 = i16 + 1;
            char c15 = cArr2[i20];
            int i21 = i17 + 1;
            char c16 = cArr2[i21];
            if (c15 != c16) {
                return c15 > c16;
            }
            int i22 = iArr[i16];
            int i23 = iArr[i17];
            if (i22 != i23) {
                return i22 > i23;
            }
            int i24 = i20 + 1;
            char c17 = cArr2[i24];
            int i25 = i21 + 1;
            char c18 = cArr2[i25];
            if (c17 != c18) {
                return c17 > c18;
            }
            int i26 = iArr[i20];
            int i27 = iArr[i21];
            if (i26 != i27) {
                return i26 > i27;
            }
            i13 = i24 + 1;
            char c19 = cArr2[i13];
            int i28 = i25 + 1;
            char c20 = cArr2[i28];
            if (c19 != c20) {
                return c19 > c20;
            }
            int i29 = iArr[i24];
            int i30 = iArr[i25];
            if (i29 != i30) {
                return i29 > i30;
            }
            int i31 = this.last;
            if (i13 > i31) {
                i13 = (i13 - i31) - 1;
            }
            int i32 = this.last;
            if (i28 > i32) {
                i28 = (i28 - i32) - 1;
            }
            i14 = i28;
            i15 -= 4;
            this.workDone++;
        } while (i15 >= 0);
        return false;
    }

    private void generateMTFValues() {
        char[] cArr = new char[256];
        makeMaps();
        int i = this.nInUse + 1;
        for (int i2 = 0; i2 <= i; i2++) {
            this.mtfFreq[i2] = 0;
        }
        for (int i3 = 0; i3 < this.nInUse; i3++) {
            cArr[i3] = (char) i3;
        }
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 <= this.last; i6++) {
            char c = this.unseqToSeq[this.block[this.zptr[i6]]];
            char c2 = cArr[0];
            int i7 = 0;
            while (c != c2) {
                i7++;
                char c3 = cArr[i7];
                cArr[i7] = c2;
                c2 = c3;
            }
            cArr[0] = c2;
            if (i7 == 0) {
                i4++;
            } else {
                if (i4 > 0) {
                    int i8 = i4 - 1;
                    while (true) {
                        int i9 = i8 % 2;
                        if (i9 == 0) {
                            this.szptr[i5] = 0;
                            i5++;
                            int[] iArr = this.mtfFreq;
                            iArr[0] = iArr[0] + 1;
                        } else if (i9 == 1) {
                            this.szptr[i5] = 1;
                            i5++;
                            int[] iArr2 = this.mtfFreq;
                            iArr2[1] = iArr2[1] + 1;
                        }
                        if (i8 < 2) {
                            break;
                        }
                        i8 = (i8 - 2) / 2;
                    }
                    i4 = 0;
                }
                int i10 = i7 + 1;
                this.szptr[i5] = (short) i10;
                i5++;
                int[] iArr3 = this.mtfFreq;
                iArr3[i10] = iArr3[i10] + 1;
            }
        }
        if (i4 > 0) {
            int i11 = i4 - 1;
            while (true) {
                int i12 = i11 % 2;
                if (i12 == 0) {
                    this.szptr[i5] = 0;
                    i5++;
                    int[] iArr4 = this.mtfFreq;
                    iArr4[0] = iArr4[0] + 1;
                } else if (i12 == 1) {
                    this.szptr[i5] = 1;
                    i5++;
                    int[] iArr5 = this.mtfFreq;
                    iArr5[1] = iArr5[1] + 1;
                }
                if (i11 < 2) {
                    break;
                }
                i11 = (i11 - 2) / 2;
            }
        }
        this.szptr[i5] = (short) i;
        int[] iArr6 = this.mtfFreq;
        iArr6[i] = iArr6[i] + 1;
        this.nMTF = i5 + 1;
    }

    private void hbAssignCodes(int[] iArr, char[] cArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i <= i2) {
            int i5 = i4;
            for (int i6 = 0; i6 < i3; i6++) {
                if (cArr[i6] == i) {
                    iArr[i6] = i5;
                    i5++;
                }
            }
            i4 = i5 << 1;
            i++;
        }
    }

    protected static void hbMakeCodeLengths(char[] cArr, int[] iArr, int i, int i2) {
        int i3 = 260;
        int[] iArr2 = new int[260];
        int i4 = 516;
        int[] iArr3 = new int[516];
        int[] iArr4 = new int[516];
        int i5 = 0;
        int i6 = 0;
        while (true) {
            int i7 = 1;
            if (i6 >= i) {
                break;
            }
            int i8 = i6 + 1;
            if (iArr[i6] != 0) {
                i7 = iArr[i6];
            }
            iArr3[i8] = i7 << 8;
            i6 = i8;
        }
        while (true) {
            iArr2[i5] = i5;
            iArr3[i5] = i5;
            iArr4[i5] = -2;
            int i9 = i5;
            for (int i10 = 1; i10 <= i; i10++) {
                iArr4[i10] = -1;
                i9++;
                iArr2[i9] = i10;
                int i11 = iArr2[i9];
                int i12 = i9;
                while (true) {
                    int i13 = i12 >> 1;
                    if (iArr3[i11] < iArr3[iArr2[i13]]) {
                        iArr2[i12] = iArr2[i13];
                        i12 = i13;
                    }
                }
                iArr2[i12] = i11;
            }
            if (i9 >= i3) {
                panic();
            }
            int i14 = i;
            while (i9 > 1) {
                int i15 = iArr2[1];
                iArr2[1] = iArr2[i9];
                int i16 = i9 - 1;
                int i17 = iArr2[1];
                int i18 = 1;
                while (true) {
                    int i19 = i18 << 1;
                    if (i19 > i16) {
                        break;
                    }
                    if (i19 < i16) {
                        int i20 = i19 + 1;
                        if (iArr3[iArr2[i20]] < iArr3[iArr2[i19]]) {
                            i19 = i20;
                        }
                    }
                    if (iArr3[i17] < iArr3[iArr2[i19]]) {
                        break;
                    }
                    iArr2[i18] = iArr2[i19];
                    i18 = i19;
                }
                iArr2[i18] = i17;
                int i21 = iArr2[1];
                iArr2[1] = iArr2[i16];
                int i22 = i16 - 1;
                int i23 = iArr2[1];
                int i24 = 1;
                while (true) {
                    int i25 = i24 << 1;
                    if (i25 > i22) {
                        break;
                    }
                    if (i25 < i22) {
                        int i26 = i25 + 1;
                        if (iArr3[iArr2[i26]] < iArr3[iArr2[i25]]) {
                            i25 = i26;
                        }
                    }
                    if (iArr3[i23] < iArr3[iArr2[i25]]) {
                        break;
                    }
                    iArr2[i24] = iArr2[i25];
                    i24 = i25;
                }
                iArr2[i24] = i23;
                i14++;
                iArr4[i21] = i14;
                iArr4[i15] = i14;
                iArr3[i14] = ((((iArr3[i15] & 255) > (iArr3[i21] & 255) ? iArr3[i15] : iArr3[i21]) & 255) + 1) | ((iArr3[i15] & InputDeviceCompat.SOURCE_ANY) + (iArr3[i21] & InputDeviceCompat.SOURCE_ANY));
                iArr4[i14] = -1;
                i9 = i22 + 1;
                iArr2[i9] = i14;
                int i27 = iArr2[i9];
                int i28 = i9;
                while (true) {
                    int i29 = i28 >> 1;
                    if (iArr3[i27] < iArr3[iArr2[i29]]) {
                        iArr2[i28] = iArr2[i29];
                        i28 = i29;
                    }
                }
                iArr2[i28] = i27;
                i4 = 516;
            }
            int i30 = i4;
            if (i14 >= i30) {
                panic();
            }
            boolean z = false;
            for (int i31 = 1; i31 <= i; i31++) {
                int i32 = i31;
                int i33 = 0;
                while (iArr4[i32] >= 0) {
                    i32 = iArr4[i32];
                    i33++;
                }
                cArr[i31 - 1] = (char) i33;
                if (i33 > i2) {
                    z = true;
                }
            }
            if (!z) {
                return;
            }
            for (int i34 = 1; i34 < i; i34++) {
                iArr3[i34] = (((iArr3[i34] >> 8) / 2) + 1) << 8;
            }
            i4 = i30;
            i3 = 260;
            i5 = 0;
        }
    }

    private void initBlock() {
        this.mCrc.initialiseCRC();
        this.last = -1;
        for (int i = 0; i < 256; i++) {
            this.inUse[i] = false;
        }
        this.allowableBlockSize = (this.blockSize100k * 100000) - 20;
    }

    private void initialize() throws IOException {
        this.bytesOut = 0;
        this.nBlocksRandomised = 0;
        bsPutUChar(104);
        bsPutUChar(this.blockSize100k + 48);
        this.combinedCRC = 0;
    }

    private void mainSort() {
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr = new int[256];
        int[] iArr2 = new int[256];
        boolean[] zArr = new boolean[256];
        int i5 = 0;
        int i6 = 0;
        while (true) {
            i = 2;
            i2 = 20;
            if (i6 >= 20) {
                break;
            }
            char[] cArr = this.block;
            int i7 = this.last;
            cArr[i7 + i6 + 2] = cArr[(i6 % (i7 + 1)) + 1];
            i6++;
        }
        int i8 = 0;
        while (true) {
            i3 = this.last;
            if (i8 > i3 + 20) {
                break;
            }
            this.quadrant[i8] = 0;
            i8++;
        }
        char[] cArr2 = this.block;
        cArr2[0] = cArr2[i3 + 1];
        if (i3 >= 4000) {
            for (int i9 = 0; i9 <= 255; i9++) {
                zArr[i9] = false;
            }
            for (int i10 = 0; i10 <= 65536; i10++) {
                this.ftab[i10] = 0;
            }
            char c = this.block[0];
            int i11 = 0;
            while (i11 <= this.last) {
                i11++;
                char c2 = this.block[i11];
                int[] iArr3 = this.ftab;
                int i12 = (c << '\b') + c2;
                iArr3[i12] = iArr3[i12] + 1;
                c = c2;
            }
            for (int i13 = 1; i13 <= 65536; i13++) {
                int[] iArr4 = this.ftab;
                iArr4[i13] = iArr4[i13] + iArr4[i13 - 1];
            }
            char c3 = this.block[1];
            int i14 = 0;
            while (true) {
                i4 = this.last;
                if (i14 >= i4) {
                    break;
                }
                char c4 = this.block[i14 + 2];
                int i15 = (c3 << '\b') + c4;
                int[] iArr5 = this.ftab;
                iArr5[i15] = iArr5[i15] - 1;
                this.zptr[iArr5[i15]] = i14;
                i14++;
                c3 = c4;
            }
            char[] cArr3 = this.block;
            int i16 = (cArr3[i4 + 1] << '\b') + cArr3[1];
            int[] iArr6 = this.ftab;
            iArr6[i16] = iArr6[i16] - 1;
            this.zptr[iArr6[i16]] = i4;
            for (int i17 = 0; i17 <= 255; i17++) {
                iArr[i17] = i17;
            }
            int i18 = 1;
            do {
                i18 = (i18 * 3) + 1;
            } while (i18 <= 256);
            do {
                i18 /= 3;
                for (int i19 = i18; i19 <= 255; i19++) {
                    int i20 = iArr[i19];
                    int i21 = i19;
                    while (true) {
                        int[] iArr7 = this.ftab;
                        int i22 = i21 - i18;
                        if (iArr7[(iArr[i22] + 1) << 8] - iArr7[iArr[i22] << 8] > iArr7[(i20 + 1) << 8] - iArr7[i20 << 8]) {
                            iArr[i21] = iArr[i22];
                            if (i22 <= i18 - 1) {
                                i21 = i22;
                                break;
                            }
                            i21 = i22;
                        }
                    }
                    iArr[i21] = i20;
                }
            } while (i18 != 1);
            int i23 = 0;
            while (i23 <= 255) {
                int i24 = iArr[i23];
                for (int i25 = i5; i25 <= 255; i25++) {
                    int i26 = (i24 << 8) + i25;
                    int[] iArr8 = this.ftab;
                    if ((iArr8[i26] & 2097152) != 2097152) {
                        int i27 = iArr8[i26] & CLEARMASK;
                        int i28 = (CLEARMASK & iArr8[i26 + 1]) - 1;
                        if (i28 > i27) {
                            qSort3(i27, i28, i);
                            if (this.workDone > this.workLimit && this.firstAttempt) {
                                return;
                            }
                        }
                        int[] iArr9 = this.ftab;
                        iArr9[i26] = 2097152 | iArr9[i26];
                    }
                }
                zArr[i24] = true;
                if (i23 < 255) {
                    int[] iArr10 = this.ftab;
                    int i29 = iArr10[i24 << 8] & CLEARMASK;
                    int i30 = (iArr10[(i24 + 1) << 8] & CLEARMASK) - i29;
                    int i31 = 0;
                    while ((i30 >> i31) > 65534) {
                        i31++;
                    }
                    int i32 = 0;
                    while (i32 < i30) {
                        int i33 = this.zptr[i29 + i32];
                        int i34 = i32 >> i31;
                        int[] iArr11 = this.quadrant;
                        iArr11[i33] = i34;
                        if (i33 < i2) {
                            iArr11[i33 + this.last + 1] = i34;
                        }
                        i32++;
                        i2 = 20;
                    }
                    if (((i30 - 1) >> i31) > 65535) {
                        panic();
                    }
                }
                for (int i35 = 0; i35 <= 255; i35++) {
                    iArr2[i35] = this.ftab[(i35 << 8) + i24] & CLEARMASK;
                }
                for (int i36 = this.ftab[i24 << 8] & CLEARMASK; i36 < (this.ftab[(i24 + 1) << 8] & CLEARMASK); i36++) {
                    char[] cArr4 = this.block;
                    int[] iArr12 = this.zptr;
                    char c5 = cArr4[iArr12[i36]];
                    if (!zArr[c5]) {
                        iArr12[iArr2[c5]] = iArr12[i36] == 0 ? this.last : iArr12[i36] - 1;
                        iArr2[c5] = iArr2[c5] + 1;
                    }
                }
                for (int i37 = 0; i37 <= 255; i37++) {
                    int[] iArr13 = this.ftab;
                    int i38 = (i37 << 8) + i24;
                    iArr13[i38] = iArr13[i38] | 2097152;
                }
                i23++;
                i5 = 0;
                i = 2;
                i2 = 20;
            }
            return;
        }
        int i39 = 0;
        while (true) {
            int i40 = this.last;
            if (i39 > i40) {
                this.firstAttempt = false;
                this.workLimit = 0;
                this.workDone = 0;
                simpleSort(0, i40, 0);
                return;
            }
            this.zptr[i39] = i39;
            i39++;
        }
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

    private char med3(char c, char c2, char c3) {
        if (c <= c2) {
            c2 = c;
            c = c2;
        }
        if (c > c3) {
            c = c3;
        }
        return c2 > c ? c2 : c;
    }

    private void moveToFrontCodeAndSend() throws IOException {
        bsPutIntVS(24, this.origPtr);
        generateMTFValues();
        sendMTFValues();
    }

    private static void panic() {
        System.out.println("panic");
    }

    private void qSort3(int i, int i2, int i3) {
        StackElem[] stackElemArr = new StackElem[1000];
        for (int i4 = 0; i4 < 1000; i4++) {
            stackElemArr[i4] = new StackElem();
        }
        stackElemArr[0].ll = i;
        stackElemArr[0].hh = i2;
        stackElemArr[0].dd = i3;
        int i5 = 1;
        while (i5 > 0) {
            if (i5 >= 1000) {
                panic();
            }
            i5--;
            int i6 = stackElemArr[i5].ll;
            int i7 = stackElemArr[i5].hh;
            int i8 = stackElemArr[i5].dd;
            if (i7 - i6 < 20 || i8 > 10) {
                simpleSort(i6, i7, i8);
                if (this.workDone > this.workLimit && this.firstAttempt) {
                    return;
                }
            } else {
                char[] cArr = this.block;
                int[] iArr = this.zptr;
                char med3 = med3(cArr[iArr[i6] + i8 + 1], cArr[iArr[i7] + i8 + 1], cArr[iArr[(i6 + i7) >> 1] + i8 + 1]);
                int i9 = i6;
                int i10 = i9;
                int i11 = i7;
                int i12 = i11;
                while (true) {
                    if (i9 <= i11) {
                        char[] cArr2 = this.block;
                        int[] iArr2 = this.zptr;
                        int i13 = cArr2[(iArr2[i9] + i8) + 1] - med3;
                        if (i13 == 0) {
                            int i14 = iArr2[i9];
                            iArr2[i9] = iArr2[i10];
                            iArr2[i10] = i14;
                            i10++;
                        } else if (i13 > 0) {
                        }
                        i9++;
                    }
                    while (i9 <= i11) {
                        char[] cArr3 = this.block;
                        int[] iArr3 = this.zptr;
                        int i15 = cArr3[(iArr3[i11] + i8) + 1] - med3;
                        if (i15 == 0) {
                            int i16 = iArr3[i11];
                            iArr3[i11] = iArr3[i12];
                            iArr3[i12] = i16;
                            i12--;
                        } else if (i15 < 0) {
                            break;
                        }
                        i11--;
                    }
                    if (i9 > i11) {
                        break;
                    }
                    int[] iArr4 = this.zptr;
                    int i17 = iArr4[i9];
                    iArr4[i9] = iArr4[i11];
                    iArr4[i11] = i17;
                    i9++;
                    i11--;
                }
                if (i12 < i10) {
                    stackElemArr[i5].ll = i6;
                    stackElemArr[i5].hh = i7;
                    stackElemArr[i5].dd = i8 + 1;
                    i5++;
                } else {
                    int i18 = i10 - i6;
                    int i19 = i9 - i10;
                    if (i18 >= i19) {
                        i18 = i19;
                    }
                    vswap(i6, i9 - i18, i18);
                    int i20 = i7 - i12;
                    int i21 = i12 - i11;
                    if (i20 >= i21) {
                        i20 = i21;
                    }
                    vswap(i9, (i7 - i20) + 1, i20);
                    int i22 = ((i9 + i6) - i10) - 1;
                    int i23 = (i7 - i21) + 1;
                    stackElemArr[i5].ll = i6;
                    stackElemArr[i5].hh = i22;
                    stackElemArr[i5].dd = i8;
                    int i24 = i5 + 1;
                    stackElemArr[i24].ll = i22 + 1;
                    stackElemArr[i24].hh = i23 - 1;
                    stackElemArr[i24].dd = i8 + 1;
                    int i25 = i24 + 1;
                    stackElemArr[i25].ll = i23;
                    stackElemArr[i25].hh = i7;
                    stackElemArr[i25].dd = i8;
                    i5 = i25 + 1;
                }
            }
        }
    }

    private void randomiseBlock() {
        for (int i = 0; i < 256; i++) {
            this.inUse[i] = false;
        }
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 <= this.last) {
            if (i3 == 0) {
                i3 = (char) BZip2Constants.rNums[i4];
                i4++;
                if (i4 == 512) {
                    i4 = 0;
                }
            }
            i3--;
            char[] cArr = this.block;
            i2++;
            cArr[i2] = (char) (cArr[i2] ^ (i3 == 1 ? (char) 1 : (char) 0));
            char[] cArr2 = this.block;
            cArr2[i2] = (char) (cArr2[i2] & 255);
            this.inUse[cArr2[i2]] = true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16, types: [int] */
    /* JADX WARN: Type inference failed for: r3v18, types: [int] */
    /* JADX WARN: Type inference failed for: r3v19, types: [int] */
    /* JADX WARN: Type inference failed for: r3v41 */
    private void sendMTFValues() throws IOException {
        int i;
        int i2;
        int i3;
        int i4 = 258;
        int i5 = 6;
        char[][] cArr = (char[][]) Array.newInstance(char.class, 6, 258);
        int i6 = this.nInUse + 2;
        short s = 0;
        for (int i7 = 0; i7 < 6; i7++) {
            for (int i8 = 0; i8 < i6; i8++) {
                cArr[i7][i8] = 15;
            }
        }
        if (this.nMTF <= 0) {
            panic();
        }
        int i9 = this.nMTF;
        int i10 = i9 < 200 ? 2 : i9 < 600 ? 3 : i9 < 1200 ? 4 : i9 < 2400 ? 5 : 6;
        int i11 = this.nMTF;
        int i12 = 0;
        int i13 = i10;
        while (true) {
            i = 1;
            if (i13 <= 0) {
                break;
            }
            int i14 = i11 / i13;
            int i15 = 0;
            int i16 = i12 - 1;
            while (i15 < i14 && i16 < i6 - 1) {
                i16++;
                i15 += this.mtfFreq[i16];
            }
            if (i16 > i12 && i13 != i10 && i13 != 1 && (i10 - i13) % 2 == 1) {
                i15 -= this.mtfFreq[i16];
                i16--;
            }
            for (int i17 = 0; i17 < i6; i17++) {
                if (i17 < i12 || i17 > i16) {
                    cArr[i13 - 1][i17] = 15;
                } else {
                    cArr[i13 - 1][i17] = 0;
                }
            }
            i13--;
            i12 = i16 + 1;
            i11 -= i15;
            i4 = 258;
            i5 = 6;
        }
        int[][] iArr = (int[][]) Array.newInstance(int.class, i5, i4);
        int[] iArr2 = new int[i5];
        short[] sArr = new short[i5];
        int i18 = 0;
        int i19 = 0;
        while (true) {
            int i20 = 20;
            if (i18 >= 4) {
                break;
            }
            for (int i21 = s; i21 < i10; i21++) {
                iArr2[i21] = s;
            }
            for (int i22 = s; i22 < i10; i22++) {
                for (int i23 = s; i23 < i6; i23++) {
                    iArr[i22][i23] = s;
                }
            }
            int i24 = s;
            i19 = i24;
            while (true) {
                int i25 = this.nMTF;
                if (i24 >= i25) {
                    break;
                }
                int i26 = (i24 + 50) - i;
                if (i26 >= i25) {
                    i26 = i25 - 1;
                }
                for (int i27 = s; i27 < i10; i27++) {
                    sArr[i27] = s;
                }
                if (i10 == 6) {
                    int i28 = i24;
                    short s2 = s;
                    short s3 = s2;
                    short s4 = s3;
                    short s5 = s4;
                    short s6 = s5;
                    short s7 = s6;
                    while (i28 <= i26) {
                        short s8 = this.szptr[i28];
                        int i29 = i18;
                        short s9 = (short) (s4 + cArr[2][s8]);
                        short s10 = (short) (s5 + cArr[3][s8]);
                        i28++;
                        s6 = (short) (s6 + cArr[4][s8]);
                        s2 = (short) (s2 + cArr[s][s8]);
                        s7 = (short) (s7 + cArr[5][s8]);
                        s5 = s10;
                        i18 = i29;
                        s = 0;
                        s4 = s9;
                        s3 = (short) (s3 + cArr[i][s8]);
                        i = 1;
                    }
                    i3 = i18;
                    sArr[s] = s2;
                    sArr[1] = s3;
                    sArr[2] = s4;
                    sArr[3] = s5;
                    sArr[4] = s6;
                    sArr[5] = s7;
                } else {
                    i3 = i18;
                    for (int i30 = i24; i30 <= i26; i30++) {
                        short s11 = this.szptr[i30];
                        for (int i31 = 0; i31 < i10; i31++) {
                            sArr[i31] = (short) (sArr[i31] + cArr[i31][s11]);
                        }
                    }
                }
                int i32 = -1;
                short s12 = 999999999;
                for (int i33 = 0; i33 < i10; i33++) {
                    if (sArr[i33] < s12) {
                        s12 = sArr[i33];
                        i32 = i33;
                    }
                }
                iArr2[i32] = iArr2[i32] + 1;
                this.selector[i19] = (char) i32;
                i19++;
                while (i24 <= i26) {
                    int[] iArr3 = iArr[i32];
                    short s13 = this.szptr[i24];
                    iArr3[s13] = iArr3[s13] + 1;
                    i24++;
                }
                i24 = i26 + 1;
                i18 = i3;
                s = 0;
                i = 1;
                i20 = 20;
            }
            for (int i34 = s; i34 < i10; i34++) {
                hbMakeCodeLengths(cArr[i34], iArr[i34], i6, i20);
            }
            i18++;
        }
        if (i10 >= 8) {
            panic();
        }
        if (i19 >= 32768 || i19 > 18002) {
            panic();
        }
        char[] cArr2 = new char[6];
        for (int i35 = 0; i35 < i10; i35++) {
            cArr2[i35] = (char) i35;
        }
        for (int i36 = 0; i36 < i19; i36++) {
            char c = this.selector[i36];
            char c2 = cArr2[0];
            int i37 = 0;
            while (c != c2) {
                i37++;
                char c3 = cArr2[i37];
                cArr2[i37] = c2;
                c2 = c3;
            }
            cArr2[0] = c2;
            this.selectorMtf[i36] = (char) i37;
        }
        int[][] iArr4 = (int[][]) Array.newInstance(int.class, 6, 258);
        for (int i38 = 0; i38 < i10; i38++) {
            char c4 = ' ';
            char c5 = 0;
            for (int i39 = 0; i39 < i6; i39++) {
                if (cArr[i38][i39] > c5) {
                    c5 = cArr[i38][i39];
                }
                if (cArr[i38][i39] < c4) {
                    c4 = cArr[i38][i39];
                }
            }
            if (c5 > 20) {
                panic();
            }
            if (c4 < 1) {
                panic();
            }
            hbAssignCodes(iArr4[i38], cArr[i38], c4, c5, i6);
        }
        boolean[] zArr = new boolean[16];
        for (int i40 = 0; i40 < 16; i40++) {
            zArr[i40] = false;
            for (int i41 = 0; i41 < 16; i41++) {
                if (this.inUse[(i40 * 16) + i41]) {
                    zArr[i40] = true;
                }
            }
        }
        for (int i42 = 0; i42 < 16; i42++) {
            if (zArr[i42]) {
                bsW(1, 1);
            } else {
                bsW(1, 0);
            }
        }
        for (int i43 = 0; i43 < 16; i43++) {
            if (zArr[i43]) {
                for (int i44 = 0; i44 < 16; i44++) {
                    if (this.inUse[(i43 * 16) + i44]) {
                        bsW(1, 1);
                    } else {
                        bsW(1, 0);
                    }
                }
            }
        }
        bsW(3, i10);
        bsW(15, i19);
        int i45 = 0;
        while (true) {
            i2 = 0;
            if (i45 >= i19) {
                break;
            }
            while (i2 < this.selectorMtf[i45]) {
                bsW(1, 1);
                i2++;
            }
            bsW(1, 0);
            i45++;
        }
        int i46 = 0;
        while (i46 < i10) {
            char c6 = cArr[i46][i2];
            bsW(5, c6);
            char c7 = c6;
            int i47 = 0;
            while (i47 < i6) {
                while (c7 < cArr[i46][i47]) {
                    bsW(2, 2);
                    c7++;
                }
                char c8 = c7;
                while (c8 > cArr[i46][i47]) {
                    bsW(2, 3);
                    c8--;
                }
                bsW(1, 0);
                i47++;
                c7 = c8;
            }
            i46++;
            i2 = 0;
        }
        int i48 = i2;
        int i49 = i48;
        while (true) {
            int i50 = this.nMTF;
            if (i48 >= i50) {
                break;
            }
            int i51 = (i48 + 50) - 1;
            if (i51 >= i50) {
                i51 = i50 - 1;
            }
            while (i48 <= i51) {
                char[] cArr3 = this.selector;
                char[] cArr4 = cArr[cArr3[i49]];
                short[] sArr2 = this.szptr;
                bsW(cArr4[sArr2[i48]], iArr4[cArr3[i49]][sArr2[i48]]);
                i48++;
            }
            i48 = i51 + 1;
            i49++;
        }
        if (i49 != i19) {
            panic();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0012, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0012, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void simpleSort(int r10, int r11, int r12) {
        /*
            r9 = this;
            int r0 = r11 - r10
            int r0 = r0 + 1
            r1 = 2
            if (r0 >= r1) goto L8
            return
        L8:
            r1 = 0
        L9:
            int[] r2 = r9.incs
            r2 = r2[r1]
            if (r2 >= r0) goto L12
            int r1 = r1 + 1
            goto L9
        L12:
            int r1 = r1 + (-1)
            if (r1 < 0) goto La4
            int[] r0 = r9.incs
            r0 = r0[r1]
            int r2 = r10 + r0
            r3 = r2
        L1d:
            if (r3 <= r11) goto L20
            goto L73
        L20:
            int[] r4 = r9.zptr
            r4 = r4[r3]
            r5 = r3
        L25:
            int[] r6 = r9.zptr
            int r7 = r5 - r0
            r6 = r6[r7]
            int r6 = r6 + r12
            int r8 = r4 + r12
            boolean r6 = r9.fullGtU(r6, r8)
            if (r6 == 0) goto L42
            int[] r6 = r9.zptr
            r8 = r6[r7]
            r6[r5] = r8
            int r5 = r2 + (-1)
            if (r7 > r5) goto L40
            r5 = r7
            goto L42
        L40:
            r5 = r7
            goto L25
        L42:
            int[] r6 = r9.zptr
            r6[r5] = r4
            int r3 = r3 + 1
            if (r3 <= r11) goto L4b
            goto L73
        L4b:
            r4 = r6[r3]
            r5 = r3
        L4e:
            int[] r6 = r9.zptr
            int r7 = r5 - r0
            r6 = r6[r7]
            int r6 = r6 + r12
            int r8 = r4 + r12
            boolean r6 = r9.fullGtU(r6, r8)
            if (r6 == 0) goto L6b
            int[] r6 = r9.zptr
            r8 = r6[r7]
            r6[r5] = r8
            int r5 = r2 + (-1)
            if (r7 > r5) goto L69
            r5 = r7
            goto L6b
        L69:
            r5 = r7
            goto L4e
        L6b:
            int[] r6 = r9.zptr
            r6[r5] = r4
            int r3 = r3 + 1
            if (r3 <= r11) goto L74
        L73:
            goto L12
        L74:
            r4 = r6[r3]
            r5 = r3
        L77:
            int[] r6 = r9.zptr
            int r7 = r5 - r0
            r6 = r6[r7]
            int r6 = r6 + r12
            int r8 = r4 + r12
            boolean r6 = r9.fullGtU(r6, r8)
            if (r6 == 0) goto L94
            int[] r6 = r9.zptr
            r8 = r6[r7]
            r6[r5] = r8
            int r5 = r2 + (-1)
            if (r7 > r5) goto L92
            r5 = r7
            goto L94
        L92:
            r5 = r7
            goto L77
        L94:
            int[] r6 = r9.zptr
            r6[r5] = r4
            int r3 = r3 + 1
            int r4 = r9.workDone
            int r5 = r9.workLimit
            if (r4 <= r5) goto L1d
            boolean r4 = r9.firstAttempt
            if (r4 == 0) goto L1d
        La4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.apache.bzip2.CBZip2OutputStream.simpleSort(int, int, int):void");
    }

    private void vswap(int i, int i2, int i3) {
        while (i3 > 0) {
            int[] iArr = this.zptr;
            int i4 = iArr[i];
            iArr[i] = iArr[i2];
            iArr[i2] = i4;
            i++;
            i2++;
            i3--;
        }
    }

    private void writeRun() throws IOException {
        int i;
        if (this.last >= this.allowableBlockSize) {
            endBlock();
            initBlock();
            writeRun();
            return;
        }
        this.inUse[this.currentChar] = true;
        int i2 = 0;
        while (true) {
            i = this.runLength;
            if (i2 >= i) {
                break;
            }
            this.mCrc.updateCRC((char) this.currentChar);
            i2++;
        }
        if (i == 1) {
            this.last++;
            this.block[this.last + 1] = (char) this.currentChar;
        } else if (i == 2) {
            this.last++;
            char[] cArr = this.block;
            int i3 = this.last;
            int i4 = this.currentChar;
            cArr[i3 + 1] = (char) i4;
            this.last = i3 + 1;
            cArr[this.last + 1] = (char) i4;
        } else if (i == 3) {
            this.last++;
            char[] cArr2 = this.block;
            int i5 = this.last;
            int i6 = this.currentChar;
            cArr2[i5 + 1] = (char) i6;
            this.last = i5 + 1;
            int i7 = this.last;
            cArr2[i7 + 1] = (char) i6;
            this.last = i7 + 1;
            cArr2[this.last + 1] = (char) i6;
        } else {
            this.inUse[i - 4] = true;
            this.last++;
            char[] cArr3 = this.block;
            int i8 = this.last;
            int i9 = this.currentChar;
            cArr3[i8 + 1] = (char) i9;
            this.last = i8 + 1;
            int i10 = this.last;
            cArr3[i10 + 1] = (char) i9;
            this.last = i10 + 1;
            int i11 = this.last;
            cArr3[i11 + 1] = (char) i9;
            this.last = i11 + 1;
            int i12 = this.last;
            cArr3[i12 + 1] = (char) i9;
            this.last = i12 + 1;
            cArr3[this.last + 1] = (char) (i - 4);
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        finish();
        this.closed = true;
        super.close();
        this.bsStream.close();
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    public void finish() throws IOException {
        if (this.finished) {
            return;
        }
        if (this.runLength > 0) {
            writeRun();
        }
        this.currentChar = -1;
        endBlock();
        endCompression();
        this.finished = true;
        flush();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        super.flush();
        this.bsStream.flush();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        int i2;
        int i3 = (i + 256) % 256;
        int i4 = this.currentChar;
        if (i4 == -1) {
            this.currentChar = i3;
            i2 = this.runLength + 1;
        } else if (i4 != i3) {
            writeRun();
            this.runLength = 1;
            this.currentChar = i3;
            return;
        } else {
            this.runLength++;
            if (this.runLength <= 254) {
                return;
            }
            writeRun();
            this.currentChar = -1;
            i2 = 0;
        }
        this.runLength = i2;
    }
}
