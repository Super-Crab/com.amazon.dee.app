package amazon.speech.audio;

import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ThreadInterruptHelper {
    private static Method BLOCKED_ON_METHOD;
    private static Class INTERRUPTIBLE_CLASS;
    private static Method POP_ACTION_METHOD;
    private static Method PUSH_ACTION_METHOD;
    private static final String TAG = GeneratedOutlineSupport1.outline39(ThreadInterruptHelper.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    private static final ThreadLocal<Runnable> sLastRunnable = new ThreadLocal<>();
    private static final ThreadLocal<ReflectionInterfaceImplementor> sReflectionInterfaceImplementor = new ThreadLocal<ReflectionInterfaceImplementor>() { // from class: amazon.speech.audio.ThreadInterruptHelper.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        /* renamed from: initialValue */
        public ReflectionInterfaceImplementor mo11initialValue() {
            return new ReflectionInterfaceImplementor(ThreadInterruptHelper.INTERRUPTIBLE_CLASS);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ReflectionInterfaceImplementor {
        private final Class mClass;
        private InvocationTarget mInvocationTarget;
        private final Object mProxy;

        /* loaded from: classes.dex */
        public interface InvocationTarget {
            Object invoke(Method method, Object[] objArr);
        }

        public ReflectionInterfaceImplementor(Class cls) {
            this.mClass = cls;
            this.mProxy = Proxy.newProxyInstance(this.mClass.getClassLoader(), new Class[]{this.mClass}, new InvocationHandler() { // from class: amazon.speech.audio.ThreadInterruptHelper.ReflectionInterfaceImplementor.1
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    if (ReflectionInterfaceImplementor.this.mInvocationTarget == null) {
                        return null;
                    }
                    return ReflectionInterfaceImplementor.this.mInvocationTarget.invoke(method, objArr);
                }
            });
        }

        public Object getProxy() {
            return this.mProxy;
        }

        public void setInvocationTarget(InvocationTarget invocationTarget) {
            this.mInvocationTarget = invocationTarget;
        }
    }

    static {
        boolean z = false;
        try {
            PUSH_ACTION_METHOD = Thread.class.getMethod("pushInterruptAction$", Runnable.class);
            POP_ACTION_METHOD = Thread.class.getMethod("popInterruptAction$", Runnable.class);
        } catch (Exception unused) {
        }
        if (PUSH_ACTION_METHOD == null && POP_ACTION_METHOD == null) {
            try {
                INTERRUPTIBLE_CLASS = Class.forName("sun.nio.ch.Interruptible");
                BLOCKED_ON_METHOD = Thread.class.getMethod("blockedOn", INTERRUPTIBLE_CLASS);
            } catch (Exception unused2) {
            }
        }
        boolean z2 = (PUSH_ACTION_METHOD == null || POP_ACTION_METHOD == null) ? false : true;
        boolean z3 = BLOCKED_ON_METHOD != null;
        if (z2 || z3) {
            z = true;
        }
        if (!z) {
            Log.wtf(TAG, "Thread interruption helper methods unavailable on this device!");
        }
    }

    ThreadInterruptHelper() {
    }

    public static void popInterruptAction(Thread thread, Runnable runnable) {
        try {
            if (POP_ACTION_METHOD != null) {
                POP_ACTION_METHOD.invoke(thread, runnable);
            } else if (BLOCKED_ON_METHOD != null) {
                BLOCKED_ON_METHOD.invoke(thread, INTERRUPTIBLE_CLASS.cast(null));
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception popping interrupt action", e);
        }
    }

    public static void pushInterruptAction(Thread thread, final Runnable runnable) {
        try {
            if (PUSH_ACTION_METHOD != null) {
                PUSH_ACTION_METHOD.invoke(thread, runnable);
            } else if (BLOCKED_ON_METHOD == null) {
            } else {
                if (sLastRunnable.get() != runnable) {
                    sLastRunnable.set(runnable);
                    sReflectionInterfaceImplementor.get().setInvocationTarget(new ReflectionInterfaceImplementor.InvocationTarget() { // from class: amazon.speech.audio.ThreadInterruptHelper.2
                        @Override // amazon.speech.audio.ThreadInterruptHelper.ReflectionInterfaceImplementor.InvocationTarget
                        public Object invoke(Method method, Object[] objArr) {
                            runnable.run();
                            return null;
                        }
                    });
                }
                BLOCKED_ON_METHOD.invoke(thread, sReflectionInterfaceImplementor.get().getProxy());
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception pushing interrupt action", e);
        }
    }
}
