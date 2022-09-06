package it.innove;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.json.JSONException;
/* loaded from: classes3.dex */
public class Peripheral extends BluetoothGattCallback {
    private static final String CHARACTERISTIC_NOTIFICATION_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";
    protected byte[] advertisingDataBytes;
    protected int advertisingRSSI;
    private final Map<String, NotifyBufferContainer> bufferedCharacteristics;
    private Callback connectCallback;
    private boolean connected;
    private final BluetoothDevice device;
    private BluetoothGatt gatt;
    private ReactContext reactContext;
    private Callback readCallback;
    private Callback readRSSICallback;
    private Callback registerNotifyCallback;
    private Callback requestMTUCallback;
    private Callback retrieveServicesCallback;
    private Callback writeCallback;
    private List<byte[]> writeQueue;

    public Peripheral(BluetoothDevice bluetoothDevice, int i, byte[] bArr, ReactContext reactContext) {
        this.advertisingDataBytes = new byte[0];
        this.connected = false;
        this.writeQueue = new ArrayList();
        this.device = bluetoothDevice;
        this.bufferedCharacteristics = new HashMap();
        this.advertisingRSSI = i;
        this.advertisingDataBytes = bArr;
        this.reactContext = reactContext;
    }

    private String bufferedCharacteristicsKey(String str, String str2) {
        return GeneratedOutlineSupport1.outline75(str, ProcessIdUtil.DEFAULT_PROCESSID, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WritableMap byteArrayToWritableMap(byte[] bArr) throws JSONException {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("CDVType", "ArrayBuffer");
        WritableArray writableArray = null;
        createMap.putString("data", bArr != null ? Base64.encodeToString(bArr, 2) : null);
        if (bArr != null) {
            writableArray = BleManager.bytesToWritableArray(bArr);
        }
        createMap.putArray("bytes", writableArray);
        return createMap;
    }

    private void clearBuffers() {
        for (Map.Entry<String, NotifyBufferContainer> entry : this.bufferedCharacteristics.entrySet()) {
            entry.getValue().resetBuffer();
        }
    }

    private BluetoothGattCharacteristic findNotifyCharacteristic(BluetoothGattService bluetoothGattService, UUID uuid) {
        try {
            List<BluetoothGattCharacteristic> characteristics = bluetoothGattService.getCharacteristics();
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : characteristics) {
                if ((bluetoothGattCharacteristic.getProperties() & 16) != 0 && uuid.equals(bluetoothGattCharacteristic.getUuid())) {
                    return bluetoothGattCharacteristic;
                }
            }
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic2 : characteristics) {
                if ((bluetoothGattCharacteristic2.getProperties() & 32) != 0 && uuid.equals(bluetoothGattCharacteristic2.getUuid())) {
                    return bluetoothGattCharacteristic2;
                }
            }
            return bluetoothGattService.getCharacteristic(uuid);
        } catch (Exception e) {
            Log.e(BleManager.LOG_TAG, "Error retriving characteristic " + uuid, e);
            return null;
        }
    }

