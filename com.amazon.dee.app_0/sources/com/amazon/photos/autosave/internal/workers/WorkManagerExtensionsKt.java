package com.amazon.photos.autosave.internal.workers;

import androidx.annotation.WorkerThread;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: WorkManagerExtensions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0001¨\u0006\u0005"}, d2 = {"hasNoPendingWorkForTag", "", "Landroidx/work/WorkManager;", "tag", "", "AndroidPhotosAutosave_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class WorkManagerExtensionsKt {
    @WorkerThread
    public static final boolean hasNoPendingWorkForTag(@NotNull WorkManager hasNoPendingWorkForTag, @NotNull String tag) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(hasNoPendingWorkForTag, "$this$hasNoPendingWorkForTag");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        List<WorkInfo> list = hasNoPendingWorkForTag.getWorkInfosByTag(tag).get();
        Intrinsics.checkExpressionValueIsNotNull(list, "getWorkInfosByTag(tag).get()");
        List<WorkInfo> list2 = list;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            for (WorkInfo it2 : list2) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                if (it2.getState() == WorkInfo.State.RUNNING || it2.getState() == WorkInfo.State.ENQUEUED) {
                    z = false;
                    continue;
                } else {
                    z = true;
                    continue;
                }
                if (!z) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }
}
