package com.amazon.photos.discovery.workinfo;

import androidx.work.WorkInfo;
import com.amazon.photos.discovery.internal.worker.ScanAddedWorkerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ScanWorkInfo.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/photos/discovery/workinfo/ScanWorkInfo;", "Lcom/amazon/photos/discovery/workinfo/DiscoveryWorkInfo;", "workInfo", "Landroidx/work/WorkInfo;", "(Landroidx/work/WorkInfo;)V", ScanAddedWorkerKt.RESULT_ADDED_ITEM_COUNT, "", "getItemAddedCount", "()I", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ScanWorkInfo extends DiscoveryWorkInfo {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScanWorkInfo(@NotNull WorkInfo workInfo) {
        super(workInfo);
        Intrinsics.checkParameterIsNotNull(workInfo, "workInfo");
    }

    public final int getItemAddedCount() {
        return getOutputData().getInt(ScanAddedWorkerKt.RESULT_ADDED_ITEM_COUNT, 0);
    }
}
