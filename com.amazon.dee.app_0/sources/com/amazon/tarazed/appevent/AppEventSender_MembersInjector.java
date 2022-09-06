package com.amazon.tarazed.appevent;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AppEventSender_MembersInjector implements MembersInjector<AppEventSender> {
    private final Provider<TarazedIoTManager> p0Provider;
    private final Provider<TarazedSessionLogger> p0Provider2;
    private final Provider<TarazedEventDispatcher> p0Provider3;

    public AppEventSender_MembersInjector(Provider<TarazedIoTManager> provider, Provider<TarazedSessionLogger> provider2, Provider<TarazedEventDispatcher> provider3) {
        this.p0Provider = provider;
        this.p0Provider2 = provider2;
        this.p0Provider3 = provider3;
    }

    public static MembersInjector<AppEventSender> create(Provider<TarazedIoTManager> provider, Provider<TarazedSessionLogger> provider2, Provider<TarazedEventDispatcher> provider3) {
        return new AppEventSender_MembersInjector(provider, provider2, provider3);
    }

    public static void injectSetEventDispatcher(AppEventSender appEventSender, TarazedEventDispatcher tarazedEventDispatcher) {
        appEventSender.setEventDispatcher(tarazedEventDispatcher);
    }

    public static void injectSetIotManager(AppEventSender appEventSender, TarazedIoTManager tarazedIoTManager) {
        appEventSender.setIotManager(tarazedIoTManager);
    }

    public static void injectSetLogger(AppEventSender appEventSender, TarazedSessionLogger tarazedSessionLogger) {
        appEventSender.setLogger(tarazedSessionLogger);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AppEventSender appEventSender) {
        injectSetIotManager(appEventSender, this.p0Provider.mo10268get());
        injectSetLogger(appEventSender, this.p0Provider2.mo10268get());
        injectSetEventDispatcher(appEventSender, this.p0Provider3.mo10268get());
    }
}
