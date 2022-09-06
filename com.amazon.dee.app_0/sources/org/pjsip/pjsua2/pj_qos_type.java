package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pj_qos_type {
    private final String swigName;
    private final int swigValue;
    public static final pj_qos_type PJ_QOS_TYPE_BEST_EFFORT = new pj_qos_type("PJ_QOS_TYPE_BEST_EFFORT");
    public static final pj_qos_type PJ_QOS_TYPE_BACKGROUND = new pj_qos_type("PJ_QOS_TYPE_BACKGROUND");
    public static final pj_qos_type PJ_QOS_TYPE_VIDEO = new pj_qos_type("PJ_QOS_TYPE_VIDEO");
    public static final pj_qos_type PJ_QOS_TYPE_VOICE = new pj_qos_type("PJ_QOS_TYPE_VOICE");
    public static final pj_qos_type PJ_QOS_TYPE_CONTROL = new pj_qos_type("PJ_QOS_TYPE_CONTROL");
    private static pj_qos_type[] swigValues = {PJ_QOS_TYPE_BEST_EFFORT, PJ_QOS_TYPE_BACKGROUND, PJ_QOS_TYPE_VIDEO, PJ_QOS_TYPE_VOICE, PJ_QOS_TYPE_CONTROL};
    private static int swigNext = 0;

    private pj_qos_type(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pj_qos_type swigToEnum(int i) {
        pj_qos_type[] pj_qos_typeVarArr = swigValues;
        if (i >= pj_qos_typeVarArr.length || i < 0 || pj_qos_typeVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pj_qos_type[] pj_qos_typeVarArr2 = swigValues;
                if (i2 < pj_qos_typeVarArr2.length) {
                    if (pj_qos_typeVarArr2[i2].swigValue == i) {
                        return pj_qos_typeVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pj_qos_type.class, " with value ", i));
                }
            }
        } else {
            return pj_qos_typeVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_qos_type(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_qos_type(String str, pj_qos_type pj_qos_typeVar) {
        this.swigName = str;
        this.swigValue = pj_qos_typeVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
