package com.amazon.photos.autosave.model;

import com.amazon.photos.discovery.model.ItemType;
import com.amazon.photos.discovery.model.LocalFolder;
import com.amazon.photos.discovery.model.LocalItem;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ModelExtensions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\nH\u0000\u001a\f\u0010\u000b\u001a\u00020\n*\u00020\tH\u0000¨\u0006\f"}, d2 = {"toAutosaveBucket", "Lcom/amazon/photos/autosave/model/AutosaveBucket;", "Lcom/amazon/photos/discovery/model/LocalFolder;", "toAutosaveItem", "Lcom/amazon/photos/autosave/model/AutosaveItem;", "Lcom/amazon/photos/discovery/model/LocalItem;", "state", "Lcom/amazon/photos/autosave/model/AutosaveState;", "toItemType", "Lcom/amazon/photos/discovery/model/ItemType;", "Lcom/amazon/photos/autosave/model/MediaType;", "toMediaType", "AndroidPhotosAutosave_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ModelExtensionsKt {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ItemType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[ItemType.PHOTO.ordinal()] = 1;
            $EnumSwitchMapping$0[ItemType.VIDEO.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[MediaType.values().length];
            $EnumSwitchMapping$1[MediaType.PHOTO.ordinal()] = 1;
            $EnumSwitchMapping$1[MediaType.VIDEO.ordinal()] = 2;
        }
    }

    @NotNull
    public static final AutosaveBucket toAutosaveBucket(@NotNull LocalFolder toAutosaveBucket) {
        Intrinsics.checkParameterIsNotNull(toAutosaveBucket, "$this$toAutosaveBucket");
        return new AutosaveBucket(0L, toAutosaveBucket.getPath(), toAutosaveBucket.getId(), 1, null);
    }

    @Nullable
    public static final AutosaveItem toAutosaveItem(@NotNull LocalItem toAutosaveItem, @NotNull AutosaveState state) {
        Intrinsics.checkParameterIsNotNull(toAutosaveItem, "$this$toAutosaveItem");
        Intrinsics.checkParameterIsNotNull(state, "state");
        String filePath = toAutosaveItem.getFilePath();
        if (filePath != null) {
            return new AutosaveItem(0L, toAutosaveItem.getId(), toAutosaveItem.getUnifiedId(), toAutosaveItem.getParentId(), filePath, state, toMediaType(toAutosaveItem.getType()), 1, null);
        }
        return null;
    }

    public static /* synthetic */ AutosaveItem toAutosaveItem$default(LocalItem localItem, AutosaveState autosaveState, int i, Object obj) {
        if ((i & 1) != 0) {
            autosaveState = AutosaveState.QUEUED;
        }
        return toAutosaveItem(localItem, autosaveState);
    }

    @NotNull
    public static final ItemType toItemType(@NotNull MediaType toItemType) {
        Intrinsics.checkParameterIsNotNull(toItemType, "$this$toItemType");
        int i = WhenMappings.$EnumSwitchMapping$1[toItemType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            return ItemType.VIDEO;
        }
        return ItemType.PHOTO;
    }

    @NotNull
    public static final MediaType toMediaType(@NotNull ItemType toMediaType) {
        Intrinsics.checkParameterIsNotNull(toMediaType, "$this$toMediaType");
        int i = WhenMappings.$EnumSwitchMapping$0[toMediaType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            return MediaType.VIDEO;
        }
        return MediaType.PHOTO;
    }
}
