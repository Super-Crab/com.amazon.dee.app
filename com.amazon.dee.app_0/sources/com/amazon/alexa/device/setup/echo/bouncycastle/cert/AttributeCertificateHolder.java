package com.amazon.alexa.device.setup.echo.bouncycastle.cert;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Integer;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500.X500Name;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.GeneralName;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.GeneralNames;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.Holder;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.IssuerSerial;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.ObjectDigestInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.DigestCalculator;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.DigestCalculatorProvider;
import com.amazon.alexa.device.setup.echo.bouncycastle.util.Arrays;
import com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class AttributeCertificateHolder implements Selector {
    private static DigestCalculatorProvider digestCalculatorProvider;
    final Holder holder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AttributeCertificateHolder(ASN1Sequence aSN1Sequence) {
        this.holder = Holder.getInstance(aSN1Sequence);
    }

    private GeneralNames generateGeneralNames(X500Name x500Name) {
        return new GeneralNames(new GeneralName(x500Name));
    }

    private X500Name[] getPrincipals(GeneralName[] generalNameArr) {
        ArrayList arrayList = new ArrayList(generalNameArr.length);
        for (int i = 0; i != generalNameArr.length; i++) {
            if (generalNameArr[i].getTagNo() == 4) {
                arrayList.add(X500Name.getInstance(generalNameArr[i].getName()));
            }
        }
        return (X500Name[]) arrayList.toArray(new X500Name[arrayList.size()]);
    }

    private boolean matchesDN(X500Name x500Name, GeneralNames generalNames) {
        GeneralName[] names = generalNames.getNames();
        for (int i = 0; i != names.length; i++) {
            GeneralName generalName = names[i];
            if (generalName.getTagNo() == 4 && X500Name.getInstance(generalName.getName()).equals(x500Name)) {
                return true;
            }
        }
        return false;
    }

    public static void setDigestCalculatorProvider(DigestCalculatorProvider digestCalculatorProvider2) {
        digestCalculatorProvider = digestCalculatorProvider2;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector
    public Object clone() {
        return new AttributeCertificateHolder((ASN1Sequence) this.holder.toASN1Object());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeCertificateHolder) {
            return this.holder.equals(((AttributeCertificateHolder) obj).holder);
        }
        return false;
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        if (this.holder.getObjectDigestInfo() != null) {
            return this.holder.getObjectDigestInfo().getDigestAlgorithm();
        }
        return null;
    }

    public int getDigestedObjectType() {
        if (this.holder.getObjectDigestInfo() != null) {
            return this.holder.getObjectDigestInfo().getDigestedObjectType().getValue().intValue();
        }
        return -1;
    }

    public X500Name[] getEntityNames() {
        if (this.holder.getEntityName() != null) {
            return getPrincipals(this.holder.getEntityName().getNames());
        }
        return null;
    }

    public X500Name[] getIssuer() {
        if (this.holder.getBaseCertificateID() != null) {
            return getPrincipals(this.holder.getBaseCertificateID().getIssuer().getNames());
        }
        return null;
    }

    public byte[] getObjectDigest() {
        if (this.holder.getObjectDigestInfo() != null) {
            return this.holder.getObjectDigestInfo().getObjectDigest().getBytes();
        }
        return null;
    }

    public ASN1ObjectIdentifier getOtherObjectTypeID() {
        if (this.holder.getObjectDigestInfo() != null) {
            new ASN1ObjectIdentifier(this.holder.getObjectDigestInfo().getOtherObjectTypeID().getId());
            return null;
        }
        return null;
    }

    public BigInteger getSerialNumber() {
        if (this.holder.getBaseCertificateID() != null) {
            return this.holder.getBaseCertificateID().getSerial().getValue();
        }
        return null;
    }

    public int hashCode() {
        return this.holder.hashCode();
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector
    public boolean match(Object obj) {
        if (!(obj instanceof X509CertificateHolder)) {
            return false;
        }
        X509CertificateHolder x509CertificateHolder = (X509CertificateHolder) obj;
        if (this.holder.getBaseCertificateID() != null) {
            return this.holder.getBaseCertificateID().getSerial().getValue().equals(x509CertificateHolder.getSerialNumber()) && matchesDN(x509CertificateHolder.getIssuer(), this.holder.getBaseCertificateID().getIssuer());
        } else if (this.holder.getEntityName() != null && matchesDN(x509CertificateHolder.getSubject(), this.holder.getEntityName())) {
            return true;
        } else {
            if (this.holder.getObjectDigestInfo() != null) {
                try {
                    DigestCalculator digestCalculator = digestCalculatorProvider.get(this.holder.getObjectDigestInfo().getDigestAlgorithm());
                    OutputStream outputStream = digestCalculator.getOutputStream();
                    int digestedObjectType = getDigestedObjectType();
                    if (digestedObjectType == 0) {
                        outputStream.write(x509CertificateHolder.getSubjectPublicKeyInfo().getEncoded());
                    } else if (digestedObjectType == 1) {
                        outputStream.write(x509CertificateHolder.getEncoded());
                    }
                    outputStream.close();
                    if (!Arrays.areEqual(digestCalculator.getDigest(), getObjectDigest())) {
                    }
                } catch (Exception unused) {
                }
            }
            return false;
        }
    }

    public AttributeCertificateHolder(X500Name x500Name, BigInteger bigInteger) {
        this.holder = new Holder(new IssuerSerial(new GeneralNames(new GeneralName(x500Name)), new ASN1Integer(bigInteger)));
    }

    public AttributeCertificateHolder(X509CertificateHolder x509CertificateHolder) {
        this.holder = new Holder(new IssuerSerial(generateGeneralNames(x509CertificateHolder.getIssuer()), new ASN1Integer(x509CertificateHolder.getSerialNumber())));
    }

    public AttributeCertificateHolder(X500Name x500Name) {
        this.holder = new Holder(generateGeneralNames(x500Name));
    }

    public AttributeCertificateHolder(int i, ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1ObjectIdentifier aSN1ObjectIdentifier2, byte[] bArr) {
        this.holder = new Holder(new ObjectDigestInfo(i, aSN1ObjectIdentifier2, new AlgorithmIdentifier(aSN1ObjectIdentifier), Arrays.clone(bArr)));
    }
}
