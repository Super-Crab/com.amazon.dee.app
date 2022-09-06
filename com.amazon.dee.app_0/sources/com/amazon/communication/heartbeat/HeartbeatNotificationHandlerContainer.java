package com.amazon.communication.heartbeat;

import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
public class HeartbeatNotificationHandlerContainer {
    private static final DPLogger log = new DPLogger("TComm.HeartbeatNotificationHandlerContainer");
    private static HeartbeatNotificationHandlerContainer sInstance;
    private HeartbeatNotificationHandler sHandler;

    private HeartbeatNotificationHandlerContainer() {
    }

    public static synchronized HeartbeatNotificationHandlerContainer getInstance() {
        HeartbeatNotificationHandlerContainer heartbeatNotificationHandlerContainer;
        synchronized (HeartbeatNotificationHandlerContainer.class) {
            if (sInstance == null) {
                log.verbose("HeartbeatNotificationHandlerContainer", "Instance created", new Object[0]);
                sInstance = new HeartbeatNotificationHandlerContainer();
            } else {
                log.verbose("HeartbeatNotificationHandlerContainer", "Instance reused", new Object[0]);
            }
            heartbeatNotificationHandlerContainer = sInstance;
        }
        return heartbeatNotificationHandlerContainer;
    }

    public synchronized HeartbeatNotificationHandler getHeartbeatNotificationHandler() {
        return this.sHandler;
    }

    public synchronized void setHeartbeatNotificationHandler(HeartbeatNotificationHandler heartbeatNotificationHandler) {
        log.verbose("setHeartbeatNotificationHandler", "setting handler", "handler", heartbeatNotificationHandler);
        if (this.sHandler != null) {
            log.warn("setHeartbeatNotificationHandler", "Called when old instance existed. This should only happen when the CommunicationService has been restarted", new Object[0]);
        }
        this.sHandler = heartbeatNotificationHandler;
    }
}
