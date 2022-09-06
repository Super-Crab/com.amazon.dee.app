package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsip_evsub_state {
    private final String swigName;
    private final int swigValue;
    public static final pjsip_evsub_state PJSIP_EVSUB_STATE_NULL = new pjsip_evsub_state("PJSIP_EVSUB_STATE_NULL");
    public static final pjsip_evsub_state PJSIP_EVSUB_STATE_SENT = new pjsip_evsub_state("PJSIP_EVSUB_STATE_SENT");
    public static final pjsip_evsub_state PJSIP_EVSUB_STATE_ACCEPTED = new pjsip_evsub_state("PJSIP_EVSUB_STATE_ACCEPTED");
    public static final pjsip_evsub_state PJSIP_EVSUB_STATE_PENDING = new pjsip_evsub_state("PJSIP_EVSUB_STATE_PENDING");
    public static final pjsip_evsub_state PJSIP_EVSUB_STATE_ACTIVE = new pjsip_evsub_state("PJSIP_EVSUB_STATE_ACTIVE");
    public static final pjsip_evsub_state PJSIP_EVSUB_STATE_TERMINATED = new pjsip_evsub_state("PJSIP_EVSUB_STATE_TERMINATED");
    public static final pjsip_evsub_state PJSIP_EVSUB_STATE_UNKNOWN = new pjsip_evsub_state("PJSIP_EVSUB_STATE_UNKNOWN");
    private static pjsip_evsub_state[] swigValues = {PJSIP_EVSUB_STATE_NULL, PJSIP_EVSUB_STATE_SENT, PJSIP_EVSUB_STATE_ACCEPTED, PJSIP_EVSUB_STATE_PENDING, PJSIP_EVSUB_STATE_ACTIVE, PJSIP_EVSUB_STATE_TERMINATED, PJSIP_EVSUB_STATE_UNKNOWN};
    private static int swigNext = 0;

    private pjsip_evsub_state(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsip_evsub_state swigToEnum(int i) {
        pjsip_evsub_state[] pjsip_evsub_stateVarArr = swigValues;
        if (i >= pjsip_evsub_stateVarArr.length || i < 0 || pjsip_evsub_stateVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsip_evsub_state[] pjsip_evsub_stateVarArr2 = swigValues;
                if (i2 < pjsip_evsub_stateVarArr2.length) {
                    if (pjsip_evsub_stateVarArr2[i2].swigValue == i) {
                        return pjsip_evsub_stateVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsip_evsub_state.class, " with value ", i));
                }
            }
        } else {
            return pjsip_evsub_stateVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_evsub_state(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_evsub_state(String str, pjsip_evsub_state pjsip_evsub_stateVar) {
        this.swigName = str;
        this.swigValue = pjsip_evsub_stateVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
