package com.amazon.alexa.presence;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.presence.eventbus.EventBusHelper;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceLifecycleManager_MembersInjector implements MembersInjector<PresenceLifecycleManager> {
    private final Provider<PresenceController> controllerProvider;
    private final Provider<DeviceInformation> deviceInformationProvider;
    private final Provider<EventBusHelper> eventBusHelperProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<PresenceSubComponents> mPresenceSubComponentsProvider;
    private final Provider<MetricsServiceV2> metricsServiceV2Provider;

    public PresenceLifecycleManager_MembersInjector(Provider<EventBusHelper> provider, Provider<IdentityService> provider2, Provider<PresenceSubComponents> provider3, Provider<MetricsServiceV2> provider4, Provider<DeviceInformation> provider5, Provider<PresenceController> provider6, Provider<EventBus> provider7) {
        this.eventBusHelperProvider = provider;
        this.identityServiceProvider = provider2;
        this.mPresenceSubComponentsProvider = provider3;
        this.metricsServiceV2Provider = provider4;
        this.deviceInformationProvider = provider5;
        this.controllerProvider = provider6;
        this.eventBusProvider = provider7;
    }

    public static MembersInjector<PresenceLifecycleManager> create(Provider<EventBusHelper> provider, Provider<IdentityService> provider2, Provider<PresenceSubComponents> provider3, Provider<MetricsServiceV2> provider4, Provider<DeviceInformation> provider5, Provider<PresenceController> provider6, Provider<EventBus> provider7) {
        return new PresenceLifecycleManager_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static void injectController(PresenceLifecycleManager presenceLifecycleManager, Lazy<PresenceController> lazy) {
        presenceLifecycleManager.controller = lazy;
    }

    public static void injectDeviceInformation(PresenceLifecycleManager presenceLifecycleManager, DeviceInformation deviceInformation) {
        presenceLifecycleManager.deviceInformation = deviceInformation;
    }

    public static void injectEventBus(PresenceLifecycleManager presenceLifecycleManager, EventBus eventBus) {
        presenceLifecycleManager.eventBus = eventBus;
    }

    public static void injectEventBusHelper(PresenceLifecycleManager presenceLifecycleManager, Lazy<EventBusHelper> lazy) {
        presenceLifecycleManager.eventBusHelper = lazy;
    }

    public static void injectIdentityService(PresenceLifecycleManager presenceLifecycleManager, IdentityService identityService) {
        presenceLifecycleManager.identityService = identityService;
    }

    public static void injectMPresenceSubComponents(PresenceLifecycleManager presenceLifecycleManager, Lazy<PresenceSubComponents> lazy) {
        presenceLifecycleManager.mPresenceSubComponents = lazy;
    }

    public static void injectMetricsServiceV2(PresenceLifecycleManager presenceLifecycleManager, MetricsServiceV2 metricsServiceV2) {
        presenceLifecycleManager.metricsServiceV2 = metricsServiceV2;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(PresenceLifecycleManager presenceLifecycleManager) {
        injectEventBusHelper(presenceLifecycleManager, DoubleCheck.lazy(this.eventBusHelperProvider));
        injectIdentityService(presenceLifecycleManager, this.identityServiceProvider.mo10268get());
        injectMPresenceSubComponents(presenceLifecycleManager, DoubleCheck.lazy(this.mPresenceSubComponentsProvider));
        injectMetricsServiceV2(presenceLifecycleManager, this.metricsServiceV2Provider.mo10268get());
        injectDeviceInformation(presenceLifecycleManager, this.deviceInformationProvider.mo10268get());
        injectController(presenceLifecycleManager, DoubleCheck.lazy(this.controllerProvider));
        injectEventBus(presenceLifecycleManager, this.eventBusProvider.mo10268get());
    }
}
