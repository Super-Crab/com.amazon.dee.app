package com.amazon.alexa.mobilytics.event.metadata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
/* loaded from: classes9.dex */
public class AMPDMetadata implements DefaultEventMetadata {
    private String deviceLockState;
    private String deviceType;
    private String dspApkVersion;
    private String enrollmentType;
    private String errorReason;
    private String lastUnlockTimestamp;
    private final String metadataType = EventMetadataType.AMPD;
    private String speakerVerificationType;
    private String svModelId;
    private String svRawScore;
    private String svThresholdLower;
    private String svThresholdUpper;
    private String wakeWordConfidence;
    private String wakeWordModelLocale;
    private String wwModelId;

    @Nullable
    public String getDeviceLockState() {
        return this.deviceLockState;
    }

    @Nullable
    @DCMAttributeName("deviceType_AMPD")
    public String getDeviceType() {
        return this.deviceType;
    }

    public String getDspAPKVersion() {
        return this.dspApkVersion;
    }

    public String getEnrollmentType() {
        return this.enrollmentType;
    }

    public String getErrorReason() {
        return this.errorReason;
    }

    @Nullable
    public String getLastUnlockTimestamp() {
        return this.lastUnlockTimestamp;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.EventMetadata
    public String getMetadataType() {
        return EventMetadataType.AMPD;
    }

    @Nullable
    @DCMAttributeName("SpeakerVerificationType")
    public String getSpeakerVerificationType() {
        return this.speakerVerificationType;
    }

    public String getSvModelId() {
        return this.svModelId;
    }

    public String getSvRawScore() {
        return this.svRawScore;
    }

    public String getSvThresholdUpper() {
        return this.svThresholdUpper;
    }

    public String getWakeWordConfidence() {
        return this.wakeWordConfidence;
    }

    public String getWakeWordModelLocale() {
        return this.wakeWordModelLocale;
    }

    public String getWwModelId() {
        return this.wwModelId;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.DefaultEventMetadata
    @NonNull
    public EventDetailsProto.Metadata serialize() {
        EventDetailsProto.Metadata.Builder newBuilder = EventDetailsProto.Metadata.newBuilder();
        EventDetailsProto.Metadata.Ampd.Builder newBuilder2 = EventDetailsProto.Metadata.Ampd.newBuilder();
        String str = this.dspApkVersion;
        if (str != null) {
            newBuilder2.setDspApkVersion(str);
        }
        String str2 = this.deviceType;
        if (str2 != null) {
            newBuilder2.setDeviceType(str2);
        }
        String str3 = this.svModelId;
        if (str3 != null) {
            newBuilder2.setSvModelId(str3);
        }
        String str4 = this.wwModelId;
        if (str4 != null) {
            newBuilder2.setWwModelId(str4);
        }
        String str5 = this.svThresholdUpper;
        if (str5 != null) {
            newBuilder2.setSvThresholdUpper(str5);
        }
        String str6 = this.svThresholdLower;
        if (str6 != null) {
            newBuilder2.setSvThresholdLower(str6);
        }
        String str7 = this.enrollmentType;
        if (str7 != null) {
            newBuilder2.setEnrollmentType(str7);
        }
        String str8 = this.svRawScore;
        if (str8 != null) {
            newBuilder2.setSvRawScore(str8);
        }
        String str9 = this.wakeWordConfidence;
        if (str9 != null) {
            newBuilder2.setWakeWordConfidence(str9);
        }
        String str10 = this.wakeWordModelLocale;
        if (str10 != null) {
            newBuilder2.setWakeWordModelLocale(str10);
        }
        String str11 = this.errorReason;
        if (str11 != null) {
            newBuilder2.setErrorReason(str11);
        }
        newBuilder.setAmpd(newBuilder2);
        return newBuilder.mo10084build();
    }

    @NonNull
    public AMPDMetadata withDeviceLockState(@NonNull String str) {
        this.deviceLockState = str;
        return this;
    }

    @NonNull
    public AMPDMetadata withDeviceType(@NonNull String str) {
        this.deviceType = str;
        return this;
    }

    public AMPDMetadata withDspApkVersion(String str) {
        this.dspApkVersion = str;
        return this;
    }

    public AMPDMetadata withEnrollmentType(String str) {
        this.enrollmentType = str;
        return this;
    }

    public AMPDMetadata withErrorReason(String str) {
        this.errorReason = str;
        return this;
    }

    @NonNull
    public AMPDMetadata withLastUnlockTimestamp(@NonNull String str) {
        this.lastUnlockTimestamp = str;
        return this;
    }

    @NonNull
    public AMPDMetadata withSpeakerVerificationType(@NonNull String str) {
        this.speakerVerificationType = str;
        return this;
    }

    public AMPDMetadata withSvModelId(String str) {
        this.svModelId = str;
        return this;
    }

    public AMPDMetadata withSvRawScore(String str) {
        this.svRawScore = str;
        return this;
    }

    public AMPDMetadata withSvThresholdLower(String str) {
        this.svThresholdLower = str;
        return this;
    }

    public AMPDMetadata withSvThresholdUpper(String str) {
        this.svThresholdUpper = str;
        return this;
    }

    public AMPDMetadata withWakeWordConfidence(String str) {
        this.wakeWordConfidence = str;
        return this;
    }

    public AMPDMetadata withWakeWordModelLocale(String str) {
        this.wakeWordModelLocale = str;
        return this;
    }

    public AMPDMetadata withWwModelId(String str) {
        this.wwModelId = str;
        return this;
    }
}
