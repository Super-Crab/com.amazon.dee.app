package org.bouncycastle.openpgp.operator;

import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPPublicKey;
/* loaded from: classes5.dex */
public interface PGPContentVerifierBuilder {
    PGPContentVerifier build(PGPPublicKey pGPPublicKey) throws PGPException;
}
