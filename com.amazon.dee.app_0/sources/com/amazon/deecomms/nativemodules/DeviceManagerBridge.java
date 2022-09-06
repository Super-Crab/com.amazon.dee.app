package com.amazon.deecomms.nativemodules;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.initiation.InitiationLogicContract;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.calling.util.CallInitiator;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.nativemodules.model.CallType;
import com.amazon.deecomms.nativemodules.model.Person;
import com.amazon.deecomms.nativemodules.util.ContactsDataStoreUtil;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
/* loaded from: classes12.dex */
public class DeviceManagerBridge extends ReactContextBaseJavaModule {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DeviceManagerBridge.class);
    private static final String REACT_BRIDGE_NAME = "CommsDeviceManager";
    private final CallInitiator mCallInitiator;
    private final CapabilitiesManager mCapabilitiesManager;
    private final CommsIdentityManager mCommsIdentityManager;
    private final ContactsDataStoreUtil mContactsDataStoreUtil;
    private final InitiationLogicFactory mInitiationLogicFactory;

    public DeviceManagerBridge(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, new ContactsDataStoreUtil(CommsDaggerWrapper.getComponent().getContext()), CommsDaggerWrapper.getComponent().getInitiationLogicFactory(), CommsDaggerWrapper.getComponent().getCommsIdentityManager(), CommsDaggerWrapper.getComponent().getCapabilitiesManager(), new CallInitiator(MetricKeys.DEVICE_CALL_INITIATED_FROM_CONTACT_RN));
    }

    private void makeDeviceCall(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, @NonNull String str5, @NonNull Promise promise) {
        try {
            CallType valueOf = CallType.valueOf(str);
            if (valueOf != CallType.AUDIO && valueOf != CallType.VIDEO) {
                GeneratedOutlineSupport.outline3("Initiating a call failed due to incorrect device call type. Call type: ", str, LOG);
                promise.reject((String) null, "Invalid call type");
                return;
            }
            CallHelper callHelper = new CallHelper();
            Activity currentActivity = getCurrentActivity();
            GeneratedOutlineSupport.outline4("Initiating a Device call. Call type: ", str, LOG);
            InitiationLogicContract create = this.mInitiationLogicFactory.create(this.mCallInitiator, currentActivity, currentActivity, callHelper, MetricKeys.SCREEN_NAME_CONVO_LIST, str2);
            if (str4 == null) {
                String iDToUseForAllDevicesCall = getIDToUseForAllDevicesCall(str3);
                if (valueOf == CallType.AUDIO) {
                    create.initiateAudioCall(iDToUseForAllDevicesCall, str5);
                } else {
                    create.initiateVideoCall(iDToUseForAllDevicesCall, str5);
                }
            } else {
                create.initiateDeviceTargetedCall(str3, str5, str4, valueOf == CallType.VIDEO);
            }
            LOG.i("Device Call started successfully.");
            promise.resolve(null);
        } catch (Exception unused) {
            GeneratedOutlineSupport.outline3("Initiating a call failed due to incorrect device call type. Call type: ", str, LOG);
            promise.reject((String) null, "Invalid call type");
        }
    }

    @ReactMethod
    public void callDevice(String str, String str2, String str3, String str4, String str5, Promise promise) {
        Person personByServerIdFromDatabase = this.mContactsDataStoreUtil.getPersonByServerIdFromDatabase(str);
        if (personByServerIdFromDatabase != null && personByServerIdFromDatabase.getCommsIds() != null && !personByServerIdFromDatabase.getCommsIds().isEmpty()) {
            makeDeviceCall(str2, str3, personByServerIdFromDatabase.getCommsIds().get(0), str5, str4, promise);
            return;
        }
        LOG.e("callDevice: Query execution failed!");
        promise.reject((String) null, "Invalid contact id");
    }

    public String getIDToUseForAllDevicesCall(@NonNull String str) {
        return this.mCommsIdentityManager.getCommsId(DeviceManagerBridge.class.getName(), false);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_BRIDGE_NAME;
    }

    @ReactMethod
    public void makeAllDevicesCall(String str, String str2, String str3, String str4, String str5, Promise promise) {
        makeDeviceCall(str, str2, str4, str5, str3, promise);
    }

    public DeviceManagerBridge(@NonNull ReactApplicationContext reactApplicationContext, @NonNull ContactsDataStoreUtil contactsDataStoreUtil, @NonNull InitiationLogicFactory initiationLogicFactory, @NonNull CommsIdentityManager commsIdentityManager, @NonNull CapabilitiesManager capabilitiesManager, @NonNull CallInitiator callInitiator) {
        super(reactApplicationContext);
        this.mContactsDataStoreUtil = contactsDataStoreUtil;
        this.mCallInitiator = callInitiator;
        this.mInitiationLogicFactory = initiationLogicFactory;
        this.mCommsIdentityManager = commsIdentityManager;
        this.mCapabilitiesManager = capabilitiesManager;
    }
}
