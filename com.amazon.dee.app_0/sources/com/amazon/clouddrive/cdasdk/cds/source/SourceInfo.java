package com.amazon.clouddrive.cdasdk.cds.source;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
/* loaded from: classes11.dex */
public class SourceInfo extends BaseSourceInfo {
    @JsonProperty("device")
    private BaseDeviceInfo device;
    @JsonProperty("sourceUsage")
    private Map<String, MediaTypeUsage> sourceUsage;

    @Override // com.amazon.clouddrive.cdasdk.cds.source.BaseSourceInfo
    protected boolean canEqual(Object obj) {
        return obj instanceof SourceInfo;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.source.BaseSourceInfo
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SourceInfo)) {
            return false;
        }
        SourceInfo sourceInfo = (SourceInfo) obj;
        if (!sourceInfo.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        BaseDeviceInfo device = getDevice();
        BaseDeviceInfo device2 = sourceInfo.getDevice();
        if (device != null ? !device.equals(device2) : device2 != null) {
            return false;
        }
        Map<String, MediaTypeUsage> sourceUsage = getSourceUsage();
        Map<String, MediaTypeUsage> sourceUsage2 = sourceInfo.getSourceUsage();
        return sourceUsage != null ? sourceUsage.equals(sourceUsage2) : sourceUsage2 == null;
    }

    public BaseDeviceInfo getDevice() {
        return this.device;
    }

    public Map<String, MediaTypeUsage> getSourceUsage() {
        return this.sourceUsage;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.source.BaseSourceInfo
    public int hashCode() {
        int hashCode = super.hashCode();
        BaseDeviceInfo device = getDevice();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (device == null ? 43 : device.hashCode());
        Map<String, MediaTypeUsage> sourceUsage = getSourceUsage();
        int i2 = hashCode2 * 59;
        if (sourceUsage != null) {
            i = sourceUsage.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("device")
    public void setDevice(BaseDeviceInfo baseDeviceInfo) {
        this.device = baseDeviceInfo;
    }

    @JsonProperty("sourceUsage")
    public void setSourceUsage(Map<String, MediaTypeUsage> map) {
        this.sourceUsage = map;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.source.BaseSourceInfo
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SourceInfo(device=");
        outline107.append(getDevice());
        outline107.append(", sourceUsage=");
        outline107.append(getSourceUsage());
        outline107.append(")");
        return outline107.toString();
    }
}
