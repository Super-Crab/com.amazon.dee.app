package com.amazon.alexa.mobilytics.timeline;

import com.amazon.alexa.mobilytics.storage.PersistentStorage;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class TimelineStorage_Factory implements Factory<TimelineStorage> {
    private final Provider<PersistentStorage.Factory> storageFactoryProvider;

    public TimelineStorage_Factory(Provider<PersistentStorage.Factory> provider) {
        this.storageFactoryProvider = provider;
    }

    public static TimelineStorage_Factory create(Provider<PersistentStorage.Factory> provider) {
        return new TimelineStorage_Factory(provider);
    }

    public static TimelineStorage newTimelineStorage(PersistentStorage.Factory factory) {
        return new TimelineStorage(factory);
    }

    public static TimelineStorage provideInstance(Provider<PersistentStorage.Factory> provider) {
        return new TimelineStorage(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TimelineStorage mo10268get() {
        return provideInstance(this.storageFactoryProvider);
    }
}
