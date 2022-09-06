package com.amazon.photos.uploader.internal.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.internal.observers.EmittingSummaryObserver;
import com.amazon.photos.uploader.observables.UploadSummary;
import com.amazon.photos.uploader.observables.UploadSummaryObservable;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: GlobalBlockerLiveDataProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00072\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u000eH\u0002J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007H\u0002J\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007R \u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/photos/uploader/internal/livedata/GlobalBlockerLiveDataProvider;", "", "summaryObservable", "Lcom/amazon/photos/uploader/observables/UploadSummaryObservable;", "(Lcom/amazon/photos/uploader/observables/UploadSummaryObservable;)V", "liveDataReference", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/lifecycle/LiveData;", "", "Lcom/amazon/photos/uploader/blockers/Blocker;", "summaryObserver", "Lcom/amazon/photos/uploader/internal/observers/EmittingSummaryObserver;", "attachGlobalBlockerLiveData", "liveData", "Landroidx/lifecycle/MediatorLiveData;", "createGlobalBlockerLiveData", "getLiveData", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class GlobalBlockerLiveDataProvider {
    private final AtomicReference<LiveData<List<Blocker>>> liveDataReference;
    private final EmittingSummaryObserver summaryObserver;

    public GlobalBlockerLiveDataProvider(@NotNull UploadSummaryObservable summaryObservable) {
        Intrinsics.checkParameterIsNotNull(summaryObservable, "summaryObservable");
        this.summaryObserver = new EmittingSummaryObserver();
        this.liveDataReference = new AtomicReference<>();
        EmittingSummaryObserver emittingSummaryObserver = this.summaryObserver;
        Scheduler computation = Schedulers.computation();
        Intrinsics.checkExpressionValueIsNotNull(computation, "Schedulers.computation()");
        summaryObservable.addObserver(emittingSummaryObserver, computation);
    }

    private final LiveData<List<Blocker>> attachGlobalBlockerLiveData(final MediatorLiveData<List<Blocker>> mediatorLiveData) {
        List<Blocker> emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        mediatorLiveData.postValue(emptyList);
        mediatorLiveData.addSource(LiveDataReactiveStreams.fromPublisher(Flowable.create(new FlowableOnSubscribe<T>() { // from class: com.amazon.photos.uploader.internal.livedata.GlobalBlockerLiveDataProvider$attachGlobalBlockerLiveData$summarySource$1
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter<UploadSummary> emitter) {
                EmittingSummaryObserver emittingSummaryObserver;
                emittingSummaryObserver = GlobalBlockerLiveDataProvider.this.summaryObserver;
                Intrinsics.checkExpressionValueIsNotNull(emitter, "emitter");
                emittingSummaryObserver.addEmitter(emitter);
            }
        }, BackpressureStrategy.LATEST).observeOn(Schedulers.computation()).map(GlobalBlockerLiveDataProvider$attachGlobalBlockerLiveData$sortedSource$1.INSTANCE)), new Observer<S>() { // from class: com.amazon.photos.uploader.internal.livedata.GlobalBlockerLiveDataProvider$attachGlobalBlockerLiveData$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<? extends Blocker> list) {
                MediatorLiveData.this.postValue(list);
            }
        });
        return mediatorLiveData;
    }

    private final LiveData<List<Blocker>> createGlobalBlockerLiveData() {
        MediatorLiveData<List<Blocker>> mediatorLiveData = new MediatorLiveData<>();
        if (this.liveDataReference.compareAndSet(null, mediatorLiveData)) {
            attachGlobalBlockerLiveData(mediatorLiveData);
        }
        LiveData<List<Blocker>> liveData = this.liveDataReference.get();
        Intrinsics.checkExpressionValueIsNotNull(liveData, "liveDataReference.get()");
        return liveData;
    }

    @NotNull
    public final LiveData<List<Blocker>> getLiveData() {
        LiveData<List<Blocker>> liveData = this.liveDataReference.get();
        return liveData != null ? liveData : createGlobalBlockerLiveData();
    }
}
