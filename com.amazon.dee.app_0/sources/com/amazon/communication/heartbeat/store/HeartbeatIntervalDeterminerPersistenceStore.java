package com.amazon.communication.heartbeat.store;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.IOException;
/* loaded from: classes12.dex */
public class HeartbeatIntervalDeterminerPersistenceStore implements HeartbeatIntervalDeterminerStore {
    public static final String STORE_NAME = "com.amazon.communication.heartbeat";
    private final SharedPreferences mHeartbeatIntervalDeterminerStateStore;

    public HeartbeatIntervalDeterminerPersistenceStore(Context context) {
        this.mHeartbeatIntervalDeterminerStateStore = context.getSharedPreferences(STORE_NAME, 0);
    }

    @Override // com.amazon.communication.heartbeat.store.HeartbeatIntervalDeterminerStore
    public HeartbeatIntervalDeterminerState retrieve(String str) {
        if (str != null) {
            try {
                String string = this.mHeartbeatIntervalDeterminerStateStore.getString(str, null);
                if (string != null) {
                    return HeartbeatIntervalDeterminerState.deserialize(string);
                }
                return null;
            } catch (IOException unused) {
                return null;
            }
        }
        throw new IllegalArgumentException("key must not be null");
    }

    @Override // com.amazon.communication.heartbeat.store.HeartbeatIntervalDeterminerStore
    public void store(String str, HeartbeatIntervalDeterminerState heartbeatIntervalDeterminerState) {
        if (str != null) {
            if (heartbeatIntervalDeterminerState != null) {
                this.mHeartbeatIntervalDeterminerStateStore.edit().putString(str, heartbeatIntervalDeterminerState.serialize()).apply();
                return;
            }
            throw new IllegalArgumentException("state must not be null");
        }
        throw new IllegalArgumentException("key must not be null");
    }
}
