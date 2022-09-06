package org.bouncycastle.jcajce.spec;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
/* loaded from: classes4.dex */
public class EdDSAParameterSpec implements AlgorithmParameterSpec {
    public static final String Ed25519 = "Ed25519";
    public static final String Ed448 = "Ed448";
    private final String curveName;

    public EdDSAParameterSpec(String str) {
        if (!str.equalsIgnoreCase(Ed25519)) {
            if (!str.equalsIgnoreCase(Ed448)) {
                if (!str.equals(EdECObjectIdentifiers.id_Ed25519.getId())) {
                    if (!str.equals(EdECObjectIdentifiers.id_Ed448.getId())) {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("unrecognized curve name: ", str));
                    }
                }
            }
            this.curveName = Ed448;
            return;
        }
        this.curveName = Ed25519;
    }

    public String getCurveName() {
        return this.curveName;
    }
}
