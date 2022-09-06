package com.amazon.photos.autosave.internal.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import com.amazon.photos.autosave.model.AutosaveItem;
import com.amazon.photos.autosave.model.AutosaveState;
import com.amazon.photos.autosave.model.MediaType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: AutosaveItemDao.kt */
@Dao
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b!\u0018\u0000  2\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH'J\u0016\u0010\n\u001a\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fH'J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\fH'J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H'J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\f2\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0011H'J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fH'J\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0011H'J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\f2\u0006\u0010\u0010\u001a\u00020\u0011H'J\u0010\u0010\u001b\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH'J\u0010\u0010\u001c\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH'J\u0018\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0010\u001a\u00020\u0011H'¨\u0006!"}, d2 = {"Lcom/amazon/photos/autosave/internal/dao/AutosaveItemDao;", "", "()V", "deleteById", "", "itemId", "", "deleteItem", "item", "Lcom/amazon/photos/autosave/model/AutosaveItem;", "deleteItemsByIds", "itemIds", "", "getAllItems", "getCountForState", "", "state", "Lcom/amazon/photos/autosave/model/AutosaveState;", "getItemById", "getItemsByFolderIdAndState", "folderId", "autosaveState", "getItemsByLocalIds", "getItemsByMediaTypeAndState", "mediaType", "Lcom/amazon/photos/autosave/model/MediaType;", "getItemsForState", "insertItem", "updateItem", "updateStateByFilePath", "filePath", "", "Companion", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public abstract class AutosaveItemDao {
    public static final Companion Companion = new Companion(null);
    public static final int QUERY_WHERE_IN_BATCH_SIZE = 998;

    /* compiled from: AutosaveItemDao.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/autosave/internal/dao/AutosaveItemDao$Companion;", "", "()V", "QUERY_WHERE_IN_BATCH_SIZE", "", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Query("DELETE FROM autosave_item WHERE id = :itemId")
    public abstract void deleteById(long j);

    @Delete
    public abstract void deleteItem(@NotNull AutosaveItem autosaveItem);

    @Query("DELETE FROM autosave_item WHERE id IN (:itemIds)")
    public abstract void deleteItemsByIds(@NotNull List<Long> list);

    @Query("SELECT * FROM autosave_item")
    @NotNull
    public abstract List<AutosaveItem> getAllItems();

    @Query("SELECT COUNT(id) FROM autosave_item WHERE state = :state")
    public abstract int getCountForState(@NotNull AutosaveState autosaveState);

    @Query("SELECT * FROM autosave_item WHERE id = :itemId")
    @NotNull
    public abstract AutosaveItem getItemById(long j);

    @Query("SELECT * FROM autosave_item WHERE folder_id = :folderId AND state = :autosaveState")
    @NotNull
    public abstract List<AutosaveItem> getItemsByFolderIdAndState(long j, @NotNull AutosaveState autosaveState);

    @Query("SELECT * FROM autosave_item WHERE local_item_id IN (:itemIds)")
    @NotNull
    public abstract List<AutosaveItem> getItemsByLocalIds(@NotNull List<Long> list);

    @Query("SELECT * FROM autosave_item WHERE media_type = :mediaType AND state = :autosaveState")
    @NotNull
    public abstract List<AutosaveItem> getItemsByMediaTypeAndState(@NotNull MediaType mediaType, @NotNull AutosaveState autosaveState);

    @Query("SELECT * FROM autosave_item WHERE state = :state")
    @NotNull
    public abstract List<AutosaveItem> getItemsForState(@NotNull AutosaveState autosaveState);

    @Insert(onConflict = 5)
    public abstract long insertItem(@NotNull AutosaveItem autosaveItem);

    @Update
    public abstract void updateItem(@NotNull AutosaveItem autosaveItem);

    @Query("UPDATE autosave_item SET state = :state WHERE file_path =:filePath")
    @Transaction
    public abstract void updateStateByFilePath(@NotNull String str, @NotNull AutosaveState autosaveState);
}
