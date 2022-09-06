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
public class NotificationsUtteranceConfig {
    @JsonProperty("limits")
    private UtteranceLimitsConfig mLimits;
    @JsonProperty("type")
    private String mType;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NotificationsUtteranceConfig.class != obj.getClass()) {
            return false;
        }
        NotificationsUtteranceConfig notificationsUtteranceConfig = (NotificationsUtteranceConfig) obj;
        return Objects.equals(this.mType, notificationsUtteranceConfig.mType) && Objects.equals(this.mLimits, notificationsUtteranceConfig.mLimits);
    }

    @Nullable
    public UtteranceLimitsConfig getLimits() {
        return this.mLimits;
    }

    @Nullable
    public String getType() {
        return this.mType;
    }

    public int hashCode() {
        return Objects.hash(this.mType, this.mLimits);
    }

    @VisibleForTesting
    void setLimits(@Nullable UtteranceLimitsConfig utteranceLimitsConfig) {
        this.mLimits = utteranceLimitsConfig;
    }

    @VisibleForTesting
    void setType(@Nullable String str) {
        this.mType = str;
    }
}
