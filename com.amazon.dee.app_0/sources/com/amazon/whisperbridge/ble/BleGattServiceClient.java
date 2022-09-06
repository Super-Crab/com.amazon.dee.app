package com.amazon.whisperbridge.ble;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import com.amazon.whisperbridge.ble.command.BleReadCharacteristicCommand;
import com.amazon.whisperbridge.ble.command.BleReadDescriptorCommand;
import com.amazon.whisperbridge.ble.command.BleWriteCharacteristicCommand;
import com.amazon.whisperbridge.ble.command.BleWriteDescriptorCommand;
import com.amazon.whisperbridge.ble.utility.ExecutorServiceFactory;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes13.dex */
public class BleGattServiceClient {
    private static final String TAG = "BleGattServiceClient";
    protected BleGattClient mBleClient;
    protected BluetoothGattService mBluetoothGattService;
    protected final ReadWriteLock mBoundObjectsLock;
    protected final Map<UUID, BleGattCharacteristic> mCharacteristics;
    protected ExecutorService mExecutor;
    private final ExecutorServiceFactory mExecutorServiceFactory;
    protected final UUID mServiceUuid;

    /* JADX INFO: Access modifiers changed from: protected */
    public BleGattServiceClient(UUID uuid) {
        this(uuid, new ExecutorServiceFactory() { // from class: com.amazon.whisperbridge.ble.BleGattServiceClient.1
            @Override // com.amazon.whisperbridge.ble.utility.ExecutorServiceFactory
            public ExecutorService newExecutorService() {
                return Executors.newSingleThreadExecutor();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void bindClient(BleGattClient bleGattClient, BluetoothGattService bluetoothGattService) {
        this.mBoundObjectsLock.writeLock().lock();
        try {
            if (bleGattClient == null) {
                throw new IllegalArgumentException("client cannot be null.");
            }
            if (bluetoothGattService != null) {
                if (this.mBleClient == null) {
                    if (this.mServiceUuid.equals(bluetoothGattService.getUuid())) {
                        this.mBleClient = bleGattClient;
                        this.mBluetoothGattService = bluetoothGattService;
                        this.mExecutor = this.mExecutorServiceFactory.newExecutorService();
                        for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                            this.mCharacteristics.put(bluetoothGattCharacteristic.getUuid(), BleGattCharacteristic.createCharacteristic(bluetoothGattCharacteristic));
                        }
                        return;
                    }
                    throw new IllegalArgumentException("Service client UUID does not match BluetoothGattService UUID.");
                }
                throw new IllegalStateException("Service client is already bound to a gatt client.");
            }
            throw new IllegalArgumentException("gattService cannot be null.");
        } finally {
            this.mBoundObjectsLock.writeLock().unlock();
        }
    }

    public boolean containsBleGattCharacteristic(UUID uuid) {
        if (uuid != null) {
            this.mBoundObjectsLock.readLock().lock();
            try {
                return this.mCharacteristics.containsKey(uuid);
            } finally {
                this.mBoundObjectsLock.readLock().unlock();
            }
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public BleGattCharacteristic getBleGattCharacteristic(UUID uuid) {
        if (uuid != null) {
            this.mBoundObjectsLock.readLock().lock();
            try {
                return this.mCharacteristics.get(uuid);
            } finally {
                this.mBoundObjectsLock.readLock().unlock();
            }
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public Collection<BleGattCharacteristic> getBleGattCharacteristics() {
        this.mBoundObjectsLock.readLock().lock();
        try {
            return Collections.unmodifiableCollection(this.mCharacteristics.values());
        } finally {
            this.mBoundObjectsLock.readLock().unlock();
        }
    }

    public UUID getServiceUuid() {
        return this.mServiceUuid;
    }

    public boolean isBound() {
        this.mBoundObjectsLock.readLock().lock();
        try {
            return this.mBleClient != null;
        } finally {
            this.mBoundObjectsLock.readLock().unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCharacteristicChanged(final BleGattCharacteristic bleGattCharacteristic) {
        this.mExecutor.submit(new Runnable() { // from class: com.amazon.whisperbridge.ble.BleGattServiceClient.2
            @Override // java.lang.Runnable
            public void run() {
                if (bleGattCharacteristic.getStoredData().length < BleGattServiceClient.this.mBleClient.getMtu()) {
                    BleGattServiceClient.this.onCharacteristicUpdated(bleGattCharacteristic);
                    return;
                }
                try {
                    if (BleGattServiceClient.this.readCharacteristic(bleGattCharacteristic.getUuid()).get().status != 0) {
                        WJLog.w(BleGattServiceClient.TAG, "Failed to updated changed characteristic.");
                    } else {
                        BleGattServiceClient.this.onCharacteristicUpdated(bleGattCharacteristic);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    String str = BleGattServiceClient.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception caught while getting future: ");
                    outline107.append(e.getMessage());
                    WJLog.d(str, outline107.toString());
                }
            }
        });
    }

    protected void onCharacteristicUpdated(BleGattCharacteristic bleGattCharacteristic) {
    }

    public Future<BleReadCharacteristicCommand.Result> readCharacteristic(UUID uuid) {
        this.mBoundObjectsLock.readLock().lock();
        try {
            if (this.mBleClient == null) {
                throw new IllegalArgumentException("Service client is not bound to BleGattClient.");
            }
            if (uuid != null) {
                if (this.mCharacteristics.containsKey(uuid)) {
                    return this.mBleClient.enqueueReadCharacteristic(this.mCharacteristics.get(uuid).getBluetoothGattCharacteristic());
                }
                throw new IllegalArgumentException("Service client does not contain characterstic with UUID " + uuid);
            }
            throw new IllegalArgumentException("uuid cannot be null.");
        } finally {
            this.mBoundObjectsLock.readLock().unlock();
        }
    }

    protected Future<BleReadDescriptorCommand.Result> readDescriptor(UUID uuid, UUID uuid2) {
        this.mBoundObjectsLock.readLock().lock();
        try {
            if (this.mBleClient == null) {
                throw new IllegalArgumentException("Service client is not bound to BleGattClient.");
            }
            if (uuid != null) {
                if (!this.mCharacteristics.containsKey(uuid)) {
                    throw new IllegalArgumentException("Service client does not contain characteristic with UUID " + uuid);
                } else if (uuid2 != null) {
                    if (this.mCharacteristics.get(uuid).containsBleDescriptor(uuid2)) {
                        return this.mBleClient.enqueueReadDescriptor(this.mCharacteristics.get(uuid).getBleDescriptor(uuid2).getBluetoothGattDescriptor());
                    }
                    throw new IllegalArgumentException("Characteristic does not contain a descriptor with UUID " + uuid2);
                } else {
                    throw new IllegalArgumentException("descriptorUuid cannot be null.");
                }
            }
            throw new IllegalArgumentException("characteristicUuid cannot be null.");
        } finally {
            this.mBoundObjectsLock.readLock().unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void unbindClient() {
        this.mBoundObjectsLock.writeLock().lock();
        try {
            if (this.mBleClient != null) {
                this.mExecutor.shutdownNow();
                this.mBleClient = null;
                this.mBluetoothGattService = null;
                this.mExecutor = null;
                this.mCharacteristics.clear();
                return;
            }
            throw new IllegalStateException("Service client is not bound.");
        } finally {
            this.mBoundObjectsLock.writeLock().unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0042, code lost:
        if (getBleGattCharacteristic(r4).isIndicatable() == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004c, code lost:
        throw new java.lang.IllegalArgumentException("Characteristic is not indicatable.");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.concurrent.Future<com.amazon.whisperbridge.ble.command.BleUpdateNotificationsCommand.Result> updateCharacteristicNotifiability(java.util.UUID r4, boolean r5, boolean r6) {
        /*
            r3 = this;
            java.util.concurrent.locks.ReadWriteLock r0 = r3.mBoundObjectsLock
            java.util.concurrent.locks.Lock r0 = r0.readLock()
            r0.lock()
            com.amazon.whisperbridge.ble.BleGattClient r0 = r3.mBleClient     // Catch: java.lang.Throwable -> Lbf
            if (r0 == 0) goto Lb7
            if (r4 == 0) goto Laf
            java.util.Map<java.util.UUID, com.amazon.whisperbridge.ble.BleGattCharacteristic> r0 = r3.mCharacteristics     // Catch: java.lang.Throwable -> Lbf
            boolean r0 = r0.containsKey(r4)     // Catch: java.lang.Throwable -> Lbf
            if (r0 == 0) goto L98
            com.amazon.whisperbridge.ble.BleGattCharacteristic r0 = r3.getBleGattCharacteristic(r4)     // Catch: java.lang.Throwable -> Lbf
            java.util.UUID r1 = com.amazon.whisperbridge.ble.BleConstants.CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR_UUID     // Catch: java.lang.Throwable -> Lbf
            boolean r0 = r0.containsBleDescriptor(r1)     // Catch: java.lang.Throwable -> Lbf
            if (r0 == 0) goto L90
            if (r5 == 0) goto L38
            com.amazon.whisperbridge.ble.BleGattCharacteristic r0 = r3.getBleGattCharacteristic(r4)     // Catch: java.lang.Throwable -> Lbf
            boolean r0 = r0.isNotifiable()     // Catch: java.lang.Throwable -> Lbf
            if (r0 == 0) goto L30
            goto L38
        L30:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r5 = "Characteristic is not notifiable."
            r4.<init>(r5)     // Catch: java.lang.Throwable -> Lbf
            throw r4     // Catch: java.lang.Throwable -> Lbf
        L38:
            if (r6 == 0) goto L4d
            com.amazon.whisperbridge.ble.BleGattCharacteristic r0 = r3.getBleGattCharacteristic(r4)     // Catch: java.lang.Throwable -> Lbf
            boolean r0 = r0.isIndicatable()     // Catch: java.lang.Throwable -> Lbf
            if (r0 == 0) goto L45
            goto L4d
        L45:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r5 = "Characteristic is not indicatable."
            r4.<init>(r5)     // Catch: java.lang.Throwable -> Lbf
            throw r4     // Catch: java.lang.Throwable -> Lbf
        L4d:
            com.amazon.whisperbridge.ble.BleGattCharacteristic r4 = r3.getBleGattCharacteristic(r4)     // Catch: java.lang.Throwable -> Lbf
            java.util.UUID r0 = com.amazon.whisperbridge.ble.BleConstants.CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR_UUID     // Catch: java.lang.Throwable -> Lbf
            com.amazon.whisperbridge.ble.BleGattDescriptor r4 = r4.getBleDescriptor(r0)     // Catch: java.lang.Throwable -> Lbf
            r0 = 0
            if (r5 == 0) goto L5f
            byte[] r1 = android.bluetooth.BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE     // Catch: java.lang.Throwable -> Lbf
            r1 = r1[r0]     // Catch: java.lang.Throwable -> Lbf
            goto L60
        L5f:
            r1 = r0
        L60:
            r1 = r1 | r0
            byte r1 = (byte) r1     // Catch: java.lang.Throwable -> Lbf
            if (r6 == 0) goto L69
            byte[] r2 = android.bluetooth.BluetoothGattDescriptor.ENABLE_INDICATION_VALUE     // Catch: java.lang.Throwable -> Lbf
            r2 = r2[r0]     // Catch: java.lang.Throwable -> Lbf
            goto L6a
        L69:
            r2 = r0
        L6a:
            r1 = r1 | r2
            byte r1 = (byte) r1     // Catch: java.lang.Throwable -> Lbf
            r2 = 2
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> Lbf
            r2[r0] = r1     // Catch: java.lang.Throwable -> Lbf
            r1 = 1
            r2[r1] = r0     // Catch: java.lang.Throwable -> Lbf
            r4.setStoredData(r2)     // Catch: java.lang.Throwable -> Lbf
            com.amazon.whisperbridge.ble.BleGattClient r2 = r3.mBleClient     // Catch: java.lang.Throwable -> Lbf
            android.bluetooth.BluetoothGattDescriptor r4 = r4.getBluetoothGattDescriptor()     // Catch: java.lang.Throwable -> Lbf
            if (r6 != 0) goto L81
            if (r5 == 0) goto L82
        L81:
            r0 = r1
        L82:
            java.util.concurrent.Future r4 = r2.enqueueUpdateNotifications(r4, r0)     // Catch: java.lang.Throwable -> Lbf
            java.util.concurrent.locks.ReadWriteLock r5 = r3.mBoundObjectsLock
            java.util.concurrent.locks.Lock r5 = r5.readLock()
            r5.unlock()
            return r4
        L90:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r5 = "Characteristic does not support notifications or indications."
            r4.<init>(r5)     // Catch: java.lang.Throwable -> Lbf
            throw r4     // Catch: java.lang.Throwable -> Lbf
        L98:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> Lbf
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbf
            r6.<init>()     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r0 = "Service client does not contain characteristic with UUID "
            r6.append(r0)     // Catch: java.lang.Throwable -> Lbf
            r6.append(r4)     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r4 = r6.toString()     // Catch: java.lang.Throwable -> Lbf
            r5.<init>(r4)     // Catch: java.lang.Throwable -> Lbf
            throw r5     // Catch: java.lang.Throwable -> Lbf
        Laf:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r5 = "characteristicUuid cannot be null."
            r4.<init>(r5)     // Catch: java.lang.Throwable -> Lbf
            throw r4     // Catch: java.lang.Throwable -> Lbf
        Lb7:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r5 = "Service client is not bound to BleGattClient."
            r4.<init>(r5)     // Catch: java.lang.Throwable -> Lbf
            throw r4     // Catch: java.lang.Throwable -> Lbf
        Lbf:
            r4 = move-exception
            java.util.concurrent.locks.ReadWriteLock r5 = r3.mBoundObjectsLock
            java.util.concurrent.locks.Lock r5 = r5.readLock()
            r5.unlock()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperbridge.ble.BleGattServiceClient.updateCharacteristicNotifiability(java.util.UUID, boolean, boolean):java.util.concurrent.Future");
    }

    public Future<BleWriteCharacteristicCommand.Result> writeCharacteristic(UUID uuid) {
        this.mBoundObjectsLock.readLock().lock();
        try {
            if (this.mBleClient == null) {
                throw new IllegalArgumentException("Service client is not bound to BleGattClient.");
            }
            if (uuid != null) {
                if (this.mCharacteristics.containsKey(uuid)) {
                    return this.mBleClient.enqueueWriteCharacteristic(this.mCharacteristics.get(uuid).getBluetoothGattCharacteristic());
                }
                throw new IllegalArgumentException("Service client does not contain characteristic with UUID " + uuid);
            }
            throw new IllegalArgumentException("uuid cannot be null.");
        } finally {
            this.mBoundObjectsLock.readLock().unlock();
        }
    }

    protected Future<BleWriteDescriptorCommand.Result> writeDescriptor(UUID uuid, UUID uuid2) {
        this.mBoundObjectsLock.readLock().lock();
        try {
            if (this.mBleClient == null) {
                throw new IllegalArgumentException("Service client is not bound to BleGattClient.");
            }
            if (uuid != null) {
                if (!this.mCharacteristics.containsKey(uuid)) {
                    throw new IllegalArgumentException("Service client does not contain characteristic with UUID " + uuid);
                } else if (uuid2 != null) {
                    if (this.mCharacteristics.get(uuid).containsBleDescriptor(uuid2)) {
                        return this.mBleClient.enqueueWriteDescriptor(this.mCharacteristics.get(uuid).getBleDescriptor(uuid2).getBluetoothGattDescriptor());
                    }
                    throw new IllegalArgumentException("Characteristic does not contain a descriptor with UUID " + uuid2);
                } else {
                    throw new IllegalArgumentException("descriptorUuid cannot be null.");
                }
            }
            throw new IllegalArgumentException("characteristicUuid cannot be null.");
        } finally {
            this.mBoundObjectsLock.readLock().unlock();
        }
    }

    protected BleGattServiceClient(UUID uuid, ExecutorServiceFactory executorServiceFactory) {
        this.mCharacteristics = new HashMap();
        this.mBoundObjectsLock = new ReentrantReadWriteLock(true);
        if (uuid != null) {
            if (executorServiceFactory != null) {
                this.mServiceUuid = uuid;
                this.mExecutorServiceFactory = executorServiceFactory;
                return;
            }
            throw new IllegalArgumentException("executorServiceFactory cannot be null.");
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }
}
