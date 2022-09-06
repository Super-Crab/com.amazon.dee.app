package com.amazon.deecomms.auth;

import com.amazon.alexa.accessory.notificationpublisher.servicerequest.HttpRequestConstants;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.util.CookieUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class AuthHeaderProvider implements IHttpClient.AuthHeaderProvider {
    private static final String COOKIE_ASSIGNER = "=";
    private static final String COOKIE_HEADER = "Cookie";
    private static final String COOKIE_SEPARATOR = ";";
    private static final String CSRF_COOKIE_NAME = "csrf";
    private static final String CSRF_HEADER_NAME = "csrf";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AuthHeaderProvider.class);

    @Override // com.amazon.deecomms.common.network.IHttpClient.AuthHeaderProvider
    public Map<String, String> getAuthHeaders(String str, boolean z) {
        String sendableCookies = CookieUtils.getSendableCookies(str, z);
        String csrfCookieValue = CookieUtils.getCsrfCookieValue(sendableCookies);
        if (csrfCookieValue == null) {
            LOG.d("No csrf token found. Adding csrf tokens ourselves.");
            csrfCookieValue = Integer.toString(new SecureRandom().nextInt());
            sendableCookies = GeneratedOutlineSupport1.outline76(sendableCookies, HttpRequestConstants.CSRF, "=", csrfCookieValue);
        }
        HashMap outline133 = GeneratedOutlineSupport1.outline133(HttpRequestConstants.CSRF, csrfCookieValue);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("cookies for Url : ");
        outline1.append(LOG.sensitive(sendableCookies));
        commsLogger.d(outline1.toString());
        outline133.put("Cookie", sendableCookies);
        return outline133;
    }
}
