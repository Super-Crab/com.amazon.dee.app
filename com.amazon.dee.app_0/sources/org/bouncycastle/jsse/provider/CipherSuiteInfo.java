package org.bouncycastle.jsse.provider;

import com.amazon.alexa.client.metrics.core.AppInformation;
import com.amazon.devicesetupservice.reporting.KeyExchangeMethod;
import com.amazon.whispercloak.KeyUtils;
import com.amazonaws.services.s3.internal.Constants;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.tls.CipherSuite;
import org.bouncycastle.tls.TlsUtils;
/* loaded from: classes4.dex */
class CipherSuiteInfo {
    private final int cipherSuite;
    private final Set<String> decompositionTLS;
    private final Set<String> decompositionX509;
    private final boolean isTLSv13;
    private final String name;

    private CipherSuiteInfo(int i, String str, boolean z, Set<String> set, Set<String> set2) {
        this.cipherSuite = i;
        this.name = str;
        this.isTLSv13 = z;
        this.decompositionTLS = set;
        this.decompositionX509 = set2;
    }

    private static void addAll(Set<String> set, String... strArr) {
        for (String str : strArr) {
            set.add(str);
        }
    }

    private static void decomposeEncryptionAlgorithm(Set<String> set, int i) {
        String str;
        set.addAll(JcaAlgorithmDecomposer.INSTANCE_JCA.decompose(getTransformation(i)));
        if (i != 0) {
            switch (i) {
                case 7:
                    str = "3DES_EDE_CBC";
                    break;
                case 8:
                    str = "AES_128_CBC";
                    break;
                case 9:
                    str = "AES_256_CBC";
                    break;
                case 10:
                    str = "AES_128_GCM";
                    break;
                case 11:
                    str = "AES_256_GCM";
                    break;
                case 12:
                    str = "CAMELLIA_128_CBC";
                    break;
                case 13:
                    str = "CAMELLIA_256_CBC";
                    break;
                default:
                    switch (i) {
                        case 15:
                            str = "AES_128_CCM";
                            break;
                        case 16:
                            str = "AES_128_CCM_8";
                            break;
                        case 17:
                            str = "AES_256_CCM";
                            break;
                        case 18:
                            str = "AES_256_CCM_8";
                            break;
                        case 19:
                            str = "CAMELLIA_128_GCM";
                            break;
                        case 20:
                            str = "CAMELLIA_256_GCM";
                            break;
                        case 21:
                            return;
                        case 22:
                            str = "ARIA_128_CBC";
                            break;
                        case 23:
                            str = "ARIA_256_CBC";
                            break;
                        case 24:
                            str = "ARIA_128_GCM";
                            break;
                        case 25:
                            str = "ARIA_256_GCM";
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
            }
        } else {
            str = "C_NULL";
        }
        set.add(str);
    }

    private static void decomposeHashAlgorithm(Set<String> set, short s) {
        if (s != 0) {
            if (s == 4) {
                addAll(set, "SHA256", "SHA-256", "HmacSHA256");
            } else if (s != 5) {
                throw new IllegalArgumentException();
            } else {
                addAll(set, "SHA384", "SHA-384", "HmacSHA384");
            }
        }
    }

    private static void decomposeKeyExchangeAlgorithm(Set<String> set, int i) {
        if (i != 0) {
            if (i == 1) {
                addAll(set, KeyUtils.ALGORITHM_RSA);
            } else if (i == 3) {
                addAll(set, "DSA", "DSS", "DH", "DHE", "DiffieHellman", "DHE_DSS");
            } else if (i == 5) {
                addAll(set, KeyUtils.ALGORITHM_RSA, "DH", "DHE", "DiffieHellman", "DHE_RSA");
            } else if (i == 17) {
                addAll(set, KeyExchangeMethod.ECDHE, "ECDSA", "ECDHE_ECDSA");
            } else if (i != 19) {
                throw new IllegalArgumentException();
            } else {
                addAll(set, KeyExchangeMethod.ECDHE, KeyUtils.ALGORITHM_RSA, "ECDHE_RSA");
            }
        }
    }

    private static void decomposeMACAlgorithm(Set<String> set, int i, int i2) {
        if (i2 == 0) {
            if (2 == i) {
                return;
            }
            addAll(set, "M_NULL");
        } else if (i2 == 1) {
            addAll(set, MessageDigestAlgorithms.MD5, "HmacMD5");
        } else if (i2 == 2) {
            addAll(set, "SHA1", "SHA-1", Constants.HMAC_SHA1_ALGORITHM);
        } else if (i2 == 3) {
            addAll(set, "SHA256", "SHA-256", "HmacSHA256");
        } else if (i2 != 4) {
            throw new IllegalArgumentException();
        } else {
            addAll(set, "SHA384", "SHA-384", "HmacSHA384");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CipherSuiteInfo forCipherSuite(int i, String str, boolean z) {
        if (str.startsWith("TLS_")) {
            int encryptionAlgorithm = TlsUtils.getEncryptionAlgorithm(i);
            int encryptionAlgorithmType = TlsUtils.getEncryptionAlgorithmType(encryptionAlgorithm);
            short hashAlgorithm = getHashAlgorithm(i);
            int keyExchangeAlgorithm = TlsUtils.getKeyExchangeAlgorithm(i);
            int mACAlgorithm = TlsUtils.getMACAlgorithm(i);
            HashSet hashSet = new HashSet();
            decomposeKeyExchangeAlgorithm(hashSet, keyExchangeAlgorithm);
            HashSet hashSet2 = new HashSet(hashSet);
            decomposeEncryptionAlgorithm(hashSet2, encryptionAlgorithm);
            decomposeHashAlgorithm(hashSet2, hashAlgorithm);
            decomposeMACAlgorithm(hashSet2, encryptionAlgorithmType, mACAlgorithm);
            return new CipherSuiteInfo(i, str, z, Collections.unmodifiableSet(hashSet2), Collections.unmodifiableSet(hashSet));
        }
        throw new IllegalArgumentException();
    }

    private static short getHashAlgorithm(int i) {
        switch (i) {
            case 2:
            case 10:
            case 19:
            case 22:
            case 47:
            case 50:
            case 51:
            case 53:
            case 56:
            case 57:
            case 59:
            case 60:
            case 61:
            case 64:
            case 65:
            case 68:
            case 69:
            case 103:
            case 106:
            case 107:
            case 132:
            case 135:
            case 136:
            case 156:
            case 158:
            case 162:
            case 186:
            case 189:
            case 190:
            case 192:
            case 195:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /* 196 */:
            case 4865:
            case CipherSuite.TLS_CHACHA20_POLY1305_SHA256 /* 4867 */:
            case 4868:
            case CipherSuite.TLS_AES_128_CCM_8_SHA256 /* 4869 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA /* 49158 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA /* 49160 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA /* 49161 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA /* 49162 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA /* 49168 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA /* 49170 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA /* 49171 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA /* 49172 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /* 49187 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /* 49191 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /* 49195 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /* 49199 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_128_CBC_SHA256 /* 49212 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_CBC_SHA256 /* 49218 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_CBC_SHA256 /* 49220 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_CBC_SHA256 /* 49224 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_CBC_SHA256 /* 49228 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_128_GCM_SHA256 /* 49232 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_128_GCM_SHA256 /* 49234 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_128_GCM_SHA256 /* 49238 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_128_GCM_SHA256 /* 49244 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_128_GCM_SHA256 /* 49248 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49266 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /* 49270 */:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49274 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49276 */:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /* 49280 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49286 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /* 49290 */:
            case CipherSuite.TLS_RSA_WITH_AES_128_CCM /* 49308 */:
            case CipherSuite.TLS_RSA_WITH_AES_256_CCM /* 49309 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /* 49310 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /* 49311 */:
            case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8 /* 49312 */:
            case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8 /* 49313 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /* 49314 */:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /* 49315 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /* 49324 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /* 49325 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /* 49326 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /* 49327 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52392 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /* 52393 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /* 52394 */:
                return (short) 4;
            case 157:
            case 159:
            case 163:
            case 4866:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /* 49188 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /* 49192 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /* 49196 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /* 49200 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_256_CBC_SHA384 /* 49213 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_CBC_SHA384 /* 49219 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_CBC_SHA384 /* 49221 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_CBC_SHA384 /* 49225 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_CBC_SHA384 /* 49229 */:
            case CipherSuite.TLS_RSA_WITH_ARIA_256_GCM_SHA384 /* 49233 */:
            case CipherSuite.TLS_DHE_RSA_WITH_ARIA_256_GCM_SHA384 /* 49235 */:
            case CipherSuite.TLS_DHE_DSS_WITH_ARIA_256_GCM_SHA384 /* 49239 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ARIA_256_GCM_SHA384 /* 49245 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ARIA_256_GCM_SHA384 /* 49249 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49267 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /* 49271 */:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49275 */:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49277 */:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /* 49281 */:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49287 */:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /* 49291 */:
                return (short) 5;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static String getTransformation(int i) {
        if (i != 0) {
            switch (i) {
                case 7:
                    return "DESede/CBC/NoPadding";
                case 8:
                case 9:
                    return "AES/CBC/NoPadding";
                case 10:
                case 11:
                    return "AES/GCM/NoPadding";
                case 12:
                case 13:
                    return "Camellia/CBC/NoPadding";
                default:
                    switch (i) {
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                            return "AES/CCM/NoPadding";
                        case 19:
                        case 20:
                            return "Camellia/GCM/NoPadding";
                        case 21:
                            return "ChaCha20-Poly1305";
                        case 22:
                        case 23:
                            return "ARIA/CBC/NoPadding";
                        case 24:
                        case 25:
                            return "ARIA/GCM/NoPadding";
                        default:
                            throw new IllegalArgumentException();
                    }
            }
        }
        return AppInformation.NULL;
    }

    public int getCipherSuite() {
        return this.cipherSuite;
    }

    public Set<String> getDecompositionTLS() {
        return this.decompositionTLS;
    }

    public Set<String> getDecompositionX509() {
        return this.decompositionX509;
    }

    public String getName() {
        return this.name;
    }

    boolean isTLSv13() {
        return this.isTLSv13;
    }
}
