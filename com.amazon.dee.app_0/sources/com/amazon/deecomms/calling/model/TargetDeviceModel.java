package com.amazon.deecomms.calling.model;

import com.amazon.deecomms.ndt.model.CommsFeatureStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.List;
/* loaded from: classes12.dex */
public class TargetDeviceModel {
    @JsonProperty("device")
    private DeviceAttributes mTargetDevice;

    /* loaded from: classes12.dex */
    public static class DeviceAttributes {
        @JsonProperty("commsFeatureStatusList")
        private List<CommsFeatureStatus> commsFeatureStatusList;
        @JsonProperty("deviceGruu")
        private String deviceGruu;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof DeviceAttributes) {
                return Objects.equal(this.deviceGruu, ((DeviceAttributes) obj).deviceGruu);
            }
            return false;
        }

        public List<CommsFeatureStatus> getCommsFeatureStatusList() {
            return this.commsFeatureStatusList;
        }

        public String getDeviceGruu() {
            return this.deviceGruu;
        }

        public int hashCode() {
            return Objects.hashCode(this.deviceGruu);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("deviceGruu", this.deviceGruu).toString();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TargetDeviceModel) {
            return Objects.equal(this.mTargetDevice, ((TargetDeviceModel) obj).mTargetDevice);
        }
        return false;
    }

    public DeviceAttributes getTargetDevice() {
        return this.mTargetDevice;
    }

    public int hashCode() {
        return Objects.hashCode(this.mTargetDevice);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("device", this.mTargetDevice).toString();
    }
}
