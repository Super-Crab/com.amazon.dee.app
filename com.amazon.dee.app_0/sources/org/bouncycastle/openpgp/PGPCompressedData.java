package org.bouncycastle.openpgp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.bouncycastle.apache.bzip2.CBZip2InputStream;
import org.bouncycastle.bcpg.BCPGInputStream;
import org.bouncycastle.bcpg.CompressedDataPacket;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.Packet;
/* loaded from: classes5.dex */
public class PGPCompressedData implements CompressionAlgorithmTags {
    CompressedDataPacket data;

    public PGPCompressedData(InputStream inputStream) throws IOException {
        this(Util.createBCPGInputStream(inputStream, 8));
    }

    public PGPCompressedData(BCPGInputStream bCPGInputStream) throws IOException {
        Packet readPacket = bCPGInputStream.readPacket();
        if (readPacket instanceof CompressedDataPacket) {
            this.data = (CompressedDataPacket) readPacket;
            return;
        }
        throw new IOException("unexpected packet in stream: " + readPacket);
    }

    public PGPCompressedData(byte[] bArr) throws IOException {
        this(Util.createBCPGInputStream(new ByteArrayInputStream(bArr), 8));
    }

    public int getAlgorithm() {
        return this.data.getAlgorithm();
    }

    public InputStream getDataStream() throws PGPException {
        if (getAlgorithm() == 0) {
            return getInputStream();
        }
        if (getAlgorithm() == 1) {
            return new InflaterInputStream(getInputStream(), new Inflater(true)) { // from class: org.bouncycastle.openpgp.PGPCompressedData.1
                private boolean eof = false;

                @Override // java.util.zip.InflaterInputStream
                protected void fill() throws IOException {
                    if (!this.eof) {
                        InputStream inputStream = ((InflaterInputStream) this).in;
                        byte[] bArr = ((InflaterInputStream) this).buf;
                        ((InflaterInputStream) this).len = inputStream.read(bArr, 0, bArr.length);
                        if (((InflaterInputStream) this).len == -1) {
                            ((InflaterInputStream) this).buf[0] = 0;
                            ((InflaterInputStream) this).len = 1;
                            this.eof = true;
                        }
                        ((InflaterInputStream) this).inf.setInput(((InflaterInputStream) this).buf, 0, ((InflaterInputStream) this).len);
                        return;
                    }
                    throw new EOFException("Unexpected end of ZIP input stream");
                }
            };
        }
        if (getAlgorithm() == 2) {
            return new InflaterInputStream(getInputStream()) { // from class: org.bouncycastle.openpgp.PGPCompressedData.2
                private boolean eof = false;

                @Override // java.util.zip.InflaterInputStream
                protected void fill() throws IOException {
                    if (!this.eof) {
                        InputStream inputStream = ((InflaterInputStream) this).in;
                        byte[] bArr = ((InflaterInputStream) this).buf;
                        ((InflaterInputStream) this).len = inputStream.read(bArr, 0, bArr.length);
                        if (((InflaterInputStream) this).len == -1) {
                            ((InflaterInputStream) this).buf[0] = 0;
                            ((InflaterInputStream) this).len = 1;
                            this.eof = true;
                        }
                        ((InflaterInputStream) this).inf.setInput(((InflaterInputStream) this).buf, 0, ((InflaterInputStream) this).len);
                        return;
                    }
                    throw new EOFException("Unexpected end of ZIP input stream");
                }
            };
        }
        if (getAlgorithm() == 3) {
            try {
                return new CBZip2InputStream(getInputStream());
            } catch (IOException e) {
                throw new PGPException(GeneratedOutlineSupport1.outline65("I/O problem with stream: ", e), e);
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("can't recognise compression algorithm: ");
        outline107.append(getAlgorithm());
        throw new PGPException(outline107.toString());
    }

    public InputStream getInputStream() {
        return this.data.getInputStream();
    }
}
