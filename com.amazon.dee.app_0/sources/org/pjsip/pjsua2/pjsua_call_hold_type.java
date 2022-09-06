package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_call_hold_type {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_call_hold_type PJSUA_CALL_HOLD_TYPE_RFC3264 = new pjsua_call_hold_type("PJSUA_CALL_HOLD_TYPE_RFC3264");
    public static final pjsua_call_hold_type PJSUA_CALL_HOLD_TYPE_RFC2543 = new pjsua_call_hold_type("PJSUA_CALL_HOLD_TYPE_RFC2543");
    private static pjsua_call_hold_type[] swigValues = {PJSUA_CALL_HOLD_TYPE_RFC3264, PJSUA_CALL_HOLD_TYPE_RFC2543};
    private static int swigNext = 0;

    private pjsua_call_hold_type(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_call_hold_type swigToEnum(int i) {
        pjsua_call_hold_type[] pjsua_call_hold_typeVarArr = swigValues;
        if (i >= pjsua_call_hold_typeVarArr.length || i < 0 || pjsua_call_hold_typeVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_call_hold_type[] pjsua_call_hold_typeVarArr2 = swigValues;
                if (i2 < pjsua_call_hold_typeVarArr2.length) {
                    if (pjsua_call_hold_typeVarArr2[i2].swigValue == i) {
                        return pjsua_call_hold_typeVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_call_hold_type.class, " with value ", i));
                }
            }
        } else {
            return pjsua_call_hold_typeVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_call_hold_type(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_call_hold_type(String str, pjsua_call_hold_type pjsua_call_hold_typeVar) {
        this.swigName = str;
        this.swigValue = pjsua_call_hold_typeVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
