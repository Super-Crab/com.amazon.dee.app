package com.amazon.photos.uploader.internal.workers;

import androidx.work.WorkInfo;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
/* compiled from: SchedulerWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"HASHED_DIRECTED_ID_KEY", "", "IN_PROGRESS_WORKER_STATES", "", "Landroidx/work/WorkInfo$State;", "QUEUE_TAG_PREFIX", SchedulerWorkerKt.REQUEST_ID_KEY, "REQUEST_TAG_PREFIX", "SCHEDULE_TAG", SchedulerWorkerKt.UNBLOCKED_QUEUES_KEY, "UPLOAD_TAG", "AndroidPhotosUploader_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SchedulerWorkerKt {
    @NotNull
    public static final String HASHED_DIRECTED_ID_KEY = "HASHED_DIRECTED_ID_KEY";
    @JvmField
    @NotNull
    public static final Set<WorkInfo.State> IN_PROGRESS_WORKER_STATES;
    @NotNull
    public static final String QUEUE_TAG_PREFIX = "AndroidPhotosUploader_QUEUE_";
    @NotNull
    public static final String REQUEST_ID_KEY = "REQUEST_ID_KEY";
    @NotNull
    public static final String REQUEST_TAG_PREFIX = "AndroidPhotosUploader_REQUEST_";
    @NotNull
    public static final String SCHEDULE_TAG = "AndroidPhotosUploader_SCHEDULE";
    @NotNull
    public static final String UNBLOCKED_QUEUES_KEY = "UNBLOCKED_QUEUES_KEY";
    @NotNull
    public static final String UPLOAD_TAG = "AndroidPhotosUploader_UPLOAD";

    static {
        Set<WorkInfo.State> of;
        of = SetsKt__SetsKt.setOf((Object[]) new WorkInfo.State[]{WorkInfo.State.ENQUEUED, WorkInfo.State.RUNNING, WorkInfo.State.BLOCKED});
        IN_PROGRESS_WORKER_STATES = of;
    }
}
