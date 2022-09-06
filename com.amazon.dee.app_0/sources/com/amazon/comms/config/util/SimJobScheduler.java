package com.amazon.comms.config.util;
/* loaded from: classes11.dex */
public interface SimJobScheduler {

    /* loaded from: classes11.dex */
    public enum ForegroundPurpose {
        IncomingCallRing,
        OutgoingCallRing,
        ActiveCallAudio
    }

    /* loaded from: classes11.dex */
    public interface Listener {
        void onForegroundState(ForegroundPurpose foregroundPurpose);
    }

    void finish();

    void registerListener(Listener listener);

    void scheduleCommunicationsJob(ForegroundPurpose foregroundPurpose);

    void scheduleExclusiveContentJob(ForegroundPurpose foregroundPurpose);

    void unregisterListener(Listener listener);
}
