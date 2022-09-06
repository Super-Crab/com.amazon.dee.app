package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class SpecificEndpointConfiguration implements EndpointConfiguration {
    @NonNull
    private String apsServiceUrl;
    @NonNull
    private String cdrsServiceUrl;
    @NonNull
    private String contentUrl;
    @NonNull
    private String dpsServiceUrl;
    @NonNull
    private String metadataUrl;
    @NonNull
    private String promptoServiceUrl;
    @NonNull
    private String thumbnailUrl;

    @JsonCreator
    public SpecificEndpointConfiguration(@NonNull @JsonProperty(required = true, value = "metadataUrl") String str, @NonNull @JsonProperty(required = true, value = "contentUrl") String str2, @NonNull @JsonProperty(required = true, value = "thumbnailUrl") String str3, @NonNull @JsonProperty(required = true, value = "promptoServiceUrl") String str4, @NonNull @JsonProperty(required = true, value = "apsServiceUrl") String str5, @NonNull @JsonProperty(required = true, value = "cdrsServiceUrl") String str6, @NonNull @JsonProperty(required = true, value = "dpsServiceUrl") String str7) {
        this.metadataUrl = str;
        this.contentUrl = str2;
        this.thumbnailUrl = str3;
        this.promptoServiceUrl = str4;
        this.apsServiceUrl = str5;
        this.cdrsServiceUrl = str6;
        this.dpsServiceUrl = str7;
    }

    @Override // com.amazon.clouddrive.cdasdk.EndpointConfiguration
    public String getApsServiceUrl() {
        return this.apsServiceUrl;
    }

    @Override // com.amazon.clouddrive.cdasdk.EndpointConfiguration
    public String getCdrsServiceUrl() {
        return this.cdrsServiceUrl;
    }

    @Override // com.amazon.clouddrive.cdasdk.EndpointConfiguration
    public String getContentUrl() {
        return this.contentUrl;
    }

    @Override // com.amazon.clouddrive.cdasdk.EndpointConfiguration
    public String getDpsServiceUrl() {
        return this.dpsServiceUrl;
    }

    @Override // com.amazon.clouddrive.cdasdk.EndpointConfiguration
    public String getMetadataUrl() {
        return this.metadataUrl;
    }

    @Override // com.amazon.clouddrive.cdasdk.EndpointConfiguration
    public String getPromptoServiceUrl() {
        return this.promptoServiceUrl;
    }

    @Override // com.amazon.clouddrive.cdasdk.EndpointConfiguration
    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }
}
