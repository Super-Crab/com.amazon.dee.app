package amazon.communication.srr;

import amazon.communication.MissingCredentialsException;
import amazon.communication.RequestFailedException;
import amazon.communication.ResponseHandlerBase;
import amazon.communication.TimeoutException;
import org.apache.http.HttpResponse;
@Deprecated
/* loaded from: classes.dex */
public interface ISrrManager {
    @Deprecated
    public static final int SRR_DEFAULT_TIMEOUT = 0;
    @Deprecated
    public static final String SYSTEM_SERVICE_KEY = "com.amazon.SingleRequestResponseManagerBase";

    @Deprecated
    void makeRequestAsync(SrrRequestBase srrRequestBase, ResponseHandlerBase responseHandlerBase) throws RequestFailedException, MissingCredentialsException;

    @Deprecated
    HttpResponse makeRequestSync(SrrRequestBase srrRequestBase) throws TimeoutException, RequestFailedException, MissingCredentialsException;
}
