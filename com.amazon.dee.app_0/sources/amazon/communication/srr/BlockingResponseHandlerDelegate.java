package amazon.communication.srr;

import amazon.communication.RequestFailedException;
import amazon.communication.ResponseHandler;
import amazon.communication.ResponseHandlerBase;
import amazon.communication.identity.EndpointIdentity;
import com.amazon.client.metrics.DataPoint;
import com.amazon.communication.BlockingResponseHandler;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes.dex */
public class BlockingResponseHandlerDelegate implements ResponseHandler, ResponseHandlerBase {
    private BlockingResponseHandler delegate;

    public BlockingResponseHandlerDelegate(BlockingResponseHandler blockingResponseHandler) {
        this.delegate = blockingResponseHandler;
    }

    @Override // amazon.communication.ResponseHandler, amazon.communication.ResponseHandlerBase
    public void onError(HttpRequestBase httpRequestBase, RequestFailedException requestFailedException) {
        this.delegate.onError(httpRequestBase, requestFailedException);
    }

    @Override // amazon.communication.ResponseHandlerBase
    public void onResponse(EndpointIdentity endpointIdentity, HttpResponse httpResponse, int i) {
        this.delegate.onResponse(endpointIdentity, httpResponse, i);
    }

    @Override // amazon.communication.ResponseHandler
    public void onResponse(EndpointIdentity endpointIdentity, HttpResponse httpResponse, int i, List<DataPoint> list) {
        this.delegate.onResponse(endpointIdentity, httpResponse, i);
    }
}
