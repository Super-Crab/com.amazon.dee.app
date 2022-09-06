package com.amazon.whisperbridge.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import com.amazon.whisperbridge.ble.request.BleExecuteWriteRequest;
import com.amazon.whisperbridge.ble.request.BleReadCharacteristicRequest;
import com.amazon.whisperbridge.ble.request.BleReadDescriptorRequest;
import com.amazon.whisperbridge.ble.request.BleWriteCharacteristicRequest;
import com.amazon.whisperbridge.ble.request.BleWriteDescriptorRequest;
import com.amazon.whisperbridge.ble.utility.ExecutorServiceFactory;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes13.dex */
public class BleGattServer extends BluetoothGattServerCallback {
    private static final String TAG = BleGattServer.class.getSimpleName();
    private BluetoothGattServer mBluetoothGattServer;
    private final Callback mCallback;
    private final ExecutorService mGattServerExecutor;
    private Integer mNotificationResult;
    private final ReentrantLock mGattServerLock = new ReentrantLock(true);
    final Map<UUID, BleGattService> mBleServices = new HashMap();
    private final ReentrantReadWriteLock mServicesLock = new ReentrantReadWriteLock(true);
    private final Condition mServiceAddedCondition = this.mServicesLock.writeLock().newCondition();
    private final Object mNotificationSync = new Object();
    final Set<BluetoothDevice> mConnectedClients = new HashSet();
    private final Map<BluetoothDevice, Integer> mMtus = new HashMap();

    /* loaded from: classes13.dex */
    public interface Callback {
        void onConnectionStateChanged(BluetoothDevice bluetoothDevice, int i, int i2);
    }

