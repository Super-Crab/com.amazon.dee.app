package amazon.communication.srr;

import amazon.communication.authentication.RequestContext;
import amazon.communication.connection.CompressionOption;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.srr.SrrRequestBase;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.fireos.sdk.annotations.Extends;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import org.apache.http.client.methods.HttpRequestBase;
@Extends(superClass = Object.class)
/* loaded from: classes.dex */
public class SrrRequest extends SrrRequestBase {

    @Extends(superClass = Object.class)
    @Deprecated
    /* loaded from: classes.dex */
    public static class Builder extends SrrRequestBase.Builder {
        @FireOsSdk
        @Deprecated
        public Builder setMetricEvent(MetricEvent metricEvent) throws IllegalAccessException {
            return this;
        }

        @Override // amazon.communication.srr.SrrRequestBase.Builder
        @FireOsSdk
        @Deprecated
        /* renamed from: build */
        public SrrRequest mo1build() throws IllegalAccessException {
            return new SrrRequest(this);
        }

        @Override // amazon.communication.srr.SrrRequestBase.Builder
        @FireOsSdk
        @Deprecated
        /* renamed from: setCompressionOption */
        public Builder mo2setCompressionOption(CompressionOption compressionOption) throws IllegalAccessException {
            super.mo2setCompressionOption(compressionOption);
            return this;
        }

        @Override // amazon.communication.srr.SrrRequestBase.Builder
        @FireOsSdk
        @Deprecated
        /* renamed from: setEndpointIdentity */
        public Builder mo3setEndpointIdentity(EndpointIdentity endpointIdentity) throws IllegalAccessException {
            super.mo3setEndpointIdentity(endpointIdentity);
            return this;
        }

        @Override // amazon.communication.srr.SrrRequestBase.Builder
        @FireOsSdk
        @Deprecated
        /* renamed from: setIsClearTextAllowed */
        public Builder mo4setIsClearTextAllowed(boolean z) throws IllegalAccessException {
            super.mo4setIsClearTextAllowed(z);
            return this;
        }

        @Override // amazon.communication.srr.SrrRequestBase.Builder
        @FireOsSdk
        @Deprecated
        /* renamed from: setIsWiFiNecessary */
        public Builder mo5setIsWiFiNecessary(boolean z) throws IllegalAccessException {
            super.mo5setIsWiFiNecessary(z);
            return this;
        }

        @Override // amazon.communication.srr.SrrRequestBase.Builder
        @FireOsSdk
        @Deprecated
        /* renamed from: setRequest */
        public Builder mo6setRequest(HttpRequestBase httpRequestBase) throws IllegalAccessException {
            super.mo6setRequest(httpRequestBase);
            return this;
        }

        @Override // amazon.communication.srr.SrrRequestBase.Builder
        @FireOsSdk
        @Deprecated
        /* renamed from: setRequestContext */
        public Builder mo7setRequestContext(RequestContext requestContext) throws IllegalAccessException {
            super.mo7setRequestContext(requestContext);
            return this;
        }

        @Override // amazon.communication.srr.SrrRequestBase.Builder
        @FireOsSdk
        @Deprecated
        /* renamed from: setTimeout */
        public Builder mo8setTimeout(int i) throws IllegalAccessException {
            super.mo8setTimeout(i);
            return this;
        }
    }

    @Deprecated
    public SrrRequest(Builder builder) {
        super(builder);
    }

    @Override // amazon.communication.srr.SrrRequestBase
    @FireOsSdk
    @Deprecated
    public CompressionOption getCompressionOption() {
        return super.getCompressionOption();
    }

    @Override // amazon.communication.srr.SrrRequestBase
    @FireOsSdk
    @Deprecated
    public EndpointIdentity getEndpointIdentity() {
        return super.getEndpointIdentity();
    }

    @FireOsSdk
    @Deprecated
    public MetricEvent getMetricEvent() {
        throw new UnsupportedOperationException();
    }

    @Override // amazon.communication.srr.SrrRequestBase
    @FireOsSdk
    @Deprecated
    public HttpRequestBase getRequest() {
        return super.getRequest();
    }

    @Override // amazon.communication.srr.SrrRequestBase
    @FireOsSdk
    @Deprecated
    public RequestContext getRequestContext() {
        return super.getRequestContext();
    }

    @Override // amazon.communication.srr.SrrRequestBase
    @FireOsSdk
    @Deprecated
    public int getTimeout() {
        return super.getTimeout();
    }

    @Override // amazon.communication.srr.SrrRequestBase
    @FireOsSdk
    @Deprecated
    public boolean isClearTextAllowed() {
        return super.isClearTextAllowed();
    }

    @Override // amazon.communication.srr.SrrRequestBase
    @FireOsSdk
    @Deprecated
    public boolean isWiFiNecessary() {
        return super.isWiFiNecessary();
    }
}
