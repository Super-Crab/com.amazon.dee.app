package com.amazon.alexa.devicesetup.sdk.whisperjoin.helper;

import com.amazon.whisperjoin.common.sharedtypes.devices.DeviceFilter;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BarcodeFormatException;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.PublicKeyDecompressionException;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.locale.LocaleConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfigurationFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSServiceConfiguration;
/* loaded from: classes7.dex */
public final class ProvisioningConfigGenerator {
    private ProvisioningConfigGenerator() {
    }

    public static ProvisioningServiceConfiguration getProvisioningServiceConfiguration(ProvisioningServiceConfiguration provisioningServiceConfiguration) {
        DSSServiceConfiguration prod;
        String provisionerDeviceGroup = provisioningServiceConfiguration.getProvisionerDeviceGroup();
        Boolean valueOf = Boolean.valueOf(provisioningServiceConfiguration.isDebugEnabled());
        if (provisioningServiceConfiguration.getDSSServiceConfiguration().getStage().name().equals("gamma")) {
            prod = DSSServiceConfiguration.gamma(true);
        } else {
            prod = DSSServiceConfiguration.prod(true);
        }
        LocaleConfiguration localeConfiguration = new LocaleConfiguration();
        localeConfiguration.setCountryCode(provisioningServiceConfiguration.getLocaleConfiguration().getCountryCode());
        localeConfiguration.setMarketplace(provisioningServiceConfiguration.getLocaleConfiguration().getMarketplace());
        localeConfiguration.setLanguageLocale(provisioningServiceConfiguration.getLocaleConfiguration().getLanguageLocale());
        return new ProvisioningServiceConfiguration.Builder().setProvisionerApplicationName("AlexaMobileAndroid").setProvisionerVersionNumber(provisioningServiceConfiguration.getProvisionerVersionNumber() != null ? provisioningServiceConfiguration.getProvisionerVersionNumber() : "1.0").setProvisionerDeviceGroup(provisionerDeviceGroup).setLocaleConfiguration(localeConfiguration).setDssServiceConfiguration(prod).setDebugEnabled(valueOf.booleanValue()).setProvisionableBeaconType(DeviceFilter.BeaconType.ALL).createProvisioningServiceConfiguration();
    }

    public static WorkflowConfiguration getWorkflowConfiguration() {
        return WorkflowConfigurationFactory.createWorkflowConfigurationForAllDevices(DeviceFilter.BeaconType.ALL);
    }

    public static WorkflowConfiguration getWorkflowConfigurationWithBarcodeData(String str) throws BarcodeFormatException, PublicKeyDecompressionException {
        if (str != null) {
            return WorkflowConfigurationFactory.createWorkflowConfigurationForPinSetup(str, DeviceFilter.BeaconType.ALL);
        }
        return null;
    }

    public static WorkflowConfiguration getWorkflowConfigurationWithDeviceId(String str) {
        if (str != null) {
            return WorkflowConfigurationFactory.createWorkflowConfigurationForTargetDevice(str, DeviceFilter.BeaconType.ALL);
        }
        return null;
    }

    public static WorkflowConfiguration getWorkflowConfigurationWithProductId(String str) {
        if (str != null) {
            return WorkflowConfigurationFactory.createWorkflowConfigurationForTargetProduct(str, DeviceFilter.BeaconType.ALL);
        }
        return null;
    }
}
