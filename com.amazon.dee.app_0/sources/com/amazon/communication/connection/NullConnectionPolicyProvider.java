package com.amazon.communication.connection;

import amazon.communication.Priority;
import amazon.communication.connection.ConnectionPolicy;
import amazon.communication.connection.ConnectionPolicyBuilder;
import amazon.communication.connection.ConnectionPolicyException;
import amazon.communication.connection.ConnectionPolicyProvider;
/* loaded from: classes12.dex */
public final class NullConnectionPolicyProvider implements ConnectionPolicyProvider {
    public static final NullConnectionPolicyProvider INSTANCE = new NullConnectionPolicyProvider();

    private NullConnectionPolicyProvider() {
    }

    @Override // amazon.communication.connection.ConnectionPolicyProvider
    public ConnectionPolicy getConnectionPolicy() throws ConnectionPolicyException {
        return null;
    }

    @Override // amazon.communication.connection.ConnectionPolicyProvider
    public ConnectionPolicyBuilder getConnectionPolicyBuilder() {
        return new ConnectionPolicyBuilder() { // from class: com.amazon.communication.connection.NullConnectionPolicyProvider.1
            @Override // amazon.communication.connection.ConnectionPolicyBuilder
            public ConnectionPolicy build() {
                return null;
            }

            @Override // amazon.communication.connection.ConnectionPolicyBuilder
            public ConnectionPolicyBuilder setCompressionOption(ConnectionPolicy.CompressionOption compressionOption) throws IllegalAccessException {
                return this;
            }

            @Override // amazon.communication.connection.ConnectionPolicyBuilder
            public ConnectionPolicyBuilder setIsAnonymousCredentialsAllowed(boolean z) throws IllegalAccessException {
                return this;
            }

            @Override // amazon.communication.connection.ConnectionPolicyBuilder
            public ConnectionPolicyBuilder setIsClearText(boolean z) throws IllegalAccessException {
                return this;
            }

            @Override // amazon.communication.connection.ConnectionPolicyBuilder
            public ConnectionPolicyBuilder setIsLowLatencyNecessary(boolean z) throws IllegalAccessException {
                return this;
            }

            @Override // amazon.communication.connection.ConnectionPolicyBuilder
            public ConnectionPolicyBuilder setIsRequestResponseOnly(boolean z) throws IllegalAccessException {
                return this;
            }

            @Override // amazon.communication.connection.ConnectionPolicyBuilder
            public ConnectionPolicyBuilder setIsRoamingAllowed(boolean z) throws IllegalAccessException {
                return this;
            }

            @Override // amazon.communication.connection.ConnectionPolicyBuilder
            public ConnectionPolicyBuilder setIsShortLived(boolean z) throws IllegalAccessException {
                return this;
            }

            @Override // amazon.communication.connection.ConnectionPolicyBuilder
            public ConnectionPolicyBuilder setIsWiFiNecessary(boolean z) throws IllegalAccessException {
                return this;
            }

            @Override // amazon.communication.connection.ConnectionPolicyBuilder
            public ConnectionPolicyBuilder setPriority(Priority priority) throws IllegalAccessException {
                return this;
            }

            @Override // amazon.communication.connection.ConnectionPolicyBuilder
            public ConnectionPolicyBuilder setPriority(amazon.communication.connection.Priority priority) throws IllegalAccessException {
                return this;
            }
        };
    }
}
