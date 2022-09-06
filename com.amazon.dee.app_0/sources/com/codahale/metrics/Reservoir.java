package com.codahale.metrics;
/* loaded from: classes9.dex */
public interface Reservoir {
    Snapshot getSnapshot();

    int size();

    void update(long j);
}
