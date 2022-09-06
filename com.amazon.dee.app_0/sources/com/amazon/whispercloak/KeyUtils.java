package com.amazon.whispercloak;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
/* loaded from: classes13.dex */
public class KeyUtils {
    public static final String ALGORITHM_EC = "EC";
    public static final String ALGORITHM_RSA = "RSA";
    private static final String PUBLIC_KEY_PEM_TYPE = "PUBLIC KEY";
    private static final String TAG = "com.amazon.whispercloak.KeyUtils";
    public static final String X509_CERITIFATE_FACTORY = "X.509";
    private static final String X509_CERT_PEM_TYPE = "CERTIFICATE";
    private final CertificateFactory mCertificateFactory;
    private final KeyFactory mEcKeyFactory;
    private final KeyFactory mRsaKeyFactory;

    public KeyUtils() {
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
        this.mEcKeyFactory = getKeyFactory(ALGORITHM_EC);
        this.mRsaKeyFactory = getKeyFactory(ALGORITHM_RSA);
        this.mCertificateFactory = getCertificateFactory(X509_CERITIFATE_FACTORY);
    }

    private CertificateFactory getCertificateFactory(String str) {
        try {
            return CertificateFactory.getInstance(str);
        } catch (CertificateException e) {
            Log.e(TAG, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private KeyFactory getKeyFactory(String str) {
        try {
            return KeyFactory.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ASN1Primitive getDerPublicKeyInfo(PublicKey publicKey) {
        return SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()).toASN1Primitive();
    }

    public PrivateKey getEcPrivateKeyFromDerEncodedByteArray(byte[] bArr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            return this.mEcKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(bArr));
        } catch (InvalidKeySpecException e) {
            Log.e(TAG, e.getMessage());
            throw e;
        }
    }

    public PublicKey getEcPublicKeyFromDerEncodedByteArray(byte[] bArr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            return this.mEcKeyFactory.generatePublic(new X509EncodedKeySpec(bArr));
        } catch (InvalidKeySpecException e) {
            Log.e(TAG, e.getMessage());
            throw e;
        }
    }

    public X509Certificate getLeafCertificate(List<X509Certificate> list) {
        X509Certificate x509Certificate = getOrderedCertificateChainFromLeafToRoot(list).get(0);
        if (x509Certificate == null || !isCACertificate(x509Certificate)) {
            return x509Certificate;
        }
        throw new IllegalArgumentException("Cert chain does not have a leaf certificate.");
    }

    public List<X509Certificate> getOrderedCertificateChainFromLeafToRoot(List<X509Certificate> list) {
        HashMap hashMap = new HashMap();
        X509Certificate x509Certificate = null;
        X509Certificate x509Certificate2 = null;
        for (X509Certificate x509Certificate3 : list) {
            if (!isCACertificate(x509Certificate3)) {
                x509Certificate = x509Certificate3;
            } else if (isSelfSignedCertificate(x509Certificate3)) {
                x509Certificate2 = x509Certificate3;
            }
            hashMap.put(x509Certificate3.getSubjectDN(), x509Certificate3);
        }
        if (x509Certificate != null) {
            LinkedList linkedList = new LinkedList();
            while (x509Certificate != null) {
                linkedList.add(x509Certificate);
                x509Certificate = (X509Certificate) hashMap.get(x509Certificate.getIssuerDN());
            }
            if (x509Certificate2 != null) {
                linkedList.add(x509Certificate2);
            }
            return linkedList;
        }
        throw new IllegalArgumentException("No leaf certificate in this chain.");
    }

    public String getPemEncodedPublicKey(byte[] bArr) {
        StringWriter stringWriter = new StringWriter();
        try {
            PemWriter pemWriter = new PemWriter(stringWriter);
            pemWriter.writeObject(new PemObject(PUBLIC_KEY_PEM_TYPE, bArr));
            pemWriter.flush();
            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException("Invalid der encoded key. Unable to serialize to PEM key.", e);
        }
    }

    public String getPemEncodedX509Certificate(X509Certificate x509Certificate) {
        StringWriter stringWriter = new StringWriter();
        try {
            PemWriter pemWriter = new PemWriter(stringWriter);
            pemWriter.writeObject(new PemObject(X509_CERT_PEM_TYPE, x509Certificate.getEncoded()));
            pemWriter.flush();
            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException("Invalid cert. Unable to serialize to PEM cert.", e);
        }
    }

    public PrivateKey getPrivateKeyFromDerEncodedByteArray(byte[] bArr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        AsymmetricKeyParameter createKey;
        try {
            createKey = PrivateKeyFactory.createKey(bArr);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        if (createKey instanceof ECKeyParameters) {
            return getEcPrivateKeyFromDerEncodedByteArray(bArr);
        }
        if (createKey instanceof RSAKeyParameters) {
            return getRsaPrivateKeyFromDerEncodedByteArray(bArr);
        }
        throw new RuntimeException("Private Key invalid/unsupported");
    }

    public PublicKey getPublicKeyFromDerEncodedByteArray(byte[] bArr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        AsymmetricKeyParameter createKey;
        try {
            createKey = PublicKeyFactory.createKey(bArr);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        if (createKey instanceof ECKeyParameters) {
            return getEcPublicKeyFromDerEncodedByteArray(bArr);
        }
        if (createKey instanceof RSAKeyParameters) {
            return getRsaPublicKeyFromDerEncodedByteArray(bArr);
        }
        throw new RuntimeException("Public Key invalid/unsupported");
    }

    public PrivateKey getRsaPrivateKeyFromDerEncodedByteArray(byte[] bArr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            return this.mRsaKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(bArr));
        } catch (InvalidKeySpecException e) {
            Log.e(TAG, e.getMessage());
            throw e;
        }
    }

    public PublicKey getRsaPublicKeyFromDerEncodedByteArray(byte[] bArr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            return this.mRsaKeyFactory.generatePublic(new X509EncodedKeySpec(bArr));
        } catch (InvalidKeySpecException e) {
            Log.e(TAG, e.getMessage());
            throw e;
        }
    }

    public List<X509Certificate> getX509CertificateChainFromPemString(String str) {
        try {
            return (List) this.mCertificateFactory.generateCertificates(new ByteArrayInputStream(str.getBytes()));
        } catch (CertificateException e) {
            throw new RuntimeException("Unable to parse certificate chain", e);
        }
    }

    public boolean isCACertificate(X509Certificate x509Certificate) {
        return x509Certificate.getBasicConstraints() != -1;
    }

    public boolean isSelfSignedCertificate(X509Certificate x509Certificate) {
        return x509Certificate.getSubjectDN().equals(x509Certificate.getIssuerDN());
    }
}
