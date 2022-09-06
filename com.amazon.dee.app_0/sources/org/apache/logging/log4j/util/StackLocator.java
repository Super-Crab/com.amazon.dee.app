package org.apache.logging.log4j.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Method;
import java.util.Stack;
/* loaded from: classes4.dex */
public final class StackLocator {
    private static final Method GET_CALLER_CLASS;
    private static final StackLocator INSTANCE;
    static final int JDK_7u25_OFFSET;

    static {
        int i = -1;
        Method method = null;
        try {
            Class<?> loadClass = LoaderUtil.loadClass("sun.reflect.Reflection");
            Method declaredMethod = loadClass.getDeclaredMethod("getCallerClass", Integer.TYPE);
            Object invoke = declaredMethod.invoke(null, 0);
            declaredMethod.invoke(null, 0);
            if (invoke != null && invoke == loadClass) {
                if (declaredMethod.invoke(null, 1) == loadClass) {
                    System.out.println("WARNING: Java 1.7.0_25 is in use which has a broken implementation of Reflection.getCallerClass().  Please consider upgrading to Java 1.7.0_40 or later.");
                    method = declaredMethod;
                    i = 1;
                } else {
                    method = declaredMethod;
                    i = 0;
                }
            }
        } catch (Exception | LinkageError unused) {
            System.out.println("WARNING: sun.reflect.Reflection.getCallerClass is not supported. This will impact performance.");
        }
        GET_CALLER_CLASS = method;
        JDK_7u25_OFFSET = i;
        INSTANCE = new StackLocator();
    }

    private StackLocator() {
    }

    public static StackLocator getInstance() {
        return INSTANCE;
    }

    private boolean isValid(StackTraceElement stackTraceElement) {
        if (stackTraceElement.isNativeMethod()) {
            return false;
        }
        String className = stackTraceElement.getClassName();
        if (className.startsWith("sun.reflect.")) {
            return false;
        }
        String methodName = stackTraceElement.getMethodName();
        if ((className.startsWith("java.lang.reflect.") && (methodName.equals("invoke") || methodName.equals("newInstance"))) || className.startsWith("jdk.internal.reflect.")) {
            return false;
        }
        if (className.equals("java.lang.Class") && methodName.equals("newInstance")) {
            return false;
        }
        return !className.equals("java.lang.invoke.MethodHandle") || !methodName.startsWith("invoke");
    }

    public StackTraceElement calcLocation(String str) {
        if (str == null) {
            return null;
        }
        StackTraceElement[] outline195 = GeneratedOutlineSupport1.outline195();
        boolean z = false;
        for (int i = 0; i < outline195.length; i++) {
            String className = outline195[i].getClassName();
            if (str.equals(className)) {
                z = true;
            } else if (z && !str.equals(className)) {
                return outline195[i];
            }
        }
        return null;
    }

    @PerformanceSensitive
    public Class<?> getCallerClass(int i) {
        if (i >= 0) {
            Method method = GET_CALLER_CLASS;
            if (method == null) {
                return null;
            }
            try {
                return (Class) method.invoke(null, Integer.valueOf(i + 1 + JDK_7u25_OFFSET));
            } catch (Exception unused) {
                return null;
            }
        }
        throw new IndexOutOfBoundsException(Integer.toString(i));
    }

    @PerformanceSensitive
    public Stack<Class<?>> getCurrentStackTrace() {
        if (PrivateSecurityManagerStackTraceUtil.isEnabled()) {
            return PrivateSecurityManagerStackTraceUtil.getCurrentStackTrace();
        }
        Stack<Class<?>> stack = new Stack<>();
        int i = 1;
        while (true) {
            Class<?> callerClass = getCallerClass(i);
            if (callerClass == null) {
                return stack;
            }
            stack.push(callerClass);
            i++;
        }
    }

    public StackTraceElement getStackTraceElement(int i) {
        StackTraceElement[] outline195;
        int i2 = 0;
        for (StackTraceElement stackTraceElement : GeneratedOutlineSupport1.outline195()) {
            if (isValid(stackTraceElement)) {
                if (i2 == i) {
                    return stackTraceElement;
                }
                i2++;
            }
        }
        throw new IndexOutOfBoundsException(Integer.toString(i));
    }

    @PerformanceSensitive
    public Class<?> getCallerClass(String str, String str2) {
        boolean z = false;
        int i = 2;
        while (true) {
            Class<?> callerClass = getCallerClass(i);
            if (callerClass != null) {
                if (str.equals(callerClass.getName())) {
                    z = true;
                } else if (z && callerClass.getName().startsWith(str2)) {
                    return callerClass;
                }
                i++;
            } else {
                return null;
            }
        }
    }

    @PerformanceSensitive
    public Class<?> getCallerClass(Class<?> cls) {
        boolean z = false;
        int i = 2;
        while (true) {
            Class<?> callerClass = getCallerClass(i);
            if (callerClass != null) {
                if (cls.equals(callerClass)) {
                    z = true;
                } else if (z) {
                    return callerClass;
                }
                i++;
            } else {
                return Object.class;
            }
        }
    }
}
