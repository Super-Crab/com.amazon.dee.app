package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_destroy_flag {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_destroy_flag PJSUA_DESTROY_NO_RX_MSG = new pjsua_destroy_flag("PJSUA_DESTROY_NO_RX_MSG", pjsua2JNI.PJSUA_DESTROY_NO_RX_MSG_get());
    public static final pjsua_destroy_flag PJSUA_DESTROY_NO_TX_MSG = new pjsua_destroy_flag("PJSUA_DESTROY_NO_TX_MSG", pjsua2JNI.PJSUA_DESTROY_NO_TX_MSG_get());
    public static final pjsua_destroy_flag PJSUA_DESTROY_NO_NETWORK = new pjsua_destroy_flag("PJSUA_DESTROY_NO_NETWORK", pjsua2JNI.PJSUA_DESTROY_NO_NETWORK_get());
    private static pjsua_destroy_flag[] swigValues = {PJSUA_DESTROY_NO_RX_MSG, PJSUA_DESTROY_NO_TX_MSG, PJSUA_DESTROY_NO_NETWORK};
    private static int swigNext = 0;

    private pjsua_destroy_flag(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_destroy_flag swigToEnum(int i) {
        pjsua_destroy_flag[] pjsua_destroy_flagVarArr = swigValues;
        if (i >= pjsua_destroy_flagVarArr.length || i < 0 || pjsua_destroy_flagVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_destroy_flag[] pjsua_destroy_flagVarArr2 = swigValues;
                if (i2 < pjsua_destroy_flagVarArr2.length) {
                    if (pjsua_destroy_flagVarArr2[i2].swigValue == i) {
                        return pjsua_destroy_flagVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_destroy_flag.class, " with value ", i));
                }
            }
        } else {
            return pjsua_destroy_flagVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_destroy_flag(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_destroy_flag(String str, pjsua_destroy_flag pjsua_destroy_flagVar) {
        this.swigName = str;
        this.swigValue = pjsua_destroy_flagVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
