package com.amazon.photos.uploader.internal.livedata;

import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.observables.UploadSummary;
import io.reactivex.rxjava3.functions.Function;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import org.bouncycastle.i18n.ErrorBundle;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GlobalBlockerLiveDataProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u000e\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "Lcom/amazon/photos/uploader/blockers/Blocker;", ErrorBundle.SUMMARY_ENTRY, "Lcom/amazon/photos/uploader/observables/UploadSummary;", "kotlin.jvm.PlatformType", "apply"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class GlobalBlockerLiveDataProvider$attachGlobalBlockerLiveData$sortedSource$1<T, R> implements Function<T, R> {
    public static final GlobalBlockerLiveDataProvider$attachGlobalBlockerLiveData$sortedSource$1 INSTANCE = new GlobalBlockerLiveDataProvider$attachGlobalBlockerLiveData$sortedSource$1();

    GlobalBlockerLiveDataProvider$attachGlobalBlockerLiveData$sortedSource$1() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    @NotNull
    /* renamed from: apply */
    public final List<Blocker> mo10358apply(UploadSummary uploadSummary) {
        List mutableList;
        Comparator naturalOrder;
        List sortedWith;
        List<Blocker> list;
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) uploadSummary.getGlobalBlockers());
        naturalOrder = ComparisonsKt__ComparisonsKt.naturalOrder();
        sortedWith = CollectionsKt___CollectionsKt.sortedWith(mutableList, naturalOrder);
        list = CollectionsKt___CollectionsKt.toList(sortedWith);
        return list;
    }
}
