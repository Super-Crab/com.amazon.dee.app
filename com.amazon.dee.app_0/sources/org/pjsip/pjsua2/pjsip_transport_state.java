package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsip_transport_state {
    private final String swigName;
    private final int swigValue;
    public static final pjsip_transport_state PJSIP_TP_STATE_CONNECTED = new pjsip_transport_state("PJSIP_TP_STATE_CONNECTED");
    public static final pjsip_transport_state PJSIP_TP_STATE_DISCONNECTED = new pjsip_transport_state("PJSIP_TP_STATE_DISCONNECTED");
    public static final pjsip_transport_state PJSIP_TP_STATE_SHUTDOWN = new pjsip_transport_state("PJSIP_TP_STATE_SHUTDOWN");
    public static final pjsip_transport_state PJSIP_TP_STATE_DESTROY = new pjsip_transport_state("PJSIP_TP_STATE_DESTROY");
    private static pjsip_transport_state[] swigValues = {PJSIP_TP_STATE_CONNECTED, PJSIP_TP_STATE_DISCONNECTED, PJSIP_TP_STATE_SHUTDOWN, PJSIP_TP_STATE_DESTROY};
    private static int swigNext = 0;

    private pjsip_transport_state(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsip_transport_state swigToEnum(int i) {
        pjsip_transport_state[] pjsip_transport_stateVarArr = swigValues;
        if (i >= pjsip_transport_stateVarArr.length || i < 0 || pjsip_transport_stateVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsip_transport_state[] pjsip_transport_stateVarArr2 = swigValues;
                if (i2 < pjsip_transport_stateVarArr2.length) {
                    if (pjsip_transport_stateVarArr2[i2].swigValue == i) {
                        return pjsip_transport_stateVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsip_transport_state.class, " with value ", i));
                }
            }
        } else {
            return pjsip_transport_stateVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_transport_state(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_transport_state(String str, pjsip_transport_state pjsip_transport_stateVar) {
        this.swigName = str;
        this.swigValue = pjsip_transport_stateVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
