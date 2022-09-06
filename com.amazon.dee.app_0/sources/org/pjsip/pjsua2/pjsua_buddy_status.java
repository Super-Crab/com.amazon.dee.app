package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_buddy_status {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_buddy_status PJSUA_BUDDY_STATUS_UNKNOWN = new pjsua_buddy_status("PJSUA_BUDDY_STATUS_UNKNOWN");
    public static final pjsua_buddy_status PJSUA_BUDDY_STATUS_ONLINE = new pjsua_buddy_status("PJSUA_BUDDY_STATUS_ONLINE");
    public static final pjsua_buddy_status PJSUA_BUDDY_STATUS_OFFLINE = new pjsua_buddy_status("PJSUA_BUDDY_STATUS_OFFLINE");
    private static pjsua_buddy_status[] swigValues = {PJSUA_BUDDY_STATUS_UNKNOWN, PJSUA_BUDDY_STATUS_ONLINE, PJSUA_BUDDY_STATUS_OFFLINE};
    private static int swigNext = 0;

    private pjsua_buddy_status(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_buddy_status swigToEnum(int i) {
        pjsua_buddy_status[] pjsua_buddy_statusVarArr = swigValues;
        if (i >= pjsua_buddy_statusVarArr.length || i < 0 || pjsua_buddy_statusVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_buddy_status[] pjsua_buddy_statusVarArr2 = swigValues;
                if (i2 < pjsua_buddy_statusVarArr2.length) {
                    if (pjsua_buddy_statusVarArr2[i2].swigValue == i) {
                        return pjsua_buddy_statusVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_buddy_status.class, " with value ", i));
                }
            }
        } else {
            return pjsua_buddy_statusVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_buddy_status(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_buddy_status(String str, pjsua_buddy_status pjsua_buddy_statusVar) {
        this.swigName = str;
        this.swigValue = pjsua_buddy_statusVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
