package com.amazon.deecomms.core;

import android.app.NotificationManager;
import android.content.Context;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.sip.SipClientState;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class LibraryModule_ProvidesCommsNotificationManagerFactory implements Factory<CommsNotificationManager> {
    private final Provider<Context> contextProvider;
    private final LibraryModule module;
    private final Provider<NotificationManager> notificationManagerProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public LibraryModule_ProvidesCommsNotificationManagerFactory(LibraryModule libraryModule, Provider<Context> provider, Provider<NotificationManager> provider2, Provider<SipClientState> provider3) {
        this.module = libraryModule;
        this.contextProvider = provider;
        this.notificationManagerProvider = provider2;
        this.sipClientStateProvider = provider3;
    }

    public static LibraryModule_ProvidesCommsNotificationManagerFactory create(LibraryModule libraryModule, Provider<Context> provider, Provider<NotificationManager> provider2, Provider<SipClientState> provider3) {
        return new LibraryModule_ProvidesCommsNotificationManagerFactory(libraryModule, provider, provider2, provider3);
    }

    public static CommsNotificationManager provideInstance(LibraryModule libraryModule, Provider<Context> provider, Provider<NotificationManager> provider2, Provider<SipClientState> provider3) {
        return (CommsNotificationManager) Preconditions.checkNotNull(libraryModule.providesCommsNotificationManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsNotificationManager proxyProvidesCommsNotificationManager(LibraryModule libraryModule, Context context, NotificationManager notificationManager, SipClientState sipClientState) {
        return (CommsNotificationManager) Preconditions.checkNotNull(libraryModule.providesCommsNotificationManager(context, notificationManager, sipClientState), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsNotificationManager mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.notificationManagerProvider, this.sipClientStateProvider);
    }
}
