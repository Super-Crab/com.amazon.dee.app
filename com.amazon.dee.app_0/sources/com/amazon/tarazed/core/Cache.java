package com.amazon.tarazed.core;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.dee.app.metrics.MetricsConstants;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.io.FilesKt__FileReadWriteKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Cache.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\n\b\u0016\u0018\u0000 \u001a*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u001aB3\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0006\u0010\u0012\u001a\u00020\u0011J\u0015\u0010\u0013\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0014\u001a\u00020\f¢\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0002J\u001b\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00028\u0000¢\u0006\u0002\u0010\u0019R\u0014\u0010\u000b\u001a\u00020\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/tarazed/core/Cache;", ExifInterface.GPS_DIRECTION_TRUE, "", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "serializer", "Lkotlinx/serialization/KSerializer;", "cacheDir", "", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lkotlinx/serialization/KSerializer;Ljava/lang/String;)V", "getCacheDir$TarazedMobileCore_release", "()Ljava/lang/String;", "cleanCache", "", "clearCache", MetricsConstants.Method.CACHE_GET, AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "(Ljava/lang/String;)Ljava/lang/Object;", "getFileName", MetricsConstants.Method.CACHE_PUT, "value", "(Ljava/lang/String;Ljava/lang/Object;)V", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public class Cache<T> {
    public static final Companion Companion = new Companion(null);
    private static final String METRIC_IO_EXCEPTION_ON_READ = "IOExceptionOnRead";
    private static final String METRIC_IO_EXCEPTION_ON_WRITE = "IOExceptionOnWrite";
    private static final String TAG = "SessionCache";
    @NotNull
    private final String cacheDir;
    private final Context context;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    private final KSerializer<T> serializer;

    /* compiled from: Cache.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/tarazed/core/Cache$Companion;", "", "()V", "METRIC_IO_EXCEPTION_ON_READ", "", "METRIC_IO_EXCEPTION_ON_WRITE", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Cache(@NotNull Context context, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull KSerializer<T> serializer, @NotNull String cacheDir) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        Intrinsics.checkParameterIsNotNull(cacheDir, "cacheDir");
        this.context = context;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.serializer = serializer;
        this.cacheDir = cacheDir;
    }

    private final void cleanCache() {
        File file = new File(this.context.getCacheDir(), this.cacheDir);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            Intrinsics.checkExpressionValueIsNotNull(listFiles, "cacheDir.listFiles()");
            for (File file2 : listFiles) {
                file2.delete();
            }
        }
    }

    private final String getFileName(String str) {
        return this.cacheDir + '/' + str;
    }

    public final void clearCache() {
        cleanCache();
    }

    @Nullable
    public final T get(@NotNull String sessionId) {
        String readText$default;
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        String fileName = getFileName(sessionId);
        File file = new File(this.context.getCacheDir() + '/' + fileName);
        if (file.exists()) {
            this.logger.i(TAG, "Cached object file exists, retrieving object.");
            try {
                readText$default = FilesKt__FileReadWriteKt.readText$default(file, null, 1, null);
                return (T) Json.Default.getNonstrict().parse(this.serializer, readText$default);
            } catch (IOException e) {
                this.logger.e(TAG, "IOException occurred when retrieving object, returning null", e);
                this.metricsHelper.addCount(TAG, METRIC_IO_EXCEPTION_ON_READ, 1.0d);
                return null;
            }
        }
        this.logger.i(TAG, "Object not found, returning null.");
        return null;
    }

    @NotNull
    public final String getCacheDir$TarazedMobileCore_release() {
        return this.cacheDir;
    }

    public final void put(@NotNull String sessionId, T t) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        cleanCache();
        new File(this.context.getCacheDir(), this.cacheDir).mkdirs();
        this.logger.i(TAG, "Storing object to cache.");
        try {
            String stringify = Json.Default.stringify(this.serializer, t);
            String fileName = getFileName(sessionId);
            FilesKt__FileReadWriteKt.writeText$default(new File(this.context.getCacheDir() + '/' + fileName), stringify, null, 2, null);
        } catch (IOException e) {
            this.logger.e(TAG, "IOException occurred when writing object", e);
            this.metricsHelper.addCount(TAG, METRIC_IO_EXCEPTION_ON_WRITE, 1.0d);
            cleanCache();
        }
    }
}
