package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pj_log_decoration {
    private final String swigName;
    private final int swigValue;
    public static final pj_log_decoration PJ_LOG_HAS_DAY_NAME = new pj_log_decoration("PJ_LOG_HAS_DAY_NAME", pjsua2JNI.PJ_LOG_HAS_DAY_NAME_get());
    public static final pj_log_decoration PJ_LOG_HAS_YEAR = new pj_log_decoration("PJ_LOG_HAS_YEAR", pjsua2JNI.PJ_LOG_HAS_YEAR_get());
    public static final pj_log_decoration PJ_LOG_HAS_MONTH = new pj_log_decoration("PJ_LOG_HAS_MONTH", pjsua2JNI.PJ_LOG_HAS_MONTH_get());
    public static final pj_log_decoration PJ_LOG_HAS_DAY_OF_MON = new pj_log_decoration("PJ_LOG_HAS_DAY_OF_MON", pjsua2JNI.PJ_LOG_HAS_DAY_OF_MON_get());
    public static final pj_log_decoration PJ_LOG_HAS_TIME = new pj_log_decoration("PJ_LOG_HAS_TIME", pjsua2JNI.PJ_LOG_HAS_TIME_get());
    public static final pj_log_decoration PJ_LOG_HAS_MICRO_SEC = new pj_log_decoration("PJ_LOG_HAS_MICRO_SEC", pjsua2JNI.PJ_LOG_HAS_MICRO_SEC_get());
    public static final pj_log_decoration PJ_LOG_HAS_SENDER = new pj_log_decoration("PJ_LOG_HAS_SENDER", pjsua2JNI.PJ_LOG_HAS_SENDER_get());
    public static final pj_log_decoration PJ_LOG_HAS_NEWLINE = new pj_log_decoration("PJ_LOG_HAS_NEWLINE", pjsua2JNI.PJ_LOG_HAS_NEWLINE_get());
    public static final pj_log_decoration PJ_LOG_HAS_CR = new pj_log_decoration("PJ_LOG_HAS_CR", pjsua2JNI.PJ_LOG_HAS_CR_get());
    public static final pj_log_decoration PJ_LOG_HAS_SPACE = new pj_log_decoration("PJ_LOG_HAS_SPACE", pjsua2JNI.PJ_LOG_HAS_SPACE_get());
    public static final pj_log_decoration PJ_LOG_HAS_COLOR = new pj_log_decoration("PJ_LOG_HAS_COLOR", pjsua2JNI.PJ_LOG_HAS_COLOR_get());
    public static final pj_log_decoration PJ_LOG_HAS_LEVEL_TEXT = new pj_log_decoration("PJ_LOG_HAS_LEVEL_TEXT", pjsua2JNI.PJ_LOG_HAS_LEVEL_TEXT_get());
    public static final pj_log_decoration PJ_LOG_HAS_THREAD_ID = new pj_log_decoration("PJ_LOG_HAS_THREAD_ID", pjsua2JNI.PJ_LOG_HAS_THREAD_ID_get());
    public static final pj_log_decoration PJ_LOG_HAS_THREAD_SWC = new pj_log_decoration("PJ_LOG_HAS_THREAD_SWC", pjsua2JNI.PJ_LOG_HAS_THREAD_SWC_get());
    public static final pj_log_decoration PJ_LOG_HAS_INDENT = new pj_log_decoration("PJ_LOG_HAS_INDENT", pjsua2JNI.PJ_LOG_HAS_INDENT_get());
    private static pj_log_decoration[] swigValues = {PJ_LOG_HAS_DAY_NAME, PJ_LOG_HAS_YEAR, PJ_LOG_HAS_MONTH, PJ_LOG_HAS_DAY_OF_MON, PJ_LOG_HAS_TIME, PJ_LOG_HAS_MICRO_SEC, PJ_LOG_HAS_SENDER, PJ_LOG_HAS_NEWLINE, PJ_LOG_HAS_CR, PJ_LOG_HAS_SPACE, PJ_LOG_HAS_COLOR, PJ_LOG_HAS_LEVEL_TEXT, PJ_LOG_HAS_THREAD_ID, PJ_LOG_HAS_THREAD_SWC, PJ_LOG_HAS_INDENT};
    private static int swigNext = 0;

    private pj_log_decoration(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pj_log_decoration swigToEnum(int i) {
        pj_log_decoration[] pj_log_decorationVarArr = swigValues;
        if (i >= pj_log_decorationVarArr.length || i < 0 || pj_log_decorationVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pj_log_decoration[] pj_log_decorationVarArr2 = swigValues;
                if (i2 < pj_log_decorationVarArr2.length) {
                    if (pj_log_decorationVarArr2[i2].swigValue == i) {
                        return pj_log_decorationVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pj_log_decoration.class, " with value ", i));
                }
            }
        } else {
            return pj_log_decorationVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_log_decoration(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_log_decoration(String str, pj_log_decoration pj_log_decorationVar) {
        this.swigName = str;
        this.swigValue = pj_log_decorationVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
