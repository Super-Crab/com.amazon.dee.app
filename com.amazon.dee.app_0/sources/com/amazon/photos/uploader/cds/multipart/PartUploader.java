package com.amazon.photos.uploader.cds.multipart;

import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.clouddrive.android.core.interfaces.ClientMetric;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.clouddrive.cdasdk.ProgressUpdate;
import com.amazon.clouddrive.cdasdk.cdus.CDUSCalls;
import com.amazon.clouddrive.cdasdk.cdus.CDUSError;
import com.amazon.clouddrive.cdasdk.cdus.CDUSException;
import com.amazon.clouddrive.cdasdk.cdus.MultipartUploadStatus;
import com.amazon.clouddrive.cdasdk.cdus.UploadPartRequest;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadResponse;
import com.amazon.photos.uploader.cds.CdsMetrics;
import com.amazon.photos.uploader.cds.PartProvider;
import com.amazon.photos.uploader.cds.error.CdsUploadPartErrorResolver;
import com.amazon.photos.uploader.cds.multipart.PartUploader;
import com.amazon.photos.uploader.cds.multipart.UploadPartResponse;
import com.amazon.photos.uploader.internal.contentsignature.ContentSignatureProvider;
import com.amazon.photos.uploader.log.UploadLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.MediaType;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: PartUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0000\u0018\u0000 K2\u00020\u0001:\u0001KBM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u001b\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0001¢\u0006\u0002\b\u001fJ\u0015\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$J5\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020!2\u0006\u0010+\u001a\u00020,H\u0001¢\u0006\u0002\b-J@\u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u001e2!\u00102\u001a\u001d\u0012\u0013\u0012\u001104¢\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(7\u0012\u0004\u0012\u00020\u001703H\u0001¢\u0006\u0002\b8J)\u00109\u001a\b\u0012\u0004\u0012\u00020;0:2\u0006\u0010<\u001a\u00020&2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020?0>H\u0001¢\u0006\u0002\b@Js\u0010A\u001a\u00020\u00172\u0006\u0010B\u001a\u00020C2\u0006\u0010*\u001a\u00020!2\u0006\u0010+\u001a\u00020,2\u0006\u0010D\u001a\u00020E2!\u0010F\u001a\u001d\u0012\u0013\u0012\u00110G¢\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(H\u0012\u0004\u0012\u00020\u0017032!\u00102\u001a\u001d\u0012\u0013\u0012\u001104¢\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(7\u0012\u0004\u0012\u00020\u001703H\u0000¢\u0006\u0002\bIJn\u0010J\u001a\u00020\u00172\u0006\u0010B\u001a\u00020C2\u0006\u0010*\u001a\u00020!2\u0006\u0010+\u001a\u00020,2\u0006\u0010D\u001a\u00020E2!\u0010F\u001a\u001d\u0012\u0013\u0012\u00110G¢\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(H\u0012\u0004\u0012\u00020\u0017032!\u00102\u001a\u001d\u0012\u0013\u0012\u001104¢\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(7\u0012\u0004\u0012\u00020\u001703H\u0002R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/PartUploader;", "", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "uploadScheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "callbackScheduler", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "partProvider", "Lcom/amazon/photos/uploader/cds/PartProvider;", "partInfoDao", "Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;", "contentSignatureProvider", "Lcom/amazon/photos/uploader/internal/contentsignature/ContentSignatureProvider;", "partErrorResolver", "Lcom/amazon/photos/uploader/cds/error/CdsUploadPartErrorResolver;", "(Lcom/amazon/clouddrive/cdasdk/CDClient;Lio/reactivex/rxjava3/core/Scheduler;Lio/reactivex/rxjava3/core/Scheduler;Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/cds/PartProvider;Lcom/amazon/photos/uploader/cds/multipart/PartInfoDao;Lcom/amazon/photos/uploader/internal/contentsignature/ContentSignatureProvider;Lcom/amazon/photos/uploader/cds/error/CdsUploadPartErrorResolver;)V", "mediaType", "Lokhttp3/MediaType;", "clearParts", "", "id", "", "ensureValidNodeIds", "", "partInfoList", "", "Lcom/amazon/photos/uploader/cds/multipart/PartInfo;", "ensureValidNodeIds$AndroidPhotosUploader_release", "getFile", "Ljava/io/File;", "uploadRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "getFile$AndroidPhotosUploader_release", "getPartForUpload", "Lcom/amazon/photos/uploader/cds/multipart/PartUploader$Companion$PartForUpload;", "partInfo", "isLastSegment", "totalPartsSize", "file", "contentUri", "Landroid/net/Uri;", "getPartForUpload$AndroidPhotosUploader_release", "handlePartFailure", "throwable", "", "part", "onError", "Lkotlin/Function1;", "Lcom/amazon/photos/uploader/UploadResponse;", "Lkotlin/ParameterName;", "name", "error", "handlePartFailure$AndroidPhotosUploader_release", "makePartRequest", "Lio/reactivex/rxjava3/core/Single;", "Lcom/amazon/clouddrive/cdasdk/cdus/UploadPartResponse;", "partForUpload", "progressSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/clouddrive/cdasdk/ProgressUpdate;", "makePartRequest$AndroidPhotosUploader_release", "startMultiPartUpload", "partUploaderParcel", "Lcom/amazon/photos/uploader/cds/multipart/PartUploaderParcel;", "progressListener", "Lcom/amazon/photos/uploader/cds/multipart/PartProgressListener;", "onPartsComplete", "", "count", "startMultiPartUpload$AndroidPhotosUploader_release", "startMultiPartUploadUnSafe", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PartUploader {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "PartUploader";
    private final Scheduler callbackScheduler;
    private final CDClient cdClient;
    private final ContentSignatureProvider contentSignatureProvider;
    private final UploadLogger logger;
    private final MediaType mediaType;
    private final Metrics metrics;
    private final CdsUploadPartErrorResolver partErrorResolver;
    private final PartInfoDao partInfoDao;
    private final PartProvider partProvider;
    private final Scheduler uploadScheduler;

    /* compiled from: PartUploader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/PartUploader$Companion;", "", "()V", "TAG", "", "PartForUpload", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {

        /* compiled from: PartUploader.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/photos/uploader/cds/multipart/PartUploader$Companion$PartForUpload;", "", "payLoad", "", "partInfo", "Lcom/amazon/photos/uploader/cds/multipart/PartInfo;", "([BLcom/amazon/photos/uploader/cds/multipart/PartInfo;)V", "getPartInfo", "()Lcom/amazon/photos/uploader/cds/multipart/PartInfo;", "getPayLoad", "()[B", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes13.dex */
        public static final class PartForUpload {
            @NotNull
            private final PartInfo partInfo;
            @NotNull
            private final byte[] payLoad;

            public PartForUpload(@NotNull byte[] payLoad, @NotNull PartInfo partInfo) {
                Intrinsics.checkParameterIsNotNull(payLoad, "payLoad");
                Intrinsics.checkParameterIsNotNull(partInfo, "partInfo");
                this.payLoad = payLoad;
                this.partInfo = partInfo;
            }

            @NotNull
            public final PartInfo getPartInfo() {
                return this.partInfo;
            }

            @NotNull
            public final byte[] getPayLoad() {
                return this.payLoad;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public PartUploader(@NotNull CDClient cdClient, @NotNull Scheduler uploadScheduler, @NotNull Scheduler callbackScheduler, @NotNull UploadLogger logger, @NotNull Metrics metrics, @NotNull PartProvider partProvider, @NotNull PartInfoDao partInfoDao, @NotNull ContentSignatureProvider contentSignatureProvider, @NotNull CdsUploadPartErrorResolver partErrorResolver) {
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(uploadScheduler, "uploadScheduler");
        Intrinsics.checkParameterIsNotNull(callbackScheduler, "callbackScheduler");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(partProvider, "partProvider");
        Intrinsics.checkParameterIsNotNull(partInfoDao, "partInfoDao");
        Intrinsics.checkParameterIsNotNull(contentSignatureProvider, "contentSignatureProvider");
        Intrinsics.checkParameterIsNotNull(partErrorResolver, "partErrorResolver");
        this.cdClient = cdClient;
        this.uploadScheduler = uploadScheduler;
        this.callbackScheduler = callbackScheduler;
        this.logger = logger;
        this.metrics = metrics;
        this.partProvider = partProvider;
        this.partInfoDao = partInfoDao;
        this.contentSignatureProvider = contentSignatureProvider;
        this.partErrorResolver = partErrorResolver;
        this.mediaType = MediaType.Companion.get("application/octet-stream");
    }

    private final void startMultiPartUploadUnSafe(final PartUploaderParcel partUploaderParcel, final File file, final Uri uri, final PartProgressListener partProgressListener, final Function1<? super Integer, Unit> function1, final Function1<? super UploadResponse, Unit> function12) {
        UploadLogger uploadLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Total parts being uploaded : ");
        outline107.append(partUploaderParcel.getPartInfoList().size());
        outline107.append(Chars.SPACE);
        outline107.append("for REQ: ");
        outline107.append(partUploaderParcel.getUploadRequestId());
        uploadLogger.d$AndroidPhotosUploader_release(TAG, outline107.toString());
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        List<Disposable> disposables = partUploaderParcel.getDisposables();
        Object blockingGet = Observable.fromIterable(partUploaderParcel.getPartInfoList()).flatMap(new Function<T, ObservableSource<? extends R>>() { // from class: com.amazon.photos.uploader.cds.multipart.PartUploader$startMultiPartUploadUnSafe$1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Observable<Disposable> mo10358apply(final PartInfo part) {
                Scheduler scheduler;
                UploadLogger uploadLogger2;
                UploadLogger uploadLogger3;
                PartInfoDao partInfoDao;
                Scheduler scheduler2;
                PublishSubject<ProgressUpdate> progressSubject = PublishSubject.create();
                List<Disposable> disposables2 = partUploaderParcel.getDisposables();
                scheduler = PartUploader.this.callbackScheduler;
                Disposable subscribe = progressSubject.subscribeOn(scheduler).subscribe(new Consumer<ProgressUpdate>() { // from class: com.amazon.photos.uploader.cds.multipart.PartUploader$startMultiPartUploadUnSafe$1.1
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(ProgressUpdate p) {
                        PartProgressListener partProgressListener2 = partProgressListener;
                        Intrinsics.checkExpressionValueIsNotNull(p, "p");
                        partProgressListener2.onProgressUpdate(p.getBytesComplete(), p.getBytesTotal(), part.getPartId());
                    }
                });
                Intrinsics.checkExpressionValueIsNotNull(subscribe, "progressSubject\n        …                        }");
                disposables2.add(subscribe);
                PartUploader partUploader = PartUploader.this;
                Intrinsics.checkExpressionValueIsNotNull(part, "part");
                PartUploader.Companion.PartForUpload partForUpload$AndroidPhotosUploader_release = partUploader.getPartForUpload$AndroidPhotosUploader_release(part, partUploaderParcel.isLastSegment(), partUploaderParcel.getTotalPartsSize(), file, uri);
                uploadLogger2 = PartUploader.this.logger;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("REQ: ");
                outline1072.append(part.getUploadRequestId());
                outline1072.append(", Uploading part: ");
                outline1072.append(part.getPartId());
                outline1072.append(" with md5 ");
                uploadLogger3 = PartUploader.this.logger;
                outline1072.append(uploadLogger3.obfuscate$AndroidPhotosUploader_release(partForUpload$AndroidPhotosUploader_release.getPartInfo().getPartMd5()));
                outline1072.append(" and ");
                outline1072.append("size ");
                outline1072.append(partForUpload$AndroidPhotosUploader_release.getPartInfo().getPartSize());
                uploadLogger2.d$AndroidPhotosUploader_release("PartUploader", outline1072.toString());
                PartUploader partUploader2 = PartUploader.this;
                Intrinsics.checkExpressionValueIsNotNull(progressSubject, "progressSubject");
                Single<com.amazon.clouddrive.cdasdk.cdus.UploadPartResponse> makePartRequest$AndroidPhotosUploader_release = partUploader2.makePartRequest$AndroidPhotosUploader_release(partForUpload$AndroidPhotosUploader_release, progressSubject);
                partInfoDao = PartUploader.this.partInfoDao;
                partInfoDao.updatePartInfo(partForUpload$AndroidPhotosUploader_release.getPartInfo());
                scheduler2 = PartUploader.this.uploadScheduler;
                return Observable.just(makePartRequest$AndroidPhotosUploader_release.subscribeOn(scheduler2).subscribe(new Consumer<com.amazon.clouddrive.cdasdk.cdus.UploadPartResponse>() { // from class: com.amazon.photos.uploader.cds.multipart.PartUploader$startMultiPartUploadUnSafe$1.2

                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* compiled from: PartUploader.kt */
                    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "getEventName"}, k = 3, mv = {1, 1, 16})
                    /* renamed from: com.amazon.photos.uploader.cds.multipart.PartUploader$startMultiPartUploadUnSafe$1$2$1  reason: invalid class name */
                    /* loaded from: classes13.dex */
                    public static final class AnonymousClass1 implements MetricName {
                        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

                        AnonymousClass1() {
                        }

                        @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                        @NotNull
                        public final String getEventName() {
                            return CdsMetrics.PART_UPLOAD_TIME_TAKEN;
                        }
                    }

                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(com.amazon.clouddrive.cdasdk.cdus.UploadPartResponse partUploadResponse) {
                        Metrics metrics;
                        PartInfoDao partInfoDao2;
                        PartInfo copy;
                        UploadLogger uploadLogger4;
                        Intrinsics.checkExpressionValueIsNotNull(partUploadResponse, "partUploadResponse");
                        if (Intrinsics.areEqual(partUploadResponse.getStatus(), MultipartUploadStatus.UPLOAD_IN_PROGRESS)) {
                            PartProgressListener partProgressListener2 = partProgressListener;
                            String serverSidePartSize = partUploadResponse.getServerSidePartSize();
                            Intrinsics.checkExpressionValueIsNotNull(serverSidePartSize, "partUploadResponse.serverSidePartSize");
                            partProgressListener2.onProgressUpdate(Long.parseLong(serverSidePartSize), part.getPartSize(), part.getPartId());
                            metrics = PartUploader.this.metrics;
                            metrics.recordSimpleDuration("PartUploader", AnonymousClass1.INSTANCE, System.currentTimeMillis() - partUploaderParcel.getStartTime());
                            partInfoDao2 = PartUploader.this.partInfoDao;
                            copy = r7.copy((r35 & 1) != 0 ? r7.partId : 0L, (r35 & 2) != 0 ? r7.uploadRequestId : 0L, (r35 & 4) != 0 ? r7.partUploadState : PartUploadState.SUCCEEDED, (r35 & 8) != 0 ? r7.partEnqueueTimestamp : 0L, (r35 & 16) != 0 ? r7.partUploadStartTimestamp : 0L, (r35 & 32) != 0 ? r7.partUploadCompleteTimestamp : System.currentTimeMillis(), (r35 & 64) != 0 ? r7.partMd5 : partUploadResponse.getServerSidePartMD5(), (r35 & 128) != 0 ? r7.partSize : 0L, (r35 & 256) != 0 ? r7.partOffset : 0L, (r35 & 512) != 0 ? r7.serviceUploadId : null, (r35 & 1024) != 0 ? part.nodeId : null);
                            partInfoDao2.updatePartInfo(copy);
                            uploadLogger4 = PartUploader.this.logger;
                            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("REQ : ");
                            outline1073.append(partUploaderParcel.getUploadRequestId());
                            outline1073.append(" Part ");
                            outline1073.append(part.getPartId());
                            outline1073.append(" completed , Response : ");
                            outline1073.append(partUploadResponse.getStatus());
                            uploadLogger4.d$AndroidPhotosUploader_release("PartUploader", outline1073.toString());
                        }
                        if (atomicInteger.incrementAndGet() == partUploaderParcel.getPartInfoList().size()) {
                            PartUploader$startMultiPartUploadUnSafe$1 partUploader$startMultiPartUploadUnSafe$1 = PartUploader$startMultiPartUploadUnSafe$1.this;
                            function1.mo12165invoke(Integer.valueOf(atomicInteger.get()));
                        }
                    }
                }, new Consumer<Throwable>() { // from class: com.amazon.photos.uploader.cds.multipart.PartUploader$startMultiPartUploadUnSafe$1.3
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Throwable it2) {
                        PartUploader partUploader3 = PartUploader.this;
                        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                        PartInfo part2 = part;
                        Intrinsics.checkExpressionValueIsNotNull(part2, "part");
                        partUploader3.handlePartFailure$AndroidPhotosUploader_release(it2, part2, function12);
                    }
                }));
            }
        }).toList().blockingGet();
        Intrinsics.checkExpressionValueIsNotNull(blockingGet, "Observable.fromIterable(… }.toList().blockingGet()");
        disposables.addAll((Collection) blockingGet);
    }

    public final void clearParts(long j) {
        this.partInfoDao.deleteByRequestId(j);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0052 A[SYNTHETIC] */
    @androidx.annotation.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean ensureValidNodeIds$AndroidPhotosUploader_release(@org.jetbrains.annotations.NotNull java.util.List<com.amazon.photos.uploader.cds.multipart.PartInfo> r6) {
        /*
            r5 = this;
            java.lang.String r0 = "partInfoList"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            boolean r0 = r6.isEmpty()
            r1 = 0
            if (r0 == 0) goto Ld
            return r1
        Ld:
            java.lang.Object r0 = r6.get(r1)
            com.amazon.photos.uploader.cds.multipart.PartInfo r0 = (com.amazon.photos.uploader.cds.multipart.PartInfo) r0
            java.lang.String r0 = r0.getNodeId()
            boolean r2 = r6.isEmpty()
            r3 = 1
            if (r2 == 0) goto L1f
            goto L53
        L1f:
            java.util.Iterator r6 = r6.iterator()
        L23:
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto L53
            java.lang.Object r2 = r6.next()
            com.amazon.photos.uploader.cds.multipart.PartInfo r2 = (com.amazon.photos.uploader.cds.multipart.PartInfo) r2
            java.lang.String r4 = r2.getNodeId()
            if (r4 == 0) goto L3e
            boolean r4 = kotlin.text.StringsKt.isBlank(r4)
            if (r4 == 0) goto L3c
            goto L3e
        L3c:
            r4 = r1
            goto L3f
        L3e:
            r4 = r3
        L3f:
            if (r4 != 0) goto L4f
            java.lang.String r2 = r2.getNodeId()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r0)
            r2 = r2 ^ r3
            if (r2 == 0) goto L4d
            goto L4f
        L4d:
            r2 = r1
            goto L50
        L4f:
            r2 = r3
        L50:
            if (r2 == 0) goto L23
            r1 = r3
        L53:
            r6 = r1 ^ 1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.photos.uploader.cds.multipart.PartUploader.ensureValidNodeIds$AndroidPhotosUploader_release(java.util.List):boolean");
    }

    @VisibleForTesting
    @NotNull
    public final File getFile$AndroidPhotosUploader_release(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        return new File(uploadRequest.getFilePath());
    }

    @VisibleForTesting
    @NotNull
    public final Companion.PartForUpload getPartForUpload$AndroidPhotosUploader_release(@NotNull PartInfo partInfo, boolean z, long j, @NotNull File file, @NotNull Uri contentUri) {
        PartInfo copy;
        Intrinsics.checkParameterIsNotNull(partInfo, "partInfo");
        Intrinsics.checkParameterIsNotNull(file, "file");
        Intrinsics.checkParameterIsNotNull(contentUri, "contentUri");
        long partSize = partInfo.getPartSize();
        long length = (!z || partInfo.getPartOffset() != j - 1) ? partSize : file.length() - (partInfo.getPartOffset() * partSize);
        byte[] part = this.partProvider.getPart(contentUri, partInfo.getPartSize() * partInfo.getPartOffset(), (int) length);
        String partMd5 = partInfo.getPartMd5();
        if (partMd5 == null) {
            partMd5 = this.contentSignatureProvider.computeMd5FromByteArray$AndroidPhotosUploader_release(part);
        }
        copy = partInfo.copy((r35 & 1) != 0 ? partInfo.partId : 0L, (r35 & 2) != 0 ? partInfo.uploadRequestId : 0L, (r35 & 4) != 0 ? partInfo.partUploadState : PartUploadState.RUNNING, (r35 & 8) != 0 ? partInfo.partEnqueueTimestamp : 0L, (r35 & 16) != 0 ? partInfo.partUploadStartTimestamp : System.currentTimeMillis(), (r35 & 32) != 0 ? partInfo.partUploadCompleteTimestamp : 0L, (r35 & 64) != 0 ? partInfo.partMd5 : partMd5, (r35 & 128) != 0 ? partInfo.partSize : length, (r35 & 256) != 0 ? partInfo.partOffset : 0L, (r35 & 512) != 0 ? partInfo.serviceUploadId : null, (r35 & 1024) != 0 ? partInfo.nodeId : null);
        return new Companion.PartForUpload(part, copy);
    }

    /* JADX WARN: Type inference failed for: r2v21, types: [T, com.amazon.photos.uploader.UploadErrorCategory] */
    /* JADX WARN: Type inference failed for: r2v23, types: [T, com.amazon.photos.uploader.UploadErrorCategory] */
    /* JADX WARN: Type inference failed for: r2v4, types: [T, com.amazon.photos.uploader.UploadErrorCategory] */
    /* JADX WARN: Type inference failed for: r4v8, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v9, types: [T, java.lang.String] */
    @VisibleForTesting
    public final void handlePartFailure$AndroidPhotosUploader_release(@NotNull final Throwable throwable, @NotNull PartInfo partInfo, @NotNull Function1<? super UploadResponse, Unit> onError) {
        PartInfo partInfo2;
        UploadResponse.Failure failure;
        PartInfo copy;
        PartInfo part = partInfo;
        Intrinsics.checkParameterIsNotNull(throwable, "throwable");
        Intrinsics.checkParameterIsNotNull(part, "part");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = UploadResponse.UNKNOWN_UPLOAD_ERROR;
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = UploadErrorCategory.SERVER_ERROR;
        if (throwable instanceof CDUSException) {
            UploadPartResponse resolve = this.partErrorResolver.resolve(part, (CDUSException) throwable);
            boolean z = resolve instanceof UploadPartResponse.Failure;
            if (!(z || (resolve instanceof UploadPartResponse.NoRetryFailure))) {
                throw new IllegalStateException("Check failed.".toString());
            }
            if (z) {
                UploadPartResponse.Failure failure2 = (UploadPartResponse.Failure) resolve;
                if (failure2.getRevisedRequest() != null) {
                    part = failure2.getRevisedRequest();
                }
                failure = new UploadResponse.Failure(failure2.getErrorCode(), failure2.getEx(), failure2.getErrorCategory(), null, false, 24, null);
                objectRef.element = failure2.getErrorCode();
                objectRef2.element = failure2.getErrorCategory();
            } else if (!(resolve instanceof UploadPartResponse.NoRetryFailure)) {
                throw new NoWhenBranchMatchedException();
            } else {
                UploadPartResponse.NoRetryFailure noRetryFailure = (UploadPartResponse.NoRetryFailure) resolve;
                failure = new UploadResponse.Failure(noRetryFailure.getErrorCode(), noRetryFailure.getEx(), noRetryFailure.getErrorCategory(), null, false, 24, null);
                objectRef.element = noRetryFailure.getErrorCode();
                objectRef2.element = noRetryFailure.getErrorCategory();
            }
            partInfo2 = part;
        } else {
            partInfo2 = part;
            failure = new UploadResponse.Failure((String) objectRef.element, throwable, (UploadErrorCategory) objectRef2.element, null, false, 24, null);
        }
        this.metrics.recordCustomMetric(TAG, new ClientMetric().addCounter(PartUploader$handlePartFailure$clientMetric$1.INSTANCE, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.multipart.PartUploader$handlePartFailure$clientMetric$2
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PART_UPLOAD_EXCEPTION:");
                outline107.append((String) Ref.ObjectRef.this.element);
                return outline107.toString();
            }
        }, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.multipart.PartUploader$handlePartFailure$clientMetric$3
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PART_UPLOAD_EXCEPTION:");
                outline107.append(throwable.getClass().getSimpleName());
                return outline107.toString();
            }
        }, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.multipart.PartUploader$handlePartFailure$clientMetric$4
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            @NotNull
            public final String getEventName() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PART_UPLOAD_EXCEPTION_CATEGORY_");
                outline107.append(((UploadErrorCategory) Ref.ObjectRef.this.element).name());
                return outline107.toString();
            }
        }, 1).withTagName(TAG), new MetricRecordingType[0]);
        try {
            PartInfoDao partInfoDao = this.partInfoDao;
            copy = partInfo2.copy((r35 & 1) != 0 ? partInfo2.partId : 0L, (r35 & 2) != 0 ? partInfo2.uploadRequestId : 0L, (r35 & 4) != 0 ? partInfo2.partUploadState : PartUploadState.FAILED, (r35 & 8) != 0 ? partInfo2.partEnqueueTimestamp : 0L, (r35 & 16) != 0 ? partInfo2.partUploadStartTimestamp : 0L, (r35 & 32) != 0 ? partInfo2.partUploadCompleteTimestamp : 0L, (r35 & 64) != 0 ? partInfo2.partMd5 : null, (r35 & 128) != 0 ? partInfo2.partSize : 0L, (r35 & 256) != 0 ? partInfo2.partOffset : 0L, (r35 & 512) != 0 ? partInfo2.serviceUploadId : null, (r35 & 1024) != 0 ? partInfo2.nodeId : null);
            partInfoDao.updatePartInfo(copy);
            onError.mo12165invoke(failure);
        } catch (Exception e) {
            this.metrics.recordCustomMetric(TAG, new ClientMetric().addCounter(PartUploader$handlePartFailure$dbMetric$1.INSTANCE, 1).addCounter(new MetricName() { // from class: com.amazon.photos.uploader.cds.multipart.PartUploader$handlePartFailure$dbMetric$2
                @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                @NotNull
                public final String getEventName() {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DB_PART_UPLOAD_EXCEPTION:");
                    outline107.append(throwable.getClass().getSimpleName());
                    return outline107.toString();
                }
            }, 1).withTagName(TAG), new MetricRecordingType[0]);
            this.logger.e$AndroidPhotosUploader_release(TAG, "Exception while saving the revised request", e);
            onError.mo12165invoke(new UploadResponse.Failure((String) objectRef.element, e, (UploadErrorCategory) objectRef2.element, null, false, 24, null));
        }
    }

    @VisibleForTesting
    @NotNull
    public final Single<com.amazon.clouddrive.cdasdk.cdus.UploadPartResponse> makePartRequest$AndroidPhotosUploader_release(@NotNull Companion.PartForUpload partForUpload, @NotNull PublishSubject<ProgressUpdate> progressSubject) {
        Intrinsics.checkParameterIsNotNull(partForUpload, "partForUpload");
        Intrinsics.checkParameterIsNotNull(progressSubject, "progressSubject");
        UploadPartRequest uploadPartRequest = new UploadPartRequest(partForUpload.getPartInfo().getServiceUploadId(), Long.valueOf(partForUpload.getPartInfo().getPartSize()));
        CDUSCalls cDUSCalls = this.cdClient.getCDUSCalls();
        String nodeId = partForUpload.getPartInfo().getNodeId();
        if (nodeId != null) {
            Long valueOf = Long.valueOf(partForUpload.getPartInfo().getPartId());
            String partMd5 = partForUpload.getPartInfo().getPartMd5();
            if (partMd5 != null) {
                Single<com.amazon.clouddrive.cdasdk.cdus.UploadPartResponse> uploadPart = cDUSCalls.uploadPart(uploadPartRequest, nodeId, valueOf, partMd5, this.mediaType, partForUpload.getPayLoad(), progressSubject);
                Intrinsics.checkExpressionValueIsNotNull(uploadPart, "cdClient.cdusCalls.uploa…payLoad, progressSubject)");
                return uploadPart;
            }
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    public final void startMultiPartUpload$AndroidPhotosUploader_release(@NotNull PartUploaderParcel partUploaderParcel, @NotNull File file, @NotNull Uri contentUri, @NotNull PartProgressListener progressListener, @NotNull Function1<? super Integer, Unit> onPartsComplete, @NotNull Function1<? super UploadResponse, Unit> onError) {
        Intrinsics.checkParameterIsNotNull(partUploaderParcel, "partUploaderParcel");
        Intrinsics.checkParameterIsNotNull(file, "file");
        Intrinsics.checkParameterIsNotNull(contentUri, "contentUri");
        Intrinsics.checkParameterIsNotNull(progressListener, "progressListener");
        Intrinsics.checkParameterIsNotNull(onPartsComplete, "onPartsComplete");
        Intrinsics.checkParameterIsNotNull(onError, "onError");
        try {
            startMultiPartUploadUnSafe(partUploaderParcel, file, contentUri, progressListener, onPartsComplete, onError);
            Unit unit = Unit.INSTANCE;
        } catch (CDUSException e) {
            this.logger.e$AndroidPhotosUploader_release(TAG, "CDUSException received while calling startMultiPartUpload", e);
            CDUSError cdusError = e.getCdusError();
            Intrinsics.checkExpressionValueIsNotNull(cdusError, "ex.cdusError");
            String errorCode = cdusError.getErrorCode();
            if (errorCode == null) {
                errorCode = UploadResponse.UNKNOWN_UPLOAD_ERROR;
            }
            onError.mo12165invoke(new UploadResponse.Failure(errorCode, e, UploadErrorCategory.UNKNOWN_ERROR, null, false, 24, null));
        } catch (Exception e2) {
            this.logger.e$AndroidPhotosUploader_release(TAG, "Exception received while calling startMultiPartUpload", e2);
            onError.mo12165invoke(new UploadResponse.Failure(UploadResponse.UNKNOWN_UPLOAD_ERROR, e2, UploadErrorCategory.UNKNOWN_ERROR, null, false, 24, null));
        }
    }
}
