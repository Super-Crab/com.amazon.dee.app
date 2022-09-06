package com.amazon.communication;
/* loaded from: classes12.dex */
public interface NetworkStabilityMonitor {

    /* loaded from: classes12.dex */
    public enum NetworkStabilityState {
        UNKNOWN,
        STABLE
    }

    void addListener(NetworkStabilityStateChangeListener networkStabilityStateChangeListener);

    void removeListener(NetworkStabilityStateChangeListener networkStabilityStateChangeListener);

    void start();

    void stop();
}
