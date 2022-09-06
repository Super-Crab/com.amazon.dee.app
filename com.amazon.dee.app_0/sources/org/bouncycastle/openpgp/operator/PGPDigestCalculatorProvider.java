package org.bouncycastle.openpgp.operator;

import org.bouncycastle.openpgp.PGPException;
/* loaded from: classes5.dex */
public interface PGPDigestCalculatorProvider {
    PGPDigestCalculator get(int i) throws PGPException;
}
