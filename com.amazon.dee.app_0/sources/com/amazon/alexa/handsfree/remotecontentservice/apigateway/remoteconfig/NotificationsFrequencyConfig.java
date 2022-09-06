package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
/* loaded from: classes8.dex */
public class NotificationsFrequencyConfig {
    @JsonProperty("intervals")
    private List<Long> mIntervals = new ArrayList();
    @JsonProperty("type")
    private String mType;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NotificationsFrequencyConfig.class != obj.getClass()) {
            return false;
        }
        NotificationsFrequencyConfig notificationsFrequencyConfig = (NotificationsFrequencyConfig) obj;
        return Objects.equals(this.mType, notificationsFrequencyConfig.mType) && Objects.equals(this.mIntervals, notificationsFrequencyConfig.mIntervals);
    }

    @Nullable
    public List<Long> getIntervals() {
        return this.mIntervals;
    }

    @Nullable
    public String getType() {
        return this.mType;
    }

    public int hashCode() {
        return Objects.hash(this.mType, this.mIntervals);
    }

    @VisibleForTesting
    void setIntervals(@Nullable List<Long> list) {
        this.mIntervals = list;
    }

    @VisibleForTesting
    void setType(@Nullable String str) {
        this.mType = str;
    }
}
