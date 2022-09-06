package com.amazon.clouddrive.cdasdk.aps.message;

import com.fasterxml.jackson.annotation.JsonValue;
/* loaded from: classes11.dex */
public enum NotificationType {
    DOWNLOAD_APP_SMS("downloadSms"),
    DESKTOP_DOWNLOAD_EMAIL("desktopDownloadEmail"),
    DOWNLOAD_APP_EMAIL("downloadAppEmail"),
    TABLET_DOWNLOAD_MOBILE_APP_EMAIL("tabletDownloadMobileAppEmail"),
    FIRETV_SCREENSAVER_PERSONALIZATION_INFO_EMAIL("fireTVScreensaverPersonalizationInfoEmail");
    
    private final String value;

    NotificationType(String str) {
        this.value = str;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
