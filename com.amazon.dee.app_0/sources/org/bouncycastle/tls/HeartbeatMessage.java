package org.bouncycastle.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes5.dex */
public class HeartbeatMessage {
    protected byte[] padding;
    protected byte[] payload;
    protected short type;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class PayloadBuffer extends ByteArrayOutputStream {
        PayloadBuffer() {
        }

        byte[] getPadding(int i) {
            return TlsUtils.copyOfRangeExact(((ByteArrayOutputStream) this).buf, i, ((ByteArrayOutputStream) this).count);
        }

        byte[] getPayload(int i) {
            if (i > ((ByteArrayOutputStream) this).count - 16) {
                return null;
            }
            return Arrays.copyOf(((ByteArrayOutputStream) this).buf, i);
        }
    }

    public HeartbeatMessage(short s, byte[] bArr, byte[] bArr2) {
        if (HeartbeatMessageType.isValid(s)) {
            if (bArr == null || bArr.length >= 65536) {
                throw new IllegalArgumentException("'payload' must have length < 2^16");
            }
            if (bArr2 == null || bArr2.length < 16) {
                throw new IllegalArgumentException("'padding' must have length >= 16");
            }
            this.type = s;
            this.payload = bArr;
            this.padding = bArr2;
            return;
        }
        throw new IllegalArgumentException("'type' is not a valid HeartbeatMessageType value");
    }

    public static HeartbeatMessage create(TlsContext tlsContext, short s, byte[] bArr) {
        return create(tlsContext, s, bArr, 16);
    }

    public static HeartbeatMessage create(TlsContext tlsContext, short s, byte[] bArr, int i) {
        return new HeartbeatMessage(s, bArr, tlsContext.getNonceGenerator().generateNonce(i));
    }

    public static HeartbeatMessage parse(InputStream inputStream) throws IOException {
        short readUint8 = TlsUtils.readUint8(inputStream);
        if (HeartbeatMessageType.isValid(readUint8)) {
            int readUint16 = TlsUtils.readUint16(inputStream);
            PayloadBuffer payloadBuffer = new PayloadBuffer();
            Streams.pipeAll(inputStream, payloadBuffer);
            byte[] payload = payloadBuffer.getPayload(readUint16);
            if (payload != null) {
                return new HeartbeatMessage(readUint8, payload, payloadBuffer.getPadding(readUint16));
            }
            return null;
        }
        throw new TlsFatalAlert((short) 47);
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(this.type, outputStream);
        TlsUtils.checkUint16(this.payload.length);
        TlsUtils.writeUint16(this.payload.length, outputStream);
        outputStream.write(this.payload);
        outputStream.write(this.padding);
    }

    public int getPaddingLength() {
        return this.padding.length;
    }

    public byte[] getPayload() {
        return this.payload;
    }

    public short getType() {
        return this.type;
    }
}
