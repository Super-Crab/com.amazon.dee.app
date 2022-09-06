package com.here.sdk.engine;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.here.sdk.core.engine.AuthenticationPreferences;
import com.here.sdk.core.engine.SDKOptions;
/* loaded from: classes3.dex */
public class OptionsReader {
    public static final String accessKeyIdName = "com.here.sdk.access_key_id";
    public static final String accessKeySecretName = "com.here.sdk.access_key_secret";
    public static final String authenticationPreferencesName = "com.here.sdk.authentication_preferences";
    public static final String cachePathKeyName = "com.here.sdk.cache_path";
    public static final String cacheSizeKeyName = "com.here.sdk.cache_size_in_bytes";
    public static final String persistentMapStoragePathKeyName = "com.here.sdk.persistent_map_storage_path";

    /* loaded from: classes3.dex */
    public static class MetaDataNotFoundException extends Exception {
        public MetaDataNotFoundException(Exception exc) {
            super(exc);
        }

        public MetaDataNotFoundException(String str) {
            super(str);
        }
    }

    @Nullable
    private Bundle getMetaData(@NonNull Context context) throws MetaDataNotFoundException, PackageManager.NameNotFoundException {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException e) {
            throw e;
        } catch (Exception e2) {
            throw new MetaDataNotFoundException(e2);
        }
    }

    @NonNull
    private String getStringOption(@NonNull Bundle bundle, @NonNull String str) throws MetaDataNotFoundException {
        String string = bundle.getString(str);
        if (string == null || string.isEmpty()) {
            throw new MetaDataNotFoundException(GeneratedOutlineSupport1.outline75("Key [", str, "] not found in manifest metadata."));
        }
        return string;
    }

    private void readAuthenticationPreferences(Bundle bundle, SDKOptions sDKOptions) {
        AuthenticationPreferences authenticationPreferences;
        try {
            authenticationPreferences = AuthenticationPreferences.valueOf(getStringOption(bundle, authenticationPreferencesName));
        } catch (MetaDataNotFoundException unused) {
            authenticationPreferences = AuthenticationPreferences.USE_SYSTEM_TIME;
        }
        sDKOptions.authenticationPreferences = authenticationPreferences;
    }

    private void readCachePath(Bundle bundle, SDKOptions sDKOptions) {
        String string = bundle.getString(cachePathKeyName);
        if (string == null || string.isEmpty()) {
            return;
        }
        sDKOptions.cachePath = string;
    }

    private void readCacheSize(Bundle bundle, SDKOptions sDKOptions) {
        long j = bundle.getInt(cacheSizeKeyName);
        if (j != 0) {
            sDKOptions.cacheSizeInBytes = j;
        }
    }

    private void readPersistentMapStoragePath(Bundle bundle, SDKOptions sDKOptions) {
        String string = bundle.getString(persistentMapStoragePathKeyName);
        if (string == null || string.isEmpty()) {
            return;
        }
        sDKOptions.persistentMapStoragePath = string;
    }

    public SDKOptions getOptionsFromManifestFile(Context context) throws MetaDataNotFoundException {
        try {
            Bundle metaData = getMetaData(context);
            if (metaData == null) {
                throw new MetaDataNotFoundException("No metadata found");
            }
            SDKOptions sDKOptions = new SDKOptions(getStringOption(metaData, accessKeyIdName), getStringOption(metaData, accessKeySecretName));
            readAuthenticationPreferences(metaData, sDKOptions);
            readCachePath(metaData, sDKOptions);
            readCacheSize(metaData, sDKOptions);
            readPersistentMapStoragePath(metaData, sDKOptions);
            return sDKOptions;
        } catch (MetaDataNotFoundException e) {
            throw e;
        } catch (Exception e2) {
            throw new MetaDataNotFoundException(e2);
        }
    }
}
