package com.amazon.dee.app.services.clouddrive;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.clouddrive.auth.AuthenticatedURLConnectionFactory;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/* loaded from: classes12.dex */
public class MAPAuthenticatedURLConnectionFactory implements AuthenticatedURLConnectionFactory {
    private static final String TAG = Log.tag(MAPAuthenticatedURLConnectionFactory.class);
    private final IdentityService identityService;
    private final Context mContext;

    public MAPAuthenticatedURLConnectionFactory(Context context, IdentityService identityService) {
        this.mContext = context;
        this.identityService = identityService;
    }

    @Override // com.amazon.clouddrive.auth.AuthenticatedURLConnectionFactory
    public HttpURLConnection createHttpURLConnection(URL url) throws IOException {
        String directedId = getDirectedId();
        if (!TextUtils.isEmpty(directedId)) {
            return AuthenticatedURLConnection.openConnection(url, new AuthenticationMethodFactory(this.mContext, directedId).newAuthenticationMethod(AuthenticationType.OAuth));
        }
        throw new IllegalStateException("There is no active account.");
    }

    String getDirectedId() {
        UserIdentity user = this.identityService.getUser(TAG);
        if (user == null) {
            Log.w(TAG, "userIdentity is null");
            return null;
        }
        return user.getDirectedId();
    }
}
