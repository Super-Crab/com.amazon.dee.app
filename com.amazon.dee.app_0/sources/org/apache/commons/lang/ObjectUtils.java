package org.apache.commons.lang;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.lang.exception.CloneFailedException;
import org.apache.commons.lang.reflect.MethodUtils;
/* loaded from: classes4.dex */
public class ObjectUtils {
    public static final Null NULL = new Null();

    /* loaded from: classes4.dex */
    public static class Null implements Serializable {
        private static final long serialVersionUID = 7092611880189329093L;

        Null() {
        }

        private Object readResolve() {
            return ObjectUtils.NULL;
        }
    }

    public static StringBuffer appendIdentityToString(StringBuffer stringBuffer, Object obj) {
        if (obj == null) {
            return null;
        }
        if (stringBuffer == null) {
            stringBuffer = new StringBuffer();
        }
        stringBuffer.append(obj.getClass().getName());
        stringBuffer.append('@');
        stringBuffer.append(Integer.toHexString(System.identityHashCode(obj)));
        return stringBuffer;
    }

    public static Object clone(Object obj) {
        if (obj instanceof Cloneable) {
            if (obj.getClass().isArray()) {
                Class<?> componentType = obj.getClass().getComponentType();
                if (!componentType.isPrimitive()) {
                    return ((Object[]) obj).clone();
                }
                int length = Array.getLength(obj);
                Object newInstance = Array.newInstance(componentType, length);
                while (true) {
                    int i = length - 1;
                    if (length <= 0) {
                        return newInstance;
                    }
                    Array.set(newInstance, i, Array.get(obj, i));
                    length = i;
                }
            } else {
                try {
                    return MethodUtils.invokeMethod(obj, "clone", (Object[]) null);
                } catch (IllegalAccessException e) {
                    StringBuffer outline103 = GeneratedOutlineSupport1.outline103("Cannot clone Cloneable type ");
                    outline103.append(obj.getClass().getName());
                    throw new CloneFailedException(outline103.toString(), e);
                } catch (NoSuchMethodException e2) {
                    StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("Cloneable type ");
                    outline1032.append(obj.getClass().getName());
                    outline1032.append(" has no clone method");
                    throw new CloneFailedException(outline1032.toString(), e2);
                } catch (InvocationTargetException e3) {
                    StringBuffer outline1033 = GeneratedOutlineSupport1.outline103("Exception cloning Cloneable type ");
                    outline1033.append(obj.getClass().getName());
                    throw new CloneFailedException(outline1033.toString(), e3.getTargetException());
                }
            }
        } else {
            return null;
        }
    }

    public static Object cloneIfPossible(Object obj) {
        Object clone = clone(obj);
        return clone == null ? obj : clone;
    }

    public static int compare(Comparable comparable, Comparable comparable2) {
        return compare(comparable, comparable2, false);
    }

    public static Object defaultIfNull(Object obj, Object obj2) {
        return obj != null ? obj : obj2;
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null && obj2 != null) {
            return obj.equals(obj2);
        }
        return false;
    }

    public static int hashCode(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public static String identityToString(Object obj) {
        if (obj == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        identityToString(stringBuffer, obj);
        return stringBuffer.toString();
    }

    public static Object max(Comparable comparable, Comparable comparable2) {
        return compare(comparable, comparable2, false) >= 0 ? comparable : comparable2;
    }

    public static Object min(Comparable comparable, Comparable comparable2) {
        return compare(comparable, comparable2, true) <= 0 ? comparable : comparable2;
    }

    public static boolean notEqual(Object obj, Object obj2) {
        return !equals(obj, obj2);
    }

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static int compare(Comparable comparable, Comparable comparable2, boolean z) {
        if (comparable == comparable2) {
            return 0;
        }
        if (comparable == null) {
            return z ? 1 : -1;
        } else if (comparable2 != null) {
            return comparable.compareTo(comparable2);
        } else {
            return z ? -1 : 1;
        }
    }

    public static String toString(Object obj, String str) {
        return obj == null ? str : obj.toString();
    }

    public static void identityToString(StringBuffer stringBuffer, Object obj) {
        if (obj != null) {
            stringBuffer.append(obj.getClass().getName());
            stringBuffer.append('@');
            stringBuffer.append(Integer.toHexString(System.identityHashCode(obj)));
            return;
        }
        throw new NullPointerException("Cannot get the toString of a null identity");
    }
}
