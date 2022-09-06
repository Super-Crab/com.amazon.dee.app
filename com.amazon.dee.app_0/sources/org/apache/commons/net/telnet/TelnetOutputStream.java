package org.apache.commons.net.telnet;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes4.dex */
final class TelnetOutputStream extends OutputStream {
    private TelnetClient __client;
    private boolean __convertCRtoCRLF = true;
    private boolean __lastWasCR = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TelnetOutputStream(TelnetClient telnetClient) {
        this.__client = telnetClient;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.__client._closeOutputStream();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.__client._flushOutputStream();
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        synchronized (this.__client) {
            int i2 = i & 255;
            if (this.__client._requestedWont(TelnetOption.BINARY)) {
                if (this.__lastWasCR) {
                    if (this.__convertCRtoCRLF) {
                        this.__client._sendByte(10);
                        if (i2 == 10) {
                            this.__lastWasCR = false;
                            return;
                        }
                    } else if (i2 != 10) {
                        this.__client._sendByte(0);
                    }
                }
                this.__lastWasCR = false;
                if (i2 == 13) {
                    this.__client._sendByte(13);
                    this.__lastWasCR = true;
                } else if (i2 != 255) {
                    this.__client._sendByte(i2);
                } else {
                    this.__client._sendByte(255);
                    this.__client._sendByte(255);
                }
            } else if (i2 == 255) {
                this.__client._sendByte(i2);
                this.__client._sendByte(255);
            } else {
                this.__client._sendByte(i2);
            }
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        synchronized (this.__client) {
            while (true) {
                int i3 = i2 - 1;
                if (i2 > 0) {
                    int i4 = i + 1;
                    write(bArr[i]);
                    i = i4;
                    i2 = i3;
                }
            }
        }
    }
}
