package com.amazon.alexa.biloba.view.account;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class MembershipViewModel_MembersInjector implements MembersInjector<MembershipViewModel> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<CrashMetadata> crashMetadataProvider;
    private final Provider<CrashReporter> crashReporterProvider;

    public MembershipViewModel_MembersInjector(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<CareActorsStore> provider4) {
        this.crashReporterProvider = provider;
        this.crashMetadataProvider = provider2;
        this.bilobaMetricsServiceProvider = provider3;
        this.careActorsStoreProvider = provider4;
    }

    public static MembersInjector<MembershipViewModel> create(Provider<CrashReporter> provider, Provider<CrashMetadata> provider2, Provider<BilobaMetricsService> provider3, Provider<CareActorsStore> provider4) {
        return new MembershipViewModel_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectBilobaMetricsService(MembershipViewModel membershipViewModel, Lazy<BilobaMetricsService> lazy) {
        membershipViewModel.bilobaMetricsService = lazy;
    }

    public static void injectCareActorsStore(MembershipViewModel membershipViewModel, Lazy<CareActorsStore> lazy) {
        membershipViewModel.careActorsStore = lazy;
    }

    public static void injectCrashMetadata(MembershipViewModel membershipViewModel, Lazy<CrashMetadata> lazy) {
        membershipViewModel.crashMetadata = lazy;
    }

    public static void injectCrashReporter(MembershipViewModel membershipViewModel, Lazy<CrashReporter> lazy) {
        membershipViewModel.crashReporter = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MembershipViewModel membershipViewModel) {
        injectCrashReporter(membershipViewModel, DoubleCheck.lazy(this.crashReporterProvider));
        injectCrashMetadata(membershipViewModel, DoubleCheck.lazy(this.crashMetadataProvider));
        injectBilobaMetricsService(membershipViewModel, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectCareActorsStore(membershipViewModel, DoubleCheck.lazy(this.careActorsStoreProvider));
    }
}
