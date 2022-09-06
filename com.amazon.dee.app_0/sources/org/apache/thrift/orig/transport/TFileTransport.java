package org.apache.thrift.orig.transport;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
/* loaded from: classes4.dex */
public class TFileTransport extends TTransport {
    protected TSeekableFile inputFile_;
    private boolean readOnly_;
    tailPolicy currentPolicy_ = tailPolicy.NOWAIT;
    protected OutputStream outputStream_ = null;
    Event currentEvent_ = null;
    InputStream inputStream_ = null;
    chunkState cs = null;
    private int readTimeout_ = 0;

    /* loaded from: classes4.dex */
    public static class Event {
        private byte[] buf_;
        private int navailable_ = 0;
        private int nread_ = 0;

        public Event(byte[] bArr) {
            this.buf_ = bArr;
        }

        public int emit(byte[] bArr, int i, int i2) {
            if (i2 == 0 || i2 > getRemaining()) {
                i2 = getRemaining();
            }
            if (i2 <= 0) {
                return i2;
            }
            System.arraycopy(this.buf_, this.nread_, bArr, i, i2);
            this.nread_ += i2;
            return i2;
        }

        public byte[] getBuf() {
            return this.buf_;
        }

        public int getRemaining() {
            return this.navailable_ - this.nread_;
        }

        public int getSize() {
            return this.buf_.length;
        }

        public void setAvailable(int i) {
            this.nread_ = 0;
            this.navailable_ = i;
        }
    }

    /* loaded from: classes4.dex */
    public enum tailPolicy {
        NOWAIT(0, 0),
        WAIT_FOREVER(500, -1);
        
        public final int retries_;
        public final int timeout_;

        tailPolicy(int i, int i2) {
            this.timeout_ = i;
            this.retries_ = i2;
        }
    }

    /* loaded from: classes4.dex */
    public static class truncableBufferedInputStream extends BufferedInputStream {
        public truncableBufferedInputStream(InputStream inputStream) {
            super(inputStream);
        }

        public void trunc() {
            ((BufferedInputStream) this).count = 0;
            ((BufferedInputStream) this).pos = 0;
        }

        public truncableBufferedInputStream(InputStream inputStream, int i) {
            super(inputStream, i);
        }
    }

    public TFileTransport(String str, boolean z) throws IOException {
        this.inputFile_ = null;
        this.readOnly_ = false;
        this.inputFile_ = new TStandardFile(str);
        this.readOnly_ = z;
    }

