package com.amazon.deecomms.common.ui;

import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.core.CapabilitiesManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DiagnosticScreen_MembersInjector implements MembersInjector<DiagnosticScreen> {
    private final Provider<ApplicationManager> applicationManagerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<CommsInternal> commsInternalProvider;
    private final Provider<DeviceCallingService> deviceCallingServiceProvider;

    public DiagnosticScreen_MembersInjector(Provider<DeviceCallingService> provider, Provider<ApplicationManager> provider2, Provider<CommsInternal> provider3, Provider<CommsIdentityManager> provider4, Provider<CapabilitiesManager> provider5) {
        this.deviceCallingServiceProvider = provider;
        this.applicationManagerProvider = provider2;
        this.commsInternalProvider = provider3;
        this.commsIdentityManagerProvider = provider4;
        this.capabilitiesManagerProvider = provider5;
    }

    public static MembersInjector<DiagnosticScreen> create(Provider<DeviceCallingService> provider, Provider<ApplicationManager> provider2, Provider<CommsInternal> provider3, Provider<CommsIdentityManager> provider4, Provider<CapabilitiesManager> provider5) {
        return new DiagnosticScreen_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectApplicationManager(DiagnosticScreen diagnosticScreen, ApplicationManager applicationManager) {
        diagnosticScreen.applicationManager = applicationManager;
    }

    public static void injectCapabilitiesManager(DiagnosticScreen diagnosticScreen, CapabilitiesManager capabilitiesManager) {
        diagnosticScreen.capabilitiesManager = capabilitiesManager;
    }

    public static void injectCommsIdentityManager(DiagnosticScreen diagnosticScreen, CommsIdentityManager commsIdentityManager) {
        diagnosticScreen.commsIdentityManager = commsIdentityManager;
    }

    public static void injectCommsInternal(DiagnosticScreen diagnosticScreen, CommsInternal commsInternal) {
        diagnosticScreen.commsInternal = commsInternal;
    }

    public static void injectDeviceCallingService(DiagnosticScreen diagnosticScreen, DeviceCallingService deviceCallingService) {
        diagnosticScreen.deviceCallingService = deviceCallingService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DiagnosticScreen diagnosticScreen) {
        diagnosticScreen.deviceCallingService = this.deviceCallingServiceProvider.mo10268get();
        diagnosticScreen.applicationManager = this.applicationManagerProvider.mo10268get();
        diagnosticScreen.commsInternal = this.commsInternalProvider.mo10268get();
        diagnosticScreen.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        diagnosticScreen.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
    }
}