    private BluetoothGattCharacteristic findReadableCharacteristic(BluetoothGattService bluetoothGattService, UUID uuid) {
        if (bluetoothGattService != null) {
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                if ((bluetoothGattCharacteristic.getProperties() & 2) != 0 && uuid.equals(bluetoothGattCharacteristic.getUuid())) {
                    return bluetoothGattCharacteristic;
                }
            }
            return bluetoothGattService.getCharacteristic(uuid);
        }
        return null;
    }

    private BluetoothGattCharacteristic findWritableCharacteristic(BluetoothGattService bluetoothGattService, UUID uuid, int i) {
        int i2 = 8;
        if (i == 1) {
            i2 = 4;
        }
        try {
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                if ((bluetoothGattCharacteristic.getProperties() & i2) != 0 && uuid.equals(bluetoothGattCharacteristic.getUuid())) {
                    return bluetoothGattCharacteristic;
                }
            }
            return bluetoothGattService.getCharacteristic(uuid);
        } catch (Exception e) {
            Log.e(BleManager.LOG_TAG, "Error on findWritableCharacteristic", e);
            return null;
        }
    }

    private String generateHashKey(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return generateHashKey(bluetoothGattCharacteristic.getService().getUuid(), bluetoothGattCharacteristic);
    }

    private void sendConnectionEvent(BluetoothDevice bluetoothDevice, String str, int i) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("peripheral", bluetoothDevice.getAddress());
        if (i != -1) {
            createMap.putInt("status", i);
        }
        sendEvent(str, createMap);
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Peripheral event (", str, "):");
        outline115.append(bluetoothDevice.getAddress());
        outline115.toString();
    }

    private void sendEvent(String str, @Nullable WritableMap writableMap) {
        ((RCTNativeAppEventEmitter) this.reactContext.getJSModule(RCTNativeAppEventEmitter.class)).emit(str, writableMap);
    }

    private void setNotify(UUID uuid, UUID uuid2, Boolean bool, Integer num, Callback callback) {
        if (!isConnected()) {
            callback.invoke("Device is not connected", null);
            return;
        }
        BluetoothGatt bluetoothGatt = this.gatt;
        if (bluetoothGatt == null) {
            callback.invoke("BluetoothGatt is null");
            return;
        }
        BluetoothGattCharacteristic findNotifyCharacteristic = findNotifyCharacteristic(bluetoothGatt.getService(uuid), uuid2);
        if (findNotifyCharacteristic != null) {
            if (this.gatt.setCharacteristicNotification(findNotifyCharacteristic, bool.booleanValue())) {
                if (num.intValue() > 1) {
                    String str = "Characteristic buffering " + uuid2 + " count:" + num;
                    String bufferedCharacteristicsKey = bufferedCharacteristicsKey(uuid.toString(), uuid2.toString());
                    this.bufferedCharacteristics.put(bufferedCharacteristicsKey, new NotifyBufferContainer(bufferedCharacteristicsKey, num));
                }
                BluetoothGattDescriptor descriptor = findNotifyCharacteristic.getDescriptor(UUIDHelper.uuidFromString(CHARACTERISTIC_NOTIFICATION_CONFIG));
                if (descriptor != null) {
                    if ((findNotifyCharacteristic.getProperties() & 16) != 0) {
                        String str2 = "Characteristic " + uuid2 + " set NOTIFY";
                        descriptor.setValue(bool.booleanValue() ? BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                    } else if ((findNotifyCharacteristic.getProperties() & 32) != 0) {
                        String str3 = "Characteristic " + uuid2 + " set INDICATE";
                        descriptor.setValue(bool.booleanValue() ? BluetoothGattDescriptor.ENABLE_INDICATION_VALUE : BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                    } else {
                        String str4 = "Characteristic " + uuid2 + " does not have NOTIFY or INDICATE property set";
                    }
                    try {
                        this.registerNotifyCallback = callback;
                        if (this.gatt.writeDescriptor(descriptor)) {
                            return;
                        }
                        this.registerNotifyCallback = null;
                        callback.invoke("Failed to set client characteristic notification for " + uuid2);
                        return;
                    } catch (Exception e) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Failed to set client characteristic notification for ");
                        sb.append(uuid2);
                        sb.append(", error: ");
                        callback.invoke(GeneratedOutlineSupport1.outline41(e, sb));
                        return;
                    }
                }
                callback.invoke("Set notification failed for " + uuid2);
                return;
            }
            callback.invoke("Failed to register notification for " + uuid2);
            return;
        }
        callback.invoke("Characteristic " + uuid2 + " not found");
    }

    public WritableMap asWritableMap() {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        try {
            createMap.putString("name", this.device.getName());
            createMap.putString("id", this.device.getAddress());
            createMap.putInt("rssi", this.advertisingRSSI);
            String name = this.device.getName();
            if (name != null) {
                createMap2.putString("localName", name);
            }
            createMap2.putMap("manufacturerData", byteArrayToWritableMap(this.advertisingDataBytes));
            createMap2.putBoolean("isConnectable", true);
            createMap.putMap("advertising", createMap2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return createMap;
    }

    public void connect(Callback callback, Activity activity) {
        if (!this.connected) {
            BluetoothDevice device = getDevice();
            this.connectCallback = callback;
            int i = Build.VERSION.SDK_INT;
            this.gatt = device.connectGatt(activity, false, this, 2);
        } else if (this.gatt != null) {
            callback.invoke(new Object[0]);
        } else {
            callback.invoke("BluetoothGatt is null");
        }
    }

    public void disconnect(boolean z) {
        this.connectCallback = null;
        this.connected = false;
        clearBuffers();
        BluetoothGatt bluetoothGatt = this.gatt;
        if (bluetoothGatt != null) {
            try {
                bluetoothGatt.disconnect();
                if (!z) {
                    return;
                }
                this.gatt.close();
                this.gatt = null;
                sendConnectionEvent(this.device, "BleManagerDisconnectPeripheral", 0);
            } catch (Exception unused) {
                sendConnectionEvent(this.device, "BleManagerDisconnectPeripheral", 257);
            }
        }
    }

    public boolean doWrite(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        bluetoothGattCharacteristic.setValue(bArr);
        return this.gatt.writeCharacteristic(bluetoothGattCharacteristic);
    }

    public BluetoothDevice getDevice() {
        return this.device;
    }

    public Boolean hasService(UUID uuid) {
        BluetoothGatt bluetoothGatt = this.gatt;
        if (bluetoothGatt == null) {
            return null;
        }
        return Boolean.valueOf(bluetoothGatt.getService(uuid) != null);
    }

    public boolean isConnected() {
        return this.connected;
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
        try {
            String uuid = bluetoothGattCharacteristic.getUuid().toString();
            String uuid2 = bluetoothGattCharacteristic.getService().getUuid().toString();
            NotifyBufferContainer notifyBufferContainer = this.bufferedCharacteristics.get(bufferedCharacteristicsKey(uuid2, uuid));
            byte[] value = bluetoothGattCharacteristic.getValue();
            if (notifyBufferContainer != null) {
                notifyBufferContainer.put(value);
                if (!notifyBufferContainer.size().equals(notifyBufferContainer.maxCount)) {
                    return;
                }
                String str = "onCharacteristicChanged sending buffered data " + notifyBufferContainer.size();
                value = notifyBufferContainer.items.array();
                notifyBufferContainer.resetBuffer();
            }
            String str2 = "onCharacteristicChanged: " + BleManager.bytesToHex(value) + " from peripheral: " + this.device.getAddress();
            WritableMap createMap = Arguments.createMap();
            createMap.putString("peripheral", this.device.getAddress());
            createMap.putString("characteristic", uuid);
            createMap.putString(NotificationCompat.CATEGORY_SERVICE, uuid2);
            createMap.putArray("value", BleManager.bytesToWritableArray(value));
            sendEvent("BleManagerDidUpdateValueForCharacteristic", createMap);
        } catch (Exception e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onCharacteristicChanged ERROR: ");
            outline107.append(e.toString());
            outline107.toString();
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
        String str = "onCharacteristicRead " + bluetoothGattCharacteristic;
        Callback callback = this.readCallback;
        if (callback != null) {
            if (i == 0) {
                byte[] value = bluetoothGattCharacteristic.getValue();
                Callback callback2 = this.readCallback;
                if (callback2 != null) {
                    callback2.invoke(null, BleManager.bytesToWritableArray(value));
                }
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error reading ");
                outline107.append(bluetoothGattCharacteristic.getUuid());
                outline107.append(" status=");
                outline107.append(i);
                callback.invoke(outline107.toString(), null);
            }
            this.readCallback = null;
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
        if (this.writeCallback != null) {
            if (this.writeQueue.size() > 0) {
                this.writeQueue.remove(0);
                doWrite(bluetoothGattCharacteristic, this.writeQueue.get(0));
                return;
            }
            if (i == 0) {
                this.writeCallback.invoke(new Object[0]);
            } else {
                Log.e(BleManager.LOG_TAG, "Error onCharacteristicWrite:" + i);
                this.writeCallback.invoke(GeneratedOutlineSupport1.outline49("Error writing status: ", i));
            }
            this.writeCallback = null;
            return;
        }
        Log.e(BleManager.LOG_TAG, "No callback on write");
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("onConnectionStateChange to ", i2, " on peripheral: ");
        outline109.append(this.device.getAddress());
        outline109.append(" with status ");
        outline109.append(i);
        outline109.toString();
        this.gatt = bluetoothGatt;
        if (i != 0) {
            this.gatt.close();
        }
        if (i2 == 2) {
            this.connected = true;
            sendConnectionEvent(this.device, "BleManagerConnectPeripheral", i);
            if (this.connectCallback == null) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Connected to: ");
            outline107.append(this.device.getAddress());
            outline107.toString();
            this.connectCallback.invoke(new Object[0]);
            this.connectCallback = null;
        } else if (i2 == 0) {
            disconnect(true);
            sendConnectionEvent(this.device, "BleManagerDisconnectPeripheral", i);
            for (Callback callback : Arrays.asList(this.writeCallback, this.retrieveServicesCallback, this.readRSSICallback, this.readCallback, this.registerNotifyCallback, this.requestMTUCallback)) {
                if (callback != null) {
                    callback.invoke("Device disconnected");
                }
            }
            Callback callback2 = this.connectCallback;
            if (callback2 != null) {
                callback2.invoke("Connection error");
                this.connectCallback = null;
            }
            this.writeCallback = null;
            this.writeQueue.clear();
            this.readCallback = null;
            this.retrieveServicesCallback = null;
            this.readRSSICallback = null;
            this.registerNotifyCallback = null;
            this.requestMTUCallback = null;
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
        Callback callback = this.registerNotifyCallback;
        if (callback != null) {
            if (i == 0) {
                callback.invoke(new Object[0]);
            } else {
                callback.invoke(GeneratedOutlineSupport1.outline49("Error writing descriptor stats=", i), null);
                Log.e(BleManager.LOG_TAG, "Error writing descriptor stats=" + i);
            }
            this.registerNotifyCallback = null;
            return;
        }
        Log.e(BleManager.LOG_TAG, "onDescriptorWrite with no callback");
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
        super.onMtuChanged(bluetoothGatt, i, i2);
        Callback callback = this.requestMTUCallback;
        if (callback != null) {
            if (i2 == 0) {
                callback.invoke(null, Integer.valueOf(i));
            } else {
                callback.invoke(GeneratedOutlineSupport1.outline49("Error requesting MTU status = ", i2), null);
            }
            this.requestMTUCallback = null;
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
        super.onReadRemoteRssi(bluetoothGatt, i, i2);
        Callback callback = this.readRSSICallback;
        if (callback != null) {
            if (i2 == 0) {
                updateRssi(i);
                this.readRSSICallback.invoke(null, Integer.valueOf(i));
            } else {
                callback.invoke(GeneratedOutlineSupport1.outline49("Error reading RSSI status=", i2), null);
            }
            this.readRSSICallback = null;
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        super.onServicesDiscovered(bluetoothGatt, i);
        if (this.retrieveServicesCallback != null) {
            this.retrieveServicesCallback.invoke(null, asWritableMap(bluetoothGatt));
            this.retrieveServicesCallback = null;
        }
    }

    public void read(UUID uuid, UUID uuid2, Callback callback) {
        if (!isConnected()) {
            callback.invoke("Device is not connected", null);
            return;
        }
        BluetoothGatt bluetoothGatt = this.gatt;
        if (bluetoothGatt == null) {
            callback.invoke("BluetoothGatt is null", null);
            return;
        }
        BluetoothGattCharacteristic findReadableCharacteristic = findReadableCharacteristic(bluetoothGatt.getService(uuid), uuid2);
        if (findReadableCharacteristic == null) {
            callback.invoke("Characteristic " + uuid2 + " not found.", null);
            return;
        }
        this.readCallback = callback;
        if (this.gatt.readCharacteristic(findReadableCharacteristic)) {
            return;
        }
        this.readCallback = null;
        callback.invoke("Read failed", null);
    }

    public void readRSSI(Callback callback) {
        if (!isConnected()) {
            callback.invoke("Device is not connected", null);
            return;
        }
        BluetoothGatt bluetoothGatt = this.gatt;
        if (bluetoothGatt == null) {
            callback.invoke("BluetoothGatt is null", null);
            return;
        }
        this.readRSSICallback = callback;
        if (bluetoothGatt.readRemoteRssi()) {
            return;
        }
        this.readCallback = null;
        callback.invoke("Read RSSI failed", null);
    }

    public void refreshCache(Callback callback) {
        try {
            Method method = this.gatt.getClass().getMethod("refresh", new Class[0]);
            if (method != null) {
                callback.invoke(null, Boolean.valueOf(((Boolean) method.invoke(this.gatt, new Object[0])).booleanValue()));
            } else {
                callback.invoke("Could not refresh cache for device.");
            }
        } catch (Exception e) {
            Log.e(ReactConstants.TAG, "An exception occured while refreshing device");
            callback.invoke(e.getMessage());
        }
    }

    public void registerNotify(UUID uuid, UUID uuid2, Integer num, Callback callback) {
        setNotify(uuid, uuid2, true, num, callback);
    }

    public void removeNotify(UUID uuid, UUID uuid2, Callback callback) {
        setNotify(uuid, uuid2, false, 1, callback);
    }

    public void requestConnectionPriority(int i, Callback callback) {
        BluetoothGatt bluetoothGatt = this.gatt;
        if (bluetoothGatt == null) {
            callback.invoke("BluetoothGatt is null", null);
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        callback.invoke(null, Boolean.valueOf(bluetoothGatt.requestConnectionPriority(i)));
    }

    public void requestMTU(int i, Callback callback) {
        if (!isConnected()) {
            callback.invoke("Device is not connected", null);
            return;
        }
        BluetoothGatt bluetoothGatt = this.gatt;
        if (bluetoothGatt == null) {
            callback.invoke("BluetoothGatt is null", null);
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        this.requestMTUCallback = callback;
        bluetoothGatt.requestMtu(i);
    }

    public void retrieveServices(Callback callback) {
        if (!isConnected()) {
            callback.invoke("Device is not connected", null);
            return;
        }
        BluetoothGatt bluetoothGatt = this.gatt;
        if (bluetoothGatt == null) {
            callback.invoke("BluetoothGatt is null", null);
            return;
        }
        this.retrieveServicesCallback = callback;
        bluetoothGatt.discoverServices();
    }

    public int unsignedToBytes(byte b) {
        return b & 255;
    }

    public void updateData(byte[] bArr) {
        this.advertisingDataBytes = bArr;
    }

    public void updateRssi(int i) {
        this.advertisingRSSI = i;
    }

    public void write(UUID uuid, UUID uuid2, byte[] bArr, Integer num, Integer num2, Callback callback, int i) {
        boolean z;
        if (!isConnected()) {
            callback.invoke("Device is not connected", null);
            return;
        }
        BluetoothGatt bluetoothGatt = this.gatt;
        if (bluetoothGatt == null) {
            callback.invoke("BluetoothGatt is null");
            return;
        }
        BluetoothGattCharacteristic findWritableCharacteristic = findWritableCharacteristic(bluetoothGatt.getService(uuid), uuid2, i);
        if (findWritableCharacteristic == null) {
            callback.invoke("Characteristic " + uuid2 + " not found.");
            return;
        }
        findWritableCharacteristic.setWriteType(i);
        if (this.writeQueue.size() > 0) {
            callback.invoke("You have already an queued message");
        } else if (this.writeCallback != null) {
            callback.invoke("You're already writing");
        } else if (this.writeQueue.size() != 0 || this.writeCallback != null) {
        } else {
            if (2 == i) {
                this.writeCallback = callback;
            }
            if (bArr.length > num.intValue()) {
                int length = bArr.length;
                ArrayList arrayList = new ArrayList();
                byte[] bArr2 = null;
                int i2 = 0;
                while (i2 < length && length - i2 > num.intValue()) {
                    if (i2 == 0) {
                        bArr2 = Arrays.copyOfRange(bArr, i2, num.intValue() + i2);
                    } else {
                        arrayList.add(Arrays.copyOfRange(bArr, i2, num.intValue() + i2));
                    }
                    i2 += num.intValue();
                }
                if (i2 < length) {
                    arrayList.add(Arrays.copyOfRange(bArr, i2, bArr.length));
                }
                if (2 == i) {
                    this.writeQueue.addAll(arrayList);
                    if (doWrite(findWritableCharacteristic, bArr2)) {
                        return;
                    }
                    this.writeQueue.clear();
                    this.writeCallback = null;
                    callback.invoke("Write failed");
                    return;
                }
                try {
                    if (!doWrite(findWritableCharacteristic, bArr2)) {
                        callback.invoke("Write failed");
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        return;
                    }
                    Thread.sleep(num2.intValue());
                    Iterator it2 = arrayList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        } else if (!doWrite(findWritableCharacteristic, (byte[]) it2.next())) {
                            callback.invoke("Write failed");
                            z = true;
                            break;
                        } else {
                            Thread.sleep(num2.intValue());
                        }
                    }
                    if (z) {
                        return;
                    }
                    callback.invoke(new Object[0]);
                } catch (InterruptedException unused) {
                    callback.invoke("Error during writing");
                }
            } else if (!doWrite(findWritableCharacteristic, bArr)) {
                callback.invoke("Write failed");
                this.writeCallback = null;
            } else if (1 != i) {
            } else {
                callback.invoke(new Object[0]);
            }
        }
    }

    private String generateHashKey(UUID uuid, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return String.valueOf(uuid) + AccessoryMetricsConstants.DELIMITER + bluetoothGattCharacteristic.getUuid() + AccessoryMetricsConstants.DELIMITER + bluetoothGattCharacteristic.getInstanceId();
    }

    public Peripheral(BluetoothDevice bluetoothDevice, ReactContext reactContext) {
        this.advertisingDataBytes = new byte[0];
        this.connected = false;
        this.writeQueue = new ArrayList();
        this.device = bluetoothDevice;
        this.bufferedCharacteristics = new HashMap();
        this.reactContext = reactContext;
    }

    public WritableMap asWritableMap(BluetoothGatt bluetoothGatt) {
        Iterator<BluetoothGattService> it2;
        WritableMap asWritableMap = asWritableMap();
        WritableArray createArray = Arguments.createArray();
        WritableArray createArray2 = Arguments.createArray();
        if (this.connected && bluetoothGatt != null) {
            Iterator<BluetoothGattService> it3 = bluetoothGatt.getServices().iterator();
            while (it3.hasNext()) {
                BluetoothGattService next = it3.next();
                WritableMap createMap = Arguments.createMap();
                createMap.putString("uuid", UUIDHelper.uuidToString(next.getUuid()));
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic : next.getCharacteristics()) {
                    WritableMap createMap2 = Arguments.createMap();
                    createMap2.putString(NotificationCompat.CATEGORY_SERVICE, UUIDHelper.uuidToString(next.getUuid()));
                    createMap2.putString("characteristic", UUIDHelper.uuidToString(bluetoothGattCharacteristic.getUuid()));
                    createMap2.putMap("properties", Helper.decodeProperties(bluetoothGattCharacteristic));
                    if (bluetoothGattCharacteristic.getPermissions() > 0) {
                        createMap2.putMap(MessagingControllerConstant.MESSAGING_CONTROLLER_ENDPOINT_PERMISSIONS_KEY, Helper.decodePermissions(bluetoothGattCharacteristic));
                    }
                    WritableArray createArray3 = Arguments.createArray();
                    for (BluetoothGattDescriptor bluetoothGattDescriptor : bluetoothGattCharacteristic.getDescriptors()) {
                        WritableMap createMap3 = Arguments.createMap();
                        createMap3.putString("uuid", UUIDHelper.uuidToString(bluetoothGattDescriptor.getUuid()));
                        if (bluetoothGattDescriptor.getValue() != null) {
                            it2 = it3;
                            createMap3.putString("value", Base64.encodeToString(bluetoothGattDescriptor.getValue(), 2));
                        } else {
                            it2 = it3;
                            createMap3.putString("value", null);
                        }
                        if (bluetoothGattDescriptor.getPermissions() > 0) {
                            createMap3.putMap(MessagingControllerConstant.MESSAGING_CONTROLLER_ENDPOINT_PERMISSIONS_KEY, Helper.decodePermissions(bluetoothGattDescriptor));
                        }
                        createArray3.pushMap(createMap3);
                        it3 = it2;
                    }
                    Iterator<BluetoothGattService> it4 = it3;
                    if (createArray3.size() > 0) {
                        createMap2.putArray("descriptors", createArray3);
                    }
                    createArray2.pushMap(createMap2);
                    it3 = it4;
                }
                createArray.pushMap(createMap);
            }
            asWritableMap.putArray("services", createArray);
            asWritableMap.putArray("characteristics", createArray2);
        }
        return asWritableMap;
    }
}
