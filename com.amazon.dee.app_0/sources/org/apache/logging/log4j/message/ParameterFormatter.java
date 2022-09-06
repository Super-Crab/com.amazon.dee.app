package org.apache.logging.log4j.message;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.StringBuilders;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class ParameterFormatter {
    private static final char DELIM_START = '{';
    private static final char DELIM_STOP = '}';
    static final String ERROR_MSG_SEPARATOR = ":";
    static final String ERROR_PREFIX = "[!!!";
    static final String ERROR_SEPARATOR = "=>";
    static final String ERROR_SUFFIX = "!!!]";
    private static final char ESCAPE_CHAR = '\\';
    static final String RECURSION_PREFIX = "[...";
    static final String RECURSION_SUFFIX = "...]";
    private static ThreadLocal<SimpleDateFormat> threadLocalSimpleDateFormat = new ThreadLocal<>();

    private ParameterFormatter() {
    }

    private static void appendArray(Object obj, StringBuilder sb, Set<String> set, Class<?> cls) {
        Object[] objArr;
        if (cls == byte[].class) {
            sb.append(Arrays.toString((byte[]) obj));
        } else if (cls == short[].class) {
            sb.append(Arrays.toString((short[]) obj));
        } else if (cls == int[].class) {
            sb.append(Arrays.toString((int[]) obj));
        } else if (cls == long[].class) {
            sb.append(Arrays.toString((long[]) obj));
        } else if (cls == float[].class) {
            sb.append(Arrays.toString((float[]) obj));
        } else if (cls == double[].class) {
            sb.append(Arrays.toString((double[]) obj));
        } else if (cls == boolean[].class) {
            sb.append(Arrays.toString((boolean[]) obj));
        } else if (cls == char[].class) {
            sb.append(Arrays.toString((char[]) obj));
        } else {
            if (set == null) {
                set = new HashSet<>();
            }
            String identityToString = identityToString(obj);
            if (set.contains(identityToString)) {
                GeneratedOutlineSupport1.outline180(sb, "[...", identityToString, "...]");
                return;
            }
            set.add(identityToString);
            sb.append(JsonReaderKt.BEGIN_LIST);
            boolean z = true;
            for (Object obj2 : (Object[]) obj) {
                if (z) {
                    z = false;
                } else {
                    sb.append(", ");
                }
                recursiveDeepToString(obj2, sb, new HashSet(set));
            }
            sb.append(JsonReaderKt.END_LIST);
        }
    }

    private static void appendCollection(Object obj, StringBuilder sb, Set<String> set) {
        if (set == null) {
            set = new HashSet<>();
        }
        String identityToString = identityToString(obj);
        if (set.contains(identityToString)) {
            GeneratedOutlineSupport1.outline180(sb, "[...", identityToString, "...]");
            return;
        }
        set.add(identityToString);
        sb.append(JsonReaderKt.BEGIN_LIST);
        boolean z = true;
        for (Object obj2 : (Collection) obj) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            recursiveDeepToString(obj2, sb, new HashSet(set));
        }
        sb.append(JsonReaderKt.END_LIST);
    }

    private static boolean appendDate(Object obj, StringBuilder sb) {
        if (!(obj instanceof Date)) {
            return false;
        }
        sb.append(getSimpleDateFormat().format((Date) obj));
        return true;
    }

    private static void appendMap(Object obj, StringBuilder sb, Set<String> set) {
        if (set == null) {
            set = new HashSet<>();
        }
        String identityToString = identityToString(obj);
        if (set.contains(identityToString)) {
            GeneratedOutlineSupport1.outline180(sb, "[...", identityToString, "...]");
            return;
        }
        set.add(identityToString);
        sb.append('{');
        boolean z = true;
        for (Map.Entry entry : ((Map) obj).entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            Object key = entry.getKey();
            Object value = entry.getValue();
            recursiveDeepToString(key, sb, new HashSet(set));
            sb.append(Chars.EQ);
            recursiveDeepToString(value, sb, new HashSet(set));
        }
        sb.append('}');
    }

    private static void appendPotentiallyRecursiveValue(Object obj, StringBuilder sb, Set<String> set) {
        Class<?> cls = obj.getClass();
        if (cls.isArray()) {
            appendArray(obj, sb, set, cls);
        } else if (obj instanceof Map) {
            appendMap(obj, sb, set);
        } else if (!(obj instanceof Collection)) {
        } else {
            appendCollection(obj, sb, set);
        }
    }

    private static boolean appendSpecialTypes(Object obj, StringBuilder sb) {
        return StringBuilders.appendSpecificTypes(sb, obj) || appendDate(obj, sb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int countArgumentPlaceholders(String str) {
        if (str == null) {
            return 0;
        }
        int length = str.length();
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (i < length - 1) {
            char charAt = str.charAt(i);
            if (charAt == '\\') {
                z = !z;
            } else {
                if (charAt == '{' && !z) {
                    int i3 = i + 1;
                    if (str.charAt(i3) == '}') {
                        i2++;
                        i = i3;
                    }
                }
                z = false;
            }
            i++;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int countArgumentPlaceholders2(String str, int[] iArr) {
        if (str == null) {
            return 0;
        }
        int length = str.length();
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (i < length - 1) {
            char charAt = str.charAt(i);
            if (charAt == '\\') {
                z = !z;
                iArr[0] = -1;
                i2++;
            } else {
                if (charAt == '{' && !z) {
                    int i3 = i + 1;
                    if (str.charAt(i3) == '}') {
                        iArr[i2] = i;
                        i2++;
                        i = i3;
                    }
                }
                z = false;
            }
            i++;
        }
        return i2;
    }

    static int countArgumentPlaceholders3(char[] cArr, int i, int[] iArr) {
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        while (i2 < i - 1) {
            char c = cArr[i2];
            if (c == '\\') {
                z = !z;
            } else {
                if (c == '{' && !z) {
                    int i4 = i2 + 1;
                    if (cArr[i4] == '}') {
                        iArr[i3] = i2;
                        i3++;
                        i2 = i4;
                    }
                }
                z = false;
            }
            i2++;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String deepToString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Integer) {
            return Integer.toString(((Integer) obj).intValue());
        }
        if (obj instanceof Long) {
            return Long.toString(((Long) obj).longValue());
        }
        if (obj instanceof Double) {
            return Double.toString(((Double) obj).doubleValue());
        }
        if (obj instanceof Boolean) {
            return Boolean.toString(((Boolean) obj).booleanValue());
        }
        if (obj instanceof Character) {
            return Character.toString(((Character) obj).charValue());
        }
        if (obj instanceof Short) {
            return Short.toString(((Short) obj).shortValue());
        }
        if (obj instanceof Float) {
            return Float.toString(((Float) obj).floatValue());
        }
        if (obj instanceof Byte) {
            return Byte.toString(((Byte) obj).byteValue());
        }
        StringBuilder sb = new StringBuilder();
        recursiveDeepToString(obj, sb, null);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String format(String str, Object[] objArr) {
        StringBuilder sb = new StringBuilder();
        formatMessage(sb, str, objArr, objArr == null ? 0 : objArr.length);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void formatMessage(StringBuilder sb, String str, Object[] objArr, int i) {
        if (str != null && objArr != null && i != 0) {
            int length = str.length();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i2 < length - 1) {
                char charAt = str.charAt(i2);
                if (charAt == '\\') {
                    i3++;
                } else {
                    if (isDelimPair(charAt, str, i2)) {
                        i2++;
                        writeEscapedEscapeChars(i3, sb);
                        if (isOdd(i3)) {
                            writeDelimPair(sb);
                        } else {
                            writeArgOrDelimPair(objArr, i, i4, sb);
                            i4++;
                        }
                    } else {
                        handleLiteralChar(sb, i3, charAt);
                    }
                    i3 = 0;
                }
                i2++;
            }
            handleRemainingCharIfAny(str, length, sb, i3, i2);
            return;
        }
        sb.append(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void formatMessage2(StringBuilder sb, String str, Object[] objArr, int i, int[] iArr) {
        if (str != null && objArr != null && i != 0) {
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                sb.append((CharSequence) str, i2, iArr[i3]);
                i2 = iArr[i3] + 2;
                recursiveDeepToString(objArr[i3], sb, null);
            }
            sb.append((CharSequence) str, i2, str.length());
            return;
        }
        sb.append(str);
    }

    static void formatMessage3(StringBuilder sb, char[] cArr, int i, Object[] objArr, int i2, int[] iArr) {
        if (cArr == null) {
            return;
        }
        if (objArr != null && i2 != 0) {
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                sb.append(cArr, i3, iArr[i4]);
                i3 = iArr[i4] + 2;
                recursiveDeepToString(objArr[i4], sb, null);
            }
            sb.append(cArr, i3, i);
            return;
        }
        sb.append(cArr);
    }

    private static SimpleDateFormat getSimpleDateFormat() {
        SimpleDateFormat simpleDateFormat = threadLocalSimpleDateFormat.get();
        if (simpleDateFormat == null) {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            threadLocalSimpleDateFormat.set(simpleDateFormat2);
            return simpleDateFormat2;
        }
        return simpleDateFormat;
    }

    private static void handleErrorInObjectToString(Object obj, StringBuilder sb, Throwable th) {
        sb.append("[!!!");
        sb.append(identityToString(obj));
        sb.append("=>");
        String message = th.getMessage();
        String name = th.getClass().getName();
        sb.append(name);
        if (!name.equals(message)) {
            sb.append(":");
            sb.append(message);
        }
        sb.append("!!!]");
    }

    private static void handleLastChar(StringBuilder sb, int i, char c) {
        if (c == '\\') {
            writeUnescapedEscapeChars(i + 1, sb);
        } else {
            handleLiteralChar(sb, i, c);
        }
    }

    private static void handleLiteralChar(StringBuilder sb, int i, char c) {
        writeUnescapedEscapeChars(i, sb);
        sb.append(c);
    }

    private static void handleRemainingCharIfAny(String str, int i, StringBuilder sb, int i2, int i3) {
        if (i3 == i - 1) {
            handleLastChar(sb, i2, str.charAt(i3));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String identityToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
    }

    private static boolean isDelimPair(char c, String str, int i) {
        return c == '{' && str.charAt(i + 1) == '}';
    }

    private static boolean isMaybeRecursive(Object obj) {
        return obj.getClass().isArray() || (obj instanceof Map) || (obj instanceof Collection);
    }

    private static boolean isOdd(int i) {
        return (i & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void recursiveDeepToString(Object obj, StringBuilder sb, Set<String> set) {
        if (appendSpecialTypes(obj, sb)) {
            return;
        }
        if (isMaybeRecursive(obj)) {
            appendPotentiallyRecursiveValue(obj, sb, set);
        } else {
            tryObjectToString(obj, sb);
        }
    }

    private static void tryObjectToString(Object obj, StringBuilder sb) {
        try {
            sb.append(obj.toString());
        } catch (Throwable th) {
            handleErrorInObjectToString(obj, sb, th);
        }
    }

    private static void writeArgOrDelimPair(Object[] objArr, int i, int i2, StringBuilder sb) {
        if (i2 < i) {
            recursiveDeepToString(objArr[i2], sb, null);
        } else {
            writeDelimPair(sb);
        }
    }

    private static void writeDelimPair(StringBuilder sb) {
        sb.append('{');
        sb.append('}');
    }

    private static void writeEscapedEscapeChars(int i, StringBuilder sb) {
        writeUnescapedEscapeChars(i >> 1, sb);
    }

    private static void writeUnescapedEscapeChars(int i, StringBuilder sb) {
        while (i > 0) {
            sb.append('\\');
            i--;
        }
    }
}
