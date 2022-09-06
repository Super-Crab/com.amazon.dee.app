package com.amazon.comms.models.sip;

import com.amazon.dee.application.service.common.logging.RedactInLogs;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
@RedactInLogs
/* loaded from: classes11.dex */
public class SIPProxyEndpoint {
    private String host;
    private Integer port;

    /* loaded from: classes11.dex */
    public static class SIPProxyEndpointBuilder {
        private String host;
        private Integer port;

        SIPProxyEndpointBuilder() {
        }

        public SIPProxyEndpoint build() {
            return new SIPProxyEndpoint(this.host, this.port);
        }

        public SIPProxyEndpointBuilder host(String str) {
            this.host = str;
            return this;
        }

        public SIPProxyEndpointBuilder port(Integer num) {
            this.port = num;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SIPProxyEndpoint.SIPProxyEndpointBuilder(host=");
            outline107.append(this.host);
            outline107.append(", port=");
            outline107.append(this.port);
            outline107.append(")");
            return outline107.toString();
        }
    }

    public SIPProxyEndpoint() {
    }

    public static SIPProxyEndpointBuilder builder() {
        return new SIPProxyEndpointBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SIPProxyEndpoint;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SIPProxyEndpoint)) {
            return false;
        }
        SIPProxyEndpoint sIPProxyEndpoint = (SIPProxyEndpoint) obj;
        if (!sIPProxyEndpoint.canEqual(this)) {
            return false;
        }
        String host = getHost();
        String host2 = sIPProxyEndpoint.getHost();
        if (host != null ? !host.equals(host2) : host2 != null) {
            return false;
        }
        Integer port = getPort();
        Integer port2 = sIPProxyEndpoint.getPort();
        return port != null ? port.equals(port2) : port2 == null;
    }

    public String getHost() {
        return this.host;
    }

    public Integer getPort() {
        return this.port;
    }

    public int hashCode() {
        String host = getHost();
        int i = 43;
        int hashCode = host == null ? 43 : host.hashCode();
        Integer port = getPort();
        int i2 = (hashCode + 59) * 59;
        if (port != null) {
            i = port.hashCode();
        }
        return i2 + i;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setPort(Integer num) {
        this.port = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SIPProxyEndpoint(host=");
        outline107.append(getHost());
        outline107.append(", port=");
        outline107.append(getPort());
        outline107.append(")");
        return outline107.toString();
    }

    private SIPProxyEndpoint(String str, Integer num) {
        this.host = str;
        this.port = num;
    }
}
