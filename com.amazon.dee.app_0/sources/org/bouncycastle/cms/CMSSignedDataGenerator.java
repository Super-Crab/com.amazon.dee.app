package org.bouncycastle.cms;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class CMSSignedDataGenerator extends CMSSignedGenerator {
    private List signerInfs = new ArrayList();

    public CMSSignedData generate(CMSTypedData cMSTypedData) throws CMSException {
        return generate(cMSTypedData, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.bouncycastle.cms.CMSSignedData generate(org.bouncycastle.cms.CMSTypedData r12, boolean r13) throws org.bouncycastle.cms.CMSException {
        /*
            r11 = this;
            java.util.List r0 = r11.signerInfs
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto Lfa
            org.bouncycastle.asn1.ASN1EncodableVector r0 = new org.bouncycastle.asn1.ASN1EncodableVector
            r0.<init>()
            org.bouncycastle.asn1.ASN1EncodableVector r1 = new org.bouncycastle.asn1.ASN1EncodableVector
            r1.<init>()
            java.util.Map r2 = r11.digests
            r2.clear()
            java.util.List r2 = r11._signers
            java.util.Iterator r2 = r2.iterator()
        L1d:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L3e
            java.lang.Object r3 = r2.next()
            org.bouncycastle.cms.SignerInformation r3 = (org.bouncycastle.cms.SignerInformation) r3
            org.bouncycastle.cms.CMSSignedHelper r4 = org.bouncycastle.cms.CMSSignedHelper.INSTANCE
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r5 = r3.getDigestAlgorithmID()
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r4 = r4.fixAlgID(r5)
            r0.add(r4)
            org.bouncycastle.asn1.cms.SignerInfo r3 = r3.toASN1Structure()
            r1.add(r3)
            goto L1d
        L3e:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r12.getContentType()
            java.lang.Object r3 = r12.getContent()
            r4 = 0
            if (r3 == 0) goto L7f
            if (r13 == 0) goto L51
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream
            r3.<init>()
            goto L52
        L51:
            r3 = r4
        L52:
            java.util.List r5 = r11.signerGens
            java.io.OutputStream r5 = org.bouncycastle.cms.CMSUtils.attachSignersToOutputStream(r5, r3)
            java.io.OutputStream r5 = org.bouncycastle.cms.CMSUtils.getSafeOutputStream(r5)
            r12.write(r5)     // Catch: java.io.IOException -> L6e
            r5.close()     // Catch: java.io.IOException -> L6e
            if (r13 == 0) goto L7f
            org.bouncycastle.asn1.BEROctetString r13 = new org.bouncycastle.asn1.BEROctetString
            byte[] r3 = r3.toByteArray()
            r13.<init>(r3)
            goto L80
        L6e:
            r12 = move-exception
            org.bouncycastle.cms.CMSException r13 = new org.bouncycastle.cms.CMSException
            java.lang.String r0 = "data processing exception: "
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            java.lang.String r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline37(r12, r0)
            r13.<init>(r0, r12)
            throw r13
        L7f:
            r13 = r4
        L80:
            java.util.List r3 = r11.signerGens
            java.util.Iterator r3 = r3.iterator()
        L86:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto Lb8
            java.lang.Object r5 = r3.next()
            org.bouncycastle.cms.SignerInfoGenerator r5 = (org.bouncycastle.cms.SignerInfoGenerator) r5
            org.bouncycastle.asn1.cms.SignerInfo r6 = r5.generate(r2)
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r7 = r6.getDigestAlgorithm()
            r0.add(r7)
            r1.add(r6)
            byte[] r5 = r5.getCalculatedDigest()
            if (r5 == 0) goto L86
            java.util.Map r7 = r11.digests
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r6 = r6.getDigestAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r6 = r6.getAlgorithm()
            java.lang.String r6 = r6.getId()
            r7.put(r6, r5)
            goto L86
        Lb8:
            java.util.List r3 = r11.certs
            int r3 = r3.size()
            if (r3 == 0) goto Lc8
            java.util.List r3 = r11.certs
            org.bouncycastle.asn1.ASN1Set r3 = org.bouncycastle.cms.CMSUtils.createBerSetFromList(r3)
            r8 = r3
            goto Lc9
        Lc8:
            r8 = r4
        Lc9:
            java.util.List r3 = r11.crls
            int r3 = r3.size()
            if (r3 == 0) goto Ld7
            java.util.List r3 = r11.crls
            org.bouncycastle.asn1.ASN1Set r4 = org.bouncycastle.cms.CMSUtils.createBerSetFromList(r3)
        Ld7:
            r9 = r4
            org.bouncycastle.asn1.cms.ContentInfo r7 = new org.bouncycastle.asn1.cms.ContentInfo
            r7.<init>(r2, r13)
            org.bouncycastle.asn1.cms.SignedData r13 = new org.bouncycastle.asn1.cms.SignedData
            org.bouncycastle.asn1.DERSet r6 = new org.bouncycastle.asn1.DERSet
            r6.<init>(r0)
            org.bouncycastle.asn1.DERSet r10 = new org.bouncycastle.asn1.DERSet
            r10.<init>(r1)
            r5 = r13
            r5.<init>(r6, r7, r8, r9, r10)
            org.bouncycastle.asn1.cms.ContentInfo r0 = new org.bouncycastle.asn1.cms.ContentInfo
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = org.bouncycastle.asn1.cms.CMSObjectIdentifiers.signedData
            r0.<init>(r1, r13)
            org.bouncycastle.cms.CMSSignedData r13 = new org.bouncycastle.cms.CMSSignedData
            r13.<init>(r12, r0)
            return r13
        Lfa:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "this method can only be used with SignerInfoGenerator"
            r12.<init>(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cms.CMSSignedDataGenerator.generate(org.bouncycastle.cms.CMSTypedData, boolean):org.bouncycastle.cms.CMSSignedData");
    }

    public SignerInformationStore generateCounterSigners(SignerInformation signerInformation) throws CMSException {
        return generate(new CMSProcessableByteArray(null, signerInformation.getSignature()), false).getSignerInfos();
    }
}
