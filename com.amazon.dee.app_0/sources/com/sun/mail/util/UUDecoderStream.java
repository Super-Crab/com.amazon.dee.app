package com.sun.mail.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import okio.Utf8;
import org.apache.commons.net.telnet.TelnetCommand;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes3.dex */
public class UUDecoderStream extends FilterInputStream {
    private byte[] buffer;
    private int bufsize;
    private boolean gotEnd;
    private boolean gotPrefix;
    private boolean ignoreErrors;
    private boolean ignoreMissingBeginEnd;
    private int index;
    private LineInputStream lin;
    private int mode;
    private String name;
    private String readAhead;

    public UUDecoderStream(InputStream inputStream) {
        super(inputStream);
        this.buffer = new byte[45];
        this.bufsize = 0;
        this.index = 0;
        this.gotPrefix = false;
        this.gotEnd = false;
        this.lin = new LineInputStream(inputStream);
        this.ignoreErrors = PropUtil.getBooleanSystemProperty("mail.mime.uudecode.ignoreerrors", false);
        this.ignoreMissingBeginEnd = PropUtil.getBooleanSystemProperty("mail.mime.uudecode.ignoremissingbeginend", false);
    }

    private boolean decode() throws IOException {
        if (this.gotEnd) {
            return false;
        }
        this.bufsize = 0;
        while (true) {
            String str = this.readAhead;
            if (str != null) {
                this.readAhead = null;
            } else {
                str = this.lin.readLine();
            }
            if (str == null) {
                if (this.ignoreMissingBeginEnd) {
                    this.gotEnd = true;
                    return false;
                }
                throw new DecodingException("UUDecoder: Missing end at EOF");
            } else if (str.equals("end")) {
                this.gotEnd = true;
                return false;
            } else if (str.length() != 0) {
                char charAt = str.charAt(0);
                if (charAt >= ' ') {
                    int i = (charAt - ' ') & 63;
                    if (i == 0) {
                        String readLine = this.lin.readLine();
                        if ((readLine != null && readLine.equals("end")) || this.ignoreMissingBeginEnd) {
                            this.gotEnd = true;
                            return false;
                        }
                        throw new DecodingException("UUDecoder: Missing End after count 0 line");
                    } else if (str.length() < (((i * 8) + 5) / 6) + 1) {
                        if (!this.ignoreErrors) {
                            throw new DecodingException("UUDecoder: Short buffer error");
                        }
                    } else {
                        int i2 = 1;
                        while (this.bufsize < i) {
                            int i3 = i2 + 1;
                            int i4 = i3 + 1;
                            byte charAt2 = (byte) ((str.charAt(i3) - Chars.SPACE) & 63);
                            byte[] bArr = this.buffer;
                            int i5 = this.bufsize;
                            this.bufsize = i5 + 1;
                            bArr[i5] = (byte) (((((byte) ((str.charAt(i2) - Chars.SPACE) & 63)) << 2) & TelnetCommand.WONT) | ((charAt2 >>> 4) & 3));
                            if (this.bufsize < i) {
                                i2 = i4 + 1;
                                byte charAt3 = (byte) ((str.charAt(i4) - Chars.SPACE) & 63);
                                byte[] bArr2 = this.buffer;
                                int i6 = this.bufsize;
                                this.bufsize = i6 + 1;
                                bArr2[i6] = (byte) (((charAt2 << 4) & 240) | ((charAt3 >>> 2) & 15));
                                charAt2 = charAt3;
                            } else {
                                i2 = i4;
                            }
                            if (this.bufsize < i) {
                                byte[] bArr3 = this.buffer;
                                int i7 = this.bufsize;
                                this.bufsize = i7 + 1;
                                bArr3[i7] = (byte) ((((byte) ((str.charAt(i2) - Chars.SPACE) & 63)) & Utf8.REPLACEMENT_BYTE) | ((charAt2 << 6) & 192));
                                i2++;
                            }
                        }
                        return true;
                    }
                } else if (!this.ignoreErrors) {
                    throw new DecodingException("UUDecoder: Buffer format error");
                }
            }
        }
    }

    private void readPrefix() throws IOException {
        String readLine;
        int charAt;
        if (this.gotPrefix) {
            return;
        }
        this.mode = 438;
        this.name = "encoder.buf";
        while (true) {
            readLine = this.lin.readLine();
            if (readLine == null) {
                if (this.ignoreMissingBeginEnd) {
                    this.gotPrefix = true;
                    this.gotEnd = true;
                    return;
                }
                throw new DecodingException("UUDecoder: Missing begin");
            } else if (readLine.regionMatches(false, 0, "begin", 0, 5)) {
                try {
                    this.mode = Integer.parseInt(readLine.substring(6, 9));
                } catch (NumberFormatException e) {
                    if (!this.ignoreErrors) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UUDecoder: Error in mode: ");
                        outline107.append(e.toString());
                        throw new DecodingException(outline107.toString());
                    }
                }
                if (readLine.length() > 10) {
                    this.name = readLine.substring(10);
                } else if (!this.ignoreErrors) {
                    throw new DecodingException(GeneratedOutlineSupport1.outline72("UUDecoder: Missing name: ", readLine));
                }
                this.gotPrefix = true;
                return;
            } else if (!this.ignoreMissingBeginEnd || readLine.length() == 0 || ((charAt = ((((readLine.charAt(0) - ' ') & 63) * 8) + 5) / 6) != 0 && readLine.length() < charAt + 1)) {
            }
        }
        this.readAhead = readLine;
        this.gotPrefix = true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return (this.bufsize - this.index) + ((((FilterInputStream) this).in.available() * 3) / 4);
    }

    public int getMode() throws IOException {
        readPrefix();
        return this.mode;
    }

    public String getName() throws IOException {
        readPrefix();
        return this.name;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.index >= this.bufsize) {
            readPrefix();
            if (!decode()) {
                return -1;
            }
            this.index = 0;
        }
        byte[] bArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int read = read();
            if (read == -1) {
                if (i3 != 0) {
                    return i3;
                }
                return -1;
            }
            bArr[i + i3] = (byte) read;
            i3++;
        }
        return i3;
    }

    public UUDecoderStream(InputStream inputStream, boolean z, boolean z2) {
        super(inputStream);
        this.buffer = new byte[45];
        this.bufsize = 0;
        this.index = 0;
        this.gotPrefix = false;
        this.gotEnd = false;
        this.lin = new LineInputStream(inputStream);
        this.ignoreErrors = z;
        this.ignoreMissingBeginEnd = z2;
    }
}
