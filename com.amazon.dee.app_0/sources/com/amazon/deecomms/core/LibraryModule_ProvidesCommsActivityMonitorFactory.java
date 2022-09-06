package com.amazon.deecomms.core;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.content.Context;
import com.amazon.deecomms.common.CommsActivityMonitor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCommsActivityMonitorFactory implements Factory<CommsActivityMonitor> {
    private final Provider<ActivityManager> activityManagerProvider;
    private final Provider<AlarmManager> alarmManagerProvider;
    private final Provider<Context> contextProvider;
    private final LibraryModule module;

    public LibraryModule_ProvidesCommsActivityMonitorFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<AlarmManager> provider2, Provider<ActivityManager> provider3) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.alarmManagerProvider = provider2;
        this.activityManagerProvider = provider3;
    }

    public static LibraryModule_ProvidesCommsActivityMonitorFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<AlarmManager> provider2, Provider<ActivityManager> provider3) {
        return new LibraryModule_ProvidesCommsActivityMonitorFactory(libraryModule, provider, provider2, provider3);
    }

    public static CommsActivityMonitor provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<AlarmManager> provider2, Provider<ActivityManager> provider3) {
        return (CommsActivityMonitor) Preconditions.checkNotNull(libraryModule.providesCommsActivityMonitor(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsActivityMonitor proxyProvidesCommsActivityMonitor(LibraryModule libraryModule, Context context, AlarmManager alarmManager, ActivityManager activityManager) {
        return (CommsActivityMonitor) Preconditions.checkNotNull(libraryModule.providesCommsActivityMonitor(context, alarmManager, activityManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsActivityMonitor mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.alarmManagerProvider, this.activityManagerProvider);
    }
}
