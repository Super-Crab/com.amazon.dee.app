package org.bouncycastle.its.asn1;

import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1Primitive;
/* loaded from: classes4.dex */
public class HashAlgorithm {
    public static final HashAlgorithm sha256 = new HashAlgorithm(0);
    public static final HashAlgorithm sha384 = new HashAlgorithm(1);
    private final ASN1Enumerated enumerated;

    protected HashAlgorithm(int i) {
        this.enumerated = new ASN1Enumerated(i);
    }

    private HashAlgorithm(ASN1Enumerated aSN1Enumerated) {
        this.enumerated = aSN1Enumerated;
    }

    public HashAlgorithm getInstance(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj instanceof HashAlgorithm ? (HashAlgorithm) obj : new HashAlgorithm(ASN1Enumerated.getInstance(obj));
    }

    public ASN1Primitive toASN1Primitive() {
        return this.enumerated;
    }
}
