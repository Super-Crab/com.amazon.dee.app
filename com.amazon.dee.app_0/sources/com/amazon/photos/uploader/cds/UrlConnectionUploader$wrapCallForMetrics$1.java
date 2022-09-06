package com.amazon.photos.uploader.cds;

import android.os.SystemClock;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.amazon.photos.uploader.cds.UrlConnectionUploader;
import com.amazon.photos.uploader.log.UploadLogger;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UrlConnectionUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00000\u0001J\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"com/amazon/photos/uploader/cds/UrlConnectionUploader$wrapCallForMetrics$1", "Lio/reactivex/rxjava3/core/SingleTransformer;", "callStartTime", "", "apply", "Lio/reactivex/rxjava3/core/SingleSource;", "upstream", "Lio/reactivex/rxjava3/core/Single;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UrlConnectionUploader$wrapCallForMetrics$1 implements SingleTransformer<O, O> {
    final /* synthetic */ String $callName;
    private long callStartTime;
    final /* synthetic */ UrlConnectionUploader this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UrlConnectionUploader$wrapCallForMetrics$1(UrlConnectionUploader urlConnectionUploader, String str) {
        this.this$0 = urlConnectionUploader;
        this.$callName = str;
    }

    @Override // io.reactivex.rxjava3.core.SingleTransformer
    @NotNull
    public SingleSource<O> apply(@NotNull Single<O> upstream) {
        Intrinsics.checkParameterIsNotNull(upstream, "upstream");
        Single onErrorResumeNext = upstream.doOnSubscribe(new Consumer<Disposable>() { // from class: com.amazon.photos.uploader.cds.UrlConnectionUploader$wrapCallForMetrics$1$apply$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Disposable disposable) {
                UrlConnectionUploader$wrapCallForMetrics$1.this.callStartTime = SystemClock.elapsedRealtime();
            }
        }).doAfterSuccess(new Consumer<O>() { // from class: com.amazon.photos.uploader.cds.UrlConnectionUploader$wrapCallForMetrics$1$apply$2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(O o) {
                long j;
                UploadLogger uploadLogger;
                UrlConnectionUploader.Companion unused;
                long elapsedRealtime = SystemClock.elapsedRealtime();
                j = UrlConnectionUploader$wrapCallForMetrics$1.this.callStartTime;
                long j2 = elapsedRealtime - j;
                uploadLogger = UrlConnectionUploader$wrapCallForMetrics$1.this.this$0.logger;
                unused = UrlConnectionUploader.Companion;
                uploadLogger.d$AndroidPhotosUploader_release("UrlConnectionUploader", " Time taken overall = " + j2);
                UrlConnectionUploader$wrapCallForMetrics$1 urlConnectionUploader$wrapCallForMetrics$1 = UrlConnectionUploader$wrapCallForMetrics$1.this;
                urlConnectionUploader$wrapCallForMetrics$1.this$0.recordCallSuccess(urlConnectionUploader$wrapCallForMetrics$1.$callName, j2);
            }
        }).onErrorResumeNext(new Function<Throwable, SingleSource<? extends O>>() { // from class: com.amazon.photos.uploader.cds.UrlConnectionUploader$wrapCallForMetrics$1$apply$3
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public final Single<O> mo10358apply(Throwable throwable) {
                if (throwable instanceof CloudDriveException) {
                    UrlConnectionUploader$wrapCallForMetrics$1 urlConnectionUploader$wrapCallForMetrics$1 = UrlConnectionUploader$wrapCallForMetrics$1.this;
                    urlConnectionUploader$wrapCallForMetrics$1.this$0.recordErrorMetric(urlConnectionUploader$wrapCallForMetrics$1.$callName, (CloudDriveException) throwable);
                } else {
                    UrlConnectionUploader$wrapCallForMetrics$1 urlConnectionUploader$wrapCallForMetrics$12 = UrlConnectionUploader$wrapCallForMetrics$1.this;
                    UrlConnectionUploader urlConnectionUploader = urlConnectionUploader$wrapCallForMetrics$12.this$0;
                    String str = urlConnectionUploader$wrapCallForMetrics$12.$callName;
                    Intrinsics.checkExpressionValueIsNotNull(throwable, "throwable");
                    urlConnectionUploader.recordUnknownErrorMetric(str, throwable);
                }
                return Single.error(throwable);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(onErrorResumeNext, "upstream\n               …                        }");
        return onErrorResumeNext;
    }
}
