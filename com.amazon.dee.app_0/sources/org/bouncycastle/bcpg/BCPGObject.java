package org.bouncycastle.bcpg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.bouncycastle.util.Encodable;
/* loaded from: classes4.dex */
public abstract class BCPGObject implements Encodable {
    public abstract void encode(BCPGOutputStream bCPGOutputStream) throws IOException;

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BCPGOutputStream bCPGOutputStream = new BCPGOutputStream(byteArrayOutputStream);
        bCPGOutputStream.writeObject(this);
        bCPGOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
