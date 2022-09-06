package com.amazon.comms.models.sip.payloads;

import com.amazon.comms.models.sip.SIPAuthenticationInfo;
import com.amazon.comms.models.sip.SIPProxyEndpoint;
import com.amazon.dee.application.service.common.logging.RedactInLogs;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
@RedactInLogs
/* loaded from: classes11.dex */
public class SIPRegisterInfo {
    private SIPAuthenticationInfo authenticationInfo;
    private String domain;
    private Integer expires;
    private SIPProxyEndpoint proxyEndpoint;
    private String scheme;
    private String sipInstance;
    private String user;

    /* loaded from: classes11.dex */
    public static class SIPRegisterInfoBuilder {
        private SIPAuthenticationInfo authenticationInfo;
        private String domain;
        private Integer expires;
        private SIPProxyEndpoint proxyEndpoint;
        private String scheme;
        private String sipInstance;
        private String user;

        SIPRegisterInfoBuilder() {
        }

        public SIPRegisterInfoBuilder authenticationInfo(SIPAuthenticationInfo sIPAuthenticationInfo) {
            this.authenticationInfo = sIPAuthenticationInfo;
            return this;
        }

        public SIPRegisterInfo build() {
            return new SIPRegisterInfo(this.scheme, this.domain, this.user, this.sipInstance, this.expires, this.authenticationInfo, this.proxyEndpoint);
        }

        public SIPRegisterInfoBuilder domain(String str) {
            this.domain = str;
            return this;
        }

        public SIPRegisterInfoBuilder expires(Integer num) {
            this.expires = num;
            return this;
        }

        public SIPRegisterInfoBuilder proxyEndpoint(SIPProxyEndpoint sIPProxyEndpoint) {
            this.proxyEndpoint = sIPProxyEndpoint;
            return this;
        }

        public SIPRegisterInfoBuilder scheme(String str) {
            this.scheme = str;
            return this;
        }

        public SIPRegisterInfoBuilder sipInstance(String str) {
            this.sipInstance = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SIPRegisterInfo.SIPRegisterInfoBuilder(scheme=");
            outline107.append(this.scheme);
            outline107.append(", domain=");
            outline107.append(this.domain);
            outline107.append(", user=");
            outline107.append(this.user);
            outline107.append(", sipInstance=");
            outline107.append(this.sipInstance);
            outline107.append(", expires=");
            outline107.append(this.expires);
            outline107.append(", authenticationInfo=");
            outline107.append(this.authenticationInfo);
            outline107.append(", proxyEndpoint=");
            outline107.append(this.proxyEndpoint);
            outline107.append(")");
            return outline107.toString();
        }

        public SIPRegisterInfoBuilder user(String str) {
            this.user = str;
            return this;
        }
    }

    public SIPRegisterInfo() {
    }

