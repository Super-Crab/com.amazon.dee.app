package com.amazon.alexa.accessory.davs;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.davs.ArtifactDownloadManager;
import com.amazon.alexa.accessory.davs.DeviceArtifactContract;
import com.amazon.alexa.accessory.davs.i18n.DavsI18nConfigData;
import com.amazon.alexa.accessory.davs.i18n.DavsI18nConfigStore;
import com.amazon.alexa.accessory.davs.i18n.DiskDavsI18nConfigStore;
import com.amazon.alexa.accessory.internal.EndpointProvider;
import com.amazon.alexa.accessory.internal.http.HttpCall;
import com.amazon.alexa.accessory.internal.http.HttpRequest;
import com.amazon.alexa.accessory.internal.http.HttpStatusUnsuccessfulException;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.MetricsUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.ByteArraySource;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccount;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class DefaultDavsClient implements DavsClient {
    private static final String DAVS_URL_SUFFIX = "/v2/deviceArtifacts/?artifactFilter=%s&delegatorDevice=%s";
    private static final long ONE_DAYS_MS = 86400000;
    private static final String TAG = "DefaultDavsClient:";
    private final ArtifactDownloadManager artifactDownloadManager;
    private final Context context;
    private final DavsI18nConfigStore davsI18nConfigStore;
    private final DeviceAccountSupplier deviceAccountSupplier;
    private final EndpointProvider endpointProvider;
    private final UserSupplier userSupplier;

    /* loaded from: classes.dex */
    public final class DelegatorDevice implements JsonObjectSerializable {
        private static final String DEVICE_ACCOUNT_ID_JSON_KEY = "deviceAccountId";
        private static final String DEVICE_TYPE_JSON_KEY = "deviceType";
        private final String deviceAccountId;
        private final String deviceType;

        DelegatorDevice(String str, String str2) {
            this.deviceAccountId = str;
            this.deviceType = str2;
        }

        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
        public JSONObject toJsonObject() throws JSONException {
            return new JSONObject().put(DEVICE_ACCOUNT_ID_JSON_KEY, this.deviceAccountId).put("deviceType", this.deviceType);
        }
    }

    public DefaultDavsClient(Context context, UserSupplier userSupplier, EndpointProvider endpointProvider, DeviceAccountSupplier deviceAccountSupplier) {
        this(context, userSupplier, endpointProvider, new DefaultArtifactDownloadManager(context), new DiskDavsI18nConfigStore(context), deviceAccountSupplier);
    }

    private Single<DavsI18nConfig> downloadI18nConfig(String str) {
        Preconditions.notNull(str, "downloadUrl");
        return HttpRequest.createBuilder().url(str).build().newCall().executeSingle().map($$Lambda$DefaultDavsClient$cu3hG_E2KjuqzvqoZn5HnC6yGIE.INSTANCE).doOnError($$Lambda$DefaultDavsClient$YZaEWOwXhpDJVEYUnjNXnO65cw.INSTANCE);
    }

    private String getDAVSUrl(DelegatorDevice delegatorDevice, DeviceArtifactsRequest deviceArtifactsRequest) throws JSONException {
        String encodeToString = Base64.encodeToString(deviceArtifactsRequest.toJsonObject().toString().getBytes(StandardCharsets.UTF_8), 10);
        String encodeToString2 = Base64.encodeToString(delegatorDevice.toJsonObject().toString().getBytes(StandardCharsets.UTF_8), 10);
        return this.endpointProvider.getAlexaApiEndpoint() + String.format(DAVS_URL_SUFFIX, encodeToString, encodeToString2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getDavsManifest */
    public Single<DeviceArtifactsResponse> lambda$fetchDavsManifest$1$DefaultDavsClient(User user, final DeviceArtifactsRequest deviceArtifactsRequest, final String str, String str2) {
        final String accessToken = user.getAccessToken();
        if (TextUtils.isEmpty(accessToken)) {
            recordMetric(MetricsConstants.DAVS.GET_DEVICE_ARTIFACT_API_MISSING_TOKEN, str);
            return Single.error(new IllegalArgumentException("No access token available for DAVS request"));
        }
        return this.deviceAccountSupplier.awaitDeviceAccount(user.getDirectedCustomerId(), str, str2).toSingle().doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultDavsClient$JB0k8VaDk4-Baqre6vJ9nojl8I0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultDavsClient.lambda$getDavsManifest$11(str, (Throwable) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultDavsClient$-T82AEZLMs-LdnZyB0sF2D_u3Ho
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultDavsClient.this.lambda$getDavsManifest$12$DefaultDavsClient(str, deviceArtifactsRequest, (DeviceAccount) obj);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultDavsClient$pxncT08BtNhm2_heBC87EdEjToM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                String str3 = accessToken;
                String str4 = str;
                return Logger.d("%s Get device artifacts with url: %s", DefaultDavsClient.TAG, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DavsI18nConfig lambda$downloadI18nConfig$16(HttpCall.HttpResult httpResult) throws Throwable {
        int i = httpResult.statuseCode;
        if (i >= 200 && i < 300) {
            String str = new String(httpResult.response);
            Logger.d("%s Received response: %s", TAG, str);
            try {
                DavsI18nConfig mo1239create = DavsI18nConfig.FACTORY.mo1239create(new JSONObject(str));
                recordMetricOccurred(MetricsConstants.DAVS.GET_I18N_CONFIG_DOWNLOAD_SUCCESS, MetricsConstants.DAVS.I18N_CONFIG, true);
                return mo1239create;
            } catch (JSONException e) {
                Logger.e("%s Error parsing response for GetDeviceArtifacts", e, TAG);
                recordMetricOccurred(MetricsConstants.DAVS.GET_I18N_CONFIG_DOWNLOAD_PARSING_ERROR, MetricsConstants.DAVS.I18N_CONFIG, true);
                throw e;
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetI18nConfigDownloadErrorCode:");
        outline107.append(httpResult.statuseCode);
        recordMetric(outline107.toString(), MetricsConstants.DAVS.I18N_CONFIG);
        Logger.e("%s Received error for artifact metadata with response: %s", TAG, httpResult.toString());
        throw new HttpStatusUnsuccessfulException(TAG, httpResult.statuseCode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$downloadI18nConfig$17(Throwable th) throws Throwable {
        recordMetricForThrowable(th, MetricsConstants.DAVS.DOWNLOAD_I18N_FAILURE_PREFIX, MetricsConstants.DAVS.I18N_CONFIG);
        recordMetricOccurred(MetricsConstants.DAVS.GET_I18N_CONFIG_DOWNLOAD_SUCCESS, MetricsConstants.DAVS.I18N_CONFIG, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$fetchDavsManifest$0(User user) throws Throwable {
        return user != User.ABSENT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getDavsI18nConfig$2(User user) throws Throwable {
        return user != User.ABSENT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getDavsManifest$11(String str, Throwable th) throws Throwable {
        Logger.e("%s Error fetching device account Id", th, TAG);
        recordMetric(MetricsConstants.DAVS.GET_DEVICE_ARTIFACT_API_DEVICE_ACCOUNT_ERROR, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DeviceArtifactsResponse lambda$null$13(String str, HttpCall.HttpResult httpResult) throws Throwable {
        int i = httpResult.statuseCode;
        if (i >= 200 && i < 300) {
            String str2 = new String(httpResult.response);
            Logger.d("%s Received response: %s", TAG, str2);
            try {
                DeviceArtifactsResponse mo1239create = DeviceArtifactsResponse.FACTORY.mo1239create(new JSONObject(str2));
                recordMetricOccurred(MetricsConstants.DAVS.GET_DEVICE_ARTIFACT_API_SUCCESS, str, true);
                return mo1239create;
            } catch (JSONException e) {
                Logger.e("%s Error parsing response for GetDeviceArtifacts", e, TAG);
                recordMetricOccurred(MetricsConstants.DAVS.GET_DEVICE_ARTIFACT_API_PARSING_ERROR, str, true);
                throw e;
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetDeviceArtifactApiErrorCode:");
        outline107.append(httpResult.statuseCode);
        recordMetric(outline107.toString(), str);
        Logger.e("%s Received error for artifact metadata with response: %s", TAG, httpResult.toString());
        throw new HttpStatusUnsuccessfulException(TAG, httpResult.statuseCode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$14(String str, Throwable th) throws Throwable {
        recordMetricForThrowable(th, MetricsConstants.DAVS.GET_DEVICE_ARTIFACT_API_FAILURE_PREFIX, str);
        recordMetricOccurred(MetricsConstants.DAVS.GET_DEVICE_ARTIFACT_API_SUCCESS, str, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DeviceArtifactContract.ArtifactPackage lambda$null$7(String str, ArtifactDownloadManager.StreamableArtifact streamableArtifact, HttpCall.HttpResult httpResult) throws Throwable {
        int i = httpResult.statuseCode;
        if (i >= 200 && i < 300) {
            if (httpResult.response.length != 0) {
                Logger.d("%s Successfully downloaded artifact: %s", TAG, httpResult);
                IOUtils.transfer(new ByteArraySource(httpResult.response), streamableArtifact.getSink());
                DeviceArtifactContract.ArtifactPackage complete = streamableArtifact.complete();
                recordMetricOccurred(MetricsConstants.DAVS.ARTIFACT_DOWNLOAD_SUCCESS, str, true);
                return complete;
            }
            recordMetric(MetricsConstants.DAVS.ARTIFACT_DOWNLOAD_RESPONSE_EMPTY, str);
            Logger.e("%s Artifact response is empty", TAG);
            throw new IOException("DefaultDavsClient:Artifact response is empty");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ArtifactDownloadErrorCode:");
        outline107.append(httpResult.statuseCode);
        recordMetric(outline107.toString(), str);
        Logger.e("%s Received error for artifact download with response: %s", TAG, httpResult.toString());
        throw new HttpStatusUnsuccessfulException(TAG, httpResult.statuseCode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$8(String str, Throwable th) throws Throwable {
        recordMetricForThrowable(th, MetricsConstants.DAVS.DOWNLOAD_ARTIFACT_FAILURE_PREFIX, str);
        recordMetricOccurred(MetricsConstants.DAVS.ARTIFACT_DOWNLOAD_SUCCESS, str, false);
    }

    private static void recordMetric(String str, String str2) {
        recordMetricOccurred(str, str2, true);
    }

    private static void recordMetricForThrowable(Throwable th, String str, String str2) {
        GeneratedOutlineSupport1.outline171(MetricsUtils.createMetricNameFromThrowable(str, th), str2, true, null);
    }

    private static void recordMetricOccurred(String str, String str2, boolean z) {
        GeneratedOutlineSupport1.outline171(str, str2, z, null);
    }

    @Override // com.amazon.alexa.accessory.davs.DavsClient
    public Maybe<DeviceArtifactContract.ArtifactPackage> downloadArtifact(final String str, final DeviceArtifactsResponse deviceArtifactsResponse, final String str2) {
        Preconditions.notNull(str, "artifactName");
        Preconditions.notNull(deviceArtifactsResponse, "deviceArtifactsResponse");
        final DeviceArtifactContract.ArtifactPackageIdentifier build = new DeviceArtifactContract.ArtifactPackageIdentifier.Builder().artifactName(str).artifactIdentifier(new DeviceArtifactContract.ArtifactIdentifier.Builder().artifactType(deviceArtifactsResponse.getArtifactType()).artifactKey(deviceArtifactsResponse.getArtifactKey()).artifactIdentifier(deviceArtifactsResponse.getArtifactIdentifier()).build()).build();
        return this.artifactDownloadManager.getArtifactPackage(deviceArtifactsResponse, build).switchIfEmpty(Maybe.defer(new Supplier() { // from class: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultDavsClient$GbkaACUzAHwt71PPdFaIl4PlZw4
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return DefaultDavsClient.this.lambda$downloadArtifact$10$DefaultDavsClient(deviceArtifactsResponse, build, str, str2);
            }
        }).subscribeOn(Schedulers.io()));
    }

    @Override // com.amazon.alexa.accessory.davs.DavsClient
    public Single<DeviceArtifactsResponse> fetchDavsManifest(final DeviceArtifactsRequest deviceArtifactsRequest, final String str, final String str2) {
        Preconditions.notNull(deviceArtifactsRequest, "request");
        Preconditions.notEmpty(str, "deviceType");
        Preconditions.notEmpty(str2, "dsn");
        return this.userSupplier.queryUser().distinctUntilChanged().filter($$Lambda$DefaultDavsClient$eK9Z1XzbAJbR12H3_gxtwa4TxTs.INSTANCE).firstOrError().flatMap(new Function() { // from class: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultDavsClient$OP9IrtslfXMSuBLfaqAHKcpa9bI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultDavsClient.this.lambda$fetchDavsManifest$1$DefaultDavsClient(deviceArtifactsRequest, str, str2, (User) obj);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.davs.DavsClient
    public Single<DavsI18nConfig> getDavsI18nConfig(final DeviceArtifactsRequest deviceArtifactsRequest, final String str, final String str2) {
        Preconditions.notNull(deviceArtifactsRequest, "request");
        return this.userSupplier.queryUser().distinctUntilChanged().filter($$Lambda$DefaultDavsClient$cTWKnWWB4jpUmm_86_T1VPIc0Qs.INSTANCE).firstOrError().flatMap(new Function() { // from class: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultDavsClient$bsR6z9OCxmXVQBluIwu4h6XjS4o
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultDavsClient.this.lambda$getDavsI18nConfig$6$DefaultDavsClient(deviceArtifactsRequest, str, str2, (User) obj);
            }
        });
    }

    public /* synthetic */ MaybeSource lambda$downloadArtifact$10$DefaultDavsClient(final DeviceArtifactsResponse deviceArtifactsResponse, DeviceArtifactContract.ArtifactPackageIdentifier artifactPackageIdentifier, final String str, final String str2) throws Throwable {
        return this.artifactDownloadManager.getStreamableArtifact(deviceArtifactsResponse, artifactPackageIdentifier).flatMap(new Function() { // from class: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultDavsClient$WGuwhT3Wj_Ovy1KbBdGX9002RDE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                String str3 = str;
                DeviceArtifactsResponse deviceArtifactsResponse2 = deviceArtifactsResponse;
                String str4 = str2;
                ArtifactDownloadManager.StreamableArtifact streamableArtifact = (ArtifactDownloadManager.StreamableArtifact) obj;
                return Logger.d("%s Artifact does not exist on disk. Starting artifact download: %s", DefaultDavsClient.TAG, str3);
            }
        }).toMaybe();
    }

    public /* synthetic */ SingleSource lambda$getDavsI18nConfig$6$DefaultDavsClient(final DeviceArtifactsRequest deviceArtifactsRequest, final String str, final String str2, final User user) throws Throwable {
        return this.davsI18nConfigStore.getDavsI18nConfig(user.getDirectedCustomerId()).flatMap(new Function() { // from class: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultDavsClient$7krJ4Fkf8YlqbAS8e7ckECg4pVE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultDavsClient.this.lambda$null$5$DefaultDavsClient(deviceArtifactsRequest, user, str, str2, (Set) obj);
            }
        });
    }

    public /* synthetic */ String lambda$getDavsManifest$12$DefaultDavsClient(String str, DeviceArtifactsRequest deviceArtifactsRequest, DeviceAccount deviceAccount) throws Throwable {
        return getDAVSUrl(new DelegatorDevice(deviceAccount.getDeviceAccountResponse().getDeviceAccountId(), str), deviceArtifactsRequest);
    }

    public /* synthetic */ SingleSource lambda$null$3$DefaultDavsClient(DeviceArtifactsResponse deviceArtifactsResponse) throws Throwable {
        return downloadI18nConfig(deviceArtifactsResponse.getDownloadUrl());
    }

    public /* synthetic */ DavsI18nConfig lambda$null$4$DefaultDavsClient(User user, DeviceArtifactsRequest deviceArtifactsRequest, DavsI18nConfig davsI18nConfig) throws Throwable {
        this.davsI18nConfigStore.putDavsI18nConfig(user.getDirectedCustomerId(), new DavsI18nConfigData(deviceArtifactsRequest, davsI18nConfig, System.currentTimeMillis()));
        return davsI18nConfig;
    }

    public /* synthetic */ SingleSource lambda$null$5$DefaultDavsClient(final DeviceArtifactsRequest deviceArtifactsRequest, final User user, String str, String str2, Set set) throws Throwable {
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            DavsI18nConfigData davsI18nConfigData = (DavsI18nConfigData) it2.next();
            if (davsI18nConfigData.getDeviceArtifactsRequest().equals(deviceArtifactsRequest) && System.currentTimeMillis() < davsI18nConfigData.getTimeLastUpdated() + 86400000) {
                return Single.just(davsI18nConfigData.getDavsi18nConfig());
            }
        }
        Logger.d("%s I18N config does not exist. Fetching from server", TAG);
        return lambda$fetchDavsManifest$1$DefaultDavsClient(user, deviceArtifactsRequest, str, str2).flatMap(new Function() { // from class: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultDavsClient$PrnQ4gYUehN1fyUIDu5WOZ1srcg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultDavsClient.this.lambda$null$3$DefaultDavsClient((DeviceArtifactsResponse) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultDavsClient$Ccm76XZ_dTFEM6yqdOFcwhg7Oks
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultDavsClient.this.lambda$null$4$DefaultDavsClient(user, deviceArtifactsRequest, (DavsI18nConfig) obj);
            }
        });
    }

    @VisibleForTesting
    DefaultDavsClient(Context context, UserSupplier userSupplier, EndpointProvider endpointProvider, ArtifactDownloadManager artifactDownloadManager, DavsI18nConfigStore davsI18nConfigStore, DeviceAccountSupplier deviceAccountSupplier) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(endpointProvider, "endpointProvider");
        Preconditions.notNull(artifactDownloadManager, "downloadManager");
        Preconditions.notNull(davsI18nConfigStore, "davsI18nConfigStore");
        Preconditions.notNull(deviceAccountSupplier, "deviceAccountSupplier");
        this.context = context;
        this.userSupplier = userSupplier;
        this.endpointProvider = endpointProvider;
        this.artifactDownloadManager = artifactDownloadManager;
        this.davsI18nConfigStore = davsI18nConfigStore;
        this.deviceAccountSupplier = deviceAccountSupplier;
    }
}
