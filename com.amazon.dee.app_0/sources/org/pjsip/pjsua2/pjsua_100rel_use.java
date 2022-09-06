package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_100rel_use {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_100rel_use PJSUA_100REL_NOT_USED = new pjsua_100rel_use("PJSUA_100REL_NOT_USED");
    public static final pjsua_100rel_use PJSUA_100REL_MANDATORY = new pjsua_100rel_use("PJSUA_100REL_MANDATORY");
    public static final pjsua_100rel_use PJSUA_100REL_OPTIONAL = new pjsua_100rel_use("PJSUA_100REL_OPTIONAL");
    private static pjsua_100rel_use[] swigValues = {PJSUA_100REL_NOT_USED, PJSUA_100REL_MANDATORY, PJSUA_100REL_OPTIONAL};
    private static int swigNext = 0;

    private pjsua_100rel_use(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_100rel_use swigToEnum(int i) {
        pjsua_100rel_use[] pjsua_100rel_useVarArr = swigValues;
        if (i >= pjsua_100rel_useVarArr.length || i < 0 || pjsua_100rel_useVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_100rel_use[] pjsua_100rel_useVarArr2 = swigValues;
                if (i2 < pjsua_100rel_useVarArr2.length) {
                    if (pjsua_100rel_useVarArr2[i2].swigValue == i) {
                        return pjsua_100rel_useVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_100rel_use.class, " with value ", i));
                }
            }
        } else {
            return pjsua_100rel_useVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_100rel_use(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_100rel_use(String str, pjsua_100rel_use pjsua_100rel_useVar) {
        this.swigName = str;
        this.swigValue = pjsua_100rel_useVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
