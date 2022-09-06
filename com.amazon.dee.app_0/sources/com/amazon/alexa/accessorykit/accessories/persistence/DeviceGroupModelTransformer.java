package com.amazon.alexa.accessorykit.accessories.persistence;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.CurrentTimeMillisProvider;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.deecomms.common.Constants;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
import java.util.List;
/* loaded from: classes6.dex */
public class DeviceGroupModelTransformer implements ArrayModelTransformer<List<DeviceGroup>>, MapModelTransformer<DeviceGroup> {
    @VisibleForTesting
    static final String KEY_DEVICES = "devices";
    @VisibleForTesting
    static final String KEY_ID = "id";
    @VisibleForTesting
    static final String KEY_IDENTIFIER = "identifier";
    @VisibleForTesting
    static final String KEY_STANDBY_EXPIRATION_TIMESTAMP = "standbyExpirationTimestamp";
    @VisibleForTesting
    static final String KEY_STANDBY_REASON = "standbyReason";
    @VisibleForTesting
    static final String KEY_TRANSPORT = "transport";
    private final ContainerProvider containerProvider;
    private final CurrentTimeMillisProvider currentTimeMillisProvider;
    private final MapModelTransformer<Device> deviceMapper;

    /* loaded from: classes6.dex */
    public static class DeviceTransformer implements MapModelTransformer<Device> {
        private final ContainerProvider containerProvider;

        public DeviceTransformer(ContainerProvider containerProvider) {
            this.containerProvider = containerProvider;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
        /* renamed from: transform */
        public Device mo626transform(ReadableMap readableMap) throws ParseException {
            throw new ParseException("Not yet implemented", 0);
        }

        @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
        public WritableMap transformToMap(Device device) {
            WritableMap map = this.containerProvider.getMap();
            map.putString("name", device.getName());
            map.putString(Constants.BUNDLE_SERIAL_NUMBER, device.getSerialNumber());
            map.putString("type", device.getType());
            map.putInt("deviceId", device.getDeviceId().intValue());
            map.putInt("color", device.getColor().intValue());
            return map;
        }
    }

    public DeviceGroupModelTransformer(ContainerProvider containerProvider, CurrentTimeMillisProvider currentTimeMillisProvider) {
        this(containerProvider, currentTimeMillisProvider, new DeviceTransformer(containerProvider));
    }

    @VisibleForTesting
    DeviceGroupModelTransformer(ContainerProvider containerProvider, CurrentTimeMillisProvider currentTimeMillisProvider, MapModelTransformer<Device> mapModelTransformer) {
        this.containerProvider = containerProvider;
        this.currentTimeMillisProvider = currentTimeMillisProvider;
        this.deviceMapper = mapModelTransformer;
    }

    @Override // com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer
    public WritableArray transformToArray(List<DeviceGroup> list) throws NotSerializableException {
        WritableArray array = this.containerProvider.getArray();
        for (DeviceGroup deviceGroup : list) {
            array.pushMap(transformToMap(deviceGroup));
        }
        return array;
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(DeviceGroup deviceGroup) throws NotSerializableException {
        WritableMap map = this.containerProvider.getMap();
        map.putString("identifier", deviceGroup.getIdentifier());
        map.putString("transport", deviceGroup.getTransportType().name());
        map.putString("condition", deviceGroup.getCondition(this.currentTimeMillisProvider.provideCurrentTimeMillis()).name());
        map.putDouble(KEY_STANDBY_EXPIRATION_TIMESTAMP, Long.valueOf(deviceGroup.getStandbyExpirationTimestamp()).doubleValue());
        map.putString(KEY_STANDBY_REASON, deviceGroup.getStandbyReason().name());
        map.putInt("id", (int) deviceGroup.getId());
        WritableArray array = this.containerProvider.getArray();
        for (Device device : deviceGroup.getDevices()) {
            array.pushMap(this.deviceMapper.transformToMap(device));
        }
        map.putArray("devices", array);
        return map;
    }

    @Override // com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer
    /* renamed from: transform  reason: collision with other method in class */
    public List<DeviceGroup> mo597transform(ReadableArray readableArray) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public DeviceGroup mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }
}
