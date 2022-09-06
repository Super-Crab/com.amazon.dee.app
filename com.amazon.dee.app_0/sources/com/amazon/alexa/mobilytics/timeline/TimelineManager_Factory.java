package com.amazon.alexa.mobilytics.timeline;

import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class TimelineManager_Factory implements Factory<TimelineManager> {
    private final Provider<TimelineStorage> pTimelineStorageProvider;

    public TimelineManager_Factory(Provider<TimelineStorage> provider) {
        this.pTimelineStorageProvider = provider;
    }

    public static TimelineManager_Factory create(Provider<TimelineStorage> provider) {
        return new TimelineManager_Factory(provider);
    }

    public static TimelineManager newTimelineManager(TimelineStorage timelineStorage) {
        return new TimelineManager(timelineStorage);
    }

    public static TimelineManager provideInstance(Provider<TimelineStorage> provider) {
        return new TimelineManager(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public TimelineManager mo10268get() {
        return provideInstance(this.pTimelineStorageProvider);
    }
}
