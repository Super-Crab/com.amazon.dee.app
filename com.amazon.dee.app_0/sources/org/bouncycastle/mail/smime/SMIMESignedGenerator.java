package org.bouncycastle.mail.smime;

import com.amazon.alexa.fitness.utils.FullScreenUpdaterUtilKt;
import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedDataStreamGenerator;
import org.bouncycastle.cms.SignerInfoGenerator;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.mail.smime.SMIMEUtil;
import org.bouncycastle.mail.smime.util.CRLFOutputStream;
import org.bouncycastle.util.Store;
/* loaded from: classes4.dex */
public class SMIMESignedGenerator extends SMIMEGenerator {
    private static final String CERTIFICATE_MANAGEMENT_CONTENT = "application/pkcs7-mime; name=smime.p7c; smime-type=certs-only";
    private static final String DETACHED_SIGNATURE_TYPE = "application/pkcs7-signature; name=smime.p7s; smime-type=signed-data";
    private static final String ENCAPSULATED_SIGNED_CONTENT_TYPE = "application/pkcs7-mime; name=smime.p7m; smime-type=signed-data";
    public static final Map RFC3851_MICALGS;
    public static final Map RFC5751_MICALGS;
    public static final Map STANDARD_MICALGS;
    private Map _digests;
    private List _oldSigners;
    private List _signers;
    private List attrCertStores;
    private List certStores;
    private List crlStores;
    private final String defaultContentTransferEncoding;
    private final Map micAlgs;
    private List signerInfoGens;
    public static final String DIGEST_SHA1 = OIWObjectIdentifiers.idSHA1.getId();
    public static final String DIGEST_MD5 = PKCSObjectIdentifiers.md5.getId();
    public static final String DIGEST_SHA224 = NISTObjectIdentifiers.id_sha224.getId();
    public static final String DIGEST_SHA256 = NISTObjectIdentifiers.id_sha256.getId();
    public static final String DIGEST_SHA384 = NISTObjectIdentifiers.id_sha384.getId();
    public static final String DIGEST_SHA512 = NISTObjectIdentifiers.id_sha512.getId();
    public static final String DIGEST_GOST3411 = CryptoProObjectIdentifiers.gostR3411.getId();
    public static final String DIGEST_RIPEMD128 = TeleTrusTObjectIdentifiers.ripemd128.getId();
    public static final String DIGEST_RIPEMD160 = TeleTrusTObjectIdentifiers.ripemd160.getId();
    public static final String DIGEST_RIPEMD256 = TeleTrusTObjectIdentifiers.ripemd256.getId();
    public static final String ENCRYPTION_RSA = PKCSObjectIdentifiers.rsaEncryption.getId();
    public static final String ENCRYPTION_DSA = X9ObjectIdentifiers.id_dsa_with_sha1.getId();
    public static final String ENCRYPTION_ECDSA = X9ObjectIdentifiers.ecdsa_with_SHA1.getId();
    public static final String ENCRYPTION_RSA_PSS = PKCSObjectIdentifiers.id_RSASSA_PSS.getId();
    public static final String ENCRYPTION_GOST3410 = CryptoProObjectIdentifiers.gostR3410_94.getId();
    public static final String ENCRYPTION_ECGOST3410 = CryptoProObjectIdentifiers.gostR3410_2001.getId();
    public static final String ENCRYPTION_ECGOST3410_2012_256 = RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256.getId();
    public static final String ENCRYPTION_ECGOST3410_2012_512 = RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512.getId();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class ContentSigner implements SMIMEStreamingProcessor {
        private final MimeBodyPart content;
        private final boolean encapsulate;
        private final boolean noProvider = true;

        ContentSigner(MimeBodyPart mimeBodyPart, boolean z) {
            this.content = mimeBodyPart;
            this.encapsulate = z;
        }

        private void writeBodyPart(OutputStream outputStream, MimeBodyPart mimeBodyPart) throws IOException, MessagingException {
            if (!SMIMEUtil.isMultipartContent(mimeBodyPart)) {
                if (SMIMEUtil.isCanonicalisationRequired(mimeBodyPart, SMIMESignedGenerator.this.defaultContentTransferEncoding)) {
                    outputStream = new CRLFOutputStream(outputStream);
                }
                mimeBodyPart.writeTo(outputStream);
                return;
            }
            Object content = mimeBodyPart.getContent();
            Multipart mimeMultipart = content instanceof Multipart ? (Multipart) content : new MimeMultipart(mimeBodyPart.getDataHandler().getDataSource());
            ContentType contentType = new ContentType(mimeMultipart.getContentType());
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(FullScreenUpdaterUtilKt.DEFAULT_DATA);
            outline107.append(contentType.getParameter("boundary"));
            String sb = outline107.toString();
            SMIMEUtil.LineOutputStream lineOutputStream = new SMIMEUtil.LineOutputStream(outputStream);
            Enumeration allHeaderLines = mimeBodyPart.getAllHeaderLines();
            while (allHeaderLines.hasMoreElements()) {
                lineOutputStream.writeln((String) allHeaderLines.nextElement());
            }
            lineOutputStream.writeln();
            SMIMEUtil.outputPreamble(lineOutputStream, mimeBodyPart, sb);
            for (int i = 0; i < mimeMultipart.getCount(); i++) {
                lineOutputStream.writeln(sb);
                writeBodyPart(outputStream, (MimeBodyPart) mimeMultipart.getBodyPart(i));
                lineOutputStream.writeln();
            }
            lineOutputStream.writeln(GeneratedOutlineSupport1.outline72(sb, FullScreenUpdaterUtilKt.DEFAULT_DATA));
        }

        protected CMSSignedDataStreamGenerator getGenerator() throws CMSException {
            CMSSignedDataStreamGenerator cMSSignedDataStreamGenerator = new CMSSignedDataStreamGenerator();
            for (Store store : SMIMESignedGenerator.this.certStores) {
                cMSSignedDataStreamGenerator.addCertificates(store);
            }
            for (Store store2 : SMIMESignedGenerator.this.crlStores) {
                cMSSignedDataStreamGenerator.addCRLs(store2);
            }
            for (Store store3 : SMIMESignedGenerator.this.attrCertStores) {
                cMSSignedDataStreamGenerator.addAttributeCertificates(store3);
            }
            for (SignerInfoGenerator signerInfoGenerator : SMIMESignedGenerator.this.signerInfoGens) {
                cMSSignedDataStreamGenerator.addSignerInfoGenerator(signerInfoGenerator);
            }
            cMSSignedDataStreamGenerator.addSigners(new SignerInformationStore(SMIMESignedGenerator.this._oldSigners));
            return cMSSignedDataStreamGenerator;
        }

        @Override // org.bouncycastle.mail.smime.SMIMEStreamingProcessor
        public void write(OutputStream outputStream) throws IOException {
            try {
                CMSSignedDataStreamGenerator generator = getGenerator();
                OutputStream open = generator.open(outputStream, this.encapsulate);
                if (this.content != null) {
                    if (!this.encapsulate) {
                        writeBodyPart(open, this.content);
                    } else {
                        MailcapCommandMap defaultCommandMap = CommandMap.getDefaultCommandMap();
                        if (defaultCommandMap instanceof MailcapCommandMap) {
                            this.content.getDataHandler().setCommandMap(MailcapUtil.addCommands(defaultCommandMap));
                        }
                        this.content.writeTo(open);
                    }
                }
                open.close();
                SMIMESignedGenerator.this._digests = generator.getGeneratedDigests();
            } catch (MessagingException e) {
                throw new IOException(e.toString());
            } catch (CMSException e2) {
                throw new IOException(e2.toString());
            }
        }
    }

