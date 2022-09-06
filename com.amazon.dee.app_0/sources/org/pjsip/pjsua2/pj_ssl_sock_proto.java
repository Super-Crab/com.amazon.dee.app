package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pj_ssl_sock_proto {
    private final String swigName;
    private final int swigValue;
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_DEFAULT = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_DEFAULT", pjsua2JNI.PJ_SSL_SOCK_PROTO_DEFAULT_get());
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_SSL2 = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_SSL2", pjsua2JNI.PJ_SSL_SOCK_PROTO_SSL2_get());
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_SSL3 = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_SSL3", pjsua2JNI.PJ_SSL_SOCK_PROTO_SSL3_get());
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_TLS1 = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_TLS1", pjsua2JNI.PJ_SSL_SOCK_PROTO_TLS1_get());
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_TLS1_1 = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_TLS1_1", pjsua2JNI.PJ_SSL_SOCK_PROTO_TLS1_1_get());
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_TLS1_2 = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_TLS1_2", pjsua2JNI.PJ_SSL_SOCK_PROTO_TLS1_2_get());
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_SSL23 = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_SSL23", pjsua2JNI.PJ_SSL_SOCK_PROTO_SSL23_get());
    public static final pj_ssl_sock_proto PJ_SSL_SOCK_PROTO_DTLS1 = new pj_ssl_sock_proto("PJ_SSL_SOCK_PROTO_DTLS1", pjsua2JNI.PJ_SSL_SOCK_PROTO_DTLS1_get());
    private static pj_ssl_sock_proto[] swigValues = {PJ_SSL_SOCK_PROTO_DEFAULT, PJ_SSL_SOCK_PROTO_SSL2, PJ_SSL_SOCK_PROTO_SSL3, PJ_SSL_SOCK_PROTO_TLS1, PJ_SSL_SOCK_PROTO_TLS1_1, PJ_SSL_SOCK_PROTO_TLS1_2, PJ_SSL_SOCK_PROTO_SSL23, PJ_SSL_SOCK_PROTO_DTLS1};
    private static int swigNext = 0;

    private pj_ssl_sock_proto(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pj_ssl_sock_proto swigToEnum(int i) {
        pj_ssl_sock_proto[] pj_ssl_sock_protoVarArr = swigValues;
        if (i >= pj_ssl_sock_protoVarArr.length || i < 0 || pj_ssl_sock_protoVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pj_ssl_sock_proto[] pj_ssl_sock_protoVarArr2 = swigValues;
                if (i2 < pj_ssl_sock_protoVarArr2.length) {
                    if (pj_ssl_sock_protoVarArr2[i2].swigValue == i) {
                        return pj_ssl_sock_protoVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pj_ssl_sock_proto.class, " with value ", i));
                }
            }
        } else {
            return pj_ssl_sock_protoVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_ssl_sock_proto(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_ssl_sock_proto(String str, pj_ssl_sock_proto pj_ssl_sock_protoVar) {
        this.swigName = str;
        this.swigValue = pj_ssl_sock_protoVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
