package com.amazon.alexa.mobilytics.timeline;

import com.amazon.alexa.mobilytics.storage.PersistentStorage;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class TimelineDataPublisher_Factory implements Factory<TimelineDataPublisher> {
    private final Provider<PersistentStorage.Factory> pStorageFactoryProvider;
    private final Provider<TimelineStorage> pTimelineStorageProvider;

    public TimelineDataPublisher_Factory(Provider<TimelineStorage> provider, Provider<PersistentStorage.Factory> provider2) {
        this.pTimelineStorageProvider = provider;
        this.pStorageFactoryProvider = provider2;
    }

    public static TimelineDataPublisher_Factory create(Provider<TimelineStorage> provider, Provider<PersistentStorage.Factory> provider2) {
        return new TimelineDataPublisher_Factory(provider, provider2);
    }

    public static TimelineDataPublisher newTimelineDataPublisher(TimelineStorage timelineStorage, PersistentStorage.Factory factory) {
        return new TimelineDataPublisher(timelineStorage, factory);
    }

    public static TimelineDataPublisher provideInstance(Provider<TimelineStorage> provider, Provider<PersistentStorage.Factory> provider2) {
        return new TimelineDataPublisher(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TimelineDataPublisher mo10268get() {
        return provideInstance(this.pTimelineStorageProvider, this.pStorageFactoryProvider);
    }
}
