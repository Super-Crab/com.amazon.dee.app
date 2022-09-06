package com.amazon.photos.uploader.internal.utils;

import io.reactivex.rxjava3.disposables.Disposable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: RxUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010 \n\u0002\b\u0004\u001a&\u0010\u0000\u001a\u00020\u0001*\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00070\u0006\u001a.\u0010\b\u001a\u00020\u0001*\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004`\u00052\u0006\u0010\t\u001a\u00020\u0003\u001a$\u0010\b\u001a\u00020\u0001*\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00070\u00062\u0006\u0010\t\u001a\u00020\u0003\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\u0004¨\u0006\u000b"}, d2 = {"disposeAndClearAll", "", "Ljava/util/HashMap;", "", "Lio/reactivex/rxjava3/disposables/Disposable;", "Lkotlin/collections/HashMap;", "", "", "disposeAndRemoveById", "requestId", "disposeQuietly", "AndroidPhotosUploader_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class RxUtilsKt {
    public static final void disposeAndClearAll(@NotNull HashMap<Long, Disposable> disposeAndClearAll) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(disposeAndClearAll, "$this$disposeAndClearAll");
        Collection<Disposable> values = disposeAndClearAll.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "this.values");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(values, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (Disposable it2 : values) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            disposeQuietly(it2);
            arrayList.add(Unit.INSTANCE);
        }
        disposeAndClearAll.clear();
    }

    public static final void disposeAndRemoveById(@NotNull HashMap<Long, Disposable> disposeAndRemoveById, long j) {
        Intrinsics.checkParameterIsNotNull(disposeAndRemoveById, "$this$disposeAndRemoveById");
        Disposable disposable = disposeAndRemoveById.get(Long.valueOf(j));
        if (disposable != null) {
            disposeQuietly(disposable);
            Unit unit = Unit.INSTANCE;
            disposeAndRemoveById.remove(Long.valueOf(j));
        }
    }

    public static final void disposeQuietly(@NotNull Disposable disposeQuietly) {
        Intrinsics.checkParameterIsNotNull(disposeQuietly, "$this$disposeQuietly");
        if (!disposeQuietly.isDisposed()) {
            disposeQuietly.dispose();
        }
    }

    public static final void disposeAndRemoveById(@NotNull Map<Long, List<Disposable>> disposeAndRemoveById, long j) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(disposeAndRemoveById, "$this$disposeAndRemoveById");
        List<Disposable> list = disposeAndRemoveById.get(Long.valueOf(j));
        if (list != null) {
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            for (Disposable disposable : list) {
                disposeQuietly(disposable);
                arrayList.add(Unit.INSTANCE);
            }
            disposeAndRemoveById.remove(Long.valueOf(j));
        }
    }

    public static final void disposeAndClearAll(@NotNull Map<Long, List<Disposable>> disposeAndClearAll) {
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        Intrinsics.checkParameterIsNotNull(disposeAndClearAll, "$this$disposeAndClearAll");
        Collection<List<Disposable>> values = disposeAndClearAll.values();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(values, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        Iterator<T> it2 = values.iterator();
        while (it2.hasNext()) {
            List<Disposable> list = (List) it2.next();
            collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
            for (Disposable disposable : list) {
                disposeQuietly(disposable);
                arrayList2.add(Unit.INSTANCE);
            }
            arrayList.add(arrayList2);
        }
        disposeAndClearAll.clear();
    }
}
