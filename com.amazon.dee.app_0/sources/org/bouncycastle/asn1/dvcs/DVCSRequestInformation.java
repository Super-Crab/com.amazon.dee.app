package org.bouncycastle.asn1.dvcs;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.PolicyInformation;
/* loaded from: classes4.dex */
public class DVCSRequestInformation extends ASN1Object {
    private static final int DEFAULT_VERSION = 1;
    private static final int TAG_DATA_LOCATIONS = 3;
    private static final int TAG_DVCS = 2;
    private static final int TAG_EXTENSIONS = 4;
    private static final int TAG_REQUESTER = 0;
    private static final int TAG_REQUEST_POLICY = 1;
    private GeneralNames dataLocations;
    private GeneralNames dvcs;
    private Extensions extensions;
    private BigInteger nonce;
    private PolicyInformation requestPolicy;
    private DVCSTime requestTime;
    private GeneralNames requester;
    private ServiceType service;
    private int version;

    private DVCSRequestInformation(ASN1Sequence aSN1Sequence) {
        int i;
        this.version = 1;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) {
            this.version = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).intValueExact();
            i = 1;
        } else {
            this.version = 1;
            i = 0;
        }
        this.service = ServiceType.getInstance(aSN1Sequence.getObjectAt(i));
        for (int i2 = i + 1; i2 < aSN1Sequence.size(); i2++) {
            ASN1Encodable objectAt = aSN1Sequence.getObjectAt(i2);
            if (objectAt instanceof ASN1Integer) {
                this.nonce = ASN1Integer.getInstance(objectAt).getValue();
            } else if (!(objectAt instanceof ASN1GeneralizedTime) && (objectAt instanceof ASN1TaggedObject)) {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objectAt);
                int tagNo = aSN1TaggedObject.getTagNo();
                if (tagNo == 0) {
                    this.requester = GeneralNames.getInstance(aSN1TaggedObject, false);
                } else if (tagNo == 1) {
                    this.requestPolicy = PolicyInformation.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, false));
                } else if (tagNo == 2) {
                    this.dvcs = GeneralNames.getInstance(aSN1TaggedObject, false);
                } else if (tagNo == 3) {
                    this.dataLocations = GeneralNames.getInstance(aSN1TaggedObject, false);
                } else if (tagNo != 4) {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("unknown tag number encountered: ", tagNo));
                } else {
                    this.extensions = Extensions.getInstance(aSN1TaggedObject, false);
                }
            } else {
                this.requestTime = DVCSTime.getInstance(objectAt);
            }
        }
    }

    public static DVCSRequestInformation getInstance(Object obj) {
        if (obj instanceof DVCSRequestInformation) {
            return (DVCSRequestInformation) obj;
        }
        if (obj == null) {
            return null;
        }
        return new DVCSRequestInformation(ASN1Sequence.getInstance(obj));
    }

    public static DVCSRequestInformation getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public GeneralNames getDVCS() {
        return this.dvcs;
    }

    public GeneralNames getDataLocations() {
        return this.dataLocations;
    }

    public Extensions getExtensions() {
        return this.extensions;
    }

    public BigInteger getNonce() {
        return this.nonce;
    }

    public PolicyInformation getRequestPolicy() {
        return this.requestPolicy;
    }

    public DVCSTime getRequestTime() {
        return this.requestTime;
    }

    public GeneralNames getRequester() {
        return this.requester;
    }

    public ServiceType getService() {
        return this.service;
    }

    public int getVersion() {
        return this.version;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(9);
        int i = this.version;
        if (i != 1) {
            aSN1EncodableVector.add(new ASN1Integer(i));
        }
        aSN1EncodableVector.add(this.service);
        BigInteger bigInteger = this.nonce;
        if (bigInteger != null) {
            aSN1EncodableVector.add(new ASN1Integer(bigInteger));
        }
        DVCSTime dVCSTime = this.requestTime;
        if (dVCSTime != null) {
            aSN1EncodableVector.add(dVCSTime);
        }
        int[] iArr = {0, 1, 2, 3, 4};
        ASN1Encodable[] aSN1EncodableArr = {this.requester, this.requestPolicy, this.dvcs, this.dataLocations, this.extensions};
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            ASN1Encodable aSN1Encodable = aSN1EncodableArr[i2];
            if (aSN1Encodable != null) {
                aSN1EncodableVector.add(new DERTaggedObject(false, i3, aSN1Encodable));
            }
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103("DVCSRequestInformation {\n");
        if (this.version != 1) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("version: ");
            outline107.append(this.version);
            outline107.append("\n");
            outline103.append(outline107.toString());
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("service: ");
        outline1072.append(this.service);
        outline1072.append("\n");
        outline103.append(outline1072.toString());
        if (this.nonce != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nonce: ");
            outline1073.append(this.nonce);
            outline1073.append("\n");
            outline103.append(outline1073.toString());
        }
        if (this.requestTime != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("requestTime: ");
            outline1074.append(this.requestTime);
            outline1074.append("\n");
            outline103.append(outline1074.toString());
        }
        if (this.requester != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("requester: ");
            outline1075.append(this.requester);
            outline1075.append("\n");
            outline103.append(outline1075.toString());
        }
        if (this.requestPolicy != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("requestPolicy: ");
            outline1076.append(this.requestPolicy);
            outline1076.append("\n");
            outline103.append(outline1076.toString());
        }
        if (this.dvcs != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("dvcs: ");
            outline1077.append(this.dvcs);
            outline1077.append("\n");
            outline103.append(outline1077.toString());
        }
        if (this.dataLocations != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("dataLocations: ");
            outline1078.append(this.dataLocations);
            outline1078.append("\n");
            outline103.append(outline1078.toString());
        }
        if (this.extensions != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("extensions: ");
            outline1079.append(this.extensions);
            outline1079.append("\n");
            outline103.append(outline1079.toString());
        }
        outline103.append("}\n");
        return outline103.toString();
    }
}
