package org.bouncycastle.mail.smime.examples;

import com.amazon.alexa.accessory.internal.util.DeviceDatabaseUtils;
import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.i18n.ErrorBundle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.mail.smime.validator.SignedMailValidator;
import org.bouncycastle.x509.PKIXCertPathReviewer;
/* loaded from: classes4.dex */
public class ValidateSignedMail {
    public static final int DETAIL = 3;
    private static final String RESOURCE_NAME = "org.bouncycastle.mail.smime.validator.SignedMailValidatorMessages";
    public static final int SUMMARY = 2;
    public static final int TEXT = 1;
    public static final int TITLE = 0;
    static int dbgLvl = 3;
    public static final boolean useCaCerts = false;

    private static TrustAnchor getDummyTrustAnchor() throws Exception {
        X500Principal x500Principal = new X500Principal("CN=Dummy Trust Anchor");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyUtils.ALGORITHM_RSA, BouncyCastleProvider.PROVIDER_NAME);
        keyPairGenerator.initialize(1024, new SecureRandom());
        return new TrustAnchor(x500Principal, keyPairGenerator.generateKeyPair().getPublic(), (byte[]) null);
    }

    protected static TrustAnchor getTrustAnchor(String str) throws Exception {
        X509Certificate loadCert = loadCert(str);
        if (loadCert != null) {
            byte[] extensionValue = loadCert.getExtensionValue(Extension.nameConstraints.getId());
            return extensionValue != null ? new TrustAnchor(loadCert, JcaX509ExtensionUtils.parseExtensionValue(extensionValue).toASN1Primitive().getEncoded("DER")) : new TrustAnchor(loadCert, null);
        }
        return null;
    }

    protected static X509CRL loadCRL(String str) {
        try {
            return (X509CRL) CertificateFactory.getInstance(KeyUtils.X509_CERITIFATE_FACTORY, BouncyCastleProvider.PROVIDER_NAME).generateCRL(new FileInputStream(str));
        } catch (Exception unused) {
            PrintStream printStream = System.out;
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("crlfile \"", str, "\" not found - classpath is ");
            outline115.append(System.getProperty("java.class.path"));
            printStream.println(outline115.toString());
            return null;
        }
    }

    protected static X509Certificate loadCert(String str) {
        try {
            return (X509Certificate) CertificateFactory.getInstance(KeyUtils.X509_CERITIFATE_FACTORY, BouncyCastleProvider.PROVIDER_NAME).generateCertificate(new FileInputStream(str));
        } catch (Exception unused) {
            PrintStream printStream = System.out;
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("certfile \"", str, "\" not found - classpath is ");
            outline115.append(System.getProperty("java.class.path"));
            printStream.println(outline115.toString());
            return null;
        }
    }

    public static void main(String[] strArr) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        MimeMessage mimeMessage = new MimeMessage(Session.getDefaultInstance(System.getProperties(), null), new FileInputStream("signed.message"));
        HashSet hashSet = new HashSet();
        TrustAnchor trustAnchor = getTrustAnchor("trustanchor");
        if (trustAnchor == null) {
            System.out.println("no trustanchor file found, using a dummy trustanchor");
            trustAnchor = getDummyTrustAnchor();
        }
        hashSet.add(trustAnchor);
        PKIXParameters pKIXParameters = new PKIXParameters(hashSet);
        ArrayList arrayList = new ArrayList();
        X509CRL loadCRL = loadCRL("crl.file");
        if (loadCRL != null) {
            arrayList.add(loadCRL);
        }
        pKIXParameters.addCertStore(CertStore.getInstance("Collection", new CollectionCertStoreParameters(arrayList), BouncyCastleProvider.PROVIDER_NAME));
        pKIXParameters.setRevocationEnabled(true);
        verifySignedMail(mimeMessage, pKIXParameters);
    }

    public static void verifySignedMail(MimeMessage mimeMessage, PKIXParameters pKIXParameters) throws Exception {
        PrintStream printStream;
        StringBuilder outline107;
        String text;
        PrintStream printStream2;
        String str;
        PrintStream printStream3;
        StringBuilder outline1072;
        String text2;
        PrintStream printStream4;
        StringBuilder outline1073;
        String text3;
        PrintStream printStream5;
        StringBuilder outline1074;
        String text4;
        PrintStream printStream6;
        StringBuilder outline1075;
        String text5;
        Locale locale = Locale.ENGLISH;
        SignedMailValidator signedMailValidator = new SignedMailValidator(mimeMessage, pKIXParameters);
        for (SignerInformation signerInformation : signedMailValidator.getSignerInformationStore().getSigners()) {
            SignedMailValidator.ValidationResult validationResult = signedMailValidator.getValidationResult(signerInformation);
            if (validationResult.isValidSignature()) {
                System.out.println(new ErrorBundle(RESOURCE_NAME, "SignedMailValidator.sigValid").getText(locale));
            } else {
                System.out.println(new ErrorBundle(RESOURCE_NAME, "SignedMailValidator.sigInvalid").getText(locale));
                System.out.println("Errors:");
                for (ErrorBundle errorBundle : validationResult.getErrors()) {
                    if (dbgLvl == 3) {
                        printStream = System.out;
                        outline107 = GeneratedOutlineSupport1.outline107("\t\t");
                        text = errorBundle.getDetail(locale);
                    } else {
                        printStream = System.out;
                        outline107 = GeneratedOutlineSupport1.outline107("\t\t");
                        text = errorBundle.getText(locale);
                    }
                    GeneratedOutlineSupport1.outline178(outline107, text, printStream);
                }
            }
            if (!validationResult.getNotifications().isEmpty()) {
                System.out.println("Notifications:");
                for (ErrorBundle errorBundle2 : validationResult.getNotifications()) {
                    if (dbgLvl == 3) {
                        printStream6 = System.out;
                        outline1075 = GeneratedOutlineSupport1.outline107("\t\t");
                        text5 = errorBundle2.getDetail(locale);
                    } else {
                        printStream6 = System.out;
                        outline1075 = GeneratedOutlineSupport1.outline107("\t\t");
                        text5 = errorBundle2.getText(locale);
                    }
                    GeneratedOutlineSupport1.outline178(outline1075, text5, printStream6);
                }
            }
            PKIXCertPathReviewer certPathReview = validationResult.getCertPathReview();
            if (certPathReview != null) {
                if (certPathReview.isValidCertPath()) {
                    printStream2 = System.out;
                    str = "Certificate path valid";
                } else {
                    printStream2 = System.out;
                    str = "Certificate path invalid";
                }
                printStream2.println(str);
                System.out.println("\nCertificate path validation results:");
                System.out.println("Errors:");
                for (ErrorBundle errorBundle3 : certPathReview.getErrors(-1)) {
                    if (dbgLvl == 3) {
                        printStream5 = System.out;
                        outline1074 = GeneratedOutlineSupport1.outline107("\t\t");
                        text4 = errorBundle3.getDetail(locale);
                    } else {
                        printStream5 = System.out;
                        outline1074 = GeneratedOutlineSupport1.outline107("\t\t");
                        text4 = errorBundle3.getText(locale);
                    }
                    GeneratedOutlineSupport1.outline178(outline1074, text4, printStream5);
                }
                System.out.println("Notifications:");
                for (ErrorBundle errorBundle4 : certPathReview.getNotifications(-1)) {
                    PrintStream printStream7 = System.out;
                    StringBuilder outline1076 = GeneratedOutlineSupport1.outline107(DeviceDatabaseUtils.DELIMITER);
                    outline1076.append(errorBundle4.getText(locale));
                    printStream7.println(outline1076.toString());
                }
                Iterator<? extends Certificate> it2 = certPathReview.getCertPath().getCertificates().iterator();
                int i = 0;
                while (it2.hasNext()) {
                    X509Certificate x509Certificate = (X509Certificate) it2.next();
                    PrintStream printStream8 = System.out;
                    printStream8.println("\nCertificate " + i + "\n========");
                    PrintStream printStream9 = System.out;
                    StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("Issuer: ");
                    outline1077.append(x509Certificate.getIssuerDN().getName());
                    printStream9.println(outline1077.toString());
                    PrintStream printStream10 = System.out;
                    StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("Subject: ");
                    outline1078.append(x509Certificate.getSubjectDN().getName());
                    printStream10.println(outline1078.toString());
                    System.out.println("\tErrors:");
                    for (ErrorBundle errorBundle5 : certPathReview.getErrors(i)) {
                        if (dbgLvl == 3) {
                            printStream4 = System.out;
                            outline1073 = GeneratedOutlineSupport1.outline107("\t\t");
                            text3 = errorBundle5.getDetail(locale);
                        } else {
                            printStream4 = System.out;
                            outline1073 = GeneratedOutlineSupport1.outline107("\t\t");
                            text3 = errorBundle5.getText(locale);
                        }
                        GeneratedOutlineSupport1.outline178(outline1073, text3, printStream4);
                    }
                    System.out.println("\tNotifications:");
                    for (ErrorBundle errorBundle6 : certPathReview.getNotifications(i)) {
                        if (dbgLvl == 3) {
                            printStream3 = System.out;
                            outline1072 = GeneratedOutlineSupport1.outline107("\t\t");
                            text2 = errorBundle6.getDetail(locale);
                        } else {
                            printStream3 = System.out;
                            outline1072 = GeneratedOutlineSupport1.outline107("\t\t");
                            text2 = errorBundle6.getText(locale);
                        }
                        GeneratedOutlineSupport1.outline178(outline1072, text2, printStream3);
                    }
                    i++;
                }
            }
        }
    }
}
