package com.amazon.photos.uploader.blockers;

import com.amazon.photos.uploader.SchedulingCallback;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: RequiresSchedulingCallback.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/uploader/blockers/RequiresSchedulingCallback;", "", "initializeSchedulerCallback", "", "schedulingCallback", "Lcom/amazon/photos/uploader/SchedulingCallback;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface RequiresSchedulingCallback {
    void initializeSchedulerCallback(@NotNull SchedulingCallback schedulingCallback);
}