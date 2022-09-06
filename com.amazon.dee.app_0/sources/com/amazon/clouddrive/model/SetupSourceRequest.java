package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class SetupSourceRequest extends PaginatedCloudDriveRequest {
    private String deviceClass;
    private String deviceFriendlyName;
    private String deviceModel;
    private String devicePlatform;
    private String deviceSerialNumber;
    private String osVersion;
    private String sourceApplicationName;
    private String sourceVersion;

    public SetupSourceRequest(String str, String str2, String str3, String str4) {
        this.sourceApplicationName = str;
        this.sourceVersion = str2;
        this.deviceClass = str3;
        this.devicePlatform = str4;
    }

    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SetupSourceRequest) && compareTo((CloudDriveRequest) ((SetupSourceRequest) obj)) == 0;
    }

    public String getDeviceClass() {
        return this.deviceClass;
    }

    public String getDeviceFriendlyName() {
        return this.deviceFriendlyName;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public String getDevicePlatform() {
        return this.devicePlatform;
    }

    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getSourceApplicationName() {
        return this.sourceApplicationName;
    }

    public String getSourceVersion() {
        return this.sourceVersion;
    }

    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveRequest
    public int hashCode() {
        int i = 0;
        int hashCode = (getSourceVersion() == null ? 0 : getSourceVersion().hashCode()) + 1 + (getDeviceFriendlyName() == null ? 0 : getDeviceFriendlyName().hashCode()) + (getDeviceSerialNumber() == null ? 0 : getDeviceSerialNumber().hashCode()) + (getOsVersion() == null ? 0 : getOsVersion().hashCode()) + (getDeviceModel() == null ? 0 : getDeviceModel().hashCode()) + (getDeviceClass() == null ? 0 : getDeviceClass().hashCode()) + (getDevicePlatform() == null ? 0 : getDevicePlatform().hashCode());
        if (getSourceApplicationName() != null) {
            i = getSourceApplicationName().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setDeviceClass(String str) {
        this.deviceClass = str;
    }

    public void setDeviceFriendlyName(String str) {
        this.deviceFriendlyName = str;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public void setDevicePlatform(String str) {
        this.devicePlatform = str;
    }

    public void setDeviceSerialNumber(String str) {
        this.deviceSerialNumber = str;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public void setSourceApplicationName(String str) {
        this.sourceApplicationName = str;
    }

    public void setSourceVersion(String str) {
        this.sourceVersion = str;
    }

    public SetupSourceRequest withDeviceFriendlyName(String str) {
        setDeviceFriendlyName(str);
        return this;
    }

    public SetupSourceRequest withDeviceModel(String str) {
        setDeviceModel(str);
        return this;
    }

    public SetupSourceRequest withDeviceSerialNumber(String str) {
        setDeviceSerialNumber(str);
        return this;
    }

    public SetupSourceRequest withOsVersion(String str) {
        setOsVersion(str);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.PaginatedCloudDriveRequest, java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof SetupSourceRequest)) {
            return 1;
        }
        SetupSourceRequest setupSourceRequest = (SetupSourceRequest) cloudDriveRequest;
        String sourceVersion = getSourceVersion();
        String sourceVersion2 = setupSourceRequest.getSourceVersion();
        if (sourceVersion != sourceVersion2) {
            if (sourceVersion == null) {
                return -1;
            }
            if (sourceVersion2 == null) {
                return 1;
            }
            int compareTo = sourceVersion.compareTo(sourceVersion2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String deviceFriendlyName = getDeviceFriendlyName();
        String deviceFriendlyName2 = setupSourceRequest.getDeviceFriendlyName();
        if (deviceFriendlyName != deviceFriendlyName2) {
            if (deviceFriendlyName == null) {
                return -1;
            }
            if (deviceFriendlyName2 == null) {
                return 1;
            }
            int compareTo2 = deviceFriendlyName.compareTo(deviceFriendlyName2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String deviceSerialNumber = getDeviceSerialNumber();
        String deviceSerialNumber2 = setupSourceRequest.getDeviceSerialNumber();
        if (deviceSerialNumber != deviceSerialNumber2) {
            if (deviceSerialNumber == null) {
                return -1;
            }
            if (deviceSerialNumber2 == null) {
                return 1;
            }
            int compareTo3 = deviceSerialNumber.compareTo(deviceSerialNumber2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        String osVersion = getOsVersion();
        String osVersion2 = setupSourceRequest.getOsVersion();
        if (osVersion != osVersion2) {
            if (osVersion == null) {
                return -1;
            }
            if (osVersion2 == null) {
                return 1;
            }
            int compareTo4 = osVersion.compareTo(osVersion2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        String deviceModel = getDeviceModel();
        String deviceModel2 = setupSourceRequest.getDeviceModel();
        if (deviceModel != deviceModel2) {
            if (deviceModel == null) {
                return -1;
            }
            if (deviceModel2 == null) {
                return 1;
            }
            int compareTo5 = deviceModel.compareTo(deviceModel2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        String deviceClass = getDeviceClass();
        String deviceClass2 = setupSourceRequest.getDeviceClass();
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
        String devicePlatform = getDevicePlatform();
        String devicePlatform2 = setupSourceRequest.getDevicePlatform();
        if (devicePlatform != devicePlatform2) {
            if (devicePlatform == null) {
                return -1;
            }
            if (devicePlatform2 == null) {
                return 1;
            }
            int compareTo7 = devicePlatform.compareTo(devicePlatform2);
            if (compareTo7 != 0) {
                return compareTo7;
            }
        }
        String sourceApplicationName = getSourceApplicationName();
        String sourceApplicationName2 = setupSourceRequest.getSourceApplicationName();
        if (sourceApplicationName != sourceApplicationName2) {
            if (sourceApplicationName == null) {
                return -1;
            }
            if (sourceApplicationName2 == null) {
                return 1;
            }
            int compareTo8 = sourceApplicationName.compareTo(sourceApplicationName2);
            if (compareTo8 != 0) {
                return compareTo8;
            }
        }
        return super.compareTo(cloudDriveRequest);
    }
}
