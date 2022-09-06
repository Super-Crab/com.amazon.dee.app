package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.tarazed.core.session.sessionEvents.PlaybackStates;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: RepeatingTimer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\n\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0018R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/RepeatingTimer;", "", "messageProcessor", "Lcom/amazon/alexa/fitness/sdk/MessageProcessor;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "durationInMillis", "", "(Lcom/amazon/alexa/fitness/sdk/MessageProcessor;Lcom/amazon/alexa/fitness/logs/ILog;J)V", "internalRunnable", "com/amazon/alexa/fitness/sdk/RepeatingTimer$internalRunnable$1", "Lcom/amazon/alexa/fitness/sdk/RepeatingTimer$internalRunnable$1;", "runnable", "Ljava/lang/Runnable;", "getRunnable", "()Ljava/lang/Runnable;", "setRunnable", "(Ljava/lang/Runnable;)V", "timerDuration", "getTimerDuration", "()J", "setTimerDuration", "(J)V", "start", "", "stop", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class RepeatingTimer {
    private final long durationInMillis;
    private final RepeatingTimer$internalRunnable$1 internalRunnable;
    private final ILog log;
    private final MessageProcessor messageProcessor;
    @Nullable
    private Runnable runnable;
    private long timerDuration;

    /* JADX WARN: Type inference failed for: r2v2, types: [com.amazon.alexa.fitness.sdk.RepeatingTimer$internalRunnable$1] */
    @Inject
    public RepeatingTimer(@NotNull MessageProcessor messageProcessor, @NotNull ILog log, long j) {
        Intrinsics.checkParameterIsNotNull(messageProcessor, "messageProcessor");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.messageProcessor = messageProcessor;
        this.log = log;
        this.durationInMillis = j;
        this.timerDuration = this.durationInMillis;
        this.internalRunnable = new Runnable() { // from class: com.amazon.alexa.fitness.sdk.RepeatingTimer$internalRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                ILog iLog;
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
                iLog = RepeatingTimer.this.log;
                ILog.DefaultImpls.error$default(iLog, "RepeatingTimer", "runnable to be repeated not set yet", null, 4, null);
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
        ILog.DefaultImpls.info$default(this.log, "RepeatingTimer", PlaybackStates.STARTING, null, 4, null);
        this.messageProcessor.postWithDelay(this.internalRunnable, this.timerDuration);
    }

    public final void stop() {
        ILog.DefaultImpls.info$default(this.log, "RepeatingTimer", "Stopping", null, 4, null);
        if (this.runnable != null) {
            this.messageProcessor.remove(this.internalRunnable);
        }
    }

    public /* synthetic */ RepeatingTimer(MessageProcessor messageProcessor, ILog iLog, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(messageProcessor, iLog, (i & 4) != 0 ? 10000L : j);
    }
}
