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
import amazon.communication.srr.SrrRequestBase;
import com.amazon.dp.logger.DPLogger;
import com.dp.framework.ByteBufferInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.InputStreamEntity;
@Deprecated
/* loaded from: classes.dex */
public class SrrUriDiscoveryRequestProcessorBase<I> {
    private static final DPLogger log = new DPLogger("TComm.SrrUriDiscoveryRequestProcessorBase");
    private final ObjectMapperFactory.ContentType mContentType;
    private final EndpointIdentity mDestinationIdentity;
    private final ObjectMapper mMapper;
    private final HttpClientSrrManagerBase mSrrManager;
    private final String mUrlPath;

    @Deprecated
    public SrrUriDiscoveryRequestProcessorBase(HttpClientSrrManagerBase httpClientSrrManagerBase, ObjectMapperFactory.ContentType contentType, EndpointIdentity endpointIdentity, String str) {
        if (httpClientSrrManagerBase != null) {
            if (endpointIdentity != null) {
                if (str == null || str.trim().equals("")) {
                    throw new IllegalArgumentException("Url path can't be empty or null.");
                }
                if (contentType != null) {
                    this.mSrrManager = httpClientSrrManagerBase;
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

    @Deprecated
    private HttpEntity createRequestEntity(I i) {
        ByteBuffer serialize = getObjectMapper().serialize(i);
        return new InputStreamEntity(new ByteBufferInputStream(serialize), serialize.remaining());
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

    @Deprecated
    public HttpPost createRequest(I i) {
        HttpPost httpPost = new HttpPost(getUrlPath());
        httpPost.addHeader("Content-Type", getContentType().toString());
        if (i != null) {
            httpPost.setEntity(createRequestEntity(i));
        }
        return httpPost;
    }

    @Deprecated
    public void executeDiscovery(int i) throws RequestFailedException, IllegalStateException, IOException, TimeoutException, MissingCredentialsException {
        executeDiscovery(Void.class, i);
    }

    @Deprecated
    public ObjectMapperFactory.ContentType getContentType() {
        return this.mContentType;
    }

    @Deprecated
    public ObjectMapper getObjectMapper() {
        return this.mMapper;
    }

    @Deprecated
    public String getUrlPath() {
        return this.mUrlPath;
    }

    @Deprecated
    public void executeDiscovery(I i, int i2) throws RequestFailedException, IllegalStateException, IOException, TimeoutException, MissingCredentialsException {
        executeDiscovery(i, Void.class, i2);
    }

    @Deprecated
    public <O> O executeDiscovery(Class<O> cls, int i) throws RequestFailedException, IllegalStateException, IOException, TimeoutException, MissingCredentialsException {
        return (O) executeDiscovery(null, cls, i);
    }

    @Deprecated
    public <O> O executeDiscovery(I i, Class<O> cls, int i2) throws RequestFailedException, IllegalStateException, IOException, TimeoutException, MissingCredentialsException {
        return (O) executeDiscovery(null, i, cls, i2);
    }

    @Deprecated
    public <O> O executeDiscovery(RequestContext requestContext, I i, Class<O> cls, int i2) throws TimeoutException, RequestFailedException, MissingCredentialsException, IllegalStateException, IOException {
        HttpRequestBase createRequest = createRequest(i);
        try {
            SrrRequestBase.Builder builder = new SrrRequestBase.Builder();
            if (requestContext != null) {
                builder.mo7setRequestContext(requestContext);
            }
            HttpResponse makeRequestSyncDiscoveryReturnResponse = this.mSrrManager.makeRequestSyncDiscoveryReturnResponse(builder.mo6setRequest(createRequest).mo3setEndpointIdentity(this.mDestinationIdentity).mo8setTimeout(i2).mo1build());
            validateHttpResponse(makeRequestSyncDiscoveryReturnResponse);
            if (makeRequestSyncDiscoveryReturnResponse.getEntity() == null) {
                return null;
            }
            if (cls == Void.class) {
                makeRequestSyncDiscoveryReturnResponse.getEntity().consumeContent();
                return null;
            }
            return (O) this.mMapper.deserialize(makeRequestSyncDiscoveryReturnResponse.getEntity().getContent(), cls);
        } catch (IllegalAccessException e) {
            throw new RequestFailedException(e);
        }
    }
}
