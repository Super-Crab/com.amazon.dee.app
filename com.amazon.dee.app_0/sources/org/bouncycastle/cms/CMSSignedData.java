package org.bouncycastle.cms;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.SignedData;
import org.bouncycastle.asn1.cms.SignerInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.Store;
/* loaded from: classes4.dex */
public class CMSSignedData implements Encodable {
    private static final CMSSignedHelper HELPER = CMSSignedHelper.INSTANCE;
    ContentInfo contentInfo;
    private Map hashes;
    CMSTypedData signedContent;
    SignedData signedData;
    SignerInformationStore signerInfoStore;

    public CMSSignedData(InputStream inputStream) throws CMSException {
        this(CMSUtils.readContentInfo(inputStream));
    }

    public CMSSignedData(Map map, ContentInfo contentInfo) throws CMSException {
        this.hashes = map;
        this.contentInfo = contentInfo;
        this.signedData = getSignedData();
    }

    public CMSSignedData(Map map, byte[] bArr) throws CMSException {
        this(map, CMSUtils.readContentInfo(bArr));
    }

    public CMSSignedData(ContentInfo contentInfo) throws CMSException {
        this.contentInfo = contentInfo;
        this.signedData = getSignedData();
        ASN1Encodable content = this.signedData.getEncapContentInfo().getContent();
        if (content != null) {
            this.signedContent = content instanceof ASN1OctetString ? new CMSProcessableByteArray(this.signedData.getEncapContentInfo().getContentType(), ((ASN1OctetString) content).getOctets()) : new PKCS7ProcessableObject(this.signedData.getEncapContentInfo().getContentType(), content);
        } else {
            this.signedContent = null;
        }
    }

    public CMSSignedData(CMSProcessable cMSProcessable, InputStream inputStream) throws CMSException {
        this(cMSProcessable, CMSUtils.readContentInfo((InputStream) new ASN1InputStream(inputStream)));
    }

    public CMSSignedData(final CMSProcessable cMSProcessable, ContentInfo contentInfo) throws CMSException {
        if (cMSProcessable instanceof CMSTypedData) {
            this.signedContent = (CMSTypedData) cMSProcessable;
        } else {
            this.signedContent = new CMSTypedData() { // from class: org.bouncycastle.cms.CMSSignedData.1
                @Override // org.bouncycastle.cms.CMSProcessable
                public Object getContent() {
                    return cMSProcessable.getContent();
                }

                @Override // org.bouncycastle.cms.CMSTypedData
                public ASN1ObjectIdentifier getContentType() {
                    return CMSSignedData.this.signedData.getEncapContentInfo().getContentType();
                }

                @Override // org.bouncycastle.cms.CMSProcessable
                public void write(OutputStream outputStream) throws IOException, CMSException {
                    cMSProcessable.write(outputStream);
                }
            };
        }
        this.contentInfo = contentInfo;
        this.signedData = getSignedData();
    }

    public CMSSignedData(CMSProcessable cMSProcessable, byte[] bArr) throws CMSException {
        this(cMSProcessable, CMSUtils.readContentInfo(bArr));
    }

    private CMSSignedData(CMSSignedData cMSSignedData) {
        this.signedData = cMSSignedData.signedData;
        this.contentInfo = cMSSignedData.contentInfo;
        this.signedContent = cMSSignedData.signedContent;
        this.signerInfoStore = cMSSignedData.signerInfoStore;
    }

    public CMSSignedData(byte[] bArr) throws CMSException {
        this(CMSUtils.readContentInfo(bArr));
    }

