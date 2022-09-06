package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.bluetooth.le.ScanFilter;
import android.content.Context;
import com.amazon.whisperjoin.common.sharedtypes.configuration.OveractiveConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.radios.ScanningMode;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.ble.ScanFilters;
import com.amazon.whisperjoin.deviceprovisioningservice.device.DiscoverySettings;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.WorkflowConfiguration;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.WJDeviceSetupModeSupportedPredicate;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import dagger.Module;
import dagger.Provides;
import java.util.List;
@Module
/* loaded from: classes13.dex */
public class DiscoveryConfigurationModule {
    private static final int FFS_DISCOVERY_COUNT_LIMIT = 1;
    private static final String TAG = "DiscoveryConfigurationModule";
    private static final int UGS_DISCOVERY_COUNT_LIMIT = 30;
    private final boolean mEnablePhilips;
    private final OveractiveConfiguration mOveractiveConfiguration;
    private final ScanningMode mScanningMode;

    public DiscoveryConfigurationModule(ScanningMode scanningMode, boolean z, OveractiveConfiguration overactiveConfiguration) {
        this.mScanningMode = scanningMode;
        this.mEnablePhilips = z;
        this.mOveractiveConfiguration = overactiveConfiguration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public DiscoverySettings providesDiscoverySettings(WorkflowConfiguration workflowConfiguration, ScanningMode scanningMode, ProvisioningMethod provisioningMethod) {
        return new DiscoverySettings(workflowConfiguration.createDeviceFilter(), scanningMode, provisioningMethod.equals(ProvisioningMethod.FFS) ? 1 : 30);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public OveractiveConfiguration providesOveractiveConfiguration() {
        return this.mOveractiveConfiguration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public List<ScanFilter> providesScanFilter(Context context) {
        if (this.mEnablePhilips) {
            WJLog.d(TAG, "Vending WJ and Philips scan filters");
            return ScanFilters.getScanFiltersForWJandPhilips();
        }
        WJLog.d(TAG, "Vending WJ scan filter");
        return ScanFilters.getDefaultScanFilters();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public ScanningMode providesScanningMode() {
        return this.mScanningMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public WJDeviceSetupModeSupportedPredicate providesWJDeviceSetupModeSupportedPredicate(ProvisioningMethod provisioningMethod, WorkflowConfiguration workflowConfiguration) {
        return new WJDeviceSetupModeSupportedPredicate(provisioningMethod, workflowConfiguration.hasBarcodeData());
    }
}
