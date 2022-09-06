package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsua_create_media_transport_flag {
    private final String swigName;
    private final int swigValue;
    public static final pjsua_create_media_transport_flag PJSUA_MED_TP_CLOSE_MEMBER = new pjsua_create_media_transport_flag("PJSUA_MED_TP_CLOSE_MEMBER", pjsua2JNI.PJSUA_MED_TP_CLOSE_MEMBER_get());
    private static pjsua_create_media_transport_flag[] swigValues = {PJSUA_MED_TP_CLOSE_MEMBER};
    private static int swigNext = 0;

    private pjsua_create_media_transport_flag(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsua_create_media_transport_flag swigToEnum(int i) {
        pjsua_create_media_transport_flag[] pjsua_create_media_transport_flagVarArr = swigValues;
        if (i >= pjsua_create_media_transport_flagVarArr.length || i < 0 || pjsua_create_media_transport_flagVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsua_create_media_transport_flag[] pjsua_create_media_transport_flagVarArr2 = swigValues;
                if (i2 < pjsua_create_media_transport_flagVarArr2.length) {
                    if (pjsua_create_media_transport_flagVarArr2[i2].swigValue == i) {
                        return pjsua_create_media_transport_flagVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsua_create_media_transport_flag.class, " with value ", i));
                }
            }
        } else {
            return pjsua_create_media_transport_flagVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsua_create_media_transport_flag(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_create_media_transport_flag(String str, pjsua_create_media_transport_flag pjsua_create_media_transport_flagVar) {
        this.swigName = str;
        this.swigValue = pjsua_create_media_transport_flagVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
