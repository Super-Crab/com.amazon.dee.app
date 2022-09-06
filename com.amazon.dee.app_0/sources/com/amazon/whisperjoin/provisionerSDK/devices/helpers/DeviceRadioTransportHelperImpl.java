package com.amazon.whisperjoin.provisionerSDK.devices.helpers;

import com.amazon.whisperbridge.ProvisioningStatus;
import com.amazon.whisperbridge.Transport;
import com.amazon.whisperjoin.common.sharedtypes.devices.DiscoveredRadio;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.provisionerSDK.devices.DeviceCommandExecutor;
import com.amazon.whisperjoin.provisionerSDK.devices.DeviceCommandExecutorImpl;
import com.amazon.whisperjoin.provisionerSDK.devices.handlers.DeviceConnectionStateEventHandler;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public class DeviceRadioTransportHelperImpl implements DeviceRadioTransportHelper {
    private static final String TAG = "DeviceRadioTransportHelperImpl";
    private DeviceCommandExecutor mDeviceCommandExecutor;
    private final DiscoveredRadio mDiscoveredRadio;
    private Transport mTransport;
    private final TransportProvider mTransportProvider;

    public DeviceRadioTransportHelperImpl(DiscoveredRadio discoveredRadio, TransportProvider transportProvider) {
        if (discoveredRadio != null) {
            if (transportProvider != null) {
                this.mDiscoveredRadio = discoveredRadio;
                this.mTransportProvider = transportProvider;
                return;
            }
            throw new IllegalArgumentException("Transport Provider can not be null");
        }
        throw new IllegalArgumentException("Selected Radio can not be null");
    }

    private void initializeTransport() {
        this.mTransport = this.mTransportProvider.getTransportForRadio(this.mDiscoveredRadio.getRadio());
        if (isConnected()) {
            this.mDeviceCommandExecutor = new DeviceCommandExecutorImpl(this.mTransport, this.mDiscoveredRadio.getRadioHandle());
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No transport found for provided radio: ");
        outline107.append(this.mDiscoveredRadio.getRadioHandle());
        throw new IllegalStateException(outline107.toString());
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.helpers.DeviceRadioTransportHelper
    public void closeConnection() {
        try {
            if (!isConnected()) {
                return;
            }
            this.mTransport.close(this.mDiscoveredRadio.getRadioHandle());
        } catch (Exception e) {
            WJLog.e(TAG, "problem closing connection", e);
        }
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.helpers.DeviceRadioTransportHelper
    public DeviceCommandExecutor getCommandExecutor() {
        if (isConnected()) {
            return this.mDeviceCommandExecutor;
        }
        throw new IllegalStateException("Command Executor is not initialized, a connection must be opened first");
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.helpers.DeviceRadioTransportHelper
    public DiscoveredRadio getSelectedRadio() {
        return this.mDiscoveredRadio;
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.helpers.DeviceRadioTransportHelper
    public boolean isConnected() {
        return this.mTransport != null;
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.helpers.DeviceRadioTransportHelper
    public Future<Void> onProvisioningComplete() {
        return this.mTransport.onProvisioningComplete(this.mDiscoveredRadio.getRadioHandle());
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.helpers.DeviceRadioTransportHelper
    public Future<Void> openConnection(DeviceConnectionStateEventHandler deviceConnectionStateEventHandler) throws Exception {
        initializeTransport();
        return this.mTransport.connect(this.mDiscoveredRadio.getRadioHandle(), deviceConnectionStateEventHandler);
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.helpers.DeviceRadioTransportHelper
    public void setEventNotificationCallback(Transport.ProvisionableEventNotificationCallback provisionableEventNotificationCallback) throws Exception {
        if (isConnected()) {
            this.mTransport.setProvisionableEventNotificationCallback(this.mDiscoveredRadio.getRadioHandle(), provisionableEventNotificationCallback);
            return;
        }
        throw new IllegalStateException("Transport is not yet initialized, must open connection before setting event notification callback");
    }

    @Override // com.amazon.whisperjoin.provisionerSDK.devices.helpers.DeviceRadioTransportHelper
    public Future<ProvisioningStatus> startProvisioning() throws Exception {
        if (isConnected()) {
            return this.mTransport.startProvisioning(this.mDiscoveredRadio.getRadioHandle());
        }
        throw new IllegalStateException("Transport is not yet initialized, must open connection before start provisioning");
    }
}
