package com.amazon.clouddrive.model;

import java.util.Map;
/* loaded from: classes11.dex */
public class SourceInfo extends BaseSourceInfo {
    private BaseDeviceInfo device;
    private Map<String, MediaTypeUsage> sourceUsage;

    @Override // com.amazon.clouddrive.model.BaseSourceInfo
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SourceInfo) && compareTo((BaseSourceInfo) ((SourceInfo) obj)) == 0;
    }

    public BaseDeviceInfo getDevice() {
        return this.device;
    }

    public Map<String, MediaTypeUsage> getSourceUsage() {
        return this.sourceUsage;
    }

    @Override // com.amazon.clouddrive.model.BaseSourceInfo
    public int hashCode() {
        int i = 0;
        int hashCode = (getSourceUsage() == null ? 0 : getSourceUsage().hashCode()) + 1;
        if (getDevice() != null) {
            i = getDevice().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setDevice(BaseDeviceInfo baseDeviceInfo) {
        this.device = baseDeviceInfo;
    }

    public void setSourceUsage(Map<String, MediaTypeUsage> map) {
        this.sourceUsage = map;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.BaseSourceInfo, java.lang.Comparable
    public int compareTo(BaseSourceInfo baseSourceInfo) {
        if (baseSourceInfo == null) {
            return -1;
        }
        if (baseSourceInfo == this) {
            return 0;
        }
        if (!(baseSourceInfo instanceof SourceInfo)) {
            return 1;
        }
        SourceInfo sourceInfo = (SourceInfo) baseSourceInfo;
        Map<String, MediaTypeUsage> sourceUsage = getSourceUsage();
        Map<String, MediaTypeUsage> sourceUsage2 = sourceInfo.getSourceUsage();
        if (sourceUsage != sourceUsage2) {
            if (sourceUsage == null) {
                return -1;
            }
            if (sourceUsage2 == null) {
                return 1;
            }
            if (sourceUsage instanceof Comparable) {
                int compareTo = ((Comparable) sourceUsage).compareTo(sourceUsage2);
                if (compareTo != 0) {
                    return compareTo;
                }
            } else if (!sourceUsage.equals(sourceUsage2)) {
                int hashCode = sourceUsage.hashCode();
                int hashCode2 = sourceUsage2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        BaseDeviceInfo device = getDevice();
        BaseDeviceInfo device2 = sourceInfo.getDevice();
        if (device != device2) {
            if (device == null) {
                return -1;
            }
            if (device2 == null) {
                return 1;
            }
            int compareTo2 = device.compareTo(device2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return super.compareTo(baseSourceInfo);
    }
}
