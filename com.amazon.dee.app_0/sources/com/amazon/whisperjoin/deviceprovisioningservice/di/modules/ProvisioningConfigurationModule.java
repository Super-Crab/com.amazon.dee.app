package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.os.Build;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.amazon.whisperjoin.common.sharedtypes.cryptography.TrustProvider;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.BuildConfig;
import com.amazon.whisperjoin.deviceprovisioningservice.di.scopes.ProvisionerServices;
import com.amazon.whisperjoin.deviceprovisioningservice.identity.MapAuthenticationProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.util.DeviceSerialProvider;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSServiceConfiguration;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes13.dex */
public class ProvisioningConfigurationModule {
    private static final String TAG = "ProvisioningConfigurationModule";
    private final ProvisioningMethod mProvisioningMethod;
    private final ProvisioningServiceConfiguration mProvisioningServiceConfiguration;
    private final WorkflowConfiguration mWorkflowConfiguration;

    public ProvisioningConfigurationModule(ProvisioningServiceConfiguration provisioningServiceConfiguration, WorkflowConfiguration workflowConfiguration, ProvisioningMethod provisioningMethod) {
        if (provisioningServiceConfiguration != null) {
            if (workflowConfiguration == null) {
                throw new IllegalArgumentException("workflowConfiguration cannot be null");
            }
            if (provisioningMethod != null) {
                this.mProvisioningServiceConfiguration = provisioningServiceConfiguration;
                this.mWorkflowConfiguration = workflowConfiguration;
                this.mProvisioningMethod = provisioningMethod;
                WJLog.setDebug(this.mProvisioningServiceConfiguration.isDebugEnabled());
                WJLog.i(TAG, "ProvisionerSDK Version: 1.21.2_sdk30.39.0");
                WJLog.d(TAG, "ProvisionerSDK Runtime Dependencies");
                WJLog.d(TAG, BuildConfig.WJ_VERSIONS);
                return;
            }
            throw new IllegalArgumentException("provisioningMethod can not be null");
        }
        throw new IllegalArgumentException("Provisioning service configuration cannot be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public DSSServiceConfiguration providesDSSServiceConfiguration() {
        return this.mProvisioningServiceConfiguration.getDSSServiceConfiguration();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public Boolean providesIsDebugEnabled() {
        return Boolean.valueOf(this.mProvisioningServiceConfiguration.isDebugEnabled());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public ProvisionerClientData providesProvisionerClientData(ProvisioningServiceConfiguration provisioningServiceConfiguration, MapAuthenticationProvider mapAuthenticationProvider) {
        ProvisionerClientData createProvisionerClientData = new ProvisionerClientData.Builder().setClientAppName(provisioningServiceConfiguration.getProvisionerApplicationName()).setClientAppVersion(provisioningServiceConfiguration.getProvisionerVersionNumber()).setDeviceManufacturer(Build.MANUFACTURER).setDeviceModel(Build.MODEL).setDeviceSerialNumber(DeviceSerialProvider.getDeviceSerial()).setDeviceFirmwareVersion(Build.VERSION.RELEASE).setMarketplace(provisioningServiceConfiguration.getLocaleConfiguration().getMarketplace()).setMetricsDeviceGroup(provisioningServiceConfiguration.getProvisionerDeviceGroup()).setCustomerEcid(mapAuthenticationProvider.getAccount()).createProvisionerClientData();
        WJLog.d(TAG, "ProvisionerClientData");
        WJLog.d(TAG, createProvisionerClientData.toString());
        return createProvisionerClientData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public ProvisionerInfo providesProvisionerInfo(ProvisionerClientData provisionerClientData) {
        ProvisionerInfo provisionerInfo = new ProvisionerInfo();
        provisionerInfo.setDeviceModel(provisionerClientData.getDeviceModel());
        provisionerInfo.setManufacturer(provisionerClientData.getDeviceManufacturer());
        provisionerInfo.setFirmwareVersion(provisionerClientData.getDeviceFirmwareVersion());
        provisionerInfo.setApplication(provisionerClientData.getClientAppName());
        provisionerInfo.setApplicationVersion(provisionerClientData.getClientAppVersion());
        return provisionerInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public ProvisioningMethod providesProvisioningMethod() {
        return this.mProvisioningMethod;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public ProvisioningServiceConfiguration providesProvisioningServiceConfiguration() {
        return this.mProvisioningServiceConfiguration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public TrustProvider.TrustState providesTrustState(WorkflowConfiguration workflowConfiguration, ProvisioningMethod provisioningMethod) {
        if (provisioningMethod.equals(ProvisioningMethod.FFS)) {
            return TrustProvider.TrustState.TRUSTED_ENCRYPTED;
        }
        if (workflowConfiguration.hasBarcodeData()) {
            return TrustProvider.TrustState.UNTRUSTED_PIN;
        }
        return TrustProvider.TrustState.UNTRUSTED_ENCRYPTED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @ProvisionerServices
    public WorkflowConfiguration providesWorkflowConfiguration() {
        return this.mWorkflowConfiguration;
    }
}
