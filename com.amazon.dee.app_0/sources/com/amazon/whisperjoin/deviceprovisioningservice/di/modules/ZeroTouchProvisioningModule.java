package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.arcus.data.FFSArcusSettings;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.di.scopes.ZeroTouchDependencyInjectorScope;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.FFSProvisioningServiceMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.deferred.DeferredDiscoveryHandler;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.WJDeviceSetupModeSupportedPredicate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowController;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowFailureUpdateHandler;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowMetricsReporter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch.ZeroTouchWorkflowRouter;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.Iso8601TimeConverter;
import com.amazon.whisperjoin.util.FireOSUtil;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes13.dex */
public class ZeroTouchProvisioningModule {
    private final Context mContext;
    private final FFSArcusSettings mFFSArcusSettings;
    private final ZeroTouchWorkflowFactory mWorkflowFactory;

    public ZeroTouchProvisioningModule(Context context, ZeroTouchWorkflowFactory zeroTouchWorkflowFactory, FFSArcusSettings fFSArcusSettings) {
        this.mContext = context;
        this.mWorkflowFactory = zeroTouchWorkflowFactory;
        this.mFFSArcusSettings = fFSArcusSettings;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public FireOSUtil providesFireOSUtil() {
        return new FireOSUtil(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public FFSArcusSettings providesZeroTouchProvisioningSettings() {
        return this.mFFSArcusSettings;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ZeroTouchDependencyInjectorScope
    public ZeroTouchWorkflowController providesZeroTouchWorkflowController(ZeroTouchWorkflowRouter zeroTouchWorkflowRouter, DeviceDiscoveryStream deviceDiscoveryStream, ZeroTouchWorkflowMetricsReporter zeroTouchWorkflowMetricsReporter, FFSProvisioningServiceMetricsRecorder fFSProvisioningServiceMetricsRecorder, DiscoverySettings discoverySettings, ZeroTouchWorkflowFailureUpdateHandler zeroTouchWorkflowFailureUpdateHandler, DeferredDiscoveryHandler deferredDiscoveryHandler, FFSArcusSettings fFSArcusSettings) {
        return new ZeroTouchWorkflowController(this.mContext, zeroTouchWorkflowRouter, deviceDiscoveryStream, zeroTouchWorkflowMetricsReporter, fFSProvisioningServiceMetricsRecorder, discoverySettings, zeroTouchWorkflowFailureUpdateHandler, deferredDiscoveryHandler, fFSArcusSettings.getThrottleSettings());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public ZeroTouchWorkflowFactory providesZeroTouchWorkflowFactory() {
        return this.mWorkflowFactory;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ZeroTouchDependencyInjectorScope
    public ZeroTouchWorkflowFailureUpdateHandler providesZeroTouchWorkflowFailureUpdateHandler(FireOSUtil fireOSUtil, Clock clock, Boolean bool, FFSArcusSettings fFSArcusSettings) {
        return new ZeroTouchWorkflowFailureUpdateHandler(fireOSUtil, clock, bool.booleanValue(), Iso8601TimeConverter.convertDurationToMs(fFSArcusSettings.getThrottleSettings().getZtwFailedMonitorWindowPeriod()), fFSArcusSettings.getThrottleSettings().getZtwFailedThreshold(), Iso8601TimeConverter.convertDurationToMs(fFSArcusSettings.getThrottleSettings().getZtwDssFailedMonitorWindowPeriod()), fFSArcusSettings.getThrottleSettings().getZtwDssFailedThreshold());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ZeroTouchDependencyInjectorScope
    public ZeroTouchWorkflowMetricsReporter providesZeroTouchWorkflowMetricsReporter(FFSProvisioningServiceMetricsRecorder fFSProvisioningServiceMetricsRecorder) {
        return new ZeroTouchWorkflowMetricsReporter(fFSProvisioningServiceMetricsRecorder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public ZeroTouchWorkflowRouter providesZeroTouchWorkflowRouter(ZeroTouchWorkflowFactory zeroTouchWorkflowFactory, FFSArcusSettings fFSArcusSettings, WJDeviceSetupModeSupportedPredicate wJDeviceSetupModeSupportedPredicate) {
        return new ZeroTouchWorkflowRouter(zeroTouchWorkflowFactory, fFSArcusSettings, wJDeviceSetupModeSupportedPredicate);
    }
}
