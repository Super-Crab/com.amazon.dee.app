package org.pjsip.pjsua2;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes5.dex */
public final class pj_ssl_cipher {
    private final String swigName;
    private final int swigValue;
    public static final pj_ssl_cipher PJ_TLS_UNKNOWN_CIPHER = new pj_ssl_cipher("PJ_TLS_UNKNOWN_CIPHER", pjsua2JNI.PJ_TLS_UNKNOWN_CIPHER_get());
    public static final pj_ssl_cipher PJ_TLS_NULL_WITH_NULL_NULL = new pj_ssl_cipher("PJ_TLS_NULL_WITH_NULL_NULL", pjsua2JNI.PJ_TLS_NULL_WITH_NULL_NULL_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_WITH_NULL_MD5 = new pj_ssl_cipher("PJ_TLS_RSA_WITH_NULL_MD5", pjsua2JNI.PJ_TLS_RSA_WITH_NULL_MD5_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_WITH_NULL_SHA = new pj_ssl_cipher("PJ_TLS_RSA_WITH_NULL_SHA", pjsua2JNI.PJ_TLS_RSA_WITH_NULL_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_WITH_NULL_SHA256 = new pj_ssl_cipher("PJ_TLS_RSA_WITH_NULL_SHA256", pjsua2JNI.PJ_TLS_RSA_WITH_NULL_SHA256_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_WITH_RC4_128_MD5 = new pj_ssl_cipher("PJ_TLS_RSA_WITH_RC4_128_MD5", pjsua2JNI.PJ_TLS_RSA_WITH_RC4_128_MD5_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_WITH_RC4_128_SHA = new pj_ssl_cipher("PJ_TLS_RSA_WITH_RC4_128_SHA", pjsua2JNI.PJ_TLS_RSA_WITH_RC4_128_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_WITH_3DES_EDE_CBC_SHA = new pj_ssl_cipher("PJ_TLS_RSA_WITH_3DES_EDE_CBC_SHA", pjsua2JNI.PJ_TLS_RSA_WITH_3DES_EDE_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_WITH_AES_128_CBC_SHA = new pj_ssl_cipher("PJ_TLS_RSA_WITH_AES_128_CBC_SHA", pjsua2JNI.PJ_TLS_RSA_WITH_AES_128_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_WITH_AES_256_CBC_SHA = new pj_ssl_cipher("PJ_TLS_RSA_WITH_AES_256_CBC_SHA", pjsua2JNI.PJ_TLS_RSA_WITH_AES_256_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_WITH_AES_128_CBC_SHA256 = new pj_ssl_cipher("PJ_TLS_RSA_WITH_AES_128_CBC_SHA256", pjsua2JNI.PJ_TLS_RSA_WITH_AES_128_CBC_SHA256_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_WITH_AES_256_CBC_SHA256 = new pj_ssl_cipher("PJ_TLS_RSA_WITH_AES_256_CBC_SHA256", pjsua2JNI.PJ_TLS_RSA_WITH_AES_256_CBC_SHA256_get());
    public static final pj_ssl_cipher PJ_TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA", pjsua2JNI.PJ_TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA", pjsua2JNI.PJ_TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA", pjsua2JNI.PJ_TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA", pjsua2JNI.PJ_TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_DSS_WITH_AES_128_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_DSS_WITH_AES_128_CBC_SHA", pjsua2JNI.PJ_TLS_DH_DSS_WITH_AES_128_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_RSA_WITH_AES_128_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_RSA_WITH_AES_128_CBC_SHA", pjsua2JNI.PJ_TLS_DH_RSA_WITH_AES_128_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DHE_DSS_WITH_AES_128_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DHE_DSS_WITH_AES_128_CBC_SHA", pjsua2JNI.PJ_TLS_DHE_DSS_WITH_AES_128_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DHE_RSA_WITH_AES_128_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DHE_RSA_WITH_AES_128_CBC_SHA", pjsua2JNI.PJ_TLS_DHE_RSA_WITH_AES_128_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_DSS_WITH_AES_256_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_DSS_WITH_AES_256_CBC_SHA", pjsua2JNI.PJ_TLS_DH_DSS_WITH_AES_256_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_RSA_WITH_AES_256_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_RSA_WITH_AES_256_CBC_SHA", pjsua2JNI.PJ_TLS_DH_RSA_WITH_AES_256_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DHE_DSS_WITH_AES_256_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DHE_DSS_WITH_AES_256_CBC_SHA", pjsua2JNI.PJ_TLS_DHE_DSS_WITH_AES_256_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DHE_RSA_WITH_AES_256_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DHE_RSA_WITH_AES_256_CBC_SHA", pjsua2JNI.PJ_TLS_DHE_RSA_WITH_AES_256_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_DSS_WITH_AES_128_CBC_SHA256 = new pj_ssl_cipher("PJ_TLS_DH_DSS_WITH_AES_128_CBC_SHA256", pjsua2JNI.PJ_TLS_DH_DSS_WITH_AES_128_CBC_SHA256_get());
    public static final pj_ssl_cipher PJ_TLS_DH_RSA_WITH_AES_128_CBC_SHA256 = new pj_ssl_cipher("PJ_TLS_DH_RSA_WITH_AES_128_CBC_SHA256", pjsua2JNI.PJ_TLS_DH_RSA_WITH_AES_128_CBC_SHA256_get());
    public static final pj_ssl_cipher PJ_TLS_DHE_DSS_WITH_AES_128_CBC_SHA256 = new pj_ssl_cipher("PJ_TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", pjsua2JNI.PJ_TLS_DHE_DSS_WITH_AES_128_CBC_SHA256_get());
    public static final pj_ssl_cipher PJ_TLS_DHE_RSA_WITH_AES_128_CBC_SHA256 = new pj_ssl_cipher("PJ_TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", pjsua2JNI.PJ_TLS_DHE_RSA_WITH_AES_128_CBC_SHA256_get());
    public static final pj_ssl_cipher PJ_TLS_DH_DSS_WITH_AES_256_CBC_SHA256 = new pj_ssl_cipher("PJ_TLS_DH_DSS_WITH_AES_256_CBC_SHA256", pjsua2JNI.PJ_TLS_DH_DSS_WITH_AES_256_CBC_SHA256_get());
    public static final pj_ssl_cipher PJ_TLS_DH_RSA_WITH_AES_256_CBC_SHA256 = new pj_ssl_cipher("PJ_TLS_DH_RSA_WITH_AES_256_CBC_SHA256", pjsua2JNI.PJ_TLS_DH_RSA_WITH_AES_256_CBC_SHA256_get());
    public static final pj_ssl_cipher PJ_TLS_DHE_DSS_WITH_AES_256_CBC_SHA256 = new pj_ssl_cipher("PJ_TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", pjsua2JNI.PJ_TLS_DHE_DSS_WITH_AES_256_CBC_SHA256_get());
    public static final pj_ssl_cipher PJ_TLS_DHE_RSA_WITH_AES_256_CBC_SHA256 = new pj_ssl_cipher("PJ_TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", pjsua2JNI.PJ_TLS_DHE_RSA_WITH_AES_256_CBC_SHA256_get());
    public static final pj_ssl_cipher PJ_TLS_DH_anon_WITH_RC4_128_MD5 = new pj_ssl_cipher("PJ_TLS_DH_anon_WITH_RC4_128_MD5", pjsua2JNI.PJ_TLS_DH_anon_WITH_RC4_128_MD5_get());
    public static final pj_ssl_cipher PJ_TLS_DH_anon_WITH_3DES_EDE_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_anon_WITH_3DES_EDE_CBC_SHA", pjsua2JNI.PJ_TLS_DH_anon_WITH_3DES_EDE_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_anon_WITH_AES_128_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_anon_WITH_AES_128_CBC_SHA", pjsua2JNI.PJ_TLS_DH_anon_WITH_AES_128_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_anon_WITH_AES_256_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_anon_WITH_AES_256_CBC_SHA", pjsua2JNI.PJ_TLS_DH_anon_WITH_AES_256_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_anon_WITH_AES_128_CBC_SHA256 = new pj_ssl_cipher("PJ_TLS_DH_anon_WITH_AES_128_CBC_SHA256", pjsua2JNI.PJ_TLS_DH_anon_WITH_AES_128_CBC_SHA256_get());
    public static final pj_ssl_cipher PJ_TLS_DH_anon_WITH_AES_256_CBC_SHA256 = new pj_ssl_cipher("PJ_TLS_DH_anon_WITH_AES_256_CBC_SHA256", pjsua2JNI.PJ_TLS_DH_anon_WITH_AES_256_CBC_SHA256_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_EXPORT_WITH_RC4_40_MD5 = new pj_ssl_cipher("PJ_TLS_RSA_EXPORT_WITH_RC4_40_MD5", pjsua2JNI.PJ_TLS_RSA_EXPORT_WITH_RC4_40_MD5_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5 = new pj_ssl_cipher("PJ_TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5", pjsua2JNI.PJ_TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_WITH_IDEA_CBC_SHA = new pj_ssl_cipher("PJ_TLS_RSA_WITH_IDEA_CBC_SHA", pjsua2JNI.PJ_TLS_RSA_WITH_IDEA_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_EXPORT_WITH_DES40_CBC_SHA = new pj_ssl_cipher("PJ_TLS_RSA_EXPORT_WITH_DES40_CBC_SHA", pjsua2JNI.PJ_TLS_RSA_EXPORT_WITH_DES40_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_RSA_WITH_DES_CBC_SHA = new pj_ssl_cipher("PJ_TLS_RSA_WITH_DES_CBC_SHA", pjsua2JNI.PJ_TLS_RSA_WITH_DES_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA", pjsua2JNI.PJ_TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_DSS_WITH_DES_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_DSS_WITH_DES_CBC_SHA", pjsua2JNI.PJ_TLS_DH_DSS_WITH_DES_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA", pjsua2JNI.PJ_TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_RSA_WITH_DES_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_RSA_WITH_DES_CBC_SHA", pjsua2JNI.PJ_TLS_DH_RSA_WITH_DES_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", pjsua2JNI.PJ_TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DHE_DSS_WITH_DES_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DHE_DSS_WITH_DES_CBC_SHA", pjsua2JNI.PJ_TLS_DHE_DSS_WITH_DES_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", pjsua2JNI.PJ_TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DHE_RSA_WITH_DES_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DHE_RSA_WITH_DES_CBC_SHA", pjsua2JNI.PJ_TLS_DHE_RSA_WITH_DES_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_anon_EXPORT_WITH_RC4_40_MD5 = new pj_ssl_cipher("PJ_TLS_DH_anon_EXPORT_WITH_RC4_40_MD5", pjsua2JNI.PJ_TLS_DH_anon_EXPORT_WITH_RC4_40_MD5_get());
    public static final pj_ssl_cipher PJ_TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA", pjsua2JNI.PJ_TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_TLS_DH_anon_WITH_DES_CBC_SHA = new pj_ssl_cipher("PJ_TLS_DH_anon_WITH_DES_CBC_SHA", pjsua2JNI.PJ_TLS_DH_anon_WITH_DES_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_SSL_FORTEZZA_KEA_WITH_NULL_SHA = new pj_ssl_cipher("PJ_SSL_FORTEZZA_KEA_WITH_NULL_SHA", pjsua2JNI.PJ_SSL_FORTEZZA_KEA_WITH_NULL_SHA_get());
    public static final pj_ssl_cipher PJ_SSL_FORTEZZA_KEA_WITH_FORTEZZA_CBC_SHA = new pj_ssl_cipher("PJ_SSL_FORTEZZA_KEA_WITH_FORTEZZA_CBC_SHA", pjsua2JNI.PJ_SSL_FORTEZZA_KEA_WITH_FORTEZZA_CBC_SHA_get());
    public static final pj_ssl_cipher PJ_SSL_FORTEZZA_KEA_WITH_RC4_128_SHA = new pj_ssl_cipher("PJ_SSL_FORTEZZA_KEA_WITH_RC4_128_SHA", pjsua2JNI.PJ_SSL_FORTEZZA_KEA_WITH_RC4_128_SHA_get());
    public static final pj_ssl_cipher PJ_SSL_CK_RC4_128_WITH_MD5 = new pj_ssl_cipher("PJ_SSL_CK_RC4_128_WITH_MD5", pjsua2JNI.PJ_SSL_CK_RC4_128_WITH_MD5_get());
    public static final pj_ssl_cipher PJ_SSL_CK_RC4_128_EXPORT40_WITH_MD5 = new pj_ssl_cipher("PJ_SSL_CK_RC4_128_EXPORT40_WITH_MD5", pjsua2JNI.PJ_SSL_CK_RC4_128_EXPORT40_WITH_MD5_get());
    public static final pj_ssl_cipher PJ_SSL_CK_RC2_128_CBC_WITH_MD5 = new pj_ssl_cipher("PJ_SSL_CK_RC2_128_CBC_WITH_MD5", pjsua2JNI.PJ_SSL_CK_RC2_128_CBC_WITH_MD5_get());
    public static final pj_ssl_cipher PJ_SSL_CK_RC2_128_CBC_EXPORT40_WITH_MD5 = new pj_ssl_cipher("PJ_SSL_CK_RC2_128_CBC_EXPORT40_WITH_MD5", pjsua2JNI.PJ_SSL_CK_RC2_128_CBC_EXPORT40_WITH_MD5_get());
    public static final pj_ssl_cipher PJ_SSL_CK_IDEA_128_CBC_WITH_MD5 = new pj_ssl_cipher("PJ_SSL_CK_IDEA_128_CBC_WITH_MD5", pjsua2JNI.PJ_SSL_CK_IDEA_128_CBC_WITH_MD5_get());
    public static final pj_ssl_cipher PJ_SSL_CK_DES_64_CBC_WITH_MD5 = new pj_ssl_cipher("PJ_SSL_CK_DES_64_CBC_WITH_MD5", pjsua2JNI.PJ_SSL_CK_DES_64_CBC_WITH_MD5_get());
    public static final pj_ssl_cipher PJ_SSL_CK_DES_192_EDE3_CBC_WITH_MD5 = new pj_ssl_cipher("PJ_SSL_CK_DES_192_EDE3_CBC_WITH_MD5", pjsua2JNI.PJ_SSL_CK_DES_192_EDE3_CBC_WITH_MD5_get());
    private static pj_ssl_cipher[] swigValues = {PJ_TLS_UNKNOWN_CIPHER, PJ_TLS_NULL_WITH_NULL_NULL, PJ_TLS_RSA_WITH_NULL_MD5, PJ_TLS_RSA_WITH_NULL_SHA, PJ_TLS_RSA_WITH_NULL_SHA256, PJ_TLS_RSA_WITH_RC4_128_MD5, PJ_TLS_RSA_WITH_RC4_128_SHA, PJ_TLS_RSA_WITH_3DES_EDE_CBC_SHA, PJ_TLS_RSA_WITH_AES_128_CBC_SHA, PJ_TLS_RSA_WITH_AES_256_CBC_SHA, PJ_TLS_RSA_WITH_AES_128_CBC_SHA256, PJ_TLS_RSA_WITH_AES_256_CBC_SHA256, PJ_TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA, PJ_TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA, PJ_TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA, PJ_TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA, PJ_TLS_DH_DSS_WITH_AES_128_CBC_SHA, PJ_TLS_DH_RSA_WITH_AES_128_CBC_SHA, PJ_TLS_DHE_DSS_WITH_AES_128_CBC_SHA, PJ_TLS_DHE_RSA_WITH_AES_128_CBC_SHA, PJ_TLS_DH_DSS_WITH_AES_256_CBC_SHA, PJ_TLS_DH_RSA_WITH_AES_256_CBC_SHA, PJ_TLS_DHE_DSS_WITH_AES_256_CBC_SHA, PJ_TLS_DHE_RSA_WITH_AES_256_CBC_SHA, PJ_TLS_DH_DSS_WITH_AES_128_CBC_SHA256, PJ_TLS_DH_RSA_WITH_AES_128_CBC_SHA256, PJ_TLS_DHE_DSS_WITH_AES_128_CBC_SHA256, PJ_TLS_DHE_RSA_WITH_AES_128_CBC_SHA256, PJ_TLS_DH_DSS_WITH_AES_256_CBC_SHA256, PJ_TLS_DH_RSA_WITH_AES_256_CBC_SHA256, PJ_TLS_DHE_DSS_WITH_AES_256_CBC_SHA256, PJ_TLS_DHE_RSA_WITH_AES_256_CBC_SHA256, PJ_TLS_DH_anon_WITH_RC4_128_MD5, PJ_TLS_DH_anon_WITH_3DES_EDE_CBC_SHA, PJ_TLS_DH_anon_WITH_AES_128_CBC_SHA, PJ_TLS_DH_anon_WITH_AES_256_CBC_SHA, PJ_TLS_DH_anon_WITH_AES_128_CBC_SHA256, PJ_TLS_DH_anon_WITH_AES_256_CBC_SHA256, PJ_TLS_RSA_EXPORT_WITH_RC4_40_MD5, PJ_TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5, PJ_TLS_RSA_WITH_IDEA_CBC_SHA, PJ_TLS_RSA_EXPORT_WITH_DES40_CBC_SHA, PJ_TLS_RSA_WITH_DES_CBC_SHA, PJ_TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA, PJ_TLS_DH_DSS_WITH_DES_CBC_SHA, PJ_TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA, PJ_TLS_DH_RSA_WITH_DES_CBC_SHA, PJ_TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA, PJ_TLS_DHE_DSS_WITH_DES_CBC_SHA, PJ_TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA, PJ_TLS_DHE_RSA_WITH_DES_CBC_SHA, PJ_TLS_DH_anon_EXPORT_WITH_RC4_40_MD5, PJ_TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA, PJ_TLS_DH_anon_WITH_DES_CBC_SHA, PJ_SSL_FORTEZZA_KEA_WITH_NULL_SHA, PJ_SSL_FORTEZZA_KEA_WITH_FORTEZZA_CBC_SHA, PJ_SSL_FORTEZZA_KEA_WITH_RC4_128_SHA, PJ_SSL_CK_RC4_128_WITH_MD5, PJ_SSL_CK_RC4_128_EXPORT40_WITH_MD5, PJ_SSL_CK_RC2_128_CBC_WITH_MD5, PJ_SSL_CK_RC2_128_CBC_EXPORT40_WITH_MD5, PJ_SSL_CK_IDEA_128_CBC_WITH_MD5, PJ_SSL_CK_DES_64_CBC_WITH_MD5, PJ_SSL_CK_DES_192_EDE3_CBC_WITH_MD5};
    private static int swigNext = 0;

    private pj_ssl_cipher(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    public static pj_ssl_cipher swigToEnum(int i) {
        pj_ssl_cipher[] pj_ssl_cipherVarArr = swigValues;
        if (i >= pj_ssl_cipherVarArr.length || i < 0 || pj_ssl_cipherVarArr[i].swigValue != i) {
            int i2 = 0;
            while (true) {
                pj_ssl_cipher[] pj_ssl_cipherVarArr2 = swigValues;
                if (i2 < pj_ssl_cipherVarArr2.length) {
                    if (pj_ssl_cipherVarArr2[i2].swigValue == i) {
                        return pj_ssl_cipherVarArr2[i2];
                    }
                    i2++;
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", pj_ssl_cipher.class, " with value ", i));
                }
            }
        } else {
            return pj_ssl_cipherVarArr[i];
        }
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    private pj_ssl_cipher(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_ssl_cipher(String str, pj_ssl_cipher pj_ssl_cipherVar) {
        this.swigName = str;
        this.swigValue = pj_ssl_cipherVar.swigValue;
        swigNext = this.swigValue + 1;
    }
}
