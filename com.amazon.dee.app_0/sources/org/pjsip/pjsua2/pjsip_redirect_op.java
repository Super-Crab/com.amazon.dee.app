package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsip_redirect_op {
    private final String swigName;
    private final int swigValue;
    public static final pjsip_redirect_op PJSIP_REDIRECT_REJECT = new pjsip_redirect_op("PJSIP_REDIRECT_REJECT");
    public static final pjsip_redirect_op PJSIP_REDIRECT_ACCEPT = new pjsip_redirect_op("PJSIP_REDIRECT_ACCEPT");
    public static final pjsip_redirect_op PJSIP_REDIRECT_ACCEPT_REPLACE = new pjsip_redirect_op("PJSIP_REDIRECT_ACCEPT_REPLACE");
    public static final pjsip_redirect_op PJSIP_REDIRECT_PENDING = new pjsip_redirect_op("PJSIP_REDIRECT_PENDING");
    public static final pjsip_redirect_op PJSIP_REDIRECT_STOP = new pjsip_redirect_op("PJSIP_REDIRECT_STOP");
    private static pjsip_redirect_op[] swigValues = {PJSIP_REDIRECT_REJECT, PJSIP_REDIRECT_ACCEPT, PJSIP_REDIRECT_ACCEPT_REPLACE, PJSIP_REDIRECT_PENDING, PJSIP_REDIRECT_STOP};
    private static int swigNext = 0;

    private pjsip_redirect_op(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsip_redirect_op swigToEnum(int i) {
        pjsip_redirect_op[] pjsip_redirect_opVarArr = swigValues;
        if (i >= pjsip_redirect_opVarArr.length || i < 0 || pjsip_redirect_opVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsip_redirect_op[] pjsip_redirect_opVarArr2 = swigValues;
                if (i2 < pjsip_redirect_opVarArr2.length) {
                    if (pjsip_redirect_opVarArr2[i2].swigValue == i) {
                        return pjsip_redirect_opVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsip_redirect_op.class, " with value ", i));
                }
            }
        } else {
            return pjsip_redirect_opVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_redirect_op(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_redirect_op(String str, pjsip_redirect_op pjsip_redirect_opVar) {
        this.swigName = str;
        this.swigValue = pjsip_redirect_opVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
