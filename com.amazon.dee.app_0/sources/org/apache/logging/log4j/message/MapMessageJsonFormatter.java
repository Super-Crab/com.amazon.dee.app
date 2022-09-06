package org.apache.logging.log4j.message;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.apache.logging.log4j.util.IndexedStringMap;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.apache.logging.log4j.util.StringBuilders;
/* loaded from: classes4.dex */
enum MapMessageJsonFormatter {
    ;
    
    private static final char COLON = ':';
    private static final char COMMA = ',';
    private static final char DQUOTE = '\"';
    private static final char LBRACE = '[';
    private static final char LCURLY = '{';
    public static final int MAX_DEPTH = readMaxDepth();
    private static final char RBRACE = ']';
    private static final char RCURLY = '}';

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void format(StringBuilder sb, Object obj) {
        format(sb, obj, 0);
    }

    private static void formatBoolean(StringBuilder sb, boolean z) {
        sb.append(z);
    }

    private static void formatBooleanArray(StringBuilder sb, boolean[] zArr) {
        sb.append('[');
        for (int i = 0; i < zArr.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(zArr[i]);
        }
        sb.append(']');
    }

    private static void formatByteArray(StringBuilder sb, byte[] bArr) {
        sb.append('[');
        for (int i = 0; i < bArr.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append((int) bArr[i]);
        }
        sb.append(']');
    }

    private static void formatCharArray(StringBuilder sb, char[] cArr) {
        sb.append('[');
        for (int i = 0; i < cArr.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            char c = cArr[i];
            sb.append('\"');
            int length = sb.length();
            sb.append(c);
            StringBuilders.escapeJson(sb, length);
            sb.append('\"');
        }
        sb.append(']');
    }

