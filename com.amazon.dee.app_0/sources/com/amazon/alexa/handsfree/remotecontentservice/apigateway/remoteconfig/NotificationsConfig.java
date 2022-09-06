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
public class NotificationsConfig {
    @JsonProperty("maxAllowedNotificationsBeforeTerms")
    private Integer mMaxAllowedNotificationsBeforeTerms;
    @JsonProperty("frequencyConfig")
    private List<NotificationsFrequencyConfig> mNotificationsFrequencyConfigList = new ArrayList();
    @JsonProperty("utteranceConfig")
    private List<NotificationsUtteranceConfig> mNotificationsUtteranceConfigList = new ArrayList();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NotificationsConfig.class != obj.getClass()) {
            return false;
        }
        NotificationsConfig notificationsConfig = (NotificationsConfig) obj;
        return Objects.equals(this.mNotificationsFrequencyConfigList, notificationsConfig.mNotificationsFrequencyConfigList) && Objects.equals(this.mNotificationsUtteranceConfigList, notificationsConfig.mNotificationsUtteranceConfigList) && Objects.equals(this.mMaxAllowedNotificationsBeforeTerms, notificationsConfig.mMaxAllowedNotificationsBeforeTerms);
    }

    @Nullable
    public Integer getMaxAllowedNotificationsBeforeTerms() {
        return this.mMaxAllowedNotificationsBeforeTerms;
    }

    @Nullable
    public List<NotificationsFrequencyConfig> getNotificationsFrequencyConfigList() {
        return this.mNotificationsFrequencyConfigList;
    }

    @Nullable
    public List<NotificationsUtteranceConfig> getNotificationsUtteranceConfigList() {
        return this.mNotificationsUtteranceConfigList;
    }

    public int hashCode() {
        return Objects.hash(this.mNotificationsFrequencyConfigList, this.mNotificationsUtteranceConfigList, this.mMaxAllowedNotificationsBeforeTerms);
    }

    @VisibleForTesting
    void setMaxAllowedNotificationsBeforeTerms(@Nullable Integer num) {
        this.mMaxAllowedNotificationsBeforeTerms = num;
    }

    @VisibleForTesting
    void setNotificationsFrequencyConfigList(@Nullable List<NotificationsFrequencyConfig> list) {
        this.mNotificationsFrequencyConfigList = list;
    }

    @VisibleForTesting
    void setNotificationsUtteranceConfigList(@Nullable List<NotificationsUtteranceConfig> list) {
        this.mNotificationsUtteranceConfigList = list;
    }
}
