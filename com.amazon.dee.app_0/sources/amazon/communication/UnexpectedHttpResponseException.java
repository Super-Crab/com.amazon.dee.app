package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import org.apache.http.HttpResponse;
/* loaded from: classes.dex */
public class UnexpectedHttpResponseException extends RequestFailedException {
    private static final long serialVersionUID = -1153420712191182556L;
    private final HttpResponse mResponse;

    @FireOsSdk
    public UnexpectedHttpResponseException(HttpResponse httpResponse) {
        this(httpResponse, httpResponse.getStatusLine().toString());
    }

    @FireOsSdk
    public HttpResponse getResponse() {
        return this.mResponse;
    }

    @FireOsSdk
    public UnexpectedHttpResponseException(HttpResponse httpResponse, String str) {
        super(str);
        this.mResponse = httpResponse;
    }
}
