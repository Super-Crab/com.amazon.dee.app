package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsip_inv_state {
    private final String swigName;
    private final int swigValue;
    public static final pjsip_inv_state PJSIP_INV_STATE_NULL = new pjsip_inv_state("PJSIP_INV_STATE_NULL");
    public static final pjsip_inv_state PJSIP_INV_STATE_CALLING = new pjsip_inv_state("PJSIP_INV_STATE_CALLING");
    public static final pjsip_inv_state PJSIP_INV_STATE_INCOMING = new pjsip_inv_state("PJSIP_INV_STATE_INCOMING");
    public static final pjsip_inv_state PJSIP_INV_STATE_EARLY = new pjsip_inv_state("PJSIP_INV_STATE_EARLY");
    public static final pjsip_inv_state PJSIP_INV_STATE_CONNECTING = new pjsip_inv_state("PJSIP_INV_STATE_CONNECTING");
    public static final pjsip_inv_state PJSIP_INV_STATE_CONFIRMED = new pjsip_inv_state("PJSIP_INV_STATE_CONFIRMED");
    public static final pjsip_inv_state PJSIP_INV_STATE_DISCONNECTED = new pjsip_inv_state("PJSIP_INV_STATE_DISCONNECTED");
    private static pjsip_inv_state[] swigValues = {PJSIP_INV_STATE_NULL, PJSIP_INV_STATE_CALLING, PJSIP_INV_STATE_INCOMING, PJSIP_INV_STATE_EARLY, PJSIP_INV_STATE_CONNECTING, PJSIP_INV_STATE_CONFIRMED, PJSIP_INV_STATE_DISCONNECTED};
    private static int swigNext = 0;

    private pjsip_inv_state(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsip_inv_state swigToEnum(int i) {
        pjsip_inv_state[] pjsip_inv_stateVarArr = swigValues;
        if (i >= pjsip_inv_stateVarArr.length || i < 0 || pjsip_inv_stateVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsip_inv_state[] pjsip_inv_stateVarArr2 = swigValues;
                if (i2 < pjsip_inv_stateVarArr2.length) {
                    if (pjsip_inv_stateVarArr2[i2].swigValue == i) {
                        return pjsip_inv_stateVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsip_inv_state.class, " with value ", i));
                }
            }
        } else {
            return pjsip_inv_stateVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_inv_state(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_inv_state(String str, pjsip_inv_state pjsip_inv_stateVar) {
        this.swigName = str;
        this.swigValue = pjsip_inv_stateVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
