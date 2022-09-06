package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsip_dialog_cap_status {
    private final String swigName;
    private final int swigValue;
    public static final pjsip_dialog_cap_status PJSIP_DIALOG_CAP_UNSUPPORTED = new pjsip_dialog_cap_status("PJSIP_DIALOG_CAP_UNSUPPORTED", pjsua2JNI.PJSIP_DIALOG_CAP_UNSUPPORTED_get());
    public static final pjsip_dialog_cap_status PJSIP_DIALOG_CAP_SUPPORTED = new pjsip_dialog_cap_status("PJSIP_DIALOG_CAP_SUPPORTED", pjsua2JNI.PJSIP_DIALOG_CAP_SUPPORTED_get());
    public static final pjsip_dialog_cap_status PJSIP_DIALOG_CAP_UNKNOWN = new pjsip_dialog_cap_status("PJSIP_DIALOG_CAP_UNKNOWN", pjsua2JNI.PJSIP_DIALOG_CAP_UNKNOWN_get());
    private static pjsip_dialog_cap_status[] swigValues = {PJSIP_DIALOG_CAP_UNSUPPORTED, PJSIP_DIALOG_CAP_SUPPORTED, PJSIP_DIALOG_CAP_UNKNOWN};
    private static int swigNext = 0;

    private pjsip_dialog_cap_status(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsip_dialog_cap_status swigToEnum(int i) {
        pjsip_dialog_cap_status[] pjsip_dialog_cap_statusVarArr = swigValues;
        if (i >= pjsip_dialog_cap_statusVarArr.length || i < 0 || pjsip_dialog_cap_statusVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsip_dialog_cap_status[] pjsip_dialog_cap_statusVarArr2 = swigValues;
                if (i2 < pjsip_dialog_cap_statusVarArr2.length) {
                    if (pjsip_dialog_cap_statusVarArr2[i2].swigValue == i) {
                        return pjsip_dialog_cap_statusVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsip_dialog_cap_status.class, " with value ", i));
                }
            }
        } else {
            return pjsip_dialog_cap_statusVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_dialog_cap_status(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_dialog_cap_status(String str, pjsip_dialog_cap_status pjsip_dialog_cap_statusVar) {
        this.swigName = str;
        this.swigValue = pjsip_dialog_cap_statusVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
