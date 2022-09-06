package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_ipv6_use {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_ipv6_use PJSUA_IPV6_DISABLED = new pjsua_ipv6_use("PJSUA_IPV6_DISABLED");
    public static final pjsua_ipv6_use PJSUA_IPV6_ENABLED = new pjsua_ipv6_use("PJSUA_IPV6_ENABLED");
    private static pjsua_ipv6_use[] swigValues = {PJSUA_IPV6_DISABLED, PJSUA_IPV6_ENABLED};
    private static int swigNext = 0;

    private pjsua_ipv6_use(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_ipv6_use swigToEnum(int i) {
        pjsua_ipv6_use[] pjsua_ipv6_useVarArr = swigValues;
        if (i >= pjsua_ipv6_useVarArr.length || i < 0 || pjsua_ipv6_useVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_ipv6_use[] pjsua_ipv6_useVarArr2 = swigValues;
                if (i2 < pjsua_ipv6_useVarArr2.length) {
                    if (pjsua_ipv6_useVarArr2[i2].swigValue == i) {
                        return pjsua_ipv6_useVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_ipv6_use.class, " with value ", i));
                }
            }
        } else {
            return pjsua_ipv6_useVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_ipv6_use(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_ipv6_use(String str, pjsua_ipv6_use pjsua_ipv6_useVar) {
        this.swigName = str;
        this.swigValue = pjsua_ipv6_useVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
