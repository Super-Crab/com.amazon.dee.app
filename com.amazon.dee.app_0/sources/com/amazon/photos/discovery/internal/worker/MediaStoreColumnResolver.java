package com.amazon.photos.discovery.internal.worker;

import com.amazon.photos.discovery.model.ItemType;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ScanAddedWorkerUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/MediaStoreColumnResolver;", "", "()V", "Companion", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MediaStoreColumnResolver {
    public static final Companion Companion = new Companion(null);

    /* compiled from: ScanAddedWorkerUtil.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/MediaStoreColumnResolver$Companion;", "", "()V", "getBucketDisplayNameColumn", "", "itemType", "Lcom/amazon/photos/discovery/model/ItemType;", "getBucketIdColumn", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        /* loaded from: classes13.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ItemType.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                $EnumSwitchMapping$0[ItemType.PHOTO.ordinal()] = 1;
                $EnumSwitchMapping$0[ItemType.VIDEO.ordinal()] = 2;
                $EnumSwitchMapping$1 = new int[ItemType.values().length];
                $EnumSwitchMapping$1[ItemType.PHOTO.ordinal()] = 1;
                $EnumSwitchMapping$1[ItemType.VIDEO.ordinal()] = 2;
            }
        }

        private Companion() {
        }

        @NotNull
        public final String getBucketDisplayNameColumn(@NotNull ItemType itemType) {
            Intrinsics.checkParameterIsNotNull(itemType, "itemType");
            int i = WhenMappings.$EnumSwitchMapping$1[itemType.ordinal()];
            if (i == 1 || i == 2) {
                return "bucket_display_name";
            }
            throw new NoWhenBranchMatchedException();
        }

        @NotNull
        public final String getBucketIdColumn(@NotNull ItemType itemType) {
            Intrinsics.checkParameterIsNotNull(itemType, "itemType");
            int i = WhenMappings.$EnumSwitchMapping$0[itemType.ordinal()];
            if (i == 1 || i == 2) {
                return "bucket_id";
            }
            throw new NoWhenBranchMatchedException();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
