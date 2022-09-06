package com.amazon.communication;

import amazon.communication.connection.ConnectionPolicy;
import amazon.communication.connection.ConnectionPolicyBuilder;
import amazon.communication.connection.ConnectionPolicyException;
import amazon.communication.connection.ConnectionPolicyProvider;
/* loaded from: classes12.dex */
public class OneShotConnectionPolicyProvider implements ConnectionPolicyProvider {
    private final ConnectionPolicy mOneShotPolicy;

    public OneShotConnectionPolicyProvider() throws ConnectionPolicyException {
        try {
            this.mOneShotPolicy = getConnectionPolicyBuilder().setIsRoamingAllowed(true).setIsShortLived(true).setIsLowLatencyNecessary(false).setIsRequestResponseOnly(true).setIsClearText(true).build();
        } catch (IllegalAccessException e) {
            throw new ConnectionPolicyException(e);
        }
    }

    @Override // amazon.communication.connection.ConnectionPolicyProvider
    public ConnectionPolicy getConnectionPolicy() {
        return this.mOneShotPolicy;
    }

    @Override // amazon.communication.connection.ConnectionPolicyProvider
    public ConnectionPolicyBuilder getConnectionPolicyBuilder() {
        return new SimpleConnectionPolicyBuilder();
    }
}