    private InputStream createInputStream() throws TTransportException {
        try {
            if (this.inputStream_ != null) {
                ((truncableBufferedInputStream) this.inputStream_).trunc();
                return this.inputStream_;
            }
            return new truncableBufferedInputStream(this.inputFile_.getInputStream());
        } catch (IOException e) {
            PrintStream printStream = System.err;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("createInputStream: ");
            outline107.append(e.getMessage());
            printStream.println(outline107.toString());
            throw new TTransportException(e.getMessage(), e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void main(java.lang.String[] r11) throws java.lang.Exception {
        /*
            int r0 = r11.length
            r1 = 0
            r2 = 1
            if (r0 < r2) goto L23
            r0 = r11[r1]
            java.lang.String r3 = "--help"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L23
            r0 = r11[r1]
            java.lang.String r3 = "-h"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L23
            r0 = r11[r1]
            java.lang.String r3 = "-?"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L26
        L23:
            printUsage()
        L26:
            int r0 = r11.length
            if (r0 <= r2) goto L40
            r0 = r11[r2]     // Catch: java.lang.Exception -> L30
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Exception -> L30
            goto L42
        L30:
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r3 = "Cannot parse "
            java.lang.StringBuilder r3 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r3)
            r4 = r11[r2]
            com.android.tools.r8.GeneratedOutlineSupport1.outline178(r3, r4, r0)
            printUsage()
        L40:
            r0 = 10
        L42:
            org.apache.thrift.orig.transport.TFileTransport r3 = new org.apache.thrift.orig.transport.TFileTransport
            r11 = r11[r1]
            r3.<init>(r11, r2)
            r3.open()
            java.io.PrintStream r11 = java.lang.System.out
            java.lang.String r4 = "NumChunks="
            java.lang.StringBuilder r4 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r4)
            int r5 = r3.getNumChunks()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r11.println(r4)
            java.util.Random r11 = new java.util.Random
            r11.<init>()
            r4 = r1
        L68:
            if (r4 >= r0) goto L9c
            r5 = 4096(0x1000, float:5.74E-42)
            byte[] r6 = new byte[r5]
            int r7 = r3.getNumChunks()
            int r7 = r7 - r2
            int r7 = r11.nextInt(r7)
            java.io.PrintStream r8 = java.lang.System.out
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Reading chunk "
            r9.append(r10)
            r9.append(r7)
            java.lang.String r9 = r9.toString()
            r8.println(r9)
            r3.seekToChunk(r7)
            r7 = r1
        L91:
            if (r7 >= r5) goto L99
            r3.read(r6, r1, r5)
            int r7 = r7 + 1
            goto L91
        L99:
            int r4 = r4 + 1
            goto L68
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.thrift.orig.transport.TFileTransport.main(java.lang.String[]):void");
    }

    private boolean performRecovery() throws TTransportException {
        int numChunks = getNumChunks();
        int chunkNum = this.cs.getChunkNum();
        if (chunkNum >= numChunks - 1) {
            return false;
        }
        seekToChunk(chunkNum + 1);
        return true;
    }

    private static void printUsage() {
        System.err.println("Usage: TFileTransport <filename> [num_chunks]");
        System.err.println("       (Opens and reads num_chunks chunks from file randomly)");
        System.exit(1);
    }

    private boolean readEvent() throws TTransportException {
        int i;
        byte[] bArr = new byte[4];
        do {
            int remaining = this.cs.getRemaining();
            if ((remaining < 4 && tailRead(this.inputStream_, bArr, 0, remaining, this.currentPolicy_) != remaining) || tailRead(this.inputStream_, bArr, 0, 4, this.currentPolicy_) != 4) {
                return false;
            }
            i = 0;
            for (int i2 = 3; i2 >= 0; i2--) {
                i |= (bArr[i2] & 255) << (i2 * 8);
            }
            if (i > this.cs.getRemaining()) {
                throw new TTransportException("FileTransport error: bad event size");
            }
        } while (i == 0);
        if (this.currentEvent_.getSize() < i) {
            this.currentEvent_ = new Event(new byte[i]);
        }
        if (tailRead(this.inputStream_, this.currentEvent_.getBuf(), 0, i, this.currentPolicy_) != i) {
            return false;
        }
        this.currentEvent_.setAvailable(i);
        return true;
    }

    private int tailRead(InputStream inputStream, byte[] bArr, int i, int i2, tailPolicy tailpolicy) throws TTransportException {
        int read;
        int i3 = i;
        int i4 = i2;
        while (true) {
            int i5 = 0;
            while (i4 > 0) {
                try {
                    read = inputStream.read(bArr, i3, i4);
                    if (read > 0) {
                        break;
                    } else if (read == -1) {
                        i5++;
                        if (tailpolicy.retries_ != -1 && tailpolicy.retries_ < i5) {
                            return i2 - i4;
                        }
                        int i6 = tailpolicy.timeout_;
                        if (i6 > 0) {
                            try {
                                Thread.sleep(i6);
                            } catch (InterruptedException unused) {
                            }
                        }
                    } else {
                        throw new TTransportException("Unexpected return from InputStream.read = " + read);
                    }
                } catch (IOException e) {
                    throw new TTransportException(e.getMessage(), e);
                }
            }
            return i2 - i4;
            i3 += read;
            i4 -= read;
            this.cs.skip(read);
        }
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void close() {
        TSeekableFile tSeekableFile = this.inputFile_;
        if (tSeekableFile != null) {
            try {
                tSeekableFile.close();
            } catch (IOException e) {
                PrintStream printStream = System.err;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WARNING: Error closing input file: ");
                outline107.append(e.getMessage());
                printStream.println(outline107.toString());
            }
            this.inputFile_ = null;
        }
        OutputStream outputStream = this.outputStream_;
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e2) {
                PrintStream printStream2 = System.err;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("WARNING: Error closing output stream: ");
                outline1072.append(e2.getMessage());
                printStream2.println(outline1072.toString());
            }
            this.outputStream_ = null;
        }
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void flush() throws TTransportException {
        throw new TTransportException("Not Supported");
    }

    public int getCurChunk() throws TTransportException {
        if (isOpen()) {
            return this.cs.getChunkNum();
        }
        throw new TTransportException(1, "Must open before getCurChunk");
    }

    public int getNumChunks() throws TTransportException {
        if (isOpen()) {
            try {
                long length = this.inputFile_.length();
                if (length != 0) {
                    return ((int) (length / this.cs.getChunkSize())) + 1;
                }
                return 0;
            } catch (IOException e) {
                throw new TTransportException(e.getMessage(), e);
            }
        }
        throw new TTransportException(1, "Must open before getNumChunks");
    }

    public tailPolicy getTailPolicy() {
        return this.currentPolicy_;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public boolean isOpen() {
        return this.inputStream_ != null && (this.readOnly_ || this.outputStream_ != null);
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void open() throws TTransportException {
        if (!isOpen()) {
            try {
                this.inputStream_ = createInputStream();
                this.cs = new chunkState();
                this.currentEvent_ = new Event(new byte[256]);
                if (this.readOnly_) {
                    return;
                }
                this.outputStream_ = new BufferedOutputStream(this.inputFile_.getOutputStream(), 8192);
                return;
            } catch (IOException e) {
                throw new TTransportException(1, e);
            }
        }
        throw new TTransportException(2);
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int read(byte[] bArr, int i, int i2) throws TTransportException {
        if (isOpen()) {
            if (this.currentEvent_.getRemaining() == 0 && !readEvent()) {
                return 0;
            }
            return this.currentEvent_.emit(bArr, i, i2);
        }
        throw new TTransportException(1, "Must open before reading");
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public int readAll(byte[] bArr, int i, int i2) throws TTransportException {
        int i3 = 0;
        while (i3 < i2) {
            int read = read(bArr, i + i3, i2 - i3);
            if (read < 0) {
                throw new TTransportException("Error in reading from file");
            }
            if (read == 0) {
                throw new TTransportException(4, "End of File reached");
            }
            i3 += read;
        }
        return i3;
    }

    public void seekToChunk(int i) throws TTransportException {
        chunkState chunkstate;
        boolean z = true;
        if (isOpen()) {
            int numChunks = getNumChunks();
            if (numChunks == 0) {
                return;
            }
            if (i < 0) {
                i += numChunks;
            }
            if (i < 0) {
                i = 0;
            }
            long j = 0;
            if (i < numChunks) {
                z = false;
            }
            if (z) {
                i--;
                try {
                    j = this.inputFile_.length();
                } catch (IOException e) {
                    throw new TTransportException(e.getMessage(), e);
                }
            }
            if (this.cs.getChunkSize() * i != this.cs.getOffset()) {
                try {
                    long j2 = i;
                    this.inputFile_.seek(this.cs.getChunkSize() * j2);
                    this.cs.seek(j2 * chunkstate.getChunkSize());
                    this.currentEvent_.setAvailable(0);
                    this.inputStream_ = createInputStream();
                } catch (IOException e2) {
                    PrintStream printStream = System.err;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("createInputStream: ");
                    outline107.append(e2.getMessage());
                    printStream.println(outline107.toString());
                    throw new TTransportException(GeneratedOutlineSupport1.outline37(e2, GeneratedOutlineSupport1.outline109("Seek to chunk ", i, " ")), e2);
                }
            }
            if (!z) {
                return;
            }
            tailPolicy tailPolicy2 = setTailPolicy(tailPolicy.WAIT_FOREVER);
            while (this.cs.getOffset() < j) {
                readEvent();
            }
            this.currentEvent_.setAvailable(0);
            setTailPolicy(tailPolicy2);
            return;
        }
        throw new TTransportException(1, "Must open before seeking");
    }

    public void seekToEnd() throws TTransportException {
        if (isOpen()) {
            seekToChunk(getNumChunks());
            return;
        }
        throw new TTransportException(1, "Must open before seeking");
    }

    public tailPolicy setTailPolicy(tailPolicy tailpolicy) {
        tailPolicy tailpolicy2 = this.currentPolicy_;
        this.currentPolicy_ = tailpolicy;
        return tailpolicy2;
    }

    @Override // org.apache.thrift.orig.transport.TTransport
    public void write(byte[] bArr, int i, int i2) throws TTransportException {
        throw new TTransportException("Not Supported");
    }

    /* loaded from: classes4.dex */
    public static class chunkState {
        public static final int DEFAULT_CHUNK_SIZE = 16777216;
        private int chunk_size_;
        private long offset_;

        public chunkState() {
            this.chunk_size_ = 16777216;
            this.offset_ = 0L;
        }

        public int getChunkNum() {
            return (int) (this.offset_ / this.chunk_size_);
        }

        public int getChunkSize() {
            return this.chunk_size_;
        }

        public long getOffset() {
            return this.offset_;
        }

        public int getRemaining() {
            int i = this.chunk_size_;
            return i - ((int) (this.offset_ % i));
        }

        public void seek(long j) {
            this.offset_ = j;
        }

        public void skip(int i) {
            this.offset_ += i;
        }

        public chunkState(int i) {
            this.chunk_size_ = 16777216;
            this.offset_ = 0L;
            this.chunk_size_ = i;
        }
    }

    public TFileTransport(TSeekableFile tSeekableFile, boolean z) {
        this.inputFile_ = null;
        this.readOnly_ = false;
        this.inputFile_ = tSeekableFile;
        this.readOnly_ = z;
    }
}
