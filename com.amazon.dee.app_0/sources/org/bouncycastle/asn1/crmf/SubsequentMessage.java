package org.bouncycastle.asn1.crmf;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1Integer;
/* loaded from: classes4.dex */
public class SubsequentMessage extends ASN1Integer {
    public static final SubsequentMessage encrCert = new SubsequentMessage(0);
    public static final SubsequentMessage challengeResp = new SubsequentMessage(1);

    private SubsequentMessage(int i) {
        super(i);
    }

    public static SubsequentMessage valueOf(int i) {
        if (i == 0) {
            return encrCert;
        }
        if (i != 1) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("unknown value: ", i));
        }
        return challengeResp;
    }
}
