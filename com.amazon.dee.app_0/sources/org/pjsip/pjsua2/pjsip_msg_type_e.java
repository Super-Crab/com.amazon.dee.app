package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsip_msg_type_e {
    private final String swigName;
    private final int swigValue;
    public static final pjsip_msg_type_e PJSIP_REQUEST_MSG = new pjsip_msg_type_e("PJSIP_REQUEST_MSG");
    public static final pjsip_msg_type_e PJSIP_RESPONSE_MSG = new pjsip_msg_type_e("PJSIP_RESPONSE_MSG");
    private static pjsip_msg_type_e[] swigValues = {PJSIP_REQUEST_MSG, PJSIP_RESPONSE_MSG};
    private static int swigNext = 0;

    private pjsip_msg_type_e(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsip_msg_type_e swigToEnum(int i) {
        pjsip_msg_type_e[] pjsip_msg_type_eVarArr = swigValues;
        if (i >= pjsip_msg_type_eVarArr.length || i < 0 || pjsip_msg_type_eVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsip_msg_type_e[] pjsip_msg_type_eVarArr2 = swigValues;
                if (i2 < pjsip_msg_type_eVarArr2.length) {
                    if (pjsip_msg_type_eVarArr2[i2].swigValue == i) {
                        return pjsip_msg_type_eVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsip_msg_type_e.class, " with value ", i));
                }
            }
        } else {
            return pjsip_msg_type_eVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_msg_type_e(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_msg_type_e(String str, pjsip_msg_type_e pjsip_msg_type_eVar) {
        this.swigName = str;
        this.swigValue = pjsip_msg_type_eVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
