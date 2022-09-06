package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjmedia_srtp_crypto_option {
    private final String swigName;
    private final int swigValue;
    public static final pjmedia_srtp_crypto_option PJMEDIA_SRTP_NO_ENCRYPTION = new pjmedia_srtp_crypto_option("PJMEDIA_SRTP_NO_ENCRYPTION", pjsua2JNI.PJMEDIA_SRTP_NO_ENCRYPTION_get());
    public static final pjmedia_srtp_crypto_option PJMEDIA_SRTP_NO_AUTHENTICATION = new pjmedia_srtp_crypto_option("PJMEDIA_SRTP_NO_AUTHENTICATION", pjsua2JNI.PJMEDIA_SRTP_NO_AUTHENTICATION_get());
    private static pjmedia_srtp_crypto_option[] swigValues = {PJMEDIA_SRTP_NO_ENCRYPTION, PJMEDIA_SRTP_NO_AUTHENTICATION};
    private static int swigNext = 0;

    private pjmedia_srtp_crypto_option(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjmedia_srtp_crypto_option swigToEnum(int i) {
        pjmedia_srtp_crypto_option[] pjmedia_srtp_crypto_optionVarArr = swigValues;
        if (i >= pjmedia_srtp_crypto_optionVarArr.length || i < 0 || pjmedia_srtp_crypto_optionVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjmedia_srtp_crypto_option[] pjmedia_srtp_crypto_optionVarArr2 = swigValues;
                if (i2 < pjmedia_srtp_crypto_optionVarArr2.length) {
                    if (pjmedia_srtp_crypto_optionVarArr2[i2].swigValue == i) {
                        return pjmedia_srtp_crypto_optionVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjmedia_srtp_crypto_option.class, " with value ", i));
                }
            }
        } else {
            return pjmedia_srtp_crypto_optionVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjmedia_srtp_crypto_option(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_srtp_crypto_option(String str, pjmedia_srtp_crypto_option pjmedia_srtp_crypto_optionVar) {
        this.swigName = str;
        this.swigValue = pjmedia_srtp_crypto_optionVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
