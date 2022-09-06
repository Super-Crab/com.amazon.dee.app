package org.bouncycastle.tls;
/* loaded from: classes5.dex */
public class PRFAlgorithm {
    public static final int ssl_prf_legacy = 0;
    public static final int tls13_hkdf_sha256 = 4;
    public static final int tls13_hkdf_sha384 = 5;
    public static final int tls_prf_legacy = 1;
    public static final int tls_prf_sha256 = 2;
    public static final int tls_prf_sha384 = 3;

    public static String getName(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNKNOWN" : "tls13_hkdf_sha384" : "tls13_hkdf_sha256" : "tls_prf_sha384" : "tls_prf_sha256" : "tls_prf_legacy" : "ssl_prf_legacy";
    }

    public static String getText(int i) {
        return getName(i) + "(" + i + ")";
    }
}
