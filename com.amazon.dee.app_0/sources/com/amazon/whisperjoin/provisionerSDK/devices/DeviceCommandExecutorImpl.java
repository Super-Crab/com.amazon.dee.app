package com.amazon.whisperjoin.provisionerSDK.devices;

import com.amazon.whisperbridge.Transport;
import com.amazon.whisperbridge.constants.Command;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.provisionerSDK.radios.error.ProvisionerCommandError;
import org.apache.commons.lang.exception.ExceptionUtils;
/* loaded from: classes13.dex */
public class DeviceCommandExecutorImpl implements DeviceCommandExecutor {
    private static final String TAG = "DeviceCommandExecutorImpl";
    private final Object mRadioHandle;
    private final Transport mTransport;

    public DeviceCommandExecutorImpl(Transport transport, Object obj) {
        this.mTransport = transport;
        this.mRadioHandle = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable] */
    @Override // com.amazon.whisperjoin.provisionerSDK.devices.DeviceCommandExecutor
    public byte[] executeCommand(Command command, byte[] bArr) throws Exception {
        try {
            String str = TAG;
            WJLog.d(str, "mRadio" + this.mRadioHandle.hashCode() + " Message " + command);
            byte[] bArr2 = this.mTransport.sendCommand(this.mRadioHandle, command, bArr).get();
            if (bArr2 != null && bArr2.length != 0) {
                return bArr2;
            }
            WJLog.d(TAG, "Result is null, just returning empty buffer");
            return null;
        } catch (Exception e) {
            e = e;
            ?? rootCause = ExceptionUtils.getRootCause(e);
            if (rootCause != 0) {
                e = rootCause;
            }
            throw new ProvisionerCommandError(e, command);
        }
    }
}
