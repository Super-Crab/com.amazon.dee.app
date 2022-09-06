package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pjsip_ssl_method {
    private final String swigName;
    private final int swigValue;
    public static final pjsip_ssl_method PJSIP_SSL_UNSPECIFIED_METHOD = new pjsip_ssl_method("PJSIP_SSL_UNSPECIFIED_METHOD", pjsua2JNI.PJSIP_SSL_UNSPECIFIED_METHOD_get());
    public static final pjsip_ssl_method PJSIP_SSLV2_METHOD = new pjsip_ssl_method("PJSIP_SSLV2_METHOD", pjsua2JNI.PJSIP_SSLV2_METHOD_get());
    public static final pjsip_ssl_method PJSIP_SSLV3_METHOD = new pjsip_ssl_method("PJSIP_SSLV3_METHOD", pjsua2JNI.PJSIP_SSLV3_METHOD_get());
    public static final pjsip_ssl_method PJSIP_TLSV1_METHOD = new pjsip_ssl_method("PJSIP_TLSV1_METHOD", pjsua2JNI.PJSIP_TLSV1_METHOD_get());
    public static final pjsip_ssl_method PJSIP_TLSV1_1_METHOD = new pjsip_ssl_method("PJSIP_TLSV1_1_METHOD", pjsua2JNI.PJSIP_TLSV1_1_METHOD_get());
    public static final pjsip_ssl_method PJSIP_TLSV1_2_METHOD = new pjsip_ssl_method("PJSIP_TLSV1_2_METHOD", pjsua2JNI.PJSIP_TLSV1_2_METHOD_get());
    public static final pjsip_ssl_method PJSIP_SSLV23_METHOD = new pjsip_ssl_method("PJSIP_SSLV23_METHOD", pjsua2JNI.PJSIP_SSLV23_METHOD_get());
    private static pjsip_ssl_method[] swigValues = {PJSIP_SSL_UNSPECIFIED_METHOD, PJSIP_SSLV2_METHOD, PJSIP_SSLV3_METHOD, PJSIP_TLSV1_METHOD, PJSIP_TLSV1_1_METHOD, PJSIP_TLSV1_2_METHOD, PJSIP_SSLV23_METHOD};
    private static int swigNext = 0;

    private pjsip_ssl_method(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pjsip_ssl_method swigToEnum(int i) {
        pjsip_ssl_method[] pjsip_ssl_methodVarArr = swigValues;
        if (i >= pjsip_ssl_methodVarArr.length || i < 0 || pjsip_ssl_methodVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pjsip_ssl_method[] pjsip_ssl_methodVarArr2 = swigValues;
                if (i2 < pjsip_ssl_methodVarArr2.length) {
                    if (pjsip_ssl_methodVarArr2[i2].swigValue == i) {
                        return pjsip_ssl_methodVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pjsip_ssl_method.class, " with value ", i));
                }
            }
        } else {
            return pjsip_ssl_methodVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pjsip_ssl_method(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_ssl_method(String str, pjsip_ssl_method pjsip_ssl_methodVar) {
        this.swigName = str;
        this.swigValue = pjsip_ssl_methodVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
