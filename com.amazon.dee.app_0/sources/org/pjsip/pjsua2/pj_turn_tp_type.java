package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pj_turn_tp_type {
    private final String swigName;
    private final int swigValue;
    public static final pj_turn_tp_type PJ_TURN_TP_UDP = new pj_turn_tp_type("PJ_TURN_TP_UDP", pjsua2JNI.PJ_TURN_TP_UDP_get());
    public static final pj_turn_tp_type PJ_TURN_TP_TCP = new pj_turn_tp_type("PJ_TURN_TP_TCP", pjsua2JNI.PJ_TURN_TP_TCP_get());
    public static final pj_turn_tp_type PJ_TURN_TP_TLS = new pj_turn_tp_type("PJ_TURN_TP_TLS", pjsua2JNI.PJ_TURN_TP_TLS_get());
    private static pj_turn_tp_type[] swigValues = {PJ_TURN_TP_UDP, PJ_TURN_TP_TCP, PJ_TURN_TP_TLS};
    private static int swigNext = 0;

    private pj_turn_tp_type(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pj_turn_tp_type swigToEnum(int i) {
        pj_turn_tp_type[] pj_turn_tp_typeVarArr = swigValues;
        if (i >= pj_turn_tp_typeVarArr.length || i < 0 || pj_turn_tp_typeVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pj_turn_tp_type[] pj_turn_tp_typeVarArr2 = swigValues;
                if (i2 < pj_turn_tp_typeVarArr2.length) {
                    if (pj_turn_tp_typeVarArr2[i2].swigValue == i) {
                        return pj_turn_tp_typeVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pj_turn_tp_type.class, " with value ", i));
                }
            }
        } else {
            return pj_turn_tp_typeVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_turn_tp_type(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_turn_tp_type(String str, pj_turn_tp_type pj_turn_tp_typeVar) {
        this.swigName = str;
        this.swigValue = pj_turn_tp_typeVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
