package org.bouncycastle.bcpg;

import java.io.IOException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class LiteralDataPacket extends InputStreamPacket {
    byte[] fileName;
    int format;
    long modDate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LiteralDataPacket(BCPGInputStream bCPGInputStream) throws IOException {
        super(bCPGInputStream);
        this.format = bCPGInputStream.read();
        this.fileName = new byte[bCPGInputStream.read()];
        for (int i = 0; i != this.fileName.length; i++) {
            int read = bCPGInputStream.read();
            if (read < 0) {
                throw new IOException("literal data truncated in header");
            }
            this.fileName[i] = (byte) read;
        }
        this.modDate = (bCPGInputStream.read() << 24) | (bCPGInputStream.read() << 16) | (bCPGInputStream.read() << 8) | bCPGInputStream.read();
        if (this.modDate >= 0) {
            return;
        }
        throw new IOException("literal data truncated in header");
    }

    public String getFileName() {
        return Strings.fromUTF8ByteArray(this.fileName);
    }

    public int getFormat() {
        return this.format;
    }

    public long getModificationTime() {
        return this.modDate * 1000;
    }

    public byte[] getRawFileName() {
        return Arrays.clone(this.fileName);
    }
}
