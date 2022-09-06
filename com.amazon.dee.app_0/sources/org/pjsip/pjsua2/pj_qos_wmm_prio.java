package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pj_qos_wmm_prio {
    private final String swigName;
    private final int swigValue;
    public static final pj_qos_wmm_prio PJ_QOS_WMM_PRIO_BULK_EFFORT = new pj_qos_wmm_prio("PJ_QOS_WMM_PRIO_BULK_EFFORT");
    public static final pj_qos_wmm_prio PJ_QOS_WMM_PRIO_BULK = new pj_qos_wmm_prio("PJ_QOS_WMM_PRIO_BULK");
    public static final pj_qos_wmm_prio PJ_QOS_WMM_PRIO_VIDEO = new pj_qos_wmm_prio("PJ_QOS_WMM_PRIO_VIDEO");
    public static final pj_qos_wmm_prio PJ_QOS_WMM_PRIO_VOICE = new pj_qos_wmm_prio("PJ_QOS_WMM_PRIO_VOICE");
    private static pj_qos_wmm_prio[] swigValues = {PJ_QOS_WMM_PRIO_BULK_EFFORT, PJ_QOS_WMM_PRIO_BULK, PJ_QOS_WMM_PRIO_VIDEO, PJ_QOS_WMM_PRIO_VOICE};
    private static int swigNext = 0;

    private pj_qos_wmm_prio(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pj_qos_wmm_prio swigToEnum(int i) {
        pj_qos_wmm_prio[] pj_qos_wmm_prioVarArr = swigValues;
        if (i >= pj_qos_wmm_prioVarArr.length || i < 0 || pj_qos_wmm_prioVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pj_qos_wmm_prio[] pj_qos_wmm_prioVarArr2 = swigValues;
                if (i2 < pj_qos_wmm_prioVarArr2.length) {
                    if (pj_qos_wmm_prioVarArr2[i2].swigValue == i) {
                        return pj_qos_wmm_prioVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pj_qos_wmm_prio.class, " with value ", i));
                }
            }
        } else {
            return pj_qos_wmm_prioVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_qos_wmm_prio(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_qos_wmm_prio(String str, pj_qos_wmm_prio pj_qos_wmm_prioVar) {
        this.swigName = str;
        this.swigValue = pj_qos_wmm_prioVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
