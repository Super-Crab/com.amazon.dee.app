package com.amazon.communication;

import amazon.communication.connection.ConnectionPolicy;
import amazon.communication.connection.ConnectionPolicyBuilder;
import amazon.communication.connection.ConnectionPolicyException;
/* loaded from: classes12.dex */
public class CommonConnectionPolicies {
    public static ConnectionPolicy createConnectionPolicyFastBidirectional(ConnectionPolicyBuilder connectionPolicyBuilder) throws ConnectionPolicyException {
        try {
            return connectionPolicyBuilder.setIsRoamingAllowed(true).setIsShortLived(false).setIsLowLatencyNecessary(true).setIsRequestResponseOnly(false).build();
        } catch (IllegalAccessException e) {
            throw new ConnectionPolicyException(e);
        }
    }

    public static ConnectionPolicy createConnectionPolicyFrequentFireAndForget(ConnectionPolicyBuilder connectionPolicyBuilder) throws ConnectionPolicyException {
        try {
            return connectionPolicyBuilder.setIsRoamingAllowed(false).setIsShortLived(false).setIsLowLatencyNecessary(false).setIsRequestResponseOnly(false).build();
        } catch (IllegalAccessException e) {
            throw new ConnectionPolicyException(e);
        }
    }

    public static ConnectionPolicy createConnectionPolicyOccasionalFireAndForget(ConnectionPolicyBuilder connectionPolicyBuilder) throws ConnectionPolicyException {
        try {
            return connectionPolicyBuilder.setIsRoamingAllowed(false).setIsShortLived(true).setIsLowLatencyNecessary(false).setIsRequestResponseOnly(false).build();
        } catch (IllegalAccessException e) {
            throw new ConnectionPolicyException(e);
        }
    }

    public static ConnectionPolicy createConnectionPolicyOneShotRequest(ConnectionPolicyBuilder connectionPolicyBuilder) throws ConnectionPolicyException {
        try {
            return connectionPolicyBuilder.setIsRoamingAllowed(true).setIsShortLived(true).setIsLowLatencyNecessary(false).setIsRequestResponseOnly(true).build();
        } catch (IllegalAccessException e) {
            throw new ConnectionPolicyException(e);
        }
    }

    public static ConnectionPolicy createConnectionPolicySharedBidirectional(ConnectionPolicyBuilder connectionPolicyBuilder) throws ConnectionPolicyException {
        try {
            return connectionPolicyBuilder.setIsRoamingAllowed(true).setIsShortLived(false).setIsLowLatencyNecessary(true).setIsRequestResponseOnly(false).build();
        } catch (IllegalAccessException e) {
            throw new ConnectionPolicyException(e);
        }
    }
}
