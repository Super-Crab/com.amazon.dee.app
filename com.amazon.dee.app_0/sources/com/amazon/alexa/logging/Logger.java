package com.amazon.alexa.logging;

import com.amazon.alexa.accessorykit.ModelTransformer;
import com.facebook.common.callercontext.ContextChain;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Logger.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\"\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\"\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J*\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\"\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\"\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0018\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\"\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/logging/Logger;", "Lcom/amazon/alexa/logging/LogFilter;", "d", "", "tag", "Lcom/amazon/alexa/logging/Tag;", "message", "", "tr", "", "e", ContextChain.TAG_INFRA, "log", ModelTransformer.KEY_BATTERY_LEVEL, "Lcom/amazon/alexa/logging/Level;", "v", "w", "wtf", "AlexaMobileAndroidLogging_release"}, k = 1, mv = {1, 1, 10})
/* loaded from: classes9.dex */
public interface Logger extends LogFilter {

    /* compiled from: Logger.kt */
    @Metadata(bv = {1, 0, 2}, k = 3, mv = {1, 1, 10})
    /* loaded from: classes9.dex */
    public static final class DefaultImpls {
        public static int d(Logger logger, @NotNull Tag tag, @NotNull String message) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(message, "message");
            return logger.log(Level.DEBUG, tag, message, null);
        }

        public static int e(Logger logger, @NotNull Tag tag, @NotNull String message) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(message, "message");
            return logger.log(Level.ERROR, tag, message, null);
        }

        public static int i(Logger logger, @NotNull Tag tag, @NotNull String message) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(message, "message");
            return logger.log(Level.INFO, tag, message, null);
        }

        public static int v(Logger logger, @NotNull Tag tag, @NotNull String message) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(message, "message");
            return logger.log(Level.VERBOSE, tag, message, null);
        }

        public static int w(Logger logger, @NotNull Tag tag, @NotNull String message) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(message, "message");
            return logger.log(Level.WARN, tag, message, null);
        }

        public static int wtf(Logger logger, @NotNull Tag tag, @NotNull String message) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(message, "message");
            return logger.log(Level.ASSERT, tag, message, null);
        }

        public static int d(Logger logger, @NotNull Tag tag, @NotNull String message, @Nullable Throwable th) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(message, "message");
            return logger.log(Level.DEBUG, tag, message, th);
        }

        public static int e(Logger logger, @NotNull Tag tag, @NotNull String message, @Nullable Throwable th) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(message, "message");
            return logger.log(Level.ERROR, tag, message, th);
        }

        public static int i(Logger logger, @NotNull Tag tag, @NotNull String message, @Nullable Throwable th) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(message, "message");
            return logger.log(Level.INFO, tag, message, th);
        }

        public static int v(Logger logger, @NotNull Tag tag, @NotNull String message, @Nullable Throwable th) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(message, "message");
            return logger.log(Level.VERBOSE, tag, message, th);
        }

        public static int w(Logger logger, @NotNull Tag tag, @NotNull String message, @Nullable Throwable th) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(message, "message");
            return logger.log(Level.WARN, tag, message, th);
        }

        public static int wtf(Logger logger, @NotNull Tag tag, @NotNull String message, @Nullable Throwable th) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            Intrinsics.checkParameterIsNotNull(message, "message");
            return logger.log(Level.ASSERT, tag, message, th);
        }
    }

    int d(@NotNull Tag tag, @NotNull String str);

    int d(@NotNull Tag tag, @NotNull String str, @Nullable Throwable th);

    int e(@NotNull Tag tag, @NotNull String str);

    int e(@NotNull Tag tag, @NotNull String str, @Nullable Throwable th);

    int i(@NotNull Tag tag, @NotNull String str);

    int i(@NotNull Tag tag, @NotNull String str, @Nullable Throwable th);

    int log(@NotNull Level level, @NotNull Tag tag, @NotNull String str, @Nullable Throwable th);

    int v(@NotNull Tag tag, @NotNull String str);

    int v(@NotNull Tag tag, @NotNull String str, @Nullable Throwable th);

    int w(@NotNull Tag tag, @NotNull String str);

    int w(@NotNull Tag tag, @NotNull String str, @Nullable Throwable th);

    int wtf(@NotNull Tag tag, @NotNull String str);

    int wtf(@NotNull Tag tag, @NotNull String str, @Nullable Throwable th);
}
