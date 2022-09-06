package org.apache.commons.lang;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
/* loaded from: classes4.dex */
public class BooleanUtils {
    public static boolean isFalse(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return !bool.booleanValue();
    }

    public static boolean isNotFalse(Boolean bool) {
        return !isFalse(bool);
    }

    public static boolean isNotTrue(Boolean bool) {
        return !isTrue(bool);
    }

    public static boolean isTrue(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public static Boolean negate(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return bool.booleanValue() ? Boolean.FALSE : Boolean.TRUE;
    }

    public static boolean toBoolean(int i) {
        return i != 0;
    }

    public static boolean toBoolean(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public static boolean toBooleanDefaultIfNull(Boolean bool, boolean z) {
        return bool == null ? z : bool.booleanValue();
    }

    public static Boolean toBooleanObject(boolean z) {
        return z ? Boolean.TRUE : Boolean.FALSE;
    }

    public static int toInteger(Boolean bool, int i, int i2, int i3) {
        return bool == null ? i3 : bool.booleanValue() ? i : i2;
    }

    public static int toInteger(boolean z) {
        return z ? 1 : 0;
    }

    public static int toInteger(boolean z, int i, int i2) {
        return z ? i : i2;
    }

    public static Integer toIntegerObject(boolean z) {
        return z ? org.apache.commons.lang.math.NumberUtils.INTEGER_ONE : org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO;
    }

    public static Integer toIntegerObject(boolean z, Integer num, Integer num2) {
        return z ? num : num2;
    }

    public static String toString(Boolean bool, String str, String str2, String str3) {
        return bool == null ? str3 : bool.booleanValue() ? str : str2;
    }

    public static String toString(boolean z, String str, String str2) {
        return z ? str : str2;
    }

    public static String toStringOnOff(Boolean bool) {
        return toString(bool, "on", "off", null);
    }

    public static String toStringTrueFalse(Boolean bool) {
        return toString(bool, "true", PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE, null);
    }

    public static String toStringYesNo(Boolean bool) {
        return toString(bool, "yes", "no", null);
    }

    public static boolean xor(boolean[] zArr) {
        if (zArr != null) {
            if (zArr.length != 0) {
                int i = 0;
                for (boolean z : zArr) {
                    if (z) {
                        if (i >= 1) {
                            return false;
                        }
                        i++;
                    }
                }
                return i == 1;
            }
            throw new IllegalArgumentException("Array is empty");
        }
        throw new IllegalArgumentException("The Array must not be null");
    }

    public static boolean toBoolean(int i, int i2, int i3) {
        if (i == i2) {
            return true;
        }
        if (i != i3) {
            throw new IllegalArgumentException("The Integer did not match either specified value");
        }
        return false;
    }

    public static Boolean toBooleanObject(int i) {
        return i == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Integer toIntegerObject(Boolean bool) {
        if (bool == null) {
            return null;
        }
        return bool.booleanValue() ? org.apache.commons.lang.math.NumberUtils.INTEGER_ONE : org.apache.commons.lang.math.NumberUtils.INTEGER_ZERO;
    }

    public static String toStringOnOff(boolean z) {
        return toString(z, "on", "off");
    }

    public static String toStringTrueFalse(boolean z) {
        return toString(z, "true", PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE);
    }

    public static String toStringYesNo(boolean z) {
        return toString(z, "yes", "no");
    }

    public static boolean toBoolean(Integer num, Integer num2, Integer num3) {
        if (num == null) {
            if (num2 == null) {
                return true;
            }
            if (num3 == null) {
                return false;
            }
        } else if (num.equals(num2)) {
            return true;
        } else {
            if (num.equals(num3)) {
                return false;
            }
        }
        throw new IllegalArgumentException("The Integer did not match either specified value");
    }

    public static Boolean toBooleanObject(Integer num) {
        if (num == null) {
            return null;
        }
        return num.intValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Integer toIntegerObject(Boolean bool, Integer num, Integer num2, Integer num3) {
        return bool == null ? num3 : bool.booleanValue() ? num : num2;
    }

    public static Boolean toBooleanObject(int i, int i2, int i3, int i4) {
        if (i == i2) {
            return Boolean.TRUE;
        }
        if (i == i3) {
            return Boolean.FALSE;
        }
        if (i != i4) {
            throw new IllegalArgumentException("The Integer did not match any specified value");
        }
        return null;
    }

    public static boolean toBoolean(String str) {
        return toBoolean(toBooleanObject(str));
    }

    public static Boolean xor(Boolean[] boolArr) {
        if (boolArr != null) {
            if (boolArr.length != 0) {
                try {
                    return xor(ArrayUtils.toPrimitive(boolArr)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (NullPointerException unused) {
                    throw new IllegalArgumentException("The array must not contain any null elements");
                }
            }
            throw new IllegalArgumentException("Array is empty");
        }
        throw new IllegalArgumentException("The Array must not be null");
    }

    public static boolean toBoolean(String str, String str2, String str3) {
        if (str == null) {
            if (str2 == null) {
                return true;
            }
            if (str3 == null) {
                return false;
            }
        } else if (str.equals(str2)) {
            return true;
        } else {
            if (str.equals(str3)) {
                return false;
            }
        }
        throw new IllegalArgumentException("The String did not match either specified value");
    }

    public static Boolean toBooleanObject(Integer num, Integer num2, Integer num3, Integer num4) {
        if (num == null) {
            if (num2 == null) {
                return Boolean.TRUE;
            }
            if (num3 == null) {
                return Boolean.FALSE;
            }
            if (num4 == null) {
                return null;
            }
        } else if (num.equals(num2)) {
            return Boolean.TRUE;
        } else {
            if (num.equals(num3)) {
                return Boolean.FALSE;
            }
            if (num.equals(num4)) {
                return null;
            }
        }
        throw new IllegalArgumentException("The Integer did not match any specified value");
    }

    public static Boolean toBooleanObject(String str) {
        if (str == "true") {
            return Boolean.TRUE;
        }
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 1) {
            char charAt = str.charAt(0);
            if (charAt == 'y' || charAt == 'Y' || charAt == 't' || charAt == 'T') {
                return Boolean.TRUE;
            }
            if (charAt == 'n' || charAt == 'N' || charAt == 'f' || charAt == 'F') {
                return Boolean.FALSE;
            }
        } else if (length == 2) {
            char charAt2 = str.charAt(0);
            char charAt3 = str.charAt(1);
            if ((charAt2 == 'o' || charAt2 == 'O') && (charAt3 == 'n' || charAt3 == 'N')) {
                return Boolean.TRUE;
            }
            if ((charAt2 == 'n' || charAt2 == 'N') && (charAt3 == 'o' || charAt3 == 'O')) {
                return Boolean.FALSE;
            }
        } else if (length == 3) {
            char charAt4 = str.charAt(0);
            char charAt5 = str.charAt(1);
            char charAt6 = str.charAt(2);
            if ((charAt4 == 'y' || charAt4 == 'Y') && ((charAt5 == 'e' || charAt5 == 'E') && (charAt6 == 's' || charAt6 == 'S'))) {
                return Boolean.TRUE;
            }
            if ((charAt4 == 'o' || charAt4 == 'O') && ((charAt5 == 'f' || charAt5 == 'F') && (charAt6 == 'f' || charAt6 == 'F'))) {
                return Boolean.FALSE;
            }
        } else if (length == 4) {
            char charAt7 = str.charAt(0);
            char charAt8 = str.charAt(1);
            char charAt9 = str.charAt(2);
            char charAt10 = str.charAt(3);
            if ((charAt7 == 't' || charAt7 == 'T') && ((charAt8 == 'r' || charAt8 == 'R') && ((charAt9 == 'u' || charAt9 == 'U') && (charAt10 == 'e' || charAt10 == 'E')))) {
                return Boolean.TRUE;
            }
        } else if (length == 5) {
            char charAt11 = str.charAt(0);
            char charAt12 = str.charAt(1);
            char charAt13 = str.charAt(2);
            char charAt14 = str.charAt(3);
            char charAt15 = str.charAt(4);
            if ((charAt11 == 'f' || charAt11 == 'F') && ((charAt12 == 'a' || charAt12 == 'A') && ((charAt13 == 'l' || charAt13 == 'L') && ((charAt14 == 's' || charAt14 == 'S') && (charAt15 == 'e' || charAt15 == 'E'))))) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    public static Boolean toBooleanObject(String str, String str2, String str3, String str4) {
        if (str == null) {
            if (str2 == null) {
                return Boolean.TRUE;
            }
            if (str3 == null) {
                return Boolean.FALSE;
            }
            if (str4 == null) {
                return null;
            }
        } else if (str.equals(str2)) {
            return Boolean.TRUE;
        } else {
            if (str.equals(str3)) {
                return Boolean.FALSE;
            }
            if (str.equals(str4)) {
                return null;
            }
        }
        throw new IllegalArgumentException("The String did not match any specified value");
    }
}
