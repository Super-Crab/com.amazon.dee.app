package com.amazon.photos.uploader.cds.nodecache;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: NodeCacheDatabase.kt */
@Database(entities = {ParentId.class}, version = 1)
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b!\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/cds/nodecache/NodeCacheDatabase;", "Landroidx/room/RoomDatabase;", "()V", "parentIdDao", "Lcom/amazon/photos/uploader/cds/nodecache/ParentIdDao;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class NodeCacheDatabase extends RoomDatabase {
    @NotNull
    public abstract ParentIdDao parentIdDao();
}
