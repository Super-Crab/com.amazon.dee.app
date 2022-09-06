package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pj_constants_ {
    private final String swigName;
    private final int swigValue;
    public static final pj_constants_ PJ_SUCCESS = new pj_constants_("PJ_SUCCESS", pjsua2JNI.PJ_SUCCESS_get());
    public static final pj_constants_ PJ_TRUE = new pj_constants_("PJ_TRUE", pjsua2JNI.PJ_TRUE_get());
    public static final pj_constants_ PJ_FALSE = new pj_constants_("PJ_FALSE", pjsua2JNI.PJ_FALSE_get());
    private static pj_constants_[] swigValues = {PJ_SUCCESS, PJ_TRUE, PJ_FALSE};
    private static int swigNext = 0;

    private pj_constants_(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pj_constants_ swigToEnum(int i) {
        pj_constants_[] pj_constants_VarArr = swigValues;
        if (i >= pj_constants_VarArr.length || i < 0 || pj_constants_VarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pj_constants_[] pj_constants_VarArr2 = swigValues;
                if (i2 < pj_constants_VarArr2.length) {
                    if (pj_constants_VarArr2[i2].swigValue == i) {
                        return pj_constants_VarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pj_constants_.class, " with value ", i));
                }
            }
        } else {
            return pj_constants_VarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_constants_(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_constants_(String str, pj_constants_ pj_constants_Var) {
        this.swigName = str;
        this.swigValue = pj_constants_Var.swigValue;
        swigNext = this.swigValue + 1;
    }
}
