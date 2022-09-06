package com.amazon.deecomms.nativemodules;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.sipclient.SipStatusCode;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.calling.util.CallInitiator;
import com.amazon.deecomms.calling.util.SetupCallHelper;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes12.dex */
public class CallingManagerBridge extends ReactContextBaseJavaModule {
    private static final String CONCATENATE = ".";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallingManagerBridge.class);
    private final CallInitiator mCallInitiator;
    private final InitiationLogicFactory mInitiationLogicFactory;
    private final ReactBridgeSerializer mReactBridgeSerializer;

    public CallingManagerBridge(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, new CallInitiator(MetricKeys.CONTACT_CALL_INITIATED_FROM_RN_SCREEN), CommsDaggerWrapper.getComponent().getInitiationLogicFactory());
    }

    @NonNull
    private String getCallInitScreenName(String str, String str2) {
        return (str2 == null || str2.isEmpty()) ? str : GeneratedOutlineSupport1.outline75(str, ".", str2);
    }

    private String getCommsIdFromContactMap(@NonNull ReadableMap readableMap) {
        if (readableMap.hasKey("commsIds")) {
            ReadableArray array = readableMap.getArray("commsIds");
            if (array.size() > 0) {
                return array.getString(0);
            }
            LOG.e("commsId array in contact map is empty.");
        }
        if (readableMap.hasKey("homeGroupCommsId")) {
            return readableMap.getString("homeGroupCommsId");
        }
        LOG.e("Could not get a commsId from contact map");
        return null;
    }

    private String getFullName(@NonNull ReadableMap readableMap) {
        String string = readableMap.getString(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME);
        ContactName contactName = null;
        if (readableMap.hasKey("name")) {
            if (ReactBridgeSerializer.PERSON_TYPE_DISCRIMINATOR.equals(string)) {
                contactName = this.mReactBridgeSerializer.createPersonContactName(readableMap.mo6945getMap("name"));
            } else if (ReactBridgeSerializer.HOMEGROUP_TYPE_DISCRIMINATOR.equals(string)) {
                contactName = new ContactName(readableMap.getString("name"), null, null);
            } else {
                LOG.e("getFullName: Invalid contact discriminator value. Proceeding with \"Unknown\" name.");
            }
        }
        return ContactUtils.getFullName(contactName);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsCallingManager";
    }

    @ReactMethod
    public void makeDropInCall(@NonNull ReadableMap readableMap, @NonNull String str, @Nullable String str2, boolean z, @NonNull String str3, @Nullable String str4, Promise promise) {
        String commsIdFromContactMap = getCommsIdFromContactMap(readableMap);
        if (commsIdFromContactMap == null) {
            MetricsHelper.recordOperationalMetricWithSource(MetricKeys.EMPTY_COMMS_ID, "makeDropInCall");
            promise.reject((String) null, "ContactMap does not contain a valid comms id.");
            return;
        }
        CallHelper callHelper = new CallHelper();
        String fullName = str2 != null ? str2 : getFullName(readableMap);
        Activity currentActivity = getCurrentActivity();
        this.mInitiationLogicFactory.create(this.mCallInitiator, currentActivity, currentActivity, callHelper, null, getCallInitScreenName(str3, str4)).initiateTargetedDropIn(commsIdFromContactMap, str4, str, fullName, z, true);
        promise.resolve(null);
    }

    @ReactMethod
    public void showEndCallScreenOnError(@NonNull ReadableMap readableMap, @NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4, Promise promise) {
        String commsIdFromContactMap = getCommsIdFromContactMap(readableMap);
        if (commsIdFromContactMap == null) {
            MetricsHelper.recordOperationalMetricWithSource(MetricKeys.EMPTY_COMMS_ID, "showEndCallScreenOnError");
            promise.reject((String) null, "ContactMap does not contain a valid comms id.");
            return;
        }
        SetupCallHelper.MetadataBuilder newBuilder = SetupCallHelper.MetadataBuilder.newBuilder();
        newBuilder.withCallType(CallType.VIDEO_DEVICE_TARGETED_DROP_IN).withCallOrigin(Call.Side.Local).withSource(SetupCallHelper.Source.SipCallPreparation).withReason(str2);
        SetupCallHelper.recordInitiationMetrics(null, null, SetupCallHelper.ResultType.EXPECTED, Integer.valueOf(SipStatusCode.TEMPORARILY_UNAVAILABLE.getCode()), newBuilder);
        new CallHelper().withRecipientID(commsIdFromContactMap).withDisplayTitleName(getFullName(readableMap)).setupNotAvailableScreenWithErrorMessage(str);
        promise.resolve(null);
    }

    public CallingManagerBridge(@NonNull ReactApplicationContext reactApplicationContext, @NonNull CallInitiator callInitiator, @NonNull InitiationLogicFactory initiationLogicFactory) {
        super(reactApplicationContext);
        this.mCallInitiator = callInitiator;
        this.mInitiationLogicFactory = initiationLogicFactory;
        this.mReactBridgeSerializer = new ReactBridgeSerializer(reactApplicationContext.getApplicationContext());
    }
}
