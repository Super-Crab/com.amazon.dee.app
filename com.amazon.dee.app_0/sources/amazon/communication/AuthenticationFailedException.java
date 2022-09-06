package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import org.apache.http.HttpResponse;
/* loaded from: classes.dex */
public class AuthenticationFailedException extends UnexpectedHttpResponseException {
    private static final long serialVersionUID = 6851186175866994465L;

    @FireOsSdk
    public AuthenticationFailedException(HttpResponse httpResponse) {
        super(httpResponse);
    }
}
