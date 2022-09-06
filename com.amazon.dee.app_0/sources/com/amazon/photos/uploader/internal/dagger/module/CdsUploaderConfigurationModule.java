package com.amazon.photos.uploader.internal.dagger.module;

import android.content.ContentResolver;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.clouddrive.cdasdk.util.MD5Fingerprint;
import com.amazon.imageutilities.JpegVisualDigest;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.cds.CdsCallClientWrapper;
import com.amazon.photos.uploader.cds.CdsSinglePartUploader;
import com.amazon.photos.uploader.cds.CdsUtil;
import com.amazon.photos.uploader.cds.ParentIdCache;
import com.amazon.photos.uploader.cds.ParentIdCacheImpl;
import com.amazon.photos.uploader.cds.ParentNodeFetcher;
import com.amazon.photos.uploader.cds.PartProvider;
import com.amazon.photos.uploader.cds.UrlConnectionProvider;
import com.amazon.photos.uploader.cds.UrlConnectionUploader;
import com.amazon.photos.uploader.cds.error.CdsConflictResolver;
import com.amazon.photos.uploader.cds.error.CdsUploadErrorResolver;
import com.amazon.photos.uploader.cds.error.CdsUploadPartErrorResolver;
import com.amazon.photos.uploader.cds.error.MultiPartUploadErrorResolver;
import com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader;
import com.amazon.photos.uploader.cds.multipart.InitializeMultiPartRequestBuilder;
import com.amazon.photos.uploader.cds.multipart.MultipartTransactionRunner;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadCompleter;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadInitiator;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadNodeFetcher;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadRequestMetadataDao;
import com.amazon.photos.uploader.cds.multipart.MultipartUploadVerifier;
import com.amazon.photos.uploader.cds.multipart.PartInfoDao;
import com.amazon.photos.uploader.cds.multipart.PartUploader;
import com.amazon.photos.uploader.cds.nodecache.ParentIdDao;
import com.amazon.photos.uploader.cds.observer.CdsUploadNotifier;
import com.amazon.photos.uploader.cds.observer.CdsUploadObservable;
import com.amazon.photos.uploader.internal.DestroyableDatabaseWrapper;
import com.amazon.photos.uploader.internal.OpenForTesting;
import com.amazon.photos.uploader.internal.contentsignature.ContentSignatureProvider;
import com.amazon.photos.uploader.internal.dagger.PerAccount;
import com.amazon.photos.uploader.internal.utils.FileUtils;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import com.amazon.photos.uploader.internal.workers.CoroutineWorkerUtil;
import com.amazon.photos.uploader.log.UploadLogger;
import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Named;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CdsUploaderConfigurationModule.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000ú\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\u0002\u0010\u0016J\r\u0010\u0017\u001a\u00020\rH\u0001¢\u0006\u0002\b\u0018J\r\u0010\u0019\u001a\u00020\u000bH\u0001¢\u0006\u0002\b\u001aJ\r\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001dJ\u0015\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001cH\u0001¢\u0006\u0002\b!J\r\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$J\r\u0010%\u001a\u00020\u0013H\u0001¢\u0006\u0002\b&J\r\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\b)J\r\u0010*\u001a\u00020\u0005H\u0001¢\u0006\u0002\b+J-\u0010,\u001a\u00020-2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0001¢\u0006\u0002\b2JE\u00103\u001a\u0002042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00105\u001a\u0002062\u0006\u0010.\u001a\u00020/2\u0006\u00107\u001a\u0002082\u0006\u00100\u001a\u0002012\u0006\u00109\u001a\u00020(H\u0001¢\u0006\u0002\b:J-\u0010;\u001a\u00020<2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010.\u001a\u00020/2\u0006\u00105\u001a\u000206H\u0001¢\u0006\u0002\b=J%\u0010>\u001a\u00020?2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00100\u001a\u000201H\u0001¢\u0006\u0002\b@J'\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\b\b\u0001\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u001fH\u0001¢\u0006\u0002\bHJ\r\u0010I\u001a\u00020JH\u0001¢\u0006\u0002\bKJ=\u0010L\u001a\u00020M2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00100\u001a\u0002012\u0006\u0010N\u001a\u00020#2\u0006\u0010O\u001a\u00020J2\u0006\u0010P\u001a\u00020QH\u0001¢\u0006\u0002\bRJ\r\u0010S\u001a\u00020\tH\u0001¢\u0006\u0002\bTJ\r\u0010U\u001a\u00020\u0007H\u0001¢\u0006\u0002\bVJ\r\u0010W\u001a\u00020\u0003H\u0001¢\u0006\u0002\bXJ\r\u0010Y\u001a\u00020\u000fH\u0001¢\u0006\u0002\bZJ\u001d\u0010[\u001a\u00020\\2\u0006\u00107\u001a\u0002082\u0006\u0010]\u001a\u00020^H\u0001¢\u0006\u0002\b_J\u008f\u0001\u0010`\u001a\u00020a2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020/2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u00100\u001a\u0002012\u0006\u0010N\u001a\u00020#2\b\b\u0001\u0010b\u001a\u00020F2\u0006\u0010O\u001a\u00020J2\u0006\u0010c\u001a\u0002042\u0006\u0010d\u001a\u00020M2\u0006\u0010e\u001a\u00020-2\u0006\u0010f\u001a\u00020?2\u0006\u0010g\u001a\u00020<2\u0006\u0010h\u001a\u00020iH\u0001¢\u0006\u0002\bjJ-\u0010k\u001a\u00020l2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020/2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00105\u001a\u000206H\u0001¢\u0006\u0002\bmJ-\u0010n\u001a\u00020/2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010o\u001a\u00020\\2\u0006\u0010 \u001a\u00020\u001cH\u0001¢\u0006\u0002\bpJ\r\u0010q\u001a\u00020QH\u0001¢\u0006\u0002\brJ\r\u0010s\u001a\u00020^H\u0001¢\u0006\u0002\btJ\r\u0010u\u001a\u00020vH\u0001¢\u0006\u0002\bwJ\u001d\u0010x\u001a\u0002062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010y\u001a\u00020BH\u0001¢\u0006\u0002\bzR\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006{"}, d2 = {"Lcom/amazon/photos/uploader/internal/dagger/module/CdsUploaderConfigurationModule;", "", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "uploadFrameworkContext", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "callbackScheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "cdsConflictResolver", "Lcom/amazon/photos/uploader/cds/error/CdsConflictResolver;", "cdsCallClientWrapper", "Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper;", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "systemUtil", "Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "fileUtils", "Lcom/amazon/photos/uploader/internal/utils/FileUtils;", "urlConnectionProvider", "Lcom/amazon/photos/uploader/cds/UrlConnectionProvider;", "(Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/UploadFrameworkContext;Lio/reactivex/rxjava3/core/Scheduler;Lcom/amazon/photos/uploader/cds/error/CdsConflictResolver;Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper;Lcom/amazon/clouddrive/cdasdk/CDClient;Lcom/amazon/photos/uploader/internal/utils/SystemUtil;Lcom/amazon/photos/uploader/internal/utils/FileUtils;Lcom/amazon/photos/uploader/cds/UrlConnectionProvider;)V", "provideCdsCallClientWrapper", "provideCdsCallClientWrapper$AndroidPhotosUploader_release", "provideCdsConflictResolver", "provideCdsConflictResolver$AndroidPhotosUploader_release", "provideCdsUploadNotifier", "Lcom/amazon/photos/uploader/cds/observer/CdsUploadNotifier;", "provideCdsUploadNotifier$AndroidPhotosUploader_release", "provideCdsUploadObservable", "Lcom/amazon/photos/uploader/cds/observer/CdsUploadObservable;", "cdsUploadNotifier", "provideCdsUploadObservable$AndroidPhotosUploader_release", "provideContentSignatureProvider", "Lcom/amazon/photos/uploader/internal/contentsignature/ContentSignatureProvider;", "provideContentSignatureProvider$AndroidPhotosUploader_release", "provideFileUtils", "provideFileUtils$AndroidPhotosUploader_release", "provideInitializeMultiPartRequestBuilder", "Lcom/amazon/photos/uploader/cds/multipart/InitializeMultiPartRequestBuilder;", "provideInitializeMultiPartRequestBuilder$AndroidPhotosUploader_release", "provideMetrics", "provideMetrics$AndroidPhotosUploader_release", "provideMultipartUploadCompleter", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadCompleter;", "cdsErrorResolver", "Lcom/amazon/photos/uploader/cds/error/CdsUploadErrorResolver;", "partInfoDao", "Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;", "provideMultipartUploadCompleter$AndroidPhotosUploader_release", "provideMultipartUploadInitiator", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadInitiator;", "parentNodeFetcher", "Lcom/amazon/photos/uploader/cds/ParentNodeFetcher;", "multipartUploadRequestMetadataDao", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadRequestMetadataDao;", "initializeMultiPartRequestBuilder", "provideMultipartUploadInitiator$AndroidPhotosUploader_release", "provideMultipartUploadNodeFetcher", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadNodeFetcher;", "provideMultipartUploadNodeFetcher$AndroidPhotosUploader_release", "provideMultipartUploadVerifier", "Lcom/amazon/photos/uploader/cds/multipart/MultipartUploadVerifier;", "provideMultipartUploadVerifier$AndroidPhotosUploader_release", "provideParentIdCache", "Lcom/amazon/photos/uploader/cds/ParentIdCache;", "parentIdDao", "Lcom/amazon/photos/uploader/cds/nodecache/ParentIdDao;", "nodeCacheDBWrapper", "Lcom/amazon/photos/uploader/internal/DestroyableDatabaseWrapper;", "cdsUploadObservable", "provideParentIdCache$AndroidPhotosUploader_release", "providePartProvider", "Lcom/amazon/photos/uploader/cds/PartProvider;", "providePartProvider$AndroidPhotosUploader_release", "providePartUploader", "Lcom/amazon/photos/uploader/cds/multipart/PartUploader;", "contentSignatureProvider", "partProvider", "partUploadErrorResolver", "Lcom/amazon/photos/uploader/cds/error/CdsUploadPartErrorResolver;", "providePartUploader$AndroidPhotosUploader_release", "provideScheduler", "provideScheduler$AndroidPhotosUploader_release", "provideUploadFrameworkContext", "provideUploadFrameworkContext$AndroidPhotosUploader_release", "provideUploadLogger", "provideUploadLogger$AndroidPhotosUploader_release", "providesCDCClient", "providesCDCClient$AndroidPhotosUploader_release", "providesCdsMultiPartErrorResolver", "Lcom/amazon/photos/uploader/cds/error/MultiPartUploadErrorResolver;", "cdsUtil", "Lcom/amazon/photos/uploader/cds/CdsUtil;", "providesCdsMultiPartErrorResolver$AndroidPhotosUploader_release", "providesCdsMultiPartUploader", "Lcom/amazon/photos/uploader/cds/multipart/CdsMultiPartUploader;", "destroyableDatabaseWrapper", "multipartUploadInitiator", "partUploader", "multipartUploadCompleter", "multipartUploadVerifier", "multipartUploadNodeFetcher", "multipartTransactionRunner", "Lcom/amazon/photos/uploader/cds/multipart/MultipartTransactionRunner;", "providesCdsMultiPartUploader$AndroidPhotosUploader_release", "providesCdsSinglePartUploader", "Lcom/amazon/photos/uploader/cds/CdsSinglePartUploader;", "providesCdsSinglePartUploader$AndroidPhotosUploader_release", "providesCdsUploadErrorResolver", "multiPartUploadErrorResolver", "providesCdsUploadErrorResolver$AndroidPhotosUploader_release", "providesCdsUploadPartErrorResolver", "providesCdsUploadPartErrorResolver$AndroidPhotosUploader_release", "providesCdsUtil", "providesCdsUtil$AndroidPhotosUploader_release", "providesCoroutineWorkerUtil", "Lcom/amazon/photos/uploader/internal/workers/CoroutineWorkerUtil;", "providesCoroutineWorkerUtil$AndroidPhotosUploader_release", "providesParentNodeFetcher", "parentIdCache", "providesParentNodeFetcher$AndroidPhotosUploader_release", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes13.dex */
public final class CdsUploaderConfigurationModule {
    private Scheduler callbackScheduler;
    private CDClient cdClient;
    private final CdsCallClientWrapper cdsCallClientWrapper;
    private final CdsConflictResolver cdsConflictResolver;
    private final FileUtils fileUtils;
    private final UploadLogger logger;
    private final Metrics metrics;
    private final SystemUtil systemUtil;
    private final UploadFrameworkContext uploadFrameworkContext;
    private final UrlConnectionProvider urlConnectionProvider;

    public CdsUploaderConfigurationModule(@NotNull UploadLogger logger, @NotNull Metrics metrics, @NotNull UploadFrameworkContext uploadFrameworkContext, @Nullable Scheduler scheduler, @NotNull CdsConflictResolver cdsConflictResolver, @NotNull CdsCallClientWrapper cdsCallClientWrapper, @NotNull CDClient cdClient, @NotNull SystemUtil systemUtil, @NotNull FileUtils fileUtils, @Nullable UrlConnectionProvider urlConnectionProvider) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        Intrinsics.checkParameterIsNotNull(cdsConflictResolver, "cdsConflictResolver");
        Intrinsics.checkParameterIsNotNull(cdsCallClientWrapper, "cdsCallClientWrapper");
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        Intrinsics.checkParameterIsNotNull(fileUtils, "fileUtils");
        this.logger = logger;
        this.metrics = metrics;
        this.uploadFrameworkContext = uploadFrameworkContext;
        this.callbackScheduler = scheduler;
        this.cdsConflictResolver = cdsConflictResolver;
        this.cdsCallClientWrapper = cdsCallClientWrapper;
        this.cdClient = cdClient;
        this.systemUtil = systemUtil;
        this.fileUtils = fileUtils;
        this.urlConnectionProvider = urlConnectionProvider;
    }

    @Provides
    @PerAccount
    @NotNull
    public final CdsCallClientWrapper provideCdsCallClientWrapper$AndroidPhotosUploader_release() {
        return this.cdsCallClientWrapper;
    }

    @Provides
    @PerAccount
    @NotNull
    public final CdsConflictResolver provideCdsConflictResolver$AndroidPhotosUploader_release() {
        return this.cdsConflictResolver;
    }

    @Provides
    @PerAccount
    @NotNull
    public final CdsUploadNotifier provideCdsUploadNotifier$AndroidPhotosUploader_release() {
        return new CdsUploadNotifier();
    }

    @Provides
    @PerAccount
    @NotNull
    public final CdsUploadObservable provideCdsUploadObservable$AndroidPhotosUploader_release(@NotNull CdsUploadNotifier cdsUploadNotifier) {
        Intrinsics.checkParameterIsNotNull(cdsUploadNotifier, "cdsUploadNotifier");
        return cdsUploadNotifier;
    }

    @Provides
    @PerAccount
    @NotNull
    public final ContentSignatureProvider provideContentSignatureProvider$AndroidPhotosUploader_release() {
        return new ContentSignatureProvider(new JpegVisualDigest(MessageDigestAlgorithms.MD5), new MD5Fingerprint(), this.metrics, this.logger);
    }

    @Provides
    @PerAccount
    @NotNull
    public final FileUtils provideFileUtils$AndroidPhotosUploader_release() {
        return this.fileUtils;
    }

    @Provides
    @PerAccount
    @NotNull
    public final InitializeMultiPartRequestBuilder provideInitializeMultiPartRequestBuilder$AndroidPhotosUploader_release() {
        return new InitializeMultiPartRequestBuilder();
    }

    @Provides
    @PerAccount
    @NotNull
    public final Metrics provideMetrics$AndroidPhotosUploader_release() {
        return this.metrics;
    }

    @Provides
    @NotNull
    public final MultipartUploadCompleter provideMultipartUploadCompleter$AndroidPhotosUploader_release(@NotNull CDClient cdClient, @NotNull Metrics metrics, @NotNull CdsUploadErrorResolver cdsErrorResolver, @NotNull PartInfoDao partInfoDao) {
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(cdsErrorResolver, "cdsErrorResolver");
        Intrinsics.checkParameterIsNotNull(partInfoDao, "partInfoDao");
        Scheduler io2 = Schedulers.io();
        Intrinsics.checkExpressionValueIsNotNull(io2, "Schedulers.io()");
        return new MultipartUploadCompleter(cdClient, io2, this.logger, partInfoDao);
    }

    @Provides
    @NotNull
    public final MultipartUploadInitiator provideMultipartUploadInitiator$AndroidPhotosUploader_release(@NotNull CDClient cdClient, @NotNull Metrics metrics, @NotNull ParentNodeFetcher parentNodeFetcher, @NotNull CdsUploadErrorResolver cdsErrorResolver, @NotNull MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao, @NotNull PartInfoDao partInfoDao, @NotNull InitializeMultiPartRequestBuilder initializeMultiPartRequestBuilder) {
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(parentNodeFetcher, "parentNodeFetcher");
        Intrinsics.checkParameterIsNotNull(cdsErrorResolver, "cdsErrorResolver");
        Intrinsics.checkParameterIsNotNull(multipartUploadRequestMetadataDao, "multipartUploadRequestMetadataDao");
        Intrinsics.checkParameterIsNotNull(partInfoDao, "partInfoDao");
        Intrinsics.checkParameterIsNotNull(initializeMultiPartRequestBuilder, "initializeMultiPartRequestBuilder");
        return new MultipartUploadInitiator(cdClient, this.logger, metrics, parentNodeFetcher, multipartUploadRequestMetadataDao, partInfoDao, initializeMultiPartRequestBuilder);
    }

    @Provides
    @NotNull
    public final MultipartUploadNodeFetcher provideMultipartUploadNodeFetcher$AndroidPhotosUploader_release(@NotNull CDClient cdClient, @NotNull Metrics metrics, @NotNull CdsUploadErrorResolver cdsErrorResolver, @NotNull ParentNodeFetcher parentNodeFetcher) {
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(cdsErrorResolver, "cdsErrorResolver");
        Intrinsics.checkParameterIsNotNull(parentNodeFetcher, "parentNodeFetcher");
        Scheduler io2 = Schedulers.io();
        Intrinsics.checkExpressionValueIsNotNull(io2, "Schedulers.io()");
        return new MultipartUploadNodeFetcher(io2, this.cdsCallClientWrapper);
    }

    @Provides
    @NotNull
    public final MultipartUploadVerifier provideMultipartUploadVerifier$AndroidPhotosUploader_release(@NotNull CDClient cdClient, @NotNull Metrics metrics, @NotNull PartInfoDao partInfoDao) {
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(partInfoDao, "partInfoDao");
        return new MultipartUploadVerifier(cdClient, this.logger, metrics, partInfoDao);
    }

    @Provides
    @PerAccount
    @NotNull
    public final ParentIdCache provideParentIdCache$AndroidPhotosUploader_release(@NotNull ParentIdDao parentIdDao, @Named("NodeCacheDestroyable") @NotNull DestroyableDatabaseWrapper nodeCacheDBWrapper, @NotNull CdsUploadObservable cdsUploadObservable) {
        Intrinsics.checkParameterIsNotNull(parentIdDao, "parentIdDao");
        Intrinsics.checkParameterIsNotNull(nodeCacheDBWrapper, "nodeCacheDBWrapper");
        Intrinsics.checkParameterIsNotNull(cdsUploadObservable, "cdsUploadObservable");
        return new ParentIdCacheImpl(parentIdDao, nodeCacheDBWrapper, cdsUploadObservable);
    }

    @Provides
    @PerAccount
    @NotNull
    public final PartProvider providePartProvider$AndroidPhotosUploader_release() {
        ContentResolver contentResolver = this.uploadFrameworkContext.getApplicationContext().getContentResolver();
        Intrinsics.checkExpressionValueIsNotNull(contentResolver, "uploadFrameworkContext.a…onContext.contentResolver");
        return new PartProvider(contentResolver);
    }

    @Provides
    @NotNull
    public final PartUploader providePartUploader$AndroidPhotosUploader_release(@NotNull CDClient cdClient, @NotNull Metrics metrics, @NotNull PartInfoDao partInfoDao, @NotNull ContentSignatureProvider contentSignatureProvider, @NotNull PartProvider partProvider, @NotNull CdsUploadPartErrorResolver partUploadErrorResolver) {
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(partInfoDao, "partInfoDao");
        Intrinsics.checkParameterIsNotNull(contentSignatureProvider, "contentSignatureProvider");
        Intrinsics.checkParameterIsNotNull(partProvider, "partProvider");
        Intrinsics.checkParameterIsNotNull(partUploadErrorResolver, "partUploadErrorResolver");
        Scheduler io2 = Schedulers.io();
        Intrinsics.checkExpressionValueIsNotNull(io2, "Schedulers.io()");
        Scheduler io3 = Schedulers.io();
        Intrinsics.checkExpressionValueIsNotNull(io3, "Schedulers.io()");
        return new PartUploader(cdClient, io2, io3, this.logger, metrics, partProvider, partInfoDao, contentSignatureProvider, partUploadErrorResolver);
    }

    @Provides
    @PerAccount
    @NotNull
    public final Scheduler provideScheduler$AndroidPhotosUploader_release() {
        if (this.callbackScheduler == null) {
            this.callbackScheduler = Schedulers.io();
        }
        Scheduler scheduler = this.callbackScheduler;
        if (scheduler != null) {
            return scheduler;
        }
        throw new TypeCastException("null cannot be cast to non-null type io.reactivex.rxjava3.core.Scheduler");
    }

    @Provides
    @PerAccount
    @NotNull
    public final UploadFrameworkContext provideUploadFrameworkContext$AndroidPhotosUploader_release() {
        return this.uploadFrameworkContext;
    }

    @Provides
    @PerAccount
    @NotNull
    public final UploadLogger provideUploadLogger$AndroidPhotosUploader_release() {
        return this.logger;
    }

    @Provides
    @PerAccount
    @NotNull
    public final CDClient providesCDCClient$AndroidPhotosUploader_release() {
        return this.cdClient;
    }

    @Provides
    @PerAccount
    @NotNull
    public final MultiPartUploadErrorResolver providesCdsMultiPartErrorResolver$AndroidPhotosUploader_release(@NotNull MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao, @NotNull CdsUtil cdsUtil) {
        Intrinsics.checkParameterIsNotNull(multipartUploadRequestMetadataDao, "multipartUploadRequestMetadataDao");
        Intrinsics.checkParameterIsNotNull(cdsUtil, "cdsUtil");
        return new MultiPartUploadErrorResolver(multipartUploadRequestMetadataDao, cdsUtil);
    }

    @Provides
    @PerAccount
    @NotNull
    public final CdsMultiPartUploader providesCdsMultiPartUploader$AndroidPhotosUploader_release(@NotNull UploadFrameworkContext uploadFrameworkContext, @NotNull CDClient cdClient, @NotNull CdsUploadErrorResolver cdsErrorResolver, @NotNull Metrics metrics, @NotNull ParentNodeFetcher parentNodeFetcher, @NotNull MultipartUploadRequestMetadataDao multipartUploadRequestMetadataDao, @NotNull PartInfoDao partInfoDao, @NotNull ContentSignatureProvider contentSignatureProvider, @Named("MultipartDestroyable") @NotNull DestroyableDatabaseWrapper destroyableDatabaseWrapper, @NotNull PartProvider partProvider, @NotNull MultipartUploadInitiator multipartUploadInitiator, @NotNull PartUploader partUploader, @NotNull MultipartUploadCompleter multipartUploadCompleter, @NotNull MultipartUploadVerifier multipartUploadVerifier, @NotNull MultipartUploadNodeFetcher multipartUploadNodeFetcher, @NotNull MultipartTransactionRunner multipartTransactionRunner) {
        Intrinsics.checkParameterIsNotNull(uploadFrameworkContext, "uploadFrameworkContext");
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(cdsErrorResolver, "cdsErrorResolver");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(parentNodeFetcher, "parentNodeFetcher");
        Intrinsics.checkParameterIsNotNull(multipartUploadRequestMetadataDao, "multipartUploadRequestMetadataDao");
        Intrinsics.checkParameterIsNotNull(partInfoDao, "partInfoDao");
        Intrinsics.checkParameterIsNotNull(contentSignatureProvider, "contentSignatureProvider");
        Intrinsics.checkParameterIsNotNull(destroyableDatabaseWrapper, "destroyableDatabaseWrapper");
        Intrinsics.checkParameterIsNotNull(partProvider, "partProvider");
        Intrinsics.checkParameterIsNotNull(multipartUploadInitiator, "multipartUploadInitiator");
        Intrinsics.checkParameterIsNotNull(partUploader, "partUploader");
        Intrinsics.checkParameterIsNotNull(multipartUploadCompleter, "multipartUploadCompleter");
        Intrinsics.checkParameterIsNotNull(multipartUploadVerifier, "multipartUploadVerifier");
        Intrinsics.checkParameterIsNotNull(multipartUploadNodeFetcher, "multipartUploadNodeFetcher");
        Intrinsics.checkParameterIsNotNull(multipartTransactionRunner, "multipartTransactionRunner");
        return new CdsMultiPartUploader(uploadFrameworkContext, this.logger, metrics, partInfoDao, multipartUploadRequestMetadataDao, destroyableDatabaseWrapper, multipartUploadInitiator, partUploader, cdsErrorResolver, multipartUploadCompleter, multipartUploadVerifier, multipartUploadNodeFetcher, multipartTransactionRunner);
    }

    @Provides
    @PerAccount
    @NotNull
    public final CdsSinglePartUploader providesCdsSinglePartUploader$AndroidPhotosUploader_release(@NotNull CDClient cdClient, @NotNull CdsUploadErrorResolver cdsErrorResolver, @NotNull Metrics metrics, @NotNull ParentNodeFetcher parentNodeFetcher) {
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(cdsErrorResolver, "cdsErrorResolver");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(parentNodeFetcher, "parentNodeFetcher");
        UrlConnectionProvider urlConnectionProvider = this.urlConnectionProvider;
        UrlConnectionUploader urlConnectionUploader = urlConnectionProvider != null ? new UrlConnectionUploader(cdClient, this.logger, urlConnectionProvider, metrics, this.uploadFrameworkContext) : null;
        Scheduler io2 = Schedulers.io();
        Intrinsics.checkExpressionValueIsNotNull(io2, "Schedulers.io()");
        Scheduler scheduler = this.callbackScheduler;
        if (scheduler != null) {
            return new CdsSinglePartUploader(cdClient, io2, scheduler, cdsErrorResolver, this.logger, metrics, parentNodeFetcher, this.uploadFrameworkContext.getCrashReporter(), urlConnectionUploader);
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    @Provides
    @PerAccount
    @NotNull
    public final CdsUploadErrorResolver providesCdsUploadErrorResolver$AndroidPhotosUploader_release(@NotNull Metrics metrics, @NotNull CdsConflictResolver cdsConflictResolver, @NotNull MultiPartUploadErrorResolver multiPartUploadErrorResolver, @NotNull CdsUploadNotifier cdsUploadNotifier) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(cdsConflictResolver, "cdsConflictResolver");
        Intrinsics.checkParameterIsNotNull(multiPartUploadErrorResolver, "multiPartUploadErrorResolver");
        Intrinsics.checkParameterIsNotNull(cdsUploadNotifier, "cdsUploadNotifier");
        return new CdsUploadErrorResolver(metrics, cdsConflictResolver, multiPartUploadErrorResolver, cdsUploadNotifier);
    }

    @Provides
    @PerAccount
    @NotNull
    public final CdsUploadPartErrorResolver providesCdsUploadPartErrorResolver$AndroidPhotosUploader_release() {
        return new CdsUploadPartErrorResolver(this.metrics);
    }

    @Provides
    @PerAccount
    @NotNull
    public final CdsUtil providesCdsUtil$AndroidPhotosUploader_release() {
        return new CdsUtil(this.cdsCallClientWrapper, this.systemUtil);
    }

    @Provides
    @PerAccount
    @NotNull
    public final CoroutineWorkerUtil providesCoroutineWorkerUtil$AndroidPhotosUploader_release() {
        return new CoroutineWorkerUtil();
    }

    @Provides
    @PerAccount
    @NotNull
    public final ParentNodeFetcher providesParentNodeFetcher$AndroidPhotosUploader_release(@NotNull CdsCallClientWrapper cdsCallClientWrapper, @NotNull ParentIdCache parentIdCache) {
        Intrinsics.checkParameterIsNotNull(cdsCallClientWrapper, "cdsCallClientWrapper");
        Intrinsics.checkParameterIsNotNull(parentIdCache, "parentIdCache");
        return new ParentNodeFetcher(cdsCallClientWrapper, this.logger, parentIdCache);
    }
}
