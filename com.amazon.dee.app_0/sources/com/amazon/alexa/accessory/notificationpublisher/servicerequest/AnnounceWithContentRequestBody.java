package com.amazon.alexa.accessory.notificationpublisher.servicerequest;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import java.util.Locale;
/* loaded from: classes.dex */
public final class AnnounceWithContentRequestBody {
    private final String announcement;
    private final String detailedContent;
    @Expose
    private final String deviceSerialNumber;
    @Expose
    private final String deviceType;
    @Expose
    private final String locale;
    @Expose
    private final String notificationId;
    @Expose
    private final String softwareVersion;

    /* loaded from: classes.dex */
    public static class Builder {
        private String announcement;
        private String detailedContent;
        private String deviceSerialNumber;
        private String deviceType;
        private String locale;
        private String notificationId;
        private String softwareVersion;

        public AnnounceWithContentRequestBody build() {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(this.deviceType), "The provided deviceType was null or empty");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(this.deviceSerialNumber), "The provided deviceSerialNumber was null or empty");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(this.announcement), "The provided announcement was null or empty");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(this.detailedContent), "The provided detailedContent was null or empty");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(this.notificationId), "The provided notificationId was null or empty");
            return new AnnounceWithContentRequestBody(this.deviceType, this.deviceSerialNumber, this.softwareVersion, this.announcement, this.detailedContent, this.notificationId, this.locale);
        }

        public Builder setAnnouncement(String str) {
            this.announcement = str;
            return this;
        }

        public Builder setDetailedContent(String str) {
            this.detailedContent = str;
            return this;
        }

        public Builder setDeviceSerialNumber(String str) {
            this.deviceSerialNumber = str;
            return this;
        }

        public Builder setDeviceType(String str) {
            this.deviceType = str;
            return this;
        }

        public Builder setLocale(String str) {
            this.locale = str;
            return this;
        }

        public Builder setNotificationId(String str) {
            this.notificationId = str;
            return this;
        }

        public Builder setSoftwareVersion(String str) {
            this.softwareVersion = str;
            return this;
        }
    }

    public String getAnnouncement() {
        return this.announcement;
    }

    public String getDetailedContent() {
        return this.detailedContent;
    }

    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getLocale() {
        return this.locale;
    }

    public String getNotificationId() {
        return this.notificationId;
    }

    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public String toLoggableString() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this);
    }

    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

    private AnnounceWithContentRequestBody(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.deviceType = str;
        this.deviceSerialNumber = str2;
        this.softwareVersion = str3;
        this.announcement = str4;
        this.detailedContent = str5;
        this.notificationId = str6;
        if (Strings.isNullOrEmpty(str7)) {
            this.locale = Locale.getDefault().toString();
        } else {
            this.locale = str7;
        }
    }
}
