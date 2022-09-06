package com.amazon.whisperbridge.ble;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.whisperbridge.ble.command.BleChangeConnectionPriorityCommand;
import com.amazon.whisperbridge.ble.command.BleChangeMtuCommand;
import com.amazon.whisperbridge.ble.command.BleCommand;
import com.amazon.whisperbridge.ble.command.BleCommandExecutor;
import com.amazon.whisperbridge.ble.command.BleDiscoverServicesCommand;
import com.amazon.whisperbridge.ble.command.BleReadCharacteristicCommand;
import com.amazon.whisperbridge.ble.command.BleReadDescriptorCommand;
import com.amazon.whisperbridge.ble.command.BleReadRssiCommand;
import com.amazon.whisperbridge.ble.command.BleUpdateNotificationsCommand;
import com.amazon.whisperbridge.ble.command.BleWriteCharacteristicCommand;
import com.amazon.whisperbridge.ble.command.BleWriteDescriptorCommand;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.inject.Inject;
/* loaded from: classes13.dex */
public class BleGattClient {
    private static final String CONNECT_GATT_METHOD_NAME = "connectGatt";
    private static final String TAG = "com.amazon.whisperbridge.ble.BleGattClient";
    private final BluetoothDevice mBluetoothDevice;
    private BluetoothGatt mBluetoothGatt;
    private final Callback mCallback;
    @Inject
    BleCommandExecutor mClientCommandExecutor;
    private final Queue<BleCommand> mCommandQueue;
    private final Lock mCommandLock = new ReentrantLock(true);
    private final BleGattCallback mGattCallback = new BleGattCallback();
    private int mMtu = 23;
    private final Map<UUID, BleGattServiceClient> mServiceClients = new HashMap();
    private final ReadWriteLock mServiceClientsLock = new ReentrantReadWriteLock(true);

    /* loaded from: classes13.dex */
    public class BleGattCallback extends BluetoothGattCallback {
        public BleGattCallback() {
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            BleGattClient.this.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            BleGattClient.this.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            BleGattClient.this.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
        }

