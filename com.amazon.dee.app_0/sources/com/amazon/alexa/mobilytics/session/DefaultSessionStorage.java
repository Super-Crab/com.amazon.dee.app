package com.amazon.alexa.mobilytics.session;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazon.alexa.mobilytics.storage.PersistentStorage;
import com.amazon.alexa.mobilytics.util.Log;
import com.google.common.base.Preconditions;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class DefaultSessionStorage implements SessionStorage {
    private static final String TAG = Log.tag(DefaultSessionStorage.class);
    private final PersistentStorage persistentStorage;
    private final String sessionIdPrefix;

    /* loaded from: classes9.dex */
    private static final class StorageKey {
        private static final String SESSION_START_TIME = "SessionStartTime";
        private static final String SESSION_STATE = "SessionState";
        private static final String SESSION_STOP_TIME = "SessionStopTime";

        private StorageKey() {
        }
    }

    @Inject
    public DefaultSessionStorage(@NonNull PersistentStorage.Factory factory, @NonNull @Named("InstallationId") String str) {
        this.persistentStorage = ((PersistentStorage.Factory) Preconditions.checkNotNull(factory)).create(Constants.MOBILYTICS_SESSION_STORAGE);
        this.sessionIdPrefix = str;
    }

    @Override // com.amazon.alexa.mobilytics.session.SessionStorage
    public void clear() {
        Log.enter();
        try {
            this.persistentStorage.edit().remove("SessionState").remove("SessionStartTime").remove("SessionStopTime").commit();
        } catch (Exception e) {
            Log.e(TAG, e, "Error clearing session", new Object[0]);
        }
        Log.leave();
    }

    @Override // com.amazon.alexa.mobilytics.session.SessionStorage
    @Nullable
    public Session get() {
        Log.enter();
        if (!this.persistentStorage.contains("SessionState")) {
            Log.i(TAG, "No session in store.");
            Log.leave();
            return null;
        }
        Session session = new Session(this.sessionIdPrefix, this.persistentStorage.getLong("SessionStartTime", 0L), this.persistentStorage.getLong("SessionStopTime", 0L), (int) this.persistentStorage.getLong("SessionState", 0L));
        Log.i(TAG, "[%s] Restoring session ...", session.id());
        Log.leave();
        return session;
    }

    @Override // com.amazon.alexa.mobilytics.session.SessionStorage
    public void put(@Nullable Session session) {
        Log.enter();
        if (session != null) {
            try {
                Log.i(TAG, "[%s] Persisting session ...", session.id());
                this.persistentStorage.edit().set("SessionState", session.state()).set("SessionStartTime", session.startTime()).set("SessionStopTime", session.stopTime()).commit();
            } catch (Exception e) {
                Log.e(TAG, e, "Error storing state information", new Object[0]);
            }
        }
        Log.leave();
    }
}
