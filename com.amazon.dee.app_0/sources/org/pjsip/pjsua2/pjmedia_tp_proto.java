package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjmedia_tp_proto {
    private final String swigName;
    private final int swigValue;
    public static final pjmedia_tp_proto PJMEDIA_TP_PROTO_NONE = new pjmedia_tp_proto("PJMEDIA_TP_PROTO_NONE", pjsua2JNI.PJMEDIA_TP_PROTO_NONE_get());
    public static final pjmedia_tp_proto PJMEDIA_TP_PROTO_RTP_AVP = new pjmedia_tp_proto("PJMEDIA_TP_PROTO_RTP_AVP");
    public static final pjmedia_tp_proto PJMEDIA_TP_PROTO_RTP_SAVP = new pjmedia_tp_proto("PJMEDIA_TP_PROTO_RTP_SAVP");
    public static final pjmedia_tp_proto PJMEDIA_TP_PROTO_UNKNOWN = new pjmedia_tp_proto("PJMEDIA_TP_PROTO_UNKNOWN");
    private static pjmedia_tp_proto[] swigValues = {PJMEDIA_TP_PROTO_NONE, PJMEDIA_TP_PROTO_RTP_AVP, PJMEDIA_TP_PROTO_RTP_SAVP, PJMEDIA_TP_PROTO_UNKNOWN};
    private static int swigNext = 0;

    private pjmedia_tp_proto(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjmedia_tp_proto swigToEnum(int i) {
        pjmedia_tp_proto[] pjmedia_tp_protoVarArr = swigValues;
        if (i >= pjmedia_tp_protoVarArr.length || i < 0 || pjmedia_tp_protoVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjmedia_tp_proto[] pjmedia_tp_protoVarArr2 = swigValues;
                if (i2 < pjmedia_tp_protoVarArr2.length) {
                    if (pjmedia_tp_protoVarArr2[i2].swigValue == i) {
                        return pjmedia_tp_protoVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjmedia_tp_proto.class, " with value ", i));
                }
            }
        } else {
            return pjmedia_tp_protoVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_tp_proto(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_tp_proto(String str, pjmedia_tp_proto pjmedia_tp_protoVar) {
        this.swigName = str;
        this.swigValue = pjmedia_tp_protoVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
