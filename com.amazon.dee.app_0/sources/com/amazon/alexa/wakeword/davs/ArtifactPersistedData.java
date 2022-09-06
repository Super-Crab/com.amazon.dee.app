package com.amazon.alexa.wakeword.davs;

import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.alexa.wakeword.davs.AutoValue_ArtifactPersistedData;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes11.dex */
public abstract class ArtifactPersistedData {
    private static final String KEY_ARTIFACT_DOWNLOADED_TIME = "artifact_downloaded_time";
    private static final String KEY_ARTIFACT_IDENTIFIER = "artifact_identifier";
    private static final String KEY_LAST_USED_ENGINE_COMPAT_ID = "engine_compatibility_id";
    private static final String KEY_LAST_USED_LOCALE = "last_used_locale";

    @AutoValue.Builder
    /* loaded from: classes11.dex */
    public static abstract class Builder {
        public abstract ArtifactPersistedData build();

        public abstract Builder setArtifactIdentifier(@Nullable String str);

        public abstract Builder setDownloadTime(long j);

        public abstract Builder setEngineCompatibilityId(@Nullable String str);

        public abstract Builder setLocale(@Nullable String str);
    }

    public static Builder builder() {
        return new AutoValue_ArtifactPersistedData.Builder();
    }

    public static ArtifactPersistedData fromSharedPreferences(SharedPreferences sharedPreferences) {
        return builder().setArtifactIdentifier(getPersistedArtifactIdentifier(sharedPreferences)).setLocale(getPersistedLocale(sharedPreferences)).setEngineCompatibilityId(getPersistedEngineCompatibilityId(sharedPreferences)).setDownloadTime(getPersistedDownloadTime(sharedPreferences).longValue()).build();
    }

    public static String getPersistedArtifactIdentifier(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("artifact_identifier", "");
    }

    public static Long getPersistedDownloadTime(SharedPreferences sharedPreferences) {
        return Long.valueOf(sharedPreferences.getLong("artifact_downloaded_time", 0L));
    }

    public static String getPersistedEngineCompatibilityId(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("engine_compatibility_id", "");
    }

    public static String getPersistedLocale(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("last_used_locale", "");
    }

    @Nullable
    public abstract String getArtifactIdentifier();

    public abstract long getDownloadTime();

    @Nullable
    public abstract String getEngineCompatibilityId();

    @Nullable
    public abstract String getLocale();

    public void persistArtifactSharedPreferences(SharedPreferences sharedPreferences) {
        String artifactIdentifier = getArtifactIdentifier();
        String locale = getLocale();
        String engineCompatibilityId = getEngineCompatibilityId();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        if (!TextUtils.isEmpty(artifactIdentifier)) {
            edit.putString("artifact_identifier", artifactIdentifier);
        }
        if (!TextUtils.isEmpty(locale)) {
            edit.putString("last_used_locale", locale);
        }
        if (!TextUtils.isEmpty(engineCompatibilityId)) {
            edit.putString("engine_compatibility_id", engineCompatibilityId);
        }
        edit.putLong("artifact_downloaded_time", getDownloadTime());
        edit.commit();
    }
}
