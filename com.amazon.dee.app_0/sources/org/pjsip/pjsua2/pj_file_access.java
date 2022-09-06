package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pj_file_access {
    private final String swigName;
    private final int swigValue;
    public static final pj_file_access PJ_O_RDONLY = new pj_file_access("PJ_O_RDONLY", pjsua2JNI.PJ_O_RDONLY_get());
    public static final pj_file_access PJ_O_WRONLY = new pj_file_access("PJ_O_WRONLY", pjsua2JNI.PJ_O_WRONLY_get());
    public static final pj_file_access PJ_O_RDWR = new pj_file_access("PJ_O_RDWR", pjsua2JNI.PJ_O_RDWR_get());
    public static final pj_file_access PJ_O_APPEND = new pj_file_access("PJ_O_APPEND", pjsua2JNI.PJ_O_APPEND_get());
    private static pj_file_access[] swigValues = {PJ_O_RDONLY, PJ_O_WRONLY, PJ_O_RDWR, PJ_O_APPEND};
    private static int swigNext = 0;

    private pj_file_access(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pj_file_access swigToEnum(int i) {
        pj_file_access[] pj_file_accessVarArr = swigValues;
        if (i >= pj_file_accessVarArr.length || i < 0 || pj_file_accessVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pj_file_access[] pj_file_accessVarArr2 = swigValues;
                if (i2 < pj_file_accessVarArr2.length) {
                    if (pj_file_accessVarArr2[i2].swigValue == i) {
                        return pj_file_accessVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pj_file_access.class, " with value ", i));
                }
            }
        } else {
            return pj_file_accessVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_file_access(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_file_access(String str, pj_file_access pj_file_accessVar) {
        this.swigName = str;
        this.swigValue = pj_file_accessVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
