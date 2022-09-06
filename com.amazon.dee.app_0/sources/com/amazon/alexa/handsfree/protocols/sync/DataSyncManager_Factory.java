package com.amazon.alexa.handsfree.protocols.sync;

import android.content.Context;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class DataSyncManager_Factory implements Factory<DataSyncManager> {
    private final Provider<Context> contextProvider;
    private final Provider<HandsFreeUserIdentityProvider> handsFreeUserIdentityProvider;

    public DataSyncManager_Factory(Provider<Context> provider, Provider<HandsFreeUserIdentityProvider> provider2) {
        this.contextProvider = provider;
        this.handsFreeUserIdentityProvider = provider2;
    }

    public static DataSyncManager_Factory create(Provider<Context> provider, Provider<HandsFreeUserIdentityProvider> provider2) {
        return new DataSyncManager_Factory(provider, provider2);
    }

    public static DataSyncManager newDataSyncManager(Context context, HandsFreeUserIdentityProvider handsFreeUserIdentityProvider) {
        return new DataSyncManager(context, handsFreeUserIdentityProvider);
    }

    public static DataSyncManager provideInstance(Provider<Context> provider, Provider<HandsFreeUserIdentityProvider> provider2) {
        return new DataSyncManager(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DataSyncManager mo10268get() {
        return provideInstance(this.contextProvider, this.handsFreeUserIdentityProvider);
    }
}
