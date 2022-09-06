package com.amazon.alexa.accessorykit.accessories.session.firmware;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.kota.UpdateRequest;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class UpdateRequestModelTransformer implements MapModelTransformer<UpdateRequest> {
    @VisibleForTesting
    static final String JSON_BUILD_DIMENSION = "buildDimension";
    @VisibleForTesting
    static final String JSON_COMPONENT_ID = "componentId";
    @VisibleForTesting
    static final String JSON_COMPONENT_VERSION = "componentVersion";
    @VisibleForTesting
    static final String JSON_DEVICE_SERIAL_NUMBER = "deviceSerialNumber";
    @VisibleForTesting
    static final String JSON_DEVICE_TYPE = "deviceType";
    @VisibleForTesting
    static final String JSON_URL = "url";
    private final ContainerProvider containerProvider;

    public UpdateRequestModelTransformer(ContainerProvider containerProvider) {
        Preconditions.notNull(containerProvider, "containerProvider");
        this.containerProvider = containerProvider;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public UpdateRequest mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(UpdateRequest updateRequest) {
        WritableMap map = this.containerProvider.getMap();
        map.putString(JSON_BUILD_DIMENSION, updateRequest.getBuildDimension());
        map.putString("deviceSerialNumber", updateRequest.getDeviceSerialNumber());
        map.putString("deviceType", updateRequest.getDeviceType());
        map.putString(JSON_COMPONENT_ID, updateRequest.getComponentId());
        map.putInt(JSON_COMPONENT_VERSION, updateRequest.getComponentVersion());
        map.putString("url", updateRequest.getUrl());
        return map;
    }
}
