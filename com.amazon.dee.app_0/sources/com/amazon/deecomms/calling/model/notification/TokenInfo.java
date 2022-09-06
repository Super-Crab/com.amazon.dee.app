package com.amazon.deecomms.calling.model.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class TokenInfo {
    @JsonProperty("CallFrom")
    private CallerIdentity callFrom;
    @JsonProperty("CallTimestamp")
    private long callTimestamp;
    @JsonProperty("CallTo")
    private CallerIdentity callTo;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TokenInfo)) {
            return false;
        }
        TokenInfo tokenInfo = (TokenInfo) obj;
        return Objects.equal(Long.valueOf(getCallTimestamp()), Long.valueOf(tokenInfo.getCallTimestamp())) && Objects.equal(getCallFrom(), tokenInfo.getCallFrom()) && Objects.equal(getCallTo(), tokenInfo.getCallTo());
    }

    public CallerIdentity getCallFrom() {
        return this.callFrom;
    }

    public long getCallTimestamp() {
        return this.callTimestamp;
    }

    public CallerIdentity getCallTo() {
        return this.callTo;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(getCallTimestamp()), getCallFrom(), getCallTo());
    }

    public void setCallFrom(CallerIdentity callerIdentity) {
        this.callFrom = callerIdentity;
    }

    public void setCallTimestamp(long j) {
        this.callTimestamp = j;
    }

    public void setCallTo(CallerIdentity callerIdentity) {
        this.callTo = callerIdentity;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("CallTimestamp", getCallTimestamp()).add("CallFrom", getCallFrom()).add("CallTo", getCallTo()).toString();
    }
}
