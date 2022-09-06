package com.google.android.datatransport.runtime.scheduling.persistence;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public final class EventStoreModule_StoreConfigFactory implements Factory<EventStoreConfig> {
    private static final EventStoreModule_StoreConfigFactory INSTANCE = new EventStoreModule_StoreConfigFactory();

    public static EventStoreModule_StoreConfigFactory create() {
        return INSTANCE;
    }

    public static EventStoreConfig storeConfig() {
        return (EventStoreConfig) Preconditions.checkNotNull(EventStoreModule.storeConfig(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventStoreConfig mo10268get() {
        return storeConfig();
    }
}
