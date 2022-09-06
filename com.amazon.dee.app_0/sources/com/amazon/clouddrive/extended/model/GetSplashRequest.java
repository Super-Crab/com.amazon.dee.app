package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
/* loaded from: classes11.dex */
public class GetSplashRequest implements CloudDriveRequest {
    private String deviceType;
    private String language;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetSplashRequest) && compareTo((CloudDriveRequest) ((GetSplashRequest) obj)) == 0;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getLanguage() {
        return this.language;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getLanguage() == null ? 0 : getLanguage().hashCode()) + 1;
        if (getDeviceType() != null) {
            i = getDeviceType().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof GetSplashRequest)) {
            return 1;
        }
        GetSplashRequest getSplashRequest = (GetSplashRequest) cloudDriveRequest;
        String language = getLanguage();
        String language2 = getSplashRequest.getLanguage();
        if (language != language2) {
            if (language == null) {
                return -1;
            }
            if (language2 == null) {
                return 1;
            }
            int compareTo = language.compareTo(language2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String deviceType = getDeviceType();
        String deviceType2 = getSplashRequest.getDeviceType();
        if (deviceType != deviceType2) {
            if (deviceType == null) {
                return -1;
            }
            if (deviceType2 == null) {
                return 1;
            }
            int compareTo2 = deviceType.compareTo(deviceType2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
