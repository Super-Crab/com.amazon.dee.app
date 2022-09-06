package com.amazon.communication.heartbeat.store;
/* loaded from: classes12.dex */
public interface HeartbeatIntervalDeterminerStore {
    HeartbeatIntervalDeterminerState retrieve(String str);

    void store(String str, HeartbeatIntervalDeterminerState heartbeatIntervalDeterminerState);
}
