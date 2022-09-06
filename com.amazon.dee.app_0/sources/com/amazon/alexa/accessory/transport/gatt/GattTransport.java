package com.amazon.alexa.accessory.transport.gatt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryAttributes;
import com.amazon.alexa.accessory.AccessorySessionOptions;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.PhonePolicy;
import com.amazon.alexa.accessory.bluetooth.BluetoothDeviceBonder;
import com.amazon.alexa.accessory.internal.bluetooth.BluetoothUtils;
import com.amazon.alexa.accessory.internal.io.BufferPool;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Buffer;
import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes6.dex */
public final class GattTransport implements AccessoryTransport {
    private static final int ATT_SIZE = 3;
    private static final int BOND = 9;
    private static final int CHARACTERISTIC_CHANGED = 5;
    private static final int CHARACTERISTIC_READ = 7;
    private static final int CHARACTERISTIC_WRITE = 6;
    private static final int CLOSE = 8;
    private static final int CONNECT = 10;
    private static final int CONNECTION_CHANGED = 1;
    private static final long CONNECT_TIMEOUT_MILLIS = 60000;
    private static final int DEFAULT_MTU_SIZE = 23;
    private static final int DESCRIPTOR_WRITE = 4;
    private static final int ENABLE_NOTIFICATIONS = 11;
    private static final int GATT_AUTH_FAIL = 137;
    private static final int MTU_CHANGED = 2;
    private static final int MTU_REQUEST_ATTEMPTS = 3;
    private static final long MTU_REQUEST_DELAY = 3000;
    private static final int NEGOTIATE_MTU = 12;
    private static final int PREFERRED_MTU_SIZE = 104;
    private static final long READ_TIMEOUT_MILLIS = 0;
    private static final int RESET_PRIORITY_CONNECTION = 13;
    private static final long RESET_PRIORITY_CONNECTION_TIMEOUT_MILLIS = 10000;
    private static final int SERVICES_DISCOVERED = 3;
    private static final int STATE_CONNECTED = 2;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_IDLE = 0;
    private static final String TAG = "GattTransport:";
    private static final long WRITE_TIMEOUT_MILLIS = 10000;
    private final Accessory accessory;
    private final AccessoryMetricsService accessoryMetricsService;
    private final AccessoryAttributes attributes;
    private final BluetoothManager bluetoothManager;
    private final BluetoothDeviceBonder bonder;
    private final BufferPool bufferPool;
    private volatile IOException cause;
    private final UUID characteristicDescriptorRxId;
    private final UUID characteristicRxId;
    private final UUID characteristicTxId;
    private final Context context;
    private BluetoothGatt gatt;
    private Handler handler;
    private volatile boolean highPriorityConnection;
    private Map<String, Object> metricsCustomAttributes;
    private final Object monitor;
    private int mtuAttemptCount;
    private int mtuSize;
    private Buffer pendingData;
    private final UUID serviceId;
    private GattSink sink;
    private GattSource source;
    private volatile int state;
    private HandlerThread thread;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public final class GattAdapter extends BluetoothGattCallback {
        GattAdapter() {
        }

        private void dispatch(int i, int i2, int i3) {
            if (GattTransport.this.state == 0) {
                return;
            }
            GattTransport.this.handler.sendMessage(GattTransport.this.handler.obtainMessage(i, i2, i3));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (!GattTransport.this.characteristicRxId.equals(bluetoothGattCharacteristic.getUuid())) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("%s Unsupported characteristic notification received ");
                outline107.append(bluetoothGattCharacteristic.getUuid());
                Logger.d(outline107.toString(), GattTransport.TAG);
                return;
            }
            byte[] value = bluetoothGattCharacteristic.getValue();
            Logger.d("%s Characteristic changed", GattTransport.TAG);
            Buffer request = GattTransport.this.bufferPool.request(value.length);
            request.write(value, 0, value.length);
            GattTransport.this.handler.sendMessage(GattTransport.this.handler.obtainMessage(5, request));
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            dispatch(7, i, 0);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (GattTransport.this.characteristicTxId.equals(bluetoothGattCharacteristic.getUuid())) {
                if (GattTransport.this.state != 2) {
                    Logger.w("%s Characteristic was written to while GATT transport is not connected!", GattTransport.TAG);
                    return;
                } else {
                    dispatch(6, i, 0);
                    return;
                }
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("%s Unsupported characteristic write received ");
            outline107.append(bluetoothGattCharacteristic.getUuid());
            Logger.d(outline107.toString(), GattTransport.TAG);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            dispatch(1, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            dispatch(4, i, 0);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            dispatch(2, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            dispatch(3, i, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public final class HandlerCallback implements Handler.Callback {
        HandlerCallback() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            Logger.d("%s handleMessage: %s", GattTransport.TAG, Integer.valueOf(message.what));
            switch (message.what) {
                case 1:
                    GattTransport.this.connectionChanged(message.arg1, message.arg2);
                    return true;
                case 2:
                    GattTransport.this.mtuChanged(message.arg1, message.arg2);
                    return true;
                case 3:
                    GattTransport.this.servicesDiscovered(message.arg1);
                    return true;
                case 4:
                    GattTransport.this.descriptorWrite(message.arg1);
                    return true;
                case 5:
                    GattTransport.this.characteristicChanged((Buffer) message.obj);
                    return true;
                case 6:
                    GattTransport.this.characteristicWrite(message.arg1);
                    return true;
                case 7:
                    GattTransport.this.characteristicRead(message.arg1);
                    return true;
                case 8:
                    GattTransport.this.internalClose();
                    return true;
                case 9:
                    GattTransport.this.bond();
                    return true;
                case 10:
                    GattTransport.this.connect();
                    return true;
                case 11:
                    GattTransport.this.enableNotifications();
                    return true;
                case 12:
                    GattTransport.this.negotiateMtu();
                    return true;
                case 13:
                    GattTransport.this.resetPriorityConnection();
                    return true;
                default:
                    return false;
            }
        }
    }

    public GattTransport(Context context, Accessory accessory, BluetoothDeviceBonder bluetoothDeviceBonder, UUID uuid, AccessoryAttributes accessoryAttributes, AccessorySessionOptions accessorySessionOptions) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(accessory, ModelTransformer.KEY_ACCESSORY);
        Preconditions.notNull(bluetoothDeviceBonder, "deviceBonder");
        Preconditions.notNull(accessoryAttributes, "attributes");
        Preconditions.notNull(accessorySessionOptions, "accessorySessionOptions");
        Preconditions.precondition(accessory.getTransport() == AccessoryTransport.Type.GATT, "Accessory must support GATT transport");
        this.context = context;
        this.accessory = accessory;
        this.serviceId = uuid;
        this.attributes = accessoryAttributes;
        this.bufferPool = new BufferPool();
        this.monitor = new Object();
        this.state = 0;
        this.mtuSize = 23;
        this.bonder = bluetoothDeviceBonder;
        this.bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        if (accessorySessionOptions.shouldUseUnsecureLowEnergyConnection()) {
            this.characteristicRxId = accessoryAttributes.unsecureLowEnergyCharacteristicRxId();
            this.characteristicTxId = accessoryAttributes.unsecureLowEnergyCharacteristicTxId();
        } else {
            this.characteristicRxId = accessoryAttributes.lowEnergyCharacteristicRxId();
            this.characteristicTxId = accessoryAttributes.lowEnergyCharacteristicTxId();
        }
        this.characteristicDescriptorRxId = accessoryAttributes.lowEnergyCharacteristicDescriptorRxId();
        this.accessoryMetricsService = AccessoryMetricsServiceHolder.getInstance().get();
        String name = accessory.getName();
        if (name != null) {
            this.metricsCustomAttributes = Collections.singletonMap("firmware_accessory_1", name);
            Logger.d("%s BLE device name is %s", TAG, name);
            return;
        }
        this.metricsCustomAttributes = null;
    }

    private void awaitConnection() throws IOException {
        Logger.d("%s: in awaitConnection() state: %s", TAG, Integer.valueOf(this.state));
        if (this.state == 2) {
            return;
        }
        synchronized (this.monitor) {
            if (this.state == 2) {
                return;
            }
            if (this.state == 0) {
                if (this.cause == null) {
                    this.state = 1;
                    this.thread = new HandlerThread(getClass().getSimpleName());
                    this.thread.start();
                    this.handler = new Handler(this.thread.getLooper(), new HandlerCallback());
                    this.handler.sendEmptyMessage(9);
                } else {
                    throw this.cause;
                }
            }
            if (this.state == 1) {
                Logger.d("%s: awaitConnection waitUntilNotified", TAG);
                IOUtils.waitUntilNotified(this.monitor, 60000L);
                Logger.d("%s notified without time expiration", TAG);
            }
            if (this.cause != null) {
                throw this.cause;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void awaitEncryption() {
        BluetoothGattService service = this.gatt.getService(this.serviceId);
        if (service == null) {
            recordGattCounterMetricWithDeviceName("gattAmaServiceNotSupported_awaitEncryption");
            notifyConnectFailed("Alexa accessory service is not supported");
            return;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(this.characteristicRxId);
        if (characteristic == null) {
            recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_CHARACTERISTIC_RX_NOT_AVAILABLE);
            notifyConnectFailed("Accessory does not support transmitting data");
            return;
        }
        Logger.d("%s Awaiting for encryption completion...", TAG);
        if (this.gatt.readCharacteristic(characteristic)) {
            return;
        }
        recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_READ_CHARACTERISTIC_INITIATION_FAILED);
        notifyConnectFailed("Failed to read characteristic Rx");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bond() {
        Logger.d("%s in bond()", TAG);
        BluetoothAdapter adapter = this.bluetoothManager.getAdapter();
        if (adapter != null && adapter.isEnabled()) {
            BluetoothDevice remoteDevice = adapter.getRemoteDevice(this.accessory.getAddress());
            Logger.d("%s calling createBond", TAG);
            this.bonder.createBond(remoteDevice, 2, new BluetoothDeviceBonder.Callback() { // from class: com.amazon.alexa.accessory.transport.gatt.GattTransport.1
                @Override // com.amazon.alexa.accessory.bluetooth.BluetoothDeviceBonder.Callback
                public void onBondCompleted(BluetoothDevice bluetoothDevice) {
                    GattTransport.this.handler.sendEmptyMessage(10);
                }

                @Override // com.amazon.alexa.accessory.bluetooth.BluetoothDeviceBonder.Callback
                public void onBondFailed(BluetoothDevice bluetoothDevice) {
                    GattTransport.this.recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_BLUETOOTH_BOND_FAILED);
                    GattTransport gattTransport = GattTransport.this;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to bond with a device ");
                    outline107.append(GattTransport.this.accessory);
                    gattTransport.notifyConnectFailed(outline107.toString());
                }
            });
            return;
        }
        notifyConnectFailed(new IOException("BluetoothAdapter is unavailable. Cannot create bond"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void characteristicChanged(Buffer buffer) {
        requestHighPriorityConnection();
        if (this.state == 2) {
            this.source.write(buffer);
        } else {
            if (this.pendingData == null) {
                this.pendingData = this.bufferPool.request(buffer.size());
            }
            this.pendingData.write(buffer.data(), 0, buffer.size());
        }
        this.bufferPool.recycle(buffer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void characteristicRead(int i) {
        if (i != 0 && i != 137) {
            recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_READ_CHARACTERISTIC_FAILED);
            notifyConnectFailed(GeneratedOutlineSupport1.outline49("Failed to enable encryption: ", i));
            return;
        }
        this.handler.sendEmptyMessage(11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void characteristicWrite(int i) {
        requestHighPriorityConnection();
        if (i == 0) {
            this.sink.notifyCharacteristicWriteCompleted();
            return;
        }
        Logger.d("%s characteristicWrite failed: %d", TAG, Integer.valueOf(i));
        recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_WRITE_CHARACTERISTIC_FAILED);
        this.sink.notifyCharacteristicWriteFailed(new IOException(GeneratedOutlineSupport1.outline49("Failed to write a characteristic ", i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connect() {
        Logger.d("%s Opening GATT connection to %s", TAG, this.accessory);
        BluetoothAdapter adapter = this.bluetoothManager.getAdapter();
        if (adapter != null && adapter.isEnabled()) {
            this.gatt = BluetoothUtils.connectGatt(adapter.getRemoteDevice(this.accessory.getAddress()), this.context, false, new GattAdapter(), 2);
            if (this.gatt != null) {
                return;
            }
            recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_CONNECTION_FAILED);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to connect to a device ");
            outline107.append(this.accessory);
            notifyConnectFailed(outline107.toString());
            return;
        }
        notifyConnectFailed(new IOException("BluetoothAdapter is unavailable. Cannot connect"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectionChanged(int i, int i2) {
        if (i2 == 0) {
            Logger.d("%s GATT is disconnected", TAG);
            recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_CONNECTION_CHANGED_TO_DISCONNECTED);
            notifyConnectFailed("GATT disconnected from accessory");
        } else if (i != 0) {
            recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_CHANGE_CONNECTION_STATE_FAILED);
            notifyConnectFailed("Failed to connect");
        } else if (i2 != 2) {
        } else {
            Logger.d("%s GATT is connected", TAG);
            requestHighPriorityConnection();
            negotiateMtu();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void descriptorWrite(int i) {
        if (i != 0) {
            recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_WRITE_DESCRIPTOR_FAILED);
            notifyConnectFailed(GeneratedOutlineSupport1.outline49("Failed to enable notifications: ", i));
            return;
        }
        Logger.d("%s GATT notifications are enabled!", TAG);
        setupIO();
    }

    private void discoverServices() {
        Logger.d("%s Requesting services over GATT...", TAG);
        if (!this.gatt.discoverServices()) {
            recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_DISCOVER_SERVICES_INITIATION_FAILED);
            notifyConnectFailed("Failed to discover services");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableNotifications() {
        BluetoothGattService service = this.gatt.getService(this.serviceId);
        if (service == null) {
            recordGattCounterMetricWithDeviceName("gattAmaServiceNotSupported_enableNotifications");
            notifyConnectFailed("Alexa accessory service is not supported");
            return;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(this.characteristicRxId);
        if (characteristic != null && this.gatt.setCharacteristicNotification(characteristic, true)) {
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(this.characteristicDescriptorRxId);
            if (descriptor == null) {
                recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_DESCRIPTOR_RX_NOT_AVAILABLE);
                notifyConnectFailed("Accessory does not support transmitting data");
                return;
            }
            Logger.d("Enabling GATT notifications...");
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            if (this.gatt.writeDescriptor(descriptor)) {
                return;
            }
            recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_WRITE_DESCRIPTOR_INITIATION_FAILED);
            notifyConnectFailed("Failed to setup receiving data");
            return;
        }
        recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_CHARACTERISTIC_NOTIFICATION_UNSUPPORTED);
        notifyConnectFailed("Accessory does not support transmitting data");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void internalClose() {
        synchronized (this.monitor) {
            this.state = 0;
            this.pendingData = null;
            IOUtils.closeQuietly(this.source);
            IOUtils.closeQuietly(this.sink);
            if (this.gatt != null) {
                this.gatt.disconnect();
                this.gatt.close();
                this.gatt = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mtuChanged(int i, int i2) {
        this.handler.removeMessages(12);
        Logger.d("%s MTU changed mtu %d status %d", TAG, Integer.valueOf(i), Integer.valueOf(i2));
        if (i2 == 0) {
            this.mtuSize = i;
        }
        discoverServices();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void negotiateMtu() {
        int i = this.mtuAttemptCount + 1;
        this.mtuAttemptCount = i;
        if (i > 3) {
            recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_MTU_ATTEMPTS_EXCEEDED);
            Logger.d("%s Accessory failed to respond to MTU request. Continue with default MTU size of %s bytes", TAG, 23);
            mtuChanged(23, 0);
            return;
        }
        Logger.d("%s Requesting mtu. Attempt %d/%d...", TAG, Integer.valueOf(this.mtuAttemptCount), 3);
        if (!this.gatt.requestMtu(104)) {
            Logger.d("%s Failed to request MTU size change", TAG);
            discoverServices();
            return;
        }
        Logger.d("%s mtu was requested successfully. Waiting for callback from Bluetooth stack... Scheduling another mtu request in %dms", TAG, 3000L);
        this.handler.sendEmptyMessageDelayed(12, 3000L);
    }

    private void notifyConnectFailed(IOException iOException) {
        synchronized (this.monitor) {
            this.cause = iOException;
            this.state = 0;
            this.pendingData = null;
            if (this.thread != null) {
                this.thread.quit();
            }
            if (this.gatt != null) {
                this.gatt.disconnect();
                this.gatt.close();
                this.gatt = null;
            }
            IOUtils.closeQuietly(this.source);
            IOUtils.closeQuietly(this.sink);
            this.monitor.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordGattCounterMetricWithDeviceName(String str) {
        this.accessoryMetricsService.recordCounter(str, MetricsConstants.Session.SESSION_CONNECTION, 1.0d, this.metricsCustomAttributes);
    }

    private void requestHighPriorityConnection() {
        this.handler.removeMessages(13);
        this.handler.sendEmptyMessageDelayed(13, 10000L);
        if (!this.highPriorityConnection) {
            this.highPriorityConnection = true;
            this.gatt.requestConnectionPriority(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetPriorityConnection() {
        BluetoothGatt bluetoothGatt;
        if (this.state == 0 || (bluetoothGatt = this.gatt) == null) {
            return;
        }
        this.highPriorityConnection = false;
        bluetoothGatt.requestConnectionPriority(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void servicesDiscovered(int i) {
        if (i != 0) {
            recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_DISCOVER_SERVICES_FAILED);
            notifyConnectFailed(GeneratedOutlineSupport1.outline49("Failed to discover GATT services: ", i));
            return;
        }
        long awaitLeEncryptionDelayMillis = PhonePolicy.awaitLeEncryptionDelayMillis();
        if (awaitLeEncryptionDelayMillis == 0) {
            awaitEncryption();
            return;
        }
        Logger.d("%s Detected a questionable device, deferring for %dms...", TAG, Long.valueOf(awaitLeEncryptionDelayMillis));
        this.handler.postDelayed(new Runnable() { // from class: com.amazon.alexa.accessory.transport.gatt.-$$Lambda$GattTransport$V6P6uqHQDerSCFHB42DRquIk_yM
            @Override // java.lang.Runnable
            public final void run() {
                GattTransport.this.awaitEncryption();
            }
        }, awaitLeEncryptionDelayMillis);
    }

    private void setupIO() {
        Logger.d("%s in setupIO()", TAG);
        BluetoothGattService service = this.gatt.getService(this.serviceId);
        if (service == null) {
            recordGattCounterMetricWithDeviceName("gattAmaServiceNotSupported_setupIO");
            notifyConnectFailed("Alexa accessory service is not supported");
            return;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(this.characteristicTxId);
        if (characteristic == null) {
            recordGattCounterMetricWithDeviceName(MetricsConstants.Session.GATT_CHARACTERISTIC_TX_NOT_AVAILABLE);
            notifyConnectFailed("Accessory does not support receiving data");
            return;
        }
        int i = this.mtuSize - 3;
        Logger.d("%s GATT services were discovered, a payload size per packet is set to %d bytes.", TAG, Integer.valueOf(i));
        this.sink = new GattSink(this.gatt, characteristic, i, 10000L);
        this.source = new GattSource(i, 0L);
        Buffer buffer = this.pendingData;
        if (buffer != null) {
            this.source.write(buffer);
            this.bufferPool.recycle(this.pendingData);
        }
        synchronized (this.monitor) {
            this.state = 2;
            this.monitor.notifyAll();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this.monitor) {
            if (this.state == 0) {
                return;
            }
            this.state = 0;
            this.handler.sendEmptyMessage(8);
            this.thread.quitSafely();
        }
    }

    @Override // com.amazon.alexa.accessory.AccessoryTransport
    public Accessory getAccessory() {
        return this.accessory;
    }

    @Override // com.amazon.alexa.accessory.AccessoryTransport
    public Sink sink() throws IOException {
        Logger.d("%s: in sink()", TAG);
        awaitConnection();
        return this.sink;
    }

    @Override // com.amazon.alexa.accessory.AccessoryTransport
    public Source source() throws IOException {
        Logger.d("%s: in source()", TAG);
        awaitConnection();
        return this.source;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyConnectFailed(String str) {
        Logger.d("%s: notifyConnectFailed with message: %s", TAG, str);
        notifyConnectFailed(new IOException(str));
    }
}
