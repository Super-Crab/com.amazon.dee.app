package com.amazon.photos.uploader.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import com.amazon.photos.uploader.Event;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: EventDao.kt */
@Dao
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H!¢\u0006\u0002\b\u0007J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH'J\u0015\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\nH!¢\u0006\u0002\b\rJ\u0015\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0010¢\u0006\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/amazon/photos/uploader/dao/EventDao;", "", "()V", "deleteOldEvents", "", "cutoffTimeMillis", "", "deleteOldEvents$AndroidPhotosUploader_release", "getEvents", "", "Lcom/amazon/photos/uploader/Event;", "insertEvent", "event", "insertEvent$AndroidPhotosUploader_release", "recordEvent", "description", "", "recordEvent$AndroidPhotosUploader_release", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class EventDao {
    @Query("DELETE FROM events WHERE event_time_millis < :cutoffTimeMillis")
    public abstract void deleteOldEvents$AndroidPhotosUploader_release(long j);

    @Query("SELECT * FROM events ORDER BY id ASC")
    @Transaction
    @NotNull
    public abstract List<Event> getEvents();

    @Insert(onConflict = 5)
    public abstract long insertEvent$AndroidPhotosUploader_release(@NotNull Event event);

    public long recordEvent$AndroidPhotosUploader_release(@NotNull String description) {
        Intrinsics.checkParameterIsNotNull(description, "description");
        deleteOldEvents$AndroidPhotosUploader_release(System.currentTimeMillis() - 86400000);
        return insertEvent$AndroidPhotosUploader_release(new Event(0L, description, System.currentTimeMillis(), 1, null));
    }
}
