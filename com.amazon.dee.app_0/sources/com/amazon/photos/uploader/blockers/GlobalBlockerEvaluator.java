package com.amazon.photos.uploader.blockers;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: GlobalBlockerEvaluator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/blockers/GlobalBlockerEvaluator;", "", "getBlocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "queryBlocker", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface GlobalBlockerEvaluator {
    @Nullable
    Blocker getBlocker();

    @Nullable
    Blocker queryBlocker();
}
