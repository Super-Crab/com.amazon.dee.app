package org.bouncycastle.jce.provider;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.misc.NetscapeCertType;
import org.bouncycastle.asn1.misc.NetscapeRevocationURL;
import org.bouncycastle.asn1.misc.VerisignCzagExtension;
import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.RFC4519Style;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes4.dex */
public class X509CertificateObject extends X509Certificate implements PKCS12BagAttributeCarrier {
    private PKCS12BagAttributeCarrier attrCarrier = new PKCS12BagAttributeCarrierImpl();
    private BasicConstraints basicConstraints;
    private Certificate c;
    private int hashValue;
    private boolean hashValueSet;
    private boolean[] keyUsage;

    public X509CertificateObject(Certificate certificate) throws CertificateParsingException {
        this.c = certificate;
        try {
            byte[] extensionBytes = getExtensionBytes("2.5.29.19");
            if (extensionBytes != null) {
                this.basicConstraints = BasicConstraints.getInstance(ASN1Primitive.fromByteArray(extensionBytes));
            }
            try {
                byte[] extensionBytes2 = getExtensionBytes("2.5.29.15");
                if (extensionBytes2 == null) {
                    this.keyUsage = null;
                    return;
                }
                DERBitString dERBitString = DERBitString.getInstance(ASN1Primitive.fromByteArray(extensionBytes2));
                byte[] bytes = dERBitString.getBytes();
                int length = (bytes.length * 8) - dERBitString.getPadBits();
                int i = 9;
                if (length >= 9) {
                    i = length;
                }
                this.keyUsage = new boolean[i];
                for (int i2 = 0; i2 != length; i2++) {
                    this.keyUsage[i2] = (bytes[i2 / 8] & (128 >>> (i2 % 8))) != 0;
                }
            } catch (Exception e) {
                throw new CertificateParsingException(GeneratedOutlineSupport1.outline68("cannot construct KeyUsage: ", e));
            }
        } catch (Exception e2) {
            throw new CertificateParsingException(GeneratedOutlineSupport1.outline68("cannot construct BasicConstraints: ", e2));
        }
    }

    private int calculateHashCode() {
        try {
            byte[] encoded = getEncoded();
            int i = 0;
            for (int i2 = 1; i2 < encoded.length; i2++) {
                i += encoded[i2] * i2;
            }
            return i;
        } catch (CertificateEncodingException unused) {
            return 0;
        }
    }

