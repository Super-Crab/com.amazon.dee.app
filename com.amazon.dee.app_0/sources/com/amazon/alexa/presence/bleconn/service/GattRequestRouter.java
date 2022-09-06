package com.amazon.alexa.presence.bleconn.service;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.util.Log;
import com.amazon.alexa.presence.bleconn.service.RequestImpl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes9.dex */
public class GattRequestRouter extends BluetoothGattServerCallback {
    private static final String TAG = GattRequestRouter.class.getName();
    private final ServiceContext serverContext;
    private final Map<UUID, CharacteristicReadRequestHandler> characteristicReadDispatchMap = new HashMap();
    private final Map<UUID, CharacteristicWriteRequestHandler> characteristicWriteDispatchMap = new HashMap();
    private final AtomicBoolean serviceSuccessfullyAdded = new AtomicBoolean(false);

    public GattRequestRouter(ServiceContext serviceContext) {
        Objects.requireNonNull(serviceContext);
        this.serverContext = serviceContext;
    }

    public boolean awaitServiceAdded(int i) {
        long j = i;
        long currentTimeMillis = System.currentTimeMillis() + j;
        while (System.currentTimeMillis() < currentTimeMillis) {
            if (this.serviceSuccessfullyAdded.get()) {
                long currentTimeMillis2 = j - (currentTimeMillis - System.currentTimeMillis());
                Log.i(TAG, "Waited " + currentTimeMillis2 + "ms before service was added");
                return true;
            }
            try {
                Thread.sleep(250L);
            } catch (InterruptedException unused) {
                Log.w(TAG, "Interrupted while waiting for service add.");
            }
        }
        return this.serviceSuccessfullyAdded.get();
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onCharacteristicReadRequest(BluetoothDevice bluetoothDevice, int i, int i2, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        String.format("Request: %s, Client Name: %s, Client Address: %s, Characteristic: %s, Offset: %s", Integer.valueOf(i), bluetoothDevice.getName(), bluetoothDevice.getAddress(), bluetoothGattCharacteristic.getUuid(), Integer.valueOf(i2));
        CharacteristicReadRequestHandler characteristicReadRequestHandler = this.characteristicReadDispatchMap.get(bluetoothGattCharacteristic.getUuid());
        RequestImpl requestImpl = new RequestImpl(this.serverContext, bluetoothDevice, i, i2);
        if (characteristicReadRequestHandler != null) {
            characteristicReadRequestHandler.onCharacteristicReadRequest(requestImpl);
        } else {
            Log.i(TAG, String.format("Read request received for characteristic with no handler: Characteristic Id: %s", bluetoothGattCharacteristic.getUuid()));
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onCharacteristicWriteRequest(BluetoothDevice bluetoothDevice, int i, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, boolean z2, int i2, byte[] bArr) {
        super.onCharacteristicWriteRequest(bluetoothDevice, i, bluetoothGattCharacteristic, z, z2, i2, bArr);
        String.format("Request: %s, Client Name: %s, Client Address: %s, Characteristic: %s, Offset: %s", Integer.valueOf(i), bluetoothDevice.getName(), bluetoothDevice.getAddress(), bluetoothGattCharacteristic.getUuid(), Integer.valueOf(i2));
        String.format("preparedWrite? %s", Boolean.valueOf(z));
        String.format("Response needed? %s", Boolean.valueOf(z2));
        String.format("Data Length: %s", Integer.valueOf(bArr.length));
        CharacteristicWriteRequestHandler characteristicWriteRequestHandler = this.characteristicWriteDispatchMap.get(bluetoothGattCharacteristic.getUuid());
        Request build = new RequestImpl.Builder(this.serverContext, bluetoothDevice, i, i2).withRequiresResponse(z2).withData(bArr).build();
        if (characteristicWriteRequestHandler != null) {
            characteristicWriteRequestHandler.onCharacteristicWriteRequest(build);
        } else {
            Log.i(TAG, String.format("Write request received for characteristic with no handler: Characteristic Id: %s", bluetoothGattCharacteristic.getUuid()));
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onExecuteWrite(BluetoothDevice bluetoothDevice, int i, boolean z) {
        super.onExecuteWrite(bluetoothDevice, i, z);
        String.format("Request: %s, Client Name: %s, Client Address: %s", Integer.valueOf(i), bluetoothDevice.getName(), bluetoothDevice.getAddress());
        Request build = new RequestImpl.Builder(this.serverContext, bluetoothDevice, i, 0).build();
        for (CharacteristicWriteRequestHandler characteristicWriteRequestHandler : this.characteristicWriteDispatchMap.values()) {
            characteristicWriteRequestHandler.onCharacteristicExecuteWriteRequest(build);
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onServiceAdded(int i, BluetoothGattService bluetoothGattService) {
        super.onServiceAdded(i, bluetoothGattService);
        this.serviceSuccessfullyAdded.set(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GattRequestRouter registerReadCharacteristicRoute(BluetoothGattCharacteristic bluetoothGattCharacteristic, CharacteristicReadRequestHandler characteristicReadRequestHandler) {
        Objects.requireNonNull(bluetoothGattCharacteristic);
        Objects.requireNonNull(characteristicReadRequestHandler);
        if (!this.characteristicReadDispatchMap.containsKey(bluetoothGattCharacteristic.getUuid())) {
            this.characteristicReadDispatchMap.put(bluetoothGattCharacteristic.getUuid(), characteristicReadRequestHandler);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72(String.format("Route %s already registered for reads to handler %s.", bluetoothGattCharacteristic.getUuid(), this.characteristicReadDispatchMap.get(bluetoothGattCharacteristic.getUuid())), String.format("Could not register %s", characteristicReadRequestHandler)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GattRequestRouter registerWriteCharacteristicRoute(BluetoothGattCharacteristic bluetoothGattCharacteristic, CharacteristicWriteRequestHandler characteristicWriteRequestHandler) {
        Objects.requireNonNull(bluetoothGattCharacteristic);
        Objects.requireNonNull(characteristicWriteRequestHandler);
        if (!this.characteristicWriteDispatchMap.containsKey(bluetoothGattCharacteristic.getUuid())) {
            this.characteristicWriteDispatchMap.put(bluetoothGattCharacteristic.getUuid(), characteristicWriteRequestHandler);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72(String.format("Route %s already registered for writes to handler %s.", bluetoothGattCharacteristic.getUuid(), this.characteristicWriteDispatchMap.get(bluetoothGattCharacteristic.getUuid())), String.format("Could not register %s", characteristicWriteRequestHandler)));
    }
}
