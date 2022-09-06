package org.bouncycastle.bcpg;

import java.io.IOException;
import java.util.Date;
/* loaded from: classes4.dex */
public class PublicSubkeyPacket extends PublicKeyPacket {
    public PublicSubkeyPacket(int i, Date date, BCPGKey bCPGKey) {
        super(i, date, bCPGKey);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PublicSubkeyPacket(BCPGInputStream bCPGInputStream) throws IOException {
        super(bCPGInputStream);
    }

    @Override // org.bouncycastle.bcpg.PublicKeyPacket, org.bouncycastle.bcpg.ContainedPacket
    public void encode(BCPGOutputStream bCPGOutputStream) throws IOException {
        bCPGOutputStream.writePacket(14, getEncodedContents(), true);
    }
}
