package dagger.internal;

import javax.inject.Provider;
/* loaded from: classes3.dex */
public final class SingleCheck<T> implements Provider<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider<T> provider;

    private SingleCheck(Provider<T> provider) {
        this.provider = provider;
    }

    public static <P extends Provider<T>, T> Provider<T> provider(P provider) {
        return ((provider instanceof SingleCheck) || (provider instanceof DoubleCheck)) ? provider : new SingleCheck((Provider) Preconditions.checkNotNull(provider));
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public T mo10268get() {
        T t = (T) this.instance;
        if (t == UNINITIALIZED) {
            Provider<T> provider = this.provider;
            if (provider == null) {
                return (T) this.instance;
            }
            T mo10268get = provider.mo10268get();
            this.instance = mo10268get;
            this.provider = null;
            return mo10268get;
        }
        return t;
    }
}
