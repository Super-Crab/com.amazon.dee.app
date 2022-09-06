package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.AccountUpgradeService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.voice.app.LatencyReportingDelegate;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.model.VoiceServiceFactory;
import com.amazon.dee.app.services.core.DefaultApplicationLifecycleService;
import com.amazon.regulator.Component;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
@Module
/* loaded from: classes12.dex */
public final class VoiceModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Context lambda$provideVoiceService$0(Lazy lazy) {
        return (Context) lazy.mo358get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DeviceInformation lambda$provideVoiceService$1(Lazy lazy) {
        return (DeviceInformation) lazy.mo358get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ IdentityService lambda$provideVoiceService$2(Lazy lazy) {
        return (IdentityService) lazy.mo358get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AccountUpgradeService lambda$provideVoiceService$3(Lazy lazy) {
        return (AccountUpgradeService) lazy.mo358get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ NetworkService lambda$provideVoiceService$4(Lazy lazy) {
        return (NetworkService) lazy.mo358get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ PersistentStorage.Factory lambda$provideVoiceService$5(Lazy lazy) {
        return (PersistentStorage.Factory) lazy.mo358get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ LatencyReportingDelegate lambda$provideVoiceService$6(Lazy lazy) {
        return (LatencyReportingDelegate) lazy.mo358get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MetricsService lambda$provideVoiceService$7(Lazy lazy) {
        return (MetricsService) lazy.mo358get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ApplicationLifecycleService lambda$provideVoiceService$8(Lazy lazy) {
        return (DefaultApplicationLifecycleService) lazy.mo358get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ RoutingService lambda$provideVoiceService$9(Lazy lazy) {
        return (RoutingService) lazy.mo358get();
    }

    @Provides
    @ApplicationScope
    public LatencyReportingDelegate provideLatencyReportingDelegate() {
        return $$Lambda$VoiceModule$t2zBxK6eAeZaIH25NWB326cZJj0.INSTANCE;
    }

    @Provides
    @ApplicationScope
    @IntoSet
    public MessagingReceiver provideVoiceMessagingReceiver(VoiceService voiceService) {
        return voiceService.getMessagingReceiver();
    }

    @Provides
    @ApplicationScope
    public VoiceService provideVoiceService(final Lazy<Context> lazy, final Lazy<DeviceInformation> lazy2, final Lazy<IdentityService> lazy3, final Lazy<AccountUpgradeService> lazy4, final Lazy<NetworkService> lazy5, final Lazy<PersistentStorage.Factory> lazy6, final Lazy<LatencyReportingDelegate> lazy7, final Lazy<MetricsService> lazy8, final Lazy<RoutingService> lazy9, final Lazy<DefaultApplicationLifecycleService> lazy10) {
        Component component = new Component();
        component.provide(Context.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$VoiceModule$D12wtvE5gXs5g5Bz_QakYN6Pcug
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return VoiceModule.lambda$provideVoiceService$0(Lazy.this);
            }
        }).register();
        component.provide(DeviceInformation.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$VoiceModule$L5hU0dDUrFdCrSMGzMzWVvypDuY
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return VoiceModule.lambda$provideVoiceService$1(Lazy.this);
            }
        }).register();
        component.provide(IdentityService.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$VoiceModule$kqbPYuUW0WGzG6GLZIpWSWls1QY
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return VoiceModule.lambda$provideVoiceService$2(Lazy.this);
            }
        }).register();
        component.provide(AccountUpgradeService.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$VoiceModule$kjeEkz_DtPUQGI7etwyx9MB-dWk
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return VoiceModule.lambda$provideVoiceService$3(Lazy.this);
            }
        }).register();
        component.provide(NetworkService.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$VoiceModule$Di-2mzrFeReVY92ouFie23vEW4M
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return VoiceModule.lambda$provideVoiceService$4(Lazy.this);
            }
        }).register();
        component.provide(PersistentStorage.Factory.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$VoiceModule$zeMI8AE_tuXbY5_fIEvWUhB4Dzg
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return VoiceModule.lambda$provideVoiceService$5(Lazy.this);
            }
        }).register();
        component.provide(LatencyReportingDelegate.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$VoiceModule$YNdVxLR4zBEgUa71b9SIEWu0DKA
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return VoiceModule.lambda$provideVoiceService$6(Lazy.this);
            }
        }).register();
        component.provide(MetricsService.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$VoiceModule$Y5vqgglYp49af2jPOl84O2_LPr8
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return VoiceModule.lambda$provideVoiceService$7(Lazy.this);
            }
        }).register();
        component.provide(ApplicationLifecycleService.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$VoiceModule$EQfOCtNefLFjHKPd_7wNbKkppxU
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return VoiceModule.lambda$provideVoiceService$8(Lazy.this);
            }
        }).register();
        component.provide(RoutingService.class, new Component.Provider() { // from class: com.amazon.dee.app.dependencies.-$$Lambda$VoiceModule$pDwKi89kSKGMjdwfKjrwn6aVPVQ
            @Override // com.amazon.regulator.Component.Provider
            public final Object get() {
                return VoiceModule.lambda$provideVoiceService$9(Lazy.this);
            }
        }).register();
        return VoiceServiceFactory.create(component);
    }
}
