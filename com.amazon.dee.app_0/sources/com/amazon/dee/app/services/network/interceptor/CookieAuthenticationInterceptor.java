package com.amazon.dee.app.services.network.interceptor;

import android.webkit.CookieManager;
import androidx.annotation.NonNull;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.dee.app.services.coral.CookiesDomainUtil;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.util.WebUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes12.dex */
public class CookieAuthenticationInterceptor implements Interceptor {
    private static final String COOKIE_RETRIEVAL_FEATURE = "ALEXA_MOBILE_APP_GENERIC_FEATURE_53";
    private static final String HEADER_COOKIE = "Cookie";
    private static final String HEADER_CSRF = "csrf";
    private static final String TAG = Log.tag(CookieAuthenticationInterceptor.class);
    private static final Random random = new SecureRandom();
    protected Lazy<CookieManager> cookieManager;
    private Lazy<FeatureServiceV2> featureServiceV2Lazy;
    protected Lazy<IdentityService> identityService;

    public CookieAuthenticationInterceptor(Lazy<CookieManager> lazy, Lazy<IdentityService> lazy2, Lazy<FeatureServiceV2> lazy3) {
        this.cookieManager = lazy;
        this.identityService = lazy2;
        this.featureServiceV2Lazy = lazy3;
    }

    private Request authenticateNetworkRequest(Request request, String str) {
        Request.Builder newBuilder = request.newBuilder();
        String header = request.header("Cookie");
        String outline75 = header == null ? str : GeneratedOutlineSupport1.outline75(header, ";", str);
        String csrfCookieValue = getCsrfCookieValue(str);
        if (csrfCookieValue == null) {
            csrfCookieValue = Integer.toString(random.nextInt());
            outline75 = GeneratedOutlineSupport1.outline75(outline75, GeneratedOutlineSupport1.outline72("csrf=", csrfCookieValue), ";");
        }
        newBuilder.addHeader("Cookie", outline75);
        newBuilder.addHeader("csrf", csrfCookieValue);
        return newBuilder.build();
    }

    private boolean checkFeature(UserIdentity userIdentity) {
        return userIdentity != null && userIdentity.hasFeature("ALEXA_MOBILE_APP_GENERIC_FEATURE_53");
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
    @NonNull
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        UserIdentity user = this.identityService.mo358get().getUser(TAG);
        if (checkFeature(user)) {
            Log.i(TAG, "Retrieving authentication from IdentityService");
            if (user != null && this.identityService.mo358get().isAuthenticated(TAG)) {
                String str = null;
                if (this.featureServiceV2Lazy.mo358get() != null && this.featureServiceV2Lazy.mo358get().hasAccess("ALEXA_CORE_DATA_REGION_ENDPOINTS_ANDROID", false)) {
                    str = CookiesDomainUtil.getCookiesDomain(request.url().toString());
                }
                return chain.proceed(authenticateNetworkRequest(request, WebUtils.createSendableCookies(this.identityService.mo358get().getCookiesFromDirectedId(user.getDirectedId(), str).toBlocking().single())));
            }
            return chain.proceed(request);
        }
        Log.i(TAG, "Retrieving authentication from CookieManager");
        return chain.proceed(authenticateNetworkRequest(request, this.cookieManager.mo358get().getCookie(request.url().toString())));
    }
}
