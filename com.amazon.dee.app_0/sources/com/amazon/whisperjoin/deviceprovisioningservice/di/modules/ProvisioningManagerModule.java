package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.bluetooth.le.ScanFilter;
import android.content.Context;
import com.amazon.whisperjoin.common.sharedtypes.configuration.OveractiveConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.device.ProvisioningManagerProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.di.scopes.ProvisioningScope;
import com.amazon.whisperjoin.deviceprovisioningservice.util.Clock;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.deferred.DeferredDiscoveryHandler;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceDiscoveryStream;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.DeviceEventStream;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.List;
@Module
/* loaded from: classes13.dex */
public class ProvisioningManagerModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public Disposable getProvisioningManagerDisposable(DeviceEventStream deviceEventStream, DeviceDiscoveryStream deviceDiscoveryStream) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(deviceEventStream.getStreamDisposable());
        compositeDisposable.add(deviceDiscoveryStream.getStreamDisposable());
        return compositeDisposable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public DeferredDiscoveryHandler providesDeferredDiscoveryHandler(Clock clock) {
        return new DeferredDiscoveryHandler(clock);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public DeviceDiscoveryStream providesDeviceDiscoveryStream(ProvisioningManagerProvider provisioningManagerProvider) {
        return new DeviceDiscoveryStream(provisioningManagerProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public DeviceEventStream providesDeviceEventStream(ProvisioningManagerProvider provisioningManagerProvider) {
        return new DeviceEventStream(provisioningManagerProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisioningScope
    public ProvisioningManagerProvider providesProvisioningManagerProvider(Context context, DSSClient dSSClient, MetricsRecorderProvider metricsRecorderProvider, List<ScanFilter> list, OveractiveConfiguration overactiveConfiguration) {
        return new ProvisioningManagerProvider(context, dSSClient, metricsRecorderProvider, list, overactiveConfiguration);
    }
}
