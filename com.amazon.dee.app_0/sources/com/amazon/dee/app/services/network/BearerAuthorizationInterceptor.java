package com.amazon.dee.app.services.network;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URL;
import javax.inject.Provider;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes12.dex */
public class BearerAuthorizationInterceptor implements Interceptor {
    private static final String AUTHORIZATION_HEADER_KEY = "authorization";
    private static final String AUTHORIZATION_HEADER_VALUE_PREFIX = "Bearer ";
    private static final String SOURCE_PLACEHOLDER = "BearerTokenAuth";
    private final boolean bypassWhitelist;
    private final Provider<EnvironmentService> environmentService;
    private Gson gson = new GsonBuilder().serializeNulls().create();
    private final Provider<IdentityService> identityService;
    private final Provider<Mobilytics> mobilyticsProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BearerAuthorizationInterceptor(Provider<IdentityService> provider, Provider<EnvironmentService> provider2, Provider<Mobilytics> provider3, boolean z) {
        this.identityService = provider;
        this.environmentService = provider2;
        this.mobilyticsProvider = provider3;
        this.bypassWhitelist = !z;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        String host = chain.request().url().host();
        String serializeExtraContent = serializeExtraContent(Pair.create("url", host));
        if (TextUtils.isEmpty(chain.request().header(AUTHORIZATION_HEADER_KEY))) {
            if (chain.request().isHttps()) {
                URL url = new URL(chain.request().url().toString());
                if (!this.bypassWhitelist) {
                    String replace = url.getHost().replace(".amazonalexa.com", ".amazon.com");
                    if (!this.environmentService.mo10268get().isKnownUrl(EnvironmentService.KnownUrlType.AMAZON_ROOT, new URL(url.getProtocol(), replace, "/").toString())) {
                        recordErrorEvent(AlexaMetricsConstants.MetricEvents.AuthError.HOST_NOT_ALLOWED, SOURCE_PLACEHOLDER, serializeExtraContent(Pair.create("url", host), Pair.create(AlexaMetricsConstants.MetricEvents.AuthError.KEY_TRANSFORMED_URL, replace)));
                        throw new IOException(GeneratedOutlineSupport1.outline72("Host not allowed for Bearer auth:", host));
                    }
                }
                String accessToken = this.identityService.mo10268get().getAccessToken(BearerAuthorizationInterceptor.class.getSimpleName());
                if (!TextUtils.isEmpty(accessToken)) {
                    Request.Builder newBuilder = chain.request().newBuilder();
                    return chain.proceed(newBuilder.addHeader(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_HEADER_VALUE_PREFIX + accessToken).build());
                }
                recordErrorEvent(AlexaMetricsConstants.MetricEvents.AuthError.EMPTY_AUTH_TOKEN, SOURCE_PLACEHOLDER, serializeExtraContent);
                throw new IOException("Auth token was empty");
            }
            recordErrorEvent(AlexaMetricsConstants.MetricEvents.AuthError.REQ_INVALID_PROTOCOL, SOURCE_PLACEHOLDER, serializeExtraContent);
            throw new IOException(GeneratedOutlineSupport1.outline72("Https is required for Bearer auth:", host));
        }
        recordErrorEvent(AlexaMetricsConstants.MetricEvents.AuthError.REQ_AUTHENTICATED, SOURCE_PLACEHOLDER, serializeExtraContent);
        throw new IOException(GeneratedOutlineSupport1.outline72("No double authentication header:", host));
    }

    protected void recordErrorEvent(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116("Event name: ", str, "\nSource context: ", str2, "\nExtras: ");
        outline116.append(str3);
        outline116.toString();
        MobilyticsOperationalEvent createOperationalEvent = this.mobilyticsProvider.mo10268get().createOperationalEvent(str, "error", AlexaMetricsConstants.MetricsComponents.OKHTTP_CLIENT, BearerAuthorizationInterceptor.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        if (str2 != null) {
            createOperationalEvent.setSourceContext(str2);
        }
        if (str3 != null) {
            createOperationalEvent.setContentProvider(str3);
        }
        this.mobilyticsProvider.mo10268get().recordOperationalEvent(createOperationalEvent);
    }

    protected String serializeExtraContent(Pair<String, String>... pairArr) {
        JsonObject jsonObject = new JsonObject();
        for (Pair<String, String> pair : pairArr) {
            jsonObject.addProperty(pair.first, pair.second);
        }
        return this.gson.toJson((JsonElement) jsonObject);
    }
}