    private SignedData getSignedData() throws CMSException {
        try {
            return SignedData.getInstance(this.contentInfo.getContent());
        } catch (ClassCastException e) {
            throw new CMSException("Malformed content.", e);
        } catch (IllegalArgumentException e2) {
            throw new CMSException("Malformed content.", e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.bouncycastle.cms.CMSSignedData replaceCertificatesAndCRLs(org.bouncycastle.cms.CMSSignedData r8, org.bouncycastle.util.Store r9, org.bouncycastle.util.Store r10, org.bouncycastle.util.Store r11) throws org.bouncycastle.cms.CMSException {
        /*
            org.bouncycastle.cms.CMSSignedData r0 = new org.bouncycastle.cms.CMSSignedData
            r0.<init>(r8)
            r1 = 0
            if (r9 != 0) goto La
            if (r10 == 0) goto L2d
        La:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            if (r9 == 0) goto L18
            java.util.List r9 = org.bouncycastle.cms.CMSUtils.getCertificatesFromStore(r9)
            r2.addAll(r9)
        L18:
            if (r10 == 0) goto L21
            java.util.List r9 = org.bouncycastle.cms.CMSUtils.getAttributeCertificatesFromStore(r10)
            r2.addAll(r9)
        L21:
            org.bouncycastle.asn1.ASN1Set r9 = org.bouncycastle.cms.CMSUtils.createBerSetFromList(r2)
            int r10 = r9.size()
            if (r10 == 0) goto L2d
            r5 = r9
            goto L2e
        L2d:
            r5 = r1
        L2e:
            if (r11 == 0) goto L40
            java.util.List r9 = org.bouncycastle.cms.CMSUtils.getCRLsFromStore(r11)
            org.bouncycastle.asn1.ASN1Set r9 = org.bouncycastle.cms.CMSUtils.createBerSetFromList(r9)
            int r10 = r9.size()
            if (r10 == 0) goto L40
            r6 = r9
            goto L41
        L40:
            r6 = r1
        L41:
            org.bouncycastle.asn1.cms.SignedData r9 = new org.bouncycastle.asn1.cms.SignedData
            org.bouncycastle.asn1.cms.SignedData r10 = r8.signedData
            org.bouncycastle.asn1.ASN1Set r3 = r10.getDigestAlgorithms()
            org.bouncycastle.asn1.cms.SignedData r10 = r8.signedData
            org.bouncycastle.asn1.cms.ContentInfo r4 = r10.getEncapContentInfo()
            org.bouncycastle.asn1.cms.SignedData r8 = r8.signedData
            org.bouncycastle.asn1.ASN1Set r7 = r8.getSignerInfos()
            r2 = r9
            r2.<init>(r3, r4, r5, r6, r7)
            r0.signedData = r9
            org.bouncycastle.asn1.cms.ContentInfo r8 = new org.bouncycastle.asn1.cms.ContentInfo
            org.bouncycastle.asn1.cms.ContentInfo r9 = r0.contentInfo
            org.bouncycastle.asn1.ASN1ObjectIdentifier r9 = r9.getContentType()
            org.bouncycastle.asn1.cms.SignedData r10 = r0.signedData
            r8.<init>(r9, r10)
            r0.contentInfo = r8
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cms.CMSSignedData.replaceCertificatesAndCRLs(org.bouncycastle.cms.CMSSignedData, org.bouncycastle.util.Store, org.bouncycastle.util.Store, org.bouncycastle.util.Store):org.bouncycastle.cms.CMSSignedData");
    }

    public static CMSSignedData replaceSigners(CMSSignedData cMSSignedData, SignerInformationStore signerInformationStore) {
        CMSSignedData cMSSignedData2 = new CMSSignedData(cMSSignedData);
        cMSSignedData2.signerInfoStore = signerInformationStore;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (SignerInformation signerInformation : signerInformationStore.getSigners()) {
            aSN1EncodableVector.add(CMSSignedHelper.INSTANCE.fixAlgID(signerInformation.getDigestAlgorithmID()));
            aSN1EncodableVector2.add(signerInformation.toASN1Structure());
        }
        DERSet dERSet = new DERSet(aSN1EncodableVector);
        DERSet dERSet2 = new DERSet(aSN1EncodableVector2);
        ASN1Sequence aSN1Sequence = (ASN1Sequence) cMSSignedData.signedData.toASN1Primitive();
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        aSN1EncodableVector3.add(aSN1Sequence.getObjectAt(0));
        aSN1EncodableVector3.add(dERSet);
        for (int i = 2; i != aSN1Sequence.size() - 1; i++) {
            aSN1EncodableVector3.add(aSN1Sequence.getObjectAt(i));
        }
        aSN1EncodableVector3.add(dERSet2);
        cMSSignedData2.signedData = SignedData.getInstance(new BERSequence(aSN1EncodableVector3));
        cMSSignedData2.contentInfo = new ContentInfo(cMSSignedData2.contentInfo.getContentType(), cMSSignedData2.signedData);
        return cMSSignedData2;
    }

    private boolean verifyCounterSignature(SignerInformation signerInformation, SignerInformationVerifierProvider signerInformationVerifierProvider) throws OperatorCreationException, CMSException {
        if (!signerInformation.verify(signerInformationVerifierProvider.get(signerInformation.getSID()))) {
            return false;
        }
        for (SignerInformation signerInformation2 : signerInformation.getCounterSignatures().getSigners()) {
            if (!verifyCounterSignature(signerInformation2, signerInformationVerifierProvider)) {
                return false;
            }
        }
        return true;
    }

    public Store<X509AttributeCertificateHolder> getAttributeCertificates() {
        return HELPER.getAttributeCertificates(this.signedData.getCertificates());
    }

    public Store<X509CRLHolder> getCRLs() {
        return HELPER.getCRLs(this.signedData.getCRLs());
    }

    public Store<X509CertificateHolder> getCertificates() {
        return HELPER.getCertificates(this.signedData.getCertificates());
    }

    public Set<AlgorithmIdentifier> getDigestAlgorithmIDs() {
        HashSet hashSet = new HashSet(this.signedData.getDigestAlgorithms().size());
        Enumeration objects = this.signedData.getDigestAlgorithms().getObjects();
        while (objects.hasMoreElements()) {
            hashSet.add(AlgorithmIdentifier.getInstance(objects.nextElement()));
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return this.contentInfo.getEncoded();
    }

    public byte[] getEncoded(String str) throws IOException {
        return this.contentInfo.getEncoded(str);
    }

    public Store getOtherRevocationInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return HELPER.getOtherRevocationInfo(aSN1ObjectIdentifier, this.signedData.getCRLs());
    }

    public CMSTypedData getSignedContent() {
        return this.signedContent;
    }

    public String getSignedContentTypeOID() {
        return this.signedData.getEncapContentInfo().getContentType().getId();
    }

    public SignerInformationStore getSignerInfos() {
        Map map;
        Object algorithm;
        if (this.signerInfoStore == null) {
            ASN1Set signerInfos = this.signedData.getSignerInfos();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i != signerInfos.size(); i++) {
                SignerInfo signerInfo = SignerInfo.getInstance(signerInfos.getObjectAt(i));
                ASN1ObjectIdentifier contentType = this.signedData.getEncapContentInfo().getContentType();
                Map map2 = this.hashes;
                if (map2 == null) {
                    arrayList.add(new SignerInformation(signerInfo, contentType, this.signedContent, null));
                } else {
                    if (map2.keySet().iterator().next() instanceof String) {
                        map = this.hashes;
                        algorithm = signerInfo.getDigestAlgorithm().getAlgorithm().getId();
                    } else {
                        map = this.hashes;
                        algorithm = signerInfo.getDigestAlgorithm().getAlgorithm();
                    }
                    arrayList.add(new SignerInformation(signerInfo, contentType, null, (byte[]) map.get(algorithm)));
                }
            }
            this.signerInfoStore = new SignerInformationStore(arrayList);
        }
        return this.signerInfoStore;
    }

    public int getVersion() {
        return this.signedData.getVersion().intValueExact();
    }

    public boolean isCertificateManagementMessage() {
        return this.signedData.getEncapContentInfo().getContent() == null && this.signedData.getSignerInfos().size() == 0;
    }

    public boolean isDetachedSignature() {
        return this.signedData.getEncapContentInfo().getContent() == null && this.signedData.getSignerInfos().size() > 0;
    }

    public ContentInfo toASN1Structure() {
        return this.contentInfo;
    }

    public boolean verifySignatures(SignerInformationVerifierProvider signerInformationVerifierProvider) throws CMSException {
        return verifySignatures(signerInformationVerifierProvider, false);
    }

    public boolean verifySignatures(SignerInformationVerifierProvider signerInformationVerifierProvider, boolean z) throws CMSException {
        for (SignerInformation signerInformation : getSignerInfos().getSigners()) {
            try {
                if (!signerInformation.verify(signerInformationVerifierProvider.get(signerInformation.getSID()))) {
                    return false;
                }
                if (!z) {
                    for (SignerInformation signerInformation2 : signerInformation.getCounterSignatures().getSigners()) {
                        if (!verifyCounterSignature(signerInformation2, signerInformationVerifierProvider)) {
                            return false;
                        }
                    }
                    continue;
                }
            } catch (OperatorCreationException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("failure in verifier provider: ");
                outline107.append(e.getMessage());
                throw new CMSException(outline107.toString(), e);
            }
        }
        return true;
    }
}
