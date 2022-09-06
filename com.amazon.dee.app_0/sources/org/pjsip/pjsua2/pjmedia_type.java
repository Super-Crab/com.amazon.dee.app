package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjmedia_type {
    private final String swigName;
    private final int swigValue;
    public static final pjmedia_type PJMEDIA_TYPE_NONE = new pjmedia_type("PJMEDIA_TYPE_NONE");
    public static final pjmedia_type PJMEDIA_TYPE_AUDIO = new pjmedia_type("PJMEDIA_TYPE_AUDIO");
    public static final pjmedia_type PJMEDIA_TYPE_VIDEO = new pjmedia_type("PJMEDIA_TYPE_VIDEO");
    public static final pjmedia_type PJMEDIA_TYPE_APPLICATION = new pjmedia_type("PJMEDIA_TYPE_APPLICATION");
    public static final pjmedia_type PJMEDIA_TYPE_UNKNOWN = new pjmedia_type("PJMEDIA_TYPE_UNKNOWN");
    private static pjmedia_type[] swigValues = {PJMEDIA_TYPE_NONE, PJMEDIA_TYPE_AUDIO, PJMEDIA_TYPE_VIDEO, PJMEDIA_TYPE_APPLICATION, PJMEDIA_TYPE_UNKNOWN};
    private static int swigNext = 0;

    private pjmedia_type(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjmedia_type swigToEnum(int i) {
        pjmedia_type[] pjmedia_typeVarArr = swigValues;
        if (i >= pjmedia_typeVarArr.length || i < 0 || pjmedia_typeVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjmedia_type[] pjmedia_typeVarArr2 = swigValues;
                if (i2 < pjmedia_typeVarArr2.length) {
                    if (pjmedia_typeVarArr2[i2].swigValue == i) {
                        return pjmedia_typeVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjmedia_type.class, " with value ", i));
                }
            }
        } else {
            return pjmedia_typeVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_type(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_type(String str, pjmedia_type pjmedia_typeVar) {
        this.swigName = str;
        this.swigValue = pjmedia_typeVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
