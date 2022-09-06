package com.amazon.dee.app.services.coral;

import android.text.TextUtils;
import android.webkit.CookieManager;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pair;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.HttpRequestConstants;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.Metric;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.util.Utils;
import com.amazon.dee.app.util.WebUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.ElementsDataRequestAuthentication;
import com.dee.app.http.HttpCoralService;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.HashMap;
import java.util.Map;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class ElementsCookieAuthenticationRequestInterceptor extends CookieAuthenticationRequestInterceptor {
    static final String AUTHORIZATION_HEADER = "Authorization";
    static final String BEARER_PREFIX = "Bearer ";
    static final String TAG = Utils.safeTag(ElementsCookieAuthenticationRequestInterceptor.class.getSimpleName());
    private final Lazy<CookieManager> cookieManager;
    private final Lazy<EnvironmentService> environmentService;
    private final Lazy<FeatureServiceV2> featureServiceV2;
    private final Lazy<IdentityService> identityService;
    private final Lazy<MetricsService> metricsService;

    public ElementsCookieAuthenticationRequestInterceptor(Lazy<CookieManager> lazy, Lazy<IdentityService> lazy2, Lazy<MetricsService> lazy3, Lazy<Mobilytics> lazy4, Lazy<FeatureServiceV2> lazy5, Lazy<EnvironmentService> lazy6) {
        super(lazy, lazy2, lazy4, lazy5, lazy6);
        this.cookieManager = lazy;
        this.identityService = lazy2;
        this.metricsService = lazy3;
        this.featureServiceV2 = lazy5;
        this.environmentService = lazy6;
    }

    private void appendRequiredAuthToken(HttpCoralService.HttpRequest httpRequest, ElementsDataRequestAuthentication elementsDataRequestAuthentication) {
        String accessToken;
        UserIdentity user = this.identityService.mo358get().getUser(TAG);
        if (this.featureServiceV2.mo358get() != null) {
            if (this.featureServiceV2.mo358get().hasAccess("ALEXA_BILOBA_ANDROID_MENU_INGRESS", false) && elementsDataRequestAuthentication.useDelegatedToken && user.getDelegatedProfile() != null) {
                accessToken = this.identityService.mo358get().getDelegatedToken(TAG);
            } else {
                accessToken = this.identityService.mo358get().getAccessToken(TAG);
            }
            if (accessToken.equals("")) {
                Log.e(TAG, "Failed fetching access token");
                recordError("Failed fetching access token");
                recordErrorEvent(AlexaMetricsConstants.MetricEvents.AuthError.EMPTY_AUTH_TOKEN, "GetAuthToken", serializeExtraContent(Pair.create("url", httpRequest.getUrl()), Pair.create("directedId", elementsDataRequestAuthentication.directedId)));
                return;
            }
            putAuthTokenIntoHeaders(elementsDataRequestAuthentication, httpRequest.getHeaders(), accessToken);
        }
    }

    private void appendRequiredCookies(HttpCoralService.HttpRequest httpRequest, String str) {
        appendCookies(httpRequest, str);
        String csrf = getCsrf(str);
        appendCookies(httpRequest, HttpRequestConstants.CSRF, csrf);
        httpRequest.getHeaders().put(HttpRequestConstants.CSRF, csrf);
    }

    @NonNull
    private ElementsDataRequestAuthentication createElementsDataRequestAuthentication(Map<String, String> map) {
        ElementsDataRequestAuthentication elementsDataRequestAuthentication = new ElementsDataRequestAuthentication();
        elementsDataRequestAuthentication.type = map.remove(ElementsDataRequestAuthentication.ELEMENTS_AUTH_HEADER_TYPE);
        elementsDataRequestAuthentication.directedId = map.remove(ElementsDataRequestAuthentication.ELEMENTS_AUTH_DIRECTED_ID);
        boolean z = true;
        elementsDataRequestAuthentication.forceRefresh = map.remove(ElementsDataRequestAuthentication.ELEMENTS_AUTH_FORCE) != null;
        elementsDataRequestAuthentication.authHeaderKey = map.remove(ElementsDataRequestAuthentication.ELEMENTS_AUTH_CUSTOM_HEADER);
        String remove = map.remove(ElementsDataRequestAuthentication.ELEMENTS_AUTH_USE_DELEGATED_TOKEN);
        if (remove == null || !remove.equals("true")) {
            z = false;
        }
        elementsDataRequestAuthentication.useDelegatedToken = z;
        return elementsDataRequestAuthentication;
    }

    private void putAuthTokenIntoHeaders(ElementsDataRequestAuthentication elementsDataRequestAuthentication, Map<String, String> map, String str) {
        String str2 = elementsDataRequestAuthentication.authHeaderKey;
        if (str2 == null) {
            str2 = "Authorization";
        }
        if (ElementsDataRequestAuthentication.ELEMENTS_AUTH_HEADER_TYPE_PLAIN.equals(elementsDataRequestAuthentication.type)) {
            map.put(str2, str);
            return;
        }
        map.put(str2, BEARER_PREFIX + str);
    }

    private void recordError(String str) {
        this.metricsService.mo358get().recordError(Metric.Event.UNKNOWN_MAP_FAILURE, str, AlexaMetricsConstants.MetricsComponents.ELEMENTS, null);
    }

    @Override // com.amazon.dee.app.services.coral.CookieAuthenticationRequestInterceptor, com.dee.app.http.HttpCoralService.RequestInterceptor
    public void intercept(HttpCoralService.HttpRequest httpRequest) {
        if (!this.identityService.mo358get().isRegistered(TAG)) {
            recordErrorEvent(AlexaMetricsConstants.MetricEvents.AuthError.USER_NOT_REGISTERED, "GetAuthToken", serializeExtraContent(Pair.create("url", httpRequest.getUrl())));
            return;
        }
        UserIdentity user = this.identityService.mo358get().getUser(TAG);
        if (user != null && user.hasFeature("ALEXA_CORE_DATA_REGION_ENDPOINTS_ANDROID")) {
            interceptDataRegion(httpRequest);
        } else {
            interceptLegacy(httpRequest);
        }
    }

    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    @VisibleForTesting
    void interceptDataRegion(final HttpCoralService.HttpRequest httpRequest) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Using data region approach for auth injection for url ");
        outline107.append(httpRequest.getUrl());
        outline107.toString();
        ElementsDataRequestAuthentication createElementsDataRequestAuthentication = createElementsDataRequestAuthentication(httpRequest.getHeaders());
        String str = createElementsDataRequestAuthentication.type;
        if (str != null && !ElementsDataRequestAuthentication.ELEMENTS_AUTH_HEADER_TYPE_COOKIES.equals(str)) {
            appendRequiredAuthToken(httpRequest, createElementsDataRequestAuthentication);
            return;
        }
        if (createElementsDataRequestAuthentication.directedId == null && createElementsDataRequestAuthentication.forceRefresh) {
            this.identityService.mo358get().refresh(TAG).toBlocking().subscribe(new Action1() { // from class: com.amazon.dee.app.services.coral.-$$Lambda$ElementsCookieAuthenticationRequestInterceptor$xdufMMlPI97Jb5DrBamuG9QuhKw
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    ElementsCookieAuthenticationRequestInterceptor.this.lambda$interceptDataRegion$3$ElementsCookieAuthenticationRequestInterceptor(httpRequest, (UserIdentity) obj);
                }
            });
        }
        UserIdentity user = this.identityService.mo358get().getUser(TAG);
        final String str2 = createElementsDataRequestAuthentication.directedId;
        if (str2 == null) {
            str2 = user.getDirectedId();
        }
        final String cookiesDomain = CookiesDomainUtil.getCookiesDomain(this.environmentService.mo358get().getCoralEndpoint());
        GeneratedOutlineSupport1.outline158("Data region Auth cookie injection for cookies domain: ", cookiesDomain);
        this.identityService.mo358get().getCookiesFromDirectedId(str2, cookiesDomain).toBlocking().subscribe(new Action1() { // from class: com.amazon.dee.app.services.coral.-$$Lambda$ElementsCookieAuthenticationRequestInterceptor$2W1PQNckkS-6AATE8MarQV_jG2E
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ElementsCookieAuthenticationRequestInterceptor.this.lambda$interceptDataRegion$4$ElementsCookieAuthenticationRequestInterceptor(httpRequest, (String[]) obj);
            }
        }, new Action1() { // from class: com.amazon.dee.app.services.coral.-$$Lambda$ElementsCookieAuthenticationRequestInterceptor$ljAKpM2HbmqPo-8NJ57SOfHyQ9M
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ElementsCookieAuthenticationRequestInterceptor.this.lambda$interceptDataRegion$5$ElementsCookieAuthenticationRequestInterceptor(str2, httpRequest, cookiesDomain, (Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    void interceptLegacy(final HttpCoralService.HttpRequest httpRequest) {
        if (httpRequest.getHeaders().containsKey(ElementsDataRequestAuthentication.ELEMENTS_AUTH_HEADER_TYPE)) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Elements domain is requesting a specialize auth header for url ");
            outline107.append(httpRequest.getUrl());
            outline107.toString();
            final ElementsDataRequestAuthentication createElementsDataRequestAuthentication = createElementsDataRequestAuthentication(httpRequest.getHeaders());
            String str = createElementsDataRequestAuthentication.type;
            if (str != null && !ElementsDataRequestAuthentication.ELEMENTS_AUTH_HEADER_TYPE_COOKIES.equals(str)) {
                appendRequiredAuthToken(httpRequest, createElementsDataRequestAuthentication);
                return;
            } else if (createElementsDataRequestAuthentication.directedId != null) {
                final UserIdentity user = this.identityService.mo358get().getUser(TAG);
                this.identityService.mo358get().getCookiesFromDirectedId(createElementsDataRequestAuthentication.directedId, null).toBlocking().subscribe(new Action1() { // from class: com.amazon.dee.app.services.coral.-$$Lambda$ElementsCookieAuthenticationRequestInterceptor$RdgA0L_5GIzX1uEnO-4amg2YjJ0
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        ElementsCookieAuthenticationRequestInterceptor.this.lambda$interceptLegacy$0$ElementsCookieAuthenticationRequestInterceptor(httpRequest, (String[]) obj);
                    }
                }, new Action1() { // from class: com.amazon.dee.app.services.coral.-$$Lambda$ElementsCookieAuthenticationRequestInterceptor$P3_DG4k8gVo_O-4QgXJ85fVWuRE
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        ElementsCookieAuthenticationRequestInterceptor.this.lambda$interceptLegacy$1$ElementsCookieAuthenticationRequestInterceptor(createElementsDataRequestAuthentication, httpRequest, user, (Throwable) obj);
                    }
                });
                return;
            } else if (createElementsDataRequestAuthentication.forceRefresh) {
                this.identityService.mo358get().refresh(TAG).toBlocking().subscribe(new Action1() { // from class: com.amazon.dee.app.services.coral.-$$Lambda$ElementsCookieAuthenticationRequestInterceptor$BjEG_9XoNcPjHAmpVeeBKnQG4gk
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        ElementsCookieAuthenticationRequestInterceptor.this.lambda$interceptLegacy$2$ElementsCookieAuthenticationRequestInterceptor(httpRequest, (UserIdentity) obj);
                    }
                });
            }
        }
        appendRequiredCookies(httpRequest, this.cookieManager.mo358get().getCookie(httpRequest.getUrl()));
    }

    public /* synthetic */ void lambda$interceptDataRegion$3$ElementsCookieAuthenticationRequestInterceptor(HttpCoralService.HttpRequest httpRequest, UserIdentity userIdentity) {
        UserIdentity user = this.identityService.mo358get().getUser(TAG);
        if (user != null) {
            if (this.identityService.mo358get().getAccessToken(TAG).equals("")) {
                Log.e(TAG, "Error: Was not able to refreshed user based on refresh interval");
                this.metricsService.mo358get().recordOccurrence(AlexaMetricsConstants.MetricEvents.REFRESH_AUTH_INTERCEPT_SUCCESS_RATE, TAG, false, null);
                recordErrorEvent(AlexaMetricsConstants.MetricEvents.AuthError.EMPTY_AUTH_TOKEN, "ForceRefreshAccessToken", serializeExtraContent(Pair.create("url", httpRequest.getUrl()), Pair.create("directedId", user.getDirectedId())));
                return;
            }
            Log.i(TAG, "Successfully refreshed user based on refresh interval");
            this.metricsService.mo358get().recordOccurrence(AlexaMetricsConstants.MetricEvents.REFRESH_AUTH_INTERCEPT_SUCCESS_RATE, TAG, true, null);
        }
    }

    public /* synthetic */ void lambda$interceptDataRegion$4$ElementsCookieAuthenticationRequestInterceptor(HttpCoralService.HttpRequest httpRequest, String[] strArr) {
        appendRequiredCookies(httpRequest, WebUtils.createSendableCookies(strArr));
    }

    public /* synthetic */ void lambda$interceptDataRegion$5$ElementsCookieAuthenticationRequestInterceptor(String str, HttpCoralService.HttpRequest httpRequest, String str2, Throwable th) {
        GeneratedOutlineSupport1.outline158("Failed fetching cookies for ", str);
        Log.e(TAG, "Failed fetching cookies", th);
        HashMap hashMap = new HashMap();
        hashMap.put(AlexaMetricsConstants.EventConstants.SOURCE, TextUtils.isEmpty(str) ? "directedId is empty" : "directedId is not empty");
        hashMap.put(AlexaMetricsConstants.EventConstants.MESSAGE, th.getMessage());
        this.metricsService.mo358get().recordEvent("MAP_COOKIES_FETCH_FAILURE", TAG, hashMap);
        recordErrorEvent(AlexaMetricsConstants.MetricEvents.AuthError.COOKIE_AUTH_ERROR, str2, serializeExtraContent(Pair.create("url", httpRequest.getUrl()), Pair.create("directedId", str), Pair.create(AlexaMetricsConstants.MetricEvents.AuthError.KEY_EXCEPTION_MESSAGE, th.getMessage())));
    }

    public /* synthetic */ void lambda$interceptLegacy$0$ElementsCookieAuthenticationRequestInterceptor(HttpCoralService.HttpRequest httpRequest, String[] strArr) {
        appendRequiredCookies(httpRequest, WebUtils.createSendableCookies(strArr));
    }

    public /* synthetic */ void lambda$interceptLegacy$1$ElementsCookieAuthenticationRequestInterceptor(ElementsDataRequestAuthentication elementsDataRequestAuthentication, HttpCoralService.HttpRequest httpRequest, UserIdentity userIdentity, Throwable th) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed fetching cookies for ");
        outline107.append(elementsDataRequestAuthentication.directedId);
        outline107.toString();
        new Object[1][0] = th;
        HashMap hashMap = new HashMap();
        hashMap.put(AlexaMetricsConstants.EventConstants.SOURCE, TextUtils.isEmpty(elementsDataRequestAuthentication.directedId) ? "directedId is empty" : "directedId is not empty");
        hashMap.put(AlexaMetricsConstants.EventConstants.MESSAGE, th.getMessage());
        this.metricsService.mo358get().recordEvent("MAP_COOKIES_FETCH_FAILURE", TAG, hashMap);
        recordErrorEvent(AlexaMetricsConstants.MetricEvents.AuthError.COOKIE_AUTH_ERROR, null, serializeExtraContent(Pair.create("url", httpRequest.getUrl()), Pair.create("directedId", userIdentity.getDirectedId()), Pair.create(AlexaMetricsConstants.MetricEvents.AuthError.KEY_EXCEPTION_MESSAGE, th.getMessage())));
    }

    public /* synthetic */ void lambda$interceptLegacy$2$ElementsCookieAuthenticationRequestInterceptor(HttpCoralService.HttpRequest httpRequest, UserIdentity userIdentity) {
        UserIdentity user = this.identityService.mo358get().getUser(TAG);
        if (user != null) {
            if (this.identityService.mo358get().getAccessToken(TAG).equals("")) {
                Log.e(TAG, "Error: Was not able to refreshed user based on refresh interval");
                this.metricsService.mo358get().recordOccurrence(AlexaMetricsConstants.MetricEvents.REFRESH_AUTH_INTERCEPT_SUCCESS_RATE, TAG, false, null);
                recordErrorEvent(AlexaMetricsConstants.MetricEvents.AuthError.EMPTY_AUTH_TOKEN, "ForceRefreshAccessToken", serializeExtraContent(Pair.create("url", httpRequest.getUrl()), Pair.create("directedId", user.getDirectedId())));
                return;
            }
            Log.i(TAG, "Successfully refreshed user based on refresh interval");
            this.metricsService.mo358get().recordOccurrence(AlexaMetricsConstants.MetricEvents.REFRESH_AUTH_INTERCEPT_SUCCESS_RATE, TAG, true, null);
        }
    }
}
