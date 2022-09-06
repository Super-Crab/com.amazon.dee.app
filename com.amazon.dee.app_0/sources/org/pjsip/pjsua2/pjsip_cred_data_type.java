package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsip_cred_data_type {
    private final String swigName;
    private final int swigValue;
    public static final pjsip_cred_data_type PJSIP_CRED_DATA_PLAIN_PASSWD = new pjsip_cred_data_type("PJSIP_CRED_DATA_PLAIN_PASSWD", pjsua2JNI.PJSIP_CRED_DATA_PLAIN_PASSWD_get());
    public static final pjsip_cred_data_type PJSIP_CRED_DATA_DIGEST = new pjsip_cred_data_type("PJSIP_CRED_DATA_DIGEST", pjsua2JNI.PJSIP_CRED_DATA_DIGEST_get());
    public static final pjsip_cred_data_type PJSIP_CRED_DATA_EXT_AKA = new pjsip_cred_data_type("PJSIP_CRED_DATA_EXT_AKA", pjsua2JNI.PJSIP_CRED_DATA_EXT_AKA_get());
    private static pjsip_cred_data_type[] swigValues = {PJSIP_CRED_DATA_PLAIN_PASSWD, PJSIP_CRED_DATA_DIGEST, PJSIP_CRED_DATA_EXT_AKA};
    private static int swigNext = 0;

    private pjsip_cred_data_type(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsip_cred_data_type swigToEnum(int i) {
        pjsip_cred_data_type[] pjsip_cred_data_typeVarArr = swigValues;
        if (i >= pjsip_cred_data_typeVarArr.length || i < 0 || pjsip_cred_data_typeVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsip_cred_data_type[] pjsip_cred_data_typeVarArr2 = swigValues;
                if (i2 < pjsip_cred_data_typeVarArr2.length) {
                    if (pjsip_cred_data_typeVarArr2[i2].swigValue == i) {
                        return pjsip_cred_data_typeVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsip_cred_data_type.class, " with value ", i));
                }
            }
        } else {
            return pjsip_cred_data_typeVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_cred_data_type(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_cred_data_type(String str, pjsip_cred_data_type pjsip_cred_data_typeVar) {
        this.swigName = str;
        this.swigValue = pjsip_cred_data_typeVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
