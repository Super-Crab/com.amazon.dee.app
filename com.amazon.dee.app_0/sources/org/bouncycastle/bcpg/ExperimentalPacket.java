package org.bouncycastle.bcpg;

import java.io.IOException;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class ExperimentalPacket extends ContainedPacket implements PublicKeyAlgorithmTags {
    private byte[] contents;
    private int tag;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExperimentalPacket(int i, BCPGInputStream bCPGInputStream) throws IOException {
        this.tag = i;
        this.contents = bCPGInputStream.readAll();
    }

    @Override // org.bouncycastle.bcpg.ContainedPacket
    public void encode(BCPGOutputStream bCPGOutputStream) throws IOException {
        bCPGOutputStream.writePacket(this.tag, this.contents, true);
    }

    public byte[] getContents() {
        return Arrays.clone(this.contents);
    }

    public int getTag() {
        return this.tag;
    }
}
