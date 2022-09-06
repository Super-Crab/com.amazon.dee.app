package com.amazon.deecomms.auth;

import com.amazon.clouddrive.auth.RequestAuthenticator;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.CookieUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import okhttp3.Request;
/* loaded from: classes12.dex */
public class CommsRequestAuthenticator implements RequestAuthenticator {
    private static final String COOKIE_HEADER = "Cookie";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, Utils.class);
    private static final String SESSION_COOKIE_START = "session-id=";
    private static final String SESSION_HEADER = "x-amzn-sessionid";
    private MAPAccountManager mAccountManager;

    public CommsRequestAuthenticator(MAPAccountManager mAPAccountManager) {
        this.mAccountManager = mAPAccountManager;
    }

    @Override // com.amazon.clouddrive.auth.RequestAuthenticator
    public Request authenticateRequest(Request request, boolean z) {
        String[] cookiesForDirectedId = CookieUtils.getCookiesForDirectedId(this.mAccountManager.getAccount(), z);
        String extractSessionIdFromCookies = CookieUtils.extractSessionIdFromCookies(cookiesForDirectedId);
        String cookieString = CookieUtils.toCookieString(cookiesForDirectedId);
        Request.Builder newBuilder = request.newBuilder();
        if (extractSessionIdFromCookies != null && extractSessionIdFromCookies.length() != 0) {
            newBuilder.header(SESSION_HEADER, extractSessionIdFromCookies);
        } else {
            LOG.e("[Sharing] FATAL: MAP did not return a session id.");
        }
        if (cookieString != null && cookieString.length() != 0) {
            newBuilder.header("Cookie", cookieString);
        } else {
            LOG.e("[Sharing] FATAL: MAP did not return a cookie.");
        }
        return newBuilder.build();
    }
}
