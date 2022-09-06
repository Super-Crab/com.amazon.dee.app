package com.amazon.alexa.assetManagementService.service;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.assetManagementService.entity.AssetMapping;
import com.amazon.alexa.assetManagementService.metrics.MetricsHelper;
import com.amazon.alexa.assetManagementService.model.AssetCache;
import com.amazon.alexa.assetManagementService.model.AssetMappingCache;
import com.amazon.alexa.assetManagementService.model.constants.GetAssetURLServiceConstants;
import com.amazon.alexa.assetManagementService.model.constants.GetAssetURLServiceMetricsConstants;
import com.amazon.alexa.assetManagementService.storage.GetAssetURLStorageService;
import com.amazon.alexa.assetManagementService.util.FallbackMappingParser;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.network.NetworkService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
/* loaded from: classes6.dex */
class CamCommon {
    private static final String EMPTY_STRING = "";
    private static final String LOGTAG = "CamCommonService";
    private Context context;
    private EnvironmentService environmentService;
    private Lazy<EventBus> eventBus;
    @VisibleForTesting
    FallbackMappingParser fallbackMappingParser;
    private boolean isCAMEnabled;
    private boolean isProdBuild;
    @VisibleForTesting
    MetricsHelper metricsHelper;
    private Lazy<Mobilytics> mobilytics;
    @VisibleForTesting
    NetworkHelper networkHelper;
    private NetworkService networkService;
    private GetAssetURLStorageService persistentStorage;
    @VisibleForTesting
    AssetMappingCache assetMappingCache = new AssetMappingCache();
    private String currentResolution = determineCurrentResolution();

    /* JADX INFO: Access modifiers changed from: package-private */
    public CamCommon(Context context, Lazy<Mobilytics> lazy, Lazy<EventBus> lazy2, GetAssetURLStorageService getAssetURLStorageService, boolean z, NetworkService networkService) {
        this.context = context;
        this.mobilytics = lazy;
        this.eventBus = lazy2;
        this.networkHelper = new NetworkHelper(this.context, this.currentResolution);
        this.networkService = networkService;
        this.metricsHelper = MetricsHelper.getInstance(this.mobilytics);
        this.persistentStorage = getAssetURLStorageService;
        this.isCAMEnabled = z;
        isCurrentBuildProd();
        this.fallbackMappingParser = FallbackMappingParser.getInstance(this.isProdBuild);
    }

    private void isCurrentBuildProd() {
        this.environmentService = (EnvironmentService) GeneratedOutlineSupport1.outline20(EnvironmentService.class);
        this.isProdBuild = !this.environmentService.getBuildFlavor().equalsIgnoreCase("prodqa");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<AssetMapping> convertCacheToPersistentStorageEntities() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> bundleIds = this.assetMappingCache.getBundleIds();
        while (bundleIds.hasNext()) {
            String next = bundleIds.next();
            Map<String, AssetCache> assetMappingCacheBundle = this.assetMappingCache.getAssetMappingCacheBundle(next);
            if (assetMappingCacheBundle != null) {
                for (Map.Entry<String, AssetCache> entry : assetMappingCacheBundle.entrySet()) {
                    arrayList.add(new AssetMapping(next, entry.getKey(), entry.getValue().getUrl()));
                }
            }
        }
        return arrayList;
    }

    @VisibleForTesting
    String determineCurrentResolution() {
        int i = this.context.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
        String str = (120 > i || i >= 160) ? (160 > i || i >= 240) ? (240 > i || i >= 320) ? (320 > i || i >= 480) ? (480 > i || i >= 640) ? i >= 640 ? "xxxhdpi" : "default" : "xxhdpi" : "xhdpi" : "hdpi" : "mdpi" : "ldpi";
        GeneratedOutlineSupport1.outline158("Resolution is ", str);
        return str;
    }

    public AssetMappingCache getAssetMappingCache() {
        return this.assetMappingCache;
    }

    public Context getContext() {
        return this.context;
    }

    public String getCurrentResolution() {
        return this.currentResolution;
    }

    public FallbackMappingParser getFallbackMappingParser() {
        return this.fallbackMappingParser;
    }

    public MetricsHelper getMetricsHelper() {
        return this.metricsHelper;
    }

    public Lazy<Mobilytics> getMobilytics() {
        return this.mobilytics;
    }

    public NetworkHelper getNetworkHelper() {
        return this.networkHelper;
    }

    public NetworkService getNetworkService() {
        return this.networkService;
    }

    String getSerializedAssetMappingCache() {
        if (!this.isCAMEnabled) {
            Log.i("CamCommonService", " Does not have access to Centralized Asset Management.");
            return "";
        }
        Log.i("CamCommonService", "getSerializedAssetMappingCache");
        if (this.assetMappingCache.getAssetMappingCache().isEmpty()) {
            return "";
        }
        this.assetMappingCache.toString();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put(GetAssetURLServiceConstants.BASE_URL, this.isProdBuild ? GetAssetURLServiceConstants.PROD_CLOUDFRONT_URL : GetAssetURLServiceConstants.STAGING_CLOUDFRONT_URL);
        ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
        concurrentHashMap2.putAll(this.assetMappingCache.getAssetMappingCache());
        concurrentHashMap2.putAll(concurrentHashMap);
        return new Gson().toJson(concurrentHashMap2);
    }

    public boolean isProdBuild() {
        return this.isProdBuild;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyAssetMappingsAreReady() {
        Log.i("CamCommonService", "Notifying asset mappings are ready.");
        String serializedAssetMappingCache = getSerializedAssetMappingCache();
        if (serializedAssetMappingCache.isEmpty()) {
            return;
        }
        this.eventBus.mo358get().publish(new Message.Builder().setEventType(GetAssetURLServiceConstants.ASSET_MAPPINGS_UPDATED_EVENT).setPayload(serializedAssetMappingCache).build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void saveToPersistentCache(List<AssetMapping> list) {
        if (list.size() > 0) {
            try {
                this.persistentStorage.save(list).get();
                Log.i("CamCommonService", "Asset mappings saved to persistent store successfully.");
            } catch (InterruptedException e) {
                Log.e("CamCommonService", "Thread that was saving asset mappings to persistent store was interrupted.", e);
                this.metricsHelper.recordErrorMetricsEvent("room_thread_error", "interrupt_error", GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, "RoomDatabase", e);
            } catch (ExecutionException e2) {
                Log.e("CamCommonService", "Thread that was saving asset mappings to persistent store could not complete the task.", e2);
                this.metricsHelper.recordErrorMetricsEvent("room_thread_error", "execution_error", GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, "RoomDatabase", e2);
            }
        }
    }
}
