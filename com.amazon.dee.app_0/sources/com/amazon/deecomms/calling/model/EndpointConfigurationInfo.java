package com.amazon.deecomms.calling.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class EndpointConfigurationInfo {
    @JsonProperty("credential")
    private String credential;
    @JsonProperty("url")
    private String url;
    @JsonProperty("username")
    private String username;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EndpointConfigurationInfo)) {
            return false;
        }
        EndpointConfigurationInfo endpointConfigurationInfo = (EndpointConfigurationInfo) obj;
        return Objects.equal(this.url, endpointConfigurationInfo.url) && Objects.equal(this.username, endpointConfigurationInfo.username) && Objects.equal(this.credential, endpointConfigurationInfo.credential);
    }

    public String getCredential() {
        return this.credential;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUsername() {
        return this.username;
    }

    public int hashCode() {
        return Objects.hashCode(this.url, this.username, this.credential);
    }

    public void setCredential(String str) {
        this.credential = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("url", this.url).add("username", this.username).add("credential", this.credential).toString();
    }
}
