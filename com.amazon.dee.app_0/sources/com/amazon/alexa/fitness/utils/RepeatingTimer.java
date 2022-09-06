package com.amazon.alexa.fitness.utils;

import android.util.Log;
import com.amazon.tarazed.core.session.sessionEvents.PlaybackStates;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: RepeatingTimer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000/\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\b\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/fitness/utils/RepeatingTimer;", "", "messageProcessor", "Lcom/amazon/alexa/fitness/utils/MessageProcessor;", "durationInMillis", "", "(Lcom/amazon/alexa/fitness/utils/MessageProcessor;J)V", "internalRunnable", "com/amazon/alexa/fitness/utils/RepeatingTimer$internalRunnable$1", "Lcom/amazon/alexa/fitness/utils/RepeatingTimer$internalRunnable$1;", "runnable", "Ljava/lang/Runnable;", "getRunnable", "()Ljava/lang/Runnable;", "setRunnable", "(Ljava/lang/Runnable;)V", "timerDuration", "getTimerDuration", "()J", "setTimerDuration", "(J)V", "start", "", "stop", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class RepeatingTimer {
    private final long durationInMillis;
    private final RepeatingTimer$internalRunnable$1 internalRunnable;
    private final MessageProcessor messageProcessor;
    @Nullable
    private Runnable runnable;
    private long timerDuration;

    /* JADX WARN: Type inference failed for: r2v2, types: [com.amazon.alexa.fitness.utils.RepeatingTimer$internalRunnable$1] */
    @Inject
    public RepeatingTimer(@NotNull MessageProcessor messageProcessor, long j) {
        Intrinsics.checkParameterIsNotNull(messageProcessor, "messageProcessor");
        this.messageProcessor = messageProcessor;
        this.durationInMillis = j;
        this.timerDuration = this.durationInMillis;
        this.internalRunnable = new Runnable() { // from class: com.amazon.alexa.fitness.utils.RepeatingTimer$internalRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                MessageProcessor messageProcessor2;
                MessageProcessor messageProcessor3;
                Runnable runnable = RepeatingTimer.this.getRunnable();
                if (runnable != null) {
                    messageProcessor2 = RepeatingTimer.this.messageProcessor;
                    messageProcessor2.post(runnable);
                    messageProcessor3 = RepeatingTimer.this.messageProcessor;
                    messageProcessor3.postWithDelay(this, RepeatingTimer.this.getTimerDuration());
                    return;
                }
                Log.e("AFX-BackfillRepeatTimer", "runnable to be repeated not set yet");
            }
        };
    }

    @Nullable
    public final Runnable getRunnable() {
        return this.runnable;
    }

    public final long getTimerDuration() {
        return this.timerDuration;
    }

    public final void setRunnable(@Nullable Runnable runnable) {
        this.runnable = runnable;
    }

    public final void setTimerDuration(long j) {
        this.timerDuration = j;
    }

    public final void start() {
        Log.i("AFX-BackfillRepeatTimer", PlaybackStates.STARTING);
        this.messageProcessor.postWithDelay(this.internalRunnable, this.timerDuration);
    }

    public final void stop() {
        Log.i("AFX-BackfillRepeatTimer", "Stopping");
        if (this.runnable != null) {
            this.messageProcessor.remove(this.internalRunnable);
        }
    }

    public /* synthetic */ RepeatingTimer(MessageProcessor messageProcessor, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(messageProcessor, (i & 2) != 0 ? 15000L : j);
    }
}
