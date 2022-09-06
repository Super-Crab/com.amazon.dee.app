package com.amazon.comms.models.sip;

import com.amazon.dee.application.service.common.logging.RedactInLogs;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
@JsonDeserialize(builder = ICEConfigurationBuilder.class)
@RedactInLogs
/* loaded from: classes11.dex */
public final class ICEConfiguration {
    private final String credential;
    private final String url;
    private final String username;

    @JsonPOJOBuilder(withPrefix = "")
    /* loaded from: classes11.dex */
    public static class ICEConfigurationBuilder {
        private String credential;
        private String url;
        private String username;

        ICEConfigurationBuilder() {
        }

        public ICEConfiguration build() {
            return new ICEConfiguration(this.url, this.username, this.credential);
        }

        public ICEConfigurationBuilder credential(String str) {
            this.credential = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ICEConfiguration.ICEConfigurationBuilder(url=");
            outline107.append(this.url);
            outline107.append(", username=");
            outline107.append(this.username);
            outline107.append(", credential=");
            return GeneratedOutlineSupport1.outline91(outline107, this.credential, ")");
        }

        public ICEConfigurationBuilder url(String str) {
            this.url = str;
            return this;
        }

        public ICEConfigurationBuilder username(String str) {
            this.username = str;
            return this;
        }
    }

    private ICEConfiguration(String str, String str2, String str3) {
        this.url = str;
        this.username = str2;
        this.credential = str3;
    }

    public static ICEConfigurationBuilder builder() {
        return new ICEConfigurationBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ICEConfiguration)) {
            return false;
        }
        ICEConfiguration iCEConfiguration = (ICEConfiguration) obj;
        String url = getUrl();
        String url2 = iCEConfiguration.getUrl();
        if (url != null ? !url.equals(url2) : url2 != null) {
            return false;
        }
        String username = getUsername();
        String username2 = iCEConfiguration.getUsername();
        if (username != null ? !username.equals(username2) : username2 != null) {
            return false;
        }
        String credential = getCredential();
        String credential2 = iCEConfiguration.getCredential();
        return credential != null ? credential.equals(credential2) : credential2 == null;
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
        String url = getUrl();
        int i = 43;
        int hashCode = url == null ? 43 : url.hashCode();
        String username = getUsername();
        int hashCode2 = ((hashCode + 59) * 59) + (username == null ? 43 : username.hashCode());
        String credential = getCredential();
        int i2 = hashCode2 * 59;
        if (credential != null) {
            i = credential.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ICEConfiguration(url=");
        outline107.append(getUrl());
        outline107.append(", username=");
        outline107.append(getUsername());
        outline107.append(", credential=");
        outline107.append(getCredential());
        outline107.append(")");
        return outline107.toString();
    }
}
