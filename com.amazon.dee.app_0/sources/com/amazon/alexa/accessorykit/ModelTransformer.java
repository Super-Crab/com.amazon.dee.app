package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryScanRecord;
import com.amazon.alexa.accessory.persistence.device.DeviceContract;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.List;
import java.util.Set;
/* loaded from: classes6.dex */
public class ModelTransformer {
    public static final String KEY_ACCESSORY = "accessory";
    public static final String KEY_ACCESSORY_SCAN_RECORD = "accessoryScanRecord";
    public static final String KEY_BATTERY = "battery";
    public static final String KEY_BATTERY_LEVEL = "level";
    public static final String KEY_BATTERY_SCALE = "scale";
    public static final String KEY_BATTERY_STATUS = "status";
    public static final String KEY_CONDITION = "condition";
    public static final String KEY_CONNECTED = "connected";
    public static final String KEY_DEVICE_TYPE = "deviceType";
    public static final String KEY_DSN = "dsn";
    public static final String KEY_FEATURE = "feature";
    public static final String KEY_FIRMWARE_LOCALE = "locale";
    public static final String KEY_FIRMWARE_NAME = "name";
    public static final String KEY_FIRMWARE_VERSION = "version";
    public static final String KEY_FIRMWARE_VERSION_NAME = "versionName";
    public static final String KEY_FW_VERSION = "firmwareVersion";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SUPPORTED_WAKEWORDS = "supported_wakewords";
    public static final String KEY_TRANSPORT = "transport";
    public static final String KEY_VALUE = "value";
    private Accessory accessory;
    private final ContainerProvider containerProvider;

    /* loaded from: classes6.dex */
    static final class AccessoryKeys {
        static final String ID = "id";
        static final String NAME = "name";
        static final String TRANSPORT = "transport";

        AccessoryKeys() {
        }
    }

    /* loaded from: classes6.dex */
    static final class AccessoryScanRecordKeys {
        static final String COLOR = "color";
        static final String HAS_AN_UPDATE_AVAILABLE = "hasAnUpdateAvailable";
        static final String IS_DISCOVERABLE_OVER_BT_CLASSIC = "isDiscoverableOverBTClassic";
        static final String IS_IN_OOBE_MODE = "isInOOBEMode";
        static final String PREFERS_RFCOMM_CONNECTION = "prefersRFCOMMConnection";
        static final String PRODUCT_ID = "productId";
        static final String VENDOR_ID = "vendorId";

        AccessoryScanRecordKeys() {
        }
    }

    public ModelTransformer(ContainerProvider containerProvider) {
        this.containerProvider = containerProvider;
    }

    public WritableArray accessoryInfoToMapArray(List<AccessoryInformation> list) {
        WritableArray array = this.containerProvider.getArray();
        for (AccessoryInformation accessoryInformation : list) {
            array.pushMap(toMap(accessoryInformation));
        }
        return array;
    }

    public WritableMap getEmptyInformationMap() {
        WritableMap map = this.containerProvider.getMap();
        map.putString("dsn", "");
        map.putString("name", "");
        map.putString("firmwareVersion", "");
        map.putBoolean("connected", false);
        map.putString("deviceType", "");
        return map;
    }

    public WritableMap getEmptyMap() {
        return this.containerProvider.getMap();
    }

    public WritableMap toMap(AccessoryInformation accessoryInformation) {
        WritableMap map = this.containerProvider.getMap();
        map.putInt("condition", accessoryInformation.getCondition());
        map.putInt("transport", accessoryInformation.getTransport());
        map.putString("id", accessoryInformation.getIdentifier());
        map.putString("name", accessoryInformation.getName());
        map.putString("deviceType", accessoryInformation.getDeviceType());
        map.putString("dsn", accessoryInformation.getSerialNumber());
        map.putBoolean("connected", accessoryInformation.isConnected());
        return map;
    }

    public WritableMap toMap(Device.DeviceInformation deviceInformation) {
        WritableMap emptyInformationMap = getEmptyInformationMap();
        WritableArray array = this.containerProvider.getArray();
        for (String str : deviceInformation.getSupportedWakewordsList()) {
            array.pushString(str);
        }
        emptyInformationMap.putString("name", deviceInformation.getName());
        emptyInformationMap.putString("deviceType", deviceInformation.getDeviceType());
        emptyInformationMap.putString("dsn", deviceInformation.getSerialNumber());
        emptyInformationMap.putBoolean("connected", deviceInformation.isInitialized());
        emptyInformationMap.putArray(KEY_SUPPORTED_WAKEWORDS, array);
        return emptyInformationMap;
    }

