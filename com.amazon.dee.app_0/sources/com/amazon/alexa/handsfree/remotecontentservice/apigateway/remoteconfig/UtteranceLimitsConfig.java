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
public class UtteranceLimitsConfig {
    @JsonProperty("maxCount")
    private Integer mMaxCount;
    @JsonProperty("maxDaysAfterFirstUtterance")
    private Integer mMaxDaysAfterFirstUtterance;
    @JsonProperty("minHoursBeforeNextUtterance")
    private Integer mMinHoursBeforeNextUtterance;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || UtteranceLimitsConfig.class != obj.getClass()) {
            return false;
        }
        UtteranceLimitsConfig utteranceLimitsConfig = (UtteranceLimitsConfig) obj;
        return Objects.equals(this.mMaxCount, utteranceLimitsConfig.mMaxCount) && Objects.equals(this.mMaxDaysAfterFirstUtterance, utteranceLimitsConfig.mMaxDaysAfterFirstUtterance) && Objects.equals(this.mMinHoursBeforeNextUtterance, utteranceLimitsConfig.mMinHoursBeforeNextUtterance);
    }

    @Nullable
    public Integer getMaxCount() {
        return this.mMaxCount;
    }

    @Nullable
    public Integer getMaxDaysAfterFirstUtterance() {
        return this.mMaxDaysAfterFirstUtterance;
    }

    @Nullable
    public Integer getMinHoursBeforeNextUtterance() {
        return this.mMinHoursBeforeNextUtterance;
    }

    public int hashCode() {
        return Objects.hash(this.mMaxCount, this.mMaxDaysAfterFirstUtterance, this.mMinHoursBeforeNextUtterance);
    }

    @VisibleForTesting
    void setMaxCount(@Nullable Integer num) {
        this.mMaxCount = num;
    }

    @VisibleForTesting
    void setMaxDaysAfterFirstUtterance(@Nullable Integer num) {
        this.mMaxDaysAfterFirstUtterance = num;
    }

    @VisibleForTesting
    void setMinHoursBeforeNextUtterance(@Nullable Integer num) {
        this.mMinHoursBeforeNextUtterance = num;
    }
}
