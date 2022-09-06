package com.amazon.photos.uploader.observables;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploadSummaryObservable.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000b"}, d2 = {"Lcom/amazon/photos/uploader/observables/UploadSummaryObservable;", "", "addObserver", "", "observer", "Lcom/amazon/photos/uploader/observables/UploadSummaryObserver;", "scheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "worker", "Lio/reactivex/rxjava3/core/Scheduler$Worker;", "removeObserver", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface UploadSummaryObservable {

    /* compiled from: UploadSummaryObservable.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ boolean addObserver$default(UploadSummaryObservable uploadSummaryObservable, UploadSummaryObserver uploadSummaryObserver, Scheduler scheduler, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    scheduler = Schedulers.computation();
                    Intrinsics.checkExpressionValueIsNotNull(scheduler, "Schedulers.computation()");
                }
                return uploadSummaryObservable.addObserver(uploadSummaryObserver, scheduler);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addObserver");
        }
    }

    boolean addObserver(@NotNull UploadSummaryObserver uploadSummaryObserver, @NotNull Scheduler.Worker worker);

    boolean addObserver(@NotNull UploadSummaryObserver uploadSummaryObserver, @NotNull Scheduler scheduler);

    boolean removeObserver(@NotNull UploadSummaryObserver uploadSummaryObserver);
}
