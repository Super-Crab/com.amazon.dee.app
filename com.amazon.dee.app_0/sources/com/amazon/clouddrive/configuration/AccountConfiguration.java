package com.amazon.clouddrive.configuration;

import com.amazon.clouddrive.auth.AuthenticatedURLConnectionFactory;
import com.amazon.clouddrive.auth.RequestAuthenticator;
import com.amazon.clouddrive.utils.AssertUtils;
/* loaded from: classes11.dex */
public class AccountConfiguration {
    private final AuthenticatedURLConnectionFactory mAuthenticatedURLConnectionFactory;
    private final EndpointsCache mEndpointsCache;
    private final RequestAuthenticator mRequestAuthenticator;
    private final SourceInfoCache mSourceInfoCache;

    /* loaded from: classes11.dex */
    public static class Builder {
        private AuthenticatedURLConnectionFactory mAuthenticatedURLConnectionFactory;
        private EndpointsCache mEndpointsCache;
        private RequestAuthenticator mRequestAuthenticator;
        private SourceInfoCache mSourceInfoCache;

        public AccountConfiguration build() {
            return new AccountConfiguration(this);
        }

        public Builder setAuthenticatedURLConnectionFactory(AuthenticatedURLConnectionFactory authenticatedURLConnectionFactory) {
            this.mAuthenticatedURLConnectionFactory = authenticatedURLConnectionFactory;
            return this;
        }

        public Builder setEndpointsCache(EndpointsCache endpointsCache) {
            this.mEndpointsCache = endpointsCache;
            return this;
        }

        public Builder setRequestAuthenticator(RequestAuthenticator requestAuthenticator) {
            this.mRequestAuthenticator = requestAuthenticator;
            return this;
        }

        public Builder setSourceInfoCache(SourceInfoCache sourceInfoCache) {
            this.mSourceInfoCache = sourceInfoCache;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AccountConfiguration(Builder builder) {
        AssertUtils.assertNotNull(builder.mRequestAuthenticator, "RequestAuthenticator was null.");
        AssertUtils.assertNotNull(builder.mEndpointsCache, "EndpointsCache was null.");
        AssertUtils.assertNotNull(builder.mAuthenticatedURLConnectionFactory, "AuthenticatedURLConnectionFactory was null.");
        this.mRequestAuthenticator = builder.mRequestAuthenticator;
        this.mEndpointsCache = builder.mEndpointsCache;
        this.mSourceInfoCache = builder.mSourceInfoCache;
        this.mAuthenticatedURLConnectionFactory = builder.mAuthenticatedURLConnectionFactory;
    }

    @Deprecated
    public AuthenticatedURLConnectionFactory getAuthenticatedURLConnectionFactory() {
        return this.mAuthenticatedURLConnectionFactory;
    }

    public EndpointsCache getEndpointsCache() {
        return this.mEndpointsCache;
    }

    public RequestAuthenticator getRequestAuthenticator() {
        return this.mRequestAuthenticator;
    }

    /* renamed from: getSourceInfoCache */
    public SourceInfoCache mo2996getSourceInfoCache() {
        return this.mSourceInfoCache;
    }
}
