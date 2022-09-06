package com.amazon.alexa.mobilytics.configuration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.internal.JsonConverter;
import com.amazon.alexa.mobilytics.storage.PersistentStorage;
import com.amazon.alexa.mobilytics.util.Log;
import com.google.common.base.Preconditions;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class PersistentConfigStorage implements ConfigStorage {
    private static final String CONFIG_KEY = "config";
    private static final String LAST_UPDATED_TIME = "lastUpdated";
    private static final String TAG = Log.tag(PersistentConfigStorage.class);
    private final JsonConverter converter;
    private final PersistentStorage persistentStorage;

    @Inject
    public PersistentConfigStorage(@NonNull PersistentStorage.Factory factory, @NonNull JsonConverter jsonConverter) {
        this.persistentStorage = ((PersistentStorage.Factory) Preconditions.checkNotNull(factory)).create("mobilytics.config-storage");
        this.converter = (JsonConverter) Preconditions.checkNotNull(jsonConverter);
    }

    @Override // com.amazon.alexa.mobilytics.configuration.ConfigStorage
    public void clear() {
        Log.enter();
        try {
            this.persistentStorage.edit().remove(CONFIG_KEY).remove(LAST_UPDATED_TIME).commit();
        } catch (Exception e) {
            Log.e(TAG, e, "Error clearing config", new Object[0]);
        }
        Log.leave();
    }

    @Override // com.amazon.alexa.mobilytics.configuration.ConfigStorage
    @Nullable
    public Config content() {
        String string;
        if (this.persistentStorage.contains(CONFIG_KEY) && (string = this.persistentStorage.getString(CONFIG_KEY)) != null) {
            return (Config) this.converter.fromJson(string, Config.class);
        }
        return null;
    }

    @Override // com.amazon.alexa.mobilytics.configuration.ConfigStorage
    @Nullable
    public String lastUpdatedTime() {
        if (!this.persistentStorage.contains(LAST_UPDATED_TIME)) {
            return null;
        }
        return this.persistentStorage.getString(LAST_UPDATED_TIME);
    }

    @Override // com.amazon.alexa.mobilytics.configuration.ConfigStorage
    public void storeContent(@NonNull Config config) {
        Log.enter();
        this.persistentStorage.edit().set(CONFIG_KEY, this.converter.toJson(config)).commit();
        Log.leave();
    }

    @Override // com.amazon.alexa.mobilytics.configuration.ConfigStorage
    public void storeUpdatedTime(@NonNull String str) {
        Log.enter();
        this.persistentStorage.edit().set(LAST_UPDATED_TIME, str).commit();
        Log.leave();
    }
}
