package com.amazon.clouddrive.model;

import java.util.Map;
/* loaded from: classes11.dex */
public class BaseDeviceInfo implements Comparable<BaseDeviceInfo> {
    private String creationTime;
    private String deviceClass;
    private String deviceFriendlyName;
    private String deviceId;
    private String deviceModel;
    private String devicePlatform;
    private String deviceStatus;
    private String lastModifiedTime;
    private Map<String, String> osVersionHistory;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BaseDeviceInfo) && compareTo((BaseDeviceInfo) obj) == 0;
    }

    public String getCreationTime() {
        return this.creationTime;
    }

    public String getDeviceClass() {
        return this.deviceClass;
    }

    public String getDeviceFriendlyName() {
        return this.deviceFriendlyName;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public String getDevicePlatform() {
        return this.devicePlatform;
    }

    public String getDeviceStatus() {
        return this.deviceStatus;
    }

    public String getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public Map<String, String> getOsVersionHistory() {
        return this.osVersionHistory;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getDeviceModel() == null ? 0 : getDeviceModel().hashCode()) + 1 + (getCreationTime() == null ? 0 : getCreationTime().hashCode()) + (getDeviceId() == null ? 0 : getDeviceId().hashCode()) + (getDeviceStatus() == null ? 0 : getDeviceStatus().hashCode()) + (getDevicePlatform() == null ? 0 : getDevicePlatform().hashCode()) + (getDeviceClass() == null ? 0 : getDeviceClass().hashCode()) + (getOsVersionHistory() == null ? 0 : getOsVersionHistory().hashCode()) + (getDeviceFriendlyName() == null ? 0 : getDeviceFriendlyName().hashCode());
        if (getLastModifiedTime() != null) {
            i = getLastModifiedTime().hashCode();
        }
        return hashCode + i;
    }

    public void setCreationTime(String str) {
        this.creationTime = str;
    }

    public void setDeviceClass(String str) {
        this.deviceClass = str;
    }

    public void setDeviceFriendlyName(String str) {
        this.deviceFriendlyName = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public void setDevicePlatform(String str) {
        this.devicePlatform = str;
    }

    public void setDeviceStatus(String str) {
        this.deviceStatus = str;
    }

    public void setLastModifiedTime(String str) {
        this.lastModifiedTime = str;
    }

    public void setOsVersionHistory(Map<String, String> map) {
        this.osVersionHistory = map;
    }

    @Override // java.lang.Comparable
    public int compareTo(BaseDeviceInfo baseDeviceInfo) {
        if (baseDeviceInfo == null) {
            return -1;
        }
        if (baseDeviceInfo == this) {
            return 0;
        }
        String deviceModel = getDeviceModel();
        String deviceModel2 = baseDeviceInfo.getDeviceModel();
        if (deviceModel != deviceModel2) {
            if (deviceModel == null) {
                return -1;
            }
            if (deviceModel2 == null) {
                return 1;
            }
            int compareTo = deviceModel.compareTo(deviceModel2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String creationTime = getCreationTime();
        String creationTime2 = baseDeviceInfo.getCreationTime();
        if (creationTime != creationTime2) {
            if (creationTime == null) {
                return -1;
            }
            if (creationTime2 == null) {
                return 1;
            }
            int compareTo2 = creationTime.compareTo(creationTime2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String deviceId = getDeviceId();
        String deviceId2 = baseDeviceInfo.getDeviceId();
        if (deviceId != deviceId2) {
            if (deviceId == null) {
                return -1;
            }
            if (deviceId2 == null) {
                return 1;
            }
            int compareTo3 = deviceId.compareTo(deviceId2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        String deviceStatus = getDeviceStatus();
        String deviceStatus2 = baseDeviceInfo.getDeviceStatus();
        if (deviceStatus != deviceStatus2) {
            if (deviceStatus == null) {
                return -1;
            }
            if (deviceStatus2 == null) {
                return 1;
            }
            int compareTo4 = deviceStatus.compareTo(deviceStatus2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        String devicePlatform = getDevicePlatform();
        String devicePlatform2 = baseDeviceInfo.getDevicePlatform();
        if (devicePlatform != devicePlatform2) {
            if (devicePlatform == null) {
                return -1;
            }
            if (devicePlatform2 == null) {
                return 1;
            }
            int compareTo5 = devicePlatform.compareTo(devicePlatform2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        String deviceClass = getDeviceClass();
        String deviceClass2 = baseDeviceInfo.getDeviceClass();
        if (deviceClass != deviceClass2) {
            if (deviceClass == null) {
                return -1;
            }
            if (deviceClass2 == null) {
                return 1;
            }
            int compareTo6 = deviceClass.compareTo(deviceClass2);
            if (compareTo6 != 0) {
                return compareTo6;
            }
        }
        Map<String, String> osVersionHistory = getOsVersionHistory();
        Map<String, String> osVersionHistory2 = baseDeviceInfo.getOsVersionHistory();
        if (osVersionHistory != osVersionHistory2) {
            if (osVersionHistory == null) {
                return -1;
            }
            if (osVersionHistory2 == null) {
                return 1;
            }
            if (osVersionHistory instanceof Comparable) {
                int compareTo7 = ((Comparable) osVersionHistory).compareTo(osVersionHistory2);
                if (compareTo7 != 0) {
                    return compareTo7;
                }
            } else if (!osVersionHistory.equals(osVersionHistory2)) {
                int hashCode = osVersionHistory.hashCode();
                int hashCode2 = osVersionHistory2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        String deviceFriendlyName = getDeviceFriendlyName();
        String deviceFriendlyName2 = baseDeviceInfo.getDeviceFriendlyName();
        if (deviceFriendlyName != deviceFriendlyName2) {
            if (deviceFriendlyName == null) {
                return -1;
            }
            if (deviceFriendlyName2 == null) {
                return 1;
            }
            int compareTo8 = deviceFriendlyName.compareTo(deviceFriendlyName2);
            if (compareTo8 != 0) {
                return compareTo8;
            }
        }
        String lastModifiedTime = getLastModifiedTime();
        String lastModifiedTime2 = baseDeviceInfo.getLastModifiedTime();
        if (lastModifiedTime != lastModifiedTime2) {
            if (lastModifiedTime == null) {
                return -1;
            }
            if (lastModifiedTime2 == null) {
                return 1;
            }
            int compareTo9 = lastModifiedTime.compareTo(lastModifiedTime2);
            if (compareTo9 != 0) {
                return compareTo9;
            }
        }
        return 0;
    }
}
