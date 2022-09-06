package com.amazon.whisperjoin.provisionerSDK.configuration;

import android.content.Context;
import com.amazon.whisperbridge.Transport;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ProtocolBuffersSerializer;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.DeviceEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.ProvisioningEvent;
import com.amazon.whisperjoin.common.sharedtypes.utility.Serializer;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.provisionerSDK.devices.helpers.TransportProvider;
import com.amazon.whisperjoin.provisionerSDK.utility.Observers;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes13.dex */
public class ConnectionConfiguration {
    private final Context mContext;
    private final DSSClient mDSSClient;
    private final Observers<ProvisioningEvent<DeviceEvent>> mDeviceObservers;
    private final ExecutorService mExecutorService;
    private final int mMaximumMessageSize;
    private final Map<String, Transport> mRadioBridges;
    private final Serializer mSerializer = new ProtocolBuffersSerializer();

    /* loaded from: classes13.dex */
    public static class ConnectionConfigurationBuilder {
        static final int DEFAULT_MAX_MESSAGE_SIZE = 4096;
        Context mContext = null;
        Map<String, Transport> mRadioBridges = new HashMap();
        ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
        int mMaximumMessageSize = 4096;
        Observers<ProvisioningEvent<DeviceEvent>> mDeviceObservers = new Observers<>();
        DSSClient mDSSClient = null;

        public ConnectionConfiguration build() {
            if (this.mDSSClient != null) {
                return new ConnectionConfiguration(this);
            }
            throw new IllegalArgumentException("DSSClient must be defined");
        }

        public ConnectionConfigurationBuilder withContext(Context context) {
            if (context != null) {
                this.mContext = context;
                return this;
            }
            throw new IllegalArgumentException("context can not be null");
        }

        public ConnectionConfigurationBuilder withDSSClient(DSSClient dSSClient) {
            if (dSSClient != null) {
                this.mDSSClient = dSSClient;
                return this;
            }
            throw new IllegalArgumentException("dssClient can not be null");
        }

        public ConnectionConfigurationBuilder withDeviceObservers(Observers<ProvisioningEvent<DeviceEvent>> observers) {
            if (observers != null) {
                this.mDeviceObservers = observers;
                return this;
            }
            throw new IllegalArgumentException("observers must not be null");
        }

        public ConnectionConfigurationBuilder withMaximumMessageSize(int i) {
            this.mMaximumMessageSize = i;
            return this;
        }

        public ConnectionConfigurationBuilder withRadioBridges(Map<String, Transport> map) {
            if (map != null) {
                this.mRadioBridges = new HashMap(map);
                return this;
            }
            throw new IllegalArgumentException("radio bridges can not be null");
        }
    }

    ConnectionConfiguration(ConnectionConfigurationBuilder connectionConfigurationBuilder) {
        this.mContext = connectionConfigurationBuilder.mContext;
        this.mRadioBridges = connectionConfigurationBuilder.mRadioBridges;
        this.mExecutorService = connectionConfigurationBuilder.mExecutorService;
        this.mMaximumMessageSize = connectionConfigurationBuilder.mMaximumMessageSize;
        this.mDeviceObservers = connectionConfigurationBuilder.mDeviceObservers;
        this.mDSSClient = connectionConfigurationBuilder.mDSSClient;
    }

    public Context getContext() {
        return this.mContext;
    }

    public DSSClient getDSSClient() {
        return this.mDSSClient;
    }

    public Observers<ProvisioningEvent<DeviceEvent>> getDeviceObservers() {
        return this.mDeviceObservers;
    }

    public ExecutorService getExecutorService() {
        return this.mExecutorService;
    }

    public int getMaximumMessageSize() {
        return this.mMaximumMessageSize;
    }

    public TransportProvider getTransportProvider() {
        return new TransportProvider.DefaultTransportProvider(this.mRadioBridges);
    }
}
