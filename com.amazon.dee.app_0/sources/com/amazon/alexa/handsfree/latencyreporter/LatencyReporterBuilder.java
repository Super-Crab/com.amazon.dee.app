package com.amazon.alexa.handsfree.latencyreporter;

import android.content.Context;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes8.dex */
public class LatencyReporterBuilder {
    private final Context mContext;
    private final Set<LatencyTimestamp> mLatencyTimestampSet = new HashSet();

    public LatencyReporterBuilder(@NonNull Context context) {
        this.mContext = context;
    }

    public LatencyReporter build() {
        return new LatencyReporter(this.mContext, new ArrayList(this.mLatencyTimestampSet));
    }

    int getRecorderCount() {
        return this.mLatencyTimestampSet.size();
    }

    public void reset() {
        this.mLatencyTimestampSet.clear();
    }

    public LatencyReporterBuilder withTimestamp(@NonNull Latency latency, @NonNull TimestampType timestampType, long j) {
        return withTimestamp(latency, timestampType, null, j);
    }

    public LatencyReporterBuilder withTimestamp(@NonNull Latency latency, @NonNull TimestampType timestampType, @Nullable String str, long j) {
        LatencyTimestamp latencyTimestamp = new LatencyTimestamp(latency, timestampType, str, j);
        this.mLatencyTimestampSet.remove(latencyTimestamp);
        this.mLatencyTimestampSet.add(latencyTimestamp);
        return this;
    }
}
