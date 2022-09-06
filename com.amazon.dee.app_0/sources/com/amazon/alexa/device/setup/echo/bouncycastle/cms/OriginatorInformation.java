package com.amazon.alexa.device.setup.echo.bouncycastle.cms;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Set;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.OriginatorInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.Certificate;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.CertificateList;
import com.amazon.alexa.device.setup.echo.bouncycastle.cert.X509CRLHolder;
import com.amazon.alexa.device.setup.echo.bouncycastle.cert.X509CertificateHolder;
import com.amazon.alexa.device.setup.echo.bouncycastle.util.CollectionStore;
import com.amazon.alexa.device.setup.echo.bouncycastle.util.Store;
import java.util.ArrayList;
import java.util.Enumeration;
/* loaded from: classes.dex */
public class OriginatorInformation {
    private final OriginatorInfo originatorInfo;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OriginatorInformation(OriginatorInfo originatorInfo) {
        this.originatorInfo = originatorInfo;
    }

    public Store getCRLs() {
        ASN1Set cRLs = this.originatorInfo.getCRLs();
        if (cRLs != null) {
            ArrayList arrayList = new ArrayList(cRLs.size());
            Enumeration objects = cRLs.getObjects();
            while (objects.hasMoreElements()) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable) objects.nextElement()).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1Sequence) {
                    arrayList.add(new X509CRLHolder(CertificateList.getInstance(aSN1Primitive)));
                }
            }
            return new CollectionStore(arrayList);
        }
        return new CollectionStore(new ArrayList());
    }

    public Store getCertificates() {
        ASN1Set certificates = this.originatorInfo.getCertificates();
        if (certificates != null) {
            ArrayList arrayList = new ArrayList(certificates.size());
            Enumeration objects = certificates.getObjects();
            while (objects.hasMoreElements()) {
                ASN1Primitive aSN1Primitive = ((ASN1Encodable) objects.nextElement()).toASN1Primitive();
                if (aSN1Primitive instanceof ASN1Sequence) {
                    arrayList.add(new X509CertificateHolder(Certificate.getInstance(aSN1Primitive)));
                }
            }
            return new CollectionStore(arrayList);
        }
        return new CollectionStore(new ArrayList());
    }

    public OriginatorInfo toASN1Structure() {
        return this.originatorInfo;
    }
}
