package rx.plugins;
/* loaded from: classes5.dex */
final class RxJavaObservableExecutionHookDefault extends RxJavaObservableExecutionHook {
    private static final RxJavaObservableExecutionHookDefault INSTANCE = new RxJavaObservableExecutionHookDefault();

    private RxJavaObservableExecutionHookDefault() {
    }

    public static RxJavaObservableExecutionHook getInstance() {
        return INSTANCE;
    }
}
