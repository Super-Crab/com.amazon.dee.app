package com.amazon.alexa.device.setup.echo.bouncycastle.cert;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x500.X500Name;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AttCertIssuer;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.GeneralName;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.GeneralNames;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.V2Form;
import com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class AttributeCertificateIssuer implements Selector {
    final ASN1Encodable form;

    public AttributeCertificateIssuer(AttCertIssuer attCertIssuer) {
        this.form = attCertIssuer.getIssuer();
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

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector
    public Object clone() {
        return new AttributeCertificateIssuer(AttCertIssuer.getInstance(this.form));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeCertificateIssuer) {
            return this.form.equals(((AttributeCertificateIssuer) obj).form);
        }
        return false;
    }

    public X500Name[] getNames() {
        GeneralNames generalNames;
        ASN1Encodable aSN1Encodable = this.form;
        if (aSN1Encodable instanceof V2Form) {
            generalNames = ((V2Form) aSN1Encodable).getIssuerName();
        } else {
            generalNames = (GeneralNames) aSN1Encodable;
        }
        GeneralName[] names = generalNames.getNames();
        ArrayList arrayList = new ArrayList(names.length);
        for (int i = 0; i != names.length; i++) {
            if (names[i].getTagNo() == 4) {
                arrayList.add(X500Name.getInstance(names[i].getName()));
            }
        }
        return (X500Name[]) arrayList.toArray(new X500Name[arrayList.size()]);
    }

    public int hashCode() {
        return this.form.hashCode();
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.util.Selector
    public boolean match(Object obj) {
        if (!(obj instanceof X509CertificateHolder)) {
            return false;
        }
        X509CertificateHolder x509CertificateHolder = (X509CertificateHolder) obj;
        ASN1Encodable aSN1Encodable = this.form;
        if (aSN1Encodable instanceof V2Form) {
            V2Form v2Form = (V2Form) aSN1Encodable;
            if (v2Form.getBaseCertificateID() != null) {
                return v2Form.getBaseCertificateID().getSerial().getValue().equals(x509CertificateHolder.getSerialNumber()) && matchesDN(x509CertificateHolder.getIssuer(), v2Form.getBaseCertificateID().getIssuer());
            }
            return matchesDN(x509CertificateHolder.getSubject(), v2Form.getIssuerName());
        }
        return matchesDN(x509CertificateHolder.getSubject(), (GeneralNames) aSN1Encodable);
    }

    public AttributeCertificateIssuer(X500Name x500Name) {
        this.form = new V2Form(new GeneralNames(new GeneralName(x500Name)));
    }
}
