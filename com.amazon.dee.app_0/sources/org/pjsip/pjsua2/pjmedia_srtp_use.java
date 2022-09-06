package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjmedia_srtp_use {
    private final String swigName;
    private final int swigValue;
    public static final pjmedia_srtp_use PJMEDIA_SRTP_DISABLED = new pjmedia_srtp_use("PJMEDIA_SRTP_DISABLED");
    public static final pjmedia_srtp_use PJMEDIA_SRTP_OPTIONAL = new pjmedia_srtp_use("PJMEDIA_SRTP_OPTIONAL");
    public static final pjmedia_srtp_use PJMEDIA_SRTP_MANDATORY = new pjmedia_srtp_use("PJMEDIA_SRTP_MANDATORY");
    private static pjmedia_srtp_use[] swigValues = {PJMEDIA_SRTP_DISABLED, PJMEDIA_SRTP_OPTIONAL, PJMEDIA_SRTP_MANDATORY};
    private static int swigNext = 0;

    private pjmedia_srtp_use(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjmedia_srtp_use swigToEnum(int i) {
        pjmedia_srtp_use[] pjmedia_srtp_useVarArr = swigValues;
        if (i >= pjmedia_srtp_useVarArr.length || i < 0 || pjmedia_srtp_useVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjmedia_srtp_use[] pjmedia_srtp_useVarArr2 = swigValues;
                if (i2 < pjmedia_srtp_useVarArr2.length) {
                    if (pjmedia_srtp_useVarArr2[i2].swigValue == i) {
                        return pjmedia_srtp_useVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjmedia_srtp_use.class, " with value ", i));
                }
            }
        } else {
            return pjmedia_srtp_useVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_srtp_use(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_srtp_use(String str, pjmedia_srtp_use pjmedia_srtp_useVar) {
        this.swigName = str;
        this.swigValue = pjmedia_srtp_useVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
