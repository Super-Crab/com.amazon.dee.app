package com.amazon.alexa.devicesetup.sdk.whisperjoin;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.devicesetup.sdk.whisperjoin.helper.ProvisioningConfigGenerator;
import com.amazon.alexa.devicesetup.sdk.whisperjoin.impl.AutoDiscoveryPresenterViewImpl;
import com.amazon.alexa.devicesetup.sdk.whisperjoin.impl.DeviceSetupPresenterViewImpl;
import com.amazon.alexa.devicesetup.utils.EventBusUtil;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.BarcodeFormatException;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.PublicKeyDecompressionException;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisioningServiceConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenterContract;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenter;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.ControlledSetupPresenterContract;
import com.google.common.base.Preconditions;
/* loaded from: classes7.dex */
public final class DeviceSetupPresenterFactory {
    private static DeviceSetupPresenterFactory instance;
    private static String languageLocale;

    @VisibleForTesting
    private DeviceSetupPresenterFactory() {
    }

    public static AutoDiscoveryPresenterContract.Action createAutoDiscoverySetupPresenter(Context context, ProvisioningServiceConfiguration provisioningServiceConfiguration) {
        Preconditions.checkNotNull(context, "React context should not be null");
        Preconditions.checkNotNull(provisioningServiceConfiguration, "Configuration should not be null");
        AutoDiscoveryPresenter autoDiscoveryPresenter = new AutoDiscoveryPresenter(context, ProvisioningConfigGenerator.getProvisioningServiceConfiguration(provisioningServiceConfiguration));
        autoDiscoveryPresenter.attachView((AutoDiscoveryPresenterContract.View) new AutoDiscoveryPresenterViewImpl(EventBusUtil.instance()));
        return autoDiscoveryPresenter;
    }

    public static ControlledSetupPresenter createSmartHomePresenter(Context context, ProvisioningServiceConfiguration provisioningServiceConfiguration) {
        Preconditions.checkNotNull(context, "React context should not be null");
        Preconditions.checkNotNull(provisioningServiceConfiguration, "Configuration should not be null");
        ProvisioningServiceConfiguration provisioningServiceConfiguration2 = ProvisioningConfigGenerator.getProvisioningServiceConfiguration(provisioningServiceConfiguration);
        WorkflowConfiguration workflowConfiguration = ProvisioningConfigGenerator.getWorkflowConfiguration();
        languageLocale = provisioningServiceConfiguration2.getLocaleConfiguration().getLanguageLocale();
        ControlledSetupPresenter controlledSetupPresenter = new ControlledSetupPresenter(context, provisioningServiceConfiguration2, workflowConfiguration);
        controlledSetupPresenter.attachView((ControlledSetupPresenterContract.View) new DeviceSetupPresenterViewImpl(EventBusUtil.instance()));
        return controlledSetupPresenter;
    }

    public static ControlledSetupPresenter createSmartHomePresenterWithBarcode(Context context, ProvisioningServiceConfiguration provisioningServiceConfiguration, String str) throws BarcodeFormatException, PublicKeyDecompressionException {
        Preconditions.checkNotNull(context, "React context should not be null");
        Preconditions.checkNotNull(provisioningServiceConfiguration, "Configuration should not be null");
        ProvisioningServiceConfiguration provisioningServiceConfiguration2 = ProvisioningConfigGenerator.getProvisioningServiceConfiguration(provisioningServiceConfiguration);
        WorkflowConfiguration workflowConfigurationWithBarcodeData = ProvisioningConfigGenerator.getWorkflowConfigurationWithBarcodeData(str);
        languageLocale = provisioningServiceConfiguration2.getLocaleConfiguration().getLanguageLocale();
        ControlledSetupPresenter controlledSetupPresenter = new ControlledSetupPresenter(context, provisioningServiceConfiguration2, workflowConfigurationWithBarcodeData);
        controlledSetupPresenter.attachView((ControlledSetupPresenterContract.View) new DeviceSetupPresenterViewImpl(EventBusUtil.instance()));
        return controlledSetupPresenter;
    }

    public static ControlledSetupPresenter createSmartHomePresenterWithDeviceId(Context context, ProvisioningServiceConfiguration provisioningServiceConfiguration, String str) {
        Preconditions.checkNotNull(context, "React context should not be null");
        Preconditions.checkNotNull(provisioningServiceConfiguration, "Configuration should not be null");
        ProvisioningServiceConfiguration provisioningServiceConfiguration2 = ProvisioningConfigGenerator.getProvisioningServiceConfiguration(provisioningServiceConfiguration);
        WorkflowConfiguration workflowConfigurationWithDeviceId = ProvisioningConfigGenerator.getWorkflowConfigurationWithDeviceId(str);
        languageLocale = provisioningServiceConfiguration2.getLocaleConfiguration().getLanguageLocale();
        ControlledSetupPresenter controlledSetupPresenter = new ControlledSetupPresenter(context, provisioningServiceConfiguration2, workflowConfigurationWithDeviceId);
        controlledSetupPresenter.attachView((ControlledSetupPresenterContract.View) new DeviceSetupPresenterViewImpl(EventBusUtil.instance()));
        return controlledSetupPresenter;
    }

    public static ControlledSetupPresenter createSmartHomePresenterWithProductId(Context context, ProvisioningServiceConfiguration provisioningServiceConfiguration, String str) {
        Preconditions.checkNotNull(context, "React context should not be null");
        Preconditions.checkNotNull(provisioningServiceConfiguration, "Configuration should not be null");
        ProvisioningServiceConfiguration provisioningServiceConfiguration2 = ProvisioningConfigGenerator.getProvisioningServiceConfiguration(provisioningServiceConfiguration);
        WorkflowConfiguration workflowConfigurationWithProductId = ProvisioningConfigGenerator.getWorkflowConfigurationWithProductId(str);
        languageLocale = provisioningServiceConfiguration2.getLocaleConfiguration().getLanguageLocale();
        ControlledSetupPresenter controlledSetupPresenter = new ControlledSetupPresenter(context, provisioningServiceConfiguration2, workflowConfigurationWithProductId);
        controlledSetupPresenter.attachView((ControlledSetupPresenterContract.View) new DeviceSetupPresenterViewImpl(EventBusUtil.instance()));
        return controlledSetupPresenter;
    }

    public static String getLanguageLocale() {
        return languageLocale;
    }

    public static DeviceSetupPresenterFactory instance() {
        if (instance == null) {
            instance = new DeviceSetupPresenterFactory();
        }
        return instance;
    }
}
