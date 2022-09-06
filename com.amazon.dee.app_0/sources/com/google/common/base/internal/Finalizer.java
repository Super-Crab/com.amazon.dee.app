package com.google.common.base.internal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes13.dex */
public class Finalizer implements Runnable {
    private static final String FINALIZABLE_REFERENCE = "com.google.common.base.FinalizableReference";
    @NullableDecl
    private static final Field inheritableThreadLocals;
    private final WeakReference<Class<?>> finalizableReferenceClassReference;
    private final PhantomReference<Object> frqReference;
    private final ReferenceQueue<Object> queue;
    private static final Logger logger = Logger.getLogger(Finalizer.class.getName());
    @NullableDecl
    private static final Constructor<Thread> bigThreadConstructor = getBigThreadConstructor();

    static {
        inheritableThreadLocals = bigThreadConstructor == null ? getInheritableThreadLocalsField() : null;
    }

    private Finalizer(Class<?> cls, ReferenceQueue<Object> referenceQueue, PhantomReference<Object> phantomReference) {
        this.queue = referenceQueue;
        this.finalizableReferenceClassReference = new WeakReference<>(cls);
        this.frqReference = phantomReference;
    }

    private boolean cleanUp(Reference<?> reference) {
        Method finalizeReferentMethod = getFinalizeReferentMethod();
        if (finalizeReferentMethod == null) {
            return false;
        }
        do {
            reference.clear();
            if (reference == this.frqReference) {
                return false;
            }
            try {
                finalizeReferentMethod.invoke(reference, new Object[0]);
            } catch (Throwable th) {
                logger.log(Level.SEVERE, "Error cleaning up after reference.", th);
            }
            reference = this.queue.poll();
        } while (reference != null);
        return true;
    }

    @NullableDecl
    private static Constructor<Thread> getBigThreadConstructor() {
        try {
            return Thread.class.getConstructor(ThreadGroup.class, Runnable.class, String.class, Long.TYPE, Boolean.TYPE);
        } catch (Throwable unused) {
            return null;
        }
    }

    @NullableDecl
    private Method getFinalizeReferentMethod() {
        Class<?> cls = this.finalizableReferenceClassReference.get();
        if (cls == null) {
            return null;
        }
        try {
            return cls.getMethod("finalizeReferent", new Class[0]);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        }
    }

    @NullableDecl
    private static Field getInheritableThreadLocalsField() {
        try {
            Field declaredField = Thread.class.getDeclaredField("inheritableThreadLocals");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            logger.log(Level.INFO, "Couldn't access Thread.inheritableThreadLocals. Reference finalizer threads will inherit thread local values.");
            return null;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:3|(10:5|6|7|(1:9)|10|11|12|(1:14)|16|17)|24|(0)|10|11|12|(0)|16|17) */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005e, code lost:
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005f, code lost:
        com.google.common.base.internal.Finalizer.logger.log(java.util.logging.Level.INFO, "Failed to clear thread local values inherited by reference finalizer thread.", r7);
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0058 A[Catch: all -> 0x005e, TRY_LEAVE, TryCatch #0 {all -> 0x005e, blocks: (B:15:0x0054, B:17:0x0058), top: B:25:0x0054 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void startFinalizer(java.lang.Class<?> r7, java.lang.ref.ReferenceQueue<java.lang.Object> r8, java.lang.ref.PhantomReference<java.lang.Object> r9) {
        /*
            java.lang.String r0 = r7.getName()
            java.lang.String r1 = "com.google.common.base.FinalizableReference"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L6c
            com.google.common.base.internal.Finalizer r0 = new com.google.common.base.internal.Finalizer
            r0.<init>(r7, r8, r9)
            java.lang.Class<com.google.common.base.internal.Finalizer> r7 = com.google.common.base.internal.Finalizer.class
            java.lang.String r7 = r7.getName()
            java.lang.reflect.Constructor<java.lang.Thread> r8 = com.google.common.base.internal.Finalizer.bigThreadConstructor
            r9 = 1
            r1 = 0
            if (r8 == 0) goto L49
            r2 = 0
            r4 = 5
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L3f
            r5 = 0
            r4[r5] = r1     // Catch: java.lang.Throwable -> L3f
            r4[r9] = r0     // Catch: java.lang.Throwable -> L3f
            r6 = 2
            r4[r6] = r7     // Catch: java.lang.Throwable -> L3f
            r6 = 3
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Throwable -> L3f
            r4[r6] = r2     // Catch: java.lang.Throwable -> L3f
            r2 = 4
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r5)     // Catch: java.lang.Throwable -> L3f
            r4[r2] = r3     // Catch: java.lang.Throwable -> L3f
            java.lang.Object r8 = r8.newInstance(r4)     // Catch: java.lang.Throwable -> L3f
            java.lang.Thread r8 = (java.lang.Thread) r8     // Catch: java.lang.Throwable -> L3f
            goto L4a
        L3f:
            r8 = move-exception
            java.util.logging.Logger r2 = com.google.common.base.internal.Finalizer.logger
            java.util.logging.Level r3 = java.util.logging.Level.INFO
            java.lang.String r4 = "Failed to create a thread without inherited thread-local values"
            r2.log(r3, r4, r8)
        L49:
            r8 = r1
        L4a:
            if (r8 != 0) goto L51
            java.lang.Thread r8 = new java.lang.Thread
            r8.<init>(r1, r0, r7)
        L51:
            r8.setDaemon(r9)
            java.lang.reflect.Field r7 = com.google.common.base.internal.Finalizer.inheritableThreadLocals     // Catch: java.lang.Throwable -> L5e
            if (r7 == 0) goto L68
            java.lang.reflect.Field r7 = com.google.common.base.internal.Finalizer.inheritableThreadLocals     // Catch: java.lang.Throwable -> L5e
            r7.set(r8, r1)     // Catch: java.lang.Throwable -> L5e
            goto L68
        L5e:
            r7 = move-exception
            java.util.logging.Logger r9 = com.google.common.base.internal.Finalizer.logger
            java.util.logging.Level r0 = java.util.logging.Level.INFO
            java.lang.String r1 = "Failed to clear thread local values inherited by reference finalizer thread."
            r9.log(r0, r1, r7)
        L68:
            r8.start()
            return
        L6c:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "Expected com.google.common.base.FinalizableReference."
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.internal.Finalizer.startFinalizer(java.lang.Class, java.lang.ref.ReferenceQueue, java.lang.ref.PhantomReference):void");
    }

    @Override // java.lang.Runnable
    public void run() {
        while (cleanUp(this.queue.remove())) {
        }
    }
}
