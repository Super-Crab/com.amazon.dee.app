package amazon.communication;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.client.metrics.DataPoint;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes.dex */
public interface ResponseHandler {
    @FireOsSdk
    void onError(HttpRequestBase httpRequestBase, RequestFailedException requestFailedException);

    @FireOsSdk
    @Deprecated
    void onResponse(EndpointIdentity endpointIdentity, HttpResponse httpResponse, int i, List<DataPoint> list);
}
