package com.amazon.deecomms.common.sip;

import com.amazon.deecomms.auth.AuthTokenHelper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
/* loaded from: classes12.dex */
public class SipAuthTokenResponse {
    @JsonProperty("authToken")
    private AuthToken authToken;

    /* loaded from: classes12.dex */
    public static class AuthToken {
        public static final int INVALID_TIME_ATTRIBUTE_IN_JSON = 0;
        @JsonProperty("created")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING, timezone = "GMT")
        private Date createdDate;
        @JsonProperty("expiration")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING, timezone = "GMT")
        private Date expirationDate;
        @JsonProperty("value")
        private String tokenValue;

        public Date getCreatedDate() {
            return this.createdDate;
        }

        public long getEpochTokenCreatedTimeInMillis() {
            Date date = this.createdDate;
            if (date != null) {
                return date.getTime();
            }
            return 0L;
        }

        public long getEpochTokenExpirationTimeInMillis() {
            Date date = this.expirationDate;
            if (date != null) {
                return date.getTime();
            }
            return 0L;
        }

        public Date getExpirationDate() {
            return this.expirationDate;
        }

        public long getExpiresIn() {
            long epochTokenCreatedTimeInMillis = getEpochTokenCreatedTimeInMillis();
            if (epochTokenCreatedTimeInMillis == 0) {
                return 3600000L;
            }
            return getEpochTokenExpirationTimeInMillis() - epochTokenCreatedTimeInMillis;
        }

        public String getTokenValue() {
            return this.tokenValue;
        }

        public void setCreatedDate(Date date) {
            this.createdDate = date;
        }

        public void setExpirationDate(Date date) {
            this.expirationDate = date;
        }

        public String toString() {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("AuthToken: value=");
            outline1.append(AuthTokenHelper.sensitive(this.tokenValue));
            outline1.append(", expiration=");
            outline1.append(this.expirationDate);
            outline1.append(", expiresIn=");
            outline1.append(getExpiresIn());
            outline1.append(", created=");
            outline1.append(this.createdDate);
            return outline1.toString();
        }
    }

    public AuthToken getAuthToken() {
        return this.authToken;
    }
}
