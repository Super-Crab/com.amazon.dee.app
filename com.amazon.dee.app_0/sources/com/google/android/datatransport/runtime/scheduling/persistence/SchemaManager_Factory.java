package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public final class SchemaManager_Factory implements Factory<SchemaManager> {
    private final Provider<Context> contextProvider;
    private final Provider<Integer> schemaVersionProvider;

    public SchemaManager_Factory(Provider<Context> provider, Provider<Integer> provider2) {
        this.contextProvider = provider;
        this.schemaVersionProvider = provider2;
    }

    public static SchemaManager_Factory create(Provider<Context> provider, Provider<Integer> provider2) {
        return new SchemaManager_Factory(provider, provider2);
    }

    public static SchemaManager newInstance(Context context, int i) {
        return new SchemaManager(context, i);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SchemaManager mo10268get() {
        return new SchemaManager(this.contextProvider.mo10268get(), this.schemaVersionProvider.mo10268get().intValue());
    }
}
