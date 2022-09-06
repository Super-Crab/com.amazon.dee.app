package com.amazon.photos.autosave.internal.workers;

import android.content.Context;
import android.util.Log;
import androidx.work.CoroutineWorker;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.autosave.internal.utils.AndroidLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BaseWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b&\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\f\u001a\u00020\rH\u0002J\u0011\u0010\u000e\u001a\u00020\u000fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001a\u00020\u0012H$J\b\u0010\u0013\u001a\u00020\u0014H$J\b\u0010\u0015\u001a\u00020\rH$J\u0011\u0010\u0016\u001a\u00020\u000fH¤@ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lcom/amazon/photos/autosave/internal/workers/BaseWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "componentFailureHappened", "", "componentInjected", "logger", "Lcom/amazon/photos/autosave/internal/utils/AndroidLogger;", "componentsSetup", "", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMetricsObj", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "getTag", "", "injectMethod", "mainTask", "Companion", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class BaseWorker extends CoroutineWorker {
    public static final Companion Companion = new Companion(null);
    public static final int MAX_RETRIES = 5;
    private static int attemptCount;
    private boolean componentFailureHappened;
    private boolean componentInjected;
    private final AndroidLogger logger;

    /* compiled from: BaseWorker.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0002¨\u0006\u0007"}, d2 = {"Lcom/amazon/photos/autosave/internal/workers/BaseWorker$Companion;", "", "()V", "MAX_RETRIES", "", "attemptCount", "attemptCount$annotations", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        private static /* synthetic */ void attemptCount$annotations() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseWorker(@NotNull Context context, @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(workerParams, "workerParams");
        this.logger = new AndroidLogger();
    }

    private final void componentsSetup() {
        if (!this.componentInjected) {
            injectMethod();
            this.componentInjected = true;
            getMetricsObj().recordSimpleEvent(getTag(), BaseWorker$componentsSetup$1.INSTANCE, new MetricRecordingType[0]);
            if (this.componentFailureHappened) {
                getMetricsObj().recordSimpleEvent(getTag(), BaseWorker$componentsSetup$2.INSTANCE, new MetricRecordingType[0]);
            }
            attemptCount = 0;
        }
    }

    static /* synthetic */ Object doWork$suspendImpl(BaseWorker baseWorker, Continuation continuation) {
        try {
            baseWorker.componentsSetup();
            return baseWorker.mainTask(continuation);
        } catch (IllegalStateException unused) {
            baseWorker.componentFailureHappened = true;
            int i = attemptCount;
            if (i < 5) {
                attemptCount = i + 1;
                AndroidLogger androidLogger = baseWorker.logger;
                String tag = baseWorker.getTag();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error injecting components to worker on attempt ");
                outline107.append(attemptCount);
                outline107.append(". Retrying...");
                androidLogger.w(tag, outline107.toString());
                ListenableWorker.Result retry = ListenableWorker.Result.retry();
                Intrinsics.checkExpressionValueIsNotNull(retry, "Result.retry()");
                return retry;
            }
            Log.e(baseWorker.getTag(), "Component injection failed.");
            attemptCount = 0;
            ListenableWorker.Result failure = ListenableWorker.Result.failure();
            Intrinsics.checkExpressionValueIsNotNull(failure, "Result.failure()");
            return failure;
        }
    }

    @Override // androidx.work.CoroutineWorker
    @Nullable
    public Object doWork(@NotNull Continuation<? super ListenableWorker.Result> continuation) {
        return doWork$suspendImpl(this, continuation);
    }

    @NotNull
    protected abstract Metrics getMetricsObj();

    @NotNull
    protected abstract String getTag();

    protected abstract void injectMethod();

    @Nullable
    protected abstract Object mainTask(@NotNull Continuation<? super ListenableWorker.Result> continuation);
}
