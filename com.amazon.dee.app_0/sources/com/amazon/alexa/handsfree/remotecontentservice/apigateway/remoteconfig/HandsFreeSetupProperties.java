package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
/* loaded from: classes8.dex */
public class HandsFreeSetupProperties {
    @JsonProperty("confidenceLevel")
    private Integer mConfidenceLevel;
    @JsonProperty("disabledState")
    private StatusConfig mDisabledState;
    @JsonProperty("minimumSupportedAlexaAppVersion")
    private Integer mMinimumSupportedAlexaAppVersion;
    @JsonProperty("minimumSupportedAlexaAppVersionForDSP")
    private List<AppVersions> mMinimumSupportedAlexaAppVersionForDSP;
    @JsonProperty("minimumSupportedLocalAppVersion")
    private LocalAppVersions mMinimumSupportedLocalAppVersion;
    @JsonProperty("notificationsConfig")
    private NotificationsConfig mNotificationsConfig;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || HandsFreeSetupProperties.class != obj.getClass()) {
            return false;
        }
        HandsFreeSetupProperties handsFreeSetupProperties = (HandsFreeSetupProperties) obj;
        return Objects.equals(this.mDisabledState, handsFreeSetupProperties.mDisabledState) && Objects.equals(this.mNotificationsConfig, handsFreeSetupProperties.mNotificationsConfig) && Objects.equals(this.mConfidenceLevel, handsFreeSetupProperties.mConfidenceLevel) && Objects.equals(this.mMinimumSupportedAlexaAppVersion, handsFreeSetupProperties.mMinimumSupportedAlexaAppVersion) && Objects.equals(this.mMinimumSupportedLocalAppVersion, handsFreeSetupProperties.mMinimumSupportedLocalAppVersion) && Objects.equals(this.mMinimumSupportedAlexaAppVersionForDSP, handsFreeSetupProperties.mMinimumSupportedAlexaAppVersionForDSP);
    }

    @Nullable
    public Integer getConfidenceLevel() {
        return this.mConfidenceLevel;
    }

    @Nullable
    public StatusConfig getDisabledState() {
        return this.mDisabledState;
    }

    @Nullable
    public Integer getMinimumSupportedAlexaAppVersion() {
        return this.mMinimumSupportedAlexaAppVersion;
    }

    @Nullable
    public List<AppVersions> getMinimumSupportedAlexaAppVersionForDSP() {
        return this.mMinimumSupportedAlexaAppVersionForDSP;
    }

    @Nullable
    public LocalAppVersions getMinimumSupportedLocalAppVersion() {
        return this.mMinimumSupportedLocalAppVersion;
    }

    @Nullable
    public NotificationsConfig getNotificationsConfig() {
        return this.mNotificationsConfig;
    }

    public int hashCode() {
        return Objects.hash(this.mDisabledState, this.mNotificationsConfig, this.mConfidenceLevel, this.mMinimumSupportedAlexaAppVersion, this.mMinimumSupportedLocalAppVersion, this.mMinimumSupportedAlexaAppVersionForDSP);
    }

    @VisibleForTesting
    void setConfidenceLevel(@Nullable Integer num) {
        this.mConfidenceLevel = num;
    }

    @VisibleForTesting
    void setDisabledState(@Nullable StatusConfig statusConfig) {
        this.mDisabledState = statusConfig;
    }

    @VisibleForTesting
    void setMinimumSupportedAlexaAppVersion(@Nullable Integer num) {
        this.mMinimumSupportedAlexaAppVersion = num;
    }

    @VisibleForTesting
    void setMinimumSupportedAlexaAppVersionForDSP(@Nullable List<AppVersions> list) {
        this.mMinimumSupportedAlexaAppVersionForDSP = list;
    }

    @VisibleForTesting
    void setMinimumSupportedLocalAppVersion(@Nullable LocalAppVersions localAppVersions) {
        this.mMinimumSupportedLocalAppVersion = localAppVersions;
    }

    @VisibleForTesting
    void setNotificationsConfig(@Nullable NotificationsConfig notificationsConfig) {
        this.mNotificationsConfig = notificationsConfig;
    }
}
