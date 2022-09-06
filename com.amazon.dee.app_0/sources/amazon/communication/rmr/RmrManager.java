package amazon.communication.rmr;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
@Deprecated
/* loaded from: classes.dex */
public interface RmrManager {
    @FireOsSdk
    @Deprecated
    RmrRequest createRmrRequest(EndpointIdentity endpointIdentity, Message message, RmrResponseHandler rmrResponseHandler, MetricEvent metricEvent);

    @FireOsSdk
    @Deprecated
    void shutdown();
}
