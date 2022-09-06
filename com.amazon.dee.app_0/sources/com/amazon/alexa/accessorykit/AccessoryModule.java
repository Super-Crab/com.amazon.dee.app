package com.amazon.alexa.accessorykit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.avsclient.AccessoryDiagnostics;
import com.amazon.alexa.accessory.internal.util.CurrentTimeMillisProvider;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.monitor.BluetoothStateMonitor;
import com.amazon.alexa.accessory.monitor.DefaultBluetoothStateMonitor;
import com.amazon.alexa.accessory.monitor.DefaultLocationStateMonitor;
import com.amazon.alexa.accessory.monitor.LocationStateMonitor;
import com.amazon.alexa.accessory.notificationpublisher.FocusFilterBridgeModule;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.DiagnosticsOuterClass;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessory.speechapi.csm.AlexaAvailabilityHelper;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.alexa.accessoryclient.common.api.ConnectionStatus;
import com.amazon.alexa.accessorykit.accessories.AccessoryModelTransformer;
import com.amazon.alexa.accessorykit.accessories.accessory.AccessorySupplierModule;
import com.amazon.alexa.accessorykit.accessories.locale.UnmatchedLocaleModule;
import com.amazon.alexa.accessorykit.accessories.persistence.DeviceSupplierModule;
import com.amazon.alexa.accessorykit.accessories.scanner.AccessoryScannerModule;
import com.amazon.alexa.accessorykit.accessories.session.device.DeviceRepositoryModule;
import com.amazon.alexa.accessorykit.accessories.session.firmware.FirmwareRepositoryModule;
import com.amazon.alexa.accessorykit.accessories.session.input.InputRepositoryModule;
import com.amazon.alexa.accessorykit.accessories.session.state.StateRepositoryModule;
import com.amazon.alexa.accessorykit.accessories.session.supplier.SessionSupplierModule;
import com.amazon.alexa.accessorykit.accessories.session.system.SystemRepositoryModule;
import com.amazon.alexa.accessorykit.accessories.session.transport.TransportRepositoryModule;
import com.amazon.alexa.accessorykit.audiodelay.AudioDelayConfig;
import com.amazon.alexa.accessorykit.audiodelay.AudioDelayUtils;
import com.amazon.alexa.accessorykit.cloudpairing.CloudPairingRepositoryModule;
import com.amazon.alexa.accessorykit.finishsetup.FinishSetupEvent;
import com.amazon.alexa.accessorykit.finishsetup.bridge.FinishSetupModule;
import com.amazon.alexa.accessorykit.finishsetup.presenters.FASCardPresenter;
import com.amazon.alexa.accessorykit.focusfilter.FocusFilterInfoSupplierModule;
import com.amazon.alexa.accessorykit.internal.RNEventEmitter;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRN;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRNEventId;
import com.amazon.alexa.accessorykit.metrics.MetricRecorder;
import com.amazon.alexa.accessorykit.metrics.MetricsConstants;
import com.amazon.alexa.accessorykit.notifications.LocalNotificationModule;
import com.amazon.alexa.accessorykit.registraton.RegistrationSupplierModule;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.common.collect.ImmutableMap;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import dagger.Lazy;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
@SuppressLint({"CheckResult"})
/* loaded from: classes6.dex */
public class AccessoryModule extends ReactContextBaseJavaModule {
    public static final String DEVICE_INFORMATION_EVENT_NAME = "ama-device-information";
    private static final String MODULE_NAME = "AbaSettingsBridge";
    private static final String QUERY_STATE_EVENT_NAME = "ama-query-state";
    private static final String TAG = "AccessoryModule";
    private final AccessoryInteractor accessoryInteractor;
    private final AccessoryMetricsService accessoryMetricsService;
    private final AccessoryModelTransformer accessoryModelTransformer;
    private final AccessoryScannerModule accessoryScannerModule;
    private final AccessorySupplierModule accessorySupplierModule;
    private final AccessoryActivityLauncher activityLauncher;
    private final BluetoothStateMonitor bluetoothStateMonitor;
    private final BluetoothStateMonitor.Observer bluetoothStateObserver;
    private final Accessories clientAccessories;
    private final CloudPairingRepositoryModule cloudPairingRepositoryModule;
    private final CompositeDisposable compositeDisposable;
    private final DeviceRepositoryModule deviceRepositoryModule;
    private final DeviceSupplierModule deviceSupplierModule;
    private final RNEventEmitter eventEmitter;
    private final FASCardPresenter fasCardPresenter;
    private final FeatureChecker featureChecker;
    private final FinishSetupModule finishSetupModule;
    private final FirmwareRepositoryModule firmwareRepositoryModule;
    private final FocusFilterBridgeModule focusFilterBridgeModule;
    private final FocusFilterInfoSupplierModule focusFilterInfoSupplierModule;
    private final GlobalSessionListener globalSessionListener;
    private final InputRepositoryModule inputRepositoryModule;
    private final LocalNotificationModule localNotificationModule;
    private final LocationStateMonitor locationStateMonitor;
    private final LocationStateMonitor.Observer locationStateObserver;
    private final Handler mainThreadHandler;
    private final ModelTransformer modelTransformer;
    private final ContainerProvider nativeContainerProvider;
    private final ReactApplicationContext reactApplicationContext;
    private final RegistrationSupplierModule registrationSupplierModule;
    private final RxRN rxRN;
    private final RxRN.RxRNCleanupListener rxRNCleanupListener;
    private final SessionSupplierModule sessionSupplierModule;
    private final StateRepositoryModule stateRepositoryModule;
    private final SystemRepositoryModule systemRepositoryModule;
    private final TransportRepositoryModule transportRepositoryModule;
    private final UnmatchedLocaleModule unmatchedLocaleModule;

    @Deprecated
    public AccessoryModule(ReactApplicationContext reactApplicationContext, com.amazon.alexa.accessory.Accessories accessories, MetricsService metricsService) {
        this(reactApplicationContext, AccessoryMetricsServiceHolder.getInstance().get());
    }

