package com.amazon.alexa.presence.bleconn.service;

import android.bluetooth.BluetoothDevice;
import java.util.Objects;
/* loaded from: classes9.dex */
public class RequestImpl implements Request {
    private final Client client;
    private final ServiceContext context;
    private final int offset;
    private final int requestId;
    private final ResponseImpl response;
    private boolean requiresResponse = true;
    private byte[] data = null;

    /* loaded from: classes9.dex */
    public static class Builder {
        private RequestImpl request;

        public Builder(ServiceContext serviceContext, BluetoothDevice bluetoothDevice, int i, int i2) {
            Objects.requireNonNull(serviceContext);
            Objects.requireNonNull(bluetoothDevice);
            this.request = new RequestImpl(serviceContext, bluetoothDevice, i, i2);
        }

        public Request build() {
            return this.request;
        }

        public Builder withData(byte[] bArr) {
            this.request.data = bArr;
            return this;
        }

        public Builder withRequiresResponse(boolean z) {
            this.request.requiresResponse = z;
            return this;
        }
    }

    public RequestImpl(ServiceContext serviceContext, BluetoothDevice bluetoothDevice, int i, int i2) {
        Objects.requireNonNull(serviceContext);
        Objects.requireNonNull(bluetoothDevice);
        this.context = serviceContext;
        this.client = new ClientImpl(bluetoothDevice);
        this.requestId = i;
        this.offset = i2;
        this.response = new ResponseImpl(serviceContext, bluetoothDevice, i);
    }

    @Override // com.amazon.alexa.presence.bleconn.service.Request
    public Client getClient() {
        return this.client;
    }

    @Override // com.amazon.alexa.presence.bleconn.service.Request
    public byte[] getData() {
        return this.data;
    }

    @Override // com.amazon.alexa.presence.bleconn.service.Request
    public int getOffset() {
        return this.offset;
    }

    @Override // com.amazon.alexa.presence.bleconn.service.Request
    public int getRequestId() {
        return this.requestId;
    }

    @Override // com.amazon.alexa.presence.bleconn.service.Request
    public boolean requiresResponse() {
        return this.requiresResponse;
    }

    @Override // com.amazon.alexa.presence.bleconn.service.Request
    /* renamed from: getResponse  reason: collision with other method in class */
    public ResponseImpl mo2296getResponse() {
        return this.response;
    }
}
