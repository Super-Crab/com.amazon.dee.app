package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsip_transport_type_e {
    private final String swigName;
    private final int swigValue;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_UNSPECIFIED = new pjsip_transport_type_e("PJSIP_TRANSPORT_UNSPECIFIED");
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_UDP = new pjsip_transport_type_e("PJSIP_TRANSPORT_UDP");
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_TCP = new pjsip_transport_type_e("PJSIP_TRANSPORT_TCP");
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_TLS = new pjsip_transport_type_e("PJSIP_TRANSPORT_TLS");
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_SCTP = new pjsip_transport_type_e("PJSIP_TRANSPORT_SCTP");
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_LOOP = new pjsip_transport_type_e("PJSIP_TRANSPORT_LOOP");
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_LOOP_DGRAM = new pjsip_transport_type_e("PJSIP_TRANSPORT_LOOP_DGRAM");
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_START_OTHER = new pjsip_transport_type_e("PJSIP_TRANSPORT_START_OTHER");
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_IPV6 = new pjsip_transport_type_e("PJSIP_TRANSPORT_IPV6", pjsua2JNI.PJSIP_TRANSPORT_IPV6_get());
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_UDP6 = new pjsip_transport_type_e("PJSIP_TRANSPORT_UDP6", pjsua2JNI.PJSIP_TRANSPORT_UDP6_get());
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_TCP6 = new pjsip_transport_type_e("PJSIP_TRANSPORT_TCP6", pjsua2JNI.PJSIP_TRANSPORT_TCP6_get());
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_TLS6 = new pjsip_transport_type_e("PJSIP_TRANSPORT_TLS6", pjsua2JNI.PJSIP_TRANSPORT_TLS6_get());
    private static pjsip_transport_type_e[] swigValues = {PJSIP_TRANSPORT_UNSPECIFIED, PJSIP_TRANSPORT_UDP, PJSIP_TRANSPORT_TCP, PJSIP_TRANSPORT_TLS, PJSIP_TRANSPORT_SCTP, PJSIP_TRANSPORT_LOOP, PJSIP_TRANSPORT_LOOP_DGRAM, PJSIP_TRANSPORT_START_OTHER, PJSIP_TRANSPORT_IPV6, PJSIP_TRANSPORT_UDP6, PJSIP_TRANSPORT_TCP6, PJSIP_TRANSPORT_TLS6};
    private static int swigNext = 0;

    private pjsip_transport_type_e(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsip_transport_type_e swigToEnum(int i) {
        pjsip_transport_type_e[] pjsip_transport_type_eVarArr = swigValues;
        if (i >= pjsip_transport_type_eVarArr.length || i < 0 || pjsip_transport_type_eVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsip_transport_type_e[] pjsip_transport_type_eVarArr2 = swigValues;
                if (i2 < pjsip_transport_type_eVarArr2.length) {
                    if (pjsip_transport_type_eVarArr2[i2].swigValue == i) {
                        return pjsip_transport_type_eVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsip_transport_type_e.class, " with value ", i));
                }
            }
        } else {
            return pjsip_transport_type_eVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_transport_type_e(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_transport_type_e(String str, pjsip_transport_type_e pjsip_transport_type_eVar) {
        this.swigName = str;
        this.swigValue = pjsip_transport_type_eVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
