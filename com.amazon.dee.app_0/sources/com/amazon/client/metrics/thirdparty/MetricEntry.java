package com.amazon.client.metrics.thirdparty;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
/* loaded from: classes11.dex */
public class MetricEntry {
    private final List<DataPoint> mDatapoints;
    private final String mProgram;
    private final String mSource;
    private final long mTimestamp;

    public MetricEntry(long j, String str, String str2, List<DataPoint> list) {
        if (str != null && !str.isEmpty()) {
            if (str2 == null || str2.isEmpty()) {
                throw new IllegalArgumentException("source cannot be empty or null");
            }
            if (list != null) {
                this.mTimestamp = j;
                this.mProgram = str;
                this.mSource = str2;
                this.mDatapoints = list;
                return;
            }
            throw new IllegalArgumentException("data points cannot be null");
        }
        throw new IllegalArgumentException("program cannot be empty or null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MetricEntry.class != obj.getClass()) {
            return false;
        }
        MetricEntry metricEntry = (MetricEntry) obj;
        return this.mDatapoints.equals(metricEntry.mDatapoints) && this.mProgram.equals(metricEntry.mProgram) && this.mSource.equals(metricEntry.mSource) && this.mTimestamp == metricEntry.mTimestamp;
    }

    public List<DataPoint> getDatapoints() {
        return this.mDatapoints;
    }

    public String getProgram() {
        return this.mProgram;
    }

    public String getSource() {
        return this.mSource;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public int hashCode() {
        int outline7 = GeneratedOutlineSupport1.outline7(this.mSource, GeneratedOutlineSupport1.outline7(this.mProgram, (this.mDatapoints.hashCode() + 31) * 31, 31), 31);
        long j = this.mTimestamp;
        return outline7 + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mTimestamp);
        sb.append(" ");
        sb.append(this.mProgram);
        sb.append(" ");
        sb.append(this.mSource);
        sb.append(" ");
        for (int i = 0; i < this.mDatapoints.size(); i++) {
            sb.append(this.mDatapoints.get(i).toString());
        }
        return sb.toString();
    }
}
