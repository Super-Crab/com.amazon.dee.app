package com.amazon.alexa.accessory.notificationpublisher;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.notificationpublisher.bluetooth.BluetoothA2dpConnectionHandler;
import com.amazon.alexa.accessory.notificationpublisher.eventBus.EventBusManager;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.notificationlistener.NotificationListenerProxy;
import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.BluetoothHeadsetProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.DistractionModeProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.InputBehaviorConfigProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.MultiDeviceDistractionModeProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.TemplateProvider;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.GetFocusFilterTemplatesFromS3RequestSender;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.GetFocusFilterTemplatesFromS3ResponseHandler;
import com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.InputConfigGestureStringBuilder;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class ConnectivityModule {
    public static final String ARMSTRONG_DEVICE_TYPE = "A3HVREY4JWAZ6K";
    private static final String TAG = "ConnectivityModule";
    public static final String ZION_DEVICE_TYPE = "A3IYPH06PH1HRA";
    private static ConnectivityModule mConnectivityModule;
    private Accessories clientAccessories;
    private final CompositeDisposable compositeDisposable;
    private final Handler mMainThreadHandler;
    private static final Integer STATE_FEATURE_ANCS = 1536;
    @VisibleForTesting
    static Map<String, String> compatibleDevicesAddressMap = new HashMap();

    /* renamed from: com.amazon.alexa.accessory.notificationpublisher.ConnectivityModule$5  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$AccessorySessionLifecycleEvent = new int[AccessorySessionLifecycleEvent.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$AccessorySessionLifecycleEvent[AccessorySessionLifecycleEvent.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$notificationpublisher$AccessorySessionLifecycleEvent[AccessorySessionLifecycleEvent.RELEASED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private ConnectivityModule(Context context, Accessories accessories) {
        Log.d(TAG, "Construct ConnectivityModule");
        this.clientAccessories = accessories;
        this.mMainThreadHandler = new Handler(context.getMainLooper());
        this.compositeDisposable = new CompositeDisposable();
    }

    private void addConnectivityListener() {
        Log.d(TAG, "addListener");
        clientConnectionObservable();
    }

    private void clientConnectionObservable() {
        Disposable subscribe = this.clientAccessories.getSessionSupplier().observeSessionConnected().observeOn(AndroidSchedulers.mainThread()).subscribe($$Lambda$ConnectivityModule$wrHD7AfUvIkOxT9bz0Hc3tZ5JLk.INSTANCE, $$Lambda$ConnectivityModule$fF0YQd4e3NtsNScxIcun3eJycaE.INSTANCE);
        Disposable subscribe2 = this.clientAccessories.getSessionSupplier().observeSessionReleased().observeOn(AndroidSchedulers.mainThread()).subscribe($$Lambda$ConnectivityModule$EZ8vPALvIo9macpkXViQ5wwjJQ.INSTANCE, $$Lambda$ConnectivityModule$HQavcTTTLp64fNPZoySKYrA9p2Y.INSTANCE);
        this.compositeDisposable.add(subscribe);
        this.compositeDisposable.add(subscribe2);
    }

    public static GetFocusFilterTemplatesFromS3ResponseHandler createGetFocusFilterTemplatesFromS3ResponseHandler() {
        return new GetFocusFilterTemplatesFromS3ResponseHandler() { // from class: com.amazon.alexa.accessory.notificationpublisher.ConnectivityModule.2
            @Override // com.amazon.alexa.accessory.notificationpublisher.servicerequest.GetFocusFilterTemplatesFromS3ResponseHandler
            public void handleGetFocusFilterTemplatesFromS3Response(boolean z, String str, String str2, String str3, GetFocusFilterTemplatesFromS3RequestSender.TemplateType templateType) {
                if (z) {
                    if (!Strings.isNullOrEmpty(str) && !Strings.isNullOrEmpty(str2)) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            String str4 = ConnectivityModule.TAG;
                            Log.d(str4, "handleGetFocusFilterTemplatesFromS3Response - " + templateType.getFileName() + RealTimeTextConstants.COLON_SPACE + str);
                            String str5 = ConnectivityModule.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("handleGetFocusFilterTemplatesFromS3Response - etag: ");
                            sb.append(str2);
                            Log.d(str5, sb.toString());
                            MetricsRecorder.getInstance().recordCounter(MetricsConstants.GET_TEMPLATE_FROM_CLOUD_SUCCESS);
                            TemplateProvider.getInstance().updateTemplateFromS3(templateType.getFileName(), jSONObject);
                            ConnectivityModule.putEtagToLocalStorage(templateType, str2);
                            return;
                        } catch (Exception e) {
                            Log.e(ConnectivityModule.TAG, "handleGetFocusFilterTemplatesFromS3Response - Error: ", e);
                            MetricsRecorder.getInstance().recordCounter(MetricsConstants.GET_TEMPLATE_FROM_CLOUD_ERROR);
                            TemplateProvider.init(DependencyProvider.getContext());
                            return;
                        }
                    } else if (Strings.isNullOrEmpty(str2)) {
                        String str6 = ConnectivityModule.TAG;
                        Log.e(str6, "handleGetFocusFilterTemplatesFromS3Response - success: " + z + " etag is null");
                        MetricsRecorder.getInstance().recordCounter(MetricsConstants.GET_TEMPLATE_FROM_CLOUD_ERROR);
                        TemplateProvider.init(DependencyProvider.getContext());
                        return;
                    } else {
                        String str7 = ConnectivityModule.TAG;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("handleGetFocusFilterTemplatesFromS3Response - No need to update template: ");
                        outline107.append(templateType.name());
                        Log.i(str7, outline107.toString());
                        MetricsRecorder.getInstance().recordCounter(MetricsConstants.GET_TEMPLATE_FROM_CLOUD_SUCCESS_NO_UPDATE);
                        return;
                    }
                }
                MetricsRecorder.getInstance().recordCounter(MetricsConstants.GET_TEMPLATE_FROM_CLOUD_ERROR);
            }
        };
    }

    private static void fetchNotificationTemplatesFromS3() {
        Log.d(TAG, "fetchNotificationTemplatesFromS3 - enter function.");
        final Map<String, String> etagsFromLocalStorage = getEtagsFromLocalStorage();
        final boolean checkIfAnyTemplatesIsNull = TemplateProvider.getInstance().checkIfAnyTemplatesIsNull();
        Executors.newCachedThreadPool().submit(new Runnable() { // from class: com.amazon.alexa.accessory.notificationpublisher.ConnectivityModule.1
            @Override // java.lang.Runnable
            public void run() {
                Log.d(ConnectivityModule.TAG, "fetchNotificationTemplatesFromS3 - enter run function.");
                GetFocusFilterTemplatesFromS3RequestSender.TemplateType templateType = GetFocusFilterTemplatesFromS3RequestSender.TemplateType.PARSE;
                String str = null;
                GetFocusFilterTemplatesFromS3RequestSender.getFocusFilterTemplatesFromS3Request("ANDROID", templateType, checkIfAnyTemplatesIsNull ? null : (String) etagsFromLocalStorage.get(templateType.name()), ConnectivityModule.createGetFocusFilterTemplatesFromS3ResponseHandler(), 2);
                GetFocusFilterTemplatesFromS3RequestSender.TemplateType templateType2 = GetFocusFilterTemplatesFromS3RequestSender.TemplateType.TRANSFORM;
                GetFocusFilterTemplatesFromS3RequestSender.getFocusFilterTemplatesFromS3Request("ANDROID", templateType2, checkIfAnyTemplatesIsNull ? null : (String) etagsFromLocalStorage.get(templateType2.name()), ConnectivityModule.createGetFocusFilterTemplatesFromS3ResponseHandler(), 2);
                GetFocusFilterTemplatesFromS3RequestSender.TemplateType templateType3 = GetFocusFilterTemplatesFromS3RequestSender.TemplateType.IGNORED;
                if (!checkIfAnyTemplatesIsNull) {
                    str = (String) etagsFromLocalStorage.get(templateType3.name());
                }
                GetFocusFilterTemplatesFromS3RequestSender.getFocusFilterTemplatesFromS3Request("ANDROID", templateType3, str, ConnectivityModule.createGetFocusFilterTemplatesFromS3ResponseHandler(), 2);
            }
        });
    }

    private static Single<ConnectedAccessory> getConnectedAccessoryInfo(final AccessorySession accessorySession) {
        return accessorySession.getDeviceRepository().queryDeviceInformationSet().firstOrError().map($$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$ConnectivityModule$6cAKQYTcRDeybjPRK-4YDe8mnes
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                SingleSource doOnError;
                doOnError = AccessorySession.this.getStateRepository().query(StateFeature.from(ConnectivityModule.STATE_FEATURE_ANCS.intValue())).firstOrError().map($$Lambda$ARkVSPIf2OWqlhRmUOw5OSUd1A.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$ConnectivityModule$JoaKcbgZWVLx2MYRu7ueiPXsoZc
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply */
                    public final Object mo10358apply(Object obj2) {
                        SingleSource onErrorReturn;
                        onErrorReturn = Single.just(new ConnectedAccessory(r0.getDeviceType(), ((Integer) obj2).intValue(), null)).onErrorReturn(new Function() { // from class: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$ConnectivityModule$IcgopP8ng-rWKC64FQ9JUWHOKG0
                            @Override // io.reactivex.rxjava3.functions.Function
                            /* renamed from: apply */
                            public final Object mo10358apply(Object obj3) {
                                return ConnectivityModule.lambda$null$7(Device.DeviceInformation.this, (Throwable) obj3);
                            }
                        });
                        return onErrorReturn;
                    }
                }).doOnError($$Lambda$ConnectivityModule$fteRCCU5HJ9gm3Hlg5Ydk3QcTSU.INSTANCE);
                return doOnError;
            }
        });
    }

    private static Map<String, String> getEtagsFromLocalStorage() {
        try {
            StorageWrapper storageWrapper = new StorageWrapper();
            HashMap hashMap = new HashMap();
            String localStorageEtagKey = GetFocusFilterTemplatesFromS3RequestSender.getLocalStorageEtagKey(GetFocusFilterTemplatesFromS3RequestSender.TemplateType.PARSE.name());
            String localStorageEtagKey2 = GetFocusFilterTemplatesFromS3RequestSender.getLocalStorageEtagKey(GetFocusFilterTemplatesFromS3RequestSender.TemplateType.TRANSFORM.name());
            String localStorageEtagKey3 = GetFocusFilterTemplatesFromS3RequestSender.getLocalStorageEtagKey(GetFocusFilterTemplatesFromS3RequestSender.TemplateType.IGNORED.name());
            Object obj = storageWrapper.get(localStorageEtagKey);
            Object obj2 = storageWrapper.get(localStorageEtagKey2);
            Object obj3 = storageWrapper.get(localStorageEtagKey3);
            if (obj != null) {
                hashMap.put(GetFocusFilterTemplatesFromS3RequestSender.TemplateType.PARSE.name(), String.class.cast(obj));
            }
            if (obj2 != null) {
                hashMap.put(GetFocusFilterTemplatesFromS3RequestSender.TemplateType.TRANSFORM.name(), String.class.cast(obj2));
            }
            if (obj3 != null) {
                hashMap.put(GetFocusFilterTemplatesFromS3RequestSender.TemplateType.IGNORED.name(), String.class.cast(obj3));
            }
            return hashMap;
        } catch (Exception e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getEtagsFromLocalStorage - ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.GET_ETAG_FROM_LOCALSTORAGE_EXCEPTION);
            if (!(e instanceof RxBlockingCallException)) {
                return null;
            }
            MetricsRecorder.getInstance().recordCounter("FocusFilter_rx_blocking_call_exception_getEtagsFromLocalStorage", MetricsRecorder.customAttributesForException(e));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Single<ConnectedAccessory> getInputBehaviorConfigSet(final AccessorySession accessorySession, final ConnectedAccessory connectedAccessory) {
        return accessorySession.getDeviceRepository().queryDeviceInformationSet().firstOrError().flatMapObservable($$Lambda$1bAoveU_BkTObiBkj7zXNC5UkA.INSTANCE).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$ConnectivityModule$e-93nl1HEYhMxEIkRoEWrKoNPyc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                MaybeSource map;
                map = AccessorySession.this.getInputRepository().queryConfiguration(((Device.DeviceInformation) obj).getDeviceId()).firstOrError().toMaybe().onErrorResumeWith(Maybe.empty()).map($$Lambda$ConnectivityModule$wcRW8jm1YPxcfUxFCT8aDGNQCNs.INSTANCE);
                return map;
            }
        }).collect($$Lambda$oJx1bDU86H6ff_sN5iFBcQ9fGU.INSTANCE, $$Lambda$GNgS2ClGQJnlYLDDlihsDXwsUPk.INSTANCE).flatMap(new Function() { // from class: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$ConnectivityModule$A4Zzy25SIBGaFcBv41-vyyhUm5s
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return ConnectivityModule.lambda$getInputBehaviorConfigSet$13(ConnectedAccessory.this, (ArrayList) obj);
            }
        });
    }

    private static boolean hasVipFilterConfig(List<Input.InputBehaviorConfiguration> list) {
        boolean z = false;
        boolean z2 = false;
        for (Input.InputBehaviorConfiguration inputBehaviorConfiguration : list) {
            if (inputBehaviorConfiguration.getBehavior() != Input.InputBehavior.UNRECOGNIZED) {
                if (inputBehaviorConfiguration.getBehavior().getNumber() == 22) {
                    z = true;
                }
                if (inputBehaviorConfiguration.getBehavior().getNumber() == 23) {
                    z2 = true;
                }
            }
        }
        return z && z2;
    }

    private void initActiveAccessories() {
        this.clientAccessories.getSessionSupplier().getActiveAccessories().observeOn(AndroidSchedulers.mainThread()).subscribe($$Lambda$ConnectivityModule$onhcQLBbYVbLh7xRD5oBfYMhlKE.INSTANCE, $$Lambda$ConnectivityModule$LpenaNPdAcdaOx1FFc4vacBi6dg.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void initConnectivityModule(Context context, Accessories accessories) throws IllegalArgumentException {
        synchronized (ConnectivityModule.class) {
            Log.d(TAG, "initConnectivityModule called");
            if (mConnectivityModule == null) {
                Log.i(TAG, "initConnectivityModule - First time init");
                if (context != null) {
                    mConnectivityModule = new ConnectivityModule(context, accessories);
                    mConnectivityModule.addConnectivityListener();
                } else {
                    Log.e(TAG, "initConnectivityModule - Context is null, throw exception");
                    throw new IllegalArgumentException("Cannot initialize ConnectivityModule with a null Context.");
                }
            }
            compatibleDevicesAddressMap = new HashMap();
            mConnectivityModule.initActiveAccessories();
            EventBusManager.subscribeToEventBusMessages();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$clientConnectionObservable$14(Accessory accessory) throws Throwable {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryClient: onAccessorySessionConnected: ");
        outline107.append(accessory.getName());
        outline107.append(" --- ");
        outline107.append(accessory.getAddress());
        Log.d(str, outline107.toString());
        updateDeviceConnectivity(accessory, true);
        recordAccessorySessionMetrics(accessory, AccessorySessionLifecycleEvent.CONNECTED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$clientConnectionObservable$16(Accessory accessory) throws Throwable {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryClient: onAccessorySessionReleased: ");
        outline107.append(accessory.getName());
        outline107.append(" --- ");
        outline107.append(accessory.getAddress());
        Log.d(str, outline107.toString());
        updateDeviceConnectivity(accessory, false);
        recordAccessorySessionMetrics(accessory, AccessorySessionLifecycleEvent.RELEASED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SingleSource lambda$getInputBehaviorConfigSet$13(ConnectedAccessory connectedAccessory, ArrayList arrayList) throws Throwable {
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.addAll((List) it2.next());
        }
        connectedAccessory.setConfigList(arrayList2);
        return Single.just(connectedAccessory);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initActiveAccessories$0(List list) throws Throwable {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            Accessory accessory = (Accessory) it2.next();
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryClient: initActiveAccessories -- ");
            outline107.append(accessory.getAddress());
            Log.d(str, outline107.toString());
            updateDeviceConnectivity(accessory, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initActiveAccessories$1(Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "initActiveAccessories -- onError " + th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ConnectedAccessory lambda$null$7(Device.DeviceInformation deviceInformation, Throwable th) throws Throwable {
        return new ConnectedAccessory(deviceInformation.getDeviceType(), -1, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$9(Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "getDeviceDetailedInfo - onError - " + th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$queryVipFilterConfig$5(Accessory accessory, boolean z, boolean z2, ConnectedAccessory connectedAccessory) throws Throwable {
        String deviceType = connectedAccessory.getDeviceType();
        int vipFilterCapableValue = connectedAccessory.getVipFilterCapableValue();
        List<Input.InputBehaviorConfiguration> configList = connectedAccessory.getConfigList();
        boolean z3 = vipFilterCapableValue != -1;
        if (z3 && hasVipFilterConfig(configList)) {
            Log.d(TAG, "is VIP Filter Gesture accessory");
            InputBehaviorConfigProvider.updateInputBehaviorConfig(accessory.getAddress(), z, configList);
        } else if (z3 && "A3IYPH06PH1HRA".equalsIgnoreCase(deviceType)) {
            Log.d(TAG, "is VIP Filter ZION accessory");
            InputBehaviorConfigProvider.updateInputBehaviorConfig(accessory.getAddress(), z, InputConfigGestureStringBuilder.getZionConfigList());
        } else if (z3) {
            Log.d(TAG, "is VIP Filter VOICE accessory");
            InputBehaviorConfigProvider.updateInputBehaviorConfig(accessory.getAddress(), z, InputConfigGestureStringBuilder.getVoiceConfigList());
            if (FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
                DistractionModeProvider.setSilentDistractionMode(deviceType, true);
            } else {
                DistractionModeProvider.setSilentDistractionMode(true);
            }
        }
        if (z2) {
            if (z3) {
                setupDeviceConnectivity(accessory, deviceType, z);
                return;
            }
            String str = TAG;
            Log.i(str, accessory.getName() + " is NOT VIP Filter capable accessory.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$queryVipFilterConfig$6(Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "queryVipFilterConfig: - onError - " + th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$updateDeviceConnectivity$2(Accessory accessory, boolean z, Device.DeviceInformation deviceInformation) throws Throwable {
        String deviceType = deviceInformation.getDeviceType();
        String str = TAG;
        Log.i(str, "AccessoryClient: updateDeviceConnectivity - deviceType " + deviceType);
        if (deviceType.toUpperCase().equals("A3IYPH06PH1HRA")) {
            setupDeviceConnectivity(accessory, deviceType, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$updateDeviceConnectivity$3(Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "AccessoryClient: updateDeviceConnectivity - onError - " + th);
    }

    private static void preconditionMainThread() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException("Current thread must be a main thread");
        Log.e(TAG, "Throwing ", illegalStateException);
        throw illegalStateException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void putEtagToLocalStorage(GetFocusFilterTemplatesFromS3RequestSender.TemplateType templateType, String str) {
        if (str != null) {
            StorageWrapper storageWrapper = new StorageWrapper();
            storageWrapper.putLocal(templateType.name() + "_etag", str);
        }
    }

    private static void queryVipFilterConfig(final Accessory accessory, final boolean z, final boolean z2) {
        String str = TAG;
        Log.d(str, "queryVipFilterConfig: isInitialSetup: " + z2);
        final AccessorySession accessorySession = DependencyProvider.getClientAccessories().getAccessorySession(accessory.getAddress());
        getConnectedAccessoryInfo(accessorySession).flatMap(new Function() { // from class: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$ConnectivityModule$ih6mfpk3uvRyzQi-jbS8YVEOfyI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                SingleSource inputBehaviorConfigSet;
                inputBehaviorConfigSet = ConnectivityModule.getInputBehaviorConfigSet(AccessorySession.this, (ConnectedAccessory) obj);
                return inputBehaviorConfigSet;
            }
        }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$ConnectivityModule$CZKXg9eEsVYmBUde5Cm-XhwW8K4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ConnectivityModule.lambda$queryVipFilterConfig$5(Accessory.this, z, z2, (ConnectedAccessory) obj);
            }
        }, $$Lambda$ConnectivityModule$dMCKrGDkjnmyacpFwFiR8wKeCys.INSTANCE);
    }

    static void recordAccessorySessionMetrics(final Accessory accessory, final AccessorySessionLifecycleEvent accessorySessionLifecycleEvent) {
        preconditionMainThread();
        if (compatibleDevicesAddressMap.containsKey(accessory.getAddress())) {
            recordSessionMetrics(accessorySessionLifecycleEvent);
        } else {
            DependencyProvider.getClientAccessories().getAccessorySession(accessory.getAddress()).getDeviceRepository().queryDeviceInformation().firstOrError().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Device.DeviceInformation>() { // from class: com.amazon.alexa.accessory.notificationpublisher.ConnectivityModule.3
                @Override // io.reactivex.rxjava3.functions.Consumer
                public void accept(@NonNull Device.DeviceInformation deviceInformation) throws Exception {
                    String deviceType = deviceInformation.getDeviceType();
                    if (deviceType.toUpperCase().equals("A3IYPH06PH1HRA")) {
                        ConnectivityModule.compatibleDevicesAddressMap.put(Accessory.this.getAddress(), deviceType);
                        ConnectivityModule.recordSessionMetrics(accessorySessionLifecycleEvent);
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.amazon.alexa.accessory.notificationpublisher.ConnectivityModule.4
                @Override // io.reactivex.rxjava3.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    String str = ConnectivityModule.TAG;
                    Log.e(str, "AccessoryClient: recordAccessorySessionMetrics - onError - " + th);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void recordSessionMetrics(AccessorySessionLifecycleEvent accessorySessionLifecycleEvent) {
        if (accessorySessionLifecycleEvent == null) {
            return;
        }
        int ordinal = accessorySessionLifecycleEvent.ordinal();
        if (ordinal == 0) {
            MetricsRecorder.getInstance().recordAccessorySessionConnected();
        } else if (ordinal != 1) {
        } else {
            MetricsRecorder.getInstance().recordAccessorySessionDisconnected();
        }
    }

    public static synchronized void releaseConnectivityModule() {
        synchronized (ConnectivityModule.class) {
            if (mConnectivityModule != null) {
                Log.d(TAG, "releaseConnectivityModule - remove session listener");
                mConnectivityModule.removeConnectivityListener();
                mConnectivityModule = null;
            }
            compatibleDevicesAddressMap = null;
        }
    }

    private void removeConnectivityListener() {
        Log.d(TAG, "removeListener");
        this.compositeDisposable.dispose();
    }

    static void setupDeviceConnectivity(Accessory accessory, String str, boolean z) {
        compatibleDevicesAddressMap.put(accessory.getAddress(), str);
        if (!FeatureAccessChecker.hasOtgVipFilterAccess() && str.equalsIgnoreCase("A3IYPH06PH1HRA")) {
            InputBehaviorConfigProvider.updateInputBehaviorConfig(accessory.getAddress(), z, InputConfigGestureStringBuilder.getZionConfigList());
        }
        if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
            MultiDeviceDistractionModeProvider.getInstance().onAccessoryConnectionChanged(str, z);
            AccessoryProvider.updateAccessory(accessory, str, z);
            FeatureToggleModule.getInstance().onConnectivityChanged(z);
        } else {
            FeatureToggleModule.getInstance().onConnectivityChanged(z);
            AccessoryProvider.updateAccessory(accessory, str, z);
        }
        BluetoothA2dpConnectionHandler.getInstance().onZionDeviceConnectionStateChanged(z, accessory.getAddress());
        if (str.equalsIgnoreCase("A3IYPH06PH1HRA") || str.equalsIgnoreCase("A3HVREY4JWAZ6K")) {
            BluetoothHeadsetProvider.getProvider().onZionDeviceConnectionStateChanged(z, accessory.getAddress());
        }
        NotificationListenerProxy.create();
        if (z) {
            fetchNotificationTemplatesFromS3();
        }
    }

    static void updateDeviceConnectivity(final Accessory accessory, final boolean z) {
        preconditionMainThread();
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("updateDeviceConnectivity - ");
        outline107.append(accessory.getName());
        outline107.append(" connected: ");
        outline107.append(z);
        Log.i(str, outline107.toString());
        if (compatibleDevicesAddressMap.containsKey(accessory.getAddress())) {
            String str2 = compatibleDevicesAddressMap.get(accessory.getAddress());
            if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
                MultiDeviceDistractionModeProvider.getInstance().onAccessoryConnectionChanged(str2, z);
                AccessoryProvider.updateAccessory(accessory, str2, z);
                FeatureToggleModule.getInstance().onConnectivityChanged(z);
            } else {
                FeatureToggleModule.getInstance().onConnectivityChanged(z);
                AccessoryProvider.updateAccessory(accessory, str2, z);
            }
            if (z && FeatureAccessChecker.hasOtgVipFilterAccess()) {
                queryVipFilterConfig(accessory, z, false);
            }
            BluetoothA2dpConnectionHandler.getInstance().onZionDeviceConnectionStateChanged(z, accessory.getAddress());
            if (str2.equalsIgnoreCase("A3IYPH06PH1HRA") || str2.equalsIgnoreCase("A3HVREY4JWAZ6K")) {
                BluetoothHeadsetProvider.getProvider().onZionDeviceConnectionStateChanged(z, accessory.getAddress());
            }
            NotificationListenerProxy.create();
            if (!z) {
                return;
            }
            fetchNotificationTemplatesFromS3();
        } else if (FeatureAccessChecker.hasOtgVipFilterAccess()) {
            queryVipFilterConfig(accessory, z, true);
        } else {
            DependencyProvider.getClientAccessories().getAccessorySession(accessory.getAddress()).getDeviceRepository().queryDeviceInformationSet().firstOrError().map($$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w.INSTANCE).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.notificationpublisher.-$$Lambda$ConnectivityModule$qXHBYMo8HlNng93oyhKkj8PO6Y8
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    ConnectivityModule.lambda$updateDeviceConnectivity$2(Accessory.this, z, (Device.DeviceInformation) obj);
                }
            }, $$Lambda$ConnectivityModule$fVzy0HqHJTX9yVucKlpfOUo9U0Y.INSTANCE);
        }
    }
}
