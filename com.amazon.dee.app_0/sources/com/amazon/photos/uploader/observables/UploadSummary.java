package com.amazon.photos.uploader.observables;

import com.amazon.photos.uploader.blockers.Blocker;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploadSummary.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\r"}, d2 = {"Lcom/amazon/photos/uploader/observables/UploadSummary;", "", "globalBlockers", "", "Lcom/amazon/photos/uploader/blockers/Blocker;", "queueSummaries", "Lcom/amazon/photos/uploader/observables/QueueSummary;", "(Ljava/util/Collection;Ljava/util/Collection;)V", "getGlobalBlockers", "()Ljava/util/Collection;", "getQueueSummaries", "toString", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadSummary {
    @NotNull
    private final Collection<Blocker> globalBlockers;
    @NotNull
    private final Collection<QueueSummary> queueSummaries;

    /* JADX WARN: Multi-variable type inference failed */
    public UploadSummary(@NotNull Collection<? extends Blocker> globalBlockers, @NotNull Collection<QueueSummary> queueSummaries) {
        Intrinsics.checkParameterIsNotNull(globalBlockers, "globalBlockers");
        Intrinsics.checkParameterIsNotNull(queueSummaries, "queueSummaries");
        this.globalBlockers = globalBlockers;
        this.queueSummaries = queueSummaries;
    }

    @NotNull
    public final Collection<Blocker> getGlobalBlockers() {
        return this.globalBlockers;
    }

    @NotNull
    public final Collection<QueueSummary> getQueueSummaries() {
        return this.queueSummaries;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UploadSummary(globalBlockers=");
        outline107.append(this.globalBlockers);
        outline107.append(", queueSummaries=");
        outline107.append(this.queueSummaries);
        outline107.append(')');
        return outline107.toString();
    }
}
