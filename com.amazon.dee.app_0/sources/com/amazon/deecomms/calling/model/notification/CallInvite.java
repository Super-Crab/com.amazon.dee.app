package com.amazon.deecomms.calling.model.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class CallInvite {
    @JsonProperty("TokenCallId")
    private String tokenCallId;
    @JsonProperty("TokenCallerName")
    private String tokenCallerName;
    @JsonProperty("TokenCallerPhoneNumber")
    private String tokenCallerPhoneNumber;
    @JsonProperty("TokenInfo")
    private TokenInfo tokenInfo;
    @JsonProperty("TokenInfoVersion")
    private String tokenInfoVersion;
    @JsonProperty("TokenValue")
    private String tokenValue;
    @JsonProperty("TokenVersion")
    private String tokenVersion;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CallInvite)) {
            return false;
        }
        CallInvite callInvite = (CallInvite) obj;
        return Objects.equal(getTokenVersion(), callInvite.getTokenVersion()) && Objects.equal(getTokenInfoVersion(), callInvite.getTokenInfoVersion()) && Objects.equal(getTokenInfo(), callInvite.getTokenInfo()) && Objects.equal(getTokenValue(), callInvite.getTokenValue()) && Objects.equal(getTokenCallerName(), callInvite.getTokenCallerName()) && Objects.equal(getTokenCallerPhoneNumber(), callInvite.getTokenCallerPhoneNumber());
    }

    public String getTokenCallId() {
        return this.tokenCallId;
    }

    public String getTokenCallerName() {
        return this.tokenCallerName;
    }

    public String getTokenCallerPhoneNumber() {
        return this.tokenCallerPhoneNumber;
    }

    public TokenInfo getTokenInfo() {
        return this.tokenInfo;
    }

    public String getTokenInfoVersion() {
        return this.tokenInfoVersion;
    }

    public String getTokenValue() {
        return this.tokenValue;
    }

    public String getTokenVersion() {
        return this.tokenVersion;
    }

    public int hashCode() {
        return Objects.hashCode(getTokenVersion(), getTokenInfoVersion(), getTokenInfo(), getTokenValue(), getTokenCallerName(), getTokenCallerPhoneNumber());
    }

    public void setTokenCallId(String str) {
        this.tokenCallId = str;
    }

    public void setTokenCallerName(String str) {
        this.tokenCallerName = str;
    }

    public void setTokenCallerPhoneNumber(String str) {
        this.tokenCallerPhoneNumber = str;
    }

    public void setTokenInfo(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public void setTokenInfoVersion(String str) {
        this.tokenInfoVersion = str;
    }

    public void setTokenValue(String str) {
        this.tokenValue = str;
    }

    public void setTokenVersion(String str) {
        this.tokenVersion = str;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("TokenVersion", getTokenVersion()).add("TokenInfoVersion", getTokenInfoVersion()).add("TokenInfo", getTokenInfo()).add("TokenValue", getTokenValue()).add("TokenCallerName", getTokenCallerName()).add("TokenCallerPhoneNumber", getTokenCallerPhoneNumber()).toString();
    }
}
