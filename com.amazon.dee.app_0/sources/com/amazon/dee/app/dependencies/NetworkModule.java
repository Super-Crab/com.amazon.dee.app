package com.amazon.dee.app.dependencies;

import android.webkit.CookieManager;
import com.amazon.alexa.network.api.HttpClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
@Module
/* loaded from: classes12.dex */
public class NetworkModule {
    @Provides
    @ApplicationScope
    public CookieManager provideCookieManager() {
        return CookieManager.getInstance();
    }

    @Provides
    @ApplicationScope
    public Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @ApplicationScope
    public OkHttpClient provideHttpClient() {
        return ((HttpClient) GeneratedOutlineSupport1.outline20(HttpClient.class)).httpClient();
    }
}
