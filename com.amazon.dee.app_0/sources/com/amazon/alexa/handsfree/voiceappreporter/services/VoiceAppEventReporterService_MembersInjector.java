package com.amazon.alexa.handsfree.voiceappreporter.services;

import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.CrashReportRecorder;
import com.amazon.alexa.handsfree.voiceappreporter.database.VoiceAppEventReporterDatabaseHelper;
import com.amazon.alexa.handsfree.voiceappreporter.schedulers.VoiceAppEventReporterServiceScheduler;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class VoiceAppEventReporterService_MembersInjector implements MembersInjector<VoiceAppEventReporterService> {
    private final Provider<AMPDInformationProvider> mAMPDInformationProvider;
    private final Provider<CrashReportRecorder> mCrashReportRecorderProvider;
    private final Provider<Initializer> mInitializerProvider;
    private final Provider<MetricsBuilderProvider> mMetricsBuilderProvider;
    private final Provider<VoiceAppEventReporterDatabaseHelper> mVoiceAppEventReporterDatabaseHelperProvider;
    private final Provider<VoiceAppEventReporterServiceScheduler> mVoiceAppEventReporterServiceSchedulerProvider;

    public VoiceAppEventReporterService_MembersInjector(Provider<Initializer> provider, Provider<CrashReportRecorder> provider2, Provider<MetricsBuilderProvider> provider3, Provider<VoiceAppEventReporterDatabaseHelper> provider4, Provider<VoiceAppEventReporterServiceScheduler> provider5, Provider<AMPDInformationProvider> provider6) {
        this.mInitializerProvider = provider;
        this.mCrashReportRecorderProvider = provider2;
        this.mMetricsBuilderProvider = provider3;
        this.mVoiceAppEventReporterDatabaseHelperProvider = provider4;
        this.mVoiceAppEventReporterServiceSchedulerProvider = provider5;
        this.mAMPDInformationProvider = provider6;
    }

    public static MembersInjector<VoiceAppEventReporterService> create(Provider<Initializer> provider, Provider<CrashReportRecorder> provider2, Provider<MetricsBuilderProvider> provider3, Provider<VoiceAppEventReporterDatabaseHelper> provider4, Provider<VoiceAppEventReporterServiceScheduler> provider5, Provider<AMPDInformationProvider> provider6) {
        return new VoiceAppEventReporterService_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectMAMPDInformationProvider(VoiceAppEventReporterService voiceAppEventReporterService, AMPDInformationProvider aMPDInformationProvider) {
        voiceAppEventReporterService.mAMPDInformationProvider = aMPDInformationProvider;
    }

    public static void injectMCrashReportRecorder(VoiceAppEventReporterService voiceAppEventReporterService, CrashReportRecorder crashReportRecorder) {
        voiceAppEventReporterService.mCrashReportRecorder = crashReportRecorder;
    }

    public static void injectMInitializer(VoiceAppEventReporterService voiceAppEventReporterService, Initializer initializer) {
        voiceAppEventReporterService.mInitializer = initializer;
    }

    public static void injectMMetricsBuilderProvider(VoiceAppEventReporterService voiceAppEventReporterService, MetricsBuilderProvider metricsBuilderProvider) {
        voiceAppEventReporterService.mMetricsBuilderProvider = metricsBuilderProvider;
    }

    public static void injectMVoiceAppEventReporterDatabaseHelper(VoiceAppEventReporterService voiceAppEventReporterService, VoiceAppEventReporterDatabaseHelper voiceAppEventReporterDatabaseHelper) {
        voiceAppEventReporterService.mVoiceAppEventReporterDatabaseHelper = voiceAppEventReporterDatabaseHelper;
    }

    public static void injectMVoiceAppEventReporterServiceScheduler(VoiceAppEventReporterService voiceAppEventReporterService, VoiceAppEventReporterServiceScheduler voiceAppEventReporterServiceScheduler) {
        voiceAppEventReporterService.mVoiceAppEventReporterServiceScheduler = voiceAppEventReporterServiceScheduler;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(VoiceAppEventReporterService voiceAppEventReporterService) {
        injectMInitializer(voiceAppEventReporterService, this.mInitializerProvider.mo10268get());
        injectMCrashReportRecorder(voiceAppEventReporterService, this.mCrashReportRecorderProvider.mo10268get());
        injectMMetricsBuilderProvider(voiceAppEventReporterService, this.mMetricsBuilderProvider.mo10268get());
        injectMVoiceAppEventReporterDatabaseHelper(voiceAppEventReporterService, this.mVoiceAppEventReporterDatabaseHelperProvider.mo10268get());
        injectMVoiceAppEventReporterServiceScheduler(voiceAppEventReporterService, this.mVoiceAppEventReporterServiceSchedulerProvider.mo10268get());
        injectMAMPDInformationProvider(voiceAppEventReporterService, this.mAMPDInformationProvider.mo10268get());
    }
}
