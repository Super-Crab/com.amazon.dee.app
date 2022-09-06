package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjmedia_file_writer_option {
    private final String swigName;
    private final int swigValue;
    public static final pjmedia_file_writer_option PJMEDIA_FILE_WRITE_PCM = new pjmedia_file_writer_option("PJMEDIA_FILE_WRITE_PCM", pjsua2JNI.PJMEDIA_FILE_WRITE_PCM_get());
    public static final pjmedia_file_writer_option PJMEDIA_FILE_WRITE_ALAW = new pjmedia_file_writer_option("PJMEDIA_FILE_WRITE_ALAW", pjsua2JNI.PJMEDIA_FILE_WRITE_ALAW_get());
    public static final pjmedia_file_writer_option PJMEDIA_FILE_WRITE_ULAW = new pjmedia_file_writer_option("PJMEDIA_FILE_WRITE_ULAW", pjsua2JNI.PJMEDIA_FILE_WRITE_ULAW_get());
    private static pjmedia_file_writer_option[] swigValues = {PJMEDIA_FILE_WRITE_PCM, PJMEDIA_FILE_WRITE_ALAW, PJMEDIA_FILE_WRITE_ULAW};
    private static int swigNext = 0;

    private pjmedia_file_writer_option(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjmedia_file_writer_option swigToEnum(int i) {
        pjmedia_file_writer_option[] pjmedia_file_writer_optionVarArr = swigValues;
        if (i >= pjmedia_file_writer_optionVarArr.length || i < 0 || pjmedia_file_writer_optionVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjmedia_file_writer_option[] pjmedia_file_writer_optionVarArr2 = swigValues;
                if (i2 < pjmedia_file_writer_optionVarArr2.length) {
                    if (pjmedia_file_writer_optionVarArr2[i2].swigValue == i) {
                        return pjmedia_file_writer_optionVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjmedia_file_writer_option.class, " with value ", i));
                }
            }
        } else {
            return pjmedia_file_writer_optionVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_file_writer_option(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_file_writer_option(String str, pjmedia_file_writer_option pjmedia_file_writer_optionVar) {
        this.swigName = str;
        this.swigValue = pjmedia_file_writer_optionVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
