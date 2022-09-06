package com.amazon.dee.app.services.network;

import android.util.Base64;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.dee.app.BuildConfig;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import okhttp3.Interceptor;
import okhttp3.Response;
/* loaded from: classes12.dex */
public class ApplicationMetadataInterceptor implements Interceptor {
    private static final String APPLICATION_METADATA_HEADER = "x-amzn-alexa-app";
    private static String applicationMetadataValue;

    private static String getApplicationMetadata() {
        String str = applicationMetadataValue;
        if (str != null) {
            return str;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(SettingsStorageModule.FILTER_SETTINGS_APP_ID_KEY, BuildConfig.APP_ID_HEADER);
        jsonObject.addProperty("version", "1.0");
        jsonObject.addProperty("appVersion", "2022.10.10");
        applicationMetadataValue = Base64.encodeToString(jsonObject.toString().getBytes(StandardCharsets.UTF_8), 2);
        return applicationMetadataValue;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().header(APPLICATION_METADATA_HEADER, getApplicationMetadata()).build());
    }
}
