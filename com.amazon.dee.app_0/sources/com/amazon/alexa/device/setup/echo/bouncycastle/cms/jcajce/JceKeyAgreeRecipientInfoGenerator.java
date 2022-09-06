package com.amazon.alexa.device.setup.echo.bouncycastle.cms.jcajce;

import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Encodable;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1EncodableVector;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.ASN1Sequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DEROctetString;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.DERSequence;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.KeyAgreeRecipientIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.RecipientEncryptedKey;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.RecipientKeyIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.cms.ecc.MQVuserKeyingMaterial;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.amazon.alexa.device.setup.echo.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSAlgorithm;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSEnvelopedGenerator;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.CMSException;
import com.amazon.alexa.device.setup.echo.bouncycastle.cms.KeyAgreeRecipientInfoGenerator;
import com.amazon.alexa.device.setup.echo.bouncycastle.jce.spec.MQVPrivateKeySpec;
import com.amazon.alexa.device.setup.echo.bouncycastle.jce.spec.MQVPublicKeySpec;
import com.amazon.alexa.device.setup.echo.bouncycastle.operator.GenericKey;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
/* loaded from: classes.dex */
public class JceKeyAgreeRecipientInfoGenerator extends KeyAgreeRecipientInfoGenerator {
    private KeyPair ephemeralKP;
    private EnvelopedDataHelper helper;
    private SecureRandom random;
    private final List recipientIDs;
    private final List recipientKeys;
    private final PrivateKey senderPrivateKey;
    private final PublicKey senderPublicKey;

    public JceKeyAgreeRecipientInfoGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier, PrivateKey privateKey, PublicKey publicKey, ASN1ObjectIdentifier aSN1ObjectIdentifier2) {
        super(aSN1ObjectIdentifier, SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()), aSN1ObjectIdentifier2);
        this.recipientIDs = new ArrayList();
        this.recipientKeys = new ArrayList();
        this.helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        this.senderPublicKey = publicKey;
        this.senderPrivateKey = privateKey;
    }

    private void init(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CMSException {
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        if (!aSN1ObjectIdentifier.equals(CMSAlgorithm.ECMQV_SHA1KDF) || this.ephemeralKP != null) {
            return;
        }
        try {
            ECParameterSpec params = ((ECPublicKey) this.senderPublicKey).getParams();
            KeyPairGenerator createKeyPairGenerator = this.helper.createKeyPairGenerator(aSN1ObjectIdentifier);
            createKeyPairGenerator.initialize(params, this.random);
            this.ephemeralKP = createKeyPairGenerator.generateKeyPair();
        } catch (InvalidAlgorithmParameterException e) {
            throw new CMSException("cannot determine MQV ephemeral key pair parameters from public key: " + e);
        }
    }

    public JceKeyAgreeRecipientInfoGenerator addRecipient(X509Certificate x509Certificate) throws CertificateEncodingException {
        this.recipientIDs.add(new KeyAgreeRecipientIdentifier(CMSUtils.getIssuerAndSerialNumber(x509Certificate)));
        this.recipientKeys.add(x509Certificate.getPublicKey());
        return this;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.KeyAgreeRecipientInfoGenerator
    public ASN1Sequence generateRecipientEncryptedKeys(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, GenericKey genericKey) throws CMSException {
        init(algorithmIdentifier.getAlgorithm());
        PrivateKey privateKey = this.senderPrivateKey;
        ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
        if (algorithm.getId().equals(CMSEnvelopedGenerator.ECMQV_SHA1KDF)) {
            privateKey = new MQVPrivateKeySpec(privateKey, this.ephemeralKP.getPrivate(), this.ephemeralKP.getPublic());
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.recipientIDs.size(); i++) {
            PublicKey publicKey = (PublicKey) this.recipientKeys.get(i);
            KeyAgreeRecipientIdentifier keyAgreeRecipientIdentifier = (KeyAgreeRecipientIdentifier) this.recipientIDs.get(i);
            if (algorithm.getId().equals(CMSEnvelopedGenerator.ECMQV_SHA1KDF)) {
                publicKey = new MQVPublicKeySpec(publicKey, publicKey);
            }
            try {
                KeyAgreement createKeyAgreement = this.helper.createKeyAgreement(algorithm);
                createKeyAgreement.init(privateKey, this.random);
                createKeyAgreement.doPhase(publicKey, true);
                SecretKey generateSecret = createKeyAgreement.generateSecret(algorithmIdentifier2.getAlgorithm().getId());
                Cipher createCipher = this.helper.createCipher(algorithmIdentifier2.getAlgorithm());
                createCipher.init(3, generateSecret, this.random);
                aSN1EncodableVector.add(new RecipientEncryptedKey(keyAgreeRecipientIdentifier, new DEROctetString(createCipher.wrap(this.helper.getJceKey(genericKey)))));
            } catch (GeneralSecurityException e) {
                throw new CMSException(GeneratedOutlineSupport1.outline99(e, GeneratedOutlineSupport1.outline107("cannot perform agreement step: ")), e);
            }
        }
        return new DERSequence(aSN1EncodableVector);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.cms.KeyAgreeRecipientInfoGenerator
    protected ASN1Encodable getUserKeyingMaterial(AlgorithmIdentifier algorithmIdentifier) throws CMSException {
        init(algorithmIdentifier.getAlgorithm());
        KeyPair keyPair = this.ephemeralKP;
        if (keyPair != null) {
            return new MQVuserKeyingMaterial(createOriginatorPublicKey(SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded())), null);
        }
        return null;
    }

    public JceKeyAgreeRecipientInfoGenerator setProvider(Provider provider) {
        this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceKeyAgreeRecipientInfoGenerator setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public JceKeyAgreeRecipientInfoGenerator setProvider(String str) {
        this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(str));
        return this;
    }

    public JceKeyAgreeRecipientInfoGenerator addRecipient(byte[] bArr, PublicKey publicKey) throws CertificateEncodingException {
        this.recipientIDs.add(new KeyAgreeRecipientIdentifier(new RecipientKeyIdentifier(bArr)));
        this.recipientKeys.add(publicKey);
        return this;
    }
}
