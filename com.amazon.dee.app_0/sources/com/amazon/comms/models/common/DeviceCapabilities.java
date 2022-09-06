package com.amazon.comms.models.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class DeviceCapabilities {
    private List<String> capabilities;
    private String deviceTypeId;

    /* loaded from: classes11.dex */
    public static class DeviceCapabilitiesBuilder {
        private List<String> capabilities;
        private String deviceTypeId;

        DeviceCapabilitiesBuilder() {
        }

        public DeviceCapabilities build() {
            return new DeviceCapabilities(this.deviceTypeId, this.capabilities);
        }

        public DeviceCapabilitiesBuilder capabilities(List<String> list) {
            this.capabilities = list;
            return this;
        }

        public DeviceCapabilitiesBuilder deviceTypeId(String str) {
            this.deviceTypeId = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceCapabilities.DeviceCapabilitiesBuilder(deviceTypeId=");
            outline107.append(this.deviceTypeId);
            outline107.append(", capabilities=");
            return GeneratedOutlineSupport1.outline95(outline107, this.capabilities, ")");
        }
    }

    public DeviceCapabilities() {
    }

    public static DeviceCapabilitiesBuilder builder() {
        return new DeviceCapabilitiesBuilder();
    }

    public List<String> getCapabilities() {
        return this.capabilities;
    }

    public String getDeviceTypeId() {
        return this.deviceTypeId;
    }

    public void setCapabilities(List<String> list) {
        this.capabilities = list;
    }

    public void setDeviceTypeId(String str) {
        this.deviceTypeId = str;
    }

    private DeviceCapabilities(String str, List<String> list) {
        this.deviceTypeId = str;
        this.capabilities = list;
    }
}
