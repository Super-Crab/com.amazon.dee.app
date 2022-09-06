package com.amazon.photos.uploader.internal.livedata;

import com.amazon.photos.uploader.UploadRequest;
import io.reactivex.rxjava3.functions.Function;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RunningRequestProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u001a\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0002 \u0005*\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "Lcom/amazon/photos/uploader/UploadRequest;", "requests", "", "kotlin.jvm.PlatformType", "apply"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class RunningRequestProvider$attachThrottledLiveData$sortedSource$1<T, R> implements Function<T, R> {
    public static final RunningRequestProvider$attachThrottledLiveData$sortedSource$1 INSTANCE = new RunningRequestProvider$attachThrottledLiveData$sortedSource$1();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RunningRequestProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "requestA", "Lcom/amazon/photos/uploader/UploadRequest;", "requestB", "compare"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.photos.uploader.internal.livedata.RunningRequestProvider$attachThrottledLiveData$sortedSource$1$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    public static final class AnonymousClass1<T> implements Comparator<UploadRequest> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
        }

        @Override // java.util.Comparator
        public final int compare(@NotNull UploadRequest requestA, @NotNull UploadRequest requestB) {
            Intrinsics.checkParameterIsNotNull(requestA, "requestA");
            Intrinsics.checkParameterIsNotNull(requestB, "requestB");
            return (int) (requestA.getId() - requestB.getId());
        }
    }

    RunningRequestProvider$attachThrottledLiveData$sortedSource$1() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    @NotNull
    /* renamed from: apply */
    public final List<UploadRequest> mo10358apply(Collection<UploadRequest> requests) {
        List<UploadRequest> mutableList;
        Intrinsics.checkExpressionValueIsNotNull(requests, "requests");
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) requests);
        CollectionsKt__MutableCollectionsJVMKt.sortWith(mutableList, AnonymousClass1.INSTANCE);
        return mutableList;
    }
}
