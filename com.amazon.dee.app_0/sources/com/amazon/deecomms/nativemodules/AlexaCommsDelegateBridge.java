package com.amazon.deecomms.nativemodules;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.MarketplaceUtils;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.acmsrecipes.GetDeviceCommsPreferences;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsComponent;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.exceptions.InvalidCommsIdentityException;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes12.dex */
public class AlexaCommsDelegateBridge extends ReactContextBaseJavaModule {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AlexaCommsDelegateBridge.class);
    static final String RN_PROVISION_STATUS_AUTO_PROVISIONED = "AutoProvisioned";
    static final String RN_PROVISION_STATUS_DEPROVISIONED = "Deprovisioned";
    static final String RN_PROVISION_STATUS_NONE = "None";
    static final String RN_PROVISION_STATUS_PROVISIONED = "Provisioned";
    private final ApplicationManager applicationManager;
    private final CapabilitiesManager capabilitiesManager;
    private final CommsComponent mCommsComponent;
    private final ReactBridgeSerializer mReactBridgeSerializer;

    /* renamed from: com.amazon.deecomms.nativemodules.AlexaCommsDelegateBridge$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$contacts$model$enums$CommsProvisionStatus = new int[CommsProvisionStatus.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$contacts$model$enums$CommsProvisionStatus[CommsProvisionStatus.PROVISIONED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$contacts$model$enums$CommsProvisionStatus[CommsProvisionStatus.DEPROVISIONED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$contacts$model$enums$CommsProvisionStatus[CommsProvisionStatus.AUTO_PROVISIONED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$contacts$model$enums$CommsProvisionStatus[CommsProvisionStatus.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public AlexaCommsDelegateBridge(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mCommsComponent = CommsDaggerWrapper.getComponent();
        this.mReactBridgeSerializer = new ReactBridgeSerializer(reactApplicationContext.getApplicationContext());
        this.capabilitiesManager = this.mCommsComponent.getCapabilitiesManager();
        this.applicationManager = this.mCommsComponent.getApplicationManager();
    }

    @VisibleForTesting
    static void setCommsEnabledToggleHelper(@NonNull final boolean z, @NonNull final boolean z2, @NonNull final ApplicationManager applicationManager) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.deecomms.nativemodules.AlexaCommsDelegateBridge.1
            @Override // java.lang.Runnable
            public void run() {
                GetDeviceCommsPreferences.cleanUpCommsIfDisabled(z, z2, applicationManager);
            }
        });
    }

    @ReactMethod
    public void getCommsAllowed(Promise promise) {
        boolean z = false;
        boolean isCommsSupportedInMarketplace = MarketplaceUtils.isCommsSupportedInMarketplace(this.mCommsComponent.getCommsIdentityManager().getPreferredMarketplace(false));
        boolean diagnosticsValue = this.mCommsComponent.getCapabilitiesManager().getDiagnosticsValue();
        if (isCommsSupportedInMarketplace || diagnosticsValue) {
            z = true;
        }
        promise.resolve(Boolean.valueOf(z));
    }

    @ReactMethod
    public void getCommsEnabledToggle(Promise promise) {
        promise.resolve(Boolean.valueOf(GetDeviceCommsPreferences.isDeviceCommunicationsEnabled(getReactApplicationContext().getApplicationContext(), this.mCommsComponent.getCapabilitiesManager())));
    }

    @ReactMethod
    public void getDeviceSerialNumber(Promise promise) {
        try {
            promise.resolve(Utils.getValueFromDataStore(getReactApplicationContext().getApplicationContext(), DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER));
        } catch (DeviceDataStoreException e) {
            LOG.e("Error getting device serial number.");
            promise.reject(e);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsAlexaCommsDelegate";
    }

    @ReactMethod
    public void getProvisionStatus(Promise promise) {
        int ordinal = this.mCommsComponent.getCommsIdentityManager().getProvisionStatus(true, "AlexaCommsDelegateBridge.getProvisionStatus", false).ordinal();
        promise.resolve(ordinal != 1 ? ordinal != 2 ? ordinal != 3 ? "None" : RN_PROVISION_STATUS_DEPROVISIONED : RN_PROVISION_STATUS_PROVISIONED : RN_PROVISION_STATUS_AUTO_PROVISIONED);
    }

    @ReactMethod
    public void identityUpdated(ReadableMap readableMap, Promise promise) {
        ContactName name = this.mReactBridgeSerializer.getPersonObjectFromReadableMap(readableMap).getName();
        if (name != null) {
            try {
                this.mCommsComponent.getCommsIdentityManager().updateUserNames(name);
            } catch (InvalidCommsIdentityException e) {
                promise.reject("An error occurred when trying to update the current commsIdentity name", e);
                return;
            }
        }
        promise.resolve(null);
    }

    @ReactMethod
    public void setCommsEnabledToggle(boolean z, @NonNull Promise promise) {
        if (!Utils.isFireOS() || !this.capabilitiesManager.isFireOSCommsGatingEnabled()) {
            return;
        }
        Context applicationContext = getReactApplicationContext().getApplicationContext();
        boolean isDeviceCommunicationsEnabled = GetDeviceCommsPreferences.isDeviceCommunicationsEnabled(applicationContext, this.capabilitiesManager);
        GetDeviceCommsPreferences.setCommunicationsPreference(applicationContext, this.mCommsComponent.getPushNotificationManager(), z, this.capabilitiesManager);
        setCommsEnabledToggleHelper(isDeviceCommunicationsEnabled, z, this.applicationManager);
        promise.resolve(null);
    }

    @ReactMethod
    public void shouldShowComms(Promise promise) {
        getCommsAllowed(promise);
    }
}
