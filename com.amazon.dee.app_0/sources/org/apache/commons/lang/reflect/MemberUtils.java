package org.apache.commons.lang.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.SystemUtils;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class MemberUtils {
    private static final int ACCESS_TEST = 7;
    private static final Method IS_SYNTHETIC;
    private static final Class[] ORDERED_PRIMITIVE_TYPES;
    static /* synthetic */ Class class$java$lang$reflect$Member;

    static {
        Class cls;
        Method method;
        if (SystemUtils.isJavaVersionAtLeast(1.5f)) {
            try {
                if (class$java$lang$reflect$Member == null) {
                    cls = class$("java.lang.reflect.Member");
                    class$java$lang$reflect$Member = cls;
                } else {
                    cls = class$java$lang$reflect$Member;
                }
                method = cls.getMethod("isSynthetic", ArrayUtils.EMPTY_CLASS_ARRAY);
            } catch (Exception unused) {
            }
            IS_SYNTHETIC = method;
            ORDERED_PRIMITIVE_TYPES = new Class[]{Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};
        }
        method = null;
        IS_SYNTHETIC = method;
        ORDERED_PRIMITIVE_TYPES = new Class[]{Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};
    }

    MemberUtils() {
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int compareParameterTypes(Class[] clsArr, Class[] clsArr2, Class[] clsArr3) {
        float totalTransformationCost = getTotalTransformationCost(clsArr3, clsArr);
        float totalTransformationCost2 = getTotalTransformationCost(clsArr3, clsArr2);
        if (totalTransformationCost < totalTransformationCost2) {
            return -1;
        }
        return totalTransformationCost2 < totalTransformationCost ? 1 : 0;
    }

    private static float getObjectTransformationCost(Class cls, Class cls2) {
        if (cls2.isPrimitive()) {
            return getPrimitivePromotionCost(cls, cls2);
        }
        float f = 0.0f;
        while (true) {
            if (cls == null || cls2.equals(cls)) {
                break;
            } else if (cls2.isInterface() && ClassUtils.isAssignable(cls, cls2)) {
                f += 0.25f;
                break;
            } else {
                f += 1.0f;
                cls = cls.getSuperclass();
            }
        }
        return cls == null ? f + 1.5f : f;
    }

    private static float getPrimitivePromotionCost(Class cls, Class cls2) {
        float f;
        if (!cls.isPrimitive()) {
            cls = ClassUtils.wrapperToPrimitive(cls);
            f = 0.1f;
        } else {
            f = 0.0f;
        }
        int i = 0;
        while (cls != cls2) {
            Class[] clsArr = ORDERED_PRIMITIVE_TYPES;
            if (i >= clsArr.length) {
                break;
            }
            if (cls == clsArr[i]) {
                f += 0.1f;
                if (i < clsArr.length - 1) {
                    cls = clsArr[i + 1];
                }
            }
            i++;
        }
        return f;
    }

    private static float getTotalTransformationCost(Class[] clsArr, Class[] clsArr2) {
        float f = 0.0f;
        for (int i = 0; i < clsArr.length; i++) {
            f += getObjectTransformationCost(clsArr[i], clsArr2[i]);
        }
        return f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAccessible(Member member) {
        return member != null && Modifier.isPublic(member.getModifiers()) && !isSynthetic(member);
    }

    static boolean isPackageAccess(int i) {
        return (i & 7) == 0;
    }

    static boolean isSynthetic(Member member) {
        Method method = IS_SYNTHETIC;
        if (method != null) {
            try {
                return ((Boolean) method.invoke(member, null)).booleanValue();
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setAccessibleWorkaround(AccessibleObject accessibleObject) {
        if (accessibleObject == null || accessibleObject.isAccessible()) {
            return;
        }
        Member member = (Member) accessibleObject;
        if (!Modifier.isPublic(member.getModifiers()) || !isPackageAccess(member.getDeclaringClass().getModifiers())) {
            return;
        }
        try {
            accessibleObject.setAccessible(true);
        } catch (SecurityException unused) {
        }
    }
}
