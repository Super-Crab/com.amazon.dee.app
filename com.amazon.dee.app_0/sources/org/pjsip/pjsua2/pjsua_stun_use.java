package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_stun_use {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_stun_use PJSUA_STUN_USE_DEFAULT = new pjsua_stun_use("PJSUA_STUN_USE_DEFAULT");
    public static final pjsua_stun_use PJSUA_STUN_USE_DISABLED = new pjsua_stun_use("PJSUA_STUN_USE_DISABLED");
    private static pjsua_stun_use[] swigValues = {PJSUA_STUN_USE_DEFAULT, PJSUA_STUN_USE_DISABLED};
    private static int swigNext = 0;

    private pjsua_stun_use(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_stun_use swigToEnum(int i) {
        pjsua_stun_use[] pjsua_stun_useVarArr = swigValues;
        if (i >= pjsua_stun_useVarArr.length || i < 0 || pjsua_stun_useVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_stun_use[] pjsua_stun_useVarArr2 = swigValues;
                if (i2 < pjsua_stun_useVarArr2.length) {
                    if (pjsua_stun_useVarArr2[i2].swigValue == i) {
                        return pjsua_stun_useVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_stun_use.class, " with value ", i));
                }
            }
        } else {
            return pjsua_stun_useVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_stun_use(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_stun_use(String str, pjsua_stun_use pjsua_stun_useVar) {
        this.swigName = str;
        this.swigValue = pjsua_stun_useVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
