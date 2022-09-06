package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.amazon.whisperbridge.ProvisioningStatus;
import com.amazon.whisperbridge.Transport;
import com.amazon.whisperbridge.ble.BleGattClient;
import com.amazon.whisperbridge.ble.BleManager;
import com.amazon.whisperbridge.constants.Command;
import com.amazon.whisperjoin.common.sharedtypes.exceptions.RadioNotEnabledException;
import com.amazon.whisperjoin.common.sharedtypes.utility.EncodingHelpers;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.provisionerSDK.radios.GenericPromiseFactory;
import com.amazon.whisperjoin.provisionerSDK.radios.RetryHandler;
import com.amazon.whisperjoin.provisionerSDK.radios.error.NoConnectedClientsException;
import com.amazon.whisperjoin.provisionerSDK.radios.error.TransportOperationRuntimeException;
import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class BLETransport implements Transport {
    private static final String CONNECT = "Connect";
    private static final String SEND_COMMAND = "Send Command : ";
    private static final String START_PROVISIONING = "Start Provisioning";
    private static final String TAG = "com.amazon.whisperjoin.provisionerSDK.radios.ble.BLETransport";
    private final Map<BluetoothDevice, BLEClients> mBleClients = new HashMap();
    private final BleManager mBleManager;
    private final ExecutorService mCommandExecutor;
    private final Context mContext;
    private final EncodingHelpers mEncodingHelpers;
    private final long mOperationRetryCount;
    private final long mOperationTimeout;
    private final TimeUnit mOperationTimeoutMs;
    private final ExecutorService mRetryExecutor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BLETransport(Context context, BleManager bleManager, EncodingHelpers encodingHelpers, long j, TimeUnit timeUnit, long j2, ExecutorService executorService, ExecutorService executorService2) {
        this.mContext = context;
        this.mBleManager = bleManager;
        this.mEncodingHelpers = encodingHelpers;
        this.mCommandExecutor = executorService;
        this.mRetryExecutor = executorService2;
        this.mOperationTimeout = j;
        this.mOperationTimeoutMs = timeUnit;
        this.mOperationRetryCount = j2;
    }

    private BLEClients getClientsFromDeviceHandle(Object obj) {
        return getConnectedClients(getDeviceFromHandle(obj));
    }

    private BLEClients getConnectedClientsFromDeviceHandle(Object obj) throws NoConnectedClientsException {
        BLEClients clientsFromDeviceHandle = getClientsFromDeviceHandle(obj);
        if (clientsFromDeviceHandle != null) {
            return clientsFromDeviceHandle;
        }
        WJLog.e(TAG, "No Clients Connected");
        throw new NoConnectedClientsException();
    }

    private BluetoothDevice getDeviceFromHandle(Object obj) {
        if (obj instanceof BluetoothDevice) {
            return (BluetoothDevice) obj;
        }
        throw new IllegalArgumentException("Device handle is not a Bluetooth Device");
    }

    @Override // com.amazon.whisperbridge.Transport
    public void close(Object obj) {
        BluetoothDevice deviceFromHandle = getDeviceFromHandle(obj);
        BLEClients clientsFromDeviceHandle = getClientsFromDeviceHandle(deviceFromHandle);
        if (clientsFromDeviceHandle == null) {
            return;
        }
        synchronized (this.mBleClients) {
            this.mBleClients.remove(deviceFromHandle);
        }
        clientsFromDeviceHandle.getGattClient().close();
    }

    @Override // com.amazon.whisperbridge.Transport
    public Future<Void> connect(Object obj, Transport.DeviceConnectionStateChangedListener deviceConnectionStateChangedListener) throws RadioNotEnabledException, IOException {
        if (getClientsFromDeviceHandle(obj) != null) {
            return Futures.immediateFuture(null);
        }
        return new RetryHandler(new GattConnectionPromiseFactory(new CreateConnection(this.mBleManager, this.mBleClients, this.mContext, (BluetoothDevice) obj, deviceConnectionStateChangedListener), this.mCommandExecutor), this.mRetryExecutor).startAttempts(this.mOperationTimeout, this.mOperationTimeoutMs, this.mOperationRetryCount, "Connect");
    }

    BLEClients getConnectedClients(BluetoothDevice bluetoothDevice) {
        BleGattClient gattClient;
        synchronized (this.mBleClients) {
            BLEClients bLEClients = this.mBleClients.get(bluetoothDevice);
            if (bLEClients == null || (gattClient = bLEClients.getGattClient()) == null || !gattClient.isConnected(this.mContext)) {
                return null;
            }
            return bLEClients;
        }
    }

    @Override // com.amazon.whisperbridge.Transport
    public Future<Void> onProvisioningComplete(Object obj) {
        BLEClients clientsFromDeviceHandle = getClientsFromDeviceHandle(obj);
        if (clientsFromDeviceHandle != null) {
            BleGattClient gattClient = clientsFromDeviceHandle.getGattClient();
            if (gattClient != null) {
                return Futures.lazyTransform(gattClient.enqueueRequestConnectionPriorityChange(2), new Function<Boolean, Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.radios.ble.BLETransport.1
                    @Override // com.google.common.base.Function
                    /* renamed from: apply  reason: avoid collision after fix types in other method */
                    public Void mo8172apply(Boolean bool) {
                        if (bool.booleanValue()) {
                            return null;
                        }
                        throw new TransportOperationRuntimeException("failed to change connection priority");
                    }
                });
            }
            throw new TransportOperationRuntimeException("No BLE gatt client exists for given BLEClient");
        }
        throw new TransportOperationRuntimeException("No BLE client exists for given device handle");
    }

    @Override // com.amazon.whisperbridge.Transport
    public Future<byte[]> sendCommand(Object obj, Command command, byte[] bArr) throws RadioNotEnabledException, IOException {
        try {
            RetryHandler retryHandler = new RetryHandler(new GenericPromiseFactory(new SendCommand(getConnectedClientsFromDeviceHandle(obj).getServiceClient(), bArr, command, this.mEncodingHelpers), this.mCommandExecutor), this.mRetryExecutor);
            long j = this.mOperationTimeout;
            TimeUnit timeUnit = this.mOperationTimeoutMs;
            long j2 = this.mOperationRetryCount;
            return retryHandler.startAttempts(j, timeUnit, j2, SEND_COMMAND + command);
        } catch (Exception e) {
            throw new TransportOperationRuntimeException(SEND_COMMAND + command, e);
        }
    }

    @Override // com.amazon.whisperbridge.Transport
    public void setProvisionableEventNotificationCallback(Object obj, Transport.ProvisionableEventNotificationCallback provisionableEventNotificationCallback) throws IOException {
        GattCharacteristicClient serviceClient = getConnectedClientsFromDeviceHandle(obj).getServiceClient();
        serviceClient.addPendingUpdate(new ProvisionableNotificationGattCharacteristicPendingUpdate(provisionableEventNotificationCallback));
        serviceClient.enableProvisionableEventNotifications();
    }

    @Override // com.amazon.whisperbridge.Transport
    public Future<ProvisioningStatus> startProvisioning(Object obj) throws RadioNotEnabledException, IOException {
        try {
            return new RetryHandler(new GenericPromiseFactory(new StartProvisioning(getConnectedClientsFromDeviceHandle(obj).getServiceClient()), this.mCommandExecutor), this.mRetryExecutor).startAttempts(this.mOperationTimeout, this.mOperationTimeoutMs, this.mOperationRetryCount, START_PROVISIONING);
        } catch (Exception e) {
            throw new TransportOperationRuntimeException(START_PROVISIONING, e);
        }
    }
}
