package org.bouncycastle.openpgp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import org.bouncycastle.apache.bzip2.CBZip2OutputStream;
import org.bouncycastle.bcpg.BCPGOutputStream;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
/* loaded from: classes5.dex */
public class PGPCompressedDataGenerator implements CompressionAlgorithmTags, StreamGenerator {
    private int algorithm;
    private int compression;
    private OutputStream dOut;
    private BCPGOutputStream pkOut;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class SafeCBZip2OutputStream extends CBZip2OutputStream {
        public SafeCBZip2OutputStream(OutputStream outputStream) throws IOException {
            super(outputStream);
        }

        @Override // org.bouncycastle.apache.bzip2.CBZip2OutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class SafeDeflaterOutputStream extends DeflaterOutputStream {
        public SafeDeflaterOutputStream(OutputStream outputStream, int i, boolean z) {
            super(outputStream, new Deflater(i, z));
        }

        @Override // java.util.zip.DeflaterOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            finish();
            ((DeflaterOutputStream) this).def.end();
        }
    }

    public PGPCompressedDataGenerator(int i) {
        this(i, -1);
    }

    public PGPCompressedDataGenerator(int i, int i2) {
        if (i == 0 || i == 1 || i == 2 || i == 3) {
            if (i2 != -1 && (i2 < 0 || i2 > 9)) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("unknown compression level: ", i2));
            }
            this.algorithm = i;
            this.compression = i2;
            return;
        }
        throw new IllegalArgumentException("unknown compression algorithm");
    }

    private void doOpen() throws IOException {
        OutputStream outputStream;
        this.pkOut.write(this.algorithm);
        int i = this.algorithm;
        if (i == 0) {
            outputStream = this.pkOut;
        } else if (i == 1) {
            outputStream = new SafeDeflaterOutputStream(this.pkOut, this.compression, true);
        } else if (i == 2) {
            outputStream = new SafeDeflaterOutputStream(this.pkOut, this.compression, false);
        } else if (i != 3) {
            throw new IllegalStateException();
        } else {
            outputStream = new SafeCBZip2OutputStream(this.pkOut);
        }
        this.dOut = outputStream;
    }

    @Override // org.bouncycastle.openpgp.StreamGenerator
    public void close() throws IOException {
        OutputStream outputStream = this.dOut;
        if (outputStream != null) {
            if (outputStream != this.pkOut) {
                outputStream.close();
            }
            this.dOut = null;
            this.pkOut.finish();
            this.pkOut.flush();
            this.pkOut = null;
        }
    }

    public OutputStream open(OutputStream outputStream) throws IOException {
        if (this.dOut == null) {
            this.pkOut = new BCPGOutputStream(outputStream, 8);
            doOpen();
            return new WrappedGeneratorStream(this.dOut, this);
        }
        throw new IllegalStateException("generator already in open state");
    }

    public OutputStream open(OutputStream outputStream, byte[] bArr) throws IOException, PGPException {
        if (this.dOut == null) {
            this.pkOut = new BCPGOutputStream(outputStream, 8, bArr);
            doOpen();
            return new WrappedGeneratorStream(this.dOut, this);
        }
        throw new IllegalStateException("generator already in open state");
    }
}
