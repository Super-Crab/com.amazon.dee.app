package com.amazon.alexa.fitness.metrics;

import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Operation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007J\u001e\u0010\u0014\u001a\u00020\u00122\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u0006\u0010\u0018\u001a\u00020\u0012J&\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00072\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u000e\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/SpanningMetricOperations;", "", "metricEvent", "Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "initialOperation", "", "maxOperationsCount", "", "(Lcom/amazon/alexa/fitness/metrics/MetricEvent;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Ljava/lang/String;I)V", "finished", "", JoinPoint.SYNCHRONIZATION_LOCK, "Ljava/util/concurrent/locks/ReentrantLock;", "operations", "", "addOperation", "", HttpClientModule.ElementsRequestKey.OPERATION_NAME, "completeAllOperationsOnError", "errorCounterName", "throwable", "", "completeAllOperationsOnSuccess", "completeOperationOnError", "completeOperationOnSuccess", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SpanningMetricOperations {
    private boolean finished;
    private final String initialOperation;
    private final ReentrantLock lock;
    private final int maxOperationsCount;
    private final MetricEvent metricEvent;
    private final MetricEventRecorder metricEventRecorder;
    private final Set<String> operations;

    public SpanningMetricOperations(@NotNull MetricEvent metricEvent, @Nullable MetricEventRecorder metricEventRecorder, @Nullable String str, int i) {
        Intrinsics.checkParameterIsNotNull(metricEvent, "metricEvent");
        this.metricEvent = metricEvent;
        this.metricEventRecorder = metricEventRecorder;
        this.initialOperation = str;
        this.maxOperationsCount = i;
        this.lock = new ReentrantLock();
        this.operations = new LinkedHashSet();
        String str2 = this.initialOperation;
        if (str2 != null) {
            addOperation(str2);
        }
    }

    public static /* synthetic */ void completeAllOperationsOnError$default(SpanningMetricOperations spanningMetricOperations, String str, Throwable th, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            th = null;
        }
        spanningMetricOperations.completeAllOperationsOnError(str, th);
    }

    public static /* synthetic */ void completeOperationOnError$default(SpanningMetricOperations spanningMetricOperations, String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            th = null;
        }
        spanningMetricOperations.completeOperationOnError(str, str2, th);
    }

    public final void addOperation(@NotNull String operationName) {
        Intrinsics.checkParameterIsNotNull(operationName, "operationName");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (!this.finished && this.operations.size() <= this.maxOperationsCount && !this.operations.contains(operationName)) {
                this.operations.add(operationName);
                OperationKt.decorateOnOperationStart(this.metricEvent, operationName);
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void completeAllOperationsOnError(@Nullable String str, @Nullable Throwable th) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.finished) {
                return;
            }
            for (String str2 : this.operations) {
                OperationKt.decorateOnOperationError(this.metricEvent, str2, th, str);
            }
            this.operations.clear();
            this.finished = true;
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            MetricEventRecorder metricEventRecorder = this.metricEventRecorder;
            if (metricEventRecorder == null) {
                return;
            }
            metricEventRecorder.record(this.metricEvent);
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void completeAllOperationsOnSuccess() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.finished) {
                return;
            }
            for (String str : this.operations) {
                OperationKt.decorateOnOperationSuccess(this.metricEvent, str);
            }
            this.operations.clear();
            this.finished = true;
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            MetricEventRecorder metricEventRecorder = this.metricEventRecorder;
            if (metricEventRecorder == null) {
                return;
            }
            metricEventRecorder.record(this.metricEvent);
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void completeOperationOnError(@NotNull String operationName, @Nullable String str, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(operationName, "operationName");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (!this.finished && this.operations.contains(operationName)) {
                this.operations.remove(operationName);
                OperationKt.decorateOnOperationError(this.metricEvent, operationName, th, str);
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void completeOperationOnSuccess(@NotNull String operationName) {
        Intrinsics.checkParameterIsNotNull(operationName, "operationName");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (!this.finished && this.operations.contains(operationName)) {
                this.operations.remove(operationName);
                OperationKt.decorateOnOperationSuccess(this.metricEvent, operationName);
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public /* synthetic */ SpanningMetricOperations(MetricEvent metricEvent, MetricEventRecorder metricEventRecorder, String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(metricEvent, (i2 & 2) != 0 ? null : metricEventRecorder, (i2 & 4) != 0 ? null : str, (i2 & 8) != 0 ? 20 : i);
    }
}
