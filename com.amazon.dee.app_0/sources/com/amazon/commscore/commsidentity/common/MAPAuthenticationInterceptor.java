package com.amazon.commscore.commsidentity.common;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.security.SecureRandom;
import okhttp3.Interceptor;
import okhttp3.Response;
import rx.Observable;
/* loaded from: classes12.dex */
public class MAPAuthenticationInterceptor implements Interceptor {
    private static final String COOKIE_HEADER = "Cookie";
    private static final String CSRF_IDENTIFIER = "csrf";
    private static final String TAG = "MAPAuthenticationInterceptor";
    private CommsLogger LOG = CommsLogger.getLogger(TAG);
    private EnvironmentService environmentService;
    private IdentityService identityService;

    public MAPAuthenticationInterceptor(IdentityService identityService, EnvironmentService environmentService) {
        this.identityService = identityService;
        this.environmentService = environmentService;
    }

    private String getAuthCookie(String str) {
        String[] strArr = new String[0];
        IdentityService identityService = this.identityService;
        Observable<String[]> cookiesFromDirectedId = identityService != null ? identityService.getCookiesFromDirectedId(str, this.environmentService.getAuthWebHost()) : null;
        if (cookiesFromDirectedId != null) {
            try {
                strArr = cookiesFromDirectedId.toBlocking().singleOrDefault(new String[0]);
            } catch (IllegalArgumentException e) {
                this.LOG.e("Failed to get cookie", e);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArr) {
            String[] split = str2.split(";", -1);
            if (split.length >= 1) {
                sb.append(split[0]);
                sb.append(";");
            } else {
                this.LOG.w("Malformed cookie: " + str2);
            }
        }
        return sb.toString();
    }

    private String getCsrfCookieValue(String str) {
        for (String str2 : str.split(";")) {
            String[] split = str2.split(Config.Compare.EQUAL_TO);
            if (split.length >= 2) {
                String trim = split[0].trim();
                String trim2 = split[1].trim();
                if ("csrf".equals(trim)) {
                    return trim2;
                }
            }
        }
        return null;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        String authCookie = getAuthCookie(this.identityService.getUser(TAG).getUserProfile().getDirectedId());
        String csrfCookieValue = getCsrfCookieValue(authCookie);
        if (csrfCookieValue == null) {
            this.LOG.d("csrf token not found, regenerating token");
            csrfCookieValue = Integer.toString(new SecureRandom().nextInt());
            authCookie = GeneratedOutlineSupport1.outline75(authCookie, "csrf=", csrfCookieValue);
        }
        return chain.proceed(chain.request().newBuilder().addHeader("Cookie", authCookie).addHeader("csrf", csrfCookieValue).build());
    }
}
