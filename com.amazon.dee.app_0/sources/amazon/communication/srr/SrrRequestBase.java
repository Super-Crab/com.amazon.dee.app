package amazon.communication.srr;

import amazon.communication.authentication.RequestContext;
import amazon.communication.connection.CompressionOption;
import amazon.communication.identity.EndpointIdentity;
import org.apache.http.client.methods.HttpRequestBase;
@Deprecated
/* loaded from: classes.dex */
public class SrrRequestBase {
    private final CompressionOption mCompressionOption;
    private final EndpointIdentity mEndpointIdentity;
    private final boolean mIsClearTextAllowed;
    private final boolean mIsWiFiNecessary;
    private final HttpRequestBase mRequest;
    private final RequestContext mRequestContext;
    private final int mTimeoutInMillis;

    @Deprecated
    /* loaded from: classes.dex */
    public static class Builder {
        private EndpointIdentity mBuilderEndpointIdentity;
        private HttpRequestBase mBuilderRequest;
        private int mBuilderTimeoutInMillis = 0;
        private CompressionOption mBuilderCompressionOption = CompressionOption.ALLOWED;
        private boolean mBuilderIsWiFiNecessary = false;
        private boolean mBuilderIsClearTextAllowed = false;
        private RequestContext mBuilderRequestContext = null;
        private boolean mIsLocked = false;

        @Deprecated
        /* renamed from: build */
        public SrrRequestBase mo1build() throws IllegalAccessException {
            if (!this.mIsLocked) {
                this.mIsLocked = true;
                if (this.mBuilderRequest != null) {
                    if (this.mBuilderEndpointIdentity != null) {
                        return new SrrRequestBase(this);
                    }
                    throw new IllegalArgumentException("EndpointIdentity cannot be null");
                }
                throw new IllegalArgumentException("Request cannot be null");
            }
            throw new IllegalAccessException("Instance already locked");
        }

        @Deprecated
        /* renamed from: setCompressionOption */
        public Builder mo2setCompressionOption(CompressionOption compressionOption) throws IllegalAccessException {
            if (!this.mIsLocked) {
                if (compressionOption != null) {
                    this.mBuilderCompressionOption = compressionOption;
                }
                return this;
            }
            throw new IllegalAccessException("Instance already locked");
        }

        @Deprecated
        /* renamed from: setEndpointIdentity */
        public Builder mo3setEndpointIdentity(EndpointIdentity endpointIdentity) throws IllegalAccessException {
            if (!this.mIsLocked) {
                this.mBuilderEndpointIdentity = endpointIdentity;
                return this;
            }
            throw new IllegalAccessException("Instance already locked");
        }

        @Deprecated
        /* renamed from: setIsClearTextAllowed */
        public Builder mo4setIsClearTextAllowed(boolean z) throws IllegalAccessException {
            if (!this.mIsLocked) {
                this.mBuilderIsClearTextAllowed = z;
                return this;
            }
            throw new IllegalAccessException("Instance already locked");
        }

        @Deprecated
        /* renamed from: setIsWiFiNecessary */
        public Builder mo5setIsWiFiNecessary(boolean z) throws IllegalAccessException {
            if (!this.mIsLocked) {
                this.mBuilderIsWiFiNecessary = z;
                return this;
            }
            throw new IllegalAccessException("Instance already locked");
        }

        @Deprecated
        /* renamed from: setRequest */
        public Builder mo6setRequest(HttpRequestBase httpRequestBase) throws IllegalAccessException {
            if (!this.mIsLocked) {
                this.mBuilderRequest = httpRequestBase;
                return this;
            }
            throw new IllegalAccessException("Instance already locked");
        }

        @Deprecated
        /* renamed from: setRequestContext */
        public Builder mo7setRequestContext(RequestContext requestContext) throws IllegalAccessException {
            if (!this.mIsLocked) {
                this.mBuilderRequestContext = requestContext;
                return this;
            }
            throw new IllegalAccessException("Instance already locked");
        }

        @Deprecated
        /* renamed from: setTimeout */
        public Builder mo8setTimeout(int i) throws IllegalAccessException {
            if (!this.mIsLocked) {
                if (i >= 0) {
                    this.mBuilderTimeoutInMillis = i;
                    return this;
                }
                throw new IllegalArgumentException("Timeout must not be negative!");
            }
            throw new IllegalAccessException("Instance already locked");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public SrrRequestBase(Builder builder) {
        this.mEndpointIdentity = builder.mBuilderEndpointIdentity;
        this.mTimeoutInMillis = builder.mBuilderTimeoutInMillis;
        this.mRequest = builder.mBuilderRequest;
        this.mCompressionOption = builder.mBuilderCompressionOption;
        this.mIsWiFiNecessary = builder.mBuilderIsWiFiNecessary;
        this.mIsClearTextAllowed = builder.mBuilderIsClearTextAllowed;
        this.mRequestContext = builder.mBuilderRequestContext;
    }

    @Deprecated
    public CompressionOption getCompressionOption() {
        return this.mCompressionOption;
    }

    @Deprecated
    public EndpointIdentity getEndpointIdentity() {
        return this.mEndpointIdentity;
    }

    @Deprecated
    public HttpRequestBase getRequest() {
        return this.mRequest;
    }

    @Deprecated
    public RequestContext getRequestContext() {
        return this.mRequestContext;
    }

    @Deprecated
    public int getTimeout() {
        return this.mTimeoutInMillis;
    }

    @Deprecated
    public boolean isClearTextAllowed() {
        return this.mIsClearTextAllowed;
    }

    @Deprecated
    public boolean isWiFiNecessary() {
        return this.mIsWiFiNecessary;
    }
}
