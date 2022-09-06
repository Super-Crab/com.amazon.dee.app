package com.amazon.client.metrics.thirdparty.batch.creator;

import com.amazon.client.metrics.thirdparty.Channel;
import com.amazon.client.metrics.thirdparty.Priority;
/* loaded from: classes11.dex */
public class PriorityChannelPair {
    final Channel mChannel;
    final Priority mPriority;

    public PriorityChannelPair(Priority priority, Channel channel) {
        this.mPriority = priority;
        this.mChannel = channel;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PriorityChannelPair.class != obj.getClass()) {
            return false;
        }
        PriorityChannelPair priorityChannelPair = (PriorityChannelPair) obj;
        return this.mChannel == priorityChannelPair.mChannel && this.mPriority == priorityChannelPair.mPriority;
    }

    public Channel getChannel() {
        return this.mChannel;
    }

    public Priority getPriority() {
        return this.mPriority;
    }

    public int hashCode() {
        Channel channel = this.mChannel;
        int i = 0;
        int hashCode = ((channel == null ? 0 : channel.hashCode()) + 31) * 31;
        Priority priority = this.mPriority;
        if (priority != null) {
            i = priority.hashCode();
        }
        return hashCode + i;
    }
}
