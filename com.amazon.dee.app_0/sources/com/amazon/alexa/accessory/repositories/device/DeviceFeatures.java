package com.amazon.alexa.accessory.repositories.device;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes6.dex */
public class DeviceFeatures {
    private static final int FEATURES_PER_INT = 32;
    public static final int FEATURE_ID_AUDIO_PLAYBACK_STATUS_OBSERVER = 18;
    private static final int INITIAL_FEATURE_NUMBER = 1;
    private static final String TAG = "DeviceFeatures";
    private List<DeviceFeature> features;

    public DeviceFeatures(List<DeviceFeature> list) {
        Preconditions.notNull(list, NetworkConstants.FEATURES_KEY);
        this.features = list;
    }

    public static DeviceFeatures fromProtobuf(Device.DeviceFeatures deviceFeatures) {
        Preconditions.notNull(deviceFeatures, NetworkConstants.FEATURES_KEY);
        LinkedList newLinkedList = Lists.newLinkedList();
        Map<Integer, Device.FeatureProperties> indexPropertiesById = indexPropertiesById(deviceFeatures.getFeaturePropertiesList());
        int features = deviceFeatures.getFeatures();
        for (int i = 0; i < 32; i++) {
            if (((1 << i) & features) != 0) {
                int generateId = generateId(0, i);
                newLinkedList.add(new DeviceFeature(generateId, indexPropertiesById.get(Integer.valueOf(generateId))));
            }
        }
        return new DeviceFeatures(newLinkedList);
    }

    @VisibleForTesting
    static int generateId(int i, int i2) {
        return GeneratedOutlineSupport1.outline3(i, 32, i2, 1);
    }

    private static Map<Integer, Device.FeatureProperties> indexPropertiesById(List<Device.FeatureProperties> list) {
        HashMap hashMap = new HashMap();
        for (Device.FeatureProperties featureProperties : list) {
            if (hashMap.containsKey(Integer.valueOf(featureProperties.getFeatureId()))) {
                Logger.e("%s: Found multiple properties with the same id: %d. Using the last version.", TAG, Integer.valueOf(featureProperties.getFeatureId()));
            }
            hashMap.put(Integer.valueOf(featureProperties.getFeatureId()), featureProperties);
        }
        return hashMap;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() == DeviceFeatures.class) {
            return Objects.equals(this.features, ((DeviceFeatures) obj).features);
        }
        return false;
    }

    public List<DeviceFeature> getFeatures() {
        return this.features;
    }

    public int hashCode() {
        return Objects.hash(this.features);
    }
}
