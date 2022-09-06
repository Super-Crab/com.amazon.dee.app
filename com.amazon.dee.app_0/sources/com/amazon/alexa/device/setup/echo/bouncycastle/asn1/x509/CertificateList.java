package com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1TaggedObject;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERBitString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500.X500Name;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.TBSCertList;
import java.util.Enumeration;
/* loaded from: classes.dex */
public class CertificateList extends ASN1Object {
    DERBitString sig;
    AlgorithmIdentifier sigAlgId;
    TBSCertList tbsCertList;

    public CertificateList(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 3) {
            this.tbsCertList = TBSCertList.getInstance(aSN1Sequence.getObjectAt(0));
            this.sigAlgId = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
            this.sig = DERBitString.getInstance(aSN1Sequence.getObjectAt(2));
            return;
        }
        throw new IllegalArgumentException("sequence wrong size for CertificateList");
    }

    public static CertificateList getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public X500Name getIssuer() {
        return this.tbsCertList.getIssuer();
    }

    public Time getNextUpdate() {
        return this.tbsCertList.getNextUpdate();
    }

    public Enumeration getRevokedCertificateEnumeration() {
        return this.tbsCertList.getRevokedCertificateEnumeration();
    }

    public TBSCertList.CRLEntry[] getRevokedCertificates() {
        return this.tbsCertList.getRevokedCertificates();
    }

    public DERBitString getSignature() {
        return this.sig;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.sigAlgId;
    }

    public TBSCertList getTBSCertList() {
        return this.tbsCertList;
    }

    public Time getThisUpdate() {
        return this.tbsCertList.getThisUpdate();
    }

    public int getVersionNumber() {
        return this.tbsCertList.getVersionNumber();
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Object, com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.tbsCertList);
        aSN1EncodableVector.add(this.sigAlgId);
        aSN1EncodableVector.add(this.sig);
        return new DERSequence(aSN1EncodableVector);
    }

    public static CertificateList getInstance(Object obj) {
        if (obj instanceof CertificateList) {
            return (CertificateList) obj;
        }
        if (obj == null) {
            return null;
        }
        return new CertificateList(ASN1Sequence.getInstance(obj));
    }
}
