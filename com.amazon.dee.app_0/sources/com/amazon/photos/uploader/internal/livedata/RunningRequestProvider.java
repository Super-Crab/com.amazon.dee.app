package com.amazon.photos.uploader.internal.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.amazon.photos.uploader.UploadProgress;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadState;
import com.amazon.photos.uploader.internal.observers.EmittingRequestObserver;
import com.amazon.photos.uploader.observables.UploadRequestObservable;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: RunningRequestProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0002J@\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00112\u0006\u0010\u0012\u001a\u00020\u00072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0015H\u0002J\u001c\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0012\u001a\u00020\u0007H\u0002J2\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010\u0012\u001a\u00020\u00072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0015H\u0002J\"\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ8\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0015R&\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/amazon/photos/uploader/internal/livedata/RunningRequestProvider;", "", "requestObservable", "Lcom/amazon/photos/uploader/observables/UploadRequestObservable;", "(Lcom/amazon/photos/uploader/observables/UploadRequestObservable;)V", "liveDatas", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Landroidx/lifecycle/LiveData;", "", "Lcom/amazon/photos/uploader/UploadRequest;", "requestObserver", "Lcom/amazon/photos/uploader/internal/observers/EmittingRequestObserver;", "totalProgressLiveDatas", "Lcom/amazon/photos/uploader/UploadProgress;", "attachThrottledLiveData", "liveData", "Landroidx/lifecycle/MediatorLiveData;", "throttle", "attachThrottledTotalProgressLiveData", "queues", "", "", "requestStates", "Lcom/amazon/photos/uploader/UploadState;", "createThrottledLiveData", "createThrottledTotalProgressLiveData", "getLiveData", "updatesPer", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "getTotalProgressLiveData", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class RunningRequestProvider {
    private final ConcurrentHashMap<Long, LiveData<List<UploadRequest>>> liveDatas;
    private final EmittingRequestObserver requestObserver;
    private final ConcurrentHashMap<Long, LiveData<UploadProgress>> totalProgressLiveDatas;

    public RunningRequestProvider(@NotNull UploadRequestObservable requestObservable) {
        Intrinsics.checkParameterIsNotNull(requestObservable, "requestObservable");
        this.requestObserver = new EmittingRequestObserver();
        this.liveDatas = new ConcurrentHashMap<>();
        this.totalProgressLiveDatas = new ConcurrentHashMap<>();
        EmittingRequestObserver emittingRequestObserver = this.requestObserver;
        Scheduler computation = Schedulers.computation();
        Intrinsics.checkExpressionValueIsNotNull(computation, "Schedulers.computation()");
        requestObservable.addObserver(emittingRequestObserver, computation);
    }

    private final LiveData<List<UploadRequest>> attachThrottledLiveData(final MediatorLiveData<List<UploadRequest>> mediatorLiveData, long j) {
        List<UploadRequest> emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        mediatorLiveData.postValue(emptyList);
        mediatorLiveData.addSource(LiveDataReactiveStreams.fromPublisher(Flowable.create(new FlowableOnSubscribe<T>() { // from class: com.amazon.photos.uploader.internal.livedata.RunningRequestProvider$attachThrottledLiveData$throttledSource$1
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter<Collection<UploadRequest>> emitter) {
                EmittingRequestObserver emittingRequestObserver;
                emittingRequestObserver = RunningRequestProvider.this.requestObserver;
                Intrinsics.checkExpressionValueIsNotNull(emitter, "emitter");
                emittingRequestObserver.addEmitter(emitter);
            }
        }, BackpressureStrategy.LATEST).debounce(j, TimeUnit.MILLISECONDS).observeOn(Schedulers.computation()).map(RunningRequestProvider$attachThrottledLiveData$sortedSource$1.INSTANCE)), new Observer<S>() { // from class: com.amazon.photos.uploader.internal.livedata.RunningRequestProvider$attachThrottledLiveData$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<UploadRequest> list) {
                MediatorLiveData.this.postValue(list);
            }
        });
        return mediatorLiveData;
    }

    private final LiveData<UploadProgress> attachThrottledTotalProgressLiveData(final MediatorLiveData<UploadProgress> mediatorLiveData, long j, final Collection<String> collection, final Collection<? extends UploadState> collection2) {
        mediatorLiveData.addSource(LiveDataReactiveStreams.fromPublisher(Flowable.create(new FlowableOnSubscribe<T>() { // from class: com.amazon.photos.uploader.internal.livedata.RunningRequestProvider$attachThrottledTotalProgressLiveData$throttledSource$1
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter<Collection<UploadRequest>> emitter) {
                EmittingRequestObserver emittingRequestObserver;
                emittingRequestObserver = RunningRequestProvider.this.requestObserver;
                Intrinsics.checkExpressionValueIsNotNull(emitter, "emitter");
                emittingRequestObserver.addEmitter(emitter);
            }
        }, BackpressureStrategy.LATEST).debounce(j, TimeUnit.MILLISECONDS).observeOn(Schedulers.computation()).map(new Function<T, R>() { // from class: com.amazon.photos.uploader.internal.livedata.RunningRequestProvider$attachThrottledTotalProgressLiveData$totalProgressSource$1
            @Override // io.reactivex.rxjava3.functions.Function
            @NotNull
            /* renamed from: apply */
            public final UploadProgress mo10358apply(Collection<UploadRequest> requests) {
                Intrinsics.checkExpressionValueIsNotNull(requests, "requests");
                ArrayList<UploadRequest> arrayList = new ArrayList();
                for (T t : requests) {
                    UploadRequest uploadRequest = (UploadRequest) t;
                    if (collection.contains(uploadRequest.getQueue()) && collection2.contains(uploadRequest.getState())) {
                        arrayList.add(t);
                    }
                }
                long j2 = 0;
                long j3 = 0;
                for (UploadRequest uploadRequest2 : arrayList) {
                    j3 += uploadRequest2.getCurrentProgress();
                }
                for (UploadRequest uploadRequest3 : arrayList) {
                    j2 += uploadRequest3.getMaxProgress();
                }
                return new UploadProgress(j3, j2);
            }
        })), new Observer<S>() { // from class: com.amazon.photos.uploader.internal.livedata.RunningRequestProvider$attachThrottledTotalProgressLiveData$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(UploadProgress uploadProgress) {
                MediatorLiveData.this.postValue(uploadProgress);
            }
        });
        return mediatorLiveData;
    }

    private final LiveData<List<UploadRequest>> createThrottledLiveData(long j) {
        MediatorLiveData<List<UploadRequest>> mediatorLiveData = new MediatorLiveData<>();
        LiveData<List<UploadRequest>> putIfAbsent = this.liveDatas.putIfAbsent(Long.valueOf(j), mediatorLiveData);
        return putIfAbsent != null ? putIfAbsent : attachThrottledLiveData(mediatorLiveData, j);
    }

    private final LiveData<UploadProgress> createThrottledTotalProgressLiveData(long j, Collection<String> collection, Collection<? extends UploadState> collection2) {
        MediatorLiveData<UploadProgress> mediatorLiveData = new MediatorLiveData<>();
        LiveData<UploadProgress> putIfAbsent = this.totalProgressLiveDatas.putIfAbsent(Long.valueOf(j), mediatorLiveData);
        return putIfAbsent != null ? putIfAbsent : attachThrottledTotalProgressLiveData(mediatorLiveData, j, collection, collection2);
    }

    @NotNull
    public final LiveData<List<UploadRequest>> getLiveData(int i, @NotNull TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
        long millis = timeUnit.toMillis(1L) / i;
        LiveData<List<UploadRequest>> liveData = this.liveDatas.get(Long.valueOf(millis));
        return liveData != null ? liveData : createThrottledLiveData(millis);
    }

    @NotNull
    public final LiveData<UploadProgress> getTotalProgressLiveData(int i, @NotNull TimeUnit timeUnit, @NotNull Collection<String> queues, @NotNull Collection<? extends UploadState> requestStates) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
        Intrinsics.checkParameterIsNotNull(queues, "queues");
        Intrinsics.checkParameterIsNotNull(requestStates, "requestStates");
        long millis = timeUnit.toMillis(1L) / i;
        LiveData<UploadProgress> liveData = this.totalProgressLiveDatas.get(Long.valueOf(millis));
        return liveData != null ? liveData : createThrottledTotalProgressLiveData(millis, queues, requestStates);
    }
}
