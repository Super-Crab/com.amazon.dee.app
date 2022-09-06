package com.amazon.alexa.assetManagementService.model.constants;
/* loaded from: classes6.dex */
public final class LocalDBQuery {
    public static final String ASSET_MAPPING_DB = "com.amazon.alexa.assetManagementService.db";
    public static final String ASSET_URL_COLUMN = "assetURL";
    public static final String DELETE_ALL_ASSET_MAPPINGS_QUERY = "DELETE FROM AssetMapping";
    public static final String SELECT_ALL_ASSET_MAPPINGS_QUERY = "SELECT * from AssetMapping";
    public static final String SELECT_ALL_BUNDLE_IDS_QUERY = "SELECT DISTINCT bundleId FROM AssetMapping";
    public static final String SELECT_MAPPING_FOR_BUNDLE_ID = "SELECT * from AssetMapping WHERE bundleId = :bundleName";

    private LocalDBQuery() {
    }
}
