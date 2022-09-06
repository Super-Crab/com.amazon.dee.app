package com.amazon.alexa.accessory.repositories.device;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Device;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
/* loaded from: classes6.dex */
public class DeviceFeature {
    private static final int DEFAULT_ENVELOPE_VERSION = 0;
    private static final int INITIAL_SUB_FEATURE_ID = 1;
    private static final int SUB_FEATURES_PER_INT = 32;
    private static final String TAG = "DeviceFeature";
    private final int id;
    private final List<SubFeature> subFeatures;
    private final Integer version;

    public DeviceFeature(int i, Device.FeatureProperties featureProperties) {
        Preconditions.precondition(featureProperties == null || featureProperties.getFeatureId() == i, "FeatureProperties' id must match the provided id");
        this.id = i;
        this.version = fetchEnvelopeVersionFromProperties(featureProperties);
        this.subFeatures = fetchSubFeaturesFromProperties(featureProperties);
    }

    private Integer fetchEnvelopeVersionFromProperties(Device.FeatureProperties featureProperties) {
        if (featureProperties == null || featureProperties.getEnvelopeVersion() == 0) {
            return null;
        }
        return Integer.valueOf(featureProperties.getEnvelopeVersion());
    }

    private List<SubFeature> fetchSubFeaturesFromProperties(Device.FeatureProperties featureProperties) {
        if (featureProperties == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int subFeatures = featureProperties.getSubFeatures();
        for (int i = 0; i < 32; i++) {
            if (((1 << i) & subFeatures) != 0) {
                arrayList.add(new SubFeature(generateSubFeatureId(0, i)));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @VisibleForTesting
    static int generateSubFeatureId(int i, int i2) {
        return GeneratedOutlineSupport1.outline3(i, 32, i2, 1);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != DeviceFeature.class) {
            return false;
        }
        DeviceFeature deviceFeature = (DeviceFeature) obj;
        return Objects.equals(Integer.valueOf(this.id), Integer.valueOf(deviceFeature.id)) && Objects.equals(this.version, deviceFeature.version) && Objects.equals(this.subFeatures, deviceFeature.subFeatures);
    }

    public int getId() {
        return this.id;
    }

    public List<SubFeature> getSubFeatures() {
        return this.subFeatures;
    }

    @Nullable
    public Integer getVersion() {
        return this.version;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.id), this.version, this.subFeatures);
    }

    public DeviceFeature(int i, Integer num, List<SubFeature> list) {
        this.id = i;
        this.version = num;
        if (list == null) {
            this.subFeatures = Collections.emptyList();
        } else {
            this.subFeatures = Collections.unmodifiableList(list);
        }
    }
}
