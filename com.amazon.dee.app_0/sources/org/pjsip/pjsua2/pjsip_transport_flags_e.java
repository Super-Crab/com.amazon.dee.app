package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsip_transport_flags_e {
    private final String swigName;
    private final int swigValue;
    public static final pjsip_transport_flags_e PJSIP_TRANSPORT_RELIABLE = new pjsip_transport_flags_e("PJSIP_TRANSPORT_RELIABLE", pjsua2JNI.PJSIP_TRANSPORT_RELIABLE_get());
    public static final pjsip_transport_flags_e PJSIP_TRANSPORT_SECURE = new pjsip_transport_flags_e("PJSIP_TRANSPORT_SECURE", pjsua2JNI.PJSIP_TRANSPORT_SECURE_get());
    public static final pjsip_transport_flags_e PJSIP_TRANSPORT_DATAGRAM = new pjsip_transport_flags_e("PJSIP_TRANSPORT_DATAGRAM", pjsua2JNI.PJSIP_TRANSPORT_DATAGRAM_get());
    private static pjsip_transport_flags_e[] swigValues = {PJSIP_TRANSPORT_RELIABLE, PJSIP_TRANSPORT_SECURE, PJSIP_TRANSPORT_DATAGRAM};
    private static int swigNext = 0;

    private pjsip_transport_flags_e(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsip_transport_flags_e swigToEnum(int i) {
        pjsip_transport_flags_e[] pjsip_transport_flags_eVarArr = swigValues;
        if (i >= pjsip_transport_flags_eVarArr.length || i < 0 || pjsip_transport_flags_eVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsip_transport_flags_e[] pjsip_transport_flags_eVarArr2 = swigValues;
                if (i2 < pjsip_transport_flags_eVarArr2.length) {
                    if (pjsip_transport_flags_eVarArr2[i2].swigValue == i) {
                        return pjsip_transport_flags_eVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsip_transport_flags_e.class, " with value ", i));
                }
            }
        } else {
            return pjsip_transport_flags_eVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_transport_flags_e(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_transport_flags_e(String str, pjsip_transport_flags_e pjsip_transport_flags_eVar) {
        this.swigName = str;
        this.swigValue = pjsip_transport_flags_eVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
