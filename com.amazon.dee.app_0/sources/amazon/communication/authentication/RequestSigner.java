package amazon.communication.authentication;

import amazon.communication.MissingCredentialsException;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import org.apache.http.client.methods.HttpRequestBase;
/* loaded from: classes.dex */
public interface RequestSigner {
    @FireOsSdk
    public static final String SERVICE_NAME_HEADER = "x-dp-service-name";
    @FireOsSdk
    public static final String SERVICE_OPERATION_NAME_HEADER = "x-dp-service-operation-name";

    @FireOsSdk
    void signRequest(HttpRequestBase httpRequestBase) throws SigningException, MissingCredentialsException;

    @FireOsSdk
    void signRequest(HttpRequestBase httpRequestBase, RequestContext requestContext) throws SigningException, MissingCredentialsException;
}
