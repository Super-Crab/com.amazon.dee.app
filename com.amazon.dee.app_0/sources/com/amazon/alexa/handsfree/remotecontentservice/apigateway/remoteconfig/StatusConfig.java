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
public class StatusConfig {
    @JsonProperty("decider")
    private List<LocalAppVersions> mDeciderVersions;
    @JsonProperty("handsFree")
    private List<AppVersions> mHandsFreeVersions;
    @JsonProperty("lockScreen")
    private List<LocalAppVersions> mLockScreenVersions;
    @JsonProperty("metrics")
    private List<LocalAppVersions> mMetricsVersions;
    @JsonProperty("notifications")
    private List<LocalAppVersions> mNotificationsVersions;
    @JsonProperty("settings")
    private List<LocalAppVersions> mSettingsVersions;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StatusConfig.class != obj.getClass()) {
            return false;
        }
        StatusConfig statusConfig = (StatusConfig) obj;
        return Objects.equals(this.mHandsFreeVersions, statusConfig.mHandsFreeVersions) && Objects.equals(this.mDeciderVersions, statusConfig.mDeciderVersions) && Objects.equals(this.mSettingsVersions, statusConfig.mSettingsVersions) && Objects.equals(this.mMetricsVersions, statusConfig.mMetricsVersions) && Objects.equals(this.mNotificationsVersions, statusConfig.mNotificationsVersions) && Objects.equals(this.mLockScreenVersions, statusConfig.mLockScreenVersions);
    }

    @Nullable
    public List<LocalAppVersions> getDeciderVersions() {
        return this.mDeciderVersions;
    }

    @Nullable
    public List<AppVersions> getHandsFreeVersions() {
        return this.mHandsFreeVersions;
    }

    @Nullable
    public List<LocalAppVersions> getLockScreenVersions() {
        return this.mLockScreenVersions;
    }

    @Nullable
    public List<LocalAppVersions> getMetricsVersions() {
        return this.mMetricsVersions;
    }

    @Nullable
    public List<LocalAppVersions> getNotificationsVersions() {
        return this.mNotificationsVersions;
    }

    @Nullable
    public List<LocalAppVersions> getSettingsVersions() {
        return this.mSettingsVersions;
    }

    public int hashCode() {
        return Objects.hash(this.mHandsFreeVersions, this.mDeciderVersions, this.mSettingsVersions, this.mMetricsVersions, this.mNotificationsVersions, this.mLockScreenVersions);
    }

    @VisibleForTesting
    void setDeciderVersions(@Nullable List<LocalAppVersions> list) {
        this.mDeciderVersions = list;
    }

    @VisibleForTesting
    void setHandsFreeVersions(@Nullable List<AppVersions> list) {
        this.mHandsFreeVersions = list;
    }

    @VisibleForTesting
    void setLockScreenVersions(@Nullable List<LocalAppVersions> list) {
        this.mLockScreenVersions = list;
    }

    @VisibleForTesting
    void setMetricsVersions(@Nullable List<LocalAppVersions> list) {
        this.mMetricsVersions = list;
    }

    @VisibleForTesting
    void setNotificationsVersions(@Nullable List<LocalAppVersions> list) {
        this.mNotificationsVersions = list;
    }

    @VisibleForTesting
    void setSettingsVersions(@Nullable List<LocalAppVersions> list) {
        this.mSettingsVersions = list;
    }
}
