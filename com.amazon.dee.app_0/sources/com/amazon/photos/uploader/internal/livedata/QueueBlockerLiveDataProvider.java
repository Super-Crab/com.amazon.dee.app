package com.amazon.photos.uploader.internal.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.internal.observers.EmittingSummaryObserver;
import com.amazon.photos.uploader.observables.QueueSummary;
import com.amazon.photos.uploader.observables.UploadSummary;
import com.amazon.photos.uploader.observables.UploadSummaryObservable;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: QueueBlockerLiveDataProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u000f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\u001c\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0010\u001a\u00020\u0007J,\u0010\u0012\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00130\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0015R&\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/photos/uploader/internal/livedata/QueueBlockerLiveDataProvider;", "", "summaryObservable", "Lcom/amazon/photos/uploader/observables/UploadSummaryObservable;", "(Lcom/amazon/photos/uploader/observables/UploadSummaryObservable;)V", "liveDatas", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Landroidx/lifecycle/LiveData;", "", "Lcom/amazon/photos/uploader/blockers/Blocker;", "summaryObserver", "Lcom/amazon/photos/uploader/internal/observers/EmittingSummaryObserver;", "attachQueueBlockerLiveData", "liveData", "Landroidx/lifecycle/MediatorLiveData;", "queueName", "createQueueBlockerLiveData", "getLiveData", "", "queueNames", "", "MultiQueueLiveData", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class QueueBlockerLiveDataProvider {
    private final ConcurrentHashMap<String, LiveData<List<Blocker>>> liveDatas;
    private final EmittingSummaryObserver summaryObserver;

    /* compiled from: QueueBlockerLiveDataProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00020\u0001B\u0013\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\bR6\u0010\t\u001a*\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\nj\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/photos/uploader/internal/livedata/QueueBlockerLiveDataProvider$MultiQueueLiveData;", "Landroidx/lifecycle/MediatorLiveData;", "", "", "", "Lcom/amazon/photos/uploader/blockers/Blocker;", "queueNames", "", "(Lcom/amazon/photos/uploader/internal/livedata/QueueBlockerLiveDataProvider;Ljava/util/Set;)V", "blockerMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class MultiQueueLiveData extends MediatorLiveData<Map<String, ? extends List<? extends Blocker>>> {
        private final HashMap<String, List<Blocker>> blockerMap;
        private final Set<String> queueNames;
        final /* synthetic */ QueueBlockerLiveDataProvider this$0;

        public MultiQueueLiveData(@NotNull QueueBlockerLiveDataProvider queueBlockerLiveDataProvider, Set<String> queueNames) {
            Intrinsics.checkParameterIsNotNull(queueNames, "queueNames");
            this.this$0 = queueBlockerLiveDataProvider;
            this.queueNames = queueNames;
            this.blockerMap = new HashMap<>();
            for (final String str : this.queueNames) {
                addSource(queueBlockerLiveDataProvider.getLiveData(str), new Observer<List<? extends Blocker>>() { // from class: com.amazon.photos.uploader.internal.livedata.QueueBlockerLiveDataProvider.MultiQueueLiveData.1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(List<? extends Blocker> it2) {
                        HashMap hashMap = MultiQueueLiveData.this.blockerMap;
                        String str2 = str;
                        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                        hashMap.put(str2, it2);
                        MultiQueueLiveData multiQueueLiveData = MultiQueueLiveData.this;
                        multiQueueLiveData.postValue(multiQueueLiveData.blockerMap);
                    }
                });
            }
        }
    }

    public QueueBlockerLiveDataProvider(@NotNull UploadSummaryObservable summaryObservable) {
        Intrinsics.checkParameterIsNotNull(summaryObservable, "summaryObservable");
        this.summaryObserver = new EmittingSummaryObserver();
        this.liveDatas = new ConcurrentHashMap<>();
        EmittingSummaryObserver emittingSummaryObserver = this.summaryObserver;
        Scheduler computation = Schedulers.computation();
        Intrinsics.checkExpressionValueIsNotNull(computation, "Schedulers.computation()");
        summaryObservable.addObserver(emittingSummaryObserver, computation);
    }

    private final LiveData<List<Blocker>> attachQueueBlockerLiveData(final MediatorLiveData<List<Blocker>> mediatorLiveData, final String str) {
        List<Blocker> emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        mediatorLiveData.postValue(emptyList);
        mediatorLiveData.addSource(LiveDataReactiveStreams.fromPublisher(Flowable.create(new FlowableOnSubscribe<T>() { // from class: com.amazon.photos.uploader.internal.livedata.QueueBlockerLiveDataProvider$attachQueueBlockerLiveData$summarySource$1
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter<UploadSummary> emitter) {
                EmittingSummaryObserver emittingSummaryObserver;
                emittingSummaryObserver = QueueBlockerLiveDataProvider.this.summaryObserver;
                Intrinsics.checkExpressionValueIsNotNull(emitter, "emitter");
                emittingSummaryObserver.addEmitter(emitter);
            }
        }, BackpressureStrategy.LATEST).observeOn(Schedulers.computation()).map(new Function<T, R>() { // from class: com.amazon.photos.uploader.internal.livedata.QueueBlockerLiveDataProvider$attachQueueBlockerLiveData$sortedSource$1
            @Override // io.reactivex.rxjava3.functions.Function
            @NotNull
            /* renamed from: apply */
            public final List<Blocker> mo10358apply(UploadSummary uploadSummary) {
                List<Blocker> emptyList2;
                List mutableList;
                Comparator naturalOrder;
                List sortedWith;
                List<Blocker> list;
                for (QueueSummary queueSummary : uploadSummary.getQueueSummaries()) {
                    if (Intrinsics.areEqual(queueSummary.getQueue().getName(), str)) {
                        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) queueSummary.getQueueBlockers());
                        naturalOrder = ComparisonsKt__ComparisonsKt.naturalOrder();
                        sortedWith = CollectionsKt___CollectionsKt.sortedWith(mutableList, naturalOrder);
                        list = CollectionsKt___CollectionsKt.toList(sortedWith);
                        return list;
                    }
                }
                emptyList2 = CollectionsKt__CollectionsKt.emptyList();
                return emptyList2;
            }
        })), new Observer<S>() { // from class: com.amazon.photos.uploader.internal.livedata.QueueBlockerLiveDataProvider$attachQueueBlockerLiveData$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(List<? extends Blocker> list) {
                MediatorLiveData.this.postValue(list);
            }
        });
        return mediatorLiveData;
    }

    private final LiveData<List<Blocker>> createQueueBlockerLiveData(String str) {
        MediatorLiveData<List<Blocker>> mediatorLiveData = new MediatorLiveData<>();
        LiveData<List<Blocker>> putIfAbsent = this.liveDatas.putIfAbsent(str, mediatorLiveData);
        return putIfAbsent != null ? putIfAbsent : attachQueueBlockerLiveData(mediatorLiveData, str);
    }

    @NotNull
    public final LiveData<List<Blocker>> getLiveData(@NotNull String queueName) {
        Intrinsics.checkParameterIsNotNull(queueName, "queueName");
        LiveData<List<Blocker>> liveData = this.liveDatas.get(queueName);
        return liveData != null ? liveData : createQueueBlockerLiveData(queueName);
    }

    @NotNull
    public final LiveData<Map<String, List<Blocker>>> getLiveData(@NotNull Set<String> queueNames) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(queueNames, "queueNames");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(queueNames, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (String str : queueNames) {
            arrayList.add(getLiveData(str));
        }
        return new MultiQueueLiveData(this, queueNames);
    }
}
