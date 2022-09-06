package dagger.internal;
/* loaded from: classes3.dex */
public final class MissingBindingFactory<T> implements Factory<T> {
    private static final MissingBindingFactory<Object> INSTANCE = new MissingBindingFactory<>();

    private MissingBindingFactory() {
    }

    public static <T> Factory<T> create() {
        return INSTANCE;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public T mo10268get() {
        throw new AssertionError("This binding is not part of the final binding graph. The key was requested by a binding that was believed to possibly be part of the graph, but is no longer requested. If this exception is thrown, it is the result of a Dagger bug.");
    }
}
