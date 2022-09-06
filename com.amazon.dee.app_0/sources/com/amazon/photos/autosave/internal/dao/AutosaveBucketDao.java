package com.amazon.photos.autosave.internal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.amazon.photos.autosave.model.AutosaveBucket;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AutosaveBucketDao.kt */
@Dao
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\b!\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH'J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH'J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH'J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH'J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\nH'J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'¨\u0006\u0014"}, d2 = {"Lcom/amazon/photos/autosave/internal/dao/AutosaveBucketDao;", "", "()V", "deleteBucket", "", "bucket", "Lcom/amazon/photos/autosave/model/AutosaveBucket;", "deleteByBucketPath", "", "bucketPath", "", "deleteById", "bucketId", "", "getAllBuckets", "", "getBucketById", "getBucketByPath", "insertOrIgnoreItem", "updateItem", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class AutosaveBucketDao {
    @Delete
    public abstract void deleteBucket(@NotNull AutosaveBucket autosaveBucket);

    @Query("DELETE FROM autosave_bucket WHERE bucket_path = :bucketPath")
    public abstract int deleteByBucketPath(@NotNull String str);

    @Query("DELETE FROM autosave_bucket WHERE id = :bucketId")
    public abstract void deleteById(long j);

    @Query("SELECT * FROM autosave_bucket")
    @NotNull
    public abstract List<AutosaveBucket> getAllBuckets();

    @Query("SELECT * FROM autosave_bucket WHERE id = :bucketId")
    @NotNull
    public abstract AutosaveBucket getBucketById(long j);

    @Query("SELECT * FROM autosave_bucket WHERE bucket_path = :bucketPath")
    @Nullable
    public abstract AutosaveBucket getBucketByPath(@NotNull String str);

    @Insert(onConflict = 5)
    public abstract long insertOrIgnoreItem(@NotNull AutosaveBucket autosaveBucket);

    @Update
    public abstract void updateItem(@NotNull AutosaveBucket autosaveBucket);
}
