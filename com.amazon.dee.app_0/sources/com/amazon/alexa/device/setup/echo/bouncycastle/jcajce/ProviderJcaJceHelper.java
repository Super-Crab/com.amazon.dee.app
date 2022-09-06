package com.amazon.alexa.device.setup.echo.bouncycastle.jcajce;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
/* loaded from: classes.dex */
public class ProviderJcaJceHelper implements JcaJceHelper {
    protected final Provider provider;

    public ProviderJcaJceHelper(Provider provider) {
        this.provider = provider;
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.JcaJceHelper
    public AlgorithmParameterGenerator createAlgorithmParameterGenerator(String str) throws NoSuchAlgorithmException {
        return AlgorithmParameterGenerator.getInstance(str, this.provider);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.JcaJceHelper
    public AlgorithmParameters createAlgorithmParameters(String str) throws NoSuchAlgorithmException {
        return AlgorithmParameters.getInstance(str, this.provider);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.JcaJceHelper
    public CertificateFactory createCertificateFactory(String str) throws NoSuchAlgorithmException, CertificateException {
        return CertificateFactory.getInstance(str, this.provider);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.JcaJceHelper
    public Cipher createCipher(String str) throws NoSuchAlgorithmException, NoSuchPaddingException {
        return Cipher.getInstance(str, this.provider);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.JcaJceHelper
    public MessageDigest createDigest(String str) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(str, this.provider);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.JcaJceHelper
    public KeyAgreement createKeyAgreement(String str) throws NoSuchAlgorithmException {
        return KeyAgreement.getInstance(str, this.provider);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.JcaJceHelper
    public KeyFactory createKeyFactory(String str) throws NoSuchAlgorithmException {
        return KeyFactory.getInstance(str, this.provider);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.JcaJceHelper
    public KeyGenerator createKeyGenerator(String str) throws NoSuchAlgorithmException {
        return KeyGenerator.getInstance(str, this.provider);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.JcaJceHelper
    public KeyPairGenerator createKeyPairGenerator(String str) throws NoSuchAlgorithmException {
        return KeyPairGenerator.getInstance(str, this.provider);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.JcaJceHelper
    public Mac createMac(String str) throws NoSuchAlgorithmException {
        return Mac.getInstance(str, this.provider);
    }

    @Override // com.amazon.alexa.device.setup.echo.bouncycastle.jcajce.JcaJceHelper
    public Signature createSignature(String str) throws NoSuchAlgorithmException {
        return Signature.getInstance(str, this.provider);
    }
}
