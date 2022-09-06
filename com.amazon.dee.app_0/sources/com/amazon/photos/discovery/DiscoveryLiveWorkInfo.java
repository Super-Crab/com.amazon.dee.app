package com.amazon.photos.discovery;

import androidx.annotation.AnyThread;
import androidx.annotation.MainThread;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.photos.discovery.DiscoveryLiveWorkInfo;
import com.amazon.photos.discovery.workinfo.DiscoveryWorkInfo;
import com.amazon.photos.discovery.workinfo.ScanWorkInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DiscoveryLiveWorkInfo.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u0015\u0016B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010H\u0007J\u001e\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004H\u0007R\u001e\u0010\u0003\u001a\u00020\u00048\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/amazon/photos/discovery/DiscoveryLiveWorkInfo;", "", "()V", "hashedDirectedId", "", "getHashedDirectedId$AndroidPhotosDiscovery_release", "()Ljava/lang/String;", "setHashedDirectedId$AndroidPhotosDiscovery_release", "(Ljava/lang/String;)V", "workManager", "Landroidx/work/WorkManager;", "getWorkManager$AndroidPhotosDiscovery_release", "()Landroidx/work/WorkManager;", "setWorkManager$AndroidPhotosDiscovery_release", "(Landroidx/work/WorkManager;)V", "getScanWorkInfos", "Landroidx/lifecycle/LiveData;", "", "Lcom/amazon/photos/discovery/workinfo/ScanWorkInfo;", "getScanWorkInfosByTag", "tag", "LiveDataWrapper", "WorkInfoConverter", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DiscoveryLiveWorkInfo {
    @Inject
    @Named("HashedDirectedId")
    @NotNull
    public String hashedDirectedId;
    @Inject
    @NotNull
    public WorkManager workManager;

    /* compiled from: DiscoveryLiveWorkInfo.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u0003B1\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004H\u0002J&\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0014\u0010\u0016\u001a\u0010\u0012\f\b\u0000\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0017H\u0017J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/photos/discovery/DiscoveryLiveWorkInfo$LiveDataWrapper;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/photos/discovery/workinfo/DiscoveryWorkInfo;", "Landroidx/lifecycle/MutableLiveData;", "", "liveData", "Landroidx/lifecycle/LiveData;", "Landroidx/work/WorkInfo;", "workInfoConverter", "Lcom/amazon/photos/discovery/DiscoveryLiveWorkInfo$WorkInfoConverter;", "tag", "", "(Lcom/amazon/photos/discovery/DiscoveryLiveWorkInfo;Landroidx/lifecycle/LiveData;Lcom/amazon/photos/discovery/DiscoveryLiveWorkInfo$WorkInfoConverter;Ljava/lang/String;)V", "accountMatches", "", "workInfo", "convertToDiscoveryWorkInfoList", "workInfos", "observe", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "Landroidx/lifecycle/LifecycleOwner;", "observer", "Landroidx/lifecycle/Observer;", "tagMatches", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class LiveDataWrapper<T extends DiscoveryWorkInfo> extends MutableLiveData<List<? extends T>> {
        private final LiveData<List<WorkInfo>> liveData;
        private final String tag;
        final /* synthetic */ DiscoveryLiveWorkInfo this$0;
        private final WorkInfoConverter<T> workInfoConverter;

        public LiveDataWrapper(@NotNull DiscoveryLiveWorkInfo discoveryLiveWorkInfo, @NotNull LiveData<List<WorkInfo>> liveData, @Nullable WorkInfoConverter<T> workInfoConverter, String str) {
            Intrinsics.checkParameterIsNotNull(liveData, "liveData");
            Intrinsics.checkParameterIsNotNull(workInfoConverter, "workInfoConverter");
            this.this$0 = discoveryLiveWorkInfo;
            this.liveData = liveData;
            this.workInfoConverter = workInfoConverter;
            this.tag = str;
        }

        private final boolean accountMatches(WorkInfo workInfo) {
            Set<String> tags = workInfo.getTags();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(DiscoveryOperationsKt.DISCOVERY_TAG_PREFIX);
            outline107.append(this.this$0.getHashedDirectedId$AndroidPhotosDiscovery_release());
            return tags.contains(outline107.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<T> convertToDiscoveryWorkInfoList(List<WorkInfo> list) {
            int collectionSizeOrDefault;
            ArrayList<WorkInfo> arrayList = new ArrayList();
            for (Object obj : list) {
                WorkInfo workInfo = (WorkInfo) obj;
                if (accountMatches(workInfo) && tagMatches(workInfo)) {
                    arrayList.add(obj);
                }
            }
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
            for (WorkInfo workInfo2 : arrayList) {
                arrayList2.add(this.workInfoConverter.convert(workInfo2));
            }
            return arrayList2;
        }

        private final boolean tagMatches(WorkInfo workInfo) {
            return this.tag == null || workInfo.getTags().contains(this.tag);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        @MainThread
        public void observe(@NotNull LifecycleOwner owner, @NotNull Observer<? super List<? extends T>> observer) {
            Intrinsics.checkParameterIsNotNull(owner, "owner");
            Intrinsics.checkParameterIsNotNull(observer, "observer");
            super.observe(owner, observer);
            this.liveData.observe(owner, new Observer<List<? extends WorkInfo>>() { // from class: com.amazon.photos.discovery.DiscoveryLiveWorkInfo$LiveDataWrapper$observe$1
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(List<? extends WorkInfo> list) {
                    onChanged2((List<WorkInfo>) list);
                }

                /* renamed from: onChanged  reason: avoid collision after fix types in other method */
                public final void onChanged2(@NotNull List<WorkInfo> workInfos) {
                    List convertToDiscoveryWorkInfoList;
                    Intrinsics.checkParameterIsNotNull(workInfos, "workInfos");
                    convertToDiscoveryWorkInfoList = DiscoveryLiveWorkInfo.LiveDataWrapper.this.convertToDiscoveryWorkInfoList(workInfos);
                    if (!convertToDiscoveryWorkInfoList.isEmpty()) {
                        DiscoveryLiveWorkInfo.LiveDataWrapper.this.postValue(convertToDiscoveryWorkInfoList);
                    }
                }
            });
        }
    }

    /* compiled from: DiscoveryLiveWorkInfo.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J\u0015\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0006H&¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/photos/discovery/DiscoveryLiveWorkInfo$WorkInfoConverter;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/photos/discovery/workinfo/DiscoveryWorkInfo;", "", "convert", "workInfo", "Landroidx/work/WorkInfo;", "(Landroidx/work/WorkInfo;)Lcom/amazon/photos/discovery/workinfo/DiscoveryWorkInfo;", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public interface WorkInfoConverter<T extends DiscoveryWorkInfo> {
        @NotNull
        T convert(@NotNull WorkInfo workInfo);
    }

    @NotNull
    public final String getHashedDirectedId$AndroidPhotosDiscovery_release() {
        String str = this.hashedDirectedId;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hashedDirectedId");
        }
        return str;
    }

    @AnyThread
    @NotNull
    public final LiveData<List<ScanWorkInfo>> getScanWorkInfos() {
        return getScanWorkInfosByTag(null);
    }

    @AnyThread
    @NotNull
    public final LiveData<List<ScanWorkInfo>> getScanWorkInfosByTag(@Nullable String str) {
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(DiscoveryOperationsKt.DISCOVERY_TAG_PREFIX);
        String str2 = this.hashedDirectedId;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hashedDirectedId");
        }
        outline107.append(str2);
        LiveData<List<WorkInfo>> workInfosByTagLiveData = workManager.getWorkInfosByTagLiveData(outline107.toString());
        Intrinsics.checkExpressionValueIsNotNull(workInfosByTagLiveData, "workManager.getWorkInfos…REFIX + hashedDirectedId)");
        return new LiveDataWrapper(this, workInfosByTagLiveData, new WorkInfoConverter<ScanWorkInfo>() { // from class: com.amazon.photos.discovery.DiscoveryLiveWorkInfo$getScanWorkInfosByTag$1
            @Override // com.amazon.photos.discovery.DiscoveryLiveWorkInfo.WorkInfoConverter
            @NotNull
            public ScanWorkInfo convert(@NotNull WorkInfo workInfo) {
                Intrinsics.checkParameterIsNotNull(workInfo, "workInfo");
                return new ScanWorkInfo(workInfo);
            }
        }, str);
    }

    @NotNull
    public final WorkManager getWorkManager$AndroidPhotosDiscovery_release() {
        WorkManager workManager = this.workManager;
        if (workManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("workManager");
        }
        return workManager;
    }

    public final void setHashedDirectedId$AndroidPhotosDiscovery_release(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.hashedDirectedId = str;
    }

    public final void setWorkManager$AndroidPhotosDiscovery_release(@NotNull WorkManager workManager) {
        Intrinsics.checkParameterIsNotNull(workManager, "<set-?>");
        this.workManager = workManager;
    }
}
