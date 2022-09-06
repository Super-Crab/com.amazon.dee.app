package com.amazon.alexa.accessory.notificationpublisher.servicerequest;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
/* loaded from: classes.dex */
public final class CreateDownloadableSpeechFromTextRequestBody {
    @Expose
    private final String deviceSerialNumber;
    @Expose
    private final String deviceType;
    @Expose
    private final String locale;
    private final String primaryText;
    @Expose
    private final String requestId;
    private final String secondaryText;
    @Expose
    private final String softwareVersion;

    /* loaded from: classes.dex */
    public static class Builder {
        private String deviceSerialNumber;
        private String deviceType;
        private String locale;
        private String primaryText;
        private String requestId;
        private String secondaryText;
        private String softwareVersion;

        public CreateDownloadableSpeechFromTextRequestBody build() {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(this.deviceType), "The provided deviceType was null or empty");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(this.deviceSerialNumber), "The provided deviceSerialNumber was null or empty");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(this.primaryText), "The provided primaryText was null or empty");
            Preconditions.checkArgument(!Strings.isNullOrEmpty(this.requestId), "The provided requestId was null or empty");
            return new CreateDownloadableSpeechFromTextRequestBody(this.deviceType, this.deviceSerialNumber, this.softwareVersion, this.primaryText, this.secondaryText, this.requestId, this.locale);
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

        public Builder setPrimaryText(String str) {
            this.primaryText = str;
            return this;
        }

        public Builder setRequestId(String str) {
            this.requestId = str;
            return this;
        }

        public Builder setSecondaryText(String str) {
            this.secondaryText = str;
            return this;
        }

        public Builder setSoftwareVersion(String str) {
            this.softwareVersion = str;
            return this;
        }
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

    public String getPrimaryText() {
        return this.primaryText;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getSecondaryText() {
        return this.secondaryText;
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

    private CreateDownloadableSpeechFromTextRequestBody(String str, String str2, String str3, String str4, @Nullable String str5, String str6, @Nullable String str7) {
        this.deviceType = str;
        this.deviceSerialNumber = str2;
        this.softwareVersion = str3;
        this.primaryText = str4;
        this.secondaryText = str5;
        this.requestId = str6;
        if (Strings.isNullOrEmpty(str7)) {
            this.locale = NotificationConstants.DEFAULT_LOCALE.toString();
        } else {
            this.locale = str7;
        }
    }
}
