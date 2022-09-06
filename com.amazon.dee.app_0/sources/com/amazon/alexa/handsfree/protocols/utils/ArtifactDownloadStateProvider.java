package com.amazon.alexa.handsfree.protocols.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes8.dex */
public class ArtifactDownloadStateProvider {
    @VisibleForTesting
    static final String SHARED_PREFERENCE_CURRENT_STARTER_KEY = "AMPD_%s_CURRENT_ARTIFACT_STARTER";
    @VisibleForTesting
    static final String SHARED_PREFERENCE_CURRENT_STATE_KEY = "AMPD_%s_CURRENT_ARTIFACT_STATE";
    @VisibleForTesting
    static final String SHARED_PREFERENCE_FILE = "AMPD_ARTIFACT_DOWNLOAD_STATE_PROVIDER";
    @VisibleForTesting
    static final String SHARED_PREFERENCE_PREVIOUS_STARTER_KEY = "AMPD_%s_PREVIOUS_ARTIFACT_STARTER";
    @VisibleForTesting
    static final String SHARED_PREFERENCE_PREVIOUS_STATE_KEY = "AMPD_%s_PREVIOUS_ARTIFACT_STATE";
    private static final String TAG = "ArtifactDownloadStateProvider";
    private final SharedPreferences mSharedPreferences;

    /* loaded from: classes8.dex */
    public enum ArtifactType {
        WAKE_WORD_MODEL,
        SV_MODEL
    }

    /* loaded from: classes8.dex */
    public enum DownloadStarter {
        SIGN_IN,
        LANGUAGE_SELECTOR,
        UNKNOWN
    }

    /* loaded from: classes8.dex */
    public enum DownloadState {
        IN_PROGRESS,
        SUCCESS,
        FAILURE,
        UNKNOWN
    }

    public ArtifactDownloadStateProvider(@NonNull Context context) {
        this.mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE, 0);
    }

    public DownloadStarter getDownloadStarter(@NonNull ArtifactType artifactType) {
        String format = String.format(SHARED_PREFERENCE_CURRENT_STARTER_KEY, artifactType);
        String string = this.mSharedPreferences.getString(format, DownloadStarter.UNKNOWN.name());
        Log.d(TAG, String.format("Artifact %s started by: %s", format, string));
        try {
            return DownloadStarter.valueOf(string);
        } catch (IllegalArgumentException unused) {
            return DownloadStarter.UNKNOWN;
        }
    }

    public DownloadState getDownloadState(@NonNull ArtifactType artifactType) {
        String format = String.format(SHARED_PREFERENCE_CURRENT_STATE_KEY, artifactType);
        String string = this.mSharedPreferences.getString(format, DownloadState.UNKNOWN.name());
        Log.d(TAG, String.format("Is Artifact %s Download Ready: %s", format, string));
        try {
            return DownloadState.valueOf(string);
        } catch (IllegalArgumentException unused) {
            return DownloadState.UNKNOWN;
        }
    }

    public void reportArtifactUpToDate(@NonNull ArtifactType artifactType) {
        String format = String.format(SHARED_PREFERENCE_CURRENT_STARTER_KEY, artifactType);
        String format2 = String.format(SHARED_PREFERENCE_CURRENT_STATE_KEY, artifactType);
        String format3 = String.format(SHARED_PREFERENCE_PREVIOUS_STATE_KEY, artifactType);
        String format4 = String.format(SHARED_PREFERENCE_PREVIOUS_STARTER_KEY, artifactType);
        String string = this.mSharedPreferences.getString(format3, DownloadState.UNKNOWN.name());
        String string2 = this.mSharedPreferences.getString(format4, DownloadStarter.UNKNOWN.name());
        Log.d(TAG, String.format("Already up to date. Retrieved previous state %s started from %s for %s", string, string2, artifactType));
        this.mSharedPreferences.edit().putString(format, string2).putString(format2, string).apply();
    }

    public void reportDownloadStarted(@NonNull ArtifactType artifactType, @NonNull DownloadStarter downloadStarter) {
        String format = String.format(SHARED_PREFERENCE_CURRENT_STARTER_KEY, artifactType);
        String format2 = String.format(SHARED_PREFERENCE_CURRENT_STATE_KEY, artifactType);
        String format3 = String.format(SHARED_PREFERENCE_PREVIOUS_STATE_KEY, artifactType);
        String format4 = String.format(SHARED_PREFERENCE_PREVIOUS_STARTER_KEY, artifactType);
        String string = this.mSharedPreferences.getString(format2, DownloadState.UNKNOWN.name());
        String string2 = this.mSharedPreferences.getString(format, DownloadStarter.UNKNOWN.name());
        Log.d(TAG, String.format("Download for %s started from %s", artifactType, downloadStarter));
        this.mSharedPreferences.edit().putString(format3, string).putString(format4, string2).putString(format, downloadStarter.name()).putString(format2, DownloadState.IN_PROGRESS.name()).apply();
    }

    public void updateDownloadState(@NonNull ArtifactType artifactType, @NonNull DownloadState downloadState) {
        String format = String.format(SHARED_PREFERENCE_CURRENT_STATE_KEY, artifactType);
        Log.d(TAG, String.format("Update for %s download. Status updated to: %s", artifactType, downloadState));
        this.mSharedPreferences.edit().putString(format, downloadState.name()).apply();
    }
}
