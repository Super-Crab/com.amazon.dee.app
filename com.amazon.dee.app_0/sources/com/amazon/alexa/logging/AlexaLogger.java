package com.amazon.alexa.logging;

import com.amazon.alexa.accessorykit.ModelTransformer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Lib.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/logging/AlexaLogger;", "Lcom/amazon/alexa/logging/DispatchingLogger;", "minLoggableLevel", "Lcom/amazon/alexa/logging/Level;", "handlers", "", "Lcom/amazon/alexa/logging/LogHandler;", "(Lcom/amazon/alexa/logging/Level;Ljava/lang/Iterable;)V", "getHandlers", "()Ljava/lang/Iterable;", "isLoggable", "", "tag", "Lcom/amazon/alexa/logging/Tag;", ModelTransformer.KEY_BATTERY_LEVEL, "AlexaMobileAndroidLogging_release"}, k = 1, mv = {1, 1, 10})
/* loaded from: classes9.dex */
public final class AlexaLogger extends DispatchingLogger {
    @NotNull
    private final Iterable<LogHandler> handlers;
    private final Level minLoggableLevel;

    public /* synthetic */ AlexaLogger(Level level, Iterable iterable, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Level.VERBOSE : level, iterable);
    }

    @Override // com.amazon.alexa.logging.DispatchingLogger
    @NotNull
    protected Iterable<LogHandler> getHandlers() {
        return this.handlers;
    }

    @Override // com.amazon.alexa.logging.LogFilter
    public boolean isLoggable(@NotNull Tag tag, @NotNull Level level) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(level, "level");
        return level.compareTo(this.minLoggableLevel) >= 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AlexaLogger(@NotNull Level minLoggableLevel, @NotNull Iterable<? extends LogHandler> handlers) {
        Intrinsics.checkParameterIsNotNull(minLoggableLevel, "minLoggableLevel");
        Intrinsics.checkParameterIsNotNull(handlers, "handlers");
        this.minLoggableLevel = minLoggableLevel;
        this.handlers = handlers;
    }
}
