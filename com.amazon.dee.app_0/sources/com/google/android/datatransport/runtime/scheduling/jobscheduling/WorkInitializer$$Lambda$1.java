package com.google.android.datatransport.runtime.scheduling.jobscheduling;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
final /* synthetic */ class WorkInitializer$$Lambda$1 implements Runnable {
    private final WorkInitializer arg$1;

    private WorkInitializer$$Lambda$1(WorkInitializer workInitializer) {
        this.arg$1 = workInitializer;
    }

    public static Runnable lambdaFactory$(WorkInitializer workInitializer) {
        return new WorkInitializer$$Lambda$1(workInitializer);
    }

    @Override // java.lang.Runnable
    public void run() {
        WorkInitializer.lambda$ensureContextsScheduled$1(this.arg$1);
    }
}