package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjmedia_orient {
    private final String swigName;
    private final int swigValue;
    public static final pjmedia_orient PJMEDIA_ORIENT_UNKNOWN = new pjmedia_orient("PJMEDIA_ORIENT_UNKNOWN");
    public static final pjmedia_orient PJMEDIA_ORIENT_NATURAL = new pjmedia_orient("PJMEDIA_ORIENT_NATURAL");
    public static final pjmedia_orient PJMEDIA_ORIENT_ROTATE_90DEG = new pjmedia_orient("PJMEDIA_ORIENT_ROTATE_90DEG");
    public static final pjmedia_orient PJMEDIA_ORIENT_ROTATE_180DEG = new pjmedia_orient("PJMEDIA_ORIENT_ROTATE_180DEG");
    public static final pjmedia_orient PJMEDIA_ORIENT_ROTATE_270DEG = new pjmedia_orient("PJMEDIA_ORIENT_ROTATE_270DEG");
    private static pjmedia_orient[] swigValues = {PJMEDIA_ORIENT_UNKNOWN, PJMEDIA_ORIENT_NATURAL, PJMEDIA_ORIENT_ROTATE_90DEG, PJMEDIA_ORIENT_ROTATE_180DEG, PJMEDIA_ORIENT_ROTATE_270DEG};
    private static int swigNext = 0;

    private pjmedia_orient(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjmedia_orient swigToEnum(int i) {
        pjmedia_orient[] pjmedia_orientVarArr = swigValues;
        if (i >= pjmedia_orientVarArr.length || i < 0 || pjmedia_orientVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjmedia_orient[] pjmedia_orientVarArr2 = swigValues;
                if (i2 < pjmedia_orientVarArr2.length) {
                    if (pjmedia_orientVarArr2[i2].swigValue == i) {
                        return pjmedia_orientVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjmedia_orient.class, " with value ", i));
                }
            }
        } else {
            return pjmedia_orientVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_orient(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_orient(String str, pjmedia_orient pjmedia_orientVar) {
        this.swigName = str;
        this.swigValue = pjmedia_orientVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
