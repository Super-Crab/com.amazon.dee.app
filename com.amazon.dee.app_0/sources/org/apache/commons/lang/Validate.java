package org.apache.commons.lang;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes4.dex */
public class Validate {
    public static void allElementsOfType(Collection collection, Class cls, String str) {
        notNull(collection);
        notNull(cls);
        for (Object obj : collection) {
            if (!cls.isInstance(obj)) {
                throw new IllegalArgumentException(str);
            }
        }
    }

    public static void isTrue(boolean z, String str, Object obj) {
        if (z) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(obj);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static void noNullElements(Object[] objArr, String str) {
        notNull(objArr);
        for (Object obj : objArr) {
            if (obj == null) {
                throw new IllegalArgumentException(str);
            }
        }
    }

    public static void notEmpty(Object[] objArr, String str) {
        if (objArr == null || objArr.length == 0) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void notNull(Object obj) {
        notNull(obj, "The validated object is null");
    }

    public static void isTrue(boolean z, String str, long j) {
        if (z) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(j);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static void notNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException(str);
    }

    public static void isTrue(boolean z, String str, double d) {
        if (z) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(d);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public static void notEmpty(Object[] objArr) {
        notEmpty(objArr, "The validated array is empty");
    }

    public static void isTrue(boolean z, String str) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException(str);
    }

    public static void notEmpty(Collection collection, String str) {
        if (collection == null || collection.size() == 0) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void isTrue(boolean z) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException("The validated expression is false");
    }

    public static void noNullElements(Object[] objArr) {
        notNull(objArr);
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] == null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("The validated array contains null element at index: ");
                stringBuffer.append(i);
                throw new IllegalArgumentException(stringBuffer.toString());
            }
        }
    }

    public static void allElementsOfType(Collection collection, Class cls) {
        notNull(collection);
        notNull(cls);
        int i = 0;
        for (Object obj : collection) {
            if (!cls.isInstance(obj)) {
                StringBuffer outline103 = GeneratedOutlineSupport1.outline103("The validated collection contains an element not of type ");
                outline103.append(cls.getName());
                outline103.append(" at index: ");
                outline103.append(i);
                throw new IllegalArgumentException(outline103.toString());
            }
            i++;
        }
    }

    public static void notEmpty(Collection collection) {
        notEmpty(collection, "The validated collection is empty");
    }

    public static void notEmpty(Map map, String str) {
        if (map == null || map.size() == 0) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void noNullElements(Collection collection, String str) {
        notNull(collection);
        for (Object obj : collection) {
            if (obj == null) {
                throw new IllegalArgumentException(str);
            }
        }
    }

    public static void notEmpty(Map map) {
        notEmpty(map, "The validated map is empty");
    }

    public static void notEmpty(String str, String str2) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException(str2);
        }
    }

    public static void notEmpty(String str) {
        notEmpty(str, "The validated string is empty");
    }

    public static void noNullElements(Collection collection) {
        notNull(collection);
        int i = 0;
        for (Object obj : collection) {
            if (obj == null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("The validated collection contains null element at index: ");
                stringBuffer.append(i);
                throw new IllegalArgumentException(stringBuffer.toString());
            }
            i++;
        }
    }
}
