package com.amazon.whisperbridge.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import com.amazon.whisperbridge.ble.request.BleExecuteWriteRequest;
import com.amazon.whisperbridge.ble.request.BleReadCharacteristicRequest;
import com.amazon.whisperbridge.ble.request.BleReadDescriptorRequest;
import com.amazon.whisperbridge.ble.request.BleRequest;
import com.amazon.whisperbridge.ble.request.BleWriteCharacteristicRequest;
import com.amazon.whisperbridge.ble.request.BleWriteDescriptorRequest;
import com.amazon.whisperbridge.ble.utility.ExecutorServiceFactory;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public abstract class BleGattService {
    private static final String TAG = "BleGattService";
    protected final Map<UUID, BleGattCharacteristic> mBleCharacteristics;
    protected BluetoothGattService mBluetoothGattService;
    protected BleGattServer mBoundBleServer;
    private final ExecutorServiceFactory mExecutorServiceFactory;
    protected final Map<UUID, Set<BluetoothDevice>> mIndicatableDevices;
    protected final Map<UUID, Set<BluetoothDevice>> mNotifiableDevices;
    protected final Map<BluetoothDevice, Map<BleGattCharacteristic, ByteArrayOutputStream>> mPreparedCharacteristicWrites;
    protected final Map<BluetoothDevice, Map<BleGattDescriptor, ByteArrayOutputStream>> mPreparedDescriptorWrites;
    protected ExecutorService mRequestExecutor;
    protected final UUID mServiceUuid;

    /* loaded from: classes13.dex */
    private static abstract class CatchAllRunnable implements Runnable {
        private CatchAllRunnable() {
        }

        protected abstract void doRun();

        @Override // java.lang.Runnable
        public void run() {
            try {
                doRun();
            } catch (Exception e) {
                WJLog.e(BleGattService.TAG, "An uncaught exception occurred while handling request!", e);
            }
        }
    }

    public BleGattService(UUID uuid) {
        this(uuid, new ExecutorServiceFactory() { // from class: com.amazon.whisperbridge.ble.BleGattService.1
            @Override // com.amazon.whisperbridge.ble.utility.ExecutorServiceFactory
            public ExecutorService newExecutorService() {
                return Executors.newSingleThreadExecutor();
            }
        });
    }

    public boolean addBleCharacteristic(BleGattCharacteristic bleGattCharacteristic) {
        if (bleGattCharacteristic != null) {
            if (!this.mBleCharacteristics.containsKey(bleGattCharacteristic.getUuid())) {
                boolean addCharacteristic = this.mBluetoothGattService.addCharacteristic(bleGattCharacteristic.getBluetoothGattCharacteristic());
                if (addCharacteristic) {
                    this.mBleCharacteristics.put(bleGattCharacteristic.getUuid(), bleGattCharacteristic);
                }
                return addCharacteristic;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("characteristic already exists with uuid=");
            outline107.append(bleGattCharacteristic.getUuid());
            throw new IllegalArgumentException(outline107.toString());
        }
        throw new IllegalArgumentException("characteristic cannot be null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void bindBleServer(BleGattServer bleGattServer) {
        if (this.mBoundBleServer == null) {
            if (bleGattServer != null) {
                this.mBoundBleServer = bleGattServer;
                this.mRequestExecutor = this.mExecutorServiceFactory.newExecutorService();
                return;
            }
            throw new IllegalArgumentException("bleServer cannot be null.");
        }
        throw new IllegalStateException("BLE server is already bound.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enqueueRequest(final BleRequest bleRequest) {
        String str = TAG;
        WJLog.d(str, "enqueueRequest " + bleRequest);
        if (bleRequest != null) {
            ExecutorService executorService = this.mRequestExecutor;
            if (executorService != null) {
                if (bleRequest instanceof BleReadCharacteristicRequest) {
                    executorService.submit(new CatchAllRunnable() { // from class: com.amazon.whisperbridge.ble.BleGattService.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super();
                        }

                        @Override // com.amazon.whisperbridge.ble.BleGattService.CatchAllRunnable
                        public void doRun() {
                            BleGattService.this.onCharacteristicReadRequest((BleReadCharacteristicRequest) bleRequest);
                        }
                    });
                    return;
                } else if (bleRequest instanceof BleWriteCharacteristicRequest) {
                    executorService.submit(new CatchAllRunnable() { // from class: com.amazon.whisperbridge.ble.BleGattService.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super();
                        }

                        @Override // com.amazon.whisperbridge.ble.BleGattService.CatchAllRunnable
                        public void doRun() {
                            BleGattService.this.onCharacteristicWriteRequest((BleWriteCharacteristicRequest) bleRequest);
                        }
                    });
                    return;
                } else if (bleRequest instanceof BleReadDescriptorRequest) {
                    executorService.submit(new CatchAllRunnable() { // from class: com.amazon.whisperbridge.ble.BleGattService.5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super();
                        }

                        @Override // com.amazon.whisperbridge.ble.BleGattService.CatchAllRunnable
                        public void doRun() {
                            BleGattService.this.onDescriptorReadRequest((BleReadDescriptorRequest) bleRequest);
                        }
                    });
                    return;
                } else if (bleRequest instanceof BleWriteDescriptorRequest) {
                    executorService.submit(new CatchAllRunnable() { // from class: com.amazon.whisperbridge.ble.BleGattService.6
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super();
                        }

                        @Override // com.amazon.whisperbridge.ble.BleGattService.CatchAllRunnable
                        public void doRun() {
                            BleGattService.this.onDescriptorWriteRequest((BleWriteDescriptorRequest) bleRequest);
                        }
                    });
                    return;
                } else if (bleRequest instanceof BleExecuteWriteRequest) {
                    executorService.submit(new CatchAllRunnable() { // from class: com.amazon.whisperbridge.ble.BleGattService.7
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super();
                        }

                        @Override // com.amazon.whisperbridge.ble.BleGattService.CatchAllRunnable
                        public void doRun() {
                            BleGattService.this.onExecuteWriteRequest((BleExecuteWriteRequest) bleRequest);
                        }
                    });
                    return;
                } else {
                    throw new IllegalArgumentException("Unknown request type encountered in BleGattService.");
                }
            }
            throw new IllegalStateException("mRequestExecutor is unexpectedly null.");
        }
        throw new IllegalArgumentException("request is unexpectedly null.");
    }

    public BleGattCharacteristic getBleCharacteristic(UUID uuid) {
        if (uuid != null) {
            if (this.mBleCharacteristics.containsKey(uuid)) {
                return this.mBleCharacteristics.get(uuid);
            }
            throw new IllegalArgumentException("No characteristic with uuid=" + uuid);
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public BluetoothGattService getBluetoothGattService() {
        return this.mBluetoothGattService;
    }

    public UUID getServiceUuid() {
        return this.mServiceUuid;
    }

    protected void handleExecuteWriteRequest(BleExecuteWriteRequest bleExecuteWriteRequest) {
        String str = TAG;
        WJLog.d(str, "handleExecuteWriteRequest " + bleExecuteWriteRequest);
        sendAckResponse(bleExecuteWriteRequest, null);
        if (bleExecuteWriteRequest.execute()) {
            if (this.mPreparedDescriptorWrites.containsKey(bleExecuteWriteRequest.getBluetoothDevice())) {
                for (Map.Entry<BleGattDescriptor, ByteArrayOutputStream> entry : this.mPreparedDescriptorWrites.get(bleExecuteWriteRequest.getBluetoothDevice()).entrySet()) {
                    enqueueRequest(new BleWriteDescriptorRequest(bleExecuteWriteRequest.getBluetoothDevice(), bleExecuteWriteRequest.getRequestID(), entry.getKey(), false, true, 0, entry.getValue().toByteArray()));
                }
            }
            if (this.mPreparedCharacteristicWrites.containsKey(bleExecuteWriteRequest.getBluetoothDevice())) {
                for (Map.Entry<BleGattCharacteristic, ByteArrayOutputStream> entry2 : this.mPreparedCharacteristicWrites.get(bleExecuteWriteRequest.getBluetoothDevice()).entrySet()) {
                    enqueueRequest(new BleWriteCharacteristicRequest(bleExecuteWriteRequest.getBluetoothDevice(), bleExecuteWriteRequest.getRequestID(), entry2.getKey(), false, true, 0, entry2.getValue().toByteArray()));
                }
            }
        }
        this.mPreparedCharacteristicWrites.remove(bleExecuteWriteRequest.getBluetoothDevice());
        this.mPreparedDescriptorWrites.remove(bleExecuteWriteRequest.getBluetoothDevice());
    }

    protected boolean handlePreparedCharacteristicWrite(BleWriteCharacteristicRequest bleWriteCharacteristicRequest) {
        String str = TAG;
        WJLog.d(str, "handlePreparedCharacteristicWrite " + bleWriteCharacteristicRequest);
        BluetoothDevice bluetoothDevice = bleWriteCharacteristicRequest.getBluetoothDevice();
        Map<BleGattCharacteristic, ByteArrayOutputStream> map = this.mPreparedCharacteristicWrites.get(bluetoothDevice);
        if (map == null) {
            map = new HashMap<>();
            this.mPreparedCharacteristicWrites.put(bluetoothDevice, map);
        }
        int offset = bleWriteCharacteristicRequest.getOffset();
        BleGattCharacteristic bleCharacteristic = bleWriteCharacteristicRequest.getBleCharacteristic();
        ByteArrayOutputStream byteArrayOutputStream = map.get(bleCharacteristic);
        if (byteArrayOutputStream == null) {
            byteArrayOutputStream = new ByteArrayOutputStream();
            map.put(bleCharacteristic, byteArrayOutputStream);
        }
        if (offset == 0) {
            byteArrayOutputStream.reset();
        }
        if (offset != byteArrayOutputStream.size()) {
            return false;
        }
        try {
            byteArrayOutputStream.write(bleWriteCharacteristicRequest.getValue());
            if (!bleWriteCharacteristicRequest.isResponseNeeded()) {
                return true;
            }
            sendAckResponse(bleWriteCharacteristicRequest, bleWriteCharacteristicRequest.getValue());
            return true;
        } catch (IOException unused) {
            WJLog.e(TAG, "Error writing into byte stream");
            throw new IllegalStateException("Unexpected interruption while outputing BLE data.");
        }
    }

    protected boolean handlePreparedDescriptorWrite(BleWriteDescriptorRequest bleWriteDescriptorRequest) {
        String str = TAG;
        WJLog.d(str, "handlePreparedDescriptorWrite " + bleWriteDescriptorRequest);
        BluetoothDevice bluetoothDevice = bleWriteDescriptorRequest.getBluetoothDevice();
        Map<BleGattDescriptor, ByteArrayOutputStream> map = this.mPreparedDescriptorWrites.get(bluetoothDevice);
        if (map == null) {
            map = new HashMap<>();
            this.mPreparedDescriptorWrites.put(bluetoothDevice, map);
        }
        int offset = bleWriteDescriptorRequest.getOffset();
        BleGattDescriptor bleDescriptor = bleWriteDescriptorRequest.getBleDescriptor();
        ByteArrayOutputStream byteArrayOutputStream = map.get(bleDescriptor);
        if (byteArrayOutputStream == null) {
            byteArrayOutputStream = new ByteArrayOutputStream();
            map.put(bleDescriptor, byteArrayOutputStream);
        }
        if (offset == 0) {
            byteArrayOutputStream.reset();
        }
        if (offset != byteArrayOutputStream.size()) {
            return false;
        }
        try {
            byteArrayOutputStream.write(bleWriteDescriptorRequest.getValue());
            if (!bleWriteDescriptorRequest.isResponseNeeded()) {
                return true;
            }
            sendAckResponse(bleWriteDescriptorRequest, bleWriteDescriptorRequest.getValue());
            return true;
        } catch (IOException unused) {
            WJLog.e(TAG, "Error writing into byte stream");
            throw new IllegalStateException("Unexpected interruption while outputing BLE data.");
        }
    }

    protected boolean handleReadCharacteristicRequest(BleReadCharacteristicRequest bleReadCharacteristicRequest) {
        String str = TAG;
        WJLog.d(str, "handleWriteCharacteristicRequest " + bleReadCharacteristicRequest);
        BleGattCharacteristic bleCharacteristic = bleReadCharacteristicRequest.getBleCharacteristic();
        if (bleCharacteristic != null) {
            if (bleCharacteristic.isReadable()) {
                byte[] storedData = bleCharacteristic.getStoredData();
                if (storedData == null) {
                    WJLog.e(TAG, "handleReadCharacteristicRequest, no response value");
                    sendResponse(bleReadCharacteristicRequest, 0, null);
                    return true;
                }
                int offset = bleReadCharacteristicRequest.getOffset();
                if (offset == storedData.length) {
                    WJLog.d(TAG, "Edge case! The packet size == offset");
                    sendResponse(bleReadCharacteristicRequest, 0, null);
                    return true;
                } else if (offset > storedData.length) {
                    WJLog.e(TAG, "handleReadCharacteristicRequest, offset > fullValue length");
                    sendResponse(bleReadCharacteristicRequest, 7, null);
                    return false;
                } else {
                    int length = storedData.length - offset;
                    byte[] bArr = new byte[length];
                    System.arraycopy(storedData, offset, bArr, 0, length);
                    WJLog.d(TAG, String.format(Locale.ENGLISH, "handleReadCharacteristicRequest remaining length [%d]", Integer.valueOf(length)));
                    WJLog.d(TAG, String.format(Locale.ENGLISH, "handleReadCharacteristicRequest sending partial response [%d]", Integer.valueOf(offset)));
                    sendResponse(bleReadCharacteristicRequest, 0, bArr);
                    return true;
                }
            }
            throw new IllegalArgumentException("bleCharacteristic is unexpectedly not readable.");
        }
        throw new IllegalArgumentException("bleCharacteristic unexpectedly null.");
    }

    protected boolean handleReadDescriptorRequest(BleReadDescriptorRequest bleReadDescriptorRequest) {
        String str = TAG;
        WJLog.d(str, "handleReadDescriptorRequest " + bleReadDescriptorRequest);
        BleGattDescriptor bleDescriptor = bleReadDescriptorRequest.getBleDescriptor();
        if (bleDescriptor != null) {
            if (bleDescriptor.isReadable()) {
                byte[] storedData = bleDescriptor.getStoredData();
                if (storedData == null) {
                    WJLog.e(TAG, "handleReadDescriptorRequest error, no response value");
                    return false;
                }
                int offset = bleReadDescriptorRequest.getOffset();
                if (offset > storedData.length) {
                    WJLog.e(TAG, "handleReadDescriptorRequest error, offset > fullValue length");
                    sendResponse(bleReadDescriptorRequest, 7, null);
                    return false;
                }
                int length = storedData.length - offset;
                byte[] bArr = new byte[length];
                System.arraycopy(storedData, offset, bArr, 0, length);
                WJLog.d(TAG, "handleReadDescriptorRequest success, sending partial response");
                sendResponse(bleReadDescriptorRequest, 0, bArr);
                return true;
            }
            throw new IllegalArgumentException("bleDescriptor is unexpectedly not readable.");
        }
        throw new IllegalArgumentException("bleDescriptor unexpectedly null.");
    }

    protected boolean handleWriteCharacteristicRequest(BleWriteCharacteristicRequest bleWriteCharacteristicRequest) {
        String str = TAG;
        WJLog.d(str, "handleWriteCharacteristicRequest " + bleWriteCharacteristicRequest);
        BleGattCharacteristic bleCharacteristic = bleWriteCharacteristicRequest.getBleCharacteristic();
        if (bleCharacteristic != null) {
            if (bleCharacteristic.isWritable()) {
                if (bleWriteCharacteristicRequest.isPreparedWrite()) {
                    return handlePreparedCharacteristicWrite(bleWriteCharacteristicRequest);
                }
                bleCharacteristic.setStoredData(bleWriteCharacteristicRequest.getValue());
                if (!bleWriteCharacteristicRequest.isResponseNeeded()) {
                    return true;
                }
                sendAckResponse(bleWriteCharacteristicRequest, bleWriteCharacteristicRequest.getValue());
                return true;
            }
            throw new IllegalArgumentException("bleCharacteristic is unexpectedly not writable.");
        }
        throw new IllegalArgumentException("bleCharacteristic unexpectedly null.");
    }

    protected boolean handleWriteDescriptorRequest(BleWriteDescriptorRequest bleWriteDescriptorRequest) {
        String str = TAG;
        WJLog.d(str, "handleWriteDescriptorRequest " + bleWriteDescriptorRequest);
        BleGattDescriptor bleDescriptor = bleWriteDescriptorRequest.getBleDescriptor();
        if (bleDescriptor != null) {
            if (bleDescriptor.isWritable()) {
                if (bleWriteDescriptorRequest.isPreparedWrite()) {
                    return handlePreparedDescriptorWrite(bleWriteDescriptorRequest);
                }
                if (bleWriteDescriptorRequest.isResponseNeeded()) {
                    sendAckResponse(bleWriteDescriptorRequest, bleWriteDescriptorRequest.getValue());
                }
                if (BleConstants.CLIENT_CHARACTERISTIC_CONFIGURATION_DESCRIPTOR_UUID.equals(bleDescriptor.getUuid())) {
                    handleWriteNotificationDescriptor(bleWriteDescriptorRequest);
                    return true;
                }
                bleDescriptor.setStoredData(bleWriteDescriptorRequest.getValue());
                return true;
            }
            throw new IllegalArgumentException("bleDescriptor is unexpectedly not writable.");
        }
        throw new IllegalArgumentException("bleDescriptor unexpectedly null.");
    }

    protected void handleWriteNotificationDescriptor(BleWriteDescriptorRequest bleWriteDescriptorRequest) {
        String str = TAG;
        WJLog.d(str, "handleWriteNotificationDescriptor " + bleWriteDescriptorRequest);
        UUID uuid = bleWriteDescriptorRequest.getBleDescriptor().getBluetoothGattDescriptor().getCharacteristic().getUuid();
        Set<BluetoothDevice> set = this.mNotifiableDevices.get(uuid);
        if (set == null) {
            set = new HashSet<>();
            this.mNotifiableDevices.put(uuid, set);
        }
        Set<BluetoothDevice> set2 = this.mIndicatableDevices.get(uuid);
        if (set2 == null) {
            set2 = new HashSet<>();
            this.mIndicatableDevices.put(uuid, set2);
        }
        byte b = bleWriteDescriptorRequest.getValue().length > 0 ? bleWriteDescriptorRequest.getValue()[0] : (byte) 0;
        if ((BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE[0] & b) != 0) {
            set.add(bleWriteDescriptorRequest.getBluetoothDevice());
        } else {
            set.remove(bleWriteDescriptorRequest.getBluetoothDevice());
        }
        if ((b & BluetoothGattDescriptor.ENABLE_INDICATION_VALUE[0]) != 0) {
            set2.add(bleWriteDescriptorRequest.getBluetoothDevice());
        } else {
            set2.remove(bleWriteDescriptorRequest.getBluetoothDevice());
        }
    }

    protected boolean indicateOnCharacteristic(UUID uuid) {
        String str = TAG;
        WJLog.d(str, "indicateOnCharacteristic characteristic=" + uuid);
        if (this.mBoundBleServer != null) {
            if (uuid != null) {
                if (this.mBleCharacteristics.containsKey(uuid)) {
                    BleGattCharacteristic bleGattCharacteristic = this.mBleCharacteristics.get(uuid);
                    if (bleGattCharacteristic.isIndicatable()) {
                        Set<BluetoothDevice> set = this.mIndicatableDevices.get(uuid);
                        boolean z = true;
                        if (set != null && !set.isEmpty()) {
                            for (BluetoothDevice bluetoothDevice : set) {
                                z &= this.mBoundBleServer.sendIndication(bluetoothDevice, bleGattCharacteristic.getBluetoothGattCharacteristic());
                            }
                            return z;
                        }
                        WJLog.w(TAG, "No indicatable devices.");
                        return true;
                    }
                    throw new IllegalArgumentException("Characteristic is not indicatable.");
                }
                throw new IllegalArgumentException("No characteristic with uuid=" + uuid);
            }
            throw new IllegalArgumentException("characteristicUuid cannot be null.");
        }
        throw new IllegalStateException("Service is not bound to GATT server.");
    }

    public boolean isBound() {
        return this.mBoundBleServer != null;
    }

    public boolean isValidReadCharacteristic(UUID uuid) {
        if (uuid != null) {
            if (this.mBleCharacteristics.containsKey(uuid)) {
                return this.mBleCharacteristics.get(uuid).isReadable();
            }
            throw new IllegalArgumentException("No characteristic with uuid=" + uuid);
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public boolean isValidReadDescriptor(UUID uuid, UUID uuid2) {
        if (uuid != null) {
            if (uuid2 != null) {
                if (this.mBleCharacteristics.containsKey(uuid)) {
                    return this.mBleCharacteristics.get(uuid).isValidReadDescriptor(uuid2);
                }
                throw new IllegalArgumentException("No characteristic with uuid=" + uuid);
            }
            throw new IllegalArgumentException("descriptorUuid cannot be null");
        }
        throw new IllegalArgumentException("characteristicUuid cannot be null.");
    }

    public boolean isValidWriteCharacteristic(UUID uuid) {
        if (uuid != null) {
            if (this.mBleCharacteristics.containsKey(uuid)) {
                return this.mBleCharacteristics.get(uuid).isWritable();
            }
            throw new IllegalArgumentException("No characteristic with uuid=" + uuid);
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public boolean isValidWriteDescriptor(UUID uuid, UUID uuid2) {
        if (uuid != null) {
            if (uuid2 != null) {
                if (this.mBleCharacteristics.containsKey(uuid)) {
                    return this.mBleCharacteristics.get(uuid).isValidWriteDescriptor(uuid2);
                }
                throw new IllegalArgumentException("No characteristic with uuid=" + uuid);
            }
            throw new IllegalArgumentException("descriptorUuid cannot be null");
        }
        throw new IllegalArgumentException("characteristicUuid cannot be null.");
    }

    protected boolean notifyOnCharacteristic(UUID uuid) {
        WJLog.d(TAG, "notifyOnCharacteristic characteristic=" + uuid);
        if (this.mBoundBleServer != null) {
            if (uuid != null) {
                if (this.mBleCharacteristics.containsKey(uuid)) {
                    BleGattCharacteristic bleGattCharacteristic = this.mBleCharacteristics.get(uuid);
                    if (bleGattCharacteristic.isNotifiable()) {
                        Set<BluetoothDevice> set = this.mNotifiableDevices.get(uuid);
                        if (set != null && !set.isEmpty()) {
                            ArrayList<Future> arrayList = new ArrayList();
                            for (BluetoothDevice bluetoothDevice : set) {
                                arrayList.add(this.mBoundBleServer.enqueueSendNotification(bluetoothDevice, bleGattCharacteristic.getBluetoothGattCharacteristic()));
                            }
                            boolean z = true;
                            for (Future future : arrayList) {
                                boolean z2 = false;
                                try {
                                    if (((Integer) future.get()).intValue() == 0) {
                                        z2 = true;
                                    }
                                    z &= z2;
                                } catch (InterruptedException | ExecutionException e) {
                                    String str = TAG;
                                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception while awaiting notification result future: ");
                                    outline107.append(e.getMessage());
                                    WJLog.e(str, outline107.toString());
                                    z = false;
                                }
                            }
                            return z;
                        }
                        WJLog.w(TAG, "No notifiable devices.");
                        return true;
                    }
                    throw new IllegalArgumentException("Characteristic is not notifiable.");
                }
                throw new IllegalArgumentException("No characteristic with uuid=" + uuid);
            }
            throw new IllegalArgumentException("characteristicUuid cannot be null.");
        }
        throw new IllegalStateException("Service is not bound to GATT server.");
    }

    protected void onCharacteristicReadRequest(BleReadCharacteristicRequest bleReadCharacteristicRequest) {
        String str = TAG;
        WJLog.d(str, "onCharacteristicReadRequest " + bleReadCharacteristicRequest);
        if (isValidReadCharacteristic(bleReadCharacteristicRequest.getBleCharacteristic().getUuid())) {
            if (handleReadCharacteristicRequest(bleReadCharacteristicRequest)) {
                return;
            }
            sendFailureResponse(bleReadCharacteristicRequest);
            return;
        }
        throw new IllegalArgumentException("Invalid read characteristic.");
    }

    protected void onCharacteristicWriteRequest(BleWriteCharacteristicRequest bleWriteCharacteristicRequest) {
        String str = TAG;
        WJLog.d(str, "onCharacteristicWriteRequest " + bleWriteCharacteristicRequest);
        if (isValidWriteCharacteristic(bleWriteCharacteristicRequest.getBleCharacteristic().getUuid())) {
            if (handleWriteCharacteristicRequest(bleWriteCharacteristicRequest)) {
                return;
            }
            sendFailureResponse(bleWriteCharacteristicRequest);
            return;
        }
        throw new IllegalArgumentException("Invalid write characteristic.");
    }

    protected void onDescriptorReadRequest(BleReadDescriptorRequest bleReadDescriptorRequest) {
        String str = TAG;
        WJLog.d(str, "onDescriptorReadRequest " + bleReadDescriptorRequest);
        if (bleReadDescriptorRequest.getBleDescriptor().isReadable()) {
            if (handleReadDescriptorRequest(bleReadDescriptorRequest)) {
                return;
            }
            sendFailureResponse(bleReadDescriptorRequest);
            return;
        }
        throw new IllegalArgumentException("Invalid write characteristic.");
    }

    protected void onDescriptorWriteRequest(BleWriteDescriptorRequest bleWriteDescriptorRequest) {
        String str = TAG;
        WJLog.d(str, "onDescriptorWriteRequest " + bleWriteDescriptorRequest);
        if (bleWriteDescriptorRequest.getBleDescriptor().isWritable()) {
            if (handleWriteDescriptorRequest(bleWriteDescriptorRequest)) {
                return;
            }
            sendFailureResponse(bleWriteDescriptorRequest);
            return;
        }
        throw new IllegalArgumentException("Invalid write characteristic.");
    }

    protected void onExecuteWriteRequest(BleExecuteWriteRequest bleExecuteWriteRequest) {
        String str = TAG;
        WJLog.d(str, "onExecuteWriteRequest " + bleExecuteWriteRequest);
        handleExecuteWriteRequest(bleExecuteWriteRequest);
    }

    protected void sendAckResponse(BleRequest bleRequest, byte[] bArr) {
        String str = TAG;
        WJLog.d(str, "sendAckResponse " + bleRequest);
        BleGattServer bleGattServer = this.mBoundBleServer;
        if (bleGattServer != null) {
            if (bleRequest != null) {
                bleGattServer.enqueueSendAckResponse(bleRequest.getBluetoothDevice(), bleRequest.getRequestID(), bleRequest.getOffset(), bArr);
                return;
            }
            throw new IllegalArgumentException("request cannot be null.");
        }
        throw new IllegalStateException("Service is not bound to GATT server.");
    }

    protected void sendFailureResponse(BleRequest bleRequest) {
        String str = TAG;
        WJLog.d(str, "sendFailureResponse " + bleRequest);
        BleGattServer bleGattServer = this.mBoundBleServer;
        if (bleGattServer != null) {
            if (bleRequest != null) {
                bleGattServer.enqueueSendFailureResponse(bleRequest.getBluetoothDevice(), bleRequest.getRequestID(), bleRequest.getOffset());
                return;
            }
            throw new IllegalArgumentException("request cannot be null.");
        }
        throw new IllegalStateException("Service is not bound to GATT server.");
    }

    protected void sendResponse(BleRequest bleRequest, int i, byte[] bArr) {
        String str = TAG;
        WJLog.d(str, "sendResponse " + bleRequest);
        BleGattServer bleGattServer = this.mBoundBleServer;
        if (bleGattServer != null) {
            if (bleRequest != null) {
                bleGattServer.enqueueSendResponse(bleRequest.getBluetoothDevice(), bleRequest.getRequestID(), i, bleRequest.getOffset(), bArr);
                return;
            }
            throw new IllegalArgumentException("request cannot be null.");
        }
        throw new IllegalStateException("Service is not bound to GATT server.");
    }

    public void setCharacteristicData(UUID uuid, byte[] bArr) {
        String str = TAG;
        WJLog.d(str, "setCharacteristicData uuid=" + uuid);
        if (uuid != null) {
            if (this.mBleCharacteristics.containsKey(uuid)) {
                this.mBleCharacteristics.get(uuid).setStoredData(bArr);
                return;
            }
            throw new IllegalArgumentException("No characteristic with uuid=" + uuid);
        }
        throw new IllegalArgumentException("uuid cannot be null.");
    }

    public Future<Boolean> setCharacteristicDataAndNotify(final UUID uuid, byte[] bArr) {
        String str = TAG;
        WJLog.d(str, "setCharacteristicDataAndNotify uuid=" + uuid);
        setCharacteristicData(uuid, bArr);
        if (this.mBleCharacteristics.get(uuid).isNotifiable()) {
            ExecutorService executorService = this.mRequestExecutor;
            if (executorService == null) {
                return null;
            }
            return executorService.submit(new Callable<Boolean>() { // from class: com.amazon.whisperbridge.ble.BleGattService.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                /* renamed from: call */
                public Boolean mo5061call() throws Exception {
                    return Boolean.valueOf(BleGattService.this.notifyOnCharacteristic(uuid));
                }
            });
        }
        throw new IllegalStateException("Attempting to notify on a characteristic that does not support notifications.");
    }

    public void setDescriptorData(UUID uuid, UUID uuid2, byte[] bArr) {
        String str = TAG;
        WJLog.d(str, "setDescriptorData descriptor=" + uuid2);
        if (uuid != null) {
            if (uuid2 != null) {
                if (this.mBleCharacteristics.containsKey(uuid)) {
                    this.mBleCharacteristics.get(uuid).setDescriptorData(uuid2, bArr);
                    return;
                }
                throw new IllegalArgumentException("No characteristic with uuid=" + uuid);
            }
            throw new IllegalArgumentException("descriptorUuid cannot be null");
        }
        throw new IllegalArgumentException("characteristicUuid cannot be null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unbindBleServer() {
        if (this.mBoundBleServer != null) {
            this.mBoundBleServer = null;
            this.mRequestExecutor.shutdownNow();
            try {
                if (!this.mRequestExecutor.awaitTermination(10L, TimeUnit.SECONDS)) {
                    this.mRequestExecutor.shutdownNow();
                    if (!this.mRequestExecutor.awaitTermination(10L, TimeUnit.SECONDS)) {
                        WJLog.d(TAG, "Background task pool did not terminate!");
                    }
                }
            } catch (InterruptedException unused) {
                this.mRequestExecutor.shutdownNow();
                Thread.currentThread().interrupt();
            }
            this.mRequestExecutor = null;
            return;
        }
        throw new IllegalStateException("BLE server is already unbound.");
    }

    BleGattService(UUID uuid, ExecutorServiceFactory executorServiceFactory) {
        this.mBleCharacteristics = new HashMap();
        this.mPreparedCharacteristicWrites = new HashMap();
        this.mPreparedDescriptorWrites = new HashMap();
        this.mNotifiableDevices = new HashMap();
        this.mIndicatableDevices = new HashMap();
        if (uuid != null) {
            if (executorServiceFactory != null) {
                this.mBluetoothGattService = new BluetoothGattService(uuid, 0);
                this.mServiceUuid = uuid;
                this.mExecutorServiceFactory = executorServiceFactory;
                return;
            }
            throw new IllegalArgumentException("executorServiceFactory cannot be null.");
        }
        throw new IllegalArgumentException("serviceUuid cannot be null.");
    }
}
