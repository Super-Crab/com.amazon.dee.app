package com.amazon.photos.uploader.cds.nodecache;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.amazon.alexa.routing.api.RouteParameter;
import com.dee.app.metrics.MetricsConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ParentIdDao.kt */
@Dao
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH'¨\u0006\u000b"}, d2 = {"Lcom/amazon/photos/uploader/cds/nodecache/ParentIdDao;", "", "deleteAll", "", MetricsConstants.Method.CACHE_GET, "", RouteParameter.PATH, "insert", "", "record", "Lcom/amazon/photos/uploader/cds/nodecache/ParentId;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface ParentIdDao {
    @Query("DELETE FROM parent_id")
    void deleteAll();

    @Query("SELECT node_id FROM parent_id WHERE path = :path")
    @Nullable
    String get(@NotNull String str);

    @Insert(onConflict = 1)
    long insert(@NotNull ParentId parentId);
}
