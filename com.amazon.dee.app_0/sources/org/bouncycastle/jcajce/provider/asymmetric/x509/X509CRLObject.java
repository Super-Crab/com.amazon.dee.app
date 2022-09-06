package org.bouncycastle.jcajce.provider.asymmetric.x509;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.cert.CRLException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.IssuingDistributionPoint;
import org.bouncycastle.jcajce.util.JcaJceHelper;
/* loaded from: classes4.dex */
class X509CRLObject extends X509CRLImpl {
    private final Object cacheLock;
    private volatile int hashValue;
    private volatile boolean hashValueSet;
    private X509CRLInternal internalCRLValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public X509CRLObject(JcaJceHelper jcaJceHelper, CertificateList certificateList) throws CRLException {
        super(jcaJceHelper, certificateList, createSigAlgName(certificateList), createSigAlgParams(certificateList), isIndirectCRL(certificateList));
        this.cacheLock = new Object();
    }

    private static String createSigAlgName(CertificateList certificateList) throws CRLException {
        try {
            return X509SignatureUtil.getSignatureName(certificateList.getSignatureAlgorithm());
        } catch (Exception e) {
            throw new CRLException(GeneratedOutlineSupport1.outline68("CRL contents invalid: ", e));
        }
    }

    private static byte[] createSigAlgParams(CertificateList certificateList) throws CRLException {
        try {
            ASN1Encodable parameters = certificateList.getSignatureAlgorithm().getParameters();
            if (parameters != null) {
                return parameters.toASN1Primitive().getEncoded("DER");
            }
            return null;
        } catch (Exception e) {
            throw new CRLException(GeneratedOutlineSupport1.outline68("CRL contents invalid: ", e));
        }
    }

    private X509CRLInternal getInternalCRL() {
        byte[] bArr;
        X509CRLInternal x509CRLInternal;
        synchronized (this.cacheLock) {
            if (this.internalCRLValue != null) {
                return this.internalCRLValue;
            }
            try {
                bArr = getEncoded();
            } catch (CRLException unused) {
                bArr = null;
            }
            X509CRLInternal x509CRLInternal2 = new X509CRLInternal(this.bcHelper, this.c, this.sigAlgName, this.sigAlgParams, this.isIndirect, bArr);
            synchronized (this.cacheLock) {
                if (this.internalCRLValue == null) {
                    this.internalCRLValue = x509CRLInternal2;
                }
                x509CRLInternal = this.internalCRLValue;
            }
            return x509CRLInternal;
        }
    }

    private static boolean isIndirectCRL(CertificateList certificateList) throws CRLException {
        try {
            byte[] extensionOctets = X509CRLImpl.getExtensionOctets(certificateList, Extension.issuingDistributionPoint.getId());
            if (extensionOctets != null) {
                return IssuingDistributionPoint.getInstance(extensionOctets).isIndirectCRL();
            }
            return false;
        } catch (Exception e) {
            throw new ExtCRLException("Exception reading IssuingDistributionPoint", e);
        }
    }

    @Override // java.security.cert.X509CRL
    public boolean equals(Object obj) {
        DERBitString signature;
        if (this == obj) {
            return true;
        }
        if (obj instanceof X509CRLObject) {
            X509CRLObject x509CRLObject = (X509CRLObject) obj;
            if (!this.hashValueSet || !x509CRLObject.hashValueSet) {
                if ((this.internalCRLValue == null || x509CRLObject.internalCRLValue == null) && (signature = this.c.getSignature()) != null && !signature.equals((ASN1Primitive) x509CRLObject.c.getSignature())) {
                    return false;
                }
            } else if (this.hashValue != x509CRLObject.hashValue) {
                return false;
            }
        }
        return getInternalCRL().equals(obj);
    }

    @Override // java.security.cert.X509CRL
    public int hashCode() {
        if (!this.hashValueSet) {
            this.hashValue = getInternalCRL().hashCode();
            this.hashValueSet = true;
        }
        return this.hashValue;
    }
}
