package com.amazon.tarazed.core;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.dee.app.metrics.MetricsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SingleEntryCache.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B;\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000eJ\r\u0010\u000f\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u0000¢\u0006\u0002\u0010\u0014R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/tarazed/core/SingleEntryCache;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/tarazed/core/Cache;", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "serializer", "Lkotlinx/serialization/KSerializer;", "cacheDirectory", "", "cacheFileName", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lkotlinx/serialization/KSerializer;Ljava/lang/String;Ljava/lang/String;)V", MetricsConstants.Method.CACHE_GET, "()Ljava/lang/Object;", MetricsConstants.Method.CACHE_PUT, "", "value", "(Ljava/lang/Object;)V", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SingleEntryCache<T> extends Cache<T> {
    private final String cacheFileName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SingleEntryCache(@NotNull Context context, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull KSerializer<T> serializer, @NotNull String cacheDirectory, @NotNull String cacheFileName) {
        super(context, logger, metricsHelper, serializer, cacheDirectory);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        Intrinsics.checkParameterIsNotNull(cacheDirectory, "cacheDirectory");
        Intrinsics.checkParameterIsNotNull(cacheFileName, "cacheFileName");
        this.cacheFileName = cacheFileName;
    }

    @Nullable
    public final T get() {
        return get(this.cacheFileName);
    }

    public final void put(T t) {
        put(this.cacheFileName, t);
    }
}
