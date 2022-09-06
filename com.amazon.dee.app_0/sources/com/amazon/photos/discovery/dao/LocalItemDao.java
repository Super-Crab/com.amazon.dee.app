package com.amazon.photos.discovery.dao;

import androidx.annotation.AnyThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;
import com.amazon.alexa.accessory.frames.contacts.ContactsModuleConstants;
import com.amazon.photos.discovery.model.LocalItem;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: LocalItemDao.kt */
@Dao
@AnyThread
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H'J\u0014\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H'J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH'J\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH'J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\bH'¨\u0006\u000f"}, d2 = {"Lcom/amazon/photos/discovery/dao/LocalItemDao;", "", "getAllItems", "Landroidx/paging/DataSource$Factory;", "", "Lcom/amazon/photos/discovery/model/LocalItem;", "getAllValidItems", "getItemCount", "Landroidx/lifecycle/LiveData;", "getLocalItemByIds", "", ContactsModuleConstants.CONTACT_IDS, "", "", "getValidItemCount", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface LocalItemDao {
    @Query("SELECT * FROM local_item ORDER BY id DESC")
    @NotNull
    DataSource.Factory<Integer, LocalItem> getAllItems();

    @Query("SELECT * FROM local_item WHERE size > 0 ORDER BY id DESC")
    @NotNull
    DataSource.Factory<Integer, LocalItem> getAllValidItems();

    @Query("SELECT COUNT(id) FROM local_item")
    @NotNull
    LiveData<Integer> getItemCount();

    @Query("SELECT * FROM local_item WHERE id IN (:ids) ORDER BY date_added ASC")
    @NotNull
    List<LocalItem> getLocalItemByIds(@NotNull Collection<Long> collection);

    @Query("SELECT COUNT(id) FROM local_item WHERE size > 0")
    @NotNull
    LiveData<Integer> getValidItemCount();
}
