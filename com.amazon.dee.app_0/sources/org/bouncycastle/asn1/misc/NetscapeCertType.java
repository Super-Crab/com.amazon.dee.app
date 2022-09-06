package org.bouncycastle.asn1.misc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.DERBitString;
/* loaded from: classes4.dex */
public class NetscapeCertType extends DERBitString {
    public static final int objectSigning = 16;
    public static final int objectSigningCA = 1;
    public static final int reserved = 8;
    public static final int smime = 32;
    public static final int smimeCA = 2;
    public static final int sslCA = 4;
    public static final int sslClient = 128;
    public static final int sslServer = 64;

    public NetscapeCertType(int i) {
        super(ASN1BitString.getBytes(i), ASN1BitString.getPadBits(i));
    }

    public NetscapeCertType(DERBitString dERBitString) {
        super(dERBitString.getBytes(), dERBitString.getPadBits());
    }

    @Override // org.bouncycastle.asn1.ASN1BitString
    public String toString() {
        return GeneratedOutlineSupport1.outline32(this.data[0] & 255, GeneratedOutlineSupport1.outline107("NetscapeCertType: 0x"));
    }
}
