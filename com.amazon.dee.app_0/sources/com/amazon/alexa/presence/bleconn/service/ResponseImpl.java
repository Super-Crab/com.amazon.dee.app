package com.amazon.alexa.presence.bleconn.service;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattServer;
import android.util.Log;
import com.amazon.alexa.presence.bleconn.service.handlers.IdentityRequestHandler;
import java.util.Objects;
/* loaded from: classes9.dex */
public class ResponseImpl implements Response {
    private static final int MAX_TRANSMISSION_DATA_SIZE_BYTES = 22;
    private static final String TAG = IdentityRequestHandler.class.getName();
    private final ServiceContext context;
    private final BluetoothDevice device;
    private final int requestId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResponseImpl(ServiceContext serviceContext, BluetoothDevice bluetoothDevice, int i) {
        Objects.requireNonNull(serviceContext);
        Objects.requireNonNull(bluetoothDevice);
        this.context = serviceContext;
        this.device = bluetoothDevice;
        this.requestId = i;
    }

    private void sendResponse(int i, int i2, byte[] bArr) {
        BluetoothGattServer bluetoothGattServer = this.context.server().get();
        if (bluetoothGattServer != null) {
            bluetoothGattServer.sendResponse(this.device, this.requestId, i, i2, bArr);
        } else {
            Log.w(TAG, "Attempted to write a response, but could not resolve the server");
            throw new IllegalStateException("Attempted to write a response, but could not resolve the server");
        }
    }

    @Override // com.amazon.alexa.presence.bleconn.service.Response
    public void sendMultipartResponseSuccess(int i, byte[] bArr) {
        sendResponse(0, i, bArr);
    }

    @Override // com.amazon.alexa.presence.bleconn.service.Response
    public void sendResponseFailure() {
        sendResponse(257, 0, new byte[0]);
    }

    @Override // com.amazon.alexa.presence.bleconn.service.Response
    public void sendResponseSuccess(byte[] bArr) {
        if (bArr.length <= 22) {
            sendMultipartResponseSuccess(0, bArr);
            return;
        }
        throw new IllegalArgumentException("Response too large");
    }

    @Override // com.amazon.alexa.presence.bleconn.service.Response
    public void sendResponseTest() {
        this.context.server().get().sendResponse(this.device, this.requestId, 0, 0, null);
    }
}
