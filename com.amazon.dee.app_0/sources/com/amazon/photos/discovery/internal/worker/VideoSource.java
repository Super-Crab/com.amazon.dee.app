package com.amazon.photos.discovery.internal.worker;

import android.content.ContentResolver;
import android.database.Cursor;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.amazon.photos.discovery.CrashReporter;
import com.amazon.photos.discovery.internal.ConstantsKt;
import com.amazon.photos.discovery.internal.db.CachedColumnIndexCursorDecorator;
import com.amazon.photos.discovery.internal.model.MutableLocalItem;
import com.amazon.photos.discovery.internal.util.MediaStoreUtil;
import com.amazon.photos.discovery.model.ItemType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ScanAddedWorkerUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0002\u001a\u00020\u0003H\u0014J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000eH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/photos/discovery/internal/worker/VideoSource;", "Lcom/amazon/photos/discovery/internal/worker/CursorItemSource;", "lastAddedRowId", "", "contentResolver", "Landroid/content/ContentResolver;", "mediaStoreUtil", "Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "crashReporter", "Lcom/amazon/photos/discovery/CrashReporter;", "(JLandroid/content/ContentResolver;Lcom/amazon/photos/discovery/internal/util/MediaStoreUtil;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/discovery/CrashReporter;)V", "createCursor", "Landroid/database/Cursor;", "getSeparator", "", "resolution", "itemFromCursor", "Lcom/amazon/photos/discovery/internal/model/MutableLocalItem;", "cursor", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class VideoSource extends CursorItemSource {
    private final ContentResolver contentResolver;
    private final CrashReporter crashReporter;
    private final MediaStoreUtil mediaStoreUtil;
    private final Metrics metrics;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoSource(long j, @NotNull ContentResolver contentResolver, @NotNull MediaStoreUtil mediaStoreUtil, @NotNull Metrics metrics, @Nullable CrashReporter crashReporter) {
        super(j, metrics);
        Intrinsics.checkParameterIsNotNull(contentResolver, "contentResolver");
        Intrinsics.checkParameterIsNotNull(mediaStoreUtil, "mediaStoreUtil");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.contentResolver = contentResolver;
        this.mediaStoreUtil = mediaStoreUtil;
        this.metrics = metrics;
        this.crashReporter = crashReporter;
    }

    private final String getSeparator(String str) {
        boolean contains$default;
        boolean contains$default2;
        boolean contains$default3;
        contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "×", false, 2, (Object) null);
        if (contains$default) {
            return "×";
        }
        contains$default2 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) ReactProperties.HereMapMarker.X, false, 2, (Object) null);
        if (contains$default2) {
            return ReactProperties.HereMapMarker.X;
        }
        contains$default3 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "X", false, 2, (Object) null);
        return contains$default3 ? "X" : ReactProperties.HereMapMarker.X;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.photos.discovery.internal.worker.CursorItemSource
    @Nullable
    public Cursor createCursor(long j) {
        Cursor query = this.contentResolver.query(this.mediaStoreUtil.getVideosUri(), new String[]{"_id", "_data", "date_added", "datetaken", "date_modified", "duration", "resolution", "_size", "bucket_id"}, "_id >= ?", new String[]{String.valueOf(j)}, "_id ASC");
        if (query != null) {
            return new CachedColumnIndexCursorDecorator(query);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.photos.discovery.internal.worker.CursorItemSource
    @NotNull
    public MutableLocalItem itemFromCursor(@NotNull Cursor cursor) {
        List split$default;
        Intrinsics.checkParameterIsNotNull(cursor, "cursor");
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("_data");
        int columnIndex3 = cursor.getColumnIndex("date_added");
        int columnIndex4 = cursor.getColumnIndex("datetaken");
        int columnIndex5 = cursor.getColumnIndex("date_modified");
        int columnIndex6 = cursor.getColumnIndex("duration");
        int columnIndex7 = cursor.getColumnIndex("resolution");
        int columnIndex8 = cursor.getColumnIndex("_size");
        int columnIndex9 = cursor.getColumnIndex("bucket_id");
        String data = cursor.getString(columnIndex2);
        long j = cursor.getLong(columnIndex9);
        ItemType itemType = ItemType.VIDEO;
        Intrinsics.checkExpressionValueIsNotNull(data, "data");
        MutableLocalItem mutableLocalItem = new MutableLocalItem(itemType, data, j);
        mutableLocalItem.setId(cursor.getLong(columnIndex));
        mutableLocalItem.setDateAdded(cursor.getLong(columnIndex3));
        mutableLocalItem.setDateTaken(cursor.getLong(columnIndex4));
        mutableLocalItem.setDateModified(cursor.getLong(columnIndex5));
        mutableLocalItem.setDuration(Long.valueOf(cursor.getLong(columnIndex6)));
        mutableLocalItem.setSize(Long.valueOf(cursor.getLong(columnIndex8)));
        String string = cursor.getString(columnIndex7);
        if (string != null) {
            split$default = StringsKt__StringsKt.split$default((CharSequence) string, new String[]{getSeparator(string)}, false, 0, 6, (Object) null);
            if (split$default.size() == 2) {
                try {
                    mutableLocalItem.setWidth(Long.valueOf(Long.parseLong((String) split$default.get(0))));
                    mutableLocalItem.setHeight(Long.valueOf(Long.parseLong((String) split$default.get(1))));
                } catch (NumberFormatException e) {
                    this.metrics.recordSimpleErrorEvent(ConstantsKt.SCAN_ADDED_WORKER_UTIL, VideoSource$itemFromCursor$1$1.INSTANCE, e);
                }
            } else {
                this.metrics.recordSimpleEvent(ConstantsKt.SCAN_ADDED_WORKER_UTIL, VideoSource$itemFromCursor$1$2.INSTANCE, new MetricRecordingType[0]);
            }
        }
        return mutableLocalItem;
    }
}