    private static void formatCollection(final StringBuilder sb, Collection<Object> collection, int i) {
        sb.append('[');
        final int i2 = i + 1;
        final boolean[] zArr = {true};
        collection.forEach(new Consumer() { // from class: org.apache.logging.log4j.message.-$$Lambda$MapMessageJsonFormatter$IA9JPaq3to0x0x2Rnd4rd1W1DyU
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MapMessageJsonFormatter.lambda$formatCollection$1(zArr, sb, i2, obj);
            }
        });
        sb.append(']');
    }

    private static void formatDoubleArray(StringBuilder sb, double[] dArr) {
        sb.append('[');
        for (int i = 0; i < dArr.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(dArr[i]);
        }
        sb.append(']');
    }

    private static void formatFloatArray(StringBuilder sb, float[] fArr) {
        sb.append('[');
        for (int i = 0; i < fArr.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(fArr[i]);
        }
        sb.append(']');
    }

    private static void formatFormattable(StringBuilder sb, StringBuilderFormattable stringBuilderFormattable) {
        sb.append('\"');
        int length = sb.length();
        stringBuilderFormattable.formatTo(sb);
        StringBuilders.escapeJson(sb, length);
        sb.append('\"');
    }

    private static void formatIndexedStringMap(StringBuilder sb, IndexedStringMap indexedStringMap, int i) {
        sb.append('{');
        int i2 = i + 1;
        for (int i3 = 0; i3 < indexedStringMap.size(); i3++) {
            String keyAt = indexedStringMap.getKeyAt(i3);
            Object valueAt = indexedStringMap.getValueAt(i3);
            if (i3 > 0) {
                sb.append(',');
            }
            sb.append('\"');
            int length = sb.length();
            sb.append(keyAt);
            StringBuilders.escapeJson(sb, length);
            sb.append('\"');
            sb.append(':');
            format(sb, valueAt, i2);
        }
        sb.append('}');
    }

    private static void formatIntArray(StringBuilder sb, int[] iArr) {
        sb.append('[');
        for (int i = 0; i < iArr.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(iArr[i]);
        }
        sb.append(']');
    }

    private static void formatList(StringBuilder sb, List<Object> list, int i) {
        sb.append('[');
        int i2 = i + 1;
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (i3 > 0) {
                sb.append(',');
            }
            format(sb, list.get(i3), i2);
        }
        sb.append(']');
    }

    private static void formatLongArray(StringBuilder sb, long[] jArr) {
        sb.append('[');
        for (int i = 0; i < jArr.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(jArr[i]);
        }
        sb.append(']');
    }

    private static void formatMap(final StringBuilder sb, Map<Object, Object> map, int i) {
        sb.append('{');
        final int i2 = i + 1;
        final boolean[] zArr = {true};
        map.forEach(new BiConsumer() { // from class: org.apache.logging.log4j.message.-$$Lambda$MapMessageJsonFormatter$Sgl7ZO0ujwSAXdBIk8ANYMaB1jY
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                MapMessageJsonFormatter.lambda$formatMap$0(zArr, sb, i2, obj, obj2);
            }
        });
        sb.append('}');
    }

    private static void formatNumber(StringBuilder sb, Number number) {
        if (number instanceof BigDecimal) {
            sb.append(((BigDecimal) number).toString());
        } else if (number instanceof Double) {
            sb.append(((Double) number).doubleValue());
        } else if (number instanceof Float) {
            sb.append(((Float) number).floatValue());
        } else if (!(number instanceof Byte) && !(number instanceof Short) && !(number instanceof Integer) && !(number instanceof Long)) {
            long longValue = number.longValue();
            double doubleValue = number.doubleValue();
            if (Double.compare(longValue, doubleValue) == 0) {
                sb.append(longValue);
            } else {
                sb.append(doubleValue);
            }
        } else {
            sb.append(number.longValue());
        }
    }

    private static void formatObjectArray(StringBuilder sb, Object[] objArr, int i) {
        sb.append('[');
        int i2 = i + 1;
        for (int i3 = 0; i3 < objArr.length; i3++) {
            if (i3 > 0) {
                sb.append(',');
            }
            format(sb, objArr[i3], i2);
        }
        sb.append(']');
    }

    private static void formatShortArray(StringBuilder sb, short[] sArr) {
        sb.append('[');
        for (int i = 0; i < sArr.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append((int) sArr[i]);
        }
        sb.append(']');
    }

    private static void formatString(StringBuilder sb, Object obj) {
        sb.append('\"');
        int length = sb.length();
        sb.append(String.valueOf(obj));
        StringBuilders.escapeJson(sb, length);
        sb.append('\"');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$formatCollection$1(boolean[] zArr, StringBuilder sb, int i, Object obj) {
        if (zArr[0]) {
            zArr[0] = false;
        } else {
            sb.append(',');
        }
        format(sb, obj, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$formatMap$0(boolean[] zArr, StringBuilder sb, int i, Object obj, Object obj2) {
        if (obj != null) {
            if (zArr[0]) {
                zArr[0] = false;
            } else {
                sb.append(',');
            }
            sb.append('\"');
            String valueOf = String.valueOf(obj);
            int length = sb.length();
            sb.append(valueOf);
            StringBuilders.escapeJson(sb, length);
            sb.append('\"');
            sb.append(':');
            format(sb, obj2, i);
            return;
        }
        throw new IllegalArgumentException("null keys are not allowed");
    }

    private static int readMaxDepth() {
        int integerProperty = PropertiesUtil.getProperties().getIntegerProperty("log4j2.mapMessage.jsonFormatter.maxDepth", 8);
        if (integerProperty >= 0) {
            return integerProperty;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("was expecting a positive maxDepth, found: ", integerProperty));
    }

    private static void format(StringBuilder sb, Object obj, int i) {
        if (i < MAX_DEPTH) {
            if (obj == null) {
                sb.append("null");
                return;
            } else if (obj instanceof IndexedStringMap) {
                formatIndexedStringMap(sb, (IndexedStringMap) obj, i);
                return;
            } else if (obj instanceof Map) {
                formatMap(sb, (Map) obj, i);
                return;
            } else if (obj instanceof List) {
                formatList(sb, (List) obj, i);
                return;
            } else if (obj instanceof Collection) {
                formatCollection(sb, (Collection) obj, i);
                return;
            } else if (obj instanceof Number) {
                formatNumber(sb, (Number) obj);
                return;
            } else if (obj instanceof Boolean) {
                formatBoolean(sb, ((Boolean) obj).booleanValue());
                return;
            } else if (obj instanceof StringBuilderFormattable) {
                formatFormattable(sb, (StringBuilderFormattable) obj);
                return;
            } else if (obj instanceof char[]) {
                formatCharArray(sb, (char[]) obj);
                return;
            } else if (obj instanceof boolean[]) {
                formatBooleanArray(sb, (boolean[]) obj);
                return;
            } else if (obj instanceof byte[]) {
                formatByteArray(sb, (byte[]) obj);
                return;
            } else if (obj instanceof short[]) {
                formatShortArray(sb, (short[]) obj);
                return;
            } else if (obj instanceof int[]) {
                formatIntArray(sb, (int[]) obj);
                return;
            } else if (obj instanceof long[]) {
                formatLongArray(sb, (long[]) obj);
                return;
            } else if (obj instanceof float[]) {
                formatFloatArray(sb, (float[]) obj);
                return;
            } else if (obj instanceof double[]) {
                formatDoubleArray(sb, (double[]) obj);
                return;
            } else if (obj instanceof Object[]) {
                formatObjectArray(sb, (Object[]) obj, i);
                return;
            } else {
                formatString(sb, obj);
                return;
            }
        }
        throw new IllegalArgumentException("maxDepth has been exceeded");
    }
}
