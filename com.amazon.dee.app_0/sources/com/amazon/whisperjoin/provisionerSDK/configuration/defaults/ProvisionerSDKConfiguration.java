package com.amazon.whisperjoin.provisionerSDK.configuration.defaults;

import android.bluetooth.le.ScanFilter;
import android.content.Context;
import com.amazon.whisperbridge.ble.BleManager;
import com.amazon.whisperjoin.common.sharedtypes.configuration.OveractiveConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.configuration.RadioConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.DeviceEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.DiscoveryEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.ProvisioningEvent;
import com.amazon.whisperjoin.common.sharedtypes.radios.RadioPreferences;
import com.amazon.whisperjoin.common.sharedtypes.radios.SupportedRadios;
import com.amazon.whisperjoin.common.sharedtypes.utility.EncodingHelpers;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.provisionerSDK.configuration.ConnectionConfiguration;
import com.amazon.whisperjoin.provisionerSDK.configuration.DiscoveryConfiguration;
import com.amazon.whisperjoin.provisionerSDK.configuration.defaults.Config;
import com.amazon.whisperjoin.provisionerSDK.radios.BasicConnectionFactory;
import com.amazon.whisperjoin.provisionerSDK.radios.ble.BLEDiscoveryService;
import com.amazon.whisperjoin.provisionerSDK.radios.ble.BLETransport;
import com.amazon.whisperjoin.provisionerSDK.radios.ble.BLETransportBuilder;
import com.amazon.whisperjoin.provisionerSDK.utility.Observers;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class ProvisionerSDKConfiguration {

    /* loaded from: classes13.dex */
    public static class ConfigurationBuilder {
        private Context mContext;
        private DSSClient mDSSClient;
        private OveractiveConfiguration mOveractiveConfiguration;
        private List<ScanFilter> mScanFilters;
        private Observers<ProvisioningEvent<DiscoveryEvent>> mDiscoveryEventObservers = new Observers<>();
        private Observers<ProvisioningEvent<DeviceEvent>> mDeviceEventObservers = new Observers<>();

        public Collection<RadioConfiguration> build() {
            validate();
            BleManager bleManager = new BleManager();
            BLETransport createBLETransport = new BLETransportBuilder().setContext(this.mContext).setBleManager(bleManager).setEncodingHelpers(new EncodingHelpers()).setOperationTimeout(Config.Transport.OPERATION_TIMEOUT_MS).setOperationTimeoutUnit(TimeUnit.MILLISECONDS).setOperationRetryCount(3L).setCommandExecutor(Executors.newSingleThreadExecutor()).setRetryExecutor(Executors.newSingleThreadExecutor()).createBLETransport();
            HashMap hashMap = new HashMap();
            hashMap.put(SupportedRadios.BLE.getString(), createBLETransport);
            DiscoveryConfiguration build = new DiscoveryConfiguration.DiscoveryConfigurationBuilder().withContext(this.mContext).withObservers(this.mDiscoveryEventObservers).withScanFilters(this.mScanFilters).withOveractiveConfiguration(this.mOveractiveConfiguration).build();
            return Collections.singleton(new RadioConfiguration(new BLEDiscoveryService(build, bleManager), SupportedRadios.BLE.getString(), new BasicConnectionFactory(new ConnectionConfiguration.ConnectionConfigurationBuilder().withRadioBridges(hashMap).withContext(this.mContext).withMaximumMessageSize(8192).withDeviceObservers(this.mDeviceEventObservers).withDSSClient(this.mDSSClient).build())));
        }

        protected void validate() {
            if (this.mContext != null) {
                if (this.mScanFilters == null) {
                    throw new IllegalArgumentException("mScanFilters can not be null");
                }
                return;
            }
            throw new IllegalArgumentException("context must be not be null, build using withContext()");
        }

        public ConfigurationBuilder withContext(Context context) {
            if (context != null) {
                this.mContext = context;
                return this;
            }
            throw new IllegalArgumentException("context must not be null");
        }

        public ConfigurationBuilder withDSSClient(DSSClient dSSClient) {
            if (dSSClient != null) {
                this.mDSSClient = dSSClient;
                return this;
            }
            throw new IllegalArgumentException("dssClient can not be null");
        }

        public ConfigurationBuilder withDeviceObservers(Observers<ProvisioningEvent<DeviceEvent>> observers) {
            if (observers != null) {
                this.mDeviceEventObservers = observers;
                return this;
            }
            throw new IllegalArgumentException("deviceEvents must not be null");
        }

        public ConfigurationBuilder withDiscoveryObservers(Observers<ProvisioningEvent<DiscoveryEvent>> observers) {
            if (observers != null) {
                this.mDiscoveryEventObservers = observers;
                return this;
            }
            throw new IllegalArgumentException("discoveryEvent must not be null");
        }

        public ConfigurationBuilder withOveractiveConfiguration(OveractiveConfiguration overactiveConfiguration) {
            this.mOveractiveConfiguration = overactiveConfiguration;
            return this;
        }

        public ConfigurationBuilder withScanFilters(List<ScanFilter> list) {
            if (list != null) {
                this.mScanFilters = list;
                return this;
            }
            throw new IllegalArgumentException("scanFilters can not be null");
        }
    }

    /* loaded from: classes13.dex */
    public static class RadioPreferencesBuilder {
        public RadioPreferences build() {
            return new RadioPreferences(Collections.singleton(new BasicAmazonBLERadioPreference()));
        }
    }

    private ProvisionerSDKConfiguration() {
    }
}
