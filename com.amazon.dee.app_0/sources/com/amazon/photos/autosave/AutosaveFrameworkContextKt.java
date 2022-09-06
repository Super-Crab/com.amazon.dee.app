package com.amazon.photos.autosave;

import com.amazon.photos.uploader.UploadFrameworkContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveFrameworkContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"toUploadFrameworkContext", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "Lcom/amazon/photos/autosave/AutosaveFrameworkContext;", "AndroidPhotosAutosave_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveFrameworkContextKt {
    @NotNull
    public static final UploadFrameworkContext toUploadFrameworkContext(@NotNull AutosaveFrameworkContext toUploadFrameworkContext) {
        Intrinsics.checkParameterIsNotNull(toUploadFrameworkContext, "$this$toUploadFrameworkContext");
        return new UploadFrameworkContext(toUploadFrameworkContext.getDirectedId(), toUploadFrameworkContext.getApplicationContext(), toUploadFrameworkContext.getApplicationId(), toUploadFrameworkContext.getApplicationName(), null, 16, null);
    }
}
