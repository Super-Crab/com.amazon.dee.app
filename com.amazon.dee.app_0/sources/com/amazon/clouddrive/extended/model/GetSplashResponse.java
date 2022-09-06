package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
/* loaded from: classes11.dex */
public class GetSplashResponse implements CloudDriveResponse {
    private String splashId;
    private String splashText;
    private String url;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetSplashResponse) && compareTo((CloudDriveResponse) ((GetSplashResponse) obj)) == 0;
    }

    public String getSplashId() {
        return this.splashId;
    }

    public String getSplashText() {
        return this.splashText;
    }

    public String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getSplashText() == null ? 0 : getSplashText().hashCode()) + 1 + (getSplashId() == null ? 0 : getSplashId().hashCode());
        if (getUrl() != null) {
            i = getUrl().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setSplashId(String str) {
        this.splashId = str;
    }

    public void setSplashText(String str) {
        this.splashText = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GetSplashResponse)) {
            return 1;
        }
        GetSplashResponse getSplashResponse = (GetSplashResponse) cloudDriveResponse;
        String splashText = getSplashText();
        String splashText2 = getSplashResponse.getSplashText();
        if (splashText != splashText2) {
            if (splashText == null) {
                return -1;
            }
            if (splashText2 == null) {
                return 1;
            }
            int compareTo = splashText.compareTo(splashText2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String splashId = getSplashId();
        String splashId2 = getSplashResponse.getSplashId();
        if (splashId != splashId2) {
            if (splashId == null) {
                return -1;
            }
            if (splashId2 == null) {
                return 1;
            }
            int compareTo2 = splashId.compareTo(splashId2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String url = getUrl();
        String url2 = getSplashResponse.getUrl();
        if (url != url2) {
            if (url == null) {
                return -1;
            }
            if (url2 == null) {
                return 1;
            }
            int compareTo3 = url.compareTo(url2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        return 0;
    }
}
