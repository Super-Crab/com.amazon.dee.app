package org.bouncycastle.bcpg;

import org.bouncycastle.util.Encodable;
/* loaded from: classes4.dex */
public interface BCPGKey extends Encodable {
    @Override // org.bouncycastle.util.Encodable
    byte[] getEncoded();

    String getFormat();
}
