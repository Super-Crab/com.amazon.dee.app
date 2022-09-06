package com.amazon.latencyinfra;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes12.dex */
public class LatencySnapshot {
    private final long duration;
    private final String name;
    private long timeFromLastSnapshot;
    private final long timeFromOrigin;

    public LatencySnapshot(String str, long j, long j2) {
        this.name = str;
        this.duration = j;
        this.timeFromOrigin = j2;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getName() {
        return this.name;
    }

    public long getTimeFromLastSnapshot() {
        return this.timeFromLastSnapshot;
    }

    public long getTimeFromOrigin() {
        return this.timeFromOrigin;
    }

    public void setTimeFromLastSnapshot(long j) {
        this.timeFromLastSnapshot = j;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LatencySnapshot{name='");
        GeneratedOutlineSupport1.outline176(outline107, this.name, Chars.QUOTE, ", duration=");
        outline107.append(this.duration);
        outline107.append(", timeFromOrigin=");
        outline107.append(this.timeFromOrigin);
        outline107.append(", timeFromLastSnapshot=");
        outline107.append(this.timeFromLastSnapshot);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
