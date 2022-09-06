package it.innove;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.onedesign.util.image.ImageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class BleManager extends ReactContextBaseJavaModule {
    private static final int ENABLE_REQUEST = 539;
    public static final String LOG_TAG = "ReactNativeBleManager";
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothManager bluetoothManager;
    private BondRequest bondRequest;
    private Context context;
    private Callback enableBluetoothCallback;
    private boolean forceLegacy;
    private final ActivityEventListener mActivityEventListener;
    private final BroadcastReceiver mReceiver;
    private final Map<String, Peripheral> peripherals;
    private ReactApplicationContext reactContext;
    private BondRequest removeBondRequest;
    private ScanManager scanManager;

    public BleManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mActivityEventListener = new BaseActivityEventListener() { // from class: it.innove.BleManager.1
            @Override // com.facebook.react.bridge.BaseActivityEventListener, com.facebook.react.bridge.ActivityEventListener
            public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
                if (i != 539 || BleManager.this.enableBluetoothCallback == null) {
                    return;
                }
                if (i2 == -1) {
                    BleManager.this.enableBluetoothCallback.invoke(new Object[0]);
                } else {
                    BleManager.this.enableBluetoothCallback.invoke("User refused to enable");
                }
                BleManager.this.enableBluetoothCallback = null;
            }
        };
        this.peripherals = new LinkedHashMap();
        this.mReceiver = new BroadcastReceiver() { // from class: it.innove.BleManager.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String str;
                Peripheral peripheral;
                String str2;
                String action = intent.getAction();
                if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                    switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE)) {
                        case 10:
                            BleManager.this.clearPeripherals();
                            str2 = "off";
                            break;
                        case 11:
                            str2 = "turning_on";
                            break;
                        case 12:
                            str2 = "on";
                            break;
                        case 13:
                            BleManager.this.disconnectPeripherals();
                            str2 = "turning_off";
                            break;
                        default:
                            str2 = "";
                            break;
                    }
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("state", str2);
                    String str3 = "state: " + str2;
                    BleManager.this.sendEvent("BleManagerDidUpdateState", createMap);
                } else if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                    int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
                    int intExtra2 = intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", Integer.MIN_VALUE);
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    switch (intExtra) {
                        case 10:
                            str = "BOND_NONE";
                            break;
                        case 11:
                            str = "BOND_BONDING";
                            break;
                        case 12:
                            str = "BOND_BONDED";
                            break;
                        default:
                            str = "UNKNOWN";
                            break;
                    }
                    GeneratedOutlineSupport1.outline158("bond state: ", str);
                    if (BleManager.this.bondRequest != null && BleManager.this.bondRequest.uuid.equals(bluetoothDevice.getAddress())) {
                        if (intExtra == 12) {
                            BleManager.this.bondRequest.callback.invoke(new Object[0]);
                            BleManager.this.bondRequest = null;
                        } else if (intExtra == 10 || intExtra == Integer.MIN_VALUE) {
                            BleManager.this.bondRequest.callback.invoke("Bond request has been denied");
                            BleManager.this.bondRequest = null;
                        }
                    }
                    if (intExtra == 12) {
                        int i = Build.VERSION.SDK_INT;
                        if (!BleManager.this.forceLegacy) {
                            peripheral = new LollipopPeripheral(bluetoothDevice, BleManager.this.reactContext);
                        } else {
                            peripheral = new Peripheral(bluetoothDevice, BleManager.this.reactContext);
                        }
                        BleManager.this.sendEvent("BleManagerPeripheralDidBond", peripheral.asWritableMap());
                    }
                    if (BleManager.this.removeBondRequest == null || !BleManager.this.removeBondRequest.uuid.equals(bluetoothDevice.getAddress()) || intExtra != 10 || intExtra2 != 12) {
                        return;
                    }
                    BleManager.this.removeBondRequest.callback.invoke(new Object[0]);
                    BleManager.this.removeBondRequest = null;
                } else if (!action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
                } else {
                    BluetoothDevice bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (BleManager.this.bondRequest == null || !BleManager.this.bondRequest.uuid.equals(bluetoothDevice2.getAddress()) || BleManager.this.bondRequest.pin == null) {
                        return;
                    }
                    bluetoothDevice2.setPin(BleManager.this.bondRequest.pin.getBytes());
                    bluetoothDevice2.createBond();
                }
            }
        };
        this.context = reactApplicationContext;
        this.reactContext = reactApplicationContext;
        reactApplicationContext.addActivityEventListener(this.mActivityEventListener);
    }

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = hexArray;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    public static WritableArray bytesToWritableArray(byte[] bArr) {
        WritableArray createArray = Arguments.createArray();
        for (byte b : bArr) {
            createArray.pushInt(b & 255);
        }
        return createArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPeripherals() {
        if (!this.peripherals.isEmpty()) {
            synchronized (this.peripherals) {
                this.peripherals.clear();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnectPeripherals() {
        if (!this.peripherals.isEmpty()) {
            synchronized (this.peripherals) {
                for (Peripheral peripheral : this.peripherals.values()) {
                    if (peripheral.isConnected()) {
                        peripheral.disconnect(true);
                    }
                }
            }
        }
    }

    private BluetoothAdapter getBluetoothAdapter() {
        if (this.bluetoothAdapter == null) {
            this.bluetoothAdapter = ((BluetoothManager) this.context.getSystemService("bluetooth")).getAdapter();
        }
        return this.bluetoothAdapter;
    }

    private BluetoothManager getBluetoothManager() {
        if (this.bluetoothManager == null) {
            this.bluetoothManager = (BluetoothManager) this.context.getSystemService("bluetooth");
        }
        return this.bluetoothManager;
    }

    @ReactMethod
    private void removeBond(String str, Callback callback) {
        GeneratedOutlineSupport1.outline158("Remove bond to: ", str);
        Peripheral retrieveOrCreatePeripheral = retrieveOrCreatePeripheral(str);
        if (retrieveOrCreatePeripheral == null) {
            callback.invoke("Invalid peripheral uuid");
            return;
        }
        try {
            retrieveOrCreatePeripheral.getDevice().getClass().getMethod("removeBond", null).invoke(retrieveOrCreatePeripheral.getDevice(), null);
            this.removeBondRequest = new BondRequest(str, callback);
        } catch (Exception unused) {
            GeneratedOutlineSupport1.outline158("Error in remove bond: ", str);
            callback.invoke("Remove bond request fail");
        }
    }

    private Peripheral retrieveOrCreatePeripheral(String str) {
        Peripheral peripheral;
        Peripheral peripheral2 = this.peripherals.get(str);
        if (peripheral2 == null) {
            synchronized (this.peripherals) {
                if (str != null) {
                    str = str.toUpperCase();
                }
                if (BluetoothAdapter.checkBluetoothAddress(str)) {
                    BluetoothDevice remoteDevice = this.bluetoothAdapter.getRemoteDevice(str);
                    int i = Build.VERSION.SDK_INT;
                    if (!this.forceLegacy) {
                        peripheral = new LollipopPeripheral(remoteDevice, this.reactContext);
                    } else {
                        peripheral = new Peripheral(remoteDevice, this.reactContext);
                    }
                    peripheral2 = peripheral;
                    this.peripherals.put(str, peripheral2);
                }
            }
        }
        return peripheral2;
    }

    private Peripheral savePeripheral(BluetoothDevice bluetoothDevice) {
        Peripheral peripheral;
        String address = bluetoothDevice.getAddress();
        synchronized (this.peripherals) {
            if (!this.peripherals.containsKey(address)) {
                int i = Build.VERSION.SDK_INT;
                if (!this.forceLegacy) {
                    peripheral = new LollipopPeripheral(bluetoothDevice, this.reactContext);
                } else {
                    peripheral = new Peripheral(bluetoothDevice, this.reactContext);
                }
                this.peripherals.put(bluetoothDevice.getAddress(), peripheral);
            }
        }
        return this.peripherals.get(address);
    }

    @ReactMethod
    public void addListener(String str) {
    }

    @ReactMethod
    public void checkState() {
        int state;
        BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();
        String str = "off";
        if (!this.context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            str = ImageType.UNSUPPORTED;
        } else if (bluetoothAdapter != null && (state = bluetoothAdapter.getState()) != 10 && state == 12) {
            str = "on";
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString("state", str);
        String str2 = "state:" + str;
        sendEvent("BleManagerDidUpdateState", createMap);
    }

    @ReactMethod
    public void connect(String str, Callback callback) {
        GeneratedOutlineSupport1.outline158("Connect to: ", str);
        Peripheral retrieveOrCreatePeripheral = retrieveOrCreatePeripheral(str);
        if (retrieveOrCreatePeripheral == null) {
            callback.invoke("Invalid peripheral uuid");
        } else {
            retrieveOrCreatePeripheral.connect(callback, getCurrentActivity());
        }
    }

    @ReactMethod
    public void createBond(String str, String str2, Callback callback) {
        GeneratedOutlineSupport1.outline158("Request bond to: ", str);
        for (BluetoothDevice bluetoothDevice : getBluetoothAdapter().getBondedDevices()) {
            if (str.equalsIgnoreCase(bluetoothDevice.getAddress())) {
                callback.invoke(new Object[0]);
                return;
            }
        }
        Peripheral retrieveOrCreatePeripheral = retrieveOrCreatePeripheral(str);
        if (retrieveOrCreatePeripheral == null) {
            callback.invoke("Invalid peripheral uuid");
        } else if (this.bondRequest != null) {
            callback.invoke("Only allow one bond request at a time");
        } else if (retrieveOrCreatePeripheral.getDevice().createBond()) {
            String str3 = "Request bond successful for: " + str;
            this.bondRequest = new BondRequest(str, str2, callback);
        } else {
            callback.invoke("Create bond request fail");
        }
    }

    @ReactMethod
    public void disconnect(String str, boolean z, Callback callback) {
        GeneratedOutlineSupport1.outline158("Disconnect from: ", str);
        Peripheral peripheral = this.peripherals.get(str);
        if (peripheral != null) {
            peripheral.disconnect(z);
            callback.invoke(new Object[0]);
            return;
        }
        callback.invoke("Peripheral not found");
    }

    @ReactMethod
    public void enableBluetooth(Callback callback) {
        if (getBluetoothAdapter() == null) {
            callback.invoke("No bluetooth support");
        } else if (!getBluetoothAdapter().isEnabled()) {
            this.enableBluetoothCallback = callback;
            Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
            if (getCurrentActivity() == null) {
                callback.invoke("Current activity not available");
            } else {
                getCurrentActivity().startActivityForResult(intent, 539);
            }
        } else {
            callback.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void getBondedPeripherals(Callback callback) {
        Peripheral peripheral;
        WritableArray createArray = Arguments.createArray();
        for (BluetoothDevice bluetoothDevice : getBluetoothAdapter().getBondedDevices()) {
            int i = Build.VERSION.SDK_INT;
            if (!this.forceLegacy) {
                peripheral = new LollipopPeripheral(bluetoothDevice, this.reactContext);
            } else {
                peripheral = new Peripheral(bluetoothDevice, this.reactContext);
            }
            createArray.pushMap(peripheral.asWritableMap());
        }
        callback.invoke(null, createArray);
    }

    @ReactMethod
    public void getConnectedPeripherals(ReadableArray readableArray, Callback callback) {
        WritableArray createArray = Arguments.createArray();
        if (getBluetoothAdapter() == null) {
            callback.invoke("No bluetooth support");
            return;
        }
        for (BluetoothDevice bluetoothDevice : getBluetoothManager().getConnectedDevices(7)) {
            createArray.pushMap(savePeripheral(bluetoothDevice).asWritableMap());
        }
        callback.invoke(null, createArray);
    }

    @ReactMethod
    public void getDiscoveredPeripherals(Callback callback) {
        WritableArray createArray = Arguments.createArray();
        synchronized (this.peripherals) {
            for (Map.Entry<String, Peripheral> entry : this.peripherals.entrySet()) {
                createArray.pushMap(entry.getValue().asWritableMap());
            }
        }
        callback.invoke(null, createArray);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "BleManager";
    }

    public Peripheral getPeripheral(BluetoothDevice bluetoothDevice) {
        return this.peripherals.get(bluetoothDevice.getAddress());
    }

    public ReactApplicationContext getReactContext() {
        return this.reactContext;
    }

    @ReactMethod
    public void read(String str, String str2, String str3, Callback callback) {
        GeneratedOutlineSupport1.outline158("Read from: ", str);
        if (str2 != null && str3 != null) {
            Peripheral peripheral = this.peripherals.get(str);
            if (peripheral != null) {
                peripheral.read(UUIDHelper.uuidFromString(str2), UUIDHelper.uuidFromString(str3), callback);
                return;
            } else {
                callback.invoke("Peripheral not found", null);
                return;
            }
        }
        callback.invoke("ServiceUUID and characteristicUUID required.");
    }

    @ReactMethod
    public void readRSSI(String str, Callback callback) {
        GeneratedOutlineSupport1.outline158("Read RSSI from: ", str);
        Peripheral peripheral = this.peripherals.get(str);
        if (peripheral != null) {
            peripheral.readRSSI(callback);
        } else {
            callback.invoke("Peripheral not found", null);
        }
    }

    @ReactMethod
    public void refreshCache(String str, Callback callback) {
        GeneratedOutlineSupport1.outline158("Refershing cache for: ", str);
        Peripheral peripheral = this.peripherals.get(str);
        if (peripheral != null) {
            peripheral.refreshCache(callback);
        } else {
            callback.invoke("Peripheral not found");
        }
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    @ReactMethod
    public void removePeripheral(String str, Callback callback) {
        GeneratedOutlineSupport1.outline158("Removing from list: ", str);
        Peripheral peripheral = this.peripherals.get(str);
        if (peripheral != null) {
            synchronized (this.peripherals) {
                if (peripheral.isConnected()) {
                    callback.invoke("Peripheral can not be removed while connected");
                } else {
                    this.peripherals.remove(str);
                    callback.invoke(new Object[0]);
                }
            }
            return;
        }
        callback.invoke("Peripheral not found");
    }

    @ReactMethod
    public void requestConnectionPriority(String str, int i, Callback callback) {
        String str2 = "Request connection priority of " + i + " from: " + str;
        Peripheral peripheral = this.peripherals.get(str);
        if (peripheral != null) {
            peripheral.requestConnectionPriority(i, callback);
        } else {
            callback.invoke("Peripheral not found", null);
        }
    }

    @ReactMethod
    public void requestMTU(String str, int i, Callback callback) {
        String str2 = "Request MTU of " + i + " bytes from: " + str;
        Peripheral peripheral = this.peripherals.get(str);
        if (peripheral != null) {
            peripheral.requestMTU(i, callback);
        } else {
            callback.invoke("Peripheral not found", null);
        }
    }

    @ReactMethod
    public void retrieveServices(String str, ReadableArray readableArray, Callback callback) {
        GeneratedOutlineSupport1.outline158("Retrieve services from: ", str);
        Peripheral peripheral = this.peripherals.get(str);
        if (peripheral != null) {
            peripheral.retrieveServices(callback);
        } else {
            callback.invoke("Peripheral not found", null);
        }
    }

    @ReactMethod
    public void scan(ReadableArray readableArray, int i, boolean z, ReadableMap readableMap, Callback callback) {
        if (getBluetoothAdapter() == null) {
            callback.invoke("No bluetooth support");
        } else if (!getBluetoothAdapter().isEnabled()) {
        } else {
            synchronized (this.peripherals) {
                Iterator<Map.Entry<String, Peripheral>> it2 = this.peripherals.entrySet().iterator();
                while (it2.hasNext()) {
                    if (!it2.next().getValue().isConnected()) {
                        it2.remove();
                    }
                }
            }
            ScanManager scanManager = this.scanManager;
            if (scanManager == null) {
                return;
            }
            scanManager.scan(readableArray, i, readableMap, callback);
        }
    }

    public void sendEvent(String str, @Nullable WritableMap writableMap) {
        ((RCTNativeAppEventEmitter) getReactApplicationContext().getJSModule(RCTNativeAppEventEmitter.class)).emit(str, writableMap);
    }

    @ReactMethod
    public void start(ReadableMap readableMap, Callback callback) {
        if (getBluetoothAdapter() == null) {
            callback.invoke("No bluetooth support");
            return;
        }
        this.forceLegacy = false;
        if (readableMap.hasKey("forceLegacy")) {
            this.forceLegacy = readableMap.getBoolean("forceLegacy");
        }
        int i = Build.VERSION.SDK_INT;
        if (!this.forceLegacy) {
            this.scanManager = new LollipopScanManager(this.reactContext, this);
        } else {
            this.scanManager = new LegacyScanManager(this.reactContext, this);
        }
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        this.context.registerReceiver(this.mReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter("android.bluetooth.device.action.PAIRING_REQUEST");
        intentFilter2.setPriority(1000);
        this.context.registerReceiver(this.mReceiver, intentFilter2);
        callback.invoke(new Object[0]);
    }

    @ReactMethod
    public void startNotification(String str, String str2, String str3, Callback callback) {
        if (str2 != null && str3 != null) {
            Peripheral peripheral = this.peripherals.get(str);
            if (peripheral != null) {
                peripheral.registerNotify(UUIDHelper.uuidFromString(str2), UUIDHelper.uuidFromString(str3), 1, callback);
                return;
            } else {
                callback.invoke("Peripheral not found");
                return;
            }
        }
        callback.invoke("ServiceUUID and characteristicUUID required.");
    }

    @ReactMethod
    public void startNotificationUseBuffer(String str, String str2, String str3, Integer num, Callback callback) {
        if (str2 != null && str3 != null) {
            Peripheral peripheral = this.peripherals.get(str);
            if (peripheral != null) {
                peripheral.registerNotify(UUIDHelper.uuidFromString(str2), UUIDHelper.uuidFromString(str3), num, callback);
                return;
            } else {
                callback.invoke("Peripheral not found");
                return;
            }
        }
        callback.invoke("ServiceUUID and characteristicUUID required.");
    }

    @ReactMethod
    public void stopNotification(String str, String str2, String str3, Callback callback) {
        if (str2 != null && str3 != null) {
            Peripheral peripheral = this.peripherals.get(str);
            if (peripheral != null) {
                peripheral.removeNotify(UUIDHelper.uuidFromString(str2), UUIDHelper.uuidFromString(str3), callback);
                return;
            } else {
                callback.invoke("Peripheral not found");
                return;
            }
        }
        callback.invoke("ServiceUUID and characteristicUUID required.");
    }

    @ReactMethod
    public void stopScan(Callback callback) {
        if (getBluetoothAdapter() == null) {
            callback.invoke("No bluetooth support");
        } else if (!getBluetoothAdapter().isEnabled()) {
            callback.invoke(new Object[0]);
        } else {
            ScanManager scanManager = this.scanManager;
            if (scanManager == null) {
                return;
            }
            scanManager.stopScan(callback);
            sendEvent("BleManagerStopScan", Arguments.createMap());
        }
    }

    @ReactMethod
    public void write(String str, String str2, String str3, ReadableArray readableArray, Integer num, Callback callback) {
        GeneratedOutlineSupport1.outline158("Write to: ", str);
        if (str2 != null && str3 != null) {
            Peripheral peripheral = this.peripherals.get(str);
            if (peripheral != null) {
                byte[] bArr = new byte[readableArray.size()];
                for (int i = 0; i < readableArray.size(); i++) {
                    bArr[i] = new Integer(readableArray.getInt(i)).byteValue();
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Message(");
                outline107.append(bArr.length);
                outline107.append("): ");
                outline107.append(bytesToHex(bArr));
                outline107.toString();
                peripheral.write(UUIDHelper.uuidFromString(str2), UUIDHelper.uuidFromString(str3), bArr, num, null, callback, 2);
                return;
            }
            callback.invoke("Peripheral not found");
            return;
        }
        callback.invoke("ServiceUUID and characteristicUUID required.");
    }

    @ReactMethod
    public void writeWithoutResponse(String str, String str2, String str3, ReadableArray readableArray, Integer num, Integer num2, Callback callback) {
        GeneratedOutlineSupport1.outline158("Write without response to: ", str);
        if (str2 != null && str3 != null) {
            Peripheral peripheral = this.peripherals.get(str);
            if (peripheral != null) {
                byte[] bArr = new byte[readableArray.size()];
                for (int i = 0; i < readableArray.size(); i++) {
                    bArr[i] = new Integer(readableArray.getInt(i)).byteValue();
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Message(");
                outline107.append(bArr.length);
                outline107.append("): ");
                outline107.append(bytesToHex(bArr));
                outline107.toString();
                peripheral.write(UUIDHelper.uuidFromString(str2), UUIDHelper.uuidFromString(str3), bArr, num, num2, callback, 1);
                return;
            }
            callback.invoke("Peripheral not found");
            return;
        }
        callback.invoke("ServiceUUID and characteristicUUID required.");
    }

    /* loaded from: classes3.dex */
    private class BondRequest {
        private Callback callback;
        private String pin;
        private String uuid;

        BondRequest(String str, Callback callback) {
            this.uuid = str;
            this.callback = callback;
        }

        BondRequest(String str, String str2, Callback callback) {
            this.uuid = str;
            this.pin = str2;
            this.callback = callback;
        }
    }

    public Peripheral savePeripheral(Peripheral peripheral) {
        synchronized (this.peripherals) {
            this.peripherals.put(peripheral.getDevice().getAddress(), peripheral);
        }
        return peripheral;
    }
}
