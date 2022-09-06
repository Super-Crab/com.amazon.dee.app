package org.bouncycastle.tls;

import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.net.telnet.TelnetCommand;
/* loaded from: classes5.dex */
public class NamedGroup {
    private static final String[] CURVE_NAMES = {"sect163k1", "sect163r1", "sect163r2", "sect193r1", "sect193r2", "sect233k1", "sect233r1", "sect239k1", "sect283k1", "sect283r1", "sect409k1", "sect409r1", "sect571k1", "sect571r1", "secp160k1", "secp160r1", "secp160r2", "secp192k1", "secp192r1", "secp224k1", "secp224r1", "secp256k1", "secp256r1", "secp384r1", "secp521r1", "brainpoolp256r1", "brainpoolp384r1", "brainpoolp512r1", "x25519", "x448"};
    private static final String[] FINITE_FIELD_NAMES = {"ffdhe2048", "ffdhe3072", "ffdhe4096", "ffdhe6144", "ffdhe8192"};
    public static final int arbitrary_explicit_char2_curves = 65282;
    public static final int arbitrary_explicit_prime_curves = 65281;
    public static final int brainpoolP256r1 = 26;
    public static final int brainpoolP384r1 = 27;
    public static final int brainpoolP512r1 = 28;
    public static final int ffdhe2048 = 256;
    public static final int ffdhe3072 = 257;
    public static final int ffdhe4096 = 258;
    public static final int ffdhe6144 = 259;
    public static final int ffdhe8192 = 260;
    public static final int secp160k1 = 15;
    public static final int secp160r1 = 16;
    public static final int secp160r2 = 17;
    public static final int secp192k1 = 18;
    public static final int secp192r1 = 19;
    public static final int secp224k1 = 20;
    public static final int secp224r1 = 21;
    public static final int secp256k1 = 22;
    public static final int secp256r1 = 23;
    public static final int secp384r1 = 24;
    public static final int secp521r1 = 25;
    public static final int sect163k1 = 1;
    public static final int sect163r1 = 2;
    public static final int sect163r2 = 3;
    public static final int sect193r1 = 4;
    public static final int sect193r2 = 5;
    public static final int sect233k1 = 6;
    public static final int sect233r1 = 7;
    public static final int sect239k1 = 8;
    public static final int sect283k1 = 9;
    public static final int sect283r1 = 10;
    public static final int sect409k1 = 11;
    public static final int sect409r1 = 12;
    public static final int sect571k1 = 13;
    public static final int sect571r1 = 14;
    public static final int x25519 = 29;
    public static final int x448 = 30;

    public static boolean canBeNegotiated(int i, ProtocolVersion protocolVersion) {
        if (TlsUtils.isTLSv13(protocolVersion)) {
            if (i >= 1 && i <= 22) {
                return false;
            }
            if (i >= 26 && i <= 28) {
                return false;
            }
            if (i >= 65281 && i <= 65282) {
                return false;
            }
        }
        return isValid(i);
    }

    public static int getByName(String str) {
        boolean equals;
        if (str != null) {
            int i = 0;
            int i2 = 0;
            do {
                String[] strArr = CURVE_NAMES;
                if (i2 < strArr.length) {
                    equals = str.equals(strArr[i2]);
                    i2++;
                } else {
                    while (true) {
                        String[] strArr2 = FINITE_FIELD_NAMES;
                        if (i >= strArr2.length) {
                            return -1;
                        }
                        if (str.equals(strArr2[i])) {
                            return i + 256;
                        }
                        i++;
                    }
                }
            } while (!equals);
            return i2;
        }
        return -1;
    }

    public static int getCurveBits(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
                return 163;
            case 4:
            case 5:
                return 193;
            case 6:
            case 7:
                return 233;
            case 8:
                return TelnetCommand.EOR;
            case 9:
            case 10:
                return 283;
            case 11:
            case 12:
                return HttpServletResponse.SC_CONFLICT;
            case 13:
            case 14:
                return 571;
            case 15:
            case 16:
            case 17:
                return 160;
            case 18:
            case 19:
                return 192;
            case 20:
            case 21:
                return 224;
            case 22:
            case 23:
            case 26:
                return 256;
            case 24:
            case 27:
                return BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT;
            case 25:
                return 521;
            case 28:
                return 512;
            case 29:
                return TelnetCommand.WONT;
            case 30:
                return 446;
            default:
                return 0;
        }
    }

    public static int getFiniteFieldBits(int i) {
        switch (i) {
            case 256:
                return 2048;
            case 257:
                return 3072;
            case 258:
                return 4096;
            case 259:
                return 6144;
            case 260:
                return 8192;
            default:
                return 0;
        }
    }

    public static int getMaximumChar2CurveBits() {
        return 571;
    }

    public static int getMaximumCurveBits() {
        return 571;
    }

    public static int getMaximumFiniteFieldBits() {
        return 8192;
    }

    public static int getMaximumPrimeCurveBits() {
        return 521;
    }

    public static String getName(int i) {
        if (isPrivate(i)) {
            return "PRIVATE";
        }
        if (i >= 1 && i <= 30) {
            return CURVE_NAMES[i - 1];
        }
        if (i >= 256 && i <= 260) {
            return FINITE_FIELD_NAMES[i - 256];
        }
        switch (i) {
            case 65281:
                return "arbitrary_explicit_prime_curves";
            case arbitrary_explicit_char2_curves /* 65282 */:
                return "arbitrary_explicit_char2_curves";
            default:
                return "UNKNOWN";
        }
    }

    public static String getText(int i) {
        return getName(i) + "(" + i + ")";
    }

    public static boolean isChar2Curve(int i) {
        return (i >= 1 && i <= 14) || i == 65282;
    }

    public static boolean isPrimeCurve(int i) {
        return (i >= 15 && i <= 30) || i == 65281;
    }

    public static boolean isPrivate(int i) {
        return (i >>> 2) == 127 || (i >>> 8) == 254;
    }

    public static boolean isValid(int i) {
        return refersToASpecificGroup(i) || isPrivate(i) || (i >= 65281 && i <= 65282);
    }

    public static boolean refersToASpecificCurve(int i) {
        return i >= 1 && i <= 30;
    }

    public static boolean refersToASpecificFiniteField(int i) {
        return i >= 256 && i <= 260;
    }

    public static boolean refersToASpecificGroup(int i) {
        return refersToASpecificCurve(i) || refersToASpecificFiniteField(i);
    }

    public static boolean refersToAnECDHCurve(int i) {
        return i >= 1 && i <= 30;
    }

    public static boolean refersToAnECDSACurve(int i) {
        return i >= 1 && i <= 28;
    }

    public static boolean refersToAnXDHCurve(int i) {
        return i >= 29 && i <= 30;
    }
}
