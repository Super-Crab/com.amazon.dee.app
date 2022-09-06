package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_invalid_id_const_ {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_invalid_id_const_ PJSUA_INVALID_ID = new pjsua_invalid_id_const_("PJSUA_INVALID_ID", pjsua2JNI.PJSUA_INVALID_ID_get());
    private static pjsua_invalid_id_const_[] swigValues = {PJSUA_INVALID_ID};
    private static int swigNext = 0;

    private pjsua_invalid_id_const_(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_invalid_id_const_ swigToEnum(int i) {
        pjsua_invalid_id_const_[] pjsua_invalid_id_const_VarArr = swigValues;
        if (i >= pjsua_invalid_id_const_VarArr.length || i < 0 || pjsua_invalid_id_const_VarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_invalid_id_const_[] pjsua_invalid_id_const_VarArr2 = swigValues;
                if (i2 < pjsua_invalid_id_const_VarArr2.length) {
                    if (pjsua_invalid_id_const_VarArr2[i2].swigValue == i) {
                        return pjsua_invalid_id_const_VarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_invalid_id_const_.class, " with value ", i));
                }
            }
        } else {
            return pjsua_invalid_id_const_VarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_invalid_id_const_(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_invalid_id_const_(String str, pjsua_invalid_id_const_ pjsua_invalid_id_const_Var) {
        this.swigName = str;
        this.swigValue = pjsua_invalid_id_const_Var.swigValue;
        swigNext = this.swigValue + 1;
    }
}
