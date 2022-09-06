package org.slf4j.helpers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes5.dex */
public final class MessageFormatter {
    static final char DELIM_START = '{';
    static final char DELIM_STOP = '}';
    static final String DELIM_STR = "{}";
    private static final char ESCAPE_CHAR = '\\';

    public static final FormattingTuple arrayFormat(String str, Object[] objArr) {
        Throwable throwableCandidate = getThrowableCandidate(objArr);
        if (throwableCandidate != null) {
            objArr = trimmedCopy(objArr);
        }
        return arrayFormat(str, objArr, throwableCandidate);
    }

    private static void booleanArrayAppend(StringBuilder sb, boolean[] zArr) {
        sb.append(JsonReaderKt.BEGIN_LIST);
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(zArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(JsonReaderKt.END_LIST);
    }

    private static void byteArrayAppend(StringBuilder sb, byte[] bArr) {
        sb.append(JsonReaderKt.BEGIN_LIST);
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append((int) bArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(JsonReaderKt.END_LIST);
    }

    private static void charArrayAppend(StringBuilder sb, char[] cArr) {
        sb.append(JsonReaderKt.BEGIN_LIST);
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(cArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(JsonReaderKt.END_LIST);
    }

    private static void deeplyAppendParameter(StringBuilder sb, Object obj, Map<Object[], Object> map) {
        if (obj == null) {
            sb.append("null");
        } else if (!obj.getClass().isArray()) {
            safeObjectAppend(sb, obj);
        } else if (obj instanceof boolean[]) {
            booleanArrayAppend(sb, (boolean[]) obj);
        } else if (obj instanceof byte[]) {
            byteArrayAppend(sb, (byte[]) obj);
        } else if (obj instanceof char[]) {
            charArrayAppend(sb, (char[]) obj);
        } else if (obj instanceof short[]) {
            shortArrayAppend(sb, (short[]) obj);
        } else if (obj instanceof int[]) {
            intArrayAppend(sb, (int[]) obj);
        } else if (obj instanceof long[]) {
            longArrayAppend(sb, (long[]) obj);
        } else if (obj instanceof float[]) {
            floatArrayAppend(sb, (float[]) obj);
        } else if (obj instanceof double[]) {
            doubleArrayAppend(sb, (double[]) obj);
        } else {
            objectArrayAppend(sb, (Object[]) obj, map);
        }
    }

    private static void doubleArrayAppend(StringBuilder sb, double[] dArr) {
        sb.append(JsonReaderKt.BEGIN_LIST);
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(dArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(JsonReaderKt.END_LIST);
    }

    private static void floatArrayAppend(StringBuilder sb, float[] fArr) {
        sb.append(JsonReaderKt.BEGIN_LIST);
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(fArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(JsonReaderKt.END_LIST);
    }

    public static final FormattingTuple format(String str, Object obj) {
        return arrayFormat(str, new Object[]{obj});
    }

    public static Throwable getThrowableCandidate(Object[] objArr) {
        if (objArr != null && objArr.length != 0) {
            Object obj = objArr[objArr.length - 1];
            if (obj instanceof Throwable) {
                return (Throwable) obj;
            }
        }
        return null;
    }

    private static void intArrayAppend(StringBuilder sb, int[] iArr) {
        sb.append(JsonReaderKt.BEGIN_LIST);
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(iArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(JsonReaderKt.END_LIST);
    }

    static final boolean isDoubleEscaped(String str, int i) {
        return i >= 2 && str.charAt(i - 2) == '\\';
    }

    static final boolean isEscapedDelimeter(String str, int i) {
        return i != 0 && str.charAt(i - 1) == '\\';
    }

    private static void longArrayAppend(StringBuilder sb, long[] jArr) {
        sb.append(JsonReaderKt.BEGIN_LIST);
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(jArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(JsonReaderKt.END_LIST);
    }

    private static void objectArrayAppend(StringBuilder sb, Object[] objArr, Map<Object[], Object> map) {
        sb.append(JsonReaderKt.BEGIN_LIST);
        if (!map.containsKey(objArr)) {
            map.put(objArr, null);
            int length = objArr.length;
            for (int i = 0; i < length; i++) {
                deeplyAppendParameter(sb, objArr[i], map);
                if (i != length - 1) {
                    sb.append(", ");
                }
            }
            map.remove(objArr);
        } else {
            sb.append("...");
        }
        sb.append(JsonReaderKt.END_LIST);
    }

    private static void safeObjectAppend(StringBuilder sb, Object obj) {
        try {
            sb.append(obj.toString());
        } catch (Throwable th) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SLF4J: Failed toString() invocation on an object of type [");
            outline107.append(obj.getClass().getName());
            outline107.append("]");
            Util.report(outline107.toString(), th);
            sb.append("[FAILED toString()]");
        }
    }

    private static void shortArrayAppend(StringBuilder sb, short[] sArr) {
        sb.append(JsonReaderKt.BEGIN_LIST);
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            sb.append((int) sArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(JsonReaderKt.END_LIST);
    }

    public static Object[] trimmedCopy(Object[] objArr) {
        if (objArr != null && objArr.length != 0) {
            int length = objArr.length - 1;
            Object[] objArr2 = new Object[length];
            if (length > 0) {
                System.arraycopy(objArr, 0, objArr2, 0, length);
            }
            return objArr2;
        }
        throw new IllegalStateException("non-sensical empty or null argument array");
    }

    public static final FormattingTuple format(String str, Object obj, Object obj2) {
        return arrayFormat(str, new Object[]{obj, obj2});
    }

    public static final FormattingTuple arrayFormat(String str, Object[] objArr, Throwable th) {
        int i;
        if (str == null) {
            return new FormattingTuple(null, objArr, th);
        }
        if (objArr == null) {
            return new FormattingTuple(str);
        }
        StringBuilder sb = new StringBuilder(str.length() + 50);
        int i2 = 0;
        int i3 = 0;
        while (i2 < objArr.length) {
            int indexOf = str.indexOf(DELIM_STR, i3);
            if (indexOf == -1) {
                if (i3 == 0) {
                    return new FormattingTuple(str, objArr, th);
                }
                sb.append((CharSequence) str, i3, str.length());
                return new FormattingTuple(sb.toString(), objArr, th);
            }
            if (isEscapedDelimeter(str, indexOf)) {
                if (!isDoubleEscaped(str, indexOf)) {
                    i2--;
                    sb.append((CharSequence) str, i3, indexOf - 1);
                    sb.append('{');
                    i = indexOf + 1;
                    i3 = i;
                    i2++;
                } else {
                    sb.append((CharSequence) str, i3, indexOf - 1);
                    deeplyAppendParameter(sb, objArr[i2], new HashMap());
                }
            } else {
                sb.append((CharSequence) str, i3, indexOf);
                deeplyAppendParameter(sb, objArr[i2], new HashMap());
            }
            i = indexOf + 2;
            i3 = i;
            i2++;
        }
        sb.append((CharSequence) str, i3, str.length());
        return new FormattingTuple(sb.toString(), objArr, th);
    }
}
