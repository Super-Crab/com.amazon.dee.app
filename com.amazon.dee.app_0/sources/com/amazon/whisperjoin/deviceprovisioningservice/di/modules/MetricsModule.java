package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.client.metrics.common.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.whisperjoin.common.sharedtypes.smarthome.metrics.CredentialSyncMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.di.scopes.ProvisionerServices;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.AutoDiscoveryMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.metrics.FFSProvisioningServiceMetricsRecorder;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.metrics.MetricsProgramName;
import com.amazon.whisperjoin.metrics.MetricsRecorderProvider;
import dagger.Module;
import dagger.Provides;
import java.lang.reflect.InvocationTargetException;
@Module
/* loaded from: classes13.dex */
public class MetricsModule {
    private void setDeviceId(Context context, ProvisionerClientData provisionerClientData) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        AndroidMetricsFactoryImpl.class.getMethod("setDeviceId", Context.class, String.class).invoke(AndroidMetricsFactoryImpl.class, context, provisionerClientData.getDeviceSerialNumber());
    }

    private void setDeviceType(Context context, ProvisionerClientData provisionerClientData) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        AndroidMetricsFactoryImpl.class.getMethod("setDeviceType", Context.class, String.class).invoke(AndroidMetricsFactoryImpl.class, context, provisionerClientData.getMetricsDeviceGroup());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public AutoDiscoveryMetricsRecorder providesAutoDiscoveryMetricsRecorder(MetricsRecorderProvider metricsRecorderProvider) {
        return new AutoDiscoveryMetricsRecorder(metricsRecorderProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public CredentialSyncMetricsRecorder providesCredentialSyncMetricsRecorder(MetricsRecorderProvider metricsRecorderProvider) {
        return new CredentialSyncMetricsRecorder(metricsRecorderProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public FFSProvisioningServiceMetricsRecorder providesFFSProvisioningServiceMetricsRecorder(MetricsRecorderProvider metricsRecorderProvider) {
        return new FFSProvisioningServiceMetricsRecorder(metricsRecorderProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public MetricsFactory providesMetricsFactory(Context context, ProvisionerClientData provisionerClientData) {
        try {
            setDeviceType(context, provisionerClientData);
            setDeviceId(context, provisionerClientData);
        } catch (Exception unused) {
        }
        return AndroidMetricsFactoryImpl.getInstance(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public MetricsRecorderProvider providesMetricsRecorderProvider(MetricsFactory metricsFactory, MapAuthenticationProvider mapAuthenticationProvider, ProvisionerClientData provisionerClientData) {
        return new MetricsRecorderProvider(metricsFactory, MetricsProgramName.WJv2Provisioner, mapAuthenticationProvider.getAccount(), provisionerClientData.getClientAppName());
    }
}
