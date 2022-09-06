package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public final class CreationContextFactory_Factory implements Factory<CreationContextFactory> {
    private final Provider<Context> applicationContextProvider;
    private final Provider<Clock> monotonicClockProvider;
    private final Provider<Clock> wallClockProvider;

    public CreationContextFactory_Factory(Provider<Context> provider, Provider<Clock> provider2, Provider<Clock> provider3) {
        this.applicationContextProvider = provider;
        this.wallClockProvider = provider2;
        this.monotonicClockProvider = provider3;
    }

    public static CreationContextFactory_Factory create(Provider<Context> provider, Provider<Clock> provider2, Provider<Clock> provider3) {
        return new CreationContextFactory_Factory(provider, provider2, provider3);
    }

    public static CreationContextFactory newInstance(Context context, Clock clock, Clock clock2) {
        return new CreationContextFactory(context, clock, clock2);
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CreationContextFactory mo10268get() {
        return new CreationContextFactory(this.applicationContextProvider.mo10268get(), this.wallClockProvider.mo10268get(), this.monotonicClockProvider.mo10268get());
    }
}
