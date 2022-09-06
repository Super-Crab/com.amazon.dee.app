package com.amazon.alexa.accessorykit.accessories.session.device;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.repositories.device.DeviceFeature;
import com.amazon.alexa.accessory.repositories.device.DeviceFeatures;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
import java.util.List;
/* loaded from: classes6.dex */
public class DeviceFeaturesModelTransformer implements MapModelTransformer<DeviceFeatures> {
    private static final String FEATURES_KEY = "features";
    private final ArrayModelTransformer<List<DeviceFeature>> arrayTransformer;
    private final ContainerProvider containerProvider;

    public DeviceFeaturesModelTransformer(ContainerProvider containerProvider, ArrayModelTransformer<List<DeviceFeature>> arrayModelTransformer) {
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(arrayModelTransformer, "arrayTransformer");
        this.containerProvider = containerProvider;
        this.arrayTransformer = arrayModelTransformer;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public DeviceFeatures mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(DeviceFeatures deviceFeatures) throws NotSerializableException {
        WritableMap map = this.containerProvider.getMap();
        map.putArray("features", this.arrayTransformer.transformToArray(deviceFeatures.getFeatures()));
        return map;
    }
}
