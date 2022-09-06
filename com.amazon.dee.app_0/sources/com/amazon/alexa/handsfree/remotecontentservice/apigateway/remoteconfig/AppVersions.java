package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
/* loaded from: classes8.dex */
public class AppVersions {
    @JsonProperty("localAppVersions")
    private LocalAppVersions mLocalAppVersions;
    @JsonProperty("voxAppVersion")
    private Integer mVoxAppVersion;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AppVersions.class != obj.getClass()) {
            return false;
        }
        AppVersions appVersions = (AppVersions) obj;
        return Objects.equals(this.mLocalAppVersions, appVersions.mLocalAppVersions) && Objects.equals(this.mVoxAppVersion, appVersions.mVoxAppVersion);
    }

    @Nullable
    public LocalAppVersions getLocalAppVersions() {
        return this.mLocalAppVersions;
    }

    @Nullable
    public Integer getVoxAppVersion() {
        return this.mVoxAppVersion;
    }

    public int hashCode() {
        return Objects.hash(this.mLocalAppVersions, this.mVoxAppVersion);
    }

    @VisibleForTesting
    void setLocalAppVersions(@Nullable LocalAppVersions localAppVersions) {
        this.mLocalAppVersions = localAppVersions;
    }

    @VisibleForTesting
    void setVoxAppVersion(@Nullable Integer num) {
        this.mVoxAppVersion = num;
    }
}
