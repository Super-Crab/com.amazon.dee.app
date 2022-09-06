package com.amazon.communication;

import amazon.communication.connection.ConnectionPolicy;
import amazon.communication.connection.ConnectionPolicyBuilder;
import amazon.communication.connection.Priority;
/* loaded from: classes12.dex */
public class SimpleConnectionPolicyBuilder implements ConnectionPolicyBuilder {
    protected final SimpleConnectionPolicy mPolicy = constructPolicy();

    @Override // amazon.communication.connection.ConnectionPolicyBuilder
    public ConnectionPolicy build() {
        this.mPolicy.setIsInstanceLocked(true);
        return this.mPolicy;
    }

    protected SimpleConnectionPolicy constructPolicy() {
        return new SimpleConnectionPolicy();
    }

    @Override // amazon.communication.connection.ConnectionPolicyBuilder
    public ConnectionPolicyBuilder setCompressionOption(ConnectionPolicy.CompressionOption compressionOption) throws IllegalAccessException {
        if (!this.mPolicy.isInstanceLocked()) {
            this.mPolicy.setCompressionOption(compressionOption);
            return this;
        }
        throw new IllegalAccessException();
    }

    @Override // amazon.communication.connection.ConnectionPolicyBuilder
    public ConnectionPolicyBuilder setIsAnonymousCredentialsAllowed(boolean z) throws IllegalAccessException {
        if (!this.mPolicy.isInstanceLocked()) {
            this.mPolicy.setIsAnonymousCredentialsAllowed(z);
            return this;
        }
        throw new IllegalAccessException();
    }

    @Override // amazon.communication.connection.ConnectionPolicyBuilder
    public ConnectionPolicyBuilder setIsClearText(boolean z) throws IllegalAccessException {
        if (!this.mPolicy.isInstanceLocked()) {
            this.mPolicy.setIsClearText(z);
            return this;
        }
        throw new IllegalAccessException();
    }

    @Override // amazon.communication.connection.ConnectionPolicyBuilder
    public ConnectionPolicyBuilder setIsLowLatencyNecessary(boolean z) throws IllegalAccessException {
        if (!this.mPolicy.isInstanceLocked()) {
            this.mPolicy.setIsLowLatencyNecessary(z);
            return this;
        }
        throw new IllegalAccessException();
    }

    @Override // amazon.communication.connection.ConnectionPolicyBuilder
    public ConnectionPolicyBuilder setIsRequestResponseOnly(boolean z) throws IllegalAccessException {
        if (!this.mPolicy.isInstanceLocked()) {
            this.mPolicy.setIsRequestResponseOnly(z);
            return this;
        }
        throw new IllegalAccessException();
    }

    @Override // amazon.communication.connection.ConnectionPolicyBuilder
    public ConnectionPolicyBuilder setIsRoamingAllowed(boolean z) throws IllegalAccessException {
        if (!this.mPolicy.isInstanceLocked()) {
            this.mPolicy.setIsRoamingAllowed(z);
            return this;
        }
        throw new IllegalAccessException();
    }

    @Override // amazon.communication.connection.ConnectionPolicyBuilder
    public ConnectionPolicyBuilder setIsShortLived(boolean z) throws IllegalAccessException {
        if (!this.mPolicy.isInstanceLocked()) {
            this.mPolicy.setIsShortLived(z);
            return this;
        }
        throw new IllegalAccessException();
    }

    @Override // amazon.communication.connection.ConnectionPolicyBuilder
    public ConnectionPolicyBuilder setIsWiFiNecessary(boolean z) throws IllegalAccessException {
        if (!this.mPolicy.isInstanceLocked()) {
            this.mPolicy.setIsWiFiNecessary(z);
            return this;
        }
        throw new IllegalAccessException();
    }

    @Override // amazon.communication.connection.ConnectionPolicyBuilder
    public ConnectionPolicyBuilder setPriority(Priority priority) throws IllegalAccessException {
        if (!this.mPolicy.isInstanceLocked()) {
            this.mPolicy.setPriority(priority);
            return this;
        }
        throw new IllegalAccessException();
    }

    @Override // amazon.communication.connection.ConnectionPolicyBuilder
    public ConnectionPolicyBuilder setPriority(amazon.communication.Priority priority) throws IllegalAccessException {
        return setPriority(priority.convertToConnectionPriority());
    }
}
