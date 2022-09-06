package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1OctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DEROctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.crypto.digests.SHA1Digest;
/* loaded from: classes.dex */
public class SubjectKeyIdentifier extends ASN1Object {
    private final byte[] keyidentifier;

    public SubjectKeyIdentifier(byte[] bArr) {
        this.keyidentifier = bArr;
    }

    public static SubjectKeyIdentifier createSHA1KeyIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        return new SubjectKeyIdentifier(subjectPublicKeyInfo);
    }

    public static SubjectKeyIdentifier createTruncatedSHA1KeyIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        byte[] digest = getDigest(subjectPublicKeyInfo);
        byte[] bArr = new byte[8];
        System.arraycopy(digest, digest.length - 8, bArr, 0, bArr.length);
        bArr[0] = (byte) (bArr[0] & 15);
        bArr[0] = (byte) (bArr[0] | 64);
        return new SubjectKeyIdentifier(bArr);
    }

    private static byte[] getDigest(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        SHA1Digest sHA1Digest = new SHA1Digest();
        byte[] bArr = new byte[sHA1Digest.getDigestSize()];
        byte[] bytes = subjectPublicKeyInfo.getPublicKeyData().getBytes();
        sHA1Digest.update(bytes, 0, bytes.length);
        sHA1Digest.doFinal(bArr, 0);
        return bArr;
    }

    public static SubjectKeyIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1OctetString.getInstance(aSN1TaggedObject, z));
    }

    public byte[] getKeyIdentifier() {
        return this.keyidentifier;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DEROctetString(this.keyidentifier);
    }

    public static SubjectKeyIdentifier getInstance(Object obj) {
        if (obj instanceof SubjectKeyIdentifier) {
            return (SubjectKeyIdentifier) obj;
        }
        if (obj == null) {
            return null;
        }
        return new SubjectKeyIdentifier(ASN1OctetString.getInstance(obj));
    }

    protected SubjectKeyIdentifier(ASN1OctetString aSN1OctetString) {
        this.keyidentifier = aSN1OctetString.getOctets();
    }

    public SubjectKeyIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.keyidentifier = getDigest(subjectPublicKeyInfo);
    }
}
