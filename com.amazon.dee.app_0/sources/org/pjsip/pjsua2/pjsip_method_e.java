package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsip_method_e {
    private final String swigName;
    private final int swigValue;
    public static final pjsip_method_e PJSIP_INVITE_METHOD = new pjsip_method_e("PJSIP_INVITE_METHOD");
    public static final pjsip_method_e PJSIP_CANCEL_METHOD = new pjsip_method_e("PJSIP_CANCEL_METHOD");
    public static final pjsip_method_e PJSIP_ACK_METHOD = new pjsip_method_e("PJSIP_ACK_METHOD");
    public static final pjsip_method_e PJSIP_BYE_METHOD = new pjsip_method_e("PJSIP_BYE_METHOD");
    public static final pjsip_method_e PJSIP_REGISTER_METHOD = new pjsip_method_e("PJSIP_REGISTER_METHOD");
    public static final pjsip_method_e PJSIP_OPTIONS_METHOD = new pjsip_method_e("PJSIP_OPTIONS_METHOD");
    public static final pjsip_method_e PJSIP_OTHER_METHOD = new pjsip_method_e("PJSIP_OTHER_METHOD");
    private static pjsip_method_e[] swigValues = {PJSIP_INVITE_METHOD, PJSIP_CANCEL_METHOD, PJSIP_ACK_METHOD, PJSIP_BYE_METHOD, PJSIP_REGISTER_METHOD, PJSIP_OPTIONS_METHOD, PJSIP_OTHER_METHOD};
    private static int swigNext = 0;

    private pjsip_method_e(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsip_method_e swigToEnum(int i) {
        pjsip_method_e[] pjsip_method_eVarArr = swigValues;
        if (i >= pjsip_method_eVarArr.length || i < 0 || pjsip_method_eVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsip_method_e[] pjsip_method_eVarArr2 = swigValues;
                if (i2 < pjsip_method_eVarArr2.length) {
                    if (pjsip_method_eVarArr2[i2].swigValue == i) {
                        return pjsip_method_eVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsip_method_e.class, " with value ", i));
                }
            }
        } else {
            return pjsip_method_eVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_method_e(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_method_e(String str, pjsip_method_e pjsip_method_eVar) {
        this.swigName = str;
        this.swigValue = pjsip_method_eVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
