package amazon.communication.srr;

import amazon.communication.MissingCredentialsException;
import amazon.communication.RequestFailedException;
import amazon.communication.ResponseHandler;
import amazon.communication.TimeoutException;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import org.apache.http.HttpResponse;
/* loaded from: classes.dex */
public interface SrrManager {
    @FireOsSdk
    @Deprecated
    public static final int SRR_DEFAULT_TIMEOUT = 0;
    @FireOsSdk
    @Deprecated
    public static final String SYSTEM_SERVICE_KEY = "com.amazon.SingleRequestResponseManager";

    @FireOsSdk
    @Deprecated
    void makeRequestAsync(SrrRequest srrRequest, ResponseHandler responseHandler) throws RequestFailedException, MissingCredentialsException;

    @FireOsSdk
    @Deprecated
    HttpResponse makeRequestSync(SrrRequest srrRequest) throws TimeoutException, RequestFailedException, MissingCredentialsException;
}
