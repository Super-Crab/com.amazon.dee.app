package org.apache.logging.log4j.util;

import java.util.Stack;
/* loaded from: classes4.dex */
final class PrivateSecurityManagerStackTraceUtil {
    private static final PrivateSecurityManager SECURITY_MANAGER;

    /* loaded from: classes4.dex */
    private static final class PrivateSecurityManager extends SecurityManager {
        private PrivateSecurityManager() {
        }

        @Override // java.lang.SecurityManager
        protected Class<?>[] getClassContext() {
            return super.getClassContext();
        }
    }

    static {
        PrivateSecurityManager privateSecurityManager = null;
        try {
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                securityManager.checkPermission(new RuntimePermission("createSecurityManager"));
            }
            privateSecurityManager = new PrivateSecurityManager();
        } catch (SecurityException unused) {
        }
        SECURITY_MANAGER = privateSecurityManager;
    }

    private PrivateSecurityManagerStackTraceUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Stack<Class<?>> getCurrentStackTrace() {
        Class<?>[] classContext = SECURITY_MANAGER.getClassContext();
        Stack<Class<?>> stack = new Stack<>();
        stack.ensureCapacity(classContext.length);
        for (Class<?> cls : classContext) {
            stack.push(cls);
        }
        return stack;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isEnabled() {
        return SECURITY_MANAGER != null;
    }
}
