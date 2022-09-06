package org.checkerframework.checker.formatter.qual;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.checkerframework.dataflow.qual.Pure;
/* loaded from: classes5.dex */
public enum ConversionCategory {
    GENERAL(null, "bBhHsS"),
    CHAR(new Class[]{Character.class, Byte.class, Short.class, Integer.class}, "cC"),
    INT(new Class[]{Byte.class, Short.class, Integer.class, Long.class, BigInteger.class}, "doxX"),
    FLOAT(new Class[]{Float.class, Double.class, BigDecimal.class}, "eEfgGaA"),
    TIME(new Class[]{Long.class, Calendar.class, Date.class}, "tT"),
    CHAR_AND_INT(new Class[]{Byte.class, Short.class, Integer.class}, null),
    INT_AND_TIME(new Class[]{Long.class}, null),
    NULL(new Class[0], null),
    UNUSED(null, null);
    
    public final String chars;
    public final Class<? extends Object>[] types;

    ConversionCategory(Class[] clsArr, String str) {
        this.types = clsArr;
        this.chars = str;
    }

    private static <E> Set<E> arrayToSet(E[] eArr) {
        return new HashSet(Arrays.asList(eArr));
    }

    private String className(Class<?> cls) {
        return cls == Boolean.class ? "boolean" : cls == Character.class ? "char" : cls == Byte.class ? "byte" : cls == Short.class ? "short" : cls == Integer.class ? "int" : cls == Long.class ? "long" : cls == Float.class ? "float" : cls == Double.class ? "double" : cls.getSimpleName();
    }

    public static ConversionCategory fromConversionChar(char c) {
        ConversionCategory[] conversionCategoryArr;
        for (ConversionCategory conversionCategory : new ConversionCategory[]{GENERAL, CHAR, INT, FLOAT, TIME}) {
            if (conversionCategory.chars.contains(String.valueOf(c))) {
                return conversionCategory;
            }
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline47("Bad conversion character ", c));
    }

    public static ConversionCategory intersect(ConversionCategory conversionCategory, ConversionCategory conversionCategory2) {
        ConversionCategory[] conversionCategoryArr;
        ConversionCategory conversionCategory3 = UNUSED;
        if (conversionCategory == conversionCategory3) {
            return conversionCategory2;
        }
        if (conversionCategory2 == conversionCategory3) {
            return conversionCategory;
        }
        ConversionCategory conversionCategory4 = GENERAL;
        if (conversionCategory == conversionCategory4) {
            return conversionCategory2;
        }
        if (conversionCategory2 == conversionCategory4) {
            return conversionCategory;
        }
        Set arrayToSet = arrayToSet(conversionCategory.types);
        arrayToSet.retainAll(arrayToSet(conversionCategory2.types));
        for (ConversionCategory conversionCategory5 : new ConversionCategory[]{CHAR, INT, FLOAT, TIME, CHAR_AND_INT, INT_AND_TIME, NULL}) {
            if (arrayToSet(conversionCategory5.types).equals(arrayToSet)) {
                return conversionCategory5;
            }
        }
        throw new RuntimeException();
    }

    public static boolean isSubsetOf(ConversionCategory conversionCategory, ConversionCategory conversionCategory2) {
        return intersect(conversionCategory, conversionCategory2) == conversionCategory;
    }

    public static ConversionCategory union(ConversionCategory conversionCategory, ConversionCategory conversionCategory2) {
        ConversionCategory[] conversionCategoryArr;
        ConversionCategory conversionCategory3 = UNUSED;
        if (conversionCategory != conversionCategory3 && conversionCategory2 != conversionCategory3) {
            ConversionCategory conversionCategory4 = GENERAL;
            if (conversionCategory != conversionCategory4 && conversionCategory2 != conversionCategory4) {
                if ((conversionCategory == CHAR_AND_INT && conversionCategory2 == INT_AND_TIME) || (conversionCategory == INT_AND_TIME && conversionCategory2 == CHAR_AND_INT)) {
                    return INT;
                }
                Set arrayToSet = arrayToSet(conversionCategory.types);
                arrayToSet.addAll(arrayToSet(conversionCategory2.types));
                for (ConversionCategory conversionCategory5 : new ConversionCategory[]{NULL, CHAR_AND_INT, INT_AND_TIME, CHAR, INT, FLOAT, TIME}) {
                    if (arrayToSet(conversionCategory5.types).equals(arrayToSet)) {
                        return conversionCategory5;
                    }
                }
                return GENERAL;
            }
            return GENERAL;
        }
        return UNUSED;
    }

    @Override // java.lang.Enum
    @Pure
    public String toString() {
        StringBuilder sb = new StringBuilder(name());
        sb.append(" conversion category (one of: ");
        Class<? extends Object>[] clsArr = this.types;
        int length = clsArr.length;
        boolean z = true;
        int i = 0;
        while (i < length) {
            Class<? extends Object> cls = clsArr[i];
            if (!z) {
                sb.append(", ");
            }
            sb.append(className(cls));
            i++;
            z = false;
        }
        sb.append(")");
        return sb.toString();
    }
}
