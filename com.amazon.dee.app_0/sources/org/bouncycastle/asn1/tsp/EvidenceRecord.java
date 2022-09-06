package org.bouncycastle.asn1.tsp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes4.dex */
public class EvidenceRecord extends ASN1Object {
    private static final ASN1ObjectIdentifier OID = new ASN1ObjectIdentifier("1.3.6.1.5.5.11.0.2.1");
    private ArchiveTimeStampSequence archiveTimeStampSequence;
    private CryptoInfos cryptoInfos;
    private ASN1Sequence digestAlgorithms;
    private EncryptionInfo encryptionInfo;
    private ASN1Integer version;

    private EvidenceRecord(ASN1Sequence aSN1Sequence) {
        this.version = new ASN1Integer(1L);
        if (aSN1Sequence.size() >= 3 || aSN1Sequence.size() <= 5) {
            ASN1Integer aSN1Integer = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
            if (aSN1Integer.intValueExact() != 1) {
                throw new IllegalArgumentException("incompatible version");
            }
            this.version = aSN1Integer;
            this.digestAlgorithms = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
            for (int i = 2; i != aSN1Sequence.size() - 1; i++) {
                ASN1Encodable objectAt = aSN1Sequence.getObjectAt(i);
                if (!(objectAt instanceof ASN1TaggedObject)) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unknown object in getInstance: ");
                    outline107.append(objectAt.getClass().getName());
                    throw new IllegalArgumentException(outline107.toString());
                }
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objectAt;
                int tagNo = aSN1TaggedObject.getTagNo();
                if (tagNo == 0) {
                    this.cryptoInfos = CryptoInfos.getInstance(aSN1TaggedObject, false);
                } else if (tagNo != 1) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline101(aSN1TaggedObject, GeneratedOutlineSupport1.outline107("unknown tag in getInstance: ")));
                } else {
                    this.encryptionInfo = EncryptionInfo.getInstance(aSN1TaggedObject, false);
                }
            }
            this.archiveTimeStampSequence = ArchiveTimeStampSequence.getInstance(aSN1Sequence.getObjectAt(aSN1Sequence.size() - 1));
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline100(aSN1Sequence, GeneratedOutlineSupport1.outline107("wrong sequence size in constructor: ")));
    }

    private EvidenceRecord(EvidenceRecord evidenceRecord, ArchiveTimeStampSequence archiveTimeStampSequence, ArchiveTimeStamp archiveTimeStamp) {
        ASN1Sequence aSN1Sequence;
        this.version = new ASN1Integer(1L);
        this.version = evidenceRecord.version;
        if (archiveTimeStamp != null) {
            AlgorithmIdentifier digestAlgorithmIdentifier = archiveTimeStamp.getDigestAlgorithmIdentifier();
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            Enumeration objects = evidenceRecord.digestAlgorithms.getObjects();
            boolean z = false;
            while (true) {
                if (!objects.hasMoreElements()) {
                    break;
                }
                AlgorithmIdentifier algorithmIdentifier = AlgorithmIdentifier.getInstance(objects.nextElement());
                aSN1EncodableVector.add(algorithmIdentifier);
                if (algorithmIdentifier.equals(digestAlgorithmIdentifier)) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                aSN1EncodableVector.add(digestAlgorithmIdentifier);
                aSN1Sequence = new DERSequence(aSN1EncodableVector);
                this.digestAlgorithms = aSN1Sequence;
                this.cryptoInfos = evidenceRecord.cryptoInfos;
                this.encryptionInfo = evidenceRecord.encryptionInfo;
                this.archiveTimeStampSequence = archiveTimeStampSequence;
            }
        }
        aSN1Sequence = evidenceRecord.digestAlgorithms;
        this.digestAlgorithms = aSN1Sequence;
        this.cryptoInfos = evidenceRecord.cryptoInfos;
        this.encryptionInfo = evidenceRecord.encryptionInfo;
        this.archiveTimeStampSequence = archiveTimeStampSequence;
    }

    public EvidenceRecord(AlgorithmIdentifier[] algorithmIdentifierArr, CryptoInfos cryptoInfos, EncryptionInfo encryptionInfo, ArchiveTimeStampSequence archiveTimeStampSequence) {
        this.version = new ASN1Integer(1L);
        this.digestAlgorithms = new DERSequence(algorithmIdentifierArr);
        this.cryptoInfos = cryptoInfos;
        this.encryptionInfo = encryptionInfo;
        this.archiveTimeStampSequence = archiveTimeStampSequence;
    }

    public static EvidenceRecord getInstance(Object obj) {
        if (obj instanceof EvidenceRecord) {
            return (EvidenceRecord) obj;
        }
        if (obj == null) {
            return null;
        }
        return new EvidenceRecord(ASN1Sequence.getInstance(obj));
    }

    public static EvidenceRecord getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public EvidenceRecord addArchiveTimeStamp(ArchiveTimeStamp archiveTimeStamp, boolean z) {
        if (z) {
            return new EvidenceRecord(this, this.archiveTimeStampSequence.append(new ArchiveTimeStampChain(archiveTimeStamp)), archiveTimeStamp);
        }
        ArchiveTimeStampChain[] archiveTimeStampChains = this.archiveTimeStampSequence.getArchiveTimeStampChains();
        archiveTimeStampChains[archiveTimeStampChains.length - 1] = archiveTimeStampChains[archiveTimeStampChains.length - 1].append(archiveTimeStamp);
        return new EvidenceRecord(this, new ArchiveTimeStampSequence(archiveTimeStampChains), null);
    }

    public ArchiveTimeStampSequence getArchiveTimeStampSequence() {
        return this.archiveTimeStampSequence;
    }

    public AlgorithmIdentifier[] getDigestAlgorithms() {
        AlgorithmIdentifier[] algorithmIdentifierArr = new AlgorithmIdentifier[this.digestAlgorithms.size()];
        for (int i = 0; i != algorithmIdentifierArr.length; i++) {
            algorithmIdentifierArr[i] = AlgorithmIdentifier.getInstance(this.digestAlgorithms.getObjectAt(i));
        }
        return algorithmIdentifierArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(5);
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(this.digestAlgorithms);
        CryptoInfos cryptoInfos = this.cryptoInfos;
        if (cryptoInfos != null) {
            aSN1EncodableVector.add(cryptoInfos);
        }
        EncryptionInfo encryptionInfo = this.encryptionInfo;
        if (encryptionInfo != null) {
            aSN1EncodableVector.add(encryptionInfo);
        }
        aSN1EncodableVector.add(this.archiveTimeStampSequence);
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline96(GeneratedOutlineSupport1.outline107("EvidenceRecord: Oid("), OID, ")");
    }
}
