package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsip_event_id_e {
    private final String swigName;
    private final int swigValue;
    public static final pjsip_event_id_e PJSIP_EVENT_UNKNOWN = new pjsip_event_id_e("PJSIP_EVENT_UNKNOWN");
    public static final pjsip_event_id_e PJSIP_EVENT_TIMER = new pjsip_event_id_e("PJSIP_EVENT_TIMER");
    public static final pjsip_event_id_e PJSIP_EVENT_TX_MSG = new pjsip_event_id_e("PJSIP_EVENT_TX_MSG");
    public static final pjsip_event_id_e PJSIP_EVENT_RX_MSG = new pjsip_event_id_e("PJSIP_EVENT_RX_MSG");
    public static final pjsip_event_id_e PJSIP_EVENT_TRANSPORT_ERROR = new pjsip_event_id_e("PJSIP_EVENT_TRANSPORT_ERROR");
    public static final pjsip_event_id_e PJSIP_EVENT_TSX_STATE = new pjsip_event_id_e("PJSIP_EVENT_TSX_STATE");
    public static final pjsip_event_id_e PJSIP_EVENT_USER = new pjsip_event_id_e("PJSIP_EVENT_USER");
    private static pjsip_event_id_e[] swigValues = {PJSIP_EVENT_UNKNOWN, PJSIP_EVENT_TIMER, PJSIP_EVENT_TX_MSG, PJSIP_EVENT_RX_MSG, PJSIP_EVENT_TRANSPORT_ERROR, PJSIP_EVENT_TSX_STATE, PJSIP_EVENT_USER};
    private static int swigNext = 0;

    private pjsip_event_id_e(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsip_event_id_e swigToEnum(int i) {
        pjsip_event_id_e[] pjsip_event_id_eVarArr = swigValues;
        if (i >= pjsip_event_id_eVarArr.length || i < 0 || pjsip_event_id_eVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsip_event_id_e[] pjsip_event_id_eVarArr2 = swigValues;
                if (i2 < pjsip_event_id_eVarArr2.length) {
                    if (pjsip_event_id_eVarArr2[i2].swigValue == i) {
                        return pjsip_event_id_eVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsip_event_id_e.class, " with value ", i));
                }
            }
        } else {
            return pjsip_event_id_eVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_event_id_e(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_event_id_e(String str, pjsip_event_id_e pjsip_event_id_eVar) {
        this.swigName = str;
        this.swigValue = pjsip_event_id_eVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
