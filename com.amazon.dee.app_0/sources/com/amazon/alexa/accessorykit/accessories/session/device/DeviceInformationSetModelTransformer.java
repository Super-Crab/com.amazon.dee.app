package com.amazon.alexa.accessorykit.accessories.session.device;

import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.text.ParseException;
import java.util.Set;
/* loaded from: classes6.dex */
public class DeviceInformationSetModelTransformer implements ArrayModelTransformer<Set<Device.DeviceInformation>> {
    private static final String ASSOCIATED_DEVICES_KEY = "associated_devices";
    private static final String BATTERY = "battery";
    private static final String BATTERY_LEVEL_KEY = "level";
    private static final String BATTERY_SCALE_KEY = "scale";
    private static final String BATTERY_STATUS_KEY = "status";
    private static final String DEVICE_ID_KEY = "device_id";
    private static final String DEVICE_TYPE_KEY = "device_type";
    private static final String NAME_KEY = "name";
    private static final String PRODUCT_COLOR = "product_color";
    private static final String SERIAL_NUMBER_KEY = "serial_number";
    private static final String STATUS_KEY = "status";
    private static final String STATUS_LINK_KEY = "link";
    private static final String STATUS_NFMI_KEY = "nfmi";
    private static final String STATUS_PRESENCE_KEY = "presence";
    private static final String SUPPORTED_SPEECH_INITIATIONS_KEY = "supported_speech_initiations";
    private static final String SUPPORTED_TRANSPORTS_KEY = "supported_transports";
    private static final String SUPPORTED_WAKEWORDS_KEY = "supported_wakewords";
    private final ContainerProvider containerProvider;

    public DeviceInformationSetModelTransformer(ContainerProvider containerProvider) {
        this.containerProvider = containerProvider;
    }

    @Override // com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer
    /* renamed from: transform  reason: collision with other method in class */
    public Set<Device.DeviceInformation> mo597transform(ReadableArray readableArray) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer
    public WritableArray transformToArray(Set<Device.DeviceInformation> set) {
        WritableArray array = this.containerProvider.getArray();
        for (Device.DeviceInformation deviceInformation : set) {
            WritableMap map = this.containerProvider.getMap();
            WritableArray array2 = this.containerProvider.getArray();
            WritableArray array3 = this.containerProvider.getArray();
            WritableArray array4 = this.containerProvider.getArray();
            WritableMap map2 = this.containerProvider.getMap();
            WritableMap map3 = this.containerProvider.getMap();
            WritableArray array5 = this.containerProvider.getArray();
            for (Common.Transport transport : deviceInformation.getSupportedTransportsList()) {
                array2.pushString(transport.name());
            }
            for (String str : deviceInformation.getSupportedWakewordsList()) {
                array3.pushString(str);
            }
            for (Common.SpeechInitiationType speechInitiationType : deviceInformation.getSupportedSpeechInitiationsList()) {
                array4.pushString(speechInitiationType.name());
            }
            map2.putInt("scale", deviceInformation.getBattery().getScale());
            map2.putInt("level", deviceInformation.getBattery().getLevel());
            map2.putString("status", deviceInformation.getBattery().getStatus().name());
            map3.putString("link", deviceInformation.getStatus().getLink().name());
            map3.putString(STATUS_NFMI_KEY, deviceInformation.getStatus().getNfmi().name());
            map3.putString("presence", deviceInformation.getStatus().getPresence().name());
            for (Integer num : deviceInformation.getAssociatedDevicesList()) {
                array5.pushInt(num.intValue());
            }
            map.putString("serial_number", deviceInformation.getSerialNumber());
            map.putString("name", deviceInformation.getName());
            map.putArray(SUPPORTED_TRANSPORTS_KEY, array2);
            map.putString("device_type", deviceInformation.getDeviceType());
            map.putInt("device_id", deviceInformation.getDeviceId());
            map.putMap("battery", map2);
            map.putMap("status", map3);
            map.putInt(PRODUCT_COLOR, deviceInformation.getProductColor());
            map.putArray(ASSOCIATED_DEVICES_KEY, array5);
            map.putArray(SUPPORTED_SPEECH_INITIATIONS_KEY, array4);
            map.putArray("supported_wakewords", array3);
            array.pushMap(map);
        }
        return array;
    }
}
