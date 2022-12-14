package org.bouncycastle.bcpg;

import java.io.IOException;
/* loaded from: classes4.dex */
public class CompressedDataPacket extends InputStreamPacket {
    int algorithm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompressedDataPacket(BCPGInputStream bCPGInputStream) throws IOException {
        super(bCPGInputStream);
        this.algorithm = bCPGInputStream.read();
    }

    public int getAlgorithm() {
        return this.algorithm;
    }
}
