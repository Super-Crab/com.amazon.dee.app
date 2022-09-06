package org.bouncycastle.cms.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.jcajce.util.AlgorithmParametersUtils;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class CMSUtils {
    private static final Set mqvAlgs = new HashSet();
    private static final Set ecAlgs = new HashSet();
    private static final Set gostAlgs = new HashSet();

    static {
        mqvAlgs.add(X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme);
        mqvAlgs.add(SECObjectIdentifiers.mqvSinglePass_sha224kdf_scheme);
        mqvAlgs.add(SECObjectIdentifiers.mqvSinglePass_sha256kdf_scheme);
        mqvAlgs.add(SECObjectIdentifiers.mqvSinglePass_sha384kdf_scheme);
        mqvAlgs.add(SECObjectIdentifiers.mqvSinglePass_sha512kdf_scheme);
        ecAlgs.add(X9ObjectIdentifiers.dhSinglePass_cofactorDH_sha1kdf_scheme);
        ecAlgs.add(X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme);
        ecAlgs.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha224kdf_scheme);
        ecAlgs.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha224kdf_scheme);
        ecAlgs.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha256kdf_scheme);
        ecAlgs.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha256kdf_scheme);
        ecAlgs.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha384kdf_scheme);
        ecAlgs.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha384kdf_scheme);
        ecAlgs.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha512kdf_scheme);
        ecAlgs.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha512kdf_scheme);
        gostAlgs.add(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_ESDH);
        gostAlgs.add(CryptoProObjectIdentifiers.gostR3410_2001);
        gostAlgs.add(RosstandartObjectIdentifiers.id_tc26_agreement_gost_3410_12_256);
        gostAlgs.add(RosstandartObjectIdentifiers.id_tc26_agreement_gost_3410_12_512);
        gostAlgs.add(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256);
        gostAlgs.add(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512);
    }

    CMSUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PrivateKey cleanPrivateKey(PrivateKey privateKey) {
        return privateKey instanceof AnnotatedPrivateKey ? cleanPrivateKey(((AnnotatedPrivateKey) privateKey).getKey()) : privateKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static EnvelopedDataHelper createContentHelper(String str) {
        return str != null ? new EnvelopedDataHelper(new NamedJcaJceExtHelper(str)) : new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static EnvelopedDataHelper createContentHelper(Provider provider) {
        return provider != null ? new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider)) : new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Encodable extractParameters(AlgorithmParameters algorithmParameters) throws CMSException {
        try {
            return AlgorithmParametersUtils.extractParameters(algorithmParameters);
        } catch (IOException e) {
            throw new CMSException(GeneratedOutlineSupport1.outline37(e, GeneratedOutlineSupport1.outline107("cannot extract parameters: ")), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static IssuerAndSerialNumber getIssuerAndSerialNumber(X509Certificate x509Certificate) throws CertificateEncodingException {
        return new IssuerAndSerialNumber(Certificate.getInstance(x509Certificate.getEncoded()).getIssuer(), x509Certificate.getSerialNumber());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] getSubjectKeyId(X509Certificate x509Certificate) {
        byte[] extensionValue = x509Certificate.getExtensionValue(Extension.subjectKeyIdentifier.getId());
        if (extensionValue != null) {
            return ASN1OctetString.getInstance(ASN1OctetString.getInstance(extensionValue).getOctets()).getOctets();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isEC(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return ecAlgs.contains(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isGOST(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return gostAlgs.contains(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isMQV(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return mqvAlgs.contains(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isRFC2631(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return aSN1ObjectIdentifier.equals((ASN1Primitive) PKCSObjectIdentifiers.id_alg_ESDH) || aSN1ObjectIdentifier.equals((ASN1Primitive) PKCSObjectIdentifiers.id_alg_SSDH);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void loadParameters(AlgorithmParameters algorithmParameters, ASN1Encodable aSN1Encodable) throws CMSException {
        try {
            AlgorithmParametersUtils.loadParameters(algorithmParameters, aSN1Encodable);
        } catch (IOException e) {
            throw new CMSException("error encoding algorithm parameters.", e);
        }
    }
}
