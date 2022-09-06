package com.amazon.dee.app.services.environment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes12.dex */
public class PersistentEndpointsStorage {
    private static final String ENDPOINTS_STORAGE_NAMESPACE = "environment.endpoints";
    private static final String TAG = "PersistentEndpointsStorage";
    private final PersistentStorage storage;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface StorageKey {
        public static final String ALEXA_API_URL = "endpoints.alexaApiUrl";
        public static final String AWS_REGION = "endpoints.awsRegion";
        public static final String RETAIL_DOMAIN = "endpoints.retailDomain";
        public static final String RETAIL_URL = "endpoints.retailUrl";
        public static final String SCHEMA_VERSION = "endpoints.version";
        public static final String SKILLS_STORE_URL = "endpoints.skillsStoreUrl";
        public static final String WEBSITE_API_URL = "endpoints.websiteApiUrl";
        public static final String WEBSITE_URL = "endpoints.websiteUrl";
    }

    public PersistentEndpointsStorage(PersistentStorage.Factory factory) {
        this.storage = factory.create(ENDPOINTS_STORAGE_NAMESPACE);
    }

    public void clear() {
        this.storage.edit().remove(StorageKey.SCHEMA_VERSION).remove(StorageKey.ALEXA_API_URL).remove(StorageKey.WEBSITE_API_URL).remove(StorageKey.WEBSITE_URL).remove(StorageKey.RETAIL_DOMAIN).remove(StorageKey.RETAIL_URL).remove(StorageKey.AWS_REGION).remove(StorageKey.SKILLS_STORE_URL).commit();
    }

    @Nullable
    public Endpoints get() {
        if (this.storage.contains(StorageKey.SCHEMA_VERSION) && "4".equals(this.storage.getString(StorageKey.SCHEMA_VERSION))) {
            return new Endpoints(this.storage.getString(StorageKey.ALEXA_API_URL), this.storage.getString(StorageKey.WEBSITE_API_URL), this.storage.getString(StorageKey.WEBSITE_URL), this.storage.getString(StorageKey.RETAIL_DOMAIN), this.storage.getString(StorageKey.RETAIL_URL), this.storage.getString(StorageKey.AWS_REGION), this.storage.getString(StorageKey.SKILLS_STORE_URL));
        }
        return null;
    }

    public void put(@NonNull Endpoints endpoints) {
        String str = "Saving endpoints to persistent storage: " + endpoints;
        PersistentStorage.Transaction transaction = this.storage.edit().set(StorageKey.SCHEMA_VERSION, "4");
        transaction.set(StorageKey.ALEXA_API_URL, endpoints.getAlexaApiUrl()).set(StorageKey.WEBSITE_API_URL, endpoints.getWebsiteApiUrl()).set(StorageKey.WEBSITE_URL, endpoints.getWebsiteUrl()).set(StorageKey.RETAIL_DOMAIN, endpoints.getRetailDomain()).set(StorageKey.RETAIL_URL, endpoints.getRetailUrl()).set(StorageKey.AWS_REGION, endpoints.getAwsRegion()).set(StorageKey.SKILLS_STORE_URL, endpoints.getSkillsStoreUrl());
        transaction.commit();
    }
}
