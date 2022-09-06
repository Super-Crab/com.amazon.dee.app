package com.amazon.whisperjoin.provisionerSDK.configuration;

import android.bluetooth.le.ScanFilter;
import android.content.Context;
import com.amazon.whisperjoin.common.sharedtypes.configuration.OveractiveConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.configuration.RadioConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.DiscoveryEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.ProvisioningEvent;
import com.amazon.whisperjoin.provisionerSDK.utility.Observers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class DiscoveryConfiguration {
    private final Context mContext;
    private final Observers<ProvisioningEvent<DiscoveryEvent>> mObservers;
    private final OveractiveConfiguration mOveractiveConfiguration;
    private final Collection<RadioConfiguration> mRadioConfigurations;
    private final List<ScanFilter> mScanFilters;

    /* loaded from: classes13.dex */
    public static class DiscoveryConfigurationBuilder {
        Observers<ProvisioningEvent<DiscoveryEvent>> mObservers = null;
        Collection<RadioConfiguration> mRadioConfigurations = new ArrayList();
        Context mContext = null;
        List<ScanFilter> mScanFilters = null;
        OveractiveConfiguration mOveractiveConfiguration = null;

        public DiscoveryConfiguration build() {
            if (this.mObservers != null) {
                if (this.mContext != null) {
                    if (this.mScanFilters != null) {
                        return new DiscoveryConfiguration(this);
                    }
                    throw new IllegalArgumentException("mScanFilters can not be null");
                }
                throw new IllegalArgumentException("context must be set");
            }
            throw new IllegalArgumentException("observers must be set");
        }

        public DiscoveryConfigurationBuilder withContext(Context context) {
            if (context != null) {
                this.mContext = context;
                return this;
            }
            throw new IllegalArgumentException("context can not be null");
        }

        public DiscoveryConfigurationBuilder withObservers(Observers<ProvisioningEvent<DiscoveryEvent>> observers) {
            if (observers != null) {
                this.mObservers = observers;
                return this;
            }
            throw new IllegalArgumentException("observers can not be null");
        }

        public DiscoveryConfigurationBuilder withOveractiveConfiguration(OveractiveConfiguration overactiveConfiguration) {
            this.mOveractiveConfiguration = overactiveConfiguration;
            return this;
        }

        public DiscoveryConfigurationBuilder withScanFilters(List<ScanFilter> list) {
            if (list != null) {
                this.mScanFilters = list;
                return this;
            }
            throw new IllegalArgumentException("scanFilters can not be null");
        }
    }

    DiscoveryConfiguration(DiscoveryConfigurationBuilder discoveryConfigurationBuilder) {
        this.mObservers = discoveryConfigurationBuilder.mObservers;
        this.mRadioConfigurations = discoveryConfigurationBuilder.mRadioConfigurations;
        this.mContext = discoveryConfigurationBuilder.mContext;
        this.mScanFilters = discoveryConfigurationBuilder.mScanFilters;
        this.mOveractiveConfiguration = discoveryConfigurationBuilder.mOveractiveConfiguration;
    }

    public Context getContext() {
        return this.mContext;
    }

    public Observers<ProvisioningEvent<DiscoveryEvent>> getObservers() {
        return this.mObservers;
    }

    public OveractiveConfiguration getOveractiveConfiguration() {
        return this.mOveractiveConfiguration;
    }

    public List<ScanFilter> getScanFilters() {
        return this.mScanFilters;
    }
}
