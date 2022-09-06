package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.SetupSourceRequest;
/* loaded from: classes11.dex */
public class SetupSourceExtendedRequest extends SetupSourceRequest {
    private String deviceLanguage;
    private String pushProvider;
    private String registrationToken;
    private String timeZone;

    public SetupSourceExtendedRequest(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
    }

    @Override // com.amazon.clouddrive.model.SetupSourceRequest, com.amazon.clouddrive.model.PaginatedCloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SetupSourceExtendedRequest) && compareTo((CloudDriveRequest) ((SetupSourceExtendedRequest) obj)) == 0;
    }

    public String getDeviceLanguage() {
        return this.deviceLanguage;
    }

    public String getPushProvider() {
        return this.pushProvider;
    }

    public String getRegistrationToken() {
        return this.registrationToken;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    @Override // com.amazon.clouddrive.model.SetupSourceRequest, com.amazon.clouddrive.model.PaginatedCloudDriveRequest
    public int hashCode() {
        int i = 0;
        int hashCode = (getDeviceLanguage() == null ? 0 : getDeviceLanguage().hashCode()) + 1 + (getRegistrationToken() == null ? 0 : getRegistrationToken().hashCode()) + (getPushProvider() == null ? 0 : getPushProvider().hashCode());
        if (getTimeZone() != null) {
            i = getTimeZone().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setDeviceLanguage(String str) {
        this.deviceLanguage = str;
    }

    public void setPushProvider(String str) {
        this.pushProvider = str;
    }

    public void setRegistrationToken(String str) {
        this.registrationToken = str;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.SetupSourceRequest, com.amazon.clouddrive.model.PaginatedCloudDriveRequest, java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof SetupSourceExtendedRequest)) {
            return 1;
        }
        SetupSourceExtendedRequest setupSourceExtendedRequest = (SetupSourceExtendedRequest) cloudDriveRequest;
        String deviceLanguage = getDeviceLanguage();
        String deviceLanguage2 = setupSourceExtendedRequest.getDeviceLanguage();
        if (deviceLanguage != deviceLanguage2) {
            if (deviceLanguage == null) {
                return -1;
            }
            if (deviceLanguage2 == null) {
                return 1;
            }
            int compareTo = deviceLanguage.compareTo(deviceLanguage2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String pushProvider = getPushProvider();
        String pushProvider2 = setupSourceExtendedRequest.getPushProvider();
        if (pushProvider != pushProvider2) {
            if (pushProvider == null) {
                return -1;
            }
            if (pushProvider2 == null) {
                return 1;
            }
            int compareTo2 = pushProvider.compareTo(pushProvider2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String registrationToken = getRegistrationToken();
        String registrationToken2 = setupSourceExtendedRequest.getRegistrationToken();
        if (registrationToken != registrationToken2) {
            if (registrationToken == null) {
                return -1;
            }
            if (registrationToken2 == null) {
                return 1;
            }
            int compareTo3 = registrationToken.compareTo(registrationToken2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        String timeZone = getTimeZone();
        String timeZone2 = setupSourceExtendedRequest.getTimeZone();
        if (timeZone != timeZone2) {
            if (timeZone == null) {
                return -1;
            }
            if (timeZone2 == null) {
                return 1;
            }
            int compareTo4 = timeZone.compareTo(timeZone2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        return 0;
    }
}