    public static SIPRegisterInfoBuilder builder() {
        return new SIPRegisterInfoBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SIPRegisterInfo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SIPRegisterInfo)) {
            return false;
        }
        SIPRegisterInfo sIPRegisterInfo = (SIPRegisterInfo) obj;
        if (!sIPRegisterInfo.canEqual(this)) {
            return false;
        }
        String scheme = getScheme();
        String scheme2 = sIPRegisterInfo.getScheme();
        if (scheme != null ? !scheme.equals(scheme2) : scheme2 != null) {
            return false;
        }
        String domain = getDomain();
        String domain2 = sIPRegisterInfo.getDomain();
        if (domain != null ? !domain.equals(domain2) : domain2 != null) {
            return false;
        }
        String user = getUser();
        String user2 = sIPRegisterInfo.getUser();
        if (user != null ? !user.equals(user2) : user2 != null) {
            return false;
        }
        String sipInstance = getSipInstance();
        String sipInstance2 = sIPRegisterInfo.getSipInstance();
        if (sipInstance != null ? !sipInstance.equals(sipInstance2) : sipInstance2 != null) {
            return false;
        }
        Integer expires = getExpires();
        Integer expires2 = sIPRegisterInfo.getExpires();
        if (expires != null ? !expires.equals(expires2) : expires2 != null) {
            return false;
        }
        SIPAuthenticationInfo authenticationInfo = getAuthenticationInfo();
        SIPAuthenticationInfo authenticationInfo2 = sIPRegisterInfo.getAuthenticationInfo();
        if (authenticationInfo != null ? !authenticationInfo.equals(authenticationInfo2) : authenticationInfo2 != null) {
            return false;
        }
        SIPProxyEndpoint proxyEndpoint = getProxyEndpoint();
        SIPProxyEndpoint proxyEndpoint2 = sIPRegisterInfo.getProxyEndpoint();
        return proxyEndpoint != null ? proxyEndpoint.equals(proxyEndpoint2) : proxyEndpoint2 == null;
    }

    public SIPAuthenticationInfo getAuthenticationInfo() {
        return this.authenticationInfo;
    }

    public String getDomain() {
        return this.domain;
    }

    public Integer getExpires() {
        return this.expires;
    }

    public SIPProxyEndpoint getProxyEndpoint() {
        return this.proxyEndpoint;
    }

    public String getScheme() {
        return this.scheme;
    }

    public String getSipInstance() {
        return this.sipInstance;
    }

    public String getUser() {
        return this.user;
    }

    public int hashCode() {
        String scheme = getScheme();
        int i = 43;
        int hashCode = scheme == null ? 43 : scheme.hashCode();
        String domain = getDomain();
        int hashCode2 = ((hashCode + 59) * 59) + (domain == null ? 43 : domain.hashCode());
        String user = getUser();
        int hashCode3 = (hashCode2 * 59) + (user == null ? 43 : user.hashCode());
        String sipInstance = getSipInstance();
        int hashCode4 = (hashCode3 * 59) + (sipInstance == null ? 43 : sipInstance.hashCode());
        Integer expires = getExpires();
        int hashCode5 = (hashCode4 * 59) + (expires == null ? 43 : expires.hashCode());
        SIPAuthenticationInfo authenticationInfo = getAuthenticationInfo();
        int hashCode6 = (hashCode5 * 59) + (authenticationInfo == null ? 43 : authenticationInfo.hashCode());
        SIPProxyEndpoint proxyEndpoint = getProxyEndpoint();
        int i2 = hashCode6 * 59;
        if (proxyEndpoint != null) {
            i = proxyEndpoint.hashCode();
        }
        return i2 + i;
    }

    public void setAuthenticationInfo(SIPAuthenticationInfo sIPAuthenticationInfo) {
        this.authenticationInfo = sIPAuthenticationInfo;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public void setExpires(Integer num) {
        this.expires = num;
    }

    public void setProxyEndpoint(SIPProxyEndpoint sIPProxyEndpoint) {
        this.proxyEndpoint = sIPProxyEndpoint;
    }

    public void setScheme(String str) {
        this.scheme = str;
    }

    public void setSipInstance(String str) {
        this.sipInstance = str;
    }

    public void setUser(String str) {
        this.user = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SIPRegisterInfo(scheme=");
        outline107.append(getScheme());
        outline107.append(", domain=");
        outline107.append(getDomain());
        outline107.append(", user=");
        outline107.append(getUser());
        outline107.append(", sipInstance=");
        outline107.append(getSipInstance());
        outline107.append(", expires=");
        outline107.append(getExpires());
        outline107.append(", authenticationInfo=");
        outline107.append(getAuthenticationInfo());
        outline107.append(", proxyEndpoint=");
        outline107.append(getProxyEndpoint());
        outline107.append(")");
        return outline107.toString();
    }

    private SIPRegisterInfo(String str, String str2, String str3, String str4, Integer num, SIPAuthenticationInfo sIPAuthenticationInfo, SIPProxyEndpoint sIPProxyEndpoint) {
        this.scheme = str;
        this.domain = str2;
        this.user = str3;
        this.sipInstance = str4;
        this.expires = num;
        this.authenticationInfo = sIPAuthenticationInfo;
        this.proxyEndpoint = sIPProxyEndpoint;
    }
}
