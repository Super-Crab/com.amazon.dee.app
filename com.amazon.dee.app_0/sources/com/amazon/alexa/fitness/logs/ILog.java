package com.amazon.alexa.fitness.logs;

import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.mobilytics.event.LogLevel;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ILog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH&J$\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH&J$\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH&J$\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH&¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/fitness/logs/ILog;", "", "debug", "", JsonFields.COMPONENT, "", "message", "throwable", "", "error", LogLevel.INFO, LogLevel.WARN, "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface ILog {

    /* compiled from: ILog.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void debug$default(ILog iLog, String str, String str2, Throwable th, int i, Object obj) {
            if (obj == null) {
                if ((i & 4) != 0) {
                    th = null;
                }
                iLog.debug(str, str2, th);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: debug");
        }

        public static /* synthetic */ void error$default(ILog iLog, String str, String str2, Throwable th, int i, Object obj) {
            if (obj == null) {
                if ((i & 4) != 0) {
                    th = null;
                }
                iLog.error(str, str2, th);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: error");
        }

        public static /* synthetic */ void info$default(ILog iLog, String str, String str2, Throwable th, int i, Object obj) {
            if (obj == null) {
                if ((i & 4) != 0) {
                    th = null;
                }
                iLog.info(str, str2, th);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: info");
        }

        public static /* synthetic */ void warn$default(ILog iLog, String str, String str2, Throwable th, int i, Object obj) {
            if (obj == null) {
                if ((i & 4) != 0) {
                    th = null;
                }
                iLog.warn(str, str2, th);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: warn");
        }
    }

    void debug(@NotNull String str, @NotNull String str2, @Nullable Throwable th);

    void error(@NotNull String str, @NotNull String str2, @Nullable Throwable th);

    void info(@NotNull String str, @NotNull String str2, @Nullable Throwable th);

    void warn(@NotNull String str, @NotNull String str2, @Nullable Throwable th);
}