    private BleGattServer(Callback callback, ExecutorService executorService) {
        this.mGattServerExecutor = executorService;
        this.mCallback = callback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BleGattServer createGattServer(Context context, BluetoothManager bluetoothManager, Callback callback) {
        return createGattServerWithExecutorServiceFactory(context, bluetoothManager, callback, new ExecutorServiceFactory() { // from class: com.amazon.whisperbridge.ble.BleGattServer.1
            @Override // com.amazon.whisperbridge.ble.utility.ExecutorServiceFactory
            public ExecutorService newExecutorService() {
                return Executors.newSingleThreadExecutor();
            }
        });
    }

    static BleGattServer createGattServerWithExecutorServiceFactory(Context context, BluetoothManager bluetoothManager, Callback callback, ExecutorServiceFactory executorServiceFactory) {
        if (context != null) {
            if (bluetoothManager == null) {
                throw new IllegalArgumentException("btManager cannot be null.");
            }
            if (executorServiceFactory != null) {
                BleGattServer bleGattServer = new BleGattServer(callback, executorServiceFactory.newExecutorService());
                bleGattServer.mBluetoothGattServer = bluetoothManager.openGattServer(context, bleGattServer);
                if (bleGattServer.mBluetoothGattServer == null) {
                    throw new IllegalStateException("Failed to open Android BluetoothGattServer.");
                }
                return bleGattServer;
            }
            throw new IllegalArgumentException("executorServiceFactory cannot be null.");
        }
        throw new IllegalArgumentException("context cannot be null.");
    }

    private BleGattService getServiceWithCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic != null) {
            BluetoothGattService service = bluetoothGattCharacteristic.getService();
            if (service != null) {
                return getBoundBleService(service.getUuid());
            }
            throw new IllegalArgumentException("BluetoothGattService retrieved from characteristic unexpectedly null!");
        }
        throw new IllegalArgumentException("characteristic unexpectedly null!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean bindBleService(final BleGattService bleGattService) {
        WJLog.d(TAG, "bindBleService");
        if (bleGattService != null) {
            if (!this.mBleServices.containsKey(bleGattService.getServiceUuid())) {
                this.mGattServerExecutor.submit(new Runnable() { // from class: com.amazon.whisperbridge.ble.BleGattServer.2
                    @Override // java.lang.Runnable
                    public void run() {
                        BleGattServer.this.mServicesLock.writeLock().lock();
                        BleGattServer.this.mGattServerLock.lock();
                        try {
                            BleGattServer.this.mBleServices.put(bleGattService.getServiceUuid(), bleGattService);
                            bleGattService.bindBleServer(BleGattServer.this);
                            BleGattServer.this.mBluetoothGattServer.addService(bleGattService.getBluetoothGattService());
                        } finally {
                            BleGattServer.this.mServicesLock.writeLock().unlock();
                            BleGattServer.this.mGattServerLock.unlock();
                        }
                    }
                });
                this.mServicesLock.writeLock().lock();
                boolean z = false;
                try {
                    try {
                        if (this.mServiceAddedCondition.awaitNanos(TimeUnit.SECONDS.toNanos(1L)) < 0) {
                            z = true;
                        }
                    } catch (InterruptedException unused) {
                        WJLog.wtf(TAG, "Interrupted while binding a service.");
                    }
                    return z;
                } finally {
                    this.mServicesLock.writeLock().unlock();
                }
            }
            throw new IllegalStateException("service already exists.");
        }
        throw new IllegalArgumentException("service cannot be null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void close() {
        WJLog.d(TAG, "close");
        this.mGattServerExecutor.shutdownNow();
        this.mGattServerLock.lock();
        try {
            if (this.mBluetoothGattServer != null) {
                this.mBluetoothGattServer.clearServices();
                this.mBluetoothGattServer.close();
                this.mConnectedClients.clear();
                this.mMtus.clear();
                this.mGattServerLock.unlock();
                unbindAllBleServices();
                return;
            }
            throw new IllegalStateException("BluetoothGattServer spruiously invalidated!");
        } catch (Throwable th) {
            this.mGattServerLock.unlock();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void connectClient(BluetoothDevice bluetoothDevice, boolean z) {
        WJLog.d(TAG, "connectClient");
        if (bluetoothDevice != null) {
            if (!this.mConnectedClients.contains(bluetoothDevice)) {
                this.mGattServerLock.lock();
                try {
                    this.mBluetoothGattServer.connect(bluetoothDevice, z);
                    return;
                } finally {
                    this.mGattServerLock.unlock();
                }
            }
            throw new IllegalArgumentException("btDevice is already connected as a client.");
        }
        throw new IllegalArgumentException("btDevice cannot be null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void disconnectClient(BluetoothDevice bluetoothDevice) {
        WJLog.d(TAG, "disconnectClient");
        if (bluetoothDevice != null) {
            if (this.mConnectedClients.contains(bluetoothDevice)) {
                this.mGattServerLock.lock();
                try {
                    this.mBluetoothGattServer.cancelConnection(bluetoothDevice);
                    return;
                } finally {
                    this.mGattServerLock.unlock();
                }
            }
            throw new IllegalArgumentException("btDevice is not connected as a client.");
        }
        throw new IllegalArgumentException("btDevice cannot be null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enqueueSendAckResponse(BluetoothDevice bluetoothDevice, int i, int i2, byte[] bArr) {
        WJLog.d(TAG, "enqueueSendAckResponse");
        enqueueSendResponse(bluetoothDevice, i, 0, i2, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enqueueSendFailureResponse(BluetoothDevice bluetoothDevice, int i, int i2) {
        WJLog.d(TAG, "enqueueSendFailureResponse");
        enqueueSendResponse(bluetoothDevice, i, 257, i2, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Future<Integer> enqueueSendNotification(final BluetoothDevice bluetoothDevice, final BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        WJLog.d(TAG, "enqueueSendNotification");
        if (bluetoothDevice != null) {
            if (bluetoothGattCharacteristic != null) {
                return this.mGattServerExecutor.submit(new Callable<Integer>() { // from class: com.amazon.whisperbridge.ble.BleGattServer.4
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.concurrent.Callable
                    /* renamed from: call */
                    public Integer mo5060call() {
                        Integer num;
                        BleGattServer.this.mGattServerLock.lock();
                        if (BleGattServer.this.mBluetoothGattServer == null) {
                            return 257;
                        }
                        try {
                            if (!BleGattServer.this.mBluetoothGattServer.notifyCharacteristicChanged(bluetoothDevice, bluetoothGattCharacteristic, true)) {
                                return 257;
                            }
                            BleGattServer.this.mGattServerLock.unlock();
                            synchronized (BleGattServer.this.mNotificationSync) {
                                while (BleGattServer.this.mNotificationResult == null) {
                                    try {
                                        BleGattServer.this.mNotificationSync.wait();
                                    } catch (InterruptedException unused) {
                                        WJLog.w(BleGattServer.TAG, "sendNotification interrupted while awaiting result.");
                                    }
                                }
                                num = BleGattServer.this.mNotificationResult;
                                BleGattServer.this.mNotificationResult = null;
                            }
                            return num;
                        } finally {
                            BleGattServer.this.mGattServerLock.unlock();
                        }
                    }
                });
            }
            throw new IllegalArgumentException("characteristic is unexpectedly null.");
        }
        throw new IllegalArgumentException("btDevice is unexpectedly null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enqueueSendResponse(final BluetoothDevice bluetoothDevice, final int i, final int i2, final int i3, final byte[] bArr) {
        WJLog.d(TAG, "enqueueSendResponse");
        if (bluetoothDevice != null) {
            if (i3 >= 0) {
                this.mGattServerExecutor.submit(new Runnable() { // from class: com.amazon.whisperbridge.ble.BleGattServer.3
                    @Override // java.lang.Runnable
                    public void run() {
                        BleGattServer.this.mGattServerLock.lock();
                        try {
                            if (BleGattServer.this.mBluetoothGattServer != null && BleGattServer.this.mConnectedClients.contains(bluetoothDevice) && !BleGattServer.this.mBluetoothGattServer.sendResponse(bluetoothDevice, i, i2, i3, bArr)) {
                                WJLog.w(BleGattServer.TAG, "Failed to initiate sendResponse on BluetoothGattServer.");
                            }
                        } finally {
                            BleGattServer.this.mGattServerLock.unlock();
                        }
                    }
                });
                return;
            }
            throw new IllegalArgumentException("offset is unexpectedly negative.");
        }
        throw new IllegalArgumentException("btDevice is unexpectedly null.");
    }

    BleGattService getBoundBleService(UUID uuid) {
        WJLog.d(TAG, "getBoundBleService");
        if (uuid != null) {
            if (this.mBleServices.containsKey(uuid)) {
                this.mServicesLock.readLock().lock();
                try {
                    return this.mBleServices.get(uuid);
                } finally {
                    this.mServicesLock.readLock().unlock();
                }
            }
            throw new IllegalArgumentException("No service with uuid=" + uuid);
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<BluetoothDevice> getConnectedClients() {
        return this.mConnectedClients;
    }

    public int getMtu(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null) {
            if (this.mMtus.containsKey(bluetoothDevice)) {
                return this.mMtus.get(bluetoothDevice).intValue();
            }
            throw new IllegalArgumentException("btDevice is not currently connected to server.");
        }
        throw new IllegalArgumentException("btDevice cannot be null.");
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onCharacteristicReadRequest(BluetoothDevice bluetoothDevice, int i, int i2, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onCharacteristicReadRequest: characteristic=");
        outline107.append(bluetoothGattCharacteristic.getUuid());
        outline107.append(", offset=");
        outline107.append(i2);
        WJLog.d(str, outline107.toString());
        BleGattService serviceWithCharacteristic = getServiceWithCharacteristic(bluetoothGattCharacteristic);
        if (serviceWithCharacteristic == null) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("onCharacteristicReadRequest called for missing service ");
            outline1072.append(bluetoothGattCharacteristic.getService().getUuid());
            WJLog.e(str2, outline1072.toString());
        } else if (!serviceWithCharacteristic.isValidReadCharacteristic(bluetoothGattCharacteristic.getUuid())) {
            String str3 = TAG;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("onCharacteristicReadRequest called for non-read characteristic ");
            outline1073.append(bluetoothGattCharacteristic.getUuid());
            WJLog.e(str3, outline1073.toString());
        } else {
            this.mServicesLock.readLock().lock();
            try {
                serviceWithCharacteristic.enqueueRequest(new BleReadCharacteristicRequest(bluetoothDevice, i, i2, serviceWithCharacteristic.getBleCharacteristic(bluetoothGattCharacteristic.getUuid())));
            } finally {
                this.mServicesLock.readLock().unlock();
            }
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onCharacteristicWriteRequest(BluetoothDevice bluetoothDevice, int i, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, boolean z2, int i2, byte[] bArr) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onCharacteristicWriteRequest: char= ");
        outline107.append(bluetoothGattCharacteristic.getUuid());
        outline107.append(", preparedWrite=");
        outline107.append(z);
        outline107.append(", responseNeeded=");
        outline107.append(z2);
        outline107.append(", offset=");
        outline107.append(i2);
        WJLog.d(str, outline107.toString());
        BleGattService serviceWithCharacteristic = getServiceWithCharacteristic(bluetoothGattCharacteristic);
        if (serviceWithCharacteristic == null) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("onCharacteristicWriteRequest called for missing service ");
            outline1072.append(bluetoothGattCharacteristic.getService().getUuid());
            WJLog.e(str2, outline1072.toString());
            if (!z2) {
                return;
            }
            enqueueSendFailureResponse(bluetoothDevice, i, i2);
        } else if (!serviceWithCharacteristic.isValidWriteCharacteristic(bluetoothGattCharacteristic.getUuid())) {
            String str3 = TAG;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("onCharacteristicWriteRequest called for non-write characteristic ");
            outline1073.append(bluetoothGattCharacteristic.getUuid());
            WJLog.e(str3, outline1073.toString());
            if (!z2) {
                return;
            }
            enqueueSendFailureResponse(bluetoothDevice, i, i2);
        } else {
            this.mServicesLock.readLock().lock();
            try {
                serviceWithCharacteristic.enqueueRequest(new BleWriteCharacteristicRequest(bluetoothDevice, i, serviceWithCharacteristic.getBleCharacteristic(bluetoothGattCharacteristic.getUuid()), z, z2, i2, bArr));
            } finally {
                this.mServicesLock.readLock().unlock();
            }
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onConnectionStateChange(BluetoothDevice bluetoothDevice, int i, int i2) {
        String str = TAG;
        WJLog.d(str, "onConnectionStateChange: status=" + i + ", new state=" + i2);
        if (bluetoothDevice != null) {
            if (i2 == 2 && i == 0) {
                this.mConnectedClients.add(bluetoothDevice);
                this.mMtus.put(bluetoothDevice, 23);
            } else if (i2 == 0) {
                this.mConnectedClients.remove(bluetoothDevice);
                this.mMtus.remove(bluetoothDevice);
            }
            this.mCallback.onConnectionStateChanged(bluetoothDevice, i, i2);
            return;
        }
        throw new IllegalArgumentException("btDevice is unexpectedly null.");
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onDescriptorReadRequest(BluetoothDevice bluetoothDevice, int i, int i2, BluetoothGattDescriptor bluetoothGattDescriptor) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDescriptorReadRequest: descriptor=");
        outline107.append(bluetoothGattDescriptor.getUuid());
        outline107.append(", characteristic=");
        outline107.append(bluetoothGattDescriptor.getCharacteristic().getUuid());
        outline107.append(", offset=");
        outline107.append(i2);
        WJLog.d(str, outline107.toString());
        BleGattService serviceWithCharacteristic = getServiceWithCharacteristic(bluetoothGattDescriptor.getCharacteristic());
        if (serviceWithCharacteristic == null) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("onDescriptorReadRequest called for missing service ");
            outline1072.append(bluetoothGattDescriptor.getCharacteristic().getService().getUuid());
            WJLog.e(str2, outline1072.toString());
        } else if (!serviceWithCharacteristic.isValidReadDescriptor(bluetoothGattDescriptor.getCharacteristic().getUuid(), bluetoothGattDescriptor.getUuid())) {
            String str3 = TAG;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("onDescriptorReadRequest called for non-read characteristic ");
            outline1073.append(bluetoothGattDescriptor.getUuid());
            WJLog.e(str3, outline1073.toString());
        } else {
            this.mServicesLock.readLock().lock();
            try {
                serviceWithCharacteristic.enqueueRequest(new BleReadDescriptorRequest(bluetoothDevice, i, i2, serviceWithCharacteristic.getBleCharacteristic(bluetoothGattDescriptor.getCharacteristic().getUuid()).getBleDescriptor(bluetoothGattDescriptor.getUuid())));
            } finally {
                this.mServicesLock.readLock().unlock();
            }
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onDescriptorWriteRequest(BluetoothDevice bluetoothDevice, int i, BluetoothGattDescriptor bluetoothGattDescriptor, boolean z, boolean z2, int i2, byte[] bArr) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onDescriptorWriteRequest: descriptor=");
        outline107.append(bluetoothGattDescriptor.getUuid());
        outline107.append(", characteristic=");
        outline107.append(bluetoothGattDescriptor.getCharacteristic().getUuid());
        outline107.append(", preparedWrite=");
        outline107.append(z);
        outline107.append(", responseNeeded=");
        outline107.append(z2);
        outline107.append(", offset=");
        outline107.append(i2);
        WJLog.d(str, outline107.toString());
        BleGattService serviceWithCharacteristic = getServiceWithCharacteristic(bluetoothGattDescriptor.getCharacteristic());
        if (serviceWithCharacteristic == null) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("onDescriptorWriteRequest called for missing service ");
            outline1072.append(bluetoothGattDescriptor.getCharacteristic().getService().getUuid());
            WJLog.e(str2, outline1072.toString());
            if (!z2) {
                return;
            }
            enqueueSendFailureResponse(bluetoothDevice, i, i2);
        } else if (!serviceWithCharacteristic.isValidWriteDescriptor(bluetoothGattDescriptor.getCharacteristic().getUuid(), bluetoothGattDescriptor.getUuid())) {
            String str3 = TAG;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("onDescriptorWriteRequest called for non-write descriptor ");
            outline1073.append(bluetoothGattDescriptor.getUuid());
            WJLog.e(str3, outline1073.toString());
            if (!z2) {
                return;
            }
            enqueueSendFailureResponse(bluetoothDevice, i, i2);
        } else {
            this.mServicesLock.readLock().lock();
            try {
                serviceWithCharacteristic.enqueueRequest(new BleWriteDescriptorRequest(bluetoothDevice, i, serviceWithCharacteristic.getBleCharacteristic(bluetoothGattDescriptor.getCharacteristic().getUuid()).getBleDescriptor(bluetoothGattDescriptor.getUuid()), z, z2, i2, bArr));
            } finally {
                this.mServicesLock.readLock().unlock();
            }
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onExecuteWrite(BluetoothDevice bluetoothDevice, int i, boolean z) {
        String str = TAG;
        WJLog.d(str, "onExecuteWrite: requestID=" + i + ", execute=" + z);
        this.mServicesLock.readLock().lock();
        try {
            for (Map.Entry<UUID, BleGattService> entry : this.mBleServices.entrySet()) {
                entry.getValue().enqueueRequest(new BleExecuteWriteRequest(bluetoothDevice, i, z));
            }
        } finally {
            this.mServicesLock.readLock().unlock();
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onMtuChanged(BluetoothDevice bluetoothDevice, int i) {
        String str = TAG;
        WJLog.d(str, "onMtuChanged: mtu=" + i);
        if (this.mMtus.containsKey(bluetoothDevice)) {
            this.mMtus.put(bluetoothDevice, Integer.valueOf(i));
            return;
        }
        throw new IllegalArgumentException("MTU updated for an unseen client connection.");
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onNotificationSent(BluetoothDevice bluetoothDevice, int i) {
        String str = TAG;
        WJLog.d(str, "onNotificationSent: status=" + i);
        synchronized (this.mNotificationSync) {
            this.mNotificationResult = Integer.valueOf(i);
            this.mNotificationSync.notify();
        }
    }

    @Override // android.bluetooth.BluetoothGattServerCallback
    public void onServiceAdded(int i, BluetoothGattService bluetoothGattService) {
        String str = TAG;
        WJLog.d(str, "onServiceAdded: status=" + i);
        this.mServicesLock.writeLock().lock();
        if (i != 0) {
            try {
                this.mBleServices.remove(bluetoothGattService.getUuid());
            } finally {
                this.mServicesLock.writeLock().unlock();
            }
        }
        this.mServiceAddedCondition.signal();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean sendIndication(BluetoothDevice bluetoothDevice, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        WJLog.d(TAG, "enqueueSendIndication");
        if (bluetoothDevice != null) {
            if (bluetoothGattCharacteristic != null) {
                BluetoothGattServer bluetoothGattServer = this.mBluetoothGattServer;
                return bluetoothGattServer != null && bluetoothGattServer.notifyCharacteristicChanged(bluetoothDevice, bluetoothGattCharacteristic, false);
            }
            throw new IllegalArgumentException("characteristic is unexpectedly null.");
        }
        throw new IllegalArgumentException("btDevice is unexpectedly null.");
    }

    void unbindAllBleServices() {
        WJLog.d(TAG, "unbindAllBleServices");
        this.mServicesLock.writeLock().lock();
        try {
            for (Map.Entry<UUID, BleGattService> entry : this.mBleServices.entrySet()) {
                entry.getValue().unbindBleServer();
            }
            this.mBleServices.clear();
        } finally {
            this.mServicesLock.writeLock().unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unbindBleService(BleGattService bleGattService) {
        WJLog.d(TAG, "unbindBleService");
        if (bleGattService != null) {
            if (this.mBleServices.containsKey(bleGattService.getServiceUuid())) {
                this.mServicesLock.writeLock().lock();
                try {
                    this.mBluetoothGattServer.removeService(bleGattService.getBluetoothGattService());
                    this.mBleServices.remove(bleGattService.getServiceUuid());
                    bleGattService.unbindBleServer();
                    return;
                } finally {
                    this.mServicesLock.writeLock().unlock();
                }
            }
            throw new IllegalStateException("service does not exist.");
        }
        throw new IllegalArgumentException("service cannot be null.");
    }
}
