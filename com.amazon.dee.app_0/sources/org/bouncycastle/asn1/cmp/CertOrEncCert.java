package org.bouncycastle.asn1.cmp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.crmf.EncryptedKey;
import org.bouncycastle.asn1.crmf.EncryptedValue;
/* loaded from: classes4.dex */
public class CertOrEncCert extends ASN1Object implements ASN1Choice {
    private CMPCertificate certificate;
    private EncryptedKey encryptedKey;

    private CertOrEncCert(ASN1TaggedObject aSN1TaggedObject) {
        if (aSN1TaggedObject.getTagNo() == 0) {
            this.certificate = CMPCertificate.getInstance(aSN1TaggedObject.getObject());
        } else if (aSN1TaggedObject.getTagNo() != 1) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline101(aSN1TaggedObject, GeneratedOutlineSupport1.outline107("unknown tag: ")));
        } else {
            this.encryptedKey = EncryptedKey.getInstance(aSN1TaggedObject.getObject());
        }
    }

    public CertOrEncCert(CMPCertificate cMPCertificate) {
        if (cMPCertificate != null) {
            this.certificate = cMPCertificate;
            return;
        }
        throw new IllegalArgumentException("'certificate' cannot be null");
    }

    public CertOrEncCert(EncryptedKey encryptedKey) {
        if (encryptedKey != null) {
            this.encryptedKey = encryptedKey;
            return;
        }
        throw new IllegalArgumentException("'encryptedKey' cannot be null");
    }

    public CertOrEncCert(EncryptedValue encryptedValue) {
        if (encryptedValue != null) {
            this.encryptedKey = new EncryptedKey(encryptedValue);
            return;
        }
        throw new IllegalArgumentException("'encryptedCert' cannot be null");
    }

    public static CertOrEncCert getInstance(Object obj) {
        if (obj instanceof CertOrEncCert) {
            return (CertOrEncCert) obj;
        }
        if (!(obj instanceof ASN1TaggedObject)) {
            return null;
        }
        return new CertOrEncCert((ASN1TaggedObject) obj);
    }

    public CMPCertificate getCertificate() {
        return this.certificate;
    }

    public EncryptedKey getEncryptedCert() {
        return this.encryptedKey;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        CMPCertificate cMPCertificate = this.certificate;
        return cMPCertificate != null ? new DERTaggedObject(true, 0, cMPCertificate) : new DERTaggedObject(true, 1, this.encryptedKey);
    }
}
