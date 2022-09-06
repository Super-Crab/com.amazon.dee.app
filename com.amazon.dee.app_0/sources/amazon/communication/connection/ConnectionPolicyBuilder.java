package amazon.communication.connection;

import amazon.communication.connection.ConnectionPolicy;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public interface ConnectionPolicyBuilder {
    @FireOsSdk
    ConnectionPolicy build();

    @FireOsSdk
    ConnectionPolicyBuilder setCompressionOption(ConnectionPolicy.CompressionOption compressionOption) throws IllegalAccessException;

    @FireOsSdk
    ConnectionPolicyBuilder setIsAnonymousCredentialsAllowed(boolean z) throws IllegalAccessException;

    @FireOsSdk
    ConnectionPolicyBuilder setIsClearText(boolean z) throws IllegalAccessException;

    @FireOsSdk
    ConnectionPolicyBuilder setIsLowLatencyNecessary(boolean z) throws IllegalAccessException;

    @FireOsSdk
    ConnectionPolicyBuilder setIsRequestResponseOnly(boolean z) throws IllegalAccessException;

    @FireOsSdk
    ConnectionPolicyBuilder setIsRoamingAllowed(boolean z) throws IllegalAccessException;

    @FireOsSdk
    ConnectionPolicyBuilder setIsShortLived(boolean z) throws IllegalAccessException;

    @FireOsSdk
    ConnectionPolicyBuilder setIsWiFiNecessary(boolean z) throws IllegalAccessException;

    @FireOsSdk
    ConnectionPolicyBuilder setPriority(amazon.communication.Priority priority) throws IllegalAccessException;

    @FireOsSdk
    ConnectionPolicyBuilder setPriority(Priority priority) throws IllegalAccessException;
}
