package com.amazon.alexa.ttcf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
public final class TTCFRecord {
    private final boolean isInitial;
    private final String name;
    private final long readyTime;
    private final List<TTCFTimedRoute> routings;

    private TTCFRecord(@NonNull String str, @NonNull List<? extends TTCFTimedRoute> list, long j, boolean z) {
        this.name = str;
        this.readyTime = j;
        this.routings = Collections.unmodifiableList(list);
        this.isInitial = z;
    }

    @Nullable
    public static TTCFRecord make(@NonNull String str, @NonNull List<? extends TTCFTimedRoute> list, long j, boolean z) {
        if (list.size() == 0) {
            return null;
        }
        return new TTCFRecord(str, list, j, z);
    }

    public String getName() {
        return this.name;
    }

    public long getReadyTime() {
        return this.readyTime;
    }

    @Nullable
    public Long getRoutingEndTime() {
        return ((TTCFTimedRoute) GeneratedOutlineSupport1.outline24(this.routings, -1)).getEndTime();
    }

    @NonNull
    public List<TTCFTimedRoute> getRoutings() {
        return this.routings;
    }

    public long getStartTime() {
        return this.routings.get(0).getStartTime();
    }

    public boolean isInitial() {
        return this.isInitial;
    }
}
