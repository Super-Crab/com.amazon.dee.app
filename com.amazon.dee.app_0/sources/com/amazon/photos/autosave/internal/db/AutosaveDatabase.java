package com.amazon.photos.autosave.internal.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.amazon.photos.autosave.internal.dao.AutosaveBucketDao;
import com.amazon.photos.autosave.internal.dao.AutosaveItemDao;
import com.amazon.photos.autosave.model.AutosaveBucket;
import com.amazon.photos.autosave.model.AutosaveItem;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveDatabase.kt */
@TypeConverters({Converters.class})
@Database(entities = {AutosaveItem.class, AutosaveBucket.class}, version = 1)
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b!\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/photos/autosave/internal/db/AutosaveDatabase;", "Landroidx/room/RoomDatabase;", "()V", "bucketDao", "Lcom/amazon/photos/autosave/internal/dao/AutosaveBucketDao;", "itemDao", "Lcom/amazon/photos/autosave/internal/dao/AutosaveItemDao;", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class AutosaveDatabase extends RoomDatabase {
    @NotNull
    public abstract AutosaveBucketDao bucketDao();

    @NotNull
    public abstract AutosaveItemDao itemDao();
}
