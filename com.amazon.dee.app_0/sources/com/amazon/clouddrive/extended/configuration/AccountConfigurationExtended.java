package com.amazon.clouddrive.extended.configuration;

import com.amazon.clouddrive.auth.AuthenticatedURLConnectionFactory;
import com.amazon.clouddrive.auth.RequestAuthenticator;
import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.EndpointsCache;
/* loaded from: classes11.dex */
public class AccountConfigurationExtended extends AccountConfiguration {
    private final SourceInfoExtendedCache mSourceInfoCache;

    /* loaded from: classes11.dex */
    public static class Builder {
        private SourceInfoExtendedCache mSourceInfoCache;
        final AccountConfiguration.Builder mSuperBuilder = new AccountConfiguration.Builder();

        public AccountConfigurationExtended build() {
            return new AccountConfigurationExtended(this);
        }

        public Builder setAuthenticatedURLConnectionFactory(AuthenticatedURLConnectionFactory authenticatedURLConnectionFactory) {
            this.mSuperBuilder.setAuthenticatedURLConnectionFactory(authenticatedURLConnectionFactory);
            return this;
        }

        public Builder setEndpointsCache(EndpointsCache endpointsCache) {
            this.mSuperBuilder.setEndpointsCache(endpointsCache);
            return this;
        }

        public Builder setRequestAuthenticator(RequestAuthenticator requestAuthenticator) {
            this.mSuperBuilder.setRequestAuthenticator(requestAuthenticator);
            return this;
        }

        public Builder setSourceInfoCache(SourceInfoExtendedCache sourceInfoExtendedCache) {
            this.mSuperBuilder.setSourceInfoCache(sourceInfoExtendedCache);
            this.mSourceInfoCache = sourceInfoExtendedCache;
            return this;
        }
    }

    protected AccountConfigurationExtended(Builder builder) {
        super(builder.mSuperBuilder);
        this.mSourceInfoCache = builder.mSourceInfoCache;
    }

    @Override // com.amazon.clouddrive.configuration.AccountConfiguration
    /* renamed from: getSourceInfoCache  reason: collision with other method in class */
    public SourceInfoExtendedCache mo2996getSourceInfoCache() {
        return this.mSourceInfoCache;
    }
}
