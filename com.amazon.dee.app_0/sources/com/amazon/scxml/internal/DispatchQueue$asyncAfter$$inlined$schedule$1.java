package com.amazon.scxml.internal;

import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
/* compiled from: Timer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004¸\u0006\u0000"}, d2 = {"kotlin/concurrent/TimersKt$timerTask$1", "Ljava/util/TimerTask;", "run", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DispatchQueue$asyncAfter$$inlined$schedule$1 extends TimerTask {
    final /* synthetic */ Function0 $completion$inlined;
    final /* synthetic */ DispatchQueue this$0;

    public DispatchQueue$asyncAfter$$inlined$schedule$1(DispatchQueue dispatchQueue, Function0 function0) {
        this.this$0 = dispatchQueue;
        this.$completion$inlined = function0;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        DispatchQueue.access$getQueueSemaphore$p(this.this$0).acquire();
        try {
            DispatchQueue.access$getWorkQueue$p(this.this$0).add(new DispatchQueue$asyncAfter$$inlined$schedule$1$lambda$1(this));
            DispatchQueue.access$getQueueSemaphore$p(this.this$0).release();
            DispatchQueue.access$processNextWorkItem(this.this$0);
        } catch (Throwable th) {
            DispatchQueue.access$getQueueSemaphore$p(this.this$0).release();
            throw th;
        }
    }
}
