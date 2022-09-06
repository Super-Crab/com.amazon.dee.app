package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_sip_timer_use {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_sip_timer_use PJSUA_SIP_TIMER_INACTIVE = new pjsua_sip_timer_use("PJSUA_SIP_TIMER_INACTIVE");
    public static final pjsua_sip_timer_use PJSUA_SIP_TIMER_OPTIONAL = new pjsua_sip_timer_use("PJSUA_SIP_TIMER_OPTIONAL");
    public static final pjsua_sip_timer_use PJSUA_SIP_TIMER_REQUIRED = new pjsua_sip_timer_use("PJSUA_SIP_TIMER_REQUIRED");
    public static final pjsua_sip_timer_use PJSUA_SIP_TIMER_ALWAYS = new pjsua_sip_timer_use("PJSUA_SIP_TIMER_ALWAYS");
    private static pjsua_sip_timer_use[] swigValues = {PJSUA_SIP_TIMER_INACTIVE, PJSUA_SIP_TIMER_OPTIONAL, PJSUA_SIP_TIMER_REQUIRED, PJSUA_SIP_TIMER_ALWAYS};
    private static int swigNext = 0;

    private pjsua_sip_timer_use(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_sip_timer_use swigToEnum(int i) {
        pjsua_sip_timer_use[] pjsua_sip_timer_useVarArr = swigValues;
        if (i >= pjsua_sip_timer_useVarArr.length || i < 0 || pjsua_sip_timer_useVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_sip_timer_use[] pjsua_sip_timer_useVarArr2 = swigValues;
                if (i2 < pjsua_sip_timer_useVarArr2.length) {
                    if (pjsua_sip_timer_useVarArr2[i2].swigValue == i) {
                        return pjsua_sip_timer_useVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_sip_timer_use.class, " with value ", i));
                }
            }
        } else {
            return pjsua_sip_timer_useVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_sip_timer_use(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_sip_timer_use(String str, pjsua_sip_timer_use pjsua_sip_timer_useVar) {
        this.swigName = str;
        this.swigValue = pjsua_sip_timer_useVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
