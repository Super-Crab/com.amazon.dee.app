package com.amazon.communication.socket;

import com.amazon.communication.time.GlobalTimeSource;
/* loaded from: classes12.dex */
public final class ProtocolSocketStats {
    private long mTimeStartConnection;
    private long mTimeLastMessageSent = 0;
    private long mTimeLastMessageReceived = 0;
    private long mTimeEstablished = 0;

    public synchronized long getTimeEstablished() {
        return this.mTimeEstablished;
    }

    public synchronized long getTimeLastMessageReceived() {
        return this.mTimeLastMessageReceived;
    }

    public synchronized long getTimeLastMessageSent() {
        return this.mTimeLastMessageSent;
    }

    public synchronized long getTimeStartConnection() {
        return this.mTimeStartConnection;
    }

    public synchronized void recordTimeEstablished() {
        this.mTimeEstablished = GlobalTimeSource.INSTANCE.currentTimeMillis();
    }

    public synchronized void recordTimeLastMessageReceived() {
        this.mTimeLastMessageReceived = GlobalTimeSource.INSTANCE.currentTimeMillis();
    }

    public synchronized void recordTimeLastMessageSent() {
        this.mTimeLastMessageSent = GlobalTimeSource.INSTANCE.currentTimeMillis();
    }

    public synchronized void recordTimeStartConnection() {
        this.mTimeStartConnection = GlobalTimeSource.INSTANCE.currentTimeMillis();
    }
}
