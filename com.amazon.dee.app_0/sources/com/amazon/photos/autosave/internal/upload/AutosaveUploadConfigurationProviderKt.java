package com.amazon.photos.autosave.internal.upload;

import com.amazon.photos.autosave.model.MediaType;
import com.amazon.photos.uploader.Queue;
import com.amazon.photos.uploader.UploadManager;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveUploadConfigurationProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"configureForAutosave", "Lcom/amazon/photos/uploader/UploadManager;", "getQueue", "", "Lcom/amazon/photos/autosave/model/MediaType;", "AndroidPhotosAutosave_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveUploadConfigurationProviderKt {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MediaType.values().length];

        static {
            $EnumSwitchMapping$0[MediaType.PHOTO.ordinal()] = 1;
            $EnumSwitchMapping$0[MediaType.VIDEO.ordinal()] = 2;
        }
    }

    @NotNull
    public static final UploadManager configureForAutosave(@NotNull UploadManager configureForAutosave) {
        Intrinsics.checkParameterIsNotNull(configureForAutosave, "$this$configureForAutosave");
        for (Queue queue : AutosaveUploadConfigurationProvider.INSTANCE.getAutosaveQueues()) {
            configureForAutosave.getQueueManager().addNewQueue(queue);
        }
        return configureForAutosave;
    }

    @NotNull
    public static final String getQueue(@NotNull MediaType getQueue) {
        Intrinsics.checkParameterIsNotNull(getQueue, "$this$getQueue");
        int i = WhenMappings.$EnumSwitchMapping$0[getQueue.ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            return AutosaveUploadConfigurationProvider.VIDEOS_QUEUE;
        }
        return AutosaveUploadConfigurationProvider.PHOTOS_QUEUE;
    }
}
