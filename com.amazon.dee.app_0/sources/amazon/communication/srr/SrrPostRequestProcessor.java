package amazon.communication.srr;

import amazon.communication.identity.EndpointIdentity;
import amazon.communication.serialize.ObjectMapperFactory;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.dp.framework.ByteBufferInputStream;
import java.nio.ByteBuffer;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.InputStreamEntity;
/* loaded from: classes.dex */
public class SrrPostRequestProcessor<I> extends SrrUriRequestProcessor<I> {
    @FireOsSdk
    @Deprecated
    public SrrPostRequestProcessor(SrrManager srrManager, ObjectMapperFactory.ContentType contentType, EndpointIdentity endpointIdentity, String str, MetricEvent metricEvent) {
        super(srrManager, contentType, endpointIdentity, str, metricEvent);
    }

    private HttpEntity createRequestEntity(I i) {
        ByteBuffer serialize = getObjectMapper().serialize(i);
        return new InputStreamEntity(new ByteBufferInputStream(serialize), serialize.remaining());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // amazon.communication.srr.SrrUriRequestProcessor
    @FireOsSdk
    @Deprecated
    /* renamed from: createRequest  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ HttpRequestBase mo0createRequest(Object obj) {
        return mo0createRequest((SrrPostRequestProcessor<I>) obj);
    }

    @FireOsSdk
    @Deprecated
    public SrrPostRequestProcessor(SingleRequestResponseManager singleRequestResponseManager, ObjectMapperFactory.ContentType contentType, EndpointIdentity endpointIdentity, String str, MetricEvent metricEvent) {
        super(singleRequestResponseManager, contentType, endpointIdentity, str, metricEvent);
    }

    @Override // amazon.communication.srr.SrrUriRequestProcessor
    @FireOsSdk
    @Deprecated
    /* renamed from: createRequest */
    public HttpPost mo0createRequest(I i) {
        HttpPost httpPost = new HttpPost(getUrlPath());
        httpPost.addHeader("Content-Type", getContentType().toString());
        if (i != null) {
            httpPost.setEntity(createRequestEntity(i));
        }
        return httpPost;
    }
}
