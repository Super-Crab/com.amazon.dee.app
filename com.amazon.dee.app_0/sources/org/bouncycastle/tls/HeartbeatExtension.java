package org.bouncycastle.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes5.dex */
public class HeartbeatExtension {
    protected short mode;

    public HeartbeatExtension(short s) {
        if (HeartbeatMode.isValid(s)) {
            this.mode = s;
            return;
        }
        throw new IllegalArgumentException("'mode' is not a valid HeartbeatMode value");
    }

    public static HeartbeatExtension parse(InputStream inputStream) throws IOException {
        short readUint8 = TlsUtils.readUint8(inputStream);
        if (HeartbeatMode.isValid(readUint8)) {
            return new HeartbeatExtension(readUint8);
        }
        throw new TlsFatalAlert((short) 47);
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(this.mode, outputStream);
    }

    public short getMode() {
        return this.mode;
    }
}
