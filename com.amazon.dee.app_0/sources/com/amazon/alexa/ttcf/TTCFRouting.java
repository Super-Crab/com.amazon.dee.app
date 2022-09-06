package com.amazon.alexa.ttcf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.ttcf.api.TTCFRoute;
/* compiled from: TTCFService.java */
/* loaded from: classes10.dex */
class TTCFRouting implements TTCFTimedRoute {
    @Nullable
    private Long endTime;
    @NonNull
    private final TTCFRoute route;
    private final long startTime;
    private boolean valid = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TTCFRouting(@NonNull TTCFRoute tTCFRoute, long j) {
        this.route = tTCFRoute;
        this.startTime = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void finishAtTime(long j) {
        if (this.endTime == null) {
            this.endTime = Long.valueOf(j);
        }
    }

    @Override // com.amazon.alexa.ttcf.TTCFTimedRoute
    @Nullable
    public Long getEndTime() {
        return this.endTime;
    }

    @Override // com.amazon.alexa.ttcf.TTCFTimedRoute
    @NonNull
    public TTCFRoute getRoute() {
        return this.route;
    }

    @Override // com.amazon.alexa.ttcf.TTCFTimedRoute
    public long getStartTime() {
        return this.startTime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void invalidate() {
        this.valid = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isValid() {
        return this.valid;
    }
}
