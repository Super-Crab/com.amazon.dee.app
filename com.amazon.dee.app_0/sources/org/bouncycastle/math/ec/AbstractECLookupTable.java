package org.bouncycastle.math.ec;
/* loaded from: classes4.dex */
public abstract class AbstractECLookupTable implements ECLookupTable {
    @Override // org.bouncycastle.math.ec.ECLookupTable
    public ECPoint lookupVar(int i) {
        return lookup(i);
    }
}
