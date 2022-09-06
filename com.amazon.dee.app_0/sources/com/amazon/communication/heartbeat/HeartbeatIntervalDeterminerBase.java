package com.amazon.communication.heartbeat;

import amazon.speech.simclient.settings.Settings;
import com.amazon.communication.NetworkType;
import com.amazon.communication.heartbeat.HeartbeatIntervalUpdatesListener;
import com.amazon.dp.logger.DPLogger;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public abstract class HeartbeatIntervalDeterminerBase implements HeartbeatIntervalDeterminer {
    private static final DPLogger log = new DPLogger("TComm.HeartbeatIntervalDeterminerBase");
    protected final List<HeartbeatIntervalUpdatesListener> mIntervalUpdatesListeners = new CopyOnWriteArrayList();

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public void addHeartbeatIntervalUpdatesListener(HeartbeatIntervalUpdatesListener heartbeatIntervalUpdatesListener) {
        this.mIntervalUpdatesListeners.add(heartbeatIntervalUpdatesListener);
        log.verbose("addHeartbeatIntervalUpdatesListener", "added listener for heartbeat interval updates", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, heartbeatIntervalUpdatesListener);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public void forceLearningMode(TriggerLearningCommand triggerLearningCommand) {
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public boolean hasLearntHeartbeatInterval() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyIntervalChange(NetworkType networkType, long j, long j2) {
        log.debug("notifyIntervalChange", "notifying listeners about change in heartbeat interval", "networkType", networkType, "oldInterval", Long.valueOf(j), "newInterval", Long.valueOf(j2), "mIntervalUpdatesListeners.size()", Integer.valueOf(this.mIntervalUpdatesListeners.size()));
        for (HeartbeatIntervalUpdatesListener heartbeatIntervalUpdatesListener : this.mIntervalUpdatesListeners) {
            heartbeatIntervalUpdatesListener.changedHeartbeatInterval(networkType, j, j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyLearningModeChange(NetworkType networkType, boolean z, HeartbeatIntervalUpdatesListener.SwitchingReason switchingReason, long j) {
        log.debug("notifyLearningModeChange", "notifying listeners about change in learning mode", "networkType", networkType, "isLearning", Boolean.valueOf(z), Settings.ListeningSettings.EXTRA_REASON, switchingReason, "currentIntervalMillis", Long.valueOf(j), "mIntervalUpdatesListeners.size()", Integer.valueOf(this.mIntervalUpdatesListeners.size()));
        for (HeartbeatIntervalUpdatesListener heartbeatIntervalUpdatesListener : this.mIntervalUpdatesListeners) {
            if (z) {
                heartbeatIntervalUpdatesListener.switchedToLearningMode(networkType, switchingReason);
            } else {
                heartbeatIntervalUpdatesListener.switchedToLearntMode(networkType, switchingReason, j);
            }
        }
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public void shutdown() {
    }
}
