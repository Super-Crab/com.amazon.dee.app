package amazon.communication;

import amazon.communication.identity.EndpointIdentity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes.dex */
public interface ResponseHandlerBase {
    void onError(HttpRequestBase httpRequestBase, RequestFailedException requestFailedException);

    void onResponse(EndpointIdentity endpointIdentity, HttpResponse httpResponse, int i);
}
