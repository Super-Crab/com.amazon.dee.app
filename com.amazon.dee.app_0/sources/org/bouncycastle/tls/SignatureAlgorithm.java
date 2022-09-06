package org.bouncycastle.tls;
/* loaded from: classes5.dex */
public class SignatureAlgorithm {
    public static final short anonymous = 0;
    public static final short dsa = 2;
    public static final short ecdsa = 3;
    public static final short ed25519 = 7;
    public static final short ed448 = 8;
    public static final short rsa = 1;
    public static final short rsa_pss_pss_sha256 = 9;
    public static final short rsa_pss_pss_sha384 = 10;
    public static final short rsa_pss_pss_sha512 = 11;
    public static final short rsa_pss_rsae_sha256 = 4;
    public static final short rsa_pss_rsae_sha384 = 5;
    public static final short rsa_pss_rsae_sha512 = 6;

    public static short getClientCertificateType(short s) {
        switch (s) {
            case 1:
            case 4:
            case 5:
            case 6:
            case 9:
            case 10:
            case 11:
                return (short) 1;
            case 2:
                return (short) 2;
            case 3:
            case 7:
            case 8:
                return (short) 64;
            default:
                return (short) -1;
        }
    }

    public static String getName(short s) {
        switch (s) {
            case 0:
                return "anonymous";
            case 1:
                return "rsa";
            case 2:
                return "dsa";
            case 3:
                return "ecdsa";
            case 4:
                return "rsa_pss_rsae_sha256";
            case 5:
                return "rsa_pss_rsae_sha384";
            case 6:
                return "rsa_pss_rsae_sha512";
            case 7:
                return "ed25519";
            case 8:
                return "ed448";
            case 9:
                return "rsa_pss_pss_sha256";
            case 10:
                return "rsa_pss_pss_sha384";
            case 11:
                return "rsa_pss_pss_sha512";
            default:
                return "UNKNOWN";
        }
    }

    public static short getRSAPSSHashAlgorithm(short s) {
        switch (s) {
            case 4:
            case 9:
                return (short) 4;
            case 5:
            case 10:
                return (short) 5;
            case 6:
            case 11:
                return (short) 6;
            case 7:
            case 8:
            default:
                return (short) -1;
        }
    }

    public static String getText(short s) {
        return getName(s) + "(" + ((int) s) + ")";
    }

    public static boolean hasIntrinsicHash(short s) {
        switch (s) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return true;
            default:
                return false;
        }
    }

    public static boolean isRSAPSS(short s) {
        switch (s) {
            case 4:
            case 5:
            case 6:
            case 9:
            case 10:
            case 11:
                return true;
            case 7:
            case 8:
            default:
                return false;
        }
    }
}
