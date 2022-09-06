package com.amazon.alexa.accessory.kota;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import android.util.Pair;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.http.HttpCall;
import com.amazon.alexa.accessory.internal.http.HttpMethod;
import com.amazon.alexa.accessory.internal.http.HttpRequest;
import com.amazon.alexa.accessory.internal.http.JsonHttpBody;
import com.amazon.alexa.accessory.internal.http.SslSocketFactoryProvider;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.ByteArraySource;
import com.amazon.alexa.accessory.kota.CompanionDeviceId;
import com.amazon.alexa.accessory.kota.CompanionInventoryRequest;
import com.amazon.alexa.accessory.kota.CompanionInventoryResponse;
import com.amazon.alexa.accessory.kota.FirmwareSupplier;
import com.amazon.alexa.accessory.kota.Inventory;
import com.amazon.alexa.accessory.kota.InventoryUpdate;
import com.amazon.alexa.accessory.kota.PackageCandidateIdentifier;
import com.amazon.alexa.accessory.kota.UpdateRequest;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.HttpRequestConstants;
import com.amazon.alexa.accessory.persistence.FileBackedJsonRxMapStore;
import com.amazon.alexa.accessory.persistence.RxMapStore;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareContract;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class DefaultKotaDownloader implements KotaDownloader {
    private static final String TAG = "DefaultKotaDownloader:";
    private static final long THIRTY_DAYS_SECONDS = 2592000;
    private static final String callContractStorePath = "accessoriesKota/callAfter.json";
    private final RxMapStore<String, CallAfterDevice> callContractStore;
    private final Context context;
    private final FirmwareSupplier firmwareSupplier;
    private final SslSocketFactoryProvider sslSocketFactoryProvider;
    private final UserSupplier userSupplier;

    public DefaultKotaDownloader(Context context, FirmwareSupplier firmwareSupplier, UserSupplier userSupplier, SslSocketFactoryProvider sslSocketFactoryProvider) {
        this(context, firmwareSupplier, userSupplier, sslSocketFactoryProvider, new FileBackedJsonRxMapStore(new File(context.getFilesDir(), callContractStorePath), CallAfterDevice.FACTORY, "callAfterStore", "directedId", "callAfterDevice"));
    }

    private Single<CompanionInventoryResponse> getInventoryUpdateCloud(final UpdateRequest updateRequest, final User user) {
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$DefaultKotaDownloader$nMyIbQonUVbgDBPPqvWtGHAoFI4
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return DefaultKotaDownloader.this.lambda$getInventoryUpdateCloud$13$DefaultKotaDownloader(updateRequest, user);
            }
        });
    }

    private static Pair<String, String> keyValue(String str) throws IOException {
        String[] split = str.split(Config.Compare.EQUAL_TO);
        if (split.length == 2) {
            return Pair.create(split[0], split[1]);
        }
        throw new IOException("Could not parse UpdateRequest");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FirmwareContract.Package lambda$null$0(FirmwareSupplier.StreamablePackage streamablePackage, UpdateRequest updateRequest, HttpCall.HttpResult httpResult) throws Throwable {
        if (httpResult.statuseCode == 200 && httpResult.response.length != 0) {
            Logger.d("%s Successfully downloaded package: %s", TAG, httpResult);
            IOUtils.transfer(new ByteArraySource(httpResult.response), streamablePackage.sink());
            KotaMetricsHelper.recordFirmwareDownloadSuccess(true, updateRequest.getDeviceType());
            return streamablePackage.complete();
        }
        throw new IOException("DefaultKotaDownloader: Received invalid response for package download: " + httpResult);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$12(UpdateRequest updateRequest, Throwable th) throws Throwable {
        KotaMetricsHelper.recordUpdateCheckSuccess(false, updateRequest.getDeviceType());
        KotaMetricsHelper.recordUpdateCheckError(th, updateRequest.getDeviceType());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MaybeSource lambda$null$5(CompanionInventoryResponse companionInventoryResponse) throws Throwable {
        List<List<InventoryUpdate>> availableUpdates = companionInventoryResponse.getAvailableUpdates();
        if (!availableUpdates.isEmpty() && !availableUpdates.get(0).isEmpty()) {
            Logger.d("%s Inventory Update: Found available update for update request.", TAG);
            return Maybe.just(availableUpdates.get(0).get(0));
        }
        Logger.d("%s Inventory Update: No update available for update request.", TAG);
        return Maybe.empty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ HttpCall.HttpResult lambda$null$8(HttpCall.HttpResult httpResult) throws Throwable {
        if (httpResult.statuseCode == 200) {
            return httpResult;
        }
        throw new IOException("Response for check inventory update was invalid " + httpResult);
    }

    static /* synthetic */ CompanionInventoryResponse lambda$null$9(CompanionInventoryResponse companionInventoryResponse, CallAfterDevice callAfterDevice) throws Throwable {
        return companionInventoryResponse;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$recordUpdateCheckOccurred$15(String str, String str2, Throwable th) throws Throwable {
        Logger.d("%s ERROR: Failed to store callAfterDevice for dsn %s and directedId %s", TAG, str, str2);
        Logger.e("%s Failed to store callAfterDevice", TAG);
    }

    @SuppressLint({"CheckResult"})
    private Single<CallAfterDevice> recordUpdateCheckOccurred(final String str, int i, long j, final String str2, CompanionInventoryResponse companionInventoryResponse) {
        return this.callContractStore.put(str2, new CallAfterDevice(str, i, System.currentTimeMillis() + (j * 1000), companionInventoryResponse)).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$DefaultKotaDownloader$OibnGVRAzUXyGnoVNVogGeeq5HU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Logger.d("%s Stored callAfterDevice %s for directedId %s", DefaultKotaDownloader.TAG, (CallAfterDevice) obj, str2);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$DefaultKotaDownloader$1QkQltZEfTvNy2cfceuIRNoHpdY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultKotaDownloader.lambda$recordUpdateCheckOccurred$15(str, str2, (Throwable) obj);
            }
        });
    }

    private void scheduleCheckForUpdateJob(UpdateRequest updateRequest, InventoryUpdate inventoryUpdate) throws JSONException {
        boolean z;
        Preconditions.notNull(updateRequest, KotaJobSchedulerService.UPDATE_REQUEST_KEY);
        Preconditions.notNull(inventoryUpdate, KotaJobSchedulerService.INVENTORY_UPDATE_KEY);
        Logger.d("%s Attempting to schedule check for update job", TAG);
        JobScheduler jobScheduler = (JobScheduler) this.context.getSystemService("jobscheduler");
        Iterator<JobInfo> it2 = jobScheduler.getAllPendingJobs().iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            }
            JobInfo next = it2.next();
            if (next.getExtras() != null) {
                try {
                    String string = next.getExtras().getString(KotaJobSchedulerService.UPDATE_REQUEST_KEY);
                    String string2 = next.getExtras().getString(KotaJobSchedulerService.INVENTORY_UPDATE_KEY);
                    if (string != null && string2 != null) {
                        UpdateRequest mo1239create = new UpdateRequest.JsonBuilder().mo1239create(new JSONObject(string));
                        InventoryUpdate mo1239create2 = new InventoryUpdate.JsonFactory().mo1239create(new JSONObject(string2));
                        if (updateRequest.equals(mo1239create) && inventoryUpdate.equals(mo1239create2)) {
                            Logger.d("%s Job is already scheduled to download this file, cancelling!", TAG);
                            z = true;
                            break;
                        }
                    } else {
                        throw new JSONException("Unable to extract strings from job info to generate JSON from Strings");
                        break;
                    }
                } catch (JSONException e) {
                    Logger.e("%s Unable to parse job for scheduleCheckForUpdateJob... continuing", TAG, e);
                }
            }
        }
        if (z) {
            KotaMetricsHelper.recordJobScheduledSuccess(false, updateRequest.getDeviceType());
            return;
        }
        try {
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString(KotaJobSchedulerService.UPDATE_REQUEST_KEY, updateRequest.toJsonObject().toString());
            persistableBundle.putString(KotaJobSchedulerService.INVENTORY_UPDATE_KEY, inventoryUpdate.toJsonObject().toString());
            JobInfo.Builder requiredNetworkType = new JobInfo.Builder(updateRequest.hashCode(), new ComponentName(this.context, KotaJobSchedulerService.class)).setExtras(persistableBundle).setRequiredNetworkType(2);
            Logger.d(String.format(Locale.US, "%s Scheduled download firmware job for firmware %s - %d", TAG, inventoryUpdate.getSoftwareComponent(), Integer.valueOf(inventoryUpdate.getTargetSoftwareComponentVersionCode())));
            jobScheduler.schedule(requiredNetworkType.build());
            KotaMetricsHelper.recordJobScheduledSuccess(true, updateRequest.getDeviceType());
        } catch (JSONException e2) {
            KotaMetricsHelper.recordJobScheduledSuccess(false, updateRequest.getDeviceType());
            throw e2;
        }
    }

    @Override // com.amazon.alexa.accessory.kota.KotaDownloader
    public Completable downloadPackage(final UpdateRequest updateRequest, final InventoryUpdate inventoryUpdate, final boolean z) {
        final PackageIdentifier packageIdentifier = new PackageIdentifier(new PackageCandidateIdentifier.Builder().deviceType(updateRequest.getDeviceType()).deviceSerialNumber(updateRequest.getDeviceSerialNumber()).componentId(updateRequest.getComponentId()).currentVersion(Integer.valueOf(updateRequest.getComponentVersion())).build(), Integer.valueOf(inventoryUpdate.getTargetSoftwareComponentVersionCode()));
        return this.firmwareSupplier.getPackage(packageIdentifier).isEmpty().flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$DefaultKotaDownloader$ZGBr4GUPwCCtGf6_ao1lIDF8yJw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultKotaDownloader.this.lambda$downloadPackage$3$DefaultKotaDownloader(updateRequest, inventoryUpdate, z, packageIdentifier, (Boolean) obj);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.kota.KotaDownloader
    public Single<UpdateRequest> generateUpdateRequest(Device.DeviceInformation deviceInformation, Firmware.FirmwareInformation firmwareInformation) {
        return Single.just(new UpdateRequest.Builder().buildDimension("ro.build.lab126.sign.type=release").componentId(UpdateRequest.generateComponentId(firmwareInformation.getName(), firmwareInformation.getLocale())).componentVersion(firmwareInformation.getVersion()).deviceSerialNumber(deviceInformation.getSerialNumber()).deviceType(deviceInformation.getDeviceType()).build());
    }

    @Override // com.amazon.alexa.accessory.kota.KotaDownloader
    public Maybe<InventoryUpdate> getAvailableInventoryUpdate(final UpdateRequest updateRequest, final boolean z) {
        return this.userSupplier.queryUser().firstOrError().flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$DefaultKotaDownloader$Nyix15SVXUq6Jm0PVXRmppJQfl8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultKotaDownloader.this.lambda$getAvailableInventoryUpdate$6$DefaultKotaDownloader(updateRequest, z, (User) obj);
            }
        });
    }

    public /* synthetic */ CompletableSource lambda$downloadPackage$3$DefaultKotaDownloader(final UpdateRequest updateRequest, final InventoryUpdate inventoryUpdate, boolean z, PackageIdentifier packageIdentifier, Boolean bool) throws Throwable {
        if (!bool.booleanValue()) {
            Logger.d("%s Package is already on disk, our job here is done.", TAG);
            return Completable.complete();
        }
        Logger.d("%s Requested firmware package not on disk, let's go and get it... %s-%d-%d %s", TAG, updateRequest.getComponentId(), Integer.valueOf(updateRequest.getComponentVersion()), Integer.valueOf(inventoryUpdate.getTargetSoftwareComponentVersionCode()), inventoryUpdate.getUrl());
        if (!z) {
            Logger.d("%s Scheduling job to download on unmetered network.", TAG);
            scheduleCheckForUpdateJob(updateRequest, inventoryUpdate);
            return Completable.complete();
        }
        return this.firmwareSupplier.getStreamablePackage(packageIdentifier).flatMap(new Function() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$DefaultKotaDownloader$kRgVeGci4-LfMGZV5WzYJoXtXWs
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultKotaDownloader.this.lambda$null$1$DefaultKotaDownloader(inventoryUpdate, updateRequest, (FirmwareSupplier.StreamablePackage) obj);
            }
        }).ignoreElement().doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$DefaultKotaDownloader$mHkquVpyQQjwJy5KglXqRvA9oaY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                KotaMetricsHelper.recordFirmwareDownloadSuccess(false, UpdateRequest.this.getDeviceType());
            }
        });
    }

    public /* synthetic */ MaybeSource lambda$getAvailableInventoryUpdate$6$DefaultKotaDownloader(final UpdateRequest updateRequest, final boolean z, final User user) throws Throwable {
        if (!user.equals(User.ABSENT)) {
            KotaMetricsHelper.recordUserRetrievedForUpdateCheck(true, updateRequest.getDeviceType());
            return this.callContractStore.get(user.getDirectedCustomerId()).flatMap(new Function() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$DefaultKotaDownloader$xRiGYiM8p5VckNnm7SUGduOzBXg
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return DefaultKotaDownloader.this.lambda$null$4$DefaultKotaDownloader(updateRequest, z, user, (Set) obj);
                }
            }).flatMapMaybe($$Lambda$DefaultKotaDownloader$nPnE_BaC_xuheShJEyr6SpnH3I.INSTANCE);
        }
        KotaMetricsHelper.recordUserRetrievedForUpdateCheck(false, updateRequest.getDeviceType());
        throw new IllegalStateException("DefaultKotaDownloader: No user available to get access token");
    }

    public /* synthetic */ SingleSource lambda$getInventoryUpdateCloud$13$DefaultKotaDownloader(final UpdateRequest updateRequest, final User user) throws Throwable {
        Single<HttpCall.HttpResult> executeSingle;
        Logger.d("%s Checking for update to cloud %s (%d)", TAG, updateRequest.getComponentId(), Integer.valueOf(updateRequest.getComponentVersion()));
        Pair<String, String> keyValue = keyValue(updateRequest.getBuildDimension());
        HttpRequest build = HttpRequest.createBuilder().method(HttpMethod.POST).url(updateRequest.getUrl()).header("Content-Type", HttpRequestConstants.JSON_CONTENT_TYPE_VALUE).header("Content-Encoding", "amz-1.0").header("x-amz-target", "com.amazon.devicesoftwaretracking.DeviceSoftwareTrackingService.getUpdatesForCompanion").header("x-amz-access-token", user.getAccessToken()).body(new JsonHttpBody(new CompanionInventoryRequest.Builder().buildDimension((String) keyValue.first, keyValue.second).softwareComponentType("OS").companionDeviceId(new CompanionDeviceId.Builder().deviceSerialNumber(updateRequest.getDeviceSerialNumber()).deviceType(updateRequest.getDeviceType()).build()).inventory(new Inventory.Builder().softwareComponentId(updateRequest.getComponentId()).softwareComponentVersionCode(updateRequest.getComponentVersion()).build()).build())).build();
        if (user.getAccessToken() == null) {
            executeSingle = Single.error(new IOException("No access token avaialble for user " + user));
            KotaMetricsHelper.recordPreparedCheckForUpdateRequest(false, updateRequest.getDeviceType());
        } else {
            executeSingle = build.newCall().executeSingle();
            KotaMetricsHelper.recordPreparedCheckForUpdateRequest(true, updateRequest.getDeviceType());
        }
        return executeSingle.onErrorResumeNext($$Lambda$DefaultKotaDownloader$EX7AyoT7UsADHv_1OZilU6NVzs.INSTANCE).map($$Lambda$DefaultKotaDownloader$QEgt8qBAt1i70jfhNy1t_7iptHU.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$DefaultKotaDownloader$W5jb4_xolR-xiQSPxHy8qtXeoLY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultKotaDownloader.this.lambda$null$10$DefaultKotaDownloader(updateRequest, user, (HttpCall.HttpResult) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$DefaultKotaDownloader$uFf-FhQqkc50Cevn1rKj53qmNDw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                CompanionInventoryResponse companionInventoryResponse = (CompanionInventoryResponse) obj;
                KotaMetricsHelper.recordUpdateCheckSuccess(true, UpdateRequest.this.getDeviceType());
                return companionInventoryResponse;
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$DefaultKotaDownloader$XcL7_zgZJirAlDyKMzGokQ75x8M
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultKotaDownloader.lambda$null$12(UpdateRequest.this, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$null$1$DefaultKotaDownloader(InventoryUpdate inventoryUpdate, final UpdateRequest updateRequest, final FirmwareSupplier.StreamablePackage streamablePackage) throws Throwable {
        HttpRequest.Builder createBuilder = HttpRequest.createBuilder();
        int i = Build.VERSION.SDK_INT;
        return createBuilder.url(inventoryUpdate.getUrl()).build().newCall().executeSingle().map(new Function() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$DefaultKotaDownloader$ErI3dM8dqZUKn9crbjSXsr13BCo
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultKotaDownloader.lambda$null$0(FirmwareSupplier.StreamablePackage.this, updateRequest, (HttpCall.HttpResult) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$null$10$DefaultKotaDownloader(UpdateRequest updateRequest, User user, HttpCall.HttpResult httpResult) throws Throwable {
        long j;
        Logger.d("%s Got httpResult for available inventory update: %s", TAG, httpResult);
        try {
            final CompanionInventoryResponse mo1239create = new CompanionInventoryResponse.JsonFactory().mo1239create(new JSONObject(new String(httpResult.response)));
            long callAfter = mo1239create.getCallAfter();
            if (callAfter > THIRTY_DAYS_SECONDS) {
                Logger.e("%s callAfter returned %d seconds from cloud, which is greater than 30 days. Using 30 days default for callAfter.", TAG, Long.valueOf(mo1239create.getCallAfter()));
                j = 2592000;
            } else {
                j = callAfter;
            }
            return recordUpdateCheckOccurred(updateRequest.getDeviceSerialNumber(), updateRequest.getComponentVersion(), j, user.getDirectedCustomerId(), mo1239create).map(new Function() { // from class: com.amazon.alexa.accessory.kota.-$$Lambda$DefaultKotaDownloader$8rWljTSbYnEjLGb60KlNzRmOaKk
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    CallAfterDevice callAfterDevice = (CallAfterDevice) obj;
                    return CompanionInventoryResponse.this;
                }
            });
        } catch (JSONException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to read JSON during getAvailableInventoryUpdate! ");
            outline107.append(e.getLocalizedMessage());
            throw new IOException(outline107.toString());
        }
    }

    public /* synthetic */ SingleSource lambda$null$4$DefaultKotaDownloader(UpdateRequest updateRequest, boolean z, User user, Set set) throws Throwable {
        Logger.d("%s Checking for update %s, force=%b", TAG, updateRequest, Boolean.valueOf(z));
        long currentTimeMillis = System.currentTimeMillis();
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            CallAfterDevice callAfterDevice = (CallAfterDevice) it2.next();
            if (!z && updateRequest.getDeviceSerialNumber().equals(callAfterDevice.dsn) && updateRequest.getComponentVersion() == callAfterDevice.firmwareVersion) {
                long j = callAfterDevice.callAfter;
                if (currentTimeMillis < j) {
                    Logger.d("%s callAfter has not yet elapsed for update check, returning cached response %s, can call again in %d milliseconds", TAG, callAfterDevice.inventoryResponse, Long.valueOf(j - currentTimeMillis));
                    return Single.just(callAfterDevice.inventoryResponse);
                }
            }
        }
        return getInventoryUpdateCloud(updateRequest, user);
    }

    @VisibleForTesting
    DefaultKotaDownloader(Context context, FirmwareSupplier firmwareSupplier, UserSupplier userSupplier, SslSocketFactoryProvider sslSocketFactoryProvider, RxMapStore<String, CallAfterDevice> rxMapStore) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(firmwareSupplier, "firmwareSupplier");
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(sslSocketFactoryProvider, "sslSocketFactoryProvider");
        Preconditions.notNull(rxMapStore, "mapStore");
        this.context = context;
        this.firmwareSupplier = firmwareSupplier;
        this.userSupplier = userSupplier;
        this.sslSocketFactoryProvider = sslSocketFactoryProvider;
        this.callContractStore = rxMapStore;
        rxMapStore.cleanCache(userSupplier);
    }
}