    static {
        AccessController.doPrivileged(new PrivilegedAction() { // from class: org.bouncycastle.mail.smime.SMIMESignedGenerator.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                MailcapCommandMap defaultCommandMap = CommandMap.getDefaultCommandMap();
                if (defaultCommandMap instanceof MailcapCommandMap) {
                    CommandMap.setDefaultCommandMap(MailcapUtil.addCommands(defaultCommandMap));
                    return null;
                }
                return null;
            }
        });
        HashMap hashMap = new HashMap();
        hashMap.put(CMSAlgorithm.MD5, SierraContentProviderContract.MD5_VALUE);
        hashMap.put(CMSAlgorithm.SHA1, "sha-1");
        hashMap.put(CMSAlgorithm.SHA224, "sha-224");
        hashMap.put(CMSAlgorithm.SHA256, "sha-256");
        hashMap.put(CMSAlgorithm.SHA384, "sha-384");
        hashMap.put(CMSAlgorithm.SHA512, "sha-512");
        hashMap.put(CMSAlgorithm.GOST3411, "gostr3411-94");
        hashMap.put(CMSAlgorithm.GOST3411_2012_256, "gostr3411-2012-256");
        hashMap.put(CMSAlgorithm.GOST3411_2012_512, "gostr3411-2012-512");
        RFC5751_MICALGS = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(CMSAlgorithm.MD5, SierraContentProviderContract.MD5_VALUE);
        hashMap2.put(CMSAlgorithm.SHA1, "sha1");
        hashMap2.put(CMSAlgorithm.SHA224, "sha224");
        hashMap2.put(CMSAlgorithm.SHA256, "sha256");
        hashMap2.put(CMSAlgorithm.SHA384, "sha384");
        hashMap2.put(CMSAlgorithm.SHA512, "sha512");
        hashMap2.put(CMSAlgorithm.GOST3411, "gostr3411-94");
        hashMap2.put(CMSAlgorithm.GOST3411_2012_256, "gostr3411-2012-256");
        hashMap2.put(CMSAlgorithm.GOST3411_2012_512, "gostr3411-2012-512");
        RFC3851_MICALGS = Collections.unmodifiableMap(hashMap2);
        STANDARD_MICALGS = RFC5751_MICALGS;
    }

    public SMIMESignedGenerator() {
        this("7bit", STANDARD_MICALGS);
    }

    public SMIMESignedGenerator(String str) {
        this(str, STANDARD_MICALGS);
    }

    public SMIMESignedGenerator(String str, Map map) {
        this.certStores = new ArrayList();
        this.crlStores = new ArrayList();
        this.attrCertStores = new ArrayList();
        this.signerInfoGens = new ArrayList();
        this._signers = new ArrayList();
        this._oldSigners = new ArrayList();
        this._digests = new HashMap();
        this.defaultContentTransferEncoding = str;
        this.micAlgs = map;
    }

    public SMIMESignedGenerator(Map map) {
        this("7bit", map);
    }

    private void addHashHeader(StringBuffer stringBuffer, List list) {
        TreeSet<String> treeSet = new TreeSet();
        for (Object obj : list) {
            String str = (String) this.micAlgs.get((obj instanceof SignerInformation ? ((SignerInformation) obj).getDigestAlgorithmID() : ((SignerInfoGenerator) obj).getDigestAlgorithm()).getAlgorithm());
            if (str == null) {
                str = "unknown";
            }
            treeSet.add(str);
        }
        int i = 0;
        for (String str2 : treeSet) {
            if (i == 0) {
                stringBuffer.append(treeSet.size() != 1 ? "; micalg=\"" : "; micalg=");
            } else {
                stringBuffer.append(JsonReaderKt.COMMA);
            }
            stringBuffer.append(str2);
            i++;
        }
        if (i == 0 || treeSet.size() == 1) {
            return;
        }
        stringBuffer.append('\"');
    }

    private MimeMultipart make(MimeBodyPart mimeBodyPart) throws SMIMEException {
        try {
            MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
            mimeBodyPart2.setContent(new ContentSigner(mimeBodyPart, false), DETACHED_SIGNATURE_TYPE);
            mimeBodyPart2.addHeader("Content-Type", DETACHED_SIGNATURE_TYPE);
            mimeBodyPart2.addHeader("Content-Disposition", "attachment; filename=\"smime.p7s\"");
            mimeBodyPart2.addHeader("Content-Description", "S/MIME Cryptographic Signature");
            mimeBodyPart2.addHeader("Content-Transfer-Encoding", this.encoding);
            StringBuffer stringBuffer = new StringBuffer("signed; protocol=\"application/pkcs7-signature\"");
            ArrayList arrayList = new ArrayList(this._signers);
            arrayList.addAll(this._oldSigners);
            arrayList.addAll(this.signerInfoGens);
            addHashHeader(stringBuffer, arrayList);
            MimeMultipart mimeMultipart = new MimeMultipart(stringBuffer.toString());
            mimeMultipart.addBodyPart(mimeBodyPart);
            mimeMultipart.addBodyPart(mimeBodyPart2);
            return mimeMultipart;
        } catch (MessagingException e) {
            throw new SMIMEException("exception putting multi-part together.", e);
        }
    }

    private MimeBodyPart makeEncapsulated(MimeBodyPart mimeBodyPart) throws SMIMEException {
        try {
            MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
            mimeBodyPart2.setContent(new ContentSigner(mimeBodyPart, true), ENCAPSULATED_SIGNED_CONTENT_TYPE);
            mimeBodyPart2.addHeader("Content-Type", ENCAPSULATED_SIGNED_CONTENT_TYPE);
            mimeBodyPart2.addHeader("Content-Disposition", "attachment; filename=\"smime.p7m\"");
            mimeBodyPart2.addHeader("Content-Description", "S/MIME Cryptographic Signed Data");
            mimeBodyPart2.addHeader("Content-Transfer-Encoding", this.encoding);
            return mimeBodyPart2;
        } catch (MessagingException e) {
            throw new SMIMEException("exception putting body part together.", e);
        }
    }

    public void addAttributeCertificates(Store store) {
        this.attrCertStores.add(store);
    }

    public void addCRLs(Store store) {
        this.crlStores.add(store);
    }

    public void addCertificates(Store store) {
        this.certStores.add(store);
    }

    public void addSignerInfoGenerator(SignerInfoGenerator signerInfoGenerator) {
        this.signerInfoGens.add(signerInfoGenerator);
    }

    public void addSigners(SignerInformationStore signerInformationStore) {
        for (SignerInformation signerInformation : signerInformationStore.getSigners()) {
            this._oldSigners.add(signerInformation);
        }
    }

    public MimeMultipart generate(MimeBodyPart mimeBodyPart) throws SMIMEException {
        return make(makeContentBodyPart(mimeBodyPart));
    }

    public MimeMultipart generate(MimeMessage mimeMessage) throws SMIMEException {
        try {
            mimeMessage.saveChanges();
            return make(makeContentBodyPart(mimeMessage));
        } catch (MessagingException e) {
            throw new SMIMEException("unable to save message", e);
        }
    }

    public MimeBodyPart generateCertificateManagement() throws SMIMEException {
        try {
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(new ContentSigner(null, true), CERTIFICATE_MANAGEMENT_CONTENT);
            mimeBodyPart.addHeader("Content-Type", CERTIFICATE_MANAGEMENT_CONTENT);
            mimeBodyPart.addHeader("Content-Disposition", "attachment; filename=\"smime.p7c\"");
            mimeBodyPart.addHeader("Content-Description", "S/MIME Certificate Management Message");
            mimeBodyPart.addHeader("Content-Transfer-Encoding", this.encoding);
            return mimeBodyPart;
        } catch (MessagingException e) {
            throw new SMIMEException("exception putting body part together.", e);
        }
    }

    public MimeBodyPart generateEncapsulated(MimeBodyPart mimeBodyPart) throws SMIMEException {
        return makeEncapsulated(makeContentBodyPart(mimeBodyPart));
    }

    public MimeBodyPart generateEncapsulated(MimeMessage mimeMessage) throws SMIMEException {
        try {
            mimeMessage.saveChanges();
            return makeEncapsulated(makeContentBodyPart(mimeMessage));
        } catch (MessagingException e) {
            throw new SMIMEException("unable to save message", e);
        }
    }

    public Map getGeneratedDigests() {
        return new HashMap(this._digests);
    }
}
