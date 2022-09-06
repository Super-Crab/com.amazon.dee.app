package org.bouncycastle.asn1.cmc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
/* loaded from: classes4.dex */
public class CMCStatus extends ASN1Object {
    private final ASN1Integer value;
    public static final CMCStatus success = new CMCStatus(new ASN1Integer(0));
    public static final CMCStatus failed = new CMCStatus(new ASN1Integer(2));
    public static final CMCStatus pending = new CMCStatus(new ASN1Integer(3));
    public static final CMCStatus noSupport = new CMCStatus(new ASN1Integer(4));
    public static final CMCStatus confirmRequired = new CMCStatus(new ASN1Integer(5));
    public static final CMCStatus popRequired = new CMCStatus(new ASN1Integer(6));
    public static final CMCStatus partial = new CMCStatus(new ASN1Integer(7));
    private static Map range = new HashMap();

    static {
        Map map = range;
        CMCStatus cMCStatus = success;
        map.put(cMCStatus.value, cMCStatus);
        Map map2 = range;
        CMCStatus cMCStatus2 = failed;
        map2.put(cMCStatus2.value, cMCStatus2);
        Map map3 = range;
        CMCStatus cMCStatus3 = pending;
        map3.put(cMCStatus3.value, cMCStatus3);
        Map map4 = range;
        CMCStatus cMCStatus4 = noSupport;
        map4.put(cMCStatus4.value, cMCStatus4);
        Map map5 = range;
        CMCStatus cMCStatus5 = confirmRequired;
        map5.put(cMCStatus5.value, cMCStatus5);
        Map map6 = range;
        CMCStatus cMCStatus6 = popRequired;
        map6.put(cMCStatus6.value, cMCStatus6);
        Map map7 = range;
        CMCStatus cMCStatus7 = partial;
        map7.put(cMCStatus7.value, cMCStatus7);
    }

    private CMCStatus(ASN1Integer aSN1Integer) {
        this.value = aSN1Integer;
    }

    public static CMCStatus getInstance(Object obj) {
        if (obj instanceof CMCStatus) {
            return (CMCStatus) obj;
        }
        if (obj == null) {
            return null;
        }
        CMCStatus cMCStatus = (CMCStatus) range.get(ASN1Integer.getInstance(obj));
        if (cMCStatus == null) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline45(obj, GeneratedOutlineSupport1.outline107("unknown object in getInstance(): ")));
        }
        return cMCStatus;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.value;
    }
}
