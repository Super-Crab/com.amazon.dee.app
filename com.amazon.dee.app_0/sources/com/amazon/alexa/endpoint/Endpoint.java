package com.amazon.alexa.endpoint;

import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes7.dex */
public final class Endpoint {
    @CheckForNull
    private final Cookie cookie;
    @CheckForNull
    private final String endpointId;
    @CheckForNull
    private final Scope scope;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes7.dex */
    public static class EndpointBuilder {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Cookie cookie;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private String endpointId;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private Scope scope;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        EndpointBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public Endpoint build() {
            return new Endpoint(this.scope, this.endpointId, this.cookie);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public EndpointBuilder cookie(Cookie cookie) {
            this.cookie = cookie;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public EndpointBuilder endpointId(String str) {
            this.endpointId = str;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public EndpointBuilder scope(Scope scope) {
            this.scope = scope;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Endpoint.EndpointBuilder(scope=");
            outline107.append(this.scope);
            outline107.append(", endpointId=");
            outline107.append(this.endpointId);
            outline107.append(", cookie=");
            outline107.append(this.cookie);
            outline107.append(")");
            return outline107.toString();
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    Endpoint(Scope scope, String str, Cookie cookie) {
        this.scope = scope;
        this.endpointId = str;
        this.cookie = cookie;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static EndpointBuilder builder() {
        return new EndpointBuilder();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Endpoint)) {
            return false;
        }
        Endpoint endpoint = (Endpoint) obj;
        Scope scope = getScope();
        Scope scope2 = endpoint.getScope();
        if (scope != null ? !scope.equals(scope2) : scope2 != null) {
            return false;
        }
        String endpointId = getEndpointId();
        String endpointId2 = endpoint.getEndpointId();
        if (endpointId != null ? !endpointId.equals(endpointId2) : endpointId2 != null) {
            return false;
        }
        Cookie cookie = getCookie();
        Cookie cookie2 = endpoint.getCookie();
        return cookie != null ? cookie.equals(cookie2) : cookie2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Cookie getCookie() {
        return this.cookie;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String getEndpointId() {
        return this.endpointId;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public Scope getScope() {
        return this.scope;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        Scope scope = getScope();
        int i = 43;
        int hashCode = scope == null ? 43 : scope.hashCode();
        String endpointId = getEndpointId();
        int hashCode2 = ((hashCode + 59) * 59) + (endpointId == null ? 43 : endpointId.hashCode());
        Cookie cookie = getCookie();
        int i2 = hashCode2 * 59;
        if (cookie != null) {
            i = cookie.hashCode();
        }
        return i2 + i;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Endpoint(scope=");
        outline107.append(getScope());
        outline107.append(", endpointId=");
        outline107.append(getEndpointId());
        outline107.append(", cookie=");
        outline107.append(getCookie());
        outline107.append(")");
        return outline107.toString();
    }
}
