package com.amazon.alexa.assetManagementService.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.amazon.alexa.assetManagementService.model.constants.LocalDBQuery;
@Entity(primaryKeys = {"resourceId", "bundleId"})
/* loaded from: classes6.dex */
public final class AssetMapping {
    @NonNull
    @ColumnInfo(name = LocalDBQuery.ASSET_URL_COLUMN)
    public String assetURL;
    @NonNull
    @ColumnInfo(name = "bundleId")
    public String bundleId;
    @NonNull
    @ColumnInfo(name = "resourceId")
    public String resourceId;

    public AssetMapping(String str, String str2, String str3) {
        this.bundleId = str;
        this.resourceId = str2;
        this.assetURL = str3;
    }

    public AssetMapping() {
    }
}
