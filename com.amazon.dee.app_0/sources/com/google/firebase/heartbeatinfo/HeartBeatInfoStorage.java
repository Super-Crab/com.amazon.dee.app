package com.google.firebase.heartbeatinfo;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
/* compiled from: com.google.firebase:firebase-common@@19.3.0 */
/* loaded from: classes3.dex */
class HeartBeatInfoStorage {
    private static final String GLOBAL = "fire-global";
    private static HeartBeatInfoStorage instance = null;
    private static final String preferencesName = "FirebaseAppHeartBeat";
    private final SharedPreferences sharedPreferences;

    private HeartBeatInfoStorage(Context context) {
        this.sharedPreferences = context.getSharedPreferences(preferencesName, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized HeartBeatInfoStorage getInstance(Context context) {
        HeartBeatInfoStorage heartBeatInfoStorage;
        synchronized (HeartBeatInfoStorage.class) {
            if (instance == null) {
                instance = new HeartBeatInfoStorage(context);
            }
            heartBeatInfoStorage = instance;
        }
        return heartBeatInfoStorage;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean shouldSendGlobalHeartBeat(long j) {
        return shouldSendSdkHeartBeat(GLOBAL, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean shouldSendSdkHeartBeat(String str, long j) {
        if (this.sharedPreferences.contains(str)) {
            if (j - this.sharedPreferences.getLong(str, -1L) < 86400000) {
                return false;
            }
            this.sharedPreferences.edit().putLong(str, j).apply();
            return true;
        }
        this.sharedPreferences.edit().putLong(str, j).apply();
        return true;
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    @VisibleForTesting
    HeartBeatInfoStorage(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }
}
