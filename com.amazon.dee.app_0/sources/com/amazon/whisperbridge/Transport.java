package com.amazon.whisperbridge;

import com.amazon.whisperbridge.constants.Command;
import com.amazon.whisperjoin.common.sharedtypes.ble.events.ProvisionableEventNotification;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.RadioNotEnabledException;
import java.io.IOException;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public interface Transport {

    /* loaded from: classes13.dex */
    public interface DeviceConnectionStateChangedListener {
        void onConnect();

        void onConnectionFailure(int i);

        void onDisconnect();
    }

    /* loaded from: classes13.dex */
    public interface ProvisionableEventNotificationCallback {
        void onProvisionableEventNotification(ProvisionableEventNotification provisionableEventNotification);
    }

    void close(Object obj);

    Future<Void> connect(Object obj, DeviceConnectionStateChangedListener deviceConnectionStateChangedListener) throws RadioNotEnabledException, IOException;

    Future<Void> onProvisioningComplete(Object obj);

    Future<byte[]> sendCommand(Object obj, Command command, byte[] bArr) throws RadioNotEnabledException, IOException;

    void setProvisionableEventNotificationCallback(Object obj, ProvisionableEventNotificationCallback provisionableEventNotificationCallback) throws IOException;

    Future<ProvisioningStatus> startProvisioning(Object obj) throws RadioNotEnabledException, IOException;
}
