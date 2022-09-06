package rx.plugins;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicReference;
import rx.annotations.Experimental;
/* loaded from: classes5.dex */
public class RxJavaPlugins {
    private static final RxJavaPlugins INSTANCE = new RxJavaPlugins();
    static final RxJavaErrorHandler DEFAULT_ERROR_HANDLER = new RxJavaErrorHandler() { // from class: rx.plugins.RxJavaPlugins.1
    };
    private final AtomicReference<RxJavaErrorHandler> errorHandler = new AtomicReference<>();
    private final AtomicReference<RxJavaObservableExecutionHook> observableExecutionHook = new AtomicReference<>();
    private final AtomicReference<RxJavaSingleExecutionHook> singleExecutionHook = new AtomicReference<>();
    private final AtomicReference<RxJavaCompletableExecutionHook> completableExecutionHook = new AtomicReference<>();
    private final AtomicReference<RxJavaSchedulersHook> schedulersHook = new AtomicReference<>();

    RxJavaPlugins() {
    }

    @Deprecated
    public static RxJavaPlugins getInstance() {
        return INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00d0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.Object getPluginImplementationViaProperty(java.lang.Class<?> r7, java.util.Properties r8) {
        /*
            java.lang.Object r8 = r8.clone()
            java.util.Properties r8 = (java.util.Properties) r8
            java.lang.String r0 = r7.getSimpleName()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "rxjava.plugin."
            r1.append(r2)
            r1.append(r0)
            java.lang.String r3 = ".implementation"
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r8.getProperty(r1)
            if (r1 != 0) goto L8a
            java.util.Set r3 = r8.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L2e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L8a
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r5 = r4.getKey()
            java.lang.String r5 = r5.toString()
            boolean r6 = r5.startsWith(r2)
            if (r6 == 0) goto L2e
            java.lang.String r6 = ".class"
            boolean r6 = r5.endsWith(r6)
            if (r6 == 0) goto L2e
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = r4.toString()
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L2e
            r1 = 0
            int r3 = r5.length()
            int r3 = r3 + (-6)
            java.lang.String r1 = r5.substring(r1, r3)
            r3 = 14
            java.lang.String r1 = r1.substring(r3)
            java.lang.String r3 = ".impl"
            java.lang.String r1 = com.android.tools.r8.GeneratedOutlineSupport1.outline75(r2, r1, r3)
            java.lang.String r8 = r8.getProperty(r1)
            if (r8 == 0) goto L7c
            goto L8b
        L7c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "Implementing class declaration for "
            java.lang.String r2 = " missing: "
            java.lang.String r8 = com.android.tools.r8.GeneratedOutlineSupport1.outline76(r8, r0, r2, r1)
            r7.<init>(r8)
            throw r7
        L8a:
            r8 = r1
        L8b:
            if (r8 == 0) goto Ld0
            java.lang.Class r1 = java.lang.Class.forName(r8)     // Catch: java.lang.IllegalAccessException -> L9a java.lang.InstantiationException -> La7 java.lang.ClassNotFoundException -> Lb4 java.lang.ClassCastException -> Lc1
            java.lang.Class r7 = r1.asSubclass(r7)     // Catch: java.lang.IllegalAccessException -> L9a java.lang.InstantiationException -> La7 java.lang.ClassNotFoundException -> Lb4 java.lang.ClassCastException -> Lc1
            java.lang.Object r7 = r7.newInstance()     // Catch: java.lang.IllegalAccessException -> L9a java.lang.InstantiationException -> La7 java.lang.ClassNotFoundException -> Lb4 java.lang.ClassCastException -> Lc1
            return r7
        L9a:
            r7 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = " implementation not able to be accessed: "
            java.lang.String r8 = com.android.tools.r8.GeneratedOutlineSupport1.outline75(r0, r2, r8)
            r1.<init>(r8, r7)
            throw r1
        La7:
            r7 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = " implementation not able to be instantiated: "
            java.lang.String r8 = com.android.tools.r8.GeneratedOutlineSupport1.outline75(r0, r2, r8)
            r1.<init>(r8, r7)
            throw r1
        Lb4:
            r7 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = " implementation class not found: "
            java.lang.String r8 = com.android.tools.r8.GeneratedOutlineSupport1.outline75(r0, r2, r8)
            r1.<init>(r8, r7)
            throw r1
        Lc1:
            r7 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = " implementation is not an instance of "
            java.lang.String r3 = ": "
            java.lang.String r8 = com.android.tools.r8.GeneratedOutlineSupport1.outline77(r0, r2, r0, r3, r8)
            r1.<init>(r8, r7)
            throw r1
        Ld0:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.plugins.RxJavaPlugins.getPluginImplementationViaProperty(java.lang.Class, java.util.Properties):java.lang.Object");
    }

    @Experimental
    public RxJavaCompletableExecutionHook getCompletableExecutionHook() {
        if (this.completableExecutionHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaCompletableExecutionHook.class, System.getProperties());
            if (pluginImplementationViaProperty == null) {
                this.completableExecutionHook.compareAndSet(null, new RxJavaCompletableExecutionHook() { // from class: rx.plugins.RxJavaPlugins.2
                });
            } else {
                this.completableExecutionHook.compareAndSet(null, (RxJavaCompletableExecutionHook) pluginImplementationViaProperty);
            }
        }
        return this.completableExecutionHook.get();
    }

    public RxJavaErrorHandler getErrorHandler() {
        if (this.errorHandler.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaErrorHandler.class, System.getProperties());
            if (pluginImplementationViaProperty == null) {
                this.errorHandler.compareAndSet(null, DEFAULT_ERROR_HANDLER);
            } else {
                this.errorHandler.compareAndSet(null, (RxJavaErrorHandler) pluginImplementationViaProperty);
            }
        }
        return this.errorHandler.get();
    }

    public RxJavaObservableExecutionHook getObservableExecutionHook() {
        if (this.observableExecutionHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaObservableExecutionHook.class, System.getProperties());
            if (pluginImplementationViaProperty == null) {
                this.observableExecutionHook.compareAndSet(null, RxJavaObservableExecutionHookDefault.getInstance());
            } else {
                this.observableExecutionHook.compareAndSet(null, (RxJavaObservableExecutionHook) pluginImplementationViaProperty);
            }
        }
        return this.observableExecutionHook.get();
    }

    public RxJavaSchedulersHook getSchedulersHook() {
        if (this.schedulersHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaSchedulersHook.class, System.getProperties());
            if (pluginImplementationViaProperty == null) {
                this.schedulersHook.compareAndSet(null, RxJavaSchedulersHook.getDefaultInstance());
            } else {
                this.schedulersHook.compareAndSet(null, (RxJavaSchedulersHook) pluginImplementationViaProperty);
            }
        }
        return this.schedulersHook.get();
    }

    public RxJavaSingleExecutionHook getSingleExecutionHook() {
        if (this.singleExecutionHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaSingleExecutionHook.class, System.getProperties());
            if (pluginImplementationViaProperty == null) {
                this.singleExecutionHook.compareAndSet(null, RxJavaSingleExecutionHookDefault.getInstance());
            } else {
                this.singleExecutionHook.compareAndSet(null, (RxJavaSingleExecutionHook) pluginImplementationViaProperty);
            }
        }
        return this.singleExecutionHook.get();
    }

    @Experimental
    public void registerCompletableExecutionHook(RxJavaCompletableExecutionHook rxJavaCompletableExecutionHook) {
        if (this.completableExecutionHook.compareAndSet(null, rxJavaCompletableExecutionHook)) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Another strategy was already registered: ");
        outline107.append(this.singleExecutionHook.get());
        throw new IllegalStateException(outline107.toString());
    }

    public void registerErrorHandler(RxJavaErrorHandler rxJavaErrorHandler) {
        if (this.errorHandler.compareAndSet(null, rxJavaErrorHandler)) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Another strategy was already registered: ");
        outline107.append(this.errorHandler.get());
        throw new IllegalStateException(outline107.toString());
    }

    public void registerObservableExecutionHook(RxJavaObservableExecutionHook rxJavaObservableExecutionHook) {
        if (this.observableExecutionHook.compareAndSet(null, rxJavaObservableExecutionHook)) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Another strategy was already registered: ");
        outline107.append(this.observableExecutionHook.get());
        throw new IllegalStateException(outline107.toString());
    }

    public void registerSchedulersHook(RxJavaSchedulersHook rxJavaSchedulersHook) {
        if (this.schedulersHook.compareAndSet(null, rxJavaSchedulersHook)) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Another strategy was already registered: ");
        outline107.append(this.schedulersHook.get());
        throw new IllegalStateException(outline107.toString());
    }

    public void registerSingleExecutionHook(RxJavaSingleExecutionHook rxJavaSingleExecutionHook) {
        if (this.singleExecutionHook.compareAndSet(null, rxJavaSingleExecutionHook)) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Another strategy was already registered: ");
        outline107.append(this.singleExecutionHook.get());
        throw new IllegalStateException(outline107.toString());
    }

    @Experimental
    public void reset() {
        INSTANCE.errorHandler.set(null);
        INSTANCE.observableExecutionHook.set(null);
        INSTANCE.singleExecutionHook.set(null);
        INSTANCE.completableExecutionHook.set(null);
        INSTANCE.schedulersHook.set(null);
    }
}
