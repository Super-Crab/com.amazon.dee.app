package org.bouncycastle.jsse;

import org.bouncycastle.tls.TlsUtils;
/* loaded from: classes4.dex */
public abstract class BCSNIMatcher {
    private final int nameType;

    /* JADX INFO: Access modifiers changed from: protected */
    public BCSNIMatcher(int i) {
        if (TlsUtils.isValidUint8(i)) {
            this.nameType = i;
            return;
        }
        throw new IllegalArgumentException("'nameType' should be between 0 and 255");
    }

    public final int getType() {
        return this.nameType;
    }

    public abstract boolean matches(BCSNIServerName bCSNIServerName);
}
