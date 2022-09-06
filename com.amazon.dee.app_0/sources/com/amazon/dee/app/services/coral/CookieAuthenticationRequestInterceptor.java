package com.amazon.dee.app.services.coral;

import android.webkit.CookieManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.HttpRequestConstants;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.util.WebUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.HttpCoralService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dagger.Lazy;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.security.SecureRandom;
import java.util.Random;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class CookieAuthenticationRequestInterceptor implements HttpCoralService.RequestInterceptor {
    private static final String COOKIE_RETRIEVAL_FEATURE = "ALEXA_MOBILE_APP_GENERIC_FEATURE_53";
    protected Lazy<CookieManager> cookieManager;
    private Lazy<EnvironmentService> environmentService;
    private Lazy<FeatureServiceV2> featureServiceV2Lazy;
    private Gson gson = new GsonBuilder().serializeNulls().create();
    protected Lazy<IdentityService> identityService;
    private Lazy<Mobilytics> mobilyticsLazy;
    private static final Random random = new SecureRandom();
    private static final String TAG = Log.tag(CookieAuthenticationRequestInterceptor.class);

    public CookieAuthenticationRequestInterceptor(Lazy<CookieManager> lazy, Lazy<IdentityService> lazy2, Lazy<Mobilytics> lazy3, Lazy<FeatureServiceV2> lazy4, Lazy<EnvironmentService> lazy5) {
        this.cookieManager = lazy;
        this.identityService = lazy2;
        this.mobilyticsLazy = lazy3;
        this.featureServiceV2Lazy = lazy4;
        this.environmentService = lazy5;
    }

    private boolean checkFeature(UserIdentity userIdentity) {
        return userIdentity != null && userIdentity.hasFeature("ALEXA_MOBILE_APP_GENERIC_FEATURE_53");
    }

    private String getCsrfCookieValue(String str) {
        if (str == null) {
            return null;
        }
        for (String str2 : str.split(";")) {
            String[] split = str2.split(Config.Compare.EQUAL_TO);
            if (split.length >= 2) {
                String trim = split[0].trim();
                String trim2 = split[1].trim();
                if (HttpRequestConstants.CSRF.equals(trim)) {
                    return trim2;
                }
            }
        }
        return null;
    }

    private void updateCookies(HttpCoralService.HttpRequest httpRequest, String str) {
        appendCookies(httpRequest, str);
        String csrf = getCsrf(str);
        appendCookies(httpRequest, HttpRequestConstants.CSRF, csrf);
        httpRequest.getHeaders().put(HttpRequestConstants.CSRF, csrf);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendCookies(HttpCoralService.HttpRequest httpRequest, String str, String str2) {
        String str3 = httpRequest.getHeaders().get("Cookie");
        String outline75 = GeneratedOutlineSupport1.outline75(str, Config.Compare.EQUAL_TO, str2);
        if (str3 != null) {
            if (str3.matches("[^;]" + str + "=.+[;$]")) {
                outline75 = str3.replaceAll("[^;]" + str + "=.+[;$]", outline75);
                httpRequest.getHeaders().put("Cookie", outline75);
            }
        }
        if (str3 != null) {
            if (!str3.endsWith(";")) {
                str3 = GeneratedOutlineSupport1.outline72(str3, ";");
            }
            outline75 = GeneratedOutlineSupport1.outline72(str3, outline75);
        }
        httpRequest.getHeaders().put("Cookie", outline75);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getCsrf(String str) {
        String csrfCookieValue = getCsrfCookieValue(str);
        return csrfCookieValue == null ? Integer.toString(random.nextInt()) : csrfCookieValue;
    }

    @Override // com.dee.app.http.HttpCoralService.RequestInterceptor
    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    public void intercept(final HttpCoralService.HttpRequest httpRequest) {
        final UserIdentity user = this.identityService.mo358get().getUser(TAG);
        if (checkFeature(user)) {
            if (user != null && this.identityService.mo358get().isRegistered(TAG)) {
                final String cookiesDomain = user.hasFeature("ALEXA_CORE_DATA_REGION_ENDPOINTS_ANDROID") ? CookiesDomainUtil.getCookiesDomain(this.environmentService.mo358get().getCoralEndpoint()) : null;
                this.identityService.mo358get().getCookiesFromDirectedId(user.getDirectedId(), cookiesDomain).toBlocking().subscribe(new Action1() { // from class: com.amazon.dee.app.services.coral.-$$Lambda$CookieAuthenticationRequestInterceptor$d8S8URZUUaQyMt_21ziWcWn1Yl0
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        CookieAuthenticationRequestInterceptor.this.lambda$intercept$0$CookieAuthenticationRequestInterceptor(httpRequest, (String[]) obj);
                    }
                }, new Action1() { // from class: com.amazon.dee.app.services.coral.-$$Lambda$CookieAuthenticationRequestInterceptor$6O13N8L1XkcqUUkZNiZN1vxOOYw
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        CookieAuthenticationRequestInterceptor.this.lambda$intercept$1$CookieAuthenticationRequestInterceptor(user, httpRequest, cookiesDomain, (Throwable) obj);
                    }
                });
                return;
            }
            recordErrorEvent(AlexaMetricsConstants.MetricEvents.AuthError.USER_NOT_REGISTERED, "CookieAuth", serializeExtraContent(Pair.create("url", httpRequest.getUrl())));
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("User Identity was null for request: ");
            outline107.append(httpRequest.getUrl());
            outline107.toString();
            return;
        }
        updateCookies(httpRequest, this.cookieManager.mo358get().getCookie(httpRequest.getUrl()));
    }

    public /* synthetic */ void lambda$intercept$0$CookieAuthenticationRequestInterceptor(HttpCoralService.HttpRequest httpRequest, String[] strArr) {
        updateCookies(httpRequest, WebUtils.createSendableCookies(strArr));
    }

    public /* synthetic */ void lambda$intercept$1$CookieAuthenticationRequestInterceptor(UserIdentity userIdentity, HttpCoralService.HttpRequest httpRequest, String str, Throwable th) {
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed fetching cookies for ");
        outline107.append(userIdentity.getDirectedId());
        Log.i(str2, outline107.toString(), th);
        recordErrorEvent(AlexaMetricsConstants.MetricEvents.AuthError.COOKIE_AUTH_ERROR, str, serializeExtraContent(Pair.create("url", httpRequest.getUrl()), Pair.create("directedId", userIdentity.getDirectedId()), Pair.create(AlexaMetricsConstants.MetricEvents.AuthError.KEY_EXCEPTION_MESSAGE, th.getMessage())));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recordErrorEvent(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        getClass().getSimpleName();
        String str4 = "Event name: " + str + "\nSource context: " + str2 + "\nExtras: " + str3;
        MobilyticsOperationalEvent createOperationalEvent = this.mobilyticsLazy.mo358get().createOperationalEvent(str, "error", AlexaMetricsConstants.MetricsComponents.CORAL_SERVICE, getClass().getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        if (str2 != null) {
            createOperationalEvent.setSourceContext(str2);
        }
        if (str3 != null) {
            createOperationalEvent.setContentProvider(str3);
        }
        this.mobilyticsLazy.mo358get().recordOperationalEvent(createOperationalEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String serializeExtraContent(Pair<String, String>... pairArr) {
        JsonObject jsonObject = new JsonObject();
        for (Pair<String, String> pair : pairArr) {
            jsonObject.addProperty(pair.first, pair.second);
        }
        return this.gson.toJson((JsonElement) jsonObject);
    }

    protected String getCsrf(String[] strArr) {
        String csrfCookieValue = getCsrfCookieValue(strArr);
        return csrfCookieValue == null ? Integer.toString(random.nextInt()) : csrfCookieValue;
    }

    private String getCsrfCookieValue(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        for (String str : strArr) {
            String[] split = str.split(Config.Compare.EQUAL_TO);
            if (split.length >= 2) {
                String trim = split[0].trim();
                String trim2 = split[1].trim();
                if (HttpRequestConstants.CSRF.equals(trim)) {
                    return trim2;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void appendCookies(HttpCoralService.HttpRequest httpRequest, String str) {
        String str2 = httpRequest.getHeaders().get("Cookie");
        if (str2 != null) {
            str = GeneratedOutlineSupport1.outline75(str2, ";", str);
        }
        httpRequest.getHeaders().put("Cookie", str);
    }
}
