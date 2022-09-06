package com.amazon.comms.calling.sipclient;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
/* loaded from: classes11.dex */
public final class RegistrarConfiguration {
    @Nullable
    private String authToken;
    private int authTokenIntervalInSecs;
    private final String callId;
    @Nonnull
    private final String domain;
    private final int expires;
    private final Map<String, String> headers;
    @Nullable
    private final String proxyHost;
    private final int proxyPort;
    @Nonnull
    private final String realm;
    private final long receivedTimeMillis;
    @Nonnull
    private final String scheme;
    @Nonnull
    private final String sipInstance;
    @Nonnull
    private final String user;

    /* loaded from: classes11.dex */
    public static class RegistrarConfigurationBuilder {
        private String authToken;
        private int authTokenIntervalInSecs;
        private String callId;
        private String domain;
        private int expires;
        private Map<String, String> headers;
        private String proxyHost;
        private int proxyPort;
        private String realm;
        private long receivedTimeMillis;
        private String scheme;
        private String sipInstance;
        private String user;

        RegistrarConfigurationBuilder() {
        }

        public RegistrarConfigurationBuilder authToken(String str) {
            this.authToken = str;
            return this;
        }

        public RegistrarConfigurationBuilder authTokenIntervalInSecs(int i) {
            this.authTokenIntervalInSecs = i;
            return this;
        }

        public RegistrarConfiguration build() {
            return new RegistrarConfiguration(this.scheme, this.domain, this.user, this.sipInstance, this.expires, this.receivedTimeMillis, this.realm, this.authToken, this.authTokenIntervalInSecs, this.proxyHost, this.proxyPort, this.headers, this.callId);
        }

        public RegistrarConfigurationBuilder callId(String str) {
            this.callId = str;
            return this;
        }

        public RegistrarConfigurationBuilder domain(String str) {
            this.domain = str;
            return this;
        }

        public RegistrarConfigurationBuilder expires(int i) {
            this.expires = i;
            return this;
        }

        public RegistrarConfigurationBuilder headers(Map<String, String> map) {
            this.headers = map;
            return this;
        }

        public RegistrarConfigurationBuilder proxyHost(String str) {
            this.proxyHost = str;
            return this;
        }

        public RegistrarConfigurationBuilder proxyPort(int i) {
            this.proxyPort = i;
            return this;
        }

        public RegistrarConfigurationBuilder realm(String str) {
            this.realm = str;
            return this;
        }

        public RegistrarConfigurationBuilder receivedTimeMillis(long j) {
            this.receivedTimeMillis = j;
            return this;
        }

        public RegistrarConfigurationBuilder scheme(String str) {
            this.scheme = str;
            return this;
        }

        public RegistrarConfigurationBuilder sipInstance(String str) {
            this.sipInstance = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RegistrarConfiguration.RegistrarConfigurationBuilder(scheme=");
            outline107.append(this.scheme);
            outline107.append(", domain=");
            outline107.append(this.domain);
            outline107.append(", user=");
            outline107.append(this.user);
            outline107.append(", sipInstance=");
            outline107.append(this.sipInstance);
            outline107.append(", expires=");
            outline107.append(this.expires);
            outline107.append(", receivedTimeMillis=");
            outline107.append(this.receivedTimeMillis);
            outline107.append(", realm=");
            outline107.append(this.realm);
            outline107.append(", authToken=");
            outline107.append(this.authToken);
            outline107.append(", authTokenIntervalInSecs=");
            outline107.append(this.authTokenIntervalInSecs);
            outline107.append(", proxyHost=");
            outline107.append(this.proxyHost);
            outline107.append(", proxyPort=");
            outline107.append(this.proxyPort);
            outline107.append(", headers=");
            outline107.append(this.headers);
            outline107.append(", callId=");
            return GeneratedOutlineSupport1.outline91(outline107, this.callId, ")");
        }

        public RegistrarConfigurationBuilder user(String str) {
            this.user = str;
            return this;
        }
    }

    RegistrarConfiguration(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nonnull String str4, int i, long j, @Nonnull String str5, @Nullable String str6, int i2, @Nullable String str7, int i3, Map<String, String> map, String str8) {
        if (str != null) {
            if (str2 == null) {
                throw new IllegalArgumentException("domain is null");
            }
            if (str3 == null) {
                throw new IllegalArgumentException("user is null");
            }
            if (str4 == null) {
                throw new IllegalArgumentException("sipInstance is null");
            }
            if (str5 == null) {
                throw new IllegalArgumentException("realm is null");
            }
            this.scheme = str;
            this.domain = str2;
            this.user = str3;
            this.sipInstance = str4;
            this.expires = i;
            this.receivedTimeMillis = j;
            this.realm = str5;
            this.authToken = str6;
            this.authTokenIntervalInSecs = i2;
            this.proxyHost = str7;
            this.proxyPort = i3;
            this.headers = map;
            this.callId = str8;
            return;
        }
        throw new IllegalArgumentException("scheme is null");
    }

