package dagger.internal;

import dagger.Lazy;
import javax.inject.Provider;
/* loaded from: classes3.dex */
public final class DoubleCheck<T> implements Provider<T>, Lazy<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider<T> provider;

    private DoubleCheck(Provider<T> provider) {
        this.provider = provider;
    }

    public static <P extends Provider<T>, T> Lazy<T> lazy(P provider) {
        if (provider instanceof Lazy) {
            return (Lazy) provider;
        }
        return new DoubleCheck((Provider) Preconditions.checkNotNull(provider));
    }

    public static <P extends Provider<T>, T> Provider<T> provider(P delegate) {
        Preconditions.checkNotNull(delegate);
        return delegate instanceof DoubleCheck ? delegate : new DoubleCheck(delegate);
    }

    public static Object reentrantCheck(Object currentInstance, Object newInstance) {
        if (!(currentInstance != UNINITIALIZED && !(currentInstance instanceof MemoizedSentinel)) || currentInstance == newInstance) {
            return newInstance;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + currentInstance + " & " + newInstance + ". This is likely due to a circular dependency.");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public T mo10268get() {
        T t = (T) this.instance;
        if (t == UNINITIALIZED) {
            synchronized (this) {
                t = this.instance;
                if (t == UNINITIALIZED) {
                    t = this.provider.mo10268get();
                    this.instance = reentrantCheck(this.instance, t);
                    this.provider = null;
                }
            }
        }
        return t;
    }
}