    public WritableMap toMap(Firmware.FirmwareInformation firmwareInformation) {
        WritableMap emptyInformationMap = getEmptyInformationMap();
        emptyInformationMap.putInt("version", firmwareInformation.getVersion());
        emptyInformationMap.putString("locale", firmwareInformation.getLocale());
        emptyInformationMap.putString(KEY_FIRMWARE_VERSION_NAME, firmwareInformation.getVersionName());
        emptyInformationMap.putString("name", firmwareInformation.getName());
        return emptyInformationMap;
    }

    public WritableMap toMap(DeviceContract.Device device) {
        WritableMap emptyInformationMap = getEmptyInformationMap();
        emptyInformationMap.putString("name", device.getName());
        emptyInformationMap.putString("deviceType", device.getType());
        emptyInformationMap.putString("dsn", device.getSerialNumber());
        return emptyInformationMap;
    }

    public WritableMap toMap(DeviceGroup deviceGroup) {
        com.amazon.alexa.accessory.repositories.device.v2.Device device = deviceGroup.getDevice();
        WritableMap emptyInformationMap = getEmptyInformationMap();
        String str = null;
        emptyInformationMap.putString("name", device != null ? device.getName() : null);
        emptyInformationMap.putString("deviceType", device != null ? device.getType() : null);
        if (device != null) {
            str = device.getSerialNumber();
        }
        emptyInformationMap.putString("dsn", str);
        return emptyInformationMap;
    }

    public WritableMap toMap(Accessory accessory) {
        WritableMap map = this.containerProvider.getMap();
        map.putString("name", accessory.getName());
        map.putString("id", accessory.getAddress());
        map.putInt("transport", accessory.getTransport().ordinal());
        return map;
    }

    public WritableMap toMap(AccessoryScanRecord accessoryScanRecord) {
        WritableMap map = this.containerProvider.getMap();
        map.putInt("vendorId", accessoryScanRecord.getVendorId());
        map.putInt("productId", accessoryScanRecord.getProductId());
        map.putInt("color", accessoryScanRecord.getColor());
        map.putBoolean("isInOOBEMode", accessoryScanRecord.isInOOBEMode());
        map.putBoolean("isDiscoverableOverBTClassic", accessoryScanRecord.isDiscoverableOverBTClassic());
        map.putBoolean("hasAnUpdateAvailable", accessoryScanRecord.hasAnUpdateAvailable());
        map.putBoolean("prefersRFCOMMConnection", accessoryScanRecord.prefersRFCOMMConnection());
        return map;
    }

    public WritableArray toMap(Set<Device.DeviceInformation> set) {
        WritableArray array = this.containerProvider.getArray();
        if (set != null) {
            for (Device.DeviceInformation deviceInformation : set) {
                WritableMap map = this.containerProvider.getMap();
                WritableArray array2 = this.containerProvider.getArray();
                for (String str : deviceInformation.getSupportedWakewordsList()) {
                    array2.pushString(str);
                }
                map.putString("name", deviceInformation.getName());
                map.putString("deviceType", deviceInformation.getDeviceType());
                map.putString("dsn", deviceInformation.getSerialNumber());
                map.putBoolean("connected", deviceInformation.isInitialized());
                map.putInt("id", deviceInformation.getDeviceId());
                map.putMap(KEY_BATTERY, toMap(deviceInformation.getBattery()));
                map.putArray(KEY_SUPPORTED_WAKEWORDS, array2);
                array.pushMap(map);
            }
        }
        return array;
    }

    public WritableMap toMap(Device.DeviceBattery deviceBattery) {
        WritableMap map = this.containerProvider.getMap();
        if (deviceBattery != null) {
            map.putInt(KEY_BATTERY_LEVEL, deviceBattery.getLevel());
            map.putInt(KEY_BATTERY_SCALE, deviceBattery.getScale());
            map.putInt("status", deviceBattery.getStatus().ordinal());
        }
        return map;
    }

    public WritableMap toMap(StateOuterClass.State state) {
        WritableMap map = this.containerProvider.getMap();
        if (state == null) {
            return map;
        }
        map.putInt("feature", state.getFeature());
        if (StateOuterClass.State.ValueCase.BOOLEAN.equals(state.getValueCase())) {
            map.putBoolean("value", state.getBoolean());
        } else if (StateOuterClass.State.ValueCase.INTEGER.equals(state.getValueCase())) {
            map.putInt("value", state.getInteger());
        } else {
            map.putNull("value");
        }
        return map;
    }
}
