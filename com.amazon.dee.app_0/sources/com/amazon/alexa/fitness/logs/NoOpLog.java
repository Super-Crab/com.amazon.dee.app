package com.amazon.alexa.fitness.logs;

import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.mobilytics.event.LogLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NoOpLog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\"\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\"\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\"\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/logs/NoOpLog;", "Lcom/amazon/alexa/fitness/logs/ILog;", "()V", "debug", "", JsonFields.COMPONENT, "", "message", "throwable", "", "error", LogLevel.INFO, LogLevel.WARN, "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class NoOpLog implements ILog {
    @Override // com.amazon.alexa.fitness.logs.ILog
    public void debug(@NotNull String component, @NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(component, "component");
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    @Override // com.amazon.alexa.fitness.logs.ILog
    public void error(@NotNull String component, @NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(component, "component");
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    @Override // com.amazon.alexa.fitness.logs.ILog
    public void info(@NotNull String component, @NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(component, "component");
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    @Override // com.amazon.alexa.fitness.logs.ILog
    public void warn(@NotNull String component, @NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(component, "component");
        Intrinsics.checkParameterIsNotNull(message, "message");
    }
}
