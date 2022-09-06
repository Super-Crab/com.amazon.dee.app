package org.bouncycastle.bcpg;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes4.dex */
public class BCPGInputStream extends InputStream implements PacketTags {
    InputStream in;
    boolean next = false;
    int nextB;

    /* loaded from: classes4.dex */
    private static class PartialInputStream extends InputStream {
        private int dataLength;
        private BCPGInputStream in;
        private boolean partial;

        PartialInputStream(BCPGInputStream bCPGInputStream, boolean z, int i) {
            this.in = bCPGInputStream;
            this.partial = z;
            this.dataLength = i;
        }

        private int loadDataLength() throws IOException {
            int read = this.in.read();
            if (read < 0) {
                return -1;
            }
            this.partial = false;
            if (read >= 192) {
                if (read <= 223) {
                    this.dataLength = this.in.read() + ((read - 192) << 8) + 192;
                    return this.dataLength;
                } else if (read == 255) {
                    read = (this.in.read() << 24) | (this.in.read() << 16) | (this.in.read() << 8) | this.in.read();
                } else {
                    this.partial = true;
                    read = 1 << (read & 31);
                }
            }
            this.dataLength = read;
            return this.dataLength;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            int available = this.in.available();
            int i = this.dataLength;
            if (available <= i || i < 0) {
                return available;
            }
            if (this.partial && i == 0) {
                return 1;
            }
            return this.dataLength;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x001b  */
        @Override // java.io.InputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int read() throws java.io.IOException {
            /*
                r2 = this;
            L0:
                int r0 = r2.dataLength
                if (r0 == 0) goto L1b
                org.bouncycastle.bcpg.BCPGInputStream r0 = r2.in
                int r0 = r0.read()
                if (r0 < 0) goto L13
                int r1 = r2.dataLength
                int r1 = r1 + (-1)
                r2.dataLength = r1
                return r0
            L13:
                java.io.EOFException r0 = new java.io.EOFException
                java.lang.String r1 = "premature end of stream in PartialInputStream"
                r0.<init>(r1)
                throw r0
            L1b:
                boolean r0 = r2.partial
                if (r0 == 0) goto L25
                int r0 = r2.loadDataLength()
                if (r0 >= 0) goto L0
            L25:
                r0 = -1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.bcpg.BCPGInputStream.PartialInputStream.read():int");
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            do {
                int i3 = this.dataLength;
                if (i3 != 0) {
                    if (i3 <= i2 && i3 >= 0) {
                        i2 = i3;
                    }
                    int read = this.in.read(bArr, i, i2);
                    if (read < 0) {
                        throw new EOFException("premature end of stream in PartialInputStream");
                    }
                    this.dataLength -= read;
                    return read;
                } else if (!this.partial) {
                    return -1;
                }
            } while (loadDataLength() >= 0);
            return -1;
        }
    }

    public BCPGInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    public int nextPacketTag() throws IOException {
        if (!this.next) {
            try {
                this.nextB = this.in.read();
            } catch (EOFException unused) {
                this.nextB = -1;
            }
            this.next = true;
        }
        int i = this.nextB;
        if (i < 0) {
            return i;
        }
        int i2 = i & 63;
        return (i & 64) == 0 ? i2 >> 2 : i2;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.next) {
            this.next = false;
            return this.nextB;
        }
        return this.in.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (!this.next) {
            return this.in.read(bArr, i, i2);
        }
        int i3 = this.nextB;
        if (i3 < 0) {
            return -1;
        }
        bArr[i] = (byte) i3;
        this.next = false;
        return 1;
    }

    public byte[] readAll() throws IOException {
        return Streams.readAll(this);
    }

    public void readFully(byte[] bArr) throws IOException {
        readFully(bArr, 0, bArr.length);
    }

    public void readFully(byte[] bArr, int i, int i2) throws IOException {
        if (Streams.readFully(this, bArr, i, i2) >= i2) {
            return;
        }
        throw new EOFException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0118  */
    /* JADX WARN: Type inference failed for: r2v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.bouncycastle.bcpg.Packet readPacket() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 374
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.bcpg.BCPGInputStream.readPacket():org.bouncycastle.bcpg.Packet");
    }
}
