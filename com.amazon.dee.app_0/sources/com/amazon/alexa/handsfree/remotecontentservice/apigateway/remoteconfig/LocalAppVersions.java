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
public class LocalAppVersions {
    @JsonProperty("dspAppVersion")
    private Integer mDspAppVersion;
    @JsonProperty("falcoVersion")
    private Integer mFalcoVersion;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LocalAppVersions.class != obj.getClass()) {
            return false;
        }
        LocalAppVersions localAppVersions = (LocalAppVersions) obj;
        return Objects.equals(this.mDspAppVersion, localAppVersions.mDspAppVersion) && Objects.equals(this.mFalcoVersion, localAppVersions.mFalcoVersion);
    }

    @Nullable
    public Integer getDspAppVersion() {
        return this.mDspAppVersion;
    }

    @Nullable
    public Integer getFalcoVersion() {
        return this.mFalcoVersion;
    }

    public int hashCode() {
        return Objects.hash(this.mDspAppVersion, this.mFalcoVersion);
    }

    @VisibleForTesting
    void setDspAppVersion(@Nullable Integer num) {
        this.mDspAppVersion = num;
    }

    @VisibleForTesting
    void setFalcoVersion(@Nullable Integer num) {
        this.mFalcoVersion = num;
    }
}
