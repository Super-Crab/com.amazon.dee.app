package com.amazon.photos.uploader.cds;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.photos.uploader.FileSizeCategoryProvider;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.Uploader;
import com.amazon.photos.uploader.cds.error.CdsConflictResolver;
import com.amazon.photos.uploader.cds.error.CdsConflictResolverImpl;
import com.amazon.photos.uploader.cds.multipart.MultipartDatabaseModule;
import com.amazon.photos.uploader.cds.nodecache.NodeCacheDatabaseModule;
import com.amazon.photos.uploader.internal.dagger.component.CdsUploaderComponent;
import com.amazon.photos.uploader.internal.dagger.component.DaggerCdsUploaderComponent;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule;
import com.amazon.photos.uploader.internal.metrics.UploaderMetrics;
import com.amazon.photos.uploader.internal.utils.FileUtils;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CdsUploaderBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010'\u001a\u00020(J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\t\u0010)\u001a\u00020\u0003HÂ\u0003J\u000e\u0010*\u001a\u00020\u0005HÀ\u0003¢\u0006\u0002\b+J\t\u0010,\u001a\u00020\u0007HÂ\u0003J\u000e\u0010-\u001a\u00020\tHÀ\u0003¢\u0006\u0002\b.J1\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0010\u00100\u001a\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0018\u00101\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u00102\u001a\u000203H\u0002J\u0013\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00107\u001a\u000208HÖ\u0001J\t\u00109\u001a\u00020:HÖ\u0001J\u000e\u0010!\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"J\u000e\u0010%\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lcom/amazon/photos/uploader/cds/CdsUploaderBuilder;", "", "uploadFrameworkContext", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(Lcom/amazon/photos/uploader/UploadFrameworkContext;Lcom/amazon/clouddrive/cdasdk/CDClient;Lcom/amazon/clouddrive/android/core/interfaces/Logger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "callbackScheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "getCallbackScheduler$AndroidPhotosUploader_release", "()Lio/reactivex/rxjava3/core/Scheduler;", "setCallbackScheduler$AndroidPhotosUploader_release", "(Lio/reactivex/rxjava3/core/Scheduler;)V", "getCdClient$AndroidPhotosUploader_release", "()Lcom/amazon/clouddrive/cdasdk/CDClient;", "cdsCallClientWrapper", "Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper;", "getCdsCallClientWrapper$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper;", "setCdsCallClientWrapper$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper;)V", "cdsConflictResolver", "Lcom/amazon/photos/uploader/cds/error/CdsConflictResolver;", "getCdsConflictResolver$AndroidPhotosUploader_release", "()Lcom/amazon/photos/uploader/cds/error/CdsConflictResolver;", "setCdsConflictResolver$AndroidPhotosUploader_release", "(Lcom/amazon/photos/uploader/cds/error/CdsConflictResolver;)V", "getMetrics$AndroidPhotosUploader_release", "()Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "uploadLogger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "uploaderMetrics", "Lcom/amazon/photos/uploader/internal/metrics/UploaderMetrics;", "urlConnectionProvider", "Lcom/amazon/photos/uploader/cds/UrlConnectionProvider;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/photos/uploader/Uploader;", "component1", "component2", "component2$AndroidPhotosUploader_release", "component3", "component4", "component4$AndroidPhotosUploader_release", "copy", "createCdsCallClientWrapper", "createConflictResolver", "fileUtils", "Lcom/amazon/photos/uploader/internal/utils/FileUtils;", "equals", "", "other", "hashCode", "", "toString", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CdsUploaderBuilder {
    @Nullable
    private Scheduler callbackScheduler;
    @NotNull
    private final CDClient cdClient;
    @NotNull
    public CdsCallClientWrapper cdsCallClientWrapper;
    @NotNull
    public CdsConflictResolver cdsConflictResolver;
    private final Logger logger;
    @NotNull
    private final Metrics metrics;
    private final UploadFrameworkContext uploadFrameworkContext;
    private UploadLogger uploadLogger;
    private final UploaderMetrics uploaderMetrics;
    private UrlConnectionProvider urlConnectionProvider;

    public CdsUploaderBuilder(@NotNull UploadFrameworkContext uploadFrameworkContext, @NotNull CDClient cdClient, @NotNull Logger logger, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.uploadFrameworkContext = uploadFrameworkContext;
        this.cdClient = cdClient;
        this.logger = logger;
        this.metrics = metrics;
        this.uploaderMetrics = new UploaderMetrics(this.metrics);
        this.uploadLogger = new UploadLogger(this.logger, UploadLogger.SecurityLevel.OBFUSCATED);
    }

    private final UploadFrameworkContext component1() {
        return this.uploadFrameworkContext;
    }

    private final Logger component3() {
        return this.logger;
    }

    public static /* synthetic */ CdsUploaderBuilder copy$default(CdsUploaderBuilder cdsUploaderBuilder, UploadFrameworkContext uploadFrameworkContext, CDClient cDClient, Logger logger, Metrics metrics, int i, Object obj) {
        if ((i & 1) != 0) {
            uploadFrameworkContext = cdsUploaderBuilder.uploadFrameworkContext;
        }
        if ((i & 2) != 0) {
            cDClient = cdsUploaderBuilder.cdClient;
        }
        if ((i & 4) != 0) {
            logger = cdsUploaderBuilder.logger;
        }
        if ((i & 8) != 0) {
            metrics = cdsUploaderBuilder.metrics;
        }
        return cdsUploaderBuilder.copy(uploadFrameworkContext, cDClient, logger, metrics);
    }

    private final CdsCallClientWrapper createCdsCallClientWrapper(CDClient cDClient) {
        return new CdsCallClientWrapper(cDClient, this.uploadLogger, this.uploaderMetrics);
    }

    private final CdsConflictResolver createConflictResolver(CdsCallClientWrapper cdsCallClientWrapper, FileUtils fileUtils) {
        return new CdsConflictResolverImpl(cdsCallClientWrapper, this.uploaderMetrics, this.uploadLogger, fileUtils, new FileSizeCategoryProvider());
    }

    @NotNull
    public final Uploader build() {
        FileUtils fileUtils = new FileUtils();
        this.cdsCallClientWrapper = createCdsCallClientWrapper(this.cdClient);
        CdsCallClientWrapper cdsCallClientWrapper = this.cdsCallClientWrapper;
        if (cdsCallClientWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cdsCallClientWrapper");
        }
        this.cdsConflictResolver = createConflictResolver(cdsCallClientWrapper, fileUtils);
        if (this.callbackScheduler == null) {
            this.callbackScheduler = Schedulers.io();
        }
        DaggerCdsUploaderComponent.Builder builder = DaggerCdsUploaderComponent.builder();
        UploadLogger uploadLogger = this.uploadLogger;
        UploaderMetrics uploaderMetrics = this.uploaderMetrics;
        UploadFrameworkContext uploadFrameworkContext = this.uploadFrameworkContext;
        Scheduler scheduler = this.callbackScheduler;
        CdsConflictResolver cdsConflictResolver = this.cdsConflictResolver;
        if (cdsConflictResolver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cdsConflictResolver");
        }
        CdsCallClientWrapper cdsCallClientWrapper2 = this.cdsCallClientWrapper;
        if (cdsCallClientWrapper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cdsCallClientWrapper");
        }
        CdsUploaderComponent build = builder.cdsUploaderConfigurationModule(new CdsUploaderConfigurationModule(uploadLogger, uploaderMetrics, uploadFrameworkContext, scheduler, cdsConflictResolver, cdsCallClientWrapper2, this.cdClient, new SystemUtil(this.uploadFrameworkContext.getApplicationContext(), false, 2, null), fileUtils, this.urlConnectionProvider)).multipartDatabaseModule(new MultipartDatabaseModule(this.uploadFrameworkContext)).nodeCacheDatabaseModule(new NodeCacheDatabaseModule(this.uploadFrameworkContext)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "DaggerCdsUploaderCompone…\n                .build()");
        return new CdsUploader(build);
    }

    @NotNull
    public final CdsUploaderBuilder callbackScheduler(@NotNull Scheduler callbackScheduler) {
        Intrinsics.checkParameterIsNotNull(callbackScheduler, "callbackScheduler");
        this.callbackScheduler = callbackScheduler;
        return this;
    }

    @NotNull
    public final CDClient component2$AndroidPhotosUploader_release() {
        return this.cdClient;
    }

    @NotNull
    public final Metrics component4$AndroidPhotosUploader_release() {
        return this.metrics;
    }

    @NotNull
    public final CdsUploaderBuilder copy(@NotNull UploadFrameworkContext uploadFrameworkContext, @NotNull CDClient cdClient, @NotNull Logger logger, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        return new CdsUploaderBuilder(uploadFrameworkContext, cdClient, logger, metrics);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof CdsUploaderBuilder)) {
                return false;
            }
            CdsUploaderBuilder cdsUploaderBuilder = (CdsUploaderBuilder) obj;
            return Intrinsics.areEqual(this.uploadFrameworkContext, cdsUploaderBuilder.uploadFrameworkContext) && Intrinsics.areEqual(this.cdClient, cdsUploaderBuilder.cdClient) && Intrinsics.areEqual(this.logger, cdsUploaderBuilder.logger) && Intrinsics.areEqual(this.metrics, cdsUploaderBuilder.metrics);
        }
        return true;
    }

    @Nullable
    public final Scheduler getCallbackScheduler$AndroidPhotosUploader_release() {
        return this.callbackScheduler;
    }

    @NotNull
    public final CDClient getCdClient$AndroidPhotosUploader_release() {
        return this.cdClient;
    }

    @NotNull
    public final CdsCallClientWrapper getCdsCallClientWrapper$AndroidPhotosUploader_release() {
        CdsCallClientWrapper cdsCallClientWrapper = this.cdsCallClientWrapper;
        if (cdsCallClientWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cdsCallClientWrapper");
        }
        return cdsCallClientWrapper;
    }

    @NotNull
    public final CdsConflictResolver getCdsConflictResolver$AndroidPhotosUploader_release() {
        CdsConflictResolver cdsConflictResolver = this.cdsConflictResolver;
        if (cdsConflictResolver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cdsConflictResolver");
        }
        return cdsConflictResolver;
    }

    @NotNull
    public final Metrics getMetrics$AndroidPhotosUploader_release() {
        return this.metrics;
    }

    public int hashCode() {
        UploadFrameworkContext uploadFrameworkContext = this.uploadFrameworkContext;
        int i = 0;
        int hashCode = (uploadFrameworkContext != null ? uploadFrameworkContext.hashCode() : 0) * 31;
        CDClient cDClient = this.cdClient;
        int hashCode2 = (hashCode + (cDClient != null ? cDClient.hashCode() : 0)) * 31;
        Logger logger = this.logger;
        int hashCode3 = (hashCode2 + (logger != null ? logger.hashCode() : 0)) * 31;
        Metrics metrics = this.metrics;
        if (metrics != null) {
            i = metrics.hashCode();
        }
        return hashCode3 + i;
    }

    public final void setCallbackScheduler$AndroidPhotosUploader_release(@Nullable Scheduler scheduler) {
        this.callbackScheduler = scheduler;
    }

    public final void setCdsCallClientWrapper$AndroidPhotosUploader_release(@NotNull CdsCallClientWrapper cdsCallClientWrapper) {
        Intrinsics.checkParameterIsNotNull(cdsCallClientWrapper, "<set-?>");
        this.cdsCallClientWrapper = cdsCallClientWrapper;
    }

    public final void setCdsConflictResolver$AndroidPhotosUploader_release(@NotNull CdsConflictResolver cdsConflictResolver) {
        Intrinsics.checkParameterIsNotNull(cdsConflictResolver, "<set-?>");
        this.cdsConflictResolver = cdsConflictResolver;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CdsUploaderBuilder(uploadFrameworkContext=");
        outline107.append(this.uploadFrameworkContext);
        outline107.append(", cdClient=");
        outline107.append(this.cdClient);
        outline107.append(", logger=");
        outline107.append(this.logger);
        outline107.append(", metrics=");
        outline107.append(this.metrics);
        outline107.append(")");
        return outline107.toString();
    }

    @NotNull
    public final CdsUploaderBuilder uploadLogger(@NotNull UploadLogger uploadLogger) {
        Intrinsics.checkParameterIsNotNull(uploadLogger, "uploadLogger");
        this.uploadLogger = uploadLogger;
        return this;
    }

    @NotNull
    public final CdsUploaderBuilder urlConnectionProvider(@NotNull UrlConnectionProvider urlConnectionProvider) {
        Intrinsics.checkParameterIsNotNull(urlConnectionProvider, "urlConnectionProvider");
        this.urlConnectionProvider = urlConnectionProvider;
        return this;
    }
}
