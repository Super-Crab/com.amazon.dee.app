package com.amazon.alexa.assetManagementService.service;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.assetManagementService.api.AssetManagementService;
import com.amazon.alexa.assetManagementService.entity.AssetMapping;
import com.amazon.alexa.assetManagementService.metrics.MetricsHelper;
import com.amazon.alexa.assetManagementService.model.AssetMappingCache;
import com.amazon.alexa.assetManagementService.model.constants.GetAssetURLServiceMetricsConstants;
import com.amazon.alexa.assetManagementService.storage.GetAssetURLStorageService;
import com.amazon.alexa.assetManagementService.util.FallbackMappingParser;
import com.amazon.alexa.assetManagementService.util.FileCheckSumSHA;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.network.NetworkService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dagger.Lazy;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
/* loaded from: classes6.dex */
public class GetAssetURLService implements AssetManagementService {
    private static final String LOGTAG = "GetAssetURLService";
    @VisibleForTesting
    MultiFilterSubscriber.FilterUuid appUpgradedFilterUuid;
    @VisibleForTesting
    MultiFilterSubscriber appUpgradedSubscriber;
    @VisibleForTesting
    AssetMappingCache assetMappingCache;
    private CamCommon camCommon;
    private Context context;
    String currentResolution;
    @VisibleForTesting
    MultiFilterSubscriber.FilterUuid elementsFilterUuid;
    @VisibleForTesting
    MultiFilterSubscriber elementsSubscriber;
    final Lazy<EventBus> eventBusLazy;
    @VisibleForTesting
    FallbackMappingParser fallbackMappingParser;
    private FeatureServiceV2 featureServiceV2;
    private FileCheckSumSHA fileCheckSumSHA;
    @VisibleForTesting
    boolean isCAMEnabled;
    private MetricsHelper metricsHelper;
    private Lazy<Mobilytics> mobilytics;
    private NetworkCallService networkCallService;
    @VisibleForTesting
    private NetworkService networkService;
    private GetAssetURLStorageService persistentStorage;
    @VisibleForTesting
    MultiFilterSubscriber.FilterUuid signOutFilterUuid;
    @VisibleForTesting
    MultiFilterSubscriber signOutSubscriber;
    boolean isCAMConfigured = false;
    @VisibleForTesting
    boolean isCAMNetworkCallEnabled = false;
    FeatureServiceV2.FeatureUpdateListener featureUpdateListener = new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.alexa.assetManagementService.service.GetAssetURLService.1
        @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
        public void onFeatureUpdate(String str) {
            GetAssetURLService.this.featureUpdateHandler(str);
        }
    };

    public GetAssetURLService(Lazy<EventBus> lazy, Lazy<Mobilytics> lazy2, GetAssetURLStorageService getAssetURLStorageService, Context context) {
        this.eventBusLazy = lazy;
        this.mobilytics = lazy2;
        this.persistentStorage = getAssetURLStorageService;
        this.context = context;
    }

    void clearCache() {
        try {
            this.persistentStorage.clearAll().get();
        } catch (InterruptedException e) {
            Log.e("GetAssetURLService", "Thread that was deleting asset mappings from persistent store was interrupted.", e);
            this.metricsHelper.recordErrorMetricsEvent("room_thread_error", "interrupt_error", GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, "RoomDatabase", e);
        } catch (ExecutionException e2) {
            Log.e("GetAssetURLService", "Thread that was deleting asset mappings from persistent store could not complete the task.", e2);
            this.metricsHelper.recordErrorMetricsEvent("room_thread_error", "execution_error", GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, "RoomDatabase", e2);
        }
        this.assetMappingCache = new AssetMappingCache();
    }

    void configureCAM() {
        if (!this.isCAMEnabled) {
            Log.i("GetAssetURLService", "camInitialize Does not have access to Centralized Asset Management.");
            return;
        }
        this.camCommon = createCamCommonInstance();
        this.networkCallService = createNetworkCallServiceInstance(this.camCommon);
        this.fileCheckSumSHA = createFileCheckSumInstance(this.context);
        this.metricsHelper = this.camCommon.getMetricsHelper();
        this.assetMappingCache = this.camCommon.getAssetMappingCache();
        this.fallbackMappingParser = this.camCommon.getFallbackMappingParser();
        this.currentResolution = this.camCommon.getCurrentResolution();
        this.isCAMConfigured = true;
        this.signOutSubscriber = this.eventBusLazy.mo358get().getNewSubscriber();
        this.appUpgradedSubscriber = this.eventBusLazy.mo358get().getNewSubscriber();
        this.elementsSubscriber = this.eventBusLazy.mo358get().getNewSubscriber();
        subscribeToAppUpdated();
        subscribeToElements();
        loadAssetMapping();
    }

    void convertPersistentStorageEntitiesToCache() {
        try {
            for (String str : this.persistentStorage.getAllBundleIds().get()) {
                JsonObject jsonObject = new JsonObject();
                for (AssetMapping assetMapping : this.persistentStorage.getAssetMapping(str).get()) {
                    JsonObject jsonObject2 = new JsonObject();
                    jsonObject2.addProperty("url", assetMapping.assetURL);
                    jsonObject.add(assetMapping.resourceId, jsonObject2);
                }
                this.assetMappingCache.setAssetMappingCache(str, jsonObject);
            }
        } catch (InterruptedException | ExecutionException e) {
            Log.i("GetAssetURLService", GetAssetURLServiceMetricsConstants.Subcomponent.STORAGE_TO_CACHE);
            this.metricsHelper.recordErrorMetricsEvent("room_thread_error", "room_thread_error", GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, GetAssetURLServiceMetricsConstants.Subcomponent.STORAGE_TO_CACHE, e);
        }
    }

    CamCommon createCamCommonInstance() {
        return new CamCommon(this.context, this.mobilytics, this.eventBusLazy, this.persistentStorage, this.isCAMEnabled, this.networkService);
    }

    @VisibleForTesting
    FileCheckSumSHA createFileCheckSumInstance(Context context) {
        return new FileCheckSumSHA(context);
    }

    NetworkCallService createNetworkCallServiceInstance(CamCommon camCommon) {
        return new NetworkCallService(camCommon);
    }

    void featureUpdateHandler(String str) {
        if (str.equalsIgnoreCase("MOSAIC_CENTRALIZED_ASSET_MANAGEMENT_ANDROID")) {
            this.isCAMEnabled = this.featureServiceV2.hasAccess("MOSAIC_CENTRALIZED_ASSET_MANAGEMENT_ANDROID", false);
            if (this.isCAMConfigured) {
                return;
            }
            configureCAM();
        } else if (!str.equalsIgnoreCase("MOSAIC_CAM_NETWORK_ANDROID")) {
        } else {
            this.isCAMNetworkCallEnabled = this.featureServiceV2.hasAccess("MOSAIC_CAM_NETWORK_ANDROID", false);
        }
    }

    List<AssetMapping> filterCurrentResolutionMappings(JsonObject jsonObject) {
        JsonObject jsonObject2 = new JsonObject();
        JsonObject jsonObject3 = new JsonObject();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            if (entry.getKey().toString().equals(this.currentResolution)) {
                jsonObject3 = jsonObject.getAsJsonObject(this.currentResolution);
            } else if (entry.getKey().toString().equals("default")) {
                jsonObject2 = jsonObject.getAsJsonObject("default");
            }
        }
        for (Map.Entry<String, JsonElement> entry2 : jsonObject2.entrySet()) {
            String obj = entry2.getKey().toString();
            if (jsonObject3.has(obj)) {
                this.assetMappingCache.setAssetMappingCache(obj, jsonObject3.getAsJsonObject(obj));
            } else {
                this.assetMappingCache.setAssetMappingCache(obj, jsonObject2.getAsJsonObject(obj));
            }
        }
        return this.camCommon.convertCacheToPersistentStorageEntities();
    }

    void getAssetMappingsFromCloudFront() {
        this.isCAMNetworkCallEnabled = this.featureServiceV2.hasAccess("MOSAIC_CAM_NETWORK_ANDROID", false);
        if (!this.isCAMNetworkCallEnabled || !this.isCAMEnabled) {
            return;
        }
        this.networkCallService.callCloudFrontForMappings(this.networkService.isConnected());
    }

    @Override // com.amazon.alexa.assetManagementService.api.AssetManagementService
    public String getAssetURL(@NonNull String str, @NonNull String str2) {
        if (!this.isCAMEnabled) {
            Log.i("GetAssetURLService", " getAssetURL Does not have access to Centralized Asset Management.");
            return "";
        }
        Log.i("GetAssetURLService", " Beginning of getAssetURL ");
        MobilyticsMetricsTimer createTimerEvent = this.metricsHelper.createTimerEvent("getAssetURLService.getAssetURL", GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, GetAssetURLServiceMetricsConstants.Subcomponent.GET_ASSET_URL);
        String outline75 = GeneratedOutlineSupport1.outline75(str2, "_", str);
        String assetMappingCacheItem = this.assetMappingCache.getAssetMappingCacheItem(str2, str);
        this.metricsHelper.recordOccurrenceMetricsEvent(GeneratedOutlineSupport1.outline72("getAssetURL.", outline75), true, GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, GeneratedOutlineSupport1.outline72("getAssetURL.", str2));
        createTimerEvent.finishTimer();
        this.mobilytics.mo358get().recordTimer(createTimerEvent);
        if (assetMappingCacheItem == null) {
            this.metricsHelper.recordOccurrenceMetricsEvent(GeneratedOutlineSupport1.outline76("getAssetURL.", outline75, ".", "null"), false, GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, GeneratedOutlineSupport1.outline72("getAssetURL.", str2));
            return null;
        }
        GeneratedOutlineSupport1.outline163("Returned url is ", assetMappingCacheItem, "GetAssetURLService");
        return this.fallbackMappingParser.stageBasedCloudFrontURL(assetMappingCacheItem);
    }

    @Override // com.amazon.alexa.assetManagementService.api.AssetManagementService
    public void initialize() {
        this.featureServiceV2 = (FeatureServiceV2) GeneratedOutlineSupport1.outline20(FeatureServiceV2.class);
        this.networkService = (NetworkService) GeneratedOutlineSupport1.outline20(NetworkService.class);
        this.featureServiceV2.observeFeature("MOSAIC_CENTRALIZED_ASSET_MANAGEMENT_ANDROID", this.featureUpdateListener);
        this.featureServiceV2.observeFeature("MOSAIC_CAM_NETWORK_ANDROID", this.featureUpdateListener);
    }

    public /* synthetic */ void lambda$subscribeToAppUpdated$3$GetAssetURLService(Message message) {
        onAppUpdated();
    }

    public /* synthetic */ void lambda$subscribeToElements$5$GetAssetURLService(Message message) {
        this.camCommon.notifyAssetMappingsAreReady();
    }

    public /* synthetic */ void lambda$subscribeToSignOut$1$GetAssetURLService(Message message) {
        onSignOutSuccess();
    }

    void loadAssetMapping() {
        if (loadAssetMappingsFromPersistentCache().size() != 0 && !this.fileCheckSumSHA.shouldFallbackBeParsed()) {
            convertPersistentStorageEntitiesToCache();
        } else {
            this.camCommon.saveToPersistentCache(filterCurrentResolutionMappings(parseFallbackMapping()));
        }
        this.camCommon.notifyAssetMappingsAreReady();
        getAssetMappingsFromCloudFront();
    }

    List<AssetMapping> loadAssetMappingsFromPersistentCache() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList = this.persistentStorage.loadAll().get();
        } catch (InterruptedException e) {
            Log.e("GetAssetURLService", "Thread that was loading asset mappings from persistent store was interrupted.", e);
            this.metricsHelper.recordErrorMetricsEvent("room_thread_error", "interrupt_error", GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, "RoomDatabase", e);
        } catch (ExecutionException e2) {
            Log.e("GetAssetURLService", "Thread that was loading asset mappings from persistent store could not complete the task.", e2);
            this.metricsHelper.recordErrorMetricsEvent("room_thread_error", "execution_error", GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, "RoomDatabase", e2);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Loaded from persistent storage successfully! ");
        outline107.append(arrayList.size());
        outline107.toString();
        return arrayList;
    }

    @VisibleForTesting
    void onAppUpdated() {
        clearCache();
        loadAssetMapping();
        unSubscribeToAppUpdated();
        subscribeToSignOut();
    }

    @VisibleForTesting
    void onSignOutSuccess() {
        clearCache();
        unSubscribeToSignOut();
    }

    JsonObject parseFallbackMapping() {
        JsonObject jsonObject = new JsonObject();
        try {
            InputStream open = this.context.getResources().getAssets().open("fallbackAssetMapping.json");
            jsonObject = this.fallbackMappingParser.readJSONStream(open);
            if (open != null) {
                open.close();
            }
        } catch (IOException e) {
            this.metricsHelper.recordErrorMetricsEvent(GetAssetURLServiceMetricsConstants.EventType.PARSER_ERROR, GetAssetURLServiceMetricsConstants.EventType.PARSER_ERROR, GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, GetAssetURLServiceMetricsConstants.Subcomponent.IO_FAILURE, e);
        }
        return jsonObject;
    }

    @VisibleForTesting
    void subscribeToAppUpdated() {
        this.appUpgradedFilterUuid = this.appUpgradedSubscriber.subscribeFilter($$Lambda$GetAssetURLService$Dm8dc1kZYNdYVQVftv8iZzerxHM.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.assetManagementService.service.-$$Lambda$GetAssetURLService$3Ndqjy7DNk5P9G9KpoFUPPKcpYY
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                GetAssetURLService.this.lambda$subscribeToAppUpdated$3$GetAssetURLService(message);
            }
        });
    }

    @VisibleForTesting
    void subscribeToElements() {
        this.elementsFilterUuid = this.elementsSubscriber.subscribeFilter($$Lambda$GetAssetURLService$abj8mSLd6Vap9DroH2UvYmp0Zfo.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.assetManagementService.service.-$$Lambda$GetAssetURLService$Kk2-PxHfJWFRa7NGfPhtSwIAiHc
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                GetAssetURLService.this.lambda$subscribeToElements$5$GetAssetURLService(message);
            }
        });
    }

    @VisibleForTesting
    void subscribeToSignOut() {
        this.signOutFilterUuid = this.signOutSubscriber.subscribeFilter($$Lambda$GetAssetURLService$H2Xk4X97HXnHNh85IXtzZB4y_I.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.assetManagementService.service.-$$Lambda$GetAssetURLService$qtkoPyp5Q-xu41ag7O2mtO-oFM8
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                GetAssetURLService.this.lambda$subscribeToSignOut$1$GetAssetURLService(message);
            }
        });
    }

    @VisibleForTesting
    void unSubscribeToAppUpdated() {
        MultiFilterSubscriber.FilterUuid filterUuid = this.appUpgradedFilterUuid;
        if (filterUuid != null) {
            this.appUpgradedSubscriber.unsubscribeFilter(filterUuid);
        }
    }

    @VisibleForTesting
    void unSubscribeToSignOut() {
        MultiFilterSubscriber.FilterUuid filterUuid = this.signOutFilterUuid;
        if (filterUuid != null) {
            this.signOutSubscriber.unsubscribeFilter(filterUuid);
        }
    }
}
