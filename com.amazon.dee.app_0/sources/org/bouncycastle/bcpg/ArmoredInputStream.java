package org.bouncycastle.bcpg;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import okio.Utf8;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.bouncycastle.util.StringList;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class ArmoredInputStream extends InputStream {
    private static final byte[] decodingTable = new byte[128];
    int bufPtr;
    boolean clearText;
    CRC24 crc;
    boolean crcFound;
    boolean hasHeaders;
    String header;
    StringList headerList;
    InputStream in;
    boolean isEndOfStream;
    int lastC;
    boolean newLineFound;
    int[] outBuf;
    boolean restart;
    boolean start;

    static {
        int i = 0;
        while (true) {
            byte[] bArr = decodingTable;
            if (i >= bArr.length) {
                break;
            }
            bArr[i] = -1;
            i++;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            decodingTable[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            decodingTable[i3] = (byte) ((i3 - 97) + 26);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            decodingTable[i4] = (byte) ((i4 - 48) + 52);
        }
        byte[] bArr2 = decodingTable;
        bArr2[43] = 62;
        bArr2[47] = Utf8.REPLACEMENT_BYTE;
    }

    public ArmoredInputStream(InputStream inputStream) throws IOException {
        this(inputStream, true);
    }

    public ArmoredInputStream(InputStream inputStream, boolean z) throws IOException {
        this.start = true;
        this.outBuf = new int[3];
        this.bufPtr = 3;
        this.crc = new CRC24();
        this.crcFound = false;
        this.hasHeaders = true;
        this.header = null;
        this.newLineFound = false;
        this.clearText = false;
        this.restart = false;
        this.headerList = Strings.newList();
        this.lastC = 0;
        this.in = inputStream;
        this.hasHeaders = z;
        if (z) {
            parseHeaders();
        }
        this.start = false;
    }

    private int decode(int i, int i2, int i3, int i4, int[] iArr) throws IOException {
        if (i4 >= 0) {
            if (i3 == 61) {
                byte[] bArr = decodingTable;
                int i5 = bArr[i] & 255;
                int i6 = bArr[i2] & 255;
                if ((i5 | i6) < 0) {
                    throw new IOException("invalid armor");
                }
                iArr[2] = ((i5 << 2) | (i6 >> 4)) & 255;
                return 2;
            } else if (i4 == 61) {
                byte[] bArr2 = decodingTable;
                byte b = bArr2[i];
                byte b2 = bArr2[i2];
                byte b3 = bArr2[i3];
                if ((b | b2 | b3) < 0) {
                    throw new IOException("invalid armor");
                }
                iArr[1] = ((b << 2) | (b2 >> 4)) & 255;
                iArr[2] = ((b2 << 4) | (b3 >> 2)) & 255;
                return 1;
            } else {
                byte[] bArr3 = decodingTable;
                byte b4 = bArr3[i];
                byte b5 = bArr3[i2];
                byte b6 = bArr3[i3];
                byte b7 = bArr3[i4];
                if ((b4 | b5 | b6 | b7) < 0) {
                    throw new IOException("invalid armor");
                }
                iArr[0] = ((b4 << 2) | (b5 >> 4)) & 255;
                iArr[1] = ((b5 << 4) | (b6 >> 2)) & 255;
                iArr[2] = ((b6 << 6) | b7) & 255;
                return 0;
            }
        }
        throw new EOFException("unexpected end of file in armored stream.");
    }

    private boolean parseHeaders() throws IOException {
        int i;
        boolean z;
        this.header = null;
        this.headerList = Strings.newList();
        if (!this.restart) {
            int i2 = 0;
            while (true) {
                int read = this.in.read();
                if (read < 0) {
                    i = i2;
                    z = false;
                    break;
                } else if (read != 45 || (i2 != 0 && i2 != 10 && i2 != 13)) {
                    i2 = read;
                }
            }
        } else {
            z = true;
            i = 0;
        }
        if (z) {
            StringBuffer stringBuffer = new StringBuffer(ProcessIdUtil.DEFAULT_PROCESSID);
            if (this.restart) {
                stringBuffer.append('-');
            }
            boolean z2 = false;
            boolean z3 = false;
            while (true) {
                int read2 = this.in.read();
                if (read2 >= 0) {
                    if (i == 13 && read2 == 10) {
                        z3 = true;
                    }
                    if ((z2 && i != 13 && read2 == 10) || (z2 && read2 == 13)) {
                        break;
                    }
                    if (read2 == 13 || (i != 13 && read2 == 10)) {
                        String stringBuffer2 = stringBuffer.toString();
                        if (stringBuffer2.trim().length() == 0) {
                            break;
                        }
                        this.headerList.add(stringBuffer2);
                        stringBuffer.setLength(0);
                    }
                    if (read2 != 10 && read2 != 13) {
                        stringBuffer.append((char) read2);
                        z2 = false;
                    } else if (read2 == 13 || (i != 13 && read2 == 10)) {
                        z2 = true;
                    }
                    i = read2;
                } else {
                    break;
                }
            }
            if (z3) {
                this.in.read();
            }
        }
        if (this.headerList.size() > 0) {
            this.header = this.headerList.get(0);
        }
        this.clearText = "-----BEGIN PGP SIGNED MESSAGE-----".equals(this.header);
        this.newLineFound = true;
        return z;
    }

    private int readIgnoreSpace() throws IOException {
        int read;
        while (true) {
            read = this.in.read();
            if (read != 32 && read != 9) {
                break;
            }
        }
        if (read < 128) {
            return read;
        }
        throw new IOException("invalid armor");
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    public String getArmorHeaderLine() {
        return this.header;
    }

    public String[] getArmorHeaders() {
        if (this.headerList.size() <= 1) {
            return null;
        }
        StringList stringList = this.headerList;
        return stringList.toStringArray(1, stringList.size());
    }

    public boolean isClearText() {
        return this.clearText;
    }

    public boolean isEndOfStream() {
        return this.isEndOfStream;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x004f, code lost:
        if (r9.lastC != 13) goto L20;
     */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int read() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.bcpg.ArmoredInputStream.read():int");
    }
}
