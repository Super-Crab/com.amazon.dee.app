package com.amazon.communication.heartbeat;

import com.amazon.communication.NetworkType;
import com.amazon.communication.heartbeat.HeartbeatControlMessage;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class TriggerLearningCommand extends HeartbeatControlMessage {
    private final long mLowerBoundMillis;
    private final String mNetworkIdentifier;
    private final NetworkType mNetworkType;
    private final long mUpperBoundMillis;

    public TriggerLearningCommand(NetworkType networkType, String str, long j, long j2) {
        this.mNetworkType = networkType;
        this.mNetworkIdentifier = str;
        this.mLowerBoundMillis = j;
        this.mUpperBoundMillis = j2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != TriggerLearningCommand.class) {
            return false;
        }
        TriggerLearningCommand triggerLearningCommand = (TriggerLearningCommand) obj;
        return getNetworkType().equals(triggerLearningCommand.getNetworkType()) && getNetworkIdentifier().equals(triggerLearningCommand.getNetworkIdentifier()) && getLowerBoundMillis() == triggerLearningCommand.getLowerBoundMillis() && getUpperBoundMillis() == triggerLearningCommand.getUpperBoundMillis();
    }

    public long getLowerBoundMillis() {
        return this.mLowerBoundMillis;
    }

    public String getNetworkIdentifier() {
        return this.mNetworkIdentifier;
    }

    public NetworkType getNetworkType() {
        return this.mNetworkType;
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatControlMessage
    public HeartbeatControlMessage.Type getType() {
        return HeartbeatControlMessage.Type.TriggerLearning;
    }

    public long getUpperBoundMillis() {
        return this.mUpperBoundMillis;
    }

    public int hashCode() {
        int hashCode = getNetworkIdentifier().hashCode() * 11;
        long lowerBoundMillis = getLowerBoundMillis() * 7;
        return (int) ((getUpperBoundMillis() * 5) + ((int) (lowerBoundMillis + hashCode + (getNetworkType().hashCode() * 13) + 37)));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getType());
        sb.append(" on n/w: ");
        sb.append(this.mNetworkType);
        sb.append(", id: ");
        sb.append(this.mNetworkIdentifier);
        sb.append(", limits (ms): (");
        sb.append(this.mLowerBoundMillis);
        sb.append(", ");
        return GeneratedOutlineSupport1.outline87(sb, this.mUpperBoundMillis, ")");
    }
}
