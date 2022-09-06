package org.bouncycastle.asn1.dvcs;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.PolicyInformation;
/* loaded from: classes4.dex */
public class DVCSCertInfo extends ASN1Object {
    private static final int DEFAULT_VERSION = 1;
    private static final int TAG_CERTS = 3;
    private static final int TAG_DV_STATUS = 0;
    private static final int TAG_POLICY = 1;
    private static final int TAG_REQ_SIGNATURE = 2;
    private ASN1Sequence certs;
    private DVCSRequestInformation dvReqInfo;
    private PKIStatusInfo dvStatus;
    private Extensions extensions;
    private DigestInfo messageImprint;
    private PolicyInformation policy;
    private ASN1Set reqSignature;
    private DVCSTime responseTime;
    private ASN1Integer serialNumber;
    private int version;

    private DVCSCertInfo(ASN1Sequence aSN1Sequence) {
        int i;
        this.version = 1;
        ASN1Encodable objectAt = aSN1Sequence.getObjectAt(0);
        try {
            this.version = ASN1Integer.getInstance(objectAt).intValueExact();
            try {
                objectAt = aSN1Sequence.getObjectAt(1);
            } catch (IllegalArgumentException unused) {
            }
            i = 2;
        } catch (IllegalArgumentException unused2) {
            i = 1;
        }
        this.dvReqInfo = DVCSRequestInformation.getInstance(objectAt);
        int i2 = i + 1;
        this.messageImprint = DigestInfo.getInstance(aSN1Sequence.getObjectAt(i));
        int i3 = i2 + 1;
        this.serialNumber = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i2));
        int i4 = i3 + 1;
        this.responseTime = DVCSTime.getInstance(aSN1Sequence.getObjectAt(i3));
        while (i4 < aSN1Sequence.size()) {
            int i5 = i4 + 1;
            ASN1Encodable objectAt2 = aSN1Sequence.getObjectAt(i4);
            if (objectAt2 instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objectAt2);
                int tagNo = aSN1TaggedObject.getTagNo();
                if (tagNo == 0) {
                    this.dvStatus = PKIStatusInfo.getInstance(aSN1TaggedObject, false);
                } else if (tagNo == 1) {
                    this.policy = PolicyInformation.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, false));
                } else if (tagNo == 2) {
                    this.reqSignature = ASN1Set.getInstance(aSN1TaggedObject, false);
                } else if (tagNo != 3) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown tag encountered: ", tagNo));
                } else {
                    this.certs = ASN1Sequence.getInstance(aSN1TaggedObject, false);
                }
            } else {
                try {
                    this.extensions = Extensions.getInstance(objectAt2);
                } catch (IllegalArgumentException unused3) {
                }
            }
            i4 = i5;
        }
    }

    public DVCSCertInfo(DVCSRequestInformation dVCSRequestInformation, DigestInfo digestInfo, ASN1Integer aSN1Integer, DVCSTime dVCSTime) {
        this.version = 1;
        this.dvReqInfo = dVCSRequestInformation;
        this.messageImprint = digestInfo;
        this.serialNumber = aSN1Integer;
        this.responseTime = dVCSTime;
    }

    public static DVCSCertInfo getInstance(Object obj) {
        if (obj instanceof DVCSCertInfo) {
            return (DVCSCertInfo) obj;
        }
        if (obj == null) {
            return null;
        }
        return new DVCSCertInfo(ASN1Sequence.getInstance(obj));
    }

    public static DVCSCertInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    private void setDvReqInfo(DVCSRequestInformation dVCSRequestInformation) {
        this.dvReqInfo = dVCSRequestInformation;
    }

    private void setMessageImprint(DigestInfo digestInfo) {
        this.messageImprint = digestInfo;
    }

    private void setVersion(int i) {
        this.version = i;
    }

    public TargetEtcChain[] getCerts() {
        ASN1Sequence aSN1Sequence = this.certs;
        if (aSN1Sequence != null) {
            return TargetEtcChain.arrayFromSequence(aSN1Sequence);
        }
        return null;
    }

    public DVCSRequestInformation getDvReqInfo() {
        return this.dvReqInfo;
    }

    public PKIStatusInfo getDvStatus() {
        return this.dvStatus;
    }

    public Extensions getExtensions() {
        return this.extensions;
    }

    public DigestInfo getMessageImprint() {
        return this.messageImprint;
    }

    public PolicyInformation getPolicy() {
        return this.policy;
    }

    public ASN1Set getReqSignature() {
        return this.reqSignature;
    }

    public DVCSTime getResponseTime() {
        return this.responseTime;
    }

    public ASN1Integer getSerialNumber() {
        return this.serialNumber;
    }

    public int getVersion() {
        return this.version;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(10);
        int i = this.version;
        if (i != 1) {
            aSN1EncodableVector.add(new ASN1Integer(i));
        }
        aSN1EncodableVector.add(this.dvReqInfo);
        aSN1EncodableVector.add(this.messageImprint);
        aSN1EncodableVector.add(this.serialNumber);
        aSN1EncodableVector.add(this.responseTime);
        PKIStatusInfo pKIStatusInfo = this.dvStatus;
        if (pKIStatusInfo != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, pKIStatusInfo));
        }
        PolicyInformation policyInformation = this.policy;
        if (policyInformation != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, policyInformation));
        }
        ASN1Set aSN1Set = this.reqSignature;
        if (aSN1Set != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, aSN1Set));
        }
        ASN1Sequence aSN1Sequence = this.certs;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 3, aSN1Sequence));
        }
        Extensions extensions = this.extensions;
        if (extensions != null) {
            aSN1EncodableVector.add(extensions);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("DVCSCertInfo {\n");
        if (this.version != 1) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("version: ");
            outline107.append(this.version);
            outline107.append("\n");
            outline103.append(outline107.toString());
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("dvReqInfo: ");
        outline1072.append(this.dvReqInfo);
        outline1072.append("\n");
        outline103.append(outline1072.toString());
        outline103.append("messageImprint: " + this.messageImprint + "\n");
        outline103.append("serialNumber: " + this.serialNumber + "\n");
        outline103.append("responseTime: " + this.responseTime + "\n");
        if (this.dvStatus != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("dvStatus: ");
            outline1073.append(this.dvStatus);
            outline1073.append("\n");
            outline103.append(outline1073.toString());
        }
        if (this.policy != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("policy: ");
            outline1074.append(this.policy);
            outline1074.append("\n");
            outline103.append(outline1074.toString());
        }
        if (this.reqSignature != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("reqSignature: ");
            outline1075.append(this.reqSignature);
            outline1075.append("\n");
            outline103.append(outline1075.toString());
        }
        if (this.certs != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("certs: ");
            outline1076.append(this.certs);
            outline1076.append("\n");
            outline103.append(outline1076.toString());
        }
        if (this.extensions != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("extensions: ");
            outline1077.append(this.extensions);
            outline1077.append("\n");
            outline103.append(outline1077.toString());
        }
        outline103.append("}\n");
        return outline103.toString();
    }
}
