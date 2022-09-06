package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pj_stun_nat_type {
    private final String swigName;
    private final int swigValue;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_UNKNOWN = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_UNKNOWN");
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_ERR_UNKNOWN = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_ERR_UNKNOWN");
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_OPEN = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_OPEN");
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_BLOCKED = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_BLOCKED");
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_SYMMETRIC_UDP = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_SYMMETRIC_UDP");
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_FULL_CONE = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_FULL_CONE");
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_SYMMETRIC = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_SYMMETRIC");
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_RESTRICTED = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_RESTRICTED");
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_PORT_RESTRICTED = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_PORT_RESTRICTED");
    private static pj_stun_nat_type[] swigValues = {PJ_STUN_NAT_TYPE_UNKNOWN, PJ_STUN_NAT_TYPE_ERR_UNKNOWN, PJ_STUN_NAT_TYPE_OPEN, PJ_STUN_NAT_TYPE_BLOCKED, PJ_STUN_NAT_TYPE_SYMMETRIC_UDP, PJ_STUN_NAT_TYPE_FULL_CONE, PJ_STUN_NAT_TYPE_SYMMETRIC, PJ_STUN_NAT_TYPE_RESTRICTED, PJ_STUN_NAT_TYPE_PORT_RESTRICTED};
    private static int swigNext = 0;

    private pj_stun_nat_type(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pj_stun_nat_type swigToEnum(int i) {
        pj_stun_nat_type[] pj_stun_nat_typeVarArr = swigValues;
        if (i >= pj_stun_nat_typeVarArr.length || i < 0 || pj_stun_nat_typeVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pj_stun_nat_type[] pj_stun_nat_typeVarArr2 = swigValues;
                if (i2 < pj_stun_nat_typeVarArr2.length) {
                    if (pj_stun_nat_typeVarArr2[i2].swigValue == i) {
                        return pj_stun_nat_typeVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pj_stun_nat_type.class, " with value ", i));
                }
            }
        } else {
            return pj_stun_nat_typeVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_stun_nat_type(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_stun_nat_type(String str, pj_stun_nat_type pj_stun_nat_typeVar) {
        this.swigName = str;
        this.swigValue = pj_stun_nat_typeVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