    public static RegistrarConfigurationBuilder builder() {
        return new RegistrarConfigurationBuilder();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RegistrarConfiguration)) {
            return false;
        }
        RegistrarConfiguration registrarConfiguration = (RegistrarConfiguration) obj;
        String scheme = getScheme();
        String scheme2 = registrarConfiguration.getScheme();
        if (scheme != null ? !scheme.equals(scheme2) : scheme2 != null) {
            return false;
        }
        String domain = getDomain();
        String domain2 = registrarConfiguration.getDomain();
        if (domain != null ? !domain.equals(domain2) : domain2 != null) {
            return false;
        }
        String user = getUser();
        String user2 = registrarConfiguration.getUser();
        if (user != null ? !user.equals(user2) : user2 != null) {
            return false;
        }
        String sipInstance = getSipInstance();
        String sipInstance2 = registrarConfiguration.getSipInstance();
        if (sipInstance != null ? !sipInstance.equals(sipInstance2) : sipInstance2 != null) {
            return false;
        }
        if (getExpires() != registrarConfiguration.getExpires()) {
            return false;
        }
        String realm = getRealm();
        String realm2 = registrarConfiguration.getRealm();
        if (realm != null ? !realm.equals(realm2) : realm2 != null) {
            return false;
        }
        String proxyHost = getProxyHost();
        String proxyHost2 = registrarConfiguration.getProxyHost();
        if (proxyHost != null ? !proxyHost.equals(proxyHost2) : proxyHost2 != null) {
            return false;
        }
        return getProxyPort() == registrarConfiguration.getProxyPort();
    }

    @Nullable
    public String getAuthToken() {
        return this.authToken;
    }

    public int getAuthTokenIntervalInSecs() {
        return this.authTokenIntervalInSecs;
    }

    public String getCallId() {
        return this.callId;
    }

    @Nonnull
    public String getDomain() {
        return this.domain;
    }

    public int getExpires() {
        return this.expires;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Nullable
    public String getProxyHost() {
        return this.proxyHost;
    }

    public int getProxyPort() {
        return this.proxyPort;
    }

    @Nonnull
    public String getRealm() {
        return this.realm;
    }

    public long getReceivedTimeMillis() {
        return this.receivedTimeMillis;
    }

    @Nonnull
    public String getScheme() {
        return this.scheme;
    }

    @Nonnull
    public String getSipInstance() {
        return this.sipInstance;
    }

    @Nonnull
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
        int expires = getExpires() + (((hashCode3 * 59) + (sipInstance == null ? 43 : sipInstance.hashCode())) * 59);
        String realm = getRealm();
        int hashCode4 = (expires * 59) + (realm == null ? 43 : realm.hashCode());
        String proxyHost = getProxyHost();
        int i2 = hashCode4 * 59;
        if (proxyHost != null) {
            i = proxyHost.hashCode();
        }
        return getProxyPort() + ((i2 + i) * 59);
    }

    public void setAuthToken(@Nullable String str) {
        this.authToken = str;
    }

    public void setAuthTokenIntervalInSecs(int i) {
        this.authTokenIntervalInSecs = i;
    }

    public RegistrarConfigurationBuilder toBuilder() {
        return new RegistrarConfigurationBuilder().scheme(this.scheme).domain(this.domain).user(this.user).sipInstance(this.sipInstance).expires(this.expires).receivedTimeMillis(this.receivedTimeMillis).realm(this.realm).authToken(this.authToken).authTokenIntervalInSecs(this.authTokenIntervalInSecs).proxyHost(this.proxyHost).proxyPort(this.proxyPort).headers(this.headers).callId(this.callId);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RegistrarConfiguration(scheme=");
        outline107.append(getScheme());
        outline107.append(", domain=");
        outline107.append(getDomain());
        outline107.append(", user=");
        outline107.append(getUser());
        outline107.append(", sipInstance=");
        outline107.append(getSipInstance());
        outline107.append(", expires=");
        outline107.append(getExpires());
        outline107.append(", receivedTimeMillis=");
        outline107.append(getReceivedTimeMillis());
        outline107.append(", realm=");
        outline107.append(getRealm());
        outline107.append(", authTokenIntervalInSecs=");
        outline107.append(getAuthTokenIntervalInSecs());
        outline107.append(", proxyHost=");
        outline107.append(getProxyHost());
        outline107.append(", proxyPort=");
        outline107.append(getProxyPort());
        outline107.append(", headers=");
        outline107.append(getHeaders());
        outline107.append(", callId=");
        outline107.append(getCallId());
        outline107.append(")");
        return outline107.toString();
    }
}
