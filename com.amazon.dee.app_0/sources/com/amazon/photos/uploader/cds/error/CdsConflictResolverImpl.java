package com.amazon.photos.uploader.cds.error;

import android.os.Build;
import androidx.annotation.VisibleForTesting;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.amazon.clouddrive.cdasdk.cds.common.ContentProperties;
import com.amazon.clouddrive.cdasdk.cds.common.ContentSignature;
import com.amazon.clouddrive.cdasdk.cds.common.ContentSignatureType;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfoResponse;
import com.amazon.dee.app.ui.web.AlexaDeviceBackgroundImageBridge;
import com.amazon.photos.uploader.FileSizeCategoryProvider;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.cds.CdsCallClientWrapper;
import com.amazon.photos.uploader.cds.CdsMetrics;
import com.amazon.photos.uploader.cds.CdsUploader;
import com.amazon.photos.uploader.internal.utils.FileUtils;
import com.amazon.photos.uploader.internal.utils.FileUtilsKt;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: CdsConflictResolverImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 -2\u00020\u0001:\u0002-.B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0001¢\u0006\u0002\b\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0010H\u0002J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u0010\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0015\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000eH\u0001¢\u0006\u0002\b J\u0010\u0010!\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0010H\u0002J \u0010$\u001a\u00020%2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020(H\u0016J \u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010'\u001a\u00020(H\u0002J\u001d\u0010+\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\u0013H\u0001¢\u0006\u0002\b,R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/amazon/photos/uploader/cds/error/CdsConflictResolverImpl;", "Lcom/amazon/photos/uploader/cds/error/CdsConflictResolver;", "cdsCallClientWrapper", "Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "fileUtils", "Lcom/amazon/photos/uploader/internal/utils/FileUtils;", "categoryProvider", "Lcom/amazon/photos/uploader/FileSizeCategoryProvider;", "(Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/photos/uploader/internal/utils/FileUtils;Lcom/amazon/photos/uploader/FileSizeCategoryProvider;)V", "createFileForPath", "Ljava/io/File;", "filePath", "", "createFileForPath$AndroidPhotosUploader_release", "getCloudNode", "Lcom/amazon/clouddrive/cdasdk/cds/common/NodeInfoResponse;", AlexaDeviceBackgroundImageBridge.KEY_NODE_ID, "getFileExtension", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "getFileType", "handleException", "", "throwable", "", "isFileJpeg", "", "file", "isFileJpeg$AndroidPhotosUploader_release", "recordDetailedMetrics", "replaceSpaces", "stringWithSpaces", "resolveConflict", "Lcom/amazon/photos/uploader/UploadResponse;", "conflictNodeId", "uploadException", "Lcom/amazon/photos/uploader/cds/error/UploadException;", "resolveNodeConflict", "cloudNode", "sizeDifferenceWithinThresholdForJpeg", "sizeDifferenceWithinThresholdForJpeg$AndroidPhotosUploader_release", "Companion", "ConflictResolutionException", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CdsConflictResolverImpl implements CdsConflictResolver {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final long SIZE_THRESHOLD_BYTES = 5000;
    private static final String TAG = "CdsConflictResolver";
    private final FileSizeCategoryProvider categoryProvider;
    private final CdsCallClientWrapper cdsCallClientWrapper;
    private final FileUtils fileUtils;
    private final UploadLogger logger;
    private final Metrics metrics;

    /* compiled from: CdsConflictResolverImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/photos/uploader/cds/error/CdsConflictResolverImpl$Companion;", "", "()V", "SIZE_THRESHOLD_BYTES", "", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: CdsConflictResolverImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/photos/uploader/cds/error/CdsConflictResolverImpl$ConflictResolutionException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class ConflictResolutionException extends Exception {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ConflictResolutionException(@NotNull String message, @NotNull Throwable cause) {
            super(message, cause);
            Intrinsics.checkParameterIsNotNull(message, "message");
            Intrinsics.checkParameterIsNotNull(cause, "cause");
        }
    }

    public CdsConflictResolverImpl(@NotNull CdsCallClientWrapper cdsCallClientWrapper, @NotNull Metrics metrics, @NotNull UploadLogger logger, @NotNull FileUtils fileUtils, @NotNull FileSizeCategoryProvider categoryProvider) {
        Intrinsics.checkParameterIsNotNull(cdsCallClientWrapper, "cdsCallClientWrapper");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(fileUtils, "fileUtils");
        Intrinsics.checkParameterIsNotNull(categoryProvider, "categoryProvider");
        this.cdsCallClientWrapper = cdsCallClientWrapper;
        this.metrics = metrics;
        this.logger = logger;
        this.fileUtils = fileUtils;
        this.categoryProvider = categoryProvider;
    }

    private final NodeInfoResponse getCloudNode(String str) throws CloudDriveException, IOException, InterruptedIOException {
        NodeInfoResponse node$AndroidPhotosUploader_release = this.cdsCallClientWrapper.getNode$AndroidPhotosUploader_release(str);
        if (node$AndroidPhotosUploader_release.getContentProperties() == null) {
            this.metrics.recordSimpleEvent(TAG, CdsConflictResolverImpl$getCloudNode$1.INSTANCE, new MetricRecordingType[0]);
            return null;
        }
        return node$AndroidPhotosUploader_release;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getFileType(UploadRequest uploadRequest) {
        return this.fileUtils.isSinglePartUploadAllowed(createFileForPath$AndroidPhotosUploader_release(uploadRequest.getFilePath())) ? "SMALL" : "BIG";
    }

    private final void handleException(final Throwable th) {
        this.logger.e$AndroidPhotosUploader_release(TAG, "Caught exception while resolving the conflict.", th);
        this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.error.CdsConflictResolverImpl$handleException$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CONFLICT_RESOLUTION_EXCEPTION_");
                outline107.append(th.getClass().getSimpleName());
                return outline107.toString();
            }
        }, new MetricRecordingType[0]);
    }

    private final void recordDetailedMetrics(final UploadRequest uploadRequest) {
        this.metrics.recordCustomMetric(TAG, new ClientMetric().addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.error.CdsConflictResolverImpl$recordDetailedMetrics$clientMetric$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                String replaceSpaces;
                CdsConflictResolverImpl cdsConflictResolverImpl = CdsConflictResolverImpl.this;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MANUFACTURER_");
                outline107.append(Build.MANUFACTURER);
                replaceSpaces = cdsConflictResolverImpl.replaceSpaces(outline107.toString());
                return replaceSpaces;
            }
        }, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.error.CdsConflictResolverImpl$recordDetailedMetrics$clientMetric$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                String replaceSpaces;
                CdsConflictResolverImpl cdsConflictResolverImpl = CdsConflictResolverImpl.this;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MODEL_");
                outline107.append(Build.MODEL);
                replaceSpaces = cdsConflictResolverImpl.replaceSpaces(outline107.toString());
                return replaceSpaces;
            }
        }, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.error.CdsConflictResolverImpl$recordDetailedMetrics$clientMetric$3
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                String fileType;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FILE_TYPE_");
                fileType = CdsConflictResolverImpl.this.getFileType(uploadRequest);
                outline107.append(fileType);
                return outline107.toString();
            }
        }, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.error.CdsConflictResolverImpl$recordDetailedMetrics$clientMetric$4
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EXTENSION_");
                outline107.append(CdsConflictResolverImpl.this.getFileExtension(uploadRequest));
                return outline107.toString();
            }
        }, 1), new MetricRecordingType[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String replaceSpaces(String str) {
        String replace$default;
        replace$default = StringsKt__StringsJVMKt.replace$default(str, " ", "_", false, 4, (Object) null);
        return replace$default;
    }

    private final UploadResponse resolveNodeConflict(NodeInfoResponse nodeInfoResponse, UploadRequest uploadRequest, UploadException uploadException) {
        UploadRequest copy;
        Object obj;
        boolean z;
        ContentProperties contentProperties = nodeInfoResponse.getContentProperties();
        Intrinsics.checkExpressionValueIsNotNull(contentProperties, "cloudNode.contentProperties");
        if (Intrinsics.areEqual(contentProperties.getMd5(), uploadRequest.getMd5())) {
            final FileSizeCategoryProvider.FileSizeCategory fileSizeCategory$AndroidPhotosUploader_release = this.categoryProvider.getFileSizeCategory$AndroidPhotosUploader_release(uploadRequest.getFilePath());
            this.metrics.recordSimpleEvent(TAG, CdsConflictResolverImpl$resolveNodeConflict$1.INSTANCE, new MetricRecordingType[0]);
            this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.error.CdsConflictResolverImpl$resolveNodeConflict$2
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107(CdsMetrics.CONFLICT_RESOLUTION_SIZE_CATEGORY_MERGE_WITH_CLOUD_MD5);
                    outline107.append(FileSizeCategoryProvider.FileSizeCategory.this.name());
                    return outline107.toString();
                }
            }, new MetricRecordingType[0]);
            this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.error.CdsConflictResolverImpl$resolveNodeConflict$3
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107(CdsMetrics.CONFLICT_RESOLUTION_SIZE_GROUP_CATEGORY_MERGE_WITH_CLOUD_MD5);
                    outline107.append(FileSizeCategoryProvider.FileSizeCategory.this.getSizeGroupCategory$AndroidPhotosUploader_release().name());
                    return outline107.toString();
                }
            }, new MetricRecordingType[0]);
            return CdsUploader.Companion.generateResultFromNodeInfo$AndroidPhotosUploader_release(nodeInfoResponse, true);
        }
        ContentProperties contentProperties2 = nodeInfoResponse.getContentProperties();
        Intrinsics.checkExpressionValueIsNotNull(contentProperties2, "cloudNode.contentProperties");
        List<ContentSignature> contentSignatures = contentProperties2.getContentSignatures();
        String str = null;
        if (contentSignatures != null) {
            Iterator<T> it2 = contentSignatures.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                ContentSignature it3 = (ContentSignature) obj;
                Intrinsics.checkExpressionValueIsNotNull(it3, "it");
                if (it3.getContentSignatureType() == ContentSignatureType.MD5) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    break;
                }
            }
            ContentSignature contentSignature = (ContentSignature) obj;
            if (contentSignature != null) {
                str = contentSignature.getContentSignature();
            }
        }
        boolean visualDigest = uploadRequest.getVisualDigest();
        if (visualDigest == null) {
            visualDigest = false;
        }
        if (Intrinsics.areEqual(str, visualDigest)) {
            final FileSizeCategoryProvider.FileSizeCategory fileSizeCategory$AndroidPhotosUploader_release2 = this.categoryProvider.getFileSizeCategory$AndroidPhotosUploader_release(uploadRequest.getFilePath());
            this.metrics.recordSimpleEvent(TAG, CdsConflictResolverImpl$resolveNodeConflict$5.INSTANCE, new MetricRecordingType[0]);
            this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.error.CdsConflictResolverImpl$resolveNodeConflict$6
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107(CdsMetrics.CONFLICT_RESOLUTION_SIZE_CATEGORY_MERGE_WITH_CLOUD_VD);
                    outline107.append(FileSizeCategoryProvider.FileSizeCategory.this.name());
                    return outline107.toString();
                }
            }, new MetricRecordingType[0]);
            this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.error.CdsConflictResolverImpl$resolveNodeConflict$7
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107(CdsMetrics.CONFLICT_RESOLUTION_SIZE_GROUP_CATEGORY_MERGE_WITH_CLOUD_VD);
                    outline107.append(FileSizeCategoryProvider.FileSizeCategory.this.getSizeGroupCategory$AndroidPhotosUploader_release().name());
                    return outline107.toString();
                }
            }, new MetricRecordingType[0]);
            return CdsUploader.Companion.generateResultFromNodeInfo$AndroidPhotosUploader_release(nodeInfoResponse, true);
        } else if (sizeDifferenceWithinThresholdForJpeg$AndroidPhotosUploader_release(uploadRequest, nodeInfoResponse)) {
            UploadLogger uploadLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not resolve Jpeg conflict for ");
            outline107.append(uploadRequest.getId());
            outline107.append(Chars.SPACE);
            outline107.append("and ");
            outline107.append(this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
            uploadLogger.e$AndroidPhotosUploader_release(TAG, outline107.toString(), uploadException);
            this.metrics.recordSimpleEvent(TAG, CdsConflictResolverImpl$resolveNodeConflict$8.INSTANCE, new MetricRecordingType[0]);
            recordDetailedMetrics(uploadRequest);
            return new UploadResponse.NoRetryFailure(uploadException.getErrorCode(), uploadException, UploadErrorCategory.CONFLICT_ERROR);
        } else {
            UploadLogger uploadLogger2 = this.logger;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Non Jpeg conflict ");
            outline1072.append(uploadRequest.getId());
            outline1072.append(" and ");
            outline1072.append(this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
            outline1072.append(". Will be renamed and retried.");
            uploadLogger2.e$AndroidPhotosUploader_release(TAG, outline1072.toString(), uploadException);
            this.metrics.recordSimpleEvent(TAG, CdsConflictResolverImpl$resolveNodeConflict$9.INSTANCE, new MetricRecordingType[0]);
            copy = uploadRequest.copy((r49 & 1) != 0 ? uploadRequest.id : 0L, (r49 & 2) != 0 ? uploadRequest.filePath : null, (r49 & 4) != 0 ? uploadRequest.uploadPath : null, (r49 & 8) != 0 ? uploadRequest.contentDate : null, (r49 & 16) != 0 ? uploadRequest.md5 : null, (r49 & 32) != 0 ? uploadRequest.visualDigest : null, (r49 & 64) != 0 ? uploadRequest.suppressDeduplication : false, (r49 & 128) != 0 ? uploadRequest.renameOnNameConflict : true, (r49 & 256) != 0 ? uploadRequest.uploadCategory : null, (r49 & 512) != 0 ? uploadRequest.state : null, (r49 & 1024) != 0 ? uploadRequest.queue : null, (r49 & 2048) != 0 ? uploadRequest.currentProgress : 0L, (r49 & 4096) != 0 ? uploadRequest.maxProgress : 0L, (r49 & 8192) != 0 ? uploadRequest.errorCode : null, (r49 & 16384) != 0 ? uploadRequest.errorCategory : null, (r49 & 32768) != 0 ? uploadRequest.blocker : null, (r49 & 65536) != 0 ? uploadRequest.totalAttemptCount : 0, (r49 & 131072) != 0 ? uploadRequest.attemptCount : 0, (r49 & 262144) != 0 ? uploadRequest.maxAttemptsExceeded : false, (r49 & 524288) != 0 ? uploadRequest.creationTimeMillis : 0L, (r49 & 1048576) != 0 ? uploadRequest.fileSize : 0L, (r49 & 2097152) != 0 ? uploadRequest.priority : 0, (4194304 & r49) != 0 ? uploadRequest.addToFamilyVault : false, (r49 & 8388608) != 0 ? uploadRequest.appData : null, (r49 & 16777216) != 0 ? uploadRequest.parentId : null, (r49 & 33554432) != 0 ? uploadRequest.contentUri : null);
            return new UploadResponse.Failure(uploadException.getErrorCode(), uploadException, UploadErrorCategory.CONFLICT_ERROR, copy, false, 16, null);
        }
    }

    @VisibleForTesting
    @NotNull
    public final File createFileForPath$AndroidPhotosUploader_release(@NotNull String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        return new File(filePath);
    }

    @VisibleForTesting
    @NotNull
    public final String getFileExtension(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        String fileExtension = Files.getFileExtension(uploadRequest.getFilePath());
        Intrinsics.checkExpressionValueIsNotNull(fileExtension, "getFileExtension(uploadRequest.filePath)");
        return fileExtension;
    }

    @VisibleForTesting
    public final boolean isFileJpeg$AndroidPhotosUploader_release(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return FileUtilsKt.isJpeg(file);
    }

    @Override // com.amazon.photos.uploader.cds.error.CdsConflictResolver
    @NotNull
    public UploadResponse resolveConflict(@NotNull UploadRequest uploadRequest, @NotNull String conflictNodeId, @NotNull UploadException uploadException) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(conflictNodeId, "conflictNodeId");
        Intrinsics.checkParameterIsNotNull(uploadException, "uploadException");
        try {
            NodeInfoResponse cloudNode = getCloudNode(conflictNodeId);
            if (cloudNode != null) {
                return resolveNodeConflict(cloudNode, uploadRequest, uploadException);
            }
            return new UploadResponse.NoRetryFailure(uploadException.getErrorCode(), uploadException, UploadErrorCategory.CONFLICT_ERROR);
        } catch (CloudDriveException e) {
            handleException(e);
            return new UploadResponse.NoRetryFailure(uploadException.getErrorCode(), uploadException, UploadErrorCategory.CONFLICT_ERROR);
        } catch (InterruptedIOException e2) {
            Thread.currentThread().interrupt();
            handleException(e2);
            return new UploadResponse.NoRetryFailure(uploadException.getErrorCode(), uploadException, UploadErrorCategory.CONFLICT_ERROR);
        } catch (IOException e3) {
            handleException(e3);
            return new UploadResponse.NoRetryFailure(uploadException.getErrorCode(), uploadException, UploadErrorCategory.CONFLICT_ERROR);
        }
    }

    @VisibleForTesting
    public final boolean sizeDifferenceWithinThresholdForJpeg$AndroidPhotosUploader_release(@NotNull UploadRequest uploadRequest, @NotNull NodeInfoResponse cloudNode) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(cloudNode, "cloudNode");
        File createFileForPath$AndroidPhotosUploader_release = createFileForPath$AndroidPhotosUploader_release(uploadRequest.getFilePath());
        long length = createFileForPath$AndroidPhotosUploader_release.length();
        ContentProperties contentProperties = cloudNode.getContentProperties();
        Intrinsics.checkExpressionValueIsNotNull(contentProperties, "cloudNode.contentProperties");
        Long size = contentProperties.getSize();
        Intrinsics.checkExpressionValueIsNotNull(size, "cloudNode.contentProperties.size");
        return Math.abs(length - size.longValue()) < 5000 && isFileJpeg$AndroidPhotosUploader_release(createFileForPath$AndroidPhotosUploader_release);
    }
}
