package amazon.communication.srr;

import amazon.communication.identity.EndpointIdentity;
import amazon.communication.serialize.ObjectMapperFactory;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import org.apache.http.client.methods.HttpGet;
/* loaded from: classes.dex */
public class SrrGetRequestProcessor extends SrrUriRequestProcessor<Void> {
    @FireOsSdk
    @Deprecated
    public SrrGetRequestProcessor(SrrManager srrManager, ObjectMapperFactory.ContentType contentType, EndpointIdentity endpointIdentity, String str, MetricEvent metricEvent) {
        super(srrManager, contentType, endpointIdentity, str, metricEvent);
    }

    @FireOsSdk
    @Deprecated
    public SrrGetRequestProcessor(SingleRequestResponseManager singleRequestResponseManager, ObjectMapperFactory.ContentType contentType, EndpointIdentity endpointIdentity, String str, MetricEvent metricEvent) {
        super(singleRequestResponseManager, contentType, endpointIdentity, str, metricEvent);
    }

    @Override // amazon.communication.srr.SrrUriRequestProcessor
    @FireOsSdk
    @Deprecated
    /* renamed from: createRequest  reason: avoid collision after fix types in other method */
    public HttpGet mo0createRequest(Void r3) {
        HttpGet httpGet = new HttpGet(getUrlPath());
        httpGet.addHeader("Content-Type", getContentType().toString());
        return httpGet;
    }
}
