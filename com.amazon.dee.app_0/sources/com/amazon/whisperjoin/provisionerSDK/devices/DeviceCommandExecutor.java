package com.amazon.whisperjoin.provisionerSDK.devices;

import com.amazon.whisperbridge.constants.Command;
/* loaded from: classes13.dex */
public interface DeviceCommandExecutor {
    byte[] executeCommand(Command command, byte[] bArr) throws Exception;
}
