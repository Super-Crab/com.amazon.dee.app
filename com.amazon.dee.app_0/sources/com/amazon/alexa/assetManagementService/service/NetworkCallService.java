package com.amazon.alexa.assetManagementService.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.assetManagementService.entity.AssetMapping;
import com.amazon.alexa.assetManagementService.metrics.MetricsHelper;
import com.amazon.alexa.assetManagementService.model.AssetMappingCache;
import com.amazon.alexa.assetManagementService.model.constants.GetAssetURLServiceMetricsConstants;
import com.amazon.alexa.assetManagementService.util.ExceptionUtil;
import com.amazon.alexa.assetManagementService.util.FallbackMappingParser;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.tasks.api.TaskManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dagger.Lazy;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import rx.Subscription;
import rx.functions.Action1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class NetworkCallService {
    private static final int CONNECT_TIMEOUT = 60000;
    private static final String LOGTAG = "NetworkCallService";
    private static final int SOCKET_TIMEOUT = 60000;
    private static final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
    private AssetMappingCache assetMappingCache;
    private CamCommon camCommon;
    private Context context;
    private String currentEtag;
    private FallbackMappingParser fallbackMappingParser;
    private boolean isNetworkCallMade = false;
    private MetricsHelper metricsHelper;
    private Lazy<Mobilytics> mobilytics;
    private NetworkHelper networkHelper;
    private NetworkService networkService;
    private Subscription networkServiceSubscription;
    private TaskManager taskManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NetworkCallService(CamCommon camCommon) {
        this.camCommon = camCommon;
        this.networkHelper = this.camCommon.getNetworkHelper();
        this.metricsHelper = this.camCommon.getMetricsHelper();
        this.mobilytics = this.camCommon.getMobilytics();
        this.context = this.camCommon.getContext();
        this.fallbackMappingParser = this.camCommon.getFallbackMappingParser();
        this.assetMappingCache = this.camCommon.getAssetMappingCache();
        this.currentEtag = this.networkHelper.getCurrentEtag();
        this.networkService = this.camCommon.getNetworkService();
        this.networkServiceSubscription = this.networkService.onConnectivityChanged().subscribe(new Action1() { // from class: com.amazon.alexa.assetManagementService.service.-$$Lambda$8Gm6-61Ekx5CdwUDGMuGsFeU8dg
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NetworkCallService.this.callCloudFrontForMappings(((Boolean) obj).booleanValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void callCloudFrontForMappings(boolean z) {
        if (!z || this.isNetworkCallMade) {
            if (z) {
                return;
            }
            this.metricsHelper.recordOccurrenceMetricsEvent("getAssetURLService.no_network.mapping_failure", false, GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, GetAssetURLServiceMetricsConstants.Subcomponent.MAPPING_FAILURE);
            return;
        }
        final Request createRequest = this.networkHelper.createRequest(this.camCommon.isProdBuild());
        final MobilyticsMetricsTimer createTimerEvent = this.metricsHelper.createTimerEvent("getAssetURLService.getAssetMappingsFromCloudFront.latency", GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, GetAssetURLServiceMetricsConstants.Subcomponent.GET_MAPPINGS);
        final Callback callback = new Callback() { // from class: com.amazon.alexa.assetManagementService.service.NetworkCallService.1
            @Override // okhttp3.Callback
            @SuppressLint({"VisibleForTests"})
            public void onFailure(@NotNull Call call, @NonNull IOException iOException) {
                call.cancel();
                if (ExceptionUtil.isNonActionableHTTPError(iOException)) {
                    MetricsHelper metricsHelper = NetworkCallService.this.metricsHelper;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getAssetURLService.non_actionable_http_error");
                    outline107.append(iOException.getClass().getSimpleName());
                    metricsHelper.recordOccurrenceMetricsEvent(outline107.toString(), false, GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, GetAssetURLServiceMetricsConstants.Subcomponent.MAPPING_FAILURE);
                    return;
                }
                NetworkCallService.this.metricsHelper.recordOccurrenceMetricsEvent("getAssetURLService.getAssetMappingsFromCloudFront.mapping_failure", false, GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, GetAssetURLServiceMetricsConstants.Subcomponent.MAPPING_FAILURE);
                NetworkCallService.this.metricsHelper.recordErrorMetricsEvent(GetAssetURLServiceMetricsConstants.Subcomponent.MAPPING_FAILURE, GetAssetURLServiceMetricsConstants.Subcomponent.GET_MAPPINGS, GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, GetAssetURLServiceMetricsConstants.Subcomponent.MAPPING_FAILURE, iOException);
            }

            @Override // okhttp3.Callback
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                if (response.body() != null) {
                    MetricsHelper metricsHelper = NetworkCallService.this.metricsHelper;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getAssetURLService.getAssetMappingsFromCloudFront.mapping_success");
                    outline107.append(response.code());
                    metricsHelper.recordOccurrenceMetricsEvent(outline107.toString(), true, GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, GetAssetURLServiceMetricsConstants.Subcomponent.MAPPING_SUCCESS);
                    createTimerEvent.finishTimer();
                    ((Mobilytics) NetworkCallService.this.mobilytics.mo358get()).recordTimer(createTimerEvent);
                    NetworkCallService.this.isNetworkCallMade = true;
                    NetworkCallService.this.handleNetworkResponse(response);
                }
            }
        };
        String str = "Making cloudfront call to get the asset mapping connection status " + z;
        this.taskManager = (TaskManager) ComponentRegistry.getInstance().get(TaskManager.class).get();
        this.taskManager.getExecutor(1).submit(new Runnable() { // from class: com.amazon.alexa.assetManagementService.service.-$$Lambda$NetworkCallService$4wVuKqnSAJEmgHgb7XluVPiIJ-c
            @Override // java.lang.Runnable
            public final void run() {
                NetworkCallService.this.lambda$callCloudFrontForMappings$0$NetworkCallService(createRequest, callback);
            }
        });
    }

    @VisibleForTesting
    List<AssetMapping> createCacheForNetworkResponse(JsonObject jsonObject) {
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String obj = entry.getKey().toString();
            this.assetMappingCache.setAssetMappingCache(obj, jsonObject.getAsJsonObject(obj));
        }
        return this.camCommon.convertCacheToPersistentStorageEntities();
    }

    @VisibleForTesting
    OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addNetworkInterceptor(httpLoggingInterceptor);
        okHttpClient.connectTimeout(60000L, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(60000L, TimeUnit.MILLISECONDS);
        return okHttpClient.build();
    }

    void handleNetworkResponse(Response response) {
        String str = response.headers().get("ETag");
        if (response.code() == 304) {
            return;
        }
        String str2 = null;
        if (!response.isSuccessful()) {
            return;
        }
        try {
            str2 = response.body().string();
            response.close();
        } catch (IOException e) {
            this.metricsHelper.recordErrorMetricsEvent("getAssetURLService.getAssetMappingsFromCloudFront", "getAssetURLService.getAssetMappingsFromCloudFront", GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, GetAssetURLServiceMetricsConstants.Subcomponent.MAPPING_FAILURE, e);
        }
        String str3 = this.currentEtag;
        if (str3 != null && str3.equalsIgnoreCase(str)) {
            return;
        }
        SharedPreferences.Editor edit = this.context.getSharedPreferences(GetAssetURLServiceMetricsConstants.CAM_COMPONENT_NAME, 0).edit();
        edit.putString("ETag", str);
        edit.apply();
        JsonObject parseJsonResponse = this.fallbackMappingParser.parseJsonResponse(str2);
        if (parseJsonResponse == null || !parseJsonResponse.has("mappings")) {
            return;
        }
        parseJsonResponse.toString();
        this.camCommon.saveToPersistentCache(createCacheForNetworkResponse(parseJsonResponse.getAsJsonObject("mappings")));
        this.camCommon.notifyAssetMappingsAreReady();
    }

    public /* synthetic */ void lambda$callCloudFrontForMappings$0$NetworkCallService(Request request, Callback callback) {
        getOkHttpClient().newCall(request).enqueue(callback);
    }
}
