package com.brentvatne.exoplayer;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.network.CookieJarContainer;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;
import java.util.Map;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
/* loaded from: classes.dex */
public class DataSourceUtil {
    private static DataSource.Factory defaultDataSourceFactory;
    private static HttpDataSource.Factory defaultHttpDataSourceFactory;
    private static DataSource.Factory rawDataSourceFactory;
    private static String userAgent;

    private DataSourceUtil() {
    }

    private static DataSource.Factory buildDataSourceFactory(ReactContext reactContext, DefaultBandwidthMeter defaultBandwidthMeter, Map<String, String> map) {
        return new DefaultDataSourceFactory(reactContext, defaultBandwidthMeter, buildHttpDataSourceFactory(reactContext, defaultBandwidthMeter, map));
    }

    private static HttpDataSource.Factory buildHttpDataSourceFactory(ReactContext reactContext, DefaultBandwidthMeter defaultBandwidthMeter, Map<String, String> map) {
        OkHttpClient okHttpClient = OkHttpClientProvider.getOkHttpClient();
        ((CookieJarContainer) okHttpClient.cookieJar()).setCookieJar(new JavaNetCookieJar(new ForwardingCookieHandler(reactContext)));
        OkHttpDataSourceFactory okHttpDataSourceFactory = new OkHttpDataSourceFactory(okHttpClient, getUserAgent(reactContext), defaultBandwidthMeter);
        if (map != null) {
            okHttpDataSourceFactory.getDefaultRequestProperties().set(map);
        }
        return okHttpDataSourceFactory;
    }

    private static DataSource.Factory buildRawDataSourceFactory(ReactContext reactContext) {
        return new RawResourceDataSourceFactory(reactContext.getApplicationContext());
    }

    public static DataSource.Factory getDefaultDataSourceFactory(ReactContext reactContext, DefaultBandwidthMeter defaultBandwidthMeter, Map<String, String> map) {
        if (defaultDataSourceFactory == null || (map != null && !map.isEmpty())) {
            defaultDataSourceFactory = buildDataSourceFactory(reactContext, defaultBandwidthMeter, map);
        }
        return defaultDataSourceFactory;
    }

    public static HttpDataSource.Factory getDefaultHttpDataSourceFactory(ReactContext reactContext, DefaultBandwidthMeter defaultBandwidthMeter, Map<String, String> map) {
        if (defaultHttpDataSourceFactory == null || (map != null && !map.isEmpty())) {
            defaultHttpDataSourceFactory = buildHttpDataSourceFactory(reactContext, defaultBandwidthMeter, map);
        }
        return defaultHttpDataSourceFactory;
    }

    public static DataSource.Factory getRawDataSourceFactory(ReactContext reactContext) {
        if (rawDataSourceFactory == null) {
            rawDataSourceFactory = buildRawDataSourceFactory(reactContext);
        }
        return rawDataSourceFactory;
    }

    public static String getUserAgent(ReactContext reactContext) {
        if (userAgent == null) {
            userAgent = Util.getUserAgent(reactContext, "ReactNativeVideo");
        }
        return userAgent;
    }

    public static void setDefaultDataSourceFactory(DataSource.Factory factory) {
        defaultDataSourceFactory = factory;
    }

    public static void setDefaultHttpDataSourceFactory(HttpDataSource.Factory factory) {
        defaultHttpDataSourceFactory = factory;
    }

    public static void setRawDataSourceFactory(DataSource.Factory factory) {
        rawDataSourceFactory = factory;
    }

    public static void setUserAgent(String str) {
        userAgent = str;
    }
}
