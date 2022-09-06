package org.bouncycastle.openpgp.operator;

import org.bouncycastle.openpgp.PGPException;
/* loaded from: classes5.dex */
public interface PGPContentVerifierBuilderProvider {
    PGPContentVerifierBuilder get(int i, int i2) throws PGPException;
}
