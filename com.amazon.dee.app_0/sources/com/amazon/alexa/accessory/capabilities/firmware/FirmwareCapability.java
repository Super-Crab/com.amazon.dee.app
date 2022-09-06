package com.amazon.alexa.accessory.capabilities.firmware;

import android.annotation.SuppressLint;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.DeviceManufacturerSupplier;
import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.capabilities.firmware.FirmwareCapability;
import com.amazon.alexa.accessory.davs.DavsClient;
import com.amazon.alexa.accessory.davs.DeviceArtifactContract;
import com.amazon.alexa.accessory.davs.DeviceArtifactsRequest;
import com.amazon.alexa.accessory.davs.DeviceArtifactsResponse;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableStream;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.RedactionUtil;
import com.amazon.alexa.accessory.io.SourceInputStream;
import com.amazon.alexa.accessory.kota.FirmwareSupplier;
import com.amazon.alexa.accessory.kota.InventoryUpdate;
import com.amazon.alexa.accessory.kota.InventoryUpdateBundle;
import com.amazon.alexa.accessory.kota.KotaDownloader;
import com.amazon.alexa.accessory.kota.PackageCandidateIdentifier;
import com.amazon.alexa.accessory.kota.UpdateRequest;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.metrics.MetricsReporter;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareContract;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareMetadata;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareProducer;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareProvider;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2;
import com.amazon.alexa.accessory.streams.control.ControlMessage;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.amazon.alexa.accessory.transport.TransportDispatcher;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.json.JSONException;
/* loaded from: classes.dex */
public final class FirmwareCapability extends AccessoryCapability {
    private static final int DEFAULT_FW_SEGMENT_SIZE = 2048;
    private static final int KOTA_RETRY_COUNT = 4;
    private static final long RETRY_DELAY_DEFAULT_MILLIS = 60000;
    private static final String TAG = "FirmwareCapability:";
    private final FirmwareActionHandler actionHandler;
    private TransportDispatcher authenticationAgnosticDispatcher;
    private final CompositeDisposable compositeDisposable;
    private final DavsClient davsClient;
    private final DeviceManufacturerSupplier deviceManufacturerSupplier;
    private final DeviceRepositoryV2 deviceRepository;
    private final FeatureChecker featureChecker;
    private Disposable firmwareAvailabilityDisposable;
    private FirmwareProducer firmwareProducer;
    private final FirmwareRepositoryV2 firmwareRepository;
    private final FirmwareSupplier firmwareSupplier;
    private final KotaDownloader kotaDownloader;
    private final MetricsReporter metricsReporter;
    private final FirmwareProvider provider;
    private final long retryDelayMillis;
    private ControlStream stream;
    private final TaskManager taskManager;
    private FirmwareUpdateTask updateTask;
    private final Subject<Boolean> packageAvailabilitySubject = PublishSubject.create();
    private Set<TaskManager.Task> updateTaskSet = new HashSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class ArtifactUpdateBundle {
        final List<DeviceArtifactContract.ArtifactPackage> artifacts;
        final int deviceId;
        final String deviceType;
        final int segmentSize;

        ArtifactUpdateBundle(List<DeviceArtifactContract.ArtifactPackage> list, String str, int i, int i2) {
            this.artifacts = list;
            this.deviceType = str;
            this.deviceId = i;
            this.segmentSize = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class DevicePackage {
        public final FirmwareContract.Package aPackage;
        public final int deviceId;
        public final String deviceType;
        public final String dsn;
        public final int segmentSize;

        DevicePackage(FirmwareContract.Package r2, int i, String str, String str2, int i2) {
            Preconditions.notNull(r2, "aPackage");
            Preconditions.notNull(str, "deviceType");
            this.aPackage = r2;
            this.deviceId = i;
            this.deviceType = str;
            this.dsn = str2;
            this.segmentSize = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class FirmwareActionHandler implements FirmwareProducer.ActionHandler {
        FirmwareActionHandler() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$handleCheckInventoryUpdate$4(Producer.Result result, Throwable th) throws Throwable {
            Logger.e("%s Error handling Inventory update", th, FirmwareCapability.TAG);
            result.completeWithError(th);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$initiateFirmwareTransfer$9(Producer.Result result, Throwable th) throws Throwable {
            Logger.e("%s Error occurred. Cannot initiateFirmwareTransfer.", th, FirmwareCapability.TAG);
            result.completeWithError(th);
        }

        @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareProducer.ActionHandler
        public void handleCheckInventoryUpdate(final boolean z, final Producer.Result<Set<InventoryUpdateBundle>> result) {
            FirmwareCapability.this.compositeDisposable.add(FirmwareCapability.this.createUpdateRequests().flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$FirmwareActionHandler$sOOqpDKxcB20NT20EA0o8p3haXM
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return FirmwareCapability.FirmwareActionHandler.this.lambda$handleCheckInventoryUpdate$2$FirmwareCapability$FirmwareActionHandler(z, (Set) obj);
                }
            }).map($$Lambda$Xlmt0nL10_onlo2I_4ahXL4SvwI.INSTANCE).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$FirmwareActionHandler$2LMl0w5okJYPVWJ6YOg1RNu89rs
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    FirmwareCapability.FirmwareActionHandler.this.lambda$handleCheckInventoryUpdate$3$FirmwareCapability$FirmwareActionHandler(result, (HashSet) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$FirmwareActionHandler$R_M-LysHGCKvSMTcHCmL5i8SRAg
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    FirmwareCapability.FirmwareActionHandler.lambda$handleCheckInventoryUpdate$4(Producer.Result.this, (Throwable) obj);
                }
            }));
        }

        @Override // com.amazon.alexa.accessory.repositories.firmware.FirmwareProducer.ActionHandler
        public void initiateFirmwareTransfer(final Producer.Result<CompletableResult.Value> result) {
            if (FirmwareCapability.this.firmwareAvailabilityDisposable != null && !FirmwareCapability.this.firmwareAvailabilityDisposable.isDisposed()) {
                FirmwareCapability.this.compositeDisposable.remove(FirmwareCapability.this.firmwareAvailabilityDisposable);
            }
            FirmwareCapability firmwareCapability = FirmwareCapability.this;
            firmwareCapability.firmwareAvailabilityDisposable = firmwareCapability.firmwareRepository.queryInventoryUpdateSet(false).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$FirmwareActionHandler$Ftioe2LdLHzX6tlcdvgcQDtPag8
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return FirmwareCapability.FirmwareActionHandler.this.lambda$initiateFirmwareTransfer$6$FirmwareCapability$FirmwareActionHandler((Set) obj);
                }
            }).andThen(Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$FirmwareActionHandler$aR6nz08jgb9UzJvy5dgOL-Bo9Kk
                @Override // io.reactivex.rxjava3.functions.Supplier
                /* renamed from: get */
                public final Object mo10365get() {
                    return FirmwareCapability.FirmwareActionHandler.this.lambda$initiateFirmwareTransfer$7$FirmwareCapability$FirmwareActionHandler();
                }
            })).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$FirmwareActionHandler$4iBj8jdjGjC6W4zx9y_xajAurRw
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    FirmwareCapability.FirmwareActionHandler.this.lambda$initiateFirmwareTransfer$8$FirmwareCapability$FirmwareActionHandler(result, (FirmwareCapability.FirmwareUpdateSet) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$FirmwareActionHandler$2aUcpkNcm3jxMnYOY3AfcDUxFLA
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    FirmwareCapability.FirmwareActionHandler.lambda$initiateFirmwareTransfer$9(Producer.Result.this, (Throwable) obj);
                }
            });
            FirmwareCapability.this.compositeDisposable.add(FirmwareCapability.this.firmwareAvailabilityDisposable);
        }

        public /* synthetic */ SingleSource lambda$handleCheckInventoryUpdate$2$FirmwareCapability$FirmwareActionHandler(final boolean z, Set set) throws Throwable {
            return Observable.fromIterable(set).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$FirmwareActionHandler$uc7BsaQSjJ_V7K-Z50fMuVVbOxk
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return FirmwareCapability.FirmwareActionHandler.this.lambda$null$1$FirmwareCapability$FirmwareActionHandler(z, (UpdateRequest) obj);
                }
            }).toList();
        }

        public /* synthetic */ void lambda$handleCheckInventoryUpdate$3$FirmwareCapability$FirmwareActionHandler(Producer.Result result, HashSet hashSet) throws Throwable {
            Logger.d("%s received Inventory update in ActionHandler", FirmwareCapability.TAG);
            FirmwareCapability.this.provider.provideInventoryUpdate(hashSet);
            result.complete(hashSet);
        }

        public /* synthetic */ CompletableSource lambda$initiateFirmwareTransfer$6$FirmwareCapability$FirmwareActionHandler(Set set) throws Throwable {
            Observable fromIterable = Observable.fromIterable(set);
            final FirmwareCapability firmwareCapability = FirmwareCapability.this;
            return fromIterable.map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$FirmwareActionHandler$TwoyF-p_szYkU_2DMeVgGo9KdHk
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return FirmwareCapability.this.scheduleDownload((InventoryUpdateBundle) obj);
                }
            }).toList().flatMapCompletable($$Lambda$2vbaKQwBXPbDE2k6nSJKg20RnA.INSTANCE);
        }

        public /* synthetic */ SingleSource lambda$initiateFirmwareTransfer$7$FirmwareCapability$FirmwareActionHandler() throws Throwable {
            return FirmwareCapability.this.getFirmwareUpdatePreferenceSingle();
        }

        public /* synthetic */ void lambda$initiateFirmwareTransfer$8$FirmwareCapability$FirmwareActionHandler(Producer.Result result, FirmwareUpdateSet firmwareUpdateSet) throws Throwable {
            FirmwareCapability.this.updateFirmware(firmwareUpdateSet);
            result.complete(CompletableResult.Value.complete());
        }

        public /* synthetic */ ObservableSource lambda$null$1$FirmwareCapability$FirmwareActionHandler(boolean z, final UpdateRequest updateRequest) throws Throwable {
            return FirmwareCapability.this.checkForUpdate(updateRequest, z).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$FirmwareActionHandler$ukSq8O2vDHUlPjoqEVmG6ai7rG4
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    InventoryUpdateBundle build;
                    build = new InventoryUpdateBundle.Builder().inventoryUpdate((InventoryUpdate) obj).updateRequest(UpdateRequest.this).build();
                    return build;
                }
            }).toObservable();
        }
    }

    public FirmwareCapability(TaskManager taskManager, FirmwareRepositoryV2 firmwareRepositoryV2, FirmwareProvider firmwareProvider, FirmwareSupplier firmwareSupplier, DeviceRepositoryV2 deviceRepositoryV2, KotaDownloader kotaDownloader, MetricsReporter metricsReporter, DavsClient davsClient, FeatureChecker featureChecker, DeviceManufacturerSupplier deviceManufacturerSupplier, FirmwareProducer firmwareProducer) {
        Preconditions.notNull(firmwareRepositoryV2, "repository");
        Preconditions.notNull(firmwareProvider, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER);
        Preconditions.notNull(taskManager, "taskManager");
        Preconditions.notNull(firmwareSupplier, "firmwareSupplier");
        Preconditions.notNull(deviceRepositoryV2, "deviceRepository");
        Preconditions.notNull(kotaDownloader, "kotaDownloader");
        Preconditions.notNull(metricsReporter, "metricsReporter");
        Preconditions.notNull(davsClient, "davsClient");
        Preconditions.notNull(featureChecker, "featureChecker");
        Preconditions.notNull(deviceManufacturerSupplier, "deviceManufacturerSupplier");
        Preconditions.notNull(firmwareProducer, "firmwareProducer");
        this.deviceRepository = deviceRepositoryV2;
        this.kotaDownloader = kotaDownloader;
        this.firmwareSupplier = firmwareSupplier;
        this.taskManager = taskManager;
        this.provider = firmwareProvider;
        this.firmwareRepository = firmwareRepositoryV2;
        this.metricsReporter = metricsReporter;
        this.retryDelayMillis = 60000L;
        this.compositeDisposable = new CompositeDisposable();
        this.davsClient = davsClient;
        this.featureChecker = featureChecker;
        this.deviceManufacturerSupplier = deviceManufacturerSupplier;
        this.firmwareProducer = firmwareProducer;
        this.actionHandler = new FirmwareActionHandler();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Maybe<InventoryUpdate> checkForUpdate(UpdateRequest updateRequest, boolean z) {
        return this.kotaDownloader.getAvailableInventoryUpdate(updateRequest, z).retryWhen(ObservableUtils.retryBackoff(4, this.retryDelayMillis)).onErrorResumeNext($$Lambda$FirmwareCapability$paJsCDjNJwPnkXYYdNL86oLJqV8.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<Set<UpdateRequest>> createUpdateRequests() {
        return this.deviceRepository.queryDeviceInformationSet().firstOrError().flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$9M1xlZnaw1O0zinFUGzrK_45Qjs
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$createUpdateRequests$70$FirmwareCapability((Set) obj);
            }
        }).map($$Lambda$2r8ViUIX5WL_pbfe_M4XomHIdYI.INSTANCE);
    }

    private Single<String> getArtifactFilter(String str, final String str2) {
        Logger.d("%s getArtifactFilter for %s", TAG, str);
        return ObservableStream.dispatchSingleSuccessOnErrorResponse(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_ARTIFACT_FILTER).setGetArtifactFilter(Firmware.GetArtifactFilter.newBuilder().setArtifactName(str)).mo10084build())).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$lJ0sbyeCg0wzEr25_lUFnMS-JkE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$getArtifactFilter$74$FirmwareCapability(str2, (Accessories.Response) obj);
            }
        });
    }

    private FirmwareContract.ArtifactFilter getArtifactFilterIfExists(String str, List<FirmwareContract.ArtifactFilter> list) {
        for (FirmwareContract.ArtifactFilter artifactFilter : list) {
            if (artifactFilter.getName().equals(str)) {
                return artifactFilter;
            }
        }
        return null;
    }

    private Maybe<List<FirmwareContract.ArtifactFilter>> getArtifactFilters(final FirmwareContract.Package r2, final String str) {
        return Maybe.defer(new Supplier() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$Kg9ffzerEL1pWWdRspMbDmcXdtE
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return FirmwareCapability.this.lambda$getArtifactFilters$26$FirmwareCapability(r2, str);
            }
        }).subscribeOn(Schedulers.io());
    }

    private Maybe<List<DeviceArtifactContract.ArtifactPackage>> getArtifactsFromDevicePackage(final DevicePackage devicePackage) {
        return getArtifactFilters(devicePackage.aPackage, devicePackage.deviceType).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$RDPt5cfhiiNu8sR-OCUQ-UWZheE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$getArtifactsFromDevicePackage$28$FirmwareCapability(devicePackage, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getComponentNamesIncludeAllIfUnsupported */
    public List<String> lambda$null$38$FirmwareCapability(Accessories.Response response, Firmware.FirmwareInformation firmwareInformation) {
        if (response.getErrorCode() == Common.ErrorCode.UNSUPPORTED) {
            Logger.d("UNSUPPORTED response for GET_FIRMWARE_UPDATE_PREFERENCES, all components will be used for DFU");
            ArrayList arrayList = new ArrayList(firmwareInformation.getComponentsCount());
            for (Firmware.FirmwareComponent firmwareComponent : firmwareInformation.getComponentsList()) {
                arrayList.add(firmwareComponent.getName());
            }
            return arrayList;
        }
        return response.getFirmwareUpdatePreferences().getDesiredComponentsList();
    }

    private Single<Accessories.Response> getDAVSUpdatePreference(DeviceArtifactsResponse deviceArtifactsResponse, String str, final String str2) {
        Logger.d("%s get artifacts update preference for %s", TAG, str);
        return ObservableStream.dispatchSingleSuccessOnErrorResponse(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_ARTIFACT_UPDATE_PREFERENCE).setGetArtifactUpdatePreference(Firmware.GetArtifactUpdatePreference.newBuilder().setArtifactName(str).setArtifactType(deviceArtifactsResponse.getArtifactType()).setArtifactKey(deviceArtifactsResponse.getArtifactKey()).setArtifactIdentifier(deviceArtifactsResponse.getArtifactIdentifier()).setArtifactSize((int) deviceArtifactsResponse.getArtifactSize()).setChecksumAlgorithm(Firmware.ChecksumAlgorithm.MD5).setChecksum(ByteString.copyFromUtf8(deviceArtifactsResponse.getMd5Value()))).mo10084build())).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$-4NPqbxVO3PckU3L-sikTxgYeiQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$getDAVSUpdatePreference$72$FirmwareCapability(str2, (Accessories.Response) obj);
            }
        });
    }

    private Single<List<DeviceArtifactContract.ArtifactPackage>> getDesiredArtifactsList(final String str, final List<FirmwareContract.ArtifactFilter> list, final String str2) {
        return getDeviceArtifacts(Firmware.ArtifactRequestReason.FIRMWARE_UPDATE, str).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$DNgIumtmXjNz6MtaY1ainCcwSK0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$getDesiredArtifactsList$45$FirmwareCapability(str, list, str2, (List) obj);
            }
        });
    }

    private Single<List<String>> getDeviceArtifacts(Firmware.ArtifactRequestReason artifactRequestReason, final String str) {
        Logger.d("%s getDeviceArtifacts with reason %s", TAG, artifactRequestReason);
        return ObservableStream.dispatchSingleSuccessOnErrorResponse(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_DEVICE_ARTIFACTS).setGetDeviceArtifacts(Firmware.GetDeviceArtifacts.newBuilder().setArtifactRequestReason(artifactRequestReason)).mo10084build())).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$HYntaCiR0XfDCeP5bHpQ4wGdu7M
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$getDeviceArtifacts$73$FirmwareCapability(str, (Accessories.Response) obj);
            }
        });
    }

    private Single<List<Integer>> getDeviceIdsFromInventorySet() {
        return this.deviceRepository.queryDeviceInformationSet().firstOrError().flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$ibedzNPJ6Czxkj3F9fmn5oMaJZg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$getDeviceIdsFromInventorySet$81$FirmwareCapability((Set) obj);
            }
        });
    }

    @Nullable
    private static Device.DeviceInformation getDeviceInformation(Set<Device.DeviceInformation> set, int i) {
        for (Device.DeviceInformation deviceInformation : set) {
            if (deviceInformation.getDeviceId() == i) {
                return deviceInformation;
            }
        }
        return null;
    }

    private Single<List<Firmware.FirmwareComponent>> getFirmwareComponents(final FirmwareContract.Package r2) {
        return Single.defer(new Supplier() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$JU4XqoI2foHStsrSVaN_o9ZPFkc
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return FirmwareCapability.this.lambda$getFirmwareComponents$18$FirmwareCapability(r2);
            }
        });
    }

    private Maybe<Firmware.FirmwareInformation> getFirmwareInformation(int i, final String str) {
        return ObservableStream.dispatchSingle(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_FIRMWARE_INFORMATION).setGetFirmwareInformation(Firmware.GetFirmwareInformation.newBuilder().setDeviceId(i)).mo10084build())).toMaybe().onErrorResumeWith(Maybe.empty()).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$QuqKzzIOFdrdQDF2BQJ8Rmi7saY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$getFirmwareInformation$5$FirmwareCapability(str, (Accessories.Response) obj);
            }
        });
    }

    private Single<ByteString> getFirmwareMetadata(FirmwareContract.Package r2) {
        return this.firmwareSupplier.getMetadata(r2).flatMap($$Lambda$FirmwareCapability$sDY29JmjAUGW4myCSTpnTEfzBgY.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public Single<FirmwareUpdateSet> getFirmwareUpdatePreferenceSingle() {
        return this.firmwareRepository.queryInformationSet().flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$nxNTihz1uLsp0eVFisS7ORVZ1aw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$getFirmwareUpdatePreferenceSingle$12$FirmwareCapability((Set) obj);
            }
        });
    }

    private ControlMessageHandler<Firmware.InitiateFirmwareUpdate> getInitiateFirmwareUpdateHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$H-AE7JwwxfuuK9ok8xYsGUm6wt4
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                FirmwareCapability.this.lambda$getInitiateFirmwareUpdateHandler$0$FirmwareCapability(controlStream, command, (Firmware.InitiateFirmwareUpdate) obj);
            }
        };
    }

    private Single<List<Firmware.NextAvailableUpdate>> getNextAvailableUpdates(final Firmware.FirmwareInformation firmwareInformation) {
        return getDeviceIdsFromInventorySet().flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$feWoLidlWtPC37OpcqxMiXRX0p8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                SingleSource list;
                list = Observable.fromIterable((List) obj).filter(new Predicate() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$iEdi04-69yEUctePuiTqFqloq2s
                    @Override // io.reactivex.rxjava3.functions.Predicate
                    public final boolean test(Object obj2) {
                        return FirmwareCapability.lambda$null$76(Firmware.FirmwareInformation.this, (Integer) obj2);
                    }
                }).map($$Lambda$FirmwareCapability$F7yqZGEwvHDSSu1EBmPBbf66hbg.INSTANCE).toList();
                return list;
            }
        });
    }

    private ControlMessageHandler<Firmware.NotifyFirmwareInformation> getNotifyFirmwareInformationHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$fPo6s0tcHjd7YLywoFHHigtDlFo
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                FirmwareCapability.this.lambda$getNotifyFirmwareInformationHandler$6$FirmwareCapability(controlStream, command, (Firmware.NotifyFirmwareInformation) obj);
            }
        };
    }

    private Single<FirmwareContract.ArtifactFilter> getOrFetchArtifactFilter(String str, String str2, List<FirmwareContract.ArtifactFilter> list) {
        if (!list.isEmpty()) {
            FirmwareContract.ArtifactFilter artifactFilterIfExists = getArtifactFilterIfExists(str, list);
            if (artifactFilterIfExists == null) {
                Logger.e("%s Filter was expected for artifactName: %s but it does not exists.", TAG, str);
                recordFirmwareMetrics(MetricsConstants.DAVS.ARTIFACT_FILTER_ABSENT, str2);
                return Single.error(new IllegalArgumentException("Incorrect filter is supplied from firmware package"));
            }
            return Single.just(artifactFilterIfExists);
        }
        return getArtifactFilter(str, str2).map($$Lambda$FirmwareCapability$PZF7MuJWOcJcwu8NTFDDYOclXs.INSTANCE);
    }

    private int getValidFWSegmentSize(int i) {
        Logger.d("%s validating segmentSize received from Firmware: %d", TAG, Integer.valueOf(i));
        if (i <= 0) {
            return 2048;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FirmwareContract.ArtifactFilter lambda$getOrFetchArtifactFilter$49(String str) throws Throwable {
        return (FirmwareContract.ArtifactFilter) new Gson().fromJson(str, (Class<Object>) FirmwareContract.ArtifactFilter.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ByteString lambda$null$13(FirmwareMetadata firmwareMetadata) throws Exception {
        SourceInputStream sourceInputStream = new SourceInputStream(firmwareMetadata.open());
        try {
            ByteString readFrom = ByteString.readFrom(sourceInputStream);
            sourceInputStream.close();
            return readFrom;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    sourceInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ProtobufControlMessage lambda$null$35(Firmware.FirmwareInformation firmwareInformation, ByteString byteString, List list) throws Throwable {
        return new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_FIRMWARE_UPDATE_PREFERENCES).setGetFirmwareUpdatePreferences(Firmware.GetFirmwareUpdatePreferences.newBuilder().setFirmwareInformation(firmwareInformation).setMetadata(byteString).addAllNextAvailableUpdates(list)).mo10084build());
    }

    static /* synthetic */ Iterable lambda$null$57(Set set) throws Throwable {
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Integer lambda$null$58(Device.DeviceInformation deviceInformation, Set set) throws Throwable {
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            Firmware.FirmwareInformation firmwareInformation = (Firmware.FirmwareInformation) it2.next();
            if (firmwareInformation.getDeviceId() == deviceInformation.getDeviceId()) {
                return Integer.valueOf(firmwareInformation.getSegmentSize());
            }
        }
        return 2048;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$null$76(Firmware.FirmwareInformation firmwareInformation, Integer num) throws Throwable {
        return num.intValue() != firmwareInformation.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$null$79(Set set, Device.DeviceInformation deviceInformation) throws Throwable {
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            if (((InventoryUpdateBundle) it2.next()).getUpdateRequest().getDeviceSerialNumber().equals(deviceInformation.getSerialNumber())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$observeDAVSArtifactsAvailability$56(Boolean bool) throws Throwable {
        return !bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$requestAndProvideFirmwareInformation$3(List list) throws Throwable {
        if (list.size() >= 1) {
            return list;
        }
        throw new NoSuchElementException("Firmware information not available");
    }

    static /* synthetic */ FirmwareUpdateSet lambda$requestFirmwareComponents$42(DevicePackage devicePackage, List list) throws Throwable {
        return new FirmwareUpdateSet(devicePackage, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ProtobufControlMessage lambda$requestFirmwareComponentsList$29(Firmware.FirmwareInformation firmwareInformation, ByteString byteString) throws Throwable {
        return new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_FIRMWARE_UPDATE_PREFERENCES).setGetFirmwareUpdatePreferences(Firmware.GetFirmwareUpdatePreferences.newBuilder().setFirmwareInformation(firmwareInformation).setMetadata(byteString)).mo10084build());
    }

    private void observeDAVSArtifactsAvailability() {
        if (!this.featureChecker.hasAccess(AccessoryFeature.ALEXA_ACCESSORY_DAVS_ANDROID)) {
            Logger.d("%s observeDAVSArtifactsAvailability DAVS disabled", TAG);
        } else {
            this.compositeDisposable.add(this.packageAvailabilitySubject.take(1L).filter($$Lambda$FirmwareCapability$05sZ0elYjwFUBXWlAA8428cQjcw.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$x1rnXnad0iuoP5iJJCTtinuwYXU
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return FirmwareCapability.this.lambda$observeDAVSArtifactsAvailability$63$FirmwareCapability((Boolean) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$PBo27BFn1Bjhtg8QBZf-OUMLy_4
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    FirmwareCapability.this.lambda$observeDAVSArtifactsAvailability$64$FirmwareCapability((FirmwareCapability.ArtifactUpdateBundle) obj);
                }
            }, $$Lambda$FirmwareCapability$4_6QNoVUjt3aHpv2TrYZQqNgjyc.INSTANCE));
        }
    }

    private void observeFirmwareAvailability() {
        Disposable disposable = this.firmwareAvailabilityDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.compositeDisposable.remove(this.firmwareAvailabilityDisposable);
        }
        this.firmwareAvailabilityDisposable = getFirmwareUpdatePreferenceSingle().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$1dPYyK45oG7rBxC8IQAc4tvAN0k
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareCapability.this.updateFirmware((FirmwareCapability.FirmwareUpdateSet) obj);
            }
        }, $$Lambda$FirmwareCapability$TkVEEzSdVTvn6QjzV0_lWibxAw.INSTANCE);
        this.compositeDisposable.add(this.firmwareAvailabilityDisposable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: prepareArtifactPackage */
    public Maybe<DeviceArtifactContract.ArtifactPackage> lambda$null$43$FirmwareCapability(FirmwareContract.ArtifactFilter artifactFilter, final String str, final String str2, String str3) {
        return this.davsClient.fetchDavsManifest(new DeviceArtifactsRequest.Builder().artifactType(artifactFilter.getArtifactType()).artifactKey(artifactFilter.getArtifactKey()).filters(artifactFilter.getFilters()).build(), str2, str3).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$CydMnm6b9Fwn2cR8pm7O6DQRppU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$prepareArtifactPackage$48$FirmwareCapability(str, str2, (DeviceArtifactsResponse) obj);
            }
        });
    }

    private Maybe<FirmwareUpdateSet> prepareFirmwareComponents(final DevicePackage devicePackage) {
        Preconditions.notNull(devicePackage, "devicePackage");
        if (this.featureChecker.hasAccess(AccessoryFeature.ALEXA_ACCESSORY_DAVS_ANDROID)) {
            Logger.d("%s prepare firmware components, DAVS enabled", TAG);
            return prepareFirmwareComponentsWithDAVS(devicePackage);
        }
        Logger.d("%s prepare firmware components, DAVS disabled", TAG);
        return getFirmwareInformation(devicePackage).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$DqevJI7NaHUNHS8La_sUJt9pTF8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$prepareFirmwareComponents$20$FirmwareCapability(devicePackage, (Firmware.FirmwareInformation) obj);
            }
        });
    }

    private Maybe<FirmwareUpdateSet> prepareFirmwareComponentsWithDAVS(final DevicePackage devicePackage) {
        return getFirmwareInformation(devicePackage).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$_9ZllL4V9lzvrXR8BNHd1Rs9o0w
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$prepareFirmwareComponentsWithDAVS$22$FirmwareCapability(devicePackage, (Firmware.FirmwareInformation) obj);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$QLSWN_8Jjm4EuaKznj3tRiyxCag
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$prepareFirmwareComponentsWithDAVS$25$FirmwareCapability(devicePackage, (List) obj);
            }
        });
    }

    private void provideAvailableUpdates() {
        Single observeOn = createUpdateRequests().flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$wJvTdpGIjMgd7uK10Qfj-U38jbg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$provideAvailableUpdates$55$FirmwareCapability((Set) obj);
            }
        }).map($$Lambda$Xlmt0nL10_onlo2I_4ahXL4SvwI.INSTANCE).observeOn(AndroidSchedulers.mainThread());
        final FirmwareProvider firmwareProvider = this.provider;
        firmwareProvider.getClass();
        Consumer consumer = new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$AH2egST4jd3MEvXtLto_Kb2QooA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareProvider.this.provideInventoryUpdate((HashSet) obj);
            }
        };
        final FirmwareProvider firmwareProvider2 = this.provider;
        firmwareProvider2.getClass();
        this.compositeDisposable.add(observeOn.subscribe(consumer, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$G0vucmXrnIPSs2bCx9dZSub0HuY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareProvider.this.provideInventoryUpdateError((Throwable) obj);
            }
        }));
    }

    private void recordFirmwareMetrics(String str, String str2) {
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(str, str2, 1.0d, null);
    }

    private void recordFirmwareUpdatePreference(Common.ErrorCode errorCode, String str, int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("firmware_accessory", String.valueOf(i));
        hashMap.put("firmware_accessory_0", String.valueOf(i2));
        AccessoryMetricsService accessoryMetricsService = AccessoryMetricsServiceHolder.getInstance().get();
        accessoryMetricsService.recordCounter("AccessoryDfuGetFirmwareUpdatePreference:" + errorCode, str, 1.0d, hashMap);
    }

    private void recordGetFirmwareInformationResponse(int i, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("firmware_accessory", String.valueOf(i));
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(MetricsConstants.Dfu.GET_FIRMWARE_INFORMATION_RESPONSE, str, 1.0d, hashMap);
    }

    @SuppressLint({"CheckResult"})
    private void requestAndProvideFirmwareInformation() {
        Single observeOn = this.deviceRepository.queryDeviceInformationSet().firstOrError().flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$u5O2f95fVqC4yUaFOacPQcwCBsA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$requestAndProvideFirmwareInformation$2$FirmwareCapability((Set) obj);
            }
        }).map($$Lambda$FirmwareCapability$8N49_yVRq3pc__OBFzb5dmg2UoQ.INSTANCE).map($$Lambda$Xlmt0nL10_onlo2I_4ahXL4SvwI.INSTANCE).observeOn(AndroidSchedulers.mainThread());
        final FirmwareProvider firmwareProvider = this.provider;
        firmwareProvider.getClass();
        observeOn.subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$Y8ecUZZbt0KLILvfYDYhkbAYy3g
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareProvider.this.provideInformation((HashSet) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$27JWZ2Z07_6WKbDAuLVOY6W3Vo0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareCapability.this.lambda$requestAndProvideFirmwareInformation$4$FirmwareCapability((Throwable) obj);
            }
        });
    }

    private Single<FirmwareUpdateSet> requestFirmwareComponents(final Firmware.FirmwareInformation firmwareInformation, final DevicePackage devicePackage) {
        return getFirmwareMetadata(devicePackage.aPackage).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$Ssjbv6M_j2Xn7ykzEwJ_0a9H424
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$requestFirmwareComponents$36$FirmwareCapability(firmwareInformation, (ByteString) obj);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$3BEH8doLicBQYsJBVkDWszNAGf4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$requestFirmwareComponents$41$FirmwareCapability(devicePackage, firmwareInformation, (ProtobufControlMessage) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$da032FpfI_D2bEF-zLehpK63L1A
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return new FirmwareCapability.FirmwareUpdateSet(FirmwareCapability.DevicePackage.this, (List) obj);
            }
        });
    }

    private Single<List<Firmware.FirmwareComponent>> requestFirmwareComponentsList(final Firmware.FirmwareInformation firmwareInformation, final DevicePackage devicePackage) {
        return getFirmwareMetadata(devicePackage.aPackage).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$DO_rwucnpzrv7iMhsB2wRMuTOSQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.lambda$requestFirmwareComponentsList$29(Firmware.FirmwareInformation.this, (ByteString) obj);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$gVAFzQNoMfOjsb9BEijGmQcbM4g
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$requestFirmwareComponentsList$34$FirmwareCapability(devicePackage, firmwareInformation, (ProtobufControlMessage) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Completable scheduleDownload(InventoryUpdateBundle inventoryUpdateBundle) {
        final UpdateRequest updateRequest = inventoryUpdateBundle.getUpdateRequest();
        final InventoryUpdate inventoryUpdate = inventoryUpdateBundle.getInventoryUpdate();
        return this.kotaDownloader.downloadPackage(updateRequest, inventoryUpdate, true).retryWhen(ObservableUtils.retryBackoff(4, this.retryDelayMillis)).onErrorResumeNext(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$08hXh4_QerBpU00MwSezd1PLuqw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$scheduleDownload$66$FirmwareCapability(updateRequest, inventoryUpdate, (Throwable) obj);
            }
        }).onErrorResumeNext($$Lambda$FirmwareCapability$koD9ODMNMZEeCPcMRNDJv4Falc.INSTANCE);
    }

    @SuppressLint({"CheckResult"})
    private void silentFirmwareDownload() {
        Logger.d("Silent DFU check initiated");
        this.compositeDisposable.add(this.firmwareRepository.queryInventoryUpdateSet(false).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$U0tadfCZaK_MiJizGPnkCJ7N85c
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$silentFirmwareDownload$50$FirmwareCapability((Set) obj);
            }
        }).subscribe(new Action() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$6ZJ_b3UcoRdjaxFWj8mNV_D5gX0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                FirmwareCapability.this.lambda$silentFirmwareDownload$51$FirmwareCapability();
            }
        }, $$Lambda$FirmwareCapability$gQFtQzPOD6Yo7JSHbPm38wPkHsk.INSTANCE));
        provideAvailableUpdates();
    }

    private Single<List<DeviceArtifactContract.ArtifactPackage>> startFirmwareUpdate(final String str, final List<DeviceArtifactContract.ArtifactPackage> list) {
        Logger.d("%s startFirmwareUpdate for accessory", TAG);
        return ObservableStream.dispatchSingleSuccessOnErrorResponse(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.START_FIRMWARE_UPDATE).setStartFirmwareUpdate(Firmware.StartFirmwareUpdate.newBuilder()).mo10084build())).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$QPWoazpWC50Xe2fE5HCJYM7yaNo
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$startFirmwareUpdate$75$FirmwareCapability(list, str, (Accessories.Response) obj);
            }
        });
    }

    private Accessories.Response throwIfNotSuccessOrUnsupported(Accessories.Response response, ControlMessage controlMessage) throws Exception {
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS || response.getErrorCode() == Common.ErrorCode.UNSUPPORTED) {
            return response;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Response for ");
        sb.append(controlMessage);
        sb.append(" failed with error ");
        throw new Exception(GeneratedOutlineSupport1.outline34(response, sb));
    }

    private void transferDeviceArtifacts(ArtifactUpdateBundle artifactUpdateBundle, int i) {
        Logger.d("%s Updating device artifacts %s", TAG, artifactUpdateBundle);
        TransportDispatcher transportDispatcher = this.authenticationAgnosticDispatcher;
        ControlStream controlStream = this.stream;
        FirmwareSupplier firmwareSupplier = this.firmwareSupplier;
        FirmwareProvider firmwareProvider = this.provider;
        List emptyList = Collections.emptyList();
        TaskManager taskManager = this.taskManager;
        taskManager.getClass();
        FirmwareUpdateTask firmwareUpdateTask = new FirmwareUpdateTask(transportDispatcher, controlStream, firmwareSupplier, firmwareProvider, emptyList, new $$Lambda$GnemjvARNmUKc1SDV0BvY19LSk(taskManager), this.metricsReporter, artifactUpdateBundle.artifacts, new DefaultDAVSUpdateCandidateInformation(artifactUpdateBundle.deviceType, this.firmwareRepository), artifactUpdateBundle.deviceId, artifactUpdateBundle.segmentSize);
        if (this.taskManager.schedule(firmwareUpdateTask, i)) {
            this.updateTaskSet.add(firmwareUpdateTask);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFirmware(FirmwareUpdateSet firmwareUpdateSet) {
        Logger.d("Updating firmware components %s", firmwareUpdateSet.components);
        this.taskManager.dispose(this.updateTask);
        DevicePackage devicePackage = firmwareUpdateSet.devicePackage;
        DefaultDfuCandidateInformation defaultDfuCandidateInformation = new DefaultDfuCandidateInformation(devicePackage.deviceType, devicePackage.aPackage);
        TransportDispatcher transportDispatcher = this.authenticationAgnosticDispatcher;
        ControlStream controlStream = this.stream;
        FirmwareSupplier firmwareSupplier = this.firmwareSupplier;
        FirmwareProvider firmwareProvider = this.provider;
        List<Firmware.FirmwareComponent> list = firmwareUpdateSet.components;
        TaskManager taskManager = this.taskManager;
        taskManager.getClass();
        $$Lambda$GnemjvARNmUKc1SDV0BvY19LSk __lambda_gnemjvarnmukc1sdv0bvy19lsk = new $$Lambda$GnemjvARNmUKc1SDV0BvY19LSk(taskManager);
        MetricsReporter metricsReporter = this.metricsReporter;
        List<DeviceArtifactContract.ArtifactPackage> list2 = firmwareUpdateSet.artifactPackages;
        DevicePackage devicePackage2 = firmwareUpdateSet.devicePackage;
        this.updateTask = new FirmwareUpdateTask(transportDispatcher, controlStream, firmwareSupplier, firmwareProvider, list, __lambda_gnemjvarnmukc1sdv0bvy19lsk, metricsReporter, list2, defaultDfuCandidateInformation, devicePackage2.deviceId, devicePackage2.segmentSize);
        if (this.taskManager.schedule(this.updateTask, 2)) {
            this.updateTaskSet.add(this.updateTask);
        }
    }

    public /* synthetic */ SingleSource lambda$createUpdateRequests$70$FirmwareCapability(final Set set) throws Throwable {
        return this.firmwareRepository.queryInformationSet().flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$F8Xq_Bu75IhAwg_j7gGGEhhb8s8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$69$FirmwareCapability(set, (Set) obj);
            }
        });
    }

    public /* synthetic */ ObservableSource lambda$createUpdateRequests$71$FirmwareCapability(Set set, Device.DeviceInformation deviceInformation) throws Throwable {
        if (!this.deviceManufacturerSupplier.isFwUpdateSupported(deviceInformation.getDeviceType())) {
            Logger.d("%s Firmware update is not supported for the device: %s", TAG, deviceInformation.getDeviceType());
            return Observable.empty();
        }
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            Firmware.FirmwareInformation firmwareInformation = (Firmware.FirmwareInformation) it2.next();
            if (deviceInformation.getDeviceId() == firmwareInformation.getDeviceId()) {
                return this.kotaDownloader.generateUpdateRequest(deviceInformation, firmwareInformation).toObservable();
            }
        }
        Logger.d("ERROR: Critical: No deviceInformation-firmwareInformation match found for deviceId comparison, no update check will occur for device %s", deviceInformation);
        Logger.e("Critical: No deviceInformation-firmwareInformation match found for deviceId comparison, no update check will occur for device %s", RedactionUtil.redact(deviceInformation));
        return Observable.empty();
    }

    public /* synthetic */ String lambda$getArtifactFilter$74$FirmwareCapability(String str, Accessories.Response response) throws Throwable {
        recordFirmwareMetrics(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("GetArtifactFilter:")), str);
        Logger.d("%s getDeviceArtifacts response: %s", TAG, response.getErrorCode());
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
            return response.getArtifactFilter().getArtifactFilter();
        }
        throw new Exception(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("Response for GET_ARTIFACT_FILTER failed with error code: ")));
    }

    public /* synthetic */ MaybeSource lambda$getArtifactFilters$26$FirmwareCapability(FirmwareContract.Package r2, String str) throws Throwable {
        try {
            return Maybe.just(r2.getArtifactFilters());
        } catch (IOException | JSONException e) {
            recordFirmwareMetrics(MetricsConstants.DAVS.GET_ARTIFACT_FROM_PACKAGE_EXCEPTION, str);
            return Maybe.error(e);
        }
    }

    public /* synthetic */ MaybeSource lambda$getArtifactsFromDevicePackage$28$FirmwareCapability(final DevicePackage devicePackage, List list) throws Throwable {
        if (list.isEmpty()) {
            Logger.d("%s No artifact filters present in package. Continuing with empty artifacts.", TAG);
            return Maybe.empty();
        }
        return getDesiredArtifactsList(devicePackage.deviceType, list, devicePackage.dsn).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$JGTr_B-V-H52Wys7W_ZQmF4CQVE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$27$FirmwareCapability(devicePackage, (List) obj);
            }
        }).toMaybe();
    }

    public /* synthetic */ Accessories.Response lambda$getDAVSUpdatePreference$72$FirmwareCapability(String str, Accessories.Response response) throws Throwable {
        recordFirmwareMetrics(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("GetArtifactUpdatePreferenceResponse:")), str);
        Logger.d("%s Received response for GET_ARTIFACT_UPDATE_PREFERENCE", TAG);
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
            return response;
        }
        throw new Exception(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("Response for GET_ARTIFACT_UPDATE_PREFERENCE failed with error code: ")));
    }

    public /* synthetic */ SingleSource lambda$getDesiredArtifactsList$45$FirmwareCapability(final String str, final List list, final String str2, List list2) throws Throwable {
        return Observable.fromIterable(list2).concatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$m9pmRLB93n4BVMeh1kRgMNh4wds
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$44$FirmwareCapability(str, list, str2, (String) obj);
            }
        }).toList();
    }

    public /* synthetic */ List lambda$getDeviceArtifacts$73$FirmwareCapability(String str, Accessories.Response response) throws Throwable {
        recordFirmwareMetrics(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("GetDeviceArtifactsResponse:")), str);
        Logger.d("%s getDeviceArtifacts response: %s", TAG, response.getErrorCode());
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
            return response.getArtifactList().getArtifactNameList();
        }
        throw new Exception(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("Response for GET_DEVICE_ARTIFACTS failed with error code: ")));
    }

    public /* synthetic */ SingleSource lambda$getDeviceIdsFromInventorySet$81$FirmwareCapability(final Set set) throws Throwable {
        return this.firmwareRepository.queryInventoryUpdateSet(false).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$wUa0lLz2jNVAR7PdUsYWpWUhwaw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                SingleSource list;
                list = Observable.fromIterable(set).filter(new Predicate() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$PLYio7jwoyOCjYqkib8TExOmlQ8
                    @Override // io.reactivex.rxjava3.functions.Predicate
                    public final boolean test(Object obj2) {
                        return FirmwareCapability.lambda$null$79(r1, (Device.DeviceInformation) obj2);
                    }
                }).map($$Lambda$_4VC6_YjUm3E2Enflssko4iPSL0.INSTANCE).toList();
                return list;
            }
        });
    }

    public /* synthetic */ SingleSource lambda$getFirmwareComponents$18$FirmwareCapability(FirmwareContract.Package r2) throws Throwable {
        return Observable.fromIterable(r2.getComponents()).concatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$K61x8ZnvxaaJdMEZ8-zE0LCBCDA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$17$FirmwareCapability((FirmwareContract.Component) obj);
            }
        }).toList();
    }

    public /* synthetic */ Firmware.FirmwareInformation lambda$getFirmwareInformation$5$FirmwareCapability(String str, Accessories.Response response) throws Throwable {
        recordGetFirmwareInformationResponse(response.getFirmwareInformation().getVersion(), str);
        return response.getFirmwareInformation();
    }

    public /* synthetic */ SingleSource lambda$getFirmwareUpdatePreferenceSingle$12$FirmwareCapability(final Set set) throws Throwable {
        return this.deviceRepository.queryDeviceInformationSet().firstOrError().flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$8tzpswYCdgwRN4d_c3i-PbOsGiY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$11$FirmwareCapability(set, (Set) obj);
            }
        });
    }

    public /* synthetic */ void lambda$getInitiateFirmwareUpdateHandler$0$FirmwareCapability(ControlStream controlStream, Accessories.Command command, Firmware.InitiateFirmwareUpdate initiateFirmwareUpdate) throws Exception {
        Logger.d("%s received INITIATE_FIRMWARE_UPDATE command for accessory", TAG);
        controlStream.respond(Accessories.Command.INITIATE_FIRMWARE_UPDATE, Common.ErrorCode.SUCCESS);
        observeFirmwareAvailability();
    }

    public /* synthetic */ void lambda$getNotifyFirmwareInformationHandler$6$FirmwareCapability(ControlStream controlStream, Accessories.Command command, Firmware.NotifyFirmwareInformation notifyFirmwareInformation) throws Exception {
        this.provider.provideInformation(Collections.singleton(notifyFirmwareInformation.getFirmwareInformation()));
        controlStream.respond(Accessories.Command.NOTIFY_FIRMWARE_INFORMATION, Common.ErrorCode.SUCCESS);
    }

    public /* synthetic */ ObservableSource lambda$null$1$FirmwareCapability(Device.DeviceInformation deviceInformation) throws Throwable {
        return getFirmwareInformation(deviceInformation.getDeviceId(), deviceInformation.getDeviceType()).toObservable();
    }

    public /* synthetic */ ObservableSource lambda$null$10$FirmwareCapability(DevicePackage devicePackage) throws Throwable {
        return prepareFirmwareComponents(devicePackage).toObservable();
    }

    public /* synthetic */ SingleSource lambda$null$11$FirmwareCapability(Set set, final Set set2) throws Throwable {
        return Observable.fromIterable(set).flatMapSingle(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$5l3UqnMFv8eUrMzs_osYFQsnNnk
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$9$FirmwareCapability(set2, (Firmware.FirmwareInformation) obj);
            }
        }).concatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$MNLcieFuDDdrghiic_lvji8abAI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$10$FirmwareCapability((FirmwareCapability.DevicePackage) obj);
            }
        }).firstOrError();
    }

    public /* synthetic */ ObservableSource lambda$null$17$FirmwareCapability(FirmwareContract.Component component) throws Throwable {
        return this.firmwareSupplier.queryComponent(component).observeOn(Schedulers.io()).map($$Lambda$FirmwareCapability$3RCeZxCi5KdtedL7XlQl7_2rxA.INSTANCE).toObservable();
    }

    public /* synthetic */ void lambda$null$23$FirmwareCapability(DevicePackage devicePackage, Throwable th) throws Throwable {
        recordFirmwareMetrics(MetricsConstants.DAVS.DAVS_PREPARE_ARTIFACTS_ERROR, devicePackage.deviceType);
    }

    public /* synthetic */ FirmwareUpdateSet lambda$null$24$FirmwareCapability(DevicePackage devicePackage, List list, List list2) throws Throwable {
        Logger.d("%s return firmware update set with artifacts size: %d", TAG, Integer.valueOf(list2.size()));
        if (list2.size() > 0) {
            recordFirmwareMetrics(MetricsConstants.DAVS.FIRMWARE_UPDATE_HAS_COMPONENT_AND_ARTIFACTS, devicePackage.deviceType);
        } else {
            recordFirmwareMetrics(MetricsConstants.DAVS.FIRMWARE_UPDATE_HAS_COMPONENT_NO_ARTIFACTS, devicePackage.deviceType);
        }
        return new FirmwareUpdateSet(devicePackage, list, list2);
    }

    public /* synthetic */ SingleSource lambda$null$27$FirmwareCapability(DevicePackage devicePackage, List list) throws Throwable {
        return startFirmwareUpdate(devicePackage.deviceType, list);
    }

    public /* synthetic */ Accessories.Response lambda$null$30$FirmwareCapability(DevicePackage devicePackage, Firmware.FirmwareInformation firmwareInformation, ProtobufControlMessage protobufControlMessage, Accessories.Response response) throws Throwable {
        recordFirmwareUpdatePreference(response.getErrorCode(), devicePackage.deviceType, devicePackage.aPackage.getCurrentVersion().intValue(), firmwareInformation.getVersion());
        return throwIfNotSuccessOrUnsupported(response, protobufControlMessage);
    }

    public /* synthetic */ Accessories.Response lambda$null$37$FirmwareCapability(DevicePackage devicePackage, Firmware.FirmwareInformation firmwareInformation, ProtobufControlMessage protobufControlMessage, Accessories.Response response) throws Throwable {
        recordFirmwareUpdatePreference(response.getErrorCode(), devicePackage.deviceType, devicePackage.aPackage.getCurrentVersion().intValue(), firmwareInformation.getVersion());
        return throwIfNotSuccessOrUnsupported(response, protobufControlMessage);
    }

    public /* synthetic */ ObservableSource lambda$null$44$FirmwareCapability(final String str, List list, final String str2, final String str3) throws Throwable {
        return getOrFetchArtifactFilter(str3, str, list).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$8PAy6AUL697zLw-uFgftaeiX71I
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$43$FirmwareCapability(str3, str, str2, (FirmwareContract.ArtifactFilter) obj);
            }
        }).toObservable();
    }

    public /* synthetic */ MaybeSource lambda$null$47$FirmwareCapability(String str, DeviceArtifactsResponse deviceArtifactsResponse, String str2, Accessories.Response response) throws Throwable {
        Logger.d("Download artifact for " + str);
        if (deviceArtifactsResponse.getDavsArtifactSignatures() != null && deviceArtifactsResponse.getDavsArtifactSignatures().size() > 0) {
            return this.davsClient.downloadArtifact(str, deviceArtifactsResponse, str2);
        }
        recordFirmwareMetrics(MetricsConstants.DAVS.ERROR_ARTIFACT_SIGNATURE_MISSING, str2);
        return Maybe.error(new IllegalArgumentException("Required artifact signature not present"));
    }

    public /* synthetic */ ObservableSource lambda$null$54$FirmwareCapability(final UpdateRequest updateRequest) throws Throwable {
        return checkForUpdate(updateRequest, false).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$o1c98gxRI_GwOaXTnZrH6khU3tg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                InventoryUpdateBundle build;
                build = new InventoryUpdateBundle.Builder().inventoryUpdate((InventoryUpdate) obj).updateRequest(UpdateRequest.this).build();
                return build;
            }
        }).toObservable();
    }

    public /* synthetic */ SingleSource lambda$null$59$FirmwareCapability(Device.DeviceInformation deviceInformation, List list) throws Throwable {
        return startFirmwareUpdate(deviceInformation.getDeviceType(), list);
    }

    public /* synthetic */ ArtifactUpdateBundle lambda$null$60$FirmwareCapability(Device.DeviceInformation deviceInformation, Integer num, List list) throws Throwable {
        Logger.d("%s Transferring %d artifacts", TAG, Integer.valueOf(list.size()));
        return new ArtifactUpdateBundle(list, deviceInformation.getDeviceType(), deviceInformation.getDeviceId(), getValidFWSegmentSize(num.intValue()));
    }

    public /* synthetic */ SingleSource lambda$null$61$FirmwareCapability(final Device.DeviceInformation deviceInformation, final Integer num) throws Throwable {
        return getDesiredArtifactsList(deviceInformation.getDeviceType(), Collections.emptyList(), deviceInformation.getSerialNumber()).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$wvh9bIH1yzxeY4GYgT_pST_PIw0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$59$FirmwareCapability(deviceInformation, (List) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$d7bqjFliHhxUVhLU74cgFdBdC4U
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$60$FirmwareCapability(deviceInformation, num, (List) obj);
            }
        });
    }

    public /* synthetic */ ObservableSource lambda$null$62$FirmwareCapability(final Device.DeviceInformation deviceInformation) throws Throwable {
        Logger.d("Get davs artifacts for device: %s", deviceInformation);
        return this.firmwareRepository.queryInformationSet().map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$915iboZF0mL4SA2HCgbaaycHSws
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.lambda$null$58(Device.DeviceInformation.this, (Set) obj);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$DVGf9Won0oXx8OVDmmQoQkCB-hE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$61$FirmwareCapability(deviceInformation, (Integer) obj);
            }
        }).toObservable();
    }

    public /* synthetic */ DevicePackage lambda$null$8$FirmwareCapability(int i, Device.DeviceInformation deviceInformation, int i2, FirmwareContract.Package r12) throws Throwable {
        this.packageAvailabilitySubject.onNext(true);
        return new DevicePackage(r12, i, deviceInformation.getDeviceType(), deviceInformation.getSerialNumber(), i2);
    }

    public /* synthetic */ SingleSource lambda$null$9$FirmwareCapability(Set set, Firmware.FirmwareInformation firmwareInformation) throws Throwable {
        final int deviceId = firmwareInformation.getDeviceId();
        final int validFWSegmentSize = getValidFWSegmentSize(firmwareInformation.getSegmentSize());
        final Device.DeviceInformation deviceInformation = getDeviceInformation(set, deviceId);
        if (deviceInformation == null) {
            return Single.error(new IllegalStateException("Could not map device ID from firmware info to device info, could not prepare firmware"));
        }
        return this.firmwareSupplier.queryPackage(new PackageCandidateIdentifier.Builder().deviceType(deviceInformation.getDeviceType()).deviceSerialNumber(deviceInformation.getSerialNumber()).componentId(UpdateRequest.generateComponentId(firmwareInformation.getName(), firmwareInformation.getLocale())).currentVersion(Integer.valueOf(firmwareInformation.getVersion())).build()).firstOrError().map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$Wn2D5WWxQ-X-euW2J-3ySOogzz8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$8$FirmwareCapability(deviceId, deviceInformation, validFWSegmentSize, (FirmwareContract.Package) obj);
            }
        });
    }

    public /* synthetic */ ObservableSource lambda$observeDAVSArtifactsAvailability$63$FirmwareCapability(Boolean bool) throws Throwable {
        Logger.d("Firmware update task is not running. Starting DAVS update check.");
        return this.deviceRepository.queryDeviceInformationSet().firstOrError().flattenAsObservable($$Lambda$FirmwareCapability$H7YMhpT5KLxRbqZNCjipvnYlBo.INSTANCE).concatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$ZZH-YsTks9OBBsBytPuRR9OIq5A
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$62$FirmwareCapability((Device.DeviceInformation) obj);
            }
        });
    }

    public /* synthetic */ void lambda$observeDAVSArtifactsAvailability$64$FirmwareCapability(ArtifactUpdateBundle artifactUpdateBundle) throws Throwable {
        Logger.d("%s Transfer device artifacts", TAG);
        transferDeviceArtifacts(artifactUpdateBundle, 2);
    }

    public /* synthetic */ MaybeSource lambda$prepareArtifactPackage$48$FirmwareCapability(final String str, final String str2, final DeviceArtifactsResponse deviceArtifactsResponse) throws Throwable {
        return getDAVSUpdatePreference(deviceArtifactsResponse, str, str2).filter($$Lambda$FirmwareCapability$4r5NLAr3qYMqK8TchX4N3QkOYCE.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$eT5SEnxHG72jtgjxzHla3i1Cy0I
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$47$FirmwareCapability(str, deviceArtifactsResponse, str2, (Accessories.Response) obj);
            }
        });
    }

    public /* synthetic */ MaybeSource lambda$prepareFirmwareComponents$20$FirmwareCapability(final DevicePackage devicePackage, Firmware.FirmwareInformation firmwareInformation) throws Throwable {
        return requestFirmwareComponents(firmwareInformation, devicePackage).toMaybe().onErrorResumeNext(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$nsILfwW1q97QGUjwgfgbblDTTH0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return Logger.e("Attempting to get firmware update preferences for device with deviceId %d, received error", (Throwable) obj, Integer.valueOf(FirmwareCapability.DevicePackage.this.deviceId));
            }
        });
    }

    public /* synthetic */ MaybeSource lambda$prepareFirmwareComponentsWithDAVS$22$FirmwareCapability(final DevicePackage devicePackage, Firmware.FirmwareInformation firmwareInformation) throws Throwable {
        return requestFirmwareComponentsList(firmwareInformation, devicePackage).toMaybe().onErrorResumeNext(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$NB1T0nxt9gHEuGVJtmwtTfk_O6Q
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return Logger.e("%s Attempting to get firmware update preferences for device with deviceId %d, received error", (Throwable) obj, FirmwareCapability.TAG, Integer.valueOf(FirmwareCapability.DevicePackage.this.deviceId));
            }
        });
    }

    public /* synthetic */ MaybeSource lambda$prepareFirmwareComponentsWithDAVS$25$FirmwareCapability(final DevicePackage devicePackage, final List list) throws Throwable {
        Logger.d("%s received firmware component list with size: %d, checking for DAVS", TAG, Integer.valueOf(list.size()));
        return getArtifactsFromDevicePackage(devicePackage).doOnError(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$6lj1qIuc-eAF4HaDKBkGGImPJRE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FirmwareCapability.this.lambda$null$23$FirmwareCapability(devicePackage, (Throwable) obj);
            }
        }).defaultIfEmpty(Collections.emptyList()).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$ZgY6rX_pnTGzbs6frppq4dF0Xkg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$24$FirmwareCapability(devicePackage, list, (List) obj);
            }
        }).toMaybe();
    }

    public /* synthetic */ SingleSource lambda$provideAvailableUpdates$55$FirmwareCapability(Set set) throws Throwable {
        return Observable.fromIterable(set).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$fFwKaHYdtQ1gru3JNz2yh1b6FRQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$54$FirmwareCapability((UpdateRequest) obj);
            }
        }).toList();
    }

    public /* synthetic */ SingleSource lambda$requestAndProvideFirmwareInformation$2$FirmwareCapability(Set set) throws Throwable {
        return Observable.fromIterable(set).concatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$nlOmg9_xEqO3q_ET8ZEeD6t-zKQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$1$FirmwareCapability((Device.DeviceInformation) obj);
            }
        }).toList();
    }

    public /* synthetic */ void lambda$requestAndProvideFirmwareInformation$4$FirmwareCapability(Throwable th) throws Throwable {
        this.provider.informationNotAvailable(new UnsupportedOperationException("This accessory doesn't provide firmware information.", th));
    }

    public /* synthetic */ SingleSource lambda$requestFirmwareComponents$36$FirmwareCapability(final Firmware.FirmwareInformation firmwareInformation, final ByteString byteString) throws Throwable {
        return getNextAvailableUpdates(firmwareInformation).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$iofYUhQN92KIs2v_aBSFiISwJEQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.lambda$null$35(Firmware.FirmwareInformation.this, byteString, (List) obj);
            }
        });
    }

    public /* synthetic */ SingleSource lambda$requestFirmwareComponents$41$FirmwareCapability(final DevicePackage devicePackage, final Firmware.FirmwareInformation firmwareInformation, final ProtobufControlMessage protobufControlMessage) throws Throwable {
        return ObservableStream.dispatchSingleSuccessOnErrorResponse(this.stream, protobufControlMessage).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$TmrOflCljY-us2kyVerGcCDEXkw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$37$FirmwareCapability(devicePackage, firmwareInformation, protobufControlMessage, (Accessories.Response) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$xJROlOwZDNCl9-cuR0EmURTAN2k
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$38$FirmwareCapability(firmwareInformation, (Accessories.Response) obj);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$jRh2De8ftIiKbG4Hm0dyFiI8vtM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                SingleSource list;
                list = Observable.fromIterable(Firmware.FirmwareInformation.this.getComponentsList()).filter(new Predicate() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$RwQUK3KoI_R0R59ZGF2ZGDLuJgU
                    @Override // io.reactivex.rxjava3.functions.Predicate
                    public final boolean test(Object obj2) {
                        boolean contains;
                        contains = r1.contains(((Firmware.FirmwareComponent) obj2).getName());
                        return contains;
                    }
                }).toList();
                return list;
            }
        });
    }

    public /* synthetic */ SingleSource lambda$requestFirmwareComponentsList$34$FirmwareCapability(final DevicePackage devicePackage, final Firmware.FirmwareInformation firmwareInformation, final ProtobufControlMessage protobufControlMessage) throws Throwable {
        return ObservableStream.dispatchSingleSuccessOnErrorResponse(this.stream, protobufControlMessage).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$JgGGCQjh32Rmqe3KYBbHNcmdiiA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$30$FirmwareCapability(devicePackage, firmwareInformation, protobufControlMessage, (Accessories.Response) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$jwD4mbGzx64NrfZxe94NRox66K0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$null$31$FirmwareCapability(firmwareInformation, (Accessories.Response) obj);
            }
        }).flatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$cWDZ2mWJKls7fxQdsbPQGYC1spo
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                SingleSource list;
                list = Observable.fromIterable(Firmware.FirmwareInformation.this.getComponentsList()).filter(new Predicate() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$PyWNu3zgGkhk_wHHRSwmzqpxhtw
                    @Override // io.reactivex.rxjava3.functions.Predicate
                    public final boolean test(Object obj2) {
                        boolean contains;
                        contains = r1.contains(((Firmware.FirmwareComponent) obj2).getName());
                        return contains;
                    }
                }).toList();
                return list;
            }
        });
    }

    public /* synthetic */ CompletableSource lambda$scheduleDownload$66$FirmwareCapability(UpdateRequest updateRequest, InventoryUpdate inventoryUpdate, Throwable th) throws Throwable {
        Logger.e("Error downloading kota package, scheduling a job to download it that will start when wifi is available", th);
        return this.kotaDownloader.downloadPackage(updateRequest, inventoryUpdate, false);
    }

    public /* synthetic */ CompletableSource lambda$silentFirmwareDownload$50$FirmwareCapability(Set set) throws Throwable {
        if (set != null && set.size() != 0) {
            return Observable.fromIterable(set).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$WSMBG3AZ0HikYQxyNXimlIaKbZg
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    Completable scheduleDownload;
                    scheduleDownload = FirmwareCapability.this.scheduleDownload((InventoryUpdateBundle) obj);
                    return scheduleDownload;
                }
            }).toList().flatMapCompletable($$Lambda$2vbaKQwBXPbDE2k6nSJKg20RnA.INSTANCE);
        }
        Logger.d("%s sending FIRMWARE_UPDATE_UNAVAILABLE command", TAG);
        return ObservableStream.dispatchSingleSuccessOnErrorResponse(this.stream, new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.FIRMWARE_UPDATE_UNAVAILABLE).setFirmwareUpdateUnavailable(Firmware.FirmwareUpdateUnavailable.getDefaultInstance()).mo10084build())).ignoreElement();
    }

    public /* synthetic */ void lambda$silentFirmwareDownload$51$FirmwareCapability() throws Throwable {
        this.packageAvailabilitySubject.onNext(false);
        Logger.d("Silent DFU check complete");
    }

    public /* synthetic */ List lambda$startFirmwareUpdate$75$FirmwareCapability(List list, String str, Accessories.Response response) throws Throwable {
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS || response.getErrorCode() == Common.ErrorCode.UNSUPPORTED) {
            return list;
        }
        recordFirmwareMetrics(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("StartFirmwareUpdate:")), str);
        throw new Exception(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("Response for START_FIRMWARE_UPDATE is not SUCCESS or UNSUPPORTED: ")));
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.firmwareProducer.detachActionHandler(this.actionHandler);
        ObservableUtils.dispose(this.compositeDisposable);
        accessoryDescriptor.remove(this.stream);
        for (TaskManager.Task task : this.updateTaskSet) {
            this.taskManager.dispose(task);
        }
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.firmwareProducer.attachActionHandler(this.actionHandler);
        this.authenticationAgnosticDispatcher = accessoryDescriptor.getAuthenticationAgnosticDispatcher();
        this.stream = new ControlStream(this.authenticationAgnosticDispatcher, ControlStream.MessageAuthenticationMode.FORCE_UNAUTHENTICATED);
        this.stream.addMessageHandler(Accessories.Command.INITIATE_FIRMWARE_UPDATE, getInitiateFirmwareUpdateHandler());
        this.stream.addMessageHandler(Accessories.Command.NOTIFY_FIRMWARE_INFORMATION, getNotifyFirmwareInformationHandler());
        accessoryDescriptor.add(this.stream);
        observeDAVSArtifactsAvailability();
        observeFirmwareAvailability();
        silentFirmwareDownload();
        requestAndProvideFirmwareInformation();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class FirmwareUpdateSet {
        List<DeviceArtifactContract.ArtifactPackage> artifactPackages;
        public final List<Firmware.FirmwareComponent> components;
        final DevicePackage devicePackage;

        /* JADX INFO: Access modifiers changed from: package-private */
        public FirmwareUpdateSet(DevicePackage devicePackage, List<Firmware.FirmwareComponent> list) {
            this.artifactPackages = Collections.emptyList();
            this.devicePackage = devicePackage;
            this.components = list;
        }

        FirmwareUpdateSet(DevicePackage devicePackage, List<Firmware.FirmwareComponent> list, List<DeviceArtifactContract.ArtifactPackage> list2) {
            this.artifactPackages = Collections.emptyList();
            this.devicePackage = devicePackage;
            this.components = list;
            this.artifactPackages = list2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createUpdateRequests */
    public Single<List<UpdateRequest>> lambda$null$69$FirmwareCapability(Set<Device.DeviceInformation> set, final Set<Firmware.FirmwareInformation> set2) {
        return Observable.fromIterable(set).concatMap(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$SYtVrtLufl_LYlavsHCipR1tWWc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FirmwareCapability.this.lambda$createUpdateRequests$71$FirmwareCapability(set2, (Device.DeviceInformation) obj);
            }
        }).toList();
    }

    private Single<Firmware.FirmwareInformation> getFirmwareInformation(final DevicePackage devicePackage) {
        return getFirmwareComponents(devicePackage.aPackage).map(new Function() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$FirmwareCapability$y0qE1KdIkOxljFfnmHeIx0DuRe0
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Firmware.FirmwareInformation mo10084build;
                mo10084build = Firmware.FirmwareInformation.newBuilder().setName(r0.aPackage.getName()).setVersion(r0.aPackage.getVersion()).setDeviceId(FirmwareCapability.DevicePackage.this.deviceId).addAllComponents((List) obj).mo10084build();
                return mo10084build;
            }
        }).subscribeOn(Schedulers.io());
    }
}
