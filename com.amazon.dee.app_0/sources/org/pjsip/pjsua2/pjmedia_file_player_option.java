package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjmedia_file_player_option {
    private final String swigName;
    private final int swigValue;
    public static final pjmedia_file_player_option PJMEDIA_FILE_NO_LOOP = new pjmedia_file_player_option("PJMEDIA_FILE_NO_LOOP", pjsua2JNI.PJMEDIA_FILE_NO_LOOP_get());
    private static pjmedia_file_player_option[] swigValues = {PJMEDIA_FILE_NO_LOOP};
    private static int swigNext = 0;

    private pjmedia_file_player_option(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjmedia_file_player_option swigToEnum(int i) {
        pjmedia_file_player_option[] pjmedia_file_player_optionVarArr = swigValues;
        if (i >= pjmedia_file_player_optionVarArr.length || i < 0 || pjmedia_file_player_optionVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjmedia_file_player_option[] pjmedia_file_player_optionVarArr2 = swigValues;
                if (i2 < pjmedia_file_player_optionVarArr2.length) {
                    if (pjmedia_file_player_optionVarArr2[i2].swigValue == i) {
                        return pjmedia_file_player_optionVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjmedia_file_player_option.class, " with value ", i));
                }
            }
        } else {
            return pjmedia_file_player_optionVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_file_player_option(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_file_player_option(String str, pjmedia_file_player_option pjmedia_file_player_optionVar) {
        this.swigName = str;
        this.swigValue = pjmedia_file_player_optionVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
