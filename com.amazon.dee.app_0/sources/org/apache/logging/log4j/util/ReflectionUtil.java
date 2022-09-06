package org.apache.logging.log4j.util;

import java.util.Stack;
@Deprecated
/* loaded from: classes4.dex */
public final class ReflectionUtil {
    private ReflectionUtil() {
    }

    public static Class<?> getCallerClass(int i) {
        return StackLocatorUtil.getCallerClass(i + 1);
    }

    public static Stack<Class<?>> getCurrentStackTrace() {
        return StackLocatorUtil.getCurrentStackTrace();
    }

    public static boolean supportsFastReflection() {
        return false;
    }

    public static Class<?> getCallerClass(String str) {
        return StackLocatorUtil.getCallerClass(str);
    }

    public static Class<?> getCallerClass(String str, String str2) {
        return StackLocatorUtil.getCallerClass(str, str2);
    }

    public static Class<?> getCallerClass(Class<?> cls) {
        return StackLocatorUtil.getCallerClass(cls);
    }
}
