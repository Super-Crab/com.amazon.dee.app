package com.amazon.photos.uploadbundle.internal;

import com.amazon.photos.autosave.AutosaveFrameworkContext;
import com.amazon.photos.uploader.UploadFrameworkContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FrameworkContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0000¨\u0006\u0005"}, d2 = {"toAutosaveFrameworkContext", "Lcom/amazon/photos/autosave/AutosaveFrameworkContext;", "Lcom/amazon/photos/uploadbundle/internal/FrameworkContext;", "toUploadFrameworkContext", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "AndroidPhotosUploadBundle_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class FrameworkContextKt {
    @NotNull
    public static final AutosaveFrameworkContext toAutosaveFrameworkContext(@NotNull FrameworkContext toAutosaveFrameworkContext) {
        Intrinsics.checkParameterIsNotNull(toAutosaveFrameworkContext, "$this$toAutosaveFrameworkContext");
        return new AutosaveFrameworkContext(toAutosaveFrameworkContext.getDirectedId(), toAutosaveFrameworkContext.getApplicationContext(), toAutosaveFrameworkContext.getApplicationId(), toAutosaveFrameworkContext.getApplicationName());
    }

    @NotNull
    public static final UploadFrameworkContext toUploadFrameworkContext(@NotNull FrameworkContext toUploadFrameworkContext) {
        Intrinsics.checkParameterIsNotNull(toUploadFrameworkContext, "$this$toUploadFrameworkContext");
        return new UploadFrameworkContext(toUploadFrameworkContext.getDirectedId(), toUploadFrameworkContext.getApplicationContext(), toUploadFrameworkContext.getApplicationId(), toUploadFrameworkContext.getApplicationName(), null, 16, null);
    }
}