        public final void onConnectionPriorityChange(BluetoothGatt bluetoothGatt, boolean z) {
            BleGattClient.this.onConnectionPriorityChange(bluetoothGatt, z);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            BleGattClient.this.onConnectionStateChange(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            BleGattClient.this.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            BleGattClient.this.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            BleGattClient.this.onMtuChanged(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            BleGattClient.this.onReadRemoteRssi(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
            BleGattClient.this.onReliableWriteCompleted(bluetoothGatt, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public final void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            BleGattClient.this.onServicesDiscovered(bluetoothGatt, i);
        }
    }

    /* loaded from: classes13.dex */
    public interface Callback {
        void onConnectionStateChanged(BleGattClient bleGattClient, int i, int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BleGattClient(BluetoothDevice bluetoothDevice, Callback callback, Queue<BleCommand> queue) {
        DaggerBleGattClientComponent.create().inject(this);
        this.mBluetoothDevice = bluetoothDevice;
        this.mCallback = callback;
        this.mCommandQueue = queue;
    }

    private void performSanityCheck(BluetoothGatt bluetoothGatt) throws AssertionError {
        if (this.mBluetoothGatt == bluetoothGatt) {
            if (this.mCommandQueue.isEmpty()) {
                throw new AssertionError("mCommandQueue unexpectedly empty.");
            }
            return;
        }
        throw new AssertionError("Callback GATT does not match client GATT.");
    }

    public final void bindAllServiceClients(Set<BleGattServiceClient> set) throws BleException {
        WJLog.d(TAG, "bindAllServiceClients");
        if (set != null) {
            for (BleGattServiceClient bleGattServiceClient : set) {
                if (!bindServiceClient(bleGattServiceClient)) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to bind service client with UUID=");
                    outline107.append(bleGattServiceClient.getServiceUuid());
                    throw new BleException(outline107.toString());
                }
            }
            return;
        }
        throw new IllegalArgumentException("serviceClients cannot be null.");
    }

    public final boolean bindAnyServiceClients(Set<BleGattServiceClient> set) {
        WJLog.d(TAG, "bindAnyServiceClients");
        if (set != null) {
            boolean z = false;
            for (BleGattServiceClient bleGattServiceClient : set) {
                z |= bindServiceClient(bleGattServiceClient);
            }
            return z;
        }
        throw new IllegalArgumentException("serviceClients cannot be null.");
    }

    public boolean bindServiceClient(BleGattServiceClient bleGattServiceClient) {
        WJLog.d(TAG, "bindServiceClient");
        if (bleGattServiceClient != null) {
            BluetoothGattService service = this.mBluetoothGatt.getService(bleGattServiceClient.getServiceUuid());
            if (service == null) {
                return false;
            }
            this.mServiceClientsLock.writeLock().lock();
            try {
                if (this.mServiceClients.containsKey(bleGattServiceClient.getServiceUuid())) {
                    this.mServiceClients.get(bleGattServiceClient.getServiceUuid()).unbindClient();
                }
                bleGattServiceClient.bindClient(this, service);
                this.mServiceClients.put(bleGattServiceClient.getServiceUuid(), bleGattServiceClient);
                this.mServiceClientsLock.writeLock().unlock();
                return true;
            } catch (Throwable th) {
                this.mServiceClientsLock.writeLock().unlock();
                throw th;
            }
        }
        throw new IllegalArgumentException("serviceClient cannot be null.");
    }

    public void close() {
        WJLog.d(TAG, "close");
        this.mCommandLock.lock();
        try {
            this.mCommandQueue.clear();
            this.mCommandLock.unlock();
            this.mBluetoothGatt.disconnect();
            this.mBluetoothGatt.close();
            this.mServiceClientsLock.writeLock().lock();
            try {
                for (BleGattServiceClient bleGattServiceClient : this.mServiceClients.values()) {
                    bleGattServiceClient.unbindClient();
                }
                this.mServiceClients.clear();
            } finally {
                this.mServiceClientsLock.writeLock().unlock();
            }
        } catch (Throwable th) {
            this.mCommandLock.unlock();
            throw th;
        }
    }

    public final boolean containsServiceClient(UUID uuid) {
        WJLog.d(TAG, "containsServiceClient");
        if (uuid != null) {
            this.mServiceClientsLock.readLock().lock();
            try {
                return this.mServiceClients.containsKey(uuid);
            } finally {
                this.mServiceClientsLock.readLock().unlock();
            }
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public final void disconnect() {
        WJLog.d(TAG, Metrics.DISCONNECT);
        this.mBluetoothGatt.disconnect();
    }

    public Future<BleDiscoverServicesCommand.Result> enqueueDiscoverServices() {
        WJLog.d(TAG, "enqueueDiscoverServices");
        this.mCommandLock.lock();
        try {
            BleDiscoverServicesCommand bleDiscoverServicesCommand = new BleDiscoverServicesCommand(this.mBluetoothGatt);
            this.mCommandQueue.add(bleDiscoverServicesCommand);
            return this.mClientCommandExecutor.executeCommand(bleDiscoverServicesCommand);
        } finally {
            this.mCommandLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Future<BleReadCharacteristicCommand.Result> enqueueReadCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        WJLog.d(TAG, "enqueueReadCharacteristic");
        if (bluetoothGattCharacteristic != null) {
            this.mCommandLock.lock();
            try {
                BleReadCharacteristicCommand bleReadCharacteristicCommand = new BleReadCharacteristicCommand(this.mBluetoothGatt, bluetoothGattCharacteristic);
                this.mCommandQueue.add(bleReadCharacteristicCommand);
                String str = TAG;
                WJLog.d(str, "Added command to queue. Attempting to execute command: " + bleReadCharacteristicCommand.toString());
                return this.mClientCommandExecutor.executeCommand(bleReadCharacteristicCommand);
            } finally {
                this.mCommandLock.unlock();
            }
        }
        throw new IllegalArgumentException("characteristic unexpectedly null.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Future<BleReadDescriptorCommand.Result> enqueueReadDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        WJLog.d(TAG, "enqueueReadDescriptor");
        if (bluetoothGattDescriptor != null) {
            this.mCommandLock.lock();
            try {
                BleReadDescriptorCommand bleReadDescriptorCommand = new BleReadDescriptorCommand(this.mBluetoothGatt, bluetoothGattDescriptor);
                this.mCommandQueue.add(bleReadDescriptorCommand);
                return this.mClientCommandExecutor.executeCommand(bleReadDescriptorCommand);
            } finally {
                this.mCommandLock.unlock();
            }
        }
        throw new IllegalArgumentException("descriptor unexpectedly null.");
    }

    public Future<BleReadRssiCommand.Result> enqueueReadRemoteRssi() {
        WJLog.d(TAG, "enqueueReadRemoteRssi");
        this.mCommandLock.lock();
        try {
            BleReadRssiCommand bleReadRssiCommand = new BleReadRssiCommand(this.mBluetoothGatt);
            this.mCommandQueue.add(bleReadRssiCommand);
            return this.mClientCommandExecutor.executeCommand(bleReadRssiCommand);
        } finally {
            this.mCommandLock.unlock();
        }
    }

    @TargetApi(21)
    public Future<Boolean> enqueueRequestConnectionPriorityChange(int i) {
        WJLog.d(TAG, "enqueueRequestConnectionPriorityChange");
        if (i != 0 && i != 1 && i != 2) {
            throw new IllegalArgumentException("Unsupported connection priority specified.");
        }
        this.mCommandLock.lock();
        try {
            BleChangeConnectionPriorityCommand bleChangeConnectionPriorityCommand = new BleChangeConnectionPriorityCommand(this.mGattCallback, this.mBluetoothGatt, i);
            this.mCommandQueue.add(bleChangeConnectionPriorityCommand);
            return this.mClientCommandExecutor.executeCommand(bleChangeConnectionPriorityCommand);
        } finally {
            this.mCommandLock.unlock();
        }
    }

    @TargetApi(21)
    public Future<BleChangeMtuCommand.Result> enqueueRequestMtuChange(int i) {
        WJLog.d(TAG, "enqueueRequestMtuChange");
        if (i >= 23) {
            if (i <= 512) {
                this.mCommandLock.lock();
                try {
                    BleChangeMtuCommand bleChangeMtuCommand = new BleChangeMtuCommand(this.mBluetoothGatt, i);
                    this.mCommandQueue.add(bleChangeMtuCommand);
                    return this.mClientCommandExecutor.executeCommand(bleChangeMtuCommand);
                } finally {
                    this.mCommandLock.unlock();
                }
            }
            throw new IllegalArgumentException("mtu cannot be larger than the maximum Bluetooth LE MTU of 512 bytes.");
        }
        throw new IllegalArgumentException("mtu cannot be smaller than the minimum Bluetooth LE MTU of 23 bytes.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Future<BleUpdateNotificationsCommand.Result> enqueueUpdateNotifications(BluetoothGattDescriptor bluetoothGattDescriptor, boolean z) {
        WJLog.d(TAG, "enqueueUpdateNotifications");
        if (bluetoothGattDescriptor != null) {
            this.mCommandLock.lock();
            try {
                BleUpdateNotificationsCommand bleUpdateNotificationsCommand = new BleUpdateNotificationsCommand(this.mBluetoothGatt, bluetoothGattDescriptor, z);
                this.mCommandQueue.add(bleUpdateNotificationsCommand);
                return this.mClientCommandExecutor.executeCommand(bleUpdateNotificationsCommand);
            } finally {
                this.mCommandLock.unlock();
            }
        }
        throw new IllegalArgumentException("descriptor unexpectedly null.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Future<BleWriteCharacteristicCommand.Result> enqueueWriteCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        WJLog.d(TAG, "enqueueWriteCharacteristic");
        if (bluetoothGattCharacteristic != null) {
            this.mCommandLock.lock();
            try {
                BleWriteCharacteristicCommand bleWriteCharacteristicCommand = new BleWriteCharacteristicCommand(this.mBluetoothGatt, bluetoothGattCharacteristic);
                this.mCommandQueue.add(bleWriteCharacteristicCommand);
                return this.mClientCommandExecutor.executeCommand(bleWriteCharacteristicCommand);
            } finally {
                this.mCommandLock.unlock();
            }
        }
        throw new IllegalArgumentException("characteristic unexpectedly null.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Future<BleWriteDescriptorCommand.Result> enqueueWriteDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        WJLog.d(TAG, "enqueueWriteDescriptor");
        if (bluetoothGattDescriptor != null) {
            this.mCommandLock.lock();
            try {
                BleWriteDescriptorCommand bleWriteDescriptorCommand = new BleWriteDescriptorCommand(this.mBluetoothGatt, bluetoothGattDescriptor);
                this.mCommandQueue.add(bleWriteDescriptorCommand);
                return this.mClientCommandExecutor.executeCommand(bleWriteDescriptorCommand);
            } finally {
                this.mCommandLock.unlock();
            }
        }
        throw new IllegalArgumentException("descriptor unexpectedly null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final BluetoothGatt getBluetoothGatt() {
        return this.mBluetoothGatt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final BleGattCallback getGattCallback() {
        return this.mGattCallback;
    }

    public int getMtu() {
        return this.mMtu;
    }

    public final BleGattServiceClient getServiceClient(UUID uuid) {
        WJLog.d(TAG, "getServiceClient");
        if (uuid != null) {
            if (this.mServiceClients.containsKey(uuid)) {
                this.mServiceClientsLock.readLock().lock();
                try {
                    return this.mServiceClients.get(uuid);
                } finally {
                    this.mServiceClientsLock.readLock().unlock();
                }
            }
            throw new IllegalArgumentException("No service client with uuid=" + uuid);
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public final Collection<BleGattServiceClient> getServiceClients() {
        WJLog.d(TAG, "getServiceClients");
        this.mServiceClientsLock.readLock().lock();
        try {
            return Collections.unmodifiableCollection(this.mServiceClients.values());
        } finally {
            this.mServiceClientsLock.readLock().unlock();
        }
    }

    public final boolean isConnected(Context context) {
        WJLog.d(TAG, "isConnected");
        if (context != null) {
            BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
            if (bluetoothManager == null) {
                throw new IllegalArgumentException("Could not retrieve BluetoothManager from Context. Ensure you are passing the correct context.");
            }
            return bluetoothManager.getConnectionState(this.mBluetoothDevice, 7) == 2;
        }
        throw new IllegalArgumentException("context cannot be null.");
    }

    final void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BleGattServiceClient serviceClient;
        WJLog.d(TAG, "onCharacteristicChanged");
        this.mServiceClientsLock.readLock().lock();
        try {
            try {
                serviceClient = getServiceClient(bluetoothGattCharacteristic.getService().getUuid());
            } catch (IllegalArgumentException e) {
                WJLog.e(TAG, "IllegalArgumentException: ", e);
            }
            if (serviceClient != null) {
                BleGattCharacteristic bleGattCharacteristic = serviceClient.getBleGattCharacteristic(bluetoothGattCharacteristic.getUuid());
                bleGattCharacteristic.setStoredData(bluetoothGattCharacteristic.getValue());
                serviceClient.onCharacteristicChanged(bleGattCharacteristic);
                return;
            }
            throw new IllegalArgumentException("Client does not contain service-client=" + bluetoothGattCharacteristic.getService().getUuid());
        } finally {
            this.mServiceClientsLock.readLock().unlock();
        }
    }

    final void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        WJLog.d(TAG, "onCharacteristicRead");
        this.mCommandLock.lock();
        try {
            try {
                performSanityCheck(bluetoothGatt);
            } catch (AssertionError e) {
                WJLog.e(TAG, "AssertionError: ", e);
            }
            if (this.mCommandQueue.peek() instanceof BleReadCharacteristicCommand) {
                ((BleReadCharacteristicCommand) this.mCommandQueue.remove()).update(new BleReadCharacteristicCommand.Result(i));
                return;
            }
            throw new AssertionError("Next command in queue is unexpectedly of wrong type.");
        } finally {
            this.mCommandLock.unlock();
        }
    }

    final void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        WJLog.d(TAG, "onCharacteristicWrite");
        this.mCommandLock.lock();
        try {
            try {
                performSanityCheck(bluetoothGatt);
            } catch (AssertionError e) {
                WJLog.e(TAG, "AssertionError: ", e);
            }
            if (this.mCommandQueue.peek() instanceof BleWriteCharacteristicCommand) {
                ((BleWriteCharacteristicCommand) this.mCommandQueue.remove()).update(new BleWriteCharacteristicCommand.Result(i));
                return;
            }
            throw new AssertionError("Next command in queue is unexpectedly of wrong type.");
        } finally {
            this.mCommandLock.unlock();
        }
    }

    final void onConnectionPriorityChange(BluetoothGatt bluetoothGatt, boolean z) {
        WJLog.d(TAG, "onConnectionPriorityChange");
        this.mCommandLock.lock();
        try {
            try {
                performSanityCheck(bluetoothGatt);
            } catch (AssertionError e) {
                WJLog.e(TAG, "AssertionError: ", e);
            }
            if (this.mCommandQueue.peek() instanceof BleChangeConnectionPriorityCommand) {
                this.mCommandQueue.remove();
                return;
            }
            throw new AssertionError("Next command in queue is unexpectedly of wrong type.");
        } finally {
            this.mCommandLock.unlock();
        }
    }

    final void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        WJLog.d(TAG, "onConnectionStateChange");
        this.mCallback.onConnectionStateChanged(this, i, i2);
        if (this.mBluetoothGatt == null || i2 != 0 || i == 0) {
            return;
        }
        String str = TAG;
        WJLog.i(str, "Closing BLE gatt connection for unexpected disconnection with status:" + i);
        this.mBluetoothGatt.close();
    }

    final void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        WJLog.d(TAG, "onDescriptorRead");
        this.mCommandLock.lock();
        try {
            try {
                performSanityCheck(bluetoothGatt);
            } catch (AssertionError e) {
                WJLog.e(TAG, "AssertionError: ", e);
            }
            if (this.mCommandQueue.peek() instanceof BleReadDescriptorCommand) {
                ((BleReadDescriptorCommand) this.mCommandQueue.remove()).update(new BleReadDescriptorCommand.Result(i));
                return;
            }
            throw new AssertionError("Next command in queue is unexpectedly of wrong type.");
        } finally {
            this.mCommandLock.unlock();
        }
    }

    final void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        WJLog.d(TAG, "onDescriptorWrite");
        this.mCommandLock.lock();
        try {
            try {
                performSanityCheck(bluetoothGatt);
                if (this.mCommandQueue.peek() instanceof BleWriteDescriptorCommand) {
                    ((BleWriteDescriptorCommand) this.mCommandQueue.remove()).update(new BleWriteDescriptorCommand.Result(i));
                } else if (this.mCommandQueue.peek() instanceof BleUpdateNotificationsCommand) {
                    ((BleUpdateNotificationsCommand) this.mCommandQueue.remove()).update(new BleUpdateNotificationsCommand.Result(i));
                } else {
                    throw new AssertionError("Next command in queue is unexpectedly of wrong type.");
                }
            } catch (AssertionError e) {
                WJLog.e(TAG, "AssertionError: ", e);
            }
        } finally {
            this.mCommandLock.unlock();
        }
    }

    final void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
        WJLog.d(TAG, "onMtuChanged");
        this.mCommandLock.lock();
        try {
            try {
                performSanityCheck(bluetoothGatt);
            } catch (AssertionError e) {
                WJLog.e(TAG, "AssertionError: ", e);
            }
            if (this.mCommandQueue.peek() instanceof BleChangeMtuCommand) {
                ((BleChangeMtuCommand) this.mCommandQueue.remove()).update(new BleChangeMtuCommand.Result(i, i2));
                if (i2 == 0) {
                    this.mMtu = i;
                }
                return;
            }
            throw new AssertionError("Next command in queue is unexpectedly of wrong type.");
        } finally {
            this.mCommandLock.unlock();
        }
    }

    final void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
        WJLog.d(TAG, "onReadRemoteRssi");
        this.mCommandLock.lock();
        try {
            try {
                performSanityCheck(bluetoothGatt);
            } catch (AssertionError e) {
                WJLog.e(TAG, "AssertionError: ", e);
            }
            if (this.mCommandQueue.peek() instanceof BleReadRssiCommand) {
                ((BleReadRssiCommand) this.mCommandQueue.remove()).update(new BleReadRssiCommand.Result(i, i2));
                return;
            }
            throw new AssertionError("Next command in queue is unexpectedly of wrong type.");
        } finally {
            this.mCommandLock.unlock();
        }
    }

    final void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
    }

    final void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        WJLog.d(TAG, "onServicesDiscovered");
        this.mCommandLock.lock();
        try {
            try {
                performSanityCheck(bluetoothGatt);
            } catch (AssertionError e) {
                WJLog.e(TAG, "AssertionError: ", e);
            }
            if (this.mCommandQueue.peek() instanceof BleDiscoverServicesCommand) {
                for (BluetoothGattService bluetoothGattService : bluetoothGatt.getServices()) {
                    if (!bindServiceClient(new BleGattServiceClient(bluetoothGattService.getUuid()))) {
                        throw new IllegalArgumentException("Service client unexpectedly failed to bind.");
                    }
                }
                ((BleDiscoverServicesCommand) this.mCommandQueue.remove()).update(new BleDiscoverServicesCommand.Result(i));
                return;
            }
            throw new AssertionError("Next command in queue is unexpectedly of wrong type.");
        } finally {
            this.mCommandLock.unlock();
        }
    }

    public final boolean reconnect() {
        WJLog.d(TAG, Metrics.RECONNECT);
        return this.mBluetoothGatt.connect();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setBluetoothGatt(BluetoothGatt bluetoothGatt) {
        this.mBluetoothGatt = bluetoothGatt;
    }

    protected boolean setCharacteristicNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        return this.mBluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, z);
    }
}
