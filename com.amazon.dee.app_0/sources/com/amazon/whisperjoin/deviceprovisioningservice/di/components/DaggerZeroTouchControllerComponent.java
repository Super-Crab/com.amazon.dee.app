package com.amazon.whisperjoin.deviceprovisioningservice.di.components;

import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ZeroTouchProvisioningModule;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ZeroTouchProvisioningModule_ProvidesFireOSUtilFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ZeroTouchProvisioningModule_ProvidesZeroTouchProvisioningSettingsFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowControllerFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowFactoryFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowFailureUpdateHandlerFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowMetricsReporterFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.di.modules.ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowRouterFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.FFSProvisioningServiceMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.deferred.DeferredDiscoveryHandler;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.WJDeviceSetupModeSupportedPredicate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowController;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowFailureUpdateHandler;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowMetricsReporter;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DaggerZeroTouchControllerComponent implements ZeroTouchControllerComponent {
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getClock getClockProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeferredDiscoveryHandler providesDeferredDiscoveryHandlerProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeviceDiscoveryStream providesDeviceDiscoveryStreamProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDiscoverySettings providesDiscoverySettingsProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesFFSProvisioningServiceMetricsRecorder providesFFSProvisioningServiceMetricsRecorderProvider;
    private ZeroTouchProvisioningModule_ProvidesFireOSUtilFactory providesFireOSUtilProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesIsDebugEnabled providesIsDebugEnabledProvider;
    private com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWJDeviceSetupModeSupportedPredicate providesWJDeviceSetupModeSupportedPredicateProvider;
    private ZeroTouchProvisioningModule_ProvidesZeroTouchProvisioningSettingsFactory providesZeroTouchProvisioningSettingsProvider;
    private Provider<ZeroTouchWorkflowController> providesZeroTouchWorkflowControllerProvider;
    private ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowFactoryFactory providesZeroTouchWorkflowFactoryProvider;
    private Provider<ZeroTouchWorkflowFailureUpdateHandler> providesZeroTouchWorkflowFailureUpdateHandlerProvider;
    private Provider<ZeroTouchWorkflowMetricsReporter> providesZeroTouchWorkflowMetricsReporterProvider;
    private ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowRouterFactory providesZeroTouchWorkflowRouterProvider;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private ProvisioningComponent provisioningComponent;
        private ZeroTouchProvisioningModule zeroTouchProvisioningModule;

        public ZeroTouchControllerComponent build() {
            Preconditions.checkBuilderRequirement(this.zeroTouchProvisioningModule, ZeroTouchProvisioningModule.class);
            Preconditions.checkBuilderRequirement(this.provisioningComponent, ProvisioningComponent.class);
            return new DaggerZeroTouchControllerComponent(this);
        }

        public Builder provisioningComponent(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = (ProvisioningComponent) Preconditions.checkNotNull(provisioningComponent);
            return this;
        }

        public Builder zeroTouchProvisioningModule(ZeroTouchProvisioningModule zeroTouchProvisioningModule) {
            this.zeroTouchProvisioningModule = (ZeroTouchProvisioningModule) Preconditions.checkNotNull(zeroTouchProvisioningModule);
            return this;
        }

        private Builder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getClock implements Provider<Clock> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getClock(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public Clock mo10268get() {
            return (Clock) Preconditions.checkNotNull(this.provisioningComponent.getClock(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeferredDiscoveryHandler implements Provider<DeferredDiscoveryHandler> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeferredDiscoveryHandler(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public DeferredDiscoveryHandler mo10268get() {
            return (DeferredDiscoveryHandler) Preconditions.checkNotNull(this.provisioningComponent.providesDeferredDiscoveryHandler(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeviceDiscoveryStream implements Provider<DeviceDiscoveryStream> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeviceDiscoveryStream(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public DeviceDiscoveryStream mo10268get() {
            return (DeviceDiscoveryStream) Preconditions.checkNotNull(this.provisioningComponent.providesDeviceDiscoveryStream(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDiscoverySettings implements Provider<DiscoverySettings> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDiscoverySettings(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public DiscoverySettings mo10268get() {
            return (DiscoverySettings) Preconditions.checkNotNull(this.provisioningComponent.providesDiscoverySettings(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesFFSProvisioningServiceMetricsRecorder implements Provider<FFSProvisioningServiceMetricsRecorder> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesFFSProvisioningServiceMetricsRecorder(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public FFSProvisioningServiceMetricsRecorder mo10268get() {
            return (FFSProvisioningServiceMetricsRecorder) Preconditions.checkNotNull(this.provisioningComponent.providesFFSProvisioningServiceMetricsRecorder(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesIsDebugEnabled implements Provider<Boolean> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesIsDebugEnabled(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public Boolean mo10268get() {
            return (Boolean) Preconditions.checkNotNull(this.provisioningComponent.providesIsDebugEnabled(), "Cannot return null from a non-@Nullable component method");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWJDeviceSetupModeSupportedPredicate implements Provider<WJDeviceSetupModeSupportedPredicate> {
        private final ProvisioningComponent provisioningComponent;

        com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWJDeviceSetupModeSupportedPredicate(ProvisioningComponent provisioningComponent) {
            this.provisioningComponent = provisioningComponent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        /* renamed from: get */
        public WJDeviceSetupModeSupportedPredicate mo10268get() {
            return (WJDeviceSetupModeSupportedPredicate) Preconditions.checkNotNull(this.provisioningComponent.providesWJDeviceSetupModeSupportedPredicate(), "Cannot return null from a non-@Nullable component method");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.providesZeroTouchWorkflowFactoryProvider = ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowFactoryFactory.create(builder.zeroTouchProvisioningModule);
        this.providesZeroTouchProvisioningSettingsProvider = ZeroTouchProvisioningModule_ProvidesZeroTouchProvisioningSettingsFactory.create(builder.zeroTouchProvisioningModule);
        this.providesWJDeviceSetupModeSupportedPredicateProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesWJDeviceSetupModeSupportedPredicate(builder.provisioningComponent);
        this.providesZeroTouchWorkflowRouterProvider = ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowRouterFactory.create(builder.zeroTouchProvisioningModule, this.providesZeroTouchWorkflowFactoryProvider, this.providesZeroTouchProvisioningSettingsProvider, this.providesWJDeviceSetupModeSupportedPredicateProvider);
        this.providesDeviceDiscoveryStreamProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeviceDiscoveryStream(builder.provisioningComponent);
        this.providesFFSProvisioningServiceMetricsRecorderProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesFFSProvisioningServiceMetricsRecorder(builder.provisioningComponent);
        this.providesZeroTouchWorkflowMetricsReporterProvider = DoubleCheck.provider(ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowMetricsReporterFactory.create(builder.zeroTouchProvisioningModule, this.providesFFSProvisioningServiceMetricsRecorderProvider));
        this.providesDiscoverySettingsProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDiscoverySettings(builder.provisioningComponent);
        this.providesFireOSUtilProvider = ZeroTouchProvisioningModule_ProvidesFireOSUtilFactory.create(builder.zeroTouchProvisioningModule);
        this.getClockProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_getClock(builder.provisioningComponent);
        this.providesIsDebugEnabledProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesIsDebugEnabled(builder.provisioningComponent);
        this.providesZeroTouchWorkflowFailureUpdateHandlerProvider = DoubleCheck.provider(ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowFailureUpdateHandlerFactory.create(builder.zeroTouchProvisioningModule, this.providesFireOSUtilProvider, this.getClockProvider, this.providesIsDebugEnabledProvider, this.providesZeroTouchProvisioningSettingsProvider));
        this.providesDeferredDiscoveryHandlerProvider = new com_amazon_whisperjoin_deviceprovisioningservice_di_components_ProvisioningComponent_providesDeferredDiscoveryHandler(builder.provisioningComponent);
        this.providesZeroTouchWorkflowControllerProvider = DoubleCheck.provider(ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowControllerFactory.create(builder.zeroTouchProvisioningModule, this.providesZeroTouchWorkflowRouterProvider, this.providesDeviceDiscoveryStreamProvider, this.providesZeroTouchWorkflowMetricsReporterProvider, this.providesFFSProvisioningServiceMetricsRecorderProvider, this.providesDiscoverySettingsProvider, this.providesZeroTouchWorkflowFailureUpdateHandlerProvider, this.providesDeferredDiscoveryHandlerProvider, this.providesZeroTouchProvisioningSettingsProvider));
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.di.components.ZeroTouchControllerComponent
    public ZeroTouchWorkflowController getZeroTouchWorkflowController() {
        return this.providesZeroTouchWorkflowControllerProvider.mo10268get();
    }

    private DaggerZeroTouchControllerComponent(Builder builder) {
        initialize(builder);
    }
}
