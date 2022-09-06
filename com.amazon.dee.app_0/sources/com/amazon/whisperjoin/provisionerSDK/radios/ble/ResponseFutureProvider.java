package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.FutureTask;
/* loaded from: classes13.dex */
public class ResponseFutureProvider {
    public SendCommandResponseFuture newSendCommandResponseFuture(ByteArrayOutputStream byteArrayOutputStream) {
        return new SendCommandResponseFuture(byteArrayOutputStream);
    }

    public SendCommandResponseUpdate newSendCommandResponseUpdate(GattCharacteristicClient gattCharacteristicClient, ByteArrayOutputStream byteArrayOutputStream, FutureTask<byte[]> futureTask) {
        return new SendCommandResponseUpdate(gattCharacteristicClient, byteArrayOutputStream, futureTask);
    }
}
