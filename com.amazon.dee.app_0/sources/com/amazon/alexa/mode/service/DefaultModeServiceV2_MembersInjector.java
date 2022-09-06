package com.amazon.alexa.mode.service;

import com.amazon.alexa.mode.drive.HomeChannelInteractor;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.mode.userstudy.ModeStatusLog;
import com.amazon.alexa.mode.util.AutomotiveAccessoryConnectivityObserver;
import com.amazon.alexa.mode.util.AutomotiveDeviceRegistry;
import com.amazon.alexa.mode.util.CatapultTtsDeviceMonitor;
import com.amazon.alexa.mode.util.DriveModePreferences;
import com.amazon.alexa.mode.util.NotificationHelper;
import com.amazon.alexa.mode.util.PrefsDialogHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class DefaultModeServiceV2_MembersInjector implements MembersInjector<DefaultModeServiceV2> {
    private final Provider<AutomotiveAccessoryConnectivityObserver> mAutomotiveDeviceObserverProvider;
    private final Provider<AutomotiveDeviceRegistry> mAutomotiveDeviceRegistryProvider;
    private final Provider<CatapultTtsDeviceMonitor> mCatapultTtsDeviceMonitorProvider;
    private final Provider<DriveModeMetrics> mDriveModeMetricsLazyProvider;
    private final Provider<DriveModePreferences> mDriveModePreferencesProvider;
    private final Provider<HomeChannelInteractor> mHomeChannelInteractorProvider;
    private final Provider<ModeStatusLog> mModeStatusLogProvider;
    private final Provider<NotificationHelper> mNotificationHelperProvider;
    private final Provider<PrefsDialogHelper> mPrefsDialogHelperProvider;

    public DefaultModeServiceV2_MembersInjector(Provider<DriveModeMetrics> provider, Provider<ModeStatusLog> provider2, Provider<NotificationHelper> provider3, Provider<HomeChannelInteractor> provider4, Provider<AutomotiveDeviceRegistry> provider5, Provider<AutomotiveAccessoryConnectivityObserver> provider6, Provider<PrefsDialogHelper> provider7, Provider<DriveModePreferences> provider8, Provider<CatapultTtsDeviceMonitor> provider9) {
        this.mDriveModeMetricsLazyProvider = provider;
        this.mModeStatusLogProvider = provider2;
        this.mNotificationHelperProvider = provider3;
        this.mHomeChannelInteractorProvider = provider4;
        this.mAutomotiveDeviceRegistryProvider = provider5;
        this.mAutomotiveDeviceObserverProvider = provider6;
        this.mPrefsDialogHelperProvider = provider7;
        this.mDriveModePreferencesProvider = provider8;
        this.mCatapultTtsDeviceMonitorProvider = provider9;
    }

    public static MembersInjector<DefaultModeServiceV2> create(Provider<DriveModeMetrics> provider, Provider<ModeStatusLog> provider2, Provider<NotificationHelper> provider3, Provider<HomeChannelInteractor> provider4, Provider<AutomotiveDeviceRegistry> provider5, Provider<AutomotiveAccessoryConnectivityObserver> provider6, Provider<PrefsDialogHelper> provider7, Provider<DriveModePreferences> provider8, Provider<CatapultTtsDeviceMonitor> provider9) {
        return new DefaultModeServiceV2_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static void injectMAutomotiveDeviceObserver(DefaultModeServiceV2 defaultModeServiceV2, AutomotiveAccessoryConnectivityObserver automotiveAccessoryConnectivityObserver) {
        defaultModeServiceV2.mAutomotiveDeviceObserver = automotiveAccessoryConnectivityObserver;
    }

    public static void injectMAutomotiveDeviceRegistry(DefaultModeServiceV2 defaultModeServiceV2, AutomotiveDeviceRegistry automotiveDeviceRegistry) {
        defaultModeServiceV2.mAutomotiveDeviceRegistry = automotiveDeviceRegistry;
    }

    public static void injectMCatapultTtsDeviceMonitor(DefaultModeServiceV2 defaultModeServiceV2, CatapultTtsDeviceMonitor catapultTtsDeviceMonitor) {
        defaultModeServiceV2.mCatapultTtsDeviceMonitor = catapultTtsDeviceMonitor;
    }

    public static void injectMDriveModeMetricsLazy(DefaultModeServiceV2 defaultModeServiceV2, Lazy<DriveModeMetrics> lazy) {
        defaultModeServiceV2.mDriveModeMetricsLazy = lazy;
    }

    public static void injectMDriveModePreferences(DefaultModeServiceV2 defaultModeServiceV2, DriveModePreferences driveModePreferences) {
        defaultModeServiceV2.mDriveModePreferences = driveModePreferences;
    }

    public static void injectMHomeChannelInteractor(DefaultModeServiceV2 defaultModeServiceV2, HomeChannelInteractor homeChannelInteractor) {
        defaultModeServiceV2.mHomeChannelInteractor = homeChannelInteractor;
    }

    public static void injectMModeStatusLog(DefaultModeServiceV2 defaultModeServiceV2, ModeStatusLog modeStatusLog) {
        defaultModeServiceV2.mModeStatusLog = modeStatusLog;
    }

    public static void injectMNotificationHelper(DefaultModeServiceV2 defaultModeServiceV2, Lazy<NotificationHelper> lazy) {
        defaultModeServiceV2.mNotificationHelper = lazy;
    }

    public static void injectMPrefsDialogHelper(DefaultModeServiceV2 defaultModeServiceV2, PrefsDialogHelper prefsDialogHelper) {
        defaultModeServiceV2.mPrefsDialogHelper = prefsDialogHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DefaultModeServiceV2 defaultModeServiceV2) {
        injectMDriveModeMetricsLazy(defaultModeServiceV2, DoubleCheck.lazy(this.mDriveModeMetricsLazyProvider));
        injectMModeStatusLog(defaultModeServiceV2, this.mModeStatusLogProvider.mo10268get());
        injectMNotificationHelper(defaultModeServiceV2, DoubleCheck.lazy(this.mNotificationHelperProvider));
        injectMHomeChannelInteractor(defaultModeServiceV2, this.mHomeChannelInteractorProvider.mo10268get());
        injectMAutomotiveDeviceRegistry(defaultModeServiceV2, this.mAutomotiveDeviceRegistryProvider.mo10268get());
        injectMAutomotiveDeviceObserver(defaultModeServiceV2, this.mAutomotiveDeviceObserverProvider.mo10268get());
        injectMPrefsDialogHelper(defaultModeServiceV2, this.mPrefsDialogHelperProvider.mo10268get());
        injectMDriveModePreferences(defaultModeServiceV2, this.mDriveModePreferencesProvider.mo10268get());
        injectMCatapultTtsDeviceMonitor(defaultModeServiceV2, this.mCatapultTtsDeviceMonitorProvider.mo10268get());
    }
}
