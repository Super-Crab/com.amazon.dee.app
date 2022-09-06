package com.amazon.alexa.mode.dependencies;

import com.amazon.alexa.mode.drive.HomeChannelInteractor;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.mode.service.DefaultModeServiceV2;
import com.amazon.alexa.mode.service.DefaultModeServiceV2_MembersInjector;
import com.amazon.alexa.mode.userstudy.ModeStatusLog;
import com.amazon.alexa.mode.util.AutomotiveAccessoryConnectivityObserver;
import com.amazon.alexa.mode.util.AutomotiveDeviceRegistry;
import com.amazon.alexa.mode.util.CatapultTtsDeviceMonitor;
import com.amazon.alexa.mode.util.DriveModePreferences;
import com.amazon.alexa.mode.util.NotificationHelper;
import com.amazon.alexa.mode.util.PrefsDialogHelper;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DaggerModeComponent implements ModeComponent {
    private Provider<AutomotiveAccessoryConnectivityObserver> provideAutomotiveAccessoryConnectivityObserverProvider;
    private Provider<AutomotiveDeviceRegistry> provideAutomotiveDeviceRegistryProvider;
    private Provider<DriveModeMetrics> provideDriveModeMetricsProvider;
    private Provider<DriveModePreferences> provideDriveModePreferencesProvider;
    private Provider<HomeChannelInteractor> provideHomeChannelInteractorProvider;
    private Provider<ModeStatusLog> provideModeStatusLogProvider;
    private Provider<CatapultTtsDeviceMonitor> provideMuffinOobeMonitorProvider;
    private Provider<NotificationHelper> provideNotificationHelperProvider;
    private Provider<PrefsDialogHelper> providePrefsDialogHelperProvider;

    /* loaded from: classes9.dex */
    public static final class Builder {
        private ModeModule modeModule;

        public ModeComponent build() {
            Preconditions.checkBuilderRequirement(this.modeModule, ModeModule.class);
            return new DaggerModeComponent(this);
        }

        public Builder modeModule(ModeModule modeModule) {
            this.modeModule = (ModeModule) Preconditions.checkNotNull(modeModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideDriveModeMetricsProvider = DoubleCheck.provider(ModeModule_ProvideDriveModeMetricsFactory.create(builder.modeModule));
        this.provideModeStatusLogProvider = DoubleCheck.provider(ModeModule_ProvideModeStatusLogFactory.create(builder.modeModule));
        this.provideNotificationHelperProvider = DoubleCheck.provider(ModeModule_ProvideNotificationHelperFactory.create(builder.modeModule, this.provideDriveModeMetricsProvider));
        this.provideHomeChannelInteractorProvider = DoubleCheck.provider(ModeModule_ProvideHomeChannelInteractorFactory.create(builder.modeModule));
        this.provideAutomotiveDeviceRegistryProvider = DoubleCheck.provider(ModeModule_ProvideAutomotiveDeviceRegistryFactory.create(builder.modeModule));
        this.provideAutomotiveAccessoryConnectivityObserverProvider = DoubleCheck.provider(ModeModule_ProvideAutomotiveAccessoryConnectivityObserverFactory.create(builder.modeModule));
        this.providePrefsDialogHelperProvider = DoubleCheck.provider(ModeModule_ProvidePrefsDialogHelperFactory.create(builder.modeModule));
        this.provideDriveModePreferencesProvider = DoubleCheck.provider(ModeModule_ProvideDriveModePreferencesFactory.create(builder.modeModule, this.providePrefsDialogHelperProvider));
        this.provideMuffinOobeMonitorProvider = DoubleCheck.provider(ModeModule_ProvideMuffinOobeMonitorFactory.create(builder.modeModule));
    }

    private DefaultModeServiceV2 injectDefaultModeServiceV2(DefaultModeServiceV2 defaultModeServiceV2) {
        DefaultModeServiceV2_MembersInjector.injectMDriveModeMetricsLazy(defaultModeServiceV2, DoubleCheck.lazy(this.provideDriveModeMetricsProvider));
        DefaultModeServiceV2_MembersInjector.injectMModeStatusLog(defaultModeServiceV2, this.provideModeStatusLogProvider.mo10268get());
        DefaultModeServiceV2_MembersInjector.injectMNotificationHelper(defaultModeServiceV2, DoubleCheck.lazy(this.provideNotificationHelperProvider));
        DefaultModeServiceV2_MembersInjector.injectMHomeChannelInteractor(defaultModeServiceV2, this.provideHomeChannelInteractorProvider.mo10268get());
        DefaultModeServiceV2_MembersInjector.injectMAutomotiveDeviceRegistry(defaultModeServiceV2, this.provideAutomotiveDeviceRegistryProvider.mo10268get());
        DefaultModeServiceV2_MembersInjector.injectMAutomotiveDeviceObserver(defaultModeServiceV2, this.provideAutomotiveAccessoryConnectivityObserverProvider.mo10268get());
        DefaultModeServiceV2_MembersInjector.injectMPrefsDialogHelper(defaultModeServiceV2, this.providePrefsDialogHelperProvider.mo10268get());
        DefaultModeServiceV2_MembersInjector.injectMDriveModePreferences(defaultModeServiceV2, this.provideDriveModePreferencesProvider.mo10268get());
        DefaultModeServiceV2_MembersInjector.injectMCatapultTtsDeviceMonitor(defaultModeServiceV2, this.provideMuffinOobeMonitorProvider.mo10268get());
        return defaultModeServiceV2;
    }

    @Override // com.amazon.alexa.mode.dependencies.ModeComponent
    public void inject(DefaultModeServiceV2 defaultModeServiceV2) {
        injectDefaultModeServiceV2(defaultModeServiceV2);
    }

    private DaggerModeComponent(Builder builder) {
        initialize(builder);
    }
}
