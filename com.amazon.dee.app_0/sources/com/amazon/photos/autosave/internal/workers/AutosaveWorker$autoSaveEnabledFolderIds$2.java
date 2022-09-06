package com.amazon.photos.autosave.internal.workers;

import com.amazon.photos.autosave.model.AutosaveBucket;
import com.amazon.photos.discovery.model.LocalFolder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class AutosaveWorker$autoSaveEnabledFolderIds$2 extends Lambda implements Function0<List<? extends Long>> {
    final /* synthetic */ AutosaveWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutosaveWorker$autoSaveEnabledFolderIds$2(AutosaveWorker autosaveWorker) {
        super(0);
        this.this$0 = autosaveWorker;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final List<? extends Long> mo12560invoke() {
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        List<AutosaveBucket> allBuckets = this.this$0.getAutosaveBucketDao().getAllBuckets();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(allBuckets, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (AutosaveBucket autosaveBucket : allBuckets) {
            arrayList.add(autosaveBucket.getBucketPath());
        }
        List<LocalFolder> foldersByPath = this.this$0.getDiscovery().getDaos().getLocalFolderDao().getFoldersByPath(arrayList);
        collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(foldersByPath, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
        for (LocalFolder localFolder : foldersByPath) {
            arrayList2.add(Long.valueOf(localFolder.getId()));
        }
        return arrayList2;
    }
}
