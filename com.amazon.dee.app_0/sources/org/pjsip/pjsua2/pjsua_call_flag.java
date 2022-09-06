package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_call_flag {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_call_flag PJSUA_CALL_UNHOLD = new pjsua_call_flag("PJSUA_CALL_UNHOLD", pjsua2JNI.PJSUA_CALL_UNHOLD_get());
    public static final pjsua_call_flag PJSUA_CALL_UPDATE_CONTACT = new pjsua_call_flag("PJSUA_CALL_UPDATE_CONTACT", pjsua2JNI.PJSUA_CALL_UPDATE_CONTACT_get());
    public static final pjsua_call_flag PJSUA_CALL_INCLUDE_DISABLED_MEDIA = new pjsua_call_flag("PJSUA_CALL_INCLUDE_DISABLED_MEDIA", pjsua2JNI.PJSUA_CALL_INCLUDE_DISABLED_MEDIA_get());
    public static final pjsua_call_flag PJSUA_CALL_NO_SDP_OFFER = new pjsua_call_flag("PJSUA_CALL_NO_SDP_OFFER", pjsua2JNI.PJSUA_CALL_NO_SDP_OFFER_get());
    private static pjsua_call_flag[] swigValues = {PJSUA_CALL_UNHOLD, PJSUA_CALL_UPDATE_CONTACT, PJSUA_CALL_INCLUDE_DISABLED_MEDIA, PJSUA_CALL_NO_SDP_OFFER};
    private static int swigNext = 0;

    private pjsua_call_flag(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_call_flag swigToEnum(int i) {
        pjsua_call_flag[] pjsua_call_flagVarArr = swigValues;
        if (i >= pjsua_call_flagVarArr.length || i < 0 || pjsua_call_flagVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_call_flag[] pjsua_call_flagVarArr2 = swigValues;
                if (i2 < pjsua_call_flagVarArr2.length) {
                    if (pjsua_call_flagVarArr2[i2].swigValue == i) {
                        return pjsua_call_flagVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_call_flag.class, " with value ", i));
                }
            }
        } else {
            return pjsua_call_flagVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_call_flag(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_call_flag(String str, pjsua_call_flag pjsua_call_flagVar) {
        this.swigName = str;
        this.swigValue = pjsua_call_flagVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
