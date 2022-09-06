package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_state {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_state PJSUA_STATE_NULL = new pjsua_state("PJSUA_STATE_NULL");
    public static final pjsua_state PJSUA_STATE_CREATED = new pjsua_state("PJSUA_STATE_CREATED");
    public static final pjsua_state PJSUA_STATE_INIT = new pjsua_state("PJSUA_STATE_INIT");
    public static final pjsua_state PJSUA_STATE_STARTING = new pjsua_state("PJSUA_STATE_STARTING");
    public static final pjsua_state PJSUA_STATE_RUNNING = new pjsua_state("PJSUA_STATE_RUNNING");
    public static final pjsua_state PJSUA_STATE_CLOSING = new pjsua_state("PJSUA_STATE_CLOSING");
    private static pjsua_state[] swigValues = {PJSUA_STATE_NULL, PJSUA_STATE_CREATED, PJSUA_STATE_INIT, PJSUA_STATE_STARTING, PJSUA_STATE_RUNNING, PJSUA_STATE_CLOSING};
    private static int swigNext = 0;

    private pjsua_state(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_state swigToEnum(int i) {
        pjsua_state[] pjsua_stateVarArr = swigValues;
        if (i >= pjsua_stateVarArr.length || i < 0 || pjsua_stateVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_state[] pjsua_stateVarArr2 = swigValues;
                if (i2 < pjsua_stateVarArr2.length) {
                    if (pjsua_stateVarArr2[i2].swigValue == i) {
                        return pjsua_stateVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_state.class, " with value ", i));
                }
            }
        } else {
            return pjsua_stateVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_state(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_state(String str, pjsua_state pjsua_stateVar) {
        this.swigName = str;
        this.swigValue = pjsua_stateVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
