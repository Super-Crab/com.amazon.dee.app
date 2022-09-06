package com.amazon.alexa.accessorykit.accessories.scanner;

import com.amazon.alexa.accessory.AccessoryInquiryRecord;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.alexa.accessorykit.accessories.persistence.DeviceGroupModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class AccessoryInquiryRecordTransformer implements MapModelTransformer<AccessoryInquiryRecord> {
    private final ContainerProvider containerProvider;
    private final MapModelTransformer<Device> deviceModelTransformer;

    public AccessoryInquiryRecordTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "containerProvider");
        this.containerProvider = containerProvider;
        this.deviceModelTransformer = new DeviceGroupModelTransformer.DeviceTransformer(containerProvider);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public AccessoryInquiryRecord mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not yet implemented", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(AccessoryInquiryRecord accessoryInquiryRecord) throws NotSerializableException {
        Preconditions.notNull(accessoryInquiryRecord, "accessoryInquiryRecord");
        WritableArray array = this.containerProvider.getArray();
        for (Device device : accessoryInquiryRecord.getDevices()) {
            array.pushMap(this.deviceModelTransformer.transformToMap(device));
        }
        WritableMap map = this.containerProvider.getMap();
        map.putArray("devices", array);
        return map;
    }
}
