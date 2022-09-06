package it.innove;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.os.Build;
import android.os.ParcelUuid;
import androidx.annotation.RequiresApi;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.Map;
@RequiresApi(21)
/* loaded from: classes3.dex */
public class LollipopPeripheral extends Peripheral {
    private ScanRecord advertisingData;
    private ScanResult scanResult;

    public LollipopPeripheral(ReactContext reactContext, ScanResult scanResult) {
        super(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes(), reactContext);
        this.advertisingData = scanResult.getScanRecord();
        this.scanResult = scanResult;
    }

    @Override // it.innove.Peripheral
    public WritableMap asWritableMap() {
        WritableMap asWritableMap = super.asWritableMap();
        WritableMap createMap = Arguments.createMap();
        try {
            createMap.putMap("manufacturerData", Peripheral.byteArrayToWritableMap(this.advertisingDataBytes));
            int i = Build.VERSION.SDK_INT;
            createMap.putBoolean("isConnectable", this.scanResult.isConnectable());
            if (this.advertisingData != null) {
                String deviceName = this.advertisingData.getDeviceName();
                if (deviceName != null) {
                    createMap.putString("localName", deviceName.replace("\u0000", ""));
                }
                WritableArray createArray = Arguments.createArray();
                if (this.advertisingData.getServiceUuids() != null && this.advertisingData.getServiceUuids().size() != 0) {
                    for (ParcelUuid parcelUuid : this.advertisingData.getServiceUuids()) {
                        createArray.pushString(UUIDHelper.uuidToString(parcelUuid.getUuid()));
                    }
                }
                createMap.putArray("serviceUUIDs", createArray);
                WritableMap createMap2 = Arguments.createMap();
                if (this.advertisingData.getServiceData() != null) {
                    for (Map.Entry<ParcelUuid, byte[]> entry : this.advertisingData.getServiceData().entrySet()) {
                        if (entry.getValue() != null) {
                            createMap2.putMap(UUIDHelper.uuidToString(entry.getKey().getUuid()), Peripheral.byteArrayToWritableMap(entry.getValue()));
                        }
                    }
                }
                createMap.putMap("serviceData", createMap2);
                createMap.putInt("txPowerLevel", this.advertisingData.getTxPowerLevel());
            }
            asWritableMap.putMap("advertising", createMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return asWritableMap;
    }

    public void updateData(ScanRecord scanRecord) {
        this.advertisingData = scanRecord;
        this.advertisingDataBytes = scanRecord.getBytes();
    }

    public LollipopPeripheral(BluetoothDevice bluetoothDevice, ReactApplicationContext reactApplicationContext) {
        super(bluetoothDevice, reactApplicationContext);
    }
}
