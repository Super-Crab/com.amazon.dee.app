package com.amazon.alexa.accessorykit.accessories.session.device;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.repositories.device.DeviceFeature;
import com.amazon.alexa.accessory.repositories.device.SubFeature;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
import java.util.List;
/* loaded from: classes6.dex */
public class DeviceFeatureModelTransformer implements MapModelTransformer<DeviceFeature> {
    private static final String ID_KEY = "id";
    private static final String SUB_FEATURES_KEY = "sub_features";
    private static final String VERSION_KEY = "version";
    private final ArrayModelTransformer<List<SubFeature>> arrayTransformer;
    private final ContainerProvider containerProvider;

    public DeviceFeatureModelTransformer(ContainerProvider containerProvider, ArrayModelTransformer<List<SubFeature>> arrayModelTransformer) {
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(arrayModelTransformer, "arrayTransformer");
        this.containerProvider = containerProvider;
        this.arrayTransformer = arrayModelTransformer;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public DeviceFeature mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(DeviceFeature deviceFeature) throws NotSerializableException {
        WritableMap map = this.containerProvider.getMap();
        map.putInt("id", deviceFeature.getId());
        map.putArray(SUB_FEATURES_KEY, this.arrayTransformer.transformToArray(deviceFeature.getSubFeatures()));
        if (deviceFeature.getVersion() != null) {
            map.putInt("version", deviceFeature.getVersion().intValue());
        }
        return map;
    }
}
