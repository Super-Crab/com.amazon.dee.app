package amazon.communication;

import amazon.communication.connection.ConnectionPolicyBuilder;
import com.amazon.communication.OneShotConnectionPolicyProvider;
/* loaded from: classes.dex */
public class DeviceOneShotConnectionPolicyProvider extends OneShotConnectionPolicyProvider {
    @Override // com.amazon.communication.OneShotConnectionPolicyProvider, amazon.communication.connection.ConnectionPolicyProvider
    public ConnectionPolicyBuilder getConnectionPolicyBuilder() {
        return new DeviceConnectionPolicyBuilder();
    }
}
