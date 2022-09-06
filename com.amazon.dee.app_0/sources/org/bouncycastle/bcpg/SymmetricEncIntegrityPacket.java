package org.bouncycastle.bcpg;

import java.io.IOException;
/* loaded from: classes4.dex */
public class SymmetricEncIntegrityPacket extends InputStreamPacket {
    int version;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SymmetricEncIntegrityPacket(BCPGInputStream bCPGInputStream) throws IOException {
        super(bCPGInputStream);
        this.version = bCPGInputStream.read();
    }
}
