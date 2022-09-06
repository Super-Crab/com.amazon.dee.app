package amazon.communication.srr;

import amazon.communication.MissingCredentialsException;
import amazon.communication.RequestFailedException;
import amazon.communication.TimeoutException;
import amazon.communication.authentication.RequestContext;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.serialize.ObjectMapperFactory;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.fireos.sdk.annotations.Extends;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.io.IOException;
import org.apache.http.client.methods.HttpPost;
@Extends(superClass = Object.class)
/* loaded from: classes.dex */
public class SrrUriDiscoveryRequestProcessor<I> extends SrrUriDiscoveryRequestProcessorBase<I> {
    @FireOsSdk
    @Deprecated
    public SrrUriDiscoveryRequestProcessor(HttpClientSrrManager httpClientSrrManager, ObjectMapperFactory.ContentType contentType, EndpointIdentity endpointIdentity, String str, MetricEvent metricEvent) {
        super(httpClientSrrManager, contentType, endpointIdentity, str);
    }

    @Override // amazon.communication.srr.SrrUriDiscoveryRequestProcessorBase
    @FireOsSdk
    @Deprecated
    public HttpPost createRequest(I i) {
        return super.createRequest(i);
    }

    @Override // amazon.communication.srr.SrrUriDiscoveryRequestProcessorBase
    @FireOsSdk
    @Deprecated
    public void executeDiscovery(int i) throws RequestFailedException, IllegalStateException, IOException, TimeoutException, MissingCredentialsException {
        super.executeDiscovery(i);
    }

    @Override // amazon.communication.srr.SrrUriDiscoveryRequestProcessorBase
    @FireOsSdk
    @Deprecated
    public ObjectMapperFactory.ContentType getContentType() {
        return super.getContentType();
    }

    @Override // amazon.communication.srr.SrrUriDiscoveryRequestProcessorBase
    @FireOsSdk
    @Deprecated
    public String getUrlPath() {
        return super.getUrlPath();
    }

    @Override // amazon.communication.srr.SrrUriDiscoveryRequestProcessorBase
    @FireOsSdk
    @Deprecated
    public void executeDiscovery(I i, int i2) throws RequestFailedException, IllegalStateException, IOException, TimeoutException, MissingCredentialsException {
        super.executeDiscovery((SrrUriDiscoveryRequestProcessor<I>) i, i2);
    }

    @Override // amazon.communication.srr.SrrUriDiscoveryRequestProcessorBase
    @FireOsSdk
    @Deprecated
    public <O> O executeDiscovery(Class<O> cls, int i) throws RequestFailedException, IllegalStateException, IOException, TimeoutException, MissingCredentialsException {
        return (O) super.executeDiscovery((Class<Object>) cls, i);
    }

    @Override // amazon.communication.srr.SrrUriDiscoveryRequestProcessorBase
    @FireOsSdk
    @Deprecated
    public <O> O executeDiscovery(I i, Class<O> cls, int i2) throws RequestFailedException, IllegalStateException, IOException, TimeoutException, MissingCredentialsException {
        return (O) super.executeDiscovery(i, cls, i2);
    }

    @Override // amazon.communication.srr.SrrUriDiscoveryRequestProcessorBase
    @FireOsSdk
    @Deprecated
    public <O> O executeDiscovery(RequestContext requestContext, I i, Class<O> cls, int i2) throws RequestFailedException, IllegalStateException, IOException, TimeoutException, MissingCredentialsException {
        return (O) super.executeDiscovery(requestContext, i, cls, i2);
    }
}
