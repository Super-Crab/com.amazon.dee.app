package com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1OctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Primitive;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.ecc.MQVuserKeyingMaterial;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSEnvelopedGenerator;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSException;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.KeyAgreeRecipient;
import com.amazon.alexa.device.setup.echo.bouncycastle.jce.spec.MQVPrivateKeySpec;
import com.amazon.alexa.device.setup.echo.bouncycastle.jce.spec.MQVPublicKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
/* loaded from: classes.dex */
public abstract class JceKeyAgreeRecipient implements KeyAgreeRecipient {
    private final PrivateKey recipientKey;
    protected EnvelopedDataHelper helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    protected EnvelopedDataHelper contentHelper = this.helper;

    public JceKeyAgreeRecipient(PrivateKey privateKey) {
        this.recipientKey = privateKey;
    }

    private SecretKey calculateAgreedWrapKey(AlgorithmIdentifier algorithmIdentifier, ASN1ObjectIdentifier aSN1ObjectIdentifier, PublicKey publicKey, ASN1OctetString aSN1OctetString, PrivateKey privateKey) throws CMSException, GeneralSecurityException, IOException {
        PublicKey publicKey2;
        PrivateKey privateKey2;
        if (algorithmIdentifier.getAlgorithm().getId().equals(CMSEnvelopedGenerator.ECMQV_SHA1KDF)) {
            publicKey2 = new MQVPublicKeySpec(publicKey, this.helper.createKeyFactory(algorithmIdentifier.getAlgorithm()).generatePublic(new X509EncodedKeySpec(new SubjectPublicKeyInfo(getPrivateKeyAlgorithmIdentifier(), MQVuserKeyingMaterial.getInstance(ASN1Primitive.fromByteArray(aSN1OctetString.getOctets())).getEphemeralPublicKey().getPublicKey().getBytes()).getEncoded())));
            privateKey2 = new MQVPrivateKeySpec(privateKey, privateKey);
        } else {
            publicKey2 = publicKey;
            privateKey2 = privateKey;
        }
        KeyAgreement createKeyAgreement = this.helper.createKeyAgreement(algorithmIdentifier.getAlgorithm());
        createKeyAgreement.init(privateKey2);
        createKeyAgreement.doPhase(publicKey2, true);
        return createKeyAgreement.generateSecret(aSN1ObjectIdentifier.getId());
    }

    private Key unwrapSessionKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, SecretKey secretKey, ASN1ObjectIdentifier aSN1ObjectIdentifier2, byte[] bArr) throws CMSException, InvalidKeyException, NoSuchAlgorithmException {
        Cipher createCipher = this.helper.createCipher(aSN1ObjectIdentifier);
        createCipher.init(4, secretKey);
        return createCipher.unwrap(bArr, this.helper.getBaseCipherName(aSN1ObjectIdentifier2), 3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Key extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, SubjectPublicKeyInfo subjectPublicKeyInfo, ASN1OctetString aSN1OctetString, byte[] bArr) throws CMSException {
        try {
            ASN1ObjectIdentifier algorithm = AlgorithmIdentifier.getInstance(algorithmIdentifier.getParameters()).getAlgorithm();
            return unwrapSessionKey(algorithm, calculateAgreedWrapKey(algorithmIdentifier, algorithm, this.helper.createKeyFactory(algorithmIdentifier.getAlgorithm()).generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded())), aSN1OctetString, this.recipientKey), algorithmIdentifier2.getAlgorithm(), bArr);
        } catch (InvalidKeyException e) {
            throw new CMSException("key invalid in message.", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new CMSException("can't find algorithm.", e2);
        } catch (InvalidKeySpecException e3) {
            throw new CMSException("originator key spec invalid.", e3);
        } catch (NoSuchPaddingException e4) {
            throw new CMSException("required padding not supported.", e4);
        } catch (Exception e5) {
            throw new CMSException("originator key invalid.", e5);
        }
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.KeyAgreeRecipient
    public AlgorithmIdentifier getPrivateKeyAlgorithmIdentifier() {
        return PrivateKeyInfo.getInstance(this.recipientKey.getEncoded()).getAlgorithmId();
    }

    public JceKeyAgreeRecipient setContentProvider(Provider provider) {
        this.contentHelper = CMSUtils.createContentHelper(provider);
        return this;
    }

    public JceKeyAgreeRecipient setProvider(Provider provider) {
        this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        this.contentHelper = this.helper;
        return this;
    }

    public JceKeyAgreeRecipient setContentProvider(String str) {
        this.contentHelper = CMSUtils.createContentHelper(str);
        return this;
    }

    public JceKeyAgreeRecipient setProvider(String str) {
        this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(str));
        this.contentHelper = this.helper;
        return this;
    }
}
