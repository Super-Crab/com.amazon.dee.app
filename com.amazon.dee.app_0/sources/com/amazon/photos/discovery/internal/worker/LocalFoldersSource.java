package com.amazon.photos.discovery.internal.worker;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.internal.db.CachedColumnIndexCursorDecorator;
import com.amazon.photos.discovery.internal.model.MutableLocalFolder;
import com.amazon.photos.discovery.internal.util.MediaStoreUtil;
import com.amazon.photos.discovery.model.ItemType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ScanAddedWorkerUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\"\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0002J\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00152\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/LocalFoldersSource;", "", "contentResolver", "Landroid/content/ContentResolver;", "mediaStoreUtil", "Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "searchBucketIds", "", "", "(Landroid/content/ContentResolver;Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Ljava/util/List;)V", "foundFolders", "", "Lcom/amazon/photos/discovery/internal/model/MutableLocalFolder;", "createCursorForType", "Landroid/database/Cursor;", "itemType", "Lcom/amazon/photos/discovery/model/ItemType;", "bucketIds", "getAllFolders", "", "getAllFoldersFromCursor", "cursor", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class LocalFoldersSource {
    private final ContentResolver contentResolver;
    private final Map<Long, MutableLocalFolder> foundFolders;
    private final MediaStoreUtil mediaStoreUtil;
    private final Metrics metrics;
    private final List<Long> searchBucketIds;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ItemType.values().length];

        static {
            $EnumSwitchMapping$0[ItemType.PHOTO.ordinal()] = 1;
        }
    }

    public LocalFoldersSource(@NotNull ContentResolver contentResolver, @NotNull MediaStoreUtil mediaStoreUtil, @NotNull Metrics metrics, @Nullable List<Long> list) {
        Intrinsics.checkParameterIsNotNull(contentResolver, "contentResolver");
        Intrinsics.checkParameterIsNotNull(mediaStoreUtil, "mediaStoreUtil");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.contentResolver = contentResolver;
        this.mediaStoreUtil = mediaStoreUtil;
        this.metrics = metrics;
        this.searchBucketIds = list;
        this.foundFolders = new LinkedHashMap();
    }

    private final Cursor createCursorForType(ItemType itemType, List<Long> list) {
        String str;
        String[] strArr;
        Uri imagesUri;
        int collectionSizeOrDefault;
        int collectionSizeOrDefault2;
        String joinToString$default;
        if (list == null || !list.isEmpty()) {
            String bucketIdColumn = MediaStoreColumnResolver.Companion.getBucketIdColumn(itemType);
            if (list != null) {
                collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
                ArrayList arrayList = new ArrayList(collectionSizeOrDefault2);
                for (Number number : list) {
                    number.longValue();
                    arrayList.add(WebConstants.UriConstants.QUESTIONMARK_KEY);
                }
                joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList, ",", GeneratedOutlineSupport1.outline72(bucketIdColumn, " IN ("), ")", 0, null, null, 56, null);
                str = joinToString$default;
            } else {
                str = null;
            }
            if (list != null) {
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
                ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
                for (Number number2 : list) {
                    arrayList2.add(String.valueOf(number2.longValue()));
                }
                Object[] array = arrayList2.toArray(new String[0]);
                if (array == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                strArr = (String[]) array;
            } else {
                strArr = null;
            }
            ContentResolver contentResolver = this.contentResolver;
            if (WhenMappings.$EnumSwitchMapping$0[itemType.ordinal()] != 1) {
                imagesUri = this.mediaStoreUtil.getVideosUri();
            } else {
                imagesUri = this.mediaStoreUtil.getImagesUri();
            }
            Cursor query = contentResolver.query(imagesUri, new String[]{MediaStoreColumnResolver.Companion.getBucketIdColumn(itemType), MediaStoreColumnResolver.Companion.getBucketDisplayNameColumn(itemType), "_data"}, str, strArr, null);
            if (query == null) {
                return null;
            }
            return new CachedColumnIndexCursorDecorator(query);
        }
        return null;
    }

    private final Set<MutableLocalFolder> getAllFoldersFromCursor(Cursor cursor, ItemType itemType) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (cursor.moveToFirst()) {
            do {
                MutableLocalFolder createFolder = this.mediaStoreUtil.createFolder(cursor, itemType, this.foundFolders);
                if (createFolder != null && !linkedHashSet.contains(createFolder)) {
                    linkedHashSet.add(createFolder);
                }
            } while (cursor.moveToNext());
            cursor.close();
            return linkedHashSet;
        }
        cursor.close();
        return linkedHashSet;
    }

    @NotNull
    public final Set<MutableLocalFolder> getAllFolders() {
        ItemType[] values;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        List<Long> list = this.searchBucketIds;
        if (list == null || !list.isEmpty()) {
            this.foundFolders.clear();
            for (ItemType itemType : ItemType.values()) {
                Cursor createCursorForType = createCursorForType(itemType, this.searchBucketIds);
                if (createCursorForType != null) {
                    for (MutableLocalFolder mutableLocalFolder : getAllFoldersFromCursor(createCursorForType, itemType)) {
                        if (!linkedHashSet.contains(mutableLocalFolder)) {
                            linkedHashSet.add(mutableLocalFolder);
                        }
                    }
                }
            }
            return linkedHashSet;
        }
        return linkedHashSet;
    }
}
