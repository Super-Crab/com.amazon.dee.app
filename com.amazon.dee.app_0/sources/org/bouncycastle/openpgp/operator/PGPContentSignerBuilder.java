package org.bouncycastle.openpgp.operator;

import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPrivateKey;
/* loaded from: classes5.dex */
public interface PGPContentSignerBuilder {
    PGPContentSigner build(int i, PGPPrivateKey pGPPrivateKey) throws PGPException;
}
