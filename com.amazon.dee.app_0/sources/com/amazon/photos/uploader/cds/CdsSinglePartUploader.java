package com.amazon.photos.uploader.cds;

import androidx.annotation.VisibleForTesting;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.amazon.clouddrive.cdasdk.ProgressUpdate;
import com.amazon.clouddrive.cdasdk.cds.CDSError;
import com.amazon.clouddrive.cdasdk.cds.CDSException;
import com.amazon.clouddrive.cdasdk.cds.common.NodeInfo;
import com.amazon.clouddrive.cdasdk.cdus.CDUSException;
import com.amazon.clouddrive.cdasdk.cdus.UploadContentRequest;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.photos.uploader.CrashReporter;
import com.amazon.photos.uploader.UploadCompleter;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadProgressListener;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.Uploader;
import com.amazon.photos.uploader.UploaderState;
import com.amazon.photos.uploader.cds.CdsUploader;
import com.amazon.photos.uploader.cds.error.CdsUploadErrorResolver;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import okhttp3.MediaType;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CdsSinglePartUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 Y2\u00020\u0001:\u0002YZBQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0013J\"\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\r\u0010$\u001a\u00020#H\u0000¢\u0006\u0002\b%J\b\u0010&\u001a\u00020#H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0018H\u0002J\u001c\u0010*\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020-0+2\u0006\u0010.\u001a\u00020/H\u0002J\u0015\u00100\u001a\u0002012\u0006\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0002\b2J\u0015\u00103\u001a\u00020\u001d2\u0006\u00104\u001a\u000201H\u0001¢\u0006\u0002\b5J \u00106\u001a\u00020#2\u0006\u00107\u001a\u0002082\u0006\u0010 \u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J4\u00109\u001a\u00020#2\u0006\u0010:\u001a\u0002082\u0006\u0010 \u001a\u00020!2\u0006\u0010;\u001a\u00020,2\b\b\u0002\u0010<\u001a\u00020,2\b\b\u0002\u0010=\u001a\u00020-H\u0002J\u001d\u0010>\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0002\b?J.\u0010@\u001a\u00020#2\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020C0B2\u0006\u0010D\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010E\u001a\u00020#2\u0006\u0010F\u001a\u00020GH\u0002J(\u0010H\u001a\u00020#2\u0006\u0010;\u001a\u00020,2\u0006\u0010<\u001a\u00020,2\u0006\u0010:\u001a\u0002082\u0006\u0010=\u001a\u00020-H\u0002J\u0018\u0010I\u001a\u00020#2\u0006\u0010J\u001a\u00020K2\u0006\u0010:\u001a\u000208H\u0002J \u0010L\u001a\u00020#2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010M\u001a\u00020NH\u0016J@\u0010O\u001a\u00020#2\b\u0010P\u001a\u0004\u0018\u00010\u001b2\u0006\u00104\u001a\u0002012\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020S0R2\u0006\u0010D\u001a\u00020\u0019H\u0002JP\u0010T\u001a\u00020#2\b\u0010P\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u00104\u001a\u0002012\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00180R2\u0006\u0010D\u001a\u00020\u00192\u0006\u0010U\u001a\u00020\u0018H\u0002J\u0010\u0010V\u001a\u00020#2\u0006\u0010W\u001a\u00020XH\u0016R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006["}, d2 = {"Lcom/amazon/photos/uploader/cds/CdsSinglePartUploader;", "Lcom/amazon/photos/uploader/Uploader;", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "uploadScheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "callbackScheduler", "uploadErrorResolver", "Lcom/amazon/photos/uploader/cds/error/CdsUploadErrorResolver;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "parentNodeFetcher", "Lcom/amazon/photos/uploader/cds/ParentNodeFetcher;", "crashReporter", "Lcom/amazon/photos/uploader/CrashReporter;", "urlConnectionUploader", "Lcom/amazon/photos/uploader/cds/UrlConnectionUploader;", "(Lcom/amazon/clouddrive/cdasdk/CDClient;Lio/reactivex/rxjava3/core/Scheduler;Lio/reactivex/rxjava3/core/Scheduler;Lcom/amazon/photos/uploader/cds/error/CdsUploadErrorResolver;Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/cds/ParentNodeFetcher;Lcom/amazon/photos/uploader/CrashReporter;Lcom/amazon/photos/uploader/cds/UrlConnectionUploader;)V", "mediaType", "Lokhttp3/MediaType;", "requestIdToDisposable", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lio/reactivex/rxjava3/disposables/Disposable;", "basicUploadData", "Lcom/amazon/photos/uploader/cds/UploadDataPair;", "uploadContentRequest", "Lcom/amazon/clouddrive/cdasdk/cdus/UploadContentRequest;", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "completer", "Lcom/amazon/photos/uploader/UploadCompleter;", "cancelUpload", "", "cleanup", "cleanup$AndroidPhotosUploader_release", "clearCompleted", "clearUploadRequestId", "", "id", "extractErrorDetails", "Lkotlin/Pair;", "", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "ex", "Lcom/amazon/clouddrive/cdasdk/cds/CDSException;", "getFile", "Ljava/io/File;", "getFile$AndroidPhotosUploader_release", "getUploadContentRequest", "file", "getUploadContentRequest$AndroidPhotosUploader_release", "handlePostNodeException", "t", "", "handleUploadContentRequestException", "throwable", "metricEventName", "errorCode", "errorCategory", "populateUploadContentRequest", "populateUploadContentRequest$AndroidPhotosUploader_release", "processPostNode", "post", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/clouddrive/cdasdk/cds/common/NodeInfo;", "progressSubscriber", "recordCloudDriveException", ADMRegistrationConstants.CALL_EXCEPTION, "Lcom/amazon/clouddrive/cdasdk/CloudDriveException;", "recordErrorMetric", "recordPostNodeErrorMetrics", "result", "Lcom/amazon/photos/uploader/UploadResponse;", "startUpload", "progressListener", "Lcom/amazon/photos/uploader/UploadProgressListener;", "startUploadUsingCdasdk", "uploadDataPair", "progressSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/clouddrive/cdasdk/ProgressUpdate;", "startUploadUsingUrlConnection", "fileSize", "updateUploaderState", "uploaderState", "Lcom/amazon/photos/uploader/UploaderState;", "Companion", "SinglePartException", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CdsSinglePartUploader implements Uploader {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String FILE_NOT_FOUND = "FileNotFound";
    private static final String TAG = "CdsSinglePartUploader";
    private final Scheduler callbackScheduler;
    private final CDClient cdClient;
    private final CrashReporter crashReporter;
    private final UploadLogger logger;
    private final MediaType mediaType;
    private final Metrics metrics;
    private final ParentNodeFetcher parentNodeFetcher;
    private final ConcurrentHashMap<Long, Disposable> requestIdToDisposable;
    private final CdsUploadErrorResolver uploadErrorResolver;
    private final Scheduler uploadScheduler;
    private final UrlConnectionUploader urlConnectionUploader;

    /* compiled from: CdsSinglePartUploader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/uploader/cds/CdsSinglePartUploader$Companion;", "", "()V", "FILE_NOT_FOUND", "", "TAG", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: CdsSinglePartUploader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/photos/uploader/cds/CdsSinglePartUploader$SinglePartException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class SinglePartException extends Exception {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SinglePartException(@NotNull String message, @NotNull Throwable cause) {
            super(message, cause);
            Intrinsics.checkParameterIsNotNull(message, "message");
            Intrinsics.checkParameterIsNotNull(cause, "cause");
        }
    }

    public CdsSinglePartUploader(@NotNull CDClient cdClient, @NotNull Scheduler uploadScheduler, @NotNull Scheduler callbackScheduler, @NotNull CdsUploadErrorResolver uploadErrorResolver, @NotNull UploadLogger logger, @NotNull Metrics metrics, @NotNull ParentNodeFetcher parentNodeFetcher, @Nullable CrashReporter crashReporter, @Nullable UrlConnectionUploader urlConnectionUploader) {
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(uploadScheduler, "uploadScheduler");
        Intrinsics.checkParameterIsNotNull(callbackScheduler, "callbackScheduler");
        Intrinsics.checkParameterIsNotNull(uploadErrorResolver, "uploadErrorResolver");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(parentNodeFetcher, "parentNodeFetcher");
        this.cdClient = cdClient;
        this.uploadScheduler = uploadScheduler;
        this.callbackScheduler = callbackScheduler;
        this.uploadErrorResolver = uploadErrorResolver;
        this.logger = logger;
        this.metrics = metrics;
        this.parentNodeFetcher = parentNodeFetcher;
        this.crashReporter = crashReporter;
        this.urlConnectionUploader = urlConnectionUploader;
        this.mediaType = MediaType.Companion.get("application/octet-stream");
        this.requestIdToDisposable = new ConcurrentHashMap<>();
    }

    private final UploadDataPair basicUploadData(UploadContentRequest uploadContentRequest, UploadRequest uploadRequest, UploadCompleter uploadCompleter) {
        try {
            UploadContentRequest populateUploadContentRequest$AndroidPhotosUploader_release = populateUploadContentRequest$AndroidPhotosUploader_release(uploadContentRequest, uploadRequest);
            String md5 = uploadRequest.getMd5();
            if (md5 != null) {
                return new UploadDataPair(md5, populateUploadContentRequest$AndroidPhotosUploader_release);
            }
            throw new IllegalArgumentException("Md5 == null".toString());
        } catch (CDSException e) {
            Pair<String, UploadErrorCategory> extractErrorDetails = extractErrorDetails(e);
            handleUploadContentRequestException(e, uploadCompleter, CdsMetrics.CDS_EXCEPTION, extractErrorDetails.component1(), extractErrorDetails.component2());
            return null;
        } catch (CloudDriveException e2) {
            handleUploadContentRequestException$default(this, e2, uploadCompleter, CdsMetrics.CLOUD_DRIVE_EXCEPTION, null, null, 24, null);
            return null;
        } catch (FileNotFoundException e3) {
            recordErrorMetric(CdsMetrics.FILE_NOT_FOUND_EXCEPTION, FILE_NOT_FOUND, e3, UploadErrorCategory.FILE_NOT_PRESENT);
            uploadCompleter.setResponse(new UploadResponse.AcceptableFailure(FILE_NOT_FOUND, e3, UploadErrorCategory.FILE_NOT_PRESENT));
            return null;
        } catch (InterruptedIOException e4) {
            Thread.currentThread().interrupt();
            handleUploadContentRequestException$default(this, e4, uploadCompleter, CdsMetrics.INTERRUPTED_IO_EXCEPTION, null, null, 24, null);
            return null;
        } catch (IOException e5) {
            handleUploadContentRequestException$default(this, e5, uploadCompleter, CdsMetrics.IO_EXCEPTION, null, null, 24, null);
            return null;
        } catch (IllegalArgumentException e6) {
            handleUploadContentRequestException$default(this, e6, uploadCompleter, CdsMetrics.ILLEGAL_ARGUMENT_EXCEPTION, null, null, 24, null);
            return null;
        } catch (Exception e7) {
            handleUploadContentRequestException$default(this, e7, uploadCompleter, CdsMetrics.GENERIC_EXCEPTION, null, null, 24, null);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean clearUploadRequestId(long j) {
        Disposable it2 = this.requestIdToDisposable.get(Long.valueOf(j));
        boolean z = false;
        if (it2 != null) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            if (!it2.isDisposed()) {
                it2.dispose();
                z = true;
            }
        }
        this.requestIdToDisposable.remove(Long.valueOf(j));
        return z;
    }

    private final Pair<String, UploadErrorCategory> extractErrorDetails(CDSException cDSException) {
        UploadErrorCategory uploadErrorCategory;
        String str;
        CDSError cdsError = cDSException.getCdsError();
        Intrinsics.checkExpressionValueIsNotNull(cdsError, "ex.cdsError");
        if (cdsError.getCode() != null) {
            CDSError cdsError2 = cDSException.getCdsError();
            Intrinsics.checkExpressionValueIsNotNull(cdsError2, "ex.cdsError");
            str = cdsError2.getCode();
            Intrinsics.checkExpressionValueIsNotNull(str, "ex.cdsError.code");
            uploadErrorCategory = UploadErrorCategory.OTHER_KNOWN_ERROR;
        } else {
            uploadErrorCategory = UploadErrorCategory.UNKNOWN_ERROR;
            str = UploadResponse.UNKNOWN_UPLOAD_ERROR;
        }
        return new Pair<>(str, uploadErrorCategory);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handlePostNodeException(Throwable th, UploadCompleter uploadCompleter, UploadRequest uploadRequest) {
        if (th instanceof CDUSException) {
            UploadResponse resolve = this.uploadErrorResolver.resolve(uploadRequest, (CDUSException) th);
            recordPostNodeErrorMetrics(resolve, th);
            uploadCompleter.setResponse(resolve);
        } else if (th instanceof CloudDriveException) {
            recordCloudDriveException((CloudDriveException) th);
            uploadCompleter.setResponse(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, th, UploadErrorCategory.UNKNOWN_ERROR, null, false, 24, null));
        } else {
            recordErrorMetric(CdsMetrics.UPLOAD_POST_NODE_UNKNOWN_EXCEPTION, UploadResponse.UNKNOWN_UPLOAD_ERROR, th, UploadErrorCategory.UNKNOWN_ERROR);
            uploadCompleter.setResponse(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, th, UploadErrorCategory.UNKNOWN_ERROR, null, false, 24, null));
        }
    }

    private final void handleUploadContentRequestException(Throwable th, UploadCompleter uploadCompleter, String str, String str2, UploadErrorCategory uploadErrorCategory) {
        this.logger.e$AndroidPhotosUploader_release(TAG, "Got exception while creating upload content request", th);
        recordErrorMetric(str, str2, th, uploadErrorCategory);
        uploadCompleter.setResponse(new UploadResponse.Failure(str2, th, uploadErrorCategory, null, false, 24, null));
    }

    static /* synthetic */ void handleUploadContentRequestException$default(CdsSinglePartUploader cdsSinglePartUploader, Throwable th, UploadCompleter uploadCompleter, String str, String str2, UploadErrorCategory uploadErrorCategory, int i, Object obj) {
        if ((i & 8) != 0) {
            str2 = UploadResponse.UNKNOWN_UPLOAD_ERROR;
        }
        String str3 = str2;
        if ((i & 16) != 0) {
            uploadErrorCategory = UploadErrorCategory.UNKNOWN_ERROR;
        }
        cdsSinglePartUploader.handleUploadContentRequestException(th, uploadCompleter, str, str3, uploadErrorCategory);
    }

    private final void processPostNode(final UploadRequest uploadRequest, Single<NodeInfo> single, final Disposable disposable, final UploadCompleter uploadCompleter) {
        ConcurrentHashMap<Long, Disposable> concurrentHashMap = this.requestIdToDisposable;
        Long valueOf = Long.valueOf(uploadRequest.getId());
        Disposable subscribe = single.subscribeOn(this.uploadScheduler).doFinally(new Action() { // from class: com.amazon.photos.uploader.cds.CdsSinglePartUploader$processPostNode$1
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                disposable.dispose();
                CdsSinglePartUploader.this.clearUploadRequestId(uploadRequest.getId());
            }
        }).subscribe(new Consumer<NodeInfo>() { // from class: com.amazon.photos.uploader.cds.CdsSinglePartUploader$processPostNode$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(NodeInfo node) {
                UploadCompleter uploadCompleter2 = UploadCompleter.this;
                CdsUploader.Companion companion = CdsUploader.Companion;
                Intrinsics.checkExpressionValueIsNotNull(node, "node");
                uploadCompleter2.setResponse(CdsUploader.Companion.generateResultFromNodeInfo$AndroidPhotosUploader_release$default(companion, node, false, 2, null));
            }
        }, new Consumer<Throwable>() { // from class: com.amazon.photos.uploader.cds.CdsSinglePartUploader$processPostNode$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable t) {
                CdsSinglePartUploader cdsSinglePartUploader = CdsSinglePartUploader.this;
                Intrinsics.checkExpressionValueIsNotNull(t, "t");
                cdsSinglePartUploader.handlePostNodeException(t, uploadCompleter, uploadRequest);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "post.subscribeOn(uploadS…uest) }\n                )");
        concurrentHashMap.put(valueOf, subscribe);
    }

    private final void recordCloudDriveException(final CloudDriveException cloudDriveException) {
        this.metrics.recordCustomMetric(TAG, new ClientMetric().addCounter(CdsSinglePartUploader$recordCloudDriveException$clientMetric$1.INSTANCE, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.CdsSinglePartUploader$recordCloudDriveException$clientMetric$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline108 = GeneratedOutlineSupport1.outline108(CdsMetrics.UPLOAD_POST_NODE_CLOUD_DRIVE_EXCEPTION, JsonReaderKt.COLON);
                outline108.append(CloudDriveException.this.getClass().getSimpleName());
                outline108.append("_HttpCode_");
                outline108.append(CloudDriveException.this.getCode());
                return outline108.toString();
            }
        }, 1).withTagName(TAG), new MetricRecordingType[0]);
    }

    private final void recordErrorMetric(final String str, final String str2, final Throwable th, final UploadErrorCategory uploadErrorCategory) {
        this.metrics.recordCustomMetric(TAG, new ClientMetric().addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.CdsSinglePartUploader$recordErrorMetric$clientMetric$1
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str;
            }
        }, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.CdsSinglePartUploader$recordErrorMetric$clientMetric$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str + JsonReaderKt.COLON + str2;
            }
        }, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.CdsSinglePartUploader$recordErrorMetric$clientMetric$3
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str + JsonReaderKt.COLON + th.getClass().getSimpleName();
            }
        }, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.CdsSinglePartUploader$recordErrorMetric$clientMetric$4
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                return str + "_CATEGORY_" + uploadErrorCategory.name();
            }
        }, 1).withTagName(TAG), new MetricRecordingType[0]);
    }

    private final void recordPostNodeErrorMetrics(UploadResponse uploadResponse, Throwable th) {
        if (uploadResponse instanceof UploadResponse.Failure) {
            UploadResponse.Failure failure = (UploadResponse.Failure) uploadResponse;
            recordErrorMetric(CdsMetrics.UPLOAD_POST_NODE_CDUS_EXCEPTION, failure.getErrorCode(), th, failure.getErrorCategory());
        } else if (!(uploadResponse instanceof UploadResponse.NoRetryFailure)) {
        } else {
            UploadResponse.NoRetryFailure noRetryFailure = (UploadResponse.NoRetryFailure) uploadResponse;
            recordErrorMetric(CdsMetrics.UPLOAD_POST_NODE_CDUS_EXCEPTION, noRetryFailure.getErrorCode(), th, noRetryFailure.getErrorCategory());
        }
    }

    private final void startUploadUsingCdasdk(UploadDataPair uploadDataPair, File file, UploadRequest uploadRequest, UploadCompleter uploadCompleter, PublishSubject<ProgressUpdate> publishSubject, Disposable disposable) {
        if (uploadDataPair != null) {
            Single<NodeInfo> postNode = this.cdClient.getCDUSCalls().postNode(uploadDataPair.getUploadContentRequest(), this.mediaType, uploadRequest.getContentUri(), uploadRequest.getFileSize(), publishSubject, uploadDataPair.getMd5());
            Intrinsics.checkExpressionValueIsNotNull(postNode, "cdClient.cdusCalls.postN…ject, uploadDataPair.md5)");
            processPostNode(uploadRequest, postNode, disposable, uploadCompleter);
        }
    }

    private final void startUploadUsingUrlConnection(UploadDataPair uploadDataPair, UrlConnectionUploader urlConnectionUploader, File file, UploadRequest uploadRequest, UploadCompleter uploadCompleter, PublishSubject<Long> publishSubject, Disposable disposable, long j) {
        if (uploadDataPair != null) {
            processPostNode(uploadRequest, urlConnectionUploader.postNode(uploadDataPair, uploadRequest.getContentUri(), publishSubject, j), disposable, uploadCompleter);
        }
    }

    @Override // com.amazon.photos.uploader.Uploader
    public void cancelUpload(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        boolean clearUploadRequestId = clearUploadRequestId(uploadRequest.getId());
        UploadLogger uploadLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Canceled the upload ");
        outline107.append(uploadRequest.getId());
        outline107.append(" path =");
        outline107.append(Chars.SPACE);
        outline107.append(this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
        outline107.append(Chars.SPACE);
        outline107.append(clearUploadRequestId);
        uploadLogger.i$AndroidPhotosUploader_release(TAG, outline107.toString());
    }

    public final void cleanup$AndroidPhotosUploader_release() {
        Collection<Disposable> values = this.requestIdToDisposable.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "requestIdToDisposable.values");
        for (Disposable it2 : values) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            if (!it2.isDisposed()) {
                it2.dispose();
            }
        }
        this.requestIdToDisposable.clear();
    }

    @Override // com.amazon.photos.uploader.Uploader
    public void clearCompleted() {
    }

    @VisibleForTesting
    @NotNull
    public final File getFile$AndroidPhotosUploader_release(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        return new File(uploadRequest.getFilePath());
    }

    @VisibleForTesting
    @NotNull
    public final UploadContentRequest getUploadContentRequest$AndroidPhotosUploader_release(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return new UploadContentRequest(file.getName(), Long.valueOf(file.length()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0066, code lost:
        if (r0 != false) goto L30;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0080  */
    @androidx.annotation.VisibleForTesting
    @androidx.annotation.WorkerThread
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.amazon.clouddrive.cdasdk.cdus.UploadContentRequest populateUploadContentRequest$AndroidPhotosUploader_release(@org.jetbrains.annotations.NotNull com.amazon.clouddrive.cdasdk.cdus.UploadContentRequest r4, @org.jetbrains.annotations.NotNull com.amazon.photos.uploader.UploadRequest r5) throws java.io.FileNotFoundException, com.amazon.clouddrive.cdasdk.cds.CDSException, com.amazon.clouddrive.cdasdk.CloudDriveException, java.io.IOException, java.io.InterruptedIOException {
        /*
            r3 = this;
            java.lang.String r0 = "uploadContentRequest"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.lang.String r0 = "uploadRequest"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "FILE"
            r4.setKind(r0)
            java.lang.String r0 = r5.getContentDate()
            if (r0 == 0) goto L1b
            r4.setContentDate(r0)
            r4.setFallbackContentDate(r0)
        L1b:
            boolean r0 = r5.getRenameOnNameConflict()
            if (r0 == 0) goto L26
            com.amazon.clouddrive.cdasdk.cdp.ConflictResolution r0 = com.amazon.clouddrive.cdasdk.cdp.ConflictResolution.RENAME
            r4.setConflictResolution(r0)
        L26:
            boolean r0 = r5.getSuppressDeduplication()
            if (r0 == 0) goto L35
            com.amazon.clouddrive.cdasdk.cdus.Suppression r0 = com.amazon.clouddrive.cdasdk.cdus.Suppression.DEDUPLICATION
            java.lang.String r0 = r0.name()
            r4.setSuppress(r0)
        L35:
            boolean r0 = r5.getAddToFamilyVault()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r4.setAddToFamilyArchive(r0)
            java.lang.String r0 = r5.getParentId()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L51
            boolean r0 = kotlin.text.StringsKt.isBlank(r0)
            if (r0 == 0) goto L4f
            goto L51
        L4f:
            r0 = r1
            goto L52
        L51:
            r0 = r2
        L52:
            if (r0 != 0) goto L5c
            java.lang.String r0 = r5.getParentId()
            r4.setParentNodeId(r0)
            goto L7a
        L5c:
            java.lang.String r0 = r5.getUploadPath()
            if (r0 == 0) goto L68
            boolean r0 = kotlin.text.StringsKt.isBlank(r0)
            if (r0 == 0) goto L69
        L68:
            r1 = r2
        L69:
            if (r1 != 0) goto L7a
            com.amazon.photos.uploader.cds.ParentNodeFetcher r0 = r3.parentNodeFetcher
            java.lang.String r1 = r5.getUploadPath()
            java.lang.String r0 = r0.getParentNodeId$AndroidPhotosUploader_release(r1)
            if (r0 == 0) goto L7a
            r4.setParentNodeId(r0)
        L7a:
            java.lang.String r5 = r5.getVisualDigest()
            if (r5 == 0) goto L88
            r4.setContentSignature(r5)
            com.amazon.clouddrive.cdasdk.cds.common.ContentSignatureType r5 = com.amazon.clouddrive.cdasdk.cds.common.ContentSignatureType.MD5
            r4.setContentSignatureType(r5)
        L88:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.cds.CdsSinglePartUploader.populateUploadContentRequest$AndroidPhotosUploader_release(com.amazon.clouddrive.cdasdk.cdus.UploadContentRequest, com.amazon.photos.uploader.UploadRequest):com.amazon.clouddrive.cdasdk.cdus.UploadContentRequest");
    }

    @Override // com.amazon.photos.uploader.Uploader
    public void startUpload(@NotNull UploadRequest uploadRequest, @NotNull UploadCompleter completer, @NotNull final UploadProgressListener progressListener) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(completer, "completer");
        Intrinsics.checkParameterIsNotNull(progressListener, "progressListener");
        UploadLogger uploadLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Starting file upload for ");
        outline107.append(uploadRequest.getId());
        uploadLogger.i$AndroidPhotosUploader_release(TAG, outline107.toString());
        UploadLogger uploadLogger2 = this.logger;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Starting file upload for ");
        outline1072.append(uploadRequest.getId());
        outline1072.append(" with path ");
        outline1072.append(this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
        uploadLogger2.d$AndroidPhotosUploader_release(TAG, outline1072.toString());
        File file$AndroidPhotosUploader_release = getFile$AndroidPhotosUploader_release(uploadRequest);
        UploadDataPair basicUploadData = basicUploadData(getUploadContentRequest$AndroidPhotosUploader_release(file$AndroidPhotosUploader_release), uploadRequest, completer);
        if (this.urlConnectionUploader == null) {
            UploadLogger uploadLogger3 = this.logger;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Starting file upload for ");
            outline1073.append(uploadRequest.getId());
            outline1073.append(" using CDASDK.");
            uploadLogger3.i$AndroidPhotosUploader_release(TAG, outline1073.toString());
            UploadLogger uploadLogger4 = this.logger;
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Starting file upload for ");
            outline1074.append(uploadRequest.getId());
            outline1074.append(" with path ");
            outline1074.append(this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
            outline1074.append(" using CDASDK.");
            uploadLogger4.d$AndroidPhotosUploader_release(TAG, outline1074.toString());
            PublishSubject<ProgressUpdate> progressSubject = PublishSubject.create();
            Disposable progressSubscriber = progressSubject.subscribeOn(this.callbackScheduler).subscribe(new Consumer<ProgressUpdate>() { // from class: com.amazon.photos.uploader.cds.CdsSinglePartUploader$startUpload$progressSubscriber$1
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(ProgressUpdate p) {
                    UploadProgressListener uploadProgressListener = UploadProgressListener.this;
                    Intrinsics.checkExpressionValueIsNotNull(p, "p");
                    uploadProgressListener.onProgressUpdate(p.getBytesComplete(), p.getBytesTotal());
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(progressSubject, "progressSubject");
            Intrinsics.checkExpressionValueIsNotNull(progressSubscriber, "progressSubscriber");
            startUploadUsingCdasdk(basicUploadData, file$AndroidPhotosUploader_release, uploadRequest, completer, progressSubject, progressSubscriber);
            return;
        }
        UploadLogger uploadLogger5 = this.logger;
        StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("Starting file upload for ");
        outline1075.append(uploadRequest.getId());
        outline1075.append(" using Url connection");
        uploadLogger5.i$AndroidPhotosUploader_release(TAG, outline1075.toString());
        UploadLogger uploadLogger6 = this.logger;
        StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("Starting file upload for ");
        outline1076.append(uploadRequest.getId());
        outline1076.append(" with path ");
        outline1076.append(this.logger.obfuscate$AndroidPhotosUploader_release(uploadRequest.getFilePath()));
        outline1076.append(" using Url connection");
        uploadLogger6.d$AndroidPhotosUploader_release(TAG, outline1076.toString());
        final long length = file$AndroidPhotosUploader_release.length();
        PublishSubject<Long> progressSubject2 = PublishSubject.create();
        Disposable progressSubscriber2 = progressSubject2.subscribeOn(this.callbackScheduler).subscribe(new Consumer<Long>() { // from class: com.amazon.photos.uploader.cds.CdsSinglePartUploader$startUpload$progressSubscriber$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Long p) {
                UploadProgressListener uploadProgressListener = UploadProgressListener.this;
                Intrinsics.checkExpressionValueIsNotNull(p, "p");
                uploadProgressListener.onProgressUpdate(p.longValue(), length);
            }
        });
        UrlConnectionUploader urlConnectionUploader = this.urlConnectionUploader;
        Intrinsics.checkExpressionValueIsNotNull(progressSubject2, "progressSubject");
        Intrinsics.checkExpressionValueIsNotNull(progressSubscriber2, "progressSubscriber");
        startUploadUsingUrlConnection(basicUploadData, urlConnectionUploader, file$AndroidPhotosUploader_release, uploadRequest, completer, progressSubject2, progressSubscriber2, length);
    }

    @Override // com.amazon.photos.uploader.Uploader
    public void updateUploaderState(@NotNull UploaderState uploaderState) {
        Intrinsics.checkParameterIsNotNull(uploaderState, "uploaderState");
    }
}
