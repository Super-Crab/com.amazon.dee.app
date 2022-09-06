package org.bouncycastle.asn1.misc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.DERIA5String;
/* loaded from: classes4.dex */
public class NetscapeRevocationURL extends DERIA5String {
    public NetscapeRevocationURL(DERIA5String dERIA5String) {
        super(dERIA5String.getString());
    }

    @Override // org.bouncycastle.asn1.DERIA5String
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NetscapeRevocationURL: ");
        outline107.append(getString());
        return outline107.toString();
    }
}
