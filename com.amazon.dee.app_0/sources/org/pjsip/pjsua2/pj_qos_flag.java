package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pj_qos_flag {
    private final String swigName;
    private final int swigValue;
    public static final pj_qos_flag PJ_QOS_PARAM_HAS_DSCP = new pj_qos_flag("PJ_QOS_PARAM_HAS_DSCP", pjsua2JNI.PJ_QOS_PARAM_HAS_DSCP_get());
    public static final pj_qos_flag PJ_QOS_PARAM_HAS_SO_PRIO = new pj_qos_flag("PJ_QOS_PARAM_HAS_SO_PRIO", pjsua2JNI.PJ_QOS_PARAM_HAS_SO_PRIO_get());
    public static final pj_qos_flag PJ_QOS_PARAM_HAS_WMM = new pj_qos_flag("PJ_QOS_PARAM_HAS_WMM", pjsua2JNI.PJ_QOS_PARAM_HAS_WMM_get());
    private static pj_qos_flag[] swigValues = {PJ_QOS_PARAM_HAS_DSCP, PJ_QOS_PARAM_HAS_SO_PRIO, PJ_QOS_PARAM_HAS_WMM};
    private static int swigNext = 0;

    private pj_qos_flag(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pj_qos_flag swigToEnum(int i) {
        pj_qos_flag[] pj_qos_flagVarArr = swigValues;
        if (i >= pj_qos_flagVarArr.length || i < 0 || pj_qos_flagVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pj_qos_flag[] pj_qos_flagVarArr2 = swigValues;
                if (i2 < pj_qos_flagVarArr2.length) {
                    if (pj_qos_flagVarArr2[i2].swigValue == i) {
                        return pj_qos_flagVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pj_qos_flag.class, " with value ", i));
                }
            }
        } else {
            return pj_qos_flagVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_qos_flag(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_qos_flag(String str, pj_qos_flag pj_qos_flagVar) {
        this.swigName = str;
        this.swigValue = pj_qos_flagVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
