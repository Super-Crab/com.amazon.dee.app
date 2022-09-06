package dagger.internal;

import dagger.Lazy;
/* loaded from: classes3.dex */
public final class InstanceFactory<T> implements Factory<T>, Lazy<T> {
    private static final InstanceFactory<Object> NULL_INSTANCE_FACTORY = new InstanceFactory<>(null);
    private final T instance;

    private InstanceFactory(T instance) {
        this.instance = instance;
    }

    public static <T> Factory<T> create(T instance) {
        return new InstanceFactory(Preconditions.checkNotNull(instance, "instance cannot be null"));
    }

    public static <T> Factory<T> createNullable(T instance) {
        if (instance == null) {
            return nullInstanceFactory();
        }
        return new InstanceFactory(instance);
    }

    private static <T> InstanceFactory<T> nullInstanceFactory() {
        return (InstanceFactory<T>) NULL_INSTANCE_FACTORY;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public T mo10268get() {
        return this.instance;
    }
}
