package com.amazon.deecomms.accessories.monitors;
/* loaded from: classes12.dex */
public enum CallNotificationStatus {
    NO_ACTIVITY(1),
    MISSED_CALLS(2),
    ACTIVE_CALL(4),
    OUTBOUND_CALL(8),
    INCOMING_CALL(16);
    
    private final int callNotificationStatus;

    CallNotificationStatus(int i) {
        this.callNotificationStatus = i;
    }

    public int getCallNotificationStatus() {
        return this.callNotificationStatus;
    }
}
