package com.amazon.alexa.fitness.utils;

import android.os.Handler;
import android.os.HandlerThread;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AfxBackfillMessageProcessor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/utils/AfxBackfillMessageProcessor;", "Lcom/amazon/alexa/fitness/utils/MessageProcessor;", "()V", "afxBackfillHandler", "Landroid/os/Handler;", "afxBackfillHandlerThread", "Landroid/os/HandlerThread;", "getAfxBackfillHandlerThread", "()Landroid/os/HandlerThread;", "post", "", "runnable", "Ljava/lang/Runnable;", "postWithDelay", "delayInMillis", "", BulkOperationType.remove, "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public class AfxBackfillMessageProcessor implements MessageProcessor {
    private Handler afxBackfillHandler;
    @NotNull
    private final HandlerThread afxBackfillHandlerThread = new HandlerThread(AfxBackfillMessageProcessorKt.AfxBackfillProcessorThread);

    public AfxBackfillMessageProcessor() {
        this.afxBackfillHandlerThread.start();
        this.afxBackfillHandler = new Handler(this.afxBackfillHandlerThread.getLooper());
    }

    @NotNull
    public final HandlerThread getAfxBackfillHandlerThread() {
        return this.afxBackfillHandlerThread;
    }

    @Override // com.amazon.alexa.fitness.utils.MessageProcessor
    public void post(@NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "runnable");
        Handler handler = this.afxBackfillHandler;
        if (handler != null) {
            handler.post(runnable);
        }
    }

    @Override // com.amazon.alexa.fitness.utils.MessageProcessor
    public void postWithDelay(@NotNull Runnable runnable, long j) {
        Intrinsics.checkParameterIsNotNull(runnable, "runnable");
        Handler handler = this.afxBackfillHandler;
        if (handler != null) {
            handler.postDelayed(runnable, j);
        }
    }

    @Override // com.amazon.alexa.fitness.utils.MessageProcessor
    public void remove(@NotNull Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(runnable, "runnable");
        Handler handler = this.afxBackfillHandler;
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
