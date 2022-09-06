package com.amazon.photos.uploader;

import android.content.Context;
import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator;
import com.amazon.photos.uploader.blockers.QueueBlockerEvaluator;
import com.amazon.photos.uploader.blockers.RequestBlockerEvaluator;
import com.amazon.photos.uploader.internal.dagger.component.DaggerUploadFrameworkComponent;
import com.amazon.photos.uploader.internal.dagger.component.UploadFrameworkComponent;
import com.amazon.photos.uploader.internal.dagger.module.ConfigurationModule;
import com.amazon.photos.uploader.internal.dagger.module.DatabaseModule;
import com.amazon.photos.uploader.internal.metrics.UploaderMetrics;
import com.amazon.photos.uploader.internal.utils.AndroidLogger;
import com.amazon.photos.uploader.log.UploadLogger;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadManagerBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u000e\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u001fJ\u000e\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u0011J\u000e\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020\fJ\u000e\u0010,\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u001aJ\u000e\u0010-\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u001dJ\u0006\u0010.\u001a\u00020/J\u0017\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u000103H\u0000¢\u0006\u0002\b4J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u000205J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0015J\u000e\u00106\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/amazon/photos/uploader/UploadManagerBuilder;", "", "uploadFrameworkContext", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "uploader", "Lcom/amazon/photos/uploader/Uploader;", "(Lcom/amazon/photos/uploader/UploadFrameworkContext;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/Uploader;)V", "abandonedRequestHandler", "Lcom/amazon/photos/uploader/AbandonedRequestHandler;", "fallbackQueue", "Lcom/amazon/photos/uploader/Queue;", "getFallbackQueue", "()Lcom/amazon/photos/uploader/Queue;", "globalBlockerEvaluators", "", "Lcom/amazon/photos/uploader/blockers/GlobalBlockerEvaluator;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "maxAttempts", "", "maxParallelUploads", "getMetrics", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "queueBlockerEvaluators", "Lcom/amazon/photos/uploader/blockers/QueueBlockerEvaluator;", "queues", "requestBlockerEvaluators", "Lcom/amazon/photos/uploader/blockers/RequestBlockerEvaluator;", "supportedFeatures", "Lcom/amazon/photos/uploader/Feature;", "getUploadFrameworkContext", "()Lcom/amazon/photos/uploader/UploadFrameworkContext;", "getUploader", "()Lcom/amazon/photos/uploader/Uploader;", "uploaderMetrics", "Lcom/amazon/photos/uploader/internal/metrics/UploaderMetrics;", "addFeature", "feature", "addGlobalBlockerEvaluator", "evaluator", "addQueue", "queue", "addQueueBlockerEvaluator", "addRequestBlockerEvaluator", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/photos/uploader/UploadManager;", "getWorkManagerInstance", "Landroidx/work/WorkManager;", "applicationContext", "Landroid/content/Context;", "getWorkManagerInstance$AndroidPhotosUploader_release", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "uploadLogger", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadManagerBuilder {
    private AbandonedRequestHandler abandonedRequestHandler;
    @NotNull
    private final Queue fallbackQueue;
    private final Set<GlobalBlockerEvaluator> globalBlockerEvaluators;
    private UploadLogger logger;
    private int maxAttempts;
    private int maxParallelUploads;
    @NotNull
    private final Metrics metrics;
    private final Set<QueueBlockerEvaluator> queueBlockerEvaluators;
    private final Set<Queue> queues;
    private final Set<RequestBlockerEvaluator> requestBlockerEvaluators;
    private final Set<Feature> supportedFeatures;
    @NotNull
    private final UploadFrameworkContext uploadFrameworkContext;
    @NotNull
    private final Uploader uploader;
    private final UploaderMetrics uploaderMetrics;

    public UploadManagerBuilder(@NotNull UploadFrameworkContext uploadFrameworkContext, @NotNull Metrics metrics, @NotNull Uploader uploader) {
        Set emptySet;
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(uploader, "uploader");
        this.uploadFrameworkContext = uploadFrameworkContext;
        this.metrics = metrics;
        this.uploader = uploader;
        this.maxParallelUploads = 4;
        this.maxAttempts = 10;
        this.logger = new UploadLogger(new AndroidLogger(), UploadLogger.SecurityLevel.OBFUSCATED);
        this.queues = new LinkedHashSet();
        this.globalBlockerEvaluators = new LinkedHashSet();
        this.queueBlockerEvaluators = new LinkedHashSet();
        this.requestBlockerEvaluators = new LinkedHashSet();
        this.supportedFeatures = new LinkedHashSet();
        this.uploaderMetrics = new UploaderMetrics(this.metrics);
        Priority priority = Priority.HIGH;
        emptySet = SetsKt__SetsKt.emptySet();
        this.fallbackQueue = new Queue(UploadManagerBuilderKt.DEFAULT_QUEUE_NAME, priority, emptySet);
    }

    @NotNull
    public final UploadManagerBuilder abandonedRequestHandler(@Nullable AbandonedRequestHandler abandonedRequestHandler) {
        this.abandonedRequestHandler = abandonedRequestHandler;
        return this;
    }

    @NotNull
    public final UploadManagerBuilder addFeature(@NotNull Feature feature) {
        Intrinsics.checkParameterIsNotNull(feature, "feature");
        this.supportedFeatures.add(feature);
        return this;
    }

    @NotNull
    public final UploadManagerBuilder addGlobalBlockerEvaluator(@NotNull GlobalBlockerEvaluator evaluator) {
        Intrinsics.checkParameterIsNotNull(evaluator, "evaluator");
        this.globalBlockerEvaluators.add(evaluator);
        return this;
    }

    @NotNull
    public final UploadManagerBuilder addQueue(@NotNull Queue queue) {
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        this.queues.add(queue);
        return this;
    }

    @NotNull
    public final UploadManagerBuilder addQueueBlockerEvaluator(@NotNull QueueBlockerEvaluator evaluator) {
        Intrinsics.checkParameterIsNotNull(evaluator, "evaluator");
        this.queueBlockerEvaluators.add(evaluator);
        return this;
    }

    @NotNull
    public final UploadManagerBuilder addRequestBlockerEvaluator(@NotNull RequestBlockerEvaluator evaluator) {
        Intrinsics.checkParameterIsNotNull(evaluator, "evaluator");
        this.requestBlockerEvaluators.add(evaluator);
        return this;
    }

    @NotNull
    public final UploadManager build() {
        UploadFrameworkComponent build = DaggerUploadFrameworkComponent.builder().configurationModule(new ConfigurationModule(this.logger, this.uploaderMetrics, this.queues.isEmpty() ^ true ? this.queues : SetsKt__SetsJVMKt.setOf(this.fallbackQueue), getWorkManagerInstance$AndroidPhotosUploader_release(this.uploadFrameworkContext.getApplicationContext()), this.maxParallelUploads, this.maxAttempts, this.abandonedRequestHandler, this.globalBlockerEvaluators, this.queueBlockerEvaluators, this.requestBlockerEvaluators, this.uploadFrameworkContext, this.uploader, this.supportedFeatures)).databaseModule(new DatabaseModule(this.uploadFrameworkContext)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "DaggerUploadFrameworkCom…\n                .build()");
        return new UploadManager(build);
    }

    @NotNull
    public final Queue getFallbackQueue() {
        return this.fallbackQueue;
    }

    @NotNull
    public final Metrics getMetrics() {
        return this.metrics;
    }

    @NotNull
    public final UploadFrameworkContext getUploadFrameworkContext() {
        return this.uploadFrameworkContext;
    }

    @NotNull
    public final Uploader getUploader() {
        return this.uploader;
    }

    @NotNull
    public final WorkManager getWorkManagerInstance$AndroidPhotosUploader_release(@Nullable Context context) {
        if (context == null) {
            Intrinsics.throwNpe();
        }
        WorkManager workManager = WorkManager.getInstance(context);
        Intrinsics.checkExpressionValueIsNotNull(workManager, "WorkManager.getInstance(applicationContext!!)");
        return workManager;
    }

    @NotNull
    public final UploadManagerBuilder logger(@NotNull Logger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.logger = new UploadLogger(logger, UploadLogger.SecurityLevel.OBFUSCATED);
        return this;
    }

    @NotNull
    public final UploadManagerBuilder maxAttempts(int i) {
        if (i != 0) {
            this.maxAttempts = i;
            return this;
        }
        throw new IllegalArgumentException("Max attempts must be at least 1.");
    }

    @NotNull
    public final UploadManagerBuilder maxParallelUploads(int i) {
        this.maxParallelUploads = i;
        return this;
    }

    @NotNull
    public final UploadManagerBuilder uploadLogger(@NotNull UploadLogger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.logger = logger;
        return this;
    }
}
