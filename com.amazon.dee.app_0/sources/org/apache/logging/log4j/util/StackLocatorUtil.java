package org.apache.logging.log4j.util;

import java.util.NoSuchElementException;
import java.util.Stack;
import org.apache.logging.log4j.status.StatusLogger;
/* loaded from: classes4.dex */
public final class StackLocatorUtil {
    private static volatile boolean errorLogged;
    private static StackLocator stackLocator = StackLocator.getInstance();

    private StackLocatorUtil() {
    }

    public static StackTraceElement calcLocation(String str) {
        try {
            return stackLocator.calcLocation(str);
        } catch (NoSuchElementException e) {
            if (errorLogged) {
                return null;
            }
            errorLogged = true;
            StatusLogger.getLogger().warn("Unable to locate stack trace element for {}", str, e);
            return null;
        }
    }

    @PerformanceSensitive
    public static Class<?> getCallerClass(int i) {
        return stackLocator.getCallerClass(i + 1);
    }

    @PerformanceSensitive
    public static Stack<Class<?>> getCurrentStackTrace() {
        return stackLocator.getCurrentStackTrace();
    }

    public static StackTraceElement getStackTraceElement(int i) {
        return stackLocator.getStackTraceElement(i + 1);
    }

    @PerformanceSensitive
    public static Class<?> getCallerClass(String str) {
        return getCallerClass(str, "");
    }

    @PerformanceSensitive
    public static Class<?> getCallerClass(String str, String str2) {
        return stackLocator.getCallerClass(str, str2);
    }

    @PerformanceSensitive
    public static Class<?> getCallerClass(Class<?> cls) {
        return stackLocator.getCallerClass(cls);
    }
}
