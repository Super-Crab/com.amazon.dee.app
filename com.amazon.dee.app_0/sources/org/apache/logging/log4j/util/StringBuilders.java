package org.apache.logging.log4j.util;

import java.util.Map;
import kotlin.text.Typography;
/* loaded from: classes4.dex */
public final class StringBuilders {
    private StringBuilders() {
    }

    public static StringBuilder appendDqValue(StringBuilder sb, Object obj) {
        sb.append('\"');
        sb.append(obj);
        sb.append('\"');
        return sb;
    }

    public static StringBuilder appendKeyDqValue(StringBuilder sb, Map.Entry<String, String> entry) {
        return appendKeyDqValue(sb, entry.getKey(), entry.getValue());
    }

    public static boolean appendSpecificTypes(StringBuilder sb, Object obj) {
        if (obj != null && !(obj instanceof String)) {
            if (obj instanceof StringBuilderFormattable) {
                ((StringBuilderFormattable) obj).formatTo(sb);
                return true;
            } else if (obj instanceof CharSequence) {
                sb.append((CharSequence) obj);
                return true;
            } else if (obj instanceof Integer) {
                sb.append(((Integer) obj).intValue());
                return true;
            } else if (obj instanceof Long) {
                sb.append(((Long) obj).longValue());
                return true;
            } else if (obj instanceof Double) {
                sb.append(((Double) obj).doubleValue());
                return true;
            } else if (obj instanceof Boolean) {
                sb.append(((Boolean) obj).booleanValue());
                return true;
            } else if (obj instanceof Character) {
                sb.append(((Character) obj).charValue());
                return true;
            } else if (obj instanceof Short) {
                sb.append((int) ((Short) obj).shortValue());
                return true;
            } else if (obj instanceof Float) {
                sb.append(((Float) obj).floatValue());
                return true;
            } else if (!(obj instanceof Byte)) {
                return false;
            } else {
                sb.append((int) ((Byte) obj).byteValue());
                return true;
            }
        }
        sb.append((String) obj);
        return true;
    }

    public static void appendValue(StringBuilder sb, Object obj) {
        if (!appendSpecificTypes(sb, obj)) {
            sb.append(obj);
        }
    }

