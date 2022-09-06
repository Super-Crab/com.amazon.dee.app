package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjrpid_activity {
    private final String swigName;
    private final int swigValue;
    public static final pjrpid_activity PJRPID_ACTIVITY_UNKNOWN = new pjrpid_activity("PJRPID_ACTIVITY_UNKNOWN");
    public static final pjrpid_activity PJRPID_ACTIVITY_AWAY = new pjrpid_activity("PJRPID_ACTIVITY_AWAY");
    public static final pjrpid_activity PJRPID_ACTIVITY_BUSY = new pjrpid_activity("PJRPID_ACTIVITY_BUSY");
    private static pjrpid_activity[] swigValues = {PJRPID_ACTIVITY_UNKNOWN, PJRPID_ACTIVITY_AWAY, PJRPID_ACTIVITY_BUSY};
    private static int swigNext = 0;

    private pjrpid_activity(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjrpid_activity swigToEnum(int i) {
        pjrpid_activity[] pjrpid_activityVarArr = swigValues;
        if (i >= pjrpid_activityVarArr.length || i < 0 || pjrpid_activityVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjrpid_activity[] pjrpid_activityVarArr2 = swigValues;
                if (i2 < pjrpid_activityVarArr2.length) {
                    if (pjrpid_activityVarArr2[i2].swigValue == i) {
                        return pjrpid_activityVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjrpid_activity.class, " with value ", i));
                }
            }
        } else {
            return pjrpid_activityVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjrpid_activity(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjrpid_activity(String str, pjrpid_activity pjrpid_activityVar) {
        this.swigName = str;
        this.swigValue = pjrpid_activityVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
