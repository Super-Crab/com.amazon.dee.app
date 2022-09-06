package com.amazon.alexa.photos;

import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.photos.events.PhotosUploaderEventType;
import com.amazon.alexa.photos.events.UploadFailureEvent;
import com.amazon.alexa.photos.events.UploadSuccessEvent;
import com.amazon.alexa.photos.events.UploadsCompleteEvent;
import com.amazon.alexa.photos.events.UploadsStartedEvent;
import com.amazon.alexa.photos.metrics.CloudDriveMetrics;
import com.amazon.alexa.photos.metrics.PhotosMetricsConstants;
import com.amazon.alexa.photos.util.SystemUtility;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.ConflictError;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtendedClient;
import com.amazon.clouddrive.extended.exception.InvalidFileLengthException;
import com.amazon.clouddrive.extended.model.UploadFileExtendedResponse;
import dagger.Lazy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class UploadOperation {
    private static final String TAG = "UploadOperation";
    private final Lazy<AmazonCloudDriveExtendedClient> cdClient;
    private final CdsRequestHelper cdsRequestHelper;
    private final Lazy<CloudDriveMetrics> cloudDriveMetrics;
    private final List<Uri> contentUris;
    private final AtomicInteger errorCount;
    private volatile boolean isUploadInProgress;
    private final MessagePublisher messagePublisher;
    private final String parentNodeId;
    private final AtomicInteger successCount;
    private final Lazy<SystemUtility> systemUtil;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class Factory {
        private final Lazy<AmazonCloudDriveExtendedClient> cdClient;
        private final CdsRequestHelper cdsRequestHelper;
        private final Lazy<CloudDriveMetrics> cloudDriveMetrics;
        private final MessagePublisher messagePublisher;
        private final Lazy<SystemUtility> systemUtil;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Factory(@NonNull Lazy<AmazonCloudDriveExtendedClient> lazy, @NonNull Lazy<CloudDriveMetrics> lazy2, @NonNull Lazy<SystemUtility> lazy3, @NonNull CdsRequestHelper cdsRequestHelper, @NonNull MessagePublisher messagePublisher) {
            this.cdClient = lazy;
            this.cloudDriveMetrics = lazy2;
            this.systemUtil = lazy3;
            this.cdsRequestHelper = cdsRequestHelper;
            this.messagePublisher = messagePublisher;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NonNull
        public UploadOperation getUploadOperation(@NonNull List<Uri> list, @NonNull String str) {
            return new UploadOperation(this.cdClient, this.cloudDriveMetrics, this.systemUtil, this.cdsRequestHelper, this.messagePublisher, list, str);
        }
    }

    @NonNull
    private Observable<UploadFileExtendedResponse> getUploadObservable(@NonNull final Uri uri, @NonNull final String str) {
        final AtomicLong atomicLong = new AtomicLong(0L);
        return Observable.fromCallable(new Callable() { // from class: com.amazon.alexa.photos.-$$Lambda$UploadOperation$mQE2a9FK7Vv8jLO638wPxiW0n9o
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return UploadOperation.this.lambda$getUploadObservable$3$UploadOperation(atomicLong, uri, str);
            }
        }).onErrorResumeNext(new Function() { // from class: com.amazon.alexa.photos.-$$Lambda$UploadOperation$qBih21c29KeJJ8XQ7WcUV32PDpQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return UploadOperation.this.lambda$getUploadObservable$9$UploadOperation(atomicLong, (Throwable) obj);
            }
        });
    }

    static /* synthetic */ String lambda$recordDuration$10(String str) {
        return str;
    }

    private void recordDuration(final String str, long j) {
        this.cloudDriveMetrics.mo358get().recordSimpleDuration(TAG, new MetricName() { // from class: com.amazon.alexa.photos.-$$Lambda$UploadOperation$3hHC0Nc_ugzPAldNcYJ6Do77NJI
            @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
            public final String getEventName() {
                return str;
            }
        }, this.systemUtil.mo358get().currentTimeMillis() - j);
    }

    private void recordUploadErrorEvent(Throwable th, String str, int i) {
        Log.e(TAG, str, th);
        this.errorCount.getAndIncrement();
        this.messagePublisher.publishMessage(PhotosUploaderEventType.FAILURE, new UploadFailureEvent(th.getMessage(), th.getClass().getSimpleName(), i));
    }

    private void recordUploadSuccessEvent(String str, String str2) {
        this.successCount.getAndIncrement();
        this.messagePublisher.publishMessage(PhotosUploaderEventType.SUCCESS, new UploadSuccessEvent(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isUploadInProgress() {
        return this.isUploadInProgress;
    }

    public /* synthetic */ UploadFileExtendedResponse lambda$getUploadObservable$3$UploadOperation(AtomicLong atomicLong, Uri uri, String str) throws Exception {
        atomicLong.set(this.systemUtil.mo358get().currentTimeMillis());
        UploadFileExtendedResponse uploadFileExtended = this.cdClient.mo358get().uploadFileExtended(this.cdsRequestHelper.getUploadFileRequest(uri, str), null);
        recordDuration(PhotosMetricsConstants.UPLOAD_SUCCESS, atomicLong.get());
        return uploadFileExtended;
    }

    public /* synthetic */ ObservableSource lambda$getUploadObservable$9$UploadOperation(AtomicLong atomicLong, Throwable th) throws Throwable {
        if (th instanceof FileNotFoundException) {
            this.cloudDriveMetrics.mo358get().recordSimpleErrorEvent(TAG, $$Lambda$UploadOperation$lvBOjhwExBtDLxgI3iIH2NkQjk.INSTANCE, "Unable to read file for upload", th);
            recordUploadErrorEvent(th, "File Not Found", 11);
        } else if (th instanceof CloudDriveException) {
            if (th instanceof ConflictError) {
                ConflictError conflictError = (ConflictError) th;
                if ("NAME_ALREADY_EXISTS".equals(conflictError.getCode())) {
                    this.cloudDriveMetrics.mo358get().recordEvent(TAG, PhotosMetricsConstants.UPLOAD_DUPLICATE);
                    recordDuration(PhotosMetricsConstants.UPLOAD_SUCCESS, atomicLong.get());
                    recordUploadSuccessEvent(conflictError.getNodeId(), "File already uploaded");
                }
            }
            this.cloudDriveMetrics.mo358get().recordSimpleErrorEvent(TAG, $$Lambda$UploadOperation$trfoY1y0AbUesIBob21ZXSzbWVY.INSTANCE, "Cloud Drive service error", th);
            recordUploadErrorEvent(th, "Upload Failed", ((CloudDriveException) th).getHttpCode());
        } else if (th instanceof InterruptedException) {
            this.cloudDriveMetrics.mo358get().recordSimpleErrorEvent(TAG, $$Lambda$UploadOperation$LQop5MnKhabWVRuTP8QHOCarQm0.INSTANCE, "Interrupted error", th);
            recordUploadErrorEvent(th, "Upload Interrupted", 12);
        } else if (th instanceof InvalidFileLengthException) {
            this.cloudDriveMetrics.mo358get().recordSimpleErrorEvent(TAG, $$Lambda$UploadOperation$WcLarJRBN9rMH8IqH1DNK73xubw.INSTANCE, "Invalid file length", th);
            recordUploadErrorEvent(th, "Upload failed due to file length failure", 13);
        } else {
            this.cloudDriveMetrics.mo358get().recordSimpleErrorEvent(TAG, $$Lambda$UploadOperation$Nq6VkiAg3CE4BmjPdSl73Fb10Nw.INSTANCE, "Upload failed to complete.", th);
            recordUploadErrorEvent(th, "Upload Failed", 10);
        }
        return Observable.empty();
    }

    public /* synthetic */ void lambda$setupUploadObservable$1$UploadOperation(UploadFileExtendedResponse uploadFileExtendedResponse) throws Throwable {
        recordUploadSuccessEvent(uploadFileExtendedResponse.getId(), String.format("Upload success for nodeId: %s", uploadFileExtendedResponse.getId()));
    }

    public /* synthetic */ void lambda$setupUploadObservable$2$UploadOperation(long j) throws Throwable {
        this.isUploadInProgress = false;
        Log.i(TAG, "Upload Flow Complete");
        recordDuration(PhotosMetricsConstants.UPLOAD_FLOW_COMPLETE, j);
        this.messagePublisher.publishMessage(PhotosUploaderEventType.COMPLETE, new UploadsCompleteEvent(this.successCount.get(), this.errorCount.get()));
    }

    public /* synthetic */ ObservableSource lambda$start$0$UploadOperation(Uri uri) throws Throwable {
        return getUploadObservable(uri, this.parentNodeId);
    }

    @NonNull
    @VisibleForTesting
    Observable<UploadFileExtendedResponse> setupUploadObservable(@NonNull Observable<UploadFileExtendedResponse> observable) {
        this.isUploadInProgress = true;
        final long currentTimeMillis = this.systemUtil.mo358get().currentTimeMillis();
        return observable.doOnNext(new Consumer() { // from class: com.amazon.alexa.photos.-$$Lambda$UploadOperation$ttl835nBEBEc8lEZHRlzuU7-5YQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                UploadOperation.this.lambda$setupUploadObservable$1$UploadOperation((UploadFileExtendedResponse) obj);
            }
        }).doOnComplete(new Action() { // from class: com.amazon.alexa.photos.-$$Lambda$UploadOperation$GYXFo0hPXE5X2sV7lC9tedYErFs
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                UploadOperation.this.lambda$setupUploadObservable$2$UploadOperation(currentTimeMillis);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Disposable start() {
        int size = this.contentUris.size();
        String.format("%d photos selected to be uploaded", Integer.valueOf(size));
        this.messagePublisher.publishMessage(PhotosUploaderEventType.START, new UploadsStartedEvent(size));
        return setupUploadObservable(Observable.fromIterable(this.contentUris).flatMap(new Function() { // from class: com.amazon.alexa.photos.-$$Lambda$UploadOperation$WMMHzoAWO7zHUmkH6fDDPWJ_yMU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return UploadOperation.this.lambda$start$0$UploadOperation((Uri) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())).subscribe();
    }

    private UploadOperation(@NonNull Lazy<AmazonCloudDriveExtendedClient> lazy, @NonNull Lazy<CloudDriveMetrics> lazy2, @NonNull Lazy<SystemUtility> lazy3, @NonNull CdsRequestHelper cdsRequestHelper, @NonNull MessagePublisher messagePublisher, @NonNull List<Uri> list, @NonNull String str) {
        this.isUploadInProgress = false;
        this.cdClient = lazy;
        this.cloudDriveMetrics = lazy2;
        this.systemUtil = lazy3;
        this.cdsRequestHelper = cdsRequestHelper;
        this.messagePublisher = messagePublisher;
        this.contentUris = list;
        this.parentNodeId = str;
        this.successCount = new AtomicInteger();
        this.errorCount = new AtomicInteger();
    }
}