    private void addReactLifecycleEventListeners() {
        this.reactApplicationContext.addActivityEventListener(this.focusFilterBridgeModule);
        this.reactApplicationContext.addLifecycleEventListener(this.rxRNCleanupListener);
        this.reactApplicationContext.addLifecycleEventListener(new CompositeDisposableClearOnHostDestroyListener(this.compositeDisposable));
        this.reactApplicationContext.addLifecycleEventListener(this.fasCardPresenter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Completable errorCodeToCompletable(Common.ErrorCode errorCode) {
        if (errorCode == Common.ErrorCode.SUCCESS) {
            return Completable.complete();
        }
        return Completable.error(new IOException("Error code wasn't success"));
    }

    private boolean isPackageInstalledAndEnabled(PackageManager packageManager, String str) {
        for (ApplicationInfo applicationInfo : packageManager.getInstalledApplications(0)) {
            if (applicationInfo.packageName.equals(str) && applicationInfo.enabled) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ Iterable lambda$getAccessories$0(List list) throws Throwable {
        return list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$getBehaviorConfigurationSet$49(List list) throws Throwable {
        HashMap hashMap = new HashMap();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            hashMap.putAll((Map) it2.next());
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Firmware.FirmwareInformation lambda$getFirmwareInformation$9(Set set) throws Throwable {
        return (Firmware.FirmwareInformation) set.iterator().next();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getRegisteredClusterDeviceSerialNumber$6(Promise promise, DeviceRegistration deviceRegistration) throws Throwable {
        if (deviceRegistration.getDeviceRegistrationResponse().getClusterDeviceSerialNumber() != null) {
            promise.resolve(deviceRegistration.getDeviceRegistrationResponse().getClusterDeviceSerialNumber());
        } else {
            promise.reject(new NullPointerException("Registration did not have cluster device serial number"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getSession$28(Promise promise, ConnectionStatus connectionStatus) throws Throwable {
        if (connectionStatus == ConnectionStatus.NONEXISTENT) {
            promise.reject(new IOException("No session for specified device"));
        }
        promise.resolve(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CompletableSource lambda$releaseSession$34(final AccessorySession accessorySession, ConnectionStatus connectionStatus) throws Throwable {
        if (connectionStatus != ConnectionStatus.CONNECTED) {
            return accessorySession.release();
        }
        return accessorySession.getSystemRepository().requestResetConnection(0, false).onErrorResumeWith(Single.just(Common.ErrorCode.SUCCESS)).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$2t8vnW7IwlW50Uur37mvuYPgTqc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Common.ErrorCode errorCode = (Common.ErrorCode) obj;
                return AccessorySession.this.release();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$releaseSession$35(Promise promise) throws Throwable {
        promise.resolve(true);
        recordSessionReleasedUserInitiatedMetric();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$releaseSession$36(Promise promise, Throwable th) throws Throwable {
        promise.resolve(true);
        recordSessionReleasedUserInitiatedMetric();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$sendLogs$14(Promise promise) throws Throwable {
        Logger.d("%s: Successfully uploaded diagnostics", TAG);
        promise.resolve(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$sendLogs$15(Promise promise, Throwable th) throws Throwable {
        Logger.e("%s: Error requesting diagnostics", th, TAG);
        promise.reject(th);
    }

    private static void recordSessionReleasedUserInitiatedMetric() {
        GeneratedOutlineSupport1.outline171(MetricsConstants.Session.SESSION_RELEASED, MetricsConstants.Session.SESSION_RELEASED_REASON_USER_INITIATED, true, null);
    }

    @ReactMethod
    public void accessoryScannerObserveOnBleAccessoryFoundNearby(ReadableMap readableMap) {
        Logger.d("%s: accessoryScannerObserveOnBleAccessoryFoundNearby", TAG);
        this.accessoryScannerModule.observeOnBleAccessoryFoundNearby(RxRNEventId.from(readableMap));
    }

    @ReactMethod
    public void accessoryScannerObserveOnConnectedAccessoryFound(ReadableMap readableMap) {
        Logger.d("%s: accessoryScannerObserveOnConnectedAccessoryFound", TAG);
        this.accessoryScannerModule.observeOnConnectedAccessoryFound(RxRNEventId.from(readableMap));
    }

    @ReactMethod
    public void accessoryScannerObserveOnConnectedAccessoryLost(ReadableMap readableMap) {
        Logger.d("%s: accessoryScannerObserveOnConnectedAccessoryLost", TAG);
        this.accessoryScannerModule.observeOnConnectedAccessoryLost(RxRNEventId.from(readableMap));
    }

    @ReactMethod
    public void accessorySupplierLink(ReadableMap readableMap, Promise promise) {
        Logger.d("%s: accessorySupplierLink", TAG);
        this.accessorySupplierModule.link(readableMap, promise);
    }

    @ReactMethod
    public void accessorySupplierUnlink(ReadableMap readableMap, Promise promise) {
        Logger.d("%s: accessorySupplierUnlink", TAG);
        this.accessorySupplierModule.unlink(readableMap, promise);
    }

    @ReactMethod
    public void areNotificationsEnabled(Promise promise) {
        Logger.d("%s: areNotificationsEnabled", TAG);
        this.localNotificationModule.areNotificationsEnabled(promise);
    }

    @ReactMethod
    public void awaitUntilAccessoryStateBooleanIsTrue(String str, int i, Promise promise) {
        Logger.d("%s: awaitUntilAccessoryStateBooleanIsTrue, identifier: %s, key: %d", TAG, str, Integer.valueOf(i));
        Single firstOrError = this.clientAccessories.getSessionSupplier().getSession(str).getStateRepository().query(StateFeature.from(i)).map($$Lambda$ekrU7cPwm7cchy7hvzK1WlgGZQ.INSTANCE).filter($$Lambda$AccessoryModule$HVvaTKLrngZDGS42fvzvOJYRuI.INSTANCE).firstOrError();
        promise.getClass();
        $$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE __lambda_nrvbijjk1binhmcqxsdc6utczse = new $$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE(promise);
        promise.getClass();
        firstOrError.subscribe(__lambda_nrvbijjk1binhmcqxsdc6utczse, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void awaitUntilDiscoverableOverBluetoothClassic(String str, Promise promise) {
        Logger.d("%s: awaitUntilDiscoverableOverBluetoothClassic, identifier: %s", TAG, str);
        awaitUntilAccessoryStateBooleanIsTrue(str, StateFeature.BLUETOOTH_CLASSIC_DISCOVERABLE.toInteger(), promise);
    }

    @ReactMethod
    public void cancelLocalNotification(int i, Promise promise) {
        Logger.d("%s: cancelLocalNotification, id: %d", TAG, Integer.valueOf(i));
        this.localNotificationModule.cancel(i, promise);
    }

    @ReactMethod
    public void clearAudioDelay(Promise promise) {
        Logger.d("%s: clearAudioDelay", TAG);
        try {
            File audioDelayConfigFileInstance = AudioDelayUtils.getAudioDelayConfigFileInstance(getReactApplicationContext());
            if (audioDelayConfigFileInstance.exists() && audioDelayConfigFileInstance.isFile()) {
                promise.resolve(Boolean.valueOf(audioDelayConfigFileInstance.delete()));
            } else {
                promise.resolve(false);
            }
        } catch (SecurityException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error clearing audio delay: ");
            outline107.append(e.getMessage());
            Log.e(MODULE_NAME, outline107.toString());
            promise.reject(e);
        }
    }

    @ReactMethod
    @Deprecated
    public void clearFocusFilterSettings(Promise promise) {
        Logger.d("%s: clearFocusFilterSettings", TAG);
        this.focusFilterBridgeModule.clear(promise);
    }

    @ReactMethod
    public void clearVipFilterSettings(String str, Promise promise) {
        Logger.d("%s: clearVipFilterSettings, deviceType: %s", TAG, str);
        this.focusFilterBridgeModule.clear(str, promise);
    }

    @ReactMethod
    public void cloudPairingRepositoryGetAttributes(String str, Promise promise) {
        Logger.d("%s: cloudPairingRepositoryGetAttributes", TAG);
        this.cloudPairingRepositoryModule.getCloudPairingAttributes(str, promise);
    }

    @ReactMethod
    public void cloudPairingRepositoryGetPairingStatus(String str, ReadableMap readableMap, Promise promise) {
        Logger.d("%s: cloudPairingRepositoryGetPairingStatus", TAG);
        this.cloudPairingRepositoryModule.getCloudPairingStatus(str, readableMap, promise);
    }

    @ReactMethod
    public void cloudPairingRepositoryRemoveKeys(String str, ReadableMap readableMap, Promise promise) {
        Logger.d("%s: cloudPairingRepositoryRemoveKeys", TAG);
        this.cloudPairingRepositoryModule.removeCloudPairingKeys(str, readableMap, promise);
    }

    @ReactMethod
    public void cloudPairingRepositorySetKeys(String str, ReadableMap readableMap, Promise promise) {
        Logger.d("%s: cloudPairingRepositorySetKeys", TAG);
        this.cloudPairingRepositoryModule.setCloudPairingKeys(str, readableMap, promise);
    }

    @ReactMethod
    public void completeSetup(final String str, final boolean z, final Promise promise) {
        Logger.d("%s: completeSetup, identifier: %s, success: %s", TAG, str, Boolean.valueOf(z));
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$6F3LPPybyj3FVrxlZ9h3pnmMulc
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryModule.this.lambda$completeSetup$27$AccessoryModule(str, promise, z);
            }
        });
    }

    @ReactMethod
    public void createSession(final String str, final Promise promise) {
        Logger.d("%s: createSession, identifier: %s", TAG, str);
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$tdI9lYbdhFDddCU-HR3pyl3G8aA
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryModule.this.lambda$createSession$31$AccessoryModule(str, promise);
            }
        });
    }

    @ReactMethod
    public void deregisterDevice(final String str, final Promise promise) {
        Logger.d("%s: deregisterDevice, identifier: %s", TAG, str);
        Completable andThen = this.clientAccessories.getRegistrationSupplier().deregister(str).observeOn(AndroidSchedulers.mainThread()).andThen(Completable.defer(new Supplier() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$4_Ynz0KXQY0TMY1egMF-nmT6vYo
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return AccessoryModule.this.lambda$deregisterDevice$3$AccessoryModule(str);
            }
        }));
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$XsKzJAUhjRDFeI7Ex7pvJIJxPdw
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(true);
            }
        };
        promise.getClass();
        andThen.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void deviceRepositoryQueryConfiguration(ReadableMap readableMap, String str) {
        Logger.d("%s: deviceRepositoryQueryConfiguration, identifier: %s", TAG, str);
        this.deviceRepositoryModule.queryConfiguration(readableMap, str);
    }

    @ReactMethod
    public void deviceRepositoryQueryDeviceFeatures(ReadableMap readableMap, String str) {
        Logger.d("%s: deviceRepositoryQueryDeviceFeatures, identifier: %s", TAG, str);
        this.deviceRepositoryModule.queryDeviceFeatures(readableMap, str);
    }

    @ReactMethod
    public void deviceRepositoryQueryInformationSet(ReadableMap readableMap, String str) {
        Logger.d("%s: deviceRepositoryQueryInformationSet, identifier: %s", TAG, str);
        this.deviceRepositoryModule.queryInformationSet(readableMap, str);
    }

    @ReactMethod
    public void deviceRepositoryRequestCompleteSetup(String str, boolean z, Promise promise) {
        Logger.d("%s: deviceRepositoryRequestCompleteSetup, identifier: %s, success: %s", TAG, str, Boolean.valueOf(z));
        this.deviceRepositoryModule.requestCompleteSetup(str, z, promise);
    }

    @ReactMethod
    public void deviceRepositoryRequestOverrideAssistant(String str, boolean z, Promise promise) {
        Logger.d("%s: deviceRepositoryRequestOverrideAssistant, identifier: %s, success; %s", TAG, str, Boolean.valueOf(z));
        this.deviceRepositoryModule.requestOverrideAssistant(str, z, promise);
    }

    @ReactMethod
    public void deviceRepositoryRequestStartSetup(String str, Promise promise) {
        Logger.d("%s: deviceRepositoryRequestStartSetup, identifier: %s", TAG, str);
        this.deviceRepositoryModule.requestStartSetup(str, promise);
    }

    @ReactMethod
    public void deviceSupplierGetDeviceGroup(String str, Promise promise) {
        Logger.d("%s: deviceSupplierGetDeviceGroup, identifier: %s", TAG, str);
        this.deviceSupplierModule.getDeviceGroup(str, promise);
    }

    @ReactMethod
    public void deviceSupplierGetDeviceGroupCondition(String str, Promise promise) {
        Logger.d("%s: deviceSupplierGetDeviceGroupCondition, identifier: %s", TAG, str);
        this.deviceSupplierModule.getDeviceGroupCondition(str, promise);
    }

    @ReactMethod
    public void deviceSupplierHasDeviceGroup(String str, Promise promise) {
        Logger.d("%s: deviceSupplierHasDeviceGroup, identifier: %s", TAG, str);
        this.deviceSupplierModule.hasDeviceGroup(str, promise);
    }

    @ReactMethod
    public void deviceSupplierQueryDeviceGroups(ReadableMap readableMap) {
        Logger.d("%s: deviceSupplierQueryDeviceGroups", TAG);
        this.deviceSupplierModule.queryDeviceGroups(readableMap);
    }

    @ReactMethod
    public void deviceSupplierRemoveDeviceGroup(int i, Promise promise) {
        Logger.d("%s: deviceSupplierRemoveDeviceGroup, id: %d", TAG, Integer.valueOf(i));
        this.deviceSupplierModule.removeDeviceGroup(i, promise);
    }

    @ReactMethod
    public void disposeRxRN(ReadableMap readableMap) {
        Logger.d("%s: disposeRxRN", TAG);
        this.rxRN.dispose(RxRNEventId.from(readableMap));
    }

    @ReactMethod
    public void doAssistantOverride(String str, boolean z, final Promise promise) {
        Logger.d("%s: doAssistantOverride, identifier: %s, override: %s", TAG, str, Boolean.valueOf(z));
        Completable flatMapCompletable = this.clientAccessories.getAccessorySession(str).getDeviceRepository().requestOverrideAssistant(z).flatMapCompletable($$Lambda$AccessoryModule$WRingmCHssZVBCsxpJTEiQp__oc.INSTANCE);
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$RwyRwAbvdwKZ0jVzFvBFWlN2Uqk
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(true);
            }
        };
        promise.getClass();
        flatMapCompletable.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void fasCardsAreReady(Promise promise) {
        this.fasCardPresenter.fasCardsAreReady();
        promise.resolve(true);
    }

    @ReactMethod
    public void finishSetupModuleOnBlacklisted(ReadableMap readableMap, Promise promise) {
        Logger.d("%s: finishSetupModuleOnBlacklisted", TAG);
        this.finishSetupModule.onBlacklisted(readableMap, promise);
    }

    @ReactMethod
    public void finishSetupModuleOnContinue(ReadableMap readableMap, Promise promise) {
        Logger.d("%s: finishSetupModuleOnContinue", TAG);
        this.finishSetupModule.onContinue(readableMap, promise);
    }

    @ReactMethod
    public void finishSetupModuleOnDismiss(ReadableMap readableMap, Promise promise) {
        Logger.d("%s: finishSetupModuleOnDismiss", TAG);
        this.finishSetupModule.onDismiss(readableMap, promise);
    }

    @ReactMethod
    public void firmwareRepositoryQueryInformationSet(ReadableMap readableMap, String str) {
        Logger.d("%s: firmwareRepositoryQueryInformationSet, identifier: %s", TAG, str);
        this.firmwareRepositoryModule.queryInformationSet(readableMap, str);
    }

    @ReactMethod
    public void firmwareRepositoryQueryInventoryUpdateBundleSet(ReadableMap readableMap, String str, boolean z) {
        Logger.d("%s: firmwareRepositoryQueryInventoryUpdateBundleSet, identifier: %s, shouldForceCheckInventoryUpdate: %b", TAG, str, Boolean.valueOf(z));
        this.firmwareRepositoryModule.queryInventoryUpdateBundleSet(readableMap, str, z);
    }

    @ReactMethod
    public void firmwareRepositoryQueryUpdateStatus(ReadableMap readableMap, String str) {
        Logger.d("%s: firmwareRepositoryQueryUpdateStatus, identifier: %s", TAG, str);
        this.firmwareRepositoryModule.queryUpdateStatus(readableMap, str);
    }

    @ReactMethod
    public void focusFilterInfoSupplierQueryFocusFilterSettings(ReadableMap readableMap, String str) {
        Logger.d("%s: getObservableFocusFilterSettings, key: %s", TAG, str);
        this.focusFilterInfoSupplierModule.queryFocusFilterSettings(readableMap, str);
    }

    @ReactMethod
    public void getAccessories(Promise promise) {
        Logger.d("%s: getAccessories", TAG);
        Observable observeOn = this.clientAccessories.getDeviceSupplier().queryDeviceGroups().firstOrError().flattenAsObservable($$Lambda$AccessoryModule$rzMqRd669LrcBKpvs0dtOdcBJRQ.INSTANCE).observeOn(AndroidSchedulers.mainThread());
        final AccessoryInteractor accessoryInteractor = this.accessoryInteractor;
        accessoryInteractor.getClass();
        Single list = observeOn.flatMapSingle(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$xMWqmhXZwHP3kY-AOF0G45lL18s
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessoryInteractor.this.registerDeviceClient((DeviceGroup) obj);
            }
        }).toList();
        final ModelTransformer modelTransformer = this.modelTransformer;
        modelTransformer.getClass();
        Single map = list.map(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$8O8uG6VIa0TukiwpCykVnb2UExg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return ModelTransformer.this.accessoryInfoToMapArray((List) obj);
            }
        });
        promise.getClass();
        $$Lambda$u7ImrEiPD2eQnKcoyUvzzuLDfUM __lambda_u7imreipd2eqnkcoyuvzzuldfum = new $$Lambda$u7ImrEiPD2eQnKcoyUvzzuLDfUM(promise);
        promise.getClass();
        map.subscribe(__lambda_u7imreipd2eqnkcoyuvzzuldfum, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void getAccessoryStateBoolean(String str, int i, Promise promise) {
        Logger.d("%s: getAccessoryStateBoolean, identifier: %s, key: %d", TAG, str, Integer.valueOf(i));
        Single firstOrError = this.clientAccessories.getSessionSupplier().getSession(str).getStateRepository().query(StateFeature.from(i)).map($$Lambda$ekrU7cPwm7cchy7hvzK1WlgGZQ.INSTANCE).firstOrError();
        promise.getClass();
        $$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE __lambda_nrvbijjk1binhmcqxsdc6utczse = new $$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE(promise);
        promise.getClass();
        firstOrError.subscribe(__lambda_nrvbijjk1binhmcqxsdc6utczse, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void getAccessoryStateInteger(String str, int i, final Promise promise) {
        Logger.d("%s: getAccessoryStateInteger, identifier: %s, key: %d", TAG, str, Integer.valueOf(i));
        Single<R> map = this.clientAccessories.getSessionSupplier().getSession(str).getStateRepository().query(StateFeature.from(i)).firstOrError().map($$Lambda$ARkVSPIf2OWqlhRmUOw5OSUd1A.INSTANCE);
        promise.getClass();
        Consumer consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$NaRRXppmZX3usm2PRH-yb9aWSQM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve((Integer) obj);
            }
        };
        promise.getClass();
        map.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void getAudioDelay(Promise promise) {
        int i = 0;
        Logger.d("%s: getAudioDelay", TAG);
        try {
            FileReader fileReader = new FileReader(AudioDelayUtils.getAudioDelayConfigFileInstance(getReactApplicationContext()));
            try {
                AudioDelayConfig audioDelayConfig = (AudioDelayConfig) new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson((Reader) fileReader, (Class<Object>) AudioDelayConfig.class);
                if (audioDelayConfig != null) {
                    i = audioDelayConfig.getDelay();
                }
                promise.resolve(Integer.valueOf(i));
                fileReader.close();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        fileReader.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        } catch (JsonParseException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error deserializing audio delay: ");
            outline107.append(e.getMessage());
            Log.e(MODULE_NAME, outline107.toString());
            promise.reject(e);
        } catch (FileNotFoundException e2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Error retrieving audio delay: ");
            outline1072.append(e2.getMessage());
            Log.e(MODULE_NAME, outline1072.toString());
            promise.reject(e2);
        } catch (IOException e3) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Error retrieving audio delay: ");
            outline1073.append(e3.getMessage());
            Log.e(MODULE_NAME, outline1073.toString());
            promise.reject(e3);
        }
    }

    @ReactMethod
    public void getBTInformation(final String str, Promise promise) {
        Logger.d("%s: getBTInformation, identifier: %s", TAG, str);
        final AccessorySession accessorySession = this.clientAccessories.getAccessorySession(str);
        Single<R> flatMap = accessorySession.queryConnectionStatus().flatMap(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$M0plMILY5xmNBQoIQMsHXNAIIhQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessoryModule.this.lambda$getBTInformation$8$AccessoryModule(str, accessorySession, (ConnectionStatus) obj);
            }
        });
        promise.getClass();
        $$Lambda$FlWipKiXyqlqsdu4VNbVtXrTjl0 __lambda_flwipkixyqlqsdu4vnbvtxrtjl0 = new $$Lambda$FlWipKiXyqlqsdu4VNbVtXrTjl0(promise);
        promise.getClass();
        flatMap.subscribe(__lambda_flwipkixyqlqsdu4vnbvtxrtjl0, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void getBehaviorConfigurationSet(String str, final Promise promise) {
        Logger.d("%s: getBehaviorConfigurationSet, identifier: %s", TAG, str);
        final AccessorySession session = this.clientAccessories.getSessionSupplier().getSession(str);
        Single map = session.getDeviceRepository().queryDeviceInformationSet().firstOrError().flatMapObservable($$Lambda$1bAoveU_BkTObiBkj7zXNC5UkA.INSTANCE).flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$qAkdatX5r8Jst-_Rn9jrgiU0fUw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                MaybeSource map2;
                map2 = AccessorySession.this.getInputRepository().queryConfiguration(r2.getDeviceId()).firstOrError().toMaybe().onErrorResumeWith(Maybe.empty()).map(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$yhI_SXIHCwOWnBuvQ0caWTw08_8
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply */
                    public final Object mo10358apply(Object obj2) {
                        Map singletonMap;
                        singletonMap = Collections.singletonMap(Integer.valueOf(Device.DeviceInformation.this.getDeviceId()), (Input.InputBehaviorConfigurationSet) obj2);
                        return singletonMap;
                    }
                });
                return map2;
            }
        }).toList().map($$Lambda$AccessoryModule$Iqdmd7Tyzpc_VMMQQDG358TsIls.INSTANCE).map($$Lambda$tzzVg5QjlAwMvcqlyMfOCcee4K4.INSTANCE);
        promise.getClass();
        Consumer consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$beIs2CaAxtnYgfwLvuEE6SpdA_U
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve((ReadableMap) obj);
            }
        };
        promise.getClass();
        map.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map<String, Object> getConstants() {
        return ImmutableMap.builder().mo7828put("connectionEventName", GlobalSessionListener.ACCESSORY_CONNECTION_EVENT_NAME).mo7828put("deviceInformationEventName", DEVICE_INFORMATION_EVENT_NAME).mo7828put("rxrnEventName", RxRN.RxRNEvent.EVENT_ID).mo7828put("finishSetupPresentEventName", FASCardPresenter.PRESENT_EVENT).mo7828put("finishSetupDismissEventName", FASCardPresenter.DISMISS_EVENT).mo7826build();
    }

    @ReactMethod
    public void getDeviceInformationV2(String str, Promise promise) {
        Logger.d("%s: getDeviceInformationV2, identifier: %s", TAG, str);
        Single<Set<Device.DeviceInformation>> firstOrError = this.clientAccessories.getSessionSupplier().getSession(str).getDeviceRepository().queryDeviceInformationSet().firstOrError();
        final ModelTransformer modelTransformer = this.modelTransformer;
        modelTransformer.getClass();
        Single observeOn = firstOrError.map(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$GU5yJHQ0Awk6Y1oWvc7j6U0TrGU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return ModelTransformer.this.toMap((Set) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread());
        promise.getClass();
        $$Lambda$u7ImrEiPD2eQnKcoyUvzzuLDfUM __lambda_u7imreipd2eqnkcoyuvzzuldfum = new $$Lambda$u7ImrEiPD2eQnKcoyUvzzuLDfUM(promise);
        promise.getClass();
        observeOn.subscribe(__lambda_u7imreipd2eqnkcoyuvzzuldfum, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void getFirmwareInformation(String str, Promise promise) {
        Logger.d("%s: getFirmwareInformation, identifier: %s", TAG, str);
        Single<R> map = this.clientAccessories.getSessionSupplier().getSession(str).getFirmwareRepository().queryInformationSet().map($$Lambda$AccessoryModule$bNkd3eXN8wH1xFmCAWIXQXZKI10.INSTANCE);
        final ModelTransformer modelTransformer = this.modelTransformer;
        modelTransformer.getClass();
        Single map2 = map.map(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$EAxllJC69HkJFHrmO-OjpP_Q2cM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return ModelTransformer.this.toMap((Firmware.FirmwareInformation) obj);
            }
        });
        promise.getClass();
        $$Lambda$FlWipKiXyqlqsdu4VNbVtXrTjl0 __lambda_flwipkixyqlqsdu4vnbvtxrtjl0 = new $$Lambda$FlWipKiXyqlqsdu4VNbVtXrTjl0(promise);
        promise.getClass();
        map2.subscribe(__lambda_flwipkixyqlqsdu4vnbvtxrtjl0, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    @Deprecated
    public void getFocusFilterSettings(String str, Promise promise) {
        Preconditions.notNull(str, "key");
        Logger.d("%s: getFocusFilterSettings, key: %s", TAG, str);
        this.focusFilterBridgeModule.get(str, promise);
    }

    @ReactMethod
    @SuppressLint({"CheckResult"})
    public void getIdentifierForAccountId(String str, final Promise promise) {
        Logger.d("%s: getIdentifierForAccountId, deviceAccountId: %s", TAG, str);
        Single<String> deviceIdentifier = this.clientAccessories.getDeviceAccountSupplier().getDeviceIdentifier(str);
        promise.getClass();
        Consumer<? super String> consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$JXMiO5RS-1OjCh6hdfNXQ8VBx2E
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve((String) obj);
            }
        };
        promise.getClass();
        deviceIdentifier.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void getNeedsSetup(String str, Promise promise) {
        Logger.d("%s: getNeedsSetup, identifier: %s", TAG, str);
        Single<R> map = this.clientAccessories.getAccessorySession(str).getDeviceRepository().queryDeviceConfiguration().firstOrError().map($$Lambda$feSo_h81h32S9hYGtVQglsHhKWw.INSTANCE);
        promise.getClass();
        $$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE __lambda_nrvbijjk1binhmcqxsdc6utczse = new $$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE(promise);
        promise.getClass();
        map.subscribe(__lambda_nrvbijjk1binhmcqxsdc6utczse, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    @SuppressLint({"CheckResult"})
    public void getRegisteredClusterDeviceSerialNumber(String str, final Promise promise) {
        Logger.d("%s: getRegisteredClusterDeviceSerialNumber, identifier: %s", TAG, str);
        Single<DeviceRegistration> deviceRegistration = this.clientAccessories.getRegistrationSupplier().getDeviceRegistration(str);
        Consumer<? super DeviceRegistration> consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$tM_1JYzhQ82n6-DVipBUspTp6D8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryModule.lambda$getRegisteredClusterDeviceSerialNumber$6(Promise.this, (DeviceRegistration) obj);
            }
        };
        promise.getClass();
        deviceRegistration.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void getRegisteredName(String str, final Promise promise) {
        Logger.d("%s: getRegisteredName, identifier: %s", TAG, str);
        Single<DeviceRegistration> deviceRegistration = this.clientAccessories.getRegistrationSupplier().getDeviceRegistration(str);
        Consumer<? super DeviceRegistration> consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$iNO3SPTbkIMk2m7tpEFCflATeSQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve(((DeviceRegistration) obj).getDeviceRegistrationResponse().getName());
            }
        };
        promise.getClass();
        deviceRegistration.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void getSession(String str, final Promise promise) {
        Logger.d("%s: getSession, identifier: %s", TAG, str);
        this.clientAccessories.getSessionSupplier().getSession(str).queryConnectionStatus().subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$ZGO2vi0X-p8KG9wsz2bdBzOrP_w
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryModule.lambda$getSession$28(Promise.this, (ConnectionStatus) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$3rJ6IrBZL-_Z0j6-vufA4fGqLqw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                Promise.this.reject(new IOException("No session for specified device"));
            }
        });
    }

    @ReactMethod
    @SuppressLint({"CheckResult"})
    public void getUserDeviceName(String str, final Promise promise) {
        Logger.d("%s: getUserDeviceName, identifier: %s", TAG, str);
        Single<DeviceRegistration> deviceRegistration = this.clientAccessories.getRegistrationSupplier().getDeviceRegistration(str);
        Consumer<? super DeviceRegistration> consumer = new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$sIlP5QfTuEWEQxuwcYki-BMcvdM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Promise.this.resolve(((DeviceRegistration) obj).getDeviceRegistrationResponse().getUserDeviceName());
            }
        };
        promise.getClass();
        deviceRegistration.subscribe(consumer, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void getVipFilterSettings(String str, String str2, Promise promise) {
        Preconditions.notNull(str2, "key");
        Logger.d("%s: getVipFilterSettings, deviceType: %s key: %s", TAG, str, str2);
        this.focusFilterBridgeModule.get(str, str2, promise);
    }

    @ReactMethod
    public void hasNotificationPermission(Promise promise) {
        Logger.d("%s: hasNotificationPermission", TAG);
        this.focusFilterBridgeModule.hasNotificationPermission(promise);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        super.initialize();
        this.eventEmitter.initialize(this.reactApplicationContext);
        startEmittingBridgeEvents();
    }

    @ReactMethod
    public void initiateFirmwareTransfer(String str, Promise promise) {
        Logger.d("%s: initiateFirmwareTransfer, identifier: %s", TAG, str);
        this.firmwareRepositoryModule.initiateFirmwareTransfer(str, promise);
    }

    @ReactMethod
    public void inputRepositoryQueryConfiguration(ReadableMap readableMap, String str, int i) {
        Logger.d("%s: inputRepositoryQueryConfiguration, identifier: %s, deviceId: %d", TAG, str, Integer.valueOf(i));
        this.inputRepositoryModule.queryConfiguration(readableMap, str, i);
    }

    @ReactMethod
    public void inputRepositoryResetConfiguration(String str, int i, Promise promise) {
        Logger.d("%s: inputRepositoryResetConfiguration, identifier: %s, deviceId: %d", TAG, str, Integer.valueOf(i));
        this.inputRepositoryModule.resetConfiguration(str, i, promise);
    }

    @ReactMethod
    public void inputRepositorySetConfiguration(String str, int i, ReadableMap readableMap, Promise promise) {
        Logger.d("%s: inputRepositorySetConfiguration, identifier: %s, deviceId: %d", TAG, str, Integer.valueOf(i));
        this.inputRepositoryModule.setConfiguration(str, i, readableMap, promise);
    }

    @ReactMethod
    public void isAlexaUnavailableDueToUnsupportedMarketPlaceOnFireTablets(Promise promise) {
        try {
            Logger.d("%s: isAlexaUnavailableDueToUnsupportedMarketPlaceOnFireTablets", TAG);
            boolean isAlexaUnavailableDueToUnsupportedMarketPlace = AlexaAvailabilityHelper.Companion.isAlexaUnavailableDueToUnsupportedMarketPlace(getReactApplicationContext().getApplicationContext());
            Logger.d("%s: Is Alexa unavailable due to unsupported market place: %b", TAG, Boolean.valueOf(isAlexaUnavailableDueToUnsupportedMarketPlace));
            promise.resolve(Boolean.valueOf(isAlexaUnavailableDueToUnsupportedMarketPlace));
        } catch (Exception e) {
            Logger.e("%s: Error fetching Alexa availability on Fire tablet.", e, TAG);
            promise.reject(e);
        }
    }

    @ReactMethod
    @RequiresApi(api = 26)
    public void isCompanionDevice(String str, Promise promise) {
        Logger.d("%s: isCompanionDevice, identifier: %s", TAG, str);
        Preconditions.notNull(str, "identifier");
        int i = Build.VERSION.SDK_INT;
        Single<Boolean> isCompanionDevice = this.clientAccessories.getCompanionDeviceModule().isCompanionDevice(str);
        promise.getClass();
        $$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE __lambda_nrvbijjk1binhmcqxsdc6utczse = new $$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE(promise);
        promise.getClass();
        isCompanionDevice.subscribe(__lambda_nrvbijjk1binhmcqxsdc6utczse, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void isConnected(String str, final Promise promise) {
        Logger.d("%s: isConnected, identifier: %s", TAG, str);
        this.clientAccessories.getSessionSupplier().getSession(str).queryConnectionStatus().subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$8Jg7piTKKHbJhDmTPpyta69heo8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ConnectionStatus connectionStatus = (ConnectionStatus) obj;
                Promise.this.resolve(Boolean.valueOf(r2 == ConnectionStatus.CONNECTED));
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$im4tzaiNDZGj2USrPmF0LruIvsQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                Promise.this.resolve(false);
            }
        });
    }

    @ReactMethod
    public void isDiscoverableOverBluetoothClassic(String str, Promise promise) {
        Logger.d("%s: isDiscoverableOverBluetoothClassic, identifier: %s", TAG, str);
        getAccessoryStateBoolean(str, StateFeature.BLUETOOTH_CLASSIC_DISCOVERABLE.toInteger(), promise);
    }

    public /* synthetic */ void lambda$completeSetup$27$AccessoryModule(final String str, final Promise promise, final boolean z) {
        if (this.accessoryInteractor.getAccessory(str) == null) {
            this.accessoryMetricsService.recordOccurrence(MetricsConstants.Oobe.COMPLETE_SETUP_FAILED_NO_ACCESSORY_IN_INTERACTOR, "UNKNOWN", true, null);
            promise.reject(new IOException(GeneratedOutlineSupport1.outline72("No session for device ", str)));
            return;
        }
        this.accessoryMetricsService.recordOccurrence(MetricsConstants.Oobe.COMPLETE_SETUP_FAILED_NO_ACCESSORY_IN_INTERACTOR, "UNKNOWN", false, null);
        AccessorySession accessorySession = this.clientAccessories.getAccessorySession(str);
        final DeviceRepositoryV2 deviceRepository = accessorySession.getDeviceRepository();
        Completable andThen = accessorySession.queryConnectionStatus().flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$B1VBXfKtb6dKoBYAQH4eDzsaRgk
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessoryModule.this.lambda$null$25$AccessoryModule(str, deviceRepository, z, (ConnectionStatus) obj);
            }
        }).andThen(z ? this.accessoryInteractor.linkAccessoryClient(str) : Completable.complete());
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$ataqFbWAUY28inRWFe9AB649WsM
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(Boolean.valueOf(z));
            }
        };
        promise.getClass();
        andThen.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    public /* synthetic */ void lambda$createSession$31$AccessoryModule(String str, final Promise promise) {
        Completable createSessionClient = this.accessoryInteractor.createSessionClient(str);
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$rOUsoW6vIkZkE_VTcUAZcjJDr-M
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(true);
            }
        };
        promise.getClass();
        createSessionClient.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    public /* synthetic */ CompletableSource lambda$deregisterDevice$3$AccessoryModule(String str) throws Throwable {
        return this.clientAccessories.unlinkAccessory(this.accessoryInteractor.getAccessory(str));
    }

    public /* synthetic */ SingleSource lambda$getBTInformation$8$AccessoryModule(String str, AccessorySession accessorySession, ConnectionStatus connectionStatus) throws Throwable {
        if (connectionStatus == ConnectionStatus.NONEXISTENT) {
            Single<DeviceGroup> deviceGroup = this.clientAccessories.getDeviceSupplier().getDeviceGroup(str);
            final ModelTransformer modelTransformer = this.modelTransformer;
            modelTransformer.getClass();
            return deviceGroup.map(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$jeyeTJ_BevYI4OVijcmFjdxVHPU
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return ModelTransformer.this.toMap((DeviceGroup) obj);
                }
            });
        }
        Single<Device.DeviceInformation> firstOrError = accessorySession.getDeviceRepository().queryDeviceInformation().firstOrError();
        final ModelTransformer modelTransformer2 = this.modelTransformer;
        modelTransformer2.getClass();
        return firstOrError.map(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$P31te-Zc2jxdBHg8svcZ4em6MqY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return ModelTransformer.this.toMap((Device.DeviceInformation) obj);
            }
        });
    }

    public /* synthetic */ void lambda$null$19$AccessoryModule(DeviceRepositoryV2 deviceRepositoryV2) throws Throwable {
        DeviceRepositoryModule.publishMetricsWithDeviceType(deviceRepositoryV2, MetricsConstants.Oobe.START_SETUP_REQUEST_SUCCESS, true, this.accessoryMetricsService);
    }

    public /* synthetic */ void lambda$null$20$AccessoryModule(DeviceRepositoryV2 deviceRepositoryV2, Throwable th) throws Throwable {
        DeviceRepositoryModule.publishMetricsWithDeviceType(deviceRepositoryV2, MetricsConstants.Oobe.START_SETUP_REQUEST_SUCCESS, false, this.accessoryMetricsService);
    }

    public /* synthetic */ void lambda$null$23$AccessoryModule(DeviceRepositoryV2 deviceRepositoryV2) throws Throwable {
        DeviceRepositoryModule.publishMetricsWithDeviceType(deviceRepositoryV2, MetricsConstants.Oobe.COMPLETE_SETUP_REQUEST_SUCCESS, true, this.accessoryMetricsService);
    }

    public /* synthetic */ void lambda$null$24$AccessoryModule(DeviceRepositoryV2 deviceRepositoryV2, Throwable th) throws Throwable {
        DeviceRepositoryModule.publishMetricsWithDeviceType(deviceRepositoryV2, MetricsConstants.Oobe.COMPLETE_SETUP_REQUEST_SUCCESS, false, this.accessoryMetricsService);
    }

    public /* synthetic */ CompletableSource lambda$null$25$AccessoryModule(String str, final DeviceRepositoryV2 deviceRepositoryV2, boolean z, ConnectionStatus connectionStatus) throws Throwable {
        if (connectionStatus == ConnectionStatus.NONEXISTENT) {
            this.accessoryMetricsService.recordOccurrence(MetricsConstants.Oobe.COMPLETE_SETUP_FAILED_NO_SESSION, "UNKNOWN", true, null);
            return Completable.error(new Exception(GeneratedOutlineSupport1.outline72("No session for identifier ", str)));
        }
        this.accessoryMetricsService.recordOccurrence(MetricsConstants.Oobe.COMPLETE_SETUP_FAILED_NO_SESSION, "UNKNOWN", false, null);
        return deviceRepositoryV2.requestCompleteSetup(z).flatMapCompletable($$Lambda$AccessoryModule$WRingmCHssZVBCsxpJTEiQp__oc.INSTANCE).doOnComplete(new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$iIfB7QYxG-itGGpy9O43J8vIuks
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AccessoryModule.this.lambda$null$23$AccessoryModule(deviceRepositoryV2);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$AlHuNGKVXG6onXcKRSfMdBzfltg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryModule.this.lambda$null$24$AccessoryModule(deviceRepositoryV2, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ void lambda$queryAccessoryState$41$AccessoryModule(String str, WritableMap writableMap) throws Throwable {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
    }

    public /* synthetic */ void lambda$registerAccessory$32$AccessoryModule(ReadableMap readableMap, Promise promise) {
        try {
            Accessory mo626transform = this.accessoryModelTransformer.mo626transform(readableMap);
            this.accessoryInteractor.registerAccessory(mo626transform.getAddress(), mo626transform);
            promise.resolve(true);
        } catch (ParseException e) {
            Logger.e("Unable to register accessory.", e);
            promise.reject(e);
        }
    }

    public /* synthetic */ void lambda$removeDevice$2$AccessoryModule(String str, final Promise promise) {
        Accessory accessory = this.accessoryInteractor.getAccessory(str);
        if (accessory == null) {
            promise.reject(new Exception(GeneratedOutlineSupport1.outline72("No such accessory: ", str)));
        }
        Completable unlinkAccessory = this.clientAccessories.unlinkAccessory(accessory);
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$29cw5b_DuiaHjAMcBNzBIqJsr8s
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(true);
            }
        };
        promise.getClass();
        unlinkAccessory.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    public /* synthetic */ void lambda$startEmittingBridgeEvents$44$AccessoryModule() {
        this.bluetoothStateMonitor.addObserver(this.bluetoothStateObserver);
        this.locationStateMonitor.addObserver(this.locationStateObserver);
    }

    public /* synthetic */ CompletableSource lambda$startSetup$21$AccessoryModule(String str, final DeviceRepositoryV2 deviceRepositoryV2, ConnectionStatus connectionStatus) throws Throwable {
        if (connectionStatus == ConnectionStatus.NONEXISTENT) {
            this.accessoryMetricsService.recordOccurrence(MetricsConstants.Oobe.START_SETUP_FAILED_NO_SESSION, "UNKNOWN", true, null);
            return Completable.error(new Exception(GeneratedOutlineSupport1.outline72("No session for identifier ", str)));
        }
        this.accessoryMetricsService.recordOccurrence(MetricsConstants.Oobe.START_SETUP_FAILED_NO_SESSION, "UNKNOWN", false, null);
        return deviceRepositoryV2.requestStartSetup().flatMapCompletable($$Lambda$AccessoryModule$WRingmCHssZVBCsxpJTEiQp__oc.INSTANCE).doOnComplete(new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$NJndfbArJvAEUyILW5fzZkDJtvg
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AccessoryModule.this.lambda$null$19$AccessoryModule(deviceRepositoryV2);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$fwbGAkwzWMhps6TUuh4C4qH_t2Q
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryModule.this.lambda$null$20$AccessoryModule(deviceRepositoryV2, (Throwable) obj);
            }
        });
    }

    @ReactMethod
    public void launchCustomIntent(String str, String str2, Promise promise) {
        Preconditions.notNull(str, CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME);
        Logger.d("%s: launchCustomIntent, packageName: %s, className: %s", TAG, str, str2);
        PackageManager packageManager = getReactApplicationContext().getPackageManager();
        if (!isPackageInstalledAndEnabled(packageManager, str)) {
            promise.reject(new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Application not installed ", str)));
        } else if (TextUtils.isEmpty(str2)) {
            Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str);
            if (launchIntentForPackage != null) {
                this.activityLauncher.startActivity(launchIntentForPackage, promise);
            } else {
                promise.reject(new IllegalArgumentException(GeneratedOutlineSupport1.outline72("No Intent to launch ", str)));
            }
        } else {
            Intent className = new Intent().setPackage(str).setClassName(str, str2);
            if (className.resolveActivity(packageManager) == null) {
                promise.reject(new IllegalArgumentException(GeneratedOutlineSupport1.outline76("Failed to resolve custom intent ", str, ":", str2)));
            } else {
                this.activityLauncher.startActivity(className, promise);
            }
        }
    }

    @ReactMethod
    public void needsAssistantOverride(String str, Promise promise) {
        Logger.d("%s: needsAssistantOverride, identifier: %s", TAG, str);
        Single firstOrError = this.clientAccessories.getAccessorySession(str).getDeviceRepository().queryDeviceConfiguration().map($$Lambda$HzMU9gqmGMUbLlBiO4fNlyHPAVA.INSTANCE).firstOrError();
        promise.getClass();
        $$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE __lambda_nrvbijjk1binhmcqxsdc6utczse = new $$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE(promise);
        promise.getClass();
        firstOrError.subscribe(__lambda_nrvbijjk1binhmcqxsdc6utczse, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void openAppSettings(Promise promise) {
        Logger.d("%s: openAppSettings", TAG);
        Intent addCategory = new Intent().setAction("android.settings.APPLICATION_DETAILS_SETTINGS").addCategory("android.intent.category.DEFAULT");
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("package:");
        outline107.append(getReactApplicationContext().getPackageName());
        this.activityLauncher.startActivity(addCategory.setData(Uri.parse(outline107.toString())), promise);
    }

    @ReactMethod
    @Deprecated
    public void putFocusFilterSettings(String str, String str2, String str3, Promise promise) {
        Preconditions.notNull(str, "key");
        Preconditions.notNull(str2, "valueString");
        Logger.d("%s: putFocusFilterSettings, key: %s, valueString: %s", TAG, str, str2);
        this.focusFilterBridgeModule.put(str, str2, str3, promise);
    }

    @ReactMethod
    public void putVipFilterSettings(String str, String str2, String str3, Promise promise) {
        Preconditions.notNull(str2, "key");
        Preconditions.notNull(str3, "valueString");
        Logger.d("%s: putVipFilterSettings, deviceType: %s, key: %s, valueString: %s", TAG, str, str2, str3);
        this.focusFilterBridgeModule.put(str, str2, str3, str2, promise);
    }

    @ReactMethod
    public void queryAccessoryState(String str, int i, Promise promise) {
        Logger.d("%s: queryAccessoryState, identifier: %s, key: %d", TAG, str, Integer.valueOf(i));
        final String format = String.format(Locale.US, "%s_%s_%d", QUERY_STATE_EVENT_NAME, str, Integer.valueOf(i));
        Flowable<StateOuterClass.State> query = this.clientAccessories.getSessionSupplier().getSession(str).getStateRepository().query(StateFeature.from(i));
        final ModelTransformer modelTransformer = this.modelTransformer;
        modelTransformer.getClass();
        query.map(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$MM3RrtOiZr19FpyAtNp5nV76lwg
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return ModelTransformer.this.toMap((StateOuterClass.State) obj);
            }
        }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$Lfmynu9IY87p4WzBXEGStHmazgs
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryModule.this.lambda$queryAccessoryState$41$AccessoryModule(format, (WritableMap) obj);
            }
        }, $$Lambda$AccessoryModule$o0QfNg9OScn_DSz4M2X1GXxPQoo.INSTANCE);
        promise.resolve(format);
    }

    @ReactMethod
    public void querySystemRepositoryI18NConfig(ReadableMap readableMap, String str) {
        Logger.d("%s: querySystemRepositoryI18NConfig, identifier: %s", TAG, str);
        this.systemRepositoryModule.queryI18NConfig(readableMap, str);
    }

    @ReactMethod
    public void registerAccessory(final ReadableMap readableMap, final Promise promise) {
        Logger.d("%s: registerAccessory", TAG);
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$LYvZ2dgvforEYc9BvXT51HUBrQ4
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryModule.this.lambda$registerAccessory$32$AccessoryModule(readableMap, promise);
            }
        });
    }

    @ReactMethod
    public void registrationSupplierModuleGetDeviceRegistration(String str, Promise promise) {
        Logger.d("%s: registrationSupplierModuleGetDeviceRegistration", TAG);
        this.registrationSupplierModule.getDeviceRegistration(str, promise);
    }

    @ReactMethod
    public void registrationSupplierModuleQueryDeviceRegistrations(ReadableMap readableMap) {
        Logger.d("%s: registrationSupplierModuleQueryDeviceRegistrations", TAG);
        this.registrationSupplierModule.queryRegistrations(readableMap);
    }

    @ReactMethod
    public void releaseSession(String str, final Promise promise) {
        Logger.d("%s: releaseSession, identifier: %s", TAG, str);
        final AccessorySession accessorySession = this.clientAccessories.getAccessorySession(str);
        accessorySession.queryConnectionStatus().flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$ecMJ0PFe2_jyyPI7n1Wst1G_GtY
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessoryModule.lambda$releaseSession$34(AccessorySession.this, (ConnectionStatus) obj);
            }
        }).subscribe(new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$zAvbbCIKHkSzuUutTBv5KqhYeUg
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AccessoryModule.lambda$releaseSession$35(Promise.this);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$J_tDPG4XR-TrGn3LdLbmdptJZiE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryModule.lambda$releaseSession$36(Promise.this, (Throwable) obj);
            }
        });
    }

    @ReactMethod
    public void removeDevice(final String str, final Promise promise) {
        Logger.d("%s: removeDevice, identifier: %s", TAG, str);
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$tIrDfOhfADgEh2ajX1smDnloUgo
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryModule.this.lambda$removeDevice$2$AccessoryModule(str, promise);
            }
        });
    }

    @ReactMethod
    @Deprecated
    public void removeFocusFilterSettings(String str, String str2, Promise promise) {
        Preconditions.notNull(str, "key");
        Logger.d("%s: removeFocusFilterSettings, key: %s", TAG, str);
        this.focusFilterBridgeModule.remove(str, str2, promise);
    }

    @ReactMethod
    public void removeVipFilterSettings(String str, String str2, Promise promise) {
        Preconditions.notNull(str2, "key");
        Logger.d("%s: removeVipFilterSettings, deviceType: %s, key: %s", TAG, str, str2);
        this.focusFilterBridgeModule.remove(str, str2, str2, promise);
    }

    @ReactMethod
    public void requestBluetoothPermissions(Promise promise) {
        if (com.amazon.alexa.accessory.Accessories.shouldRequestEnableBluetooth(getReactApplicationContext())) {
            this.activityLauncher.startActivity(com.amazon.alexa.accessory.Accessories.createRequestEnableBluetoothStatic(), promise);
        } else {
            promise.resolve(null);
        }
    }

    @ReactMethod
    @RequiresApi(api = 26)
    @SuppressLint({"CheckResult"})
    public void requestCompanionDevice(String str, Promise promise) {
        Logger.d("%s: requestCompanionDevice, identifier: %s", TAG, str);
        Preconditions.notNull(str, "identifier");
        int i = Build.VERSION.SDK_INT;
        Single<Boolean> requestCompanionDevice = this.clientAccessories.getCompanionDeviceModule().requestCompanionDevice(str);
        promise.getClass();
        $$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE __lambda_nrvbijjk1binhmcqxsdc6utczse = new $$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE(promise);
        promise.getClass();
        requestCompanionDevice.subscribe(__lambda_nrvbijjk1binhmcqxsdc6utczse, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void requestIgnoreBatteryOptimizations(Promise promise) {
        Logger.d("%s: requestIgnoreBatteryOptimizations", TAG);
        if (com.amazon.alexa.accessory.Accessories.shouldRequestIgnoreBatteryOptimizations(getReactApplicationContext())) {
            this.activityLauncher.startActivity(com.amazon.alexa.accessory.Accessories.createRequestIgnoreBatteryOptimizations(getReactApplicationContext()), promise);
        } else {
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void requestTransportUpgrade(String str, final Promise promise) {
        Logger.d("%s: requestTransportUpgrade, identifier: %s", TAG, str);
        Completable requestUpgrade = this.clientAccessories.getAccessorySession(str).getTransportRepository().requestUpgrade();
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$kTFBzXQ84hTJdbZYJQ4NaDSx_ns
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(true);
            }
        };
        promise.getClass();
        requestUpgrade.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void requestUpdateDeviceInformation(String str, String str2, final Promise promise) {
        Logger.d("%s: requestUpdateDeviceInformation, identifier: %s, updatedName: %s", TAG, str, str2);
        Completable flatMapCompletable = this.clientAccessories.getAccessorySession(str).getDeviceRepository().requestUpdateDeviceInformation(str2).flatMapCompletable($$Lambda$AccessoryModule$WRingmCHssZVBCsxpJTEiQp__oc.INSTANCE);
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$LEtmGG9lUgTHB4e-SbD75NxJz-U
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(true);
            }
        };
        promise.getClass();
        flatMapCompletable.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void sendLogs(String str, final Promise promise) {
        Logger.d("%s: sendLogs, identifier: %s", TAG, str);
        final AccessorySession accessorySession = this.clientAccessories.getAccessorySession(str);
        AccessoryDiagnostics.getAccessoryDeviceWithHighestDeviceId(accessorySession).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$31fg53yXcOQAfXwQAkW2Z3BWLvk
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                CompletableSource andThen;
                andThen = AccessoryDiagnostics.uploadAccessoryClientDiagnostics(r0, r2, DiagnosticsOuterClass.DiagnosticsType.LOG).onErrorResumeNext($$Lambda$AccessoryModule$Hh_8OGYtZ5NT8lmdmqZtgaym1s.INSTANCE).andThen(AccessoryDiagnostics.uploadAccessoryClientDiagnostics(AccessorySession.this, r2, DiagnosticsOuterClass.DiagnosticsType.CRASH_REPORT).onErrorResumeNext($$Lambda$AccessoryModule$HJ11QHgagMpY9_f2RXSQrA7gXk.INSTANCE)).andThen(AccessoryDiagnostics.uploadApplicationLogs((AccessoryDiagnostics.AccessoryDevice) obj).onErrorResumeNext($$Lambda$AccessoryModule$1e5CG8PMUL2CDqSQaO7xmwuDQI.INSTANCE));
                return andThen;
            }
        }).subscribe(new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$-fGOXYIfqEY7zHPv7yWFCY0JClg
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AccessoryModule.lambda$sendLogs$14(Promise.this);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$sd5kGLud1tRGzzrfpjipoZh7fuc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryModule.lambda$sendLogs$15(Promise.this, (Throwable) obj);
            }
        });
    }

    @ReactMethod
    public void sessionSupplierConnectSession(ReadableMap readableMap, Promise promise) {
        Logger.d("%s: sessinoSupplierConnectSession", TAG);
        this.sessionSupplierModule.connectSession(readableMap, promise);
    }

    @ReactMethod
    public void sessionSupplierGetSessionIdentifiers(Promise promise) {
        Logger.d("%s: sessionSupplierGetSessionIdentifiers", TAG);
        this.sessionSupplierModule.getSessionIdentifiers(promise);
    }

    @ReactMethod
    public void sessionSupplierHasSession(String str, Promise promise) {
        Logger.d("%s: sessionSupplierHasSession, identifier: %s", TAG, str);
        this.sessionSupplierModule.hasSession(str, promise);
    }

    @ReactMethod
    public void sessionSupplierHasSessions(Promise promise) {
        Logger.d("%s: sessionSupplierHasSessions", TAG);
        this.sessionSupplierModule.hasSessions(promise);
    }

    @ReactMethod
    public void sessionSupplierQuerySessionConnected(ReadableMap readableMap) {
        Logger.d("%s: sessionSupplierQuerySessionConnected", TAG);
        this.sessionSupplierModule.querySessionConnected(readableMap);
    }

    @ReactMethod
    public void sessionSupplierQuerySessionCreated(ReadableMap readableMap) {
        Logger.d("%s: sessionSupplierQuerySessionCreated", TAG);
        this.sessionSupplierModule.querySessionCreated(readableMap);
    }

    @ReactMethod
    public void sessionSupplierQuerySessionDisconnected(ReadableMap readableMap) {
        Logger.d("%s: sessionSupplierQuerySessionDisconnected", TAG);
        this.sessionSupplierModule.querySessionDisconnected(readableMap);
    }

    @ReactMethod
    public void sessionSupplierQuerySessionFailed(ReadableMap readableMap) {
        Logger.d("%s: sessionSupplierQuerySessionFailed", TAG);
        this.sessionSupplierModule.querySessionFailed(readableMap);
    }

    @ReactMethod
    public void sessionSupplierQuerySessionReleased(ReadableMap readableMap) {
        Logger.d("%s: sessionSupplierQuerySessionReleased", TAG);
        this.sessionSupplierModule.querySessionReleased(readableMap);
    }

    @ReactMethod
    public void sessionSupplierQuerySessionTransportChanged(ReadableMap readableMap) {
        Logger.d("%s: sessionSupplierQuerySessionTransportChanged", TAG);
        this.sessionSupplierModule.querySessionTransportChanged(readableMap);
    }

    @ReactMethod
    public void sessionSupplierReleaseSession(String str, Promise promise) {
        Logger.d("%s: sessionSupplierReleaseSession, identifier: %s", TAG, str);
        this.sessionSupplierModule.releaseSession(str, promise);
    }

    @ReactMethod
    public void setAccessoryStateBoolean(String str, int i, boolean z, final Promise promise) {
        Logger.d("%s: setAccessoryStateBoolean, identifier: %s, key: %d, value: %s", TAG, str, Integer.valueOf(i), Boolean.valueOf(z));
        Completable completable = this.clientAccessories.getSessionSupplier().getSession(str).getStateRepository().set(StateOuterClass.State.newBuilder().setFeature(i).setBoolean(z).mo10084build());
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$PlNW4E6fw2g_w8LKo86SX289BR4
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(true);
            }
        };
        promise.getClass();
        completable.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void setAccessoryStateInteger(String str, int i, int i2, final Promise promise) {
        Logger.d("%s: setAccessoryStateInteger, identifier: %s, key: %d, value: %d", TAG, str, Integer.valueOf(i), Integer.valueOf(i2));
        Completable completable = this.clientAccessories.getSessionSupplier().getSession(str).getStateRepository().set(StateOuterClass.State.newBuilder().setFeature(i).setInteger(i2).mo10084build());
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$4FNCz08gkWC25jzo4YQ0mDc3y-4
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(true);
            }
        };
        promise.getClass();
        completable.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void setAudioDelay(int i, Promise promise) {
        Logger.d("%s: setAudioDelay, delay: %d", TAG, Integer.valueOf(i));
        try {
            AudioDelayUtils.writeAudioDelayToConfigFile(i, getReactApplicationContext());
            promise.resolve(null);
        } catch (JsonIOException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error serializing audio delay: ");
            outline107.append(e.getMessage());
            Log.e(MODULE_NAME, outline107.toString());
            promise.reject(e);
        } catch (IOException e2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Error writing audio delay: ");
            outline1072.append(e2.getMessage());
            Log.e(MODULE_NAME, outline1072.toString());
            promise.reject(e2);
        }
    }

    @ReactMethod
    public void setBehaviorConfiguration(String str, int i, ReadableMap readableMap, final Promise promise) {
        Logger.d("%s: setBehaviorConfiguration, identifier: %s, deviceId: %d", TAG, str, Integer.valueOf(i));
        Completable configuration = this.clientAccessories.getSessionSupplier().getSession(str).getInputRepository().setConfiguration(i, InputBehaviorConfigurationTransformer.configFromReadable(readableMap));
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$d5yhpCa_Rizp87BWEU8rB-gWbGo
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(null);
            }
        };
        promise.getClass();
        configuration.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void shouldRequestEnableBluetooth(Promise promise) {
        Logger.d("%s: shouldRequestEnableBluetooth", TAG);
        promise.resolve(Boolean.valueOf(com.amazon.alexa.accessory.Accessories.shouldRequestEnableBluetooth(getReactApplicationContext())));
    }

    @ReactMethod
    public void shouldRequestIgnoreBatteryOptimizations(Promise promise) {
        Logger.d("%s: shouldRequestIgnoreBatteryOptimizations", TAG);
        promise.resolve(Boolean.valueOf(com.amazon.alexa.accessory.Accessories.shouldRequestIgnoreBatteryOptimizations(getReactApplicationContext())));
    }

    @ReactMethod
    public void shouldTransportUpgrade(String str, Promise promise) {
        Logger.d("%s: shouldTransportUpgrade, identifier: %s", TAG, str);
        Single<Boolean> shouldUpgrade = this.clientAccessories.getAccessorySession(str).getTransportRepository().shouldUpgrade();
        promise.getClass();
        $$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE __lambda_nrvbijjk1binhmcqxsdc6utczse = new $$Lambda$nrVbIJjK1bINHmCQxSDC6utCZSE(promise);
        promise.getClass();
        shouldUpgrade.subscribe(__lambda_nrvbijjk1binhmcqxsdc6utczse, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void showLocalNotification(int i, ReadableMap readableMap, Promise promise) {
        Logger.d("%s: showLocalNotification, id: %d", TAG, Integer.valueOf(i));
        this.localNotificationModule.show(i, readableMap, promise);
    }

    @ReactMethod
    public void showNotificationAccess(Promise promise) {
        Logger.d("%s: showNotificationAccess", TAG);
        this.focusFilterBridgeModule.showNotificationAccess(promise);
    }

    @ReactMethod
    public void startConnectionEvents(Promise promise) {
        promise.resolve(null);
    }

    @VisibleForTesting
    void startEmittingBridgeEvents() {
        Logger.d("AccessoryModule: startEmittingBluetoothEvents");
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$UNJnwUs2EDOu6upHqTgevUe9UB8
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryModule.this.lambda$startEmittingBridgeEvents$44$AccessoryModule();
            }
        });
        Observable<Accessory> observeSessionConnected = this.clientAccessories.getSessionSupplier().observeSessionConnected();
        final GlobalSessionListener globalSessionListener = this.globalSessionListener;
        globalSessionListener.getClass();
        Disposable subscribe = observeSessionConnected.subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$6iQ3U9tLtQrrLnL3KBLR3oBOkDk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GlobalSessionListener.this.onAccessorySessionConnected((Accessory) obj);
            }
        }, $$Lambda$AccessoryModule$eMaUTY_Wf3_XY9M4fT6PnP3R0.INSTANCE);
        Observable<Accessory> observeSessionReleased = this.clientAccessories.getSessionSupplier().observeSessionReleased();
        final GlobalSessionListener globalSessionListener2 = this.globalSessionListener;
        globalSessionListener2.getClass();
        Disposable subscribe2 = observeSessionReleased.subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$M1W790RH0NWpJI9B8pc8Y9a6yk4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                GlobalSessionListener.this.onAccessorySessionReleased((Accessory) obj);
            }
        }, $$Lambda$AccessoryModule$3Qiap1WmjkMjkhxPuzf7QAb7coY.INSTANCE);
        this.compositeDisposable.add(subscribe);
        this.compositeDisposable.add(subscribe2);
    }

    @ReactMethod
    public void startSetup(final String str, final Promise promise) {
        Logger.d("%s: startSetup, identifier: %s", TAG, str);
        AccessorySession accessorySession = this.clientAccessories.getAccessorySession(str);
        final DeviceRepositoryV2 deviceRepository = accessorySession.getDeviceRepository();
        Completable flatMapCompletable = accessorySession.queryConnectionStatus().flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$Dni33vJrhjt3HVqx_koYrMG8VLc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessoryModule.this.lambda$startSetup$21$AccessoryModule(str, deviceRepository, (ConnectionStatus) obj);
            }
        });
        Action action = new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryModule$GGgFLBOlv9038gBXimHVB_nd2fE
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                Promise.this.resolve(true);
            }
        };
        promise.getClass();
        flatMapCompletable.subscribe(action, new $$Lambda$uy64BVvzETLGSEBSP57B5ZCU4o(promise));
    }

    @ReactMethod
    public void stateRepositoryQuery(ReadableMap readableMap, String str, int i) {
        Logger.d("%s: stateRepositoryQuery, identifier: %s, key: %d", TAG, str, Integer.valueOf(i));
        this.stateRepositoryModule.query(readableMap, str, i);
    }

    @ReactMethod
    public void stateRepositorySet(String str, ReadableMap readableMap, Promise promise) {
        Logger.d("%s: stateRepositorySet, identifier: %s", TAG, str);
        this.stateRepositoryModule.set(str, readableMap, promise);
    }

    @ReactMethod
    public void stopConnectionEvents(Promise promise) {
        promise.resolve(null);
    }

    @ReactMethod
    public void systemRepositoryConnectUser(String str, String str2, Promise promise) {
        Logger.d("%s: systemRepositoryConnectUser, identifier: %s, userAddress: %s", TAG, str, str2);
        this.systemRepositoryModule.connectUser(str, str2, promise);
    }

    @ReactMethod
    public void systemRepositoryDisconnectUser(String str, String str2, Promise promise) {
        Logger.d("%s: systemRepositoryDisconnectUser, identifier: %s, userAddress: %s", TAG, str, str2);
        this.systemRepositoryModule.disconnectUser(str, str2, promise);
    }

    @ReactMethod
    public void systemRepositoryQueryLocales(ReadableMap readableMap, String str) {
        Logger.d("%s: systemRepositoryQueryLocales, identifier: %s", TAG, str);
        this.systemRepositoryModule.queryLocales(readableMap, str);
    }

    @ReactMethod
    public void systemRepositoryQueryUsers(ReadableMap readableMap, String str) {
        Logger.d("%s: systemRepositoryQueryUsers, identifier: %s", TAG, str);
        this.systemRepositoryModule.queryUsers(readableMap, str);
    }

    @ReactMethod
    public void systemRepositorySetLocale(String str, String str2, Promise promise) {
        Logger.d("%s: systemRepositorySetLocale, identifier: %s, locale: %s", TAG, str, str2);
        this.systemRepositoryModule.setLocale(str, str2, promise);
    }

    @ReactMethod
    public void systemRepositoryUnpairUser(String str, String str2, Promise promise) {
        Logger.d("%s: systemRepositoryUnpairUseer, identifier: %s, userAddress: %s", TAG, str, str2);
        this.systemRepositoryModule.unpairUser(str, str2, promise);
    }

    @ReactMethod
    public void transportRepositoryRequestUpgrade(String str, Promise promise) {
        Logger.d("%s: transportRepositoryRequestUpgrade, identifier: %s", TAG, str);
        this.transportRepositoryModule.requestUpgrade(str, promise);
    }

    @ReactMethod
    public void transportRepositoryShouldUpgrade(String str, Promise promise) {
        Logger.d("%s: transportRepositoryShouldUpgrade, identifier: %s", TAG, str);
        this.transportRepositoryModule.shouldUpgrade(str, promise);
    }

    @ReactMethod
    public void unmatchedLocaleNotifierMarkLocaleNotified(String str, String str2, Promise promise) {
        Logger.d("%s: unmatchedLocaleNotifierMarkLocaleNotified, identifier: %s, locale: %s", TAG, str, str2);
        this.unmatchedLocaleModule.markLocaleAsNotified(str, str2);
        promise.resolve(null);
    }

    public AccessoryModule(ReactApplicationContext reactApplicationContext, Lazy<Mobilytics> lazy) {
        this(reactApplicationContext, new MetricRecorder(lazy));
    }

    private AccessoryModule(ReactApplicationContext reactApplicationContext, AccessoryMetricsService accessoryMetricsService) {
        super(reactApplicationContext);
        this.reactApplicationContext = reactApplicationContext;
        this.featureChecker = AccessoriesFactory.featureChecker;
        Preconditions.notNull(this.featureChecker, "featureChecker");
        Preconditions.notNull(accessoryMetricsService, "metricsService");
        this.accessoryMetricsService = accessoryMetricsService;
        this.nativeContainerProvider = new NativeContainerProvider();
        this.clientAccessories = AccessoryClientProvider.getOrCreateConnectedClient(reactApplicationContext, accessoryMetricsService).getAccessories();
        this.eventEmitter = RNEventEmitter.getInstance();
        this.accessoryInteractor = new AccessoryInteractor(this.featureChecker, this.clientAccessories, accessoryMetricsService);
        this.mainThreadHandler = new Handler(reactApplicationContext.getMainLooper());
        this.activityLauncher = new AccessoryActivityLauncher(reactApplicationContext);
        this.modelTransformer = new ModelTransformer(this.nativeContainerProvider);
        this.globalSessionListener = new GlobalSessionListener(reactApplicationContext, this.nativeContainerProvider);
        this.bluetoothStateMonitor = new DefaultBluetoothStateMonitor(reactApplicationContext);
        this.bluetoothStateObserver = new ReactBluetoothStateObserver(reactApplicationContext, this.nativeContainerProvider);
        this.locationStateMonitor = new DefaultLocationStateMonitor(reactApplicationContext);
        this.locationStateObserver = new ReactLocationStateObserver(reactApplicationContext, this.nativeContainerProvider);
        this.localNotificationModule = new LocalNotificationModule(reactApplicationContext);
        this.rxRN = new RxRN(this.eventEmitter, this.nativeContainerProvider);
        this.registrationSupplierModule = new RegistrationSupplierModule(this.nativeContainerProvider, this.rxRN, this.clientAccessories);
        this.rxRNCleanupListener = new RxRN.RxRNCleanupListener(this.rxRN);
        this.stateRepositoryModule = new StateRepositoryModule(this.nativeContainerProvider, this.rxRN, this.clientAccessories.getSessionSupplier());
        this.firmwareRepositoryModule = new FirmwareRepositoryModule(this.nativeContainerProvider, this.rxRN, this.clientAccessories.getSessionSupplier());
        this.systemRepositoryModule = new SystemRepositoryModule(this.nativeContainerProvider, this.rxRN, this.clientAccessories.getSessionSupplier());
        this.deviceRepositoryModule = new DeviceRepositoryModule(this.nativeContainerProvider, this.rxRN, this.clientAccessories.getSessionSupplier(), accessoryMetricsService);
        this.inputRepositoryModule = new InputRepositoryModule(this.nativeContainerProvider, this.rxRN, this.clientAccessories.getSessionSupplier());
        this.transportRepositoryModule = new TransportRepositoryModule(this.clientAccessories.getSessionSupplier());
        this.deviceSupplierModule = new DeviceSupplierModule(this.nativeContainerProvider, this.rxRN, this.clientAccessories.getDeviceSupplier(), new CurrentTimeMillisProvider());
        this.accessorySupplierModule = new AccessorySupplierModule(this.nativeContainerProvider, this.clientAccessories);
        this.accessoryScannerModule = new AccessoryScannerModule(this.rxRN, this.nativeContainerProvider, this.clientAccessories.getScanner());
        this.sessionSupplierModule = new SessionSupplierModule(this.rxRN, this.nativeContainerProvider, this.clientAccessories.getSessionSupplier());
        this.accessoryModelTransformer = new AccessoryModelTransformer(this.nativeContainerProvider);
        this.focusFilterBridgeModule = new FocusFilterBridgeModule(reactApplicationContext);
        this.focusFilterInfoSupplierModule = new FocusFilterInfoSupplierModule(this.rxRN, this.nativeContainerProvider, this.focusFilterBridgeModule);
        this.finishSetupModule = new FinishSetupModule(new FinishSetupEvent.Transformer(this.nativeContainerProvider), reactApplicationContext);
        this.unmatchedLocaleModule = new UnmatchedLocaleModule(reactApplicationContext);
        this.compositeDisposable = new CompositeDisposable();
        FASCardPresenter fASCardPresenter = FASCardPresenter.getInstance();
        Preconditions.notNull(fASCardPresenter, "fasCardPresenter");
        this.fasCardPresenter = fASCardPresenter;
        this.cloudPairingRepositoryModule = new CloudPairingRepositoryModule(this.clientAccessories.getSessionSupplier(), this.nativeContainerProvider);
        addReactLifecycleEventListeners();
    }

    @VisibleForTesting
    AccessoryModule(ReactApplicationContext reactApplicationContext, Handler handler, AccessoryInteractor accessoryInteractor, ModelTransformer modelTransformer, AccessoryActivityLauncher accessoryActivityLauncher, GlobalSessionListener globalSessionListener, AccessoryMetricsService accessoryMetricsService, BluetoothStateMonitor bluetoothStateMonitor, BluetoothStateMonitor.Observer observer, LocationStateMonitor locationStateMonitor, LocationStateMonitor.Observer observer2, FocusFilterBridgeModule focusFilterBridgeModule, FocusFilterInfoSupplierModule focusFilterInfoSupplierModule, FeatureChecker featureChecker, Accessories accessories, CompositeDisposable compositeDisposable, DeviceSupplierModule deviceSupplierModule, FASCardPresenter fASCardPresenter, RegistrationSupplierModule registrationSupplierModule, CloudPairingRepositoryModule cloudPairingRepositoryModule, FirmwareRepositoryModule firmwareRepositoryModule) {
        super(reactApplicationContext);
        this.reactApplicationContext = reactApplicationContext;
        this.clientAccessories = accessories;
        this.accessoryInteractor = accessoryInteractor;
        this.mainThreadHandler = handler;
        this.modelTransformer = modelTransformer;
        this.activityLauncher = accessoryActivityLauncher;
        this.globalSessionListener = globalSessionListener;
        this.bluetoothStateMonitor = bluetoothStateMonitor;
        this.locationStateMonitor = locationStateMonitor;
        this.bluetoothStateObserver = observer;
        this.locationStateObserver = observer2;
        this.focusFilterBridgeModule = focusFilterBridgeModule;
        this.focusFilterInfoSupplierModule = focusFilterInfoSupplierModule;
        this.deviceSupplierModule = deviceSupplierModule;
        this.registrationSupplierModule = registrationSupplierModule;
        this.localNotificationModule = null;
        this.stateRepositoryModule = null;
        this.firmwareRepositoryModule = firmwareRepositoryModule;
        this.systemRepositoryModule = null;
        this.deviceRepositoryModule = null;
        this.inputRepositoryModule = null;
        this.transportRepositoryModule = null;
        this.accessorySupplierModule = null;
        this.accessoryScannerModule = null;
        this.sessionSupplierModule = null;
        this.eventEmitter = RNEventEmitter.getInstance();
        this.rxRN = null;
        this.rxRNCleanupListener = null;
        this.nativeContainerProvider = null;
        this.accessoryModelTransformer = null;
        this.finishSetupModule = null;
        this.unmatchedLocaleModule = null;
        this.featureChecker = featureChecker;
        this.compositeDisposable = compositeDisposable;
        this.accessoryMetricsService = accessoryMetricsService;
        this.fasCardPresenter = fASCardPresenter;
        this.cloudPairingRepositoryModule = cloudPairingRepositoryModule;
        addReactLifecycleEventListeners();
    }
}
