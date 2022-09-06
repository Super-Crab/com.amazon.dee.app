package com.amazon.alexa.logging;

import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.logging.Logger;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DispatchingLogger.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012²\u0006\r\u0010\u0013\u001a\u00020\u0014X\u008a\u0084\u0002¢\u0006\u0000"}, d2 = {"Lcom/amazon/alexa/logging/DispatchingLogger;", "Lcom/amazon/alexa/logging/Logger;", "()V", "handlers", "", "Lcom/amazon/alexa/logging/LogHandler;", "getHandlers", "()Ljava/lang/Iterable;", "log", "", ModelTransformer.KEY_BATTERY_LEVEL, "Lcom/amazon/alexa/logging/Level;", "tag", "Lcom/amazon/alexa/logging/Tag;", "message", "", "tr", "", "AlexaMobileAndroidLogging_release", "record", "Lcom/amazon/alexa/logging/LogRecord;"}, k = 1, mv = {1, 1, 10})
/* loaded from: classes9.dex */
public abstract class DispatchingLogger implements Logger {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property0(new PropertyReference0Impl(Reflection.getOrCreateKotlinClass(DispatchingLogger.class), "record", "<v#0>"))};

    @Override // com.amazon.alexa.logging.Logger
    public int d(@NotNull Tag tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return Logger.DefaultImpls.d(this, tag, message);
    }

    @Override // com.amazon.alexa.logging.Logger
    public int e(@NotNull Tag tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return Logger.DefaultImpls.e(this, tag, message);
    }

    @NotNull
    protected abstract Iterable<LogHandler> getHandlers();

    @Override // com.amazon.alexa.logging.Logger
    public int i(@NotNull Tag tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return Logger.DefaultImpls.i(this, tag, message);
    }

    @Override // com.amazon.alexa.logging.Logger
    public final int log(@NotNull Level level, @NotNull Tag tag, @NotNull String message, @Nullable Throwable th) {
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(level, "level");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        int i = 0;
        if (isLoggable(tag, level)) {
            lazy = LazyKt__LazyJVMKt.lazy(new DispatchingLogger$log$record$2(level, tag, message, th));
            KProperty kProperty = $$delegatedProperties[0];
            for (LogHandler logHandler : getHandlers()) {
                if (logHandler.isLoggable(tag, level)) {
                    i += logHandler.log((LogRecord) lazy.getValue());
                }
            }
        }
        return i;
    }

    @Override // com.amazon.alexa.logging.Logger
    public int v(@NotNull Tag tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return Logger.DefaultImpls.v(this, tag, message);
    }

    @Override // com.amazon.alexa.logging.Logger
    public int w(@NotNull Tag tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return Logger.DefaultImpls.w(this, tag, message);
    }

    @Override // com.amazon.alexa.logging.Logger
    public int wtf(@NotNull Tag tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return Logger.DefaultImpls.wtf(this, tag, message);
    }

    @Override // com.amazon.alexa.logging.Logger
    public int d(@NotNull Tag tag, @NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return Logger.DefaultImpls.d(this, tag, message, th);
    }

    @Override // com.amazon.alexa.logging.Logger
    public int e(@NotNull Tag tag, @NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return Logger.DefaultImpls.e(this, tag, message, th);
    }

    @Override // com.amazon.alexa.logging.Logger
    public int i(@NotNull Tag tag, @NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return Logger.DefaultImpls.i(this, tag, message, th);
    }

    @Override // com.amazon.alexa.logging.Logger
    public int v(@NotNull Tag tag, @NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return Logger.DefaultImpls.v(this, tag, message, th);
    }

    @Override // com.amazon.alexa.logging.Logger
    public int w(@NotNull Tag tag, @NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return Logger.DefaultImpls.w(this, tag, message, th);
    }

    @Override // com.amazon.alexa.logging.Logger
    public int wtf(@NotNull Tag tag, @NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        return Logger.DefaultImpls.wtf(this, tag, message, th);
    }
}
