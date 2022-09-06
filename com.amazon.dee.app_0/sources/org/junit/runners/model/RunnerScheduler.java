package org.junit.runners.model;
/* loaded from: classes5.dex */
public interface RunnerScheduler {
    void finished();

    void schedule(Runnable runnable);
}
