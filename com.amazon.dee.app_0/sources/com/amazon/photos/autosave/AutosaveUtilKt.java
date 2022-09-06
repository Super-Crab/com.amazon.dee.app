package com.amazon.photos.autosave;

import com.amazon.photos.autosave.internal.upload.AutosaveUploadConfigurationProvider;
import com.amazon.photos.uploader.UploadRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"isFromAutosave", "", "Lcom/amazon/photos/uploader/UploadRequest;", "AndroidPhotosAutosave_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveUtilKt {
    public static final boolean isFromAutosave(@NotNull UploadRequest isFromAutosave) {
        Intrinsics.checkParameterIsNotNull(isFromAutosave, "$this$isFromAutosave");
        return Intrinsics.areEqual(isFromAutosave.getQueue(), AutosaveUploadConfigurationProvider.PHOTOS_QUEUE) || Intrinsics.areEqual(isFromAutosave.getQueue(), AutosaveUploadConfigurationProvider.VIDEOS_QUEUE);
    }
}
