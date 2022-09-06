package amazon.communication.connection;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* loaded from: classes.dex */
public interface ConnectionPolicyProvider {
    @FireOsSdk
    ConnectionPolicy getConnectionPolicy() throws ConnectionPolicyException;

    @FireOsSdk
    ConnectionPolicyBuilder getConnectionPolicyBuilder();
}
