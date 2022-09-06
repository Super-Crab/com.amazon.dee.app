package com.google.android.datatransport.runtime.scheduling.persistence;

import dagger.internal.Factory;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public final class EventStoreModule_SchemaVersionFactory implements Factory<Integer> {
    private static final EventStoreModule_SchemaVersionFactory INSTANCE = new EventStoreModule_SchemaVersionFactory();

    public static EventStoreModule_SchemaVersionFactory create() {
        return INSTANCE;
    }

    public static int schemaVersion() {
        return EventStoreModule.schemaVersion();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Integer mo10268get() {
        return Integer.valueOf(schemaVersion());
    }
}
