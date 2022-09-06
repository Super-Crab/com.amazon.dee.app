package com.amazon.photos.uploader.observables;

import kotlin.Metadata;
import org.bouncycastle.i18n.ErrorBundle;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploadSummaryObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/amazon/photos/uploader/observables/UploadSummaryObserver;", "", "onChanged", "", ErrorBundle.SUMMARY_ENTRY, "Lcom/amazon/photos/uploader/observables/UploadSummary;", "onUploaderStarted", "onUploaderStopped", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface UploadSummaryObserver {
    void onChanged(@NotNull UploadSummary uploadSummary);

    void onUploaderStarted(@NotNull UploadSummary uploadSummary);

    void onUploaderStopped(@NotNull UploadSummary uploadSummary);
}
