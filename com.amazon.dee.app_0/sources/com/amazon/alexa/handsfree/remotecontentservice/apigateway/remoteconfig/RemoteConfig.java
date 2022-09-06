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
public class RemoteConfig {
    @JsonProperty("handsFreeSetupProperties")
    private HandsFreeSetupProperties mHandsFreeSetupProperties;
    @JsonProperty("version")
    private String mVersion;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RemoteConfig.class != obj.getClass()) {
            return false;
        }
        RemoteConfig remoteConfig = (RemoteConfig) obj;
        return Objects.equals(this.mHandsFreeSetupProperties, remoteConfig.mHandsFreeSetupProperties) && Objects.equals(this.mVersion, remoteConfig.mVersion);
    }

    @Nullable
    public HandsFreeSetupProperties getHandsFreeSetupProperties() {
        return this.mHandsFreeSetupProperties;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public int hashCode() {
        return Objects.hash(this.mHandsFreeSetupProperties, this.mVersion);
    }

    @VisibleForTesting
    void setHandsFreeSetupProperties(@Nullable HandsFreeSetupProperties handsFreeSetupProperties) {
        this.mHandsFreeSetupProperties = handsFreeSetupProperties;
    }

    @VisibleForTesting
    void setVersion(String str) {
        this.mVersion = str;
    }
}
