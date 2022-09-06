package com.amazon.alexa.accessorykit.interprocess.environment;

import android.content.Context;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.avsclient.utils.GsonJsonConverter;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.finishsetup.persistence.AccessoryPersistentStorage;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.google.gson.Gson;
/* loaded from: classes6.dex */
public final class AccessoryEnvironmentServicePreferences {
    static final String ALEXA_API_ENDPOINT_KEY = "alexaApiEndpoint";
    static final String ALEXA_API_HOST_KEY = "alexaApiHost";
    static final String AWS_REGION_KEY = "awsRegion";
    static final String ENDPOINT_KEY = "endpoint";
    static final String ENVIRONMENT_SERVICE_NAMESPACE = "accessories-environment-service";
    private static AccessoryEnvironmentServicePreferences instance;
    private final PersistentStorage store;

    private AccessoryEnvironmentServicePreferences(PersistentStorage.Factory factory) {
        Preconditions.notNull(factory, "factory");
        this.store = factory.create(ENVIRONMENT_SERVICE_NAMESPACE);
    }

    public static AccessoryEnvironmentServicePreferences getInstance(Context context) {
        if (instance == null) {
            synchronized (AccessoryEnvironmentServicePreferences.class) {
                if (instance == null) {
                    instance = new AccessoryEnvironmentServicePreferences(new AccessoryPersistentStorage.Factory(context, new GsonJsonConverter(new Gson())));
                }
            }
        }
        return instance;
    }

    public String getAWSRegion() {
        return this.store.getString(AWS_REGION_KEY);
    }

    @Nullable
    public String getAlexaApiEndpoint() {
        return this.store.getString(ALEXA_API_ENDPOINT_KEY);
    }

    @Nullable
    public String getAlexaApiHost() {
        return this.store.getString(ALEXA_API_HOST_KEY);
    }

    @Nullable
    public String getEndpoint() {
        return this.store.getString("endpoint");
    }

    public void setAWSRegion(String str) {
        this.store.edit().set(AWS_REGION_KEY, str).commit();
    }

    public void setAlexaApiEndpoint(String str) {
        this.store.edit().set(ALEXA_API_ENDPOINT_KEY, str).commit();
    }

    public void setAlexaApiHost(String str) {
        this.store.edit().set(ALEXA_API_HOST_KEY, str).commit();
    }

    public void setEndpoint(String str) {
        this.store.edit().set("endpoint", str).commit();
    }
}
