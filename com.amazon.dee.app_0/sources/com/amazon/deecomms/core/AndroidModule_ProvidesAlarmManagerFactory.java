package com.amazon.deecomms.core;

import android.app.AlarmManager;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AndroidModule_ProvidesAlarmManagerFactory implements Factory<AlarmManager> {
    private final Provider<Context> contextProvider;
    private final AndroidModule module;

    public AndroidModule_ProvidesAlarmManagerFactory(AndroidModule androidModule, Provider<Context> provider) {
        this.module = androidModule;
        this.contextProvider = provider;
    }

    public static AndroidModule_ProvidesAlarmManagerFactory create(AndroidModule androidModule, Provider<Context> provider) {
        return new AndroidModule_ProvidesAlarmManagerFactory(androidModule, provider);
    }

    public static AlarmManager provideInstance(AndroidModule androidModule, Provider<Context> provider) {
        return (AlarmManager) Preconditions.checkNotNull(androidModule.providesAlarmManager(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static AlarmManager proxyProvidesAlarmManager(AndroidModule androidModule, Context context) {
        return (AlarmManager) Preconditions.checkNotNull(androidModule.providesAlarmManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlarmManager mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
