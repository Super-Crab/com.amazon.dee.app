package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsip_role_e {
    private final String swigName;
    private final int swigValue;
    public static final pjsip_role_e PJSIP_ROLE_UAC = new pjsip_role_e("PJSIP_ROLE_UAC");
    public static final pjsip_role_e PJSIP_ROLE_UAS = new pjsip_role_e("PJSIP_ROLE_UAS");
    public static final pjsip_role_e PJSIP_UAC_ROLE = new pjsip_role_e("PJSIP_UAC_ROLE", pjsua2JNI.PJSIP_UAC_ROLE_get());
    public static final pjsip_role_e PJSIP_UAS_ROLE = new pjsip_role_e("PJSIP_UAS_ROLE", pjsua2JNI.PJSIP_UAS_ROLE_get());
    private static pjsip_role_e[] swigValues = {PJSIP_ROLE_UAC, PJSIP_ROLE_UAS, PJSIP_UAC_ROLE, PJSIP_UAS_ROLE};
    private static int swigNext = 0;

    private pjsip_role_e(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsip_role_e swigToEnum(int i) {
        pjsip_role_e[] pjsip_role_eVarArr = swigValues;
        if (i >= pjsip_role_eVarArr.length || i < 0 || pjsip_role_eVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsip_role_e[] pjsip_role_eVarArr2 = swigValues;
                if (i2 < pjsip_role_eVarArr2.length) {
                    if (pjsip_role_eVarArr2[i2].swigValue == i) {
                        return pjsip_role_eVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsip_role_e.class, " with value ", i));
                }
            }
        } else {
            return pjsip_role_eVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_role_e(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_role_e(String str, pjsip_role_e pjsip_role_eVar) {
        this.swigName = str;
        this.swigValue = pjsip_role_eVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
