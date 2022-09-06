package org.apache.commons.net.io;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
/* loaded from: classes4.dex */
public final class DotTerminatedMessageReader extends Reader {
    private static final String LS = System.getProperty("line.separator");
    private static final char[] LS_CHARS = LS.toCharArray();
    private boolean atBeginning;
    private boolean eof;
    private char[] internalBuffer;
    private PushbackReader internalReader;
    private int pos;

    public DotTerminatedMessageReader(Reader reader) {
        super(reader);
        this.internalBuffer = new char[LS_CHARS.length + 3];
        this.pos = this.internalBuffer.length;
        this.atBeginning = true;
        this.eof = false;
        this.internalReader = new PushbackReader(reader);
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (((Reader) this).lock) {
            if (this.internalReader == null) {
                return;
            }
            if (!this.eof) {
                while (read() != -1) {
                }
            }
            this.eof = true;
            this.atBeginning = false;
            this.pos = this.internalBuffer.length;
            this.internalReader = null;
        }
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        synchronized (((Reader) this).lock) {
            if (this.pos < this.internalBuffer.length) {
                char[] cArr = this.internalBuffer;
                int i = this.pos;
                this.pos = i + 1;
                return cArr[i];
            } else if (this.eof) {
                return -1;
            } else {
                int read = this.internalReader.read();
                if (read == -1) {
                    this.eof = true;
                    return -1;
                }
                if (this.atBeginning) {
                    this.atBeginning = false;
                    if (read == 46) {
                        if (this.internalReader.read() == 46) {
                            return 46;
                        }
                        this.eof = true;
                        this.internalReader.read();
                        return -1;
                    }
                }
                char c = read;
                if (read == 13) {
                    int read2 = this.internalReader.read();
                    if (read2 == 10) {
                        int read3 = this.internalReader.read();
                        if (read3 == 46) {
                            int read4 = this.internalReader.read();
                            if (read4 != 46) {
                                this.internalReader.read();
                                this.eof = true;
                            } else {
                                char[] cArr2 = this.internalBuffer;
                                int i2 = this.pos - 1;
                                this.pos = i2;
                                cArr2[i2] = (char) read4;
                            }
                        } else {
                            this.internalReader.unread(read3);
                        }
                        this.pos -= LS_CHARS.length;
                        System.arraycopy(LS_CHARS, 0, this.internalBuffer, this.pos, LS_CHARS.length);
                        char[] cArr3 = this.internalBuffer;
                        int i3 = this.pos;
                        this.pos = i3 + 1;
                        c = cArr3[i3];
                    } else {
                        char[] cArr4 = this.internalBuffer;
                        int i4 = this.pos - 1;
                        this.pos = i4;
                        cArr4[i4] = (char) read2;
                        return 13;
                    }
                }
                return c;
            }
        }
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        boolean z;
        synchronized (((Reader) this).lock) {
            if (this.pos >= this.internalBuffer.length && !this.internalReader.ready()) {
                z = false;
            }
            z = true;
        }
        return z;
    }

    @Override // java.io.Reader
    public int read(char[] cArr) throws IOException {
        return read(cArr, 0, cArr.length);
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3;
        synchronized (((Reader) this).lock) {
            if (i2 < 1) {
                return 0;
            }
            int read = read();
            if (read == -1) {
                return -1;
            }
            int i4 = i2;
            int i5 = i;
            while (true) {
                i3 = i5 + 1;
                cArr[i5] = (char) read;
                i4--;
                if (i4 <= 0 || (read = read()) == -1) {
                    break;
                }
                i5 = i3;
            }
            return i3 - i;
        }
    }
}
