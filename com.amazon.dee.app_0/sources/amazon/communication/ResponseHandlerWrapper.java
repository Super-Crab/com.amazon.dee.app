package amazon.communication;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.client.metrics.DataPoint;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes.dex */
public class ResponseHandlerWrapper implements ResponseHandler, ResponseHandlerBase {
    private ResponseHandler delegate;

    public ResponseHandlerWrapper(ResponseHandler responseHandler) {
        this.delegate = responseHandler;
    }

    @Override // amazon.communication.ResponseHandler, amazon.communication.ResponseHandlerBase
    public void onError(HttpRequestBase httpRequestBase, RequestFailedException requestFailedException) {
        this.delegate.onError(httpRequestBase, requestFailedException);
    }

    @Override // amazon.communication.ResponseHandler
    public void onResponse(EndpointIdentity endpointIdentity, HttpResponse httpResponse, int i, List<DataPoint> list) {
        this.delegate.onResponse(endpointIdentity, httpResponse, i, list);
    }

    @Override // amazon.communication.ResponseHandlerBase
    public void onResponse(EndpointIdentity endpointIdentity, HttpResponse httpResponse, int i) {
        this.delegate.onResponse(endpointIdentity, httpResponse, i, new ArrayList());
    }
}
