package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import com.amazon.whisperjoin.common.sharedtypes.ble.commands.ProvisioningCommandResponse;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.UnsuccessfulProvisioningCommandError;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ProtocolBuffersSerializer;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.reflect.TypeToken;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
/* loaded from: classes13.dex */
public class SendCommandResponseFuture extends FutureTask<byte[]> {
    private static final String TAG = SendCommandResponseFuture.class.getName();

    public SendCommandResponseFuture(final ByteArrayOutputStream byteArrayOutputStream) {
        super(new Callable() { // from class: com.amazon.whisperjoin.provisionerSDK.radios.ble.SendCommandResponseFuture.1
            @Override // java.util.concurrent.Callable
            public byte[] call() throws Exception {
                ProtocolBuffersSerializer protocolBuffersSerializer = new ProtocolBuffersSerializer();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                ProvisioningCommandResponse provisioningCommandResponse = (ProvisioningCommandResponse) protocolBuffersSerializer.deserialize(byteArray, TypeToken.of(ProvisioningCommandResponse.class));
                String str = SendCommandResponseFuture.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Command Response Data for UUID: ");
                outline107.append(provisioningCommandResponse.getIdentifier().toString());
                WJLog.byteStruct(str, outline107.toString(), byteArray);
                String str2 = SendCommandResponseFuture.TAG;
                WJLog.d(str2, "Response: " + provisioningCommandResponse);
                if (provisioningCommandResponse.getStatus() != 0) {
                    WJLog.e(SendCommandResponseFuture.TAG, "Command was not successful");
                    throw new UnsuccessfulProvisioningCommandError(provisioningCommandResponse.getIdentifier().toString(), provisioningCommandResponse.getStatus());
                }
                return provisioningCommandResponse.getData();
            }
        });
    }
}
