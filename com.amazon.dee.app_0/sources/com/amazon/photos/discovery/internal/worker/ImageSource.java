package com.amazon.photos.discovery.internal.worker;

import android.content.ContentResolver;
import android.database.Cursor;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.discovery.internal.db.CachedColumnIndexCursorDecorator;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import com.amazon.photos.discovery.internal.util.MediaStoreUtil;
import com.amazon.photos.discovery.model.ItemType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ScanAddedWorkerUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0014J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/ImageSource;", "Lcom/amazon/photos/discovery/internal/worker/CursorItemSource;", "lastAddedRowId", "", "contentResolver", "Landroid/content/ContentResolver;", "mediaStoreUtil", "Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "(JLandroid/content/ContentResolver;Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;)V", "createCursor", "Landroid/database/Cursor;", "itemFromCursor", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "cursor", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ImageSource extends CursorItemSource {
    private final ContentResolver contentResolver;
    private final MediaStoreUtil mediaStoreUtil;
    private final Metrics metrics;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageSource(long j, @NotNull ContentResolver contentResolver, @NotNull MediaStoreUtil mediaStoreUtil, @NotNull Metrics metrics) {
        super(j, metrics);
        Intrinsics.checkParameterIsNotNull(contentResolver, "contentResolver");
        Intrinsics.checkParameterIsNotNull(mediaStoreUtil, "mediaStoreUtil");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.contentResolver = contentResolver;
        this.mediaStoreUtil = mediaStoreUtil;
        this.metrics = metrics;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.photos.discovery.internal.worker.CursorItemSource
    @Nullable
    public Cursor createCursor(long j) {
        Cursor query = this.contentResolver.query(this.mediaStoreUtil.getImagesUri(), new String[]{"_id", "_data", "date_added", "datetaken", "date_modified", "width", "height", "_size", "bucket_id"}, "_id >= ?", new String[]{String.valueOf(j)}, "_id ASC");
        if (query != null) {
            return new CachedColumnIndexCursorDecorator(query);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.photos.discovery.internal.worker.CursorItemSource
    @NotNull
    public MutableLocalItem itemFromCursor(@NotNull Cursor cursor) {
        Intrinsics.checkParameterIsNotNull(cursor, "cursor");
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("_data");
        int columnIndex3 = cursor.getColumnIndex("date_added");
        int columnIndex4 = cursor.getColumnIndex("datetaken");
        int columnIndex5 = cursor.getColumnIndex("date_modified");
        int columnIndex6 = cursor.getColumnIndex("width");
        int columnIndex7 = cursor.getColumnIndex("height");
        int columnIndex8 = cursor.getColumnIndex("_size");
        int columnIndex9 = cursor.getColumnIndex("bucket_id");
        String data = cursor.getString(columnIndex2);
        long j = cursor.getLong(columnIndex9);
        ItemType itemType = ItemType.PHOTO;
        Intrinsics.checkExpressionValueIsNotNull(data, "data");
        MutableLocalItem mutableLocalItem = new MutableLocalItem(itemType, data, j);
        mutableLocalItem.setId(cursor.getLong(columnIndex));
        mutableLocalItem.setDateAdded(cursor.getLong(columnIndex3));
        mutableLocalItem.setDateTaken(cursor.getLong(columnIndex4));
        mutableLocalItem.setDateModified(cursor.getLong(columnIndex5));
        mutableLocalItem.setWidth(Long.valueOf(cursor.getLong(columnIndex6)));
        mutableLocalItem.setHeight(Long.valueOf(cursor.getLong(columnIndex7)));
        mutableLocalItem.setSize(Long.valueOf(cursor.getLong(columnIndex8)));
        return mutableLocalItem;
    }
}
