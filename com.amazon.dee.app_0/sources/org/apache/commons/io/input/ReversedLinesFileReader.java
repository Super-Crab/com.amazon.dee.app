package org.apache.commons.io.input;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
/* loaded from: classes4.dex */
public class ReversedLinesFileReader implements Closeable {
    private final int avoidNewlineSplitBufferSize;
    private final int blockSize;
    private final int byteDecrement;
    private FilePart currentFilePart;
    private final String encoding;
    private final byte[][] newLineSequences;
    private final RandomAccessFile randomAccessFile;
    private final long totalBlockCount;
    private final long totalByteLength;
    private boolean trailingNewlineOfFileSkipped;

    /* loaded from: classes4.dex */
    private class FilePart {
        private int currentLastBytePos;
        private final byte[] data;
        private byte[] leftOver;
        private final long no;

        private void createLeftOver() {
            int i = this.currentLastBytePos + 1;
            if (i > 0) {
                this.leftOver = new byte[i];
                System.arraycopy(this.data, 0, this.leftOver, 0, i);
            } else {
                this.leftOver = null;
            }
            this.currentLastBytePos = -1;
        }

        private int getNewLineMatchByteCount(byte[] bArr, int i) {
            byte[][] bArr2;
            for (byte[] bArr3 : ReversedLinesFileReader.this.newLineSequences) {
                boolean z = true;
                for (int length = bArr3.length - 1; length >= 0; length--) {
                    int length2 = (i + length) - (bArr3.length - 1);
                    z &= length2 >= 0 && bArr[length2] == bArr3[length];
                }
                if (z) {
                    return bArr3.length;
                }
            }
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String readLine() throws IOException {
            String str;
            byte[] bArr;
            boolean z = this.no == 1;
            int i = this.currentLastBytePos;
            while (true) {
                if (i > -1) {
                    if (!z && i < ReversedLinesFileReader.this.avoidNewlineSplitBufferSize) {
                        createLeftOver();
                        break;
                    }
                    int newLineMatchByteCount = getNewLineMatchByteCount(this.data, i);
                    if (newLineMatchByteCount <= 0) {
                        i -= ReversedLinesFileReader.this.byteDecrement;
                        if (i < 0) {
                            createLeftOver();
                            break;
                        }
                    } else {
                        int i2 = i + 1;
                        int i3 = (this.currentLastBytePos - i2) + 1;
                        if (i3 >= 0) {
                            byte[] bArr2 = new byte[i3];
                            System.arraycopy(this.data, i2, bArr2, 0, i3);
                            str = new String(bArr2, ReversedLinesFileReader.this.encoding);
                            this.currentLastBytePos = i - newLineMatchByteCount;
                        } else {
                            throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unexpected negative line length=", i3));
                        }
                    }
                } else {
                    break;
                }
            }
            str = null;
            if (!z || (bArr = this.leftOver) == null) {
                return str;
            }
            String str2 = new String(bArr, ReversedLinesFileReader.this.encoding);
            this.leftOver = null;
            return str2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public FilePart rollOver() throws IOException {
            if (this.currentLastBytePos <= -1) {
                long j = this.no;
                if (j > 1) {
                    ReversedLinesFileReader reversedLinesFileReader = ReversedLinesFileReader.this;
                    return new FilePart(j - 1, reversedLinesFileReader.blockSize, this.leftOver);
                } else if (this.leftOver == null) {
                    return null;
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected leftover of the last block: leftOverOfThisFilePart=");
                    outline107.append(new String(this.leftOver, ReversedLinesFileReader.this.encoding));
                    throw new IllegalStateException(outline107.toString());
                }
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Current currentLastCharPos unexpectedly positive... last readLine() should have returned something! currentLastCharPos=");
            outline1072.append(this.currentLastBytePos);
            throw new IllegalStateException(outline1072.toString());
        }

        private FilePart(long j, int i, byte[] bArr) throws IOException {
            this.no = j;
            this.data = new byte[(bArr != null ? bArr.length : 0) + i];
            long j2 = (j - 1) * ReversedLinesFileReader.this.blockSize;
            if (j > 0) {
                ReversedLinesFileReader.this.randomAccessFile.seek(j2);
                if (ReversedLinesFileReader.this.randomAccessFile.read(this.data, 0, i) != i) {
                    throw new IllegalStateException("Count of requested bytes and actually read bytes don't match");
                }
            }
            if (bArr != null) {
                System.arraycopy(bArr, 0, this.data, i, bArr.length);
            }
            this.currentLastBytePos = this.data.length - 1;
            this.leftOver = null;
        }
    }

    public ReversedLinesFileReader(File file) throws IOException {
        this(file, 4096, Charset.defaultCharset().toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.randomAccessFile.close();
    }

    public String readLine() throws IOException {
        String readLine = this.currentFilePart.readLine();
        while (readLine == null) {
            this.currentFilePart = this.currentFilePart.rollOver();
            FilePart filePart = this.currentFilePart;
            if (filePart == null) {
                break;
            }
            readLine = filePart.readLine();
        }
        if (!"".equals(readLine) || this.trailingNewlineOfFileSkipped) {
            return readLine;
        }
        this.trailingNewlineOfFileSkipped = true;
        return readLine();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ReversedLinesFileReader(java.io.File r10, int r11, java.lang.String r12) throws java.io.IOException {
        /*
            r9 = this;
            r9.<init>()
            r0 = 0
            r9.trailingNewlineOfFileSkipped = r0
            r9.blockSize = r11
            r9.encoding = r12
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile
            java.lang.String r2 = "r"
            r1.<init>(r10, r2)
            r9.randomAccessFile = r1
            java.io.RandomAccessFile r10 = r9.randomAccessFile
            long r1 = r10.length()
            r9.totalByteLength = r1
            long r1 = r9.totalByteLength
            long r3 = (long) r11
            long r5 = r1 % r3
            int r10 = (int) r5
            if (r10 <= 0) goto L2a
            long r1 = r1 / r3
            r3 = 1
            long r1 = r1 + r3
            r9.totalBlockCount = r1
            goto L36
        L2a:
            long r3 = r1 / r3
            r9.totalBlockCount = r3
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L36
            r6 = r11
            goto L37
        L36:
            r6 = r10
        L37:
            org.apache.commons.io.input.ReversedLinesFileReader$FilePart r10 = new org.apache.commons.io.input.ReversedLinesFileReader$FilePart
            long r4 = r9.totalBlockCount
            r7 = 0
            r8 = 0
            r2 = r10
            r3 = r9
            r2.<init>(r4, r6, r7)
            r9.currentFilePart = r10
            java.nio.charset.Charset r10 = java.nio.charset.Charset.forName(r12)
            java.nio.charset.CharsetEncoder r11 = r10.newEncoder()
            float r11 = r11.maxBytesPerChar()
            r1 = 1065353216(0x3f800000, float:1.0)
            int r11 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            r1 = 2
            r2 = 1
            if (r11 != 0) goto L5b
            r9.byteDecrement = r2
            goto La2
        L5b:
            java.lang.String r11 = "UTF-8"
            java.nio.charset.Charset r11 = java.nio.charset.Charset.forName(r11)
            if (r10 != r11) goto L66
            r9.byteDecrement = r2
            goto La2
        L66:
            java.lang.String r11 = "Shift_JIS"
            java.nio.charset.Charset r11 = java.nio.charset.Charset.forName(r11)
            if (r10 != r11) goto L71
            r9.byteDecrement = r2
            goto La2
        L71:
            java.lang.String r11 = "UTF-16BE"
            java.nio.charset.Charset r11 = java.nio.charset.Charset.forName(r11)
            if (r10 == r11) goto La0
            java.lang.String r11 = "UTF-16LE"
            java.nio.charset.Charset r11 = java.nio.charset.Charset.forName(r11)
            if (r10 != r11) goto L82
            goto La0
        L82:
            java.lang.String r11 = "UTF-16"
            java.nio.charset.Charset r11 = java.nio.charset.Charset.forName(r11)
            if (r10 != r11) goto L92
            java.io.UnsupportedEncodingException r10 = new java.io.UnsupportedEncodingException
            java.lang.String r11 = "For UTF-16, you need to specify the byte order (use UTF-16BE or UTF-16LE)"
            r10.<init>(r11)
            throw r10
        L92:
            java.io.UnsupportedEncodingException r10 = new java.io.UnsupportedEncodingException
            java.lang.String r11 = "Encoding "
            java.lang.String r0 = " is not supported yet (feel free to submit a patch)"
            java.lang.String r11 = com.android.tools.r8.GeneratedOutlineSupport1.outline75(r11, r12, r0)
            r10.<init>(r11)
            throw r10
        La0:
            r9.byteDecrement = r1
        La2:
            r10 = 3
            byte[][] r10 = new byte[r10]
            java.lang.String r11 = "\r\n"
            byte[] r11 = r11.getBytes(r12)
            r10[r0] = r11
            java.lang.String r11 = "\n"
            byte[] r11 = r11.getBytes(r12)
            r10[r2] = r11
            java.lang.String r11 = "\r"
            byte[] r11 = r11.getBytes(r12)
            r10[r1] = r11
            r9.newLineSequences = r10
            byte[][] r10 = r9.newLineSequences
            r10 = r10[r0]
            int r10 = r10.length
            r9.avoidNewlineSplitBufferSize = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.input.ReversedLinesFileReader.<init>(java.io.File, int, java.lang.String):void");
    }
}
