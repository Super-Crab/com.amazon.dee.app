package org.bouncycastle.mail.smime.examples;

import com.amazon.whispercloak.KeyUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.smime.SMIMECapabilitiesAttribute;
import org.bouncycastle.asn1.smime.SMIMECapability;
import org.bouncycastle.asn1.smime.SMIMECapabilityVector;
import org.bouncycastle.asn1.smime.SMIMEEncryptionKeyPreferenceAttribute;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.mail.smime.SMIMESignedGenerator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
/* loaded from: classes4.dex */
public class CreateLargeSignedMail {
    static int serialNo = 1;

    public static void main(String[] strArr) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyUtils.ALGORITHM_RSA, BouncyCastleProvider.PROVIDER_NAME);
        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
        X509Certificate makeCertificate = makeCertificate(generateKeyPair, "O=Bouncy Castle, C=AU", generateKeyPair, "O=Bouncy Castle, C=AU");
        KeyPair generateKeyPair2 = keyPairGenerator.generateKeyPair();
        X509Certificate makeCertificate2 = makeCertificate(generateKeyPair2, "CN=Eric H. Echidna, E=eric@bouncycastle.org, O=Bouncy Castle, C=AU", generateKeyPair, "O=Bouncy Castle, C=AU");
        ArrayList arrayList = new ArrayList();
        arrayList.add(makeCertificate2);
        arrayList.add(makeCertificate);
        JcaCertStore jcaCertStore = new JcaCertStore(arrayList);
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        SMIMECapabilityVector sMIMECapabilityVector = new SMIMECapabilityVector();
        sMIMECapabilityVector.addCapability(SMIMECapability.dES_EDE3_CBC);
        sMIMECapabilityVector.addCapability(SMIMECapability.rC2_CBC, 128);
        sMIMECapabilityVector.addCapability(SMIMECapability.dES_CBC);
        aSN1EncodableVector.add(new SMIMECapabilitiesAttribute(sMIMECapabilityVector));
        aSN1EncodableVector.add(new SMIMEEncryptionKeyPreferenceAttribute(new IssuerAndSerialNumber(new X500Name("O=Bouncy Castle, C=AU"), makeCertificate2.getSerialNumber())));
        SMIMESignedGenerator sMIMESignedGenerator = new SMIMESignedGenerator();
        sMIMESignedGenerator.addSignerInfoGenerator(new JcaSimpleSignerInfoGeneratorBuilder().setProvider(BouncyCastleProvider.PROVIDER_NAME).setSignedAttributeGenerator(new AttributeTable(aSN1EncodableVector)).build("SHA1withRSA", generateKeyPair2.getPrivate(), makeCertificate2));
        sMIMESignedGenerator.addCertificates(jcaCertStore);
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setDataHandler(new DataHandler(new FileDataSource(new File(strArr[0]))));
        mimeBodyPart.setHeader("Content-Type", "application/octet-stream");
        mimeBodyPart.setHeader("Content-Transfer-Encoding", "base64");
        MimeMultipart generate = sMIMESignedGenerator.generate(mimeBodyPart);
        Session defaultInstance = Session.getDefaultInstance(System.getProperties(), null);
        InternetAddress internetAddress = new InternetAddress("\"Eric H. Echidna\"<eric@bouncycastle.org>");
        InternetAddress internetAddress2 = new InternetAddress("example@bouncycastle.org");
        MimeMessage mimeMessage = new MimeMessage(defaultInstance);
        mimeMessage.setFrom(internetAddress);
        mimeMessage.setRecipient(Message.RecipientType.TO, internetAddress2);
        mimeMessage.setSubject("example signed message");
        mimeMessage.setContent(generate, generate.getContentType());
        mimeMessage.saveChanges();
        mimeMessage.writeTo(new FileOutputStream("signed.message"));
    }

    static X509Certificate makeCertificate(KeyPair keyPair, String str, KeyPair keyPair2, String str2) throws GeneralSecurityException, IOException, OperatorCreationException {
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair2.getPrivate();
        PublicKey publicKey2 = keyPair2.getPublic();
        JcaX509ExtensionUtils jcaX509ExtensionUtils = new JcaX509ExtensionUtils();
        X500Name x500Name = new X500Name(str2);
        int i = serialNo;
        serialNo = i + 1;
        JcaX509v3CertificateBuilder jcaX509v3CertificateBuilder = new JcaX509v3CertificateBuilder(x500Name, BigInteger.valueOf(i), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 8640000000L), new X500Name(str), publicKey);
        jcaX509v3CertificateBuilder.addExtension(Extension.subjectKeyIdentifier, false, (ASN1Encodable) jcaX509ExtensionUtils.createSubjectKeyIdentifier(publicKey));
        jcaX509v3CertificateBuilder.addExtension(Extension.authorityKeyIdentifier, false, (ASN1Encodable) jcaX509ExtensionUtils.createAuthorityKeyIdentifier(publicKey2));
        return new JcaX509CertificateConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME).getCertificate(jcaX509v3CertificateBuilder.build(new JcaContentSignerBuilder("MD5withRSA").setProvider(BouncyCastleProvider.PROVIDER_NAME).build(privateKey)));
    }
}
