package com.amazon.photos.discovery.internal.util;

import androidx.room.TypeConverter;
import com.amazon.photos.discovery.model.FolderType;
import com.amazon.photos.discovery.model.ItemType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DiscoveryTypeConverters.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\bH\u0007¨\u0006\u000e"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/DiscoveryTypeConverters;", "", "()V", "fromFolderType", "", "folderType", "Lcom/amazon/photos/discovery/model/FolderType;", "fromItemType", "", "type", "Lcom/amazon/photos/discovery/model/ItemType;", "toFolderType", "toItemType", "value", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DiscoveryTypeConverters {
    @TypeConverter
    @NotNull
    public final String fromFolderType(@NotNull FolderType folderType) {
        Intrinsics.checkParameterIsNotNull(folderType, "folderType");
        return folderType.name();
    }

    @TypeConverter
    public final int fromItemType(@NotNull ItemType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        return type.getValue();
    }

    @TypeConverter
    @NotNull
    public final FolderType toFolderType(@NotNull String folderType) {
        Intrinsics.checkParameterIsNotNull(folderType, "folderType");
        return Intrinsics.areEqual(folderType, FolderType.CAMERA.name()) ? FolderType.CAMERA : FolderType.GENERIC;
    }

    @TypeConverter
    @NotNull
    public final ItemType toItemType(int i) {
        return ItemType.Companion.toItemType(i);
    }
}