    private void checkSignature(PublicKey publicKey, Signature signature) throws CertificateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (isAlgIdEqual(this.c.getSignatureAlgorithm(), this.c.getTBSCertificate().getSignature())) {
            X509SignatureUtil.setSignatureParameters(signature, this.c.getSignatureAlgorithm().getParameters());
            signature.initVerify(publicKey);
            signature.update(getTBSCertificate());
            if (!signature.verify(getSignature())) {
                throw new SignatureException("certificate does not verify with supplied key");
            }
            return;
        }
        throw new CertificateException("signature algorithm in TBS cert not same as outer cert");
    }

    private static Collection getAlternativeNames(byte[] bArr) throws CertificateParsingException {
        String string;
        if (bArr == null) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            Enumeration objects = ASN1Sequence.getInstance(bArr).getObjects();
            while (objects.hasMoreElements()) {
                GeneralName generalName = GeneralName.getInstance(objects.nextElement());
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(Integers.valueOf(generalName.getTagNo()));
                switch (generalName.getTagNo()) {
                    case 0:
                    case 3:
                    case 5:
                        arrayList2.add(generalName.getEncoded());
                        break;
                    case 1:
                    case 2:
                    case 6:
                        string = ((ASN1String) generalName.getName()).getString();
                        arrayList2.add(string);
                        break;
                    case 4:
                        string = X500Name.getInstance(RFC4519Style.INSTANCE, generalName.getName()).toString();
                        arrayList2.add(string);
                        break;
                    case 7:
                        try {
                            string = InetAddress.getByAddress(ASN1OctetString.getInstance(generalName.getName()).getOctets()).getHostAddress();
                            arrayList2.add(string);
                            break;
                        } catch (UnknownHostException unused) {
                            break;
                        }
                    case 8:
                        string = ASN1ObjectIdentifier.getInstance(generalName.getName()).getId();
                        arrayList2.add(string);
                        break;
                    default:
                        throw new IOException("Bad tag number: " + generalName.getTagNo());
                }
                arrayList.add(Collections.unmodifiableList(arrayList2));
            }
            if (arrayList.size() != 0) {
                return Collections.unmodifiableCollection(arrayList);
            }
            return null;
        } catch (Exception e) {
            throw new CertificateParsingException(e.getMessage());
        }
    }

    private byte[] getExtensionBytes(String str) {
        Extension extension;
        Extensions extensions = this.c.getTBSCertificate().getExtensions();
        if (extensions == null || (extension = extensions.getExtension(new ASN1ObjectIdentifier(str))) == null) {
            return null;
        }
        return extension.getExtnValue().getOctets();
    }

    private boolean isAlgIdEqual(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        if (!algorithmIdentifier.getAlgorithm().equals((ASN1Primitive) algorithmIdentifier2.getAlgorithm())) {
            return false;
        }
        return algorithmIdentifier.getParameters() == null ? algorithmIdentifier2.getParameters() == null || algorithmIdentifier2.getParameters().equals(DERNull.INSTANCE) : algorithmIdentifier2.getParameters() == null ? algorithmIdentifier.getParameters() == null || algorithmIdentifier.getParameters().equals(DERNull.INSTANCE) : algorithmIdentifier.getParameters().equals(algorithmIdentifier2.getParameters());
    }

    @Override // java.security.cert.X509Certificate
    public void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException {
        checkValidity(new Date());
    }

    @Override // java.security.cert.X509Certificate
    public void checkValidity(Date date) throws CertificateExpiredException, CertificateNotYetValidException {
        if (date.getTime() > getNotAfter().getTime()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("certificate expired on ");
            outline107.append(this.c.getEndDate().getTime());
            throw new CertificateExpiredException(outline107.toString());
        } else if (date.getTime() >= getNotBefore().getTime()) {
        } else {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("certificate not valid till ");
            outline1072.append(this.c.getStartDate().getTime());
            throw new CertificateNotYetValidException(outline1072.toString());
        }
    }

    @Override // java.security.cert.Certificate
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof java.security.cert.Certificate)) {
            return false;
        }
        try {
            return Arrays.areEqual(getEncoded(), ((java.security.cert.Certificate) obj).getEncoded());
        } catch (CertificateEncodingException unused) {
            return false;
        }
    }

    @Override // org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.attrCarrier.getBagAttribute(aSN1ObjectIdentifier);
    }

    @Override // org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public Enumeration getBagAttributeKeys() {
        return this.attrCarrier.getBagAttributeKeys();
    }

    @Override // java.security.cert.X509Certificate
    public int getBasicConstraints() {
        BasicConstraints basicConstraints = this.basicConstraints;
        if (basicConstraints == null || !basicConstraints.isCA()) {
            return -1;
        }
        if (this.basicConstraints.getPathLenConstraint() != null) {
            return this.basicConstraints.getPathLenConstraint().intValue();
        }
        return Integer.MAX_VALUE;
    }

    @Override // java.security.cert.X509Extension
    public Set getCriticalExtensionOIDs() {
        if (getVersion() == 3) {
            HashSet hashSet = new HashSet();
            Extensions extensions = this.c.getTBSCertificate().getExtensions();
            if (extensions == null) {
                return null;
            }
            Enumeration oids = extensions.oids();
            while (oids.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) oids.nextElement();
                if (extensions.getExtension(aSN1ObjectIdentifier).isCritical()) {
                    hashSet.add(aSN1ObjectIdentifier.getId());
                }
            }
            return hashSet;
        }
        return null;
    }

    @Override // java.security.cert.Certificate
    public byte[] getEncoded() throws CertificateEncodingException {
        try {
            return this.c.getEncoded("DER");
        } catch (IOException e) {
            throw new CertificateEncodingException(e.toString());
        }
    }

    @Override // java.security.cert.X509Certificate
    public List getExtendedKeyUsage() throws CertificateParsingException {
        byte[] extensionBytes = getExtensionBytes("2.5.29.37");
        if (extensionBytes != null) {
            try {
                ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(extensionBytes).readObject();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i != aSN1Sequence.size(); i++) {
                    arrayList.add(((ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(i)).getId());
                }
                return Collections.unmodifiableList(arrayList);
            } catch (Exception unused) {
                throw new CertificateParsingException("error processing extended key usage extension");
            }
        }
        return null;
    }

    @Override // java.security.cert.X509Extension
    public byte[] getExtensionValue(String str) {
        Extension extension;
        Extensions extensions = this.c.getTBSCertificate().getExtensions();
        if (extensions == null || (extension = extensions.getExtension(new ASN1ObjectIdentifier(str))) == null) {
            return null;
        }
        try {
            return extension.getExtnValue().getEncoded();
        } catch (Exception e) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline42(e, GeneratedOutlineSupport1.outline107("error parsing ")));
        }
    }

    @Override // java.security.cert.X509Certificate
    public Collection getIssuerAlternativeNames() throws CertificateParsingException {
        return getAlternativeNames(getExtensionBytes(Extension.issuerAlternativeName.getId()));
    }

    @Override // java.security.cert.X509Certificate
    public Principal getIssuerDN() {
        return new X509Principal(this.c.getIssuer());
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getIssuerUniqueID() {
        DERBitString issuerUniqueId = this.c.getTBSCertificate().getIssuerUniqueId();
        if (issuerUniqueId != null) {
            byte[] bytes = issuerUniqueId.getBytes();
            boolean[] zArr = new boolean[(bytes.length * 8) - issuerUniqueId.getPadBits()];
            for (int i = 0; i != zArr.length; i++) {
                zArr[i] = (bytes[i / 8] & (128 >>> (i % 8))) != 0;
            }
            return zArr;
        }
        return null;
    }

    @Override // java.security.cert.X509Certificate
    public X500Principal getIssuerX500Principal() {
        try {
            return new X500Principal(this.c.getIssuer().getEncoded());
        } catch (IOException unused) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getKeyUsage() {
        return this.keyUsage;
    }

    @Override // java.security.cert.X509Extension
    public Set getNonCriticalExtensionOIDs() {
        if (getVersion() == 3) {
            HashSet hashSet = new HashSet();
            Extensions extensions = this.c.getTBSCertificate().getExtensions();
            if (extensions == null) {
                return null;
            }
            Enumeration oids = extensions.oids();
            while (oids.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) oids.nextElement();
                if (!extensions.getExtension(aSN1ObjectIdentifier).isCritical()) {
                    hashSet.add(aSN1ObjectIdentifier.getId());
                }
            }
            return hashSet;
        }
        return null;
    }

    @Override // java.security.cert.X509Certificate
    public Date getNotAfter() {
        return this.c.getEndDate().getDate();
    }

    @Override // java.security.cert.X509Certificate
    public Date getNotBefore() {
        return this.c.getStartDate().getDate();
    }

    @Override // java.security.cert.Certificate
    public PublicKey getPublicKey() {
        try {
            return BouncyCastleProvider.getPublicKey(this.c.getSubjectPublicKeyInfo());
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public BigInteger getSerialNumber() {
        return this.c.getSerialNumber().getValue();
    }

    @Override // java.security.cert.X509Certificate
    public String getSigAlgName() {
        Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        if (provider != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Alg.Alias.Signature.");
            outline107.append(getSigAlgOID());
            String property = provider.getProperty(outline107.toString());
            if (property != null) {
                return property;
            }
        }
        Provider[] providers = Security.getProviders();
        for (int i = 0; i != providers.length; i++) {
            Provider provider2 = providers[i];
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Alg.Alias.Signature.");
            outline1072.append(getSigAlgOID());
            String property2 = provider2.getProperty(outline1072.toString());
            if (property2 != null) {
                return property2;
            }
        }
        return getSigAlgOID();
    }

    @Override // java.security.cert.X509Certificate
    public String getSigAlgOID() {
        return this.c.getSignatureAlgorithm().getAlgorithm().getId();
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getSigAlgParams() {
        if (this.c.getSignatureAlgorithm().getParameters() != null) {
            try {
                return this.c.getSignatureAlgorithm().getParameters().toASN1Primitive().getEncoded("DER");
            } catch (IOException unused) {
            }
        }
        return null;
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getSignature() {
        return this.c.getSignature().getOctets();
    }

    @Override // java.security.cert.X509Certificate
    public Collection getSubjectAlternativeNames() throws CertificateParsingException {
        return getAlternativeNames(getExtensionBytes(Extension.subjectAlternativeName.getId()));
    }

    @Override // java.security.cert.X509Certificate
    public Principal getSubjectDN() {
        return new X509Principal(this.c.getSubject());
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getSubjectUniqueID() {
        DERBitString subjectUniqueId = this.c.getTBSCertificate().getSubjectUniqueId();
        if (subjectUniqueId != null) {
            byte[] bytes = subjectUniqueId.getBytes();
            boolean[] zArr = new boolean[(bytes.length * 8) - subjectUniqueId.getPadBits()];
            for (int i = 0; i != zArr.length; i++) {
                zArr[i] = (bytes[i / 8] & (128 >>> (i % 8))) != 0;
            }
            return zArr;
        }
        return null;
    }

    @Override // java.security.cert.X509Certificate
    public X500Principal getSubjectX500Principal() {
        try {
            return new X500Principal(this.c.getSubject().getEncoded());
        } catch (IOException unused) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getTBSCertificate() throws CertificateEncodingException {
        try {
            return this.c.getTBSCertificate().getEncoded("DER");
        } catch (IOException e) {
            throw new CertificateEncodingException(e.toString());
        }
    }

    @Override // java.security.cert.X509Certificate
    public int getVersion() {
        return this.c.getVersionNumber();
    }

    @Override // java.security.cert.X509Extension
    public boolean hasUnsupportedCriticalExtension() {
        Extensions extensions;
        if (getVersion() != 3 || (extensions = this.c.getTBSCertificate().getExtensions()) == null) {
            return false;
        }
        Enumeration oids = extensions.oids();
        while (oids.hasMoreElements()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) oids.nextElement();
            String id = aSN1ObjectIdentifier.getId();
            if (!id.equals(RFC3280CertPathUtilities.KEY_USAGE) && !id.equals(RFC3280CertPathUtilities.CERTIFICATE_POLICIES) && !id.equals(RFC3280CertPathUtilities.POLICY_MAPPINGS) && !id.equals(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY) && !id.equals(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS) && !id.equals(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT) && !id.equals(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR) && !id.equals(RFC3280CertPathUtilities.POLICY_CONSTRAINTS) && !id.equals(RFC3280CertPathUtilities.BASIC_CONSTRAINTS) && !id.equals(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME) && !id.equals(RFC3280CertPathUtilities.NAME_CONSTRAINTS) && extensions.getExtension(aSN1ObjectIdentifier).isCritical()) {
                return true;
            }
        }
        return false;
    }

    @Override // java.security.cert.Certificate
    public synchronized int hashCode() {
        if (!this.hashValueSet) {
            this.hashValue = calculateHashCode();
            this.hashValueSet = true;
        }
        return this.hashValue;
    }

    @Override // org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public void setBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.attrCarrier.setBagAttribute(aSN1ObjectIdentifier, aSN1Encodable);
    }

    @Override // java.security.cert.Certificate
    public String toString() {
        Object verisignCzagExtension;
        StringBuffer stringBuffer = new StringBuffer();
        String lineSeparator = Strings.lineSeparator();
        stringBuffer.append("  [0]         Version: ");
        stringBuffer.append(getVersion());
        stringBuffer.append(lineSeparator);
        stringBuffer.append("         SerialNumber: ");
        stringBuffer.append(getSerialNumber());
        stringBuffer.append(lineSeparator);
        stringBuffer.append("             IssuerDN: ");
        stringBuffer.append(getIssuerDN());
        stringBuffer.append(lineSeparator);
        stringBuffer.append("           Start Date: ");
        stringBuffer.append(getNotBefore());
        stringBuffer.append(lineSeparator);
        stringBuffer.append("           Final Date: ");
        stringBuffer.append(getNotAfter());
        stringBuffer.append(lineSeparator);
        stringBuffer.append("            SubjectDN: ");
        stringBuffer.append(getSubjectDN());
        stringBuffer.append(lineSeparator);
        stringBuffer.append("           Public Key: ");
        stringBuffer.append(getPublicKey());
        stringBuffer.append(lineSeparator);
        stringBuffer.append("  Signature Algorithm: ");
        stringBuffer.append(getSigAlgName());
        stringBuffer.append(lineSeparator);
        byte[] signature = getSignature();
        stringBuffer.append("            Signature: ");
        stringBuffer.append(new String(Hex.encode(signature, 0, 20)));
        stringBuffer.append(lineSeparator);
        int i = 20;
        while (i < signature.length) {
            int length = signature.length - 20;
            stringBuffer.append("                       ");
            stringBuffer.append(i < length ? new String(Hex.encode(signature, i, 20)) : new String(Hex.encode(signature, i, signature.length - i)));
            stringBuffer.append(lineSeparator);
            i += 20;
        }
        Extensions extensions = this.c.getTBSCertificate().getExtensions();
        if (extensions != null) {
            Enumeration oids = extensions.oids();
            if (oids.hasMoreElements()) {
                stringBuffer.append("       Extensions: \n");
            }
            while (oids.hasMoreElements()) {
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) oids.nextElement();
                Extension extension = extensions.getExtension(aSN1ObjectIdentifier);
                if (extension.getExtnValue() != null) {
                    ASN1InputStream aSN1InputStream = new ASN1InputStream(extension.getExtnValue().getOctets());
                    stringBuffer.append("                       critical(");
                    stringBuffer.append(extension.isCritical());
                    stringBuffer.append(") ");
                    try {
                    } catch (Exception unused) {
                        stringBuffer.append(aSN1ObjectIdentifier.getId());
                        stringBuffer.append(" value = ");
                        stringBuffer.append("*****");
                    }
                    if (aSN1ObjectIdentifier.equals((ASN1Primitive) Extension.basicConstraints)) {
                        verisignCzagExtension = BasicConstraints.getInstance(aSN1InputStream.readObject());
                    } else if (aSN1ObjectIdentifier.equals((ASN1Primitive) Extension.keyUsage)) {
                        verisignCzagExtension = KeyUsage.getInstance(aSN1InputStream.readObject());
                    } else if (aSN1ObjectIdentifier.equals((ASN1Primitive) MiscObjectIdentifiers.netscapeCertType)) {
                        verisignCzagExtension = new NetscapeCertType((DERBitString) aSN1InputStream.readObject());
                    } else if (aSN1ObjectIdentifier.equals((ASN1Primitive) MiscObjectIdentifiers.netscapeRevocationURL)) {
                        verisignCzagExtension = new NetscapeRevocationURL((DERIA5String) aSN1InputStream.readObject());
                    } else if (aSN1ObjectIdentifier.equals((ASN1Primitive) MiscObjectIdentifiers.verisignCzagExtension)) {
                        verisignCzagExtension = new VerisignCzagExtension((DERIA5String) aSN1InputStream.readObject());
                    } else {
                        stringBuffer.append(aSN1ObjectIdentifier.getId());
                        stringBuffer.append(" value = ");
                        stringBuffer.append(ASN1Dump.dumpAsString(aSN1InputStream.readObject()));
                        stringBuffer.append(lineSeparator);
                    }
                    stringBuffer.append(verisignCzagExtension);
                    stringBuffer.append(lineSeparator);
                }
                stringBuffer.append(lineSeparator);
            }
        }
        return stringBuffer.toString();
    }

    @Override // java.security.cert.Certificate
    public final void verify(PublicKey publicKey) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        Signature signature;
        String signatureName = X509SignatureUtil.getSignatureName(this.c.getSignatureAlgorithm());
        try {
            signature = Signature.getInstance(signatureName, BouncyCastleProvider.PROVIDER_NAME);
        } catch (Exception unused) {
            signature = Signature.getInstance(signatureName);
        }
        checkSignature(publicKey, signature);
    }

    @Override // java.security.cert.Certificate
    public final void verify(PublicKey publicKey, String str) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        String signatureName = X509SignatureUtil.getSignatureName(this.c.getSignatureAlgorithm());
        checkSignature(publicKey, str != null ? Signature.getInstance(signatureName, str) : Signature.getInstance(signatureName));
    }

    @Override // java.security.cert.X509Certificate, java.security.cert.Certificate
    public final void verify(PublicKey publicKey, Provider provider) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        String signatureName = X509SignatureUtil.getSignatureName(this.c.getSignatureAlgorithm());
        checkSignature(publicKey, provider != null ? Signature.getInstance(signatureName, provider) : Signature.getInstance(signatureName));
    }
}
