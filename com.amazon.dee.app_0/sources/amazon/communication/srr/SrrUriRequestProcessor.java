package amazon.communication.srr;

import amazon.communication.AuthenticationFailedException;
import amazon.communication.MissingCredentialsException;
import amazon.communication.RequestFailedException;
import amazon.communication.TimeoutException;
import amazon.communication.UnexpectedHttpResponseException;
import amazon.communication.authentication.RequestContext;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.serialize.ObjectMapper;
import amazon.communication.serialize.ObjectMapperFactory;
import amazon.communication.srr.SrrRequest;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.dp.logger.DPLogger;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes.dex */
public abstract class SrrUriRequestProcessor<I> {
    private static final DPLogger log = new DPLogger("TComm.SrrUriRequestProcessor");
    private final ObjectMapperFactory.ContentType mContentType;
    private final EndpointIdentity mDestinationIdentity;
    private final ObjectMapper mMapper;
    private final SrrManager mSrrManager;
    private final String mUrlPath;

    @FireOsSdk
    @Deprecated
    public SrrUriRequestProcessor(SrrManager srrManager, ObjectMapperFactory.ContentType contentType, EndpointIdentity endpointIdentity, String str, MetricEvent metricEvent) {
        if (srrManager != null) {
            if (endpointIdentity != null) {
                if (str == null || str.trim().equals("")) {
                    throw new IllegalArgumentException("Url path can't be empty or null.");
                }
                if (contentType != null) {
                    this.mSrrManager = srrManager;
                    this.mContentType = contentType;
                    this.mDestinationIdentity = endpointIdentity;
                    this.mUrlPath = str;
                    this.mMapper = ObjectMapperFactory.newObjectMapper(this.mContentType);
                    return;
                }
                throw new IllegalArgumentException("Content type can't be null.");
            }
            throw new IllegalArgumentException("Destination endpoint can't be null.");
        }
        throw new IllegalArgumentException("Srr manager can't be null.");
    }

    private void validateHttpResponse(HttpResponse httpResponse) throws UnexpectedHttpResponseException {
        StatusLine statusLine = httpResponse.getStatusLine();
        log.verbose("validateHttpResponse", "Validating response", "Response status line", statusLine);
        if (statusLine.getStatusCode() != 200) {
            if (statusLine.getStatusCode() != 401) {
                throw new UnexpectedHttpResponseException(httpResponse);
            }
            throw new AuthenticationFailedException(httpResponse);
        }
    }

    @FireOsSdk
    @Deprecated
    /* renamed from: createRequest */
    public abstract HttpRequestBase mo0createRequest(I i);

    @FireOsSdk
    @Deprecated
    public void execute(int i) throws TimeoutException, RequestFailedException, MissingCredentialsException, IllegalStateException, IOException {
        execute(Void.class, i);
    }

    @FireOsSdk
    @Deprecated
    public ObjectMapperFactory.ContentType getContentType() {
        return this.mContentType;
    }

    @FireOsSdk
    @Deprecated
    public EndpointIdentity getDestinationIdentity() {
        return this.mDestinationIdentity;
    }

    public ObjectMapper getObjectMapper() {
        return this.mMapper;
    }

    @FireOsSdk
    @Deprecated
    public String getUrlPath() {
        return this.mUrlPath;
    }

    @FireOsSdk
    @Deprecated
    public void execute(I i, int i2) throws TimeoutException, RequestFailedException, MissingCredentialsException, IllegalStateException, IOException {
        execute(i, Void.class, i2);
    }

    @FireOsSdk
    @Deprecated
    public <O> O execute(Class<O> cls, int i) throws TimeoutException, RequestFailedException, MissingCredentialsException, IllegalStateException, IOException {
        return (O) execute(null, cls, i);
    }

    @FireOsSdk
    @Deprecated
    public <O> O execute(I i, Class<O> cls, int i2) throws TimeoutException, RequestFailedException, MissingCredentialsException, IllegalStateException, IOException {
        return (O) execute(null, i, cls, i2);
    }

    @FireOsSdk
    @Deprecated
    public <O> O execute(RequestContext requestContext, I i, Class<O> cls, int i2) throws TimeoutException, RequestFailedException, MissingCredentialsException, IllegalStateException, IOException {
        HttpRequestBase mo0createRequest = mo0createRequest(i);
        try {
            SrrRequest.Builder builder = new SrrRequest.Builder();
            if (requestContext != null) {
                builder.mo7setRequestContext(requestContext);
            }
            HttpResponse makeRequestSync = this.mSrrManager.makeRequestSync(builder.mo6setRequest(mo0createRequest).mo3setEndpointIdentity(this.mDestinationIdentity).mo8setTimeout(i2).mo1build());
            validateHttpResponse(makeRequestSync);
            if (makeRequestSync.getEntity() == null) {
                return null;
            }
            if (cls == Void.class) {
                makeRequestSync.getEntity().consumeContent();
                return null;
            }
            return (O) this.mMapper.deserialize(makeRequestSync.getEntity().getContent(), cls);
        } catch (IllegalAccessException e) {
            throw new RequestFailedException(e);
        }
    }

    @FireOsSdk
    @Deprecated
    public SrrUriRequestProcessor(SingleRequestResponseManager singleRequestResponseManager, ObjectMapperFactory.ContentType contentType, EndpointIdentity endpointIdentity, String str, MetricEvent metricEvent) {
        this((SrrManager) singleRequestResponseManager, contentType, endpointIdentity, str, metricEvent);
    }
}
