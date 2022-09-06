package com.amazon.alexa.logging;

import com.amazon.alexa.accessorykit.ModelTransformer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LogRecord.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/logging/LogRecord;", "", ModelTransformer.KEY_BATTERY_LEVEL, "Lcom/amazon/alexa/logging/Level;", "tag", "Lcom/amazon/alexa/logging/Tag;", "message", "", "tr", "", "(Lcom/amazon/alexa/logging/Level;Lcom/amazon/alexa/logging/Tag;Ljava/lang/String;Ljava/lang/Throwable;)V", "getLevel", "()Lcom/amazon/alexa/logging/Level;", "getMessage", "()Ljava/lang/String;", "getTag", "()Lcom/amazon/alexa/logging/Tag;", "getTr", "()Ljava/lang/Throwable;", "AlexaMobileAndroidLogging_release"}, k = 1, mv = {1, 1, 10})
/* loaded from: classes9.dex */
public final class LogRecord {
    @NotNull
    private final Level level;
    @NotNull
    private final String message;
    @NotNull
    private final Tag tag;
    @Nullable
    private final Throwable tr;

    public LogRecord(@NotNull Level level, @NotNull Tag tag, @NotNull String message, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(level, "level");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        this.level = level;
        this.tag = tag;
        this.message = message;
        this.tr = th;
    }

    @NotNull
    public final Level getLevel() {
        return this.level;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @NotNull
    public final Tag getTag() {
        return this.tag;
    }

    @Nullable
    public final Throwable getTr() {
        return this.tr;
    }
}
