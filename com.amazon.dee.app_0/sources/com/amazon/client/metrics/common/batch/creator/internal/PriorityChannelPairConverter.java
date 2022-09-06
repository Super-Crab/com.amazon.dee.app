package com.amazon.client.metrics.common.batch.creator.internal;

import android.util.Log;
import com.amazon.client.metrics.thirdparty.Channel;
import com.amazon.client.metrics.thirdparty.Priority;
import com.amazon.client.metrics.thirdparty.batch.creator.PriorityChannelPair;
/* loaded from: classes11.dex */
public class PriorityChannelPairConverter {
    public static PriorityChannelPair convertCommonToThirdParty(com.amazon.client.metrics.common.batch.creator.PriorityChannelPair priorityChannelPair) {
        if (priorityChannelPair == null) {
            Log.i("convertCommonToThird", "Returning null for null Common PriorityChannelPair input");
            return null;
        }
        return new PriorityChannelPair(Priority.valueOf(priorityChannelPair.getPriority().name()), Channel.valueOf(priorityChannelPair.getChannel().name()));
    }

    public static com.amazon.client.metrics.common.batch.creator.PriorityChannelPair convertThirdPartyToCommon(PriorityChannelPair priorityChannelPair) {
        if (priorityChannelPair == null) {
            Log.i("convertThirdPartyToComm", "Returning null for null ThirdParty PriorityChannelPair input");
            return null;
        }
        return new com.amazon.client.metrics.common.batch.creator.PriorityChannelPair(com.amazon.client.metrics.common.Priority.valueOf(priorityChannelPair.getPriority().name()), com.amazon.client.metrics.common.Channel.valueOf(priorityChannelPair.getChannel().name()));
    }
}
