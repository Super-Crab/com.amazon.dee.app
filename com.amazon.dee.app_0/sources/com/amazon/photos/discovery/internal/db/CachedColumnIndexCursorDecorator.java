package com.amazon.photos.discovery.internal.db;

import android.database.Cursor;
import android.database.CursorWrapper;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CachedColumnIndexCursorDecorator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0007H\u0016R\u001c\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/photos/discovery/internal/db/CachedColumnIndexCursorDecorator;", "Landroid/database/CursorWrapper;", "cursor", "Landroid/database/Cursor;", "(Landroid/database/Cursor;)V", "columnIndexCache", "", "", "", "getColumnIndex", "columnName", "getColumnIndexOrThrow", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CachedColumnIndexCursorDecorator extends CursorWrapper {
    private final Map<String, Integer> columnIndexCache;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CachedColumnIndexCursorDecorator(@NotNull Cursor cursor) {
        super(cursor);
        Intrinsics.checkParameterIsNotNull(cursor, "cursor");
        this.columnIndexCache = new HashMap();
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getColumnIndex(@NotNull String columnName) {
        Intrinsics.checkParameterIsNotNull(columnName, "columnName");
        if (!this.columnIndexCache.containsKey(columnName)) {
            this.columnIndexCache.put(columnName, Integer.valueOf(super.getColumnIndex(columnName)));
        }
        Integer num = this.columnIndexCache.get(columnName);
        if (num == null) {
            Intrinsics.throwNpe();
        }
        return num.intValue();
    }

    @Override // android.database.CursorWrapper, android.database.Cursor
    public int getColumnIndexOrThrow(@NotNull String columnName) throws IllegalArgumentException {
        Intrinsics.checkParameterIsNotNull(columnName, "columnName");
        if (!this.columnIndexCache.containsKey(columnName)) {
            this.columnIndexCache.put(columnName, Integer.valueOf(super.getColumnIndexOrThrow(columnName)));
        }
        Integer num = this.columnIndexCache.get(columnName);
        if (num == null) {
            Intrinsics.throwNpe();
        }
        return num.intValue();
    }
}
