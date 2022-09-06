package com.amazon.alexa.biloba.service;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.service.mock.MockFrontEndService;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
@Deprecated
/* loaded from: classes6.dex */
public class FrontEndServiceRequest {
    private static final String ALERT_CONFIGURATIONS_PATH = "alertConfigurations";
    private static final String API = "api";
    private static final String API_VERSION = "v1";
    private static final String GET_ACTIVITY_PATH = "activities";
    private static final String GET_AVAILABLE_TIMEZONES_PATH = "availabletimezonesbyregion";
    private static final String GROUP_ID_KEY = "groupId";
    public static final MediaType JSON = MediaType.parse(LocationPublisher.CONTENT_TYPE_JSON);
    private static final String MAX_RESULTS_KEY = "maxResults";
    private static final String OFFSET_KEY = "offset";
    private static final String PERSIONID = "personId";
    private static final String PERSONS_PATH = "persons";
    private static final String SETTINGS_PATH = "settings";
    private static final String TAG = "FrontEndServiceRequest";
    private static final String TIMEZONE_KEY = "SystemTimeZone";
    private BilobaFrontEndServiceUrlResolver bilobaFrontEndServiceUrlResolver;
    private OkHttpClient httpClient;
    private MockFrontEndService mockFrontEndService;

    public FrontEndServiceRequest(BilobaFrontEndServiceUrlResolver bilobaFrontEndServiceUrlResolver) {
        this.mockFrontEndService = null;
        this.httpClient = getHttpClient();
        this.bilobaFrontEndServiceUrlResolver = bilobaFrontEndServiceUrlResolver;
        this.mockFrontEndService = new MockFrontEndService();
        this.mockFrontEndService.start();
    }

    private OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @VisibleForTesting
    private HttpUrl.Builder getUrlBuilder(boolean z) {
        HttpUrl.Builder addPathSegment = new HttpUrl.Builder().scheme("https").host(this.bilobaFrontEndServiceUrlResolver.resolve()).addPathSegment(API_VERSION);
        if (!z && this.mockFrontEndService != null) {
            LogUtils.i(TAG, "Use MockWebServer for HTTP request");
            return addPathSegment.scheme("http").host(this.mockFrontEndService.getHostName()).port(this.mockFrontEndService.getPort());
        }
        LogUtils.i(TAG, "Use FES for HTTP request");
        return addPathSegment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public HttpUrl addAlertConfigurationUrl(String str) {
        return getUrlBuilder(true).addPathSegment(ALERT_CONFIGURATIONS_PATH).addQueryParameter(GROUP_ID_KEY, str).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public HttpUrl deleteAlertConfigurationUrl(String str, String str2) {
        return getUrlBuilder(true).addPathSegment(ALERT_CONFIGURATIONS_PATH).addPathSegment(str2).addQueryParameter(GROUP_ID_KEY, str).build();
    }

    public Response executeHttpRequest(HttpUrl httpUrl, int i) {
        return executeHttpRequest(httpUrl, i, null);
    }

    @VisibleForTesting
    HttpUrl getActivityRequestUrl(String str) {
        return getUrlBuilder(false).addPathSegment(GET_ACTIVITY_PATH).addQueryParameter(GROUP_ID_KEY, str).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public HttpUrl getAlertConfigurationUrl(String str) {
        return getUrlBuilder(false).addPathSegment(ALERT_CONFIGURATIONS_PATH).addQueryParameter(GROUP_ID_KEY, str).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpUrl getDashboardCardsUrl() {
        return getUrlBuilder(false).addEncodedPathSegment("cards").build();
    }

    @VisibleForTesting
    HttpUrl getFetchAvailableTimezonesUrl(boolean z) {
        return getUrlBuilder(z).addPathSegment(API).addPathSegment(GET_AVAILABLE_TIMEZONES_PATH).build();
    }

    @VisibleForTesting
    HttpUrl getTimeZoneSettingsUrl(boolean z, String str) {
        return getUrlBuilder(z).addPathSegment(PERSONS_PATH).addPathSegment("personId").addPathSegment("settings").addPathSegment(TIMEZONE_KEY).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public HttpUrl modifyAlertConfigurationUrl(String str, String str2) {
        return getUrlBuilder(true).addPathSegment(ALERT_CONFIGURATIONS_PATH).addPathSegment(str2).addQueryParameter(GROUP_ID_KEY, str).build();
    }

    public Response executeHttpRequest(HttpUrl httpUrl, int i, String str) {
        Request request;
        if (this.httpClient == null) {
            this.httpClient = new OkHttpClient();
        }
        if (i == 0) {
            request = new Request.Builder().url(httpUrl).build();
        } else if (i == 2) {
            request = new Request.Builder().url(httpUrl).delete().build();
        } else if (i == 1) {
            request = new Request.Builder().url(httpUrl).post(RequestBody.create(JSON, str)).build();
        } else if (i == 3) {
            request = new Request.Builder().url(httpUrl).put(RequestBody.create(JSON, str)).build();
        } else {
            request = null;
        }
        try {
            return this.httpClient.newCall(request).execute();
        } catch (IOException e) {
            LogUtils.e(TAG, "Sent Http request with exception " + e);
            return null;
        }
    }

    @VisibleForTesting
    HttpUrl getActivityRequestUrl(String str, int i, int i2) {
        return getUrlBuilder(false).addPathSegment(GET_ACTIVITY_PATH).addQueryParameter(GROUP_ID_KEY, str).addQueryParameter(OFFSET_KEY, Integer.toString(i)).addQueryParameter(MAX_RESULTS_KEY, Integer.toString(i2)).build();
    }

    @VisibleForTesting
    public FrontEndServiceRequest(OkHttpClient okHttpClient, BilobaFrontEndServiceUrlResolver bilobaFrontEndServiceUrlResolver) {
        this.mockFrontEndService = null;
        this.httpClient = okHttpClient;
        this.bilobaFrontEndServiceUrlResolver = bilobaFrontEndServiceUrlResolver;
    }
}
