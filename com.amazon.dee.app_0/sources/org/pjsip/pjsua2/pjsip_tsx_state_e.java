package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsip_tsx_state_e {
    private final String swigName;
    private final int swigValue;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_NULL = new pjsip_tsx_state_e("PJSIP_TSX_STATE_NULL");
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_CALLING = new pjsip_tsx_state_e("PJSIP_TSX_STATE_CALLING");
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_TRYING = new pjsip_tsx_state_e("PJSIP_TSX_STATE_TRYING");
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_PROCEEDING = new pjsip_tsx_state_e("PJSIP_TSX_STATE_PROCEEDING");
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_COMPLETED = new pjsip_tsx_state_e("PJSIP_TSX_STATE_COMPLETED");
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_CONFIRMED = new pjsip_tsx_state_e("PJSIP_TSX_STATE_CONFIRMED");
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_TERMINATED = new pjsip_tsx_state_e("PJSIP_TSX_STATE_TERMINATED");
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_DESTROYED = new pjsip_tsx_state_e("PJSIP_TSX_STATE_DESTROYED");
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_MAX = new pjsip_tsx_state_e("PJSIP_TSX_STATE_MAX");
    private static pjsip_tsx_state_e[] swigValues = {PJSIP_TSX_STATE_NULL, PJSIP_TSX_STATE_CALLING, PJSIP_TSX_STATE_TRYING, PJSIP_TSX_STATE_PROCEEDING, PJSIP_TSX_STATE_COMPLETED, PJSIP_TSX_STATE_CONFIRMED, PJSIP_TSX_STATE_TERMINATED, PJSIP_TSX_STATE_DESTROYED, PJSIP_TSX_STATE_MAX};
    private static int swigNext = 0;

    private pjsip_tsx_state_e(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsip_tsx_state_e swigToEnum(int i) {
        pjsip_tsx_state_e[] pjsip_tsx_state_eVarArr = swigValues;
        if (i >= pjsip_tsx_state_eVarArr.length || i < 0 || pjsip_tsx_state_eVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsip_tsx_state_e[] pjsip_tsx_state_eVarArr2 = swigValues;
                if (i2 < pjsip_tsx_state_eVarArr2.length) {
                    if (pjsip_tsx_state_eVarArr2[i2].swigValue == i) {
                        return pjsip_tsx_state_eVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsip_tsx_state_e.class, " with value ", i));
                }
            }
        } else {
            return pjsip_tsx_state_eVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_tsx_state_e(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_tsx_state_e(String str, pjsip_tsx_state_e pjsip_tsx_state_eVar) {
        this.swigName = str;
        this.swigValue = pjsip_tsx_state_eVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