    public static boolean equals(CharSequence charSequence, int i, int i2, CharSequence charSequence2, int i3, int i4) {
        if (i2 == i4) {
            for (int i5 = 0; i5 < i4; i5++) {
                if (charSequence.charAt(i5 + i) != charSequence2.charAt(i5 + i3)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean equalsIgnoreCase(CharSequence charSequence, int i, int i2, CharSequence charSequence2, int i3, int i4) {
        if (i2 == i4) {
            for (int i5 = 0; i5 < i4; i5++) {
                if (Character.toLowerCase(charSequence.charAt(i5 + i)) != Character.toLowerCase(charSequence2.charAt(i5 + i3))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static int escapeAndDecrement(StringBuilder sb, int i, char c) {
        int i2 = i - 1;
        sb.setCharAt(i, c);
        int i3 = i2 - 1;
        sb.setCharAt(i2, '\\');
        return i3;
    }

    public static void escapeJson(StringBuilder sb, int i) {
        int i2 = 0;
        while (i < sb.length()) {
            char charAt = sb.charAt(i);
            if (charAt != '\f' && charAt != '\r' && charAt != '\"' && charAt != '\\') {
                switch (charAt) {
                    case '\b':
                    case '\t':
                    case '\n':
                        break;
                    default:
                        if (Character.isISOControl(charAt)) {
                            i2 += 5;
                        } else {
                            continue;
                        }
                        i++;
                }
            }
            i2++;
            i++;
        }
        sb.setLength(sb.length() + i2);
        int length = sb.length() - 1;
        for (int length2 = sb.length() - 1; length > length2; length2--) {
            char charAt2 = sb.charAt(length2);
            if (charAt2 == '\f') {
                length = escapeAndDecrement(sb, length, 'f');
            } else if (charAt2 == '\r') {
                length = escapeAndDecrement(sb, length, 'r');
            } else if (charAt2 != '\"' && charAt2 != '\\') {
                switch (charAt2) {
                    case '\b':
                        length = escapeAndDecrement(sb, length, 'b');
                        continue;
                    case '\t':
                        length = escapeAndDecrement(sb, length, 't');
                        continue;
                    case '\n':
                        length = escapeAndDecrement(sb, length, 'n');
                        continue;
                    default:
                        if (Character.isISOControl(charAt2)) {
                            int i3 = length - 1;
                            sb.setCharAt(length, Chars.getUpperCaseHex(charAt2 & 15));
                            int i4 = i3 - 1;
                            sb.setCharAt(i3, Chars.getUpperCaseHex((charAt2 & 240) >> 4));
                            int i5 = i4 - 1;
                            sb.setCharAt(i4, '0');
                            int i6 = i5 - 1;
                            sb.setCharAt(i5, '0');
                            int i7 = i6 - 1;
                            sb.setCharAt(i6, 'u');
                            length = i7 - 1;
                            sb.setCharAt(i7, '\\');
                            continue;
                        } else {
                            sb.setCharAt(length, charAt2);
                            length--;
                            break;
                        }
                }
            } else {
                length = escapeAndDecrement(sb, length, charAt2);
            }
        }
    }

    public static void escapeXml(StringBuilder sb, int i) {
        int i2 = 0;
        while (i < sb.length()) {
            char charAt = sb.charAt(i);
            if (charAt != '\"') {
                if (charAt == '<' || charAt == '>') {
                    i2 += 3;
                } else if (charAt == '&') {
                    i2 += 4;
                } else if (charAt != '\'') {
                }
                i++;
            }
            i2 += 5;
            i++;
        }
        sb.setLength(sb.length() + i2);
        int length = sb.length() - 1;
        for (int length2 = sb.length() - 1; length > length2; length2--) {
            char charAt2 = sb.charAt(length2);
            if (charAt2 == '\"') {
                int i3 = length - 1;
                sb.setCharAt(length, ';');
                int i4 = i3 - 1;
                sb.setCharAt(i3, 't');
                int i5 = i4 - 1;
                sb.setCharAt(i4, 'o');
                int i6 = i5 - 1;
                sb.setCharAt(i5, 'u');
                int i7 = i6 - 1;
                sb.setCharAt(i6, 'q');
                length = i7 - 1;
                sb.setCharAt(i7, Typography.amp);
            } else if (charAt2 == '<') {
                int i8 = length - 1;
                sb.setCharAt(length, ';');
                int i9 = i8 - 1;
                sb.setCharAt(i8, 't');
                int i10 = i9 - 1;
                sb.setCharAt(i9, 'l');
                length = i10 - 1;
                sb.setCharAt(i10, Typography.amp);
            } else if (charAt2 == '>') {
                int i11 = length - 1;
                sb.setCharAt(length, ';');
                int i12 = i11 - 1;
                sb.setCharAt(i11, 't');
                int i13 = i12 - 1;
                sb.setCharAt(i12, 'g');
                length = i13 - 1;
                sb.setCharAt(i13, Typography.amp);
            } else if (charAt2 == '&') {
                int i14 = length - 1;
                sb.setCharAt(length, ';');
                int i15 = i14 - 1;
                sb.setCharAt(i14, 'p');
                int i16 = i15 - 1;
                sb.setCharAt(i15, 'm');
                int i17 = i16 - 1;
                sb.setCharAt(i16, 'a');
                sb.setCharAt(i17, Typography.amp);
                length = i17 - 1;
            } else if (charAt2 != '\'') {
                sb.setCharAt(length, charAt2);
                length--;
            } else {
                int i18 = length - 1;
                sb.setCharAt(length, ';');
                int i19 = i18 - 1;
                sb.setCharAt(i18, 's');
                int i20 = i19 - 1;
                sb.setCharAt(i19, 'o');
                int i21 = i20 - 1;
                sb.setCharAt(i20, 'p');
                int i22 = i21 - 1;
                sb.setCharAt(i21, 'a');
                length = i22 - 1;
                sb.setCharAt(i22, Typography.amp);
            }
        }
    }

    public static void trimToMaxSize(StringBuilder sb, int i) {
        if (sb == null || sb.capacity() <= i) {
            return;
        }
        sb.setLength(i);
        sb.trimToSize();
    }

    public static StringBuilder appendKeyDqValue(StringBuilder sb, String str, Object obj) {
        sb.append(str);
        sb.append(Chars.EQ);
        sb.append('\"');
        sb.append(obj);
        sb.append('\"');
        return sb;
    }
}
