package com.amazon.whisperjoin.common.sharedtypes.devices.interfaces;

import com.amazon.whisperbridge.ProvisioningStatus;
import com.amazon.whisperbridge.constants.Command;
import com.amazon.whisperjoin.common.sharedtypes.devices.DiscoveredRadio;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DeviceConnectionConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetailsProtoData;
import com.google.common.reflect.TypeToken;
import java.io.Closeable;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public interface PeripheralDevice extends Closeable {
    Future<Void> completeProvisioning();

    Future<Void> connectToPeripheral(DeviceConnectionConfiguration deviceConnectionConfiguration);

    Future<Void> connectToPeripheral(String str);

    byte[] executeCommand(Command command, byte[] bArr) throws Exception;

    <O> O executeOperation(Command command, Object obj, TypeToken<O> typeToken) throws Exception;

    DiscoveredRadio getConnectionRadio();

    Future<DeviceDetailsProtoData> getDeviceDetails();

    WhisperJoinPeripheralDeviceDetails getPeripheralDeviceDetails();

    Future<byte[]> sendCommand(String str, byte[] bArr);

    Future<ProvisioningStatus> startProvisioning();

    Future<Void> stopProvisioning();
}
