package com.amazon.client.metrics.common.batch.creator;

import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.Priority;
/* loaded from: classes11.dex */
public class PriorityChannelPair {
    private final Channel mChannel;
    private final Priority mPriority;

    public PriorityChannelPair(Priority priority, Channel channel) {
        if (priority != null) {
            if (channel != null) {
                this.mPriority = priority;
                this.mChannel = channel;
                return;
            }
            throw new NullPointerException("Channel may not be null");
        }
        throw new NullPointerException("Priority may not be null");
    }

    public Channel getChannel() {
        return this.mChannel;
    }

    public Priority getPriority() {
        return this.mPriority;
    }
}
