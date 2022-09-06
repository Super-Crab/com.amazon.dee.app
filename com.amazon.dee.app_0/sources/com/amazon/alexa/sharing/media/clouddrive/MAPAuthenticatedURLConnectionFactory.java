package com.amazon.alexa.sharing.media.clouddrive;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.clouddrive.auth.AuthenticatedURLConnectionFactory;
import com.amazon.comms.log.CommsLogger;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/* loaded from: classes10.dex */
public class MAPAuthenticatedURLConnectionFactory implements AuthenticatedURLConnectionFactory {
    private static final CommsLogger LOG = CommsLogger.getLogger("Alexa-Social-Sharing", MAPAuthenticatedURLConnectionFactory.class);
    private static final String TAG = "Alexa-Social-Sharing";
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
            return AuthenticatedURLConnection.openConnection(url, getAuthenticationMethod(directedId));
        }
        throw new IllegalStateException("There is no active account.");
    }

    AuthenticationMethod getAuthenticationMethod(String str) {
        return new AuthenticationMethodFactory(this.mContext, str).newAuthenticationMethod(AuthenticationType.OAuth);
    }

    String getDirectedId() {
        UserIdentity user = this.identityService.getUser("Alexa-Social-Sharing");
        if (user == null) {
            LOG.w("UserIdentity is null");
            return null;
        }
        LOG.v("Getting directedId from userIdentity");
        return user.getDirectedId();
    }
}
