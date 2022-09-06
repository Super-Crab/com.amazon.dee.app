package com.amazon.alexa.assetManagementService.dao;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.amazon.alexa.assetManagementService.entity.AssetMapping;
import com.amazon.alexa.assetManagementService.model.constants.LocalDBQuery;
import java.util.List;
@Dao
/* loaded from: classes6.dex */
public interface AssetMappingDao {
    @Query(LocalDBQuery.DELETE_ALL_ASSET_MAPPINGS_QUERY)
    void deleteAll();

    @Query(LocalDBQuery.SELECT_MAPPING_FOR_BUNDLE_ID)
    List<AssetMapping> get(@NonNull String str);

    @Query(LocalDBQuery.SELECT_ALL_ASSET_MAPPINGS_QUERY)
    List<AssetMapping> getAll();

    @Query(LocalDBQuery.SELECT_ALL_BUNDLE_IDS_QUERY)
    List<String> getAllBundleIds();

    @Insert(onConflict = 1)
    void insert(@NonNull AssetMapping assetMapping);
}
