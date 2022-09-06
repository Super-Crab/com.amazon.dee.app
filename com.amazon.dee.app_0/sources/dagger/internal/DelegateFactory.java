package dagger.internal;

import javax.inject.Provider;
/* loaded from: classes3.dex */
public final class DelegateFactory<T> implements Factory<T> {
    private Provider<T> delegate;

    public static <T> void setDelegate(Provider<T> delegateFactory, Provider<T> delegate) {
        Preconditions.checkNotNull(delegate);
        DelegateFactory delegateFactory2 = (DelegateFactory) delegateFactory;
        if (delegateFactory2.delegate == null) {
            delegateFactory2.delegate = delegate;
            return;
        }
        throw new IllegalStateException();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public T mo10268get() {
        Provider<T> provider = this.delegate;
        if (provider != null) {
            return provider.mo10268get();
        }
        throw new IllegalStateException();
    }

    @Deprecated
    public void setDelegatedProvider(Provider<T> delegate) {
        setDelegate(this, delegate);
    }
}
