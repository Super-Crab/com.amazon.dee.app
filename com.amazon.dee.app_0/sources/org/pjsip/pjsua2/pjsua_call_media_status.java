package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_call_media_status {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_NONE = new pjsua_call_media_status("PJSUA_CALL_MEDIA_NONE");
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_ACTIVE = new pjsua_call_media_status("PJSUA_CALL_MEDIA_ACTIVE");
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_LOCAL_HOLD = new pjsua_call_media_status("PJSUA_CALL_MEDIA_LOCAL_HOLD");
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_REMOTE_HOLD = new pjsua_call_media_status("PJSUA_CALL_MEDIA_REMOTE_HOLD");
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_ERROR = new pjsua_call_media_status("PJSUA_CALL_MEDIA_ERROR");
    private static pjsua_call_media_status[] swigValues = {PJSUA_CALL_MEDIA_NONE, PJSUA_CALL_MEDIA_ACTIVE, PJSUA_CALL_MEDIA_LOCAL_HOLD, PJSUA_CALL_MEDIA_REMOTE_HOLD, PJSUA_CALL_MEDIA_ERROR};
    private static int swigNext = 0;

    private pjsua_call_media_status(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_call_media_status swigToEnum(int i) {
        pjsua_call_media_status[] pjsua_call_media_statusVarArr = swigValues;
        if (i >= pjsua_call_media_statusVarArr.length || i < 0 || pjsua_call_media_statusVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_call_media_status[] pjsua_call_media_statusVarArr2 = swigValues;
                if (i2 < pjsua_call_media_statusVarArr2.length) {
                    if (pjsua_call_media_statusVarArr2[i2].swigValue == i) {
                        return pjsua_call_media_statusVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_call_media_status.class, " with value ", i));
                }
            }
        } else {
            return pjsua_call_media_statusVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_call_media_status(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_call_media_status(String str, pjsua_call_media_status pjsua_call_media_statusVar) {
        this.swigName = str;
        this.swigValue = pjsua_call_media_statusVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
