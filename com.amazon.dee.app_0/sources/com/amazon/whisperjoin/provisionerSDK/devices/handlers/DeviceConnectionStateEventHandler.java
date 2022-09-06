package com.amazon.whisperjoin.provisionerSDK.devices.handlers;

import com.amazon.whisperbridge.Transport;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.DeviceEvent;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.EventHandler;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.events.ProvisioningEvent;
/* loaded from: classes13.dex */
public class DeviceConnectionStateEventHandler implements Transport.DeviceConnectionStateChangedListener {
    private final EventHandler<DeviceEvent> mDelegateEventHandler;

    public DeviceConnectionStateEventHandler(EventHandler<DeviceEvent> eventHandler) {
        this.mDelegateEventHandler = eventHandler;
    }

    @Override // com.amazon.whisperbridge.Transport.DeviceConnectionStateChangedListener
    public void onConnect() {
    }

    @Override // com.amazon.whisperbridge.Transport.DeviceConnectionStateChangedListener
    public void onConnectionFailure(int i) {
        this.mDelegateEventHandler.handleEvent(new ProvisioningEvent<>(DeviceEvent.DISCONNECTED, Integer.valueOf(i)));
    }

    @Override // com.amazon.whisperbridge.Transport.DeviceConnectionStateChangedListener
    public void onDisconnect() {
    }
}
