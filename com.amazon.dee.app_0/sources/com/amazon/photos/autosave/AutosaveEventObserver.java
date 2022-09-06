package com.amazon.photos.autosave;

import com.amazon.photos.autosave.model.MediaType;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveEventObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, d2 = {"Lcom/amazon/photos/autosave/AutosaveEventObserver;", "", "onAutosaveStateChanged", "", "mediaType", "Lcom/amazon/photos/autosave/model/MediaType;", "enabled", "", "onNoUploadsScanComplete", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface AutosaveEventObserver {
    void onAutosaveStateChanged(@NotNull MediaType mediaType, boolean z);

    void onNoUploadsScanComplete();
}
