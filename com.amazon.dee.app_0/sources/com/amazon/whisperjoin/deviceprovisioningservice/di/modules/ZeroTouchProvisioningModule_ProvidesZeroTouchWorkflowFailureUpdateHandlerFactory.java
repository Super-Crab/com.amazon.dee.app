package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowFailureUpdateHandler;
import com.amazon.whisperjoin.util.FireOSUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowFailureUpdateHandlerFactory implements Factory<ZeroTouchWorkflowFailureUpdateHandler> {
    private final Provider<Clock> clockProvider;
    private final Provider<Boolean> debugModeProvider;
    private final Provider<FFSArcusSettings> ffsArcusSettingsProvider;
    private final Provider<FireOSUtil> fireOSUtilProvider;
    private final ZeroTouchProvisioningModule module;

    public ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowFailureUpdateHandlerFactory(ZeroTouchProvisioningModule zeroTouchProvisioningModule, Provider<FireOSUtil> provider, Provider<Clock> provider2, Provider<Boolean> provider3, Provider<FFSArcusSettings> provider4) {
        this.module = zeroTouchProvisioningModule;
        this.fireOSUtilProvider = provider;
        this.clockProvider = provider2;
        this.debugModeProvider = provider3;
        this.ffsArcusSettingsProvider = provider4;
    }

    public static ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowFailureUpdateHandlerFactory create(ZeroTouchProvisioningModule zeroTouchProvisioningModule, Provider<FireOSUtil> provider, Provider<Clock> provider2, Provider<Boolean> provider3, Provider<FFSArcusSettings> provider4) {
        return new ZeroTouchProvisioningModule_ProvidesZeroTouchWorkflowFailureUpdateHandlerFactory(zeroTouchProvisioningModule, provider, provider2, provider3, provider4);
    }

    public static ZeroTouchWorkflowFailureUpdateHandler provideInstance(ZeroTouchProvisioningModule zeroTouchProvisioningModule, Provider<FireOSUtil> provider, Provider<Clock> provider2, Provider<Boolean> provider3, Provider<FFSArcusSettings> provider4) {
        return proxyProvidesZeroTouchWorkflowFailureUpdateHandler(zeroTouchProvisioningModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    public static ZeroTouchWorkflowFailureUpdateHandler proxyProvidesZeroTouchWorkflowFailureUpdateHandler(ZeroTouchProvisioningModule zeroTouchProvisioningModule, FireOSUtil fireOSUtil, Clock clock, Boolean bool, FFSArcusSettings fFSArcusSettings) {
        return (ZeroTouchWorkflowFailureUpdateHandler) Preconditions.checkNotNull(zeroTouchProvisioningModule.providesZeroTouchWorkflowFailureUpdateHandler(fireOSUtil, clock, bool, fFSArcusSettings), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ZeroTouchWorkflowFailureUpdateHandler mo10268get() {
        return provideInstance(this.module, this.fireOSUtilProvider, this.clockProvider, this.debugModeProvider, this.ffsArcusSettingsProvider);
    }
}
