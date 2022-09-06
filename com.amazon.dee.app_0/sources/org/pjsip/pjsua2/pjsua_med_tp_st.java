package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_med_tp_st {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_med_tp_st PJSUA_MED_TP_NULL = new pjsua_med_tp_st("PJSUA_MED_TP_NULL");
    public static final pjsua_med_tp_st PJSUA_MED_TP_CREATING = new pjsua_med_tp_st("PJSUA_MED_TP_CREATING");
    public static final pjsua_med_tp_st PJSUA_MED_TP_IDLE = new pjsua_med_tp_st("PJSUA_MED_TP_IDLE");
    public static final pjsua_med_tp_st PJSUA_MED_TP_INIT = new pjsua_med_tp_st("PJSUA_MED_TP_INIT");
    public static final pjsua_med_tp_st PJSUA_MED_TP_RUNNING = new pjsua_med_tp_st("PJSUA_MED_TP_RUNNING");
    public static final pjsua_med_tp_st PJSUA_MED_TP_DISABLED = new pjsua_med_tp_st("PJSUA_MED_TP_DISABLED");
    private static pjsua_med_tp_st[] swigValues = {PJSUA_MED_TP_NULL, PJSUA_MED_TP_CREATING, PJSUA_MED_TP_IDLE, PJSUA_MED_TP_INIT, PJSUA_MED_TP_RUNNING, PJSUA_MED_TP_DISABLED};
    private static int swigNext = 0;

    private pjsua_med_tp_st(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_med_tp_st swigToEnum(int i) {
        pjsua_med_tp_st[] pjsua_med_tp_stVarArr = swigValues;
        if (i >= pjsua_med_tp_stVarArr.length || i < 0 || pjsua_med_tp_stVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_med_tp_st[] pjsua_med_tp_stVarArr2 = swigValues;
                if (i2 < pjsua_med_tp_stVarArr2.length) {
                    if (pjsua_med_tp_stVarArr2[i2].swigValue == i) {
                        return pjsua_med_tp_stVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_med_tp_st.class, " with value ", i));
                }
            }
        } else {
            return pjsua_med_tp_stVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_med_tp_st(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_med_tp_st(String str, pjsua_med_tp_st pjsua_med_tp_stVar) {
        this.swigName = str;
        this.swigValue = pjsua_med_tp_stVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
